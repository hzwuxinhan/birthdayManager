package com.edu.bean;

import android.graphics.Bitmap;


/*****
 * 
 * ����listviewItem
 * 
 * @author bing
 *
 */
public class Search_contact_listview_item {
	//����
	String suoyin;
	//ͷ��
	Bitmap photo;
	//����
	String name;
	//�绰
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