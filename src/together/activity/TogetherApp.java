package together.activity;


import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class TogetherApp extends Application{
	
	private static TogetherApp mApp=null;
	private BMapManager mMapManager=null;
	private final String key="1F2D99F56D2766A3C32DD06ED9EBDB603595634C";
	
	public void onCreate(){
		super.onCreate();
		mApp=this;
		initMapManager(this);
	}
	
	public void onTerminate(){
		if (mMapManager!=null)
			mMapManager.destroy();
		mMapManager=null;
		super.onTerminate();
	}
	
	public void initMapManager(Context context){
		
		Log.v("app", "init mapmanager");
		if(mMapManager==null)
			mMapManager=new BMapManager(context);
		if(!mMapManager.init(key, new MyGeneralListener())){
			Toast.makeText(context, "mMapManager init error!", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public static Application getInstance(){
		return mApp;
	}
	
	public BMapManager getMapManager(){
		return mMapManager;
	}
	
	// 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {
        
        @Override
        public void onGetNetworkState(int iError) {
            if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
                Toast.makeText(TogetherApp.getInstance().getApplicationContext(), "您的网络出错啦！",
                    Toast.LENGTH_LONG).show();
            }
            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
                Toast.makeText(TogetherApp.getInstance().getApplicationContext(), "输入正确的检索条件！",
                        Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onGetPermissionState(int iError) {
            if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
                //授权Key错误：
                Toast.makeText(TogetherApp.getInstance().getApplicationContext(), 
                        "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
            }
        }
    }

}
