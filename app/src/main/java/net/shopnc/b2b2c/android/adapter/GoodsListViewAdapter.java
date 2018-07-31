package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.GoodsList;
import net.shopnc.b2b2c.android.common.AnimateFirstDisplayListener;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.LogHelper;
import net.shopnc.b2b2c.android.common.SystemHelper;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;
import net.shopnc.b2b2c.android.ui.store.newStoreInFoActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 商品列表适配器
 *
 * @author KingKong·HE
 * @Time 2014-1-6 下午12:06:09
 * @E-mail hjgang@bizpoer.com
 */
public class GoodsListViewAdapter extends BaseAdapter {
    private Context context;

    private LayoutInflater inflater;

    private String listType;

    private ArrayList<GoodsList> goodsLists;

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public GoodsListViewAdapter(Context context, String listType) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listType = listType;
    }

    @Override
    public int getCount() {
        if (goodsLists == null) {
            LogHelper.d("huting--count:", "0");
        } else {
            LogHelper.d("huting--count:", String.valueOf(goodsLists.size()));
        }
        return goodsLists == null ? 0 : goodsLists.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public ArrayList<GoodsList> getGoodsLists() {
        return goodsLists;
    }

    public void setGoodsLists(ArrayList<GoodsList> goodsLists) {
        this.goodsLists = goodsLists;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        GoodsList bean = goodsLists.get(position);
        final String goodsId = bean.getGoods_id();
        final String storeId = bean.getStore_id();

        if (null == convertView) {
            if (listType.equals("grid")) {
                convertView = inflater.inflate(R.layout.gridview_goods_item, null);
                LogHelper.d("huting+++", convertView.toString());
            } else {
                convertView = inflater.inflate(R.layout.listivew_goods_item, null);
                LogHelper.d("huting====", convertView.toString());
            }

            holder = new ViewHolder();
            holder.imageGoodsPic = convertView.findViewById(R.id.imageGoodsPic);
            holder.llGoodsItem = convertView.findViewById(R.id.llGoodsItem);
            holder.textGoodsName = convertView.findViewById(R.id.textGoodsName);
            holder.textGoodsJingle = convertView.findViewById(R.id.textGoodsJingle);
            holder.textGoodsPrice = convertView.findViewById(R.id.textGoodsPrice);
            holder.textGoodsType = convertView.findViewById(R.id.textGoodsType);
            holder.textZengPin = convertView.findViewById(R.id.textZengPin);
            holder.tvGoodsSalenum = convertView.findViewById(R.id.tvGoodsSalenum);
            holder.tvOwnShop = convertView.findViewById(R.id.tvOwnShop);
            holder.btnStoreName = convertView.findViewById(R.id.btnStoreName);
            holder.llStoreInfo = convertView.findViewById(R.id.llStoreInfo);
            holder.tvStoreName = convertView.findViewById(R.id.tvStoreName);
            holder.llStoreEval = convertView.findViewById(R.id.llStoreEval);
            holder.tvStoreDescPoint = convertView.findViewById(R.id.tvStoreDescPoint);
            holder.tvStoreDescText = convertView.findViewById(R.id.tvStoreDescText);
            holder.tvStoreServicePoint = convertView.findViewById(R.id.tvStoreServicePoint);
            holder.tvStoreServiceText = convertView.findViewById(R.id.tvStoreServiceText);
            holder.tvStoreDeliveryPoint = convertView.findViewById(R.id.tvStoreDeliveryPoint);
            holder.tvStoreDeliveryText = convertView.findViewById(R.id.tvStoreDeliveryText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.llStoreInfo.setVisibility(View.GONE);
        LogHelper.e("position", String.valueOf(position));
//        imageLoader.displayImage(bean.getGoods_image_url(), holder.imageGoodsPic, options, animateFirstListener);
        LoadImage.loadImg(context, holder.imageGoodsPic, bean.getGoods_image_url());

        holder.textGoodsName.setText(bean.getGoods_name());
        holder.textGoodsJingle.setText(bean.getGoods_jingle());
        holder.textGoodsPrice.setText("￥" + bean.getGoods_price());
        holder.tvGoodsSalenum.setText("销量:" + bean.getGoods_salenum());

        if (Boolean.valueOf(bean.getSole_flag())) {
            holder.textGoodsType.setText("");
            holder.textGoodsType.setVisibility(View.VISIBLE);
            holder.textGoodsType.setBackgroundResource(R.drawable.nc_icon_mobile_price);
        } else if (Boolean.valueOf(bean.getGroup_flag())) {
//            holder.textGoodsType.setText(context.getString(R.string.text_groupbuy));
            holder.textGoodsType.setText("降");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_tuangou);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else if (Boolean.valueOf(bean.getXianshi_flag())) {
//            holder.textGoodsType.setText(context.getString(R.string.text_xianshi));
            holder.textGoodsType.setText("降");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_xianshi);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else if (bean.getIs_appoint().equals("1")) {
//            holder.textGoodsType.setText(context.getString(R.string.text_appoint));
            holder.textGoodsType.setText("预");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_yuyue);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else if (bean.getIs_fcode().equals("1")) {
//            holder.textGoodsType.setText(context.getString(R.string.text_fcode));
            holder.textGoodsType.setText("F");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_Fcode);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else if (bean.getIs_presell().equals("1")) {
//            holder.textGoodsType.setText(context.getString(R.string.text_presell));
            holder.textGoodsType.setText("预");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_yushou);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else if (bean.getIs_virtual().equals("1")) {
//            holder.textGoodsType.setText(context.getString(R.string.text_virtual));
            holder.textGoodsType.setText("虚");
            holder.textGoodsType.setVisibility(View.VISIBLE);
//            holder.textGoodsType.setBackgroundResource(R.color.text_xuni);
            holder.textGoodsType.setBackgroundResource(R.color.nc_red);
        } else {
            holder.textGoodsType.setVisibility(View.GONE);
        }
        if (bean.getHave_gift().equals("1")) {
            holder.textZengPin.setVisibility(View.VISIBLE);
            holder.textZengPin.setText("赠");
            holder.textZengPin.setBackgroundResource(R.color.nc_red);
        } else {
            holder.textZengPin.setVisibility(View.GONE);
        }


        //店铺信息控制
        if (bean.getIs_own_shop().equals("1")) {
            holder.tvOwnShop.setVisibility(View.VISIBLE);
            holder.btnStoreName.setVisibility(View.GONE);
        } else {
            holder.tvOwnShop.setVisibility(View.GONE);
            holder.btnStoreName.setVisibility(View.VISIBLE);
            holder.btnStoreName.setText(bean.getStore_name());
            holder.btnStoreName.setTag(position);
            holder.btnStoreName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.llStoreInfo.setVisibility(View.VISIBLE);
                    RemoteDataHandler.asyncDataStringGet(Constants.URL_STORE_CREDIT + "&store_id=" + storeId, new RemoteDataHandler.Callback() {
                        @Override
                        public void dataLoaded(ResponseData data) {
                            LogHelper.e("wj", "点击了" + position);
                            if (data.getCode() == HttpStatus.SC_OK) {
                                String json = data.getJson();
                                try {
                                    JSONObject jsonObj = new JSONObject(json);
                                    String objString = jsonObj.getString("store_credit");
                                    JSONObject obj = new JSONObject(objString);
                                    String desc = obj.getString("store_desccredit");
                                    JSONObject objDesc = new JSONObject(desc);
                                    setStoreCredit(objDesc.getString("credit"), objDesc.getString("text"), holder.tvStoreDescPoint, holder.tvStoreDescText);
                                    String service = obj.getString("store_servicecredit");
                                    JSONObject objService = new JSONObject(service);
                                    setStoreCredit(objService.getString("credit"), objService.getString("text"), holder.tvStoreServicePoint, holder.tvStoreServiceText);
                                    String delivery = obj.getString("store_deliverycredit");
                                    JSONObject objDelivery = new JSONObject(delivery);
                                    setStoreCredit(objDelivery.getString("credit"), objDelivery.getString("text"), holder.tvStoreDeliveryPoint, holder.tvStoreDeliveryText);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    holder.llStoreInfo.setVisibility(View.GONE);
                                }
                            } else {
                                holder.llStoreInfo.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });
        }

        //跳转到店铺
        holder.tvStoreName.setText(bean.getStore_name());
        holder.tvStoreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, newStoreInFoActivity.class);
                intent.putExtra("store_id", storeId);
                context.startActivity(intent);
            }
        });

        //关闭店铺信息
        holder.llStoreEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.llStoreInfo.setVisibility(View.GONE);
            }
        });

        //点击商品显示商品详细
        holder.llGoodsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", goodsId);
                context.startActivity(intent);
            }
        });

        //点击商品图片商品详细
        holder.imageGoodsPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", goodsId);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private void setStoreCredit(String credit, String type, TextView tvPoint, TextView tvText) {
        tvPoint.setText(credit);
        if (type.equals("low")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_green));
            tvText.setText("低");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_green));
        }
        if (type.equals("equal")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("平");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
        if (type.equals("high")) {
            tvPoint.setTextColor(context.getResources().getColor(R.color.nc_red));
            tvText.setText("高");
            tvText.setBackgroundColor(context.getResources().getColor(R.color.nc_red));
        }
    }

    class ViewHolder {
        ImageView imageGoodsPic;
        TextView textGoodsName;
        TextView textGoodsJingle;
        TextView textGoodsPrice;
        TextView textGoodsType;
        TextView textZengPin;
        TextView tvGoodsSalenum;
        TextView tvOwnShop;
        Button btnStoreName;
        LinearLayout llGoodsItem;
        LinearLayout llStoreInfo;
        TextView tvStoreName;
        LinearLayout llStoreEval;
        TextView tvStoreDescPoint;
        TextView tvStoreDescText;
        TextView tvStoreServicePoint;
        TextView tvStoreServiceText;
        TextView tvStoreDeliveryPoint;
        TextView tvStoreDeliveryText;
    }
}
