package com.fragmentoapps.kotlin_example_1.pages

import android.content.ContentValues
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fragmentoapps.kotlin_example_1.R
import com.fragmentoapps.kotlin_example_1.database.DatabaseHelper
import com.fragmentoapps.kotlin_example_1.methods.PublicMethods
import com.fragmentoapps.kotlin_example_1.models.ProgramLanguage
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateLanguagePage : AppCompatActivity() {
    private lateinit var fabFinish: FloatingActionButton
    private lateinit var txtName: TextView
    private lateinit var txtDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_language_page)

        title = "Create Language"
        fabFinish = findViewById(R.id.fabFinishLanguage)
        txtName = findViewById(R.id.txtNameCreateLanguage)
        txtDescription = findViewById(R.id.txtDescriptionCreateLanguage)
        fabFinish.setOnClickListener { view ->
            var myName: String = txtName.text.toString()
            var myDescription: String = txtDescription.text.toString()

            var language = ProgramLanguage(null, myName, myDescription)
            saveData(language)
        }
    }

    fun saveData(language: ProgramLanguage) {
        try {
            val dbHelper = DatabaseHelper(this)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name", language.name)
                put("description", language.desciption)
            }

            val newRowId = db?.insert("Languages", null, values)
            finish()
        } catch (error: Exception) {
            PublicMethods().showSnackBar(
                this.findViewById(android.R.id.content),
                "${error?.message}",
                true
            )
        }
    }
}