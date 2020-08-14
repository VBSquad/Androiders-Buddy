package com.dj.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private Button previousbtn, nextbtn;
    private ImageSwitcher imgsw;
    private int[] images = {R.drawable.g5,R.drawable.g6,R.drawable.g7,R.drawable.g8};
    private int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousbtn = (Button)findViewById(R.id.btnPrevious);
        nextbtn = (Button)findViewById(R.id.btnNext);
        imgsw = (ImageSwitcher) findViewById(R.id.imgSw);

        imgsw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imgVw = new ImageView(MainActivity.this);
                imgVw.setImageResource(images[position]);
                return imgVw;
            }
        });
        imgsw.setInAnimation(this, android.R.anim.fade_in);
        imgsw.setOutAnimation(this, android.R.anim.fade_out);

        previousbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>0)
                    position--;
                else if(position<0)
                    position = 0;
                imgsw.setImageResource(images[position]);
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<images.length)
                    position++;
                if(position>=images.length)
                    position = images.length-1;
                imgsw.setImageResource(images[position]);
            }
        });
    }
}