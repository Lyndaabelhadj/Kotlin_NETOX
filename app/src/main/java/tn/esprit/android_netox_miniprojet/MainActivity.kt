package tn.esprit.android_netox_miniprojet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.android_netox_miniprojet.adapters.ArticlesAdapter
import tn.esprit.android_netox_miniprojet.models.Article


class MainActivity : AppCompatActivity() {



    lateinit var recycler_view_Article: RecyclerView
    lateinit var articlesAdapter: ArticlesAdapter
    lateinit var toolbar: Toolbar
    lateinit var imageSearch: ImageView
    lateinit var editSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recycler_view_Article= findViewById(R.id.recycler_view_Article)
        toolbar= findViewById(R.id.toolbar)
        imageSearch= findViewById(R.id.imageSearch)
        editSearch= findViewById(R.id.editSearch)
        setSupportActionBar(toolbar)
/*
        val items = listOf(

            Article(
                "How Is Social Media Stress Affecting Today’s Teens and Young Adults?",
                "While Tik Tok, Instagram, and other social media sites can help young people connect, they can also become toxic spaces that perpetuate distorted images of perfection, cyberbullying, and other harmful messages and interactions.",
                R.drawable.article1,
                4.1F,
                "ons maghraoui"),

            Article(
                "Instagram May Worsen Body Image Issues, Increase Suicidal Thoughts",
                "“Thirty-two percent of teen girls said that when they felt bad about their bodies, Instagram made them feel worse,” the researchers said in a spring 2020 slide presentation posted to Facebook’s internal message board.",
                R.drawable.article2,
                4.1F,
                "lina belhadj"),

            Article(
                "How to Do a Digital Detox Without Unplugging Completely",
                "A study published in 2021 by the Libyan Journal of Medicine found that students who completed a social media detox reported positive changes to their mood, sleep, and anxiety. " +
                        "And another study, published February 2020 in Cyberpsychology, Behavior, and Social Networking, found that women who quit Instagram reported higher life satisfaction and more positive affect than women who continued using the social media app.",
                R.drawable.article3,
                4.1F,
                "linda belhadj"),

            Article(
                "Teens May Have Trouble Setting Social Media Limits",
                "“Like the social media platform TikTok, Instagram tends to put certain things in front of you based on what you’ve been liking, who you are following, and all of that,” she says.",
                R.drawable.article4,
                4.1F,
                "donia fouzri"),


        )*/
        articlesAdapter = ArticlesAdapter(this)
        recycler_view_Article.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = articlesAdapter
        }

        imageSearch.setOnClickListener{
            val search = editSearch.text.toString()
           // articlesAdapter.filter.filter(search)
        }



        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        //navController = navHostFragment.navController
        //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        //setupWithNavController(bottomNavigationView , navController)

       /* Handler(mainLooper).postDelayed({ //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3500)*/



    }
    }
