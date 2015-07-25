package com.wallforfry.budget_etudiant;

/**
 * Created by Wallerand on 22/07/2015.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class CustomAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_DIVIDER = 2;

    private ArrayList<String> mData = new ArrayList<String>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public int image = 0;


    private Context mContext;
    private List<Item> mListP;

    public CustomAdapter(Context context, List<Item> aListP) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mContext = context;
        mListP = aListP;
        image++;
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mListP.size();
    }

    @Override
    public Object getItem(int position) {
        return mListP.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isEnabled(int position) {
        if(position==1){ return false;}
        else if(position==2){ return false;}
        else if(position==6){ return false;}
        else{ return true;}
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem =null;
       // ViewHolder holder = null;
        int rowType = mListP.get(position).prenom;
        if (convertView == null) {
           /* holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:*/

                    //convertView = mInflater.inflate(R.layout.rowtitle, null);
                    /*TextView textView = (TextView) convertView.findViewById(R.id.text);
                    ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
                    imageView.setImageResource(mListP.get(position).genre);
                    textView.setText(mListP.get(position).nom);

                  /*  break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.rowsubheader, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }*/

           // convertView.setTag(holder);
        } else {
            layoutItem = (LinearLayout) convertView;
            //holder = (ViewHolder) convertView.getTag();
        }
        switch (rowType) {
            case TYPE_ITEM:
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.rowtitle, parent, false);
                TextView textView = (TextView) layoutItem.findViewById(R.id.text);
                ImageView imageView = (ImageView) layoutItem.findViewById(R.id.icon);
                imageView.setImageResource(mListP.get(position).genre);
                textView.setText(mListP.get(position).nom);
                textView.setTag(mListP.get(position).nom);
                break;


        //holder.textView.setText(mData.get(position));
            case TYPE_SEPARATOR:
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.rowsubheader, parent, false);
                layoutItem.isEnabled();
                TextView textView2 = (TextView) layoutItem.findViewById(R.id.textSeparator);
                textView2.setText(mListP.get(position).nom);
                break;

            case TYPE_DIVIDER:
                layoutItem = (LinearLayout) mInflater.inflate(R.layout.rowdivider, parent, false);
                layoutItem.isEnabled();
                View divider  = (View) layoutItem.findViewById(R.id.divider);
                divider.setVisibility(View.VISIBLE);
                break;
        }


        return layoutItem;
    }



    public static class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

}
