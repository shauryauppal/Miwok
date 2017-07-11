package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("Where are you going?","minto wuksus"));
        Num.add(new Word("What is your name?","tinnә oyaase'nә"));
        Num.add(new Word("My name is","oyaaset..."));
        Num.add(new Word("How are you feeling?","michәksәs?"));
        Num.add(new Word("I’m feeling good.","kuchi achit"));
        Num.add(new Word("Are you coming?","әәnәs'aa?"));
        Num.add(new Word("Yes, I’m coming.","hәә’ әәnәm"));
        Num.add(new Word("I’m coming","әәnәm"));
        Num.add(new Word("Let’s go.","yoowutis"));
        Num.add(new Word("Come here.","әnni'nem"));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_phrases);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);


    }
}
