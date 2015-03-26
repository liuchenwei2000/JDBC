/**
 * 
 */
package jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * ��������ʾ��
 * <p>
 * �����Ҫִ�ж��ָ��²���(��insert��update��delete)������ʹ���������µķ�����������ܡ�
 *
 * @author ����ΰ
 *
 * �������ڣ�2013-6-15
 */
public class BatchUpdateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// ������ݿ��Ƿ�֧������������������
			boolean isSupport = con.getMetaData().supportsBatchUpdates();
			/*
			 * Ϊ��������ģʽ����ȷ������󣬱��뽫����ִ�еĲ�����Ϊ��������
			 * �������������ִ�й�����ʧ�ܣ���ô���뽫���ع�������������ʼ֮ǰ��״̬��
			 * 
			 * �����������£�
			 * 1���ر��Զ��ύģʽ��
			 * 2���ռ�����������
			 * 3��ִ�в��ύ����������
			 * 4���ָ�������Զ��ύģʽ��
			 */
			if(isSupport){
				boolean autoCommit = con.getAutoCommit();// �����Ƿ��Զ��ύ����
				// �ر��Զ��ύģʽ
				con.setAutoCommit(false);
				/*
				 * ��ʹ���������µ�ʱ��һ��������Ϊһ���������н�ͬʱ���ռ����ύ��
				 * ����ͬһ���е����������insert��update��delete��Ҳ������DDL�����create table��drop table
				 * �����������������������select�����Ϊִ��select��佫�᷵��һ���������
				 */
				Statement st = con.createStatement();
				
				for (int i = 0; i < 10; i++) {
					String sql = "update table" + i + " set column" + i + "=" + i;
					// ģ�������������(10��)
					st.addBatch(sql);
				}
				// ִ���������£����������Ԫ����ÿ��������Ӱ�������
				@SuppressWarnings("unused")
				int[] rows = st.executeBatch();
				
				con.commit();
				con.setAutoCommit(autoCommit);// �ָ��Ƿ��Զ��ύ����
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