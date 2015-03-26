/**
 * 
 */
package jdbc.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.ConnectionFactory;

/**
 * PreparedStatementʾ��
 * <p>
 * PreparedStatementʵ�������ѱ����SQL��䣬��ʾ����׼���á���
 * ������PreparedStatement�����е�SQL�����Ծ���һ�����߶������������
 * ��Щ������SQL����ʱδ��ָ����ֻ��Ϊ���Ǳ���һ��?��Ϊռλ����
 * ÿ��?��ֵ�����ڸ����ִ��֮ǰ��ͨ��setXXX�������ṩ��
 * <p>
 * �����ŵ㣺
 * <li>1��ÿ����ѯ���ݿ�ִ��һ����ѯʱ������������ͨ��������ȷ����ѯ���ԣ��Ա��Ч��ִ�в�ѯ������
 * ͨ������׼���õĲ�ѯ����������������ǾͿ���ȷ����ѯ�����׼������ֻ��ִ��һ�Ρ�
 * PreparedStatement������Ԥ�������������ִ���ٶ�Ҫ����Statement����
 * ��ˣ����ִ�е�SQL��侭������ΪPreparedStatement���������Ч�ʡ�
 * <li>2��������Ч��ֹSQLע�롣
 *
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-23
 */
public class PreparedStatementDemo {
	
	// ��̬sql��? ��ռλ��(placeholder)
	private static final String SQL = "select * from user where username = ? and age = ?";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// ������ͨ�� Statement
			Statement s = con.createStatement();
			// ���� PreparedStatement
			PreparedStatement ps = con.prepareStatement(SQL);
			// ����?�Ĳ�ͬ���͵��������setXXX��������һ��������?��λ�ã��ڶ���������������ֵ
			ps.setString(1, "liucw1");
			ps.setInt(2, 27);
			// ��ʱִ�и��ֲ����Ѿ�����Ҫ������
			ps.executeQuery();

			s.close();
			ps.close();
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
}