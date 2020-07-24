package e.vatsalkesarwani.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class wordAdapter extends ArrayAdapter<Word> {

    private int mColorResourseId;

    public wordAdapter(Context context, ArrayList<Word> words,int colorResourseId) {
        super(context, 0, words);
        mColorResourseId=colorResourseId;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        View ListItemView=convertView;
        if(ListItemView==null)
        {
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }


        Word currentWord =getItem(position);
        ImageView i= ListItemView.findViewById(R.id.image);
        i.setImageResource(currentWord.getResourcesId());

        TextView t= ListItemView.findViewById(R.id.miwok_text_view);
        t.setText(currentWord.getMiwokTranslation());

        TextView tt= ListItemView.findViewById(R.id.default_text_view);
        tt.setText(currentWord.getDefaultTranslation());

        View textContainer =ListItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourseId);
        textContainer.setBackgroundColor(color);

        return ListItemView;
    }

}
