package com.pdinc.classmates

import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pdinc.classmates.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding:ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()
        if(mAuth.currentUser!=null){
            startActivity(Intent(this,UserActivity::class.java))
        }
        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.signInBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}