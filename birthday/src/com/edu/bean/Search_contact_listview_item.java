package com.edu.bean;

import android.graphics.Bitmap;


/*****
 * 
 * 界面listviewItem
 * 
 * @author bing
 *
 */
public class Search_contact_listview_item {
	//索引
	String suoyin;
	//头像
	Bitmap photo;
	//姓名
	String name;
	//电话
	String phone;
	
	public String getSuoyin() {
		return suoyin;
	}
	public void setSuoyin(String suoyin) {
		this.suoyin = suoyin;
	}
	public Bitmap getPhoto() {
		return photo;
	}
	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
