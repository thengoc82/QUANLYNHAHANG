package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controler.bienStatic;
import dao.LoaiSanPhamDAO;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.LoaiSanPham;
import entity.NhanVien;
import entity.SanPham;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class pnlSanPham extends JPanel implements ActionListener, MouseListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtSmall;
	private JTextField txtMedium;
	private JTextField txtLarge;
	private JTextField txtMaSPTim;
	private JTable tblSanPham;
	
	private JFrame frmTrangChu;
	private DefaultTableModel model;
	private DefaultTableModel dataModel;
	private JComboBox cboLoai;
	private JComboBox cboLocTrangThai, cboTrangThai;
	private String filePath;
	private static JComboBox cboLoaiSP;
	private JTextArea txtAreaMoTa;
	private JPanel pnlKichThuoc1, pnlKichThuoc2;
	private JTextField txtGiaChung;
	private JCheckBox chkSmall, chkLarge, chkMedium;
	private JLabel lblHinhSP;
	private JButton btnLamMoi, btnThem, btnCapNhat, btnThemSP ;
	private static JPanel pnlThongTin;
	private JLabel lblChuThich;
	ArrayList<SanPham> danhSachSanPham = null;
	/**
	 * Create the frame.
	 */
	public pnlSanPham(JFrame frmTrangChu) {
		this.frmTrangChu = frmTrangChu;
		setBackground(new Color(186, 90, 90));
		setBounds(10, 58, 1491, 697);
		setLayout(null);
		
		pnlThongTin = new JPanel();
		pnlThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTin.setForeground(Color.WHITE);
		pnlThongTin.setBackground(new Color(186, 90, 90));
		pnlThongTin.setBounds(10, 10, 638, 677);
		add(pnlThongTin);
		pnlThongTin.setLayout(null);
		
		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaSP.setBounds(10, 128, 103, 24);
		pnlThongTin.add(lblMaSP);
		
		txtMaSP = new RoundedTextField(10);
		txtMaSP.setBounds(154, 127, 335, 25);
		pnlThongTin.add(txtMaSP);
		txtMaSP.setColumns(10);
		
		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenSP.setBounds(10, 171, 103, 24);
		pnlThongTin.add(lblTenSP);
		
		txtTenSP = new RoundedTextField(10);
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(154, 173, 335, 25);
		pnlThongTin.add(txtTenSP);
		
		pnlKichThuoc1 = new JPanel();
		pnlKichThuoc1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "K\u00EDch th\u01B0\u1EDBc v\u00E0 gi\u00E1 b\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlKichThuoc1.setBounds(21, 288, 506, 162);
		pnlThongTin.add(pnlKichThuoc1);
		pnlKichThuoc1.setLayout(null);
		
		chkSmall = new JCheckBox("Small");
		chkSmall.setBounds(18, 55, 82, 21);
		pnlKichThuoc1.add(chkSmall);
		
		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setBounds(101, 30, 47, 13);
		pnlKichThuoc1.add(lblGiaBan);
		
		txtSmall = new RoundedTextField(10);
		txtSmall.setBounds(139, 56, 197, 19);
		pnlKichThuoc1.add(txtSmall);
		txtSmall.setColumns(10);
		
		txtMedium = new RoundedTextField(10);
		txtMedium.setColumns(10);
		txtMedium.setBounds(139, 89, 197, 19);
		pnlKichThuoc1.add(txtMedium);
		
		chkMedium = new JCheckBox("Medium");
		chkMedium.setBounds(18, 88, 82, 21);
		pnlKichThuoc1.add(chkMedium);
		
		txtLarge = new RoundedTextField(10);
		txtLarge.setColumns(10);
		txtLarge.setBounds(139, 121, 197, 19);
		pnlKichThuoc1.add(txtLarge);
		
		chkLarge = new JCheckBox("Large");
		chkLarge.setBounds(18, 120, 82, 21);
		pnlKichThuoc1.add(chkLarge);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTrangThai.setBounds(23, 460, 103, 24);
		pnlThongTin.add(lblTrangThai);
		
		cboTrangThai = new JComboBox();
		cboTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Đang bán", "Ngừng bán"}));
		cboTrangThai.setBounds(167, 464, 335, 30);
		pnlThongTin.add(cboTrangThai);
		
		JLabel lblMT = new JLabel("Mô tả:");
		lblMT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMT.setBounds(23, 516, 103, 24);
		pnlThongTin.add(lblMT);
		
		 txtAreaMoTa = new JTextArea();
		txtAreaMoTa.setBounds(167, 518, 335, 64);
		pnlThongTin.add(txtAreaMoTa);
		
		btnCapNhat = new RoundedButton("Cập nhật");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCapNhat.setBounds(300, 622, 139, 30);
		pnlThongTin.add(btnCapNhat);
		
		btnThemSP = new RoundedButton("Thêm");
		btnThemSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemSP.setBounds(461, 622, 121, 30);
		pnlThongTin.add(btnThemSP);
		
		cboLoaiSP = new JComboBox();
		cboLoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 15));

		cboLoaiSP.setBounds(154, 216, 335, 30);
		pnlThongTin.add(cboLoaiSP);
		
		JLabel lblLoaiSP = new JLabel("Loại sản phẩm:");
		lblLoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoaiSP.setBounds(10, 219, 115, 24);
		pnlThongTin.add(lblLoaiSP);
		
		btnThem = new RoundedButton("");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgThemLoaiSanPham dlgThemLoai = new dlgThemLoaiSanPham(frmTrangChu);
				dlgThemLoai.setLocationRelativeTo(frmTrangChu);
				dlgThemLoai.setVisible(true);
			}
		});
		danhSachSanPham = SanPhamDAO.getInstance().selectAll();
		btnThem.setIcon(new ImageIcon(pnlSanPham.class.getResource("/icon/logoThem.jpg")));
		btnThem.setBounds(499, 216, 35, 30);
		pnlThongTin.add(btnThem);
		
		lblHinhSP = new JLabel("");
		lblHinhSP.setBounds(165, 10, 101, 110);
		pnlThongTin.add(lblHinhSP);
		
		RoundedButton btnThayAnh = new RoundedButton("Thay đổi");
		btnThayAnh.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Hình ảnh", "jpg", "png");
		        fileChooser.setFileFilter(imgFilter);
		        
		        fileChooser.setMultiSelectionEnabled(false);
		        int x = fileChooser.showDialog(btnThayAnh, "Chọn file");
		        
		        if (x == JFileChooser.APPROVE_OPTION) {
		            File f = fileChooser.getSelectedFile();
		            filePath = f.getAbsolutePath(); 

		            ImageIcon icon = new ImageIcon(filePath);
		            Image img = icon.getImage();
		            Image newImg = img.getScaledInstance(lblHinhSP.getWidth(), lblHinhSP.getHeight(), Image.SCALE_SMOOTH);
		            ImageIcon newIcon = new ImageIcon(newImg);
		            
		            lblHinhSP.setIcon(newIcon);
		        }
		    }
		});
		btnThayAnh.setBackground(new Color(255, 255, 255));
		btnThayAnh.setBounds(276, 63, 85, 21);
		pnlThongTin.add(btnThayAnh);
		
		pnlKichThuoc2 = new JPanel();
		pnlKichThuoc2.setBounds(21, 288, 506, 162);
		pnlKichThuoc2.setVisible(false);
		pnlThongTin.add(pnlKichThuoc2);
		pnlKichThuoc2.setLayout(null);
		
		JLabel lblTB = new JLabel("(*) Loại sản phẩm không phần kích thước, đây là đơn giá chung");
		lblTB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTB.setBounds(12, 26, 484, 27);
		pnlKichThuoc2.add(lblTB);
		
		JLabel lblGiaBan2 = new JLabel("Giá bán:");
		lblGiaBan2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiaBan2.setBounds(30, 70, 89, 27);
		pnlKichThuoc2.add(lblGiaBan2);
		
		txtGiaChung = new JTextField();
		txtGiaChung.setBounds(108, 63, 209, 32);
		pnlKichThuoc2.add(txtGiaChung);
		txtGiaChung.setColumns(10);
		
		btnLamMoi = new RoundedButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLamMoi.setBounds(417, 592, 121, 21);
		pnlThongTin.add(btnLamMoi);
		
		lblChuThich = new JLabel("*");
		lblChuThich.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChuThich.setBounds(151, 254, 447, 24);
		pnlThongTin.add(lblChuThich);
		
		JPanel pnlDanhSachSP = new JPanel();
		pnlDanhSachSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDanhSachSP.setBounds(667, 10, 814, 677);
		add(pnlDanhSachSP);
		pnlDanhSachSP.setLayout(null);
		
		JPanel pnlKhung5 = new JPanel();
		pnlKhung5.setBounds(10, 21, 794, 70);
		pnlDanhSachSP.add(pnlKhung5);
		pnlKhung5.setLayout(null);
		
		JLabel lblMaSPTim = new JLabel("Nhập mã sản phẩm:");
		lblMaSPTim.setBounds(10, 10, 143, 13);
		pnlKhung5.add(lblMaSPTim);
		
		txtMaSPTim = new RoundedTextField(10);
		txtMaSPTim.setBounds(10, 30, 184, 19);
		pnlKhung5.add(txtMaSPTim);
		txtMaSPTim.setColumns(10);
		
		JLabel lblLocTrangThai = new JLabel("Trạng thái:");
		lblLocTrangThai.setBounds(295, 9, 108, 13);
		pnlKhung5.add(lblLocTrangThai);
		
		cboLocTrangThai = new JComboBox();
		cboLocTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboLocTrangThai.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Đang bán", "Ngừng bán"}));
		cboLocTrangThai.setBounds(295, 28, 160, 21);
		pnlKhung5.add(cboLocTrangThai);
		cboLocTrangThai.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedTT = (String) cboLocTrangThai.getSelectedItem();
		        updateTableTT(selectedTT);;
		    }
		});
		
		cboLoai = new JComboBox();
		cboLoai.setModel(new DefaultComboBoxModel(new String[] {"Tất cả","Mì","Cơm","Khác"}));
		cboLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboLoai.setBounds(539, 28, 168, 21);
		pnlKhung5.add(cboLoai);
		cboLoai.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedLoai = (String) cboLoai.getSelectedItem();  
		        updateTable(selectedLoai);
		    }
		});
		
		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(539, 9, 108, 13);
		pnlKhung5.add(lblLoai);
		
		RoundedButton btnTim = new RoundedButton("Tìm");
		btnTim.setBounds(200, 29, 68, 21);
		pnlKhung5.add(btnTim);
		btnTim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String maTim = txtMaSPTim.getText().trim();
		        if (maTim.isEmpty()) {
		            capNhatBangSanPham();
		        } else {
		        		SanPham sanPham = searchSanPham(maTim, danhSachSanPham);
			            if (sanPham != null) {
			            	timKiem(maTim);
			            	txtMaSPTim.setText("");
			            } else {
			                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên với mã đã nhập!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			            }
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 794, 585);
		pnlDanhSachSP.add(scrollPane);
		
		tblSanPham = new JTable();
		tblSanPham.setModel( model=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "Tr\u1EA1ng th\u00E1i", "M\u00F4 t\u1EA3", "Lo\u1EA1i"
			}
		));
		capNhatBangSanPham();
		tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(78);
		tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(80);
		scrollPane.setViewportView(tblSanPham);
		tblSanPham.addMouseListener(this);
		cboLoaiSP.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selected = (String) cboLoaiSP.getSelectedItem();
		        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
		        LoaiSanPham loaiSanPham = loaiSanPhamDAO.selectById(selected);
		        if(loaiSanPham != null) {
		        	lblChuThich.setText("(*) " + loaiSanPham.getTenloai());
		        	if(loaiSanPham.isCoPhanLoai()) {
		        		pnlKichThuoc1.setVisible(true);
		        		pnlKichThuoc2.setVisible(false);
		        	}else {
		        		pnlKichThuoc1.setVisible(false);
		        		pnlKichThuoc2.setVisible(true);
		        	}
		        }
		    }
		});
		txtMaSP.setEditable(false);
		btnLamMoi.addActionListener(this);
		btnThem.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnThemSP.addActionListener(this);
		
		chkSmall.addItemListener(this);
		chkMedium.addItemListener(this);
		chkLarge.addItemListener(this);
		
		updateCboLoaiSP();
		txtLarge.setEditable(false);
		txtMedium.setEditable(false);
		txtSmall.setEditable(false);
		
		txtMaSPTim.addKeyListener(new KeyListener()  {
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
	}
	public static void updateCboLoaiSP() {
		cboLoaiSP.removeAllItems();
		ArrayList<LoaiSanPham> dsLoai = LoaiSanPhamDAO.getInstance().selectAll();
		for (LoaiSanPham loaiSanPham : dsLoai) {
			cboLoaiSP.addItem(loaiSanPham.getMaLoai());
		}
	}
	public void capNhatBangSanPham() {
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (SanPham sp : danhSachSanPham) {
            if(sp.getLoaiSanPham().isCoPhanLoai()) {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.isTrangThai()?"Đang bán":"Ngừng bán",sp.getMoTa(),sp.getLoaiSanPham().getMaLoai() };
                model.addRow(row);
            }else {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.isTrangThai()?"Đang bán":"Ngừng bán",sp.getMoTa(),sp.getLoaiSanPham().getMaLoai() };
                model.addRow(row);
            }
        }
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		convertToTextField(tblSanPham.getSelectedRow());
	}
	//convertToTextField
	private void convertToTextField(int row) {
//       JWindow loadingWindow = createLoadingWindow();
//       loadingWindow.setVisible(true);
//       SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//           @Override
//           protected Void doInBackground() throws Exception {
        	   	txtMaSP.setText(model.getValueAt(row, 0).toString());
	       		txtTenSP.setText(model.getValueAt(row, 1).toString());
	       		String chucVu = model.getValueAt(row, 2).toString();
	       	    cboTrangThai.setSelectedItem(chucVu);
	       	    String loaiSP = model.getValueAt(row, 4).toString();
	       	    // Set giá trị chức vụ trong JComboBox
	       	    cboLoaiSP.setSelectedItem(loaiSP);
	       	    txtAreaMoTa.setText(model.getValueAt(row, 3).toString());
	       	    String ma= txtMaSP.getText();
	       	    ArrayList<SanPham> spSize = SanPhamDAO.getInstance().selectByIdAllSize(ma);
	       	    SanPham sanPham = SanPhamDAO.getInstance().selectById(ma);
	            LoaiSanPham loaiSanPham = LoaiSanPhamDAO.getInstance().selectById(loaiSP);
	       	    if (loaiSanPham != null) {
	       	    	
	       	        chkSmall.setSelected(false);
	       	        txtSmall.setText("");
	       	        chkMedium.setSelected(false);
	       	        txtMedium.setText("");
	       	        chkLarge.setSelected(false);
	       	        txtLarge.setText("");
	       	        txtGiaChung.setText("");
	       	        for (SanPham sanPham1 : spSize) {
	       	        	for (Map.Entry<KichThuoc, Double> entry : sanPham1.getKichThuocGia().entrySet()) {
	       		        	KichThuoc kt = entry.getKey();
	       		            Double gia = entry.getValue();
	       		            String kichThuoc = kt.toString(); 
	       		            if (kichThuoc.equalsIgnoreCase("S")) {
	       		            	if(gia == 0) {
	       		            		chkSmall.setSelected(false);
		       		                txtSmall.setText("");
	       		            	}else {
	       		            		chkSmall.setSelected(true);
		       		                txtSmall.setText(gia.toString());
	       		            	}
	       		            } else if (kichThuoc.equalsIgnoreCase("M")) {
	       		            	if(gia == 0) {
	       		            		chkMedium.setSelected(false);
		       		                txtMedium.setText("");
	       		            	}else {
	       		            		chkMedium.setSelected(true);
		       		                txtMedium.setText(gia.toString());
	       		            	}
	       		            } else if (kichThuoc.equalsIgnoreCase("L")) {
	       		             if(gia == 0) {
	       		            		chkLarge.setSelected(false);
		       		                txtLarge.setText("");
	       		            	}else {
	       		            		chkLarge.setSelected(true);
		       		                txtLarge.setText(gia.toString());
	       		            	}
	       		            } else if(kichThuoc.equalsIgnoreCase("D")) {
	       		            	txtGiaChung.setText(gia.toString());
	       		            }
	       		        }
	       			}
	       	    }
	       	   filePath = sanPham.getHinhAnh();
	              
	              // Thay đổi kích thước của ảnh		       
		       	ImageIcon imageIcon = null;
	            Image imgHinh = null;
	            if(filePath.startsWith("/")) {
	            	imageIcon = new ImageIcon(frmTrangChu.class.getResource(filePath));
	            	imgHinh = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	            }else {
	            	imageIcon = new ImageIcon(filePath);
	                imgHinh = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	            } 	  
		       lblHinhSP.setIcon(new ImageIcon(imgHinh));
//	           return null;
//           }
//
//           @Override
//           protected void done() {
//               loadingWindow.dispose();
//           }
//       };
//       worker.execute();		        
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
		Object ob = e.getSource();
		if(ob.equals(btnLamMoi)) {
			resetTextField();
		}
		if(ob.equals(btnThemSP)) {
			if(checkData()) {
				SanPhamDAO.getInstance().insert(convertToObject());
				JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm, Vui lòng đăng nhập lại để cập nhật menu sản phẩm!");
				JWindow loadingWindow = bienStatic.createLoadingWindow("Đang tải dữ liệu mới...");
                loadingWindow.setVisible(true);
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                    	capNhatBangSanPham();
                        return null;
                    }

                    @Override
                    protected void done() {
                    	loadingWindow.dispose();
                    }
                };
                worker.execute();		        
			}
		}
		if(ob.equals(btnCapNhat)) {
			if(checkData()) {
				SanPhamDAO.getInstance().update(convertToObjectToUpdate());
				JOptionPane.showMessageDialog(null, "Đã cập nhật sản phẩm, Vui lòng đăng nhập lại để cập nhật menu sản phẩm!");
				JWindow loadingWindow = bienStatic.createLoadingWindow("Đang tải dữ liệu mới...");
                loadingWindow.setVisible(true);
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                    	capNhatBangSanPham();
                        return null;
                    }

                    @Override
                    protected void done() {
                    	loadingWindow.dispose();
                    }
                };
                worker.execute();		        
			}
		}
	}
	private SanPham convertToObject() {
		String ma = SanPhamDAO.getInstance().taoMaSP();
		String ten = txtTenSP.getText();
		String loaiSPS = cboLoaiSP.getSelectedItem().toString();
		LoaiSanPham loaiSP = LoaiSanPhamDAO.getInstance().selectById(loaiSPS);
		boolean trangThai = cboTrangThai.equals("Đang bán")?true:false;
		String moTa = txtAreaMoTa.getText().trim().equals("")?"":txtAreaMoTa.getText();
		Double giaS, giaM, giaL, giaD;
		SanPham sp = new SanPham(ma, ten, trangThai, loaiSP, filePath, moTa, null);
		if(loaiSP != null) {
			if(loaiSP.isCoPhanLoai()) {
				if(chkSmall.isSelected()) {
					giaS= Double.parseDouble(txtSmall.getText().toString());
					sp.themKichThuocGia(KichThuoc.S, giaS);
				}
				if(chkMedium.isSelected()) {
					giaM= Double.parseDouble(txtMedium.getText().toString());
					sp.themKichThuocGia(KichThuoc.M, giaM);
				}
				if(chkLarge.isSelected()) {
					giaL= Double.parseDouble(txtLarge.getText().toString());
					sp.themKichThuocGia(KichThuoc.L, giaL);
				}
			} else {
				giaD = Double.parseDouble(txtGiaChung.getText());
				sp.themKichThuocGia(KichThuoc.D, giaD);
			}
		}
		return sp;
	}
	
	private SanPham convertToObjectToUpdate(){
		String ma = txtMaSP.getText();
		String ten = txtTenSP.getText();
		String loaiSPS = cboLoaiSP.getSelectedItem().toString();
		LoaiSanPham loaiSP = LoaiSanPhamDAO.getInstance().selectById(loaiSPS);
		boolean trangThai = cboTrangThai.getSelectedItem().toString().equals("Đang bán")?true:false;
		String moTa = txtAreaMoTa.getText().trim().equals("")?"":txtAreaMoTa.getText();
		Double giaS, giaM, giaL, giaD;
		SanPham sp = new SanPham(ma, ten, trangThai, loaiSP, filePath, moTa, null);
		if(loaiSP != null) {
			if(loaiSP.isCoPhanLoai()) {
				if(chkSmall.isSelected()) {
					giaS= Double.parseDouble(txtSmall.getText().toString());
					sp.themKichThuocGia(KichThuoc.S, giaS);
				}
				if(chkMedium.isSelected()) {
					giaM= Double.parseDouble(txtMedium.getText().toString());
					sp.themKichThuocGia(KichThuoc.M, giaM);
				}
				if(chkLarge.isSelected()) {
					giaL= Double.parseDouble(txtLarge.getText().toString());
					sp.themKichThuocGia(KichThuoc.L, giaL);
				}
			} else {
				giaD = Double.parseDouble(txtGiaChung.getText());
				sp.themKichThuocGia(KichThuoc.D, giaD);
			}
		}
		return sp;
	}
	private boolean checkData() {
		if(txtTenSP.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập tên sản phẩm!");
			txtTenSP.requestFocus();
			return false;
		}
		return true;
	}
	private void resetTextField() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		cboLoaiSP.setSelectedIndex(1);
		pnlKichThuoc1.setVisible(true);
		pnlKichThuoc2.setVisible(false);
		chkSmall.setSelected(false);
		chkMedium.setSelected(false);
		chkLarge.setSelected(false);
		txtSmall.setText("");
		txtMedium.setText("");
		txtLarge.setText("");
		cboTrangThai.setSelectedIndex(1);
		txtAreaMoTa.setText("");
	}
	public void timKiem(String maSP) {
	    if (model != null) {
	        model.setRowCount(0);
	        SanPham sp = searchSanPham(maSP, danhSachSanPham);
	        if(sp.getLoaiSanPham().isCoPhanLoai()) {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.isTrangThai()?"Đang bán":"Ngừng bán",sp.getMoTa(),sp.getLoaiSanPham().getMaLoai() };
                model.addRow(row);
            }else {
            	Object[] row = { sp.getMaSanPham(),sp.getTenSanPham(),sp.isTrangThai()?"Đang bán":"Ngừng bán",sp.getMoTa(),sp.getLoaiSanPham().getMaLoai() };
                model.addRow(row);
            }
	 
	    }}
	public SanPham searchSanPham(String maSP, ArrayList<SanPham> dsSP) {
		for (SanPham sanPham : dsSP) {
			if(sanPham.getMaSanPham().equals(maSP)) {
				return sanPham;
			}
		}
		return null;
	}
	public void updateTable(String loai) {
	    if (model != null) {
	        model.setRowCount(0);
	        if (loai.equals("Cafe")) {
	            danhSachSanPham = SanPhamDAO.getInstance().selectCafe();
	        } else if (loai.equals("Bánh ngọt")) {
	            danhSachSanPham = SanPhamDAO.getInstance().selectCake();
	            
	        } else if (loai.equals("Khác")) {
	            danhSachSanPham = SanPhamDAO.getInstance().selectOrther();
	        }else if (loai.equals("Tất cả")) {
	            danhSachSanPham = SanPhamDAO.getInstance().selectAll();
	        }

	        if (danhSachSanPham != null) {
	            for (SanPham sp : danhSachSanPham) {
	                Object[] row;
	                if (sp.getLoaiSanPham().isCoPhanLoai()) {
	                    row = new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.isTrangThai() ? "Đang bán" : "Ngừng bán", sp.getMoTa(), sp.getGiaByKichThuoc(KichThuoc.M), sp.getLoaiSanPham().getTenloai()};
	                } else {
	                    row = new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.isTrangThai() ? "Đang bán" : "Ngừng bán", sp.getMoTa(), sp.getGiaByKichThuoc(KichThuoc.D), sp.getLoaiSanPham().getTenloai()};
	                }
	                model.addRow(row);
	            }
	        }

	    }
	}
	public void updateTableTT(String tt) {
	    if (model != null) {
	        model.setRowCount(0);
	        SanPhamDAO sanPhamDAO = new SanPhamDAO();
	        if (tt.equals("Đang bán")) {
	            danhSachSanPham = sanPhamDAO.selectDangBan(true);
	        } else if (tt.equals("Ngừng bán")) {
	            danhSachSanPham = sanPhamDAO.selectNgungBan(false);
	        }else if (tt.equals("Tất cả")) {
	            danhSachSanPham = sanPhamDAO.selectAll();
	        }
	        if (danhSachSanPham != null) {
	            for (SanPham sp : danhSachSanPham) {
	                Object[] row;
	                if (sp.getLoaiSanPham().isCoPhanLoai()) {
	                    row = new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.isTrangThai() ? "Đang bán" : "Ngừng bán", sp.getMoTa(), sp.getLoaiSanPham().getMaLoai()};
	                } else {
	                    row = new Object[]{sp.getMaSanPham(), sp.getTenSanPham(), sp.isTrangThai() ? "Đang bán" : "Ngừng bán", sp.getMoTa(), sp.getLoaiSanPham().getMaLoai()};
	                }
	                model.addRow(row);
	            }
	        }

	    }
	}
//	private static JWindow createLoadingWindow() {
//        JWindow window = new JWindow();
//        JPanel content = new JPanel(new BorderLayout());
//        JLabel label = new JLabel("Đang tải...", SwingConstants.CENTER);
//        JProgressBar progressBar = new JProgressBar();
//        progressBar.setIndeterminate(true);
//
//        content.add(label, BorderLayout.CENTER);
//        content.add(progressBar, BorderLayout.SOUTH);
//
//        window.add(content);
//        window.setSize(300, 150);
//        window.setLocationRelativeTo(pnlThongTin);
////        window.setAlwaysOnTop(true);
//
//        return window;
//    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();
		if(source == chkSmall) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				txtSmall.setEditable(true);
			} else {
				txtSmall.setText("");
				txtSmall.setEditable(false);
			}
		}
		if(source == chkMedium) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				txtMedium.setEditable(true);
			} else {
				txtMedium.setText("");
				txtMedium.setEditable(false);
			}
		}
		if(source == chkLarge) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				txtLarge.setEditable(true);
			} else {
				txtLarge.setText("");
				txtLarge.setEditable(false);
			}
		}
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
