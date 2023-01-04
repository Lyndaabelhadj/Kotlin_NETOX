package tn.esprit.android_netox_miniprojet.views

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.android_netox_miniprojet.R
import tn.esprit.android_netox_miniprojet.models.Question
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainQuizActivity: AppCompatActivity() {

    lateinit var questionsList:ArrayList<Question>
    private var index:Int = 0
    lateinit var questionModel: Question

    private var correctAnswerCount:Int=0
    private var wrongAnswerCount:Int=0

    lateinit var countDown:TextView
    lateinit var questions:TextView
    lateinit var option1:Button
    lateinit var option2:Button
    lateinit var option3:Button
    //lateinit var option4:Button




    private var backPressedTime: Long = 0
    private var backToast: Toast? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainquiz)
        supportActionBar?.hide()

        countDown=findViewById(R.id.countdown)
        questions=findViewById(R.id.questions)
        option1=findViewById(R.id.option1)
        option2=findViewById(R.id.option2)
        option3=findViewById(R.id.option3)
        //option4=findViewById(R.id.option4)




        questionsList= ArrayList()
        questionsList.add(Question(
            "Vous surfez ou publiez au moins 4 fois par jour sur votre compte Facebook/ Instagram /Twitter.",
            "Oui",
            "Non",
            "Parfois",
            " A flow of atoms"))

        questionsList.add(Question(
            "Votre téléphone est toujours en mode sonore pour voir les notifications et appels tout de suite.",
            "Non, il est toujours en mode sourdine",
            "Parfois",
            "Oui, toujours",
            "Oui, toujours"))
        questionsList.add(Question(
            "Vous passez plus de temps avec votre écran qu’avec des relations dans la vraie vie.",
            "Non",
            "Oui",
            "Neutre",
            "Neutre"))
        questionsList.add(Question(
            "Vous avez parfois le syndrome du Fomo (peur de manquer d’information). \n",
            "Oui, je ne veux rien rater",
            "Non",
            "Parfois",
            "Parfois"))
        questionsList.add(Question(
            "Vous actualisez votre boîte email toutes les 15 minutes pour vérifier si rien “d’essentiel” n’est arrivé.",
            "Oui, toujours",
            "Non, ça ne m'interesse pas",
            "Parfois",
            "Parfois"))


        //questionsList.shuffle()
        questionModel= questionsList[index]

        setAllQuestions()

       countdown()










    }


   fun countdown(){
        var duration:Long=TimeUnit.SECONDS.toMillis(15)


        object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var sDuration:String= String.format(
                    Locale.ENGLISH,
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))

                countDown.text = sDuration

            }
            override fun onFinish() {
                index++
                if (index<questionsList.size){
                    questionModel=questionsList[index]
                    setAllQuestions()
                    resetBackground()
                    enableButton()
                    countdown()

                }
                else{

                    gameResult()

                }


            }



        }.start()



    }


    private fun correctAns(option: Button){
        option.background=getDrawable(R.drawable.right_bg)

        correctAnswerCount++



    }
    private fun wrongAns(option: Button){

        option.background=resources.getDrawable(R.drawable.wrong_bg)

        wrongAnswerCount++


    }

    private fun gameResult(){
        var intent= Intent(this,ResultActivity::class.java)

        intent.putExtra("correct",correctAnswerCount.toString())
        intent.putExtra("total",questionsList.size.toString())

        startActivity(intent)
    }



    private fun setAllQuestions() {
        questions.text=questionModel.question
        option1.text=questionModel.option1
        option2.text=questionModel.option2
        option3.text=questionModel.option3
        //option4.text=questionModel.option4
    }
    private fun enableButton(){
        option1.isClickable=true
        option2.isClickable=true
        option3.isClickable=true
        //option4.isClickable=true
    }
    private fun disableButton(){
        option1.isClickable=false
        option2.isClickable=false
        option3.isClickable=false
        //option4.isClickable=false
    }
    private fun resetBackground(){
        option1.background=resources.getDrawable(R.drawable.option_bg)
        option2.background=resources.getDrawable(R.drawable.option_bg)
        option3.background=resources.getDrawable(R.drawable.option_bg)
        //option4.background=resources.getDrawable(R.drawable.option_bg)
    }
    fun option1Clicked(view:View){
        disableButton()
        if(questionModel.option1==questionModel.answer){
            option1.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option1)

        }
        else{
            wrongAns(option1)
        }
    }

    fun option2Clicked(view:View){
        disableButton()
        if(questionModel.option2==questionModel.answer){
            option2.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option2)

        }
        else{
            wrongAns(option2)
        }
    }
    fun option3Clicked(view:View){
        disableButton()
        if(questionModel.option3==questionModel.answer){

            option3.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option3)


        }
        else{
            wrongAns(option3)
        }
    }
   /* fun option4Clicked(view: View){
        disableButton()
        if(questionModel.option4==questionModel.answer){
            option4.background=resources.getDrawable(R.drawable.right_bg)


            correctAns(option4)

        }
        else{
            wrongAns(option4)
        }
    }*/

    override fun onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finish()
        }

        else {
            backToast = Toast.makeText(baseContext, "DOUBLE PRESS TO QUIT GAME", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }





}