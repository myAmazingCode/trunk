package net.shopnc.b2b2c.android.ui.order;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.widget.RatingBar;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.EvaluateOrderAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by snm on 2016/7/23.
 */

public class OrderEvaluateActivity extends BaseActivity {
    @Bind(R.id.rbDescriptionScore)
    RatingBar rbDescriptionScore;
    @Bind(R.id.rbServiceScore)
    RatingBar rbServiceScore;
    @Bind(R.id.rbGoodsFast)
    RatingBar rbGoodsFast;
    @Bind(R.id.lvEvaluate)
    MyListView lvEvaluate;

    private String ordersId;
    private EvaluateOrderAdapter adapter;
    private List<OrdersGoodsVo> goodsVoList;
    private StoreInfo storeInfo;
    private SparseArray<List<ImageFile>> imageSpaList;   //商品晒图列表
    private SparseArray<String> contentList;   //商品评价内容列表
    private SparseArray<String> scoreList;   //商品评分列表
    private SparseArray<Boolean> isAnonymousList;   //是否匿名列表  0不匿名 1匿名
    private List<Integer> orderGoodsIdList;
    private List<Integer> goodsIdList;

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setCommonHeader("评价订单");
        ordersId = getIntent().getStringExtra("ordersId");
        requestOrdersDetails(ordersId);
        imageSpaList = new SparseArray<>();
        contentList = new SparseArray<>();
        scoreList = new SparseArray<>();
        isAnonymousList = new SparseArray<>();
        adapter = new EvaluateOrderAdapter(context);
        adapter.setImageSpaList(imageSpaList);
        adapter.setContentList(contentList);
        adapter.setScoreList(scoreList);
        adapter.setIsAnonymousList(isAnonymousList);
        lvEvaluate.setAdapter(adapter);
        orderGoodsIdList = new ArrayList<>();
        goodsIdList = new ArrayList<>();
        setLimitScore();
    }


    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_evaluate);
    }

    private void setLimitScore() {
        RatingBar.OnRatingBarChangeListener listener = new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating < 1) {
                    ratingBar.setRating(1);
                }
            }
        };
        rbDescriptionScore.setOnRatingBarChangeListener(listener);
        rbServiceScore.setOnRatingBarChangeListener(listener);
        rbGoodsFast.setOnRatingBarChangeListener(listener);
    }

    private void requestOrdersDetails(String ordersId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("memberId", application.getMemberID());
        params.put("orderId", ordersId);
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_EVALUATE_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                goodsVoList = JsonUtil.toBean(data, "ordersGoodsList", new TypeToken<ArrayList<OrdersGoodsVo>>() {
                }.getType());
                storeInfo = JsonUtil.toBean(data, "store", new TypeToken<StoreInfo>() {
                }.getType());
                adapter.setmDatas(goodsVoList);
                adapter.notifyDataSetChanged();
                for (OrdersGoodsVo ordersGoodsVo : goodsVoList) {
                    orderGoodsIdList.add(ordersGoodsVo.getOrdersGoodsId());
                    goodsIdList.add(ordersGoodsVo.getGoodsId());
                    imageSpaList.put(ordersGoodsVo.getGoodsId(), new ArrayList<ImageFile>());
                    scoreList.put(ordersGoodsVo.getGoodsId(), "5");
                    isAnonymousList.put(ordersGoodsVo.getGoodsId(), true);
                }
            }
        }, params);
    }


    @OnClick(R.id.btnAddEvaluateOrder)
    public void AddEvaluateOrder() {
        PostEvaluateAdd();
    }

    public void PostEvaluateAdd() {
        ArrayList<OkHttpUtil.Param> params = new ArrayList<>();
        params.add(new OkHttpUtil.Param("token", application.getToken()));
        params.add(new OkHttpUtil.Param("orderId", ordersId));
        params.add(new OkHttpUtil.Param("storeId", storeInfo.getStoreId() + ""));
        params.add(new OkHttpUtil.Param("memberId", application.getMemberID()));
        params.add(new OkHttpUtil.Param("memberName", application.getMemberName()));
        for (Integer i : orderGoodsIdList) {
            params.add(new OkHttpUtil.Param("orderGoodsIdList", i + ""));
        }
        for (Integer i : goodsIdList) {
            params.add(new OkHttpUtil.Param("goodsIdList", i + ""));

        }
        for (int i = 0; i < goodsIdList.size(); i++) {
            params.add(new OkHttpUtil.Param("isAnonymousList", isAnonymousList.get(goodsIdList.get(i)) ? "1" : "0"));
        }
        for (int i = 0; i < goodsIdList.size(); i++) {
            params.add(new OkHttpUtil.Param("contentList", contentList.get(goodsIdList.get(i)) == null ? "" : contentList.get(goodsIdList.get(i))));
        }
        for (int i = 0; i < goodsIdList.size(); i++) {
            params.add(new OkHttpUtil.Param("scoreList", scoreList.get(goodsIdList.get(i))));
        }
        for (int i = 0; i < goodsIdList.size(); i++) {
            int goodsId = goodsIdList.get(i);
            List<ImageFile> imageFiles = imageSpaList.get(goodsId);
            String imgString = "";
            for (int f = 0; f < imageFiles.size(); f++) {
                if (f != 0) {
                    imgString = imgString + "_";
                }
                imgString = imgString + imageFiles.get(f).getName();
            }
            params.add(new OkHttpUtil.Param("imageList", imgString));
        }
        params.add(new OkHttpUtil.Param("descriptionCredit", (rbDescriptionScore.getRating() + "").substring(0, 1)));
        params.add(new OkHttpUtil.Param("serviceCredit", (rbServiceScore.getRating() + "").substring(0, 1)));
        params.add(new OkHttpUtil.Param("deliveryCredit", (rbGoodsFast.getRating() + "").substring(0, 1)));

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_EVALUATE_ADD, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TToast.showShort(context, "评价成功");
                finish();
            }
        }, (OkHttpUtil.Param[]) params.toArray(new OkHttpUtil.Param[params.size()]));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoBottomDialog.SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imagePath = LoadImage.getPath(this, uri);
                    fileToUpload();
                }
                break;
            case PhotoBottomDialog.FLAG_CHOOSE_PHONE:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        imagePath = Constants.APP_DIR + "/" + adapter.getPhotoBottomDialog().getImageName();
                        fileToUpload();
                    }
                }
            case  300:
                if (data != null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fileToUpload();
                }
                break;
        }
    }

    private void fileToUpload() {
        File file = new File(imagePath);
        if (file.length() < 1024 * 1024) {
            ShopHelper.postImage(context, file, new ShopHelper.PostImage() {
                @Override
                public void postImageSuccess(ImageFile imageFile) {
                    int goodsId = adapter.getPhotoBottomDialog().getId();
                    imageSpaList.get(goodsId).add(imageFile);
                    adapter.getPhotoBottomDialog().getPhotoAdapter().setImageFileList(imageSpaList.get(goodsId));
                    adapter.getPhotoBottomDialog().getPhotoAdapter().notifyDataSetChanged();
                }
            });
        } else {
            TToast.showShort(context, "图片文件过大，请上传1M以下的图片");
            trimPhoto(Uri.fromFile(file));
        }
    }

    //裁剪照片
    public void trimPhoto(Uri uri) {
        imagePath=Constants.APP_DIR+"/"+"trim" + System.currentTimeMillis() + ".jpg";
        File trimfile = new File(imagePath);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", Uri.fromFile(trimfile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 300);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionHelper.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                adapter.getPhotoBottomDialog().showFileChooser();
            } else {
                TToast.showShort(context, "暂无法选择图片");
            }
        } else if (requestCode == PermissionHelper.USE_CAMERA) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                adapter.getPhotoBottomDialog().doGoToPhone();
            } else {
                TToast.showShort(context, "暂无法使用摄像头");
            }
        }
    }

}

