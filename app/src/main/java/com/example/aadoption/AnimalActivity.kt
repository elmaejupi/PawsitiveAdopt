package com.example.aadoption

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.aadoption.DBHelper.Companion.DATABASE_NAME
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AnimalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var databaseHelper: DatabaseHelper



    private val animalTypesMap = mapOf(
        "parrot" to 3,
        "golden retriever" to 1,
        "siamese cat" to 2,
        "rabbit" to 0,
        "cat" to 2,
        "bird" to 3,
        "dog" to 1 ,
        "ca" to 2,
        "c" to 2,
        "d" to 1,
        "do" to 1,
        "b" to 3,
        "bir" to 3,
        "ra" to 0,
        "rab" to 0,
        "r" to 0,
        "dogs" to 1,
        "cats"  to 2,
        "rabbits" to 0,
        "birds" to 3

    )


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
        setupViewPagerAndTabs()

        val dbHelper = DBHelper(this)
        val db = dbHelper.writableDatabase
        val dbPath = getDatabasePath(DATABASE_NAME).absolutePath
        Log.d("DBPath", "Database path: $dbPath")
        dbHelper.addAnimal("birds","Angel","Scarlet Macaw",20)
        dbHelper.addAnimal("birds","Daisy","Rock Pigeon",3)
        dbHelper.addAnimal("birds","Baby","Pine grosbeak",8)
        dbHelper.addAnimal("birds","Coco","House Sparrow",2)
        dbHelper.addAnimal("birds","Ala","Parakeet",7)
        dbHelper.addAnimal("cats","Kitty","Sokoke",2)
        dbHelper.addAnimal("cats","Luna","Munchkin",4)
        dbHelper.addAnimal("cats","Charlie","American curl",4)
        dbHelper.addAnimal("cats","Oliver","Carthusian",4)
        dbHelper.addAnimal("cats","Lily","Chausie",2)
        dbHelper.addAnimal("dogs","Milo","Husky",6)
        dbHelper.addAnimal("dogs","Max","Akita",1)
        dbHelper.addAnimal("dogs","Coodper","Pomeranian",6)
        dbHelper.addAnimal("dogs","Bubby","Golden Retriever",4)
        dbHelper.addAnimal("dogs","Ice","Dobbermann",9)
        dbHelper.addAnimal("rabbits","Nibbles","Netherland Dwarf",6)
        dbHelper.addAnimal("rabbits","Olive","Dwarf Hotot",5)
        dbHelper.addAnimal("rabbits","Bun Bun","Harlequin",6)
        dbHelper.addAnimal("rabbits","Carrot","Holand Lop",3)
        dbHelper.addAnimal("rabbits","Fuzzy","Havana",4)



        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter

        drawerLayout = findViewById(R.id.drawer_layout)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        findViewById<Button>(R.id.bbb).setOnClickListener {
            openNavigationDrawer()
        }
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)



        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                // Call a function to handle the search (e.g., display animal type)
                handleSearch(searchQuery)
            } else {
                Toast.makeText(this, "Please enter an animal name", Toast.LENGTH_SHORT).show()
            }
            databaseHelper = DatabaseHelper(this)
        }
    }

        fun signUp(email: String, username: String, password: String) {
            try {
                val result = databaseHelper.insertUser(email, username, password)
                if (result == -1L) {
                    Toast.makeText(this, "Error during signup", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error during signup: ${e.message}", Toast.LENGTH_SHORT).show()
            }




            fun login(username: String, password: String): Boolean {
                val count = databaseHelper.getUserCount(username, password)
                return count > 0
            }

        fun onLoginClick(v: View) {
            val loginUsernameEditText: EditText = findViewById(R.id.text1) // Replace with the actual ID of your login username EditText
            val loginPasswordEditText: EditText = findViewById(R.id.text4) // Replace with the actual ID of your login password EditText

            val loginUsername = loginUsernameEditText.text.toString()
            val loginPassword = loginPasswordEditText.text.toString()

            try {
                if (login(loginUsername, loginPassword)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Error during login: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager2.currentItem = it.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Implement as needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Implement as needed
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })

    }

    private fun openNavigationDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setupViewPagerAndTabs() {
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager2.adapter = viewPagerAdapter


        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Rabbits"
                1 -> tab.text = "Dogs"
                2 -> tab.text = "Cats"
                3 -> tab.text = "Birds"
            }
        }.attach()
    }

    private fun handleSearch(searchQuery: String) {
        // Perform the search logic based on the entered animal name
        val animalTypePosition = animalTypesMap[searchQuery.toLowerCase()]
        if (animalTypePosition != null) {
            viewPager2.currentItem = animalTypePosition
        } else {
            Toast.makeText(this, "Animal not found", Toast.LENGTH_SHORT).show()
        }
    }

    fun adoptcontact(v: View) {
        setContentView(R.layout.adopting_contact)
    }

    fun backtolog(fragment: Fragment){
        setContentView(R.layout.activity_main)
        val fragmentManager=supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }



    fun applied(v:View){
        Toast.makeText(this,"Your application has been sent",Toast.LENGTH_SHORT).show()
        setContentView(R.layout.main_menu)
        setupViewPagerAndTabs()
        TabLayoutMediator(tabLayout, viewPager2) { tab, position -> when(position){
            0 -> tab.text = "Rabbits"
            1 -> tab.text = "Dogs"
            2 -> tab.text = "Cats"
            3 -> tab.text = "Birds"}
        }.attach()
    }
    fun main(v:View){
        val intent = Intent(this, AnimalActivity::class.java)
        startActivity(intent)
        setContentView(R.layout.main_menu)
        setupViewPagerAndTabs()
        TabLayoutMediator(tabLayout, viewPager2) { tab, position -> when(position){
            0 -> tab.text = "Rabbits"
            1 -> tab.text = "Dogs"
            2 -> tab.text = "Cats"
            3 -> tab.text = "Birds"}
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {

                val intent = Intent(this, AnimalActivity::class.java)
                startActivity(intent)

                return true}

            R.id.nav_about -> {

                setContentView(R.layout.fragment_about)
                return true
            }
            R.id.nav_rating -> {
                setContentView(R.layout.fragment_rating)
                return true
            }
            R.id.logout -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                return true
            }
            else -> return false
        }
    }


}