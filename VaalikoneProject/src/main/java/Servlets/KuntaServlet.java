package Servlets;

import Models.DAO2;
import Models.Kunta;
import Models.DAO2;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @deprecated
* Servlet implementation class Kunta
* Was created before index functionalities were added into dao.java.
* 
*/

@WebServlet(name="KuntaServlet", value = "/kunta")
@Deprecated
public class KuntaServlet extends HttpServlet {
	
	private DAO2 dao;
	
	public void Init() {
		dao = new DAO2();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Kunta> list = null;
		if(dao.getConnection()) {
			list=dao.readKunta();
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("kuntaList", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
