package net.shopnc.b2b2c.android.ui.promotion;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.base.BaseActivity;
import net.shopnc.b2b2c.android.bean.ImageFile;
import net.shopnc.b2b2c.android.common.PermissionHelper;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.custom.PhotoBottomDialog;
import net.shopnc.b2b2c.android.custom.TToast;
import net.shopnc.b2b2c.android.util.Constants;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

public class AuthenticationStep1Activity extends BaseActivity implements AuthImageView.OnImageUploadListener {

    @Bind(R.id.tvStep1)
    TextView mTvStep1;
    @Bind(R.id.tvStep2)
    TextView mTvStep2;
    @Bind(R.id.tvStep3)
    TextView mTvStep3;
    @Bind(R.id.tvIndicatorStep1)
    TextView mTvIndicatorStep1;
    @Bind(R.id.etName)
    EditText mEtName;
    @Bind(R.id.etPhone)
    EditText mEtPhone;
    @Bind(R.id.etID)
    EditText mEtID;
    @Bind(R.id.aivFront)
    AuthImageView mAivFront;
    @Bind(R.id.aivBack)
    AuthImageView mAivBack;
    @Bind(R.id.aivHand)
    AuthImageView mAivHand;
    @Bind(R.id.tvSwitch)
    TextView mTvSwitch;
    @Bind(R.id.tvAgreement)
    TextView mTvAgreement;
    @Bind(R.id.tvNextStep)
    TextView mTvNextStep;
    private boolean mInputOk;//输入信息完成
    private boolean mImageOk;//上传身份证完成
    private PhotoBottomDialog dialog;
    private int mFlag;
    //    private String imagePath;
    private String imageName;

    private String idCartFrontImage;
    private String idCartBackImage;
    private String idCartHandImage;
    private File mTrimfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCommonHeader("个人实名认证");

        //初始化界面
        initView();
        //初始化输入监听器
        initTextWatcher();
    }

    private void initTextWatcher() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mInputOk = !TextUtils.isEmpty(mEtName.getText().toString()) &&
                        !TextUtils.isEmpty(mEtPhone.getText().toString()) &&
                        !TextUtils.isEmpty(mEtID.getText().toString());

                checkState();
            }
        };

        mEtName.addTextChangedListener(watcher);
        mEtPhone.addTextChangedListener(watcher);
        mEtID.addTextChangedListener(watcher);
    }

    //检查所有输入信息
    private void checkState() {
        if (mInputOk && mTvSwitch.isSelected() && mImageOk) {
            mTvNextStep.setEnabled(true);
        } else {
            mTvNextStep.setEnabled(false);
        }
    }

    private void initView() {
        mTvStep1.setSelected(true);
        mTvIndicatorStep1.setVisibility(View.VISIBLE);
        mAivBack.setTitle("身份证反面照", "上传反面照");
        mAivHand.setTitle("手持身份证照", "上传手持身份证照");
        mAivHand.setDesc("拍照时请对焦在证件上(屏幕上对着身份证按一下)", "请严格按照范例姿势拍照，否则不会通过审核！");
        mAivHand.setImageIndicator(R.drawable.join_inhand_faq);

        mAivFront.setOnImageUploadListener(this, AuthImageView.FLAG_FRONT);
        mAivBack.setOnImageUploadListener(this, AuthImageView.FLAG_BACK);
        mAivHand.setOnImageUploadListener(this, AuthImageView.FLAG_HAND);
    }

    @Override
    protected void onDestroy() {
        //清除第二步保存的sp数据
        SharedPreferences sp = getSharedPreferences("auth", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("payPerson");
        editor.remove("bankAccountNumber");
        editor.remove("bankAccountName");
        editor.remove("accountType");
        editor.apply();
        super.onDestroy();
    }

    @OnClick({R.id.tvSwitch, R.id.tvNextStep, R.id.tvAgreement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSwitch:
                mTvSwitch.setSelected(!mTvSwitch.isSelected());
                checkState();
                break;
            case R.id.tvNextStep:
                verifyInputInfo();
                break;
            case R.id.tvAgreement:
                startActivity(new Intent(this, PromotionAgreementActivity.class));
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoBottomDialog.SELELCT_FILE_TO_UPLOAD_ITEM:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    imageName = System.currentTimeMillis() + PhotoBottomDialog.getExtensionName(this, uri);//解决部分图片格式不能被解析
                    if (PhotoBottomDialog.getFileFromUri(this, uri, imageName)) {
                        fileToUpload();
                    }
                }
                break;
            case PhotoBottomDialog.FLAG_CHOOSE_PHONE:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        imageName = dialog.getImageName();
                        fileToUpload();
                    }
                }
                break;
            case 300:
                if (data != null)
                    fileToUpload();
                break;
        }
    }

    public static final String TAG = "Authentication";

    private void fileToUpload() {
        Log.d(TAG, "fileToUpload: imageName = " + imageName);
//        File file = new File(Constants.APP_DIR + "/" + imageName);

        File path = new File(Constants.APP_DIR);
        if (!path.exists()) {
            path.mkdirs();
        }

        final File file = new File(path, imageName);

        if (file.length() >= 1024 * 1024) {
            TToast.showShort(context, "图片文件过大，请上传1M以下的图片");
            trimPhoto(Uri.fromFile(file));
        } else {
            ShopHelper.postImage(context, file, new ShopHelper.PostImage() {
                @Override
                public void postImageSuccess(ImageFile imageFile) {
                    Log.d(TAG, "postImageSuccess: imageFile = " + imageFile + ",mFlag = " + mFlag);

//                    mTrimfile = null;//将裁剪图片置空
                    String url = imageFile.getUrl();
                    String name = imageFile.getName();

                    switch (mFlag) {
                        case AuthImageView.FLAG_FRONT:
                            mAivFront.setImageRes(url);
                            idCartFrontImage = name;
                            break;
                        case AuthImageView.FLAG_BACK:
                            mAivBack.setImageRes(url);
                            idCartBackImage = name;
                            break;
                        case AuthImageView.FLAG_HAND:
                            mAivHand.setImageRes(url);
                            idCartHandImage = name;
                            break;

                    }


                    mImageOk = !TextUtils.isEmpty(idCartBackImage) && !TextUtils.isEmpty(idCartFrontImage) && !TextUtils.isEmpty(idCartHandImage);

                    checkState();
                }
            });
        }
    }


    //裁剪照片
    public void trimPhoto(Uri uri) {
        imageName = "trim_" + System.currentTimeMillis() + ".jpg";
        File path = new File(Constants.APP_DIR);
        if (!path.exists()) {
            path.mkdirs();
        }

        File trimfile = new File(path, imageName);


        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("output", Uri.fromFile(trimfile));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);

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

    private void verifyInputInfo() {
        String realName = mEtName.getText().toString();
        String bindPhone = mEtPhone.getText().toString();
        if (bindPhone.length() != 11) {
            TToast.showShort(this, "手机号格式错误");
            return;
        }

        String idCartNumber = mEtID.getText().toString();
        if (idCartNumber.length() != 18) {
            TToast.showShort(this, "身份证格式错误");
            return;
        }

        String agree = "1";


        Intent intent = new Intent(this, AuthenticationStep2Activity.class);
        intent.putExtra("realName", realName);
        intent.putExtra("bindPhone", bindPhone);
        intent.putExtra("idCartNumber", idCartNumber);
        intent.putExtra("idCartFrontImage", idCartFrontImage);
        intent.putExtra("idCartBackImage", idCartBackImage);
        intent.putExtra("idCartHandImage", idCartHandImage);
        startActivity(intent);
    }

    @Override
    protected void setView() {
        setContentView(R.layout.activity_step1_authentication);
    }

    @Override
    public void onImageUpload(int flag) {
        mFlag = flag;
        if (dialog == null) {
            dialog = new PhotoBottomDialog(this);
        }

        dialog.show();
    }
}
