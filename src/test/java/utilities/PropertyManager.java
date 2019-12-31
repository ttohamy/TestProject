package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	private static PropertyManager instance ; 
	private  static String username1; 
	private static String password1; 
	private  static String username2; 
	private static String password2; 
	private static String username3;
	private static String password3;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir")+
            "/resources/configuration.properties";
    
    public static PropertyManager getInstance() {
    	if(instance == null) {
    		synchronized (lock) {
    			instance = new PropertyManager();
    			instance.loadData();
    		}
    	}
    	return instance;
    }
    public void loadData() {
        Properties prop = new Properties();
        try {
        prop.load(new FileInputStream(propertyFilePath));
        }catch(IOException e) {
        	System.out.println("Configuration properties file cannot be found");
        }
        username1 = prop.getProperty("username1");
        password1 = prop.getProperty("password1");
        username2 = prop.getProperty("username2");
        password2 = prop.getProperty("password2");
        username3 = prop.getProperty("username3");
        password3 = prop.getProperty("password3");

        

    }
	
	public String getUsername1() {
		return username1 ; 
	}
	public String getPassword1() {
		return password1 ; 
	}
	public String getUsername2() {
		return username2 ; 
	}
	public String getPassword2() {
		return password2 ; 
	}
	public String getUsername3() {
		return username3 ;
	}
	public String getPassword3() {
		return password3 ;
	}
	
}
