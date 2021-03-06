package com.edu.bean;

import android.graphics.Bitmap;

public class Birth_info_item {
	//id
	private int id;
	//头像
	private Bitmap photo;
	//姓名
	private String name;
	//年
	private int year;
	//月
	private int month;
	//日
	private int day;
	//生肖
	private String animal;
	//星座
	private String constellation;
	//年龄
	private int age;
	//性别
	private String sex;
	//备注
	private String beizhuInfo;
	//电话
	private String phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBeizhuInfo() {
		return beizhuInfo;
	}
	public void setBeizhuInfo(String beizhuInfo) {
		this.beizhuInfo = beizhuInfo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
