package net.shopnc.b2b2c.android.bean;

/**
 * 个人中心分类
 * @author KingKong-HE
 * @Time 2015-1-12
 * @Email KingKong@QQ.COM
 */
public class MineType {
	
	private int name;
	
	private int imageURL;
	
	private String description;
	
	public MineType() {}

	public MineType(int name, int imageURL, String description) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.description = description;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getImageURL() {
		return imageURL;
	}

	public void setImageURL(int imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MineType [name=" + name + ", imageURL=" + imageURL
				+ ", description=" + description + "]";
	}
	
}
