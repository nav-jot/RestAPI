package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE200 = 200;
	public int RESPONSE_STATUS_CODE201 = 201;
	public int RESPONSE_STATUS_CODE500 = 500;
	public static int RESPONSE_STATUS_CODE400 = 400; 
	
	
	public Properties prop;
	
	public TestBase() {
		
		prop = new Properties();

		String projectPath = System.getProperty("user.dir");
		File file = new File(projectPath+"/src/main/java/com/qa/config/config.properties");
		FileInputStream fso;
		try {
			fso = new FileInputStream(file);
			prop.load(fso);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		public static void main(String args[]) {
	
		}
		
		
	
	
}
