package JDBC;

import java.sql.*;

public class JDBCTest {
    /*
    * JDBC six steps:
    * */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
          /*
           * 1.Register driver
           * The first way
           * DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
          */

            /*
            * The second way
            * Use class loading to trigger the static code block
            * */
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.Connect
            String url = "jdbc:mysql://localhost:3306/mysql_learning?&useSSL=false&serverTimezone=UTC";
            String userName = "root";
            String password = "root1234";
            conn = DriverManager.getConnection(url, userName, password);
            //3.Get the operation object of database
            stmt = conn.createStatement();

            //Execute sql sentences
//            String sql = "insert into EMP(deptno, ename, empno) values(10, 'BINJK', '7794')";
//            String sql = "delete from EMP where ename = 'BINJK'";
//            int retVal = stmt.executeUpdate(sql);
//            System.out.println("Insert " + (retVal == 1 ? "successful" : "failed"));

            /*
            * int executeUpdate(insert/update/delete)
            * ResultSet executeQuery(select)
            * */

            /*
            * Query result
            * ResultSet executeQuery(select)
            * */

            ResultSet rs = stmt.executeQuery("select * from EMP");

            /*
            * Get the data from ResultSet
            * If .next() return true, the next is not null
            * */

            while (rs.next()) {
                String empno = rs.getString("empno"); //Get the column through column name
                String ename = rs.getString(2); //Get the column through column index

                System.out.println(empno + " " + ename);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
