package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class ThongTinSinhVien
 */
@WebServlet("/ThongTinSinhVien")
public class ThongTinSinhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThongTinSinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SinhVienInterface dao = new SinhvienDAO();
		System.out.println(id);
		SinhVien s = dao.GetSinhVien(id);
		out.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>"
				+ "<style> "
				+ "#customers{font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif; width:100%;border-collapse:collapse;}"
				+ "#customers td, #customers th{ font-size:1em;border:1px solid #98bf21;padding:3px 7px 2px 7px;}"
				+ "#customers th {font-size:1.1em;text-align:left;padding-top:5px;padding-bottom:4px;background-color:#A7C942;color:#ffffff;}"
				+ "#customers tr:nth-child(2n+1) td {color:#000000;background-color:#EAF2D3;}"
				+ "</style>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Danh Sách Sinh Viên</title></head>\n"
				+ "<body>\n"
				+ "<center><h2 style=\"color:#007A29\">Danh Sách Sinh Viên</h2></center>");
		out.println("<center><table id=\"customers\">");
		out.println("<tr><th>MSSV</th><th>Họ Và Tên</th><th>Phái Nam</th><th>Nơi Sinh</th><th>Ngày Sinh</th><th>Sở Thích</th><th>Quá Trình Học Tập</th></tr>");
		out.println("<tr>");
		out.println("<td>"+ s.getMssv()+ "</td>");
		out.println("<td>" + s.getHoTen() + "</td>");
		String check = s.isGioiTinh() ? "checked" : null;
		out.println("<td><input type='checkbox' " + check + " /></td>");
		out.println("<td>" + s.getNoiSinh() + "</td>");
		out.println("<td>" + s.getNgaySinh() + "</td>");
		String sothich="";
		for (String so : s.getMonTheThaoYeuThich()) {
			sothich +=so+" ,";
		}
		out.println("<td>" + sothich + "</td>");
		out.println("<td>" + s.getQuaTrinhHocTap() + "</td>");
		out.println("</tr>");
		out.println("</table></center>");
		out.println("<input type=\"button\" onclick=\"window.location ='DanhSachSinhVien'\" value = 'Back'/>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
