package jdbc.statement;
/**
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * PreparedStatement示例
 * <p>
 * PreparedStatement实例包含已编译的SQL语句，表示“已准备好”。
 * 包含在PreparedStatement对象中的SQL语句可以具有一个或者多个待定参数，
 * 这些参数在SQL创建时未被指定，只是为它们保留一个?作为占位符。
 * 每个?的值必须在该语句执行之前，通过setXXX方法来提供。
 * <p>
 * 两个优点：
 * <li>1，每当查询数据库执行一个查询时，它总是首先通过计算来确定查询策略，以便高效地执行查询操作。
 * 通过事先准备好的查询并多次重用它，我们就可以确保查询所需的准备步骤只被执行一次。
 * PreparedStatement对象已预编译过，所以其执行速度要快于Statement对象。
 * 因此，多次执行的SQL语句经常创建为PreparedStatement对象，以提高效率。
 * <li>2，可以有效防止SQL注入。
 *
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-23
 */
public class PreparedStatementDemo {
	
	// 动态sql，? 是占位符(placeholder)
	private static final String SQL = "select * from user where username = ? and age = ?";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection con =  ConnectionFactory.create();
			Statement s = con.createStatement();
			PreparedStatement ps = con.prepareStatement(SQL);
			// 根据?的不同类型调用相符的setXXX方法，第一个参数是?的位置，第二个参数是真正的值
			ps.setString(1, "liucw1");
			ps.setInt(2, 27);
			// 此时执行各种操作已经不需要参数了
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}