package com.naltynbekkz.core

interface BaseActivity {

    fun hideOverlay()

    fun showSuccess(message: String)

    fun showError(message: String?)

    fun hideBottomNav()

    fun showBottomNav()

    fun logout()
}