/**
 * the login page
 * @author zhangjie
 * @version 1.0
 * @since 2012-2-13
 * 
 * */
package together.activity;
 

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
 

import together.utils.MyConstants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Context context;
 	private TextView login;
	private TextView sina;
	private TextView tecent;
	private TextView register;
	private EditText name;
	private EditText pwd;
	private ProgressDialog progressDialogFirstTime;

 	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	/**
	 * called when the activity is created
	 * 
	 * @param bundle
	 *            saved instance state
	 */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		context = this;
 		initUI();

		setClickListener();
	}

	private void initUI() {
 		login = (TextView) findViewById(R.id.login_login);
		register = (TextView) findViewById(R.id.login_register);
		name = (EditText) findViewById(R.id.login_account);
		pwd = (EditText) findViewById(R.id.login_password); 
		progressDialogFirstTime = new ProgressDialog(context);
		progressDialogFirstTime.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialogFirstTime.setIcon(R.drawable.loading);
		progressDialogFirstTime.setMessage(getString(R.string._loading));
	}

	private void setClickListener() {
		 
		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						RegisterActivity.class);
				startActivity(intent);
				finish();
			}
		});

		 

		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clickLogin();
			}

		});
	}

	private void clickLogin() {
 		Animation shakeAnim = AnimationUtils.loadAnimation(context,
				R.anim.shake_x);
		String name_str = name.getText().toString();
		String pwd_str = pwd.getText().toString();
		if (name_str == null || name_str.trim().equals("")) {
			name.startAnimation(shakeAnim);
			progressDialogFirstTime.cancel();
			Toast.makeText(LoginActivity.this, R.string.login_miss_username,
					Toast.LENGTH_SHORT).show();
			return;
		}
		if (pwd_str == null || pwd_str.trim().equals("")) {
			pwd.startAnimation(shakeAnim);
			progressDialogFirstTime.cancel();
			Toast.makeText(LoginActivity.this, R.string.login_miss_password,
					Toast.LENGTH_SHORT).show();
			return;
		}
		final String name1 = name_str.trim();
		final String pwd1 = pwd_str.trim();
		progressDialogFirstTime.show();
		loginVarify(name1, pwd1);
	}

	private void loginVarify(final String name1, final String pwd1) {
//		new Thread() {
//			public void run() {
//				MyHttpPost p = new MyHttpPost();
//				String urls = MyConstants.SITE_PORT
//						+ "/api/loginIn.action?uname=" + name1.trim() + "&pwd="
//						+ pwd1.trim() + "&plat=in";
//				p.noparameterHttp(urls);
//				String response = p.doPost();
//				JsonHandler jsonHandler = new JsonHandler();
//				if (response != null) {
//					String[] strs = response.split("\"");
//					if (strs.length > 7) {
//						if (strs[1].equals("errorCode")) {
//							progressDialogFirstTime.cancel();
//							handler.obtainMessage(MyConstants.MSG_FAILURE,
//									strs[7]).sendToTarget();
//							return;
//						} else {
//							LoginUser u;
//							try {
//								u = jsonHandler.getLoginUser(response);
								handler.obtainMessage(MyConstants.MSG_SUCCESS1).sendToTarget();
//								preferences = getSharedPreferences("user_name",
//										Context.MODE_PRIVATE);
//								editor = preferences.edit();
//								editor.putString("name", name1);
//								editor.putString("id", u.getUid());
//								editor.commit();
//							} catch (JSONException e) {
//								e.printStackTrace();
//								handler.obtainMessage(MyConstants.MSG_FAILURE,
//										"json exception").sendToTarget();
//							}
//						}
//					} else {
//						progressDialogFirstTime.cancel();
//						handler.obtainMessage(MyConstants.MSG_FAILURE, "")
//								.sendToTarget();
//						return;
//					}
//				} else {
//					progressDialogFirstTime.cancel();
//					handler.obtainMessage(MyConstants.MSG_FAILURE,
//							"null response").sendToTarget();
//					return;
//				}
//			}
//		}.start();
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MyConstants.MSG_SUCCESS1:
				progressDialogFirstTime.cancel();
				File f = new File(MyConstants.ROOTDIR + "names.txt");
				if (f.exists())
					f.delete();
//				try {
////					LoginUser u = (LoginUser) msg.obj;
					Intent intent = new Intent(LoginActivity.this,
							TabsActivity.class);
////					intent.putExtra("uid", u.getUid());
////					intent.putExtra("nickname", u.getNickname());
////					intent.putExtra("gender", u.getGender());
////					intent.putExtra("location", u.getLocation());
////					intent.putExtra("avatar", u.getAvatar());
////					intent.putExtra("integrity", u.getIntegrity());
////					intent.putExtra("point", u.getPoint());
////					intent.putExtra("uname", u.getUname());
////					intent.putExtra("mobile", u.getMobile());
////					f.createNewFile();
////					BufferedOutputStream out;
////					out = new BufferedOutputStream(new FileOutputStream(f),
////							8192);
////					out.write(u.getUid().getBytes());
////					out.flush();
////					out.close();
////
////					Toast.makeText(getApplication(), R.string.login_succ,
////							Toast.LENGTH_LONG).show();
////					if (src != null && src.equals("0"))
						startActivity(intent);
					finish();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				break;
			case MyConstants.MSG_FAILURE:
				progressDialogFirstTime.cancel();
				String str = (String) msg.obj;
				Toast.makeText(LoginActivity.this,
						getString(R.string.login_fail) + " " + str,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

}
