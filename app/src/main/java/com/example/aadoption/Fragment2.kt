package com.example.aadoption
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.aadoption.AnimalActivity
import com.example.aadoption.DBHelper
import com.example.aadoption.databinding.Fragment2Binding

class Fragment2 : Fragment(R.layout.fragment2) {
    private lateinit var binding: Fragment2Binding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())

        binding.b1.setOnClickListener {
            val text1 = binding.text1.text.toString()
            val text2 = binding.text2.text.toString()
            val text4 = binding.text4.text.toString()
            loginDatabase(text1, text2, text4)
        }

        binding.text3.setOnClickListener {

            val intent = Intent(requireContext(), AnimalActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }

    private fun loginDatabase(email: String, username: String, password: String) {
        val insertRowId = databaseHelper.insertUser(email, username, password)
        if (insertRowId != -1L) {

            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), AnimalActivity::class.java)
            startActivity(intent)

            requireActivity().finish()
        } else {

            Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}
