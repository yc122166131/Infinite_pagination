package com.ibm.core;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * JDBCµÄ·ÖÒ³
 * @author john
 *
 */
public class ConnectionUtil {
	
		public static Connection getConnection(){
			Connection conn = null;
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/desk","root","root");  
				return conn;
			} catch (Exception e) {
				
				e.printStackTrace();
				return null;
			}  
			
		}
		
		public static List<HashMap<String,Object>> getContents(int pageNo,int pageSize){
			PreparedStatement statement = null;
			ResultSet rs = null;
			Connection conn = getConnection();
			List<HashMap<String,Object>> list = null;
			 try {
	            String sql = "SELECT id,name,content from user LIMIT ?,?";
	            statement = conn.prepareStatement(sql);
	            statement.setInt(1, pageNo);
	            statement.setInt(2, pageSize);
            	rs = statement.executeQuery();
			    list = new ArrayList<HashMap<String,Object>>();
	            while(rs.next()){
	            	HashMap<String,Object> map = new HashMap<String,Object>();
	            	map.put("id", rs.getInt("id"));
	            	map.put("name", rs.getString("name"));
	            	map.put("content", rs.getString("content"));
	            	list.add(map);
	            }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						rs.close();
						statement.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return list;
           
		}
		
		public static int getcount(){
			PreparedStatement statement = null;
			ResultSet rs = null;
			Connection conn = getConnection();
			
			 try {
				 	String sql = "SELECT count(1) from user ";
				 	statement = conn.prepareStatement(sql);
            		rs = statement.executeQuery();
            		int count = 0;
	            	if(rs.next()){
	            		count = rs.getInt(1);
	            	}
	            	return count;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						rs.close();
						statement.close();
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return 0 ;
           
		}
	
		public static void main(String[] args) throws Exception   
	    {  
			
			 List<HashMap<String,Object>> list = getContents(10,10);
			 int count = getcount();
			 System.out.println(count);
	    }

		

				
}
