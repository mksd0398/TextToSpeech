package com.parta.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        button.setOnClickListener {
            speakOut()
        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                button.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    private fun speakOut() {
        val text = input.text.toString()
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}