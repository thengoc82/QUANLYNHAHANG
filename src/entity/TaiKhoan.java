package entity;

public class TaiKhoan {
	private String maTaiKhoan;
	private String tenTaiKhoan;
	private String matKhau;
	private boolean loaiTaiKhoan; /*true: Quyền quản lý, quản lý nhân viên; false: chỉ thực hiện quyền nhân viên*/
	
	public TaiKhoan(String maTaiKhoan, String matKhau, boolean loaiTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
		this.matKhau = matKhau;
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public TaiKhoan(String maTaiKhoan, String tenTaiKhoan, String matKhau, boolean loaiTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}

	public void setLoaiTaiKhoan(boolean loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTaiKhoan=" + maTaiKhoan + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau
				+ ", loaiTaiKhoan=" + loaiTaiKhoan + "]";
	}
	
	
}
