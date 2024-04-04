package com.reverb.navigationdrawerwfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var navigation_view: NavigationView

    lateinit var homeFragment: HomeFragment
    lateinit var workFragment: WorkFragment
    lateinit var schoolFragment: SchoolFragment
    lateinit var settingsFragment: SettingsFragment
    lateinit var timelineFragment: TimelineFragment
    lateinit var logoutFragment: LogoutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo(https://www.youtube.com/watch?v=Y8mGxxRe3k4)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigation_view = findViewById(R.id.nav_view)

        val toolbar: Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close).apply {
            isDrawerIndicatorEnabled = true
        }

        drawerLayout.addDrawerListener(drawerToggle)

        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener(this)

        homeFragment = HomeFragment()//default
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if( drawerLayout.isDrawerOpen(GravityCompat.START) ){
                    drawerLayout.closeDrawer(GravityCompat.START)
                }else{
                    onBackPressedDispatcher.onBackPressed()
                }
            }

        })

    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.home ->{
                homeFragment = HomeFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.work ->{
                workFragment = WorkFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, workFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.school ->{
                schoolFragment = SchoolFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, schoolFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.timeline ->{
                timelineFragment = TimelineFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, timelineFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.settings ->{
                settingsFragment = SettingsFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, settingsFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.logout ->{
                logoutFragment = LogoutFragment()//default
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, logoutFragment)
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }





}