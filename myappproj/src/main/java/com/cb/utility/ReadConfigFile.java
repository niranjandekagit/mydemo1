package com.cb.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
	
		protected InputStream input=new FileInputStream("src/main/resources/config.properties");
		protected Properties prop=null;
		
		public ReadConfigFile() throws Exception
		{
			prop=new Properties();
			prop.load(input);			
		}
				
		public String getUrl(){
			return prop.getProperty("appUrl").trim();
		}
		
		public String getBrowser(){
			return prop.getProperty("browser").trim();
		}
		
		public String getFirepathexebinaries(){
			return prop.getProperty("firefoxexebinariespath").trim();
		}

	}



