package controler;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InHoaDon implements Printable {
    private final HoaDon hoaDon;
    private final NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public InHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void print() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                System.err.println("Error printing: " + e.getMessage());
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Set font
        Font titleFont = new Font("Monospaced", Font.BOLD, 14);
        Font normalFont = new Font("Monospaced", Font.PLAIN, 12);
        Font smallFont = new Font("Monospaced", Font.PLAIN, 10);

        int y = 20;
        int lineHeight = 15;

        // Header
        g2d.setFont(titleFont);
        g2d.drawString("HOÁ ĐƠN BÁN HÀNG", 80, y);
        y += lineHeight * 2;

        // Restaurant info
        g2d.setFont(normalFont);
        g2d.drawString("NHÀ HÀNG COFFEE SHOP", 70, y);
        y += lineHeight;
        g2d.setFont(smallFont);
        g2d.drawString("Địa chỉ: 12 Nguyễn Văn Bảo, P.4, Gò Vấp, TP.HCM", 30, y);
        y += lineHeight;
        g2d.drawString("SĐT: 0123456789", 100, y);
        y += lineHeight * 2;

        // Invoice details
        g2d.setFont(normalFont);
        g2d.drawString("Số HĐ: " + hoaDon.getMaHoaDon(), 10, y);
        g2d.drawString("Ngày: " + hoaDon.getNgayTao().format(dtf), 150, y);
        y += lineHeight;
        g2d.drawString("Thu ngân: " + hoaDon.getNhanVien().getHoTen(), 10, y);
        y += lineHeight * 2;

        // Table header
        g2d.drawString("STT  Tên SP         SL    Đơn giá      T.Tiền", 10, y);
        y += lineHeight;
        g2d.drawString("------------------------------------------------", 10, y);
        y += lineHeight;

        // Items
        int stt = 1;
        double totalAmount = 0.0;
        for (ChiTietHoaDon ct : hoaDon.getDsCTHD()) {
            String tenSP = ct.getSanPham().getTenSanPham();
            if (tenSP.length() > 12) {
                tenSP = tenSP.substring(0, 12) + "..";
            }
            double lineTotal = ct.getGiaBan() * ct.getSoLuong();
            totalAmount += lineTotal;
            g2d.drawString(String.format("%-4d %-14s %-5d %-12s %-12s",
                stt++,
                tenSP,
                ct.getSoLuong(),
                fmt.format(ct.getGiaBan()),
                fmt.format(lineTotal)), 10, y);
            y += lineHeight;
        }

        // Footer
        g2d.drawString("------------------------------------------------", 10, y);
        y += lineHeight;
        g2d.drawString("Tổng tiền hàng: " + fmt.format(hoaDon.tongTienSanPham()), 10, y);
        y += lineHeight;
        g2d.drawString("Giảm giá: " + fmt.format(hoaDon.tongTienGiam()), 10, y);
        y += lineHeight;
        double vat = totalAmount * 0.1; // 10% VAT
        g2d.drawString("VAT (10%): " + fmt.format(vat), 10, y);
        y += lineHeight;
        g2d.setFont(titleFont);
        double finalTotal = totalAmount + vat - hoaDon.tongTienGiam();
        g2d.drawString("TỔNG THANH TOÁN: " + fmt.format(finalTotal), 10, y);
        y += lineHeight * 2;

        // Thank you message
        g2d.setFont(normalFont);
        g2d.drawString("Cảm ơn quý khách, hẹn gặp lại!", 50, y);

        return PAGE_EXISTS;
    }
} 