package net.shopnc.b2b2c.android.bean;

/**
 * 表情列表
 * @author KingKong·HE
 * @Time 2014年6月3日 下午2:37:44
 */
public class SmiliesList {
	
	private String name;
	private String title;
	private int path;
	public SmiliesList() {
	}
	
	public SmiliesList(String name, String title, int path) {
		super();
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
	@Override
	public String toString() {
		return "SmiliesList [name=" + name + ", title=" + title + ", path="
				+ path + "]";
	}

	
	
}
