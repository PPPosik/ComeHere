package com.example.khj_pc.gaonnuri.Listener

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import com.example.khj_pc.gaonnuri.*
import com.example.khj_pc.gaonnuri.Activity.LoginActivity
import com.example.khj_pc.gaonnuri.Activity.SearchActivity
import com.example.khj_pc.gaonnuri.Util.SharedPreferenceUtil

public class MainNavigationListener(val context : Context, val drawer : DrawerLayout) : NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.main_menu_search -> {
                var intent: Intent = Intent(context, SearchActivity::class.java)
                context.startActivity(intent)
            }
            R.id.main_menu_logout -> {
                SharedPreferenceUtil.removePreferences(context, "username")
                SharedPreferenceUtil.removePreferences(context, "token")
                SharedPreferenceUtil.removePreferences(context, "name")
                var intent: Intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }

        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}