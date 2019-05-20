package android.example.com.bottomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_deuxieme.*


class DeuxiemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deuxieme)

        toolbar.setNavigationOnClickListener{
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }


    }
}
