package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.EmployeeDAO;
import dto.EmployeeDTO;

/**
 * Servlet implementation class ProductInputServlet
 */
@WebServlet("/search.do")
public class EmployeeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String kind = request.getParameter("kind");
			String search = request.getParameter("search");
			
			if(search == null || search.length() == 0)
				throw new Exception();
			
			ArrayList<EmployeeDTO> list = null;
			list = EmployeeDAO.getInstance().selectEmployee(kind, search);
			if(list.size()==0)
				throw new SQLException();
			JSONArray array = new JSONArray(list);
			response.getWriter().write(array.toString());
			System.out.println(array.toString());
		}catch (NumberFormatException e) {
			response.setStatus(1001);
			response.getWriter().write("직급은 숫자로 입력하세요.");
		}catch(SQLException e) {
			response.setStatus(1002);
			response.getWriter().write("조회 결과가 없습니다.");
		}catch (Exception e) {
			response.setStatus(1000);
			response.getWriter().write("검색어를 입력하세요.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
