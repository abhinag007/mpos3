package com.tech.mpos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class IntroActivity : AppCompatActivity() {

    // variable for our view pager
    lateinit var viewPager: ViewPager

    lateinit var sliderAdapter: SliderAdapter
    lateinit var sliderList: ArrayList<SliderData>

    lateinit var skipBtn: TextView
    lateinit var nextBtn: TextView
    lateinit var startBtn: Button
    lateinit var indicatorSlideOneTV: TextView
    lateinit var indicatorSlideTwoTV: TextView
    lateinit var indicatorSlideThreeTV: TextView
    lateinit var indicatorSlideFourTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        viewPager = findViewById(R.id.idViewPager)
        skipBtn = findViewById(R.id.skip_button)
        nextBtn = findViewById(R.id.next_button)
        skipBtn = findViewById(R.id.skip_button)
        startBtn = findViewById(R.id.startBtn)

        indicatorSlideOneTV = findViewById(R.id.idTVSlideOne)
        indicatorSlideTwoTV = findViewById(R.id.idTVSlideTwo)
        indicatorSlideThreeTV = findViewById(R.id.idTVSlideThree)
        indicatorSlideFourTV = findViewById(R.id.idTVSlideFour)

        // on below line we are adding click listener for our skip button
        skipBtn.setOnClickListener {
            // on below line we are opening a new activity
            val i = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(i)
        }

        startBtn.setOnClickListener {
            // on below line we are opening a new activity
            val i = Intent(this@IntroActivity, LoginActivity::class.java)
            startActivity(i)
        }

        // on below line we are initializing our slider list.
        sliderList = ArrayList()

        // on below line we are adding data to our list
        sliderList.add(
            SliderData(
                "Python",
                "Get Payments just by\n" +
                        "Tap of your Mobile",
                R.drawable.ic_introimage1
            )
        )

        sliderList.add(
            SliderData(
                "Java",
                "Share Payment Link\n" +
                        "to get your Payments",
                R.drawable.ic_intro_image2
            )
        )

        sliderList.add(
            SliderData(
                "C++",
                "Invite Team Members\n" +
                        "to Ease the Payments",
                R.drawable.ic_intro_image3
            )
        )

        sliderList.add(
            SliderData(
                "C++",
                "Easy and Quick Setup\n" +
                        "of Account Details",
                R.drawable.ic_intro_image4
            )
        )

        // on below line we are adding slider list
        // to our adapter class.
        sliderAdapter = SliderAdapter(this, sliderList)

        // on below line we are setting adapter
        // for our view pager on below line.
        viewPager.adapter = sliderAdapter

        // on below line we are adding page change
        // listener for our view pager.
        viewPager.addOnPageChangeListener(viewListener)


    }

    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }



        override fun onPageSelected(position: Int) {
            if (position == 0) {
                skipBtn.visibility = View.VISIBLE
                nextBtn.visibility = View.VISIBLE
                startBtn.visibility = View.GONE
                indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideFourTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideOneTV.setTextColor(resources.getColor(R.color.primary))

            }
            else if (position == 1) {
                skipBtn.visibility = View.VISIBLE
                nextBtn.visibility = View.VISIBLE
                startBtn.visibility = View.GONE
                indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.primary))
                indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideOneTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideFourTV.setTextColor(resources.getColor(R.color.dotIntroColor))
            }
            else if(position==2) {

                skipBtn.visibility = View.VISIBLE
                nextBtn.visibility = View.VISIBLE
                startBtn.visibility = View.GONE
                indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.primary))
                indicatorSlideOneTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideFourTV.setTextColor(resources.getColor(R.color.dotIntroColor))
            }
            else {
                skipBtn.visibility = View.GONE
                nextBtn.visibility = View.GONE
                startBtn.visibility = View.VISIBLE
                indicatorSlideTwoTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideThreeTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideOneTV.setTextColor(resources.getColor(R.color.dotIntroColor))
                indicatorSlideFourTV.setTextColor(resources.getColor(R.color.primary))
                indicatorSlideOneTV.setTextColor(resources.getColor(R.color.dotIntroColor))
            }
        }

        // below method is use to check scroll state.
        override fun onPageScrollStateChanged(state: Int) {}
    }



}

//        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
