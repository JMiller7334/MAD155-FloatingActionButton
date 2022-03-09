package com.example.mad155_actionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var listItems = arrayListOf<String>()
    var adapter: ArrayAdapter<String>? = null
    lateinit var listView: ListView //initialize variable here
    lateinit var fab: FloatingActionButton
    lateinit var undoOnClickListener: View.OnClickListener // this is listener for snackbar action




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lv1)
        fab = findViewById(R.id.fab1)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems) //configure the adapter)
        listView.adapter = adapter

        fab.setOnClickListener {
            //view -> --this would set the view currently this is using 'it' which is this view
            addListItem()
            Snackbar.make(it, "Added an item", Snackbar.LENGTH_LONG)
                .setAction("UNDO", //'UNDOis where you set the action, currently no action.
                    undoOnClickListener) //null for no action - currently using an undo listener
                .show() // show the snackbar
        }
        // config for the snackbar action listener here
        undoOnClickListener = View.OnClickListener {
            listItems.removeAt(listItems.size-1)
            adapter?.notifyDataSetChanged() //note adapter is a safecall

            // a snackbar is basically a toast msg with an action button on it.
            Snackbar.make(it, "The item was removed()", Snackbar.LENGTH_SHORT)
                .setAction("Action", null)
                .show()


        }


    }

    private fun addListItem() {
        listItems.add("Item1")
        adapter?.notifyDataSetChanged() //this refreshs/syncs adapter to show data changes
    }
}