package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNhanVien;
    private TaiKhoan taiKhoan;
    private String hoTen;
    private boolean gioiTinh;
    private String sdt;
    private LocalDate ngaySinh;
    private boolean chucVu; // true: quản lý, false: nhân viên thường
    private LocalDate ngayThem;
    private String diaChi;
    
    private String email;
    private boolean trangThai;
    private String hinhAnh;

    public NhanVien(String maNhanVien, TaiKhoan taiKhoan, String hoTen, boolean gioiTinh, String sdt, LocalDate ngaySinh,
            boolean chucVu, LocalDate ngayThem, String diaChi, String email, boolean trangThai,
            String hinhAnh) {
        this.maNhanVien = maNhanVien;
        this.taiKhoan = taiKhoan;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.chucVu = chucVu;
        this.ngayThem = ngayThem;
        this.diaChi = diaChi;
      
        this.email = email;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
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

	public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public LocalDate getNgayThem() {
        return ngayThem;
    }

    public void setNgayThem(LocalDate ngayThem) {
        this.ngayThem = ngayThem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "NhanVien [maNhanVien=" + maNhanVien + ", taiKhoan=" + taiKhoan + ", hoTen=" + hoTen
                + ", gioiTinh=" + gioiTinh + ", sdt=" + sdt + ", ngaySinh=" + getNgaySinh() + ", chucVu=" + chucVu
                + ", ngayThem=" + getNgayThem() + ", diaChi=" + diaChi +", email=" + email
                + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + "]";
    }

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
	public Object getObjectTable() {
        // Trả về một đối tượng đại diện cho thông tin của Nhân viên
        Object[] objectArray = {
            maNhanVien,
            hoTen,
            gioiTinh,
            sdt,
            ngaySinh,
            chucVu,
            ngayThem,
            
            
            email,
            diaChi
        
        };
        return objectArray;
    }
	
}
