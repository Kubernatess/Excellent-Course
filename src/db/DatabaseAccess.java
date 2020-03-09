package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bean.Column;
import bean.Comment;
import bean.Course;
import bean.Tab;


public class DatabaseAccess {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
	// 根据指定的用户名获得用户信息
	public static Map<String,String> getUserById(String identity){
		Map<String,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from `user` where identity=?";
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
	
	// 获取所有用户工号
	public static List<String> getAllIdentity(){
		List<String> list = new ArrayList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select identity from `user`";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("identity"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 修改用户密码
	public static void modifyPassword(String identity,String confirmPassword){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update `user` set `password`=? where identity=?";
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
			String sql = "insert into course(teacher_identity,name,description,major,team,cover) values(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,description=?,major=?,team=?,cover=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,course.getTeacherIdentity());
		    ps.setString(2,course.getName());
		    ps.setString(3,course.getDescription());
		    ps.setString(4,course.getMajor());
		    ps.setString(5,course.getTeam());
		    ps.setString(6,course.getCover());
		    ps.setString(7,course.getName());
		    ps.setString(8,course.getDescription());
		    ps.setString(9,course.getMajor());
		    ps.setString(10,course.getTeam());
		    ps.setString(11,course.getCover());
		    ps.executeUpdate();
		    JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 根据教师工号和课程名称获得课程信息
	public static Map<String,String> getCourseByIdAndName(String teacherIdentity,String courseName){
		Map<String,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from course where teacher_identity=? and name=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			if(rs.next()){
				map.put("description",rs.getString("description"));
				map.put("major",rs.getString("major"));
				map.put("team",rs.getString("team"));
				map.put("cover",rs.getString("cover"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 根据教师工号获得课程信息
	public static List<Map<String,String>> getCourseById(String teacherIdentity){
		List<Map<String,String>> list=new ArrayList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from course where teacher_identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,String> map=new HashMap<>();
				map.put("courseName",rs.getString("name"));
				map.put("cover",rs.getString("cover"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 根据专业名称获得课程信息
	public static List<Map<String,String>> getCourseByMajor(String major){
		List<Map<String,String>> list=new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from `user` u join course c on u.identity=c.teacher_identity";
			String where = " where c.major=?";
			// 如果没有传递major参数值,则查询所有系得课程
			if(major==""){
				ps=con.prepareStatement(sql);
			}
			// 否则查询指定专业的课程
			else{
				sql += where;
				ps=con.prepareStatement(sql);
				ps.setString(1,major);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,String> map=new HashMap<>();
				map.put("teacherIdentity",rs.getString("teacher_identity"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("cover",rs.getString("cover"));
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
	public static List<Map<String,String>> getCourseByKey(String key){
		List<Map<String,String>> list=new ArrayList<>();
		try {	
			con=JDBCUtil.getConnection();
			String sql = "select * from `user` u join course c on u.identity=c.teacher_identity where c.`name` like ?";
			ps=con.prepareStatement(sql);
			if(key==""){
				ps.setString(1,"");
			}
			else{
				ps.setString(1,"%"+key+"%");
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,String> map=new HashMap<>();
				map.put("teacherIdentity",rs.getString("teacher_identity"));
				map.put("courseName",rs.getString("c.name"));
				map.put("description",rs.getString("description"));
				map.put("team",rs.getString("team"));
				map.put("cover",rs.getString("cover"));
				map.put("depart",rs.getString("depart"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 添加一个标题栏目
	public static void addTab(Tab tab){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into tab(teacher_identity,course_name,`index`,`name`) values(?,?,?,?) ON DUPLICATE KEY UPDATE `name`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,tab.getTeacherIdentity());
			ps.setString(2,tab.getCourseName());
			ps.setInt(3,tab.getTabIndex());
			ps.setString(4,tab.getName());
			ps.setString(5,tab.getName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改标题栏目索引值
	public static void modifyTabIndex(Tab tab,int index){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update tab set `index`=? where teacher_identity=? and course_name=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,index);
			ps.setString(2,tab.getTeacherIdentity());
			ps.setString(3,tab.getCourseName());
			ps.setInt(4,tab.getTabIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据教师工号和课程名获得该课程的标题栏目
	public static Map<Integer,String> selectTabByIdAndName(String teacherIdentity,String courseName){
		Map<Integer,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from tab where teacher_identity=? and course_name=? order by `index`";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			while(rs.next()){
				map.put(rs.getInt("index"),rs.getString("name"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 添加一个新的栏目
	public static void addColumn(Column column){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into `column`(teacher_identity,course_name,tab_index,`index`,`name`) values(?,?,?,?,?) ON DUPLICATE KEY UPDATE `name`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getTeacherIdentity());
			ps.setString(2,column.getCourseName());
			ps.setInt(3,column.getTabIndex());
			ps.setInt(4,column.getColumnIndex());
			ps.setString(5,column.getColumnName());
			ps.setString(6,column.getColumnName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据课程名称和标题栏目名称查询对应的子栏目
	public static Map<Integer,String> selectColumnByIdAndNameAndIndex(String teacherIdentity , String courseName , int tabIndex){
		Map<Integer,String> map=new LinkedHashMap<>();
		try {
			con=JDBCUtil.getConnection();
			String sql = "select * from `column` where teacher_identity=? and course_name=? and tab_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			ps.setInt(3,tabIndex);
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
			String sql = "update `column` set `index`=? where teacher_identity=? and course_name=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,index);
			ps.setString(2,column.getTeacherIdentity());
			ps.setString(3,column.getCourseName());
			ps.setInt(4,column.getTabIndex());
			ps.setInt(5,column.getColumnIndex());
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
			String sql = "update `column` set content=? where teacher_identity=? and course_name=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getContent());
			ps.setString(2,column.getTeacherIdentity());
			ps.setString(3,column.getCourseName());
			ps.setInt(4,column.getTabIndex());
			ps.setInt(5,column.getColumnIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 查询编辑器的内容
	public static String getContent(Column column){
		String content="";
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select content from `column` where teacher_identity=? and course_name=? and tab_index=? and `index`=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getTeacherIdentity());
			ps.setString(2,column.getCourseName());
			ps.setInt(3,column.getTabIndex());
			ps.setInt(4,column.getColumnIndex());
			rs=ps.executeQuery();
			if(rs.next()){
				content=rs.getString("content");
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	// 根据教师工号和课程名来获得课程大纲所有栏目的信息
	public static List<Map<String,Object>> selectTabAndColumnByIdAndName(String teacherIdentity,String courseName){
		List<Map<String,Object>> list=new LinkedList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select tab.`name` , col.`name` from tab join `column` col on tab.teacher_identity=col.teacher_identity and tab.course_name=col.course_name and tab.`index`=col.tab_index  where tab.teacher_identity=? and tab.course_name=? ORDER BY tab.`index` , col.`index`";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			Map<String,Object> map=new LinkedHashMap<>();
			List<String> columnList=new LinkedList<>();
			while(rs.next()){
				if(map.containsValue(rs.getString("tab.name"))){
					columnList.add(rs.getString("col.name"));
				}
				// 如果是新的一级栏目
				else{
					map=new LinkedHashMap<>();
					map.put("tabName",rs.getString("tab.name"));
					columnList=new LinkedList<>();
					columnList.add(rs.getString("col.name"));
					map.put("columnList",columnList);
					list.add(map);
				}
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
			con=JDBCUtil.getConnection();
			String sql = "insert into `comment`(teacher_identity,course_name,identity,date,comment) values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,comment.getTeacherIdentity());
			ps.setString(2,comment.getCourseName());
			ps.setString(3,comment.getIdentity());
			// 精确到年月日时分秒
			ps.setTimestamp(4,new Timestamp(comment.getDate().getTime()));
			ps.setString(5,comment.getComment());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 根据课程名称和教师工号获得该课程所有的评论
	public static List<Map<String,Object>> getAllCommentByIdAndName(String teacherIdentity,String courseName){
		List<Map<String,Object>> list=new LinkedList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from `comment` c join `user` u on c.identity=u.identity where teacher_identity=? and course_name=? ORDER BY date";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			while(rs.next()){
				Map<String,Object> map=new HashMap<>();
				if(rs.getString("name")==null){
					map.put("username",rs.getString("u.identity"));
				}
				else{
					map.put("username",rs.getString("name"));
				}
				map.put("date",rs.getDate("date"));
				map.put("comment",rs.getString("comment"));
				list.add(map);
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
