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

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mp;


    private MediaPlayer.OnCompletionListener mcompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mAudioFocus = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN)
                mp.start();
            else if(i==AudioManager.AUDIOFOCUS_LOSS)
                releaseMediaPlayer();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        Num.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        Num.add(new Word("My name is","oyaaset...",R.raw.phrase_my_name_is));
        Num.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        Num.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        Num.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        Num.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        Num.add(new Word("I’m coming","әәnәm",R.raw.phrase_im_coming));
        Num.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        Num.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_phrases);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = Num.get(i);
                int result = mAudioManager.requestAudioFocus(mAudioFocus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = mp.create(PhrasesActivity.this, word.getMusic());
                    mp.start();


                    mp.setOnCompletionListener(mcompletion);
                }

            }
        });

    }

    private void releaseMediaPlayer(){
        if(mp!=null)
        {
            mp.release();
            mp=null;
            mAudioManager.abandonAudioFocus(mAudioFocus);

        }
    }
}
