package com.tutorial.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class AlienRepository {
	Connection con;
	
	public AlienRepository() {
		String url="jdbc:mysql://localhost:3306/restdb?autoReconnect=true&useSSL=false";
		String username="root";
		String password="Tada@5454";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Alien> getAliens() throws SQLException{
		List<Alien>aliens=new ArrayList<>();
		String query="select * from alien";
		PreparedStatement pstmt=con.prepareStatement(query);
		ResultSet set=pstmt.executeQuery();
		while(set.next()) {
			int id=set.getInt("id");
			String name=set.getString("name");
			int points=set.getInt("points");
			aliens.add(new Alien(id,name,points));
		}
		return aliens;
	}
	public Alien getAlien(int id) {
		Alien a;
		String query="select * from alien where id=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet set=pstmt.executeQuery();
			while(set.next()) {
				int id1=set.getInt("id");
				String name=set.getString("name");
				int points=set.getInt("points");
				return new Alien(id1,name,points);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Alien();
		
	}
	public void create(Alien a1) { 
		String query="insert into alien(id,name,points) values(?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,a1.getId());
			pstmt.setString(2,a1.getName());
			pstmt.setInt(3, a1.getPoints());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void updateAlien(Alien a1) {
		try {
		String sql="update alien set name=?,points=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, a1.getName());
		pstmt.setInt(2, a1.getPoints());
		pstmt.setInt(3, a1.getId());
		pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteAlien(int id) {
		try {
			String query="delete from alien where id=?";
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
