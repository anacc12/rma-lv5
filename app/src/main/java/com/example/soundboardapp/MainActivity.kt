package com.example.soundboardapp

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSoundPool: SoundPool
    private var mLoaded: Boolean = false
    var mSoundMap: HashMap<Int, Int> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setUpUi()
        this.loadSounds()
    }

    private fun setUpUi() {
        this.first.setOnClickListener(this)
        this.second.setOnClickListener(this)
        this.third.setOnClickListener(this)
    }
    private fun loadSounds() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mSoundPool = SoundPool.Builder().setMaxStreams(10).build()
        } else {
            this.mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
        }
        this.mSoundPool.setOnLoadCompleteListener { _, _, _ -> mLoaded = true }
        this.mSoundMap[R.raw.cat] = this.mSoundPool.load(this, R.raw.cat, 1)
        this.mSoundMap[R.raw.bird] = this.mSoundPool.load(this, R.raw.bird, 1)
        this.mSoundMap[R.raw.dog] = this.mSoundPool.load(this, R.raw.dog, 1)
    }

     override fun onClick(v: View) {
        if (!this.mLoaded) return
        when (v.id) {
            R.id.first -> playSound(R.raw.cat)
            R.id.second -> playSound(R.raw.bird)
            R.id.third -> playSound(R.raw.dog)
        }
    }
    private fun playSound(selectedSound: Int) {
        val soundID = this.mSoundMap[selectedSound] ?: 0
        this.mSoundPool.play(soundID, 1f, 1f, 1, 0, 1f)
    }
}
