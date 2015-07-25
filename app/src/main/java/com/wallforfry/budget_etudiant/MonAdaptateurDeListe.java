package com.wallforfry.budget_etudiant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Wallerand on 22/07/2015.
 */
public class MonAdaptateurDeListe extends ArrayAdapter<String> {

    private Integer[] tab_images_pour_la_liste = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_file_download_black_24dp,
            R.drawable.ic_file_upload_black_24dp,
            R.drawable.ic_add_black_24dp,
            R.drawable.ic_settings_black_24dp,
            };

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        textView.setText(getItem(position));

        if(convertView == null )
            imageView.setImageResource(tab_images_pour_la_liste[position]);
        else
            rowView = (View)convertView;

        return rowView;
    }

    public MonAdaptateurDeListe(Context context, String[] values) {
        super(context, R.layout.rowlayout, values);
    }
}
