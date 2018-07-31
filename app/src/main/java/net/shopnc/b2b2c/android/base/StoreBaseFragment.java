package net.shopnc.b2b2c.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.ypy.eventbus.EventBus;

import net.shopnc.b2b2c.android.common.MyShopApplication;
import net.shopnc.b2b2c.android.ui.store.StoreActivitiesFragment;
import net.shopnc.b2b2c.android.ui.store.StoreIndexFragment;
import net.shopnc.b2b2c.android.ui.store.StoreNewGoodsFragment;
import net.shopnc.b2b2c.android.ui.store.StoreSearchFragment;

import butterknife.ButterKnife;

/**
 * 店铺中fragment的基类
 *
 * @author huting
 * @date 2016/4/12
 */
public class StoreBaseFragment extends Fragment {
    protected static final String ARG_STORE_ID = "storeId";

    protected static String storeId;
    protected MyShopApplication application;
    protected Context context;
    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            storeId = getArguments().getString(ARG_STORE_ID);
            application = MyShopApplication.getInstance();
            context = this.getActivity();
        }
    }


    public static Fragment newInstance(String tag, String storeId) {
        Fragment fragment = null;
        if (tag.equals("index")) {
            fragment = new StoreIndexFragment();
            getParams(fragment, storeId);

        } else if (tag.equals("goods")) {
            fragment = new StoreSearchFragment();
            Bundle b = new Bundle();
            b.putString("storeId", storeId);
            fragment.setArguments(b);
        } else if (tag.equals("new")) {
            fragment = new StoreNewGoodsFragment();
            getParams(fragment, storeId);

        } else if (tag.equals("activity")) {
            fragment = new StoreActivitiesFragment();
            getParams(fragment, storeId);
        }

        return fragment;
    }

    public static void getParams(Fragment fragment, String storeId) {
        Bundle args = new Bundle();
        args.putString(ARG_STORE_ID, storeId);
        fragment.setArguments(args);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
