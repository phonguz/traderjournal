package com.trade.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	 public static final String DEFAULT_ENCODING="UTF-8";
	 static BASE64Encoder enc=new BASE64Encoder();
	 static BASE64Decoder dec=new BASE64Decoder();
	 
	 private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	 private static final int RANDOM_STRING_LENGTH = 10;
	 
	 
	public static String base64encode(String text){
	      try {
	         String rez = enc.encode( text.getBytes( DEFAULT_ENCODING ) );
	         return rez;         
	      }
	      catch ( UnsupportedEncodingException e ) {
	         return null;
	      }
	   }//base64encode

	   public static String base64decode(String text){

	         try {
	            return new String(dec.decodeBuffer( text ),DEFAULT_ENCODING);
	         }
	         catch ( IOException e ) {
	           return null;
	         }
 
	   }//base64decode
	   
	   
	   
	   public static String xorMessage(String message, String key){
	       try {
	          if (message==null || key==null ) return null;

	          
	          //String message1=message+"";
	          //String m2= Integer.toString(message);
	          //String m3=new Integer(message).toString();
	          
	         char[] keys=key.toCharArray();
	         char[] mesg=message.toCharArray();

	         int ml=mesg.length;
	         int kl=keys.length;
	         char[] newmsg=new char[ml];

	         for (int i=0; i<ml; i++){
	            newmsg[i]=(char)(mesg[i]^keys[i%kl]);
	         }//for i
	         mesg=null; keys=null;
	         return new String(newmsg);
	      }
	      catch ( Exception e ) {
	         return null;
	       }  
	 }//xorMessage
	   
	   
	   /**
	     * This method generates random string
	     * @return
	     */
	   public String generateRandomString(){
	        
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
		
	   /**
	     * This method generates random numbers
	     * @return int
	     */
		private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }

}
