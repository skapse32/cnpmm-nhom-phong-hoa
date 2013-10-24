package com.wind.connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.log.LogFactory;
import com.wind.modal.SinhVien;
import com.wind.modal.SinhVienInterface;

public class SinhvienDAO implements SinhVienInterface {
	private ResultSet rs = null;
	private Connection conn;
	private Statement stmt;
	List<SinhVien> DSSV = new ArrayList<SinhVien>();
	public SinhvienDAO() {
		try {
			conn = Connection_JDBC.getConnection();
			stmt = (Statement) conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM studenttb");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<SinhVien> DanhSachSV() {
		try {

			while (rs.next()) {
				SinhVien SV = new SinhVien();
				SV.setMssv(rs.getString("MSSV"));
				SV.setHoTen(rs.getNString("HoVaTen"));
				SV.setGioiTinh(rs.getBoolean("Phai"));
				SV.setNoiSinh(rs.getNString("NoiSinh"));
				SV.setNgaySinh(rs.getDate("NgaySinh"));
				String[] sothich = rs.getNString("Sothich").split(",");
				SV.setMonTheThaoYeuThich(sothich);
				SV.setQuaTrinhHocTap(rs.getNString("QuaTrinhHocTap"));
				DSSV.add(SV);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return DSSV;
	}

	@Override
	public boolean AddSV(SinhVien SV) {
		try {
			rs.moveToInsertRow();
			rs.updateString("MSSV", SV.getMssv());
			rs.updateNString("HoVaTen", SV.getHoTen());
			rs.updateBoolean("Phai", SV.isGioiTinh());
			rs.updateNString("NoiSinh", SV.getNoiSinh());
			rs.updateDate("NgaySinh", (Date) SV.getNgaySinh());
			String sothich = "";
			for (String s : SV.getMonTheThaoYeuThich()) {
				sothich += s + ",";

			}
			rs.updateString("SoThich", sothich);
			rs.updateNString("QuaTrinhHocTap", SV.getQuaTrinhHocTap());
			rs.insertRow();
			rs.moveToCurrentRow();
			Connection_JDBC.closeConnection(conn);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public SinhVien GetSinhVien(String id) {
		try {
			PreparedStatement prs = conn
					.prepareStatement("SELECT * FROM studenttb WHERE MSSV='"
							+ id + "'");
			ResultSet rs2 = prs.executeQuery();
			SinhVien SV = new SinhVien();
			while (rs2.next()) {
				SV.setMssv(rs2.getString("MSSV"));
				SV.setHoTen(rs2.getNString("HoVaTen"));
				SV.setGioiTinh(rs2.getBoolean("Phai"));
				SV.setNoiSinh(rs2.getNString("NoiSinh"));
				SV.setNgaySinh(rs2.getDate("NgaySinh"));
				String[] sothich = rs2.getNString("Sothich").split(",");

				SV.setMonTheThaoYeuThich(sothich);
				SV.setQuaTrinhHocTap(rs2.getNString("QuaTrinhHocTap"));
			}
			return SV;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean DeleSV(String id) {

		try {
			return stmt
					.execute("DELETE FROM studenttb WHERE MSSV='" + id + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean UpdateSV(SinhVien sv) {
		try {
			PreparedStatement prs = conn
					.prepareStatement("UPDATE studenttb SET HoVaTen = ? ,Phai = ? ,NoiSinh =? ,NgaySinh =? , SoThich =?,QuaTrinhHocTap=? WHERE MSSV =?");
			prs.setNString(1, sv.getHoTen());
			prs.setBoolean(2, sv.isGioiTinh());
			prs.setNString(3, sv.getNoiSinh());
			prs.setDate(4, sv.getNgaySinh());
			String sothich = "";
			for (String s : sv.getMonTheThaoYeuThich()) {
				sothich += s + ",";
			}
			prs.setNString(5, sothich);
			prs.setNString(6, sv.getQuaTrinhHocTap());
			prs.setString(7, sv.getMssv());
			System.out.println(sv);
			prs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
