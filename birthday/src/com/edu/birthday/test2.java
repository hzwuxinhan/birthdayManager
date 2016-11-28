package com.edu.birthday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class test2 extends Activity {

	private WebView test_webview;
	private Button test_open;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		viewInit();
	}
	
	private void viewInit(){
		test_webview = (WebView) this.findViewById(R.id.test_webview);
		test_webview.setWebViewClient(new webViewClient());
		test_open = (Button) this.findViewById(R.id.test_open);
		test_open.setOnClickListener(onClickListener);
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.test_open:
				dateInit();
//				connect();
//				new Thread(runnable).start();
				break;
			}
		}
	};
	
	private void dateInit(){
		test_webview.loadUrl("http://www.baidu.com");
//		new Thread(runnable).start();
//		Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
//		System.out.println("hello");
	}
	
	private void connect(){
		try {
			ServerSocket socket = new ServerSocket(52202);
			while(true){
				System.out.println("监听中");
				Socket client = socket.accept();
				System.out.println("成功了");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("失败了12");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("失败了2");
			e.printStackTrace();
		} catch (Exception e){
			System.out.println("失败了3");
			e.printStackTrace();
		}
	}
	
	public class webViewClient extends WebViewClient{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
	}
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			try {
//				URL newurl = new URL("http://www.baidu.com");
//				URLConnection connection = newurl.openConnection();
//				System.out.println("111");
//				DataInputStream dis = new DataInputStream(connection.getInputStream());
//				System.out.println("221");
//				BufferedReader in = new BufferedReader(new BufferedReader(new InputStreamReader(dis, "UTF-8")));
//				String html = "";
//				String readline = null;
//				while((readline = in.readLine()) != null){
//					html += readline;
//				}
//				System.out.println(html);
//				in.close();
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch(Exception e){
//				e.printStackTrace();
//			}
			connect();
		}
	};
}
