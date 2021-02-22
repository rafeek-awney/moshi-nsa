package mario.rafeek.etba3ny;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rifo on 1/2/16.
 */
public class bibleDisplayItemAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    List<String> ItemData;
    int textSize;

    public bibleDisplayItemAdapter(Context context, List<String> data, int textSize) {
        this.ItemData = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.textSize = textSize;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ItemData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.bible_display_item, null);
        holder.dispTV = rowView.findViewById(R.id.bibletxt);
        holder.dispTV.setText(ItemData.get(position));
        holder.dispTV.setTextSize(textSize);
        return rowView;
    }

    public class Holder {
        TextView dispTV;
    }
}