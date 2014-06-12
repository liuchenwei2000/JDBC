/**
 * 
 */
package jdbc.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * LDAP����ʾ��
 * <p>
 * LDAP��Lightweight Directory Access Protocol����������Ŀ¼����Э�飬��һ�ֲַ����ݿ⡣
 * <p>
 * ���Ӧ�����������״�ṹ���Ҷ�����ԶԶ����д��������ô��ʱӦ��ѡLDAP���ǹ�ϵ���ݿ⡣
 * LDAPͨ����Ҫ����Ŀ¼�洢���Ҹ�Ŀ¼�����������û����������Ȩ��֮������ݡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-16
 */
public class LDAPAccessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String username = "user";
		String password = "12345678";
		// �洢����LDAP���������û���/����
		Hashtable env = new Hashtable();
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			// ʹ�����������LDAPĿ¼��������
			DirContext initialContext = new InitialDirContext(env);
			String ldapURL = "ldap://localhost:389";
			DirContext context = (DirContext) initialContext.lookup(ldapURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}