package br.com.challenge.presentation

import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun loadingManager(viewProgressBar: View, isShowLoading: Boolean) {
        if (isShowLoading) {
            viewProgressBar.visibility = View.VISIBLE
        } else {
            hideLoading(viewProgressBar)
        }
    }

    fun hideLoading(viewProgressBar: View) {
        viewProgressBar.visibility = View.GONE
    }
}