package gui;

import controler.InHoaDon;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.PhuongThucThanhToan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class pnlHoaDon extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaHoaDon;
	private JTable tblHoaDon;
	private final JTable tblChiTiet;
	private DefaultTableModel modalHoaDon;
	private DefaultTableModel modalCTHD;
	JLabel lbltxtMaHD ;
	JLabel lbltxtMaKH;
	JLabel lbltxtSdt ;
	JLabel lbltxtTGTao; 
	JLabel lbltxtLoaiThanhToans;
	JLabel lblTienSanPham;
	JLabel lbltxtTienSanPham;
	JLabel lbltxtTongTienHoaDOn, lbltxtTienGiam;
	JLabel lbltxtMaNV;
	JDateChooser dcDenNgay;
	JDateChooser dcTuNgay;
	
	ArrayList<HoaDon> danhSachHoaDon;
	NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public class Colors {
	    public static final Color COLOR_LIGHTBLACK = new Color(34, 34, 34);        // Gần như đen
	    public static final Color COLOR_MEDIUM_GRAY = new Color(91, 90, 90); // Xám đậm
	    public static final Color COLOR_LIGHT_GRAY = new Color(200, 200, 200); // Xám nhạt
	    public static final Color COLOR_RED = new Color(186, 90, 90);        // Đỏ nhạt
	    public static final Color COLOR_GREEN = new Color(77, 188, 137);     // Xanh lục
	    public static final Color COLOR_LIGHT_CYAN = new Color(70, 130, 180); //XANH DUONG NHAT
	    // Có thể thêm nhiều màu khác nếu cầnnew Color(70, 130, 180)
	}
	public pnlHoaDon() {
		setBackground(Colors.COLOR_LIGHTBLACK);
		setBounds(10, 58, 1479, 697);
		setLayout(null);
		
		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setBorder(new TitledBorder(null, "L\u1ECBch s\u1EED ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung1.setForeground(Color.WHITE);
		pnlKhung1.setBackground(Colors.COLOR_MEDIUM_GRAY);
		pnlKhung1.setBounds(23, 10, 848, 363);
		add(pnlKhung1);
		pnlKhung1.setLayout(null);
		
		JPanel khung = new JPanel();
		khung.setBounds(10, 20, 828, 43);
		pnlKhung1.add(khung);
		khung.setLayout(null);
		
		JLabel lblMaHoaDon = new JLabel("Mã hoá đơn:");
		lblMaHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMaHoaDon.setBounds(10, 10, 76, 13);
		khung.add(lblMaHoaDon);
		
		txtMaHoaDon = new RoundedTextField(10);
		txtMaHoaDon.setBounds(85, 8, 140, 20);
		khung.add(txtMaHoaDon);
		txtMaHoaDon.setColumns(10);
		
		JButton btnTim = new RoundedButton("Tìm");
		btnTim.setBackground(Colors.COLOR_LIGHT_GRAY);
		btnTim.setBounds(235, 7, 67, 21);
		khung.add(btnTim);
		btnTim.addActionListener((ActionEvent e) -> {
                    String maHD = txtMaHoaDon.getText().trim();
                    timKiem(maHD);
                    txtMaHoaDon.setText("");
                });
		txtMaHoaDon.addKeyListener(new KeyListener()  {
	          @Override
	          public void keyTyped(KeyEvent e) {}

	          @Override
	          public void keyPressed(KeyEvent e) {
	              if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	  btnTim.doClick();
	              }
	          }

	          @Override
	          public void keyReleased(KeyEvent e) {}
	      });
		JLabel lblDenNgay = new JLabel("Đến ngày:");
		lblDenNgay.setBounds(520, 10, 60, 13);
		khung.add(lblDenNgay);
		
		dcDenNgay = new JDateChooser();
		dcDenNgay.setBounds(580, 7, 138, 20);
		khung.add(dcDenNgay);
		
		JLabel lblTuNgay = new JLabel("Từ ngày:");
		lblTuNgay.setBounds(312, 10, 60, 13);
		khung.add(lblTuNgay);

		dcTuNgay = new JDateChooser();
		dcTuNgay.setBounds(370, 7, 138, 20);
		khung.add(dcTuNgay);
		
		JScrollPane cptable = new JScrollPane();
		cptable.setBounds(10, 68, 828, 285);
		pnlKhung1.add(cptable);
		
		tblHoaDon = new JTable();
		tblHoaDon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 h\u00F3a \u0111\u01A1n", "Nh\u00E2n vi\u00EAn", "Ng\u00E0y t\u1EA1o", "Kh\u00E1ch h\u00E0ng", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Lo\u1EA1i thanh to\u00E1n", " Ti\u1EC1n s\u1EA3n ph\u1EA9m", "Ti\u1EC1n gi\u1EA3m"
			}
		));
		tblHoaDon.getColumnModel().getColumn(5).setPreferredWidth(90);
		tblHoaDon.getColumnModel().getColumn(6).setPreferredWidth(90);
		cptable.setViewportView(tblHoaDon);
		
		JPanel pnlKhung3 = new JPanel();
		pnlKhung3.setBorder(new TitledBorder(null, "Chi ti\u1EBFt ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung3.setBounds(23, 384, 848, 288);
		add(pnlKhung3);
		pnlKhung3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 27, 828, 251);
		pnlKhung3.add(scrollPane_1);
		
		tblChiTiet = new JTable();
		tblChiTiet.setModel(modalCTHD=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sản phẩm", "Tên sản phẩm" ,"Kích Thước","Giá bán","Số lượng","Tổng tiền"
			}
		));
		tblChiTiet.getColumnModel().getColumn(1).setPreferredWidth(81);
		tblChiTiet.getColumnModel().getColumn(3).setPreferredWidth(64);
		tblChiTiet.getColumnModel().getColumn(4).setPreferredWidth(73);
		scrollPane_1.setViewportView(tblChiTiet);
		
		JButton btnInHoaDon = new RoundedButton("In hoá đơn");
		btnInHoaDon.addActionListener((ActionEvent e) -> {
                    int r = tblHoaDon.getSelectedRow();
                    if(r == -1) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn hoá đơn cần in!");
                    } else {
                        int response = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hoá đơn này không?", "Xác nhận",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        
                        if (response == JOptionPane.YES_OPTION) {
                            try {
                                HoaDon hd = HoaDonDAO.getInstance().selectById(modalHoaDon.getValueAt(r, 0).toString());
                                InHoaDon inHD = new InHoaDon(hd);
                                inHD.print();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Lỗi khi in hoá đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });
		btnInHoaDon.setBackground(new Color(255, 255, 255));
		btnInHoaDon.setIcon(new ImageIcon(pnlHoaDon.class.getResource("/icon/iconInHoaDon.png")));
		btnInHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnInHoaDon.setBounds(1285, 10, 184, 51);
		add(btnInHoaDon);
		
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin ho\u00E1 \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTin.setBounds(935, 88, 522, 505);
		add(pnlThongTin);
		pnlThongTin.setLayout(null);
		
		JLabel lblMaHD = new JLabel("Mã hoá đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaHD.setBounds(34, 24, 133, 22);
		pnlThongTin.add(lblMaHD);
		
		lbltxtMaHD = new JLabel("X");
		lbltxtMaHD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtMaHD.setBounds(251, 28, 207, 18);
		pnlThongTin.add(lbltxtMaHD);
		
		JLabel lblKhachHang = new JLabel("Khách hàng:");
		lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKhachHang.setBounds(34, 108, 133, 30);
		pnlThongTin.add(lblKhachHang);
		
		lbltxtMaKH = new JLabel("X");
		lbltxtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtMaKH.setBounds(251, 116, 207, 22);
		pnlThongTin.add(lbltxtMaKH);
		
		JLabel lblSdt = new JLabel("SDT:");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSdt.setBounds(34, 155, 95, 26);
		pnlThongTin.add(lblSdt);
		 lbltxtSdt = new JLabel("X");
		lbltxtSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtSdt.setBounds(251, 159, 207, 22);
		pnlThongTin.add(lbltxtSdt);
		
		JLabel lblTGTao = new JLabel("TG tạo:");
		lblTGTao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTGTao.setBounds(34, 198, 95, 22);
		pnlThongTin.add(lblTGTao);
		lbltxtTGTao = new JLabel("X");
		lbltxtTGTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtTGTao.setBounds(251, 202, 261, 22);
		pnlThongTin.add(lbltxtTGTao);
		
		JLabel lblLoaiThanhToan = new JLabel("Loại thanh toán:");
		lblLoaiThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoaiThanhToan.setBounds(34, 242, 133, 22);
		pnlThongTin.add(lblLoaiThanhToan);
		
		 lbltxtLoaiThanhToans = new JLabel("X");
		lbltxtLoaiThanhToans.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtLoaiThanhToans.setBounds(251, 242, 207, 22);
		pnlThongTin.add(lbltxtLoaiThanhToans);
		
		lblTienSanPham = new JLabel("Tổng tiền sản phẩm:");
		lblTienSanPham.setBackground(new Color(240, 240, 240));
		lblTienSanPham.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTienSanPham.setBounds(34, 291, 188, 22);
		pnlThongTin.add(lblTienSanPham);
		
		lbltxtTienSanPham = new JLabel("X");
		lbltxtTienSanPham.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtTienSanPham.setBounds(251, 290, 261, 22);
		pnlThongTin.add(lbltxtTienSanPham);
		
		JLabel lblTongTienHoaDon = new JLabel("Tổng tiền HD:");
		lblTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongTienHoaDon.setBounds(34, 399, 156, 43);
		pnlThongTin.add(lblTongTienHoaDon);
		
		lbltxtTongTienHoaDOn = new JLabel("X");
		lbltxtTongTienHoaDOn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbltxtTongTienHoaDOn.setBounds(250, 408, 240, 22);
		pnlThongTin.add(lbltxtTongTienHoaDOn);
		
		JLabel lbltxtNhanVien = new JLabel("Nhân Viên");
		lbltxtNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltxtNhanVien.setBounds(34, 68, 133, 30);
		pnlThongTin.add(lbltxtNhanVien);
		
		lbltxtMaNV = new JLabel("X");
		lbltxtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtMaNV.setBounds(251, 76, 207, 22);
		pnlThongTin.add(lbltxtMaNV);
		
		JLabel lblTienGiam = new JLabel("Tiền giảm:");
		lblTienGiam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTienGiam.setBackground(UIManager.getColor("Button.background"));
		lblTienGiam.setBounds(34, 343, 188, 22);
		pnlThongTin.add(lblTienGiam);
		
		lbltxtTienGiam = new JLabel("X");
		lbltxtTienGiam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltxtTienGiam.setBounds(251, 342, 261, 22);
		pnlThongTin.add(lbltxtTienGiam);
		
		tblHoaDon.addMouseListener(this);
		capNhatBangHoaDonThangNay();
		 
	        
	    JButton btnCapNhat = new RoundedButton("Câp Nhật");
	    btnCapNhat.setBounds(720, 7, 98, 21);
	    khung.add(btnCapNhat);
	    btnCapNhat.addActionListener((ActionEvent e) -> {
                LocalDate d1= convertJDateChooserToLocalDate(dcTuNgay);
                LocalDate d2= convertJDateChooserToLocalDate(dcDenNgay);
                if (d1.isEqual(LocalDate.now()) || d2.isEqual(LocalDate.now())) {
                    capNhatBangHoaDon();
                }else {
                    capNhatHoaDonTheoNgay(d1.atStartOfDay(), d2.atTime(23, 59, 59, 999999999));
                }
                });
	    
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    Date startDate = calendar.getTime();
	    dcTuNgay.setDate(startDate);

	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    Date endDate = calendar.getTime();
	    dcDenNgay.setDate(endDate);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int selectedRow = tblHoaDon.getSelectedRow();
		 lbltxtMaHD.setText(modalHoaDon.getValueAt(selectedRow, 0).toString());
		 lbltxtMaKH.setText(modalHoaDon.getValueAt(selectedRow, 3).toString());
		 lbltxtSdt.setText(modalHoaDon.getValueAt(selectedRow, 4).toString());
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		 lbltxtTGTao.setText(LocalDateTime.parse(modalHoaDon.getValueAt(selectedRow, 2).toString(), formatter).toString());
		 lbltxtMaNV.setText(modalHoaDon.getValueAt(selectedRow, 1).toString());
		 lbltxtTienGiam.setText(fmt.format(Double.parseDouble(modalHoaDon.getValueAt(selectedRow, 7).toString())));
		 lbltxtLoaiThanhToans.setText(PhuongThucThanhToan.valueOf(modalHoaDon.getValueAt(selectedRow, 5).toString())==PhuongThucThanhToan.BANK_TRANSFER?"Chuyển khoản":"Tiền mặt");
		 lbltxtTienSanPham.setText(fmt.format(Double.parseDouble(modalHoaDon.getValueAt(selectedRow, 6).toString())));
		 lbltxtTongTienHoaDOn.setText(fmt.format(Double.parseDouble(modalHoaDon.getValueAt(selectedRow, 6).toString()) - Double.parseDouble(modalHoaDon.getValueAt(selectedRow, 7).toString())));		       
		capNhatBangChiTietHoaDon(modalHoaDon.getValueAt(selectedRow, 0).toString());
	}
	public void capNhatBangHoaDon() {
        modalHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modalHoaDon.setRowCount(0);
        for (HoaDon hd : danhSachHoaDon) {
        	Object[] row =  {hd.getMaHoaDon(), hd.getNhanVien().getMaNhanVien(),hd.getNgayTao(), hd.getKhachHang().getMaKhachHang(), hd.getKhachHang().getDienThoai(), hd.getPhuongThucThanhToan().toString(), hd.tongTienGiam(), hd.tongTienSanPham(), hd.tongTienGiam()};
            modalHoaDon.addRow(row);
        }
    }
	public void capNhatBangHoaDonThangNay() {
        modalHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modalHoaDon.setRowCount(0);

        HoaDonDAO HoaDonDAO = new HoaDonDAO();
        danhSachHoaDon = HoaDonDAO.selectByMonth();
        for (HoaDon hd : danhSachHoaDon) {
        	 Object[] row =  {hd.getMaHoaDon(), hd.getNhanVien().getMaNhanVien(),hd.getNgayTao(), hd.getKhachHang().getMaKhachHang(), hd.getKhachHang().getDienThoai(), hd.getPhuongThucThanhToan().toString(), hd.tongTienSanPham(), hd.tongTienGiam()};
            modalHoaDon.addRow(row);
        }
    }

	public void timKiem(String maHoaDon) {
        boolean found = false;
        for (int i = 0; i < modalHoaDon.getRowCount(); i++) {
            if (modalHoaDon.getValueAt(i, 0).equals(maHoaDon)) {
            	tblHoaDon.setRowSelectionInterval(i, i);
                tblHoaDon.scrollRectToVisible(tblHoaDon.getCellRect(i, 0, true));
                found = true;
                
                MouseEvent clickEvent = new MouseEvent(tblHoaDon, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false);
                for (MouseListener listener : tblHoaDon.getMouseListeners()) {
                    listener.mouseClicked(clickEvent);
                }
                break;
            }
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã: " + maHoaDon, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
	public HoaDon findHD(String maHD, ArrayList<HoaDon> dshd) {
		for (HoaDon hoaDon : dshd) {
			if(hoaDon.getMaHoaDon().equals(maHD)) {
				return hoaDon;
			}
		}
		return null;
	}
	public void capNhatBangChiTietHoaDon(String maHD) {
        modalCTHD = (DefaultTableModel) tblChiTiet.getModel();
        modalCTHD.setRowCount(0);
        for (ChiTietHoaDon ct : findHD(maHD, danhSachHoaDon).getDsCTHD()) {
            Object[] row = { ct.getSanPham().getMaSanPham(), ct.getSanPham().getTenSanPham(), ct.getKt().toString() ,ct.getSanPham().getGiaByKichThuoc(ct.getKt()), ct.getSoLuong(), ct.getSanPham().getGiaByKichThuoc(ct.getKt())* ct.getSoLuong() };
            modalCTHD.addRow(row);
        }
    }
	public static LocalDate convertJDateChooserToLocalDate(JDateChooser jDateChooser) {
	    Date selectedDate = jDateChooser.getDate();
	    if (selectedDate == null) {
	        return LocalDate.now(); 
	    }
	    return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public void capNhatHoaDonTheoNgay(LocalDateTime d1, LocalDateTime d2 ) {
		
		if (modalHoaDon != null) {
	        modalHoaDon.setRowCount(0);

	        HoaDonDAO HoaDonDAO = new HoaDonDAO();
	        danhSachHoaDon = HoaDonDAO.selectByDay(d1,d2);

	        for (HoaDon hd : danhSachHoaDon) {
	        	Object[] row =  {hd.getMaHoaDon(), hd.getNhanVien().getMaNhanVien(),hd.getNgayTao(), hd.getKhachHang().getMaKhachHang(), hd.getKhachHang().getDienThoai(), hd.getPhuongThucThanhToan().toString(), hd.tongTienSanPham(), hd.tongTienGiam()};
	            modalHoaDon.addRow(row);
	        }
	 
	    }
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// Custom bo tròn 4 góc cho JTextField
	public class RoundedTextField extends JTextField {
		private static final long serialVersionUID = 1L;
		private final int arc = 20;

		public RoundedTextField(int columns) {
			super(columns);
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
			super.paintComponent(g2);
			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Colors.COLOR_LIGHT_GRAY);
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
			g2.dispose();
		}
	}
	
	public class RoundedButton extends JButton {
		private static final long serialVersionUID = 1L;
		private final int arc = 20;

		public RoundedButton(String text) {
			super(text);
			setContentAreaFilled(false);
			setFocusPainted(false);
			setBorderPainted(false);
			setForeground(Color.WHITE);
			setBackground(Colors.COLOR_LIGHT_CYAN); // xanh dương nhạt
			setFont(new Font("Segoe UI", Font.PLAIN, 16));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
			super.paintComponent(g2);
			g2.dispose();
		}

		@Override
		protected void paintBorder(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(new Color(100, 100, 100));
			g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
			g2.dispose();
		}
	}
}
