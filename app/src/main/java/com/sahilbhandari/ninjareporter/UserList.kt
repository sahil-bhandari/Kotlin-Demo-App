package com.sahilbhandari.ninjareporter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.sahilbhandari.ninjareporter.adapter.UserAdapter
import com.sahilbhandari.ninjareporter.model.UserDetails
import io.realm.Realm


class UserList : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var rvUserList : RecyclerView
    lateinit var userAdapterVar: UserAdapter
    lateinit var userDetailsList: ArrayList<UserDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setupToolbar()
        initViews()
        initRealm()
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        displayUser()
    }

    private fun displayUser() {
        userDetailsList.clear()
        val realmUserList = realm.where(UserDetails::class.java).findAll()
        for (s in 0 until realmUserList.size) {
            realmUserList[s]?.let { userDetailsList.add(it) }
        }
        Log.d("TAG",realmUserList.toString())
        Log.d("TAG",userDetailsList.toString())
        userAdapterVar= UserAdapter(userDetailsList, object : UserAdapter.ItemClickListener {
            override fun clickToDelete(id: Int) {
                realmDelete(id)
            }
        },this@UserList)
        rvUserList.adapter = userAdapterVar
        userAdapterVar.notifyDataSetChanged()
    }

    private fun realmDelete(id: Int) {
        val alertDialog= AlertDialog.Builder(this)
        alertDialog.setTitle("Delete!")
            .setMessage("Are you Sure?")
            .setPositiveButton(android.R.string.ok) { dialogInterface, i ->
                realm.where(UserDetails::class.java).equalTo("id", id).findFirst()!!.deleteFromRealm()
                Toast.makeText(this@UserList,"Entry deleted.",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(android.R.string.cancel){dialogInterface, i ->null}
            .show()
    }

    private fun initViews() {
        userDetailsList= ArrayList()
        rvUserList=findViewById(R.id.rvUserList)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        toolbar.navigationIcon = null
        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return  when (item.itemId) {
            R.id.action_add -> {
                startActivity(Intent(this, AddUserDetail::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
       }
    }

    override fun onResume() {
        super.onResume()
        displayUser()
    }


}
