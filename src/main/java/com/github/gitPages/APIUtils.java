package com.github.gitPages;
import groovy.json.JsonException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Represents gitHub API interactions
 * @author tmtinsi

 */
public class APIUtils {
	private static Logger logger = Logger.getLogger(APIUtils.class);
	public static  int statusCode;
	
	/**
	 * basic Authentication using a tokente e.t.c. Repo based Token
	 * @author tmtinsi
	 */
	public String basicAUthtoken(){
		String token =  "df82fa979c3b0b943c6e0102d83becca92e107a1";
		String urlToken = "https://" + token+ ":x-oauth-basic@";
		return urlToken;	
	}
	
	/**
	 * JSON Object pupolation
	 * @return jsonObject
	 * @author tmtinsi
	 */
	public JSONObject json() throws Exception{
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		//label already created , change label id if the current one is deleted
		int id = 2;
		try{
			jsonObject.put("title","TestingPgfgfofdgfdst");
			jsonObject.put("assignee","tmtinsi");
			jsonObject.put("body","testing1fdgffds23s");
			jsonObject.put("milestone",id);
			jsonArray.put("bug");
			jsonArray.put("wontfix");
			jsonObject.put("labels", jsonArray);
		}catch (JsonException e){
			e.printStackTrace();			
		}
		return jsonObject;
	}

	/**
	 * Get the issues list
	 * @param url
	 * @return is
	 * @author tmtinsi
	 */
	@SuppressWarnings({"deprecation","resource"})
	public InputStream getJSONObject(String url) throws Exception{
		InputStream is = null;
		HttpGet request = new HttpGet(url);	
		CloseableHttpClient client = new DefaultHttpClient();
		//HttpResponse response = client.execute(request);
		CloseableHttpResponse response = client.execute(request);
		statusCode = response.getStatusLine().getStatusCode();
		logger.info(statusCode + " JSON  get status code\n");
		is = response.getEntity().getContent();
		return is;
	}
	
	/**
	 * post JSONObject
	 * @param jsobObject, url
	 * @return is
	 * @author tmtinsi
	 */
	public InputStream postJSONObject(JSONObject jsonObject, String url) throws Exception{
		CloseableHttpClient client = new DefaultHttpClient();
		InputStream is = null;
		HttpPost httpPost = new HttpPost(url);
		String message = jsonObject.toString();
	
		//create the entity
		HttpEntity entity = new StringEntity(message);
		httpPost.setEntity(entity);	
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		CloseableHttpResponse response = client.execute(httpPost);
		StatusLine statusLine = response.getStatusLine();
		statusCode = statusLine.getStatusCode();
		logger.info(statusCode + " JSON  Post status code\n");
		is = response.getEntity().getContent();
		logger.info(is + " JSON  Post inputstream\n");	
		return is;			
	}
	
	/**
	 * patch/Edit an issue
	 * @param url
	 * @return is
	 * @author tmtinsi
	 */
	public InputStream patchJSONObject(String url) throws Exception{
		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("state", "closed");
		jsonObject.put("title","x!!!!fdfd!!!xxx");
		HttpClient client = new DefaultHttpClient();
		InputStream is = null;
		HttpPatch httpPatch = new HttpPatch(url);
		logger.debug("httpPatch IRI =" + httpPatch.getURI() +" \n");
		String message = jsonObject.toString();
	
		//create the entity
		HttpEntity entity = new StringEntity(message);
		httpPatch.setEntity(entity);	
		httpPatch.setHeader("Accept", "application/json");
		httpPatch.setHeader("Content-type", "application/json");
		HttpResponse response = client.execute(httpPatch);
		StatusLine statusLine = response.getStatusLine();
		statusCode = statusLine.getStatusCode();
		logger.info("patch json status code = " + statusCode);	
		if(statusCode ==HttpStatus.SC_OK){
			is = response.getEntity().getContent();
		}		
		return is;			
	}
	
	/**
	 * JSON Object pupolation
	 * @param inputStream
	 * @return BuffererdReader string
	 * @author tmtinsi
	 */
	//read string content
	public String readStringContent(InputStream inputStream){
		Reader reader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(reader);
		while(true){
			try {
				String line = bufferedReader.readLine();
				if(line!=null){
					logger.info(line + "readStringContent \n");
				}else{
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bufferedReader.toString();
	}
}
