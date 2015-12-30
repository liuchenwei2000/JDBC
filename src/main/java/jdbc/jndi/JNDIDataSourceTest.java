/**
 * 
 */
package jdbc.jndi;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 高级连接管理示例
 * <p>
 * 在企业环境中部署JDBC应用时，数据库连接管理与Java名字与目录接口(JNDI)是集成在一起的。
 * 遍布企业的数据源的属性可以被存储在同一个目录中，采用这种方式可以集中管理用户名、密码、数据库名和JDBC URL。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-16
 */
public class JNDIDataSourceTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			/**
			 * 不再使用DriverManager，而是使用JNDI服务来定位数据源。
			 * 数据源是一个能够提供简单的JDBC连接和更多高级服务的接口，比如执行涉及多个数据库的分布式事务。
			 */
			Context jndiContext = new InitialContext();
			/**
			 * 必须在某个地方设置数据源，如果数据库程序将在Web容器中运行，比如Tomcat，
			 * 或者在应用服务器中运行，比如Websphere，那么必须将数据源配置信息放置到指定的配置文件中。
			 */
			String name = "java:comp/env/jdbc/mydb";// 数据源配置信息的位置
			DataSource dataSource = (DataSource)jndiContext.lookup(name);
			
			Connection connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
