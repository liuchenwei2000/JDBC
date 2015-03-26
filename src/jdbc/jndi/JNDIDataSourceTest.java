/**
 * 
 */
package jdbc.jndi;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * �߼����ӹ���ʾ��
 * <p>
 * ����ҵ�����в���JDBCӦ��ʱ�����ݿ����ӹ�����Java������Ŀ¼�ӿ�(JNDI)�Ǽ�����һ��ġ�
 * �鲼��ҵ������Դ�����Կ��Ա��洢��ͬһ��Ŀ¼�У��������ַ�ʽ���Լ��й����û��������롢���ݿ�����JDBC URL��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-16
 */
public class JNDIDataSourceTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			/**
			 * ����ʹ��DriverManager������ʹ��JNDI��������λ����Դ��
			 * ����Դ��һ���ܹ��ṩ�򵥵�JDBC���Ӻ͸���߼�����Ľӿڣ�����ִ���漰������ݿ�ķֲ�ʽ����
			 */
			Context jndiContext = new InitialContext();
			/**
			 * ������ĳ���ط���������Դ��������ݿ������Web���������У�����Tomcat��
			 * ������Ӧ�÷����������У�����Websphere����ô���뽫����Դ������Ϣ���õ�ָ���������ļ��С�
			 */
			String name = "java:comp/env/jdbc/mydb";// ����Դ������Ϣ��λ��
			DataSource dataSource = (DataSource)jndiContext.lookup(name);
			
			Connection connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}