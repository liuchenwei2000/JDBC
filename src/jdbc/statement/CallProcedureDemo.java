package jdbc.statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import jdbc.ConnectionFactory;

/**
 * 调用存储过程示例
 *
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-19
 */
public class CallProcedureDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.create();
			// CallableStatement 是专门用来调用存储过程的
			CallableStatement cstmt = con.prepareCall("CALL age_pro (?, ?)");
			/*
			 * age_pro存储过程定义：
			 * 
			 * create procedure age_pro(IN age int,OUT errormsg varchar(20))
			 * begin
			 * if age<0 then 
			 * select 'age cannot below 0.' into errormsg;
			 * end if;
			 * end
			 */
			cstmt.setInt(1, -1);// 设置输入参数值
			cstmt.registerOutParameter(2, Types.VARCHAR);// 注册输出参数类型
			cstmt.executeQuery();// 调用存储过程
			
			String errorinfo = cstmt.getString(2);// 获取输出参数的值
			cstmt.close();
			System.out.println(errorinfo);
			
			/*
			 * 若调用存储过程时报了下面的错误： 
			 * ERROR 1370 (42000): execute command denied to user backupAccount@'localhost' for routine 'databaseName.spName' 
			 * 
			 * 解决办法 ：对该账户授予执行存储过程的权限 
			 * 
			 * mysql> grant execute on procedure databaseName.spName to 'backupAccount'@'localhost'; 
			 * mysql> flush privileges; 
			 */
		} catch (SQLException e) {
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
