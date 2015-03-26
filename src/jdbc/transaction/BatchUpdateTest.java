/**
 * 
 */
package jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * 批量更新示例
 * <p>
 * 如果需要执行多种更新操作(如insert、update、delete)，可以使用批量更新的方法来提高性能。
 *
 * @author 刘晨伟
 *
 * 创建日期：2013-6-15
 */
public class BatchUpdateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// 检查数据库是否支持批量更新这种特性
			boolean isSupport = con.getMetaData().supportsBatchUpdates();
			/*
			 * 为了在批量模式下正确处理错误，必须将批量执行的操作视为单个事务。
			 * 如果批量更新在执行过程中失败，那么必须将它回滚到批量操作开始之前的状态。
			 * 
			 * 基本步骤如下：
			 * 1，关闭自动提交模式。
			 * 2，收集批量操作。
			 * 3，执行并提交批量操作。
			 * 4，恢复最初的自动提交模式。
			 */
			if(isSupport){
				boolean autoCommit = con.getAutoCommit();// 备份是否自动提交属性
				// 关闭自动提交模式
				con.setAutoCommit(false);
				/*
				 * 在使用批量更新的时候，一批操作作为一个命令序列将同时被收集和提交。
				 * 处于同一批中的命令可以是insert、update和delete，也可以是DDL命令，如create table、drop table
				 * 不过不能在批量处理中添加select命令，因为执行select语句将会返回一个结果集。
				 */
				Statement st = con.createStatement();
				
				for (int i = 0; i < 10; i++) {
					String sql = "update table" + i + " set column" + i + "=" + i;
					// 模拟加入批量操作(10个)
					st.addBatch(sql);
				}
				// 执行批量更新，返回数组的元素是每个更新所影响的行数
				@SuppressWarnings("unused")
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
	}
}