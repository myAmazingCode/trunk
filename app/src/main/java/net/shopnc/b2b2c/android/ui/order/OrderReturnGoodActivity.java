package net.shopnc.b2b2c.android.ui.order;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.adapter.EvaluatePhotoAdapter;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.bean.OrdersGoodsVo;
import net.shopnc.b2b2c.android.bean.OrdersVo;
import net.shopnc.b2b2c.android.bean.RefundReasonListItem;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.adapter.CommonAdapter;
import net.shopnc.b2b2c.android.common.adapter.ViewHolder;
import net.shopnc.b2b2c.android.common.http.JsonUtil;
import net.shopnc.b2b2c.android.custom.MyGridView;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.custom.dialog.NCDialog;
import net.shopnc.b2b2c.android.custom.dialog.NCDialogConfirm;
import net.shopnc.b2b2c.android.util.BeanCallback;
import net.shopnc.b2b2c.android.util.ConstantUrl;
import net.shopnc.b2b2c.android.util.Constants;
import net.shopnc.b2b2c.android.util.LoadImage;
import net.shopnc.b2b2c.android.util.OkHttpUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderReturnGoodActivity extends BaseActivity {

    @Bind(R.id.tvStoreName)
    TextView tvStoreName;
    @Bind(R.id.ivGoodPic)
    ImageView ivGoodPic;
    @Bind(R.id.tvGoodName)
    TextView tvGoodName;
    @Bind(R.id.tvGoodsSPec)
    TextView tvGoodsSPec;
    @Bind(R.id.tvGoodPrice)
    TextView tvGoodPrice;
    @Bind(R.id.tvGoodsNum)
    TextView tvGoodsNum;
    @Bind(R.id.btnOrderMoney)
    TextView btnOrderMoney;
    @Bind(R.id.btnOrderGood)
    TextView btnOrderGood;
    @Bind(R.id.llOrderRefund)
    LinearLayout llOrderRefund;
    @Bind(R.id.tvReason)
    TextView tvReason;
    @Bind(R.id.llReason)
    LinearLayout llReason;
    @Bind(R.id.etRefundPrice)
    EditText etRefundPrice;
    @Bind(R.id.tvMaxPrice)
    TextView tvMaxPrice;
    @Bind(R.id.etRefundNum)
    EditText etRefundNum;
    @Bind(R.id.tvMaxNum)
    TextView tvMaxNum;
    @Bind(R.id.etBuyerMessage)
    EditText etBuyerMessage;
    @Bind(R.id.gvPhoto)
    MyGridView gvPhoto;
    @Bind(R.id.btnCommit)
    Button btnCommit;

    private int ordersId;
    private int ordersGoodsId;
    private OrdersGoodsVo ordersGoodsVo;
    private OrdersVo ordersVo;
    private List<RefundReasonListItem> reasonList;
    private EvaluatePhotoAdapter photoAdapter;
    private List<ImageFile> imageList;
    private PhotoBottomDialog dialog;
    private NCDialog ncDialog;
    private String moneyRmb;
    private String goodNumFlag;
    private String imagePath;
    private int selectedReasonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("商品退货");
        setBtnMoreVisible();
        ordersId = getIntent().getIntExtra("ordersId", 0);
        ordersGoodsId = getIntent().getIntExtra("ordersGoodsId", 0);
        moneyRmb = context.getResources().getString(R.string.money_rmb);
        goodNumFlag = context.getResources().getString(R.string.good_num);
        dialog = new PhotoBottomDialog(context);
        imageList = new ArrayList<>();
        photoAdapter = new EvaluatePhotoAdapter(context, 3);
        photoAdapter.setImageFileList(imageList);
        gvPhoto.setAdapter(photoAdapter);
        requestDetails();
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_order_refund_good);
    }

    private void requestDetails() {
        HashMap<String, String> params = new HashMap();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");
        params.put("ordersGoodsId", ordersGoodsId + "");
        OkHttpUtil.postAsyn(this,ConstantUrl.URL_SINGLE_GOOD_RETURN, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                ordersGoodsVo = JsonUtil.toBean(data, "ordersGoodsVo", new TypeToken<OrdersGoodsVo>() {
                }.getType());
                ordersVo = JsonUtil.toBean(data, "ordersVo", new TypeToken<OrdersVo>() {
                }.getType());
                reasonList = JsonUtil.toBean(data, "refundReasonList", new TypeToken<ArrayList<RefundReasonListItem>>() {
                }.getType());
                bindData();
            }
        }, params);
    }

    private void bindData() {
        tvStoreName.setText("  " + ordersVo.getStoreName() + "  ");
        LoadImage.loadRemoteImg(context, ivGoodPic, ordersGoodsVo.getImageSrc());
        tvGoodName.setText(ordersGoodsVo.getGoodsName());
        tvGoodsSPec.setText(ordersGoodsVo.getGoodsFullSpecs());
        tvGoodPrice.setText(moneyRmb + ShopHelper.getPriceString(ordersGoodsVo.getGoodsPrice()));
        tvGoodsNum.setText(goodNumFlag + ordersGoodsVo.getBuyNum());
        tvReason.setText(reasonList.get(0).getReasonInfo());
        selectedReasonId = reasonList.get(0).getReasonId();
        etRefundPrice.setText(ShopHelper.getPriceString(ordersGoodsVo.getGoodsPayAmount()));
        tvMaxPrice.setText(moneyRmb + ShopHelper.getPriceString(ordersGoodsVo.getGoodsPayAmount()));
        etRefundNum.setText(ordersGoodsVo.getBuyNum() + "");
        tvMaxNum.setText("最多" + ordersGoodsVo.getBuyNum() + ordersGoodsVo.getUnitName());
        gvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (imageList.size() == 0 || position == imageList.size()) {
                    dialog.show();
                } else {
                    ncDialog = new NCDialog(context);
                    ncDialog.setText1("确认删除该图片?");
                    ncDialog.setOnDialogConfirm(new NCDialogConfirm() {
                        @Override
                        public void onDialogConfirm() {
                            imageList.remove(position);
                            photoAdapter.notifyDataSetChanged();
                        }
                    });
                    ncDialog.showPopupWindow();
                }
            }
        });
    }


    @OnClick({R.id.llReason, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llReason:
                final AlertDialog dialog = new AlertDialog.Builder(context).create();
                View v = LayoutInflater.from(context).inflate(R.layout.follow_bottom_dialog, null);
                ListView listView = v.findViewById(R.id.llChoosens);
                dialog.setView(v);
                dialog.show();
                listView.setAdapter(new CommonAdapter<RefundReasonListItem>(context, reasonList, R.layout.follow_botton_dialog_item) {
                    @Override
                    public void convert(ViewHolder holder, final RefundReasonListItem reasonItem) {
                        Button btnSortView = holder.getView(R.id.btnSortView);
                        btnSortView.setText(reasonItem.getReasonInfo());

                        btnSortView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                selectedReasonId = reasonItem.getReasonId();
                                tvReason.setText(reasonItem.getReasonInfo());
                                dialog.dismiss();
                            }
                        });
                    }
                });
                break;
            case R.id.btnCommit:
                refundCommit();
                break;
        }
    }

    private void refundCommit() {
        String inputMaxPrice = etRefundPrice.getText().toString().trim();
        if (inputMaxPrice.length() == 0 || new BigDecimal(inputMaxPrice).compareTo(new BigDecimal(0)) == 0 || new BigDecimal(inputMaxPrice).compareTo(ordersGoodsVo.getGoodsPayAmount()) == 1) {
            TToast.showShort(context, "退货金额不能为空，或不能超过可退金额");
            return;
        }
        String inputMaxNum = etRefundNum.getText().toString().trim();
        if (inputMaxNum.length() == 0 || Integer.valueOf(inputMaxNum) == 0 || Integer.valueOf(inputMaxNum) > ordersGoodsVo.getBuyNum()) {
            TToast.showShort(context, "退货数量不能为空，或不能超过可退数量");
            return;
        }
        String buyerMessage = etBuyerMessage.getText().toString();
        if (buyerMessage.length() == 0) {
            TToast.showShort(context, "请填写退货说明");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("token", application.getToken());
        params.put("ordersId", ordersId + "");
        params.put("picJson", translateImage());
        params.put("buyerMessage", buyerMessage);
        params.put("ordersGoodsId", ordersGoodsId + "");
        params.put("reasonId", selectedReasonId + "");
        params.put("refundAmount", inputMaxPrice);
        params.put("goodsNum", inputMaxNum);

        OkHttpUtil.postAsyn(this,ConstantUrl.URL_SINGLE_GOOD_RETURN_SAVE, new BeanCallback<String>() {
            @Override
            public void response(String data) {
                Intent intent = new Intent(activity, OrderRefundAndReturnListActivity.class);
                intent.putExtra("flag", "return");
                startActivity(intent);
                finish();
            }
        }, params);
    }

    private String translateImage() {
        String image = "";
        for (ImageFile imageFile : imageList) {
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
                    imageList.add(imageFile);
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

}
