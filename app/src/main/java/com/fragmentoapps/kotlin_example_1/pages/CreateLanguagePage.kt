package com.fragmentoapps.kotlin_example_1.pages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fragmentoapps.kotlin_example_1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateLanguagePage : AppCompatActivity() {
    private lateinit var fabFinish: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_language_page)

        title = "Create Language"
        fabFinish = findViewById(R.id.fabFinishLanguage)
        fabFinish.setOnClickListener { view ->
            finish()
        }
    }
}