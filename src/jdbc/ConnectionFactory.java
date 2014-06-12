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
 * ���ݿ����ӹ���
 * <p>
 * ʵ������ͨ��һ�������ļ��洢����Դ��Ϣ�������Ժ��޸ġ�
 * ����ֻ������һ������Դ��ʵ���ϣ������������ļ���ע��������Դ��
 * ���⣬����ֱ�ӷ���Connecton������������Ӧ���Ƿ���ָ��������Դ(DataSource)������������Դ����Connection��
 * �����Ĳ���ʹ�ó�����������ǿ����ȫ����ֻ�޸������ļ��������޸Ĵ��롣
 * <p>
 * ������Ҫ�Ƿ����������������Connection������ǿ���������ɶ��ԡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-16
 */
public class ConnectionFactory {

	/** �����ļ�·��  */
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
	 * ����һ�����ݿ�����
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
			// ���������ļ���Ϣ
			settings.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settings;
	}
}