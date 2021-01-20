package com.naltynbekkz.schools

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.naltynbekkz.core.preferences.SessionManager

class SchoolsViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun logout(){
        sessionManager.logout()
    }

}