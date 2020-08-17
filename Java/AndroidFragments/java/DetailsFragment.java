package com.dj.androidfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    TextView language,rating;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_info, container, false);
        language = (TextView)view.findViewById(R.id.language);
        rating = (TextView)view.findViewById(R.id.rating);
        return view;
    }
    public void change(String ulanguage, String urating){
        language.setText(ulanguage);
        rating.setText(urating);
    }
}