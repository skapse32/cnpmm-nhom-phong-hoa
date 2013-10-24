package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class XoaSinhVien
 */
@WebServlet("/XoaSinhVien")
public class XoaSinhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XoaSinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE>"
				+ "<html xmlns='http://www.w3.org/1999/xhtml'>"
				+ "<head>"
				+ "<style> "
				+ ".main{font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif; width:100%;border-collapse:collapse;}"
				+ "#bg {background-color:#EAF2D3;}"
				+ "#success {text-align:center ;background-color:#A7C942;color:white}"
				+ "#err {background-color:#FF9900;color:white;text-align:center}"
				+ "</style>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
				+ "<title>Thông Báo</title>" + "</head>");

		out.println("<body>" + " <div class='main'>");
		out.println("<center><h2 style=\"color:#007A29\">Xóa Sinh Viên</h2>");

		if (request.getParameterValues("cbx_xoa") == null) {
			out.println("<div id='err'> ERROR: Please check student's row that you need delete</div>");
		} else {
			String[] Ids = request.getParameterValues("cbx_xoa");
			SinhVienInterface dao = new SinhvienDAO();

			for (String id : Ids) {

				if (!dao.DeleSV(id)) {
					out.println("<div id='success'>["+ id+ "]Delete succes.</div>");
				} else {
					out.println("<div id='err'>"+ id+ "  Delete fail.</div>");
				}
			}
		}
		out.println("<input type=\"button\" onclick=\"window.location ='DanhSachSinhVien'\" value = 'Back'/>");
		out.println("</center></div>");
		out.println("</body></html>");
	}

}
