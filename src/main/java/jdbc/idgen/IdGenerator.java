package jdbc.idgen;

import jdbc.ConnectionFactory;

import java.sql.*;

/**
 * 统一的主键生成器
 * <p>
 *     利用
 *     REPLACE INTO sequence(stub) values('a');
 *     select LAST_INSERT_ID();
 *     来获取保证不重复的 id
 * <p>
 * Created by liuchenwei on 2017/8/1.
 */
public class IdGenerator {

    public static int generate() throws SQLException {
        int id = -1;
        Connection con = null;
        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.create();

            // 插入
            String sql = "REPLACE INTO sequence(stub) values('a')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

            // 查询
            sql = "select LAST_INSERT_ID()";

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 10; i++) {
            System.out.println(generate());
        }
    }
}
