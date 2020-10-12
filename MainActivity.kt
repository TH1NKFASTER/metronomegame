package com.example.myapplication

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pauseButton = findViewById(R.id.pauseButton) as Button
        val checkButton = findViewById(R.id.button2) as Button
        var textview = findViewById(R.id.textView) as TextView
        var expectbpm = findViewById(R.id.editTextNumber) as EditText
        val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        var metronome = Timer()
        var realBpm = 0.0
        pauseButton.setOnClickListener() {
            var bpm: Long = Random.nextLong(40, 250)
            realBpm = (1000 * 60 * 1.0 / (1000 * 60 / bpm))
            if (pauseButton.text == "Pause") {
                pauseButton.text = "Play"
                metronome.cancel()
                metronome.purge()
                metronome = Timer()
            } else {
                pauseButton.text = "Pause"
                metronome.schedule(
                    timerTask {
                        val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP)
                        toneGenerator.release()
                    },
                    0L,
                    (1000 * 60 / bpm)
                )
            }
        }
        checkButton.setOnClickListener() {
            textview.text = "You wrote: ${expectbpm.text}\n Real: ${realBpm.toString()}"
        }
    }


}


