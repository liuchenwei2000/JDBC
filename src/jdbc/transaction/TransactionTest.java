/**
 * 
 */
package jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * 事务的一般操作过程
 *
 * @author 刘晨伟
 *
 * 创建日期：2013-6-15
 */
public class TransactionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// 默认情况下，数据库连接处于自动提交模式
			// 每个SQL语句一旦被执行便被提交给数据库，顺利提交之后就无法在对其进行回滚操作
			boolean autoCommit = con.getAutoCommit();
			System.out.println("autoCommit=" + autoCommit);
			// 关闭自动提交模式
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			
			// 下面将多次更新操作合并为一个事务
			st.executeUpdate("update table1 set column1=1");
			st.executeUpdate("update table2 set column2=2");
			st.executeUpdate("update table3 set column3=3");
			// 执行了所有命令之后(意味着事务结束)，调用commit
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 如果事务出现了错误则需要回滚。
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
