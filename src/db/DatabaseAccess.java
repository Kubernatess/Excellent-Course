package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import bean.Column;
import bean.Comment;
import bean.Course;
import bean.Pageview;
import bean.Record;
import bean.Tab;
import bean.User;


public class DatabaseAccess {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
	// 新增一个用户或修改一个用户信息
	public static void addUser(User user){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into users(identity,`status`,`password`,`name`,depart,major) values(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE `status`=? , `password`=? , `name`=? , depart=? , major=? ";
			ps=con.prepareStatement(sql);
			ps.setString(1,user.getIdentity());
		    ps.setString(2,user.getStatus());
		    ps.setString(3,user.getPassword());
		    ps.setString(4,user.getName());
		    ps.setString(5,user.getDepart());
			ps.setString(6,user.getMajor());
		    ps.setString(7,user.getStatus());
		    ps.setString(8,user.getPassword());
		    ps.setString(9,user.getName());
		    ps.setString(10,user.getDepart());
		    ps.setString(11,user.getMajor());
		    ps.executeUpdate();
		    JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取所有用户信息
	public static List<Map<String,String>> getAllUser(){
		List<Map<String,String>> list = new ArrayList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from users";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<>();
				map.put("identity",rs.getString("identity"));
				map.put("password",rs.getString("password"));
				map.put("name",rs.getString("name"));
				map.put("status",rs.getString("status"));
				map.put("depart",rs.getString("depart"));
				map.put("major",rs.getString("major"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 根据指定的用户名获得用户信息
	public static Map<String,String> getUserById(String identity){
		Map<String,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from users where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,identity);
			rs=ps.executeQuery();
			
			if(rs.next()){
				map.put("password",rs.getString("password"));
				map.put("name",rs.getString("name"));
				map.put("status",rs.getString("status"));
				map.put("depart",rs.getString("depart"));
				map.put("major",rs.getString("major"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	// 删除一个用户
	public static void deleteUser(String identity){
		try {
			con=JDBCUtil.getConnection();
			String sql = "delete from users where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,identity);
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改用户密码
	public static void modifyPassword(String identity,String confirmPassword){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update users set `password`=? where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,confirmPassword);
			ps.setString(2,identity);
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加一门课程
	public static void addCourse(Course course){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into courses(id,name,description,team,teacher_identity) values(?,?,?,?,?) ON DUPLICATE KEY UPDATE `name`=?, description=?, team=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,course.getId());
		    ps.setString(2,course.getName());
		    ps.setString(3,course.getDescription());
		    ps.setString(4,course.getTeam());
		    ps.setString(5,course.getTeacherIdentity());
			ps.setString(6,course.getName());
		    ps.setString(7,course.getDescription());
		    ps.setString(8,course.getTeam());
		    ps.executeUpdate();
		    JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据教师工号获得课程信息
	public static Map<String,String> getCourseByTeacherID(String teacherIdentity){
		Map<String,String> map = new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from courses where teacher_identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			rs=ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString("id"),rs.getString("name"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
		
		
	// 根据教师工号和课程名称获得课程信息
	public static Map<String,Object> getCourseById(String courseID){
		Map<String,Object> map = new HashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from courses where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("name",rs.getString("name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("teacherIdentity",rs.getString("teacher_identity"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	// 获取所有的课程
	public static List<Map<String,Object>> getAllCourse(){
		List<Map<String,Object>> list = new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from users u join courses c on u.identity=c.teacher_identity";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("courseID",rs.getString("c.id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 根据系别获得课程信息
	public static List<Map<String,Object>> getCourseByDepart(String depart){
		List<Map<String,Object>> list=new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from users u join courses c on u.identity=c.teacher_identity where u.depart=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,depart);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("courseID",rs.getString("c.id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 根据专业名称获得课程信息
	public static List<Map<String,Object>> getCourseByMajor(String major){
		List<Map<String,Object>> list=new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from users u join courses c on u.identity=c.teacher_identity where u.major=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,major);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("courseID",rs.getString("c.id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 模糊查询课程
	public static List<Map<String,Object>> getCourseByKey(String key){
		List<Map<String,Object>> list = new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from users u join course c on u.identity=c.teacher_identity where c.`name` like ?";
			ps=con.prepareStatement(sql);
			ps.setString(1,"%"+key+"%");
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("courseID",rs.getString("c.id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 修改课程代码
	public static void modifyCourseID(String courseID,String originID){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update courses set id=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			ps.setString(2,originID);
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加一个标题栏目或修改标题栏目方法
	public static void addTab(Tab tab){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into tabs(course_id,`index`,`name`) values(?,?,?) ON DUPLICATE KEY UPDATE `name`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,tab.getCourseID());
			ps.setString(2,tab.getIndex());
			ps.setString(3,tab.getName());
			ps.setString(4,tab.getName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据教师工号和课程名获得该课程的标题栏目
	public static Map<String,String> getTabByCourseId(String courseID){
		Map<String,String> map = new LinkedHashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from tabs where course_id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs=ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString("index"),rs.getString("name"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 删除指定的标题栏目
	public static void removeTab(Tab tab){
		try {
			con=JDBCUtil.getConnection();
			String sql = "delete from tabs where course_id=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,tab.getCourseID());
			ps.setString(2,tab.getIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 添加一个新的栏目
	public static void addColumn(Column column){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into columns(course_id,tab_index,`index`,`name`) values(?,?,?,?) ON DUPLICATE KEY UPDATE `name`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getCourseID());
			ps.setString(2,column.getTabIndex());
			ps.setInt(3,column.getIndex());
			ps.setString(4,column.getName());
			ps.setString(5,column.getName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 删除指定的小栏目
	public static void removeColumn(Column column){
		try {
			con=JDBCUtil.getConnection();
			String sql = "delete from columns where course_id=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getCourseID());
			ps.setString(2,column.getTabIndex());
			ps.setInt(3,column.getIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 根据课程名称和标题栏目名称查询对应的子栏目
	public static Map<Integer,String> getColumnByCourseIdAndId(Column column){
		Map<Integer,String> map = new LinkedHashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from columns where course_id=? and tab_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getCourseID());
			ps.setString(2,column.getTabIndex());
			rs=ps.executeQuery();
			while(rs.next()){
				map.put(rs.getInt("index"),rs.getString("name"));
			}
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 修改栏目索引值
	public static void modifyColumnIndex(Column column,int index){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update columns set `index`=? where course_id=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,index);
			ps.setString(2,column.getCourseID());
			ps.setString(3,column.getTabIndex());
			ps.setInt(4,column.getIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// 更新编辑器内容
	public static void updateContent(Column column){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update columns set content=? , answer=? where course_id=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getContent());
			ps.setString(2,column.getAnswer());
			ps.setString(3,column.getCourseID());
			ps.setString(4,column.getTabIndex());
			ps.setInt(5,column.getIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 查询编辑器的内容
	public static Map<String,String> getColumnById(Column column){
		Map<String,String> map = new LinkedHashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from columns where course_id=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getCourseID());
			ps.setString(2,column.getTabIndex());
			ps.setInt(3,column.getIndex());
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("content",rs.getString("content"));
				map.put("answer",rs.getString("answer"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	// 新增一条评论
	public static void addComment(Comment comment){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into comments(course_id,identity,date,comment) values(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,comment.getCourseID());
			ps.setString(2,comment.getIdentity());
			// 精确到年月日时分秒
			ps.setTimestamp(3,new Timestamp(comment.getDate().getTime()));
			ps.setString(4,comment.getComment());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据课程名称和教师工号获得该课程所有的评论
	public static List<Map<String,Object>> getReviewsByCourseId(String courseID){
		List<Map<String,Object>> list=new LinkedList<>();
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from reviews r join users u on r.identity=u.identity where course_id=? ORDER BY date";
			ps = con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("username",rs.getString("name"));
				map.put("date",rs.getDate("date"));
				map.put("review",rs.getString("review"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 获取某个学生已加入的课程
	public static List<Map<String,Object>> getPageviewsByStudentId(String studentIdentity){
		List<Map<String,Object>> list = new LinkedList<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from pageviews p join courses c on p.course_id=c.id where p.student_identity=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,studentIdentity);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("courseID",rs.getString("p.course_id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("hitcount",rs.getInt("hitcount"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 获取某个学生是否参加了某一门课程
	public static boolean isjoined(Record record){
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from records where student_identity=? and course_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,record.getStudentIdentity());
			ps.setString(2,record.getCourseID());
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 添加或更新一次访问量
	public static void addPageview(Pageview pageview){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into pageviews(student_identity,course_id,hitcount) values(?,?,?) ON DUPLICATE KEY UPDATE hitcount=hitcount+1";
			ps=con.prepareStatement(sql);
			ps.setString(1,pageview.getStudentIdentity());
			ps.setString(2,pageview.getCourseID());
			ps.setInt(3,1);
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获得某一门课程参与的学生数量
	public static int getVisitorsByCourseId(String courseID){
		int count = 0;
		try {
			con=JDBCUtil.getConnection();
			String sql = "select count(student_identity) from records where course_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("count(student_identity)");
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	// 获取某一个学生是否完成某一小节的内容
	public static boolean isRecord(Record record){
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from records where course_id=? and tab_index=? and column_index=? and student_identity=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,record.getCourseID());
			ps.setString(2,record.getTabIndex());
			ps.setInt(3,record.getColumnIndex());
			ps.setString(4,record.getStudentIdentity());
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 添加一个绿灯
	public static void addRecord(Record record){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into records(course_id,tab_index,column_index,student_identity) values(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,record.getCourseID());
			ps.setString(2,record.getTabIndex());
			ps.setInt(3,record.getColumnIndex());
			ps.setString(4,record.getStudentIdentity());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
