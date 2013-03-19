/**
 * get response from the server
 * @author zhangjie
 * @version 1.0
 * @since 2012-2-13
 * Created on 2012-2-13
 *
 * TODO To get response from the server
 * Window - Preferences - Java - Code Style - Code Templates
 */
package info.nemoworks.inmusic.connectivity;

import info.nemoworks.inmusic.utils.BitmapUtil;
import info.nemoworks.inmusic.utils.CacheTime;
import info.nemoworks.inmusic.utils.MD5;
import info.nemoworks.inmusic.utils.MyConstants;
import info.nemoworks.inmusic.utils.MyUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.util.Log;

public class MyHttpResponse {

	public static final String ID = "_id";
	private static String TAG = "MyHttpResponse";

	/**
	 * get bitmap from url
	 * 
	 * @param url
	 *            get bitmap from the url
	 * @return bitmap
	 * */
	public static Bitmap saveImage(final String url) {
		Bitmap bm = null;
		try {
			String hash = MD5.getMD5(url);
			String filename = MyConstants.ROOTDIR + hash + "_png";
			File f = new File(filename);
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ CacheTime.CACHE_DURATION_ONE_WEEK)) {
				bm = BitmapUtil.decodeFile(f, 480);
			} else { // get from url
				URL imgURL = new URL(url);
				HttpGet httpRequest = null;
				try {
					httpRequest = new HttpGet(imgURL.toURI());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = (HttpResponse) httpclient
						.execute(httpRequest);
				HttpEntity entity = response.getEntity();
				BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(
						entity);
				InputStream instream = bufHttpEntity.getContent();
				bm = BitmapUtil.decodeStream(instream);
				if (bm != null)
					BitmapUtil.saveBitmap(filename, bm,
							CacheTime.CACHE_DURATION_ONE_WEEK);
			}
		} catch (Exception e) {
			System.out.println("url Exception");
			e.printStackTrace();
		}
		return bm;
	}

	/**
	 * get image from url and save
	 * 
	 * @param url
	 *            get image from the url and save it
	 * */
	public static void saveShareImage(String url) {// save json
		URL myURL;
		try {
			myURL = new URL(url);
			String filename = MyConstants.ROOTDIR + "shares.png";
			File f = new File(filename);
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ CacheTime.CACHE_DURATION_ONE_DAY)) {
				return;
			} else {// cache is old
				ByteArrayBuffer baf = MyUtils.readByteFromURL(myURL);

				if (baf.toByteArray() != null) {
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(f), 8192);
					out.write(baf.toByteArray());
					out.flush();
					out.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get response from the url
	 * 
	 * @param url
	 *            get json from the url
	 * @return response from the internet
	 * */
	public static String getResponse(String url) {// save json
		String s = null;
		URL myURL;
		try {
			myURL = new URL(MyConstants.SITE + url);
			String hash = MD5.getMD5(url);
			String filename = MyConstants.ROOTDIR + hash;
			File f = new File(filename);
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ CacheTime.CACHE_DURATION_ONE_DAY)) {
				s = MyUtils.readFromFile(f);
			} else {// cache is old
				s = MyUtils.readStringFromURL(myURL);
				if (s != null) {
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(f), 8192);
					out.write(s.getBytes());
					out.flush();
					out.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	public static String getResponsePort(String url, int cachetime) {
		String s = null;
		URL myURL;
		try {
			myURL = new URL(MyConstants.SITE_PORT + url);
			String hash = MD5.getMD5(url);
			String filename = MyConstants.ROOTDIR + hash;
			File f = new File(filename);
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ cachetime)) {
				//
				s = MyUtils.readFromFile(f);
			} else {// cache is old
				s = MyUtils.readStringFromURL(myURL);
				if (s != null) {
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(f), 8192);
					out.write(s.getBytes());
					out.flush();
					out.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * this is different from getResponse in site
	 * 
	 * @param url
	 *            get json from the url
	 * @return response string from url
	 * */
	public static String getResponsePort(String url) {// save json
		String s = null;
		URL myURL;
		try {
			myURL = new URL(MyConstants.SITE_PORT + url);
			String hash = MD5.getMD5(url);
			String filename = MyConstants.ROOTDIR + hash;
			File f = new File(filename);
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ CacheTime.CACHE_DURATION_ONE_DAY)) {
				//
				s = MyUtils.readFromFile(f);
			} else {// cache is old
				s = MyUtils.readStringFromURL(myURL);
				if (s != null) {
					BufferedOutputStream out = new BufferedOutputStream(
							new FileOutputStream(f), 8192);
					out.write(s.getBytes());
					out.flush();
					out.close();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * get m3u8 from url
	 * 
	 * @param m3u8
	 * @return substring without ".m3u8"
	 * */
	public static String getM3U8(String m3u8) {
		if (m3u8 != null && m3u8.length() > 5) {
			return m3u8.substring(0, m3u8.length() - 5);
		} else
			return null;
	}

	/**
	 * get head name of a file
	 * 
	 * @param filename
	 *            a filename in the sdcard
	 * @return head the dir of the file
	 * */
	public static String getHead(String filename) {
		if (filename != null) {
			String str = null;
			int i = filename.lastIndexOf("/");
			if (i == -1)
				return filename;
			str = filename.substring(0, i);
			return str;
		} else
			return null;
	}

	/**
	 * get end of a file
	 * 
	 * @param filename
	 *            a filename in the sdcard
	 * @return end the short name of the file
	 * */
	public static String getEnd(String filename) {
		if (filename != null) {
			String str = null;
			int i = filename.lastIndexOf("/");
			str = filename.substring(i + 1, filename.length());
			return str;
		} else
			return null;
	}

	/**
	 * insert a line into database
	 * 
	 * @param download
	 *            SQL database
	 * @param title
	 *            title of the m3u8
	 * @param state
	 *            state of the m3u8:1/0
	 * @param path
	 *            path of the m3u8
	 * @param image
	 *            image url of the m3u8
	 * @param id
	 *            id of the m3u8
	 * @param current
	 *            current ts of the m3u8
	 * @param m3u8
	 *            m3u8 url
	 * */
	public static void insert(SQLiteDatabase download, String title,
			String state, String path, String image_url, String music_id,
			int current, String m3u8) {
		ContentValues values = new ContentValues();
		values.put("title", title);
		values.put("state", state);
		values.put("path", path);
		values.put("url", image_url);
		values.put("music_id", music_id);
		values.put("current", 0);
		values.put("total", 0);
		values.put(MyConstants.M3U8, m3u8);
		if (download != null)
			try {
				download.insert(MyConstants.DOWN_TB_NAME, ID, values);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");

			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		else
			Log.e("DATABASE insert", "database null point");
	}

	/**
	 * update the state of the m3u8
	 * 
	 * @param download
	 *            SQL Database
	 * @param title
	 *            title of the m3u8
	 * */
	public static void update(SQLiteDatabase download, String title) {
		String sql = "UPDATE " + MyConstants.DOWN_TB_NAME
				+ " SET state = 1 WHERE title = '" + title + "'";
		if (download != null)
			try {
				download.execSQL(sql);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");

			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		else
			Log.e("DATABASE update", "database null point");
	}

	/**
	 * update state of the m3u8
	 * 
	 * @param download
	 *            SQL Database
	 * @param title
	 *            title of the m3u8
	 * @param state
	 *            state of the m3u8
	 * */
	public static void updateState(SQLiteDatabase download, String title,
			int state) {
		String sql = "UPDATE " + MyConstants.DOWN_TB_NAME + " SET state = "
				+ state + " WHERE title = '" + title + "'";
		if (download != null)
			try {
				download.execSQL(sql);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");

			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		else
			Log.e("DATABASE update", "database null point");
	}

	/**
	 * update total value of the m3u8
	 * 
	 * @param download
	 *            Database
	 * @param title
	 *            title of the m3u8
	 * @param total
	 *            ts number of the m3u8
	 * */
	public static void updateTotal(SQLiteDatabase download, String title,
			int total) {
		String sql = "UPDATE " + MyConstants.DOWN_TB_NAME + " SET total = "
				+ total + " WHERE title = '" + title + "'";
		if (download != null)
			try {
				download.execSQL(sql);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");
			} catch (IllegalStateException e) {
				Log.e(TAG, "IllegalStateException");
			}
		else
			Log.e("DATABASE total update", "database null point");
	}

	/**
	 * update current ts of the m3u8
	 * 
	 * @param download
	 *            Database
	 * @param title
	 *            title of the m3u8
	 * @param current
	 *            ts of the m3u8
	 * */
	public static void updateCurrent(SQLiteDatabase download, String title,
			int current) {
		String sql = "UPDATE " + MyConstants.DOWN_TB_NAME + " SET current = "
				+ current + " WHERE title = '" + title + "'";
		if (download != null) {
			try {
				download.execSQL(sql);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		} else
			Log.e("DATABASE current update", "database null point");
	}

	/**
	 * delete a line in the database
	 * 
	 * @param download
	 *            Database
	 * @param title
	 *            of the m3u8
	 * */
	public static void delete(SQLiteDatabase download, String title) {
		String sql = "DELETE FROM " + MyConstants.DOWN_TB_NAME
				+ " WHERE title = '" + title + "'";
		if (download != null) {
			try {
				download.execSQL(sql);
			} catch (SQLiteException e) {
				e.printStackTrace();
				Log.e("null", "SQLITE Exception");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		} else
			Log.e("DATABASE delete ", "database null point");
	}

	/**
	 * request to a url and get response
	 * 
	 * @param url
	 *            url of the request
	 * @param time
	 *            cachetime
	 * @return string get response from the internet, and set the cache time
	 * */
	public static String getResponse(String url, long cacheTime) {// save json
		String s = null;
		URL myURL;
		try {
			myURL = new URL(MyConstants.SITE + url);
			String hash = MD5.getMD5(url);
			String filename = MyConstants.ROOTDIR + hash;
			File f = new File(filename);
			// BufferedInputStream bis = null;
			if (f.exists()
					&& (System.currentTimeMillis() < f.lastModified()
							+ cacheTime)) {
				s = MyUtils.readFromFile(f);
				// FileInputStream fis = new FileInputStream(f);
				// bis = new BufferedInputStream(fis, 8192);
				// ByteArrayBuffer buffer = new ByteArrayBuffer(50);
				// int current = 0;
				// while ((current = bis.read()) != -1) {
				// buffer.append((byte) current);
				// }
				// s = EncodingUtils.getString(buffer.toByteArray(), "UTF-8");
			} else {// cache is old
				// URLConnection ucon = myURL.openConnection();
				// InputStream is = ucon.getInputStream();
				// BufferedInputStream biss = new BufferedInputStream(is, 8192);
				// ByteArrayBuffer baf = new ByteArrayBuffer(50);
				// int current = 0;
				// while ((current = biss.read()) != -1) {
				// baf.append((byte) current);
				// }
				// s = EncodingUtils.getString(baf.toByteArray(), "UTF-8");
				s = MyUtils.readStringFromURL(myURL);
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(f), 8192);
				out.write(s.getBytes());
				out.flush();
				out.close();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

}
