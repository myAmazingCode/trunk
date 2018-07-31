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
import net.shopnc.b2b2c.android.bean.ImageFile;

import java.util.HashMap;
import java.util.List;


/**
 * Created by wj on 2016/1/4.
 * 评论上传图片适配器
 */
public class EvaluatePhotoAdapter extends BaseAdapter {
    private String res_id;
    private Context ctx;
    private LayoutInflater inflater;
    //商品图片缓存， 第一层标记商品编号，二层标记照片位置，第三层为图片属性
    private HashMap<String, List<ImageFile>> itemImageBean;


    public EvaluatePhotoAdapter(Context ctx ,String res_id,HashMap<String, List<ImageFile>> itemImageBean){
        this.inflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
        this.itemImageBean = itemImageBean;
        this.res_id = res_id;

    }
    public HashMap<String, List<ImageFile>> getItemImageBean(){
        return this.itemImageBean;
    }
    public void setItemImageBean(HashMap<String, List<ImageFile>> itemImageBean){
        this.itemImageBean = itemImageBean;
    }

    @Override
    public int getCount() {
        return itemImageBean.get(res_id) == null ? 1 : itemImageBean.get(res_id).size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.publish_photoitem, null);
        ImageView iv = view.findViewById(R.id.iv_photo);

        if (itemImageBean.get(res_id) == null || i == itemImageBean.get(res_id).size()) {

            iv.setImageBitmap(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.addphoto));
            if (i == 5) {
                iv.setVisibility(View.GONE);
            }
        } else{
            Glide.with(ctx)
                    .load(itemImageBean.get(res_id).get(i).getFile_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_launcher)  //设置占位图
                    .error(R.drawable.ic_launcher)      //加载错误图
                    .into(iv);
        }

        return view;
    }
}
