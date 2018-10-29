package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	public static MemberVO getMaxCustNo() {
		System.out.println("-------------- getMaxCustNo [start] --------------");
		MemberVO vo = new MemberVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT nvl(max(custno), 100000) + 1 "
				+ " , to_char(sysdate, 'YYYYMMDD') "
				+ " FROM member_tbl_02 ";
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				int maxCustNo = rs.getInt(1);
				String toDay = rs.getString(2);
				
				System.out.println("maxCustNo : " + maxCustNo);
				System.out.println("toDay : " + toDay);
				
				vo.setCustno(maxCustNo);
				vo.setJoindate(toDay);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		System.out.println("-------------- getMaxCustNo [end] --------------\n");
		return vo;
	}
	
	public static int insertMember(MemberVO vo) {
		System.out.println("-------------- insertMember [start] --------------");		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " insert into member_tbl_02"
				+ " (custno, custname, phone, address, joindate, grade, city) "
				+ " values "
				+ " (?, ?, ?, ?, ?, ?, ?) ";
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getCustno());
			ps.setString(2, vo.getCustname());
			ps.setString(3, vo.getPhone());
			ps.setString(4, vo.getAddress());
			ps.setString(5, vo.getJoindate());
			ps.setString(6, vo.getGrade());
			ps.setString(7, vo.getCity());
			return ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
		}
		
		System.out.println("-------------- insertMember [end] --------------");
		return -1;
	}
	
	public static List<MemberVO> getMemberList() {
		System.out.println("-------------- getMemberList [start] --------------");
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select " 
				   + " custno, custname, phone, address "
				   + " , to_char(joindate, 'yyyy-mm-dd') as joindate "
				   + " , decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade "
				   + " , city "
				   + " from member_tbl_02 ";
		
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();			
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setAddress(rs.getString("address"));
				vo.setPhone(rs.getString("phone"));
				vo.setJoindate(rs.getString("joindate"));
				String grade = rs.getString("grade");
				/*
				if(grade.equals("A")) {
					grade = "VIP";
				} else if (grade.equals("B")) {
					grade = "일반";
				} else if (grade.equals("C")) {
					grade = "직원";
				}
				*/
				vo.setGrade(grade);
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		System.out.println("-------------- getMemberList [end] --------------");
		return list;
	}
	
	public static MemberVO getMember(int custno) {
		System.out.println("-------------- getMember [start] --------------");
		MemberVO vo = new MemberVO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " select "
				+ " custno, custname, phone, address "
				+ "  , to_char(joindate, 'yyyy-mm-dd') as joindate "
				+ "  , grade, city "
				+ " from member_tbl_02 "
				+ " where custno = ? ";
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, custno);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setAddress(rs.getString("address"));
				vo.setPhone(rs.getString("phone"));
				vo.setJoindate(rs.getString("joindate"));				
				vo.setGrade(rs.getString("grade"));
				vo.setCity(rs.getString("city"));
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		System.out.println("-------------- getMember [end] --------------");
		return vo;
	}
	
	//수정
	public static int updateMember(MemberVO vo) {
		System.out.println("-------------- updateMember [start] --------------");		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " update member_tbl_02 set "
				+ " custname = ? "
				+ " , phone = ?"
				+ " , address = ?"
				+ " , joindate = ?"
				+ " , grade = ?"
				+ " , city = ?"
				+ " where custno = ? ";
				
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);			
			ps.setString(1, vo.getCustname());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddress());
			ps.setString(4, vo.getJoindate());
			ps.setString(5, vo.getGrade());
			ps.setString(6, vo.getCity());
			ps.setInt(7, vo.getCustno());
			return ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, null);
			System.out.println("-------------- updateMember [end] --------------");
		}
		return -1;
	}
	
	//회원매출조회
	public static List<MemberVO> getIncomeChart() {
		System.out.println("-------------- getMember [start] --------------");
		List<MemberVO> list = new ArrayList<MemberVO>();		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT A.CUSTNO, A.CUSTNAME "
				+ " , DECODE(A.GRADE, 'A', 'VIP'"
				+ " , 'B', '일반', 'C', '직원') AS GRADE "
				+ " , SUM(B.PRICE) AS PRICE"
				+ " FROM MEMBER_TBL_02 A"
				+ " INNER JOIN MONEY_TBL_02 B"
				+ " ON A.CUSTNO = B.CUSTNO"
				+ " GROUP BY A.CUSTNO, A.CUSTNAME, A.GRADE"
				+ " ORDER BY A.GRADE ASC, PRICE DESC ";
		try {
			con = DBConn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String grade = rs.getString("grade");
				int price = rs.getInt("price");
				
				MemberVO vo = new MemberVO();
				vo.setCustno(custno);
				vo.setCustname(custname);
				vo.setGrade(grade);
				vo.setPrice(price);
				
				list.add(vo);				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(con, ps, rs);
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
