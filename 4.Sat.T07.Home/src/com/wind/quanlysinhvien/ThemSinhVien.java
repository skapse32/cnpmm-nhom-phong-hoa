package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class ThemSinhVien
 */
@WebServlet("/ThemSinhVien")
public class ThemSinhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemSinhVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String[] errors = request.getParameterValues("errors");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE>"
				+ "<html xmlns='http://www.w3.org/1999/xhtml'>"
				+ "<head>"
				+ "<style> "
				+ ".main{font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif; width:100%;border-collapse:collapse;}"
				+ "#form1 {background-color:#EAF2D3;}"
				+ "#textfield {width:380px;}"
				+ "#err {background-color:#FF9900;color:white}"
				+ "#success {text-align:center ;background-color:#A7C942;color:white}"
				+ "</style>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
				+ "<title>Thêm Sinh Viên</title>" + "</head>");

		out.println("<body>" + " <div class='main'>");
		out.println("<center><h2 style=\"color:#007A29\">Thêm Sinh Viên</h2>");
		if (errors != null && errors.length > 0) {
			out.println("<div id ='err'> ERRORS");
			for (String error : errors) {
				out.println(error + "</div></center>");
			}
		}
		out.println(" <form id='form1' name='form1' method=\"POST\" action=\"ThemSinhVien\">");
		out.println("<table width=\"700\" align=\"center\">");
		out.println("<tr><td>MSSV</td>");
		out.println("<td><input type=\"text\" name=\"mssv\" id=\"textfield\" /></td></tr>");
		out.println("<tr><td>Họ và Tên</td><td><input type=\"text\" name=\"hovaten\" id=\"textfield\" /></td></tr>");
		out.println("<tr><td>Phái</td><td><input type=\"checkbox\" value = 'true' name=\"phai\" /><label for=\"checkbox\">Nam</label></td></tr>");
		out.println("<tr><td>Nơi Sinh</td><td><input type=\"text\" name=\"noisinh\" /></td></tr>");
		out.println("<tr><td>Ngày Sinh</td><td><input type=\"date\" name=\"ngaysinh\" /></td></tr>");
		out.println("<tr><td>Sở Thích</td><td><div class=\"sothich\">"
				+ "<input type=\"checkbox\" name=\"check\" value='Bơi Lội'/>Bơi lội"
				+ "<input type=\"checkbox\" name=\"check\" value='Bóng Đá'/>Bóng đá"
				+ "<input type=\"checkbox\" name=\"check\"  value='Bóng Chuyền'/>Bóng chuyền"
				+ "<input type=\"checkbox\" name=\"check\" value='Đua Xe'/>Đua xe</div></td></tr>");
		out.println("<tr><td>Quá trình học tập</td><td><textarea name=\"quatrinhhoctap\" id=\"textarea\" cols=\"45\" rows=\"5\"></textarea></td></tr>");
		out.println("<tr><td> </td><td><input type=\"submit\" name=\"nhap\" value='Thêm Sinh Viên'/><input type='reset' value='Clear'/><input type=\"button\" onclick=\"window.location ='DanhSachSinhVien'\" value = 'Back'/></td></tr>");
		out.println("</table></form></div>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mssv = request.getParameter("mssv");
		if (mssv != null && mssv.length() == 0) {
			response.sendRedirect("ThemSinhVien?errors=MSSV INVALID");
			return;
		}
		String hovaten = request.getParameter("hovaten");
		Boolean phai = Boolean.valueOf(request.getParameter("phai"));
		String noisinh = request.getParameter("noisinh");
		Date ngaysinh = Date.valueOf(request.getParameter("ngaysinh"));
		String quatrinhhoctap = request.getParameter("quatrinhhoctap");
		/* System.out.println(Boolean.valueOf(phai) +""+ ngaysinh); */
		String[] sothich = request.getParameterValues("check");

		SinhVien SV = new SinhVien();
		SV.setMssv(mssv);
		SV.setHoTen(hovaten);
		SV.setGioiTinh(phai);
		SV.setNoiSinh(noisinh);
		SV.setNgaySinh(ngaysinh);
		SV.setMonTheThaoYeuThich(sothich);
		SV.setQuaTrinhHocTap(quatrinhhoctap);
		SinhVienInterface svi = new SinhvienDAO();
		if (svi.AddSV(SV)) {
			response.sendRedirect("DanhSachSinhVien");
		} else {
			response.sendRedirect("ThemSinhVien");
		}
	}

}
