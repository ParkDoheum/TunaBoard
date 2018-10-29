package kr.co.hk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbpkg.MemberDAO;
import dbpkg.MemberVO;

@WebServlet("/memberMod")
public class MemberModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n--------------memberMod [GET] -------------");
		String custno = request.getParameter("custno");
		System.out.println("custno : " + custno);
		int intCustNo = Integer.parseInt(custno);
		MemberVO vo = MemberDAO.getMember(intCustNo);
		request.setAttribute("vo", vo);
		Utils.dispatcher("memberMod", request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n--------------memberMod [POST] -------------");
		
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
		
		//TODO 업데이트 실시
		int result = MemberDAO.updateMember(vo);
		
		request.setAttribute("msg", "회원정보수정이 완료 되었습니다.!");
		Utils.dispatcher("memberMod", request, response);
	}
}
