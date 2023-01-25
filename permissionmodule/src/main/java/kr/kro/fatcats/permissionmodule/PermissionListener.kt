package kr.kro.fatcats.permissionmodule

interface PermissionListener {
    fun onPermissionGranted()
    fun onPermissionDenied(deniedPermissions: List<String>)
}