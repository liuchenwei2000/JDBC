/**
 * 
 */
package jdbc;

import java.sql.*;

/**
 * 标准数据库连接示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-28
 */
public class StandardConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";// 数据库驱动器类
		String url = "jdbc:mysql://localhost:3306/mydb";// 数据库URL
		String id = "liucw1";// 登陆帐号
		String pw = "woailo99";// 密码
		
		Connection connection = null;
		try {
			Class.forName(driver);// 加载驱动程序，自JDBC4.0开始，这行代码可以省略
			connection = DriverManager.getConnection(url, id, pw);// 创建连接

			/** 插入数据，更新操作与此相同，只是sql不一样 */
			String sql = "insert into person(pk_person,name,age) values ('person1000','张三',20)";
			Statement stmt = connection.createStatement();// 创建Statement对象
			int rows = stmt.executeUpdate(sql);// 执行更新，返回本次操作影响的行数
			stmt.close();
			
			/** 查询数据 */
			sql = "select * from person";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);// 执行查询并返回结果集
			// 打印结果集信息
			while (rs.next()) {// 光标挪到下一行，若没有则返回false
				System.out.print(rs.getString(1) + "  ");// 返回指定列的值，列从1开始计数
				System.out.print(rs.getString(2) + "  ");
				System.out.print(rs.getInt(3) + "  ");
				System.out.println();
			}
			rs.close();
			stmt.close();
			
			/** 删除数据 */
			sql = "delete from person where pk_person = 'person1000'";
			stmt = connection.createStatement();
			rows = stmt.executeUpdate(sql);// 执行更新（删除），返回本次操作影响的行数
			System.out.println(rows + " rows have been deleted.");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 最后要在finally块中关闭连接
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