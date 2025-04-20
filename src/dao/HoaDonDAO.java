package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KichThuoc;
import entity.NhanVien;
import entity.PhuongThucThanhToan;
import entity.SanPham;

public class HoaDonDAO implements InterfaceDAO<HoaDon>{
    public static HoaDonDAO getInstance() {
        return new HoaDonDAO();
    }

    @Override
    public int insert(HoaDon hoaDon) {
        int rowsInserted = 0;
        
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "INSERT INTO HoaDon (MaHoaDon, NhanVien, Ban, KhachHang, NgayTao, PhuongThucThanhToan, TrangThaiThanhToan, GhiChu) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hoaDon.getMaHoaDon());
            preparedStatement.setString(2, hoaDon.getNhanVien().getMaNhanVien());
            if (hoaDon.getBan() == null) {
                preparedStatement.setNull(3, java.sql.Types.VARCHAR); 
            } else {
                preparedStatement.setString(3, hoaDon.getBan().getMaBan());
            }
            preparedStatement.setString(4, hoaDon.getKhachHang().getMaKhachHang());
            preparedStatement.setObject(5, hoaDon.getNgayTao());
            String phuongThuc = hoaDon.getPhuongThucThanhToan().name();
            // Set giá trị chuỗi vào preparedStatement
            preparedStatement.setString(6, phuongThuc);
            preparedStatement.setBoolean(7, hoaDon.isTrangThaiThanhToan());
            preparedStatement.setString(8, hoaDon.getGhiChu());
            rowsInserted = preparedStatement.executeUpdate();
            ChiTietHoaDonDAO chiTietHoaDon = new ChiTietHoaDonDAO();
            chiTietHoaDon.insertCTHD(hoaDon);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsInserted;
    }

    @Override
    public int update(HoaDon hoaDon) {
//        int rowsUpdated = 0;
//        try (Connection connection = connectDB.getConnectDB()) {
//            String sql = "UPDATE HoaDon SET NhanVien=?, Ban=?, KhachHang=?, NgayTao=?, PhuongThucThanhToan=?, TrangThaiThanhToan=?, GhiChu=? WHERE MaHoaDon=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, hoaDon.getNhanVien().getMaNhanVien());
//            preparedStatement.setString(2, hoaDon.getBan().getMaBan());
//            preparedStatement.setString(3, hoaDon.getKhachHang().getMaKhachHang());
//            preparedStatement.setObject(4, hoaDon.getNgayTao());
//            // Sử dụng toán tử ba ngôi để xác định giá trị chuỗi PhuongThucThanhToan
//            String phuongThuc = hoaDon.isTrangThaiThanhToan() ? "Trực tiếp" : "Chuyển khoản";
//            // Set giá trị chuỗi vào preparedStatement
//            preparedStatement.setString(6, phuongThuc);
//            preparedStatement.setBoolean(6, hoaDon.isTrangThaiThanhToan());
//            preparedStatement.setString(7, hoaDon.getGhiChu());
//            preparedStatement.setString(8, hoaDon.getMaHoaDon());
//            rowsUpdated = preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rowsUpdated;
    	return 0;
    }

    @Override
    public ArrayList<HoaDon> selectAll() {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM HoaDon ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maHoaDon = resultSet.getString("MaHoaDon");
                String maNhanVien = resultSet.getString("NhanVien");
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                String maBan = resultSet.getString("Ban");
                BanDAO banDAO = new BanDAO();
//                String strMaBan = String.valueOf(maBan); 
                Ban ban = banDAO.selectById(maBan);

                String maKhachHang = resultSet.getString("KhachHang");
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang khachHang = khachHangDAO.selectById(maKhachHang);
                LocalDateTime ngayTao = resultSet.getTimestamp("NgayTao").toLocalDateTime();
                boolean trangThaiThanhToan = resultSet.getBoolean("TrangThaiThanhToan");
                String ghiChu = resultSet.getString("GhiChu");
                if (ban == null) {
                	HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, null, null, khachHang);
                } else {
                	HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, ban, null, khachHang);
                }
//                ChiTietHoaDon
//                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }


    @Override
    public HoaDon selectById(String id) {
        HoaDon hoaDon = null;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String maHoaDon = resultSet.getString("MaHoaDon");
                String maNhanVien = resultSet.getString("NhanVien");
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                String maBan = resultSet.getString("Ban");
                BanDAO banDAO = new BanDAO();
                Ban ban = banDAO.selectById(maBan); 

                String maKhachHang = resultSet.getString("KhachHang");
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang khachHang = khachHangDAO.selectById(maKhachHang);
                LocalDateTime ngayTao = resultSet.getTimestamp("NgayTao").toLocalDateTime();
                boolean trangThaiThanhToan = resultSet.getBoolean("TrangThaiThanhToan");
                String ghiChu = resultSet.getString("GhiChu");
                String phuongThucThanhToan = resultSet.getString("PhuongThucThanhToan");
                
                ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
                String sqlCTHD = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
                PreparedStatement preparedStatementCTHD = connection.prepareStatement(sqlCTHD);
                preparedStatementCTHD.setString(1, maHoaDon);
                ResultSet resultSetCTHD = preparedStatementCTHD.executeQuery();
                while (resultSetCTHD.next()) {
                    String maSanPham = resultSetCTHD.getString("SanPham");
                    int soLuong = resultSetCTHD.getInt("SoLuong");
                    String kichThuoc = resultSetCTHD.getString("KichThuoc");

                    SanPham sanPham = SanPhamDAO.getInstance().selectById(maSanPham);

                    KichThuoc kt = KichThuoc.valueOf(kichThuoc);
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kt);

                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
                hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, ban, PhuongThucThanhToan.valueOf(phuongThucThanhToan), khachHang); 
                hoaDon.setDsCTHD(danhSachChiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public ArrayList<HoaDon> selectByDay(LocalDateTime d1, LocalDateTime d2) {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM HoaDon WHERE NgayTao BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(d1));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(d2));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maHoaDon = resultSet.getString("MaHoaDon");
                String maNhanVien = resultSet.getString("NhanVien");
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                int maBan = resultSet.getInt("Ban");
                BanDAO banDAO = new BanDAO();
                String strMaBan = String.valueOf(maBan); 
                Ban ban = banDAO.selectById(strMaBan);

                String maKhachHang = resultSet.getString("KhachHang");
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang khachHang = khachHangDAO.selectById(maKhachHang);

                LocalDateTime ngayTao = resultSet.getTimestamp("NgayTao").toLocalDateTime();
                boolean trangThaiThanhToan = resultSet.getBoolean("TrangThaiThanhToan");
                String phuongThucThanhToan = resultSet.getString("PhuongThucThanhToan");
                String ghiChu = resultSet.getString("GhiChu");

                HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, ban, PhuongThucThanhToan.valueOf(phuongThucThanhToan), khachHang);
                
                ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
                String sqlCTHD = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
                PreparedStatement preparedStatementCTHD = connection.prepareStatement(sqlCTHD);
                preparedStatementCTHD.setString(1, maHoaDon);
                ResultSet resultSetCTHD = preparedStatementCTHD.executeQuery();
                while (resultSetCTHD.next()) {
                    String maSanPham = resultSetCTHD.getString("SanPham");
                    int soLuong = resultSetCTHD.getInt("SoLuong");
                    String kichThuoc = resultSetCTHD.getString("KichThuoc");

                    SanPham sanPham = SanPhamDAO.getInstance().selectById(maSanPham);

                    KichThuoc kt = KichThuoc.valueOf(kichThuoc);
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kt);

                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
                hoaDon.setDsCTHD(danhSachChiTietHoaDon);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }

    
    public ArrayList<HoaDon> selectDay(LocalDate d1) {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM HoaDon WHERE NgayTao = ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            LocalDateTime dateTime = d1.atStartOfDay();
            preparedStatement.setTimestamp(1, Timestamp.valueOf(dateTime));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	String maHoaDon = resultSet.getString("MaHoaDon");
                String maNhanVien = resultSet.getString("NhanVien");
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                int maBan = resultSet.getInt("Ban");

                BanDAO banDAO = new BanDAO();
                String strMaBan = String.valueOf(maBan); 
                Ban ban = banDAO.selectById(strMaBan);

                String maKhachHang = resultSet.getString("KhachHang");
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang khachHang = khachHangDAO.selectById(maKhachHang);
                LocalDateTime ngayTao = resultSet.getTimestamp("NgayTao").toLocalDateTime();
                boolean trangThaiThanhToan = resultSet.getBoolean("TrangThaiThanhToan");
                String ghiChu = resultSet.getString("GhiChu");

                HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, ban, null, khachHang); // Đối số thứ hai và thứ ba của constructor chưa được hoàn thiện
                
                ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
                String sqlCTHD = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
                PreparedStatement preparedStatementCTHD = connection.prepareStatement(sqlCTHD);
                preparedStatementCTHD.setString(1, maHoaDon);
                ResultSet resultSetCTHD = preparedStatementCTHD.executeQuery();
                while (resultSetCTHD.next()) {
                    String maSanPham = resultSetCTHD.getString("SanPham");
                    int soLuong = resultSetCTHD.getInt("SoLuong");
                    String kichThuoc = resultSetCTHD.getString("KichThuoc");

                    SanPham sanPham = SanPhamDAO.getInstance().selectById(maSanPham);

                    KichThuoc kt = KichThuoc.valueOf(kichThuoc);
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kt);

                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
                hoaDon.setDsCTHD(danhSachChiTietHoaDon);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    public ArrayList<HoaDon> selectByMonth() {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection connection = connectDB.getConnectDB()) {
            LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
            YearMonth yearMonth = YearMonth.from(LocalDate.now());
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();
            
            String sql = "SELECT * FROM HoaDon WHERE NgayTao BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            LocalDateTime fromDateTime = firstDayOfMonth.atStartOfDay();
            LocalDateTime toDateTime = lastDayOfMonth.atTime(23, 59, 59);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(fromDateTime));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(toDateTime));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	String maHoaDon = resultSet.getString("MaHoaDon");
                String maNhanVien = resultSet.getString("NhanVien");
                NhanVienDAO nhanVienDAO = new NhanVienDAO();
                NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

                int maBan = resultSet.getInt("Ban");
                BanDAO banDAO = new BanDAO();
                String strMaBan = String.valueOf(maBan); 
                Ban ban = banDAO.selectById(strMaBan);

                String maKhachHang = resultSet.getString("KhachHang");
                KhachHangDAO khachHangDAO = new KhachHangDAO();
                KhachHang khachHang = khachHangDAO.selectById(maKhachHang);

                LocalDateTime ngayTao = resultSet.getTimestamp("NgayTao").toLocalDateTime();
                boolean trangThaiThanhToan = resultSet.getBoolean("TrangThaiThanhToan");
                String ghiChu = resultSet.getString("GhiChu");
                String pttt = resultSet.getString("PhuongThucThanhToan");
                HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, ngayTao, trangThaiThanhToan, ghiChu, ban, PhuongThucThanhToan.valueOf(pttt), khachHang); 
                
                ArrayList<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
                String sqlCTHD = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
                PreparedStatement preparedStatementCTHD = connection.prepareStatement(sqlCTHD);
                preparedStatementCTHD.setString(1, maHoaDon);
                ResultSet resultSetCTHD = preparedStatementCTHD.executeQuery();
                while (resultSetCTHD.next()) {
                    String maSanPham = resultSetCTHD.getString("SanPham");
                    int soLuong = resultSetCTHD.getInt("SoLuong");
                    String kichThuoc = resultSetCTHD.getString("KichThuoc");

                    SanPham sanPham = SanPhamDAO.getInstance().selectById(maSanPham);

                    KichThuoc kt = KichThuoc.valueOf(kichThuoc);
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kt);

                    danhSachChiTietHoaDon.add(chiTietHoaDon);
                }
                hoaDon.setDsCTHD(danhSachChiTietHoaDon);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachHoaDon;
    }
    public double DAOthanhTien(LocalTime d1, LocalTime d2, LocalDate ngay) {
        double thanhTien = 0;
        String sql = "SELECT * FROM HoaDon hd " +
                "JOIN ThongTinCa nv ON hd.NhanVien = nv.MaNhanVien " +
                "JOIN CaLam c ON c.MaCa = nv.MaCa " +
                "WHERE CONVERT(date, NgayTao) = ? " +
                "AND CONVERT(time, NgayTao) <= CONVERT(time, ?) " +
                "AND CONVERT(time, NgayTao) >= CONVERT(time, ?)";

        try (Connection connection = connectDB.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Timestamp timestamp1 = Timestamp.valueOf(LocalDateTime.of(ngay, d1));
            Timestamp timestamp2 = Timestamp.valueOf(LocalDateTime.of(ngay, d2));

            preparedStatement.setDate(1, java.sql.Date.valueOf(ngay));
            preparedStatement.setTimestamp(2, timestamp2);
            preparedStatement.setTimestamp(3, timestamp1);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	String maHoaDon = resultSet.getString("MaHoaDon");
            	ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
            	thanhTien+=chiTietHoaDonDAO.TongTienHoaDon(maHoaDon);
            	
            }
            
            resultSet.close();
            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thanhTien;
    	
    }
    //Tạo mã hoá đơn
    public String taoMaHD() throws SQLException {
        String newMaHoaDon = null;
        LocalDate today = LocalDate.now();
        String currentDate = today.format(DateTimeFormatter.ofPattern("ddMMyy"));
        String query = "SELECT MAX(CAST(SUBSTRING(maHoaDon, 9, 3) AS INT)) AS maxMaHoaDon " +
                       "FROM HoaDon " +
                       "WHERE SUBSTRING(maHoaDon, 3, 6) = ?";

        try (PreparedStatement preparedStatement = connectDB.getConnectDB().prepareStatement(query)) {
            preparedStatement.setString(1, currentDate);
            ResultSet resultSet = preparedStatement.executeQuery();  
            int currentMax = 0;
            if (resultSet.next() && resultSet.getObject("maxMaHoaDon") != null) {
                currentMax = resultSet.getInt("maxMaHoaDon");
                System.out.println(currentMax + "\n");
            }
            int nextMaHoaDon = currentMax + 1;
            newMaHoaDon = "HD" + currentDate + String.format("%03d", nextMaHoaDon);
        }

        return newMaHoaDon;
    }
}
