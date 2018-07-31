package net.shopnc.b2b2c.android.ui.store;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.shopnc.b2b2c.android.MainFragmentManager;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.ui.home.SearchActivity;
import net.shopnc.b2b2c.android.ui.mine.IMFriendsListActivity;
import net.shopnc.b2b2c.android.ui.mine.IMNewListActivity;
import net.shopnc.b2b2c.android.ui.mine.LoginActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsGridFragment;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragment;

import java.net.URLEncoder;

/**
 * 商品列表菜单管理界面
 *
 * @author 胡婷
 * @Time 2015/7/8
 */
public class StoreGoodsListFragmentManager extends FragmentActivity implements OnClickListener {
    private MyShopApplication myApplication;

    private FragmentManager fragmentManager;

    private PopupWindow popSort;
    private PopupWindow popScreen;

    //列表类型
    private Boolean isList = true;

    //排序条件
    private String gc_id;
    private String gc_name;
    private String barcode;//商品条形码
    private String keyword;//搜索关键字
    private String b_id;//品牌
    private String key = ""; //排序方式 1-销量 2-浏览量(人气排序) 3-价格 空-最新发布(综合排序)
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
    private EditText etPriceForm;
    private EditText etPriceTo;
    private Button btnScreenSave;
    private MyGridView gvContract;


    private String store_id;
    private String stc_id;
    private String curpage = "1";
    private String page = "10";

    private ImageView imgClassify;
    private ImageView imgMenu;
    private ImageButton btnBack;
    private TextView etSearchText;

    private Intent intent = null;
    private PopupWindow popupWindow;
    private View mPopupWindowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_goods_fragmentmanager_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();
        fragmentManager = getSupportFragmentManager();

        keyword = this.getIntent().getStringExtra("keyword");//关键字搜索
        stc_id = this.getIntent().getStringExtra("stc_id");//分类的id
        gc_name = this.getIntent().getStringExtra("gc_name");//分类Name
        store_id = this.getIntent().getStringExtra("store_id");

        initView();
        initPopupWindow();
    }

    private void initView() {
        //返回按钮
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //分类
        imgClassify = findViewById(R.id.imgClassify);
        imgClassify.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(StoreGoodsListFragmentManager.this, StoreSearchActivity.class);
                intent.putExtra("store_id", store_id);
                startActivity(intent);
            }
        });

        //悬浮菜单
        imgMenu = findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

        //搜索
        etSearchText = findViewById(R.id.etSearchText);
        etSearchText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StoreGoodsListFragmentManager.this, StoreSearchActivity.class);
                intent.putExtra("store_id", store_id);
                startActivity(intent);
                finish();
            }
        });


        //排序下拉
        btnSort = findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showSortPopWindow(view);
            }
        });

        //销量优先
        btnSale = findViewById(R.id.btnSale);
        btnSale.setOnClickListener(new OnClickListener() {
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
        btnScreen = findViewById(R.id.btnScreen);
        btnScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreenPopWindow(view);
            }
        });

        //列表类型
        final Button btnListType = findViewById(R.id.btnListType);
        btnListType.setSelected(true);
        isList = true;
        btnListType.setOnClickListener(new OnClickListener() {
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
            View viewPopSort = LayoutInflater.from(StoreGoodsListFragmentManager.this).inflate(R.layout.nc_popwindow_goods_sort, null);
            popSort = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popSort.setTouchable(true);
            popSort.setOutsideTouchable(true);
            popSort.setBackgroundDrawable(new BitmapDrawable(StoreGoodsListFragmentManager.this.getResources(), (Bitmap) null));

            //选择综合排序
            btnSortDefault = viewPopSort.findViewById(R.id.btnSortDefault);
            btnSortDefault.setOnClickListener(new OnClickListener() {
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
            btnSortPriceDown.setOnClickListener(new OnClickListener() {
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
            btnSortPriceUp.setOnClickListener(new OnClickListener() {
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
            btnSortView.setOnClickListener(new OnClickListener() {
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
            tvBackground.setOnClickListener(new OnClickListener() {
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
            final View viewPopScreen = LayoutInflater.from(this).inflate(R.layout.nc_popwindow_goods_screen_store, null);
            popScreen = new PopupWindow(viewPopScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popScreen.setTouchable(true);
            popScreen.setOutsideTouchable(true);
            popScreen.setBackgroundDrawable(new BitmapDrawable(this.getResources(), (Bitmap) null));

            //关闭筛选弹出窗口
            ImageButton btnBack = viewPopScreen.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new OnClickListener() {
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
            tvBackground.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    popScreen.dismiss();
                }
            });

            //重置选项
            Button btnReset = viewPopScreen.findViewById(R.id.btnReset);
            btnReset.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //initScreen(viewPopScreen);
                    etPriceForm.setText("");
                    etPriceTo.setText("");
                }
            });

            //筛选保存
            btnScreenSave.setOnClickListener(new OnClickListener() {
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
                    if (contact.length() > 0) {
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
        url += "&prom_type=" + prom_type;
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
        GoodsListFragment goodsListFragment = new GoodsListFragment();
        goodsListFragment.url = url;
        goodsListFragment.pageno = 1;
        transaction.replace(R.id.content, goodsListFragment);
        transaction.commit();
    }

    //网格模式
    private void loadGoodsGrid(String url) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        GoodsGridFragment goodsGridFragment = new GoodsGridFragment();
        goodsGridFragment.url = url;
        goodsGridFragment.pageno = 1;
        transaction.replace(R.id.content, goodsGridFragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //菜单点击事件
            case R.id.textview_home:
                //ShopHelper.showMessage(this,"首页");
                intent = new Intent(StoreGoodsListFragmentManager.this, MainFragmentManager.class);
                myApplication.sendBroadcast(new Intent(Constants.SHOW_HOME_URL));
                startActivity(intent);
                popupWindow.dismiss();
                break;

            case R.id.textview_search:
                //ShopHelper.showMessage(this,"搜索");
                startActivity(new Intent(StoreGoodsListFragmentManager.this, SearchActivity.class));
                popupWindow.dismiss();
                break;

            case R.id.textview_cart:
                //ShopHelper.showMessage(this,"购物车");
                intent = new Intent(StoreGoodsListFragmentManager.this, MainFragmentManager.class);
                myApplication.sendBroadcast(new Intent(Constants.SHOW_CART_URL));
                startActivity(intent);
                popupWindow.dismiss();
                break;

            case R.id.textview_msg:
                //ShopHelper.showMessage(this,"消息");
                if (ShopHelper.isLogin(StoreGoodsListFragmentManager.this, myApplication.getLoginKey())) {
//                    startActivity(new Intent(StoreGoodsListFragmentManager.this, IMFriendsListActivity.class));
                    startActivity(new Intent(StoreGoodsListFragmentManager.this, IMNewListActivity.class));

                } else {
                    startActivity(new Intent(StoreGoodsListFragmentManager.this, LoginActivity.class));
                }
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 显示popupwindow
     */
    private void showPopupWindow() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(imgMenu, imgMenu.getLayoutParams().width / 2, 0);
        } else {
            popupWindow.dismiss();
        }
    }

    /**
     * 初始化popupwindow
     */
    private void initPopupWindow() {
        initPopupWindowView();
        popupWindow = new PopupWindow(mPopupWindowView, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.nc_icon_setting));
        popupWindow.update();
        popupWindow.setAnimationStyle(R.anim.popup_window_enter);
        popupWindow.setAnimationStyle(R.anim.popup_window_exit);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    /**
     * 初始化popupwindowView
     */
    private void initPopupWindowView() {
        mPopupWindowView = LayoutInflater.from(this).inflate(R.layout.menu_store, null);
        TextView textview_home = mPopupWindowView.findViewById(R.id.textview_home);
        textview_home.setOnClickListener(this);

        TextView textview_search = mPopupWindowView.findViewById(R.id.textview_search);
        textview_search.setOnClickListener(this);

        TextView textview_cart = mPopupWindowView.findViewById(R.id.textview_cart);
        textview_cart.setOnClickListener(this);

        TextView textview_msg = mPopupWindowView.findViewById(R.id.textview_msg);
        textview_msg.setOnClickListener(this);
    }
}
