package dao;
import connectDB.connectDB;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.LoaiSanPham;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamDAO implements InterfaceDAO<SanPham>{
	public static SanPhamDAO getInstance() {
		return new SanPhamDAO();
	}
    @Override
    public int insert(SanPham sanPham) {
        int rowsInserted = 0;
        PreparedStatement preparedStatement = null;
        KhuyenMai km = sanPham.getKhuyenMai();
        try {
            String sql = "INSERT INTO SanPham (MaSanPham, KhuyenMai, LoaiSanPham, TenSanPham, KichThuoc, Gia, TrangThai, HinhAnh, MoTa) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = connectDB.getConnectDB();

            LoaiSanPham loaiSanPham = sanPham.getLoaiSanPham();
            String maLoaiSanPham = loaiSanPham.getMaLoai();         
            if (sanPham.getLoaiSanPham().isCoPhanLoai()) {
                for (KichThuoc kichThuoc : KichThuoc.values()) {
                    if (kichThuoc != KichThuoc.D) {
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, sanPham.getMaSanPham());
                        if (km == null) {
                            preparedStatement.setNull(2, java.sql.Types.VARCHAR);
                        } else {
                            preparedStatement.setString(2, km.getMaKhuyenMai());
                        }
                        preparedStatement.setString(3, maLoaiSanPham);
                        preparedStatement.setString(4, sanPham.getTenSanPham());
                        preparedStatement.setString(5, kichThuoc.toString());
                        preparedStatement.setDouble(6, sanPham.getGiaByKichThuoc(kichThuoc));
                        preparedStatement.setBoolean(7, sanPham.isTrangThai());
                        preparedStatement.setString(8, sanPham.getHinhAnh());
                        preparedStatement.setString(9, sanPham.getMoTa());
                        rowsInserted += preparedStatement.executeUpdate();
                    }
                }
            } else  {
            	for (KichThuoc kichThuoc : KichThuoc.values()) {
                    if (kichThuoc == KichThuoc.D) {
                       
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, sanPham.getMaSanPham() );
                        if (km == null) {
                            preparedStatement.setNull(2, java.sql.Types.VARCHAR);
                        } else {
                            preparedStatement.setString(2, km.getMaKhuyenMai());
                        }
                        preparedStatement.setString(3, maLoaiSanPham);
                        preparedStatement.setString(4, sanPham.getTenSanPham());
                        preparedStatement.setString(5, "D");
                        preparedStatement.setDouble(6, sanPham.getGiaByKichThuoc(kichThuoc));
                        preparedStatement.setBoolean(7, sanPham.isTrangThai());
                        preparedStatement.setString(8, sanPham.getHinhAnh());
                        preparedStatement.setString(9, sanPham.getMoTa());
                        rowsInserted += preparedStatement.executeUpdate();
                    }
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng PreparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsInserted;
    }

    public int update(SanPham sanPham) {
        int rowsUpdated = 0;
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE SanPham SET KhuyenMai=?, TenSanPham=?, Gia=?, TrangThai=?, HinhAnh=?, MoTa=? WHERE MaSanPham = ? AND KichThuoc = ?";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            KhuyenMai khuyenMai = sanPham.getKhuyenMai();
            if (sanPham.getLoaiSanPham().isCoPhanLoai()) {
                for (KichThuoc kichThuoc : KichThuoc.values()) {
                    if (kichThuoc != KichThuoc.D) {
                    	if (khuyenMai == null) {
                            preparedStatement.setNull(1, java.sql.Types.VARCHAR);
                        } else {
                            preparedStatement.setString(1, khuyenMai.getMaKhuyenMai());
                        }
                        preparedStatement.setString(2, sanPham.getTenSanPham());
                        preparedStatement.setDouble(3, sanPham.getGiaByKichThuoc(kichThuoc));
                        preparedStatement.setBoolean(4, sanPham.isTrangThai());
                        preparedStatement.setString(5, sanPham.getHinhAnh());
                        preparedStatement.setString(6, sanPham.getMoTa());
                        preparedStatement.setString(7, sanPham.getMaSanPham());
                        preparedStatement.setString(8, kichThuoc.toString());
                        rowsUpdated += preparedStatement.executeUpdate();
                    }
                }
            } else {
            	if (khuyenMai == null) {
                    preparedStatement.setNull(1, java.sql.Types.VARCHAR);
                } else {
                    preparedStatement.setString(1, khuyenMai.getMaKhuyenMai());
                }
                preparedStatement.setString(2, sanPham.getTenSanPham());
                preparedStatement.setDouble(3, sanPham.getGiaByKichThuoc(KichThuoc.D)); 
                preparedStatement.setBoolean(4, sanPham.isTrangThai());
                preparedStatement.setString(5, sanPham.getHinhAnh());
                preparedStatement.setString(6, sanPham.getMoTa());
                preparedStatement.setString(7, sanPham.getMaSanPham() );
                preparedStatement.setString(8, "D");
                rowsUpdated += preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng PreparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsUpdated;
    }


    @Override
    public ArrayList<SanPham> selectAll() {
    	ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                if (!sanPhamMap.containsKey(maSanPham)) {
                    String maLoai = resultSet.getString("LoaiSanPham");
                    LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                    LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                    String maKhuyenMai = resultSet.getString("KhuyenMai");
                    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                    KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                    sanPhamMap.put(maSanPham, sanPham);
                }

                SanPham sanPham = sanPhamMap.get(maSanPham);
                sanPham.themKichThuocGia(kichThuoc, gia);
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

        sanPhams.addAll(sanPhamMap.values()); 
        return sanPhams;
    }

    @Override
    public SanPham selectById(String maSanPham ) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SanPham sanPham = null;     
        try {
        	String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        	Connection conn = connectDB.getConnectDB();
        	preparedStatement = conn.prepareStatement(sql);
        	preparedStatement.setString(1, maSanPham);
        	resultSet = preparedStatement.executeQuery();

            
            
            while (resultSet.next()) {
                String maSP = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");
                String maLoai = resultSet.getString("LoaiSanPham");
                LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                String maKhuyenMai = resultSet.getString("KhuyenMai");
                KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);
                

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);
                if(sanPham == null) {
                	sanPham= new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                }
                sanPham.themKichThuocGia(kichThuoc, gia);
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
        return sanPham;
    }
    public ArrayList<SanPham> selectByIdAllSize(String ma) {
    	ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
        	String sql = "SELECT * FROM SanPham WHERE MaSanPham = ?";
        	Connection conn = connectDB.getConnectDB();
        	preparedStatement = conn.prepareStatement(sql);
        	preparedStatement.setString(1, ma);
        	resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                if (!sanPhamMap.containsKey(maSanPham)) {
                    String maLoai = resultSet.getString("LoaiSanPham");
                    LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                    LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                    String maKhuyenMai = resultSet.getString("KhuyenMai");
                    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                    KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                    sanPhamMap.put(maSanPham, sanPham);
                }
                SanPham sanPham = sanPhamMap.get(maSanPham);
                sanPham.themKichThuocGia(kichThuoc, gia);
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

        sanPhams.addAll(sanPhamMap.values()); 
        return sanPhams;
    }
    public SanPham selectByIdAndSize(String maSanPham, KichThuoc kichThuoc) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SanPham sanPham = null;

        try {
            String sql = "SELECT * FROM SanPham WHERE MaSanPham = ? AND KichThuoc = ?";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, maSanPham);
            preparedStatement.setString(2, kichThuoc.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String maSP = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                double gia = resultSet.getDouble("Gia");
                String maLoai = resultSet.getString("LoaiSanPham");
                LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                String maKhuyenMai = resultSet.getString("KhuyenMai");
                KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                sanPham.themKichThuocGia(kichThuoc, gia);
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
        return sanPham;
    }


  //Select sản phẩm thuộc loại cafe
    public ArrayList<SanPham> selectCafe() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham WHERE LoaiSanPham = 'L1'";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                if (!sanPhamMap.containsKey(maSanPham)) {
                    String maLoai = resultSet.getString("LoaiSanPham");
                    LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                    LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                    String maKhuyenMai = resultSet.getString("KhuyenMai");
                    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                    KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                    sanPhamMap.put(maSanPham, sanPham);
                }

                SanPham sanPham = sanPhamMap.get(maSanPham);
                sanPham.themKichThuocGia(kichThuoc, gia);
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

        sanPhams.addAll(sanPhamMap.values());
        return sanPhams;
    }
    //Select sản phẩm thuộc loại bánh
    public ArrayList<SanPham> selectCake() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham WHERE LoaiSanPham = 'L2'";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                if (!sanPhamMap.containsKey(maSanPham)) {
                    String maLoai = resultSet.getString("LoaiSanPham");
                    LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                    LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                    String maKhuyenMai = resultSet.getString("KhuyenMai");
                    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                    KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                    sanPhamMap.put(maSanPham, sanPham);
                }

                // Lấy sản phẩm từ `sanPhamMap` và thêm kích thước và giá
                SanPham sanPham = sanPhamMap.get(maSanPham);
                sanPham.themKichThuocGia(kichThuoc, gia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng `ResultSet` và `PreparedStatement`
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

        sanPhams.addAll(sanPhamMap.values()); // Chuyển từ `sanPhamMap` sang `ArrayList`
        return sanPhams;
    }
    //Sản phẩm khác
    public ArrayList<SanPham> selectOrther() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham WHERE LoaiSanPham = 'L3'";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                if (!sanPhamMap.containsKey(maSanPham)) {
                    String maLoai = resultSet.getString("LoaiSanPham");
                    LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                    LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                    String maKhuyenMai = resultSet.getString("KhuyenMai");
                    KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                    KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                    SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                    sanPhamMap.put(maSanPham, sanPham);
                }

                // Lấy sản phẩm từ `sanPhamMap` và thêm kích thước và giá
                SanPham sanPham = sanPhamMap.get(maSanPham);
                sanPham.themKichThuocGia(kichThuoc, gia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng `ResultSet` và `PreparedStatement`
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

        sanPhams.addAll(sanPhamMap.values()); // Chuyển từ `sanPhamMap` sang `ArrayList`
        return sanPhams;
    }
    
    private void updateKichThuocGia(SanPham sanPham) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            String sql = "SELECT * FROM SanPham WHERE maSanPham = ?";
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getMaSanPham());
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String kichThuoc = resultSet.getString("kichThuoc");
                double gia = resultSet.getDouble("gia");
                sanPham.themKichThuocGia(KichThuoc.fromStringIgnoreCase(kichThuoc), gia);
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
        
    }
  //Sản phẩm khác
    public ArrayList<SanPham> selectDangBan(boolean trangThai1) {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham WHERE TrangThai = ?"; // Thêm giá trị cho điều kiện TrangThai
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, trangThai1); // Giả sử true là trạng thái "Đang bán"
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                if (!resultSet.wasNull()) { // Kiểm tra giá trị null trước khi lấy kích thước
                    KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                    if (!sanPhamMap.containsKey(maSanPham)) {
                        String maLoai = resultSet.getString("LoaiSanPham");
                        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                        LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                        String maKhuyenMai = resultSet.getString("KhuyenMai");
                        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                        KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                        SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                        sanPhamMap.put(maSanPham, sanPham);
                    }

                    // Lấy sản phẩm từ `sanPhamMap` và thêm kích thước và giá
                    SanPham sanPham = sanPhamMap.get(maSanPham);
                    sanPham.themKichThuocGia(kichThuoc, gia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng `ResultSet` và `PreparedStatement`
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

        sanPhams.addAll(sanPhamMap.values()); // Chuyển từ `sanPhamMap` sang `ArrayList`
        return sanPhams;
    }
    public ArrayList<SanPham> selectNgungBan(boolean trangThai1) {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
            String sql = "SELECT * FROM SanPham WHERE TrangThai = ?"; // Thêm giá trị cho điều kiện TrangThai
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, trangThai1); // Giả sử true là trạng thái "Đang bán"
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");

                if (!resultSet.wasNull()) { // Kiểm tra giá trị null trước khi lấy kích thước
                    KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                    if (!sanPhamMap.containsKey(maSanPham)) {
                        String maLoai = resultSet.getString("LoaiSanPham");
                        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                        LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                        String maKhuyenMai = resultSet.getString("KhuyenMai");
                        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                        KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                        SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                        sanPhamMap.put(maSanPham, sanPham);
                    }

                    // Lấy sản phẩm từ `sanPhamMap` và thêm kích thước và giá
                    SanPham sanPham = sanPhamMap.get(maSanPham);
                    sanPham.themKichThuocGia(kichThuoc, gia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng `ResultSet` và `PreparedStatement`
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

        sanPhams.addAll(sanPhamMap.values()); // Chuyển từ `sanPhamMap` sang `ArrayList`
        return sanPhams;
    }
    public ArrayList<SanPham> sanPhamCoKhuyenMai(String ma, String a) {
   	 ArrayList<SanPham> sanPhams = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<String, SanPham> sanPhamMap = new HashMap<>();
        
        try {
       	 String sql;
       	 if(a.equals("Chưa áp dụng")) {
       		 sql = "SELECT * FROM SanPham WHERE KhuyenMai <> ?"; }
       	 else {
       		 sql = "SELECT * FROM SanPham WHERE KhuyenMai = ?"; }
            Connection conn = connectDB.getConnectDB();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ma); 
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maSanPham = resultSet.getString("MaSanPham");
                String tenSanPham = resultSet.getString("TenSanPham");
                boolean trangThai = resultSet.getBoolean("TrangThai");
                String hinhAnh = resultSet.getString("HinhAnh");
                String moTa = resultSet.getString("MoTa");
                String kichThuocStr = resultSet.getString("KichThuoc");
                double gia = resultSet.getDouble("Gia");
                
                if (!resultSet.wasNull()) { // Kiểm tra giá trị null trước khi lấy kích thước
                    KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

                    if (!sanPhamMap.containsKey(maSanPham)) {
                        String maLoai = resultSet.getString("LoaiSanPham");
                        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
                        LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(maLoai);

                        String maKhuyenMai = resultSet.getString("KhuyenMai");
                        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                        KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                        SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                        sanPhamMap.put(maSanPham, sanPham);
                    }

                    // Lấy sản phẩm từ `sanPhamMap` và thêm kích thước và giá
                    SanPham sanPham = sanPhamMap.get(maSanPham);
                    sanPham.themKichThuocGia(kichThuoc, gia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng `ResultSet` và `PreparedStatement`
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

        sanPhams.addAll(sanPhamMap.values()); // Chuyển từ `sanPhamMap` sang `ArrayList`
        return sanPhams;
   }
   public int updateKM(String maSP, String maKM) {
       int rowsUpdated = 0;
       PreparedStatement preparedStatement = null;

       try {
           String sql = "UPDATE SanPham SET KhuyenMai=? WHERE MaSanPham Like ?";
           Connection conn = connectDB.getConnectDB();

           preparedStatement = conn.prepareStatement(sql);

           // Lấy loại sản phẩm và mã khuyến mãi từ đối tượng SanPham
          

           // Tạo câu lệnh SQL dựa trên loại sản phẩm
           for (KichThuoc kichThuoc : KichThuoc.values()) {
               if (kichThuoc != KichThuoc.D) {
                   preparedStatement.setString(1, maKM);
                       
                   preparedStatement.setString(2,maSP);
                   rowsUpdated += preparedStatement.executeUpdate();
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           // Đóng PreparedStatement
           if (preparedStatement != null) {
               try {
                   preparedStatement.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
       return rowsUpdated;
   }
   public int soLuongSanPham(String maSP) {
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       int tongSoLuong = 0; // Biến lưu trữ tổng số lượng sản phẩm
       
       try {
           String sql = "SELECT SUM(SoLuong) AS TongSoLuong FROM ChiTietHoaDon WHERE SanPham = ?";
           Connection conn = connectDB.getConnectDB();
           preparedStatement = conn.prepareStatement(sql);
           preparedStatement.setString(1, maSP);
           resultSet = preparedStatement.executeQuery();

           // Lấy giá trị tổng số lượng từ ResultSet
           if (resultSet.next()) {
               tongSoLuong = resultSet.getInt("TongSoLuong");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           // Đóng resultSet
           if (resultSet != null) {
               try {
                   resultSet.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           // Đóng preparedStatement
           if (preparedStatement != null) {
               try {
                   preparedStatement.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
       // Trả về tổng số lượng sản phẩm
       System.out.println("so luong "+tongSoLuong);
       return tongSoLuong;
   }
   public ArrayList<Object[]> danhSach(LocalDateTime d1, LocalDateTime d2, boolean s) {
	    ArrayList<Object[]> danhSach = new ArrayList<>();
	    try {
	        String sql;
	        if (s) {
	            sql = "SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, ct.KichThuoc as KichThuoc, sp.Gia, SUM(ct.SoLuong) AS SoLuong, SUM(ct.SoLuong * sp.Gia) AS ThanhTien " +
	                  "FROM ChiTietHoaDon as ct " +
	                  "JOIN SanPham as sp ON (sp.MaSanPham = ct.SanPham AND sp.KichThuoc = ct.KichThuoc) " +
	                  "JOIN HoaDon as hd ON hd.MaHoaDon = ct.HoaDon " +
	                  "WHERE hd.NgayTao BETWEEN ? AND ? " +
	                  "GROUP BY sp.MaSanPham, sp.TenSanPham, sp.MoTa, ct.KichThuoc, sp.Gia " +
	                  "ORDER BY SoLuong DESC";
	        } else {
	            sql = "SELECT sp.MaSanPham, sp.TenSanPham, sp.MoTa, ct.KichThuoc as KichThuoc, sp.Gia, SUM(ct.SoLuong) AS SoLuong, SUM(ct.SoLuong * sp.Gia) AS ThanhTien " +
	                  "FROM ChiTietHoaDon as ct " +
	                  "JOIN SanPham as sp ON (sp.MaSanPham = ct.SanPham AND sp.KichThuoc = ct.KichThuoc) " +
	                  "JOIN HoaDon as hd ON hd.MaHoaDon = ct.HoaDon " +
	                  "WHERE hd.NgayTao BETWEEN ? AND ? " +
	                  "GROUP BY sp.MaSanPham, sp.TenSanPham, sp.MoTa, ct.KichThuoc, sp.Gia";
	        }

	        Connection conn = connectDB.getConnectDB();
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);

	        preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(d1));
	        preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(d2));

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            Object[] row = new Object[7];
	            row[0] = resultSet.getString("MaSanPham");
	            row[1] = resultSet.getString("TenSanPham");
	            row[2] = resultSet.getString("MoTa");
	            row[3] = resultSet.getString("KichThuoc");
	            row[4] = resultSet.getDouble("Gia");
	            row[5] = resultSet.getInt("SoLuong");
	            row[6] = resultSet.getDouble("ThanhTien");
	            danhSach.add(row);
	        }
	        resultSet.close();
	        preparedStatement.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return danhSach;
	}

   public ArrayList<Object[]> danhSachSPBanChay() {
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
   public String taoMaSP()  {
       String newMaSP = null;
       int currentMax = 0;
       String query = "SELECT MAX(CAST(SUBSTRING(MaSanPham, 4, 2) AS INT)) AS maxMaSP " +
                      "FROM SanPham ";

       try (PreparedStatement preparedStatement = connectDB.getConnectDB().prepareStatement(query)) {
           ResultSet resultSet = preparedStatement.executeQuery();  
           if (resultSet.next() && resultSet.getObject("maxMaSP") != null) {
               currentMax = resultSet.getInt("maxMaSP");
           }
           int nextMaHoaDon = currentMax + 1;
           newMaSP = "SP" + String.format("%03d", nextMaHoaDon);
       } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       return newMaSP;
   }

   public ArrayList<SanPham> selectByCategory(String categoryName) {
       ArrayList<SanPham> sanPhams = new ArrayList<>();
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       Map<String, SanPham> sanPhamMap = new HashMap<>();
       
       try {
           // First get the category ID
           LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
           LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectByName(categoryName);
           
           if (loaiSanPham == null) {
               return sanPhams; // Return empty list if category not found
           }
           
           String sql = "SELECT * FROM SanPham WHERE LoaiSanPham = ?";
           Connection conn = connectDB.getConnectDB();
           preparedStatement = conn.prepareStatement(sql);
           preparedStatement.setString(1, loaiSanPham.getMaLoai());
           resultSet = preparedStatement.executeQuery();
           
           while (resultSet.next()) {
               String maSanPham = resultSet.getString("MaSanPham");
               String tenSanPham = resultSet.getString("TenSanPham");
               boolean trangThai = resultSet.getBoolean("TrangThai");
               String hinhAnh = resultSet.getString("HinhAnh");
               String moTa = resultSet.getString("MoTa");
               String kichThuocStr = resultSet.getString("KichThuoc");
               double gia = resultSet.getDouble("Gia");

               KichThuoc kichThuoc = KichThuoc.fromStringIgnoreCase(kichThuocStr);

               if (!sanPhamMap.containsKey(maSanPham)) {
                   String maKhuyenMai = resultSet.getString("KhuyenMai");
                   KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
                   KhuyenMai khuyenMai = khuyenMaiDAO.selectById(maKhuyenMai);

                   SanPham sanPham = new SanPham(maSanPham, tenSanPham, trangThai, loaiSanPham, hinhAnh, moTa, khuyenMai);
                   sanPhamMap.put(maSanPham, sanPham);
               }

               SanPham sanPham = sanPhamMap.get(maSanPham);
               sanPham.themKichThuocGia(kichThuoc, gia);
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

       sanPhams.addAll(sanPhamMap.values());
       return sanPhams;
   }
}
