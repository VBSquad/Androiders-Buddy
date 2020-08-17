package com.dj.androidfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

public class ListMenuFragment extends ListFragment {
    String[] language = new String[] { "C","C++","Java","Python",
    "Julia","PHP","MySQL","Kotlin","Okay fine"};
    String[] rating = new String[]{"4 star","4.5 star","5 star","5 star","4 star","4 star","4 star","4 star","4.9 Star", "Okay fine"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.listitems_info, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, language);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment txt = (DetailsFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change("Language: "+ language[position],"Rating : "+ rating[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }
}