package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.DBUtil;
import vo.WelcomeVO;

public class WelcomeDAO {
	public static WelcomeDAO dao = new WelcomeDAO();
	
	private WelcomeDAO() {}
	
	public int getMaxID()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT MAX(id) + 1 maxId FROM welcome_tbl";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt("maxId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return 0;
	}
	
	public List<WelcomeVO> getList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT * FROM welcome_tbl ORDER BY id DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<WelcomeVO> list = new ArrayList<WelcomeVO>();
			while(rs.next())
			{
				int id = rs.getInt("id");
				String msg = rs.getString("msg");
				WelcomeVO vo = new WelcomeVO(id, msg);
				list.add(vo);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return null;
	}
	
	public int add(int id, String msg)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "INSERT INTO welcome_tbl(id, msg) VALUES(?, ?)";
			pstmt = con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return 0;
	}
	
	public int delete(int id)
	{
		return 0;
	}
}
