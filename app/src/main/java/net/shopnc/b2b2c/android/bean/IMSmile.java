package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/8/9 15:08.
 */
public class IMSmile {
    private String name;
    private String title;
    private int path;

    public IMSmile(String name, String title, int path) {
        this.name = name;
        this.title = title;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
