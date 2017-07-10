/**
 * 
 */
package jdbc.transaction;

import jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批量插入示例
 * <p>
 * 利用 MySQL 的插入特性完成更高性能的批量插入操作。
 *
 * @author 刘晨伟
 *
 * 创建日期：2013-6-15
 */
public class BatchInsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			boolean isSupport = con.getMetaData().supportsBatchUpdates();
			if(isSupport){
				boolean autoCommit = con.getAutoCommit();// 备份是否自动提交属性
				con.setAutoCommit(false);
				Statement st = con.createStatement();

                String sql = "insert into test_demo(code,ts) values ";

                for (int i = 1; i < 1000; i++) {
                    sql += "('code" + i + "',null),";
                }

                sql = sql.substring(0, sql.length() - 1);
                // insert into test_demo(code,ts) values ('code1',null),('code2',null),('code3',null)...
                System.out.println(sql);

                st.addBatch(sql);

                int[] rows = st.executeBatch();

                con.commit();
                con.setAutoCommit(autoCommit);// 恢复是否自动提交属性
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
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
		long b = System.currentTimeMillis();
		System.out.println((b-a)/1000d + "s");
	}
}
