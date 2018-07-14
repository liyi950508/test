import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	static Properties properties = new Properties();
	static {
		try {
			properties.load(PropertyMgr.class.getResourceAsStream("config/tank.properties"));
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	}
		
	private PropertyMgr(){};
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
