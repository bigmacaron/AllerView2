package kr.kro.fatcats.permissionmodule.view

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.kro.fatcats.permissionmodule.PermissionListener
import kr.kro.fatcats.permissionmodule.util.dataStore

class PermissionActivity : AppCompatActivity() {

    companion object {
        var permissionListener: PermissionListener? = null
        fun startActivity(context: Context, intent: Intent, listener: PermissionListener) {
            context.startActivity(intent)
            permissionListener = listener
        }
        const val KEY_DIALOG = "key:dialog"
        const val KEY_PERMISSIONS = "key:permissions"
    }

    private var permissions: Array<String>? = null
    private var deniedPermission = listOf<String>()
    private var dialogShow = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        permissionListener?.let {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                finish()
            } else {
                if (intent.hasExtra(KEY_DIALOG)) {
                    dialogShow = intent.getBooleanExtra(KEY_DIALOG, false)
                }
                if (intent.hasExtra(KEY_PERMISSIONS)) {
                    permissions = intent.getStringArrayExtra(KEY_PERMISSIONS)
                    CoroutineScope(Dispatchers.Default).launch {
                        if (isPermissionDenied()) requestPermissionLaunch.launch(permissions) else finish()
                    }
                }
            }
        } ?: run { // 설정에서 권한을 해제 하면 kill 된 후 복귀 됨으로 null 처리
            Log.e(this@PermissionActivity.javaClass.simpleName, "Activity was killed by android system")
            finish()
        }
    }
    /*
    * Remove Activity Animation
    * */
    override fun onPause() {
        overridePendingTransition(0, 0)
        super.onPause()
    }
    /*
    * 종료 후 권한 승인 요청에 대한 결과를 리스너를 통해 전달
    * */
    override fun onDestroy() {
        if (deniedPermission.isNotEmpty()) {
            permissionListener?.onPermissionDenied(deniedPermission)
        } else {
            permissionListener?.onPermissionGranted()
        }
        super.onDestroy()
    }
    // 거부된 권한이 있는지 체크
    private fun isPermissionDenied(): Boolean {
        var isPermissionDenied = false
        val deniedList = arrayListOf<String>()
        permissions?.let {
            it.forEach { permission ->
                if (ContextCompat.checkSelfPermission(this@PermissionActivity, permission)
                    == PackageManager.PERMISSION_DENIED
                ) {
                    deniedList.add(permission)
                    isPermissionDenied = true
                }
            }
        }
        deniedPermission = deniedList
        return isPermissionDenied
    }
    //설정 화면에서 돌아왔을 때 권한 결과 값
    val requestSettingDetailLaunch =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            isPermissionDenied().run {
                finish()
            }
        }

    /*
    * 권한 요청 launch
    * */
    @RequiresApi(Build.VERSION_CODES.M)
    private val requestPermissionLaunch =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            deniedPermission = result.filter {
                !it.value
            }.map {
                it.key
            }

            CoroutineScope(Dispatchers.Default).launch {
                if (deniedPermission.isEmpty()) {
                    finish()
                } else {
                    var isShow = false
                    // 거부된 모든 권한 (명시적 거부와, 취소 포함 )
                    deniedPermission.forEachIndexed { index, permission ->
                        if (shouldShowRequestPermissionRationale(permission)) {
                            savePermissionHistory(this@PermissionActivity, permission)
                            finish()
                        } else {
                            // 사용자의 명시적 거부인 경우
                            if (getPermissionHistory(this@PermissionActivity, permission)) {
                                isShow = true
                            } else {
                                if(!isPermissionDenied()){
                                    // 마지막 인덱스 일때만 종료 로직을 수행 함
                                    if (index == deniedPermission.lastIndex) finish()
                                }else{
                                    isShow = true
                                }
                            }
                        }
                    }
                    showDialog(isShow)
                }
            }
        }

    // show Dialog
    private suspend fun showDialog(isShow: Boolean) = withContext(Dispatchers.Main) {
        if (dialogShow) {
            if (isShow) setContentView(FrameLayout(this@PermissionActivity))
        } else {
            finish()
        }
    }

    // save Permission History
    private suspend fun savePermissionHistory(context: Context, permission: String) {
        val key = booleanPreferencesKey(permission)
        context.dataStore.edit { preferences ->
            preferences[key] = true
        }
    }

    // get Permission History
    private suspend fun getPermissionHistory(context: Context, permission: String): Boolean {
        val key = booleanPreferencesKey(permission)
        return context.dataStore.data.map {
            it[key]
        }.firstOrNull() ?: false
    }


}