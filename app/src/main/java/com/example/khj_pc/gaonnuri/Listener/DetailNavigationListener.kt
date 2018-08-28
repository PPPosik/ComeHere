package com.example.khj_pc.gaonnuri.Listener

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import com.example.khj_pc.gaonnuri.*


public class DetailNavigationListener(val context : Context, val drawer : DrawerLayout) : NavigationView.OnNavigationItemSelectedListener{
    var id : String = ""
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {

            R.id.detail_menu_list -> {
                (context as Activity).finish()
            }
            R.id.detail_menu_search -> {
                var intent: Intent = Intent(context, SearchActivity::class.java)
                context.startActivity(intent)
            }
            R.id.detail_menu_board -> {
                var intent: Intent = Intent(context, BoardActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
            R.id.detail_menu_chat -> {
                var intent: Intent = Intent(context, ChatActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
            R.id.detail_menu_survey_make -> {
                var intent: Intent = Intent(context, SurveyCreateActivity::class.java)
                context.startActivity(intent)
            }
            R.id.detail_menu_survey_join -> {
                var intent: Intent = Intent(context, SurveyDoActivity::class.java)
                context.startActivity(intent)
            }
            R.id.detail_menu_survey_result -> {
                var intent: Intent = Intent(context, SurveyChartActivity::class.java)
                context.startActivity(intent)
            }
            R.id.detail_menu_result -> {
                var intent: Intent = Intent(context, ChartActivity::class.java)
                intent.putExtra("id", id)
                context.startActivity(intent)
            }
            R.id.detail_menu_logout -> {
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