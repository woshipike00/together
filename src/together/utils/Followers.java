package together.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import together.activity.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class Followers extends ItemizedOverlay<OverlayItem>{

	private List<OverlayItem> overlaylist=new ArrayList<OverlayItem>();
	 
	private Context context;
//	private MapView mapView;
//	private static int selected=0;
//	private BMapManager mapManager;

	public Followers(Context context,Drawable d,List<GeoPoint> list,MapView mapView,ArrayList<HashMap<String, Object>> allfollowers) throws IOException {
		super(d);
		this.context=context;
//		this.mapView=mapView;
//		mapManager=((TogetherApp)context.getApplicationContext()).getMapManager();
		 

		for (int i=0;i<list.size()-1;i++){
			HashMap<String, Object> follower=allfollowers.get(i);
			OverlayItem item=new OverlayItem(list.get(i), null,"用户: "+(String)follower.get("uid"));
			overlaylist.add(item);
			 

		}
		overlaylist.add(new OverlayItem(list.get(list.size()-1), "event", "event"));
		Log.v("together", "geopoint: "+list.get(list.size()-1).toString()+","+list.get(list.size()-2));

		overlaylist.get(list.size()-1).setMarker(context.getResources().getDrawable(R.drawable.current_event));

		populate();


	}
	
	protected boolean onTap(int index) {
		super.onTap(index);
		Toast.makeText(context, overlaylist.get(index).getSnippet(), Toast.LENGTH_SHORT).show();
		return true;
		
	}
	

	@Override
	protected OverlayItem createItem(int i) {
 		return overlaylist.get(i);
	}

	@Override
	public int size() {
 		return overlaylist.size();
	}



}

