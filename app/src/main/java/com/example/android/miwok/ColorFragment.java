package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

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

    private void releaseMediaPlayer()
    {
        if(mp!=null)
        {
            mp.release();
            mp=null;
            mAudioManager.abandonAudioFocus(mAudioFoucus);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public ColorFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.word_list,container,false);



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
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        WordAdapter adapter = new WordAdapter  (getActivity(),Num,R.color.category_colors);
        ListView listview = (ListView) rootview.findViewById(R.id.list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = Num.get(i);
//                Log.v("ColorActivity->","Current word:"+ word);
                int result = mAudioManager.requestAudioFocus(mAudioFoucus,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mp = mp.create(getActivity(), word.getMusic());
                    mp.start();

                    mp.setOnCompletionListener(mcompletion);
                }

            }
        });


        return rootview;
    }
}
