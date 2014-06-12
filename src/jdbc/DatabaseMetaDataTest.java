/**
 * 
 */
package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * ���ݿ�Ԫ����
 * <p>
 * JDBC�����ṩ�������ݿ�ṹ�ͱ����ϸ��Ϣ��
 * ������Ի�ȡĳ�����ݿ�����б���б�Ҳ���Ի��ĳ�����������е����Ƽ����������͡�
 * ��Щ���ݿ�Ľṹ��Ϣ�Ա�д���ݿ⹤�ߵĳ���Ա�����Ǽ������õġ�
 * <p>
 * �������ݿ������ɲ��ֵ����ݳ�ΪԪ����(��������Щ�������ݿ��е�ʵ������)��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-15
 */
public class DatabaseMetaDataTest {
	
	/**
	 * �����Ի������Ԫ���ݣ���������������
	 */

	/**
	 * �������ݿ��Ԫ����(DatabaseMetaData)
	 */
	public static void showDatabaseMetaData(Connection con) throws SQLException {
		// DatabaseMetaData��װ���й����ݿ����ӵ�Ԫ����
		DatabaseMetaData meta = con.getMetaData();
		// ������ƣ���������洢�����ݿ��е��������ƥ��
		String catalog = null;
		// ģʽ���Ƶ�ģʽ����������洢�����ݿ��е�ģʽ����ƥ��
		String schemaPattern = null;
		// ������ģʽ����������洢�����ݿ��еı�����ƥ��
		String tableNamePattern = null;
		// ���������ƣ����͵�����Ϊ "TABLE"��"VIEW"��"SYSTEM TABLE"��"GLOBAL TEMPORARY"��"LOCAL TEMPORARY"��"ALIAS" �� "SYNONYM"�� 
		String[] types = new String[]{"TABLE"};
		/**
		 * ����ķ����᷵�����ݿ������еı���Ϣ��������������У�
		 * TABLE_CAT����Ŀ¼����Ӧ�����е�catalog��
		 * TABLE_SCHEM����ṹģʽ����Ӧ�����е�schemaPattern��
		 * TABLE_NAME����������Ӧ�����е�tableNamePattern��
		 * TABLE_TYPE�������ͣ���Ӧ�����е�types��
		 * REMARKS�����ע��
		 */
		ResultSet tables = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
		// ��ӡ�����ԭ��Ϣ���ɺ�����ı���Ϣ�Ա�
		showResultSetMetaData(tables);
		
		System.out.println("***tables begins***");
		
		while (tables.next()) {
			// �������Ǳ���
			System.out.println(tables.getString(3) + "  ");
		}
		
		System.out.println("***tables ends***");
		
		/**
		 * ���ݿ��Ƿǳ����ӵģ�SQL��׼Ϊ���ݿ�Ķ������ṩ�˺ܴ�Ŀռ䡣
		 * DatabaseMetaData�������ϰٸ������������ڲ�ѯ���ݿ�������Ϣ������һЩ�������ֵķ�����
		 * ���£�
		 */
		meta.nullPlusNonNullIsNull();
		/**
		 * ��Щ������Ҫ���������Ҫ��ĸ߼��ͻ�����������Щ��Ҫ��д�漰������ݿ��Ҿ߱�����ֲ�Եĳ���Ա��
		 * ��������ķ������Ի�֪�����ݿ��Ƿ�֧�ֿɹ����Ľ����
		 */
		meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
		
		// ��������ʹ�õ�JDBC�����������汾��
		System.out.println("JDBCMajorVersion=" + meta.getJDBCMajorVersion());
		// ��������ʹ�õ�JDBC��������ΰ汾��
		System.out.println("JDBCMinorVersion=" + meta.getJDBCMinorVersion());
		// �������ݿ�����������
		System.out.println("MaxConnections=" + meta.getMaxConnections());
		// ���ص������ݿ����ӿ�ͬʱ�򿪵�������ֵ
		System.out.println("MaxStatements=" + meta.getMaxStatements());
	}

	/**
	 * ���ڽ������Ԫ����(ResultSetMetaData)
	 */
	public static void showResultSetMetaData(ResultSet rs) throws SQLException{
		ResultSetMetaData meta = rs.getMetaData();
		showResultSetMetaData0(meta);
	}

	private static void showResultSetMetaData0(ResultSetMetaData meta) throws SQLException {
		// ���ؽ����������
		System.out.println("ColumnCount=" + meta.getColumnCount());
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			// ����ָ��������(������Ǵ�1��ʼ����)
			System.out.print("Column Name=" + meta.getColumnName(i));
			// ����ָ���е�����
			System.out.print(",Column Type Name=" + meta.getColumnTypeName(i));
			// ����ָ���п�
			System.out.println(",Column Display Size=" + meta.getColumnDisplaySize(i));
		}
	}

	/**
	 * ����Ԥ�����(PreparedStatement)������Ԫ����
	 */
	public static void showDatabaseMetaData(PreparedStatement ps) throws SQLException{
		
		/**
		 * ��ȡ�����й� ResultSet ��������Ϣ�� ResultSetMetaData ����ResultSet ������ִ�д� PreparedStatement ����ʱ���ء� 
		 * ��Ϊ PreparedStatement ����Ԥ���룬���Բ���ִ�оͿ���֪���������ص� ResultSet ����
		 * ��ˣ����Զ� PreparedStatement ������� getMetaData �����������صȴ�ִ�иö���
		 * Ȼ���ٶԷ��ص� ResultSet ������� ResultSet.getMetaData ������
		 */
		ResultSetMetaData rs = ps.getMetaData();
		showResultSetMetaData0(rs);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.create();
			showDatabaseMetaData(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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