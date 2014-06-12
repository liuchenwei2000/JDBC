package jdbc.statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import jdbc.ConnectionFactory;

/**
 * ���ô洢����ʾ��
 *
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-19
 */
public class CallProcedureDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Connection con = ConnectionFactory.create();
			// CallableStatement��ר���������ô洢���̵�
			CallableStatement cstmt = con.prepareCall("CALL age_pro (?, ?)");
			/*
			 * age_pro�洢���̶��壺
			 * 
			 * create procedure age_pro(IN age int,OUT errormsg varchar(20))
			 * begin
			 * if age<0 then 
			 * select 'age cannot below 0.' into errormsg;
			 * end if;
			 * end
			 */
			cstmt.setInt(1, -1);// �����������ֵ
			cstmt.registerOutParameter(2, Types.VARCHAR);// ע�������������
			cstmt.executeQuery();// ���ô洢����
			String errorinfo = cstmt.getString(2);// ��ȡ���������ֵ
			cstmt.close();
			System.out.println(errorinfo);
			/*
			 * �����ô洢����ʱ��������Ĵ��� 
			 * ERROR 1370 (42000): execute command denied to user backupAccount@'localhost' for routine 'databaseName.spName' 
			 * 
			 * ����취 ���Ը��˻�����ִ�д洢���̵�Ȩ�� 
			 * 
			 * mysql> grant execute on procedure databaseName.spName to 'backupAccount'@'localhost'; 
			 * mysql> flush privileges; 
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}