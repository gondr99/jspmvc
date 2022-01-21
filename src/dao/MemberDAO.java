package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBUtil;
import vo.MemberMoneyVO;
import vo.MemberVO;

public class MemberDAO {
	public static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	
	public List<MemberVO> getMemberList()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT custno, custname, phone, address, joindate, " 
						+ "DECODE(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city "
						+ "FROM member_tbl_02 ORDER BY custno";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MemberVO vo = new MemberVO();
				
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoinDate(rs.getDate("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return list;
	}

	public List<MemberMoneyVO> getMemberMoneyList()
	{
		List<MemberMoneyVO> list = new ArrayList<MemberMoneyVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT A.custno, A.custname, "
					+ "DECODE(A.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, " 
					+ "SUM(B.price) price FROM member_tbl_02 A, money_tbl_02 B "
					+ "WHERE A.custno = B.custno GROUP BY A.custno, A.custname, A.grade "
					+ "ORDER BY price DESC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				MemberMoneyVO vo = new MemberMoneyVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setGrade(rs.getString("grade"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return list;
	}

	//하나는 현재 회원중에 가장 큰 번호의 회원을 가져와서 +1을 해주는 maxCustno
	public int getMaxCustNo()
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int custno = 0;
		
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT MAX(custno) + 1 custno FROM member_tbl_02";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				custno = rs.getInt("custno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return custno;
	}
	//실제 회원을 가입시켜주는 registerProcess
	
	public int memberRegister(MemberVO vo)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int n = 0;
		
		try {
			con = DBUtil.getConnection();
			String sql = "INSERT INTO member_tbl_02 VALUES(?, ?, ?, ?, ?, ?, ?)"; //? 7개
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setDate(5, vo.getJoinDate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		return n;
	}

	//회원번호를 기반으로 회원정보를 가져오는 함수
	public MemberVO getMemeber(int custno)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO vo = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "SELECT * FROM member_tbl_02 WHERE custno = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoinDate(rs.getDate("joindate"));
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}
		
		return vo;
	}
	
	//실제 멤버 데이터 업데이트 함수
	public int updateMemeber(MemberVO member)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		
		try {
			con = DBUtil.getConnection();
			String sql = "UPDATE member_tbl_02 SET custname = ?, phone = ?, "
					+ "address = ?, joindate = ?, grade = ?, city = ? WHERE custno = ?";
			//물음표 7개
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getCustname());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setDate(4, member.getJoinDate());
			pstmt.setString(5, member.getGrade());
			pstmt.setString(6, member.getCity());
			pstmt.setInt(7, member.getCustno());
			
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(con);
		}		
		return n;
	}
}















