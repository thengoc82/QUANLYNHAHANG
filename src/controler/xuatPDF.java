package controler;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class xuatPDF {
    
    public static void xuatPDF(HoaDon hoaDon) throws IOException {
        if (hoaDon == null) {
            throw new IllegalArgumentException("Hóa đơn không thể là null");
        }

        String hoaDonName = hoaDon.getMaHoaDon();
        String filePath = hoaDonName + ".txt";
        File existingFile = new File(filePath);
        if (existingFile.exists()) {
            existingFile.delete();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            // Format currency
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            // Write header
            writer.write("==========================================\n");
            writer.write("            HÓA ĐƠN BÁN HÀNG\n");
            writer.write("==========================================\n\n");
            
            // Write invoice info
            writer.write("Mã hóa đơn: " + hoaDon.getMaHoaDon() + "\n");
            writer.write("Ngày tạo: " + dateFormat.format(hoaDon.getNgayTao()) + "\n");
            writer.write("Nhân viên: " + hoaDon.getNhanVien().getHoTen() + "\n");
            writer.write("Khách hàng: " + hoaDon.getKhachHang().getHoTen() + "\n");
            writer.write("Số điện thoại: " + hoaDon.getKhachHang().getDienThoai() + "\n");
            writer.write("Bàn: " + hoaDon.getBan().getMaBan() + "\n");
            writer.write("Phương thức thanh toán: " + hoaDon.getPhuongThucThanhToan() + "\n\n");

            // Write items
            writer.write("==========================================\n");
            writer.write("STT | Tên sản phẩm | Kích thước | Số lượng | Đơn giá | Thành tiền\n");
            writer.write("==========================================\n");

            int stt = 1;
            for (ChiTietHoaDon cthd : hoaDon.getDsCTHD()) {
                writer.write(String.format("%-4d| %-12s| %-11s| %-9d| %-8s| %-10s\n",
                    stt++,
                    cthd.getSanPham().getTenSanPham(),
                    cthd.getKt().name(),
                    cthd.getSoLuong(),
                    currencyFormat.format(cthd.getSanPham().getGiaByKichThuoc(cthd.getKt())),
                    currencyFormat.format(cthd.getSanPham().getGiaByKichThuoc(cthd.getKt()) * cthd.getSoLuong())
                ));
            }

            // Write totals
            writer.write("==========================================\n");
            writer.write(String.format("%-40s %s\n", "Tổng tiền sản phẩm:", currencyFormat.format(hoaDon.tongTienSanPham())));
            writer.write(String.format("%-40s %s\n", "Tiền giảm:", currencyFormat.format(hoaDon.tongTienGiam())));
            writer.write(String.format("%-40s %s\n", "Tổng tiền thanh toán:", currencyFormat.format(hoaDon.tongTienSanPham() - hoaDon.tongTienGiam())));
            writer.write("==========================================\n");
            writer.write("Cảm ơn quý khách đã sử dụng dịch vụ!\n");
            writer.write("Hẹn gặp lại quý khách!\n");
        }

        // Open the file
        try {
            File txtFile = new File(filePath);
            if (txtFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(txtFile);
            } else {
                throw new IOException("Không thể mở tệp. File tồn tại: " + txtFile.exists() + ", Desktop hỗ trợ: " + Desktop.isDesktopSupported());
            }
        } catch (IOException ex) {
            throw new IOException("Lỗi khi mở file: " + ex.getMessage(), ex);
        }
    }
}
