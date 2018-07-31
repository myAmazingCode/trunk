package net.shopnc.b2b2c.android.ui.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.ExchangePhotoAdapter;
import net.shopnc.b2b2c.android.bean.GiftListBean;
import net.shopnc.b2b2c.android.bean.GoodsListBean;
import net.shopnc.b2b2c.android.bean.ImageFileBean;
import net.shopnc.b2b2c.android.bean.OrderExchangeBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.FileUtils;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.NCDialog;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ncinterface.INCOnDialogConfirm;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderExchangeActivity extends BaseActivity {
    private String orderId;
    private MyShopApplication myApplication;
    private LayoutInflater inflater;
    private LinearLayout lll;
    private LinearLayout addViewID;
    private TextView textNum;
    private Button buttonCommit;
    private TextView textOrderStoreName;
    private EditText editSeason;
    private GridView gv_photo;
    private PhotoBottomDialog bottomDialog;
    private ExchangePhotoAdapter adapter;
    private List<ImageFileBean> images;
    private NCDialog ncDialog;
    public static final int COMMENT_SUCCESS = 122;
    public static final int SELELCT_FILE_TO_UPLOAD_ITEM = 108;
    private static final int FLAG_CHOOSE_PHONE = 6;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_exchange);
        MyExceptionHandler.getInstance().setContext(this);
        inflater = LayoutInflater.from(OrderExchangeActivity.this);
        orderId=getIntent().getStringExtra("order_id");
        myApplication = (MyShopApplication) getApplicationContext();

        setCommonHeader("订单退款");

        initViews();
        loadDatas();
    }

    public void initViews(){
        lll = (LinearLayout) findViewById(R.id.lll);
        addViewID = lll.findViewById(R.id.addViewID);
        textNum=(TextView)findViewById(R.id.textNum);
        buttonCommit=(Button)findViewById(R.id.buttonCommit);
        textOrderStoreName=(TextView)findViewById(R.id.textOrderStoreName);
        editSeason=(EditText)findViewById(R.id.editSeason);
        gv_photo = (GridView)findViewById(R.id.gv_photo);
        adapter=new ExchangePhotoAdapter(OrderExchangeActivity.this,images);
        gv_photo.setAdapter(adapter);
    }

    public void loadDatas(){
        String url= Constants.URL_ORDER_REFUND+ "&key=" + myApplication.getLoginKey() + "&order_id=" + orderId;

        RemoteDataHandler.asyncDataStringGet(url,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json=data.getJson();
                if(data.getCode()== HttpStatus.SC_OK){
                    try{
                        JSONObject obj=new JSONObject(json);
                        String order=obj.getString("order");
                        OrderExchangeBean orderDetails= com.alibaba.fastjson.JSONObject.parseObject(order, OrderExchangeBean.class);
                        String gift_list=obj.getString("gift_list");
                        ArrayList<GiftListBean> giftLists= new ArrayList<GiftListBean>(com.alibaba.fastjson.JSONObject.parseArray(gift_list, GiftListBean.class));
                        String goods_list=obj.getString("goods_list");
                        ArrayList<GoodsListBean> goodsList=new ArrayList<GoodsListBean>(com.alibaba.fastjson.JSONObject.parseArray(goods_list,GoodsListBean.class));

                        textOrderStoreName.setText(orderDetails.getStore_name());

                        //显示店铺商品列表
                        LinearLayout llGift = null;
                        LinearLayout llGiftList = null;

                        for (int j = 0; j < goodsList.size(); j++) {
                            final GoodsListBean goods = goodsList.get(j);
                            View orderGoodsListView = inflater.inflate(
                                    R.layout.listivew_order_goods_item, null);
                            addViewID.addView(orderGoodsListView);

                            ImageView imageGoodsPic = orderGoodsListView
                                    .findViewById(R.id.imageGoodsPic);
                            TextView textGoodsName = orderGoodsListView
                                    .findViewById(R.id.textGoodsName);
                            TextView textGoodsPrice = orderGoodsListView
                                    .findViewById(R.id.textGoodsPrice);
                            TextView textGoodsNUM = orderGoodsListView
                                    .findViewById(R.id.textGoodsNUM);
                            TextView textGoodsSPec= orderGoodsListView.findViewById(R.id.textGoodsSPec);
                            llGift = orderGoodsListView.findViewById(R.id.llGift);
                            llGiftList = orderGoodsListView.findViewById(R.id.llGiftList);

                            textGoodsName.setText(goods.getGoods_name());
                            textGoodsPrice.setText("￥" + goods.getGoods_price());
                            textGoodsNUM.setText("×" + goods.getGoods_num());
                            Glide.with(OrderExchangeActivity.this).load(goods.getGoods_img_360()).into(imageGoodsPic);
                            if(goods.getGoods_spec()==null){
                                textGoodsSPec.setVisibility(View.GONE);
                            }else{
                                textGoodsSPec.setText(goods.getGoods_spec());
                            }
                            textGoodsName.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(OrderExchangeActivity.this, GoodsDetailsActivity.class);
                                    i.putExtra("goods_id", goods.getGoods_id());
                                    OrderExchangeActivity.this.startActivity(i);
                                }
                            });

                        }
                        //赠品列表的显示
                        if (giftLists.isEmpty()) {
                            llGift.setVisibility(View.GONE);
                        } else {
                            for (int j = 0; j < giftLists.size(); j++) {
                                View giftView = inflater.inflate(R.layout.cart_list_gift_item, null);
                                TextView tvGiftInfo = giftView.findViewById(R.id.tvGiftInfo);
                                tvGiftInfo.setText(giftLists.get(j).getGoods_name() + "x" + giftLists.get(j).getGoods_num());
                                llGiftList.addView(giftView);
                            }
                        }

                        textNum.setText("￥"+orderDetails.getAllow_refund_amount());


                        gv_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                                if(images==null||i==images.size()){
                                    bottomDialog = new PhotoBottomDialog(OrderExchangeActivity.this);
                                    bottomDialog.show();
                                }else{
                                    ncDialog = new NCDialog(OrderExchangeActivity.this);
                                    ncDialog.setText1("确认删除该图片?");
                                    ncDialog.setOnDialogConfirm(new INCOnDialogConfirm() {
                                        @Override
                                        public void onDialogConfirm() {
                                            images.remove(i);
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                    ncDialog.showPopupWindow();
                                }
                            }
                        });

                    }catch (JSONException e){

                    }
                }else{
                    ShopHelper.showApiError(OrderExchangeActivity.this, json);
                }
            }
        });

    }

    /**
     * 上传图片
     * @param file
     */
    public void uploadImage(File file){
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("key", myApplication.getLoginKey());
        HashMap<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("refund_pic", file);

        RemoteDataHandler.asyncMultipartPostString(Constants.URL_ORDER_EXCHANGE_PHOTO,params,fileMap,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                        ImageFileBean image= com.alibaba.fastjson.JSONObject.parseObject(json,ImageFileBean.class);
                    if (images != null) {
                        images.add(image);
                    } else {
                        images = new ArrayList<ImageFileBean>();
                        images.add(image);
                    }
                    adapter.setData(images);
                    adapter.notifyDataSetChanged();
                }else{
                    T.showShort(OrderExchangeActivity.this, "文件上传失败");
                }
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
                        T.showShort(OrderExchangeActivity.this, "图片文件过大，请上传1M以下的图片");
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
                        T.showShort(OrderExchangeActivity.this, "图片文件过大，请上传1M以下的图片");
                    }

                }
        }
    }

    public void btnCommitExchange(View v){
        String url=Constants.URL_ORDER_EXCHANGE_REFUND_ALL;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", orderId);
        params.put("buyer_message",editSeason.getText().toString());


        if(images!=null){
            for (int position=0;position<images.size();position++){
                ImageFileBean i=images.get(position);
                params.put("refund_pic["+position+"]",i.getPic());
            }
        }


        RemoteDataHandler.asyncLoginPostDataString(url,params,myApplication,new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (json.equals("1")) {
                    setResult(COMMENT_SUCCESS);
                    T.showShort(OrderExchangeActivity.this, "退款成功");
                }else{
                    T.showShort(OrderExchangeActivity.this, json);
                }
                finish();
            }
        });

    }

}
