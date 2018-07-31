package net.shopnc.b2b2c.android.ui.type;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.StoreAddressBean;
import net.shopnc.b2b2c.android.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;

public class StoreBranchActivity extends BaseActivity {

    @Bind(R.id.recycler_branch)
    RecyclerView recyclerBranch;
    @Bind(R.id.tv_branch_count)
    TextView tvBranchCount;
    @Bind(R.id.btn_all_branch)
    Button btnAllBranch;
    private String[] areaList;
    private int selectIndex = 0;
    private List<StoreAddressBean.AddrListBean> addrListBeen;
    private BranchAdapter adapter;
    private StoreAddressBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("商家门店信息");
        bean = getIntent().getParcelableExtra("addressBean");
        final Set<String> area = new HashSet<>(); //使用set去除重复
        addrListBeen = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerBranch.setLayoutManager(manager);

        if (bean != null) {
            addrListBeen.addAll(bean.getAddr_list());
            adapter = new BranchAdapter(this, R.layout.adapter_store_branch_address, addrListBeen);
            recyclerBranch.setAdapter(adapter);
            for (int i = 0; i < bean.getAddr_list().size(); i++) {
                area.add(bean.getAddr_list().get(i).getArea_name());
            }
            int i = 1;
            areaList = new String[area.size() + 1];
            areaList[0] = "所有城区";
            for (String s : area) {
                areaList[i++] = s;
            }

        }
        tvBranchCount.setText("全部门店共" + bean.getAddr_list().size() + "家");
        btnAllBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择城区弹窗
                showAreaWindow(areaList);

            }
        });


    }

    //选择城区弹窗
    public void showAreaWindow(final String[] datas) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(StoreBranchActivity.this);
        View view = LayoutInflater.from(StoreBranchActivity.this).inflate(R.layout.dialog_select_store_area, null);
        dialog.setView(view);
        dialog.setSingleChoiceItems(datas, selectIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectIndex = which;
            }
        });
        final AlertDialog alertDialog = dialog.create();
        Button btnPositive = view.findViewById(R.id.btn_positive);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //筛选
                addrListBeen.clear();
                if (selectIndex == 0) {
                    addrListBeen.addAll(bean.getAddr_list());
                } else {
                    for (int i=0;i<bean.getAddr_list().size();i++) {
                        if (TextUtils.equals(bean.getAddr_list().get(i).getArea_name(),datas[selectIndex])) {
                            addrListBeen.add(bean.getAddr_list().get(i));
                        }
                    }
                }
                btnAllBranch.setText(datas[selectIndex]);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialog.show();

//        final AlertDialog dialog = new AlertDialog.Builder(StoreBranchActivity.this).create();
//        dialog.show();
//        View window = LayoutInflater.from(StoreBranchActivity.this).inflate(R.layout.dialog_select_store_area, null);
//        dialog.getWindow().setContentView(window);
//
//        RecyclerView areaRecycler = (RecyclerView) window.findViewById(R.id.area_recyclerview);
//        Button btnPositive = (Button) window.findViewById(R.id.btn_positive);
//        Button btnCancel = (Button) window.findViewById(R.id.btn_cancel);
////        for (int i=0; i<items.length;i++) {
////            RadioButton button = new RadioButton(this);
////            button.setText(items[i]);
////            button.setTextColor(ContextCompat.getColor(this, R.color.nc_text));
////            button.setTextSize(ConvertUtils.px2dip(this, 48));
////            button.setHeight(ConvertUtils.dip2px(this, 50));
////
////        }
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        areaRecycler.setLayoutManager(manager);
//
//        areaRecycler.setAdapter(new DialogAreaAdapter(this,R.layout.adapter_dialog_area,datas));
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_store_branch);
    }

    public class BranchAdapter extends CommonAdapter<StoreAddressBean.AddrListBean> {
        public BranchAdapter(Context context, int layoutId, List<StoreAddressBean.AddrListBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, StoreAddressBean.AddrListBean addrListBean, int position) {
            holder.setText(R.id.tv_store_name, addrListBean.getChain_name());
            holder.setText(R.id.tv_store_price, "门店价格：¥" + addrListBean.getShopnc_chain_price());
            holder.setText(R.id.tv_store_address, "门店地址：" + addrListBean.getChain_address() + "，电话：" +
                    addrListBean.getChain_phone());
        }
    }

    public class DialogAreaAdapter extends CommonAdapter<String> {
        public DialogAreaAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, String string, int position) {
            holder.setText(R.id.radio_default, string);
        }
    }


}
