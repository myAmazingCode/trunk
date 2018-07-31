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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.VirtualList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.custom.ShareButton;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.RemoteDataHandler.Callback;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.mine.PayMentWebActivity;
import net.shopnc.b2b2c.android.ui.mine.VirtualInfoActivity;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 虚拟订单列表适配器
 *
 * @author dqw
 * @Time 2015/8/14
 */
public class VirtualOrderListViewAdapter extends BaseAdapter {
    private Activity context;
    private LayoutInflater inflater;
    private MyShopApplication myApplication;

//    private AlertDialog menuDialog;// menu菜单Dialog
//    private View menuView;
    private ArrayList<VirtualList> virtualLists;
//    private GridView menuGrid;
    private VirtualList Qbean;
    PopupWindow pop;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public VirtualOrderListViewAdapter(Activity context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        myApplication = (MyShopApplication) context.getApplicationContext();

//        menuView = View.inflate(context, R.layout.gridview_menu, null);
        // 创建AlertDialog
//        menuDialog = new AlertDialog.Builder(context).create();
//        menuDialog.setView(menuView);
//        menuGrid = (GridView) menuView.findViewById(R.id.gridview);
    }

    @Override
    public int getCount() {
        return virtualLists == null ? 0 : virtualLists.size();
    }

    @Override
    public Object getItem(int position) {
        return virtualLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setVirtualLists(ArrayList<VirtualList> virtualLists) {
        this.virtualLists = virtualLists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final VirtualList bean = virtualLists.get(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.listivew_virtual_order_item, null);
            holder = new ViewHolder();
            holder.textOrderStoreName = convertView.findViewById(R.id.textOrderStoreName);
            holder.rlOrderItem = convertView.findViewById(R.id.rlOrderItem);
            holder.textGoodsPrice = convertView.findViewById(R.id.textGoodsPrice);
            holder.textGoodsNUM = convertView.findViewById(R.id.textGoodsNUM);
            holder.textOrderAllPrice = convertView.findViewById(R.id.textOrderAllPrice);
            holder.buttonFuKuan = convertView.findViewById(R.id.buttonFuKuan);
            holder.textOrderSuccess = convertView.findViewById(R.id.textOrderSuccess);
            holder.rlCancel = convertView.findViewById(R.id.rlCancel);
            holder.btnCancel = convertView.findViewById(R.id.btnCancel);
            holder.imageGoodsPic = convertView.findViewById(R.id.imageGoodsPic);
            holder.textGoodsName = convertView.findViewById(R.id.textGoodsName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textOrderStoreName.setText((bean.getStore_name() == null ? "" : bean.getStore_name()));
        holder.rlOrderItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VirtualInfoActivity.class);
                intent.putExtra("order_id", bean.getOrder_id());
                context.startActivity(intent);
            }
        });

        imageLoader.displayImage(bean.getGoods_image_url(), holder.imageGoodsPic, options, animateFirstListener);
        holder.textGoodsName.setText(bean.getGoods_name());
        holder.textOrderSuccess.setText(bean.getOrder_state_text());
        holder.textGoodsPrice.setText("￥" + (bean.getGoods_price() == null ? "0.00" : bean.getGoods_price()));
        holder.textGoodsNUM.setText("x" + (bean.getGoods_num() == null ? "0" : bean.getGoods_num()));
        holder.textOrderAllPrice.setText("￥" + (bean.getOrder_amount() == null ? "0.00" : bean.getOrder_amount()));

        //设置取消按钮是否可见
        if (bean.getIf_cancel().equals("true")) {
            holder.rlCancel.setVisibility(View.VISIBLE);
        } else {
            holder.rlCancel.setVisibility(View.GONE);
        }

        //设置支付按钮是否可见
        if (bean.getIf_pay().equals("true")) {
            holder.buttonFuKuan.setVisibility(View.VISIBLE);
        } else {
            holder.buttonFuKuan.setVisibility(View.GONE);
        }

        holder.btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("操作提示")
                        .setMessage("是否确认操作")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                loadingSaveOrderData(Constants.URL_MEMBER_VR_ORDER_CANCEL, bean.getOrder_id());
                            }
                        }).create().show();
            }
        });

        holder.buttonFuKuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                menuDialog.show();

                Qbean = bean;
                loadingPaymentListData();


            }
        });

//        menuGrid.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                menuDialog.dismiss();
//                HashMap<String, Object> map = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
//                switch (Integer.parseInt(map.get("itemImage").toString())) {
//                    case R.drawable.sns_weixin_icon:// "微信"
//                        loadingWXPaymentData(Qbean.getOrder_sn());
//                        break;
//                    case R.drawable.zhifubao_appicon:// "支付宝"
//                        Intent intent = new Intent(context, PayMentWebActivity.class);
//                        intent.putExtra("order_sn", Qbean.getOrder_sn());
//                        context.startActivity(intent);
//                        break;
//
//                    //TODO Modify
//                    case R.drawable.sns_ydnote_icon:// "支付宝原生支付"
//                        loadingAlipayNativePaymentData(Qbean.getOrder_sn());
//                        break;
//                }
//            }
//        });
        return convertView;
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

        RemoteDataHandler.asyncLoginPostDataString(
                Constants.URL_MEMBER_WX_VPAYMENT, params, myApplication,
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
                                Toast.makeText(context, "正常调起支付",
                                        Toast.LENGTH_SHORT).show();
                                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                api.sendReq(req);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ShopHelper.showApiError(context, json);
                        }
                    }
                });
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
                                int size = null == arr ? 0 : arr.length();
                                if(size<1){
                                    T.showShort(context,"没有支付方式，请后台配置");
                                    return;
                                }
                                View view = initPayWindowView(context,size,arr);
                                if(pop == null){
                                    pop = initPopupWindow(view);
                                }
                                if(!pop.isShowing()){
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
//                                                R.drawable.sns_ydnote_icon);
//                                        map.put("itemText", "原生支付");
//                                    }
//                                    hashMaps.add(map);
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
                            ShopHelper.showApiError(context, json);
                        }
                    }
                });
    }

    /**
     *
     *  获取支付宝原生支付的参数
     */
    public void loadingAlipayNativePaymentData(String pay_sn){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", myApplication.getLoginKey());
        params.put("pay_sn", pay_sn);

        Log.d("huting-------url:", Constants.URL_ALIPAY_NATIVE_Virtual);
        Log.d("huting-------key:", myApplication.getLoginKey());
        Log.d("huting-------pay_sn:", pay_sn);

        RemoteDataHandler.asyncLoginPostDataString(
                Constants.URL_ALIPAY_NATIVE_Virtual, params, myApplication,
                new Callback() {
                    @Override
                    public void dataLoaded(ResponseData data) {
                        String json = data.getJson();
                        Log.d("huting-----json", json);
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
     * 取消订单
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
                            // Toast.makeText(context, "",
                            // Toast.LENGTH_SHORT).show();;
                            // 刷新界面
                            Intent mIntent = new Intent(
                                    Constants.VPAYMENT_SUCCESS);
                            context.sendBroadcast(mIntent);
                        } else {
                            ShopHelper.showApiError(context, json);
                        }
                    }
                });
    }

    class ViewHolder {
        TextView textOrderStoreName;
        RelativeLayout rlOrderItem;
        TextView textGoodsPrice;
        TextView textGoodsNUM;
        TextView textOrderAllPrice;
        TextView textOrderSuccess;
        RelativeLayout rlCancel;
        Button btnCancel;
        Button buttonFuKuan;
        ImageView imageGoodsPic;
        TextView textGoodsName;
    }
    /**
     * 初始化popupwindow
     */
    public PopupWindow initPopupWindow(View mPopupWindowView){
        PopupWindow popupWindow ;

        popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT ,true);
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
    public View initPayWindowView(Context context ,int size ,JSONArray arr){
        View mPopupWindowView = LayoutInflater.from(context).inflate(R.layout.es_activity_social_share, null);
        TextView pay_miss = mPopupWindowView.findViewById(R.id.pay_miss);
        pay_miss.setOnClickListener(new SetOnclickListener(R.id.pay_miss));
        ShareButton social_share_sb_wechat = mPopupWindowView.findViewById(R.id.social_sb_wechat);
        social_share_sb_wechat.setOnClickListener(new SetOnclickListener(R.id.social_sb_wechat));
        ShareButton social_share_sb_zhifubao = mPopupWindowView.findViewById(R.id.social_sb_zhifubao);
        social_share_sb_zhifubao.setOnClickListener(new SetOnclickListener(R.id.social_sb_zhifubao));
        ShareButton social_sb_wzhifubao = mPopupWindowView.findViewById(R.id.social_sb_wzhifubao);
        social_sb_wzhifubao.setOnClickListener(new SetOnclickListener(R.id.social_sb_wzhifubao));
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return mPopupWindowView;
    }
        public class SetOnclickListener implements View.OnClickListener{

            public int id;
            public SetOnclickListener(int clickId){
                this.id = clickId;
            }
            @Override
            public void onClick(View view) {
                pop.dismiss();
                Intent intent;
                switch (id){
                    case R.id.social_sb_wechat:// "微信"
                        loadingWXPaymentData(Qbean.getOrder_sn());
                        break;
                    case R.id.social_sb_zhifubao:// "支付宝"
                        loadingAlipayNativePaymentData(Qbean.getOrder_sn());
                        break;

                    case R.id.social_sb_wzhifubao:// "支付宝原生支付"
                        intent = new Intent(context, PayMentWebActivity.class);
                        intent.putExtra("order_sn", Qbean.getOrder_sn());
                        context.startActivity(intent);

                        break;
                }
            }
        }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }
}
