package net.shopnc.b2b2c.android.bean;

/**
 * @Description 分页参数实体
 * @Author qyf
 *
 * Created 2016/4/14 11:56.
 */
public class PageEntity {
    private boolean hasMore;
    private int totalPage;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public PageEntity(boolean hasMore, int totalPage) {

        this.hasMore = hasMore;
        this.totalPage = totalPage;
    }

    public PageEntity() {

    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "hasMore=" + hasMore +
                ", totalPage=" + totalPage +
                '}';
    }
}

