package net.shopnc.b2b2c.android.custom.dialog;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.circlelibrary.ImageCycleView;
import net.shopnc.b2b2c.android.common.LoadImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/7/7 14:35.
 */
public class ClickBigImageDialog extends AlertDialog {
    @Bind(R.id.icvImg)
    ImageCycleView icvImg;

    private Context context;
    private ArrayList<String> urlList;
    private int position;


    public ClickBigImageDialog(Context context, List<String> urlList, int position) {
        super(context);
        this.context = context;
        this.urlList = new ArrayList<>();
        this.urlList.addAll(urlList);
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.click_bigimage_list_show);
        ButterKnife.bind(this);

        icvImg.setFocusable(true);
        icvImg.setFocusableInTouchMode(true);
        icvImg.requestFocus();
        icvImg.requestFocusFromTouch();
        loadImages();

    }

    private void loadImages() {
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
            }

            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                LoadImage.loadRemoteImg(context, imageView, imageURL);
            }
        };
        icvImg.setImageResources(urlList, mAdCycleViewListener, false);
        icvImg.pushImageCycle();
        icvImg.setCurrentShow(position);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.tvOut)
    public void onClick() {
        this.dismiss();
    }
}
