package android.example.com.bottomapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_annonces.*

class Annonces : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annonces)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        supportActionBar?.setTitle("Créer une annonce")


    }

}
