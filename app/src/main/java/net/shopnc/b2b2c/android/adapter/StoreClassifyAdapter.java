package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.StoreGoodsClassList;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.ui.store.StoreGoodsListFragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家产品分类适配器
 *
 * @author 胡婷
 * @date 2015/12/1
 */
public class StoreClassifyAdapter extends ListBaseAdapter<StoreGoodsClassList> {

    private String store_id;
    private Intent intent;

    //存储一级分类
    private List<StoreGoodsClassList> categoryList = new ArrayList<StoreGoodsClassList>();

    //存储二级分类
    private List<StoreGoodsClassList> categoryList_lev2 = new ArrayList<StoreGoodsClassList>();

    private StoreGoodsClassList categoryTwo = new StoreGoodsClassList();

    private MyShopApplication myShopApplication;

    public StoreClassifyAdapter(Context ctx) {
        super(ctx);
        this.ctx = ctx;
        myShopApplication = (MyShopApplication) ctx.getApplicationContext();
    }


    @Override
    public void setData(List<StoreGoodsClassList> data) {
        super.setData(data);
        getCategory(data);
    }

    @Override
    public void setStoreId(String id) {
        super.setStoreId(id);
        store_id = id;
    }

    @Override
    public int getCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    @Override
    public StoreGoodsClassList getItem(int position) {
        if (categoryList.size() > position) {
            return categoryList.get(position);
        }
        return null;
    }

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (null == convertView) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.category_item, parent, false);
            vh.textOneName = convertView.findViewById(R.id.textOneName);
            vh.gv_category_item = convertView.findViewById(R.id.gv_category_item);
            vh.textseaAll = convertView.findViewById(R.id.textseaAll);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //一级分类
        final StoreGoodsClassList category = categoryList.get(position);
        vh.textOneName.setText(category.getName());

        getSubCategory(category, mDatas);
        Log.i("aaa", mDatas.size() + "-" + categoryList.size() + "-" + categoryList_lev2.size());

        final int subCategoryCount = categoryList_lev2.size();
        final ViewHolder finalVh = vh;

        //一级分类点击事件
        vh.textOneName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ShopHelper.showMessage(ctx,category.getId() + ":" + category.getName());

                intent = new Intent(ctx,StoreGoodsListFragmentManager.class);
                intent.putExtra("store_id",store_id);
                intent.putExtra("stc_id",category.getId());//分类id
                intent.putExtra("gc_name",category.getName());//分类名
                ctx.startActivity(intent);
            }
        });

        vh.textseaAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* LogHelper.d("huting--storeId1", store_id);
                LogHelper.d("huting--gc_name1",category.getName());
                LogHelper.d("huting--stc_id1",category.getId());*/

                intent = new Intent(ctx,StoreGoodsListFragmentManager.class);
                intent.putExtra("store_id",store_id);
                intent.putExtra("stc_id",category.getId());//分类id
                intent.putExtra("gc_name",category.getName());//分类名
                ctx.startActivity(intent);
            }
        });


        //二级分类
        StoreClassifyItemAdapter categoryItemAdapter = new StoreClassifyItemAdapter(ctx);
        categoryItemAdapter.setData(categoryList_lev2);
        categoryItemAdapter.setStoreId(store_id);
        vh.gv_category_item.setAdapter(categoryItemAdapter );

        return convertView;
    }

    private class ViewHolder {
        TextView textOneName;//一级分类名称
        TextView textseaAll;//查看全部
        GridView gv_category_item;//二级分类的gridview
    }

    /**
     * 从data中筛选出一级分类
     *
     * @return
     * @author peak
     */
    private void getCategory(List<StoreGoodsClassList> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String level = String.valueOf(list.get(i).getLevel());
            if (level.equals(StoreGoodsClassList.LEVEL_ONE)) {
                categoryList.add(list.get(i));
            }
        }
    }

    /**
     * 根据一级分类及所有分类的集合获取当前一级分类的二级分类
     *
     * @param category
     * @param categories
     * @return
     * @author peak
     */
    private void getSubCategory(StoreGoodsClassList category, List<StoreGoodsClassList> categories) {
        categoryList_lev2.clear();

        for (int i = 0; i < categories.size(); i++) {
            categoryTwo = categories.get(i);
            String levelTwo = String.valueOf(categoryTwo.getLevel());
            String pid = String.valueOf(categoryTwo.getPid());
            if (levelTwo.equals(StoreGoodsClassList.LEVEL_TWO) && pid.equals(category.getId())) {
                categoryList_lev2.add(categoryTwo);
            }
        }
    }

    @Override
    public void clear() {
        super.clear();
        categoryList.clear();
        categoryList_lev2.clear();
    }
}
