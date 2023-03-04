package com.earl.utils.coreUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.earl.utils.navigation.NavigationContract

abstract class ShopBaseFragment<VB: ViewBinding> : Fragment() {

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) : VB

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    protected lateinit var navigator: NavigationContract.ShopNavigationContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        navigator = requireActivity().supportFragmentManager.findFragmentByTag(shopNavHostFrag)
                as NavigationContract.ShopNavigationContract
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val shopNavHostFrag = "shopNavHostFrag"
    }
}