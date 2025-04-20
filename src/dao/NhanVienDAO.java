package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import connectDB.connectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDAO  implements InterfaceDAO<NhanVien>{
	
	public static NhanVienDAO getInstance() {
		return new NhanVienDAO();
	}

	@Override
	// Thêm một đối tượng Nhân viên vào cơ sở dữ liệu
    public int insert(NhanVien nhanVien) {
        int kq = 0;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "INSERT INTO NhanVien (MaNhanVien, MaTaiKhoan, HoTen, GioiTinh, Sdt, NgaySinh, ChucVu, NgayTuyenDung, DiaChi, Email, TrangThai, HinhAnh) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nhanVien.getMaNhanVien());
            pst.setString(2, nhanVien.getTaiKhoan().getMaTaiKhoan());
            pst.setString(3, nhanVien.getHoTen());
            pst.setBoolean(4, nhanVien.isGioiTinh());
            pst.setString(5, nhanVien.getSdt());
            pst.setObject(6, nhanVien.getNgaySinh());
            pst.setBoolean(7, nhanVien.isChucVu());
            pst.setObject(8, nhanVien.getNgayThem());
            pst.setString(9, nhanVien.getDiaChi());

            pst.setString(10, nhanVien.getEmail());
            pst.setBoolean(11, nhanVien.isTrangThai());
            pst.setString(12, nhanVien.getHinhAnh());

            kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

	@Override
	// Cập nhật thông tin của một đối tượng Nhân viên trong cơ sở dữ liệu
    public int update(NhanVien nhanVien) {
        int kq = 0;
        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "UPDATE NhanVien SET MaTaiKhoan = ?, HoTen = ?, GioiTinh = ?, Sdt = ?, NgaySinh = ?, ChucVu = ?, NgayTuyenDung = ?, DiaChi = ?, Email = ?, TrangThai = ?, HinhAnh = ? WHERE MaNhanVien = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nhanVien.getTaiKhoan().getMaTaiKhoan());
            pst.setString(2, nhanVien.getHoTen());
            pst.setBoolean(3, nhanVien.isGioiTinh());
            pst.setString(4, nhanVien.getSdt());
            pst.setObject(5, nhanVien.getNgaySinh());
            pst.setBoolean(6, nhanVien.isChucVu());
            pst.setObject(7, nhanVien.getNgayThem());
            pst.setString(8, nhanVien.getDiaChi());

            pst.setString(9, nhanVien.getEmail());
            pst.setBoolean(10, nhanVien.isTrangThai());
            pst.setString(11, nhanVien.getHinhAnh());
            pst.setString(12, nhanVien.getMaNhanVien());

            kq = pst.executeUpdate();
            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

	
	@Override
	public ArrayList<NhanVien> selectAll() {
	    ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM NhanVien";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maTaiKhoan = rs.getString("MaTaiKhoan");
	            String hoTen = rs.getString("HoTen");
	            Boolean gioiTinh = rs.getBoolean("GioiTinh");
	            String sdt = rs.getString("Sdt");
	            // Lấy ngày sinh dưới dạng java.sql.Date
	            java.sql.Date ngaySinhSQL = rs.getDate("NgaySinh");
	            // Chuyển đổi java.sql.Date sang LocalDate
	            LocalDate ngaySinh = ngaySinhSQL.toLocalDate();
	            boolean chucVu = rs.getBoolean("ChucVu");
	            // Tương tự, lấy và chuyển đổi ngày thêm
	            java.sql.Date ngayThemSQL = rs.getDate("NgayTuyenDung");
	            LocalDate ngayThem = ngayThemSQL.toLocalDate();
	            String diaChi = rs.getString("DiaChi");
	           ;
	            String email = rs.getString("Email");
	            Boolean trangThai = rs.getBoolean("TrangThai");
	            String hinhAnh = rs.getString("HinhAnh");

	            // Lấy thông tin tài khoản từ cơ sở dữ liệu
	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

	            // Tạo đối tượng Nhân viên và thêm vào danh sách
	            NhanVien nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayThem, diaChi, email, trangThai, hinhAnh);
	            danhSachNhanVien.add(nhanVien);
	        }
	        System.out.println(danhSachNhanVien);
	        connectDB.closeConnection(conn);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachNhanVien;
	}
	public ArrayList<NhanVien> selectAllQuanLy() {
	    ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM NhanVien WHERE ChucVu = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setBoolean(1, true); 
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maTaiKhoan = rs.getString("MaTaiKhoan");
	            String hoTen = rs.getString("HoTen");
	            Boolean gioiTinh = rs.getBoolean("GioiTinh");
	            String sdt = rs.getString("Sdt");
	            // Lấy ngày sinh dưới dạng java.sql.Date
	            java.sql.Date ngaySinhSQL = rs.getDate("NgaySinh");
	            // Chuyển đổi java.sql.Date sang LocalDate
	            LocalDate ngaySinh = ngaySinhSQL.toLocalDate();
	            boolean chucVu = rs.getBoolean("ChucVu");
	            // Tương tự, lấy và chuyển đổi ngày thêm
	            java.sql.Date ngayThemSQL = rs.getDate("NgayTuyenDung");
	            LocalDate ngayThem = ngayThemSQL.toLocalDate();
	            String diaChi = rs.getString("DiaChi");
	          
	            String email = rs.getString("Email");
	            Boolean trangThai = rs.getBoolean("TrangThai");
	            String hinhAnh = rs.getString("HinhAnh");

	            // Lấy thông tin tài khoản từ cơ sở dữ liệu
	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

	            // Tạo đối tượng Nhân viên và thêm vào danh sách
	            NhanVien nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayThem, diaChi, email, trangThai, hinhAnh);
	            danhSachNhanVien.add(nhanVien);
	        }
	        System.out.println(danhSachNhanVien);
	        connectDB.closeConnection(conn);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachNhanVien;
	}
	
	public ArrayList<NhanVien> selectAllNhanVien() {
	    ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM NhanVien WHERE ChucVu = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setBoolean(1, false); 
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maTaiKhoan = rs.getString("MaTaiKhoan");
	            String hoTen = rs.getString("HoTen");
	            Boolean gioiTinh = rs.getBoolean("GioiTinh");
	            String sdt = rs.getString("Sdt");
	            // Lấy ngày sinh dưới dạng java.sql.Date
	            java.sql.Date ngaySinhSQL = rs.getDate("NgaySinh");
	            // Chuyển đổi java.sql.Date sang LocalDate
	            LocalDate ngaySinh = ngaySinhSQL.toLocalDate();
	            boolean chucVu = rs.getBoolean("ChucVu");
	            // Tương tự, lấy và chuyển đổi ngày thêm
	            java.sql.Date ngayThemSQL = rs.getDate("NgayTuyenDung");
	            LocalDate ngayThem = ngayThemSQL.toLocalDate();
	            String diaChi = rs.getString("DiaChi");
	         
	            String email = rs.getString("Email");
	            Boolean trangThai = rs.getBoolean("TrangThai");
	            String hinhAnh = rs.getString("HinhAnh");

	            // Lấy thông tin tài khoản từ cơ sở dữ liệu
	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

	            // Tạo đối tượng Nhân viên và thêm vào danh sách
	            NhanVien nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayThem, diaChi, email, trangThai, hinhAnh);
	            danhSachNhanVien.add(nhanVien);
	        }
	        System.out.println(danhSachNhanVien);
	        connectDB.closeConnection(conn);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSachNhanVien;
	}

	@Override
	
	public NhanVien selectById(String id) {
	    NhanVien nhanVien = null;
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, id);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maTaiKhoan = rs.getString("MaTaiKhoan");
	            String hoTen = rs.getString("HoTen");
	            Boolean gioiTinh = rs.getBoolean("GioiTinh");
	            String sdt = rs.getString("Sdt");
	            java.sql.Date ngaySinhSQL = rs.getDate("NgaySinh");
	            LocalDate ngaySinh = ngaySinhSQL.toLocalDate();
	            boolean chucVu = rs.getBoolean("ChucVu");
	            java.sql.Date ngayThemSQL = rs.getDate("NgayTuyenDung");
	            LocalDate ngayThem = ngayThemSQL.toLocalDate();
	            String diaChi = rs.getString("DiaChi");
	            
	            String email = rs.getString("Email");
	            Boolean trangThai = rs.getBoolean("TrangThai");
	            String hinhAnh = rs.getString("HinhAnh");

	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

	            nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayThem, diaChi, email, trangThai, hinhAnh);
	        }
	        connectDB.closeConnection(conn);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nhanVien;
	}
	public NhanVien selectByTK(String maTK) {
	    NhanVien nhanVien = null;
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "SELECT * FROM NhanVien WHERE MaTaiKhoan = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, maTK);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maTaiKhoan = rs.getString("MaTaiKhoan");
	            String hoTen = rs.getString("HoTen");
	            Boolean gioiTinh = rs.getBoolean("GioiTinh");
	            String sdt = rs.getString("Sdt");
	            // Lấy ngày sinh dưới dạng java.sql.Date
	            java.sql.Date ngaySinhSQL = rs.getDate("NgaySinh");
	            // Chuyển đổi java.sql.Date sang LocalDate
	            LocalDate ngaySinh = ngaySinhSQL.toLocalDate();
	            boolean chucVu = rs.getBoolean("ChucVu");
	            // Tương tự, lấy và chuyển đổi ngày thêm
	            java.sql.Date ngayThemSQL = rs.getDate("NgayTuyenDung");
	            LocalDate ngayThem = ngayThemSQL.toLocalDate();
	            String diaChi = rs.getString("DiaChi");
	            
	            String email = rs.getString("Email");
	            Boolean trangThai = rs.getBoolean("TrangThai");
	            String hinhAnh = rs.getString("HinhAnh");

	            // Lấy thông tin tài khoản từ cơ sở dữ liệu
	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

	            // Tạo đối tượng Nhân viên
	            nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayThem, diaChi, email, trangThai, hinhAnh);
	        }
	        connectDB.closeConnection(conn);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nhanVien;
	}
	
	//xóa nv
	public int delete(String maNhanVien) {
	    int kq = 0;
	    try {
	        Connection conn = connectDB.getConnectDB();
	        String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, maNhanVien);

	        kq = pst.executeUpdate();

	        // Gọi phương thức xóa tài khoản tương ứng
	        if (kq > 0) {
	            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	            NhanVien nhanVien = selectById(maNhanVien); // Lấy thông tin nhân viên để xóa tài khoản
	            if (nhanVien != null) {
	                kq = taiKhoanDAO.delete(nhanVien.getTaiKhoan().getMaTaiKhoan());
	            }
	        }

	        connectDB.closeConnection(conn);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return kq;
	}
	
	public String getMaxMaNhanVien() {
        String maxMaNhanVien = ""; // Mã nhân viên lớn nhất

        try {
            Connection conn = connectDB.getConnectDB();
            String sql = "SELECT MAX(MaNhanVien) AS maxMa FROM NhanVien";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                maxMaNhanVien = rs.getString("maxMa"); 
            }

            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maxMaNhanVien;
    }
	public String getNextMaNhanVien() {
        String maxMaNhanVien = getMaxMaNhanVien(); 

        if (maxMaNhanVien.isEmpty()) {
            return "KH001";
        } else {
            int nextNumber = Integer.parseInt(maxMaNhanVien.substring(2)) + 1; 
            String nextMaNhanVien = "NV" + String.format("%03d", nextNumber); 
            return nextMaNhanVien;
        }
    }
	public NhanVien findNhanVien(String maTaiKhoan, String hoTen, String sdt) {
        NhanVien nhanVien = null;

        try {
            Connection conn = connectDB.getConnectDB(); 
            String sql = "SELECT * FROM NhanVien WHERE MaTaiKhoan = ? AND HoTen = ? AND Sdt = ?";
            
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, maTaiKhoan);
            pst.setString(2, hoTen);
            pst.setString(3, sdt);
            
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maNhanVien = rs.getString("MaNhanVien");
                Boolean gioiTinh = rs.getBoolean("GioiTinh");
                
                LocalDate ngaySinh = null;
                if (rs.getDate("NgaySinh") != null) {
                    ngaySinh = rs.getDate("NgaySinh").toLocalDate();
                }

                boolean chucVu = rs.getBoolean("ChucVu");

                LocalDate ngayTuyenDung = null;
                if (rs.getDate("NgayTuyenDung") != null) {
                    ngayTuyenDung = rs.getDate("NgayTuyenDung").toLocalDate();
                }

                String diaChi = rs.getString("DiaChi");
                String email = rs.getString("Email");
                Boolean trangThai = rs.getBoolean("TrangThai");
                String hinhAnh = rs.getString("HinhAnh");

                TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
                TaiKhoan taiKhoan = taiKhoanDAO.selectById(maTaiKhoan);

                nhanVien = new NhanVien(maNhanVien, taiKhoan, hoTen, gioiTinh, sdt, ngaySinh, chucVu, ngayTuyenDung, diaChi, email, trangThai, hinhAnh);
            }

            connectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhanVien;
    }
}
