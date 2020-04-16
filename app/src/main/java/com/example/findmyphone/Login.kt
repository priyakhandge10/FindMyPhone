package com.example.findmyphone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class Login : AppCompatActivity() {
         var mAuth:FirebaseAuth?=null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            mAuth= FirebaseAuth.getInstance()
            signInAnonymously()
        }
        private fun signInAnonymously(){
            mAuth!!.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        makeText(applicationContext, "Authentication success.", LENGTH_SHORT).show()
                        val user = mAuth!!.currentUser

                    } else {
                        makeText(applicationContext, "Authentication failed.", LENGTH_SHORT).show()
                    }
                }
        }

        fun buRegisterEvent(view: View){
            val userData= UserData(this)
            userData.savePhone(etPhoneNumber.text.toString())
            val df = SimpleDateFormat("yyyy/MMM/dd HH:MM:ss")
            val date = Date()
            val mDatabase = FirebaseDatabase.getInstance().reference
            mDatabase.child("Users").child(etPhoneNumber.text.toString()).child("request").setValue(df.format(date).toString())
            mDatabase.child("Users").child(etPhoneNumber.text.toString()).child("Finders").setValue(df.format(date).toString())
            finish()
        }
    }

