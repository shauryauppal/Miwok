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

public class FamilyActivity extends AppCompatActivity {


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
    @Override
    protected void onStop() {
        super.onStop();
        //this would help use release the resource when we leave the Activity
        releaseMediaPlayer();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        Num.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        Num.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
    ;

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_family);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = Num.get(i);

                int result = mAudioManager.requestAudioFocus(mAudioFocus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = mp.create(FamilyActivity.this, word.getMusic());
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
