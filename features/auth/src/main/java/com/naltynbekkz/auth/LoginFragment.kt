package com.naltynbekkz.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.naltynbekkz.core.network.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            val loginEnabled = remember { mutableStateOf(true) }
            Button(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    viewModel.login(Login(email = "admin@gmail.com", password = "adminPassword"))
                        .observe(
                            viewLifecycleOwner
                        ) {
                            when (it) {
                                is Result.Success -> {
                                    viewModel.save(it.data.id)
                                }
                                is Result.Error -> {
                                    loginEnabled.value = false

                                }
                                is Result.Loading -> {

                                }
                            }
                        }
                },
                enabled = loginEnabled.value
            ) {
                Text(text = stringResource(R.string.login))
            }
        }
    }
}