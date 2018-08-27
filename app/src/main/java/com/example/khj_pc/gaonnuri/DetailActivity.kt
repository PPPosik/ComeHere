package com.example.khj_pc.gaonnuri

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import com.example.khj_pc.gaonnuri.Adapter.BoardRecyclerViewAdapter
import com.example.khj_pc.gaonnuri.Adapter.DetailViewPageAdapter
import com.example.khj_pc.gaonnuri.Data.Board
import kotlinx.android.synthetic.main.activity_detail.*
import com.example.khj_pc.gaonnuri.Listener.NavigationListener
import kotlinx.android.synthetic.main.app_bar_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_board.*

class DetailActivity : AppCompatActivity() {

    private val boards : ArrayList<Board> = ArrayList()
    private val imgUrl : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.title = null

        val toggle = ActionBarDrawerToggle(
                this, detail_drawerLayout, detail_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        detail_drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        detail_nav_view.setNavigationItemSelectedListener(NavigationListener(this, detail_drawerLayout))

        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))
        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))
        boards.add(Board("자유게시물", "배현빈", "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", 21, 1))

        for (b in boards) {
            for (i in 0..2)
                b.content = b.content + "\n" + b.content
        }

        val adapter = BoardRecyclerViewAdapter(this, boards)
        board_recyclerview.adapter = adapter
        board_recyclerview.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()

        imgUrl.add("https://images.pexels.com/photos/104827/cat-pet-animal-domestic-104827.jpeg")
        imgUrl.add("https://images.pexels.com/photos/730896/pexels-photo-730896.jpeg")
        imgUrl.add("https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg")

        detail_viewpager.adapter = DetailViewPageAdapter(imgUrl, this)
        detail_tablayout.setupWithViewPager(detail_viewpager, true)

        home_button.setOnClickListener {
            var intent : Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (detail_drawerLayout.isDrawerOpen(GravityCompat.START)) {
            detail_drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}