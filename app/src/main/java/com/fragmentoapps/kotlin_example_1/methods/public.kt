package com.fragmentoapps.kotlin_example_1.methods

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import com.fragmentoapps.kotlin_example_1.R
import com.google.android.material.snackbar.Snackbar

class PublicMethods {
    fun showSnackBar(view: View, text: String, isError: Boolean) {
        val snackBar = Snackbar.make(view, "$text", Snackbar.LENGTH_LONG)
        snackBar.setActionTextColor(Color.WHITE)
        val snackBarView = snackBar.view
        if (isError) snackBarView.setBackgroundColor(Color.argb(255, 150, 0, 0))
        else snackBarView.setBackgroundColor(Color.argb(255, 0, 150, 0))
        val textView = snackBarView.findViewById(R.id.snackbar_text) as TextView
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        snackBar.show()
    }
}