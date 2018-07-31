package net.shopnc.b2b2c.android.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.DiscountCouponAdapter;
import net.shopnc.b2b2c.android.bean.MemberVoucher;
import net.shopnc.b2b2c.android.bean.PageEntity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.util.OkHttpUtil;
import net.shopnc.b2b2c.android.custom.MyListView;
import net.shopnc.b2b2c.android.util.ConstantUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: net.shopnc.b2b2c.android.ui.mine.MineVoucherMyFragment.java
 * @author: Jie
 * @date: 2016-05-30 09:54
 */
public class MineVoucherMyFragment extends MineBaseFragment {

    @Bind(R.id.mine_pre_recycler)
    MyListView mine_pre_recycler;
    @Bind(R.id.mine_pre_recycler2)
    MyListView mine_pre_recycler2;

    @Bind(R.id.layoutEmpty)
    RelativeLayout layoutEmpty;
    @Bind(R.id.imgEmptyLogo)
    ImageView imgEmptyLogo;
    @Bind(R.id.tvEmptyTitle)
    TextView tvEmptyTitle;
    @Bind(R.id.tvEmptyBody)
    TextView tvEmptyBody;
    @Bind(R.id.voucher_line)
    LinearLayout voucher_line;


    private List<MemberVoucher> myListTemp1 = new ArrayList<>();
    private List<MemberVoucher> myListTemp2 = new ArrayList<>();
    private List<MemberVoucher> myList = new ArrayList<>();
    private List<MemberVoucher> myList2 = new ArrayList<>();

    private DiscountCouponAdapter adapter;
    private DiscountCouponAdapter adapter2;

    public static MineVoucherMyFragment newInstance() {
        return new MineVoucherMyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("cur_fragment", 1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deposit;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        adapter = new DiscountCouponAdapter(getActivity());
        adapter2 = new DiscountCouponAdapter(getActivity());
        mine_pre_recycler.setAdapter(adapter);
        mine_pre_recycler2.setAdapter(adapter2);
//        if (myList.isEmpty()) {
//            mine_pre_recycler.setVisibility(View.GONE);
//            layoutEmpty.setVisibility(View.VISIBLE);
//            imgEmptyLogo.setBackgroundResource(R.drawable.empty_photo);
//            tvEmptyTitle.setText("您尚无任何优惠券信息");
//            tvEmptyBody.setText("快去店铺领取吧");
//        }
//        adapter.setNUmber(myListTemp1.size());
        loadDiscount();
//        ++curPage;
    }


    private void loadDiscount() {
        Map<String, String> param = new HashMap<>();
        param.put("token", myApplication.getToken());
        param.put("page", curPage + "");

        OkHttpUtil.postAsyn(getActivity(),ConstantUrl.URL_GET_VOUCHER, new BeanCallback<String>() {

            @Override
            public void response(String response) {
//                LogHelper.e("优惠券",response);

                ArrayList<MemberVoucher> list = JsonUtil.toBean(response, "voucherList", new TypeToken<ArrayList<MemberVoucher>>() {
                }.getType());
                PageEntity pageEntity = JsonUtil.toBean(response, "pageEntity", new TypeToken<PageEntity>() {
                }.getType());
                if (list != null && pageEntity != null) {
                    if (list.isEmpty() && curPage == 1) {
                        layoutEmpty.setVisibility(View.VISIBLE);
                        imgEmptyLogo.setImageResource(R.drawable.no_data_b);
                        tvEmptyTitle.setText("亲，您还没有优惠券哦~");
                        tvEmptyBody.setText("");
                    } else {
                        layoutEmpty.setVisibility(View.GONE);
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getVoucherState() == 0 && list.get(i).getVoucherExpiredState() == 0) {
                                myListTemp1.add(list.get(i));
                            } else {
                                myListTemp2.add(list.get(i));
                            }
                        }
                        myList.addAll(myListTemp1);
                        myList2.addAll(myListTemp2);
                        adapter.setmDatas(myList);
                        adapter2.setmDatas(myList2);

                        if (myList2.isEmpty()) {
                            voucher_line.setVisibility(View.GONE);
                        }

                        adapter.notifyDataSetChanged();
                        adapter2.notifyDataSetChanged();
                    }

                }
            }
        }, param);
    }

}
