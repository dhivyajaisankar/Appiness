package com.example.appiness

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class Productdetailsscreen : AppCompatActivity() {

    lateinit var profile_imageview: ImageView
    lateinit var name_tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetailsscreen)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()


        profile_imageview = findViewById(R.id.profile_imageview)
        name_tv = findViewById(R.id.name_tv)


        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")


        val removeextrastring = image?.replace("ProductImage(square=", "")
        val url = removeextrastring?.replace(")", "")

        name_tv.text = name



        Picasso.get().load(url).resize(50, 50).centerCrop().into(profile_imageview)


        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = (size.x) / 2
        val height = ((size.y) / 2.2).roundToInt()

        Picasso.get().load(url).noFade().resize(width, height).into(profile_imageview)


    }
}