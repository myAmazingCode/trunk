package net.shopnc.b2b2c.android.common.adapter;

import java.util.HashMap;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/5 17:40.
 */
public interface MultiRecyclerItemSupport<T> {

    int getItemViewType(T t);

    HashMap<Integer, Integer> getLayoutMap();
}
