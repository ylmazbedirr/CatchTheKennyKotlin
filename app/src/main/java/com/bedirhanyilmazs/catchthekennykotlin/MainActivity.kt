package com.bedirhanyilmazs.catchthekennykotlin

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bedirhanyilmazs.catchthekennykotlin.databinding.ActivityMainBinding
import java.util.Random
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable{}
    var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ---Image Arrays---
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        //---End Image Arrays---

        hideImages()


        // ----------Timer---------
        object : CountDownTimer(15500, 1000) {
            override fun onTick(p0: Long) {
                binding.textView.text = "Time: ${p0 / 1000}"
            }
            override fun onFinish() {
                binding.textView.text = "Time: 0"
                handler.removeCallbacks(runnable)

                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")

                alert.setPositiveButton("Yes") {dialog, which ->
                    // restart
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity, "GameOver", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
        }.start()
        // -------- End Timer --------
    }

    // --- Visible & INVISIBLE ---
    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {

                for(image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)
    }
    //---End Visible & INVISIBLE ---





    //----Score----

    fun increaseSocre(view : View) {
        score++
        binding.scoreText.text = "Score: ${score}"
    }
    //---End Score---
}