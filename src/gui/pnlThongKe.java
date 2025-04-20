package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controler.bienStatic;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class pnlThongKe extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable tblSanPhamBanChay;
	private JFrame frmTrangChu;
  	private JLabel lblDoanhThuHomNay;
	private JLabel lblSoHoaDonHomNay;
	private LocalDate dauThang;
	private LocalDate cuoiThang;
	private LocalDate dauTuan,cuoiTuan;
	private DefaultTableModel dataBanChay;
	private LocalDate ngayDau;
	private LocalDate ngayCuoi;
	private JDateChooser dateDau;
	private JDateChooser dateCuoi;
	private JLabel lblTuNgay;
	private JLabel lblToiNgay;
	private JLabel lbltxtDoanhThuTuyChinh;
	private JLabel lbltxtSoHoaDonTuyChinh;
	private JLabel lbltxtSoSanPhamTuyChinh;
	/**
	 * Create the frame.
	 */
	public pnlThongKe(JFrame frmTrangChu) {
		this.frmTrangChu = frmTrangChu;
		setBackground(new Color(186, 90, 90));
		setBounds(10, 58, 1491, 697);
		setLayout(null);
		
		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setForeground(Color.WHITE);
		pnlKhung1.setBackground(new Color(186, 90, 90));
		pnlKhung1.setBounds(10, 64, 1471, 258);
		add(pnlKhung1);
		pnlKhung1.setLayout(null);
		
		JPanel pnlTuanNay = new JPanel();
		pnlTuanNay.setBackground(new Color(122, 159, 149));
		pnlTuanNay.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(64, 128, 128), null, null, null));
		pnlTuanNay.setBounds(541, 0, 412, 258);
		pnlKhung1.add(pnlTuanNay);
		pnlTuanNay.setLayout(null);
		
		JLabel lblTuanNay = new JLabel("Tuần này");
		lblTuanNay.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTuanNay.setBounds(125, 10, 164, 51);
		pnlTuanNay.add(lblTuanNay);
		
		JLabel lblDoanhThuTuanNay = new JLabel("500.000.000đ");
		lblDoanhThuTuanNay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDoanhThuTuanNay.setBounds(112, 71, 186, 65);
		pnlTuanNay.add(lblDoanhThuTuanNay);
		
		JLabel lblSoHoaDonTuanNay = new JLabel("Số hóa đơn:");
		lblSoHoaDonTuanNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoHoaDonTuanNay.setBounds(39, 179, 107, 19);
		pnlTuanNay.add(lblSoHoaDonTuanNay);
		
		JLabel lbltxtSoHoaDonTuanNay = new JLabel("10");
		lbltxtSoHoaDonTuanNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoHoaDonTuanNay.setBounds(187, 179, 100, 19);
		pnlTuanNay.add(lbltxtSoHoaDonTuanNay);
		
		JLabel lblSoSanPhanTuanNay = new JLabel("Số sản phẩm:");
		lblSoSanPhanTuanNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoSanPhanTuanNay.setBounds(39, 208, 107, 19);
		pnlTuanNay.add(lblSoSanPhanTuanNay);
		
		JLabel lbltxtSoSanPhamTuanNay = new JLabel("10");
		lbltxtSoSanPhamTuanNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoSanPhamTuanNay.setBounds(187, 208, 100, 19);
		pnlTuanNay.add(lbltxtSoSanPhamTuanNay);
		
		JPanel pnlHomNay = new JPanel();
		pnlHomNay.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(64, 128, 128), null, null, null));
		pnlHomNay.setBounds(42, 0, 412, 258);
		pnlKhung1.add(pnlHomNay);
		pnlHomNay.setLayout(null);
		
		JLabel lblHomNay = new JLabel("Hôm nay");
		lblHomNay.setBounds(130, 10, 164, 51);
		lblHomNay.setFont(new Font("Tahoma", Font.BOLD, 35));
		pnlHomNay.add(lblHomNay);
		
		lblDoanhThuHomNay = new JLabel("3.130.000đ");
		lblDoanhThuHomNay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlHomNay.setBackground(new Color(122, 159, 149));
		lblDoanhThuHomNay.setBounds(118, 73, 186, 65);
		pnlHomNay.add(lblDoanhThuHomNay);
		
		lblSoHoaDonHomNay = new JLabel("Số hóa đơn:");
		lblSoHoaDonHomNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoHoaDonHomNay.setBounds(27, 177, 107, 19);
		pnlHomNay.add(lblSoHoaDonHomNay);
		
		JLabel lbltxtSoHoaDonHomNay = new JLabel("62");
		lbltxtSoHoaDonHomNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoHoaDonHomNay.setBounds(175, 177, 100, 19);
		pnlHomNay.add(lbltxtSoHoaDonHomNay);
		
		JLabel lblSoSanPham = new JLabel("Số sản phẩm:");
		lblSoSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoSanPham.setBounds(27, 206, 107, 19);
		pnlHomNay.add(lblSoSanPham);
		
		JLabel lbltxtSoSanPhamHomNay = new JLabel("110");
		lbltxtSoSanPhamHomNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoSanPhamHomNay.setBounds(175, 206, 100, 19);
		pnlHomNay.add(lbltxtSoSanPhamHomNay);
		
		JPanel pnlThangNay = new JPanel();
		pnlThangNay.setBackground(new Color(122, 159, 149));
		pnlThangNay.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThangNay.setBounds(1033, 0, 412, 258);
		pnlKhung1.add(pnlThangNay);
		pnlThangNay.setLayout(null);
		
		JLabel lblThangNay = new JLabel("Tháng này");
		lblThangNay.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblThangNay.setBounds(131, 10, 209, 51);
		pnlThangNay.add(lblThangNay);
		
		JLabel lblDoanhThuThangNay = new JLabel("15.120.000đ");
		lblDoanhThuThangNay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDoanhThuThangNay.setBounds(131, 71, 186, 65);
		pnlThangNay.add(lblDoanhThuThangNay);
		
		JLabel lblSoHoaDonThangNay = new JLabel("Số hóa đơn:");
		lblSoHoaDonThangNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoHoaDonThangNay.setBounds(30, 172, 107, 19);
		pnlThangNay.add(lblSoHoaDonThangNay);
		
		JLabel lbltxtSoHoaDonThangNay = new JLabel("193");
		lbltxtSoHoaDonThangNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoHoaDonThangNay.setBounds(178, 172, 100, 19);
		pnlThangNay.add(lbltxtSoHoaDonThangNay);
		
		JLabel lblSoSanPhamThangNay = new JLabel("Số sản phẩm:");
		lblSoSanPhamThangNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoSanPhamThangNay.setBounds(30, 201, 107, 19);
		pnlThangNay.add(lblSoSanPhamThangNay);
		
		JLabel lbltxtSoSanPhamThangNay = new JLabel("400");
		lbltxtSoSanPhamThangNay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoSanPhamThangNay.setBounds(178, 201, 100, 19);
		pnlThangNay.add(lbltxtSoSanPhamThangNay);
		
		RoundedButton btnThongKeTheoCa = new RoundedButton("Thống kê theo ca");
		btnThongKeTheoCa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgThongKeTheoCa dlgThongKe = new dlgThongKeTheoCa(frmTrangChu);
				dlgThongKe.setLocationRelativeTo(frmTrangChu);
				dlgThongKe.setVisible(true);
			}
		});
		btnThongKeTheoCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThongKeTheoCa.setBounds(1292, 10, 171, 33);
		add(btnThongKeTheoCa);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "T\u00F9y ch\u1EC9nh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 331, 1471, 356);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Từ ngày:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(37, 44, 90, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Đến ngày:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(37, 142, 135, 25);
		panel_2.add(lblNewLabel_1_1);
		
		RoundedButton btnThongKe = new RoundedButton("Thống kê");
		btnThongKe.setBounds(201, 260, 128, 39);
		panel_2.add(btnThongKe);
		btnThongKe.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	ngayDau = convertJDateChooserToLocalDate(dateDau);
    			ngayCuoi = convertJDateChooserToLocalDate(dateCuoi);
    			lblTuNgay.setText(ngayDau.toString());
    			lblToiNgay.setText(ngayCuoi.toString());
    			capNhatThongKe(ngayDau.atStartOfDay(), ngayCuoi.atTime(23, 59, 59, 999999999), lbltxtDoanhThuTuyChinh, lbltxtSoHoaDonTuyChinh, lbltxtSoSanPhamTuyChinh);
    	    	 
    	    
    	    }
    	});
		
		JPanel pnlKhungTuyChinh = new JPanel();
		pnlKhungTuyChinh.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(64, 128, 128), null, null, null));
		pnlKhungTuyChinh.setBounds(404, 22, 1077, 332);
		panel_2.add(pnlKhungTuyChinh);
		pnlKhungTuyChinh.setLayout(null);
		
		JPanel pnlKhung2 = new JPanel();
		pnlKhung2.setLayout(null);
		pnlKhung2.setBorder(new TitledBorder(null, "S\u1EA3n ph\u1EA9m b\u00E1n ch\u1EA1y", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung2.setBounds(409, 10, 642, 332);
		pnlKhungTuyChinh.add(pnlKhung2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 622, 284);
		pnlKhung2.add(scrollPane);
		
		tblSanPhamBanChay = new JTable();
		tblSanPhamBanChay.setModel(dataBanChay= new DefaultTableModel(
			new Object[][]{
				
			},
			new String[] {
				"X\u1EBFp h\u1EA1ng", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "Tr\u1EA1ng th\u00E1i", "M\u00F4 t\u1EA3"
			}
		));
		tblSanPhamBanChay.getColumnModel().getColumn(2).setPreferredWidth(107);
		tblSanPhamBanChay.getColumnModel().getColumn(4).setPreferredWidth(169);
		scrollPane.setViewportView(tblSanPhamBanChay);
		
		JPanel pnlTuyChinh = new JPanel();
		pnlTuyChinh.setBackground(new Color(122, 159, 149));
		pnlTuyChinh.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(64, 128, 128), null, null, null));
		pnlTuyChinh.setBounds(10, 10, 389, 312);
		pnlKhungTuyChinh.add(pnlTuyChinh);
		pnlTuyChinh.setLayout(null);
		pnlTuyChinh.setVisible(false);
		
		lblTuNgay = new JLabel("29/04/2025");
		lblTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTuNgay.setBounds(22, 10, 146, 51);
		pnlTuyChinh.add(lblTuNgay);
		
		lblToiNgay = new JLabel("30/04/2025");
		lblToiNgay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblToiNgay.setBounds(204, 10, 172, 51);
		pnlTuyChinh.add(lblToiNgay);
		
		JLabel lblTu = new JLabel("-");
		lblTu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTu.setBounds(173, 10, 21, 51);
		pnlTuyChinh.add(lblTu);
		
		lbltxtDoanhThuTuyChinh = new JLabel("");
		lbltxtDoanhThuTuyChinh.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbltxtDoanhThuTuyChinh.setBounds(101, 87, 186, 65);
		pnlTuyChinh.add(lbltxtDoanhThuTuyChinh);
		
		JLabel lblSoHoaDonTuyChinh = new JLabel("Số hóa đơn:");
		lblSoHoaDonTuyChinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoHoaDonTuyChinh.setBounds(10, 242, 107, 19);
		pnlTuyChinh.add(lblSoHoaDonTuyChinh);
		
		lbltxtSoHoaDonTuyChinh = new JLabel("10");
		lbltxtSoHoaDonTuyChinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoHoaDonTuyChinh.setBounds(158, 242, 100, 19);
		pnlTuyChinh.add(lbltxtSoHoaDonTuyChinh);
		
		JLabel lblSoSanPhamTuyChinh = new JLabel("Số sản phẩm:");
		lblSoSanPhamTuyChinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoSanPhamTuyChinh.setBounds(10, 271, 107, 19);
		pnlTuyChinh.add(lblSoSanPhamTuyChinh);
		
		 lbltxtSoSanPhamTuyChinh = new JLabel("10");
		lbltxtSoSanPhamTuyChinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbltxtSoSanPhamTuyChinh.setBounds(158, 271, 100, 19);
		pnlTuyChinh.add(lbltxtSoSanPhamTuyChinh);
		
		JPanel pnlNull = new JPanel();
		pnlNull.setLayout(null);
		pnlNull.setBackground(new Color(122, 159, 149));
		pnlNull.setBounds(10, 10, 389, 312);
		pnlKhungTuyChinh.add(pnlNull);
		
		JLabel lblNULL = new JLabel("Vui lòng chọn thông số tùy chỉnh!");
		lblNULL.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNULL.setBounds(30, 87, 354, 50);
		pnlNull.add(lblNULL);
		
		dateDau = new JDateChooser();
		dateDau.setBounds(47, 88, 296, 25);
		panel_2.add(dateDau);
		
		dateCuoi = new JDateChooser();
		dateCuoi.setBounds(47, 179, 296, 25);
		panel_2.add(dateCuoi);
		
		JLabel lblHuongDan = new JLabel("(*) Nhấn vào để xem chi tiết");
		lblHuongDan.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblHuongDan.setBounds(10, 35, 273, 21);
		add(lblHuongDan);
//		capNhatBangBanChay();
//		//doanh thu hôm nay
//		capNhatThongKe(LocalDate.now(), LocalDate.now(),lblDoanhThuHomNay,lbltxtSoHoaDonHomNay,lbltxtSoSanPhamHomNay);
//		
//		//doanh thu tháng này
//		dauThang = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
//		cuoiThang = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
//		capNhatThongKe(dauThang, cuoiThang, lblDoanhThuThangNay, lbltxtSoHoaDonThangNay,lbltxtSoSanPhamThangNay);
//		//doanh thu Tuần này
//		dauTuan = LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
//		cuoiTuan = LocalDate.now().with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
//		capNhatThongKe(dauTuan, cuoiTuan, lblDoanhThuTuanNay, lbltxtSoHoaDonTuanNay,lbltxtSoSanPhamTuanNay);
		RoundedButton btnReset = new RoundedButton("");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		            JWindow loadingWindow = bienStatic.createLoadingWindow("Đang thống kê...");
		            loadingWindow.setVisible(true);
		            loadingWindow.setAlwaysOnTop(true);
		            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                    @Override
	                    protected Void doInBackground() throws Exception {
	        				capNhatThongKe(LocalDate.now().atStartOfDay(), LocalDateTime.now(),lblDoanhThuHomNay,lbltxtSoHoaDonHomNay,lbltxtSoSanPhamHomNay);
	        				dauThang = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
	        				cuoiThang = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
	        				capNhatThongKe(dauThang.atStartOfDay(), cuoiThang.atTime(23, 59, 59, 999999999), lblDoanhThuThangNay, lbltxtSoHoaDonThangNay,lbltxtSoSanPhamThangNay);
	        				capNhatThongKe(LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).atStartOfDay(),LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).atTime(23, 59, 59, 999999999), lblDoanhThuTuanNay, lbltxtSoHoaDonTuanNay,lbltxtSoSanPhamTuanNay);
	                        return null;
	                    }

	                    @Override
	                    protected void done() {
	                    	loadingWindow.dispose();
	                    }
	                };
	                worker.execute();		        
					}
		});
		pnlHomNay.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
				dlgThongKe dlgThongKe1 = new dlgThongKe(frmTrangChu,LocalDate.now(),LocalDate.now(),lbltxtSoSanPhamHomNay.getText(),lblDoanhThuHomNay.getText());
				dlgThongKe1.setLocationRelativeTo(frmTrangChu);
				dlgThongKe1.setVisible(true);
            }
		});
		pnlTuanNay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlgThongKe dlgThongKe2 = new dlgThongKe(frmTrangChu,LocalDate.now().with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)),LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)),lbltxtSoSanPhamTuanNay.getText(),lblDoanhThuTuanNay.getText());
				dlgThongKe2.setLocationRelativeTo(frmTrangChu);			
				dlgThongKe2.setVisible(true);
			}
		});
		pnlThangNay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlgThongKe dlgThongKe3 = new dlgThongKe(frmTrangChu,dauThang,cuoiThang,lbltxtSoSanPhamThangNay.getText(),lblDoanhThuThangNay.getText());
				dlgThongKe3.setLocationRelativeTo(frmTrangChu);
				dlgThongKe3.setVisible(true);
			}
		});
		pnlTuyChinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlgThongKe dlgThongKe4 = new dlgThongKe(frmTrangChu,ngayDau,ngayCuoi,lbltxtSoSanPhamTuyChinh.getText(),lbltxtDoanhThuTuyChinh.getText());
				dlgThongKe4.setLocationRelativeTo(frmTrangChu);
				dlgThongKe4.setVisible(true);
			}
		});
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlTuyChinh.setVisible(true);
			}
		});
		btnReset.setToolTipText("Nhấn vào để cập nhật thống kê");
		btnReset.doClick();
		btnReset.setOpaque(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
		btnReset.setIcon(new ImageIcon(pnlThongKe.class.getResource("/icon/iconReset.png")));
		btnReset.setBounds(1197, 10, 68, 44);
		add(btnReset);
		btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                btnReset.setContentAreaFilled(true);
//                btnReset.setBackground(new Color(255, 167, 38)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnReset.setContentAreaFilled(false);
            }
        });
		
	}
	public static LocalDate convertJDateChooserToLocalDate(JDateChooser jDateChooser) {
	    Date selectedDate = jDateChooser.getDate();
	    
	    if (selectedDate == null) {

	        return LocalDate.now(); 
	    }
	    
	    return selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public void capNhatThongKe(LocalDateTime d1, LocalDateTime d2,JLabel dt, JLabel shd,JLabel soSP) {
		NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		 HoaDonDAO HoaDonDAO = new HoaDonDAO();
		 int soHoaDon=0;
		 double tongTien=0;
		 int sl=0;
	     ArrayList<HoaDon> danhSachHoaDon = HoaDonDAO.selectByDay(d1,d2);
	     for (HoaDon hd : danhSachHoaDon) {
	    	 soHoaDon++;
	         for (ChiTietHoaDon ct : hd.getDsCTHD() ) {
	        	 sl++;
	        	 tongTien+= ct.getGiaBan() * ct.getSoLuong() ;
	         }
	     }
	     dt.setText(fm.format(tongTien));
	     shd.setText(String.valueOf(soHoaDon));
	     soSP.setText(String.valueOf(sl));
	}
	public void capNhatBangBanChay() {
		 dataBanChay.setRowCount(0);
	        
	        SanPhamDAO sanPhamDAO = new SanPhamDAO();
	        ArrayList<Object[]> danhSachSanPhamTK = sanPhamDAO.danhSachSPBanChay();

	        for (Object[] ds : danhSachSanPhamTK) {
	            dataBanChay.addRow(ds);
	        }
	}
//	public static LocalDate getStartOfWeek(LocalDate date) {
//        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//    }
//
//    public static LocalDate getEndOfWeek(LocalDate date) {
//        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// Custom bo tròn 4 góc cho JTextField
				public class RoundedTextField extends JTextField {
					private static final long serialVersionUID = 1L;
					private int arc = 20;

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
						g2.setColor(Color.GRAY);
						g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
						g2.dispose();
					}
				}
				
				public class RoundedButton extends JButton {
					private static final long serialVersionUID = 1L;
					private int arc = 20;

					public RoundedButton(String text) {
						super(text);
						setContentAreaFilled(false);
						setFocusPainted(false);
						setBorderPainted(false);
						setForeground(Color.WHITE);
						setBackground(new Color(70, 130, 180)); // xanh dương nhạt
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
