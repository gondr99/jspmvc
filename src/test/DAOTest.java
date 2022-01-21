package test;

import java.util.List;

import dao.MemberDAO;
import vo.MemberMoneyVO;
import vo.MemberVO;

public class DAOTest {

	public static void main(String[] args) {
//		List<MemberVO> list =  MemberDAO.dao.getMemberList();
//		
//		for(MemberVO m : list)
//		{
//			System.out.println(m.getCustname());
//		}
		
		List<MemberMoneyVO> list = MemberDAO.dao.getMemberMoneyList();
		for(MemberMoneyVO vo : list)
		{
			System.out.println(vo.getCustname() + " : " + vo.getPrice());
		}
	}

}
