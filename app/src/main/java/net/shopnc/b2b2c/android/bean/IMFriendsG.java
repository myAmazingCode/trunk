package net.shopnc.b2b2c.android.bean;

/**
 * 
 * @author KingKong-HE
 * @Time 2015-2-10
 * @Email KingKong@QQ.COM
 */
public class IMFriendsG {

	private String name ;
	private int id ;
	
	public IMFriendsG() {
	}
	
	public IMFriendsG(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IMFriendsG [name=" + name + ", id=" + id + "]";
	}
}
