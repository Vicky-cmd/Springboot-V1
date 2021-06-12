package com.infotrends.in.SpringbootV1.Connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component("connInterface")
public class ConnectionInterface {

    public HashMap<String, Object> executesForHttp(String urlStr,JSONObject jsonRequest, String httpMethod) {
        HashMap<String, Object> ResponseMap = new HashMap<String, Object>();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            if(!httpMethod.equalsIgnoreCase("GET")) {
                System.out.println(jsonRequest);
	            conn.setDoOutput(true);
	            String jsonInputString = jsonRequest.toString();
	            try(OutputStream os = conn.getOutputStream()) {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	                os.flush();
	                os.close();
	            }
            }
            
            int status = conn.getResponseCode();
            String respStatus = null;
            InputStream inputStream;
//            if (status != HttpURLConnection.HTTP_OK)  {
//            	respStatus = "Failure";
//                inputStream = conn.getErrorStream();
//            }
//            else  {
//            	respStatus = "Success";
//                inputStream = conn.getInputStream();
//            }
            
            try {
            	respStatus = "Success";
            	inputStream = conn.getInputStream();
            } catch (Exception e) {
            	respStatus = "Failure";
            	inputStream = conn.getErrorStream();
			}

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                ResponseMap = toHashMap(new JSONObject(response.toString()));
                ResponseMap.put("jsonResponse", response);
            }
            ResponseMap.put("status", respStatus);

        }catch (JSONException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (MalformedURLException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (IOException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (Exception e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        }

        return ResponseMap;
    }
	
    public HashMap<String, Object> executes(String urlStr,JSONObject jsonRequest, String httpMethod) {
        HashMap<String, Object> ResponseMap = new HashMap<String, Object>();
        try {
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            if(!httpMethod.equalsIgnoreCase("GET")) {
                System.out.println(jsonRequest);
	            conn.setDoOutput(true);
	            String jsonInputString = jsonRequest.toString();
	            try(OutputStream os = conn.getOutputStream()) {
	                byte[] input = jsonInputString.getBytes("utf-8");
	                os.write(input, 0, input.length);
	            }
            }

            int status = conn.getResponseCode();
            String respStatus = null;
            InputStream inputStream;
            if (status != HttpURLConnection.HTTP_OK)  {
            	respStatus = "Failure";
                inputStream = conn.getErrorStream();
            }
            else  {
            	respStatus = "Success";
                inputStream = conn.getInputStream();
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                ResponseMap = toHashMap(new JSONObject(response.toString()));
                ResponseMap.put("jsonResponse", response);
            }
            ResponseMap.put("status", respStatus);

        }catch (JSONException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (MalformedURLException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (IOException e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        } catch (Exception e) {
        	ResponseMap.put("status", "Failure");
            e.printStackTrace();
        }

        return ResponseMap;
    }    

    public HashMap<String, Object> toHashMap(JSONObject json) throws JSONException {
        HashMap<String, Object> respObj = new HashMap<String, Object>();
        if(json!=null) {

            Iterator<String> itr = json.keys();
            while (itr.hasNext()) {
                String key = itr.next();
                Object jsonObj = json.get(key);
                if(jsonObj instanceof JSONArray) {
                    respObj.put(key, toList((JSONArray) jsonObj));
                } else if(jsonObj instanceof JSONObject) {
                    respObj.put(key, toHashMap((JSONObject) jsonObj));
                } else if(jsonObj!=null) {
                    respObj.put(key, jsonObj);
                }
            }

        }
        return respObj;
    }

    private List toList(JSONArray jsonObj) throws JSONException {
        List<Object> RespLst = new ArrayList();
        for(int i=0; i<jsonObj.length(); i++) {
            Object jObj = jsonObj.get(i);
            if (jObj instanceof JSONArray) {
                RespLst.add(toList((JSONArray) jObj));
            } else if (jObj instanceof JSONObject) {
                RespLst.add(toHashMap((JSONObject) jObj));
            } else if(jObj!=null) {
                RespLst.add(jObj);
            }
        }
        return RespLst;
    }    
}
