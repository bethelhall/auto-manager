package com.example.beheer

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.beheer.*
import com.example.beheer.NavigationHost
import com.example.beheer.R
import kotlinx.android.synthetic.main.activity_main.*
import view.LoginFragment

class MainActivity : AppCompatActivity(), NavigationHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, LoginFragment())
                .commit()
        }

//        val navController = findNavController(R.id.my_nav_host_fragment)
//        //setup the navigation graph
//        navController.setGraph(R.navigation.chareta_navigation)
//        //navigate to each fragment from bottom menu
//        findViewById<BottomNavigationView>(R.id.bottom_navigation)
//            .setupWithNavController(navController)
//        //hide the bottom bar
//        hideBottomBar(false)
    }

    fun hideBottomBar(isHidden: Boolean) {
        bottom_navigation.visibility = if (isHidden) View.GONE else View.VISIBLE
    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean) {

        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    fun connected(): Boolean {

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }
}
