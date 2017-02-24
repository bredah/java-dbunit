package exemple.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Help commands
 * 
 * @since 20170224
 * @version 1.0
 * @author henriquebreda
 */
public abstract class Utils {

	private static Properties prop;

	/**
	 * Absolute path from MAIN resource
	 * 
	 * @return Absolute path from "src/main/resources"
	 */
	public static String getPathResource() {
		return new File("src/main/resources").getAbsoluteFile() + File.separator;
	}

	/**
	 * Absolute path from TEST resource
	 * 
	 * @return Absolute path from "src/test/resources"
	 */
	public static String getPathResourceTest() {
		return new File("src/test/resources").getAbsolutePath() + File.separator;
	}

	/**
	 * Get a specific property form database
	 * 
	 * @param property
	 *            Database property
	 * @return Database information
	 */
	public static String getDbProperty(DbProperties property) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(getPathResource() + "database.properties"));
			switch (property) {
			case DRIVER_CLASS:
			case CONNECTION_URL:
			case USERNAME:
			case PASSWORD:
			case SCHEMA:
				return prop.getProperty(property.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
