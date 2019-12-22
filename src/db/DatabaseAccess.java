package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bean.Column;
import bean.Course;
import bean.Subcolumn;
import bean.Tab;
import bean.Team;

public class DatabaseAccess {
	
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
    
	// 根据指定的用户名获得用户信息
	public static Map<String,String> getUserByName(String username){
		Map<String,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from `user` where identity=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			rs=ps.executeQuery();
			
			if(rs.next()){
				map.put("password",rs.getString("password"));
				map.put("name",rs.getString("name"));
				map.put("avatar",rs.getString("avatar"));
				map.put("status",rs.getString("status"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	// 添加一门课程
	public static void addCourse(Course course){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into course(teacher_identity,name,description,depart,major,cover,template) values(?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,course.getTeacherIdentity());
		    ps.setString(2,course.getName());
		    ps.setString(3,course.getDescription());
		    ps.setString(4,course.getDepart());
		    ps.setString(5,course.getMajor());
		    ps.setString(6,course.getCover());
		    ps.setString(7,course.getTemplate());
		    ps.executeUpdate();
		    JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 添加一门课程
	public static void addTeam(Team team){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into team(teacher_identity,course_name,member) values(?,?,?)";
			for(int i=0;i<team.getMember().size();i++){
				ps=con.prepareStatement(sql);
				ps.setString(1,team.getTeacherIdentity());
				ps.setString(2,team.getCourseName());
				ps.setString(3,team.getMember().get(i));
				ps.executeUpdate();
			}
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
				map.put("depart",rs.getString("depart"));
				map.put("major",rs.getString("major"));
				map.put("template",rs.getString("template"));
				map.put("cover",rs.getString("cover"));
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	// 根据教师工号和课程名称获得该课程的教学队伍
	public static List<String> selectTeamByIdAndName(String teacherIdentity,String courseName){
		List<String> list=new ArrayList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from team where teacher_identity=? and course_name=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("member"));
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
			String sql = "insert into tab(teacher_identity,course_name,tab_index,name) values(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,tab.getTeacherIdentity());
			ps.setString(2,tab.getCourseName());
			ps.setInt(3,tab.getTabIndex());
			ps.setString(4,tab.getName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改标题栏目名称
	public static void modifyTab(Tab tab){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update tab set name=? where teacher_identity=? and course_name=? and tab_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,tab.getName());
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
	
	
	// 修改标题栏目索引值
	public static void modifyTabIndex(Tab tab,int index){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update tab set tab_index=? where teacher_identity=? and course_name=? and tab_index=?";
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
	public static Map<Integer,String> selectTagNameByIdAndName(String teacherIdentity,String courseName){
		Map<Integer,String> map=new HashMap<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select * from tab where teacher_identity=? and course_name=? order by tab_index";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			while(rs.next()){
				map.put(rs.getInt("tab_index"),rs.getString("name"));
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
			String sql = "insert into `column`(teacher_identity,course_name,tab_index,column_index,column_name) values(?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getTeacherIdentity());
			ps.setString(2,column.getCourseName());
			ps.setInt(3,column.getTabIndex());
			ps.setInt(4,column.getColumnIndex());
			ps.setString(5,column.getColumnName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改栏目名称
	public static void modifyColumn(Column column){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update `column` set column_name=? where teacher_identity=? and course_name=? and tab_index=? and column_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,column.getColumnName());
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
	
	
	// 修改栏目索引值
	public static void modifyColumnIndex(Column column,int index){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update `column` set column_index=? where teacher_identity=? and course_name=? and tab_index=? and column_index=?";
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
	
	
	// 添加一个栏目
	public static void addSubColumn(Subcolumn subcolumn){
		try {
			con=JDBCUtil.getConnection();
			String sql = "insert into subcolumn(teacher_identity,course_name,tab_index,column_index,subcolumn_index,subcolumn_name) values(?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,subcolumn.getTeacherIdentity());
			ps.setString(2,subcolumn.getCourseName());
			ps.setInt(3,subcolumn.getTabIndex());
			ps.setInt(4,subcolumn.getColumnIndex());
			ps.setInt(5,subcolumn.getSubcolumnIndex());
			ps.setString(6,subcolumn.getSubcolumnName());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改子栏目名称
	public static void modifySubColumn(Subcolumn subcolumn){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update subcolumn set subcolumn_name=? where teacher_identity=? and course_name=? and tab_index=? and column_index=? and subcolumn_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,subcolumn.getSubcolumnName());
			ps.setString(2,subcolumn.getTeacherIdentity());
			ps.setString(3,subcolumn.getCourseName());
			ps.setInt(4,subcolumn.getTabIndex());
			ps.setInt(5,subcolumn.getColumnIndex());
			ps.setInt(6,subcolumn.getSubcolumnIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 修改子栏目索引值
	public static void modifySubcolumnIndex(Subcolumn subcolumn,int index){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update subcolumn set subcolumn_index=? where teacher_identity=? and course_name=? and tab_index=? and column_index=? and subcolumn_index=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,index);
			ps.setString(2,subcolumn.getTeacherIdentity());
			ps.setString(3,subcolumn.getCourseName());
			ps.setInt(4,subcolumn.getTabIndex());
			ps.setInt(5,subcolumn.getColumnIndex());
			ps.setInt(6,subcolumn.getSubcolumnIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 根据教师工号和课程名以及选项卡标记来获得所有子栏目信息
	public static List<Map<String,Object>> selectColumnAndSubcolumnByIdAndNameAndIndex(String teacherIdentity,String courseName,int tabIndex){
		List<Map<String,Object>> list=new ArrayList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select col.column_index , col.column_name , sub.subcolumn_index , sub.subcolumn_name from `column` col join subcolumn sub on col.teacher_identity=sub.teacher_identity and col.course_name=sub.course_name and col.tab_index=sub.tab_index and col.column_index=sub.column_index and col.teacher_identity=? and col.course_name=? and col.tab_index=? order by col.column_index , sub.subcolumn_index";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			ps.setInt(3,tabIndex);
			rs=ps.executeQuery();
			Map<String,Object> map=new LinkedHashMap<>();
			Map<Integer,String> subMap=new LinkedHashMap<>();
			while(rs.next()){
				if(map.containsValue(rs.getInt("col.column_index"))){
					subMap.put(rs.getInt("sub.subcolumn_index"),rs.getString("sub.subcolumn_name"));
				}
				// 如果是新的一级栏目
				else{
					map=new LinkedHashMap<>();
					map.put("columnIndex",rs.getInt("col.column_index"));
					map.put("columnName",rs.getString("col.column_name"));
					subMap=new LinkedHashMap<>();
					subMap.put(rs.getInt("sub.subcolumn_index"),rs.getString("sub.subcolumn_name"));
					map.put("subMap",subMap);
					list.add(map);
				}
			}
			JDBCUtil.close(rs,ps,con);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 根据教师工号和课程名来获得课程大纲所有栏目的信息
	public static List<Map<String,Object>> selectTabAndColumnAndSubcolumnByIdAndName(String teacherIdentity,String courseName){
		List<Map<String,Object>> list=new LinkedList<>();
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select tab.`name` , col.column_name from tab join `column` col on tab.teacher_identity=col.teacher_identity and tab.course_name=col.course_name and tab.tab_index=col.tab_index  where tab.teacher_identity=? and tab.course_name=? ORDER BY tab.tab_index , col.column_index";
			ps=con.prepareStatement(sql);
			ps.setString(1,teacherIdentity);
			ps.setString(2,courseName);
			rs=ps.executeQuery();
			Map<String,Object> map=new LinkedHashMap<>();
			List<String> columnList=new LinkedList<>();
			while(rs.next()){
				if(map.containsValue(rs.getString("tab.name"))){
					columnList.add(rs.getString("col.column_name"));
				}
				// 如果是新的一级栏目
				else{
					map=new LinkedHashMap<>();
					map.put("tabName",rs.getString("tab.name"));
					columnList=new LinkedList<>();
					columnList.add(rs.getString("col.column_name"));
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
	
	// 更新编辑器内容
	public static void updateContent(Subcolumn subcolumn){
		try {
			con=JDBCUtil.getConnection();
			String sql = "update subcolumn set content=? where teacher_identity=? and course_name=? and tab_index=? and column_index=? and subcolumn_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,subcolumn.getContent());
			ps.setString(2,subcolumn.getTeacherIdentity());
			ps.setString(3,subcolumn.getCourseName());
			ps.setInt(4,subcolumn.getTabIndex());
			ps.setInt(5,subcolumn.getColumnIndex());
			ps.setInt(6,subcolumn.getSubcolumnIndex());
			ps.executeUpdate();
			JDBCUtil.close(rs,ps,con);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 查询编辑器的内容
	public static String getContent(Subcolumn subcolumn){
		String content="";
		try {			
			con=JDBCUtil.getConnection();
			String sql = "select content from subcolumn where teacher_identity=? and course_name=? and tab_index=? and column_index=? and subcolumn_index=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,subcolumn.getTeacherIdentity());
			ps.setString(2,subcolumn.getCourseName());
			ps.setInt(3,subcolumn.getTabIndex());
			ps.setInt(4,subcolumn.getColumnIndex());
			ps.setInt(5,subcolumn.getSubcolumnIndex());
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
	
}
