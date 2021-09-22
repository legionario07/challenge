package br.com.challenge.presentation

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun Int.hasCurrentFragment(): Boolean {
        return supportFragmentManager.findFragmentById(this).let { fragment ->
            fragment?.let { true } ?: kotlin.run { false }
        }
    }

    protected fun setCurrentFragment(containerId: Int, baseFagmentClass: BaseFragment) {
        val simpleName = baseFagmentClass.javaClass.simpleName
        supportFragmentManager.beginTransaction()
            .replace(containerId, baseFagmentClass, simpleName).commit()
    }
}