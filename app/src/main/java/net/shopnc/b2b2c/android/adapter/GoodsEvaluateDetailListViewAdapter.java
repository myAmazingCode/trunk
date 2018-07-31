/**
 * ProjectName:AndroidShopNC2014Moblie
 * PackageName:net.shopnc.android.adapter
 * FileNmae:CommendGridViewAdapter.java
 */
package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsEvaluateInfo;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.ui.type.ImageSwitchActivity;

import java.util.ArrayList;

/**
 * @author dqw
 * @Time 2015/8/4
 */
public class GoodsEvaluateDetailListViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<GoodsEvaluateInfo> list;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public GoodsEvaluateDetailListViewAdapter(Context context) {
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
            convertView = inflater.inflate(R.layout.listview_goods_evaluate_detail_item, null);
            holder = new ViewHolder();
            holder.ivMemberAvatar = convertView.findViewById(R.id.ivMemberAvatar);
            holder.tvMemberName = convertView.findViewById(R.id.tvMemberName);
            holder.tvAddTime = convertView.findViewById(R.id.tvAddTime);
            holder.llGoodsStar = convertView.findViewById(R.id.llGoodsStar);
            holder.tvContent = convertView.findViewById(R.id.tvContent);
            holder.llImage = convertView.findViewById(R.id.llImage);
            holder.llAgain = convertView.findViewById(R.id.llAgain);
            holder.tvAddTimeAgain = convertView.findViewById(R.id.tvAddTimeAgain);
            holder.tvContentAgain = convertView.findViewById(R.id.tvGevalContentAgain);
            holder.llImageAgain = convertView.findViewById(R.id.llImageAgain);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsEvaluateInfo bean = list.get(position);

        //评论
        imageLoader.displayImage(bean.getMemberAvatar(), holder.ivMemberAvatar, options, animateFirstListener);
        holder.tvMemberName.setText(bean.getGevalFromMemberName());
        holder.tvAddTime.setText(bean.getGevalAddtimeDate());
        int count = Integer.parseInt(bean.getGevalScores());
        holder.llGoodsStar.removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(24, 24);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundResource(R.drawable.nc_icon_goods_star);
            holder.llGoodsStar.addView(imageView);
        }
        holder.tvContent.setText(bean.getGevalContent());
        showImage(holder.llImage, bean.getGevalImage240(), bean.getGevalImage1024());

        //追加评论
        if (bean.getGevalContentAgain().equals("")) {
            //没有追加评论
            holder.llAgain.setVisibility(View.GONE);
        } else {
            holder.llAgain.setVisibility(View.VISIBLE);
            holder.tvAddTimeAgain.setText(bean.getGevalAddtimeAgainDate() + "追加评价");
            holder.tvContentAgain.setText(bean.getGevalContentAgain());
            showImage(holder.llImageAgain, bean.getGevalImageAgain240(), bean.getGevalImageAgain1024());
        }

        return convertView;
    }

    private void showImage(LinearLayout llContainer, ArrayList<String> imageList, final ArrayList<String> bigImageList) {
        llContainer.removeAllViews();
        if (imageList != null && imageList.size() > 0) {
            for (int i = 0; i < imageList.size(); i++) {
                final int index = i;
                String imageUrl = imageList.get(i);
                llContainer.setVisibility(View.VISIBLE);
                ImageView imageView = new ImageView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(60, 60);
                layoutParams.setMargins(0, 0, 10, 0);
                imageView.setLayoutParams(layoutParams);
                imageView.setPadding(1, 1, 1, 1);
                imageView.setBackgroundResource(R.drawable.nc_border);
                imageLoader.displayImage(imageUrl, imageView, options, animateFirstListener);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ImageSwitchActivity.class);
                        intent.putStringArrayListExtra("image_list", bigImageList);
                        intent.putExtra("image_index", index);
                        context.startActivity(intent);
                    }
                });
                llContainer.addView(imageView);
            }
        } else {
            llContainer.setVisibility(View.GONE);
        }
    }

    class ViewHolder {
        ImageView ivMemberAvatar;
        TextView tvMemberName;
        TextView tvAddTime;
        LinearLayout llGoodsStar;
        TextView tvContent;
        LinearLayout llImage;
        LinearLayout llAgain;
        TextView tvAddTimeAgain;
        TextView tvContentAgain;
        LinearLayout llImageAgain;
    }
}
