package org.hatice.tasktrackerapp.constant;

public class EndPoints {
	
	public static final String VERSION = "/v1";
	
	public static final String API = "/api";
	public static final String DEVELOPER = "/dev";
	public static final String TEST = "/test";
	public static final String PROD = "/prod";
	
	public static final String ROOT = VERSION + DEVELOPER;
	
	
	public static final String USERS = ROOT + "/users";
	public static final String TASKS = ROOT + "/tasks";
	public static final String ROLE = ROOT + "/role";
	
	
	//MAIN OPERATIONS
	public static final String SAVE =  "/save";
	public static final String UPDATE = "/update/{userId}";
	public static final String DELETE =  "/delete/{userId}";
	public static final String LIST = "/list";
	public static final String GETBYID = "/get-by-id/{userId}";
	public static final String GETBYUSERNAME = "/get-by-username";
	public static final String GETBYEMAIL =  "/get-by-email";
	public static final String REGISTER =  "/register";
	public static final String LOGIN = "/login";
	public static final String GETUSERBYNAME = "/get-user-by-username";
	//TASKS ENDPOINTS
	
	public static final String GETBYSTATUS = "/get-by-status";
	
	
	
	
}