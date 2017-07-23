package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mp;

    private MediaPlayer.OnCompletionListener mcompetition = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeLister = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focuschange) {

            if( (focuschange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focuschange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mp!=null)
            {
                // Pause playback because your Audio Focus was
                // temporarily stolen, but will be back soon.
                // i.e. for a phone call



                // Lower the volume, because something else is also
                // playing audio over you.
                // i.e. for notifications or navigation directions
                // Depending on your audio playback, you may prefer to
                // pause playback here instead. You do you.
                mp.pause();
                mp.seekTo(0);
            }
            else if(focuschange == AudioManager.AUDIOFOCUS_GAIN)
            {
                // Resume playback, because you hold the Audio Focus
                // again!
                // i.e. the phone call ended or the nav directions
                // are finished
                // If you implement ducking and lower the volume, be
                // sure to return it to normal here, as well.
                mp.start();
            }
            else if(focuschange==AudioManager.AUDIOFOCUS_LOSS)
            {
                //Stop playback and clean up
                releaseMediaPlayer();
            }
        }
    };




    @Override
    protected void onStop() {
        super.onStop();
        //this would help use release the resource when we leave the Activity
        releaseMediaPlayer();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        final ArrayList < Word > Num= new ArrayList< Word >();
        Num.add(new Word("One","lutti",R.drawable.number_one,R.raw.number_one));
        Num.add(new Word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        Num.add(new Word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        Num.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        Num.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        Num.add(new Word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        Num.add(new Word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        Num.add(new Word("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        Num.add(new Word("Nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        Num.add(new Word("Ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_numbers);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();
//              Toast.makeText(NumbersActivity.this, "hello colors", Toast.LENGTH_SHORT).show();
                //Get link og position
                Word word = Num.get(position);

                //    mp=MediaPlayer.create(NumbersActivity.this,word.getMusic());

                //AudioFocus Request creation
                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeLister,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = MediaPlayer.create(NumbersActivity.this,word.getMusic());
                    mp.start();
                    mp.setOnCompletionListener(mcompetition);
                }


            }
        });


    }
    private void releaseMediaPlayer()
    {
        if(mp!=null)
        {
            mp.release();
            mp=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeLister);

        }
    }
}
