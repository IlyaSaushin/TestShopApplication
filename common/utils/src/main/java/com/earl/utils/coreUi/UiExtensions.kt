package com.earl.utils.coreUi

fun String.isEmailValid() : Boolean {
    return if (!this.contains("@")) {
        return false
    } else if (!this.contains(".")) {
        return false
    } else (!this.contains("mail")
            || !this.contains("yandex")
            || !this.contains("mail")
            || !this.contains("gmail"))
}
