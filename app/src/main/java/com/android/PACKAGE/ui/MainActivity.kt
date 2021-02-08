package com.android.PACKAGE.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.android.PACKAGE.R
import com.mvvm_starter.common.ui.BaseActivityVM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivityVM<MainViewModel>() {

    override val layoutResId: Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {
    }
}