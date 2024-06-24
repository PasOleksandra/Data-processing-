package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import users.Mock;
import users.User;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Servlet1/*")
public class Servlet1 extends HttpServlet {
	private FileIO fio = new FileIO();
	private static final long serialVersionUID = 1L;
    private List<User> lu = new Mock().getUserList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		response.setContentType("application/json");
		response.getWriter().println(lu);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
        User user = Helpers.userParse(request);
        user.setId(Helpers.getNextId(lu));
        lu.add(user);
        fio.saveToFile(lu);
        doGet(request,response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        User user = Helpers.userParse(request);
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        System.out.println(id);
        response.setContentType("application/json");
        int index = Helpers.getIndexByUserId(id, lu);
        lu.set(index,user);
        fio.saveToFile(lu);
        doGet(request,response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
	    int id = Integer.parseInt(request.getPathInfo().substring(1));
	    System.out.println(id);
	    response.setContentType("application/json");
	    int index = Helpers.getIndexByUserId(id,lu);
	    lu.remove(index);
	    fio.saveToFile(lu);
	    doGet(request,response);
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	 private void setAccessControlHeaders(HttpServletResponse resp){
	        resp.setHeader("Access-Control-Allow-Origin","*");
	        resp.setHeader("Access-Control-Allow-Methods","*");
	        resp.setHeader("Access-Control-Allow-Headers","*");
	    }
}
