package com.example.aadoption
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aadoption.AnimalActivity
import com.example.aadoption.DBHelper

import com.example.aadoption.databinding.Fragment1Binding


class Fragment1 : Fragment(R.layout.fragment1) {
    private lateinit var binding: Fragment1Binding
    private lateinit var databaseHelper: DatabaseHelper



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())

        binding.b1.setOnClickListener {
            val text1 = binding.text1.text.toString()
            val text2 = binding.text2.text.toString()
            val text4 = binding.text4.text.toString()
            signupDatabase(text1, text2, text4)
        }

        binding.text3.setOnClickListener {
            val intent = Intent(requireContext(), AnimalActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }


    private fun logError(errorMessage: String, exception: Exception) {
        Log.e("DatabaseError", errorMessage)
        Log.e("DatabaseError", Log.getStackTraceString(exception))
    }

    private fun signupDatabase(email: String, username: String, password: String) {
        try {
            val insertRowId = databaseHelper.insertUser(email, username, password)
            if (insertRowId != -1L) {
                Toast.makeText(requireContext(), "Sign up successful", Toast.LENGTH_SHORT).show()

                // Navigate to AnimalActivity after successful signup
                val intent = Intent(requireContext(), AnimalActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Signup failed", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            logError("Error during signup", e)
            Toast.makeText(requireContext(), "Error during signup", Toast.LENGTH_SHORT).show()
        }
    }

}



