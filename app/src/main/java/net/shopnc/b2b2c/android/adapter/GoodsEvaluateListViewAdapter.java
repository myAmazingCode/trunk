/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:CommendGridViewAdapter.java
 */
package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsEvaluateInfo;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import java.util.ArrayList;

/**
 * @author dqw
 * @Time 2015/8/4
 */
public class GoodsEvaluateListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GoodsEvaluateInfo> list;

    public GoodsEvaluateListViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<GoodsEvaluateInfo> getCommendLists() {
        return list;
    }

    public void setList(ArrayList<GoodsEvaluateInfo> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_goods_evaluate_item, null);
            holder = new ViewHolder();
            holder.llGoodsStar = convertView.findViewById(R.id.llGoodsStar);
            holder.tvMemberInfo = convertView.findViewById(R.id.tvMemberInfo);
            holder.tvGevalContent = convertView.findViewById(R.id.tvGevalContent);
            holder.llGoodsEvaluateitem = convertView.findViewById(R.id.llGoodsEvaluateitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsEvaluateInfo bean = list.get(position);
        int count = Integer.parseInt(bean.getGevalScores());
        holder.llGoodsStar.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(24, 24);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(R.drawable.nc_icon_goods_star);
            holder.llGoodsStar.addView(imageView);
        }
        holder.tvMemberInfo.setText(bean.getGevalFromMemberName() + " " + bean.getGevalAddtimeDate());
        holder.tvGevalContent.setText(bean.getGevalContent());
        holder.llGoodsEvaluateitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GoodsDetailsActivity) context).changeFreagemt(2);
            }
        });

        return convertView;
    }

    class ViewHolder {
        LinearLayout llGoodsStar;
        TextView tvMemberInfo;
        TextView tvGevalContent;
        LinearLayout llGoodsEvaluateitem;
    }
}
