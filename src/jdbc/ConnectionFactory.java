/**
 * 
 */
package jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工厂
 * <p>
 * 实际上是通过一个配置文件存储数据源信息，便于以后修改。
 * 本例只配置了一个数据源，实际上，可以在配置文件中注册多个数据源。
 * 另外，本类直接返回Connecton对象，主流做法应该是返回指定的数据源(DataSource)对象，再由数据源返回Connection。
 * 这样的策略使得程序的灵活性增强，完全可以只修改配置文件而无需修改代码。
 * <p>
 * 本类主要是方便快速其他各类获得Connection对象，增强其他类代码可读性。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-16
 */
public class ConnectionFactory {

	/** 配置文件路径  */
	private static final String CONFIG_FILE = "config/datasource.properties";

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		Properties config = load();
		init(config);
	}

	/**
	 * 创建一个数据库连接
	 */
	public static Connection create() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	private static void init(Properties config) {
		driver = config.getProperty("driver");
		url = config.getProperty("url");
		username = config.getProperty("username");
		password = config.getProperty("password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Properties load() {
		Properties settings = new Properties();
		try {
			FileInputStream in = new FileInputStream(CONFIG_FILE);
			// 加载配置文件信息
			settings.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settings;
	}
}