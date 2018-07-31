package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.ImageFileBean;

import java.util.List;

/**
 * Created by qyf on 2016/1/18.
 */
public class ExchangePhotoAdapter extends BaseAdapter {

    private Context context;
    private List<ImageFileBean> images;
    private LayoutInflater inflater;

    public ExchangePhotoAdapter(Context context, List<ImageFileBean> images) {
        this.context = context;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<ImageFileBean> images){
        this.images=images;
    }

    @Override
    public int getCount() {
        return images==null?1:images.size()+1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.publish_photoitem, null);
        ImageView iv = view.findViewById(R.id.iv_photo);

        if(images==null||i==images.size()){
            iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.addphoto));
            if (i == 3) {
                iv.setVisibility(View.GONE);
            }
        }else{
            Glide.with(context)
                    .load(images.get(i).getPic())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher)  //设置占位图
                    .error(R.drawable.ic_launcher)      //加载错误图
                    .into(iv);
        }


        return view;
    }
}
