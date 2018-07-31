package net.shopnc.b2b2c.android.ui.mine;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseFragment;
import net.shopnc.b2b2c.android.bean.Mine;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.fenxiao.FenxiaoAllActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.FenxiaoGoodsActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.FenxiaoOrderActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.FenxiaoSettlementActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.FenxiaoTixianActivity;
import net.shopnc.b2b2c.android.ui.type.AddressListActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsBrowseActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 我的
 *
 * @author dqw
 * @Time 2015-7-6
 */
public class MineFragment extends BaseFragment {

    private LinearLayout llLogin;
    private LinearLayout llMemberInfo;
    private ImageView ivMemberAvatar;
    private TextView tvMemberName;
    private ImageView ivFavGoods;
    private TextView tvFavGoodsCount;
    private ImageView ivFavStore;
    private TextView tvFavStoreCount;
    private TextView tv_Member_v;
    private TextView daifukuan, daishouhuo, daiziti, daipingjia, tuikuang;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getRoundedBitmapDisplayImageOptions(100);
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    /*分销*/
    private LinearLayout fenxiao_llOrderNoeval, fenxiao_llOrderNotakes, fenxiao_llOrderSend, fenxiao_llOrderNew, ll_fenxiao;
    private Button fenxiao_btnOrderAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.main_mine_view, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());

        initSettingButton(viewLayout);
        initMemberButton(viewLayout);
        initOrderButton(viewLayout);
        initAssetButton(viewLayout);
        initFenxiaoLinearlayout(viewLayout);

        return viewLayout;
    }

    private void initFenxiaoLinearlayout(View viewLayout) {
        fenxiao_llOrderNoeval = viewLayout.findViewById(R.id.fenxiao_llOrderNoeval);
        fenxiao_llOrderNotakes = viewLayout.findViewById(R.id.fenxiao_llOrderNotakes);
        fenxiao_llOrderSend = viewLayout.findViewById(R.id.fenxiao_llOrderSend);
        fenxiao_llOrderNew = viewLayout.findViewById(R.id.fenxiao_llOrderNew);
        LinearLayout fenxiao_llOrdertixian = viewLayout.findViewById(R.id.fenxiao_llOrdertixian);
        fenxiao_btnOrderAll = viewLayout.findViewById(R.id.fenxiao_btnOrderAll);
        ll_fenxiao = viewLayout.findViewById(R.id.ll_fenxiao);
//        /*直播*/
//        fenxiao_llOrderNoeval.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                TODO
//                if (StringUtils.isEmpty(movie_msg)) {
//                    ApplyVerifyMovie();
//                } else {
//                    T.showShort(getActivity(), movie_msg);
//                }
//
//
////                   startActivity(new Intent(getActivity(), ApplyLiveActivity.class));
////                   startActivity(new Intent(getActivity(), PushActivity.class));
//            }
//        });
        /*全部*/
        fenxiao_btnOrderAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FenxiaoAllActivity.class));

            }
        });
        /*分销结算*/
        fenxiao_llOrderNotakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FenxiaoSettlementActivity.class));
            }
        });
        /*分销订单*/
        fenxiao_llOrderSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FenxiaoOrderActivity.class));
            }
        });
        /*分销商品 */
        fenxiao_llOrderNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FenxiaoGoodsActivity.class));
            }
        });
        /*分销提现列表*/
        fenxiao_llOrdertixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FenxiaoTixianActivity.class));
            }
        });
    }

    /**
     * 初始化设置相关按钮
     *
     * @param viewLayout
     */
    private void initSettingButton(View viewLayout) {
        //设置
        Button btnSetting = viewLayout.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                }
            }
        });

        //设置2
        RelativeLayout rlSetting = viewLayout.findViewById(R.id.rlSetting);
        rlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                }
            }
        });

        //IM
        Button btnIm = viewLayout.findViewById(R.id.btnIm);
        btnIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
//                    startActivity(new Intent(getActivity(), IMFriendsListActivity.class));
                    startActivity(new Intent(getActivity(), IMNewListActivity.class));
                }
            }
        });

        //登录
        llLogin = viewLayout.findViewById(R.id.llLogin);
        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        llMemberInfo = viewLayout.findViewById(R.id.llMemberInfo);
        ivMemberAvatar = viewLayout.findViewById(R.id.ivMemberAvatar);
        tvMemberName = viewLayout.findViewById(R.id.tvMemberName);

        daifukuan = viewLayout.findViewById(R.id.daifukuang_num);
        daishouhuo = viewLayout.findViewById(R.id.daishouhuo_num);
        daiziti = viewLayout.findViewById(R.id.daiziti_num);
        daipingjia = viewLayout.findViewById(R.id.daipingjia_num);
        tuikuang = viewLayout.findViewById(R.id.tuikuan_num);
    }

    /**
     * 初始化用户信息相关按钮
     *
     * @param viewLayout
     */
    private void initMemberButton(View viewLayout) {
        //商品收藏
        LinearLayout llFavGoods = viewLayout.findViewById(R.id.llFavGoods);
        llFavGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), FavGoodsListActivity.class));
                }
            }
        });
        ivFavGoods = viewLayout.findViewById(R.id.ivFavGoods);
        tvFavGoodsCount = viewLayout.findViewById(R.id.tvFavGoodsCount);

        //收藏店铺
        LinearLayout llFavStore = viewLayout.findViewById(R.id.llFavStore);
        llFavStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), FavStoreListActivity.class));
                }
            }
        });
        ivFavStore = viewLayout.findViewById(R.id.ivFavStore);
        tvFavStoreCount = viewLayout.findViewById(R.id.tvFavStoreCount);
        tv_Member_v = viewLayout.findViewById(R.id.tv_Member_v);
        //我的足迹
        LinearLayout llZuji = viewLayout.findViewById(R.id.llZuji);
        llZuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), GoodsBrowseActivity.class));
                }
            }
        });

        //收货地址
        RelativeLayout rlAddress = viewLayout.findViewById(R.id.rlAddress);
        rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    Intent intent = new Intent(getActivity(), AddressListActivity.class);
                    intent.putExtra("addressFlag", "0");
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 初始化订单相关按钮
     *
     * @param viewLayout
     */
    private void initOrderButton(View viewLayout) {
        //全部订单
        Button btnOrderAll = viewLayout.findViewById(R.id.btnOrderAll);
        btnOrderAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOrderList("");
            }
        });
        //待付款订单
        LinearLayout llOrderNew = viewLayout.findViewById(R.id.llOrderNew);
        setOrderButtonEvent(llOrderNew, "state_new");
        //待收货
        LinearLayout llOrderSend = viewLayout.findViewById(R.id.llOrderSend);
        setOrderButtonEvent(llOrderSend, "state_send");
        //待自提订单
        LinearLayout llOrderNotakes = viewLayout.findViewById(R.id.llOrderNotakes);
        setOrderButtonEvent(llOrderNotakes, "state_notakes");
        //待评价订单
        LinearLayout llOrderNoeval = viewLayout.findViewById(R.id.llOrderNoeval);
        setOrderButtonEvent(llOrderNoeval, "state_noeval");
        //退款退货
        LinearLayout llRefund = viewLayout.findViewById(R.id.llRefund);
        llRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), OrderExchangeListActivity.class));
            }
        });
    }

    /**
     * 初始化财产相关按钮
     *
     * @param viewLayout
     */
    private void initAssetButton(View viewLayout) {
        //全部财产
        Button btnMyAsset = viewLayout.findViewById(R.id.btnMyAsset);
        btnMyAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), MyAssetActivity.class));
                }
            }
        });

        //预存款
        LinearLayout llPredeposit = viewLayout.findViewById(R.id.llPredeposit);
        llPredeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), PredepositActivity.class));
                }
            }
        });

        //充值卡
        LinearLayout llRechargeCard = viewLayout.findViewById(R.id.llRechargeCard);
        llRechargeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), RechargeCardLogActivity.class));
                }
            }
        });

        //代金券
        LinearLayout llVoucherList = viewLayout.findViewById(R.id.llVoucherList);
        llVoucherList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), VoucherListActivity.class));
                }
            }
        });

        //红包
        LinearLayout llRedpacket = viewLayout.findViewById(R.id.llRedpacketList);
        llRedpacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), RedpacketListActivity.class));
                }
            }
        });

        //积分
        LinearLayout llPointLog = viewLayout.findViewById(R.id.llPointLog);
        llPointLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
                    startActivity(new Intent(getActivity(), PointLogActivity.class));
                }
            }
        });
    }

    /**
     * 设置订单按钮事件
     */
    private void setOrderButtonEvent(LinearLayout btn, final String stateType) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOrderList(stateType);
            }
        });
    }

    /**
     * 显示订单列表
     */
    private void showOrderList(String stateType) {
        if (ShopHelper.isLogin(getActivity(), application.getLoginKey())) {
            Intent intent = new Intent(getActivity(), OrderListActivity.class);
            intent.putExtra("state_type", stateType);
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setLoginInfo();
    }

    @Override
    public void onStart() {
        super.onStart();
        registerBoradcastReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.LOGIN_SUCCESS_URL)) {
                loadMemberInfo();
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constants.LOGIN_SUCCESS_URL);
        getActivity().registerReceiver(mBroadcastReceiver, myIntentFilter);  //注册广播
    }

    /**
     * 检测是否登录
     */
    public void setLoginInfo() {
        String loginKey = application.getLoginKey();
        if (loginKey != null && !loginKey.equals("")) {
            llMemberInfo.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);
            ivFavGoods.setVisibility(View.GONE);
            ivFavStore.setVisibility(View.GONE);
            tvFavGoodsCount.setVisibility(View.VISIBLE);
            tvFavStoreCount.setVisibility(View.VISIBLE);
            loadMemberInfo();
        } else {
            llMemberInfo.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);
            ivFavGoods.setVisibility(View.VISIBLE);
            ivFavStore.setVisibility(View.VISIBLE);
            tvFavGoodsCount.setVisibility(View.GONE);
            tvFavStoreCount.setVisibility(View.GONE);
        }
    }

    String movie_msg = "";

    /**
     * 初始化加载我的信息
     */
    public void loadMemberInfo() {
        String url = Constants.URL_MYSTOIRE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", application.getLoginKey());

        RemoteDataHandler.asyncLoginPostDataString(url, params, application, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {

                String json = data.getJson();
//                Logger.d(json);
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject obj = new JSONObject(json);
                        String objJson = obj.getString("member_info");
                        Mine bean = Mine.newInstanceList(objJson);

                        if (bean != null) {
                            tvMemberName.setText(bean.getMemberName() == null ? "" : bean.getMemberName());
                            imageLoader.displayImage(bean.getMemberAvatar(), ivMemberAvatar, options, animateFirstListener);
                            tvFavGoodsCount.setText(bean.getFavGoods() == null ? "0" : bean.getFavGoods());
                            tvFavStoreCount.setText(bean.getFavStore() == null ? "0" : bean.getFavStore());
                            tv_Member_v.setText(bean.getLevelName() == null ? "0" : bean.getLevelName());
                            if (bean.getOrderReturn().equals("0")) {
                                tuikuang.setVisibility(View.INVISIBLE);
                            } else {
                                tuikuang.setText(bean.getOrderReturn());
                                tuikuang.setVisibility(View.VISIBLE);
                            }
                            if (bean.getOrderNoeval().equals("0")) {
                                daipingjia.setVisibility(View.INVISIBLE);
                            } else {
                                daipingjia.setText(bean.getOrderNoeval());
                                daipingjia.setVisibility(View.VISIBLE);
                            }
                            if (bean.getOrderNotakes().equals("0")) {
                                daiziti.setVisibility(View.INVISIBLE);
                            } else {
                                daiziti.setText(bean.getOrderNotakes());
                                daiziti.setVisibility(View.VISIBLE);
                            }
                            if (bean.getOrderNoreceipt().equals("0")) {
                                daishouhuo.setVisibility(View.INVISIBLE);
                            } else {
                                daishouhuo.setText(bean.getOrderNoreceipt());
                                daishouhuo.setVisibility(View.VISIBLE);
                            }
                            if (bean.getOrderNopay().equals("0")) {
                                daifukuan.setVisibility(View.INVISIBLE);
                            } else {
                                daifukuan.setText(bean.getOrderNopay());
                                daifukuan.setVisibility(View.VISIBLE);
                            }
                            //d/d
                            if (bean.getIs_distr() != null) {
                                if ("1".equals(bean.getIs_distr())) {
                                    ll_fenxiao.setVisibility(View.VISIBLE);
                                } else {
                                    ll_fenxiao.setVisibility(View.GONE);
                                }

                            } else {
                                ll_fenxiao.setVisibility(View.GONE);
                            }
                            if (bean.getIs_movie() != null) {
//                                if ("1".equals(bean.getIs_movie())) {
//                                    fenxiao_llOrderNoeval.setVisibility(View.VISIBLE);
//                                } else {
//                                    fenxiao_llOrderNoeval.setVisibility(View.GONE);
//                                }
                            }
                            movie_msg = bean.getIs_movie_msg();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(getActivity(), json);
                }
            }
        });
    }


}