package servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.Connect;
import lab5.entity.*;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1/*")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LabCRUDInterface<User> crud = new SqlCRUD();
	
		

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub	
		
		crud = new SqlCRUD();
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			((SqlCRUD) crud).getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		setAccessControlHeaders(response);
		response.setContentType("application/json");
//		System.out.println(((SqlCRUD) crud).getConnection());
		response.getWriter().println(crud.read());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		User user = Helpers.userParse(request);
		crud.create(user);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		User user = Helpers.userParse(request);
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		response.setContentType("application/json");
		crud.update(id, user);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		
		response.setContentType("application/json");
		crud.delete(id);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	
	 private void setAccessControlHeaders(HttpServletResponse resp) {
		  resp.setHeader("Access-Control-Allow-Origin", "*");
	      resp.setHeader("Access-Control-Allow-Methods", "*");
	      resp.setHeader("Access-Control-Allow-Headers", "*");
	  }

}