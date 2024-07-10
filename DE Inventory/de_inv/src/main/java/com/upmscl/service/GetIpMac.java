package com.upmscl.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class GetIpMac extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	byte[] mac1;
    String macaddr;

	public String getClientIPAddress(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteUser();
		}
		return request.getHeader("x-forwarded-for");
	}

	public String getClientIPAddress() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ipAdd = request.getHeader("X-FORWARDED-FOR");
		if (ipAdd == null) {
			ipAdd = request.getRemoteAddr();
		}
		System.out.println("Ip address of requested client is " + ipAdd);
		return ipAdd;
	}

	//this method i am using
	public InetAddress getClientIPAddress1() throws UnknownHostException {
		InetAddress thisIp =InetAddress.getLocalHost();
		return thisIp;
	}

	public String getClientMACAddress(String clientIP) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + clientIP);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}
	
	public String getClientMACAddress1(String clientIP) {
		
		 try {

	            InetAddress add = InetAddress.getByName(clientIP);
				/*
				 * byte[] mac1; String macaddr;
				 */

	            NetworkInterface ni1 = NetworkInterface.getByInetAddress(add);
	            if (ni1 != null) {
	                mac1 = ni1.getHardwareAddress();
	                macaddr = new String(mac1, StandardCharsets.US_ASCII); //type cast byte array to string
	                if (mac1 != null) {

	                    for (int k = 0; k < mac1.length; k++) {
	                        int i = 0;
							System.out.format("%02X%s", mac1[k], (i < mac1.length - 1) ? "-" : "ha bhai");
	                    }
	                } else {
	                    System.out.println("Address doesn't exist ");
	                }
	            } else {
	                System.out.println("address is not found.");
	            }
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
		return macaddr;
	    }

	 {
	 }
}
