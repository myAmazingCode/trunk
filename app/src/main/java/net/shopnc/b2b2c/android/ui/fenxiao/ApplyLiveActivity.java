package net.shopnc.b2b2c.android.ui.fenxiao;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.ta.utdid2.android.utils.StringUtils;

import net.shopnc.b2b2c.android.BaseActivity;
import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.common.Constants;
import net.shopnc.b2b2c.android.common.DialogHelper;
import net.shopnc.b2b2c.android.common.LoadImage;
import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.common.ShopHelper;
import net.shopnc.b2b2c.android.common.T;
import net.shopnc.b2b2c.android.http.RemoteDataHandler;
import net.shopnc.b2b2c.android.http.ResponseData;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by snm on 2016/9/21.
 */
public class ApplyLiveActivity extends BaseActivity implements View.OnClickListener{

    private EditText etUsername,etcardnum;
    private TextView tv_box_applay;
    private LinearLayout ll_left_add_img,ll_right_add_img;
    private CheckBox cb_box;
    private Context mContext;
    private ImageView iv_right_add_img,iv_left_add_img;
    private int upimg = 1;//1 是左 2是右
//    private String life,right;

    String true_name = null;
    String card_number = null;
    String card_before_image = null;
    String card_behind_image = null;
    String card_before_image_url = null;
    String card_behind_image_url = null;
    String is_agree = null;
    String member_id = null;
    String movie_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applaylive);
        setCommonHeader("主播申请");
        initView();

        mContext = ApplyLiveActivity.this;
        Intent intent = getIntent();
        if(null != intent) {
            true_name = intent.getStringExtra("true_name");
            card_number = intent.getStringExtra("card_number");
            card_before_image = intent.getStringExtra("card_before_image");
            card_behind_image = intent.getStringExtra("card_behind_image");
            card_before_image_url = intent.getStringExtra("card_before_image_url");
            card_behind_image_url = intent.getStringExtra("card_behind_image_url");
            is_agree = intent.getStringExtra("is_agree");
            member_id = intent.getStringExtra("member_id");
            movie_id = intent.getStringExtra("movie_id");
            if(!StringUtils.isEmpty(card_before_image_url)){
                setText2View();
            }
        }
        dialogFile();
    }
    private void initView(){
        etUsername = (EditText)findViewById(R.id.etUsername);
        etcardnum = (EditText)findViewById(R.id.etcardnum);
        tv_box_applay = (TextView) findViewById(R.id.tv_box_applay);
        iv_left_add_img = (ImageView)findViewById(R.id.iv_left_add_img);
        iv_right_add_img = (ImageView)findViewById(R.id.iv_right_add_img);
        ll_left_add_img = (LinearLayout) findViewById(R.id.ll_left_add_img);
        ll_right_add_img = (LinearLayout) findViewById(R.id.ll_right_add_img);
        cb_box = (CheckBox) findViewById(R.id.cb_box);
        tv_box_applay.setOnClickListener(this);
        ll_left_add_img.setOnClickListener(this);
        ll_right_add_img.setOnClickListener(this);
        cb_box.setOnClickListener(this);
    }

   public void setText2View(){
       etUsername.setText(true_name);
       etcardnum.setText(card_number);
       if("1".equals(is_agree)) {
           cb_box.setChecked(true);
       }else {
           cb_box.setChecked(false);
       }
       LoadImage.loadImg(getApplicationContext(),iv_left_add_img,card_before_image_url);
       LoadImage.loadImg(getApplicationContext(),iv_right_add_img,card_behind_image_url);
   }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_box_applay:
                String username = etUsername.getText().toString().trim();
                String card = etcardnum.getText().toString().trim();
                  if(checkapply(username,card)){
                      apply(username,card);
                  }
                break;
            case R.id.ll_left_add_img:
                upimg = 1;
                DialogHelper.setAlpha(ApplyLiveActivity.this,0.6f);
                dialogFile();
                break;
            case R.id.ll_right_add_img:
                upimg = 2;
                DialogHelper.setAlpha(ApplyLiveActivity.this,0.6f);
                dialogFile();
                break;
            case R.id.cb_box:

                break;
            case R.id.btn_photo:
                ll_right_add_img.setClickable(false);
                ll_left_add_img.setClickable(false);
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, 1);
                popDismiss();
                break;
            case R.id.btn_photobyalbum:
                ll_right_add_img.setClickable(false);
                ll_left_add_img.setClickable(false);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 108);
                popDismiss();
                break;
            case R.id.btn_cancle:
                popDismiss();
                break;
        }
    }

    private void popDismiss() {
        DialogHelper.setAlpha(ApplyLiveActivity.this,1.0f);
        if(null != FilePopWindow) {
            FilePopWindow.dismiss();
        }
    }

    private PopupWindow FilePopWindow;//时间对话
    private View FileView;  //时间
    //  弹出选择 文件dialog
    public void dialogFile() {
        if (FilePopWindow == null) {
            FileView = this.getLayoutInflater().inflate(R.layout.comment_photo_dialog, null);
            FilePopWindow = getFilePopWindow(FileView);
        } else {
            if (!FilePopWindow.isShowing()) {
                FilePopWindow.showAtLocation(FileView, Gravity.RIGHT | Gravity.BOTTOM, 0, 0);
            }
        }
    }
    /*文件pop*/
    private PopupWindow getFilePopWindow(View view) {
        final PopupWindow popupWindow = DialogHelper.getPopupWindow(ApplyLiveActivity.this,view);

        Button btn_photo = view.findViewById(R.id.btn_photo);
        Button btn_photobyalbum = view.findViewById(R.id.btn_photobyalbum);

        Button btn_cancle = view.findViewById(R.id.btn_cancle);
        btn_photo.setOnClickListener(this);
        btn_photobyalbum.setOnClickListener(this);
        btn_cancle.setOnClickListener(this);
        return popupWindow;
    }

    public boolean checkapply(String username,String card){

        if(StringUtils.isEmpty(username)){
            T.showShort(getApplicationContext(),"真实不能为空");
            return false;
        }
        if(StringUtils.isEmpty(card)){
            T.showShort(getApplicationContext(),"身份证不能为空");
            return false;
        }
//        if(!Utils.check(getApplicationContext(),card)){
//            return false;
//        }
        if(!cb_box.isChecked()){
            T.showShort(getApplicationContext(),"是否同意主播公约");
            return false;
        }
        if(StringUtils.isEmpty(card_before_image)){
            T.showShort(getApplicationContext(),"身份证正面照");
            return false;
        }
        if(StringUtils.isEmpty(card_behind_image)){
            T.showShort(getApplicationContext(),"身份证反面照");
            return false;
        }

        return true;
    }
    public void apply(String username,String card){
        String url = Constants.URL_MEMBER_MOVIE;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        params.put("true_name", username);
        params.put("card_number", card);
        params.put("card_before_image", card_before_image);
        params.put("card_behind_image", card_behind_image);
        params.put("movie_id", movie_id);
        if(cb_box.isChecked()) {
            params.put("is_agree", "1");
        }else {
            params.put("is_agree", "0");
        }
        Logger.d(params.toString());
        RemoteDataHandler.asyncPostDataString(url, params, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                Logger.d(data.getJson());
                if (data.getCode() == HttpStatus.SC_OK)  {
                    try {
                        JSONObject obj = new JSONObject(data.getJson());
                        String state = obj.optString("state");
                        String movie_id = obj.optString("movie_id");
                        T.showShort(getApplicationContext(),state);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    ShopHelper.showApiError(getApplicationContext(), data.getJson());
                }
            }
        });
    }
    /**
     * 上传图片
     * @param file
     */
    public void uploadImage(final String path,File file) {
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("key", MyShopApplication.getInstance().getLoginKey());
        HashMap<String, File> fileMap = new HashMap<String, File>();
        fileMap.put("file", file);

        RemoteDataHandler.asyncMultipartPostString(Constants.URL_FILE_UPLOAD, params, fileMap, new RemoteDataHandler.Callback() {
            @Override
            public void dataLoaded(ResponseData data) {
                String json = data.getJson();
                Logger.d(json);
                if (data.getCode() == HttpStatus.SC_OK)  {
                    try {
                        popDismiss();
                        T.showShort(getApplicationContext(),"上传成功");
                        JSONObject obj = new JSONObject(json);
//                        int file_id = obj.optInt("file_id");
                        String file_name = obj.optString("file_name");
//                        String origin_file_name = obj.optString("origin_file_name");
                        String file_url = obj.optString("file_url");
//                        ImageFile imageFile = new ImageFile();
//                        imageFile.setFile_id(file_id);
//                        imageFile.setFile_name(file_name);
//                        imageFile.setFile_url(file_url);
//                        imageFile.setOrigin_file_name(origin_file_name);
                        if(1 == upimg){
                            LoadImage.loadImg(getApplicationContext(),iv_left_add_img,file_url);
                            card_before_image = file_name;
                        }else {
                            card_behind_image = file_name;
                            LoadImage.loadImg(getApplicationContext(),iv_right_add_img,file_url);
                        }
                        ll_right_add_img.setClickable(true);
                        ll_left_add_img.setClickable(true);
                    } catch (JSONException e) {
                        ll_right_add_img.setClickable(true);
                        ll_left_add_img.setClickable(true);
                        e.printStackTrace();
                    }
                } else {
                    ll_right_add_img.setClickable(true);
                    ll_left_add_img.setClickable(true);
                    T.showShort(getApplicationContext(), "文件上传失败");
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 108:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        Logger.e(uri.toString());
                        String path = getRealPathFromURI(uri);
                        try {
                            getPathUpload(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case 1:

                Bundle extras = data.getExtras();
                Bitmap b = (Bitmap) extras.get("data");
                String name = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                String fileNmae = Environment.getExternalStorageDirectory().toString()+File.separator+"dong/image/"+name+".jpg";
                Uri uri = MediaStore.Audio.Media.getContentUriForPath(fileNmae);

                File myCaptureFile = new File(fileNmae);
                String mimeType = "image/*";
                ContentValues values = new ContentValues(); values.put(MediaStore.MediaColumns.DATA, myCaptureFile.getAbsolutePath());
                values.put(MediaStore.MediaColumns.TITLE, myCaptureFile.getName());
                values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
                values.put(MediaStore.MediaColumns.SIZE, myCaptureFile.length());

                this.getContentResolver().insert(uri,values);
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        if(!myCaptureFile.getParentFile().exists()){
                            myCaptureFile.getParentFile().mkdirs();
                        }
                        BufferedOutputStream bos;
                        bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                        b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                        getPathUpload(fileNmae);
                        bos.flush();
                        bos.close();
                    }else{

                        Toast toast= Toast.makeText(getApplicationContext(), "保存失败，SD卡无效", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private void getPathUpload(String path) {
        if(path!=null){
            File file = new File(path);
            if(file.exists()){
                if (file.length() < 1024 * 1024 * 3) {
                    Logger.e(file.getAbsolutePath());
                    uploadImage(path,file);
                } else {
                    T.showShort(getApplicationContext(), "图片文件过大，请上传3M以下的图片");
                }
            } else {
                T.showShort(getApplicationContext(), "文件不存在");
            }

        }else {
            T.showShort(getApplicationContext(), "地址不存在");
        }
    }
}
