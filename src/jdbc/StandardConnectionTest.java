/**
 * 
 */
package jdbc;

import java.sql.*;

/**
 * ��׼���ݿ�����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-28
 */
public class StandardConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";// ���ݿ���������
		String url = "jdbc:mysql://localhost:3306/mydb";// ���ݿ�URL
		String id = "liucw1";// ��½�ʺ�
		String pw = "woailo99";// ����
		
		Connection connection = null;
		try {
			Class.forName(driver);// ��������������JDBC4.0��ʼ�����д������ʡ��
			connection = DriverManager.getConnection(url, id, pw);// ��������

			/** �������ݣ����²��������ͬ��ֻ��sql��һ�� */
			String sql = "insert into person(pk_person,name,age) values ('person1000','����',20)";
			Statement stmt = connection.createStatement();// ����Statement����
			int rows = stmt.executeUpdate(sql);// ִ�и��£����ر��β���Ӱ�������
			stmt.close();
			
			/** ��ѯ���� */
			sql = "select * from person";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);// ִ�в�ѯ�����ؽ����
			// ��ӡ�������Ϣ
			while (rs.next()) {// ���Ų����һ�У���û���򷵻�false
				System.out.print(rs.getString(1) + "  ");// ����ָ���е�ֵ���д�1��ʼ����
				System.out.print(rs.getString(2) + "  ");
				System.out.print(rs.getInt(3) + "  ");
				System.out.println();
			}
			rs.close();
			stmt.close();
			
			/** ɾ������ */
			sql = "delete from person where pk_person = 'person1000'";
			stmt = connection.createStatement();
			rows = stmt.executeUpdate(sql);// ִ�и��£�ɾ���������ر��β���Ӱ�������
			System.out.println(rows + " rows have been deleted.");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���Ҫ��finally���йر�����
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}