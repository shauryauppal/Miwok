package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private MediaPlayer.OnCompletionListener mcompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
           releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFoucus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN)
            {
                mp.start();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
            else if((i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mp!=null)
            {
                mp.pause();
                mp.seekTo(0);
            }
        }
    };

    protected void onStop() {
        super.onStop();
        //this would help use release the resource when we leave the Activity
        releaseMediaPlayer();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        Num.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        Num.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        Num.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        Num.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        Num.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));
       mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_colors);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = Num.get(i);
//                Log.v("ColorActivity->","Current word:"+ word);
               int result = mAudioManager.requestAudioFocus(mAudioFoucus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = mp.create(ColorsActivity.this, word.getMusic());
                    mp.start();

                    mp.setOnCompletionListener(mcompletion);
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
            mAudioManager.abandonAudioFocus(mAudioFoucus);

        }
    }
}
