package com.earl.utils.coreUi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.earl.utils.navigation.NavigationContract

abstract class AuthBaseFragment<VB: ViewBinding> : Fragment() {

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) : VB

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected lateinit var navigator: NavigationContract.AuthNavigationContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        navigator = requireActivity().supportFragmentManager.findFragmentByTag(navHostAuth)
                as NavigationContract.AuthNavigationContract
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val navHostAuth = "navHostAuth"
    }
}