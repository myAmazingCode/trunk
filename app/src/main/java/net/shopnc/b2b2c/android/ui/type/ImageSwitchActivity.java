package net.shopnc.b2b2c.android.ui.type;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.adapter.ImageSwitchPagerAdapter;
import net.shopnc.b2b2c.android.common.MyExceptionHandler;

import java.util.ArrayList;

public class ImageSwitchActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ImageButton btnBack;
    private ViewPager vpImage;
    private ArrayList<String> imageList;
    private int index;

    //装点点的ImageView数组
    private ImageView[] tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switch);
        MyExceptionHandler.getInstance().setContext(this);
        ViewGroup group = findViewById(R.id.viewGroup);
        vpImage = findViewById(R.id.vpImage);

        imageList = getIntent().getStringArrayListExtra("image_list");
        index = getIntent().getIntExtra("image_index", 0);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //将点点加入到ViewGroup中
        tips = new ImageView[imageList.size()];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.nc_page_on);
            } else {
                tips[i].setBackgroundResource(R.drawable.nc_page_off);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }

        vpImage.setAdapter(new ImageSwitchPagerAdapter(ImageSwitchActivity.this, imageList));
        vpImage.setOnPageChangeListener(this);
        vpImage.setCurrentItem(index);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % imageList.size());
    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.nc_page_on);
            } else {
                tips[i].setBackgroundResource(R.drawable.nc_page_off);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
