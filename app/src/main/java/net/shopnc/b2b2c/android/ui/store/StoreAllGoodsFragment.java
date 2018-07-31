package net.shopnc.b2b2c.android.ui.store;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.custom.MyGridView;

import java.net.URLEncoder;

/**
 * 店铺所有商品fragment
 *
 * Created by 胡婷 on 2015/12/29.
 */
public class StoreAllGoodsFragment extends Fragment {

    private MyShopApplication myApplication;
    private static final String ARG_STORE_ID = "store_id";
    private String store_id;
    private FragmentManager fragmentManager;

    private PopupWindow popSort;
    private PopupWindow popScreen;
    private InputMethodManager imm;

    //列表类型
    private Boolean isList = true;

    //排序条件
    private String gc_id;
    private String gc_name;
    private String barcode;//商品条形码
    private String keyword;//搜索关键字
    private String b_id;//品牌
    private String key = ""; //排序方式 1-销量 2-浏览量(人气排序) 3-价格 空-最新发布(综合排序)--接口处改变了
    private String order = "2";// 排序方式 1-升序 2-降序
    private String prom_type = "";//促销类型

    //筛选条件
    private String gift;//赠品 1-是 0-否
    private String groupbuy;//团购 1-是 0-否
    private String xianshi;//显示 1-是 0-否
    private String own_shop;//自营 1-是 0-否
    private String area_id;//一级地区编号
    private String ci;//消保ID，多个消保时ID以英文下划线“_”分隔,如 2_7_8
    private String price_from;//价格筛选下限
    private String price_to;//价格筛选上线限

    //排序按钮
    private Button btnSort;
    private Button btnSale;
    private Button btnSortDefault;
    private Button btnSortPriceDown;
    private Button btnSortPriceUp;
    private Button btnSortView;
    private Button btnScreen;


    //商品筛选弹出窗口
    private Button btnScreenGift;
    private Button btnScreenGroupbuy;
    private Button btnScreenXianshi;
    private Button btnScreenOwnShop;
    private TextView tvArea;
    private EditText etAreaId;
    private EditText etPriceForm;
    private EditText etPriceTo;
    private Button btnScreenSave;
    private MyGridView gvContract;

    private String stc_id;
    private String curpage = "1";
    private String page = "10";

    public StoreAllGoodsFragment() {}
    public static StoreAllGoodsFragment newInstance(String store_id) {
        StoreAllGoodsFragment fragment = new StoreAllGoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STORE_ID, store_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store_id = getArguments().getString(ARG_STORE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_store_goods, container, false);
        MyExceptionHandler.getInstance().setContext(getActivity());
        myApplication = (MyShopApplication) getActivity().getApplicationContext();
        fragmentManager = getChildFragmentManager();

        keyword = getActivity().getIntent().getStringExtra("keyword");//关键字搜索
        stc_id = getActivity().getIntent().getStringExtra("stc_id");//分类的id
        store_id = getActivity().getIntent().getStringExtra("store_id");

        initView(layout);
        loadGoods();

        return layout;
    }


    /**
     * 初始化控件
     * @param layout
     */
    private void initView(View layout){
        //排序下拉
        btnSort = layout.findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortPopWindow(view);
            }
        });

        //销量优先
        btnSale = layout.findViewById(R.id.btnSale);
        btnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSale.setSelected(true);
                btnSort.setSelected(false);
                btnSort.setText("综合排序");
                key = "3";
                order = "2";
                loadGoods();
            }
        });
        btnSort.setSelected(true);

        //筛选
        btnScreen = layout.findViewById(R.id.btnScreen);
        btnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreenPopWindow(view);
            }
        });

        //列表类型
        final Button btnListType = layout.findViewById(R.id.btnListType);
        btnListType.setSelected(true);
        isList = true;
        btnListType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isList) {
                    btnListType.setSelected(false);
                    isList = false;
                } else {
                    btnListType.setSelected(true);
                    isList = true;
                }
                loadGoods();
            }
        });

        loadGoods();
    }


    /**
     * 显示排序弹出窗口
     */
    private void showSortPopWindow(View view) {
        if (popSort == null) {
            View viewPopSort = LayoutInflater.from(getActivity()).inflate(R.layout.nc_popwindow_goods_sort, null);
            popSort = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popSort.setTouchable(true);
            popSort.setOutsideTouchable(true);
            popSort.setBackgroundDrawable(new BitmapDrawable(getActivity().getResources(), (Bitmap) null));

            //选择综合排序
            btnSortDefault = viewPopSort.findViewById(R.id.btnSortDefault);
            btnSortDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSort.setSelected(true);
                    btnSort.setText(btnSortDefault.getText());
                    btnSale.setSelected(false);
                    key = "1";
                    order = "2";
                    loadGoods();
                    popSort.dismiss();
                }
            });

            //选择价格从高到低排序
            btnSortPriceDown = viewPopSort.findViewById(R.id.btnSortPriceDown);
            btnSortPriceDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSort.setSelected(true);
                    btnSort.setText(btnSortPriceDown.getText());
                    btnSale.setSelected(false);
                    key = "2";
                    order = "2";
                    loadGoods();
                    popSort.dismiss();
                }
            });

            //选择价格从低到高排序
            btnSortPriceUp = viewPopSort.findViewById(R.id.btnSortPriceUp);
            btnSortPriceUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSort.setSelected(true);
                    btnSort.setText(btnSortPriceUp.getText());
                    btnSale.setSelected(false);
                    key = "2";
                    order = "1";
                    loadGoods();
                    popSort.dismiss();
                }
            });

            //选择人气排序
            btnSortView = viewPopSort.findViewById(R.id.btnSortView);
            btnSortView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSort.setSelected(true);
                    btnSort.setText(btnSortView.getText());
                    btnSale.setSelected(false);
                    key = "4";
                    order = "2";
                    loadGoods();
                    popSort.dismiss();
                }
            });

            TextView tvBackground = viewPopSort.findViewById(R.id.tvBackground);
            tvBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popSort.dismiss();
                }
            });
        }
        btnSortDefault.setSelected(false);
        btnSortPriceDown.setSelected(false);
        btnSortPriceUp.setSelected(false);
        btnSortView.setSelected(false);
        if (key.equals("1")) {
            btnSortDefault.setSelected(true);
        }
        if (key.equals("4")) {
            btnSortView.setSelected(true);
        }
        if (key.equals("2")) {
            if (order.equals("1")) {
                btnSortPriceUp.setSelected(true);
            } else {
                btnSortPriceDown.setSelected(true);
            }
        }
        popSort.showAsDropDown(view);

    }

    /**
     * 显示筛选弹出窗口
     */
    private void showScreenPopWindow(View view) {
        if (popScreen == null) {
            final View viewPopScreen = LayoutInflater.from(getActivity()).inflate(R.layout.nc_popwindow_goods_screen_store, null);
            popScreen = new PopupWindow(viewPopScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popScreen.setTouchable(true);
            popScreen.setOutsideTouchable(true);
            popScreen.setBackgroundDrawable(new BitmapDrawable(getActivity().getResources(), (Bitmap) null));

            //关闭筛选弹出窗口
            ImageButton btnBack = viewPopScreen.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popScreen.dismiss();
                }
            });

            etPriceForm = viewPopScreen.findViewById(R.id.etPriceFrom);
            etPriceTo = viewPopScreen.findViewById(R.id.etPriceTo);

            btnScreenSave = viewPopScreen.findViewById(R.id.btnScreenSave);
            gvContract = viewPopScreen.findViewById(R.id.gvContract);


            TextView tvBackground = viewPopScreen.findViewById(R.id.tvBackground);
            tvBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popScreen.dismiss();
                }
            });

            //重置选项
            Button btnReset = viewPopScreen.findViewById(R.id.btnReset);
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //initScreen(viewPopScreen);
                    etPriceForm.setText("");
                    etPriceTo.setText("");
                }
            });

            //筛选保存
            btnScreenSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    price_from = etPriceForm.getText().toString();
                    price_to = etPriceTo.getText().toString();
                    String contact = "";
                    for (int i = 0; i < gvContract.getChildCount(); i++) {
                        LinearLayout ll = (LinearLayout) gvContract.getChildAt(i);
                        Button btnContract = ll.findViewById(R.id.btnContract);
                        TextView tvContract = ll.findViewById(R.id.tvContract);
                        if (btnContract.isActivated()) {
                            contact += tvContract.getText().toString() + "_";
                        }
                    }
                    if(contact.length() > 0) {
                        ci = contact.substring(0, contact.length() - 1);
                    } else {
                        ci = contact;
                    }
                    loadGoods();
                    popScreen.dismiss();
                }
            });

        }
        popScreen.showAtLocation(view, Gravity.CENTER, 0, 0);
    }


    /**
     * 加载商品列表(这个商品列表是所有店铺的商品列表)
     */
    public void loadGoods() {
        String url = Constants.URL_STORE_GOODS_LIST;
        if (keyword != null && !keyword.equals("null")) {
            String keywordstr = URLEncoder.encode(keyword);
            url = url + "&keyword=" + keywordstr + "&key=" + key;
        }
        if (barcode != null && !barcode.equals("") && !barcode.equals("null")) {
            url = url + "&key=" + key + "&barcode=" + barcode;
        }
        if (gc_id != null && !gc_id.equals("") && !gc_id.equals("null")) {
            url = url + "&gc_id=" + gc_id + "&key=" + key;
        }
        if (b_id != null && !b_id.equals("") && !b_id.equals("null")) {
            url = url + "&b_id=" + b_id + "&key=" + key;
        }
        if (order != null && !order.equals("null") && !order.equals("")) {
            url = url + "&order=" + order + "&key=" + key;
        }
        if (stc_id != null && !stc_id.equals("null") && !stc_id.equals("")) {
            url = url + "&stc_id=" + stc_id + "&username=" + myApplication.getUserName();
        }
        if (store_id != null && !store_id.equals("null") && !store_id.equals("")) {
            url = url + "&store_id=" + store_id + "&username=" + myApplication.getUserName();
        }
        url += "&gift=" + gift;
        url += "&groupbuy=" + groupbuy;
        url += "&xianshi=" + xianshi;
        url += "&own_shop=" + own_shop;
        url += "&price_from=" + price_from;
        url += "&price_to=" + price_to;
        url += "&area_id=" + area_id;
        url += "&ci=" + ci;
        url += "&prom_type=" + prom_type ;
        //url += "&stc_id=" + stc_id;
        url += "&curpage=" + curpage;
        url += "&page=" + page;

        if (isList) {
            //列表模式
            loadGoodsList(url);
        } else {
            //网格模式
           loadGoodsGrid(url);
        }
    }

    //列表模式
    private void loadGoodsList(String url) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LogHelper.d("huting----", "StoreAllGoodsFragment--1");
        StoreGoodsListFragment goodsListFragment = new StoreGoodsListFragment();
        goodsListFragment.url = url;
        goodsListFragment.pageno = 1;
        transaction.replace(R.id.content, goodsListFragment);
        transaction.commit();
    }

    //网格模式
    private void loadGoodsGrid(String url) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        StoreGoodsGridFragment goodsGridFragment = new StoreGoodsGridFragment();
        goodsGridFragment.url = url;
        goodsGridFragment.pageno = 1;
        transaction.replace(R.id.content, goodsGridFragment);
        transaction.commit();
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
