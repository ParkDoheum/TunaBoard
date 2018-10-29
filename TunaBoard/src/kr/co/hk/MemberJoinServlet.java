package kr.co.hk;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;

@WebServlet("/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO vo = MemberDAO.getMaxCustNo();
		request.setAttribute("vo", vo);
				
		Utils.dispatcher("memberJoin", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------------- memberJoin [POST] -------------------");
		request.setCharacterEncoding("UTF-8");
		String custno = request.getParameter("custno");
		String custname = request.getParameter("custname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city");
				
		System.out.println("custno : " + custno);
		System.out.println("custname : " + custname);
		System.out.println("phone : " + phone);
		System.out.println("address : " + address);
		System.out.println("joindate : " + joindate);
		System.out.println("grade : " + grade);
		System.out.println("city : " + city);
		
		int intCustno = Integer.parseInt(custno);		
		
		MemberVO vo = new MemberVO();
		vo.setCustno(intCustno);
		vo.setCustname(custname);
		vo.setPhone(phone);
		vo.setAddress(address);
		vo.setJoindate(joindate);
		vo.setGrade(grade);
		vo.setCity(city);
		
		int result = MemberDAO.insertMember(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("msg", "회원 등록이 완료되었습니다.");
		
		Utils.dispatcher("memberJoin", request, response);
	}
}



