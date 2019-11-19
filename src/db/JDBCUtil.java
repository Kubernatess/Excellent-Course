package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	private static Connection con;
    private static final String drivername="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/excellent course?useSSL=false&characterEncoding=utf-8";

    //使用静态代码块加载驱动
    static{
    	try{
    		Class.forName(drivername);
		}catch(ClassNotFoundException e){
		   	System.out.println("加载驱动异常");
		   	e.printStackTrace();
		}
    }
    //提供连接方法
    public static synchronized Connection getConnection() throws Exception {
        try {
        	String username="root";
        	String password="502717";
        	con=DriverManager.getConnection(url,username,password);
            return con;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
    
    //关闭连接方法
    public static void close(ResultSet rs,Statement ps,Connection con){
    	try{
    		if(rs!=null)
    			rs.close();   		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	try{
    		if(ps!=null)
    			ps.close();   		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	try{
    		if(con!=null)
    			con.close();   		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
}
