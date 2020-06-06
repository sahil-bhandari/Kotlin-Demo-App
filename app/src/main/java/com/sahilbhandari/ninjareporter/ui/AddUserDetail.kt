package com.sahilbhandari.ninjareporter.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sahilbhandari.ninjareporter.R
import com.sahilbhandari.ninjareporter.model.UserDetails
import io.realm.Realm

class AddUserDetail : AppCompatActivity() {

    lateinit var btnBack:ImageView
    lateinit var btnSubmit:Button
    lateinit var etName:EditText
    lateinit var etExp:EditText
    lateinit var etOccupation:EditText
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user_detail)
        initViews()
        realm = Realm.getDefaultInstance()

        btnBack.setOnClickListener { finish() }
        btnSubmit.setOnClickListener { addUser() }
    }

    private fun addUser() {
        if (etName.text.toString().isEmpty()){
            customDiag("Please Enter User's name")
        }else if (etExp.text.toString().isEmpty()){
            customDiag("Please Enter User's experience in years")
        }else if (etOccupation.text.toString().isEmpty()){
            customDiag("Please Enter User's occupation")
        } else{
            saveData()
        }
    }

    private fun customDiag(s: String) {
        val alertDialog= AlertDialog.Builder(this)
        alertDialog.setTitle("Warning!")
            .setMessage(s)
            .setPositiveButton(android.R.string.ok) { dialogInterface, i -> null}
            .setNegativeButton(android.R.string.cancel,null)
            .show()
    }

    private fun saveData() {
        try {
            realm.beginTransaction()
        } catch (e: Exception) {
            Log.d("TAG","transaction failed")
        }
        val maxId = realm.where(UserDetails::class.java).max("id")
        val nextId = if (maxId == null) 1 else maxId.toInt() + 1
        val user = UserDetails()
        user.id = nextId
        user.name = etName.text.toString()
        user.experience = etExp.text.toString().toInt()
        user.occupation = etOccupation.text.toString()
        realm.insertOrUpdate(user)
        realm.commitTransaction()
        val alertDialog= AlertDialog.Builder(this)
            alertDialog.setTitle("Success!")
                .setMessage("Data Written Successfully!")
                .setPositiveButton("Go Back") { dialogInterface, i ->
                    finish()
                }
                .setNegativeButton("Add More"){dialogInterface, i ->
                    etName.text.clear()
                    etExp.text.clear()
                    etOccupation.text.clear()
                }
                .show()

        //not working!!
//        realm.executeTransactionAsync ({
//            val maxId = realm.where(UserDetails::class.java).max("id")
//            val nextId = if (maxId == null) 1 else maxId.toInt() + 1
//            val user = it.createObject(UserDetails::class.java)
//            user.id = nextId
//            user.name = etName.text.toString()
//            user.experience = etExp.text.toString().toInt()
//            user.occupation = etOccupation.text.toString()
//        },{
//            Log.d("TAG","On Success: Data Written Successfully!")
//            val alertDialog= AlertDialog.Builder(this)
//            alertDialog.setTitle("Success!")
//                .setMessage("Data Written Successfully!")
//                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->
//                    finish()
//                }
//                .setNegativeButton(android.R.string.cancel,null)
//                .show()
//        },{
//            Log.d("TAG","On Error: Error in saving Data!")
//        })
    }

    private fun initViews() {
        btnBack=findViewById(R.id.btnBack)
        btnSubmit=findViewById(R.id.btnSubmit)
        etName=findViewById(R.id.etName)
        etOccupation=findViewById(R.id.etOccupation)
        etExp=findViewById(R.id.etExp)
    }
}
