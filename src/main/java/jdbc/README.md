## JDBC Framework ##

JDBC(Java DataBase Connectivity) 是一种可用于执行 SQL 语句的 Java API。它由 JDBC 驱动程序管理器、驱动程序、连接器、执行 SQL 的语句和获取数据库查询结果等组成。

### JDBC 体系结构 ###

应用程序--》JDBC API--》JDBC Driver Manager--》数据库驱动--》数据库

### JDBC 的主要功能 ###

* 通过连接器建立与数据库的连接。
* 调用JDBC API向数据库发送SQL语句。
* 处理数据库返回结果。

### JDBC各组件作用 ###

* 驱动程序管理器

	负责装载和管理各个数据库软件商提供的正确的数据库驱动程序，即将Java应用程序连接到正确的JDBC驱动程序上。

* 驱动程序

	负责定位并存取数据库数据（访问数据库）。

* 连接器

	负责Java应用程序同数据库的连接。

### JDBC URL 使用的标准语法 ###

`jdbc:<子协议>:<子名称>`

* 子协议：表示驱动程序名或数据库连接机制。
* 子名称：确定数据资源，标识数据库。

### 重要的类和接口 ###

`java.sql.DriverManager`用于对数据库驱动程序的管理、注册、注销以及连接等。其主要功能是可以自动为数据库访问加载合适的驱动程序进行数据库连接。

`java.sql.Connection` 处理与特定数据库的连接，能够处理锁定表、提交和恢复数据、断开连接等功能。

`java.sql.Statement` 在指定连接处处理 SQL 语句。从 Connection 对象请求一个 Statement 对象，并且把 SQL 语句放入到这个对象中，可以认为 Statement 对象是一个信封，把 SQL 语句放在信封中，而 Connection 对象是信封的传送者（邮递员）。当 SQL 语句传送给连接后，再把这些消息转发给驱动程序，驱动程序把这些 SQL 语句传送给数据库，然后以结果集的形式返回结果。

`java.sql.ResultSet` 处理数据库操作结果集。
