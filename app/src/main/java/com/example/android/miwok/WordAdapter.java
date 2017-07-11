package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shaurya on 29/6/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    private int mColor;
    public WordAdapter (Activity context, ArrayList<Word> Num,int color)
    {
        super(context,0,Num);
        mColor=color;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v=convertView;
        if(v==null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item,parent,false);
        }
        Word currentWord = getItem(position);

        TextView nameTextView = (TextView) v.findViewById(R.id.miwok_text_view);

        nameTextView.setText(currentWord.getMiwokTranslation());

        TextView defaulltTextView = (TextView) v.findViewById(R.id.default_text_view);
        defaulltTextView.setText(currentWord.getdefaultTranslation());
        ImageView imageview = (ImageView) v.findViewById(R.id.miwok_image);
        if(currentWord.hasImage()) {
           imageview.setImageResource(currentWord.getImage());
            imageview.setVisibility(View.VISIBLE);
       }
       else
       {
           imageview.setVisibility(View.GONE);
       }
        // Set the theme color for the list item
                View textContainer = v.findViewById(R.id.text_container);
                // Find the color that the resource ID maps to
                       int color = ContextCompat.getColor(getContext(), mColor);
               // Set the background color of the text container View
                        textContainer.setBackgroundColor(color);

        return v;
    }

}
