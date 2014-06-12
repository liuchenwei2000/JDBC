/**
 * 
 */
package jdbc.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * LDAP访问示例
 * <p>
 * LDAP（Lightweight Directory Access Protocol）：轻量级目录访问协议，是一种分层数据库。
 * <p>
 * 如果应用数据遵从树状结构，且读操作远远多于写操作，那么此时应首选LDAP而非关系数据库。
 * LDAP通常主要用于目录存储，且该目录包含了诸如用户名、密码和权限之类的数据。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-16
 */
public class LDAPAccessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String username = "user";
		String password = "12345678";
		// 存储访问LDAP服务器的用户名/密码
		Hashtable env = new Hashtable();
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			// 使用如下语句获得LDAP目录的上下文
			DirContext initialContext = new InitialDirContext(env);
			String ldapURL = "ldap://localhost:389";
			DirContext context = (DirContext) initialContext.lookup(ldapURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}