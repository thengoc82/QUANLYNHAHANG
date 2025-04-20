package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.CaLam;
import entity.NhanVien;
import entity.ThongTinCa;

public class ThongTinCaDAO implements InterfaceDAO<ThongTinCa> {
	
	public static ThongTinCaDAO getInstance() {
		return new ThongTinCaDAO();
	}
    @Override
    public int insert(ThongTinCa thongTinCa) {
        int rowsInserted = 0;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "INSERT INTO ThongTinCa ( MaCa, MaNhanVien, NgayLam, TongDoanhThu) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, thongTinCa.getCalam().getMaCa());
            preparedStatement.setString(2, thongTinCa.getNhanVien().getMaNhanVien());
            preparedStatement.setObject(3, thongTinCa.getNgayLam());
            preparedStatement.setDouble(4, thongTinCa.getDoanhThuCa());
            rowsInserted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

//    @Override
//    public int update(ThongTinCa thongTinCa) {
//        int rowsUpdated = 0;
//        try (Connection connection = connectDB.getConnectDB()) {
//            String sql = "UPDATE ThongTinCa SET DoanhThuCa=? WHERE MaNhanVien=? AND MaCa=? AND NgayLam=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDouble(1, thongTinCa.getDoanhThuCa());
//            preparedStatement.setString(2, thongTinCa.getCalam().getMaCa());
//            preparedStatement.setString(3, thongTinCa.getNhanVien().getMaNhanVien());
//            preparedStatement.setObject(4, thongTinCa.getNgayLam());
//            rowsUpdated = preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rowsUpdated;
//    }

    @Override
    public ArrayList<ThongTinCa> selectAll() {
        ArrayList<ThongTinCa> danhSachThongTinCa = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM ThongTinCa";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("MaNhanVien");
                String maCa = resultSet.getString("MaCa");
                LocalDateTime ngayLam = resultSet.getTimestamp("NgayLam").toLocalDateTime();

                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                CaLamDAO caLamDAO = new CaLamDAO();
                CaLam caLam = caLamDAO.selectById(maCa);

                // Khởi tạo đối tượng ThongTinCa và thêm vào danh sách
                ThongTinCa thongTinCa = new ThongTinCa(nhanVien, caLam, ngayLam);
                thongTinCa.getDoanhThuCa();
               // thongTinCa.setDoanhThuCa(doanhThuCa);
                danhSachThongTinCa.add(thongTinCa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachThongTinCa;
    }

    @Override
    public ThongTinCa selectById(String id) {
        ThongTinCa thongTinCa = null;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM ThongTinCa WHERE MaNhanVien = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String maNhanVien = resultSet.getString("MaNhanVien");
                String maCa = resultSet.getString("MaCa");
                LocalDateTime ngayLam = resultSet.getTimestamp("NgayLam").toLocalDateTime();
                double doanhThuCa = resultSet.getDouble("DoanhThuCa");

                // Lấy thông tin đối tượng Nhân viên từ cơ sở dữ liệu
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                // Lấy thông tin đối tượng Ca làm từ cơ sở dữ liệu
                CaLamDAO caLamDAO = new CaLamDAO();
                CaLam caLam = caLamDAO.selectById(maCa);

                // Khởi tạo đối tượng ThongTinCa
                thongTinCa = new ThongTinCa(nhanVien, caLam, ngayLam);
                //thongTinCa.setDoanhThuCa(doanhThuCa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thongTinCa;
    }


	@Override
	public int update(ThongTinCa t) {
		// TODO Auto-generated method stub
		return 0;
	}
	 public ArrayList<Object[]> danhSachCA_NV() {
	        ArrayList<Object[]> danhSach = new ArrayList<>();
	        try {
	        	String sql= "SELECT ROW_NUMBER() OVER (ORDER BY SUM(ct.SoLuong) DESC) AS STT, sp.MaSanPham, sp.TenSanPham, sp.TrangThai, sp.MoTa "
	        			+ "FROM ChiTietHoaDon as ct "
	        			+ "JOIN SanPham as sp ON (sp.MaSanPham = ct.SanPham AND sp.KichThuoc = ct.KichThuoc) "
	        			+ "join HoaDon as hd on hd.MaHoaDon=ct.HoaDon "
	        			+ "GROUP BY sp.MaSanPham, sp.TenSanPham, sp.TrangThai, sp.MoTa "
	        			+ "ORDER BY SUM(ct.SoLuong) DESC";
	        					

	            Connection conn = connectDB.getConnectDB();
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            // Lặp qua kết quả từ resultSet và thêm vào danh sách
	            while (resultSet.next()) {
	                Object[] row = new Object[5];
	                row[0] = resultSet.getString("STT");
	                row[1] = resultSet.getString("MaSanPham");
	                row[2] = resultSet.getString("TenSanPham");
	                String trangThai = resultSet.getBoolean("TrangThai") ? "Đang bán" : "Ngừng bán";
	                row[3] = trangThai;
	                row[4] = resultSet.getString("MoTa");
	                
	                danhSach.add(row);
	            }
	            
	            // Đóng resultSet và preparedStatement
	            resultSet.close();
	            preparedStatement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return danhSach;
	    }
	 public ArrayList<Object[]> danhSachThongKe() {
	        ArrayList<Object[]> danhSach = new ArrayList<>();
	        try {
	        	String sql= "SELECT c.MaCa, MaNhanVien, ThoiGianBatDau, ThoiGianKetThuc, NgayLam "
	        			+ "FROM ThongTinCa nv JOIN CaLam c ON c.MaCa = nv.MaCa "
	        			+ "ORDER BY NgayLam ASC, c.MaCa ASC";
	        					

	            Connection conn = connectDB.getConnectDB();
	            PreparedStatement preparedStatement = conn.prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            double tien=0,tongTien=0;
	            
	            // Lặp qua kết quả từ resultSet và thêm vào danh sách
	            while (resultSet.next()) {
	            	String maCa = resultSet.getString("MaCa");
	                String maNhanVien = resultSet.getString("MaNhanVien");
	                LocalTime thoiGianBatDau = resultSet.getTime("ThoiGianBatDau").toLocalTime();
	                LocalTime thoiGianKetThuc = resultSet.getTime("ThoiGianKetThuc").toLocalTime();
	                LocalDate ngayLam = resultSet.getDate("NgayLam").toLocalDate();
	                if(maCa.equals("CA01"))
	                	tien=1000000;
	                else
	                	tien=tongTien;
	                HoaDonDAO hoaDonDAO = new HoaDonDAO();
	                Double thanhTien= hoaDonDAO.DAOthanhTien(thoiGianBatDau, thoiGianKetThuc, ngayLam);
	                tongTien=tien+thanhTien;
	                // Thêm các giá trị vào mảng row
	                Object[] row = new Object[]{maCa, maNhanVien, thoiGianBatDau, thoiGianKetThuc, ngayLam, tien, tongTien};
	                
	                danhSach.add(row);
	            }
	            
	            // Đóng resultSet và preparedStatement
	            resultSet.close();
	            preparedStatement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return danhSach;
	    }
	 public String timMaCaTruoc(String maCaHienTai, LocalDate ngayLam) {
		    String maCaTruoc = "";
		    String sql = "SELECT TOP 1 MaCa FROM CaLam " +
		                 "WHERE MaCa < ? AND NgayLam = ? " +
		                 "ORDER BY MaCa DESC";
		    try (Connection connection = connectDB.getConnectDB();
		         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setString(1, maCaHienTai);
		        preparedStatement.setDate(2, java.sql.Date.valueOf(ngayLam));
		        ResultSet resultSet = preparedStatement.executeQuery();
		        if (resultSet.next()) {
		            maCaTruoc = resultSet.getString("MaCa");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return maCaTruoc;
		}

}
