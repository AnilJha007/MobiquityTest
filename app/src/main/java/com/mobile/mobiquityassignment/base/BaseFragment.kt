package com.mobile.mobiquityassignment.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.mobiquityassignment.ui.MobiquityActivity

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    /* override this method in fragment to update page title*/
    open fun setPageTitle(title: String) {
        getActionBar()?.let {
            it.title = title
            it.show()
        }
    }

    private fun getActionBar() = ((activity as? MobiquityActivity)?.supportActionBar)

    /* override this method in fragment to attach the layout*/
    abstract fun getLayoutResourceId(): Int
}