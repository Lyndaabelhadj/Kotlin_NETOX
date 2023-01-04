package tn.esprit.android_netox_miniprojet.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.fragments.ArticleFragment
import tn.esprit.android_netox_miniprojet.fragments.VideoFragment

class HomeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var prefs : SharedPreferences


    //private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        prefs = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE)


       // val navHostFragment= supportFragmentManager.findFragmentById(R.id.HomeContainer) as NavHostController
       // navController =navHostFragment.NavController
        // val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        //setupWithNavController(bottomNavigationView , navController)

         val articleFragment = ArticleFragment()
         val videoFragment = VideoFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        replaceFragment(articleFragment)


        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.articleFragment -> replaceFragment(articleFragment)
                R.id.videoFragment -> replaceFragment(videoFragment)
            }
            true
        }




        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val header: View = navView.getHeaderView(0)
        val user_name : TextView = header.findViewById(R.id.user_name)
        val user_email : TextView = header.findViewById(R.id.user_email)
        val nom = prefs.getString(USERNAME,"")
        user_name.setText(nom)
        val email = prefs.getString(EMAIL,"")
        user_email.setText(email)

        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){
                //R.id.nav_home -> Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_home-> startActivity(Intent(applicationContext, HomeActivity::class.java))
                //R.id.nav_message -> Toast.makeText(applicationContext,"Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_doctors -> startActivity(Intent(applicationContext,DoctorsActivity::class.java))
                R.id.nav_video -> startActivity(Intent(applicationContext,HomeVideoActivity::class.java))
                R.id.nav_quiz-> startActivity(Intent(applicationContext, StartQuizActivity::class.java))
                R.id.nav_profile-> startActivity(Intent(applicationContext, ProfileActivity::class.java))

                R.id.nav_share -> Toast.makeText(applicationContext,"Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.nav_rate_us -> Toast.makeText(applicationContext,"Clicked Rate us", Toast.LENGTH_SHORT).show()
                R.id.nav_logout -> {

                    val preferences : SharedPreferences = this.getSharedPreferences(PREF_LOGIN,Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor=preferences.edit()
                    editor.clear()
                    editor.apply()
                    startActivity(Intent(this,IntroActivity::class.java))

                }


            }

            true

        }

    }



    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.HomeContainer,fragment)
            transaction.commit()
    }}



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}