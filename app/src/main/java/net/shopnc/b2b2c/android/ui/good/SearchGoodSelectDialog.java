package net.shopnc.b2b2c.android.ui.good;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.bean.Attribute;
import net.shopnc.b2b2c.android.bean.AttributeValue;
import net.shopnc.b2b2c.android.bean.Brand;
import net.shopnc.b2b2c.android.bean.GoodCategory;
import net.shopnc.b2b2c.android.bean.SelectFilter;
import net.shopnc.b2b2c.android.bean.SortString;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.Common;
import net.shopnc.b2b2c.android.common.LinkedMultiValueMap;
import net.shopnc.b2b2c.android.common.ScreenUtil;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.xrefresh.XScrollView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description XXX
 * @Author qyf
 * <p>
 * Created 2016/8/31 13:41.
 */
public class SearchGoodSelectDialog extends Dialog {

    @Bind(R.id.scrollView)
    XScrollView scrollView;
    @Bind(R.id.btnBack)
    ImageView btnBack;
    @Bind(R.id.tvCommonTitle)
    TextView tvCommonTitle;
    @Bind(R.id.btnMore)
    ImageButton btnMore;
    @Bind(R.id.btnClear)
    TextView btnClear;
    @Bind(R.id.btnRevert)
    TextView btnRevert;
    @Bind(R.id.etPriceFrom)
    EditText etPriceFrom;
    @Bind(R.id.etPriceTo)
    EditText etPriceTo;
    @Bind(R.id.etNumBatch)
    EditText etNumBatch;
//        @Bind(R.id.btnSaleDefault)
//    RadioButton btnSaleDefault;
    @Bind(R.id.btnXianshi)
    RadioButton btnXianshi;
    @Bind(R.id.btnPreShow)
    RadioButton btnPreShow;
    @Bind(R.id.btnPayPresell)
    RadioButton mBtnPayPresell;

    @Bind(R.id.btnStoreDefault)
    RadioButton btnStoreDefault;
    @Bind(R.id.btnOwnShop)
    RadioButton btnOwnShop;
    @Bind(R.id.btnOtherShop)
    RadioButton btnOtherShop;
    @Bind(R.id.btnGiftDefault)
    RadioButton btnGiftDefault;
    @Bind(R.id.btnGift)
    RadioButton btnGift;
    @Bind(R.id.llSort)
    LinearLayout llSort;
    @Bind(R.id.llBrand)
    LinearLayout llBrand;
    @Bind(R.id.llCategory)
    LinearLayout llCategory;
    @Bind(R.id.btnSelectSave)
    Button btnSelectSave;

    //新增包邮、优惠券、定金预售等
    @Bind(R.id.btnDeliveryDefault)
    RadioButton mBtnDeliveryDefault;
    @Bind(R.id.rbDeliveryFree)
    RadioButton mRbDeliveryFree;
    @Bind(R.id.btnVoucherDefault)
    RadioButton mBtnVoucherDefault;
    @Bind(R.id.rbStoreVouchers)
    RadioButton mRbStoreVouchers;


    private Context context;
    private SelectFilter filter;
    private String cat = "";
    private List<Brand> selectedBrandList;
    private LinkedMultiValueMap<Integer, Integer> selectAttributeList;

    public SearchGoodSelectDialog(Context context) {
        super(context, R.style.CommonDialog);
        this.context = context;
    }

    public void setFilter(SelectFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_good_select);
        ButterKnife.bind(this);
        getWindow().setLayout(ScreenUtil.getScreenSize(context).x / 5 * 4, LinearLayout.LayoutParams.MATCH_PARENT);
        getWindow().setWindowAnimations(R.style.AnimationFade);
        getWindow().setGravity(Gravity.RIGHT);
        tvCommonTitle.setText("筛选");
        setFormatListener();
        selectedBrandList = new ArrayList<>();
        selectAttributeList = new LinkedMultiValueMap<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSortShow();
        scrollView.smoothScrollTo(0, 0);
    }

    private void setSortShow() {
        setCategoryShow();
        setBrandShow();
        setAttributeShow();
    }

    private void setCategoryShow() {
        if (filter.getCategoryList() != null) {
            llCategory.removeAllViews();
            final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
            addViewHolder.setText(R.id.tvSortName, "包含分类");
            RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
            rvSort.setLayoutManager(new GridLayoutManager(context, 3));
            final RRecyclerAdapter<GoodCategory> categoryAdapter = new RRecyclerAdapter<GoodCategory>(context, R.layout.select_dialog_sort_recycler_item) {
                @Override
                public void convert(final RecyclerHolder holder, final GoodCategory o) {
                    final TextView btnSpec = holder.getView(R.id.btnSpec);
                    btnSpec.setText(o.getCategoryName());
                    btnSpec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!btnSpec.isActivated()) {
                                btnSpec.setActivated(true);
                                cat = "&cat=" + o.getCategoryId();
                                btnSelectSaveClick();
                            }
                        }
                    });
                }
            };
            categoryAdapter.setDatas(filter.getCategoryList().subList(0, filter.getCategoryList().size() > 3 ? 3 : filter.getCategoryList().size()));
            rvSort.setAdapter(categoryAdapter);
            categoryAdapter.notifyDataSetChanged();

            addViewHolder.setVisible(R.id.ivMoreFlag, filter.getCategoryList().size() > 3);
            addViewHolder.setSelected(R.id.ivMoreFlag, false);
            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryAdapter.getDatas().size() == 3) {  //展开
                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
                        categoryAdapter.setDatas(filter.getCategoryList());
                        categoryAdapter.notifyDataSetChanged();
                    } else {  //点击关闭
                        addViewHolder.setSelected(R.id.ivMoreFlag, false);
                        categoryAdapter.setDatas(filter.getCategoryList().subList(0, 3));
                        categoryAdapter.notifyDataSetChanged();
                    }
                }
            });

            llCategory.addView(addViewHolder.getCustomView());
        } else {
            llCategory.removeAllViews();
        }
    }

    private void setBrandShow() {
        if (filter.getBrandList() != null && filter.getBrandList().size() > 0) {
            llBrand.removeAllViews();
            final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
            addViewHolder.setText(R.id.tvSortName, "品牌");
            RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
            rvSort.setLayoutManager(new GridLayoutManager(context, 3));
            final RRecyclerAdapter<Brand> categoryRRecyclerAdapter = new RRecyclerAdapter<Brand>(context, R.layout.select_dialog_sort_recycler_item) {
                @Override
                public void convert(final RecyclerHolder holder, final Brand o) {
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
            categoryRRecyclerAdapter.setDatas(filter.getBrandList().subList(0, filter.getBrandList().size() > 3 ? 3 : filter.getBrandList().size()));
            rvSort.setAdapter(categoryRRecyclerAdapter);
            categoryRRecyclerAdapter.notifyDataSetChanged();

            addViewHolder.setVisible(R.id.ivMoreFlag, filter.getBrandList().size() > 3);
            addViewHolder.setSelected(R.id.ivMoreFlag, false);
            addViewHolder.setOnClickListener(R.id.ivMoreFlag, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryRRecyclerAdapter.getDatas().size() == 3) {  //展开
                        addViewHolder.setSelected(R.id.ivMoreFlag, true);
                        categoryRRecyclerAdapter.setDatas(filter.getBrandList());
                        categoryRRecyclerAdapter.notifyDataSetChanged();
                    } else {  //点击关闭
                        addViewHolder.setSelected(R.id.ivMoreFlag, false);
                        categoryRRecyclerAdapter.setDatas(filter.getBrandList().subList(0, 3));
                        categoryRRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            });

            llBrand.addView(addViewHolder.getCustomView());
        }
    }

    private void setAttributeShow() {
        if (filter.getAttributeList() != null) {
            llSort.removeAllViews();
            for (final Attribute attribute : filter.getAttributeList()) {
                final AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.select_dialog_sort_item);
                addViewHolder.setText(R.id.tvSortName, attribute.getAttributeName());
                RecyclerView rvSort = addViewHolder.getView(R.id.rvSort);
                rvSort.setLayoutManager(new GridLayoutManager(context, 3));
                final RRecyclerAdapter<AttributeValue> attrAdapter = new RRecyclerAdapter<AttributeValue>(context, R.layout.select_dialog_sort_recycler_item) {
                    @Override
                    public void convert(final RecyclerHolder holder, final AttributeValue o) {
                        final TextView btnSpec = holder.getView(R.id.btnSpec);
                        btnSpec.setText(o.getAttributeValueName());
                        btnSpec.setActivated(selectAttributeList.containsValue(attribute.getAttributeId(), o.getAttributeValueId()));
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

                llSort.addView(addViewHolder.getCustomView());
            }
        }
    }

    private void setFormatListener() {
        etPriceFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!Common.isEmptyEditText(etPriceFrom)) {
                    etPriceFrom.setText(ShopHelper.getPriceString(new BigDecimal(etPriceFrom.getText().toString())));
                }
            }
        });
        etPriceTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!Common.isEmptyEditText(etPriceTo)) {
                    etPriceTo.setText(ShopHelper.getPriceString(new BigDecimal(etPriceTo.getText().toString())));
                }
            }
        });
    }

    @OnClick(R.id.btnRevert)
    public void btnRevertClick() {
        etPriceFrom.setText("");
        etPriceTo.setText("");
        etNumBatch.setText("");
//        btnSaleDefault.setChecked(true);
        btnXianshi.setChecked(false);
        btnPreShow.setChecked(false);
        mBtnPayPresell.setChecked(false);

        btnStoreDefault.setChecked(true);
//        btnOwnShop.setChecked(false);
//        btnOtherShop.setChecked(false);

        mBtnVoucherDefault.setChecked(true);
//        mRbStoreVouchers.setChecked(false);
        mBtnDeliveryDefault.setChecked(true);
//        mRbDeliveryFree.setChecked(false);

        btnGiftDefault.setChecked(true);
//        btnGift.setChecked(false);
        selectedBrandList.clear();
        selectAttributeList.clear();
        setSortShow();
    }

    @OnClick(R.id.btnSelectSave)
    public void btnSelectSaveClick() {
        String selectValue = "";
        if (Common.isNotEmpty(Common.getText(etPriceFrom), Common.getText(etPriceTo))) {
            selectValue = selectValue + "&price=" + Common.getText(etPriceFrom) + "-" + Common.getText(etPriceTo);
        }
        //促销活动
//        if (btnSaleDefault.isChecked()) {
//            selectValue += "&promotion=0";
//        }
        if (btnXianshi.isChecked()) {
            selectValue += "&promotion=1";
        } else if (btnPreShow.isChecked()) {
            selectValue += "&promotion=2";
        } else if (mBtnPayPresell.isChecked()) {
            selectValue += "&promotion=3";
        } else {
            selectValue += "&promotion=0";
        }


        selectValue = selectValue + (Common.isEmpty(Common.getText(etNumBatch)) ? "" : "&batch=" + Common.getText(etNumBatch));
        selectValue = selectValue + (btnOwnShop.isChecked() ? "&own=1" : "");
        selectValue = selectValue + (btnOtherShop.isChecked() ? "&own=0" : "");
        selectValue = selectValue + (btnGift.isChecked() ? "&gift=1" : "");
        //包邮
        selectValue = selectValue + (mRbDeliveryFree.isChecked() ? "&express=1" : "");
        //优惠券
        selectValue = selectValue + (mRbStoreVouchers.isChecked() ? "&voucher=1" : "");
        selectValue = selectValue + cat;
        selectValue = selectValue + getBrandString();
        selectValue = selectValue + getAttributeValue();

        Log.d("selectValue", "btnSelectSaveClick: selectValue = "+selectValue);


        EventBus.getDefault().post(new SortString(selectValue));
        dismiss();
    }

    private String getBrandString() {
        String brandString = "&brand=";
        String value = "";
        if (selectedBrandList.size() > 0) {
            for (Brand brand : selectedBrandList) {
                value = value + brand.getBrandId() + ",";
            }
            value = value.substring(0, value.length() - 1);
        }

        return value.length() > 0 ? brandString + value : "";
    }

    private String getAttributeValue() {
        String attributeString = "&attr=";
        String value = "";
        for (Integer i : selectAttributeList.keySet()) {
            value = value + i + "-";
            for (Integer v : selectAttributeList.getValues(i)) {
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

    @OnClick({R.id.btnXianshi, R.id.btnPreShow, R.id.btnOwnShop, /*R.id.btnSaleDefault, R.id.btnStoreDefault,R.id.btnGiftDefault,*/
    R.id.btnOtherShop, R.id.btnGift,R.id.rbDeliveryFree,R.id.rbStoreVouchers,R.id.btnPayPresell,})
    public void onSortClick(View view) {
        switch (view.getId()) {
            /*case R.id.btnSaleDefault:
                btnSaleDefault.setChecked(true);
                break;*/
            case R.id.btnXianshi:
                btnXianshi.setChecked(true);
                break;
            case R.id.btnPreShow:
                btnPreShow.setChecked(true);
                break;
            /*case R.id.btnStoreDefault:
                btnStoreDefault.setChecked(true);
                break;*/
            case R.id.btnOwnShop:
                btnOwnShop.setChecked(true);
                break;
            case R.id.btnOtherShop:
                btnOtherShop.setChecked(true);
                break;
//            case R.id.btnGiftDefault:
//                btnGiftDefault.setChecked(true);
//                break;
            case R.id.btnGift:
                btnGift.setChecked(true);
                break;
            case R.id.rbDeliveryFree:
                mRbDeliveryFree.setChecked(true);
                break;
            case R.id.rbStoreVouchers:
                mRbStoreVouchers.setChecked(true);
                break;
            case R.id.btnPayPresell:
                mBtnPayPresell.setChecked(true);
                break;
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                dismiss();
                break;
        }
    }

    @Override
    public void setOnDismissListener(OnDismissListener listener) {
        super.setOnDismissListener(listener);
        ButterKnife.unbind(this);
    }
}
