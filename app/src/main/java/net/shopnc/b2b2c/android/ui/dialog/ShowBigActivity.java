package net.shopnc.b2b2c.android.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.shopnc.b2b2c.R;
import net.shopnc.b2b2c.android.common.AddViewHolder;

/**
 * Created by shopnc on 2016/5/23.
 */
public class ShowBigActivity extends Activity implements View.OnClickListener {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_big);

        url = getIntent().getStringExtra("url");
        AddViewHolder holder = new AddViewHolder(this, R.id.show_big_imageView);
        holder.setImage(R.id.show_big_imageView, url);
        holder.getView(R.id.show_big_imageView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
