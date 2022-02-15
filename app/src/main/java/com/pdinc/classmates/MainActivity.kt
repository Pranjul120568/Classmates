package com.pdinc.classmates

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pdinc.classmates.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding:ActivityMainBinding
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()
        binding.backIv.setOnClickListener {
            finish()
        }
        binding.loginBt.setOnClickListener {
            val email = binding.enterEmailEt.text.toString()
            val password = binding.enterPasswordEt.text.toString()
            if(!email.matches(emailPattern.toRegex()) || password.length<4 ||email.length<4){
                Toast.makeText(this,"Check email and password",Toast.LENGTH_SHORT).show()
            }
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        // val user = mAuth.currentUser
                        startActivity(Intent(this,UserActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        binding.forgotPasswordTv.setOnClickListener {
            val email = binding.enterEmailEt.text.toString()
            if(email.matches(emailPattern.toRegex()) &&  email.length>4) {
                mAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Password reset link set to your email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Error sending password link Try Later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }else{
                Toast.makeText(
                    this,
                    "Enter valid email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}