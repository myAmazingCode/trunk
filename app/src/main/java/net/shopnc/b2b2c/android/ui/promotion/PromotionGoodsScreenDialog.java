package net.shopnc.b2b2c.android.ui.promotion;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.PromotionSearch;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.LinkedMultiValueMap;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.xrefresh.XScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 促销商品筛选
 */

public class PromotionGoodsScreenDialog extends Dialog {

    @Bind(R.id.btnBack)
    ImageView mBtnBack;
    @Bind(R.id.tvCommonTitle)
    TextView mTvCommonTitle;
    @Bind(R.id.btnMore)
    ImageButton mBtnMore;
    @Bind(R.id.btnClear)
    TextView mBtnClear;
    @Bind(R.id.btnReset)
    TextView mBtnRevert;
    @Bind(R.id.etPriceFrom)
    EditText mEtPriceFrom;
    @Bind(R.id.etPriceTo)
    EditText mEtPriceTo;
    @Bind(R.id.etNumBatch)
    EditText mEtNumBatch;
    @Bind(R.id.etRateFrom)
    EditText mEtRateFrom;
    @Bind(R.id.etRateTo)
    EditText mEtRateTo;
    @Bind(R.id.rbDeliveryNo)
    RadioButton mRbDeliveryNo;
    @Bind(R.id.rbDelivery)
    RadioButton mRbDelivery;
    @Bind(R.id.rbVoucherNo)
    RadioButton mRbVoucherNo;
    @Bind(R.id.rbVoucher)
    RadioButton mRbVoucher;
    @Bind(R.id.btnStoreDefault)
    RadioButton mBtnStoreDefault;
    @Bind(R.id.btnOwnShop)
    RadioButton mBtnOwnShop;
    @Bind(R.id.btnOtherShop)
    RadioButton mBtnOtherShop;
    @Bind(R.id.btnGiftDefault)
    RadioButton mBtnGiftDefault;
    @Bind(R.id.btnGift)
    RadioButton mBtnGift;
    @Bind(R.id.llCategory)
    LinearLayout mLlCategory;
    @Bind(R.id.llBrand)
    LinearLayout mLlBrand;
    @Bind(R.id.llSort)
    LinearLayout mLlSort;
    @Bind(R.id.scrollView)
    XScrollView mScrollView;
    @Bind(R.id.btnSelectSave)
    Button mBtnSelectSave;
    private Context context;
    private String cat;
    public static final String TAG = "PromotionScreenDialog";
    private List<PromotionSearch.DatasBean.FilterBean.BrandListBean> selectedBrandList;
    private LinkedMultiValueMap<String, String> selectAttributeList;

    @Bind(R.id.btnXianshi)
    RadioButton btnXianshi;
    @Bind(R.id.btnPreShow)
    RadioButton btnPreShow;
    @Bind(R.id.btnPayPresell)
    RadioButton mBtnPayPresell;

    public PromotionGoodsScreenDialog(Context context) {
        super(context, R.style.CommonDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion_goods_screen_dialog);
        ButterKnife.bind(this);
        getWindow().setLayout(ScreenUtil.getScreenSize(context).x / 5 * 4, LinearLayout.LayoutParams.MATCH_PARENT);
        getWindow().setWindowAnimations(R.style.AnimationFade);
        getWindow().setGravity(Gravity.RIGHT);
        mTvCommonTitle.setText("商品筛选");
        selectedBrandList = new ArrayList<>();
        selectAttributeList = new LinkedMultiValueMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        showCategory();
        setSortShow();
    }


    private List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> mCategoryList;
    private List<PromotionSearch.DatasBean.FilterBean.BrandListBean> mBrandList;
    private List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> mAttributeList;

    public void setAttributeList(List<PromotionSearch.DatasBean.FilterBean.AttributeListBean> attributeList) {
        mAttributeList = attributeList;
    }

    public void setBrandList(List<PromotionSearch.DatasBean.FilterBean.BrandListBean> brandList) {
        mBrandList = brandList;
    }

    public void setCategoryList(List<PromotionSearch.DatasBean.FilterBean.CategoryListBean> categoryList) {
        mCategoryList = categoryList;
    }

    private void setSortShow() {
        showCategory();
        setBrandShow();
        setAttributeShow();
    }

    private void setAttributeShow() {
        if (mAttributeList != null) {
            mLlSort.removeAllViews();
            for (final PromotionSearch.DatasBean.FilterBean.AttributeListBean attribute : mAttributeList) {
                final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
                addViewHolder.setText(R.id.tvSortName, attribute.getAttributeName());
                RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
                rvSort.setLayoutManager(new GridLayoutManager(context, 3));
                final RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean> attrAdapter
                        = new RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean>(context, R.layout.select_dialog_sort_recycler_item) {
                    @Override
                    public void convert(final RecyclerHolder holder, final PromotionSearch.DatasBean.FilterBean.AttributeListBean.AttributeValueListBean o) {
                        final TextView btnSpec = holder.getView(R.id.btnSpec);
                        btnSpec.setText(o.getAttributeValueName());
                        btnSpec.setActivated(selectAttributeList.containsValue(attribute.getAttributeId(), o.getAttributeValueName()));
                        btnSpec.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (btnSpec.isActivated()) {
                                    btnSpec.setActivated(false);
                                    selectAttributeList.remove(attribute.getAttributeId(), o.getAttributeValueId());
                                } else {
                                    if (selectAttributeList.getValues(attribute.getAttributeId()) == null || selectAttributeList.getValues(attribute.getAttributeId()).size() < 5) {
                                        btnSpec.setActivated(true);
                                        selectAttributeList.put(attribute.getAttributeId(), o.getAttributeValueId());
                                    } else {
                                        TToast.showShort(context, "筛选个数不能超过5个！");
                                    }
                                }
                            }
                        });
                    }
                };
                attrAdapter.setDatas(attribute.getAttributeValueList().subList(0, attribute.getAttributeValueList().size() > 3 ? 3 : attribute.getAttributeValueList().size()));
                rvSort.setAdapter(attrAdapter);
                attrAdapter.notifyDataSetChanged();

                addViewHolder.setVisible(R.id.ivMoreFlag, attribute.getAttributeValueList().size() > 3);
                addViewHolder.setSelected(R.id.ivMoreFlag, false);
                addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (attrAdapter.getDatas().size() == 3) {  //展开
                            addViewHolder.setSelected(R.id.ivMoreFlag, true);
                            attrAdapter.setDatas(attribute.getAttributeValueList());
                            attrAdapter.notifyDataSetChanged();
                        } else {  //关闭
                            addViewHolder.setSelected(R.id.ivMoreFlag, false);
                            attrAdapter.setDatas(attribute.getAttributeValueList().subList(0, 3));
                            attrAdapter.notifyDataSetChanged();
                        }
                    }
                });

                mLlSort.addView(addViewHolder.getCustomView());
            }
        }
    }


    private void showCategory() {
        if (mCategoryList != null) {
            mLlCategory.removeAllViews();
            final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
            addViewHolder.setText(R.id.tvSortName, "包含分类");
            RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
            rvSort.setLayoutManager(new GridLayoutManager(context, 3));
            final RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.CategoryListBean> categoryAdapter =
                    new RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.CategoryListBean>(context, R.layout.select_dialog_sort_recycler_item) {

                        @Override
                        public void convert(RecyclerHolder holder, final PromotionSearch.DatasBean.FilterBean.CategoryListBean categoryListBean) {
                            final TextView btnSpec = holder.getView(R.id.btnSpec);
                            btnSpec.setText(categoryListBean.getCategoryName());
                            btnSpec.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!btnSpec.isActivated()) {
                                        btnSpec.setActivated(true);
                                        cat = "&cat=" + categoryListBean.getCategoryId();
                                        Log.d(TAG, "onClick: cat = " + cat);
                                        btnSelectSaveClick();
                                    }
                                }
                            });
                        }
                    };
            categoryAdapter.setDatas(mCategoryList.subList(0, mCategoryList.size() > 3 ? 3 : mCategoryList.size()));
            rvSort.setAdapter(categoryAdapter);
            categoryAdapter.notifyDataSetChanged();

            addViewHolder.setVisible(R.id.ivMoreFlag, mCategoryList.size() > 3);
            addViewHolder.setSelected(R.id.ivMoreFlag, false);
            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryAdapter.getDatas().size() == 3) {  //展开
                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
                        categoryAdapter.setDatas(mCategoryList);
                        categoryAdapter.notifyDataSetChanged();
                    } else {  //点击关闭
                        addViewHolder.setSelected(R.id.ivMoreFlag, false);
                        categoryAdapter.setDatas(mCategoryList.subList(0, 3));
                        categoryAdapter.notifyDataSetChanged();
                    }
                }
            });

            mLlCategory.addView(addViewHolder.getCustomView());
        } else {
            mLlCategory.removeAllViews();
        }
    }

    private void setBrandShow() {
        if (mBrandList != null && mBrandList.size() > 0) {
            mLlBrand.removeAllViews();
            final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
            addViewHolder.setText(R.id.tvSortName, "品牌");
            RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
            rvSort.setLayoutManager(new GridLayoutManager(context, 3));
            final RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.BrandListBean> categoryRRecyclerAdapter = new RRecyclerAdapter<PromotionSearch.DatasBean.FilterBean.BrandListBean>(context, R.layout.select_dialog_sort_recycler_item) {
                @Override
                public void convert(final RecyclerHolder holder, final PromotionSearch.DatasBean.FilterBean.BrandListBean o) {
                    final TextView btnSpec = holder.getView(R.id.btnSpec);
                    btnSpec.setText(o.getBrandName());
                    btnSpec.setActivated(selectedBrandList.contains(o));
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (btnSpec.isActivated()) {
                                btnSpec.setActivated(false);
                                selectedBrandList.remove(o);
                            } else {
                                if (selectedBrandList.size() < 5) {
                                    btnSpec.setActivated(true);
                                    selectedBrandList.add(o);
                                } else {
                                    TToast.showShort(context, "筛选个数不能超过5个！");
                                }
                            }
                        }
                    });
                }
            };
            categoryRRecyclerAdapter.setDatas(mBrandList.subList(0, mBrandList.size() > 3 ? 3 : mBrandList.size()));
            rvSort.setAdapter(categoryRRecyclerAdapter);
            categoryRRecyclerAdapter.notifyDataSetChanged();

            addViewHolder.setVisible(R.id.ivMoreFlag, mBrandList.size() > 3);
            addViewHolder.setSelected(R.id.ivMoreFlag, false);
            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryRRecyclerAdapter.getDatas().size() == 3) {  //展开
                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
                        categoryRRecyclerAdapter.setDatas(mBrandList);
                        categoryRRecyclerAdapter.notifyDataSetChanged();
                    } else {  //点击关闭
                        addViewHolder.setSelected(R.id.ivMoreFlag, false);
                        categoryRRecyclerAdapter.setDatas(mBrandList.subList(0, 3));
                        categoryRRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            });

            mLlBrand.addView(addViewHolder.getCustomView());
        }
    }

    @OnClick(R.id.btnSelectSave)
    public void btnSelectSaveClick() {
        String selectValue = "";
        //价格区间
        if (Common.isNotEmpty(Common.getText(mEtPriceFrom), Common.getText(mEtPriceTo))) {
            selectValue = selectValue + "&price=" + Common.getText(mEtPriceFrom) + "-" + Common.getText(mEtPriceTo);
        }

        //销量
        if (Common.isNotEmpty(Common.getText(mEtPriceFrom))) {
            selectValue = selectValue + "&sellNum=" + Common.getText(mEtNumBatch);
        }

        //收入比率
        if (Common.isNotEmpty(Common.getText(mEtRateFrom), Common.getText(mEtRateTo))) {
            selectValue = selectValue + "&commission=" + Common.getText(mEtRateFrom) + "-" + Common.getText(mEtRateTo);
        }

        selectValue = selectValue + "&express=" + (mRbDeliveryNo.isChecked() ? "0" : "1");//包邮
        selectValue = selectValue + "&voucher=" + (mRbVoucherNo.isChecked() ? "0" : "1");//优惠券

        //经营类型
        selectValue = selectValue + (mBtnOwnShop.isChecked() ? "&own=1" : "");
        selectValue = selectValue + (mBtnOtherShop.isChecked() ? "&own=0" : "");
        selectValue = selectValue + (mBtnGift.isChecked() ? "&gift=1" : "");//赠品
        if (!TextUtils.isEmpty(cat)) {
            selectValue = selectValue + cat;
        }
        Log.d(TAG, "btnSelectSaveClick: cat = "+cat);
        selectValue = selectValue + getBrandString();
        selectValue = selectValue + getAttributeValue();

        //促销活动
        if (btnXianshi.isChecked()) {
            selectValue += "&promotion=1";
        } else if (btnPreShow.isChecked()) {
            selectValue += "&promotion=2";
        } else if (mBtnPayPresell.isChecked()) {
            selectValue += "&promotion=3";
        } else {
            selectValue += "&promotion=0";
        }

        Log.d(TAG, "btnSelectSaveClick: selectValue = "+selectValue);

        if (mOnConditionConfirmedListener != null) {
            mOnConditionConfirmedListener.onConditionConfirmed(selectValue);
        }
        dismiss();
    }

    private String getBrandString() {
        String brandString = "&brand=";
        String value = "";
        if (selectedBrandList.size() > 0) {
            for (PromotionSearch.DatasBean.FilterBean.BrandListBean brand : selectedBrandList) {
                value = value + brand.getBrandId() + ",";
            }
            value = value.substring(0, value.length() - 1);
        }

        return value.length() > 0 ? brandString + value : "";
    }

    private String getAttributeValue() {
        String attributeString = "&attr=";
        String value = "";
        for (String i : selectAttributeList.keySet()) {
            value = value + i + "-";
            for (String v : selectAttributeList.getValues(i)) {
                value = value + v + ":";
            }
            if (selectAttributeList.getValues(i).size() > 0) {
                value = value.substring(0, value.length() - 1);
            }
            value = value + ",";
        }
        if (value.length() > 0) {
            value = value.substring(0, value.length() - 1);
            return attributeString + value;
        } else {
            return value;
        }
    }

    private OnConditionConfirmedListener mOnConditionConfirmedListener;

    public void setOnConditionConfirmedListener(OnConditionConfirmedListener onConditionConfirmedListener) {
        mOnConditionConfirmedListener = onConditionConfirmedListener;
    }

    public interface OnConditionConfirmedListener {
        void onConditionConfirmed(String val);
    }


    @OnClick(R.id.btnReset)
    public void btnRevertClick() {
        mEtPriceFrom.setText("");
        mEtPriceTo.setText("");
        mEtNumBatch.setText("");
        mEtRateFrom.setText("");
        mEtRateTo.setText("");

        mRbDeliveryNo.setChecked(true);
        mRbVoucherNo.setChecked(true);
        mBtnGiftDefault.setChecked(true);
        mBtnStoreDefault.setChecked(true);

        btnXianshi.setChecked(false);
        btnPreShow.setChecked(false);
        mBtnPayPresell.setChecked(false);

        setSortShow();
    }

    @OnClick(R.id.btnBack)
    public void onBackClick() {
        dismiss();
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        ButterKnife.unbind(this);
    }
}
