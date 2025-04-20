package entity;

import java.util.Objects;

public class Ban {
	private String maBan;
	private int soGhe;
	private boolean tinhTrang; // true: đang trống, false: đang bận
	private int lau; // Thêm thuộc tính lầu

	// Constructor có lầu
	public Ban(String maBan, int soGhe, boolean tinhTrang, int lau) {
		this.maBan = maBan;
		this.soGhe = soGhe;
		this.tinhTrang = tinhTrang;
		this.lau = lau;
	}

	// Constructor cũ vẫn giữ lại nếu bạn dùng ở nơi khác
	public Ban(String maBan, int soGhe, boolean tinhTrang) {
		this(maBan, soGhe, tinhTrang, 0); // Mặc định lầu = 0
	}

	public String getMaBan() {
		return maBan;
	}

	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public int getLau() {
		return lau;
	}

	public void setLau(int lau) {
		this.lau = lau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maBan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ban other = (Ban) obj;
		return Objects.equals(maBan, other.maBan);
	}

	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", soGhe=" + soGhe + ", tinhTrang=" + tinhTrang + ", lau=" + lau + "]";
	}
}
