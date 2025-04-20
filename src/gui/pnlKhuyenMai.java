package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import java.awt.Color;

import dao.KhuyenMaiDAO;
import dao.SanPhamDAO;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.SanPham;
import java.awt.HeadlessException;

public class pnlKhuyenMai extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtTenKM;
	private JTextField txtGiamGia;
	private JTextField txtMaTim;
	private JTable tblSanPhamApDung;
	private JTextField txtMaKMTim;
	private JTable tblKhuyenMai;
	private JTextField txtMaKM;
	private DefaultTableModel modalKhuyenMai;
	private DefaultTableModel modalSanPham;
	private JButton btnTimKM;
	private JDateChooser dcNgayBD;
	private JDateChooser dcNgayKT;
	private JTextArea txtAreaMT;
	private JComboBox cboTinhTrangKM;
	private JButton btnThem;
	private final JButton btnLamMoi;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private JComboBox cboTinhTrang;
	
	ArrayList<KhuyenMai> danhSachKhuyenMai;
	ArrayList<SanPham> danhSachSanPham;
	/**
	 * Create the frame.
	 */
	public pnlKhuyenMai() { 	
		setBackground(new Color(186, 90, 90));
		setBounds(10, 74, 1479, 681);
		setLayout(null);
		
		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setBorder(new TitledBorder(null, "Danh s\u00E1ch khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung1.setForeground(Color.WHITE);
		pnlKhung1.setBackground(new Color(186, 90, 90));
		pnlKhung1.setBounds(10, 10, 805, 261);
		add(pnlKhung1);
		pnlKhung1.setLayout(null);
		
		JPanel pnlKhung4 = new JPanel();
		pnlKhung4.setBounds(10, 20, 785, 29);
		pnlKhung1.add(pnlKhung4);
		pnlKhung4.setLayout(null);
		
		JLabel lblMaKM = new JLabel("Nhập mã khuyến mãi:");
		lblMaKM.setBounds(10, 10, 118, 13);
		pnlKhung4.add(lblMaKM);
		
		txtMaKMTim = new JTextField();
		txtMaKMTim.setBounds(138, 7, 137, 19);
		pnlKhung4.add(txtMaKMTim);
		txtMaKMTim.setColumns(10);
		
		btnTimKM = new RoundedButton("Tìm");
		btnTimKM.setBounds(285, 6, 58, 21);
		pnlKhung4.add(btnTimKM);
		
		JLabel lblTinhTrangKM = new JLabel("Tình trạng:");
		lblTinhTrangKM.setBounds(438, 10, 64, 13);
		pnlKhung4.add(lblTinhTrangKM);
		
		JComboBox cboApDungKM = new JComboBox();
		cboApDungKM.setModel(new DefaultComboBoxModel(new String[] {"Đang áp dụng", "Kết thúc", "Tất cả"}));
		cboApDungKM.setBounds(512, 6, 126, 21);
		pnlKhung4.add(cboApDungKM);
		danhSachKhuyenMai = KhuyenMaiDAO.getInstance().selectAll();
		cboApDungKM.addActionListener((ActionEvent e) -> {
                    String selectedLoai = (String) cboApDungKM.getSelectedItem();
                    modalKhuyenMai.setRowCount(0);
                    if(selectedLoai.equals("Tất cả")){
                        capNhatBangKhuyenMai();
                    }else {
                        for (KhuyenMai km : danhSachKhuyenMai) {
                            if(km.isTrangThai() == true && km.getNgayKetThuc().isBefore(LocalDateTime.now()) && selectedLoai.equals("Đang áp dụng")) {
                                Object[] row = { km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTramGiamGia(),km.isTrangThai()?"Đang áp dụng":"Kết thúc",km.getMoTa()};
                                modalKhuyenMai.addRow(row);
                            }else {
                                if(km.isTrangThai() == false && selectedLoai.equals("Kết thúc")) {   //&& km.getNgayKetThuc().isAfter(LocalDateTime.now())
                                    Object[] row = { km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTramGiamGia(),km.isTrangThai()?"Đang áp dụng":"Kết thúc",km.getMoTa()};
                                    modalKhuyenMai.addRow(row);
                                }
                            }
                        }
                        
                    }
                });
		
		JScrollPane cp1 = new JScrollPane();
		cp1.setBounds(10, 49, 785, 205);
		pnlKhung1.add(cp1);
		
		tblKhuyenMai = new JTable();
		tblKhuyenMai.setModel(modalKhuyenMai = new DefaultTableModel(
			new String[] {
				"M\u00E3 Khuy\u1EBFn m\u00E3i", "T\u00EAn khuy\u1EBFn m\u00E3i", "Ng\u00E0y b\u1EAFt \u0111\u1EA7u", "Ng\u00E0y k\u1EBFt th\u00FAc", "Ph\u1EA7n tr\u0103m gi\u1EA3m gi\u00E1", "T\u00ECnh tr\u1EA1ng", "M\u00F4 t\u1EA3"
			}, 0
		));
		tblKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(81);
		tblKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(84);
		tblKhuyenMai.getColumnModel().getColumn(4).setPreferredWidth(101);
		tblKhuyenMai.getColumnModel().getColumn(5).setPreferredWidth(82);
		tblKhuyenMai.getColumnModel().getColumn(6).setPreferredWidth(140);
		cp1.setViewportView(tblKhuyenMai);
		
		JPanel pnlKhung2 = new JPanel();
		pnlKhung2.setBorder(new TitledBorder(null, "S\u1EA3n ph\u1EA9m \u00E1p d\u1EE5ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung2.setBounds(10, 281, 805, 379);
		add(pnlKhung2);
		pnlKhung2.setLayout(null);
		
		JPanel pnlTim = new JPanel();
		pnlTim.setBounds(10, 20, 785, 51);
		pnlKhung2.add(pnlTim);
		pnlTim.setLayout(null);
		
		JLabel lblMaSP = new JLabel("Nhập mã sản phẩm:");
		lblMaSP.setBounds(10, 0, 122, 13);
		pnlTim.add(lblMaSP);
		
		txtMaTim = new JTextField();
		txtMaTim.setBounds(20, 22, 151, 19);
		pnlTim.add(txtMaTim);
		txtMaTim.setColumns(10);
		
		RoundedButton btnTim = new RoundedButton("Tìm");
		btnTim.setBounds(181, 21, 57, 21);
		pnlTim.add(btnTim);
		
		JLabel lblTinhTrang = new JLabel("Tình trạng:");
		lblTinhTrang.setBounds(260, 0, 75, 13);
		pnlTim.add(lblTinhTrang);
		
		cboTinhTrang = new JComboBox();
		cboTinhTrang.setModel(new DefaultComboBoxModel(new String[] {"Đã áp dụng", "Chưa áp dụng","Tất cả"}));
		cboTinhTrang.setBounds(274, 21, 122, 21);
		cboTinhTrang.addActionListener((ActionEvent e) -> {
                    String selectedLoai = (String) cboTinhTrang.getSelectedItem();
                    modalSanPham.setRowCount(0);
                    if( txtMaKM.getText()==null)
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        if(selectedLoai.equals("Tất cả")){
                            capNhatBangSanPham(txtMaKM.getText());
                        }else {
                            KhuyenMai khuyenMai = searchKM(txtMaKM.getText(), danhSachKhuyenMai);
                            danhSachSanPham = SanPhamDAO.getInstance().selectAll();
                            for (SanPham sp : danhSachSanPham) {
                                boolean kt;
                                if(sp.getKhuyenMai()==null || sp.getKhuyenMai().isTrangThai() == false || sp.getKhuyenMai().getNgayKetThuc().isAfter(LocalDateTime.now()))
                                    kt= false;
                                else {
                                    kt=sp.getKhuyenMai().equals(khuyenMai);
                                }
                                if(sp.getLoaiSanPham().isCoPhanLoai()) {
                                    Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.M),sp.getGiaByKichThuoc(KichThuoc.M)*(100-khuyenMai.getPhanTramGiamGia())/100
                                            ,kt};
                                    modalSanPham.addRow(row);
                                }else {
                                    Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.D),sp.getGiaByKichThuoc(KichThuoc.D)*(100-khuyenMai.getPhanTramGiamGia())/100
                                            ,kt};
                                    modalSanPham.addRow(row);
                                }
                            }
                        }
                    }
                });
		pnlTim.add(cboTinhTrang);
		
		JCheckBox chkApDung = new JCheckBox("Áp dụng tất cả");
		chkApDung.setBounds(574, 21, 108, 21);
		pnlTim.add(chkApDung);
		chkApDung.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        boolean selected = chkApDung.isSelected();
		        for (int row = 0; row < tblSanPhamApDung.getRowCount(); row++) {
		            tblSanPhamApDung.setValueAt(selected, row, 4);
		        }
		    }
		});

		JLabel lblLoai = new JLabel("Loại sản phẩm:");
		lblLoai.setBounds(415, 0, 108, 13);
		pnlTim.add(lblLoai);
		
		btnLuu = new RoundedButton("Lưu");
		btnLuu.setBounds(718, 10, 57, 32);
		pnlTim.add(btnLuu);
		btnLuu.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        for (int row = 0; row < tblSanPhamApDung.getRowCount(); row++) {
		            boolean isSelected = (boolean) tblSanPhamApDung.getValueAt(row, 4);
		            SanPham temp = searchSP(tblSanPhamApDung.getValueAt(row, 0).toString(), danhSachSanPham);
		            if (isSelected && temp != null && temp.getKhuyenMai() != null && temp.getKhuyenMai().getMaKhuyenMai().equals(searchKM(txtMaKM.getText(), danhSachKhuyenMai).getMaKhuyenMai()) == true && temp.getKhuyenMai().isTrangThai() ==false) {
		                xuLyDong(row);
		            }
		        }
		        JOptionPane.showMessageDialog(null, "Đã lưu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		    }
		});
		
		JComboBox cboLoai = new JComboBox();
		cboLoai.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Cafe","Bánh ngọt","Khác"}));
		
		cboLoai.setBounds(425, 20, 115, 21);
		pnlTim.add(cboLoai);
		cboLoai.addActionListener((ActionEvent e) -> {
                    String selectedLoai = (String) cboLoai.getSelectedItem();
                    updateTable(selectedLoai);
                });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 785, 209);
		pnlKhung2.add(scrollPane);
		
		tblSanPhamApDung = new JTable();
		tblSanPhamApDung = new JTable();
		tblSanPhamApDung.setModel(modalSanPham = new DefaultTableModel(
		    new Object[][] {},
		    new String[] {
		        "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Giá sau khuyến mãi", "Áp dụng"
		    }
		) {
		    Class[] columnTypes = new Class[] {
		        Object.class, Object.class, Object.class, Object.class, Boolean.class
		    };
		    
		    @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        return columnTypes[columnIndex];
		    }
		});

		tblSanPhamApDung.getColumnModel().getColumn(1).setPreferredWidth(78);
		tblSanPhamApDung.getColumnModel().getColumn(4).setPreferredWidth(102);
		scrollPane.setViewportView(tblSanPhamApDung);
		
		JPanel pnlKhung3 = new JPanel();
		pnlKhung3.setBorder(new TitledBorder(null, "Th\u00F4ng tin khuy\u1EBFn m\u00E3i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhung3.setBounds(845, 10, 599, 650);
		add(pnlKhung3);
		pnlKhung3.setLayout(null);
		
		 btnThem = new RoundedButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setBounds(325, 534, 150, 47);
		pnlKhung3.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin mới từ các trường nhập liệu
		    	KhuyenMaiDAO KhuyenMaiDAO= new KhuyenMaiDAO();
		        String maKhuyenMai = KhuyenMaiDAO.getNextMaKhuyenMai();
		        
		        String tenKhuyenMai = txtTenKM.getText();
		        
		        Double phanTramGiam= Double.parseDouble(txtGiamGia.getText());
		        LocalDateTime ngayBD = doiNgaySangLDT(dcNgayBD) ;
		        LocalDateTime ngayKT = doiNgaySangLDT(dcNgayKT) ;
		        
		        boolean tinhTrang ;
		        String selectedTT = (String) cboTinhTrangKM.getSelectedItem();
		        if(selectedTT.equals("Đang áp dụng"))
		        	tinhTrang=true;
		        else
		        	tinhTrang=false;
		        String moTa=txtAreaMT.getText();
		        KhuyenMai khuyenMai= new KhuyenMai(maKhuyenMai, tenKhuyenMai, ngayBD, ngayKT, phanTramGiam, tinhTrang, moTa);
		        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
		        if ( tenKhuyenMai.isEmpty() || phanTramGiam==null || dcNgayBD ==null|| dcNgayKT == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        } else {
		        	 
		            // Thêm KM vào cơ sở dữ liệu
		            KhuyenMaiDAO KhuyenMaiDAO1 = new KhuyenMaiDAO();
		            System.out.println(khuyenMai.toString());
		            int inserted = KhuyenMaiDAO1.insert(khuyenMai);
		            if (inserted != 0) {
		                JOptionPane.showMessageDialog(null, "Thêm Khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                capNhatBangKhuyenMai(); 
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm Khuyến Mãi thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		
		btnCapNhat = new RoundedButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.setBounds(121, 538, 138, 43);
		pnlKhung3.add(btnCapNhat);
		btnCapNhat.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin mới từ các trường nhập liệu
		    
		        String maKhuyenMai = txtMaKM.getText();		        
		        String tenKhuyenMai = txtTenKM.getText();
		        
		        Double phanTramGiam= Double.parseDouble(txtGiamGia.getText());
		        LocalDateTime ngayBD = doiNgaySangLDT(dcNgayBD) ;
		        LocalDateTime ngayKT = doiNgaySangLDT(dcNgayKT) ;
		        
		        boolean tinhTrang ;
		        String selectedTT = (String) cboTinhTrangKM.getSelectedItem();
		        if(selectedTT.equals("Đang áp dụng"))
		        	tinhTrang=true;
		        else
		        	tinhTrang=false;
		        String moTa=txtAreaMT.getText();
		        KhuyenMai khuyenMai= new KhuyenMai(maKhuyenMai, tenKhuyenMai, ngayBD, ngayKT, phanTramGiam, tinhTrang, moTa);
		        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
		        if ( tenKhuyenMai.isEmpty() || phanTramGiam==null || dcNgayBD ==null|| dcNgayKT == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		            return;
		        } else {
		        	 
		            // Thêm KM vào cơ sở dữ liệu
		            KhuyenMaiDAO KhuyenMaiDAO1 = new KhuyenMaiDAO();
		            System.out.println(khuyenMai.toString());
		            int inserted = KhuyenMaiDAO1.update(khuyenMai);
		            if (inserted != 0) {
		                JOptionPane.showMessageDialog(null, "Cập nhật Khuyến mãi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                capNhatBangKhuyenMai(); 
		            } else {
		                JOptionPane.showMessageDialog(null, "Cập nhật Khuyến Mãi thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		txtAreaMT = new JTextArea();
		txtAreaMT.setBounds(175, 381, 377, 87);
		pnlKhung3.add(txtAreaMT);
		
		JLabel lblMT = new JLabel("Mô tả:");
		lblMT.setBounds(100, 382, 49, 19);
		pnlKhung3.add(lblMT);
		lblMT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cboTinhTrangKM = new JComboBox();
		cboTinhTrangKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboTinhTrangKM.setBounds(175, 328, 377, 28);
		pnlKhung3.add(cboTinhTrangKM);
		cboTinhTrangKM.setModel(new DefaultComboBoxModel(new String[] {"Đang áp dụng", "Kết thúc"}));
		
		JLabel lblTinhTrang1 = new JLabel("Tình trạng:");
		lblTinhTrang1.setBounds(80, 331, 76, 19);
		pnlKhung3.add(lblTinhTrang1);
		lblTinhTrang1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtGiamGia = new JTextField();
		txtGiamGia.setBounds(175, 282, 377, 28);
		pnlKhung3.add(txtGiamGia);
		txtGiamGia.setColumns(10);
		
		JLabel lblGiamGia = new JLabel("Phần trăm giảm giá:");
		lblGiamGia.setBounds(16, 284, 138, 19);
		pnlKhung3.add(lblGiamGia);
		lblGiamGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNgayKT = new JLabel("Ngày kết thúc:");
		lblNgayKT.setBounds(50, 236, 104, 19);
		pnlKhung3.add(lblNgayKT);
		lblNgayKT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setBounds(57, 172, 97, 19);
		pnlKhung3.add(lblNgayBatDau);
		lblNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtTenKM = new JTextField();
		txtTenKM.setBounds(175, 104, 377, 28);
		pnlKhung3.add(txtTenKM);
		txtTenKM.setColumns(10);
		
		JLabel lblTenKM = new JLabel("Tên khuyến mãi:");
		lblTenKM.setBounds(50, 106, 114, 19);
		pnlKhung3.add(lblTenKM);
		lblTenKM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnLamMoi = new RoundedButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBounds(438, 478, 114, 21);
		pnlKhung3.add(btnLamMoi);
		
		dcNgayKT = new JDateChooser();
		dcNgayKT.setBounds(175, 227, 378, 28);
		pnlKhung3.add(dcNgayKT);
		
		dcNgayBD = new JDateChooser();
		dcNgayBD.setBounds(174, 163, 378, 28);
		pnlKhung3.add(dcNgayBD);
		
		JLabel lblMaKhuyenMai = new JLabel("Mã khuyến mãi:");
		lblMaKhuyenMai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaKhuyenMai.setBounds(50, 49, 114, 19);
		pnlKhung3.add(lblMaKhuyenMai);
		
		txtMaKM = new JTextField();
		txtMaKM.setEditable(false);
		txtMaKM.setColumns(10);
		txtMaKM.setBounds(175, 47, 377, 28);
		pnlKhung3.add(txtMaKM);
		btnCapNhat.addActionListener(this);
		tblKhuyenMai.addMouseListener(this);
		btnTimKM.addActionListener(this);
		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		
		//loadDuLieu
		capNhatBangKhuyenMai();
		danhSachSanPham = SanPhamDAO.getInstance().selectAll();
	}
	public static LocalDateTime doiNgaySangLDT(JDateChooser jDateChooser) {
        java.util.Date date = jDateChooser.getDate();
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
	public KhuyenMai searchKM(String maKM, ArrayList<KhuyenMai> dsKM) {
		for (KhuyenMai khuyenMai : dsKM) {
			if(khuyenMai.getMaKhuyenMai().equals(maKM)) {
				return khuyenMai;
			}
		}
		return null;
	}
	public SanPham searchSP(String maSP, ArrayList<SanPham> dsSP) {
		for (SanPham sanPham : dsSP) {
			if(sanPham.getMaSanPham().equals(maSP)) {
				return sanPham;
			}
		}
		return null;
	}
	public void capNhatBangKhuyenMai() {
        modalKhuyenMai = (DefaultTableModel) tblKhuyenMai.getModel();
        modalKhuyenMai.setRowCount(0);
        danhSachKhuyenMai = KhuyenMaiDAO.getInstance().selectAll();
        for (KhuyenMai km : danhSachKhuyenMai) {
            Object[] row = { km.getMaKhuyenMai(), km.getTenKhuyenMai(),km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTramGiamGia(),km.isTrangThai()?"Đang áp dụng":"Kết thúc",km.getMoTa()};
            modalKhuyenMai.addRow(row);
        }
    }
	public void capNhatBangSanPham(String maKM) {
        modalSanPham = (DefaultTableModel) tblSanPhamApDung.getModel();
        modalSanPham.setRowCount(0);
        KhuyenMai khuyenMai = searchKM(maKM, danhSachKhuyenMai);
        for (SanPham sp : danhSachSanPham) {
        	boolean kt;
        	if(sp.getKhuyenMai()==null)
        		kt= false;
        	else {
        		kt=sp.getKhuyenMai().equals(khuyenMai);
        	}
            if(sp.getLoaiSanPham().isCoPhanLoai()) {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.M),sp.getGiaByKichThuoc(KichThuoc.M)*(100-khuyenMai.getPhanTramGiamGia())/100 
            			,kt};
                modalSanPham.addRow(row);
            }else {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.D),sp.getGiaByKichThuoc(KichThuoc.D)*(100-khuyenMai.getPhanTramGiamGia())/100 
            			,kt};
                modalSanPham.addRow(row);
            }
        }
    }
	public void clear() {
		txtTenKM.setText("");
		dcNgayBD.setDate(null);
		dcNgayKT.setDate(null);
		txtGiamGia.setText("");
		txtAreaMT.setText("");
	}
	
	private KhuyenMai getKMFromField() {
		KhuyenMai km = null;
		
		String maKM = generateMaKM();
		
		
		String tenKM = txtTenKM.getText();
		if(tenKM.matches("[\\d\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\=\\+\\:\\;\\'\"\\<\\>\\.\\,\\?]") || tenKM.isBlank()) {
			JOptionPane.showMessageDialog(null, "Tên khuyến mãi không chứa kí tự đặc biệt và không được để trống!");
			return null;
		}
		
		//KT ngayBD, KT
        LocalDateTime ngayHienTai = LocalDateTime.now();      
        LocalDateTime ngayBD = dcNgayBD.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime ngayKT = dcNgayKT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (!ngayKT.isAfter(ngayBD) && !ngayBD.isAfter(ngayHienTai)) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc phải sau ngày bắt đầu!");
            return null;
        }
        
        
        //KT giamGia
        double giamGia = 0;
        try {
        	giamGia = Double.parseDouble(txtGiamGia.getText());
        	if(giamGia <= 0 || giamGia >= 100) {
        		JOptionPane.showMessageDialog(null, "Phần trăm giảm giá lớn hơn 0 và nhỏ hơn 100!");
        		return null;
        	}
		} catch (HeadlessException | NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Phần trăm giảm gias phải là số!");
			txtGiamGia.requestFocus();
			return null;
		}
        
        // Tinh trang
        boolean tinhTrang = true;
        if(cboTinhTrangKM.getSelectedItem().equals("Kết thúc")) {
        	tinhTrang = false;
        }
        
        //KT Mota
        String moTa = txtAreaMT.getText();
		if(moTa.matches("[\\d\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\=\\+\\:\\;\\'\"\\<\\>\\.\\,\\?]") || moTa.isBlank()) {
			JOptionPane.showMessageDialog(null, "Mô tả không chứa kí tự đặc biệt và không được để trống!");
			return null;
		}
		
		try {
			km = new KhuyenMai(maKM, tenKM, ngayBD, ngayKT, giamGia, tinhTrang, moTa);
			return km;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
			txtTenKM.requestFocus();
			return null;
		}
		
	}
	
	private int maKhuyenMaiCount = 2;
	
	private String generateMaKM() {
		
		maKhuyenMaiCount++;
        String maSoThuTu = String.format("%03d", maKhuyenMaiCount);
        String maKhuyenMai = "KM" + maSoThuTu;
        return maKhuyenMai;
	}
	
	public void clearTable() {
		while (modalKhuyenMai.getRowCount() > 0)
			modalKhuyenMai.removeRow(0);
			
	}
	
//	public void timKiem(String maKM) {
//	    // Kiểm tra nếu model đã được khởi tạo
//	    if (dataModel != null) {
//	        // Xóa dữ liệu hiện tại trong bảng
//	        dataModel.setRowCount(0);
//
//	        // Lấy danh sách nhân viên từ cơ sở dữ liệu dựa trên loại chức vụ được chọn
//
//	        KhuyenMai km = khuyenMaiDAO.selectById(maKM);
//
//	 
//	            Object[] row =  { km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getPhanTramGiamGia(), km.getMoTa()};
//	            dataModel.addRow(row);
//	 
//	    }
//	}
//	
//	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o =e.getSource();
		int pos = tblKhuyenMai.getSelectedRow();
		if(o.equals(tblKhuyenMai)) {
			txtMaKM.setText((String) modalKhuyenMai.getValueAt(pos, 0));
			txtTenKM.setText((String) modalKhuyenMai.getValueAt(pos, 1));
			String ngayBD = modalKhuyenMai.getValueAt(pos, 2).toString();
		    try {
		        java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayBD);
		        dcNgayBD.setDate(date1);
		    } catch (ParseException ex) {
		        ex.printStackTrace();
		    }
			
		    String ngayKT = modalKhuyenMai.getValueAt(pos, 3).toString();
		    try {
		        java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayKT);
		        dcNgayKT.setDate(date2);
		    } catch (ParseException ex) {
		        ex.printStackTrace();
		    }
		    
			txtGiamGia.setText((String.format("%.1f", (double) modalKhuyenMai.getValueAt(pos, 4))));
			cboTinhTrangKM.setSelectedItem(modalKhuyenMai.getValueAt(pos, 5).toString());
			txtAreaMT.setText((String) modalKhuyenMai.getValueAt(pos, 6));
			capNhatBangSanPham(modalKhuyenMai.getValueAt(pos, 0).toString());
		}
	}
	public void updateTable(String loai) {
	    if (modalSanPham != null) {
	        modalSanPham.setRowCount(0);
	        ArrayList<SanPham> dsLoai = new ArrayList<SanPham>();
	        for (SanPham sanPham : dsLoai) {
				if(sanPham.getLoaiSanPham().getTenloai().equals(loai)) {
					dsLoai.add(sanPham);
				}
			}
	        KhuyenMai khuyenMai = searchKM(txtMaKM.getText(), danhSachKhuyenMai);
	        for (SanPham sp : danhSachSanPham) {
	        	boolean kt;
	        	if(sp.getKhuyenMai()==null)
	        		kt= false;
	        	else {
	        		kt=sp.getKhuyenMai().equals(khuyenMai);
	        	}
	            if(sp.getLoaiSanPham().isCoPhanLoai()) {
	            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.M),sp.getGiaByKichThuoc(KichThuoc.M)*(100-khuyenMai.getPhanTramGiamGia())/100 
	            			,kt};
	                modalSanPham.addRow(row);
	            }else {
	            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.getGiaByKichThuoc(KichThuoc.D),sp.getGiaByKichThuoc(KichThuoc.D)*(100-khuyenMai.getPhanTramGiamGia())/100 
	            			,kt};
	                modalSanPham.addRow(row);
	            }
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
		Object o = e.getSource();
		KhuyenMai temp = null;
		int pos = -1;
		
//		if(o.equals(btnThem)) {
//			temp = getKMFromField();
//			if(temp == null) return;
//			if(khuyenMaiDAO.insert(temp) != 0) {
//				JOptionPane.showMessageDialog(null, "Thêm thành công!");
//                clear();
//                dataModel.addRow(temp.getObjectTable());
//			}
//			else {
//                JOptionPane.showMessageDialog(null, "Lỗi: Trùng mã hoặc kết nối cơ sở dữ liệu thất bại!");
//            }
//		}
//		
//		if(o.equals(btnLamMoi)) {
//			clear();
//		}
//		
//		if(o.equals(btnCapNhat)) {
//			pos = tblKhuyenMai.getSelectedRow();
//			if (pos == -1) {
//				JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào để thực hiện cập nhật!");
//				return;
//			}
//			
//			temp = getKMFromField();
//			if (temp == null)
//				return;
//			
//			if (khuyenMaiDAO.update(temp) != 0) {
//				dataModel.removeRow(pos);
//				dataModel.insertRow(pos, temp.getObjectTable());
//				clear();
//			}else {
//				JOptionPane.showMessageDialog(null, "Lỗi: Cập nhật thất bại!");
//				txtTenKM.requestFocus();
//			}
//			return;
//		}
//		
//		if(o.equals(btnTimKM)) {
//			String maKM = txtMaTim.getText().trim();
//			if(!maKM.isEmpty()) {
//				KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
//				KhuyenMai km = khuyenMaiDAO.selectById(maKM);
//				if(km != null) {
//					timKiem(maKM);
//					txtMaKM.setText("");
//			
//				} 
//			} else JOptionPane.showMessageDialog(null, "Không tìm thấy khuyến mãi với mã đã nhập!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//		}
//		
		
		
	}
	private void xuLyDong(int rowIndex) {
	    String maSanPham = tblSanPhamApDung.getValueAt(rowIndex, 0).toString(); 
	    SanPhamDAO.getInstance().updateKM(maSanPham,txtMaKM.getText());
	    SanPham sp = searchSP(tblSanPhamApDung.getValueAt(rowIndex, 0).toString(), danhSachSanPham);
	    
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
