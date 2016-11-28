package com.edu.birthday;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class more extends Activity {
	
	private Button more_havepassword;
	private boolean isEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		dateInit();
		viewInit();
	}
	
	private void dateInit(){
		isEdit = false;
	}
	
	private void viewInit(){
		more_havepassword = (Button) this.findViewById(R.id.more_havePassword);
		more_havepassword.setOnClickListener(onClickListener);
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.more_havePassword:
				if(isEdit == false){
					isEdit = true;
					more_havepassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.slip_bg_on));
				}else{
					isEdit = false;
					more_havepassword.setBackgroundDrawable(getResources().getDrawable(R.drawable.slip_bg_off));
				}
				break;
			}
		}
	};
}
