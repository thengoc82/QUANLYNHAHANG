package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.connectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KichThuoc;
import entity.SanPham;

public class ChiTietHoaDonDAO {

    public int insertCTHD(HoaDon hoaDon) {
        int rowsInserted = 0;
        PreparedStatement pst = null;

        try {
            // Câu lệnh SQL chèn dữ liệu vào bảng ChiTietHoaDon
            String sql = "INSERT INTO ChiTietHoaDon (SoLuong, SanPham, HoaDon ,KichThuoc) VALUES (?, ?, ?, ?)";
            Connection conn = connectDB.getConnectDB();
            pst = conn.prepareStatement(sql);

            for (ChiTietHoaDon t : hoaDon.getDsCTHD()) {
                pst.setInt(1, t.getSoLuong());
                pst.setString(2, t.getSanPham().getMaSanPham());
                pst.setString(3, hoaDon.getMaHoaDon());
                pst.setString(4, t.getKt().name());
                rowsInserted += pst.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng PreparedStatement
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return rowsInserted;
    }


   


    public ArrayList<HoaDon> selectAll() {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM ChiTietHoaDon";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maHoaDon = resultSet.getString("HoaDon");
                int soLuong = resultSet.getInt("SoLuong");
                String maSanPham = resultSet.getString("SanPham");
                String kichThuocName = resultSet.getString("KichThuoc");

                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = sanPhamDAO.selectById(maSanPham);

                // Tạo đối tượng enum KichThuoc từ tên
                KichThuoc kichThuoc = KichThuoc.valueOf(kichThuocName);

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kichThuoc);

                boolean found = false;
                for (HoaDon hoaDon : danhSachHoaDon) {
                    if (hoaDon.getMaHoaDon().equals(maHoaDon)) {
                        hoaDon.addChiTietHoaDon(chiTietHoaDon);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    HoaDonDAO hoaDonDAO = new HoaDonDAO();
                    HoaDon hoaDon = hoaDonDAO.selectById(maHoaDon);
                    hoaDon.addChiTietHoaDon(chiTietHoaDon);
                    danhSachHoaDon.add(hoaDon);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return danhSachHoaDon;
    }

    public ArrayList<ChiTietHoaDon> selectByMaHoaDon(String maHoaDon) {
        ArrayList<ChiTietHoaDon> danhSachCTHD = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maHoaDon);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maSanPham = resultSet.getString("SanPham");
                int soLuong = resultSet.getInt("SoLuong");
                String kichThuocName = resultSet.getString("KichThuoc");

                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = sanPhamDAO.selectById(maSanPham);

                KichThuoc kichThuoc = KichThuoc.valueOf(kichThuocName);

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kichThuoc);
                danhSachCTHD.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return danhSachCTHD;
    }
    public ArrayList<ChiTietHoaDon> ThongKe(String maHoaDon) {
        ArrayList<ChiTietHoaDon> danhSachCTHD = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maHoaDon);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maSanPham = resultSet.getString("SanPham");
                int soLuong = resultSet.getInt("SoLuong");
                String kichThuocName = resultSet.getString("KichThuoc");

                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = sanPhamDAO.selectById(maSanPham);

                KichThuoc kichThuoc = KichThuoc.valueOf(kichThuocName);

                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(sanPham, soLuong, kichThuoc);
                danhSachCTHD.add(chiTietHoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return danhSachCTHD;
    }
    public double TongTienHoaDon(String maHoaDon) {
        ArrayList<ChiTietHoaDon> danhSachCTHD = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        double tong=0;
        try (Connection connection = connectDB.getConnectDB()) {
            String sql = "SELECT * FROM ChiTietHoaDon WHERE HoaDon = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maHoaDon);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maSanPham = resultSet.getString("SanPham");
                int soLuong = resultSet.getInt("SoLuong");
                String kichThuocName = resultSet.getString("KichThuoc");

                SanPhamDAO sanPhamDAO = new SanPhamDAO();
                SanPham sanPham = sanPhamDAO.selectByIdAndSize(maSanPham,KichThuoc.valueOf(kichThuocName));
                
                tong+=sanPham.getGiaByKichThuoc(KichThuoc.valueOf(kichThuocName));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tong;
    }

}
