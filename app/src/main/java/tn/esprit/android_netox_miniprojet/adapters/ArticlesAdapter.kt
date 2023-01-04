package tn.esprit.android_netox_miniprojet.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.Article
import tn.esprit.android_netox_miniprojet.views.ArticleDetails


class ArticlesAdapter (var context : Context) : RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>()/*, Filterable*/{

    private var listTitre = arrayOf(
        "How Is Social Media Stress Affecting Today’s Teens and Young Adults?",
        "Instagram May Worsen Body Image Issues, Increase Suicidal Thoughts",
        "How to Do a Digital Detox Without Unplugging Completely",
        "Teens May Have Trouble Setting Social Media Limits")
    private var listauthor= arrayOf("ons maghraoui",
        "lina belhadj",
        "linda belhadj",
        "donia fouzri")
    private var listSummary = arrayOf("While Tik Tok, Instagram, and other social media sites can help young people connect, they can also become toxic spaces that perpetuate distorted images of perfection, cyberbullying, and other harmful messages and interactions.",
        "“Thirty-two percent of teen girls said that when they felt bad about their bodies, Instagram made them feel worse,” the researchers said in a spring 2020 slide presentation posted to Facebook’s internal message board.",
        "A study published in 2021 by the Libyan Journal of Medicine found that students who completed a social media detox reported positive changes to their mood, sleep, and anxiety. \" +\n" +
                "                        \"And another study, published February 2020 in Cyberpsychology, Behavior, and Social Networking, found that women who quit Instagram reported higher life satisfaction and more positive affect than women who continued using the social media app.",
        "Like the social media platform TikTok, Instagram tends to put certain things in front of you based on what you’ve been liking, who you are following, and all of that,” she says.")
    private var listImage = intArrayOf(R.drawable.article1,R.drawable.article2,R.drawable.article3,R.drawable.article4)

//articlesFilteredList au début elle prend tous les items
   /* var articlesFilteredList: List<Article> = ArrayList()
    init {
        articlesFilteredList = items
    }
*/

/*
    override fun getFilter(): Filter {
        return  object : Filter(){
            //quest ce que tu veux filtrer
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    articlesFilteredList = items
                } else {
                    var resultList = ArrayList<Article>()
                    //items.filter{article -> article.title.lowercase().contains(charSearch.lowercase())}

                    for (article in items) {
                        // || article.author.lowercase().contains(charSearch.lowercase()))
                        if (article.title.lowercase().contains(charSearch.lowercase())|| article.summary.lowercase().contains(charSearch.lowercase()) || article.author.lowercase().contains(charSearch.lowercase())) {
                            resultList.add(article)
                        }
                    }
                    articlesFilteredList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = articlesFilteredList
                return filterResults
            }


            //raffrechir les nouveaux données
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                articlesFilteredList = results?.values as ArrayList<Article>
                notifyDataSetChanged()
            }
        }
    }
*/


    //contenant les views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        //convertir un xml en un objet view grace a inflater

        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.item_article,parent,false)
        return ArticlesViewHolder(itemView)
    }
    //injecter les data dans ViewHolder donc dans ArticlesViewHolder et la position de l'item
    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
  /*  val article = articlesFilteredList[position]
    holder.bind(article)*/
        holder.imageArticle.setImageResource(listImage[position])
        holder.tvTitle.text= listTitre[position]
        holder.tvSummary.text=listSummary[position]
        holder.tvAuthor.text=listauthor[position]

        holder.itemView.setOnClickListener{
            val intent = Intent(context ,ArticleDetails::class.java)
            var titre = holder.tvTitle.text.toString()
            var author = holder.tvAuthor.text.toString()
            var summary = holder.tvSummary.text.toString()

            intent.putExtra("titre",titre)
            intent.putExtra("author",author)
            intent.putExtra("summary",summary)


            context.startActivity(intent)

        }
    }

    override fun getItemCount() : Int{
        return listTitre.size
    }




    //ArticlesViewHolder de type recycle view
    //inner class : une classe dans une autre classe
    inner class ArticlesViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var imageArticle: ImageView
        lateinit var tvTitle: TextView
        lateinit var tvAuthor: TextView
        lateinit var ratingBar: RatingBar
        lateinit var tvSummary: TextView

        init{
            imageArticle= itemView.findViewById(R.id.imageArticle)
            tvTitle= itemView.findViewById(R.id.tvTitle)
            tvAuthor= itemView.findViewById(R.id.tvAuthor)
            //ratingBar= itemView.findViewById(R.id.ratingBar)
            tvSummary= itemView.findViewById(R.id.tvSummary)
        }
        fun bind(article: Article){
            tvTitle.text = article.title
            tvAuthor.text = article.author
            tvSummary.text = article.summary
            imageArticle.setImageResource(article.image)
            //ratingBar.rating = article.rating
        }
    }



}