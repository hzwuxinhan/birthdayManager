package com.edu.birthday;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.edu.bean.Search_contact_listview_item;
import com.edu.interface1.OnIndex;
import com.edu.util.PinyinUtil;
import com.edu.view.Index;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class search_contact extends Activity {

	//界面控件
	private Button search_contact_title_back;
	private ListView search_contact_listview;
	private Index search_contact_index;
	private TextView search_contact_suoyin;
	
	//手机联系人获取
	private static final String[] PHONES_PROJECTION = new String[]{
		Phone.DISPLAY_NAME,Phone.NUMBER,Phone.PHOTO_ID,Phone.CONTACT_ID
	};
	
	//其他
	private Search_contact_adapter search_contact_adapter;
	private List<Search_contact_listview_item> listItems = new ArrayList<Search_contact_listview_item>();
	private Bitmap defaultBitmap;
	
	private boolean search_contact_for_name,search_contact_for_phone;
	private boolean send_sms_search_contact_for_phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_contact);
		bundle();
		dateInit();
		viewInit();
	}
	
	private void bundle(){
		Intent intent = getIntent();
		search_contact_for_name = intent.getBooleanExtra("search_contact_for_name", false);
		search_contact_for_phone = intent.getBooleanExtra("search_contact_for_phone", false);
		send_sms_search_contact_for_phone = intent.getBooleanExtra("send_sms_search_contact_for_phone", false);
	}
	
	private void dateInit(){
		defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.defaultboy);
		Long photoId;
		Long contactId;
		ContentResolver contentResolver = this.getContentResolver();
		//获取手机联系人
		Cursor cursor = contentResolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		if(cursor.getColumnCount() != 0){
			cursor.moveToPosition(0);
			while(true){
				Search_contact_listview_item item = new Search_contact_listview_item();
				if(cursor.isAfterLast()){
					break;
				}else{
					if(cursor.getString(1) == null){
						continue;
					}
					if(cursor.getString(0) == null){
						continue;
					}
					item.setName(cursor.getString(0));
					item.setSuoyin(PinyinUtil.converToFirstSpell(cursor.getString(0).substring(0, 1)));
					item.setPhone(cursor.getString(1));
					photoId = cursor.getLong(2);
					contactId = cursor.getLong(3);
					//photoId大于0表示有联系人头像 如果没有则给头像一个默认的
					if(photoId > 0){
						Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
						InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
						item.setPhoto(BitmapFactory.decodeStream(input));
					}else{
						item.setPhoto(defaultBitmap);
					}
				}
				listItems.add(item);
				cursor.moveToNext();
			}
		}
		cursor.close();
		
		//索引排序
		for(int i = 0; i < listItems.size(); i++){
			if((listItems.get(i).getSuoyin().charAt(0) >= 'A' && listItems.get(i).getSuoyin().charAt(0) <= 'Z') == false){
				listItems.get(i).setSuoyin("#");
			}
		}
		
		for(int i = 0; i < listItems.size(); i++){
			for(int j = i+1; j < listItems.size(); j++){
				if(listItems.get(i).getSuoyin().charAt(0) > listItems.get(j).getSuoyin().charAt(0)){
					Search_contact_listview_item item = new Search_contact_listview_item();
					item = listItems.get(i);
					listItems.set(i, listItems.get(j));
					listItems.set(j, item);
				}
			}
		}
	}
	
	private void viewInit(){
		search_contact_title_back = (Button) this.findViewById(R.id.search_contact_title_back);
		search_contact_title_back.setOnClickListener(onClickListener);
		search_contact_listview = (ListView) this.findViewById(R.id.search_contact_listview);
		search_contact_listview.setOnItemClickListener(onItemClickListener);
		search_contact_suoyin = (TextView) this.findViewById(R.id.search_contact_suoyin);
		search_contact_adapter = new Search_contact_adapter();
		search_contact_listview.setAdapter(search_contact_adapter);
		search_contact_index = (Index) this.findViewById(R.id.search_contact_index);
		search_contact_index.setOnIndex(onIndex);
	}
	
	OnIndex onIndex = new OnIndex() {
		
		@Override
		public void onIndexUp() {
			// TODO Auto-generated method stub
			search_contact_suoyin.setVisibility(View.GONE);
		}
		
		@Override
		public void onIndexSelect(String str) {
			// TODO Auto-generated method stub
			search_contact_suoyin.setVisibility(View.VISIBLE);
			search_contact_suoyin.setText(str);
			for(int i = 0; i < listItems.size(); i++){
				if(listItems.get(i).getSuoyin().equals(str)){
					search_contact_listview.setSelection(i);
					break;
				}
			}
		}
	};
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.search_contact_title_back:
				search_contact.this.finish();
				break;
			}
		}
	};
	
	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int postion,
				long id) {
			// TODO Auto-generated method stub
			if(search_contact_for_name == true){
				Intent intent = new Intent(search_contact.this,add_birthday.class);
				intent.putExtra("search_contact_for_name", listItems.get(postion).getName());
				setResult(add_birthday.SEARCH_CONTACT_FOR_NAME, intent);
				search_contact.this.finish();
				return;
			}
			if(search_contact_for_phone == true){
				Intent intent = new Intent(search_contact.this,add_birthday.class);
				intent.putExtra("search_contact_for_phone", listItems.get(postion).getPhone());
				setResult(add_birthday.SEARCH_CONTACT_FOR_PHONE, intent);
				search_contact.this.finish();
				return;
			}
			if(send_sms_search_contact_for_phone == true){
				Intent intent = new Intent(search_contact.this,send_sms.class);
				intent.putExtra("send_sms_search_contact_for_phone", listItems.get(postion).getPhone());
				setResult(send_sms.SEARCH_CONTACT_FOR_PHONE, intent);
				search_contact.this.finish();
				return;
			}
		}
	};
	
	public class Search_contact_adapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listItems.size();
		}

		@Override
		public Search_contact_listview_item getItem(int position) {
			// TODO Auto-generated method stub
			return listItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Search_contact_listview_View search_contact_listview_view;
			if(convertView == null){
				convertView = getLayoutInflater().inflate(R.layout.search_contact_item, null);
				search_contact_listview_view = new Search_contact_listview_View();
				search_contact_listview_view.suoyin = (TextView) convertView.findViewById(R.id.search_contact_item_suoyin);
				search_contact_listview_view.photo = (ImageView) convertView.findViewById(R.id.search_contact_item_photo);
				search_contact_listview_view.name = (TextView) convertView.findViewById(R.id.search_contact_item_name);
				search_contact_listview_view.phone = (TextView) convertView.findViewById(R.id.search_contact_item_phone);
				convertView.setTag(search_contact_listview_view);
			}else{
				search_contact_listview_view = (Search_contact_listview_View) convertView.getTag();
			}
			Search_contact_listview_item item = listItems.get(position);
			if(position > 0 && item.getSuoyin().equals(listItems.get(position -1).getSuoyin())){
				search_contact_listview_view.suoyin.setVisibility(View.GONE);
				search_contact_listview_view.photo.setImageBitmap(item.getPhoto());
				search_contact_listview_view.name.setText(item.getName());
				search_contact_listview_view.phone.setText(item.getPhone());
			}else{
				search_contact_listview_view.suoyin.setVisibility(View.VISIBLE);
				search_contact_listview_view.suoyin.setText(item.getSuoyin());
				search_contact_listview_view.photo.setImageBitmap(item.getPhoto());
				search_contact_listview_view.name.setText(item.getName());
				search_contact_listview_view.phone.setText(item.getPhone());
			}
			return convertView;
		}
	}
	
	public class Search_contact_listview_View{
		TextView suoyin;
		
		ImageView photo;
		
		TextView name;
		
		TextView phone;
	}
}
