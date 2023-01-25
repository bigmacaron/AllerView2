@file:Suppress("UNCHECKED_CAST")

package kr.kro.fatcats.permissionmodule.builder

import android.content.Intent
import kr.kro.fatcats.permissionmodule.view.PermissionActivity
import kr.kro.fatcats.permissionmodule.PermissionListener
import kr.kro.fatcats.permissionmodule.PermissionProvider
import kr.kro.fatcats.permissionmodule.util.permissionEmptyCheck

object Permission {

    fun create(): Builder = Builder()

    class Builder: PermissionBuilder<Builder>() {

        fun check() {
            checkPermissions()
        }
    }
}
abstract class PermissionBuilder<T : PermissionBuilder<T>> {

    private val context = PermissionProvider.providerContext

    private var permissions: Array<String> = emptyArray()
    private var listener: PermissionListener? = null

    private var dialog = false


        protected fun checkPermissions() {
        if (listener == null) {
            throw IllegalArgumentException("You must setPermissionListener() on com.pineone.permission.Permission")
        }
        else if (permissions.permissionEmptyCheck()) {
            throw IllegalArgumentException("You must setPermissions() on com.pineone.permission.Permission")
        }

        context.let {
            PermissionActivity.startActivity(it, createIntent(), listener!!)
        }
    }

    private fun createIntent(): Intent {
        return Intent().run {
            context.let {
                setClass(it, PermissionActivity::class.java)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION)
                /**
                 * Activity 자동 호출을 막는다. onUserLeaveHint() 실행 차단
                 * 사용자의 액션 없이 Activity가 실행 / 전환되는 경우에 호출되는 메서드
                 * ex) APlication 사용 중에 전화가 온다거나 등..
                 */
                addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION)
                putExtra(PermissionActivity.KEY_PERMISSIONS, permissions)
                putExtra(PermissionActivity.KEY_DIALOG, dialog )
            }
            this
        }
    }

    open fun setPermissionListener(listener: PermissionListener): T {
        this.listener = listener
        return this as T
    }

    open fun setPermissions(vararg permissions: String): T {
        permissions.also { this.permissions = it as Array<String> }
        return this as T
    }
    open fun useDialog(boolean: Boolean ) : T{
        dialog = boolean
        return this as T
    }

}