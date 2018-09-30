package com.example.khj_pc.gaonnuri.Util

import android.graphics.Bitmap

object BitmapUtil {
   fun cropCenterBitmap(src : Bitmap?, w : Int, h : Int) : Bitmap?  {
        if(src == null)
            return null;

        var width : Int = src.getWidth();
        var height : Int = src.getHeight();

        if(width < w && height < h)
            return src;

        var x : Int = 0;
        var y : Int = 0;

        if(width > w)
            x = (width - w)/2

        if(height > h)
            y = (height - h)/2

        var cw : Int = w; // crop width
        var ch : Int = h; // crop height

        if(w > width)
            cw = width;

        if(h > height)
            ch = height;

        return Bitmap.createBitmap(src, x, y, cw, ch);
    }
}