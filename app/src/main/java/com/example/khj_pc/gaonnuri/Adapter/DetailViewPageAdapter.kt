package com.example.khj_pc.gaonnuri.Adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.khj_pc.gaonnuri.R
import com.example.khj_pc.gaonnuri.Util.BitmapUtil
import com.example.khj_pc.gaonnuri.Util.loadUrlWithCenterCrop
import kotlinx.android.synthetic.main.content_detail_viewpage.*
import kotlinx.android.synthetic.main.content_detail_viewpage.view.*
import java.io.InputStream
import java.net.URL

class DetailViewPageAdapter(val items : ArrayList<String>, context : Context) : PagerAdapter(){
    var inflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var v : View? = null

        v = inflater.inflate(R.layout.content_detail_viewpage, container, false)
//        val bmp = BitmapFactory.decodeStream(items[position].openConnection().getInputStream())
        addImg(v, items[position])

        container.addView(v)
        return v
    }

    private fun addImg(view : View, item : String){
        view.detail_imageView.loadUrlWithCenterCrop(item)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view == `object`)

    override fun getCount(): Int = items.size

}

private class DownloadImageTask(val bmImage : ImageView) : AsyncTask<String, Void, Bitmap>(){
    override fun onPostExecute(result: Bitmap?) {
         // super.onPostExecute(result)
        bmImage.setImageBitmap(result)
    }

    override fun doInBackground(vararg params: String?): Bitmap {
        val url : String? = params[0]
        lateinit var icon : Bitmap

        try{
            val ins : InputStream = URL(url).openStream()
            icon = BitmapFactory.decodeStream(ins)
            icon = BitmapUtil.cropCenterBitmap(icon, icon.width, 500)!!
        }
        catch (e : Exception){
            e.printStackTrace()
        }

        return icon
    }
}