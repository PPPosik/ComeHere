package com.example.khj_pc.gaonnuri.Listener

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import com.example.khj_pc.gaonnuri.*

public class NavigationListener(val context : Context, val drawer : DrawerLayout) : NavigationView.OnNavigationItemSelectedListener{
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_list -> {
                // Handle the camera action
                var intent: Intent = Intent(context, ChatActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_search -> {
                var intent: Intent = Intent(context, SearchActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_chat -> {
                var intent: Intent = Intent(context, DialogActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_notifications -> {
                var intent: Intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_photos -> {
                var intent: Intent = Intent(context, BoardActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_videos -> {
                var intent: Intent = Intent(context, DetailActivity::class.java)
                context.startActivity(intent)
            }
            R.id.menu_places -> {

            }
            R.id.menu_settings -> {

            }
            R.id.menu_search_tmp -> {

            }
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}