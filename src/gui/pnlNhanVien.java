package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class pnlNhanVien extends JPanel implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private final JTable tblNhanVien;
	private JTextField txtMaNhanVienTim;
	private JTextField txtMaNhanVien;
	private JTextField txtTenNhanVien;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtNgayTuyenDung;
	private JFrame frmTrangChu;
	
	private DefaultTableModel model;
	private DefaultTableModel dataModel;
	private JRadioButton radNu;
	private JRadioButton radNam;
	private JDateChooser dcNgaySinh;
	private JComboBox cboChucVuLoc;
	private JComboBox cboChucVu;
	private String filePath;
	/**
	 * Create the frame.
	 */
	public pnlNhanVien(JFrame frmTrangChu) {
		this.frmTrangChu = frmTrangChu;
		setBackground(new Color(186, 90, 90));
		setBounds(10, 58, 1491, 697);
		setLayout(null);
		
		JPanel pnlKhungTT = new JPanel();
		pnlKhungTT.setBorder(new TitledBorder(null, "Th\u00F4ng tin nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlKhungTT.setForeground(Color.WHITE);
		pnlKhungTT.setBackground(new Color(186, 90, 90));
		pnlKhungTT.setBounds(10, 10, 629, 677);
		add(pnlKhungTT);
		pnlKhungTT.setLayout(null);
		
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMa.setBounds(56, 130, 138, 19);
		pnlKhungTT.add(lblMa);
		
		txtMaNhanVien = new RoundedTextField(10);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setBounds(219, 127, 275, 29);
		pnlKhungTT.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);
		
		JLabel lblNhanVien = new JLabel("Tên nhân viên:");
		lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhanVien.setBounds(56, 187, 138, 19);
		pnlKhungTT.add(lblNhanVien);
		
		txtTenNhanVien = new RoundedTextField(10);
		txtTenNhanVien.setColumns(10);
		txtTenNhanVien.setBounds(219, 184, 275, 29);
		pnlKhungTT.add(txtTenNhanVien);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGioiTinh.setBounds(56, 243, 138, 19);
		pnlKhungTT.add(lblGioiTinh);
		
		JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoDienThoai.setBounds(56, 286, 138, 19);
		pnlKhungTT.add(lblSoDienThoai);
		
		txtSoDienThoai = new RoundedTextField(10);
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(219, 283, 275, 29);
		pnlKhungTT.add(txtSoDienThoai);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgaySinh.setBounds(56, 341, 138, 19);
		pnlKhungTT.add(lblNgaySinh);
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChucVu.setBounds(56, 405, 138, 19);
		pnlKhungTT.add(lblChucVu);
		
		JLabel lblNgayTuyenDung = new JLabel("Ngày tuyển dụng:");
		lblNgayTuyenDung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayTuyenDung.setBounds(56, 457, 138, 19);
		pnlKhungTT.add(lblNgayTuyenDung);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDiaChi.setBounds(56, 507, 138, 19);
		pnlKhungTT.add(lblDiaChi);
		
		txtDiaChi = new RoundedTextField(10);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(219, 504, 275, 29);
		pnlKhungTT.add(txtDiaChi);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(56, 560, 138, 19);
		pnlKhungTT.add(lblEmail);
		
		txtEmail = new RoundedTextField(10);
		txtEmail.setColumns(10);
		txtEmail.setBounds(219, 557, 275, 29);
		pnlKhungTT.add(txtEmail);
		
		radNam = new JRadioButton("Nam");
		buttonGroup.add(radNam);
		radNam.setBounds(219, 244, 60, 21);
		pnlKhungTT.add(radNam);
		
		radNu = new JRadioButton("Nữ");
		buttonGroup.add(radNu);
		radNu.setBounds(294, 244, 72, 21);
		pnlKhungTT.add(radNu);
		
		cboChucVu = new JComboBox();
		cboChucVu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cboChucVu.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên bán hàng", "Quản lý"}));
		cboChucVu.setBounds(219, 402, 275, 29);
		pnlKhungTT.add(cboChucVu);
		
		RoundedButton btnCapNhat = new RoundedButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.setBounds(113, 619, 138, 36);
		pnlKhungTT.add(btnCapNhat);
		
		btnCapNhat.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin mới từ các trường nhập liệu
		        String maNhanVien = txtMaNhanVien.getText();
		        String tenNhanVien = txtTenNhanVien.getText();
		        String soDienThoai = txtSoDienThoai.getText();
		        boolean gioiTinh = radNam.isSelected() ? true : false;
		        LocalDate ngaySinh = null;
		        if (dcNgaySinh.getDate() != null) {
		            java.util.Date date = dcNgaySinh.getDate();
		            ngaySinh = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        }
		        boolean isQuanLy = cboChucVu.getSelectedIndex() == 1;
		        String ngayTuyenDungStr = txtNgayTuyenDung.getText();
		        LocalDate ngayTuyenDung = LocalDate.parse(ngayTuyenDungStr);
		        String diaChi = txtDiaChi.getText();
		        String email= txtEmail.getText();
		        
		        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
		        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || ngaySinh == null || ngayTuyenDung==null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        } else {
		            // Cập nhật thông tin nhân viên vào cơ sở dữ liệu
		            NhanVienDAO nhanVienDAO = new NhanVienDAO();
		            NhanVien nv1= nhanVienDAO.selectById(maNhanVien);
		            NhanVien nhanVien = new NhanVien(maNhanVien,nv1.getTaiKhoan() ,tenNhanVien, gioiTinh, soDienThoai, ngaySinh, isQuanLy, ngayTuyenDung,diaChi,email,true,filePath);
		            int updated = nhanVienDAO.update(nhanVien);

		            if (updated!=0) {
		                JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                capNhatBangNhanVien(); // Cập nhật lại bảng nhân viên sau khi cập nhật thành công
		            } else {
		                JOptionPane.showMessageDialog(null, "Cập nhật thông tin thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		
		RoundedButton btnThem = new RoundedButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setBounds(272, 619, 138, 36);
		pnlKhungTT.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Lấy thông tin mới từ các trường nhập liệu
		    	NhanVienDAO nhanVienDAO= new NhanVienDAO();
		        String maNhanVien = nhanVienDAO.getNextMaNhanVien();
		        System.out.println(maNhanVien);
		        String tenNhanVien = txtTenNhanVien.getText();
		        String soDienThoai = txtSoDienThoai.getText();
		        boolean gioiTinh = radNam.isSelected() ? true : false;
		        LocalDate ngaySinh = null;
		        if (dcNgaySinh.getDate() != null) {
		            java.util.Date date = dcNgaySinh.getDate();
		            ngaySinh = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        }
		        boolean isQuanLy = cboChucVu.getSelectedIndex() == 1;
		        
		        LocalDate ngayTuyenDung = LocalDate.now();
		        String diaChi = txtDiaChi.getText();
		        String email = txtEmail.getText();
		        
		        // Kiểm tra xem các trường thông tin đã được nhập đầy đủ hay không
		        if (maNhanVien.isEmpty() || tenNhanVien.isEmpty() || soDienThoai.isEmpty() || ngaySinh == null || ngayTuyenDung == null) {
		            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        } else {
		        	 String tenTaiKhoan ="taikhoan"; // Tên tài khoản mặc định là họ tên
		             String matKhau = "password"; // Mật khẩu mặc định
		             boolean loaiTaiKhoan = false; // Loại tài khoản mặc định
		             TaiKhoanDAO khoanDAO= new TaiKhoanDAO();
		             
		             String maTaiKhoan =khoanDAO.getNextMaTaiKhoan(); // Lấy mã tài khoản tiếp theo
		             System.out.println(maTaiKhoan);
		             TaiKhoan taiKhoan = new TaiKhoan(maTaiKhoan, tenTaiKhoan, matKhau, loaiTaiKhoan);
		             khoanDAO.insert(taiKhoan);
		            // Tạo đối tượng nhân viên mới
		            NhanVien nhanVien = new NhanVien(maNhanVien, taiKhoan, tenNhanVien, gioiTinh, soDienThoai, ngaySinh, isQuanLy, ngayTuyenDung, diaChi, email, true, filePath);
		            
		            // Thêm nhân viên vào cơ sở dữ liệu
		            NhanVienDAO nhanVienDAO1 = new NhanVienDAO();
		            int inserted = nhanVienDAO1.insert(nhanVien);
		            
		            if (inserted != 0) {
		                JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		                capNhatBangNhanVien(); // Cập nhật lại bảng nhân viên sau khi thêm thành công
		            } else {
		                JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});

		
		RoundedButton btnQuanLyTaiKhoan = new RoundedButton("Quản lý tài khoản");
		btnQuanLyTaiKhoan.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maNhanVien = txtMaNhanVien.getText();
		        if (maNhanVien.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên trước khi quản lý tài khoản!", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        } else {
		            NhanVienDAO nhanVienDAO = new NhanVienDAO();
		            NhanVien nv1= nhanVienDAO.selectById(maNhanVien);
		            String maTK= nv1.getTaiKhoan().getMaTaiKhoan();
		            dlgQuanLyTaiKhoan dlgQuanLyTK = new dlgQuanLyTaiKhoan(frmTrangChu,maTK);
		            dlgQuanLyTK.setLocationRelativeTo(frmTrangChu);
		            dlgQuanLyTK.setVisible(true);
		        }
		    }
		});

		btnQuanLyTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnQuanLyTaiKhoan.setBounds(431, 619, 161, 36);
		pnlKhungTT.add(btnQuanLyTaiKhoan);
		
		 dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(219, 341, 275, 29);
		pnlKhungTT.add(dcNgaySinh);
		
		txtNgayTuyenDung = new RoundedTextField(10);
		txtNgayTuyenDung.setEditable(false);
		txtNgayTuyenDung.setColumns(10);
		txtNgayTuyenDung.setBounds(219, 447, 275, 29);
		pnlKhungTT.add(txtNgayTuyenDung);
		
		JLabel lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(217, 23, 102, 94);
		pnlKhungTT.add(lblHinhAnh);
		
//		JButton btnThayAnh = new JButton("Thay đổi");
//		btnThayAnh.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        JFileChooser fileChooser = new JFileChooser();
//		        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Hình ảnh", "jpg", "png");
//		        fileChooser.setFileFilter(imgFilter);
//		        
//		        fileChooser.setMultiSelectionEnabled(false);
//		        int x = fileChooser.showDialog(btnThayAnh, "Chọn file");
//		        
//		        if (x == JFileChooser.APPROVE_OPTION) {
//		            File f = fileChooser.getSelectedFile();
//		            filePath = f.getAbsolutePath(); // Lấy đường dẫn của tập tin hình ảnh
//		           
//		            // Thay đổi kích thước của ảnh
//		            ImageIcon icon = new ImageIcon(filePath);
//		            Image img = icon.getImage();
//		            Image newImg = img.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
//		            ImageIcon newIcon = new ImageIcon(newImg);
//		            
//		            lblHinhAnh.setIcon(newIcon);
//		        }
//		    }
//		});
//
//		btnThayAnh.setBounds(339, 96, 92, 21);
//		pnlKhungTT.add(btnThayAnh);
		
		JPanel pnlDanhSachNhanVien = new JPanel();
		pnlDanhSachNhanVien.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDanhSachNhanVien.setBounds(649, 10, 832, 677);
		add(pnlDanhSachNhanVien);
		pnlDanhSachNhanVien.setLayout(null);
		
		JPanel pnlKhung1 = new JPanel();
		pnlKhung1.setBounds(10, 22, 822, 65);
		pnlDanhSachNhanVien.add(pnlKhung1);
		pnlKhung1.setLayout(null);
		
		JLabel lblNhapMa = new JLabel("Nhập mã nhân viên:");
		lblNhapMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhapMa.setBounds(10, 10, 175, 21);
		pnlKhung1.add(lblNhapMa);
		
		txtMaNhanVienTim = new RoundedTextField(10);
		txtMaNhanVienTim.setBounds(20, 33, 165, 19);
		pnlKhung1.add(txtMaNhanVienTim);
		txtMaNhanVienTim.setColumns(10);
		
		RoundedButton btnTim = new RoundedButton("Tìm");
		btnTim.setBounds(205, 32, 71, 21);
		pnlKhung1.add(btnTim);
		
		btnTim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maNhanVienTim = txtMaNhanVienTim.getText().trim();
		        // Kiểm tra xem trường mã nhân viên để tìm kiếm có được nhập không
		        if (maNhanVienTim.isEmpty()) {
		            capNhatBangNhanVien();
		        } else {
		            // Gọi phương thức để tìm nhân viên trong cơ sở dữ liệu dựa trên mã
		            NhanVienDAO nhanVienDAO = new NhanVienDAO();
		            NhanVien nhanVien = nhanVienDAO.selectById((maNhanVienTim));
		            if (nhanVien != null) {
		            	timKiem(maNhanVienTim);
		            	txtMaNhanVienTim.setText("");
		            } else {
		                // Nếu không tìm thấy nhân viên, hiển thị thông báo không tìm thấy
		                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã đã nhập!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		    }
		});

		
		JLabel lblChucVuLoc = new JLabel("Chức vụ");
		lblChucVuLoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChucVuLoc.setBounds(353, 10, 95, 21);
		pnlKhung1.add(lblChucVuLoc);
		
		 cboChucVuLoc = new JComboBox();
		cboChucVuLoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboChucVuLoc.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên", "Quản lý", "Tất cả"}));
		cboChucVuLoc.setBounds(363, 32, 175, 21);
		pnlKhung1.add(cboChucVuLoc);
		cboChucVuLoc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy loại chức vụ được chọn từ JComboBox
		        String selectedChucVu = cboChucVuLoc.getSelectedItem().toString();
		        
		        // Cập nhật bảng nhân viên dựa trên loại chức vụ được chọn
		        capNhatBangNhanVienTheoChucVu(selectedChucVu);
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 812, 598);
		pnlDanhSachNhanVien.add(scrollPane);
		
		tblNhanVien = new JTable();
		tblNhanVien.setModel( model = new DefaultTableModel(
	            new Object[][] {
	                {null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null},
	            },
	            new String[] {
	                "Mã nhân viên", "Họ tên", "Giới tính", "Số điện thoại", "Ngày sinh", "Chức vụ", "Ngày tuyển dụng", "Email", "Địa chỉ"
	            }
	        
		));
		
		capNhatBangNhanVien();
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(93);
		tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(81);
		tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(95);
		scrollPane.setViewportView(tblNhanVien);
		tblNhanVien.addMouseListener(this);
	}
	public void capNhatBangNhanVien() {
        // Lấy model từ bảng
        model = (DefaultTableModel) tblNhanVien.getModel();

        // Xóa dữ liệu hiện tại trong bảng
        model.setRowCount(0);

        // Lấy danh sách nhân viên từ cơ sở dữ liệu
        NhanVienDAO nhanVienDAO = new NhanVienDAO();
        ArrayList<NhanVien> danhSachNhanVien = nhanVienDAO.selectAll();

        // Thêm dữ liệu vào bảng
        for (NhanVien nv : danhSachNhanVien) {
            Object[] row = { nv.getMaNhanVien(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.isChucVu() ? "Quản lý" : "Nhân viên", nv.getNgayThem(), nv.getEmail(), nv.getDiaChi() };
            model.addRow(row);
        }
    }
	public void capNhatBangNhanVienTheoChucVu(String chucVu) {
	    // Kiểm tra nếu model đã được khởi tạo
	    if (model != null) {
	        // Xóa dữ liệu hiện tại trong bảng
	        model.setRowCount(0);

	        // Lấy danh sách nhân viên từ cơ sở dữ liệu dựa trên loại chức vụ được chọn
	        NhanVienDAO nhanVienDAO = new NhanVienDAO();
	        ArrayList<NhanVien> danhSachNhanVien = null;
	        if (chucVu.equals("Nhân viên")) {
	            danhSachNhanVien = nhanVienDAO.selectAllNhanVien();
	        } else if (chucVu.equals("Quản lý")) {
	            danhSachNhanVien = nhanVienDAO.selectAllQuanLy();
	        } else if (chucVu.equals("Tất cả")) {
	            danhSachNhanVien = nhanVienDAO.selectAll();
	        }

	        // Thêm dữ liệu vào bảng
	        for (NhanVien nv : danhSachNhanVien) {
	            Object[] row = { nv.getMaNhanVien(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.isChucVu() ? "Quản lý" : "Nhân viên", nv.getNgayThem(), nv.getEmail(), nv.getDiaChi() };
	            model.addRow(row);
	        }
	    }
	}
	public void timKiem(String maNV) {
	    // Kiểm tra nếu model đã được khởi tạo
	    if (model != null) {
	        // Xóa dữ liệu hiện tại trong bảng
	        model.setRowCount(0);

	        // Lấy danh sách nhân viên từ cơ sở dữ liệu dựa trên loại chức vụ được chọn
	        NhanVienDAO nhanVienDAO = new NhanVienDAO();
	        NhanVien
	        	nv = nhanVienDAO.selectById(maNV);

	 
	            Object[] row = { nv.getMaNhanVien(), nv.getHoTen(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSdt(), nv.getNgaySinh(), nv.isChucVu() ? "Quản lý" : "Nhân viên", nv.getNgayThem(), nv.getEmail(), nv.getDiaChi() };
	            model.addRow(row);
	 
	    }
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    // Lấy chỉ số của dòng được click
	    int row = tblNhanVien.getSelectedRow();
	    txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
		txtTenNhanVien.setText(model.getValueAt(row, 1).toString());
		 String gioiTinh = model.getValueAt(row, 2).toString();
	        // Hiển thị giới tính lên các trường tương ứng trong giao diện
	        if (gioiTinh.equals("Nam")) {
	            radNam.setSelected(true);
	        } else {
	            radNu.setSelected(true);
	        }
	    txtSoDienThoai.setText(model.getValueAt(row, 3).toString());
	    // Lấy giá trị ngày sinh từ cột thứ 4
	    String ngaySinh = model.getValueAt(row, 4).toString();
	    // Chuyển đổi kiểu chuỗi thành đối tượng Date
	    try {
	        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
	        dcNgaySinh.setDate(date);
	    } catch (ParseException ex) {
	        ex.printStackTrace();
	    }

	    // Lấy giá trị chức vụ từ cột thứ 5
	    String chucVu = model.getValueAt(row, 5).toString();
	    // Set giá trị chức vụ trong JComboBox
	    cboChucVu.setSelectedItem(chucVu);

	    // Lấy giá trị ngày tuyển dụng từ cột thứ 6
	    String ngayTuyenDung = model.getValueAt(row, 6).toString();
	    txtNgayTuyenDung.setText(ngayTuyenDung);
	    txtDiaChi.setText(model.getValueAt(row, 8).toString());
	    txtEmail.setText(model.getValueAt(row, 7).toString());
	    
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
	private void hienThiThongTinNhanVien(String maNhanVien) {
	    // Gọi đến lớp DAO để lấy thông tin của nhân viên từ cơ sở dữ liệu
	    NhanVienDAO nhanVienDAO = new NhanVienDAO();
	    NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

	    // Hiển thị thông tin của nhân viên lên các trường tương ứng trên panel thông tin nhân viên
	    txtMaNhanVien.setText(nhanVien.getMaNhanVien());
	    txtTenNhanVien.setText(nhanVien.getHoTen());
	    // ... các trường thông tin khác tương tự
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
