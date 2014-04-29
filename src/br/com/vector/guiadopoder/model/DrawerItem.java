package br.com.vector.guiadopoder.model;

public class DrawerItem {

	String ItemName;
	String title;
	private String searchText;
	int imgResID;

	public DrawerItem(String itemName, int imgResID) {
		ItemName = itemName;
		this.imgResID = imgResID;
	}

	public DrawerItem(String title) {
		this(null, 0);
		this.title = title;
	}
	
	public DrawerItem(int imgResID,String searchText){
		this.searchText = searchText;
		this.imgResID = imgResID;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
