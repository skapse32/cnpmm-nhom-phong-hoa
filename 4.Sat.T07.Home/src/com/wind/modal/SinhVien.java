package com.wind.modal;

import java.util.Arrays;
import java.sql.Date;

public class SinhVien {
	private String mssv;
	private String hoTen;
	private boolean gioiTinh;
	private String noiSinh;
	private Date ngaySinh;
	private String[] monTheThaoYeuThich;
	private String quaTrinhHocTap;
	
	public String getMssv() {
		return mssv;
	}
	public void setMssv(String mssv) {
		this.mssv = mssv;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNoiSinh() {
		return noiSinh;
	}
	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String[] getMonTheThaoYeuThich() {
		return monTheThaoYeuThich;
	}
	public void setMonTheThaoYeuThich(String[] monTheThaoYeuThich) {
		this.monTheThaoYeuThich = monTheThaoYeuThich;
	}
	public String getQuaTrinhHocTap() {
		return quaTrinhHocTap;
	}
	public void setQuaTrinhHocTap(String quaTrinhHocTap) {
		this.quaTrinhHocTap = quaTrinhHocTap;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (gioiTinh ? 1231 : 1237);
		result = prime * result + ((hoTen == null) ? 0 : hoTen.hashCode());
		result = prime * result + Arrays.hashCode(monTheThaoYeuThich);
		result = prime * result + ((mssv == null) ? 0 : mssv.hashCode());
		result = prime * result
				+ ((ngaySinh == null) ? 0 : ngaySinh.hashCode());
		result = prime * result + ((noiSinh == null) ? 0 : noiSinh.hashCode());
		result = prime * result
				+ ((quaTrinhHocTap == null) ? 0 : quaTrinhHocTap.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		if (gioiTinh != other.gioiTinh)
			return false;
		if (hoTen == null) {
			if (other.hoTen != null)
				return false;
		} else if (!hoTen.equals(other.hoTen))
			return false;
		if (!Arrays.equals(monTheThaoYeuThich, other.monTheThaoYeuThich))
			return false;
		if (mssv == null) {
			if (other.mssv != null)
				return false;
		} else if (!mssv.equals(other.mssv))
			return false;
		if (ngaySinh == null) {
			if (other.ngaySinh != null)
				return false;
		} else if (!ngaySinh.equals(other.ngaySinh))
			return false;
		if (noiSinh == null) {
			if (other.noiSinh != null)
				return false;
		} else if (!noiSinh.equals(other.noiSinh))
			return false;
		if (quaTrinhHocTap == null) {
			if (other.quaTrinhHocTap != null)
				return false;
		} else if (!quaTrinhHocTap.equals(other.quaTrinhHocTap))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SinhVien [mssv=" + mssv + ", hoTen=" + hoTen + ", gioiTinh="
				+ gioiTinh + ", noiSinh=" + noiSinh + ", ngaySinh=" + ngaySinh
				+ ", monTheThaoYeuThich=" + Arrays.toString(monTheThaoYeuThich)
				+ ", quaTrinhHocTap=" + quaTrinhHocTap + "]";
	}
	public SinhVien(String mssv, String hoTen, boolean gioiTinh,
			String noiSinh, Date ngaySinh, String[] monTheThaoYeuThich,
			String quaTrinhHocTap) {
		super();
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.noiSinh = noiSinh;
		this.ngaySinh = ngaySinh;
		this.monTheThaoYeuThich = monTheThaoYeuThich;
		this.quaTrinhHocTap = quaTrinhHocTap;
	}
	
	public SinhVien(){ }	
}
