package net.shopnc.b2b2c.android.adapter.fenxiao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.CityList;
import net.shopnc.b2b2c.android.bean.fenxiao.GoodsList;
import net.shopnc.b2b2c.android.bean.fenxiao.MsgList;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.custom.GlideCircleTransform;
import net.shopnc.b2b2c.android.lib.tab.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by snm on 2016/9/28.
 */
public class ZhiBoTakeMessageAdapter extends BaseAdapter{
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private LayoutInflater inflater;
    ArrayList<MsgList> mList  = new ArrayList<MsgList>();
    public ZhiBoTakeMessageAdapter(Context context){
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }
    public void addmList(ArrayList<MsgList> list) {
        if(list!= null) {
            this.mList.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void setmList(ArrayList<MsgList> list) {
        mList.clear();
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.activity_zhibo_toke_item, null);
            holder = new MyViewHolder();
            holder.take_text = convertView.findViewById(R.id.take_text);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        MsgList msgList = mList.get(position);
        holder.take_text.setText(msgList.getMember_name() + ":" + msgList.getMsg_txt());
        return convertView;
    }
    public class MyViewHolder {
        TextView take_text;
    }

}

//public class ZhiBoTakeMessageAdapter extends RecyclerView.Adapter<ZhiBoTakeMessageAdapter.MyViewHolder> {
//
//    private OnItemClickListener onItemClickListener;
//    private Context mContext;
//    private LayoutInflater inflater;
//    ArrayList<MsgList> mList  = new ArrayList<MsgList>();
//    public ZhiBoTakeMessageAdapter(Context context){
//        this.mContext = context;
//        inflater = LayoutInflater.from(mContext);
//    }
//    public void addmList(ArrayList<MsgList> list) {
//        if(list!= null) {
//            this.mList.addAll(list);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void setmList(ArrayList<MsgList> list) {
//        mList.clear();
//        this.mList = list;
//        notifyDataSetChanged();
//    }
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View layout =inflater.inflate(R.layout.activity_zhibo_toke_item, parent, false);
//        return new MyViewHolder(layout);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, final int pos) {
//        MsgList msgList = mList.get(pos);
//        holder.take_text.setText(msgList.getMsg_txt());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList == null ? 0 : mList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView take_text;
//        public MyViewHolder(View view) {
//            super(view);
//            take_text = (TextView) view.findViewById(R.id.take_text);
//        }
//
//    }
//}
