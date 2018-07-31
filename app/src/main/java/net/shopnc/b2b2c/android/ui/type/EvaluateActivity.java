package net.shopnc.b2b2c.android.ui.type;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.EvaluateGoodsListAdapter;
import net.shopnc.b2b2c.android.bean.GoodsDetailForEvaluate;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.StoreInfo;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.FileUtils;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
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
 * Created by wj on 2015/12/24.
 * 评价界面
 */
public class EvaluateActivity extends BaseActivity {
    private MyShopApplication myApplication;
    private Button btn_commit;
    private RatingBar rb_description_score  ,rb_service_score , rb_goods_fast;
    private LinearLayout ll_description;
    //设置最小星级选择
    private RatingBar.OnRatingBarChangeListener onSetMinRating;

    private EvaluateGoodsListAdapter adapter;

    private NoScrollListViewNormal lvGoodEvaluate;

    private PhotoBottomDialog bottomDialog;


    private StoreInfo storeInfo;
    private List<GoodsDetailForEvaluate> goodsDetailForEvaluate = new ArrayList<GoodsDetailForEvaluate>();
    private String order_id;

    Map<String, String> commentCache = new HashMap<String, String>();
    Map<String, Integer> rateCache = new HashMap<String, Integer>();
    Map<String, String> anonyCache =  new HashMap<String, String>();

    public static final int COMMENT_SUCCESS = 122;
    public static final int SELELCT_FILE_TO_UPLOAD_ITEM = 108;
    private static final int FLAG_CHOOSE_PHONE = 6;

    //商品图片缓存， 第一层标记商品编号，二层标记照片位置，第三层为图片属性
    HashMap<String, List<ImageFile>> itemImageBean = new HashMap<String, List<ImageFile>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        MyExceptionHandler.getInstance().setContext(this);
        setCommonHeader("评价订单");
        myApplication = (MyShopApplication) getApplicationContext();
        order_id = getIntent().getStringExtra("order_id");
        LogHelper.e("order_id", order_id);
        initView();
        laodOrderData(order_id);
    }

    private void initView(){

        lvGoodEvaluate = (NoScrollListViewNormal) findViewById(R.id.lvGoodEvaluate);
        adapter = new EvaluateGoodsListAdapter(EvaluateActivity.this);
        lvGoodEvaluate.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        onSetMinRating = new OnSetMinRating();
        ll_description = (LinearLayout) findViewById(R.id.ll_description);

        rb_description_score = (RatingBar)  findViewById(R.id.rb_description_score);
        rb_description_score.setOnRatingBarChangeListener(onSetMinRating);
        rb_service_score = (RatingBar)  findViewById(R.id.rb_service_score);
        rb_service_score.setOnRatingBarChangeListener(onSetMinRating);
        rb_goods_fast = (RatingBar)  findViewById(R.id.rb_goods_fast);
        rb_goods_fast.setOnRatingBarChangeListener(onSetMinRating);

        btn_commit = (Button) findViewById(R.id.btn_commit) ;

    }


    //获取订单商品数据
    private void laodOrderData(String order_id){
        String url = Constants.URL_ORDER_EVALUATE;
        url += "&key=" + myApplication.getLoginKey() + "&order_id=" + order_id;
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("json", json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        storeInfo = StoreInfo.newInstanceList(obj.optString("store_info"));
                        if (storeInfo.getIsOwnShop().equals("0")) {
                            ll_description.setVisibility(View.VISIBLE);
                        } else {
                            ll_description.setVisibility(View.GONE);
                        }

                        goodsDetailForEvaluate = GoodsDetailForEvaluate.newInstanceList(obj.optString("order_goods"));
                        LogHelper.e("goodsDetailForEvaluate", goodsDetailForEvaluate.toString());
                        //刷新列表
                        adapter.setGoodsDetailForEvaluateList(goodsDetailForEvaluate);
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                } else {
                    T.showLong(EvaluateActivity.this, "网络异常");
                }

            }
        });


    }

    /**
     * 设置最小星级选择
     */

    class OnSetMinRating implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if (rating == 0) {
                ratingBar.setRating(1);
            }
        }
    }


    /**
     * 上传图片
     * @param file
     */
    public void uploadImage(File file) {
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("key", myApplication.getLoginKey());
        HashMap<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("file", file);
        LogHelper.e("file",file.toString());

        LogHelper.e("wj---file", fileMap.toString());
        LogHelper.e("wj---file", myApplication.getLoginKey());

        RemoteDataHandler.asyncMultipartPostString(Constants.URL_ORDER_EVALUATE_UPLOAD_IAMGE, params, fileMap, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                LogHelper.e("xxjson", json);
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
                    T.showShort(EvaluateActivity.this, "文件上传失败");
                }


            }
        });
    }




    /**
     * 提交评价
     */
    public void btnCommitClick(View view) {
        String url = Constants.URL_ORDER_EVALUATE_COMMIT;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", order_id);


        commentCache = adapter.getCommentCache();
        rateCache = adapter.getRateCache();
        anonyCache = adapter.getAnonyCache();

        for (int i = 0; i < goodsDetailForEvaluate.size(); i++) {
            GoodsDetailForEvaluate goodsEvaluate = goodsDetailForEvaluate.get(i);
            if (TextUtils.isEmpty(commentCache.get(goodsEvaluate.getRec_id()))) {
                T.showShort(EvaluateActivity.this, "请填写商品评论");
                return;
            }
            if (commentCache.get(goodsEvaluate.getRec_id()).length() < 10) {
                Toast.makeText(EvaluateActivity.this, "商品评论不能少于10个字", Toast.LENGTH_SHORT).show();
                return;
            }



            params.put("goods[" + goodsEvaluate.getRec_id() + "][score]", String.valueOf(rateCache.get(goodsEvaluate.getRec_id())));
            params.put("goods[" + goodsEvaluate.getRec_id() + "][comment]", commentCache.get(goodsEvaluate.getRec_id()));
            params.put("goods[" + goodsEvaluate.getRec_id() + "][anony]", anonyCache.get(goodsEvaluate.getRec_id()));


            //遍历图片参数
            Set recSet = itemImageBean.keySet();
            Iterator it = recSet.iterator();
            while (it.hasNext()) {
                String rec_id = (String) it.next();
                List<ImageFile> photoItem = itemImageBean.get(rec_id);

                for (int imageItemPosition = 0; imageItemPosition < photoItem.size(); imageItemPosition++) {
                    ImageFile imageFile = photoItem.get(imageItemPosition);
                    params.put("goods[" + rec_id + "][evaluate_image][" + imageItemPosition + "]", imageFile.getFile_name());
                }
            }

        }

        //为第三方店铺进行评价
        if ((storeInfo.getIsOwnShop().equals("0"))) {
            if (rb_description_score.getRating() == 0 || rb_service_score.getRating() == 0 || rb_goods_fast.getRating() == 0) {
                T.showShort(EvaluateActivity.this, "请给店铺评分");
                return;
            }
            // 宝贝与描述相符（只有三方店铺需要填写，即is_own_shop=0）
            params.put("store_desccredit", String.valueOf(rb_description_score.getRating()));
            // 卖家的服务态度（只有三方店铺需要填写，即is_own_shop=0）
            params.put("store_servicecredit", String.valueOf(rb_service_score.getRating()));
            // 卖家的发货速度（只有三方店铺需要填写，即is_own_shop=0）
            params.put("store_deliverycredit", String.valueOf(rb_goods_fast.getRating()));
        }
        LogHelper.e("params", params.toString());
        LogHelper.e("CommentCache", commentCache.toString());
        LogHelper.e("CommentCache", rateCache.toString());
        LogHelper.e("CommentCache", anonyCache.toString());

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (json.equals("1")) {
                    setResult(COMMENT_SUCCESS);
                    T.showShort(EvaluateActivity.this, "评论成功");
                }
                finish();
            }
        });
    }


    //选择照片后的回调方法
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    LogHelper.e("uri", uri.toString());

                    String path = FileUtils.getPath(this, uri);

                    File file = new File(path);

                    if (file.length() < 1024 * 1024) {
                        uploadImage(file);
                    } else {
                        T.showShort(EvaluateActivity.this, "图片文件过大，请上传1M以下的图片");
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
                        T.showShort(EvaluateActivity.this, "图片文件过大，请上传1M以下的图片");
                    }

                }
        }
    }

}
