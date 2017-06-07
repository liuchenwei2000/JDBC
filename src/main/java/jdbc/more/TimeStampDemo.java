/**
 * 
 */
package jdbc.more;

import jdbc.ConnectionFactory;

import java.sql.*;
import java.util.Date;

/**
 * Java 中 TimeStamp 类型属性与 MySQL 数据库 DateTime 类型字段匹配示例
 * <p>
 *
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-23
 */
public class TimeStampDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();

			// 插入
			String sql = "INSERT INTO test_demo(code,ts) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "liucw1");
			ps.setTimestamp(2, now());
			ps.executeUpdate();

			ps.close();

			// 查询
			sql = "select * from test_demo";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "  ");
				System.out.print(rs.getString(2) + "  ");
				System.out.print(rs.getTimestamp(3) + "  ");
				System.out.println();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static Timestamp now() {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}
}
