package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList< Word > Num= new ArrayList< Word >();
        Num.add(new Word("father","әpә",R.drawable.family_father));
        Num.add(new Word("son","angsi",R.drawable.family_son));
        Num.add(new Word("mother","әṭa",R.drawable.family_mother));
    ;

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_family);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);


    }
}
