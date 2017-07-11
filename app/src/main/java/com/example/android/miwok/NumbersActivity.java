package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList < Word > Num= new ArrayList< Word >();
        Num.add(new Word("One","lutti",R.drawable.number_one));
        Num.add(new Word("Two","otiiko",R.drawable.number_two));
        Num.add(new Word("Three","tolookosu",R.drawable.number_three));
        Num.add(new Word("Four","oyyisa",R.drawable.number_four));
        Num.add(new Word("Five","massokka",R.drawable.number_five));
        Num.add(new Word("Six","temmokka",R.drawable.number_six));
        Num.add(new Word("Seven","kenekaku",R.drawable.number_seven));
        Num.add(new Word("Eight","kawinta",R.drawable.number_eight));
        Num.add(new Word("Nine","wo’e",R.drawable.number_nine));
        Num.add(new Word("Ten","na’aacha",R.drawable.number_ten));

//         Log.v("number->",Num.get());
//          Log.v("number->",Num.get(1));
//        Log.v("number->",Num.get(2));

        WordAdapter adapter = new WordAdapter  (this,Num,R.color.category_numbers);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(adapter);





    }
}
