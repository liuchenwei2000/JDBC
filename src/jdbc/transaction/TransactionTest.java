/**
 * 
 */
package jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * �����һ���������
 *
 * @author ����ΰ
 *
 * �������ڣ�2013-6-15
 */
public class TransactionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// Ĭ������£����ݿ����Ӵ����Զ��ύģʽ
			// ÿ��SQL���һ����ִ�б㱻�ύ�����ݿ⣬˳���ύ֮����޷��ڶ�����лع�����
			boolean autoCommit = con.getAutoCommit();
			System.out.println("autoCommit=" + autoCommit);
			// �ر��Զ��ύģʽ
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			
			// ���潫��θ��²����ϲ�Ϊһ������
			st.executeUpdate("update table1 set column1=1");
			st.executeUpdate("update table2 set column2=2");
			st.executeUpdate("update table3 set column3=3");
			// ִ������������֮��(��ζ���������)������commit
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// �����������˴�������Ҫ�ع���
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