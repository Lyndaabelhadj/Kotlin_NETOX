package tn.esprit.android_netox_miniprojet.views

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.ProgressBar
import android.widget.SimpleAdapter
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.google.common.collect.ImmutableList
import kotlinx.android.synthetic.main.activity_home_video.*
import tn.esprit.android_netox_miniprojet.R

class HomeVideoActivity : AppCompatActivity(), Player.Listener{

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTv: TextView

/*private var player: SimpleExoPlayer? = null
private var playWhenReady = true
private var currentWindow = 0
private var playbackPosition: Long = 0*/


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home_video)

    //initPlayer()



/*
private fun initPlayer() {
   player = SimpleExoPlayer.Builder(this).build()
    video_view.player = player

    val videoUrl = "https://www.youtube.com/watch?v=UzeHZsPzCTk"
    object : YouTubeExtractor(this){
        override fun onExtractionComplete(
            ytFiles: SparseArray<YtFile>?,
            videoMeta: VideoMeta?
        ) {
            if(ytFiles != null)
            {
                val itag =137
                val audioTag = 140
                val videoUrl = ytFiles[itag].url
                val audioUrl = ytFiles[audioTag].url

                val audioSource : MediaSource = ProgressiveMediaSource
                    .Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(MediaItem.fromUri(audioUrl))

                val videoSource : MediaSource = ProgressiveMediaSource
                    .Factory(DefaultHttpDataSource.Factory())
                    .createMediaSource(MediaItem.fromUri(videoUrl))

                player!!.setMediaSource(MergingMediaSource(
                    true,videoSource,audioSource),
                    true
                )
                player!!.prepare()
                player!!.playWhenReady = playWhenReady
                player!!.seekTo(currentWindow, playbackPosition)
            }
        }

    }.extract(videoUrl,false,true)
}

override fun onStart() {
    super.onStart()
    if(Util.SDK_INT >= 24)
        initPlayer()
}

override fun onResume() {
    super.onResume()
    if(Util.SDK_INT < 24 || player == null)
    {
        initPlayer()
        hideSystemUi()
    }
}

private fun hideSystemUi() {
    video_view.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            )
}

override fun onPause() {
    if(Util.SDK_INT < 24) releasePlayer()
    super.onPause()
}

override fun onStop() {
    if(Util.SDK_INT < 24) releasePlayer()
    super.onStop()
}

private fun releasePlayer() {
    if(player != null)
    {
        playWhenReady = player!!.playWhenReady
        playbackPosition = player!!.currentPosition
        currentWindow = player!!.currentWindowIndex
        player!!.release()
        player = null
    }
} */

 progressBar = findViewById(R.id.progressBar)
 titleTv = findViewById(R.id.title)

 setupPlayer()
 addMP3()
 addMP4Files()

 if (savedInstanceState != null) {
     savedInstanceState.getInt("mediaItem").let { restoredMedia ->
         val seekTime = savedInstanceState.getLong("SeekTime")
         player.seekTo(restoredMedia, seekTime)
     }
 }


}

//second
private fun addMP4Files() {
 val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
 val mediaItem2 = MediaItem.fromUri(getString(R.string.myTestMp4))
 val newItems: List<MediaItem> = ImmutableList.of(
     mediaItem,
     mediaItem2
 )
 player.addMediaItems(newItems)
 player.prepare()
}

//First function
private fun setupPlayer() {
 player = ExoPlayer.Builder(this).build()
 playerView = findViewById(R.id.video_view)
 playerView.player = player
 player.addListener(this)
}

//3emee
private fun addMP3() {
 // Build the media item.
 val mediaItem = MediaItem.fromUri(getString(R.string.test_mp3))
 player.setMediaItem(mediaItem)
 // Set the media item to be played.
 player.setMediaItem(mediaItem)
 // Prepare the player.
 player.prepare()
}


override fun onStop() {
 super.onStop()
 player.release()
}

override fun onResume() {
 super.onResume()
 setupPlayer()
 addMP3()
 addMP4Files()
}

//4emeee
// handle loading
override fun onPlaybackStateChanged(state: Int) {
 when (state) {
     Player.STATE_BUFFERING -> {
         progressBar.visibility = View.VISIBLE

     }
     Player.STATE_READY -> {
         progressBar.visibility = View.INVISIBLE
     }
 }
}

//get Title from metadata
override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

 titleTv.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "no title found"

}

//5emee
// save details if Activity is destroyed
override fun onSaveInstanceState(outState: Bundle) {
 super.onSaveInstanceState(outState)
 Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
 // current play position
 outState.putLong("SeekTime", player.currentPosition)
 // current mediaItem
 outState.putInt("mediaItem", player.currentMediaItemIndex)
}

override fun onDestroy() {
 super.onDestroy()
 Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
}

companion object {
 private const val TAG = "MainActivity"
}



}
