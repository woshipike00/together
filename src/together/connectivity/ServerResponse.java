package together.connectivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServerResponse {
//	public static String getResponse(String url) throws JSONException, ParseException, IOException {
//		String result = null;
//		//请求数据
//		HttpClient hc = new DefaultHttpClient();
//		HttpPost hp = new HttpPost(url);
//		//请求json报文
//		JSONObject jo = new JSONObject();
//		jo.put("uid", "0");
//		jo.put("radius", "0");
//		hp.setEntity(new StringEntity(jo.toString()));
//		HttpResponse hr = hc.execute(hp);
//		//获取返回json报文
//		if(hr.getStatusLine().getStatusCode() == 200){
//			result = EntityUtils.toString(hr.getEntity(), "utf-8");
//		}
//		//关闭连接
//		if (hc != null) {
//			hc.getConnectionManager().shutdown();
//		}
//		return result;
//	}
	
	public static String getResponse(String url, JSONObject json) throws ClientProtocolException, IOException {
		String result = null;
		//请求数据
		HttpClient hc = new DefaultHttpClient();
		HttpPost hp = new HttpPost(url);
		hp.setEntity(new StringEntity(json.toString(), HTTP.UTF_8));
		HttpResponse hr = hc.execute(hp);
		//获取返回json报文
		if(hr.getStatusLine().getStatusCode() == 200){
			result = EntityUtils.toString(hr.getEntity(), "utf-8");
		}
		//关闭连接
		if (hc != null) {
			hc.getConnectionManager().shutdown();
		}
		return result;
	}
}
