package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;


@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("PhonebookController");
		
		//?action= 뒤에 올값으로 각 주소 > 파라미터 가져오기
		String act = request.getParameter("action");
		System.out.println(act);
		
		if("list".equals(act)) {
			
			System.out.println("action = list");
			
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//test용 : System.out.println(personList.toString());
			
			//포워드전에 값을 넣어놓기!
			request.setAttribute("pList", personList); //꺼내쓸이름, 넣어줄아이
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp"); //로 보낼게!
			rd.forward(request, response);
			
		}else if("writeForm".equals(act)) {
			System.out.println("action = writeForm");
			
			//값
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
		}else if("write".equals(act)){
			System.out.println("action=write");
			
			//파라미터 3개를 꺼내온다
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 만든다
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);			
			
			//dao 메모리 올린다
			PhoneDao phoneDao = new PhoneDao();
						
			//dao.insert(vo);
			phoneDao.personInsert(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("delete".equals(act)) {
			System.out.println("action = delete");
			
			//phonedao선언
			PhoneDao phoneDao = new PhoneDao();

			//파라미터받고
			String str = request.getParameter ("id");
			int id = Integer.parseInt(str);
			
			//삭제하고
			phoneDao.personDelete(id);
			
			//리다이렉션
			response.sendRedirect("/phonebook2/pbc?action=list");
			
		}else if("updateForm".equals(act)) {
			System.out.println("action = UpdateForm");
			
			//값
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/updateForm.jsp");
			rd.forward(request, response);
		}else if("update".equals(act)) {
			
			//phonedao쓸거고
			PhoneDao phoneDao = new PhoneDao();
			
			//파라미터 받고
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			String str = request.getParameter ("id");
			int id = Integer.parseInt(str);
			
			//personVo도 쓸거임
			PersonVo upvo = new PersonVo(id, name, hp, company);
			System.out.println(upvo);
			
			//수정
			phoneDao.personUpdate(upvo);
					
			//리다이렉션
			response.sendRedirect("/phonebook2/pbc?action=list");
		}else {
			System.out.println("파라미터 값 없음");
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
