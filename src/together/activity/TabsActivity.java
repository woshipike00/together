package together.activity;

import java.io.File;
import java.io.IOException;

import together.utils.MyConstants;
import together.utils.StorageUtils;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TabsActivity extends ActivityGroup {

	private LinearLayout bodylayout;
//	private RelativeLayout root;
	private LinearLayout tabMain;
	private LinearLayout tabStar;
	private LinearLayout tabColumn;
	private LinearLayout tabMore;
//	private LinearLayout tab_layout;
	private int current = 1;
	private View viewHome;
	private View viewMore;
	private View viewColumn;
	private View viewStar;
	private Dialog quit_builder = null;
	// private static final String tag = "InMusic";
	private LinearLayout.LayoutParams linearLayouttParams;
	final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		quit_builder = new AlertDialog.Builder(this)
				.setTitle(R.string.music_exit)
				.setMessage(R.string.music_sure_exit)
				.setPositiveButton(R.string.music_positive_btn,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						})
				.setNegativeButton(R.string.music_negative_btn,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						}).create();
		if (!StorageUtils.isSDCardPresent()) {
			Toast.makeText(this, R.string.miss_sdcard, Toast.LENGTH_LONG)
					.show();
			return;
		}

		if (!StorageUtils.isSdCardWrittenable()) {
			Toast.makeText(this, R.string.sdcard_can_write, Toast.LENGTH_LONG)
					.show();
			return;
		}

		try {
			StorageUtils.mkdir();
		} catch (IOException e) {
			e.printStackTrace();
		}

		File destDir = new File(MyConstants.ROOTDIR);
		File m3u8Dir = new File(MyConstants.M3U8DIR);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		if (!m3u8Dir.exists()) {
			m3u8Dir.mkdirs();
		}
		initUI();
		linearLayouttParams = new LinearLayout.LayoutParams(WRAP_CONTENT,
				WRAP_CONTENT);

		bodylayout.removeAllViews();
		viewHome = getLocalActivityManager().startActivity(
				"indexHome",
				new Intent(TabsActivity.this, HomeActivity.class)
						.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView();
		bodylayout.addView(viewHome, linearLayouttParams);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			this.getLocalActivityManager().getCurrentActivity()
					.openOptionsMenu();
			return super.onKeyDown(keyCode, event);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			quit_builder.show();
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}

	private void initUI() {
		setContentView(R.layout.tab);
//		root = (RelativeLayout) findViewById(R.id.roots);
		bodylayout = (LinearLayout) findViewById(R.id.bodylayout);

		tabMain = (LinearLayout) findViewById(R.id.tab_main_layout);
		tabStar = (LinearLayout) findViewById(R.id.tab_star_layout);
		tabColumn = (LinearLayout) findViewById(R.id.tab_column_layout);
		tabMore = (LinearLayout) findViewById(R.id.tab_more_layout);

		tabMain.setOnClickListener(new OnTabMainClickListener());
		tabStar.setOnClickListener(new OnTabStarClickListener());
		tabColumn.setOnClickListener(new OnTabColumnClickListener());
		tabMore.setOnClickListener(new OnTabMoreClickListener());

	}

	private class OnTabMainClickListener implements OnClickListener {
		public void onClick(View v) {
			if (current != R.id.tab_main_layout) {
				viewHome = getLocalActivityManager()
						.startActivity(
								"indexHome",
								new Intent(TabsActivity.this,
										HomeActivity.class)
										.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
						.getDecorView();
				current = R.id.tab_main_layout;
				bodylayout.removeAllViews();
				bodylayout.addView(viewHome);

			}
		}
	};

	private class OnTabStarClickListener implements OnClickListener {
		public void onClick(View v) {
			viewStar = getLocalActivityManager().startActivity(
					"indexStar",
					new Intent(TabsActivity.this, MessageListActivity.class)
							.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
					.getDecorView();
			if (current != R.id.tab_star_layout) {
				current = R.id.tab_star_layout;
				getLocalActivityManager().removeAllActivities();
				bodylayout.removeAllViews();
				bodylayout.addView(viewStar);
			}
		}
	};

	private class OnTabColumnClickListener implements OnClickListener {
		public void onClick(View v) {
			viewColumn = getLocalActivityManager().startActivity(
					"indexColumn",
					new Intent(TabsActivity.this, FriendActivity.class)
							.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
					.getDecorView();
			if (current != R.id.tab_column_layout) {
				current = R.id.tab_column_layout;
				bodylayout.removeAllViews();
				bodylayout.addView(viewColumn);

			}
		}
	};

	private class OnTabMoreClickListener implements OnClickListener {
		public void onClick(View v) {
			viewMore = getLocalActivityManager().startActivity(
					"indexMore",
					new Intent(TabsActivity.this, SettingsActivity.class)
							.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT))
					.getDecorView();
			if (current != R.id.tab_more_layout) {
				current = R.id.tab_more_layout;
				bodylayout.removeAllViews();
				bodylayout.addView(viewMore);

			}
		}
	};

	/**
	 * called when the activity is destroyed
	 */
	@Override
	public void onDestroy() {
		if (quit_builder != null)
			quit_builder.dismiss();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
