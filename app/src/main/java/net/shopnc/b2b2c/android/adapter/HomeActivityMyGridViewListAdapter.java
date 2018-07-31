package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.Home3Data;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ui.home.SubjectWebActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragmentManager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页Home3GridView适配器
 *
 * @author KingKong-HE
 * @Time 2015年1月4日
 * @Email KingKong@QQ.COM
 */
public class HomeActivityMyGridViewListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Home3Data> home3Datas;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public HomeActivityMyGridViewListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return home3Datas == null ? 0 : home3Datas.size();
    }

    @Override
    public Object getItem(int position) {
        return home3Datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<Home3Data> getHome3Datas() {
        return home3Datas;
    }

    public void setHome3Datas(ArrayList<Home3Data> home3Datas) {
        this.home3Datas = home3Datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.tab_home_item_home3_gridview_item, null);
            holder = new ViewHolder(convertView);
            holder.ImageViewImagePic01 = convertView.findViewById(R.id.ImageViewImagePic01);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Home3Data bean = home3Datas.get(position);

//		new MyBackAsynaTask(bean.getImage(), holder.ImageViewImagePic01, context).execute();
        imageLoader.displayImage(bean.getImage(), holder.ImageViewImagePic01, options, animateFirstListener);
        OnImageViewClick(holder.ImageViewImagePic01, bean.getType(), bean.getData());

        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.ImageViewImagePic01)
        ImageView ImageViewImagePic01;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

//    static class ViewHolder {
//        ImageView ImageViewImagePic01;
//    }

    public void OnImageViewClick(View view, final String type, final String data) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("keyword")) {//搜索关键字
                    Intent intent = new Intent(context, GoodsListFragmentManager.class);
                    intent.putExtra("keyword", data);
                    intent.putExtra("gc_name", data);
                    context.startActivity(intent);
                } else if (type.equals("special")) {//专题编号
                    Intent intent = new Intent(context, SubjectWebActivity.class);
                    intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + data + "&type=html");
                    context.startActivity(intent);
                } else if (type.equals("goods")) {//商品编号
                    Intent intent = new Intent(context, GoodsDetailsActivity.class);
                    intent.putExtra("goods_id", data);
                    context.startActivity(intent);
                } else if (type.equals("url")) {//地址
                    Intent intent = new Intent(context, SubjectWebActivity.class);
                    intent.putExtra("data", data);
                    context.startActivity(intent);
                }
            }
        });
    }


}
