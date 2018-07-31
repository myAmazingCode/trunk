package net.shopnc.b2b2c.android.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.pay.PayDemoActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.OrderGoodsList;
import net.shopnc.b2b2c.android.bean.OrderGroupList;
import net.shopnc.b2b2c.android.bean.OrderList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.ShareButton;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.mine.OrderDeliverDetailsActivity;
import net.shopnc.b2b2c.android.ui.mine.OrderExchangeActivity;
import net.shopnc.b2b2c.android.ui.mine.PayMentWebActivity;
import net.shopnc.b2b2c.android.ui.type.EvaluateActivity;
import net.shopnc.b2b2c.android.ui.mine.OrderDetailsActivity;
import net.shopnc.b2b2c.android.ui.type.EvaluateAddActivity;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 我的订单列表适配器
 *
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class OrderGroupListViewAdapter extends BaseAdapter {

    private Activity context;
    private LayoutInflater inflater;
    private ArrayList<OrderGroupList> orderLists;
    private MyShopApplication myApplication;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    //    private AlertDialog menuDialog;// menu菜单Dialog
//    private GridView menuGrid;
    //    private View menuView;
    PopupWindow pop;
    private OrderGroupList groupList2FU;

    public OrderGroupListViewAdapter(Activity context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        myApplication = (MyShopApplication) context.getApplicationContext();
        // 创建AlertDialog
//        menuView = View.inflate(context, R.layout.gridview_menu, null);
//        menuDialog = new AlertDialog.Builder(context).create();
//        menuDialog.setView(menuView);
//        menuGrid = (GridView) menuView.findViewById(R.id.gridview);
    }

    @Override
    public int getCount() {
        return orderLists == null ? 0 : orderLists.size();
    }

    @Override
    public Object getItem(int position) {
        return orderLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public ArrayList<OrderGroupList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(ArrayList<OrderGroupList> orderLists) {
        this.orderLists = orderLists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listivew_order_item, null);
            holder = new ViewHolder();
            holder.linearLayoutFLag = convertView.findViewById(R.id.linearLayoutFLag);
            holder.buttonFuKuan = convertView.findViewById(R.id.buttonFuKuan);
            holder.addViewID = convertView.findViewById(R.id.addViewID);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final OrderGroupList bean = orderLists.get(position);

        if (!bean.getPay_amount().equals("")
                && !bean.getPay_amount().equals("null")
                && !bean.getPay_amount().equals("0")
                && bean.getPay_amount() != null) {
            holder.linearLayoutFLag.setVisibility(View.VISIBLE);
        } else {
            holder.linearLayoutFLag.setVisibility(View.GONE);
        }

        if (!bean.getPay_amount().equals("0") && !bean.getPay_amount().equals("null") && bean.getPay_amount() != null) {
            String price = new DecimalFormat("#0.00").format(Double.parseDouble((bean.getPay_amount() == null ? "0.00" : bean.getPay_amount()) == "" ? "0.00" : bean.getPay_amount()));
            holder.buttonFuKuan.setText("订单支付(￥ " + price + ")");
        }

        holder.buttonFuKuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(context,PayMentWebAcivity.class);
                // intent.putExtra("pay_sn", bean.getPay_sn());
                // context.startActivity(intent);;
                groupList2FU = orderLists.get(position);
//                menuDialog.show();
                loadingPaymentListData();
            }
        });

        ArrayList<OrderList> orderLists = OrderList.newInstanceList(bean.getOrder_list());

        holder.addViewID.removeAllViews();

        for (int i = 0; i < orderLists.size(); i++) {
            OrderList orderList = orderLists.get(i);
            View orderListView = inflater.inflate(R.layout.listivew_order2_item, null);

            initUIOrderList(orderListView, orderList);

            holder.addViewID.addView(orderListView);
        }

//        menuGrid.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                menuDialog.dismiss();
//                HashMap<String, Object> map = (HashMap<String, Object>) arg0
//                        .getItemAtPosition(arg2);
//                switch (Integer.parseInt(map.get("itemImage").toString())) {
//                    case R.drawable.sns_weixin_icon:// "微信"
//
//                        loadingWXPaymentData(groupList2FU.getPay_sn());
//
//                        break;
//                    case R.drawable.zhifubao_appicon:// "支付宝"
//                        Intent intent = new Intent(context,
//                                PayMentWebActivity.class);
//                        intent.putExtra("pay_sn", groupList2FU.getPay_sn());
//                        context.startActivity(intent);
//                        break;
//
//                    //TODO Modify
//                    case R.drawable.pay:// "支付宝原生支付"
//                        loadingAlipayNativePaymentData(groupList2FU.getPay_sn());
//                        break;
//            }
//            }
//        });

        return convertView;
    }

    /**
     * 生成界面
     */
    public void initUIOrderList(View view, final OrderList orderList) {

        TextView textOrderStoreName = view.findViewById(R.id.textOrderStoreName);
        TextView textOrderAllPrice = view.findViewById(R.id.textOrderAllPrice);
        TextView textOrderShippingFee = view.findViewById(R.id.textOrderShippingFee);
        final Button textOrderOperation = view.findViewById(R.id.textOrderOperation);
        final Button buttonQueRen = view.findViewById(R.id.buttonQueRen);
        TextView textOrderSuccess = view.findViewById(R.id.textOrderSuccess);
        LinearLayout addViewID = view.findViewById(R.id.addViewID);
        TextView textOrderGoodsNum = view.findViewById(R.id.textOrderGoodsNum);
        TextView textOrderDel = view.findViewById(R.id.textOrderDel);
        TextView textTui = view.findViewById(R.id.textTui);

        textOrderStoreName.setText(orderList.getStore_name());
        textOrderAllPrice.setText("￥" + orderList.getOrder_amount());
        textOrderShippingFee.setText("(含运费￥" + orderList.getShipping_fee() + ")");
        ArrayList<OrderGoodsList> goodsDatas = OrderGoodsList.newInstanceList(orderList.getExtend_order_goods());

        textOrderGoodsNum.setText("共" + goodsDatas.size() + "件商品，合计");


        if (orderList.getIf_cancel().equals("true")) {
            textOrderOperation.setVisibility(View.VISIBLE);
            textOrderOperation.setText("取消订单");
        }
        if (orderList.getIf_receive().equals("true")) {
            buttonQueRen.setVisibility(View.VISIBLE);
            buttonQueRen.setText("确认收货");
        }
        if (orderList.getIf_lock().equals("true")) {
            textTui.setVisibility(View.VISIBLE);
        }
        if (orderList.getIf_evaluation().equals("true")) {
            buttonQueRen.setVisibility(View.VISIBLE);
            buttonQueRen.setText("评价");
        }
        if (orderList.getIf_evaluation_again().equals("true")) {
            buttonQueRen.setVisibility(View.VISIBLE);
            buttonQueRen.setText("追加评价");
        }
        if (orderList.getIf_refund_cancel().equals("true")) {
            textOrderOperation.setVisibility(View.VISIBLE);
            textOrderOperation.setText("退款");
        }
        if (orderList.getIf_deliver().equals("true")) {
            textOrderOperation.setVisibility(View.VISIBLE);
            textOrderOperation.setText("查看物流");
        }


//        if (orderList.getIf_deliver().equals("true")) {
//            textOrderOperation2.setText(Html.fromHtml("<a href='#'>查看物流</a>"));
//            textOrderOperation2.setVisibility(View.VISIBLE);
//        } else {
//            textOrderOperation2.setVisibility(View.GONE);
//        }

        if (orderList.getState_desc() != null
                && !orderList.getState_desc().equals("")) {
            textOrderSuccess.setVisibility(View.VISIBLE);
            textOrderSuccess.setText(orderList.getState_desc());
            if (orderList.getState_desc().equals("已取消")) {
                textOrderDel.setVisibility(View.VISIBLE);
                textOrderDel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadingSaveOrderData(Constants.URL_ORDER_DEL, orderList.getOrder_id());
                    }
                });
            }
        } else {
            textOrderSuccess.setVisibility(View.GONE);
        }

        buttonQueRen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = buttonQueRen.getText().toString();
                if (s.equals("确认收货")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("操作提示")
                            .setMessage("是否确认操作")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .setPositiveButton("确认",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            loadingSaveOrderData(Constants.URL_ORDER_RECEIVE, orderList.getOrder_id());
                                        }
                                    }).create().show();
                } else if (s.equals("评价")) {
                    Intent i = new Intent(context, EvaluateActivity.class);
                    i.putExtra("order_id", orderList.getOrder_id());
                    context.startActivity(i);
                } else if (s.equals("追加评价")) {
                    Intent i = new Intent(context, EvaluateAddActivity.class);
                    i.putExtra("order_id", orderList.getOrder_id());
                    context.startActivity(i);
                }
            }
        });

        textOrderOperation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final String key = textOrderOperation.getText().toString();
                if (key.equals("查看物流")) {
                    Intent intent = new Intent(context, OrderDeliverDetailsActivity.class);
                    intent.putExtra("order_id", orderList.getOrder_id());
                    context.startActivity(intent);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("操作提示")
                        .setMessage("是否确认操作")
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                    }
                                })
                        .setPositiveButton("确认",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog,
                                            int whichButton) {
                                        if (key.equals("取消订单")) {
                                            loadingSaveOrderData(Constants.URL_ORDER_CANCEL, orderList.getOrder_id());
                                        }
                                        if (key.equals("退款")) {
                                            Intent intent = new Intent(context, OrderExchangeActivity.class);
                                            intent.putExtra("order_id", orderList.getOrder_id());
                                            context.startActivity(intent);
                                        }
                                    }
                                }).create().show();

            }
        });
//        textOrderOperation2.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, OrderDeliverDetailsActivity.class);
//                intent.putExtra("order_id", orderList.getOrder_id());
//                context.startActivity(intent);
//            }
//        });

        LinearLayout llGift = null;
        LinearLayout llGiftList = null;
        TextView imgZeng = null;
        for (int j = 0; j < goodsDatas.size(); j++) {
            final OrderGoodsList ordergoodsList = goodsDatas.get(j);
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
            imgZeng = orderGoodsListView.findViewById(R.id.imgZeng);
            TextView textGoodsSPec = orderGoodsListView.findViewById(R.id.textGoodsSPec);
            llGift = orderGoodsListView.findViewById(R.id.llGift);
            llGiftList = orderGoodsListView.findViewById(R.id.llGiftList);

            textGoodsName.setText(ordergoodsList.getGoods_name());
            textGoodsPrice.setText("￥" + ordergoodsList.getGoods_price());
            textGoodsNUM.setText("×" + ordergoodsList.getGoods_num());
            textGoodsName.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, OrderDetailsActivity.class);
                    i.putExtra("order_id", orderList.getOrder_id());
                    context.startActivity(i);
                }
            });
            if (ordergoodsList.getGoods_spec().equals("null") || ordergoodsList.getGoods_spec().equals("")) {
                textGoodsSPec.setVisibility(View.GONE);
            } else {
                textGoodsSPec.setText(ordergoodsList.getGoods_spec());
            }


            imageLoader.displayImage(ordergoodsList.getGoods_image_url(),
                    imageGoodsPic, options, animateFirstListener);

        }


        //赠品
        String giftListString = orderList.getZengpin_list();
        if (giftListString.equals("") || giftListString.equals("[]")) {
            llGift.setVisibility(View.GONE);
        } else {
            try {
                imgZeng.setVisibility(View.VISIBLE);
                JSONArray giftArray = new JSONArray(giftListString);
                for (int j = 0; j < giftArray.length(); j++) {
                    View giftView = inflater.inflate(R.layout.cart_list_gift_item, null);
                    TextView tvGiftInfo = giftView.findViewById(R.id.tvGiftInfo);
                    JSONObject giftObj = (JSONObject) giftArray.get(j);
                    tvGiftInfo.setText(giftObj.optString("goods_name") + "x" + giftObj.optString("goods_num"));
                    llGiftList.addView(giftView);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // goodsListView.setOnItemClickListener(new OnItemClickListener() {
        // @Override
        // public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
        // long arg3) {
        // OrderGoodsList bean =(OrderGoodsList)
        // goodsListView.getItemAtPosition(arg2);
        // if(bean != null){
        // Intent intent =new Intent(context,GoodsDetailsActivity.class);
        // intent.putExtra("goods_id", bean.getGoods_id());
        // context.startActivity(intent);
        // }
        // }
        // });
    }

    /**
     * 获取可用支付方式
     */
    public void loadingPaymentListData() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        RemoteDataHandler.asyncLoginPostDataString(
                Constants.URL_ORDER_PAYMENT_LIST, params, myApplication,
                new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String JosnObj = jsonObject
                                        .getString("payment_list");
                                JSONArray arr = new JSONArray(JosnObj);
                                Log.d("huting====pay", arr.toString());

                                int size = null == arr ? 0 : arr.length();
                                if (size < 1) {
                                    T.showShort(context, "没有支付方式，请后台配置");
                                    return;
                                }
                                View view = initPayWindowView(context, size, arr);
                                if (pop == null) {
                                    pop = initPopupWindow(view);
                                }
                                if (!pop.isShowing()) {
                                    pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                                    backgroundAlpha(0.5f);
                                }
//                                ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<HashMap<String, Object>>();
//                                for (int i = 0; i < size; i++) {
//                                    String Values = arr.getString(i);
//                                    HashMap<String, Object> map = new HashMap<String, Object>();
//                                    if (Values.equals("wxpay")) {
//                                        map.put("itemImage",
//                                                R.drawable.sns_weixin_icon);
//                                        map.put("itemText", "微信支付");
//                                    } else if (Values.equals("alipay")) {
//                                        map.put("itemImage",
//                                                R.drawable.zhifubao_appicon);
//                                        map.put("itemText", "支付宝");
//                                    } else if (Values.equals("alipay_native")) {//TODO Modify 支付宝原生支付
//                                        map.put("itemImage",
//                                                R.drawable.pay);
//                                        map.put("itemText", "原生支付");
//                                    }
//                                    if(!map.isEmpty()){
//                                        hashMaps.add(map);
//                                    }
//
//                                }
//                                SimpleAdapter simperAdapter = new SimpleAdapter(
//                                        context,
//                                        hashMaps,
//                                        R.layout.item_menu,
//                                        new String[]{"itemImage", "itemText"},
//                                        new int[]{R.id.item_image,
//                                                R.id.item_text});
//                                menuGrid.setAdapter(simperAdapter);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }

                        } else {
                            try {
                                JSONObject obj2 = new JSONObject(json);
                                String error = obj2.getString("error");
                                if (error != null) {
                                    Toast.makeText(context, error,
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 获取微信参数
     *
     * @param pay_sn 支付编号
     */
    public void loadingWXPaymentData(String pay_sn) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("pay_sn", pay_sn);
        Log.d("dqw", Constants.URL_MEMBER_WX_PAYMENT);
        Log.d("dqw", myApplication.getLoginKey());
        Log.d("dqw", pay_sn);

        RemoteDataHandler.asyncLoginPostDataString(
                Constants.URL_MEMBER_WX_PAYMENT, params, myApplication,
                new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String appid = jsonObject.getString("appid");// 微信开放平台appid
                                String noncestr = jsonObject
                                        .getString("noncestr");// 随机字符串
                                String packageStr = jsonObject
                                        .getString("package");// 支付内容
                                String partnerid = jsonObject
                                        .getString("partnerid");// 财付通id
                                String prepayid = jsonObject
                                        .getString("prepayid");// 微信预支付编号
                                String sign = jsonObject.getString("sign");// 签名
                                String timestamp = jsonObject
                                        .getString("timestamp");// 时间戳

                                IWXAPI api = WXAPIFactory.createWXAPI(context, appid);

                                PayReq req = new PayReq();
                                req.appId = appid;
                                req.partnerId = partnerid;
                                req.prepayId = prepayid;
                                req.nonceStr = noncestr;
                                req.timeStamp = timestamp;
                                req.packageValue = packageStr;
                                req.sign = sign;
                                req.extData = "app data"; // optional

                                Log.d("huting----------", req.toString());

                                Toast.makeText(context, "正常调起支付",
                                        Toast.LENGTH_SHORT).show();
                                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                api.sendReq(req);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                JSONObject obj2 = new JSONObject(json);
                                String error = obj2.getString("error");
                                if (error != null) {
                                    Toast.makeText(context, error,
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    /**
     * 获取支付宝原生支付的参数
     */
    public void loadingAlipayNativePaymentData(String pay_sn) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("pay_sn", pay_sn);

        Log.d("huting", Constants.URL_ALIPAY_NATIVE_GOODS);
        Log.d("huting", myApplication.getLoginKey());
        Log.d("huting", pay_sn);

        RemoteDataHandler.asyncLoginPostDataString(
                Constants.URL_ALIPAY_NATIVE_GOODS, params, myApplication,
                new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String signStr = jsonObject.optString("signStr");

                                Log.d("huting-----nativePay", signStr);
                                PayDemoActivity payDemoActivity = new PayDemoActivity(context, signStr);
                                payDemoActivity.doPay();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            try {
                                JSONObject obj2 = new JSONObject(json);
                                String error = obj2.getString("error");
                                if (error != null) {
                                    Toast.makeText(context, error,
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 确认收货、取消订单
     *
     * @param url
     * @param //orderID 订单ID
     */
    public void loadingSaveOrderData(String url, String order_id) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("order_id", order_id);

        RemoteDataHandler.asyncLoginPostDataString(url, params, myApplication,
                new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        if (data.getCode() == HttpStatus.SC_OK) {
                            if (json.equals("1")) {
                                // Toast.makeText(context, "",
                                // Toast.LENGTH_SHORT).show();;
                                // 刷新界面
                                Intent mIntent = new Intent(
                                        Constants.PAYMENT_SUCCESS);
                                context.sendBroadcast(mIntent);
                            }

                        } else {
                            try {
                                JSONObject obj2 = new JSONObject(json);
                                String error = obj2.getString("error");
                                if (error != null) {
                                    Toast.makeText(context, error,
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    class ViewHolder {
        LinearLayout linearLayoutFLag;
        Button buttonFuKuan;
        LinearLayout addViewID;
    }

    /**
     * 初始化popupwindow
     */
    public PopupWindow initPopupWindow(View mPopupWindowView) {
        PopupWindow popupWindow;
        popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        popupWindow.update();
        popupWindow.setAnimationStyle(R.anim.popup_window_enter);
        popupWindow.setAnimationStyle(R.anim.popup_window_exit);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        return popupWindow;
    }

    /**
     * 初始化 支付window
     */
    public View initPayWindowView(Context context, int size, JSONArray arr) {
        View mPopupWindowView = LayoutInflater.from(context).inflate(R.layout.es_activity_social_share, null);
        TextView pay_miss = mPopupWindowView.findViewById(R.id.pay_miss);
        pay_miss.setOnClickListener(new SetOnclickListener(R.id.pay_miss));
        ShareButton social_share_sb_wechat = mPopupWindowView.findViewById(R.id.social_sb_wechat);
        social_share_sb_wechat.setOnClickListener(new SetOnclickListener(R.id.social_sb_wechat));
        ShareButton social_share_sb_zhifubao = mPopupWindowView.findViewById(R.id.social_sb_zhifubao);
        social_share_sb_zhifubao.setOnClickListener(new SetOnclickListener(R.id.social_sb_zhifubao));
        ShareButton social_sb_wzhifubao = mPopupWindowView.findViewById(R.id.social_sb_wzhifubao);
        social_sb_wzhifubao.setOnClickListener(new SetOnclickListener(R.id.social_sb_wzhifubao));
        try {
            for (int i = 0; i < size; i++) {
                String Values = arr.getString(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
                if (Values.equals("wxpay")) {
                    social_share_sb_wechat.setVisibility(View.VISIBLE);
                } else if (Values.equals("alipay")) {
                    social_sb_wzhifubao.setVisibility(View.VISIBLE);
                } else if (Values.equals("alipay_native")) {//TODO Modify 支付宝原生支付
                    social_share_sb_zhifubao.setVisibility(View.VISIBLE);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mPopupWindowView;
    }

    public class SetOnclickListener implements View.OnClickListener {

        public int id;

        public SetOnclickListener(int clickId) {
            this.id = clickId;
        }

        @Override
        public void onClick(View view) {
            pop.dismiss();
            Intent intent;
            switch (id) {
                case R.id.social_sb_wechat:// "微信"
//                    loadingWXPaymentData(Qbean.getOrder_sn());
                    loadingWXPaymentData(groupList2FU.getPay_sn());
                    break;
                case R.id.social_sb_zhifubao:// "获取支付宝原生支付的参数"
//                    loadingAlipayNativePaymentData(Qbean.getOrder_sn());
                    loadingAlipayNativePaymentData(groupList2FU.getPay_sn());
                    break;

                case R.id.social_sb_wzhifubao:// "web支付宝
//                    intent = new Intent(context, PayMentWebActivity.class);
//                    intent.putExtra("order_sn", Qbean.getOrder_sn());
//                    context.startActivity(intent);
                    intent = new Intent(context,
                            PayMentWebActivity.class);
                    intent.putExtra("pay_sn", groupList2FU.getPay_sn());
                    context.startActivity(intent);
                    break;
            }
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }
}
