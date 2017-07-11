package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("red","weṭeṭṭi",R.drawable.color_red));
        Num.add(new Word("green","chokokki",R.drawable.color_green));
        Num.add(new Word("brown","ṭakaakki",R.drawable.color_brown));
        Num.add(new Word("gray","ṭopoppi",R.drawable.color_gray));
        Num.add(new Word("black","kululli",R.drawable.color_black));
        Num.add(new Word("white","kelelli",R.drawable.color_white));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_colors);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);


    }
}
