package net.shopnc.b2b2c.android.ui.order;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.EvaluatePhotoAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ComplainDetail;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.TalkBean;
import net.shopnc.b2b2c.android.common.AddViewHolder;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.ClickBigImageDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.ui.good.GoodDetailsActivity;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderComplaintDetailActivity extends BaseActivity {

    @Bind(R.id.tvComplaintGoods)
    TextView tvComplaintGoods;
    @Bind(R.id.tvComplaintState)
    TextView tvComplaintState;
    @Bind(R.id.tvComplaintTime)
    TextView tvComplaintTime;
    @Bind(R.id.tvComplaintTheme)
    TextView tvComplaintTheme;
    @Bind(R.id.tvComplaintContent)
    TextView tvComplaintContent;
    @Bind(R.id.tvImageNone)
    TextView tvImageNone;
    @Bind(R.id.fblImages)
    FlexboxLayout fblImages;
    @Bind(R.id.tvTalk)
    TextView tvTalk;
    @Bind(R.id.llTalk)
    LinearLayout llTalk;
    @Bind(R.id.tvArbitrationTime)
    TextView tvArbitrationTime;
    @Bind(R.id.tvArbitrationOpinion)
    TextView tvArbitrationOpinion;
    @Bind(R.id.llArbitrament)
    LinearLayout llArbitrament;
    @Bind(R.id.llComplaintGoods)
    LinearLayout llComplaintGoods;
    @Bind(R.id.tvSubmmit)
    TextView tvSubmmit;
    @Bind(R.id.gvPhoto)
    MyGridView gvPhoto;
    @Bind(R.id.rlSupplementary)
    RelativeLayout rlSupplementary;
    @Bind(R.id.tvSupplementary)
    TextView tvSupplementary;
    @Bind(R.id.tvMyComplaintInfo)
    TextView tvMyComplaintInfo;
    @Bind(R.id.tvAccusedTime)
    TextView tvAccusedTime;
    @Bind(R.id.tvAccusedContent)
    TextView tvAccusedContent;
    @Bind(R.id.fblImagesSeller)
    FlexboxLayout fblImagesSeller;
    @Bind(R.id.llSellerInfo)
    LinearLayout llSellerInfo;
    @Bind(R.id.btnSubmitArbitration)
    Button btnSubmitArbitration;
    @Bind(R.id.etTalkAdd)
    EditText etTalkAdd;
    @Bind(R.id.tvTalkAdd)
    TextView tvTalkAdd;
    @Bind(R.id.llTalkAdd)
    LinearLayout llTalkAdd;
    private int complainId;
    private ComplainDetail complainDetail;
    private String moneyRmb;
    private String uploadRoot;
    private List<String> imageList;
    private List<String> imageList_seller;
    private String url;
    boolean isUpload = false;//补充凭证
    private EvaluatePhotoAdapter photoAdapter;
    private List<ImageFile> imageFileList;
    private PhotoBottomDialog dialog;
    private NCDialog ncDialog;
    private String imagePath;
    private ArrayList<TalkBean> talkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complainId = getIntent().getIntExtra("complainId", 0);
        setViewName();
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        requestComplainInfo();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_complaint_details);
    }

    private void setViewName() {
        setCommonHeader("投诉详情");
        url = ConstantUrl.URL_MEMBER_COMPLAIN_INFO;
    }

    /**
     * 查看投诉详情
     */
    private void requestComplainInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("complainId", complainId + "");

        OkHttpUtil.postAsyn(this,url, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                complainDetail = JsonUtil.toBean(data, "complain", new TypeToken<ComplainDetail>() {
                }.getType());
                talkList = JsonUtil.toBean(data, "talkList", new TypeToken<ArrayList<TalkBean>>() {
                }.getType());
                bindData();
            }
        }, params);
    }

    /**
     * 投诉对话列表
     */
    private void requestComplainTalkList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("complainId", complainId + "");

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_COMPLAIN_TALK_LIST, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                talkList = JsonUtil.toBean(data, "talkList", new TypeToken<ArrayList<TalkBean>>() {
                }.getType());
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < talkList.size(); i++) {
                    TalkBean talkBean = talkList.get(i);
                    stringBuffer.append(talkBean.getTalkRole() + "[" + talkBean.getTalkTime() + "]" + talkBean.getTalkContent() + "\n");
                }
                tvTalk.setText(stringBuffer);
                etTalkAdd.setText("");
            }
        }, params);
    }


    /**
     * 发送对话
     */
    private void requestTalkSave(String talkContent) {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("complainId", complainId + "");
        params.put("talkContent", talkContent);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_COMPLAIN_TALK_SAVE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                requestComplainTalkList();
            }
        }, params);
    }

    /**
     * 提交仲裁
     */
    private void requestStopTalk() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("complainId", complainId + "");

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_COMPLAIN_STOP_TALK, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                requestComplainInfo();
            }
        }, params);
    }

    private void bindData() {
        tvComplaintGoods.setText(complainDetail.getGoodsName());
        tvComplaintState.setText(complainDetail.getComplainStateName());
        tvComplaintTime.setText(complainDetail.getAccuserTime());
        tvComplaintTheme.setText(complainDetail.getSubjectTitle());
        tvComplaintContent.setText(complainDetail.getAccuserContent());
        etTalkAdd.setText("");

        if (TextUtils.isEmpty(complainDetail.getAccusedTime())) {
            llSellerInfo.setVisibility(View.GONE);
        } else {
            llSellerInfo.setVisibility(View.VISIBLE);
            tvAccusedTime.setText(complainDetail.getAccusedTime());
            tvAccusedContent.setText(complainDetail.getAccusedContent());
            bindImages_seller();
        }

        //判断是否
        if (complainDetail.getShowTalkList() == 1) {
            llTalk.setVisibility(View.VISIBLE);
            if (talkList.size() == 0) {
                tvTalk.setText("暂无对话");
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < talkList.size(); i++) {
                    TalkBean talkBean = talkList.get(i);
                    stringBuffer.append(talkBean.getTalkRole() + "[" + talkBean.getTalkTime() + "]" + talkBean.getTalkContent() + "\n");
                }
                tvTalk.setText(stringBuffer);
            }
        } else {
            llTalk.setVisibility(View.GONE);
        }

        if (complainDetail.getShowTalkAdd() == 1) {
            llTalkAdd.setVisibility(View.VISIBLE);
            tvTalkAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(etTalkAdd.getText().toString())) {
                        TToast.showShort(context, "请输入对话内容");
                    } else {
                        requestTalkSave(etTalkAdd.getText().toString());
                    }
                }
            });
        } else {
            llTalkAdd.setVisibility(View.GONE);
        }


        if (complainDetail.getIsComplainStateFinish() == 1) {
            llArbitrament.setVisibility(View.VISIBLE);
            tvArbitrationTime.setText(complainDetail.getAdminConfirmTime());
            tvArbitrationOpinion.setText(complainDetail.getAdminConfirmContent());
        } else {
            llArbitrament.setVisibility(View.GONE);
        }

        if (complainDetail.getIsComplainStateTalk() == 1) {
            btnSubmitArbitration.setVisibility(View.VISIBLE);
            btnSubmitArbitration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ncDialog = new NCDialog(context);
                    ncDialog.setText1("确认提交仲裁?");
                    ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            requestStopTalk();
                        }
                    });
                    ncDialog.showPopupWindow();
                }
            });
        } else {
            btnSubmitArbitration.setVisibility(View.GONE);
        }

        bindImages();
    }

    /**
     * 我的投诉凭证
     */
    private void bindImages() {
        if (complainDetail.getAccuserImagesList().size() > 0) {
            tvImageNone.setVisibility(View.GONE);
            fblImages.setVisibility(View.VISIBLE);
            rlSupplementary.setVisibility(View.GONE);
            isUpload = false;
            imageList = new ArrayList<>();
            for (int i = 0; i < complainDetail.getAccuserImagesList().size(); i++) {
                imageList.add(complainDetail.getAccuserImagesList().get(i));
            }
            for (final String s : imageList) {
                AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_image_simple);
                addViewHolder.setImage(R.id.ivImg, s);
                addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击查看大图并设置当前位置
                        ClickBigImageDialog dialog = new ClickBigImageDialog(context, imageList, imageList.indexOf(s));
                        dialog.show();
                    }
                });
                fblImages.addView(addViewHolder.getCustomView());
            }
        } else {
            tvImageNone.setVisibility(View.VISIBLE);
            fblImages.setVisibility(View.GONE);
            rlSupplementary.setVisibility(View.GONE);
            isUpload = false;
            dialog = new PhotoBottomDialog(context);
            imageFileList = new ArrayList<>();
            photoAdapter = new EvaluatePhotoAdapter(context, 3);
            photoAdapter.setImageFileList(imageFileList);
            gvPhoto.setAdapter(photoAdapter);

            gvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    if (imageFileList.size() == 0 || position == imageFileList.size()) {
                        dialog.show();
                    } else {
                        ncDialog = new NCDialog(context);
                        ncDialog.setText1("确认删除该图片?");
                        ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                            @Override
                            public void onDialogConfirm() {
                                imageFileList.remove(position);
                                photoAdapter.notifyDataSetChanged();
                            }
                        });
                        ncDialog.showPopupWindow();
                    }
                }
            });

            tvImageNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isUpload) {
                        isUpload = false;
                        rlSupplementary.setVisibility(View.GONE);
                    } else {
                        isUpload = true;
                        rlSupplementary.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    /**
     * 商家申诉凭证
     */
    private void bindImages_seller() {
        if (complainDetail.getAccusedImagesList().size() > 0) {
//            tvImageNone.setVisibility(View.GONE);
            fblImagesSeller.setVisibility(View.VISIBLE);
            imageList_seller = new ArrayList<>();
            for (int i = 0; i < complainDetail.getAccusedImagesList().size(); i++) {
                imageList_seller.add(complainDetail.getAccusedImagesList().get(i));
            }
            for (final String s : imageList_seller) {
                AddViewHolder addViewHolder = new AddViewHolder(context, R.layout.view_image_simple);
                addViewHolder.setImage(R.id.ivImg, s);
                addViewHolder.getCustomView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击查看大图并设置当前位置
                        ClickBigImageDialog dialog = new ClickBigImageDialog(context, imageList_seller, imageList_seller.indexOf(s));
                        dialog.show();
                    }
                });
                fblImagesSeller.addView(addViewHolder.getCustomView());
            }
        } else {
            fblImagesSeller.setVisibility(View.GONE);
        }
    }

    private String translateImage() {
        String image = "";
        for (ImageFile imageFile : imageFileList) {
            image = image + imageFile.getName() + ",";
        }
        if (image.length() > 0) {
            image.substring(0, image.length() - 1);
        }
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoBottomDialog.SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imagePath = LoadImage.getPath(this, uri);
                    fileToUpload();
                }
                break;
            case PhotoBottomDialog.FLAG_CHOOSE_PHONE:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        imagePath = Constants.APP_DIR + "/" + dialog.getImageName();
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
                    imageFileList.add(imageFile);
                    photoAdapter.notifyDataSetChanged();
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

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionHelper.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dialog.showFileChooser();
            } else {
                TToast.showShort(context, "暂无法选择图片");
            }
        } else if (requestCode == PermissionHelper.USE_CAMERA) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                dialog.doGoToPhone();
            } else {
                TToast.showShort(context, "暂无法使用摄像头");
            }
        }
    }

    @OnClick({R.id.llComplaintGoods, R.id.tvSubmmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llComplaintGoods:
                Intent intent = new Intent(context, GoodDetailsActivity.class);
                intent.putExtra(GoodDetailsActivity.COMMONID, complainDetail.getCommonId());
                startActivity(intent);
                break;
            case R.id.tvSubmmit:
                Map<String, String> params = new HashMap<>();
                params.put("token", application.getToken());
                params.put("complainId", complainId + "");
                params.put("accuserImages", translateImage());

                OkHttpUtil.postAsyn(this,ConstantUrl.URL_MEMBER_COMPLAIN_UPDATE_IMAGE, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        requestComplainInfo();
                    }
                }, params);
                break;
        }
    }

}
