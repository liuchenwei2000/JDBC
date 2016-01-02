## Statement相关类总结 ##

* 1，执行静态 SQL 语句，通常通过 Statement 实例实现。
* 2，执行动态 SQL 语句，通常通过 PreparedStatement 实例实现。
* 3，执行数据库存储过程，通常通过 CallableStatement 实例实现。


示例

* 1，Statement stmt = con.createStatement();
* 2，PreparedStatement pstmt = con.prepareStatement(sql);
* 3，CallableStatement stmt = con.prepareCall("call ...");