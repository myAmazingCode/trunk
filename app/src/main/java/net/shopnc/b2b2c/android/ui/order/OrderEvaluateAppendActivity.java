package net.shopnc.b2b2c.android.ui.order;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.widget.Button;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.OrderEvaluateAppendAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.OrdersGoodsEvaluateVo;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
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
import butterknife.OnClick;

public class OrderEvaluateAppendActivity extends BaseActivity {

    @Bind(R.id.lvEvaluate)
    MyListView lvEvaluate;
    @Bind(R.id.btnAddEvaluateOrder)
    Button btnAddEvaluateOrder;

    private String ordersId;
    private List<OrdersGoodsEvaluateVo> goodsVoList;
    private ArrayList<Integer> evaluateIdList;
    private SparseArray<List<ImageFile>> imageSpaList;   //商品晒图列表
    private SparseArray<String> contentList;   //商品评价内容列表
    private OrderEvaluateAppendAdapter adapter;
    private String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("追加评价");
        ordersId = getIntent().getStringExtra("ordersId");
        adapter=new OrderEvaluateAppendAdapter(context);
        lvEvaluate.setAdapter(adapter);
        imageSpaList=new SparseArray<>();
        contentList=new SparseArray<>();
        adapter.setImageSpaList(imageSpaList);
        adapter.setContentList(contentList);
        evaluateIdList=new ArrayList<>();
        requestOrderDetails();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_evaluate_append);
    }

    private void requestOrderDetails(){
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("memberId", application.getMemberID());
        params.put("orderId", ordersId);
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_EVALUATE_ADD_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                goodsVoList = JsonUtil.toBean(data, "evaluateGoodsOrderVoList", new TypeToken<ArrayList<OrdersGoodsEvaluateVo>>() {
                }.getType());
                adapter.setmDatas(goodsVoList);
                adapter.notifyDataSetChanged();
                for (OrdersGoodsEvaluateVo ordersGoodsVo : goodsVoList) {
                    evaluateIdList.add(ordersGoodsVo.getEvaluateId());
                    imageSpaList.put(ordersGoodsVo.getEvaluateId(), new ArrayList<ImageFile>());
                }
            }
        }, params);
    }

    @OnClick(R.id.btnAddEvaluateOrder)
    public void onClick() {
        appendEvaluateOrder();
    }

    private void appendEvaluateOrder(){
        ArrayList<OkHttpUtil.Param> params = new ArrayList<>();
        params.add(new OkHttpUtil.Param("token", application.getToken()));
        params.add(new OkHttpUtil.Param("orderId", ordersId));
        params.add(new OkHttpUtil.Param("memberId", application.getMemberID()));
        params.add(new OkHttpUtil.Param("memberName", application.getMemberName()));
        for (Integer i : evaluateIdList) {
            params.add(new OkHttpUtil.Param("evaluateIdList", i + ""));
        }
        for (int i = 0; i < evaluateIdList.size(); i++) {
            params.add(new OkHttpUtil.Param("contentList", contentList.get(evaluateIdList.get(i)) == null ? "" : contentList.get(evaluateIdList.get(i))));
        }
        for (int i = 0; i < evaluateIdList.size(); i++) {
            int goodsId = evaluateIdList.get(i);
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
        LogHelper.i("QIN",params.toString());
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_EVALUATE_ADD_SAVE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                TToast.showShort(context, "追加评价成功");
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
                    int evaluateId = adapter.getPhotoBottomDialog().getId();
                    imageSpaList.get(evaluateId).add(imageFile);
                    adapter.getPhotoBottomDialog().getPhotoAdapter().setImageFileList(imageSpaList.get(evaluateId));
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
