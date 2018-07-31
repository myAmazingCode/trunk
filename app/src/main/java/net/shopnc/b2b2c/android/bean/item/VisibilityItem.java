package net.shopnc.b2b2c.android.bean.item;

import android.graphics.Rect;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.volokh.danylo.visibility_utils.items.ListItem;

import net.shopnc.b2b2c.android.R;
import net.shopnc.b2b2c.android.bean.NewsListBean;

import tcking.github.com.jcvideo.JCVideoPlayer;
import tcking.github.com.jcvideo.JCVideoPlayerStandard;

/**
 * Created by snm on 2016/9/9.
 */
public class VisibilityItem implements ListItem {

    private final Rect mCurrentViewRect = new Rect();
//    private final ItemCallback mItemCallback;

    public NewsListBean newsListBean;

    public VisibilityItem(NewsListBean bean) {
        this.newsListBean = bean;
//        mItemCallback = callback;
    }

    int parcents  = 0;

    @Override
    public int getVisibilityPercents(View view) {
        int percents = 100;
        if(view != null) {
           view.getLocalVisibleRect(mCurrentViewRect);

           int height = view.getHeight();

           if (viewIsPartiallyHiddenTop()) {
               // view is partially hidden behind the top edge
               percents = (height - mCurrentViewRect.top) * 100 / height;
           } else if (viewIsPartiallyHiddenBottom(height)) {
               percents = mCurrentViewRect.bottom * 100 / height;
           }
        }
        parcents = percents;
        return percents;
    }

    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {
        JCVideoPlayerStandard videoPlayerStandard1 = newActiveView.findViewById(R.id.videoplayer);

        if (videoPlayerStandard1 != null)
        {
            if (videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayerStandard1.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
                Logger.e(videoPlayerStandard1.currentState + "======================performClick======================" + parcents);
//                if(parcents == 100) {
                    videoPlayerStandard1.startButton.performClick();
//                }
            }
            return;
        }
    }
    /*停用*/
    @Override
    public void deactivate(View currentView, int position) {
// // STOPSHIP: 2016/9/9
        JCVideoPlayer.releaseAllVideos();
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }



//    public interface ItemCallback {
//        void makeToast(String text);
//        void onActiveViewChangedActive(View newActiveView, int newActiveViewPosition);
//    }
}
