package e.vatsalkesarwani.miwok;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class colorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        setTitle("Color");

        final ArrayList<Word> colors=new ArrayList<Word>();

        colors.add(new Word("red","weṭeṭṭi",R.mipmap.color_red));
        colors.add(new Word("green", "chokokki",R.mipmap.color_green));
        colors.add(new Word("brown", "ṭakaakki",R.mipmap.color_brown));
        colors.add(new Word("gray", "ṭopoppi",R.mipmap.color_gray));
        colors.add(new Word("black", "kululli",R.mipmap.color_black));
        colors.add(new Word("white", "kelelli",R.mipmap.color_white));
        colors.add(new Word("dusty yellow", "ṭopiisә",R.mipmap.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә",R.mipmap.color_mustard_yellow));

        wordAdapter bb=new wordAdapter(this, colors,R.color.category_color);  //simple_list_item_1 is predefined layout in android
        ListView listView = (ListView)findViewById(R.id.root1);
        listView.setAdapter(bb);
    }
}
