package com.fragmentoapps.kotlin_example_1

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fragmentoapps.kotlin_example_1.adapters.AdapterRVLanguage
import com.fragmentoapps.kotlin_example_1.models.ProgramLanguage
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterRVLanguage.OnItemClickListener {
    private lateinit var myRecyclerViewLanguage: RecyclerView
    private lateinit var myListLanguages: ArrayList<ProgramLanguage>

    val language = arrayOf(
        "C",
        "C++",
        "Java",
        ".Net",
        "Kotlin",
        "Ruby",
        "Rails",
        "Python",
        "Java Script",
        "Php",
        "Ajax",
        "Perl",
        "Hadoop"
    )
    val description = arrayOf(
        "C programming is considered as the base for other programming languages",
        "C++ is an object-oriented programming language.",
        "Java is a programming language and a platform.",
        ".NET is a framework which is used to develop software applications.",
        "Kotlin is a open-source programming language, used to develop Android apps and much more.",
        "Ruby is an open-source and fully object-oriented programming language.",
        "Ruby on Rails is a server-side web application development framework written in Ruby language.",
        "Python is interpreted scripting  and object-oriented programming language.",
        "JavaScript is an object-based scripting language.",
        "PHP is an interpreted language, i.e., there is no need for compilation.",
        "AJAX allows you to send and receive data asynchronously without reloading the web page.",
        "Perl is a cross-platform environment used to create network and server-side applications.",
        "Hadoop is an open source framework from Apache written in Java."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myListLanguages = arrayListOf()
        for (index in language.indices) {
            myListLanguages.add(ProgramLanguage(language[index], description[index]))
        }
        myRecyclerViewLanguage = findViewById(R.id.rvLanguages)
        myRecyclerViewLanguage.layoutManager = LinearLayoutManager(this)
        myRecyclerViewLanguage.setHasFixedSize(true)
        myRecyclerViewLanguage.adapter = AdapterRVLanguage(myListLanguages, this)
    }

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

    override fun onItemClick(position: Int) {
        if (myListLanguages != null && myListLanguages.isNotEmpty()) {
            val clickedItem: ProgramLanguage = myListLanguages[position]
            showSnackBar(this.findViewById(android.R.id.content), "${clickedItem.name}", false)
        }
    }
}