package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.support.v7.widget.GridLayoutManager
import com.example.khj_pc.gaonnuri.Adapter.RecyclerViewItemAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val testData: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        testDataAdd()
        competitionRecyclerView.layoutManager = GridLayoutManager(this, 3)
        competitionRecyclerView.adapter = RecyclerViewItemAdapter(testData, this)
        competitionRecyclerView.isNestedScrollingEnabled = false

        seminarRecyclerView.layoutManager = GridLayoutManager(this, 3)
        seminarRecyclerView.adapter = RecyclerViewItemAdapter(testData, this)
        seminarRecyclerView.isNestedScrollingEnabled = false

    }

    fun testDataAdd() {
        testData.add("AAA")
        testData.add("BBB")
        testData.add("CCC")
        testData.add("DDD")
        testData.add("EEE")
        testData.add("FFF")
        testData.add("GGG")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                var intent: Intent = Intent(this, ChatActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_gallery -> {
                var intent: Intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_slideshow -> {
                var intent: Intent = Intent(this, DialogActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_manage -> {
                var intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                var intent: Intent = Intent(this, BoardActivity::class.java)
            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
