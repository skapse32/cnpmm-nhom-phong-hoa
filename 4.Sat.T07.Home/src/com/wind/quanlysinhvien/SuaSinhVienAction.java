package com.wind.quanlysinhvien;

import java.io.IOException;
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
 * Servlet implementation class SuaSinhVienAction
 */
@WebServlet("/SuaSinhVienAction")
public class SuaSinhVienAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuaSinhVienAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
