package com.lmonkey.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lmonkey.dao.Basedao;
import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_CATEGORY;

public class LMONKEY_CARTDao {
	public static int insert(LMONKEY_CART c) {
		String sql = "insert into LMONKEY_CART values(null, ?, ?, ?, ?, ?, ?, ?, 1)";
		
		Object[] params = {
				c.getCART_P_FILENAME(),
				c.getCART_P_NAME(),
				c.getCART_P_PRICE(),
				c.getCART_QUANTITY(),
				c.getCART_P_STOCK(),
				c.getCART_P_ID(),
				c.getCART_U_ID()};
		
		return Basedao.executeIUD(sql, params);
	}
	
	public static ArrayList<LMONKEY_CART> selectByUid(String id) {
		ArrayList<LMONKEY_CART> list = new ArrayList<LMONKEY_CART>();
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql="select * from LMONKEY_CART where CART_U_ID = ? and CART_VALID = 1 order by CART_ID desc";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LMONKEY_CART c = new LMONKEY_CART(
						rs.getInt("CART_ID"), 
						rs.getString("CART_P_FILENAME"), 
						rs.getString("CART_P_NAME"), 
						rs.getInt("CART_P_PRICE"), 
						rs.getInt("CART_QUANTITY"), 
						rs.getInt("CART_P_STOCK"), 
						rs.getInt("CART_P_ID"), 
						rs.getString("CART_U_ID"), 
						rs.getInt("CART_VALID"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return list;
	}
	
	public static LMONKEY_CART selectCartById(String uid, String pid) {
		LMONKEY_CART c = null;
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql="select * from LMONKEY_CART where CART_U_ID = ? and CART_P_ID = ? and CART_VALID = 1";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, pid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				c = new LMONKEY_CART(
						rs.getInt("CART_ID"), 
						rs.getString("CART_P_FILENAME"), 
						rs.getString("CART_P_NAME"), 
						rs.getInt("CART_P_PRICE"), 
						rs.getInt("CART_QUANTITY"), 
						rs.getInt("CART_P_STOCK"), 
						rs.getInt("CART_P_ID"), 
						rs.getString("CART_U_ID"), 
						rs.getInt("CART_VALID"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return c;
	}
	
	public static int updateCartQuantity(int id, int count) {
		String sql="update LMONKEY_CART set CART_QUANTITY=? where CART_ID=?";
		
		Object[] params = {count, id};
		
		return Basedao.executeIUD(sql, params);
	}
	
	public static int deleteById(int id) {
		String sql="delete from LMONKEY_CART where CART_ID = ?";
		
		Object[] params = {id};
		
		return Basedao.executeIUD(sql, params);
	}
	
	public static LMONKEY_CART selectById(String id) {
		LMONKEY_CART c = null;
		
		ResultSet rs = null;
		Connection conn = Basedao.getconn();
		PreparedStatement ps = null;
		
		try {
			String sql="select * from LMONKEY_CART where CART_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				c = new LMONKEY_CART(
						rs.getInt("CART_ID"), 
						rs.getString("CART_P_FILENAME"), 
						rs.getString("CART_P_NAME"), 
						rs.getInt("CART_P_PRICE"), 
						rs.getInt("CART_QUANTITY"), 
						rs.getInt("CART_P_STOCK"), 
						rs.getInt("CART_P_ID"), 
						rs.getString("CART_U_ID"), 
						rs.getInt("CART_VALID"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Basedao.closeall(rs, ps, conn);
		}
		
		return c;
	}
}
