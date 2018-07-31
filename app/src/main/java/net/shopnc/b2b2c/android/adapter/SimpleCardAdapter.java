package net.shopnc.b2b2c.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.FocusListBean;
import net.shopnc.b2b2c.android.bean.NewsListBean;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.StringUtils;
import net.shopnc.b2b2c.android.bean.item.VisibilityItem;
import net.shopnc.b2b2c.android.custom.GlideCircleTransform;
import net.shopnc.b2b2c.android.custom.MyDecoration;
import net.shopnc.b2b2c.android.lib.tab.NetworkImageHolderView;
import net.shopnc.b2b2c.android.ui.fenxiao.DianboAudeoInfoActivity;
import net.shopnc.b2b2c.android.lib.video.videoimg.VideoFrameImageLoader;
import net.shopnc.b2b2c.android.ui.fenxiao.WebActivity;
import net.shopnc.b2b2c.android.ui.fenxiao.ZhixunContentActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsDetailsActivity;
import net.shopnc.b2b2c.android.ui.type.GoodsListFragmentManager;

import java.util.ArrayList;

import tcking.github.com.jcvideo.JCVideoPlayer;
import tcking.github.com.jcvideo.JCVideoPlayerStandard;

/**
 * Created by snm on 2016/8/31.
 */
public class SimpleCardAdapter extends BaseAdapter implements com.bigkoo.convenientbanner.listener.OnItemClickListener {


    private Context mContext;
    private LayoutInflater inflater;
    private ArrayList<VisibilityItem> mList;

    public SimpleCardAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmList(ArrayList<VisibilityItem> list) {
        if (list != null) {

            this.mList = (ArrayList<VisibilityItem>) list.clone();
            notifyDataSetChanged();
        }
    }

    public void clearDeviceList() {
        if (mList != null) {
            mList.clear();
        }
        notifyDataSetChanged();
    }


    public void addDatas(ArrayList<VisibilityItem> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }


    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VisibilityItem bean1 = mList.get(position);
        NewsListBean bean = bean1.newsListBean;
        View view = null;

        if (bean.getCateList() != null) {
            cateListBeen.clear();
            cateListBeen.addAll(bean.getCateList());
            view = setHeader(bean.getCateList(), convertView);
        }
        if ("demand".equals(bean.getIdentity())) {
            view = DemandIsShow(bean, convertView);
        } else if ("news".equals(bean.getIdentity())) {
            view = setZiXunListView(bean, convertView);
        } else if ("movie".equals(bean.getIdentity())) {
            view = setLiveListView(bean, convertView);
        }

        return view;
    }

    /*滚动条的跳转*/
    @Override
    public void onItemClick(int position) {
        FocusListBean d = cateListBeen.get(position);
        String type = d.getFocus_type();
        String data = d.getFocus_data();
        if (type.equals("keyword")) {//搜索关键字
            Intent intent = new Intent(mContext, GoodsListFragmentManager.class);
            intent.putExtra("keyword", data);
            intent.putExtra("gc_name", data);
            mContext.startActivity(intent);
//        } else if (type.equals("special")) {//专题编号
//            Intent intent = new Intent(mContext, SubjectWebActivity.class);
//            intent.putExtra("data", Constants.URL_SPECIAL + "&special_id=" + data + "&type=html");
//            mContext.startActivity(intent);
        } else if (type.equals("goods")) {//商品编号
            Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
            intent.putExtra("goods_id", data);
            mContext.startActivity(intent);
        } else if (type.equals("url")) {//地址
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("data", data);
            mContext.startActivity(intent);
        }
    }


    public class ViewHolder {
        ImageView iv_image_bg;
        RelativeLayout img1_text;
        /*视频*/
        RelativeLayout video_text;
        JCVideoPlayerStandard jcVideoPlayerStandard;

        //        TextView textView = (TextView)view.findViewById(R.id.textView);
//        TextView img_price = (TextView)view.findViewById(R.id.img_price);
//        TextView textView3 ;
        TextView dian_title;
        ImageView dian_shoucang, iv_image_play;
        ImageView dian_logo;
        RecyclerView recyclerView_wenzi;
    }

    /*点播*/
    public View DemandIsShow(final NewsListBean bean, View view) {
        final ViewHolder holder;

//        String avdeo = "http://down13.68mtv.com/MP4/mp413/%E5%AD%99%E8%89%BA-%E4%BC%A4%E5%BF%83%E7%9A%84%E8%AF%BA%E8%A8%80[68mtv.com].mp4";

//        if(view == null){
        view = inflater.inflate(R.layout.fragment_fenxiao_listview_item_dianbo_image, null);
        holder = new ViewHolder();

        /*图片*/
        holder.iv_image_bg = view.findViewById(R.id.iv_image_bg);
        holder.iv_image_play = view.findViewById(R.id.iv_image_play);
        holder.img1_text = view.findViewById(R.id.img1_text);
        /*视频*/
        holder.video_text = view.findViewById(R.id.video_text);
        holder.jcVideoPlayerStandard = view.findViewById(R.id.videoplayer);

//        TextView textView = (TextView)view.findViewById(R.id.textView);
//        TextView img_price = (TextView)view.findViewById(R.id.img_price);
//            holder.textView3 = (TextView)view.findViewById(R.id.textView3);
        holder.dian_title = view.findViewById(R.id.dian_title);
        holder.dian_shoucang = view.findViewById(R.id.dian_shoucang);
        holder.dian_logo = view.findViewById(R.id.dian_logo);
        holder.recyclerView_wenzi = view.findViewById(R.id.recyclerView);

        view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }

        DingboWenziAdapter dingboWenziAdapter = new DingboWenziAdapter(mContext);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView_wenzi.setLayoutManager(linearLayoutManager);
        holder.recyclerView_wenzi.setAdapter(dingboWenziAdapter);
        holder.recyclerView_wenzi.addItemDecoration(new MyDecoration(mContext, MyDecoration.VERTICAL_LIST));
        if (!bean.getRecommend_goods().isEmpty()) {

            holder.recyclerView_wenzi.setVisibility(View.VISIBLE);

            dingboWenziAdapter.setmList(bean.getRecommend_goods());
        } else {
            holder.recyclerView_wenzi.setVisibility(View.GONE);
        }
        dingboWenziAdapter.setOnItemClickListener(new net.shopnc.b2b2c.android.lib.tab.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                intent.putExtra("goods_id", bean.getRecommend_goods().get(position).getGoods_id());
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        if (!bean.isIs_favorate()) {
            holder.dian_shoucang.setImageResource(R.drawable.shouocang);
        } else {
            holder.dian_shoucang.setImageResource(R.drawable.yishoucang);
        }

        holder.dian_shoucang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!bean.isIs_favorate()) {
//                    添加为收藏
                    if (shoucangLintener != null) {
                        shoucangLintener.setShoucang(bean.getStore_id(), "1");
                    }
                } else {
//                  取消收藏
                    if (shoucangLintener != null) {
                        shoucangLintener.setShoucang(bean.getStore_id(), "2");
                    }
                }
            }
        });

        holder.dian_title.setText(bean.getStore_name());

        Glide.with(mContext).load(bean.getStore_avatar()).transform(new GlideCircleTransform(mContext)).into(holder.dian_logo);
        String image = bean.getPromote_image();
        String avdeo = bean.getPromote_video();
//        String avdeo = "http://live.shopnctest.com/shopnc/test1.m3u8";
        /*视频是否存在*/
        if (!StringUtils.isEmpty(image)) {
            holder.img1_text.setVisibility(View.VISIBLE);
            holder.video_text.setVisibility(View.GONE);
            LoadImage.loadImg(mContext, holder.iv_image_bg, image);
        } else {
            holder.img1_text.setVisibility(View.GONE);
            holder.video_text.setVisibility(View.VISIBLE);
            holder.jcVideoPlayerStandard.setStreamVolume();
            boolean setUp = holder.jcVideoPlayerStandard.setUp(
                    avdeo, JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            if (setUp) {
                holder.jcVideoPlayerStandard.app_video_status_text.setText(bean.getPromote_text());
                holder.jcVideoPlayerStandard.startButton1.setVisibility(View.GONE);
                VideoFrameImageLoader mVideoFrameImageLoader = new VideoFrameImageLoader(mContext, null, null);
                mVideoFrameImageLoader.initview(avdeo, holder.jcVideoPlayerStandard.thumbImageView);
                Bitmap bitmap = mVideoFrameImageLoader.showCacheBitmap(VideoFrameImageLoader.formatVideoUrl(avdeo));
                if (bitmap != null) {
//                    Glide.with(mContext).load(bitmap).into(jcVideoPlayerStandard.thumbImageView);
                    holder.jcVideoPlayerStandard.thumbImageView.setImageBitmap(bitmap);
                } else {
//                    Glide.with(mContext).load("http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg").into(holder.jcVideoPlayerStandard.thumbImageView);
                }
            }

        }
        holder.jcVideoPlayerStandard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                            Intent intent = new Intent(mContext, DianboAudeoInfoActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("demand_id", bean.getVideo_id());
                            mContext.startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });

        holder.iv_image_bg.setOnClickListener(new DianboAudeoInfoOnclick(bean.getVideo_id()));
        holder.iv_image_play.setOnClickListener(new DianboAudeoInfoOnclick(bean.getVideo_id()));
        holder.video_text.setOnClickListener(new DianboAudeoInfoOnclick(bean.getVideo_id()));
        holder.dian_logo.setOnClickListener(new DianboAudeoInfoOnclick(bean.getVideo_id()));
        holder.dian_title.setOnClickListener(new DianboAudeoInfoOnclick(bean.getVideo_id()));

        return view;
    }

    private class setZiXunListViewHolder {

    }

    public View setZiXunListView(NewsListBean bean, View view) {
        TextView news_name, pageview;
        TextView add_time;
        ImageView news_pic;
//        if(view == null){
        view = inflater.inflate(R.layout.fragment_fenxiao_listview_item_zixun, null);
//            holder = new setZiXunListViewHolder();
        news_name = view.findViewById(R.id.news_name);
        pageview = view.findViewById(R.id.page_view);
        add_time = view.findViewById(R.id.add_time);
        news_pic = view.findViewById(R.id.news_pic);
//            view.setTag(holder);
//        }else {
//            holder = (setZiXunListViewHolder)view.getTag();
//        }
        news_name.setText(bean.getNews_name());
        pageview.setText(bean.getPage_view() + "人观看");
        add_time.setText(bean.getAdd_time());
        LoadImage.loadImg(mContext, news_pic, bean.getNews_pic());

        news_name.setOnClickListener(new PushNewsInfoOnckerListener(bean));
        pageview.setOnClickListener(new PushNewsInfoOnckerListener(bean));
        add_time.setOnClickListener(new PushNewsInfoOnckerListener(bean));
        news_pic.setOnClickListener(new PushNewsInfoOnckerListener(bean));

        return view;
    }

    /*跳转到点播详情页面*/
    public class DianboAudeoInfoOnclick implements View.OnClickListener {

        private String demand_id;

        public DianboAudeoInfoOnclick(String commonid) {
            this.demand_id = commonid;
        }

        @Override
        public void onClick(View v) {
            if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
                Intent intent = new Intent(mContext, DianboAudeoInfoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("demand_id", demand_id);
                mContext.startActivity(intent);
            }

        }
    }

    /*跳转到咨询详情页面*/
    public class PushNewsInfoOnckerListener implements View.OnClickListener {
        private NewsListBean bean;

        public PushNewsInfoOnckerListener(NewsListBean newsListBean) {
            this.bean = newsListBean;
        }

        @Override
        public void onClick(View v) {
//            if (ShopHelper.isLogin(mContext, MyShopApplication.getInstance().getLoginKey())) {
            Intent in = new Intent(mContext, ZhixunContentActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.putExtra("news_id", bean.getVideo_id());
            mContext.startActivity(in);
//            }

        }
    }

    /*dianji led souchelg  */
    public interface setShoucangLintener {
        void setShoucang(String store_id, String isShou);
    }

    private setShoucangLintener shoucangLintener;

    public void setShoucangLintener(setShoucangLintener Lintener) {
        this.shoucangLintener = Lintener;
    }

    private ConvenientBanner convenientBanner;//顶部广告栏控件
    private ArrayList<FocusListBean> cateListBeen = new ArrayList<>();

    /*头部试图*/
    private View setHeader(ArrayList<FocusListBean> mList, View header) {

        header = inflater.inflate(R.layout.commonbanner, null);
        convenientBanner = header.findViewById(R.id.convenientBanner);

        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, mList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.point_unfocused, R.drawable.nc_page_on})
                .setOnItemClickListener(this);
        //开始自动翻页
        if (mList.size() > 0) {
            convenientBanner.startTurning(3000);
        }
        return header;
//        adapter.setmHeaderView(header);
//        myListView.addHeaderView(header);
    }

    /*直播版本*/
    public View setLiveListView(NewsListBean bean, View view) {

        TextView live_title, live_num;
        ImageView live_logo, live_img;

        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_fenxiao_listview_item_zhibo, null);
        live_title = view.findViewById(R.id.live_title);
        live_num = view.findViewById(R.id.live_num);
        live_logo = view.findViewById(R.id.live_logo);
        live_img = view.findViewById(R.id.live_img);

//        live_title.setText(bean.getMovie_title());
        live_num.setText(bean.getPage_view() + "人观看");
//        add_time.setText(mList.get(pos).getAdd_time());dianji dianji SDK

//        LoadImage.loadImg(mContext,live_logo,bean.getMovie_cover_img());//
//        LoadImage.loadImg(mContext,live_img,bean.getMovie_cover_img());      //直播封面图
//        Glide.with(mContext).load(bean.getMember_avater()).transform(new GlideCircleTransform(mContext)).into(live_logo);

//        live_title.setOnClickListener(new LiveInfoOnclick(bean));
//        live_num.setOnClickListener(new LiveInfoOnclick(bean));
//        live_logo.setOnClickListener(new LiveInfoOnclick(bean));
//        live_img.setOnClickListener(new LiveInfoOnclick(bean));

        return view;
    }
}
