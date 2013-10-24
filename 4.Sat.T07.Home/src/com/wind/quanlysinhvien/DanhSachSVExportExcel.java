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
 * Servlet implementation class TableExcel
 */
@WebServlet("/DanhSachSV")
public class DanhSachSVExportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultSet rs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DanhSachSVExportExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=danhsachsinhvien.xls");
		PrintWriter out = response.getWriter();
		SinhVienInterface dao = new SinhvienDAO();
		List<SinhVien> sv = dao.DanhSachSV();
			out.println("MSSV\tHọ Và Tên\tPhái\tNơi Sinh\tNgày Sinh\tSở Thích\tQuá Trình Học Tập");
			for (SinhVien s: sv) {
			out.println(s.getMssv()+"\t"
			+ s.getHoTen()+ "\t"
			+ s.isGioiTinh() + "\t"
			+ s.getNoiSinh()+ "\t"
			+ s.getNgaySinh() + "\t"
			+ s.getMonTheThaoYeuThich()+"\t"
			+ s.getQuaTrinhHocTap());
			}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
	}

}
