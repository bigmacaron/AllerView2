package kr.kro.fatcats.permissionmodule.util



fun Any?.permissionEmptyCheck(): Boolean {

    if (this == null) { return true }

    if (this is String && this.trim().isEmpty()) { return true }

    if (this is List<*>) { return this.isEmpty() }

    return false
}