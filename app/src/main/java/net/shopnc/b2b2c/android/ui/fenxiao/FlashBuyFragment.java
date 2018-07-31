package net.shopnc.b2b2c.android.ui.fenxiao;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FlashBean;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.JsonUtil;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.utils.SpaceItemTopDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashBuyFragment extends Fragment {


    @Bind(R.id.flash_recycler)
    RecyclerView flashRecycler;

    public FlashBuyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flash_buy, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        String state = bundle.getString("state");
        initData(state);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    //获取闪购信息
    public void initData(String state) {
        RemoteDataHandler.asyncDataStringGet(Constants.URL_FLASH_LIST+"&state="+state, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String info = data.getJson();
                final FlashBean bean = JsonUtil.getBean(info, FlashBean.class);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                flashRecycler.setLayoutManager(manager);
                FlashItemAdapter adapter = new FlashItemAdapter(getActivity(), R.layout.flash_layout_recycler, bean.getFlash_list());
                flashRecycler.setAdapter(adapter);
                SpaceItemTopDecoration topDecoration = new SpaceItemTopDecoration(30);
                flashRecycler.addItemDecoration(topDecoration);
            }
        });
    }
    public class FlashItemAdapter extends CommonAdapter<FlashBean.FlashListBean> {


        public FlashItemAdapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, final FlashBean.FlashListBean flashListBean, int position) {
            holder.setText(R.id.tv_flash_name, mDatas.get(position).getFlash_name());
            holder.setText(R.id.tv_left_days, "剩余" + String.valueOf(mDatas.get(position).getExp_day()) + "天");
            if (mDatas.get(position).getFlash_explain().length() != 0) {
                holder.setText(R.id.tv_flash_explain, mDatas.get(position).getFlash_explain());
            } else {
                holder.setVisible(R.id.tv_flash_explain, false);
            }

            ImageView ivBrand = holder.getView(R.id.iv_brand);
            Glide.with(getActivity())
                    .load(mDatas.get(position).getFlash_brand_url())
                    .into(ivBrand);
            ImageView ivFlashBg = holder.getView(R.id.iv_flash_pict);
            Glide.with(getActivity())
                    .load(mDatas.get(position).getFlash_pic_url())
                    .into(ivFlashBg);
            holder.setOnClickListener(R.id.layout_item, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),FlashBuyDetailActivity.class);
                    intent.putExtra("flash_id", flashListBean.getFlash_id());
                    startActivity(intent);
                }
            });
        }
    }
}
