package com.sendMessageFCM.utils;

public class Constants {

	public static final String firebaseURL = "https://fcm.googleapis.com/fcm/send";
	public static final String serverKey = "AAAASgXNggY:APA91bFmiVnIjuWSvNubsa7_kIzIeOchj2cRkMqbbNZNB11we4XE6Jgy-byISVAByoSvovuYQODOFNlpucppSXNxot6-DcbViRvK4BLvawdrKSpC3l_vQZPlPxMqB_oOX8r5k3_Ps-xo";
	
	public static final int RESPONSE_CODE = 900; // initial response code value
	public static final int ERROR_CREATING_CONNECTION = 910; // error on paring json message
	public static final int ERROR_SENDING_MESSAGE = 910; // error on paring json message
	public static final int ERROR_READING_MESSAGE = 920; // error on paring json message
	public static final int ERROR_PARSING_MESSAGE = 930; // error on paring json message
}
