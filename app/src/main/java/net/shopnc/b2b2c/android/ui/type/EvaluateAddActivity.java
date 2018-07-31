package net.shopnc.b2b2c.android.ui.type;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.EvaluateAddGoodsListAdapter;
import net.shopnc.b2b2c.android.bean.GoodsDetailForEvaluateAdd;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.FileUtils;
import net.shopnc.b2b2c.android.common.JsonFastUtil;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.OkHttpUtil;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.NoScrollListViewNormal;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wj on 2016/1/5.
 *追加评论界面
 */
public class EvaluateAddActivity extends BaseActivity {

    private MyShopApplication myApplication;
    private Button btn_commit;
    private LinearLayout ll_description;
    private EvaluateAddGoodsListAdapter adapter;

    private NoScrollListViewNormal lvGoodEvaluate;
    private PhotoBottomDialog bottomDialog;

    Map<String, String> commentCache = new HashMap<String, String>();
    private List<GoodsDetailForEvaluateAdd> goodsDetailForEvaluateAdd = new ArrayList<GoodsDetailForEvaluateAdd>();
    private String order_id;
    private  Gson gson ;

    //商品图片缓存， 第一层标记商品编号，二层标记照片位置，第三层为图片属性
    HashMap<String, List<ImageFile>> itemImageBean = new HashMap<String, List<ImageFile>>();

    public static final int COMMENT_SUCCESS = 122;
    public static final int SELELCT_FILE_TO_UPLOAD_ITEM = 108;
    private static final int FLAG_CHOOSE_PHONE = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("评价订单");
        myApplication = (MyShopApplication) getApplicationContext();
        gson = new Gson();
        order_id = getIntent().getStringExtra("order_id");
        LogHelper.e("order_id", order_id);
        initView();
        laodOrderData(order_id);
    }

    private void initView(){

        lvGoodEvaluate = (NoScrollListViewNormal) findViewById(R.id.lvGoodEvaluate);
        adapter = new EvaluateAddGoodsListAdapter(EvaluateAddActivity.this);
        lvGoodEvaluate.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        ll_description = (LinearLayout) findViewById(R.id.ll_description);
        ll_description.setVisibility(View.GONE);
        btn_commit = (Button) findViewById(R.id.btn_commit) ;

    }

    //获取订单商品数据
    private void laodOrderData(String order_id) {
        String url = Constants.URL_ORDER_EVALUATE_ADD;
        url += "&key=" + myApplication.getLoginKey() + "&order_id=" + order_id;

        OkHttpUtil.getAsyn(this, url, new OkHttpUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                T.showShort(EvaluateAddActivity.this, "请求失败");
                e.printStackTrace();
            }

            @Override
            public void onResponse(String response) {
                LogHelper.e("response", response);
                ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                LogHelper.e("data", data.toString());
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {

                        JSONObject obj = new JSONObject(data.getDatas());
                        LogHelper.e("obj", obj.toString());
                        goodsDetailForEvaluateAdd = GoodsDetailForEvaluateAdd.newInstanceList(obj.optString("evaluate_goods"));
                        LogHelper.e("goodsDetailForEvaluate", goodsDetailForEvaluateAdd.toString());
                        //刷新列表
                        adapter.setGoodsDetailForEvaluateList(goodsDetailForEvaluateAdd);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                } else {
                    T.showLong(EvaluateAddActivity.this, "网络异常");
                }


            }
        });
    }


/*
    //获取订单商品数据(原始数据解析)
    private void laodOrderData(String order_id){
        String url = Constants.URL_ORDER_EVALUATE_ADD;
        url += "&key=" + myApplication.getLoginKey() + "&order_id=" + order_id;
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("json", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);

                        goodsDetailForEvaluateAdd = GoodsDetailForEvaluateAdd.newInstanceList(obj.optString("evaluate_goods"));
                        LogHelper.e("goodsDetailForEvaluate", goodsDetailForEvaluateAdd.toString());
                        //刷新列表
                        adapter.setGoodsDetailForEvaluateList(goodsDetailForEvaluateAdd);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                } else {
                    T.showLong(EvaluateAddActivity.this, "网络异常");
                }

            }
        });
    }
*/

    /**
     * 上传图片
     * @param file
     */
    public void uploadImage(File file) {

        String url = Constants.URL_ORDER_EVALUATE_UPLOAD_IAMGE;
/*
        try {
            OkHttpUtil.post(EvaluateAddActivity.this,url, new OkHttpUtil.ResultCallback<String>() {
                @Override
                public void onError(Request request, Exception e) {
                    T.showShort(EvaluateAddActivity.this, "请求失败");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(String response) {

                    LogHelper.e("response---->file",response);


                }
            },file,"file",new OkHttpUtil.Param[]{
                    new OkHttpUtil.Param("key", myApplication.getLoginKey()),
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        HashMap<String,String> params = new HashMap<String,String>();
        params.put("key", myApplication.getLoginKey());
        HashMap<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("file", file);

        LogHelper.d("wj---file", String.valueOf(file));
        LogHelper.d("wj---file", myApplication.getLoginKey());

        RemoteDataHandler.asyncMultipartPostString(Constants.URL_ORDER_EVALUATE_UPLOAD_IAMGE, params, fileMap, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("json", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        int file_id = obj.optInt("file_id");
                        String file_name = obj.optString("file_name");
                        String origin_file_name = obj.optString("origin_file_name");
                        String file_url = obj.optString("file_url");
                        ImageFile imageFile = new ImageFile();
                        imageFile.setFile_id(file_id);
                        imageFile.setFile_name(file_name);
                        imageFile.setFile_url(file_url);
                        imageFile.setOrigin_file_name(origin_file_name);

                        LogHelper.e("imageFile", imageFile.toString());
                        bottomDialog = adapter.getBottomDialog();
                        if (itemImageBean.get(bottomDialog.getId()) != null) {
                            itemImageBean.get(bottomDialog.getId()).add(imageFile);
                        } else {
                            List<ImageFile> imageList = new ArrayList<ImageFile>();
                            imageList.add(imageFile);

                            itemImageBean.put(bottomDialog.getId(), imageList);
                        }

                        LogHelper.e("itemImageBean", itemImageBean.toString());
                        adapter.setData(itemImageBean);
                        bottomDialog.getmPhotoAdapter().setItemImageBean(itemImageBean);
                        itemImageBean = bottomDialog.getmPhotoAdapter().getItemImageBean();
                        bottomDialog.getmPhotoAdapter().notifyDataSetChanged();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    T.showShort(EvaluateAddActivity.this, "文件上传失败");
                }


            }
        });

    }





    //提交
    public void btnCommitClick(View view){
        String url = Constants.URL_ORDER_EVALUATE_ADD_COMMIT;
        commentCache = adapter.getCommentCache();

        Map<String, String> params = new HashMap<String, String>();

        params.put("key", myApplication.getLoginKey());
        params.put("order_id", order_id);

        for (int i = 0; i < goodsDetailForEvaluateAdd.size(); i++) {
            GoodsDetailForEvaluateAdd evaluateAdd = goodsDetailForEvaluateAdd.get(i);
            if (TextUtils.isEmpty(commentCache.get(evaluateAdd.getGeval_id()))) {
                T.showShort(EvaluateAddActivity.this, "请填写商品评论");
                return;
            }
            if (commentCache.get(evaluateAdd.getGeval_id()).length() < 10) {
                Toast.makeText(EvaluateAddActivity.this, "商品评论不能少于10个字", Toast.LENGTH_SHORT).show();
                return;
            }

            params.put("goods[" + evaluateAdd.getGeval_id() + "][comment]", commentCache.get(evaluateAdd.getGeval_id()));

            //遍历图片参数
            Set recSet = itemImageBean.keySet();
            Iterator it = recSet.iterator();
            while (it.hasNext()) {
                String geval_id = (String) it.next();
                List<ImageFile> photoItem = itemImageBean.get(geval_id);

                for (int imageItemPosition = 0; imageItemPosition < photoItem.size(); imageItemPosition++) {
                    ImageFile imageFile = photoItem.get(imageItemPosition);
                    params.put("goods[" + geval_id + "][evaluate_image][" + imageItemPosition + "]", imageFile.getFile_name());
                }
            }


        }

        LogHelper.e("params",params.toString());
        OkHttpUtil.postAsyn(EvaluateAddActivity.this, url, new OkHttpUtil.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response) {
                        LogHelper.e("response", response);
                         ResponseData data = JsonFastUtil.fromJsonFast(response, ResponseData.class);
                        //ResponseData data = gson.fromJson(response, ResponseData.class);
                        if (data.getCode() == HttpStatus.SC_OK) {
                            if (data.getDatas().equals("1")) {
                                setResult(COMMENT_SUCCESS);
                                T.showShort(EvaluateAddActivity.this, "评论成功");
                            }
                        }
                        finish();

                    }
                }, params
        );


    }


    /**
     * 选择照片后的回调方法
     * @param requestCode
     * @param resultCode
     * @param data
     */

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    String path = FileUtils.getPath(this, uri);

                    File file = new File(path);

                    if (file.length() < 1024 * 1024) {
                        uploadImage(file);
                    } else {
                        T.showShort(EvaluateAddActivity.this, "图片文件过大，请上传1M以下的图片");
                    }
                }
                break;
            case FLAG_CHOOSE_PHONE:
                if (resultCode == RESULT_OK) {
                    String imgpath = myApplication.getImgPath();
                    LogHelper.e("imgpath",imgpath);
                    File file = new File(imgpath);
                    if (file.length() < 1024 * 1024) {
                        uploadImage(file);
                    } else {
                        T.showShort(EvaluateAddActivity.this, "图片文件过大，请上传1M以下的图片");
                    }

                }
        }

    }



}
