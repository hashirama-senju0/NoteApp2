package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import com.example.noteapp.databinding.ActivityLoginPageBinding
import com.example.noteapp.databinding.LoginPageBinding
import java.util.Arrays
import java.util.logging.Logger.global

class Login_page : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityLoginPageBinding
    private var user:Array<String>? = null
    private var pass:Array<String>? = null
    private var found1:Boolean=true
    private var found2:Boolean=true
    private var password:String?=null
    private var username:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user?.set(0, "akash")
        pass?.set(0,"akash")
        binding.button.setOnClickListener(this)
        binding.textView.setOnClickListener(this)
        username=binding.editText.text.toString()
        password= binding.editText2.text.toString()
        found1 = Arrays.stream(user).anyMatch { t -> t == username }
        found2 = Arrays.stream(pass).anyMatch { t -> t == password }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button->{
                if(found1 && found2){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.textView->{

            }
        }
    }
}