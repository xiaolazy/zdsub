import org.junit.Test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * <p>Description: 获取数据库基本信息的工具类</p>
 *
 * @author qxl
 * @date 2016年7月22日 下午1:00:34
 */
/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 10:07
 **/
public class HB {

    @Test
    public void test(){

    }

    /**
     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释
     *
     * @param driver 数据库连接驱动
     * @param url    数据库连接url
     * @param user   数据库登陆用户名
     * @param pwd    数据库登陆密码
     * @param table  表名
     * @return Map集合
     */
    public static List getTableInfo(String driver, String url, String user, String pwd, String table) {
        List result = new ArrayList();

        Connection conn = null;
        DatabaseMetaData dbmd = null;

        try {
            conn = getConnections(driver, url, user, pwd);

            dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[]{"TABLE"});

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(tableName);

                if (tableName.equals(table)) {
                    ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName.toUpperCase(), "%");

                    while (rs.next()) {
                        //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
                        Map map = new HashMap();
                        String colName = rs.getString("COLUMN_NAME");
                        map.put("code", colName);

                        String remarks = rs.getString("REMARKS");
                        if (remarks == null || remarks.equals("")) {
                            remarks = colName;
                        }
                        map.put("name", remarks);

                        String dbType = rs.getString("TYPE_NAME");
                        map.put("dbType", dbType);

                        map.put("valueType", changeDbType(dbType));
                        result.add(map);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private static String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {
            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
                return "1";
            case "NUMBER":
            case "DECIMAL":
                return "4";
            case "INT":
            case "SMALLINT":
            case "INTEGER":
                return "2";
            case "BIGINT":
                return "6";
            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "7";
            default:
                return "1";
        }
    }

    //获取连接
    private static Connection getConnections(String driver, String url, String user, String pwd) throws Exception {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.put("remarksReporting", "true");
            props.put("user", user);
            props.put("password", pwd);
            Class.forName(driver);
            conn = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return conn;
    }

    //其他数据库不需要这个方法 oracle和db2需要
    private static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }

    public static void main(String[] args) {

        //这里是Oracle连接方法

       /* String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.12.44:1521:orcl";
        String user = "bdc";
        String pwd = "bdc123";
        //String table = "FZ_USER_T";
        String table = "FZ_USER_T";
*/
        //mysql
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String pwd = "root";
		String url = "jdbc:mysql://182.92.64.223:3306/zdsub"
				+ "?serverTimezone=GMT%2B8";
		String table = "manager";
		List<String> tables = new ArrayList<>();
		tables.add("manager");
        tables.add("entername");
        tables.add("menu");
        tables.add("role");
        tables.add("school");
        tables.add("workdynamic");
        tables.add("advertises");
        tables.add("need");
        tables.add("process");
        tables.add("policyfile");
        tables.add("manager_role");
        tables.add("menu_role");
        tables.forEach(e->{
            List list = getTableInfo(driver, url, user, pwd, e);
            System.out.println(list);
        });

    }
}

