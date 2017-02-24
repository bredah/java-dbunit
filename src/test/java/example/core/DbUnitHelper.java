package example.core;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

import exemple.core.DbProperties;
import exemple.core.Utils;

/**
 * DbUnit Helper class
 * 
 * @since 20170224
 * @version 1.0
 * @author henriquebreda
 */
public class DbUnitHelper extends DBTestCase {

	public DbUnitHelper(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				Utils.getDbProperty(DbProperties.DRIVER_CLASS));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				Utils.getDbProperty(DbProperties.CONNECTION_URL));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				Utils.getDbProperty(DbProperties.USERNAME));
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				Utils.getDbProperty(DbProperties.PASSWORD));
		// System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
		// Utils.getDbProperty(DbProperties.SCHEMA));

	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return null;
	}

	@Override
	/**
	 * Override method to set custom properties/features
	 */
	protected void setUpDatabaseConfig(DatabaseConfig config) {
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
	}

	@Override
	/**
	 * Operation before executing each test
	 */
	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	@Override
	/**
	 * Operation after executing each test
	 */
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}
}
