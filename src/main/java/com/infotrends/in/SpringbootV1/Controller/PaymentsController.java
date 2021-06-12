package com.infotrends.in.SpringbootV1.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.Connections.ConnectionInterface;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

	@Autowired
	private ConnectionInterface connInterface;
	
	@PostMapping("/redirectURL")
	public void redirectURL(@RequestBody String reqBody,HttpServletRequest request, HttpServletResponse response) {
		System.out.println(reqBody);
		String razorpay_payment_id = request.getParameter("razorpay_payment_id");
		String razorpay_order_id = request.getParameter("razorpay_order_id");
		String razorpay_signature = request.getParameter("razorpay_signature");

		String orderId = request.getSession().getAttribute("orderId").toString();
		
		JSONObject jsonresp = new JSONObject();
		JSONObject jsonReq = new JSONObject();
		jsonReq.put("orderId", orderId);
		jsonReq.put("razorpay_payment_id", razorpay_payment_id);
		jsonReq.put("razorpay_order_id", razorpay_order_id);
		jsonReq.put("razorpay_signature", razorpay_signature);
//		jsonReq.put("receipt", "");
		HashMap<String, Object> resp = connInterface.executesForHttp(AppConstants.confirmOrderPaymentUrl, jsonReq, "POST");
	}
	
	@PostMapping("/createOrder")
	public String createNewOrder(@RequestBody String reqJsonStr, HttpServletRequest request, HttpServletResponse response) {
		JSONObject reqJson = new JSONObject(reqJsonStr);
		double amount = reqJson.getDouble("amount");
		int cartId = reqJson.getInt("cartId");
		int userId = reqJson.getInt("userId");
		JSONObject jsonresp = new JSONObject();
		System.out.println(amount);
		JSONObject jsonReq = new JSONObject();
		jsonReq.put("amount", amount);
		jsonReq.put("userId", userId);
		jsonReq.put("cartId", cartId);
//		jsonReq.put("receipt", "");
		HashMap<String, Object> resp = connInterface.executesForHttp(AppConstants.createOrderPaymentUrl, jsonReq, "POST");
		System.out.println(resp);
		if(resp.get("status")!=null && resp.get("status").toString().equalsIgnoreCase("Success")) {
			
			jsonresp.put("id", resp.get("orderId"));		
			
			request.getSession().setAttribute("orderId", resp.get("orderId").toString());
			
			jsonresp.put("status", resp.get("status").toString());
			jsonresp.put("amount", resp.get("amount"));
		} else {
			jsonresp.put("status", "Failure");
		}
		return jsonresp.toString();
	}
}
