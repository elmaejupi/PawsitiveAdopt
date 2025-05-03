package com.example.aadoption
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aadoption.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fragment1Btn.setOnClickListener {
            replaceFragment(Fragment1())
        }

        binding.fragment2Btn.setOnClickListener {
            replaceFragment(Fragment2())

        }



    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }




    fun toAnimals(view: View) {
        val intent = Intent(this, AnimalActivity::class.java)
        startActivity(intent)
    }


    fun goToMain(v:View){
        val intent = Intent(this, AnimalActivity::class.java)
        startActivity(intent)
    }




}