package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ActivityLoginBinding
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.notetoself.DialogNewNote
import com.example.notetoself.DialogShowNote
import com.example.notetoself.Note

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2:ActivityLoginBinding


    //private val noteList=ArrayList<Note>()
    private var mSerilazer: JSONSerializer? = null
    private var noteList: ArrayList<Note>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding2.root)


        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "")
        }
        mSerilazer = JSONSerializer("NoteToSelf.json", applicationContext)
        try {
            noteList = mSerilazer!!.load()
        } catch (e: Exception) {
            noteList = ArrayList()
            Log.e("Error Loading notes :", "", e)
        }


        recyclerView = findViewById<View>(R.id.recylerView) as RecyclerView
        adapter = NoteAdapter(this, noteList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView!!.adapter = adapter

    }
    private fun saveNotes(){
        try{
            mSerilazer!!.save(this.noteList!!)
        }catch(e:Exception){
            Log.e("Error Saving Notes","",e)
        }
    }
    override fun onPause(){
        super.onPause()
        saveNotes()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun createNewNote(n: Note) {
        noteList!!.add(n)
        adapter!!.notifyDataSetChanged()

    }

    fun showNote(noteToShow: Int) {
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList!![noteToShow])
        dialog.show(supportFragmentManager, "")
    }
}