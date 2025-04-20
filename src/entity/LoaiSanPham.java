package entity;

import java.util.Objects;

public class LoaiSanPham {
	private String maLoai;
	private String tenloai;
	private String moTa;
	private boolean coPhanLoai;
	
	public LoaiSanPham(String maLoai, String tenloai, String moTa,boolean coPhanLoai) {
		this.maLoai = maLoai;
		this.tenloai = tenloai;
		this.moTa = moTa;
		this.coPhanLoai=coPhanLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public boolean isCoPhanLoai() {
		return coPhanLoai;
	}

	public void setCoPhanLoai(boolean coPhanLoai) {
		this.coPhanLoai = coPhanLoai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiSanPham other = (LoaiSanPham) obj;
		return Objects.equals(maLoai, other.maLoai);
	}

	@Override
	public String toString() {
		return "LoaiSanPham [maLoai=" + maLoai + ", tenloai=" + tenloai + ", moTa=" + moTa + "]";
	}
	
	
}
