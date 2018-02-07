package com.sendMessageFCM.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sendMessageFCM.core.Send;
import com.sendMessageFCM.utils.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class Controller {

	static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	
	@RequestMapping("/")
	public String index() {
		return "Application running!";
	}
	
	@RequestMapping("/message")
	public Result notificationMessage(@RequestParam("token") String token, @RequestParam("title") String title, @RequestParam("message") String message){
		LOG.info("Begin process");
		Send sendMessage = new Send();
		Result result = sendMessage.sendMessage(token, title, message);
		LOG.info("End process");
		return result;
	}

}
