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
 * �����(save point)ʾ��
 * <p>
 * ʹ�ñ������Ը��õؿ��ƻع�����������һ���������ζ���Ժ�ֻ��ع���������������Ŀ�ʼ��
 *
 * @author ����ΰ
 *
 * �������ڣ�2013-6-15
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
			// ����һ�������
			Savepoint savepoint = con.setSavepoint();
			st.executeUpdate("update table2 set column2=2");
			
			boolean someLogic = true;// ģ��ĳ�ֳ�������
			if(someLogic){
				// �ع���savepoint�ĵط�����Ӱ��savepoint֮ǰ�Ĳ���
				con.rollback(savepoint);
			}
			// ��ʹ���걣�����������в����󣬱����ͷ�����
			con.releaseSavepoint(savepoint);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// ���ｫ��ع�������ʼ�ĵط�
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