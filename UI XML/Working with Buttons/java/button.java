package e.vatsalkesarwani.intentsndservices.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import e.vatsalkesarwani.intentsndservices.R;

public class MainActivity2 extends AppCompatActivity {

    int aa=0,bb=0; //aa store score of team a
                   //bb store score of team b

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

    }

    int fa=0,fb=0;  // fa store last increment of a ( flag )
                    // fb store last increment of b ( flag )

    public void plus1a(View view)
    {
        aa+=1;
        fa=1;
        displaya();
    }

    public void plus2a(View view)
    {
        aa+=2;
        fa=2;
        displaya();
    }

    public void plus3a(View view)
    {
        aa+=3;
        fa=3;
        displaya();
    }

    public void plus1b(View view)
    {
        bb+=1;
        fb=1;
        displayb();
    }

    public void plus2b(View view)
    {
        bb+=2;
        fb=2;
        displayb();
    }

    public void plus3b(View view)
    {
        bb+=3;
        fb=3;
        displayb();
    }

    public void reset(View view)
    {
        aa=0;
        bb=0;
        displaya();
        displayb();
    }

    public void displaya()
    {
        TextView teaa=(TextView)findViewById(R.id.a);
        teaa.setText(""+aa);
    }

    public void displayb()
    {
        TextView teab=(TextView)findViewById(R.id.b);
        teab.setText(""+bb);
    }


    public void undob(View view)
    {
        if(fb==0)
            Toast.makeText(this,"Only One Undo Allowed",Toast.LENGTH_SHORT).show();
        if(fb==1)
        {
            bb-=1;
            fb=0;
            if(bb<0)
                bb=0;
            displayb();
        }
        if(fb==2)
        {
            bb-=2;
            fb=0;
            if(bb<0)
                bb=0;
            displayb();
        }
        if(fb==3)
        {
            bb-=3;
            fb=0;
            if(bb<0)
                bb=0;
            displayb();
        }
    }

    public void undoa(View view)
    {
        if(fa==0)
            Toast.makeText(this,"Only One Undo Allowed",Toast.LENGTH_SHORT).show();
        if(fa==1)
        {
            aa-=1;
            fa=0;
            if(aa<0)
                aa=0;
            displaya();
        }
        if(fa==2)
        {
            aa-=2;
            fa=0;
            if(aa<0)
               aa=0;
            displaya();
        }
        if(fa==3)
        {
            aa-=3;
            fa=0;
            if(aa<0)
                aa=0;
            displaya();
        }
    }
}
