/**
 * 
 */
package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 数据库元数据
 * <p>
 * JDBC可以提供关于数据库结构和表的详细信息。
 * 例如可以获取某个数据库的所有表的列表，也可以获得某个表中所有列的名称及其数据类型。
 * 这些数据库的结构信息对编写数据库工具的程序员而言是极其有用的。
 * <p>
 * 描述数据库或其组成部分的数据称为元数据(区别于那些存在数据库中的实际数据)。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-15
 */
public class DatabaseMetaDataTest {
	
	/**
	 * 共可以获得三类元数据，如下三个方法。
	 */

	/**
	 * 关于数据库的元数据(DatabaseMetaData)
	 */
	public static void showDatabaseMetaData(Connection con) throws SQLException {
		// DatabaseMetaData封装了有关数据库连接的元数据
		DatabaseMetaData meta = con.getMetaData();
		// 类别名称：它必须与存储在数据库中的类别名称匹配
		String catalog = null;
		// 模式名称的模式：它必须与存储在数据库中的模式名称匹配
		String schemaPattern = null;
		// 表名称模式；它必须与存储在数据库中的表名称匹配
		String tableNamePattern = null;
		// 表类型名称：典型的类型为 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。 
		String[] types = new String[]{"TABLE"};
		/**
		 * 下面的方法会返回数据库中所有的表信息，结果集包括五列：
		 * TABLE_CAT：表目录，对应参数中的catalog。
		 * TABLE_SCHEM：表结构模式，对应参数中的schemaPattern。
		 * TABLE_NAME：表名，对应参数中的tableNamePattern。
		 * TABLE_TYPE：表类型，对应参数中的types。
		 * REMARKS：表的注释
		 */
		ResultSet tables = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
		// 打印结果集原信息，可和上面的表信息对比
		showResultSetMetaData(tables);
		
		System.out.println("***tables begins***");
		
		while (tables.next()) {
			// 第三列是表名
			System.out.println(tables.getString(3) + "  ");
		}
		
		System.out.println("***tables ends***");
		
		/**
		 * 数据库是非常复杂的，SQL标准为数据库的多样性提供了很大的空间。
		 * DatabaseMetaData类中有上百个方法可以用于查询数据库的相关信息，包括一些奇特名字的方法。
		 * 如下：
		 */
		meta.nullPlusNonNullIsNull();
		/**
		 * 这些方法主要针对有特殊要求的高级客户，尤其是那些需要编写涉及多个数据库且具备可移植性的程序员。
		 * 比如下面的方法可以获知该数据库是否支持可滚动的结果集
		 */
		meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		
		// 返回连接使用的JDBC驱动程序主版本号
		System.out.println("JDBCMajorVersion=" + meta.getJDBCMajorVersion());
		// 返回连接使用的JDBC驱动程序次版本号
		System.out.println("JDBCMinorVersion=" + meta.getJDBCMinorVersion());
		// 返回数据库的最大连接数
		System.out.println("MaxConnections=" + meta.getMaxConnections());
		// 返回单个数据库连接可同时打开的语句最大值
		System.out.println("MaxStatements=" + meta.getMaxStatements());
	}

	/**
	 * 关于结果集的元数据(ResultSetMetaData)
	 */
	public static void showResultSetMetaData(ResultSet rs) throws SQLException{
		ResultSetMetaData meta = rs.getMetaData();
		showResultSetMetaData0(meta);
	}

	private static void showResultSetMetaData0(ResultSetMetaData meta) throws SQLException {
		// 返回结果集中列数
		System.out.println("ColumnCount=" + meta.getColumnCount());
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			// 返回指定列名称(列序号是从1开始计数)
			System.out.print("Column Name=" + meta.getColumnName(i));
			// 返回指定列的类型
			System.out.print(",Column Type Name=" + meta.getColumnTypeName(i));
			// 返回指定列宽
			System.out.println(",Column Display Size=" + meta.getColumnDisplaySize(i));
		}
	}

	/**
	 * 关于预备语句(PreparedStatement)参数的元数据
	 */
	public static void showDatabaseMetaData(PreparedStatement ps) throws SQLException{
		
		/**
		 * 获取包含有关 ResultSet 对象列信息的 ResultSetMetaData 对象，ResultSet 对象将在执行此 PreparedStatement 对象时返回。 
		 * 因为 PreparedStatement 对象被预编译，所以不必执行就可以知道它将返回的 ResultSet 对象。
		 * 因此，可以对 PreparedStatement 对象调用 getMetaData 方法，而不必等待执行该对象，
		 * 然后再对返回的 ResultSet 对象调用 ResultSet.getMetaData 方法。
		 */
		ResultSetMetaData rs = ps.getMetaData();
		showResultSetMetaData0(rs);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.create();
			showDatabaseMetaData(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}