package com.trade.service.marketdata;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.model.Instrument;

public class GoogleMarketDataService {
	
	static String preURL = "http://finance.google.com/finance/info?client=ig&q=";
	
	private class GInfo{
		 String id;
		 String t;
		 String e;
		 String l;
		 String l_fix;
		 String l_cur;
		 String s;
		 String ltt;
		 String lt;
		 String lt_dts;
		 String c;
		 String c_fix;
		 public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getT() {
			return t;
		}
		public void setT(String t) {
			this.t = t;
		}
		public String getE() {
			return e;
		}
		public void setE(String e) {
			this.e = e;
		}
		public String getL() {
			return l;
		}
		public void setL(String l) {
			this.l = l;
		}
		public String getL_fix() {
			return l_fix;
		}
		public void setL_fix(String l_fix) {
			this.l_fix = l_fix;
		}
		public String getL_cur() {
			return l_cur;
		}
		public void setL_cur(String l_cur) {
			this.l_cur = l_cur;
		}
		public String getS() {
			return s;
		}
		public void setS(String s) {
			this.s = s;
		}
		public String getLtt() {
			return ltt;
		}
		public void setLtt(String ltt) {
			this.ltt = ltt;
		}
		public String getLt() {
			return lt;
		}
		public void setLt(String lt) {
			this.lt = lt;
		}
		public String getLt_dts() {
			return lt_dts;
		}
		public void setLt_dts(String lt_dts) {
			this.lt_dts = lt_dts;
		}
		public String getC() {
			return c;
		}
		public void setC(String c) {
			this.c = c;
		}
		public String getC_fix() {
			return c_fix;
		}
		public void setC_fix(String c_fix) {
			this.c_fix = c_fix;
		}
		public String getCp() {
			return cp;
		}
		public void setCp(String cp) {
			this.cp = cp;
		}
		String cp;
		
		
		
		
	}
	
	public static long getPriceForInstrument(String gcode){
		RestTemplate rs = new RestTemplate();
		Client c = ClientBuilder.newClient();
		
		Response res = c.target(preURL + gcode).request().get();
		String sr = res.readEntity(String.class);
		if (sr == null || sr.length() <3)
			return 0;
		
		String subsr= sr.substring(3);
		ObjectMapper om = new ObjectMapper();
		try {
			JsonNode js = om.readTree(subsr);
			String last = js.get(0) .path("l").textValue();
			last = last.replace(",","");
			return Math.round( Double.parseDouble(last) / 100);
			//System.out.println(js.toString());
			//GInfo g = om.readValue(sr, GInfo.class);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//GInfo gi =  rs.getForObject(preURL + gcode, GInfo.class);
		//System.out.println(gi);
		return  0;//Integer.parseInt(gi.getL());
		
		
	}
}
