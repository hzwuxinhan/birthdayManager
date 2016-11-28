package com.edu.birthday;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.edu.util.Encrypt;
import com.edu.util.SharedPrefrencesUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.AsyncTask.Status;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends Activity {
	
	//view�ؼ�
	private Button register_back;
	private Button register_registerbutton;
	private EditText register_user;
	private EditText register_password;
	
	private String user;
	private String password;
	private String user_base64_encode;
	private String password_base64_encode;
	private RegisterTask registerTask;
	private Boolean isSuccessed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		dataInit();
		viewInit();
	}
	
	private void dataInit(){
		isSuccessed = false;
	}
	
	private void viewInit(){
		register_back = (Button) this.findViewById(R.id.register_back);
		register_back.setOnClickListener(onClickListener);
		register_registerbutton = (Button) this.findViewById(R.id.register_registerbutton);
		register_registerbutton.setOnClickListener(onClickListener);
		register_user = (EditText) this.findViewById(R.id.register_user);
		register_password = (EditText) this.findViewById(R.id.register_password);
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.register_back:
				if(isSuccessed){
					Intent intent = new Intent(register.this,login.class);
					setResult(login.LOGIN_FOR_REGISTER, intent);
					register.this.finish();
				}else{
					register.this.finish();
				}
				break;
			case R.id.register_registerbutton:
				InputMethodManager inputMethodManager = (InputMethodManager) getApplication().getSystemService(INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(register.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				if(register_registerbutton.getText().equals("ע��")){
					if((registerTask == null || registerTask.getStatus() == Status.FINISHED) && getScreenInput()){
						registerTask = new RegisterTask();
						registerTask.execute();
					}
				}else if(register_registerbutton.getText().equals("���")){
					register_registerbutton.setText("ע��");
				}
				break;
			}
		}
	};
	
	private boolean getScreenInput(){
		user = register_user.getText().toString();
		password = register_password.getText().toString();
		if(user.equals("") || password.equals("")){
			Toast.makeText(getApplicationContext(), "����д�˺�����", Toast.LENGTH_SHORT).show();
			return false;
		}else{
			user_base64_encode = Encrypt.encryptBase64(user);
			password_base64_encode = Encrypt.encryptBase64(password);
			user = Encrypt.encrpyMD5(user);
			password = Encrypt.encrpyMD5(password);
		}
		return true;
	}
	
	private class RegisterTask extends AsyncTask<Void, Integer, Void>{
		private String httpurl = "http://pickingstone.sinaapp.com/api/register.php?";
		private String content = "";

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			publishProgress(0);
			try {
				URL url = new URL(httpurl + "user=" + user + "&password=" + password + "&action=register");
				HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
				InputStreamReader isr = new InputStreamReader(httpURLConnection.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String stringline = "";
				while((stringline = br.readLine()) != null){
					content += stringline + "\n";
				}
				if(content.equals("�ɹ�\n")){
					publishProgress(1);
				}else if(content.equals("ʧ��1\n")){
					publishProgress(2);
				}
				br.close();
				isr.close();
				httpURLConnection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			switch(values[0]){
			case 0:
				register_registerbutton.setText("����ע��");
				break;
			case 1:
				register_registerbutton.setText("���");
				SharedPrefrencesUtil.saveToSharedPrefrences("account", "user",user_base64_encode,getApplicationContext());
				SharedPrefrencesUtil.saveToSharedPrefrences("account","password", password_base64_encode,getApplicationContext());
				SharedPrefrencesUtil.saveToSharedPrefrences("account", "haveSaved", true,getApplicationContext());
				isSuccessed = true;
				break;
			case 2:
				Toast.makeText(getApplicationContext(), "�û����ѱ�ʹ��", Toast.LENGTH_SHORT).show();
				register_registerbutton.setText("ע��");
				break;
			}
		}
	}
}