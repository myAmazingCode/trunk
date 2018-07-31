package net.shopnc.b2b2c.android.ui.im;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.IMDetailsListAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.base.EventObj;
import net.shopnc.b2b2c.android.bean.GoodDetailVo;
import net.shopnc.b2b2c.android.bean.IMMessage;
import net.shopnc.b2b2c.android.bean.IMSmile;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.LinkManInfo;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.MultiRecyclerItemSupport;
import net.shopnc.b2b2c.android.common.adapter.RRecyclerAdapter;
import net.shopnc.b2b2c.android.common.adapter.RecyclerHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.common.log.LogHelper;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class IMDetailsActivity extends BaseActivity {

    @Bind(R.id.xrvIMDetails)
    XRecyclerView xrvIMDetails;
    @Bind(R.id.rvSmiles)
    RecyclerView rvSmiles;
    @Bind(R.id.ivSmile)
    ImageView ivSmile;
    @Bind(R.id.etInput)
    EditText etInput;
    @Bind(R.id.ivSend)
    ImageView ivSend;
    @Bind(R.id.llBottom)
    LinearLayout llBottom;
    @Bind(R.id.tvPic)
    TextView tvPic;
    @Bind(R.id.tvCamera)
    TextView tvCamera;
    @Bind(R.id.tvFav)
    TextView tvFav;
    @Bind(R.id.llMore)
    LinearLayout llMore;

    public static final int SELELCT_FILE_TO_UPLOAD_ITEM = 108;
    public static final int FLAG_CHOOSE_PHONE = 6;

    private IMDetailsListAdapter adapter;
    private MultiRecyclerItemSupport<IMMessage> multiItemTypeSupport;
    private int page = 1;
    private int sid;
    private int gid;
    private int lid;
    private String turnFlag;
    private List<IMMessage> messageList;
    private LinkManInfo linkManInfo;
    private GoodDetailVo goodDetailVo;
    private HashMap<Integer, View> middleViews;
    private AddViewHolder addViewHolder;
    private String moneyRmb;
    private String imageName;
    private String imagePath;
    private String messageImgUrl;
    private String messageImgWidth;
    private String messageImgHeight;
    private IMService imService;
    private ServiceConnection connection = new ServiceConnection() {
        //获取服务对象时的操作
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            imService = ((IMService.IMServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("消息详情");
        EventBus.getDefault().register(this);
        sid = getIntent().getIntExtra("sid", 0);
        gid = getIntent().getIntExtra("gid", 0);
        lid = getIntent().getIntExtra("lid", 0);
        turnFlag = getIntent().getStringExtra("flag");
        moneyRmb = getResources().getString(R.string.money_rmb);
        messageList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvIMDetails.setLayoutManager(layoutManager);
        //设置动画
        xrvIMDetails.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvIMDetails.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvIMDetails.setLoadingListener(listener);
        setMultiItemTypeSupport();
        adapter = new IMDetailsListAdapter(context, multiItemTypeSupport);
        xrvIMDetails.setAdapter(adapter);

        Intent chatService = new Intent(context, IMService.class);
        chatService.putExtra("sid", sid);
        bindService(chatService, connection, BIND_AUTO_CREATE);
        initSmiles();
        setSoftInputHide();
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    ivSend.setSelected(false);
                } else {
                    if (s.toString().length() > 500) {
                        TToast.showShort(context, "您发送的内容太长，请分多次发送！");
                        ivSend.setSelected(false);
                    } else {
                        ivSend.setSelected(true);
                    }
                }
            }
        });


        if (gid > 0) {
            addGoodId();
        } else {
            getLinkInfo();
        }
    }

    /**
     * 实现上下拉刷新方法接口
     */
    private XRecyclerView.LoadingListener listener = new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            page++;
            requestHistoryMessage();
        }

        @Override
        public void onLoadMore() {
            xrvIMDetails.loadMoreComplete();
        }
    };

    @Override
    protected void setView() {
        setContentView(R.layout.activity_imdetails);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        LogHelper.d("gid:" + gid);
//        LogHelper.d("sid:" + sid);
//        LogHelper.d("lid:" + lid);
//        if (gid > 0) {
//            addGoodId();
//        } else {
//            getLinkInfo();
//        }
    }

    private void initSmiles() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 8);
        rvSmiles.setLayoutManager(gridLayoutManager);
        rvSmiles.setAdapter(new RRecyclerAdapter<IMSmile>(context, R.layout.recyclerview_smile_item, SmilesData.smiliesLists) {
            @Override
            public void convert(RecyclerHolder holder, final IMSmile smile) {
                holder.setImageResource(R.id.ivImg, smile.getPath());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), smile.getPath());
                        ImageSpan imageSpan = new ImageSpan(context, bitmap);
                        SpannableString spannableString = new SpannableString(smile.getTitle());
                        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        int index = etInput.getSelectionStart();
                        Editable editable = etInput.getText();
                        editable.insert(index, spannableString);
                    }
                });
            }
        });
    }

    private void setMultiItemTypeSupport() {
        multiItemTypeSupport = new MultiRecyclerItemSupport<IMMessage>() {
            @Override
            public int getItemViewType(IMMessage message) {
                if (message.getFrom_user_id() == Integer.valueOf(application.getMemberID())) {  //买家  右侧布局
                    return 1;
                }
                return 2;
            }

            @Override
            public HashMap<Integer, Integer> getLayoutMap() {
                HashMap<Integer, Integer> layoutMap = new HashMap<>();
                layoutMap.put(2, R.layout.left);
                layoutMap.put(1, R.layout.right);
                return layoutMap;
            }
        };
    }


    private void addGoodId() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("sid", sid + "");
        params.put("gid", gid + "");

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_IM_ADD_GOOD, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                linkManInfo = JsonUtil.toBean(data, "linkManInfo", new TypeToken<LinkManInfo>() {
                }.getType());
                if (linkManInfo != null) {
                    adapter.setUserImg(linkManInfo.getUser_avatar().equals("") ? application.getAvatar() : linkManInfo.getUser_avatar());
                    adapter.setSellerImg(linkManInfo.getLink_man_avatar().equals("") ? ConstantUrl.URL_SELLER_IMG_DEFAULT : linkManInfo.getLink_man_avatar());
                }
                requestGoodsMessage();
            }
        }, params);
    }

    private void getLinkInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("lid", lid + "");

        LogHelper.d("lid:" + lid);

        OkHttpUtil.postAsyn(this, ConstantUrl.URL_IM_GET_LINK_INFO, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                linkManInfo = JsonUtil.toBean(data, "linkManInfo", new TypeToken<LinkManInfo>() {
                }.getType());
                if (linkManInfo != null) {
                    adapter.setUserImg(linkManInfo.getUser_avatar().equals("") ? application.getAvatar() : linkManInfo.getUser_avatar());
                    adapter.setSellerImg(linkManInfo.getLink_man_avatar().equals("") ? ConstantUrl.URL_SELLER_IMG_DEFAULT : linkManInfo.getLink_man_avatar());
                }
                requestHistoryMessage();
            }
        }, params);
    }

    private void requestGoodsMessage() {
        String url = ConstantUrl.URL_GOOD_DETAILS + gid;

        OkHttpUtil.getAsyn(this, url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                goodDetailVo = JsonUtil.toBean(data, "goodsDetail", new TypeToken<GoodDetailVo>() {
                }.getType());
                initGoodsView();
                //赋值lid
                lid = linkManInfo.getLink_id();
                getLinkInfo();
//                requestHistoryMessage();
            }
        });
    }

    private void requestHistoryMessage() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("sid", sid + "");
        params.put("page", page + "");
        OkHttpUtil.postAsyn(this, ConstantUrl.URL_IM_HISTORY_MESSAGE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                LogHelper.e("QIN", data);
                List<IMMessage> list = JsonUtil.toBean(data, "his", new TypeToken<ArrayList<IMMessage>>() {
                }.getType());
                if (xrvIMDetails != null) {
                    xrvIMDetails.refreshComplete();
                }
                messageList.addAll(0, list);
                adapter.setDatas(messageList);
                if (messageList.size() == list.size()) {//第一次记载
                    middleViews = new HashMap<>();
                    if (gid > 0) {
                        middleViews.put(messageList.size(), addViewHolder.getCustomView());
                    }
                    adapter.setMiddleViews(middleViews);
                    xrvIMDetails.smoothScrollToPosition(list.size());//移动到底部
                    adapter.notifyDataSetChanged();
                } else {//下拉加载分页
                    adapter.notifyItemRangeInserted(0, list.size());
                }
            }
        }, params);
    }

    private void initGoodsView() {
        addViewHolder = new AddViewHolder(context, R.layout.im_details_add_goods_info);
        addViewHolder.setImage(R.id.ivGoodImg, goodDetailVo.getImageSrc())
                .setText(R.id.tvGoodName, goodDetailVo.getGoodsName())
                .setText(R.id.tvGoodPrice, moneyRmb + ShopHelper.getPriceString(goodDetailVo.getAppPriceMin()));
        addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (turnFlag.equals("details")) {
                    finish();
                } else if (turnFlag.equals("imList")) {
                    ShopHelper.gotoGoodDetailsActivity(context, gid);
                }
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(60, 60, 60, 60);
        addViewHolder.getCustomView().setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.ivSmile, R.id.ivSend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSmile:
                if (rvSmiles.getVisibility() == View.VISIBLE) {
                    rvSmiles.setVisibility(View.GONE);
                } else {
                    rvSmiles.setVisibility(View.VISIBLE);
                    llMore.setVisibility(View.GONE);
                }
//                xrvIMDetails.smoothScrollToPosition(messageList.size());
                hideSoftInput(view);
                break;
            case R.id.ivSend:
                if (ivSend.isSelected()) {
                    imService.sendBuyerMessage(etInput.getText().toString());
                    etInput.setText("");
                } else {   //点击展开更多
                    hideSoftInput(view);
                    rvSmiles.setVisibility(View.GONE);
                    llMore.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public void onEventMainThread(EventObj obj) {
        if (obj.getFlag().equals(EventObj.IMMESSAGESENDSUCCESS)) {
            messageList.add((IMMessage) obj.getObj());
        } else if (obj.getFlag().equals(EventObj.IMMESSAGEGETSUCCESS)) {
            messageList.addAll((List<IMMessage>) obj.getObj());
        }
        adapter.notifyItemInserted(messageList.size());
        xrvIMDetails.smoothScrollToPosition(messageList.size());//移动到底部
    }

    private void hideSoftInput(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void setSoftInputHide() {
        xrvIMDetails.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSoftInput(v);
                if (rvSmiles.getVisibility() == View.VISIBLE) {
                    rvSmiles.setVisibility(View.GONE);
                }
                if (llMore.getVisibility() == View.VISIBLE) {
                    llMore.setVisibility(View.GONE);
                }
                return false;
            }
        });
        etInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (rvSmiles.getVisibility() == View.VISIBLE) {
                    rvSmiles.setVisibility(View.GONE);
                }
                if (llMore.getVisibility() == View.VISIBLE) {
                    llMore.setVisibility(View.GONE);
                }
                return false;
            }
        });

        llBottom.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                int[] location = new int[2];
//                ivSend.getLocationOnScreen(location);
//                int x = location[0];
//                int y = location[1];
//                xrvIMDetails.smoothScrollBy(x,y);
                xrvIMDetails.smoothScrollToPosition(messageList.size());
            }
        });

    }


    @OnClick({R.id.tvPic, R.id.tvCamera, R.id.tvFav})
    public void onLLMoreClick(View view) {
        switch (view.getId()) {
            case R.id.tvPic:
                if (PermissionHelper.checkStoragePermission(context)) {
                    showFileChooser();
                }
                break;
            case R.id.tvCamera:
                if (PermissionHelper.checkCameraPermission(context)) {
                    doGoToPhone();
                }
                break;
            case R.id.tvFav:

                break;
        }
    }

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            ((Activity) context).startActivityForResult(Intent.createChooser(intent, "请选择文件"), SELELCT_FILE_TO_UPLOAD_ITEM);
        } catch (ActivityNotFoundException ex) {
            TToast.showShort(context, "请安装文件管理器");
        }
    }

    public void doGoToPhone() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    imageName = System.currentTimeMillis() + ".jpg";
                    File filePath = new File(Constants.APP_DIR);
                    File imageFile = new File(filePath, imageName);
                    Uri photoUri = Uri.fromFile(imageFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    ((Activity) context).startActivityForResult(intent, FLAG_CHOOSE_PHONE);
                }
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionHelper.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showFileChooser();
            } else {
                TToast.showShort(context, "暂无法选择图片");
            }
        } else if (requestCode == PermissionHelper.USE_CAMERA) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                doGoToPhone();
            } else {
                TToast.showShort(context, "暂无法使用摄像头");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imagePath = LoadImage.getPath(this, uri);
                    fileToUpload();
                }
                break;
            case FLAG_CHOOSE_PHONE:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        imagePath = Constants.APP_DIR + "/" + imageName;
                        fileToUpload();
                    }
                }
            case 300:
                if (data != null) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fileToUpload();
                }
                break;
        }
    }

    private void fileToUpload() {
        File file = new File(imagePath);
        if (file.length() < 1024 * 1024) {
            ShopHelper.postImage(context, file, new ShopHelper.PostImage() {
                @Override
                public void postImageSuccess(ImageFile imageFile) {
                    String message = "<a href=\"" + imageFile.getUrl() + "\" target=\"_blank\" data-lightbox=\"image\" >\n" +
                            "\t<img class=\"showPictrue\" nc-img-width=\"" + imageFile.getWidth() + "\" nc-img-height=\"" + imageFile.getHeight() + "\" width=\"40\" height=\"40\" style=\"max-width:200px;width:auto;max-height:200px;height:auto;cursor:zoom-in;\" src=\"" + imageFile.getUrl() + "\" />\n" +
                            "</a>";
                    imService.sendBuyerMessage(message);
                }
            });
        } else {
            TToast.showShort(context, "图片文件过大，请上传1M以下的图片");
            trimPhoto(Uri.fromFile(file));
        }
    }

    //裁剪照片
    public void trimPhoto(Uri uri) {
        imagePath = Constants.APP_DIR + "/" + "trim" + System.currentTimeMillis() + ".jpg";
        File trimfile = new File(imagePath);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", Uri.fromFile(trimfile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, 300);
    }

}
