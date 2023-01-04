package tn.esprit.android_netox_miniprojet.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import tn.esprit.android_netox_miniprojet.R

class ArticleDetails : AppCompatActivity() {
lateinit var titre : TextView
lateinit var summary : TextView
lateinit var author : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_article_details)

        titre = findViewById(R.id.tvTitle1)
        summary = findViewById(R.id.tvSummary1)
        author = findViewById(R.id.tvAuthor1)

        var tit = intent.getStringExtra("titre")
        var summ = intent.getStringExtra("summary")
        var auth = intent.getStringExtra("author")


        titre.text=tit
        summary.text=summ
        author.text=auth

    }
}