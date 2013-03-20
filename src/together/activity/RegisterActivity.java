/**
 * register page
 * @author zhangjie
 * @version 1.0
 * @since 2012-2-13
 * */
package together.activity;
 

import org.json.JSONException;
 

import together.utils.MyConstants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Context context;
	private ImageButton back;
	private TextView register;
	private EditText name;
	private EditText pwd;
	private EditText confirm;
	private ProgressDialog progressDialogFirstTime;
	/***
	 * called when the activity is created
	 * 
	 * @param bundle
	 *            saved instance state
	 */
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MyConstants.MSG_SUCCESS1:
//				Intent intent = new Intent(RegisterActivity.this,
//						AccountActivity.class);
//				User u = (User) msg.obj;
//				intent.putExtra("uid", u.getUid());
//				intent.putExtra("nickname", u.getNickname());
//				intent.putExtra("gender", u.getGender());
//				intent.putExtra("location", u.getLocation());
//				intent.putExtra("avatar", u.getAvatar());
//				intent.putExtra("integrity", u.getIntegrity());
//				intent.putExtra("point", u.getPoint());
//				intent.putExtra("uname", u.getUname());
//				startActivity(intent);
//				finish();
				break;
			case MyConstants.MSG_FAILURE:
				String str = (String) msg.obj;
				Toast.makeText(RegisterActivity.this,
						getString(R.string.register_fail) + " " + str,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		context = this;
//		back = (ImageButton) findViewById(R.id.register_back);
		register = (TextView) findViewById(R.id.register_btn);
		name = (EditText) findViewById(R.id.register_name_text);
		pwd = (EditText) findViewById(R.id.register_password_text);
		confirm = (EditText) findViewById(R.id.register_confirm_text);
		final Animation shakeAnim = AnimationUtils.loadAnimation(context,
				R.anim.shake_x);
		progressDialogFirstTime = new ProgressDialog(context);
		progressDialogFirstTime.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialogFirstTime.setIcon(R.drawable.loading);
		progressDialogFirstTime.setMessage(getString(R.string._loading));

		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}

		});

		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name_str = name.getText().toString();
				String pwd_str = pwd.getText().toString();
				String confirm_str = confirm.getText().toString();
				if (name_str == null || name_str.trim().equals("")) {
					name.startAnimation(shakeAnim);
					Toast.makeText(RegisterActivity.this,
							R.string.login_miss_username, Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (pwd_str == null || pwd_str.trim().equals("")) {
					pwd.startAnimation(shakeAnim);
					Toast.makeText(RegisterActivity.this,
							R.string.login_miss_password, Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (confirm_str == null || confirm_str.trim().equals("")) {
					confirm.startAnimation(shakeAnim);
					Toast.makeText(RegisterActivity.this,
							R.string.login_miss_confirm, Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (pwd_str.trim().equals(confirm_str.trim()) == false) {
					Toast.makeText(RegisterActivity.this,
							R.string.login_confirm_fail, Toast.LENGTH_SHORT)
							.show();
					return;
				}
				final String name1 = name_str.trim();
				final String pwd1 = pwd_str.trim();
				progressDialogFirstTime.show();
				register(name1, pwd1);
			}
		});
	}

	private void register(final String name1, final String pwd1) {
//		new Thread() {
//			public void run() {
//				MyHttpPost p = new MyHttpPost();
//				String urls = MyConstants.SITE_PORT
//						+ "/api/registerUser.action?uname=" + name1.trim()
//						+ "&pwd=" + pwd1.trim() + "&plat=in";
//				p.noparameterHttp(urls);
//				String response = p.doPost();
//				JsonHandler jsonHandler = new JsonHandler();
//				if (response != null) {
//					String[] strs = response.split("\"");
//					if (strs.length > 7) {
//						if (strs[1].equals("errorCode")) {
//							handler.obtainMessage(MyConstants.MSG_FAILURE,
//									strs[7]).sendToTarget();
//							progressDialogFirstTime.cancel();
//							return;
//						} else {
//							try {
//								User u = jsonHandler.getUser(response);
//								// System.out.println("res="+response);
//								handler.obtainMessage(MyConstants.MSG_SUCCESS1,
//										u).sendToTarget();
//								progressDialogFirstTime.cancel();
//							} catch (JSONException e) {
//								e.printStackTrace();
//								progressDialogFirstTime.cancel();
//								handler.obtainMessage(MyConstants.MSG_FAILURE,
//										strs[7]).sendToTarget();
//							}
//						}
//					} else {
//						progressDialogFirstTime.cancel();
//						handler.obtainMessage(MyConstants.MSG_FAILURE, strs[7])
//								.sendToTarget();
//					}
//				} else {
//					progressDialogFirstTime.cancel();
//					Log.e("ee", "null response exception");
//					handler.obtainMessage(MyConstants.MSG_FAILURE, "")
//							.sendToTarget();
//				}
//
//			}
//		}.start();
	}

}
