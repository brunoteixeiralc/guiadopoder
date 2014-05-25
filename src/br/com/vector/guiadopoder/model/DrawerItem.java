package br.com.vector.guiadopoder.model;

public class DrawerItem {

	String ItemName;
	String title;
	String searchText;
	int imgResID;
	int imgResLogoAbout;
	String color;

	public DrawerItem(String itemName, int imgResID,String color) {
		ItemName = itemName;
		this.imgResID = imgResID;
		this.color = color;
	}

	public DrawerItem(String title) {
		this.title = title;
	}
	
	public DrawerItem(int imgResID,String searchText){
		this.searchText = searchText;
		this.imgResID = imgResID;
	}
	
	public DrawerItem(int imgResLogoAbout) {
		this.imgResLogoAbout = imgResLogoAbout;
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

	public int getImgResLogoAbout() {
		return imgResLogoAbout;
	}

	public void setImgResLogoAbout(int imgResLogoAbout) {
		this.imgResLogoAbout = imgResLogoAbout;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
