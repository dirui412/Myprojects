package lj.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils {
	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url, Map<String, String> headers) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet requestGet = new HttpGet(url);
		if (headers != null)
			for (Map.Entry<String, String> entry : headers.entrySet())
				requestGet.addHeader(entry.getKey(), entry.getValue());
		CloseableHttpResponse response = null;
		try {
			response = client.execute(requestGet);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			String str = FileUtils.inputStreamtoString(is);
			is.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
		}
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 */
	public static String doPost(String url, Map<String, String> headers, Map<String, String> postParams) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		if (headers != null)
			for (Map.Entry<String, String> entry : headers.entrySet())
				httpPost.addHeader(entry.getKey(), entry.getValue());
		List<NameValuePair> entityParams = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : postParams.entrySet())
			entityParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));

		CloseableHttpResponse response = null;
		try {
			HttpEntity requestEntity = new UrlEncodedFormEntity(entityParams);
			httpPost.setEntity(requestEntity);
			response = client.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			InputStream is = responseEntity.getContent();
			String str = FileUtils.inputStreamtoString(is);
			is.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param filePath
	 * @return
	 */
	public static boolean doDownLoad(String url, String filePath) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet requestGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(requestGet);
			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			FileOutputStream os = new FileOutputStream(filePath);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				// System.out.println("len:"+len);
				os.write(buffer, 0, len);
			}
			os.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
			if (client != null)
				try {
					client.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
		}
	}

	/**
	 * 发送REST GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGetRest(String url) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String ret = doGet(url, headers);
		return ret;
	}

	/**
	 * 
	 * @param url
	 * @param obj
	 * @return
	 */
	public static String doPostRest(String url, Object obj) {
		String strJson = JsonUtils.objectToJson(obj);
		return doPostRest(url,strJson);
	}
	/**
	 * 
	 * @param url
	 * @param obj
	 * @return
	 */
	public static String doPostRest(String url,String strJson) {
		CloseableHttpResponse response=null;
		CloseableHttpClient httpClient=HttpClients.createDefault();
		try {
			HttpPost postRequest = new HttpPost(url);
			
			StringEntity input = new StringEntity(strJson);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			HttpEntity responseEntity = response.getEntity();
			InputStream is = responseEntity.getContent();
			String strRest = FileUtils.inputStreamtoString(is);
			is.close();
			return strRest;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		} finally {
			if (response != null)
				try {
					response.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
			if (httpClient != null)
				try {
					httpClient.close();
				} catch (Exception inEx) {
					inEx.printStackTrace();
				}
		}
	}

}
