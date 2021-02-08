package com.mvvm_starter.common.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.mvvm_starter.common.R

/**
 * Created by nikolay on 17/03/2019
 */

class ProgressDialogFragment : BaseDialogFragment() {

    override val layoutResId: Int = R.layout.dialog_progress

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)
        isCancelable = false
    }
}