package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;

import java.util.ArrayList;

/**
 * @author dqw
 * @Time 2015/7/14
 */
public class SearchGridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> searchLists;

    public SearchGridViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int size = searchLists == null ? 0 : searchLists.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return searchLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSearchLists(ArrayList<String> list) {
        this.searchLists = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_search_item, null);
            holder = new ViewHolder();
            holder.searchKeyWord = convertView.findViewById(R.id.searchKeyWord);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String bean = searchLists.get(position);
        holder.searchKeyWord.setText(bean);

        return convertView;
    }

    class ViewHolder {
        TextView searchKeyWord;
    }
}
