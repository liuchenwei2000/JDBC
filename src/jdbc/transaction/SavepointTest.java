/**
 * 
 */
package jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * 保存点(save point)示例
 * <p>
 * 使用保存点可以更好地控制回滚操作，创建一个保存点意味着稍后只需回滚到这个点而非事务的开始。
 *
 * @author 刘晨伟
 *
 * 创建日期：2013-6-15
 */
public class SavepointTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			Statement st = con.createStatement();
			st.executeUpdate("update table1 set column1=1");
			// 创建一个保存点
			Savepoint savepoint = con.setSavepoint();
			st.executeUpdate("update table2 set column2=2");
			
			boolean someLogic = true;// 模拟某种出错情形
			if(someLogic){
				// 回滚到savepoint的地方，不影响savepoint之前的操作
				con.rollback(savepoint);
			}
			// 当使用完保存点完成了所有操作后，必须释放它。
			con.releaseSavepoint(savepoint);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 这里将会回滚到事务开始的地方
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