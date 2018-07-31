package net.shopnc.b2b2c.android.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.MembenDetailsInfo;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.ui.message.ChatListActivity;
import net.shopnc.b2b2c.android.ui.message.MemberMessageSiteActivity;
import net.shopnc.b2b2c.android.ui.mine.AddressListActivity;
import net.shopnc.b2b2c.android.ui.mine.FavGoodAndStoreActivity;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;
import net.shopnc.b2b2c.android.ui.mine.MemberAccountBindActivity;
import net.shopnc.b2b2c.android.ui.mine.MemberConsultListActivity;
import net.shopnc.b2b2c.android.ui.mine.MemberInfoActivity;
import net.shopnc.b2b2c.android.ui.mine.MineDepositActivity;
import net.shopnc.b2b2c.android.ui.mine.MineFootPrintActivity;
import net.shopnc.b2b2c.android.ui.mine.MinePointsActivity;
import net.shopnc.b2b2c.android.ui.mine.MineRedPackageActivity;
import net.shopnc.b2b2c.android.ui.mine.MineVoucherActivity;
import net.shopnc.b2b2c.android.ui.mine.MyAssetActivity;
import net.shopnc.b2b2c.android.ui.mine.SettingActivity;
import net.shopnc.b2b2c.android.ui.order.OrderListActivity;
import net.shopnc.b2b2c.android.ui.order.OrderRefundAndReturnListActivity;
import net.shopnc.b2b2c.android.ui.points.PointOrdersListActivity;
import net.shopnc.b2b2c.android.ui.promotion.MyRepoActivity;
import net.shopnc.b2b2c.android.ui.showorders.ShowOrdersMineActivity;
import net.shopnc.b2b2c.android.ui.trys.MyTryListActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Global;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的
 *
 * @author qyf ht
 * @Time 2016/4/7
 */
public class MineFragment extends Fragment {

    @Bind(R.id.vOrderNew)
    View vOrderNew;
    @Bind(R.id.vOrderPay)
    View vOrderPay;
    @Bind(R.id.vOrderNoeval)
    View vOrderNoeval;
    @Bind(R.id.vOrderChange)
    View vOrderChange;
    @Bind(R.id.vhint)
    View vhint;
    @Bind(R.id.llOrderPay)
    LinearLayout llOrderPay;
    @Bind(R.id.llOrderChange)
    LinearLayout llOrderChange;
    @Bind(R.id.llOrderAll)
    LinearLayout llOrderAll;
    @Bind(R.id.llVoucher)
    LinearLayout llVoucher;
    @Bind(R.id.llRedpacket)
    LinearLayout llRedpacket;
    @Bind(R.id.llPointList)
    LinearLayout llPointList;
    @Bind(R.id.llConsultList)
    LinearLayout llConsultList;
    @Bind(R.id.llPoint)
    LinearLayout llPoint;
    @Bind(R.id.llTry)
    LinearLayout llTry;
    @Bind(R.id.llShow)
    LinearLayout llShow;
    @Bind(R.id.llPromotionGoods)
    LinearLayout llPromotionGoods;
    @Bind(R.id.llAddress)
    LinearLayout llAddress;
    @Bind(R.id.llMemberInfo)
    LinearLayout llMemberInfo;
    @Bind(R.id.llAccountBind)
    LinearLayout llAccountBind;
    @Bind(R.id.llMessage)
    LinearLayout llMessage;
    @Bind(R.id.llSetting)
    LinearLayout llSetting;

    private Context context;

    private FragmentActivity activity;

    @Bind(R.id.llLogin)
    LinearLayout llLogin;
    @Bind(R.id.ivMemberAvatar)
    ImageView ivMemberAvatar;
    @Bind(R.id.tvMemberName)
    TextView tvMemberName;
    @Bind(R.id.tvMemberGrade)
    TextView tvMemberGrade;
    @Bind(R.id.rlMemberInfo)
    RelativeLayout rlMemberInfo;
    @Bind(R.id.llFavGoods)
    LinearLayout llFavGoods;
    @Bind(R.id.llFavStore)
    LinearLayout llFavStore;
    @Bind(R.id.llZuji)
    LinearLayout llZuji;
    @Bind(R.id.llOrderNew)
    LinearLayout llOrderNew;
    @Bind(R.id.llOrderNoeval)
    LinearLayout llOrderNoeval;
    @Bind(R.id.llMyAsset)
    LinearLayout llMyAsset;
    @Bind(R.id.llPredeposit)
    LinearLayout llPredeposit;
    @Bind(R.id.tvPredeposit)
    TextView tvPredeposit;
    @Bind(R.id.tvVoucher)
    TextView tvVoucher;
    @Bind(R.id.tvRedpacket)
    TextView tvRedpacket;
    @Bind(R.id.tvPoint)
    TextView tvPoint;

    private MyShopApplication application;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewLayout = inflater.inflate(R.layout.main_mine_view, container, false);
        activity = getActivity();
        context = getActivity();
        ButterKnife.bind(this, viewLayout);

        application = (MyShopApplication) getActivity().getApplicationContext();
        return viewLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化view
     */
    private void initView() {
        if (ShopHelper.isLogin()) {
            rlMemberInfo.setVisibility(View.VISIBLE);
            llLogin.setVisibility(View.GONE);

            loadMemberInfo();
            postUnreadMessageCount();
        } else {
            rlMemberInfo.setVisibility(View.GONE);
            llLogin.setVisibility(View.VISIBLE);

            vOrderNew.setVisibility(View.INVISIBLE);
            vOrderPay.setVisibility(View.INVISIBLE);
            vOrderNoeval.setVisibility(View.INVISIBLE);
            vOrderChange.setVisibility(View.INVISIBLE);

            tvPredeposit.setText("0.00");
            tvVoucher.setText("0");
            tvRedpacket.setText("0");
            tvPoint.setText("0");
        }
    }

    /**
     * 我的商城信息（收藏商品数目。。）
     */
    private void loadMemberInfo() {
        Map<String, String> p = new HashMap<>();
        p.put("token", application.getToken());

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_GETTOTALNUM, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                MembenDetailsInfo membenDetailsInfo = JsonUtil.toBean(data, "memberInfo", new TypeToken<MembenDetailsInfo>() {
                }.getType());
                application.setAvatar(membenDetailsInfo.getAvatarUrl());

                tvMemberName.setText(application.getMemberName());
                LoadImage.loadRemoteCircleImg(context, ivMemberAvatar, application.getAvatar());
                tvMemberGrade.setText(membenDetailsInfo.getCurrGrade().getGradeName() + "会员");

                if (JsonUtil.toInteger(data, "ordersStateNewCount") > 0) {
                    vOrderNew.setVisibility(View.VISIBLE);
                } else {
                    vOrderNew.setVisibility(View.INVISIBLE);
                }
                if (JsonUtil.toInteger(data, "ordersStatePayCount") > 0) {
                    vOrderPay.setVisibility(View.VISIBLE);
                } else {
                    vOrderPay.setVisibility(View.INVISIBLE);
                }
                if (JsonUtil.toInteger(data, "ordersStateEvaluationCount") > 0) {
                    vOrderNoeval.setVisibility(View.VISIBLE);
                } else {
                    vOrderNoeval.setVisibility(View.INVISIBLE);
                }
                if (JsonUtil.toInteger(data, "refundAndReturnCount") > 0) {
                    vOrderChange.setVisibility(View.VISIBLE);
                } else {
                    vOrderChange.setVisibility(View.INVISIBLE);
                }
            }
        }, p);

        //读取预存款
        p.put("scope", "predeposit");
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e(">>", data);
                tvPredeposit.setText(ShopHelper.getPriceString(Double.valueOf(JsonUtil.toString(data, "predepositAvailable"))));
            }
        }, p);

        //读取优惠券
        p.put("scope", "voucher");
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e(">>", data);
                tvVoucher.setText(JsonUtil.toString(data, "voucher"));
            }
        }, p);

        //读取红包
        p.put("scope", "redpacket");
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e(">>", data);
                tvRedpacket.setText(JsonUtil.toString(data, "redpacket"));
            }
        }, p);

        //读取积分
        p.put("scope", "points");
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_DEPOSIT_REMAIN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e(">>", data);
                tvPoint.setText(JsonUtil.toString(data, "points"));
            }
        }, p);
    }

    /**
     * 获取站内未读消息数量
     */
    protected void postUnreadMessageCount() {
        Map<String, String> map = new HashMap<>();
        map.put("clientType", "android");
        map.put("token", application.getToken());
        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_UNREAD_MESSAGE_COUNT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                String cartCount = JsonUtil.toString(data, "count");
                Global.isUnreadMessage = !TextUtils.isEmpty(cartCount) && !cartCount.equals("0");

                if (Global.isUnreadMessage) {
                    vhint.setVisibility(View.VISIBLE);
                } else {
                    vhint.setVisibility(View.INVISIBLE);
                }
            }
        }, map);
    }


    @OnClick({R.id.llPromotionGoods})
    public void onPromotionClick(View view) {
        switch (view.getId()) {
            case R.id.llPromotionGoods:
                if (!ShopHelper.isLogin(getActivity())) {
                    return;
                }
                startActivity(new Intent(context, MyRepoActivity.class));
                break;
        }
    }


    /**
     * 订单
     *
     * @param view
     */
    @OnClick({R.id.llOrderAll, R.id.llOrderNew, R.id.llOrderNoeval, R.id.llOrderPay})
    public void onClick(View view) {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Intent intent = new Intent(activity, OrderListActivity.class);
        switch (view.getId()) {
            case R.id.llOrderAll:
                intent.putExtra(OrderListActivity.STATE, "");
                break;
            case R.id.llOrderNew:
                intent.putExtra(OrderListActivity.STATE, "new");
                break;
            case R.id.llOrderPay:
                intent.putExtra(OrderListActivity.STATE, "pay");
                break;
            case R.id.llOrderNoeval:
                intent.putExtra(OrderListActivity.STATE, "noeval");
                break;
        }
        startActivity(intent);
    }

    @OnClick({R.id.llOrderChange, R.id.llMyAsset, R.id.llPredeposit, R.id.llVoucher, R.id.llRedpacket, R.id.llPointList, R.id.llTry})
    public void depositClick(View v) {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Intent intent = null;
        switch (v.getId()) {
            case R.id.llOrderChange:
                intent = new Intent(activity, OrderRefundAndReturnListActivity.class);
                intent.putExtra("flag", "refund");
                break;
            case R.id.llMyAsset:
                intent = new Intent(activity, MyAssetActivity.class);
                break;
            case R.id.llPredeposit:
                intent = new Intent(activity, MineDepositActivity.class);
                break;
            case R.id.llVoucher:
                intent = new Intent(activity, MineVoucherActivity.class);
                break;
            case R.id.llRedpacket:
                intent = new Intent(activity, MineRedPackageActivity.class);
                break;
            case R.id.llPointList:
                intent = new Intent(activity, MinePointsActivity.class);
                break;
            case R.id.llTry:
                intent = new Intent(context, MyTryListActivity.class);
                break;
        }
        startActivity(intent);
    }

    /**
     * 跳转到消息列表
     */
    @OnClick(R.id.rlMessage)
    public void messageClick() {
        if (ShopHelper.isLogin(getContext())) {
            startActivity(new Intent(getActivity(), ChatListActivity.class));
        }
    }

    /**
     * 跳转到消息设置
     */
    @OnClick(R.id.llMessage)
    public void messageSiteClick() {
        if (ShopHelper.isLogin(getContext())) {
            startActivity(new Intent(context, MemberMessageSiteActivity.class));
        }
    }

    /**
     * 跳转到账户管理
     */
    @OnClick({R.id.btnMemberInfo, R.id.ivMemberAvatar, R.id.llMemberInfo})
    public void memberInfoClick() {
        if (ShopHelper.isLogin(getContext())) {
            startActivity(new Intent(getActivity(), MemberInfoActivity.class));
        }
    }

    /**
     * 跳转到商品咨询
     */
    @OnClick(R.id.llPoint)
    public void pointClick() {
        if (ShopHelper.isLogin(getContext())) {
            Intent intent = new Intent(context, PointOrdersListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到积分兑换
     */
    @OnClick(R.id.llConsultList)
    public void ConsultListClick() {
        if (ShopHelper.isLogin(getContext())) {
            Intent intent = new Intent(context, MemberConsultListActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到晒宝
     */
    @OnClick(R.id.llShow)
    public void showClick() {
        if (ShopHelper.isLogin(getContext())) {
            Intent intent = new Intent(context, ShowOrdersMineActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 跳转到登录绑定
     */
    @OnClick(R.id.llAccountBind)
    public void accountBindClick() {
        if (ShopHelper.isLogin(getContext())) {
            Intent intent = new Intent(context, MemberAccountBindActivity.class);
            startActivity(intent);
        }
    }

    @OnClick({R.id.llSetting})
    public void settingClick() {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Common.gotoActivity(activity, SettingActivity.class, false, null);
    }

    @OnClick(R.id.llAddress)
    public void addressClick() {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("addressFlag", "0");
        Common.gotoActivity(getActivity(), AddressListActivity.class, false, params);
    }

    @OnClick(R.id.llZuji)
    public void printClick() {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Intent i = new Intent(getActivity(), MineFootPrintActivity.class);
        getActivity().startActivity(i);
    }

    @OnClick(R.id.llLogin)
    public void loginClick() {
        Common.gotoActivity(activity, LoginActivity.class, false, null);
    }

    @OnClick(R.id.llFavGoods)
    public void goodClick() {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Intent i = new Intent(getActivity(), FavGoodAndStoreActivity.class);
        i.putExtra("flag", "good");
        getActivity().startActivity(i);
    }

    @OnClick(R.id.llFavStore)
    public void storeClick() {
        if (!ShopHelper.isLogin(getActivity())) {
            return;
        }
        Intent i = new Intent(getActivity(), FavGoodAndStoreActivity.class);
        i.putExtra("flag", "store");
        getActivity().startActivity(i);
    }


}
