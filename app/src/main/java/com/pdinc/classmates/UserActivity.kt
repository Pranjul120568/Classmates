package com.pdinc.classmates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pdinc.classmates.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()
        binding.signOutBtn.setOnClickListener {
            mAuth.signOut()
            finish()
        }
    }
}