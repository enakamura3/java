package com.sendMessageFCM.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sendMessageFCM.utils.Constants;
import com.sendMessageFCM.utils.Result;

public class Send {
	
	static final Logger LOG = LoggerFactory.getLogger(Send.class);
		
	public Result sendMessage(String token, String title, String message){

		Result result = null;
		String requestMessage = String.format("{\"to\":\"%s\", \"notification\" : {\"title\" : \"%s\", \"body\":\"%s\"}}", token, title, message);
		LOG.info("Request message: "+requestMessage);
		
		
		// CREATING CONNECTION
		URL url = null;
		try {
			url = new URL(Constants.firebaseURL);
		} catch (MalformedURLException e1) {
			String logMessage = String.format("Error creating object from URL: %s",Constants.firebaseURL);
			LOG.info(logMessage);
			LOG.debug(e1.getMessage());
			result = new Result(Constants.ERROR_CREATING_CONNECTION,logMessage);
			return result;
		}
		
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "key=" + Constants.serverKey);
			connection.setDoOutput(true);
		} catch (IOException e1) {
			String logMessage = String.format("Error getting connection from URL: %s",Constants.firebaseURL);
			LOG.info(logMessage);
			LOG.debug(e1.getMessage());
			result = new Result(Constants.ERROR_CREATING_CONNECTION,logMessage);
			return result;
		}
		

		// SENDING MESSAGE
		OutputStream os;
		int responseCode = Constants.RESPONSE_CODE;
		try {
			os = connection.getOutputStream();
			os.write(requestMessage.getBytes());
			os.flush();
			os.close();
			responseCode = connection.getResponseCode();
			LOG.info("Response code: "+responseCode);
		} catch (IOException e1) {
			String logMessage = "Error getting output stream from connection.";
			LOG.info(logMessage);
			LOG.debug(e1.getMessage());
			result = new Result(Constants.ERROR_SENDING_MESSAGE,logMessage);
			return result;
		}
		

		// READING MESSAGE
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer responseMessage = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
			    responseMessage.append(inputLine);
			}
			in.close();
			LOG.info("Response message: "+responseMessage.toString());
			
			JSONObject jsonObject = new JSONObject(responseMessage.toString());
			int success = (int) jsonObject.get("success");
			int failure = (int) jsonObject.get("failure");
			
			if (success == 1 && failure == 0){
				result = new Result(success,"Message sent to Firebase Cloud Message."); 
			}
			else{
				result = new Result(failure,"Error sending message to Firebase Cloud Message.");
			}
			
		} catch (IOException e) {
			String logMessage = String.format("Error getting response message from connection. Response code: %s", responseCode);
			LOG.info(logMessage);
			LOG.debug(e.getMessage());
			result = new Result(Constants.ERROR_READING_MESSAGE, logMessage);
			return result;
			
		} catch (JSONException e) {
			String logMessage = String.format("Error parsing message from Firebase Cloud Message. Response code: %s", responseCode);
			LOG.info(logMessage);
			LOG.debug(e.getMessage());
			result = new Result(Constants.ERROR_PARSING_MESSAGE, logMessage);
			return result;
		}
		
		return result;
	}
}
