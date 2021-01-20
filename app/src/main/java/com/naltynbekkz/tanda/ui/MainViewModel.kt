package com.naltynbekkz.tanda.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.naltynbekkz.core.preferences.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
//    private val database: AppDatabase,
    sessionManager: SessionManager,
) : ViewModel() {

    fun logout(navigateToAuthActivity: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            // Here we should drop all tables except for auth_logs
//            database.accessDao().dropTable()
//            database.userDao().dropTable()
            navigateToAuthActivity()
        }
    }
}