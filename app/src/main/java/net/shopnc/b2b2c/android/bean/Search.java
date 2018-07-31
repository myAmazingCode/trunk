package net.shopnc.b2b2c.android.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 商品搜索页面
 * @author KingKong-HE
 * @Time 2015-2-2
 * @Email KingKong@QQ.COM
 */
//表名称
@DatabaseTable(tableName = "shop_search")
public class Search {
	// 主键 id 自增长
	@DatabaseField(generatedId = true)
	private int id;
	// 映射
	@DatabaseField(canBeNull = false)
	private String searchKeyWord;
	
	
	public Search() {
	}
	
	public Search(String searchKeyWord) {
		super();
		this.searchKeyWord = searchKeyWord;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	@Override
	public String toString() {
		return "Search [id=" + id + ", searchKeyWord=" + searchKeyWord + "]";
	}
}
