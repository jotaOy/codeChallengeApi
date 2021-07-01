package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class ConsumerRestClient {
	private String urlBase;
	private Map<String, String> params;
	private String metod;
	private String resp;

	public ConsumerRestClient(String coreBase, String metod, Map<String, String> params, String type)
			throws IOException {

		try {
			this.urlBase = coreBase;
			this.params = params;
			this.metod = metod;
			Map<String, String> header = new HashMap<String, String>();
			if (type.equalsIgnoreCase("GET")) {
				sendGet();
			}
			if (type.equalsIgnoreCase("POST")) {
				sendPost(header);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// HTTP GET request
	private void sendGet() throws IOException {
		URL url = new URL(urlBase + metod + formParams(params));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output;
		String resp = "";
		while ((output = br.readLine()) != null) {
			resp += output;
		}
		conn.disconnect();
		this.resp = resp;
	}


	public <T> List<T> listEntity(Class<T> clazz) throws Exception {
		final Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(resp).getAsJsonArray();
		List<T> lst = new ArrayList<T>();
		for (final JsonElement json : array) {
			T entity = gson.fromJson(json, clazz);
			lst.add(entity);
		}
		return lst;

	}

	public <T> T entity(Class<T> clazz) {
		final Gson gson = new Gson();
		return gson.fromJson(resp, clazz);

	}

	public String stringFromXML() {
		return resp;

	}

	private static String formParams(Map<String, String> params) {
		String param = params.keySet().size() > 0 ? "?" : "";
		for (String key : params.keySet()) {
			String valor = String.valueOf(params.get(key));
			param += param.equalsIgnoreCase("?") ? key + "=" + valor : "&" + key + "=" + valor;
		}
		return param;
	}

	// HTTP POST request
	public void sendPost(Map<String, String> header) throws IOException {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(urlBase + metod);
		Gson gson = new Gson();
		String json = gson.toJson(params);
		postMethod.setRequestBody(json);
		for (String key : header.keySet()) {
			String valor = String.valueOf(header.get(key));
			postMethod.addRequestHeader(key, valor);

		}
		httpClient.setTimeout(10000);
		httpClient.executeMethod(postMethod);
		int statusCode = postMethod.getStatusCode();
		if (statusCode != 401) {
			resp = postMethod.getResponseBodyAsString();
		} else {
			resp = postMethod.getResponseBodyAsString();
		}
	}

	public static Map<String, Object> getMap(Object o) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> result = new HashMap<String, Object>();
		Field[] declaredFields = o.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			result.put(field.getName(), field.get(o));
		}
		return result;
	}

}
