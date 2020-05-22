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
import bean.*;


public class DatabaseAccess {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
	// 新增一个用户或修改一个用户信息
	public static void addUser(User user){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into users(identity,`status`,`password`,`name`,depart) values(?,?,?,?,?) ON DUPLICATE KEY UPDATE `status`=? , `password`=? , `name`=? , depart=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,user.getIdentity());
		    ps.setString(2,user.getStatus());
		    ps.setString(3,user.getPassword());
		    ps.setString(4,user.getUsername());
		    ps.setString(5,user.getDepart());
			ps.setString(6,user.getStatus());
		    ps.setString(7,user.getPassword());
		    ps.setString(8,user.getUsername());
		    ps.setString(9,user.getDepart());
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
				map.put("username",rs.getString("name"));
				map.put("status",rs.getString("status"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 根据指定的用户名获得用户信息
	public static Map<String,String> getUser(String identity){
		Map<String,String> map = new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from users where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,identity);
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("password",rs.getString("password"));
				map.put("username",rs.getString("name"));
				map.put("status",rs.getString("status"));
				map.put("depart",rs.getString("depart"));
			}
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
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
	
	
	
	// 添加一门课程或更新课程信息
	public static void addCourse(Course course){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into courses(id,name,major,description,team,teacher_identity) values(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE `name`=?, major=?, description=?, team=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,course.getId());
		    ps.setString(2,course.getName());
		    ps.setString(3,course.getMajor());
		    ps.setString(4,course.getDescription());
		    ps.setString(5,course.getTeam());
		    ps.setString(6,course.getTeacherIdentity());
			ps.setString(7,course.getName());
			ps.setString(8,course.getMajor());
		    ps.setString(9,course.getDescription());
		    ps.setString(10,course.getTeam());
		    ps.executeUpdate();
		    JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据教师工号获得课程信息
	public static Map<String,String> getAllCourseByTeacherId(String teacherIdentity){
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
		
	
		
	// 根据课程代码获得课程信息
	public static Map<String,String> getCourse(String courseID){
		Map<String,String> map = new HashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from courses where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("courseName",rs.getString("name"));
				map.put("major",rs.getString("major"));
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
	public static List<Map<String,String>> getAllCourseAndDetail(String key,String depart,String major){
		List<Map<String,String>> list = new ArrayList<>();
		try {	
			con = JDBCUtil.getConnection();
			String sql = "";
			if(key.equals("")&&depart.equals("")&&major.equals("")){
				sql = "select * from users u join courses c on u.identity=c.teacher_identity";
				ps = con.prepareStatement(sql);
			}			
			else if(!key.equals("")){
				sql = "select * from users u join courses c on u.identity=c.teacher_identity where c.`name` like ?";
				ps = con.prepareStatement(sql);
				ps.setString(1,"%"+key+"%");
			}			
			else if(!depart.equals("")){
				sql = "select * from users u join courses c on u.identity=c.teacher_identity where u.depart=?";
				ps = con.prepareStatement(sql);
				ps.setString(1,depart);
			}			
			else if(key.equals("")&&depart.equals("")&&!major.equals("")){
				sql = "select * from users u join courses c on u.identity=c.teacher_identity where c.major=?";
				ps = con.prepareStatement(sql);
				ps.setString(1,major);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<>();
				map.put("courseID",rs.getString("c.id"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
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
	
	// 修改课程代码
	public static void modifyCourseID(String courseID,String defaultID){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update courses set id=? where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			ps.setString(2,defaultID);
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
	public static Map<String,String> getAllTabByCourseId(String courseID){
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
	
	// 根据课程名称和标题栏目名称查询对应的子栏目
	public static Map<Integer,String> getColumnsByCourseIdAndTabId(String courseID,String tabIndex){
		Map<Integer,String> map = new LinkedHashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from columns where course_id=? and tab_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			ps.setString(2,tabIndex);
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
	
	// 查询编辑器的内容
	public static Map<String,String> getColumn(Column column){
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
				map.put("columnName",rs.getString("name"));
				map.put("answer",rs.getString("answer"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 根据课程名称获取所有的子栏目
	public static List<Map<String,Object>> getTestsByCourseId(String courseID){
		List<Map<String,Object>> list = new LinkedList<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from columns where course_id=? and answer is not null and answer!=\"\" ";
			ps=con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new LinkedHashMap<>();
				map.put("tabIndex",rs.getString("tab_index"));
				map.put("columnIndex",rs.getInt("index"));
				map.put("columnName",rs.getString("name"));
				map.put("answer",rs.getString("answer"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	// 获取所有课程的参与人数
	public static Map<String,Integer> getAllVisitor(){
		Map<String,Integer> map = new LinkedHashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select course_id , count(distinct student_identity) from records group by course_id ORDER BY student_identity";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString("course_id"),rs.getInt("count(distinct student_identity)"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 更新访问量
	public static void addHitcount(Record record){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into records(course_id,tab_index,column_index,student_identity) values(?,?,?,?) ON DUPLICATE KEY UPDATE hitcount=hitcount+1";
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
	
	// 添加一个完成度
	public static void addCompletion(Record record){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into records(course_id,tab_index,column_index,student_identity) values(?,?,?,?) ON DUPLICATE KEY UPDATE finished=1";
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
	
	// 添加学生分数
	public static void addScore(Record record){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "insert into records(course_id,tab_index,column_index,student_identity) values(?,?,?,?) ON DUPLICATE KEY UPDATE score=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,record.getCourseID());
			ps.setString(2,record.getTabIndex());
			ps.setInt(3,record.getColumnIndex());
			ps.setString(4,record.getStudentIdentity());
			ps.setFloat(5,record.getScore());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取学生在某个子页面的一条记录
	public static Map<String,Object> getRecord(Record record){
		Map<String,Object> map = new HashMap<>();
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
				map.put("hitcount",rs.getString("hitcount"));
				map.put("score",rs.getFloat("score"));
				map.put("finished",rs.getBoolean("finished"));
			}
			JDBCUtil.close(rs,ps,con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 获得学生所加入的所有课程
	public static Map<String,String> getRecordsByStudentId(String studentIdentity){
		Map<String,String> map = new HashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select DISTINCT course_id , c.`name` from records r,courses c where course_id=c.id and student_identity=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,studentIdentity);
			rs = ps.executeQuery();
			while(rs.next()){
				map.put(rs.getString("course_id"),rs.getString("c.name"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 获取某个学生是否参加了某一门课程
	public static List<Map<String,Object>> getAllRecordByStudentId(String studentIdentity,String courseID){
		List<Map<String,Object>> list = new LinkedList<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from records where student_identity=? and course_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,studentIdentity);
			ps.setString(2,courseID);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new LinkedHashMap<>();
				map.put("tabIndex",rs.getString("tab_index"));
				map.put("columnIndex",rs.getInt("column_index"));
				map.put("hitcount",rs.getInt("hitcount"));
				map.put("score",rs.getFloat("score"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	// 新增一条评论
	public static void addComment(Comment comment){
		try {			
			con = JDBCUtil.getConnection();
			String sql = "insert into comments(identity,date_time,course_id,tab_index,column_index,opposite,lunch_time,`comment`) values(?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1,comment.getIdentity());
			ps.setTimestamp(2,comment.getDateTime());
			ps.setString(3,comment.getCourseID());
			ps.setString(4,comment.getTabIndex());
			ps.setInt(5,comment.getColumnIndex());
			ps.setString(6,comment.getOpposite());
			ps.setTimestamp(7,comment.getLunchTime());
			ps.setString(8,comment.getComment());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获得某一课程的所有评价
	public static List<Map<String,String>> getAllReviewAndDetail(String courseID){
		List<Map<String,String>> list = new LinkedList<>();
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from comments c join users u on c.identity=u.identity where course_id=? and tab_index='' and column_index=0 ORDER BY date_time";
			ps = con.prepareStatement(sql);
			ps.setString(1,courseID);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<>();
				map.put("identity",rs.getString("identity"));
				map.put("username",rs.getString("name"));
				map.put("dateTime",rs.getTimestamp("date_time").toString());
				map.put("comment",rs.getString("comment"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 获得某一小节的所有评论
	public static List<Map<String,String>> getAllCommentAndDetail(Comment comment){
		List<Map<String,String>> list = new LinkedList<>();
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from comments c join users u on c.identity=u.identity where course_id=? and tab_index=? and column_index=? ORDER BY date_time";
			ps = con.prepareStatement(sql);
			ps.setString(1,comment.getCourseID());
			ps.setString(2,comment.getTabIndex());
			ps.setInt(3,comment.getColumnIndex());
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,String> map = new HashMap<>();
				map.put("identity",rs.getString("identity"));
				map.put("username",rs.getString("name"));
				map.put("dateTime",rs.getTimestamp("date_time").toString());
				map.put("comment",rs.getString("comment"));
				map.put("opposite",rs.getString("opposite"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 获得某一学生的所有消息
	public static List<Map<String,Object>> getAllMessageAndDetail(String identity){
		List<Map<String,Object>> list = new LinkedList<>();
		try {
			con = JDBCUtil.getConnection();
			String sql = "select * from comments c join users u on c.identity=u.identity where opposite=? ORDER BY date_time";
			ps = con.prepareStatement(sql);
			ps.setString(1,identity);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map = new HashMap<>();
				map.put("identity",rs.getString("identity"));
				map.put("username",rs.getString("name"));
				map.put("dateTime",rs.getTimestamp("date_time").toString());
				map.put("comment",rs.getString("comment"));
				map.put("lunchTime",rs.getTimestamp("lunch_time"));
				map.put("courseID",rs.getString("course_id"));
				map.put("tabIndex",rs.getString("tab_index"));
				map.put("columnIndex",rs.getInt("column_index"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 获得一条评论
	public static String getComment(Comment comment){
		String comments = "";
		try {
			con = JDBCUtil.getConnection();
			String sql = "select `comment` from comments where identity=? and date_time=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,comment.getIdentity());
			ps.setTimestamp(2,comment.getDateTime());
			rs = ps.executeQuery();
			if(rs.next()){
				comments = rs.getString("comment");
			}			
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}
	
	// 删除一条评论
	public static void removeComment(String identity,Timestamp dateTime){
		try {			
			con=JDBCUtil.getConnection();
			String sql = "delete from comments where identity=? and date_time=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,identity);
			ps.setTimestamp(2,dateTime);
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
