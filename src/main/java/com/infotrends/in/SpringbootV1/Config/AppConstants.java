package com.infotrends.in.SpringbootV1.Config;

public class AppConstants {

	public static String base_uri="http://localhost:8081";
	public static String client_id = "293427061737-v7v5b0i75i8cnb9bc092354auth3qgmg.apps.googleusercontent.com";
	public static String client_secret = "EDKP6ZIaFJxRst6WhG66ith9";
	public static String exec_token_redirect_uri ="/authenticate/google/requestToken/processResponse"; //"/google/requestUserInfo";
	public static String grant_type = "authorization_code";
	
	public static int bCryptEncoderStrength = 10;
	
	public static String send_mail_toNewRegisteredUsr_QName = "mailNewUser";
	public static String token_deactivation_QName = "expireTokens";
	public static String validateOtpUrl = "http://localhost:8085/api/v1/validateAuthToken";
	public static String createOrderPaymentUrl = "http://localhost:8088/api/v1/payment/createOrder";
	public static String confirmOrderPaymentUrl = "http://localhost:8088/api/v1/payment/confirmPayment";
	
	public static int articlesPerPage = 10;
}
