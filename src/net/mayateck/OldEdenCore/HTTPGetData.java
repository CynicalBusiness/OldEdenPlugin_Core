package net.mayateck.OldEdenCore;

import java.io.*;
import java.net.*;

public class HTTPGetData {
	private static OldEdenCore plugin;
	public HTTPGetData(OldEdenCore plugin) {
		HTTPGetData.plugin = plugin;
	}
	
	public static String getGeneralData(String filename, String args){
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
	         url = new URL("http://services.mayateck.net/oldEden/"+filename+"?"+args);
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      } catch (Exception e) {
	         String stack = e.getStackTrace().toString();
	         plugin.getLogger().info("HTTP Error: "+stack);
	      }
		
		return result;
	}
}
