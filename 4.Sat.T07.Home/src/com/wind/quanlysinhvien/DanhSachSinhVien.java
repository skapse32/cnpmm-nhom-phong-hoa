package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.Connection_JDBC;
import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class DanhSachSinhVien
 */
@WebServlet("/DanhSachSinhVien")
public class DanhSachSinhVien extends HttpServlet {
	private ResultSet rs;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhSachSinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SinhVienInterface dao = new SinhvienDAO();
		List<SinhVien> sv = dao.DanhSachSV();
		out.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>"
				+ "<style> "
				+ "#customers{font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif; width:600px;border-collapse:collapse;}"
				+ "#customers td, #customers th{ font-size:1em;border:1px solid #98bf21;padding:3px 7px 2px 7px;}"
				+ "#customers th {font-size:1.1em;text-align:left;padding-top:5px;padding-bottom:4px;background-color:#A7C942;color:#ffffff;}"
				+ "#customers tr:nth-child(2n+1) td {color:#000000;background-color:#EAF2D3;}"
				+ "#main {background-color:#EAF2D3;}"
				+ "</style>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Danh Sách Sinh Viên</title></head>\n"
				+ "<body>\n"
				+ "<center><h2 style=\"color:#007A29\">Danh Sách Sinh Viên</h2></center>");
		out.println("<center><form name='form1' method=\"POST\" action=\"XoaSinhVien\"><div id='main' ><table id=\"customers\">");
		out.println("<tr><th>MSSV</th><th>Họ Và Tên</th><th>Sửa</th><th>Xóa</th></tr>");
		for (SinhVien s : sv) {
			out.println("<tr>");
			out.println("<td><a href='ThongTinSinhVien?id=" + s.getMssv()
					+ "'>" + s.getMssv() + "</a></td>");
			out.println("<td>" + s.getHoTen() + "</td>");
			out.println("<td><a href='SuaSinhVien?id=" + s.getMssv()
					+ "'>Edit</a></td>");
			out.println("<td><input type='checkbox' name=\"cbx_xoa\" value=\""+s.getMssv()+"\"/></td>");
			out.println("</tr>");
		}
		out.println("</table></div>");
		out.println("<a href='ThemSinhVien'>Thêm Sinh Viên</a>|<a href='DanhSachSV'>Save file Excel</a><input type='submit' name='xoa' value='Delete'/>");
		out.println("</center></form></body></html>");

	}

}
