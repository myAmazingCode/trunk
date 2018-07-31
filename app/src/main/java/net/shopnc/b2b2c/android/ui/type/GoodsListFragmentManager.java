package net.shopnc.b2b2c.android.ui.type;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.AreaSpinnerAdapter;
import net.shopnc.b2b2c.android.adapter.ContractGridViewAdapter;
import net.shopnc.b2b2c.android.bean.CityList;
import net.shopnc.b2b2c.android.bean.ContractInfo;
import net.shopnc.b2b2c.android.bracode.ui.CaptureActivity;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DialogHelper;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.home.SearchActivity;
import net.shopnc.b2b2c.android.ui.mine.IMFriendsListActivity;
import net.shopnc.b2b2c.android.ui.mine.IMNewListActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * 商品列表菜单管理界面
 *
 * @author dqw
 * @Time 2015/7/8
 */
public class GoodsListFragmentManager extends FragmentActivity {
    private MyShopApplication myApplication;

    private FragmentManager fragmentManager;

    private PopupWindow popSort;
    private PopupWindow popScreen;

    private Button btnIm;

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
    private Button history_htn;
    Spinner spAreaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_fragmentmanager_view);
        MyExceptionHandler.getInstance().setContext(this);
        myApplication = (MyShopApplication) getApplicationContext();

        fragmentManager = getSupportFragmentManager();

        gc_id = GoodsListFragmentManager.this.getIntent().getStringExtra("gc_id");//分类ID
        gc_name = GoodsListFragmentManager.this.getIntent().getStringExtra("gc_name");//分类Name
        barcode = GoodsListFragmentManager.this.getIntent().getStringExtra("barcode");//条码扫描
        keyword = GoodsListFragmentManager.this.getIntent().getStringExtra("keyword");//关键字搜索
        b_id = GoodsListFragmentManager.this.getIntent().getStringExtra("b_id");//品牌编号
        history_htn = findViewById(R.id.history_htn);
        //返回按钮
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //搜索
        TextView tvSearch = findViewById(R.id.tvSearch);
        tvSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsListFragmentManager.this, SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //设置热门搜索词
        String searchHotName = myApplication.getSearchHotName();
        if (searchHotName != null && !searchHotName.equals("")) {
            if (searchHotName.length() > 8) {
                tvSearch.setHint(searchHotName.substring(0, 8) + "...");
            } else {
                tvSearch.setHint(searchHotName);
            }
        } else {
            tvSearch.setHint(R.string.default_search_text);
        }
        if (keyword != null && !keyword.equals("")) {
            if (keyword.length() > 8) {
                tvSearch.setHint(keyword.substring(0, 8) + "...");
            } else {
                tvSearch.setHint(keyword);
            }

        }

        //二维码扫描
        Button btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodsListFragmentManager.this, CaptureActivity.class);
                startActivity(intent);
            }
        });

        btnIm = findViewById(R.id.btnIm);
        btnIm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (ShopHelper.isLogin(GoodsListFragmentManager.this, myApplication.getLoginKey())) {
////                    startActivity(new Intent(GoodsListFragmentManager.this, IMFriendsListActivity.class));
//                    startActivity(new Intent(GoodsListFragmentManager.this, IMNewListActivity.class));
//                }
                showPopupWindow();
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
                key = "1";
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
        final ImageButton btnListType = findViewById(R.id.btnListType);
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
        history_htn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopHelper.isLogin(GoodsListFragmentManager.this, myApplication.getLoginKey())) {
                    startActivity(new Intent(GoodsListFragmentManager.this, GoodsBrowseActivity.class));
                }
            }
        });

        loadGoods();
    }

    //更多加载
    private PopupWindow popupWindow;

    /**
     * 显示popupwindow
     */
    private void showPopupWindow() {
        if (popupWindow != null) {
            if (!popupWindow.isShowing()) {
                popupWindow.showAsDropDown(btnIm, btnIm.getLayoutParams().width / 2, 0);
            } else {
                popupWindow.dismiss();
                popupWindow = null;
            }
        } else {
            popupWindow = DialogHelper.initPopupWindow(this);
            popupWindow.showAsDropDown(btnIm, btnIm.getLayoutParams().width / 2, 0);
        }
    }

    /**
     * 显示排序弹出窗口
     */
    private void showSortPopWindow(View view) {
        if (popSort == null) {
            View viewPopSort = LayoutInflater.from(GoodsListFragmentManager.this).inflate(R.layout.nc_popwindow_goods_sort, null);
            popSort = new PopupWindow(viewPopSort, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popSort.setTouchable(true);
            popSort.setOutsideTouchable(true);
            popSort.setBackgroundDrawable(new BitmapDrawable(GoodsListFragmentManager.this.getResources(), (Bitmap) null));

            //选择综合排序
            btnSortDefault = viewPopSort.findViewById(R.id.btnSortDefault);
            btnSortDefault.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnSort.setSelected(true);
                    btnSort.setText(btnSortDefault.getText());
                    btnSale.setSelected(false);
                    key = "";
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
                    key = "3";
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
                    key = "3";
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
                    key = "2";
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
        if (key.equals("")) {
            btnSortDefault.setSelected(true);
        }
        if (key.equals("2")) {
            btnSortView.setSelected(true);
        }
        if (key.equals("3")) {
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
            final View viewPopScreen = LayoutInflater.from(GoodsListFragmentManager.this).inflate(R.layout.nc_popwindow_goods_screen, null);
            popScreen = new PopupWindow(viewPopScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            popScreen.setTouchable(true);
            popScreen.setBackgroundDrawable(new BitmapDrawable(GoodsListFragmentManager.this.getResources(), (Bitmap) null));
            popScreen.setOutsideTouchable(false);

            //关闭筛选弹出窗口
            ImageButton btnBack = viewPopScreen.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    popScreen.dismiss();
                }
            });

            btnScreenGift = viewPopScreen.findViewById(R.id.btnGift);
            btnScreenGroupbuy = viewPopScreen.findViewById(R.id.btnGroupbuy);
            btnScreenXianshi = viewPopScreen.findViewById(R.id.btnXianshi);
            btnScreenOwnShop = viewPopScreen.findViewById(R.id.btnOwnShop);
            tvArea = viewPopScreen.findViewById(R.id.tvArea);
            etAreaId = viewPopScreen.findViewById(R.id.etAreaId);
            etPriceForm = viewPopScreen.findViewById(R.id.etPriceFrom);
            etPriceTo = viewPopScreen.findViewById(R.id.etPriceTo);
            btnScreenSave = viewPopScreen.findViewById(R.id.btnScreenSave);
            gvContract = viewPopScreen.findViewById(R.id.gvContract);
            spAreaList = viewPopScreen.findViewById(R.id.spAreaList);

            setSelectBtnState(btnScreenGift);
            setSelectBtnState(btnScreenGroupbuy);
            setSelectBtnState(btnScreenXianshi);
            setSelectBtnState(btnScreenOwnShop);

            initScreen(viewPopScreen);

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
                    initScreen(viewPopScreen);
                }
            });

            //筛选保存
            btnScreenSave.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    price_from = etPriceForm.getText().toString();
                    price_to = etPriceTo.getText().toString();
                    area_id = etAreaId.getText().toString();
                    gift = btnScreenGift.isActivated() ? "1" : "0";
                    groupbuy = btnScreenGroupbuy.isActivated() ? "1" : "0";
                    xianshi = btnScreenXianshi.isActivated() ? "1" : "0";
                    own_shop = btnScreenOwnShop.isActivated() ? "1" : "0";
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

    private void initScreen(final View viewPopScreen) {
        //初始化按钮
        btnScreenGift.setActivated(false);
        btnScreenGroupbuy.setActivated(false);
        btnScreenXianshi.setActivated(false);
        btnScreenOwnShop.setActivated(false);
        tvArea.setText("不限");
        etAreaId.setText("");
        etPriceForm.setText("");
        etPriceTo.setText("");

        String url = Constants.URL_SEARCH_ADV;
        RemoteDataHandler.asyncDataStringGet(url, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                if (data.getCode() == HttpStatus.SC_OK) {
                    try {
                        JSONObject objJson = new JSONObject(json);

                        //地区
                        String areaListJson = objJson.getString("area_list");
                        if (!areaListJson.equals("[]")) {
                            final ArrayList<CityList> areaList = CityList.newInstanceList(areaListJson);
                            CityList defaultCityInfo = new CityList();
                            defaultCityInfo.setArea_id("0");
                            defaultCityInfo.setArea_name("不限");
                            areaList.add(0, defaultCityInfo);
                            AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(GoodsListFragmentManager.this);
                            adapter.setAreaList(areaList);

//                            final Spinner spAreaList = (Spinner) viewPopScreen.findViewById(R.id.spAreaList);
                            spAreaList.setAdapter(adapter);
                            spAreaList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    CityList areaInfo = (CityList) spAreaList.getItemAtPosition(i);
                                    tvArea.setText(areaInfo.getArea_name());
                                    etAreaId.setText(areaInfo.getArea_id());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                }
                            });

                            tvArea.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    spAreaList.performClick();
                                }
                            });
                        }

                        //消保
                        String contractListJson = objJson.getString("contract_list");
                        if (!contractListJson.equals("[]")) {
                            ArrayList<ContractInfo> contractList = ContractInfo.newInstanceList(contractListJson);
                            ContractGridViewAdapter contractGridViewAdapter = new ContractGridViewAdapter(GoodsListFragmentManager.this);
                            contractGridViewAdapter.setContractList(contractList);
                            gvContract.setAdapter(contractGridViewAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ShopHelper.showApiError(GoodsListFragmentManager.this, json);
                }
            }
        });
    }

    /**
     * 设置选择按钮状态
     *
     * @param btn
     */
    private void setSelectBtnState(final Button btn) {
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn.isActivated()) {
                    btn.setActivated(false);
                } else {
                    btn.setActivated(true);
                }
            }
        });
    }

    /**
     * 加载商品列表
     */
    public void loadGoods() {
        String url = Constants.URL_GOODSLIST;

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
            url = url + "&order=" + order;
        }
        url += "&gift=" + gift;
        url += "&groupbuy=" + groupbuy;
        url += "&xianshi=" + xianshi;
        url += "&own_shop=" + own_shop;
        url += "&price_from=" + price_from;
        url += "&price_to=" + price_to;
        url += "&area_id=" + area_id;
        url += "&ci=" + ci;

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
}
