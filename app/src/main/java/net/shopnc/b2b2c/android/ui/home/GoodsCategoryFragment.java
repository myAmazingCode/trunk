package net.shopnc.b2b2c.android.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.BrandGridViewAdapter;
import net.shopnc.b2b2c.android.adapter.GoodsCategoryAdapter;
import net.shopnc.b2b2c.android.bean.Brand;
import net.shopnc.b2b2c.android.bean.GoodCategory;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.ui.good.SearchGoodActivity;
import net.shopnc.b2b2c.android.ui.promotion.CategoryHelper;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;


/**
 * 商品分类页
 *
 * @author huting
 * @date 2016/4/7
 */
public class GoodsCategoryFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.tvSearch)
    TextView tvSearch;
//    @Bind(R.id.btnCamera)
//    Button btnCamera;
//    @Bind(R.id.rlIm)
//    Button rlIm;

    @Bind(R.id.llGoodsClassRoot)
    LinearLayout llGoodsClassRoot;
    @Bind(R.id.svGoodsClassRoot)
    ScrollView svGoodsClassRoot;
    @Bind(R.id.gvBrand)
    GridView gvBrand;
    @Bind(R.id.llGoodsClass)
    LinearLayout llGoodsClass;
    @Bind(R.id.svGoodsClass)
    ScrollView svGoodsClass;

    private List<GoodCategory> goodsCategoryList_one = new ArrayList<>();    //存储商品的一级分类
    private List<GoodCategory> goodsCategoryList_two = new ArrayList<>();    //存储商品的2级分类
    private List<GoodCategory> goodsCategoryList_three = new ArrayList<>();    //存储商品的3级分类

    private AddViewHolder currentGoodsClassView;
    private int currentItem = 0;
    private int mPromotion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewLayout = inflater.inflate(R.layout.main_one_type_view, container, false);
        ButterKnife.bind(this, viewLayout);
        Log.d(TAG, "onCreateView: ");

        setOnClick();
        loadGoodsCategory();
        loadBrandList();
        getKeyWords();
        return viewLayout;
    }


    public static final String TAG = "GoodsCategory";

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("promotion", MODE_PRIVATE);
        mPromotion = sharedPreferences.getInt("promotion", 0);
        Log.d(TAG, "onResume: mPromotion = " + mPromotion);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setOnClick() {
        tvSearch.setOnClickListener(this);
//        btnCamera.setOnClickListener(this);
//        rlIm.setOnClickListener(this);
    }

    private String keyWord;
    private String showWord;

    private void getKeyWords() {
        OkHttpUtil.getAsyn(getActivity(), ConstantUrl.URL_SEARCH_HOT, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                showWord = JsonUtil.toString(data, "keywordName");
                keyWord = JsonUtil.toString(data, "keywordValue");
                tvSearch.setHint(showWord);
            }
        });
    }

    /**
     * 获取商品分类
     */
    private void loadGoodsCategory() {
        OkHttpUtil.getAsyn(getActivity(), ConstantUrl.URL_GOODS_CATEGORY, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<GoodCategory> goodsCategoryList = JsonUtil.toBean(data, "categoryList", new TypeToken<List<GoodCategory>>() {
                }.getType());

                //一级分类
                for (int i = 0; i < goodsCategoryList.size(); i++) {
                    goodsCategoryList_one.add(goodsCategoryList.get(i));
                }

                //二级
                for (int k = 0; k < goodsCategoryList_one.size(); k++) {
                    List<GoodCategory> goodsCategories = goodsCategoryList_one.get(k).getCategoryList();
                    for (int m = 0; m < goodsCategories.size(); m++) {
                        goodsCategoryList_two.add(goodsCategories.get(m));
                    }
                }

                //三级
                for (int j = 0; j < goodsCategoryList_two.size(); j++) {
                    List<GoodCategory> goodsCategoryList1 = goodsCategoryList_two.get(j).getCategoryList();
                    for (int g = 0; g < goodsCategoryList1.size(); g++) {
                        goodsCategoryList_three.add(goodsCategoryList1.get(g));
                    }
                }

                goodsCategoryList_one.add(0, new GoodCategory(0, "品牌推荐", ConstantUrl.URL_CATEGORY_ICON_DEFAULT));

                for (int m = 0; m < goodsCategoryList_one.size(); m++) {
//                    if (m == 0) {
//                        isClick.put(m, true);
//                    } else {
//                        isClick.put(m, false);
//                    }
                    addGoodsClass(goodsCategoryList_one.get(m), m);
                }
            }
        });
    }

    /**
     * 添加分类节点
     */
    private void addGoodsClass(final GoodCategory classItem, final int m) {

        final AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.listivew_type_item);


        holder.setText(R.id.tvGoodsClassId, String.valueOf(classItem.getCategoryId()))
                .setText(R.id.tvGoodsClassName, classItem.getCategoryName())
                .setImageGrey(R.id.ivGoodsClassImage, classItem.getAppImageUrl());
        //初始状态,默认为第一个高亮显示
        if (m == 0) {
            setCurrentGoodsClass(holder, classItem.getAppImageUrl());
            currentGoodsClassView = holder;
            currentItem = m;
        }

        llGoodsClassRoot.addView(holder.getCustomView());
        setItemClick(holder, classItem, m);
    }

    private void setItemClick(final AddViewHolder holder, final GoodCategory classItem, final int m) {
        holder.setOnClickListener(R.id.llView, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m == currentItem) {  //重复点击无状态改变
                    return;
                }

                setCurrentGoodsClass(holder, classItem.getAppImageUrl());
                resetCurrentGoodsClass(currentGoodsClassView, goodsCategoryList_one.get(currentItem).getAppImageUrl());


                currentItem = m;
                currentGoodsClassView = holder;
                svGoodsClassRoot.smoothScrollTo(0, view.getTop());

                String goodsClassId = holder.getText(R.id.tvGoodsClassId);
                if (goodsClassId.equals("0")) {
                    gvBrand.setVisibility(View.VISIBLE);
                    svGoodsClass.setVisibility(View.GONE);
                } else {
                    showGoodsClass(goodsClassId);
                }
            }
        });
    }

    /**
     * 高亮显示选中分类，红色
     */
    private void setCurrentGoodsClass(AddViewHolder holder, String url) {//, Bitmap b
        LinearLayout llView = holder.getCustomView().findViewById(R.id.llView);
        llView.setBackgroundColor(getContext().getResources().getColor(R.color.nc_white));
        holder.setTextColor(R.id.tvGoodsClassName, R.color.nc_btn_bg)
                .setBackgroundColor(R.id.tvLine, R.color.nc_btn_bg)
                .setDimensionPixelSize(R.id.tvLine, R.dimen.dimen_2)
                .setImageRed(R.id.ivGoodsClassImage, url);
//                .setColorFilter(R.id.ivGoodsClassImage, R.color.nc_btn_bg, PorterDuff.Mode.MULTIPLY);
//        holder.getCustomView().invalidate();
    }

    /**
     * 重置一级分类选中状态,灰色
     */
    private void resetCurrentGoodsClass(AddViewHolder holder, String url) {
        LinearLayout llView = holder.getCustomView().findViewById(R.id.llView);
        llView.setBackgroundColor(getContext().getResources().getColor(R.color.nc_bg));
        holder.setTextColor(R.id.tvGoodsClassName, R.color.nc_text)//TODO
                .setBackgroundColor(R.id.tvLine, R.color.nc_border)
                .setDimensionPixelSize(R.id.tvLine, R.dimen.dimen_1)
                .setImageGrey(R.id.ivGoodsClassImage, url);
//                .setColorFilter(R.id.ivGoodsClassImage, R.color.nc_text, PorterDuff.Mode.MULTIPLY);
//        holder.getCustomView().invalidate();
    }

    /**
     * 显示品牌推荐
     */
    private void loadBrandList() {
        OkHttpUtil.getAsyn(getActivity(), ConstantUrl.URL_BRAND_RECOMMEND, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                List<Brand> brandList = JsonUtil.toBean(data, "brandList", new TypeToken<List<Brand>>() {
                }.getType());

                BrandGridViewAdapter adapter = new BrandGridViewAdapter(getActivity());
                gvBrand.setAdapter(adapter);
                adapter.setmDatas(brandList);
            }
        });
    }

    /**
     * 显示下级分类
     */
    private void showGoodsClass(String classId) {
        //隐藏品牌列表
        gvBrand.setVisibility(View.GONE);
        svGoodsClass.setVisibility(View.VISIBLE);
        svGoodsClass.scrollTo(0, 0);

        llGoodsClass.removeAllViews();

        //显示二级
        for (int i = 0; i < goodsCategoryList_two.size(); i++) {
            if (goodsCategoryList_two.get(i).getParentId() == Integer.valueOf(classId)) {
                showRightView(goodsCategoryList_two.get(i), i);
            }
        }
    }


    /**
     * 显示二级分类的右边视图
     */
    private void showRightView(final GoodCategory goodsCategory, int position) {
        final AddViewHolder holder = new AddViewHolder(getActivity(), R.layout.item_goods_class);
        GridView gvGoodsClass = holder.getCustomView().findViewById(R.id.gvGoodsClass);

        if (position == 0) {
            holder.setVisible(R.id.tvLine, false);
        }

        int index = position % 10;
        switch (index) {
            case 0:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot0);
                break;
            case 1:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot1);
                break;
            case 2:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot2);
                break;
            case 3:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot3);
                break;
            case 4:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot4);
                break;
            case 5:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot5);
                break;
            case 6:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot6);
                break;
            case 7:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot7);
                break;
            case 8:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot8);
                break;
            case 9:
                holder.setImageDrawable(R.id.tvGoodsClassDot, R.drawable.nc_sharp_dot9);
                break;
        }

        holder.setText(R.id.tvGoodsClassName, goodsCategory.getCategoryName());
        holder.setOnClickListener(R.id.rlGoodClass, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: cat = " + goodsCategory.getCategoryId());
                CategoryHelper.jump(getActivity(), goodsCategory.getCategoryId(), false);
            }
        });

        //显示三级
        String id = String.valueOf(goodsCategory.getCategoryId());
        List<GoodCategory> categories = new ArrayList<>();
        List<List<GoodCategory>> goodCatrList = new ArrayList<>();
        for (int j = 0; j < goodsCategoryList_three.size(); j++) {
            if (goodsCategoryList_three.get(j).getParentId() == Integer.valueOf(id)) {
                categories.add(goodsCategoryList_three.get(j));
            }
        }
        if (!categories.isEmpty()) {
            goodCatrList.add(categories);
        }
        for (int k = 0; k < goodCatrList.size(); k++) {
            GoodsCategoryAdapter adapter = new GoodsCategoryAdapter(getActivity());
            adapter.setmDatas(goodCatrList.get(k));
            gvGoodsClass.setAdapter(adapter);
        }

        llGoodsClass.addView(holder.getCustomView());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                Intent i = new Intent(getActivity(), SearchGoodActivity.class);
                i.putExtra("keyword", keyWord);
                i.putExtra("showWord", showWord);
                startActivity(i);
                break;
//
//            case R.id.btnCamera:
//                TToast.showShort(getActivity(), "二维码扫描");
//                break;
//
//            case R.id.rlIm:
//                TToast.showShort(getActivity(), "IM聊天");
//                break;
        }
    }
}