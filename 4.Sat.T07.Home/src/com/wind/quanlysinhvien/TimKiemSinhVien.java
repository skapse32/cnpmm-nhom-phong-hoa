package com.wind.quanlysinhvien;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wind.connection.SinhvienDAO;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

/**
 * Servlet implementation class TimKiemSinhVien
 */
@WebServlet("/TimKiemSinhVien")
public class TimKiemSinhVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimKiemSinhVien() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		SinhVienInterface svi = new SinhvienDAO();
		String mssv = request.getParameter("mssv");
		String hoTen = request.getParameter("hoten");
		Date start = Date.valueOf(request.getParameter("startDate"));
		Date end = Date.valueOf(request.getParameter("endDate"));
		List<SinhVien> svs = svi.findSinhVien(mssv, hoTen,start,end);
		String json = new Gson().toJson(svs);
		response.getWriter().write(json);
	}

}
