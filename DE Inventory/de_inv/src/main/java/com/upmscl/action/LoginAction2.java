package com.upmscl.action;

import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.upmscl.bean.User;
import com.upmscl.service.CaptchaService;
import com.upmscl.service.GetIpMac;
import com.upmscl.service.HashedPassword;
import com.upmscl.service.LoginService;
import com.upmscl.test.GetMacAddress;

public class LoginAction2 extends ActionSupport implements SessionAware, ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap; // local variable for session handelling.
	private String sessionCaptcha = (String) ServletActionContext.getRequest().getSession()
			.getAttribute(CaptchaService.CAPTCHA_KEY);
	private String captcha;
	String loggedUserName = null;
	String clientIP = null;
	String macAdress = null;
	String systemName = null;

	// Model object User
	private User user = new User();
	LoginService loginService = new LoginService();

	// hashed password object
	HashedPassword hp = new HashedPassword();

	// Object Get IPmacaddress
	GetIpMac getIpMac;

	public String login() throws Exception {

		// Hashing and salting password SHA-256
		//hashPassword();
		if (loginService.validateUser(user,getCaptcha())) {
			/*
			 * check if captcha entered is correct or not if not then send to login page ,
			 * else send for userid and password verification
			 */
			if (!sessionCaptcha.equals(captcha)) {
				user.setMsg("captcha not match");
				return "login";
			} else {
				// check if the userName is already stored in the session
				if (sessionMap.containsKey("userName")) {
					loggedUserName = (String) sessionMap.get("username");
				}
				if (loggedUserName != null) {
					return "success"; // return welcome page
				} else if (loginService.verifyLogin(user)) {

					getIpMacAndSaveinDb(); // Get Client IP and system name and save in DB

					// add/store userName to the session variable username
					sessionMap.put("username", user.getUsername());
					return "success";
				} else {
					return "login";
				}
			}
		}
		return "login";
	}

	private void getIpMacAndSaveinDb() throws SQLException, Exception {
		/*
		 * // getting Client IP and Mac addresses
		 */
		getIpMac = new GetIpMac();
		clientIP = getIpMac.getClientIPAddress1().getHostAddress();
		getIpMac.getClientIPAddress1();
		systemName = InetAddress.getLocalHost().getHostName();

		// getting current time for adding login time in db
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeNow = dtf.format(now);

		loginService.addLoginTrail(clientIP, systemName, dateTimeNow, user.getUsername());
		loginService.addLoginDetails(user.getUsername(), clientIP, systemName, dateTimeNow);
		/*
		 * // getting Client IP and Mac addresses
		 */
	}

	private void hashPassword() throws NoSuchAlgorithmException {
		byte[] salt = hp.getSalt();
		String hashedPassword = hp.getSecurePassword(user.getPassword(), salt);
		user.setPassword(hashedPassword); // password hased
	}

	public String logout() {
		// remove userName from the session
		if (sessionMap.containsKey("username")) {
			sessionMap.remove("username");
			// Invalidate the session
		}
		return "logout";
	}

	/*
	 * _________________________getters and
	 * setters__________________________________________
	 */
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getSessionCaptcha() {
		return sessionCaptcha;
	}

	public void setSessionCaptcha(String sessionCaptcha) {
		this.sessionCaptcha = sessionCaptcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}