package com.fragmentoapps.kotlin_example_1

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fragmentoapps.kotlin_example_1.adapters.AdapterRVLanguage
import com.fragmentoapps.kotlin_example_1.database.DatabaseHelper
import com.fragmentoapps.kotlin_example_1.models.ProgramLanguage
import com.fragmentoapps.kotlin_example_1.pages.CreateLanguagePage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterRVLanguage.OnItemClickListener {
    private lateinit var myFrameLayout: FrameLayout
    private lateinit var fabCreate: FloatingActionButton
    private var myListLanguages: ArrayList<ProgramLanguage> = arrayListOf()

    private val language = arrayOf(
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
    private val description = arrayOf(
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
        title = "Languages"
        myFrameLayout = findViewById(R.id.frmMainActivity)
        fabCreate = findViewById(R.id.fabCreateLanguage)

        fabCreate.setOnClickListener { view ->
            startActivity(Intent(this, CreateLanguagePage::class.java))
        }
        readData()
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

    fun readData() {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Languages",
            null,
            null,
            null,
            null,
            null,
            "id DESC"
        )
        val listItems = mutableListOf<ProgramLanguage>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow("id"))
                val itemName = getString(getColumnIndexOrThrow("name"))
                val itemDescription = getString(getColumnIndexOrThrow("description"))
                val language = ProgramLanguage(itemId, itemName, itemDescription)
                listItems.add(language)
            }
        }
        myListLanguages = listItems as ArrayList<ProgramLanguage>
        if (myListLanguages == null || myListLanguages?.isEmpty()) showNoData() else showListData()
    }

    fun showNoData() {
        val myTextView = TextView(this)
        myTextView.text = "No Data"
        myTextView.textSize = 40f
        myTextView.gravity = Gravity.CENTER
        myTextView.setTypeface(null, Typeface.BOLD)
        insertView(myTextView)
    }

    fun showListData() {
        val myRecyclerView = RecyclerView(this)
        myRecyclerView.layoutManager = LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter = AdapterRVLanguage(myListLanguages, this)
        insertView(myRecyclerView)
    }

    fun insertView(view: View) {
        myFrameLayout.removeAllViews()
        myFrameLayout.addView(view)
    }

}