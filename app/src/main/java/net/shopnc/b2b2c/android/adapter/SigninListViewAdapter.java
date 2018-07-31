package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.SigninInfo;

import java.util.ArrayList;

/**
 * 签到列表适配器
 */
public class SigninListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<SigninInfo> signinInfoArrayList;

    public SigninListViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return signinInfoArrayList == null ? 0 : signinInfoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return signinInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<SigninInfo> getSigninInfoArrayList() {
        return signinInfoArrayList;
    }

    public void setList(ArrayList<SigninInfo> signinInfoArrayList) {
        this.signinInfoArrayList = signinInfoArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listview_signin_item, null);
            holder = new ViewHolder();
            holder.tvPoints = convertView.findViewById(R.id.tvPoints);
            holder.tvAddtime = convertView.findViewById(R.id.tvAddtime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SigninInfo signinInfo = signinInfoArrayList.get(position);
        holder.tvPoints.setText("+" + signinInfo.getSlPoints());
        holder.tvAddtime.setText(signinInfo.getSlAddtimeText());

        return convertView;
    }

    class ViewHolder {
        TextView tvPoints;
        TextView tvAddtime;
    }
}
