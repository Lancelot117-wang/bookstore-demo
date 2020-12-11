package com.lmonkey.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;

public class LMONKEY_CATEGORYDao {
	public static int insert(LMONKEY_CATEGORY c) {
		String sql = "insert into LMONKEY_CATEGORY values(null, ?, ?)";
		
		Object[] params = {
				c.getCATE_NAME(),
				c.getCATE_PARENT_ID()};
		
		return Basedao.executeIUD(sql, params);
	}
	
	public static int deleteById(int id) {
		String sql = "delete from LMONKEY_CATEGORY where CATE_ID=?";
		
		Object[] params = {id};
		
		return Basedao.executeIUD(sql, params);
	}
	
	public static ArrayList<LMONKEY_CATEGORY> selectAll() {
		ArrayList<LMONKEY_CATEGORY> list = new ArrayList<LMONKEY_CATEGORY>();
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql = "select * from LMONKEY_CATEGORY";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LMONKEY_CATEGORY u = new LMONKEY_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return list;
	}
	
	public static ArrayList<LMONKEY_CATEGORY> selectCate(String flag) {
		ArrayList<LMONKEY_CATEGORY> list = new ArrayList<LMONKEY_CATEGORY>();
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql = null;
			
			if(flag!=null && flag.equals("parent")) {
				sql = "select * from LMONKEY_CATEGORY where CATE_PARENT_ID=0";
			}else {
				sql = "select * from LMONKEY_CATEGORY where CATE_PARENT_ID!=0";
			}				

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LMONKEY_CATEGORY u = new LMONKEY_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return list;
	}
	
	public static LMONKEY_CATEGORY selectById(int id) {
		LMONKEY_CATEGORY c = null;
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql="select * from LMONKEY_CATEGORY where CATE_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				c = new LMONKEY_CATEGORY(
						rs.getInt("CATE_ID"),
						rs.getString("CATE_NAME"),
						rs.getInt("CATE_PARENT_ID")
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return c;
	}
	
	public static int update(LMONKEY_CATEGORY c) {
		String sql = "update LMONKEY_CATEGORY set CATE_NAME=?, CATE_PARENT_ID=? where CATE_ID=?";
		
		Object[] params = {
				c.getCATE_NAME(),
				c.getCATE_PARENT_ID(),
				c.getCATE_ID()};
		
		return Basedao.executeIUD(sql, params);
	}
}
