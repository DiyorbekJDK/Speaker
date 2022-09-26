package com.example.texttoswitch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import java.util.*

class SecondActivity : AppCompatActivity() {
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var imageButton: ImageButton
    private lateinit var textSpeech: AppCompatTextView
    private lateinit var myLinearLayout: LinearLayout
    private var textToSpeech: TextToSpeech? = null
    private lateinit var editText: EditText
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        speechToText()
        actionBar?.hide()

    }

    private fun speechToText() {
        imageButton = findViewById(R.id.btnVoice)
        textSpeech = findViewById(R.id.textVoice)
        myLinearLayout = findViewById(R.id.bigLinear)
        imageButton.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Hello world")
            try {
                startActivityForResult(intent, 100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK || data == null) {

                val result: ArrayList<String> =
                    data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                textSpeech.text = result[0]
                textToSpeak(result[0])
//                result[0]
//                if (textSpeech.text == "Exit" || textSpeech.text == "exit") {
//                    finish()
//                }
//                if (textSpeech.text.contains("red")) {
//                    myLinearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
//                }
//                if (textSpeech.text.contains("blue")) {
//                    myLinearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
//                }
//                if (textSpeech.text.contains("green")) {
//                    myLinearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
//                }
//                if (textSpeech.text.contains("dialog")) {
//                    AlertDialog.Builder(this).apply {
//                        setTitle("Dialog")
//                        setMessage("This is a Dialog")
//                        setNegativeButton("Ok") { dialog, _ ->
//                            dialog.dismiss()
//                        }
//                    }.show()
//                }
//                if (textSpeech.text.contains("activity")) {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                }
            }
        }
    }

    private fun textToSpeak(text: String) {
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.btnSpeak)
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.US
                textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        }

    }

}