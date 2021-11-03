package org.zerock.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zerock.controller.HomeController;


public class SampleServiceImpl implements SampleService {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Override
	public int doAdd(String str1, String str2) throws Exception {
		
		logger.info("doAdd.......");
		
		return Integer.parseInt(str1)+Integer.parseInt(str2);
	}
	
}
