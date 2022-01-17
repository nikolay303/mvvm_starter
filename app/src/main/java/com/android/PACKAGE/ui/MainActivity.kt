package com.android.PACKAGE.ui

import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.PACKAGE.R
import com.android.PACKAGE.databinding.ActivityMainBinding
import com.mvvm_starter.common.ui.BaseActivityVM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivityVM<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val binding: ActivityMainBinding by viewBinding()

    override val viewModel: MainViewModel by viewModels()

    override fun setupView(savedInstanceState: Bundle?) {

    }
}