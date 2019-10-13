import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DatabaseAccess {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
	// 根据指定的用户名获得用户信息
	public static Map<String,String> selectUser(String username){
		Map<String,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from user where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("password",rs.getString("password"));
				map.put("name",rs.getString("name"));
				map.put("avatar",rs.getString("avatar"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
