package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class CapNhatSinhVien
 */
@WebServlet("/SuaSinhVien")
public class SuaSinhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuaSinhVien() {
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
		String error = request.getParameter("error");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		SinhvienDAO dao = new SinhvienDAO();
		SinhVien sv = dao.GetSinhVien(id);
		String checked = "";
		if (sv.isGioiTinh())
			checked = "checked";
		String[] checkeds = null;

		out.println("<!DOCTYPE>"
				+ "<html xmlns='http://www.w3.org/1999/xhtml'>"
				+ "<head>"
				+ "<style> "
				+ ".main{font-family:\"Trebuchet MS\", Arial, Helvetica, sans-serif; width:100%;border-collapse:collapse;}"
				+ "#form1 {background-color:#EAF2D3;}"
				+ "#textfield {width:380px;}"
				+ "#err {background-color:#FF9900;color:white}"
				+ "#text{ width:380px;background-color:#FCFCFC;}"
				+ "#success {text-align:center ;background-color:#A7C942;color:white}"
				+ "</style>"
				+ "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
				+ "<title>Cập Nhật Sinh Viên</title>" + "</head>");

		out.println("<body>" + " <div class='main'>");
		out.println("<center><h2 style=\"color:#007A29\">Cập Nhật Sinh Viên</h2>");
		if (error != null) {
			if (error.equals("Success")) {
				out.println("<div id ='success'> Update Success");
			} else {
				out.println("<div id ='err'> Update Unsuccess");
			}
		}
		out.println(" <form id='form1' name='form1' method=\"POST\" action=\"SuaSinhVien\">");
		out.println("<table width=\"700\" align=\"center\">");
		out.println("<tr><td>MSSV</td>");
		out.println("<td><input type=\"text\" name=\"mssv\" id=\"text\" value='"
				+ sv.getMssv() + "' readOnly=\"true\" /></td></tr>");
		out.println("<tr><td>Họ và Tên</td><td><input type=\"text\" name=\"hovaten\" id=\"textfield\" value='"
				+ sv.getHoTen() + "'/></td></tr>");
		out.println("<tr><td>Phái</td><td><input type=\"checkbox\" value = 'true' "
				+ checked
				+ " name=\"phai\" /><label for=\"checkbox\">Nam</label></td></tr>");
		out.println("<tr><td>Nơi Sinh</td><td><input type=\"text\" name='noisinh' value='"
				+ sv.getNoiSinh() + "' /></td></tr>");
		out.println("<tr><td>Ngày Sinh</td><td><input type=\"date\" name=\"ngaysinh\" value='"
				+ sv.getNgaySinh() + "' /></td></tr>");
		String[] sothich = sv.getMonTheThaoYeuThich();
		int j = 0;
		String[] sothichall = { "Bơi Lội", "Bóng Đá", "Bóng Chuyền", "Đua Xe" };
		out.println("<tr><td>Sở Thích</td><td><div class=\"sothich\">");
		for (int i = 0; i < sothichall.length; i++) {
			if (sothich[j].equals(sothichall[i])) {
				out.println("<input type=\"checkbox\" name=\"check\" checked value='"
						+ sothichall[i] + "'/>" + sothichall[i]);
				if(j < sothich.length-1)//loi khi check boi loi va bong chuyen
					j++;
			} else {
				out.println("<input type=\"checkbox\" name=\"check\" value='"
						+ sothichall[i] + "'/>" + sothichall[i]);
			}

		}
		out.println("</div></td></tr>");
		out.println("<tr><td>Quá trình học tập</td><td><textarea name=\"quatrinhhoctap\" id=\"textarea\" cols=\"45\" rows=\"5\">"
				+ sv.getQuaTrinhHocTap() + "</textarea></td></tr>");
		out.println("<tr><td></td><td><input type=\"submit\" name=\"sua\" value='Sửa Sinh Viên'/></td></tr>");
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
		String hovaten = request.getParameter("hovaten");
		Boolean phai = Boolean.valueOf(request.getParameter("phai"));
		String noisinh = request.getParameter("noisinh");
		Date ngaysinh = Date.valueOf(request.getParameter("ngaysinh"));
		String quatrinhhoctap = request.getParameter("quatrinhhoctap");
		String[] sothich = request.getParameterValues("check");

		SinhVien SV = new SinhVien();
		SV.setMssv(mssv);
		SV.setHoTen(hovaten);
		SV.setGioiTinh(phai);
		SV.setNoiSinh(noisinh);
		System.out.println(hovaten);
		SV.setNgaySinh(ngaysinh);
		SV.setMonTheThaoYeuThich(sothich);
		SV.setQuaTrinhHocTap(quatrinhhoctap);
		SinhVienInterface svi = new SinhvienDAO();
		if (svi.UpdateSV(SV)) {
			response.sendRedirect("SuaSinhVien?error=Success&id="+ mssv);
			System.out.println("done");
		} else {
			response.sendRedirect("SuaSinhVien?error=Unsuccess&id="+ mssv);
			System.out.println("fail");
		}
	}

}
