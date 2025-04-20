package gui;

import controler.bienStatic;
import dao.BanDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.SanPham;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class frmTrangChu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMenu;
    private JPanel pnlMenuIcon;
    private JPanel pnlTrangChu;
    private JPanel pnlTienMat;
    private JPanel controlFlag;
    
    protected JTable tblSanPhamChon;
    protected JTable tblKhuyenMai;
    
	private JTextField txtSdt;
	private JTextField txtHoTen;
	private JTextField txtTienKhachDua;
	private JTextField txtTienThua;
    private JTextField txtMaSPThem;
    
    private JButton btnTrangChu;
    private JButton btnHoaDon;
    private JButton btnKhuyenMai;
    private JButton btnSanPham;
    private JButton btnKhachHang;
    private JButton btnNhanVien;
    private JButton btnThongKe;
    private JButton btnFlag;
    private JButton btnThanhToan;
    private JButton btnTim;
    
    private JRadioButton radNam;
    private JRadioButton radNu;
    
    private JLabel lbltxtBanDat;
    private static JLabel lblTenNhanVien;
    private static JLabel lblThoiGian;
    private static JLabel lbltxtSoLuongSP;
    private static JLabel lbltxtPhiVAT;
    private static JLabel lbltxtTongTien;
    private static JLabel lblThongBao;
    
    private JComboBox cboThanhToan;
    private Timer timer;
    
	private pnlHoaDon pnlHoaDon;
	private pnlKhuyenMai pnlKhuyenMai;
	private pnlSanPham pnlSanPham;
	private pnlKhachHang pnlKhachHang;
	private pnlThongKe pnlThongKe;
	private pnlNhanVien pnlNhanVien;
    private frmDatBan pnlDatBan;
    
	private static double tongTien;
    private static Ban banDat;
	public static bienStatic bienTemp = new bienStatic();
    private final static ArrayList<SanPham> dsSanPham = new ArrayList<>();
    
	/**
	 * Create the frame.
	 */
	public frmTrangChu() {
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Quản lý nhà hàng");
        setBounds(0, 0, 1515, 790);
        
        // Initialize all components
        contentPane = new JPanel();
        contentPane.setBackground(new Color(186, 90, 90));
        contentPane.setBounds(0, 0, 1515, 790);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Initialize labels
        lbltxtBanDat = new JLabel("Đã đặt bàn: ");
        lblTenNhanVien = new JLabel();
        lblThoiGian = new JLabel();
        lbltxtSoLuongSP = new JLabel("0");
        lbltxtPhiVAT = new JLabel("0 đ");
        lbltxtTongTien = new JLabel("0 đ");
        lblThongBao = new JLabel();
        
        // Initialize text fields with RoundedTextField
        txtSdt = new RoundedTextField(10);
        txtHoTen = new RoundedTextField(10);
        txtTienKhachDua = new RoundedTextField(10);
        txtTienThua = new RoundedTextField(10);
        txtMaSPThem = new RoundedTextField(10);
        
        // Initialize radio buttons with proper ButtonGroup
        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        javax.swing.ButtonGroup genderGroup = new javax.swing.ButtonGroup();
        genderGroup.add(radNam);
        genderGroup.add(radNu);
        
        // Initialize combo box
        cboThanhToan = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        
        // Initialize tables
        tblSanPhamChon = new JTable();
        tblKhuyenMai = new JTable();
        
        // Initialize panels
        pnlMenu = new JPanel();
        pnlTrangChu = new JPanel();
        pnlTienMat = new JPanel();
        
        // Initialize other panels
        pnlHoaDon = new pnlHoaDon();
        pnlKhuyenMai = new pnlKhuyenMai();
        pnlSanPham = new pnlSanPham(this);
        pnlKhachHang = new pnlKhachHang(this);
        pnlThongKe = new pnlThongKe(this);
        pnlNhanVien = new pnlNhanVien(this);
        
        // Set up the rest of the UI
        setupUI();
        setupListeners();
        loadInitialData();
    }
    
    private void setupUI() {
        // Initialize the content pane
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 51, 51));
        contentPane.setBounds(0, 0, 1515, 790);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Set up menu panel - Always expanded
        pnlMenu = new JPanel();
        pnlMenu.setBackground(new Color(64, 64, 64));
        pnlMenu.setBounds(0, 0, 248, 808);
        contentPane.add(pnlMenu);
        pnlMenu.setLayout(null);

        // Create and set up menu buttons
        setupMenuButtons();

        // Initialize main content panel (pnlTrangChu)
        pnlTrangChu = new JPanel();
        pnlTrangChu.setBackground(new Color(51, 51, 51));
        pnlTrangChu.setBounds(258, 58, 1243, 697);
        contentPane.add(pnlTrangChu);
        pnlTrangChu.setLayout(new BorderLayout(10, 10));

        // Create the dashboard layout
        setupDashboard();

        // Initialize other panels with adjusted bounds
        setupOtherPanels();

        // Set up the flag control panel
        setupFlagControl();
    }
    
    private void setupDashboard() {
        // Main dashboard panel with grid layout
        JPanel pnlDashboard = new JPanel(new BorderLayout(10, 10));
        pnlDashboard.setBackground(new Color(51, 51, 51));
        pnlDashboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top section - Quick Actions Bar
        JPanel pnlQuickActions = createQuickActionsPanel();
        pnlDashboard.add(pnlQuickActions, BorderLayout.NORTH);

        // Center section with grid layout
        JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlCenter.setBackground(new Color(51, 51, 51));

        // Table Overview Panel
        JPanel pnlTableOverview = createTableOverviewPanel();
        pnlCenter.add(pnlTableOverview);

        // Active Orders Dashboard
        JPanel pnlActiveOrders = createActiveOrdersPanel();
        pnlCenter.add(pnlActiveOrders);

        // Daily Summary Panel with Popular Items
        JPanel pnlDailySummary = createDailySummaryPanel();
        pnlCenter.add(pnlDailySummary);

        pnlDashboard.add(pnlCenter, BorderLayout.CENTER);
        pnlTrangChu.add(pnlDashboard);
    }

    private JPanel createQuickActionsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(new Color(64, 64, 64));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search bar
        JPanel pnlSearch = new JPanel(new BorderLayout(5, 0));
        pnlSearch.setBackground(new Color(64, 64, 64));
        JTextField txtSearch = new RoundedTextField(20);
        txtSearch.setPreferredSize(new Dimension(300, 35));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlSearch.add(txtSearch, BorderLayout.CENTER);

        // Quick action buttons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlButtons.setBackground(new Color(64, 64, 64));

        // Create "Đơn mới" button with special handling
        JButton btnDonMoi = createStyledButton("Đơn mới", new Color(102, 187, 106));
        btnDonMoi.addActionListener(e -> {
            showDatBanForm();
        });

        // Create other action buttons
        JButton btnInHoaDon = createStyledButton("In hóa đơn", new Color(41, 182, 246));
        JButton btnKhuyenMai = createStyledButton("Khuyến mãi", new Color(255, 167, 38));

        pnlButtons.add(btnDonMoi);
        pnlButtons.add(btnInHoaDon);
        pnlButtons.add(btnKhuyenMai);

        panel.add(pnlSearch, BorderLayout.WEST);
        panel.add(pnlButtons, BorderLayout.CENTER);
        return panel;
    }

    private void showDatBanForm() {
        pnlTrangChu.setVisible(false);
        pnlDatBan.setBounds(258, 58, 1243, 697); // Ensure correct bounds
        pnlDatBan.setVisible(true);
        pnlDatBan.revalidate();
        pnlDatBan.repaint();
    }

    private JPanel createTableOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(64, 64, 64));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Tổng quan bàn",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));

        // Table grid
        JPanel pnlTables = new JPanel(new GridLayout(0, 5, 5, 5));
        pnlTables.setBackground(new Color(64, 64, 64));
        
        // Add table buttons
        for (Ban ban : bienStatic.bans) {
            pnlTables.add(taoBtnBan(ban));
        }

        JScrollPane scrollPane = new JScrollPane(pnlTables);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(64, 64, 64));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createActiveOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(64, 64, 64));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Đơn hàng đang hoạt động",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));

        // Create table for active orders
        String[] columns = {"Bàn", "Thời gian", "Trạng thái", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tblActiveOrders = new JTable(model);
        styleTables(tblActiveOrders);

        // Add occupied tables to the active orders table
        for (Ban ban : bienStatic.bans) {
            if (!ban.isTinhTrang()) {  // If table is occupied (đã đặt)
                model.addRow(new Object[]{
                    ban.getMaBan(),
                    new SimpleDateFormat("HH:mm:ss").format(new Date()),
                    "Đang phục vụ",
                    "0 đ"  // You might want to fetch actual order amount here
                });
            }
        }

        JScrollPane scrollPane = new JScrollPane(tblActiveOrders);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDailySummaryPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(64, 64, 64));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Tổng kết ngày",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));

        // Create grid for summary metrics
        JPanel pnlMetrics = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlMetrics.setBackground(new Color(64, 64, 64));

        // Add metric labels
        addMetricPair(pnlMetrics, "Tổng doanh thu:", "0 đ");
        addMetricPair(pnlMetrics, "Số lượng khách:", "0");

        // Popular items list
        String[] columns = {"Món ăn", "Số lượng đã bán", "Doanh thu"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable tblPopularItems = new JTable(model);
        styleTables(tblPopularItems);

        // TODO: Add logic to fetch and display most frequently ordered items
        model.addRow(new Object[]{"Lẩu Thái", "15", "1,500,000 đ"});
        model.addRow(new Object[]{"Bò nướng", "12", "960,000 đ"});
        model.addRow(new Object[]{"Hải sản", "10", "2,000,000 đ"});

        JScrollPane scrollPane = new JScrollPane(tblPopularItems);
        scrollPane.setBorder(null);

        panel.add(pnlMetrics, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void addMetricPair(JPanel panel, String label, String value) {
        JLabel lblMetric = new JLabel(label);
        lblMetric.setForeground(Color.WHITE);
        lblMetric.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblValue = new JLabel(value);
        lblValue.setForeground(Color.YELLOW);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        panel.add(lblMetric);
        panel.add(lblValue);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bgColor);
            }
            public void mousePressed(MouseEvent e) {
                btn.setBackground(bgColor.darker());
            }
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
        
        return btn;
    }

    private void setupTables() {
        // Set up SanPhamChon table
        tblSanPhamChon.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã SP", "Tên SP", "Kích thước", "Giá", "Loại SP", "Số lượng", "Thành tiền"}
        ));
        
        // Set up KhuyenMai table
        tblKhuyenMai.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã KM", "Mã SP", "Giá gốc", "Giá KM"}
        ));
    }

    private void setupListeners() {
        btnTrangChu.addActionListener(e -> showPanel(pnlTrangChu));
        btnHoaDon.addActionListener(e -> showPanel(pnlHoaDon));
        btnKhuyenMai.addActionListener(e -> showPanel(pnlKhuyenMai));
        btnSanPham.addActionListener(e -> showPanel(pnlSanPham));
        btnKhachHang.addActionListener(e -> showPanel(pnlKhachHang));
        btnNhanVien.addActionListener(e -> showPanel(pnlNhanVien));
        btnThongKe.addActionListener(e -> showPanel(pnlThongKe));
    }

    private void showPanel(JPanel panel) {
        // First remove all panels from the content pane
        Component[] components = contentPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && comp != pnlMenu && comp != controlFlag) {
                contentPane.remove(comp);
            }
        }

        // Re-initialize the panel we want to show
        if (panel == pnlTrangChu) {
            setupDashboard();
        } else if (panel == pnlDatBan) {
            pnlDatBan = new frmDatBan();
            pnlDatBan.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlHoaDon) {
            pnlHoaDon = new pnlHoaDon();
            pnlHoaDon.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlKhuyenMai) {
            pnlKhuyenMai = new pnlKhuyenMai();
            pnlKhuyenMai.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlSanPham) {
            pnlSanPham = new pnlSanPham(this);
            pnlSanPham.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlKhachHang) {
            pnlKhachHang = new pnlKhachHang(this);
            pnlKhachHang.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlNhanVien) {
            pnlNhanVien = new pnlNhanVien(this);
            pnlNhanVien.setBounds(258, 58, 1243, 697);
        } else if (panel == pnlThongKe) {
            pnlThongKe = new pnlThongKe(this);
            pnlThongKe.setBounds(258, 58, 1243, 697);
        }

        // Add and show the new panel
        contentPane.add(panel);
        panel.setVisible(true);
        
        // Refresh the display
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    private void loadInitialData() {
        // Load initial data
        if (bienStatic.bans.size() == 0) {
            bienStatic.bans = BanDAO.getInstance().selectAll();
            Collections.sort(bienStatic.bans, (b1, b2) -> {
                    int num1 = Integer.parseInt(b1.getMaBan());
                    int num2 = Integer.parseInt(b2.getMaBan());
                    return Integer.compare(num1, num2);
            });
        }
        
        // Start time updates
        capNhatThoiGian();
    }
    
	private void updateTienThua() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        if(!txtTienKhachDua.getText().trim().equals("")) {
        	txtTienThua.setText(fm.format(Double.parseDouble(txtTienKhachDua.getText())* 1000 - tongTien) + "");
        }
	}
    
	 private JPanel addProduct(String name, String imagePath) {
	        JPanel productPanel = new JPanel();
	        productPanel.setLayout(new BorderLayout());

	        JLabel productImage = new JLabel(new ImageIcon(imagePath)); 
	        JLabel productName = new JLabel(name, SwingConstants.CENTER); 

	        productPanel.add(productImage, BorderLayout.CENTER);
	        productPanel.add(productName, BorderLayout.SOUTH);

	        return productPanel;  
	    }

	@Override
	public void mouseClicked(MouseEvent e) {
        // No longer needed since menu is always expanded
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
	
	public static ArrayList<SanPham> loadCafeProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectCafe(); 
    }

    public static ArrayList<SanPham> loadPastryProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectCake(); 
    }

    public static ArrayList<SanPham> loadDrinkProducts() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        return sanPhamDAO.selectOrther(); 
    }

    public static void themSanPhamMua(SanPham sanPham) {
    	dsSanPham.add(sanPham);
    	JOptionPane.showMessageDialog(null, "Đã thêm!");
    }
    
    //Xử lý tranh chính
    public static void updateTable(JTable table, ArrayList<ChiTietHoaDon> chiTietHoaDons) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
        	System.out.println(chiTietHoaDon);
            String maSanPham = chiTietHoaDon.getSanPham().getMaSanPham();
            String tenSanPham = chiTietHoaDon.getSanPham().getTenSanPham();
            String kichThuoc = chiTietHoaDon.getKt().name(); 
            Double gia = chiTietHoaDon.getGiaBan(); //getKichThuocGia().values().iterator().next()
//            if(chiTietHoaDon.getSanPham().getKhuyenMai() != null && chiTietHoaDon.getSanPham().getKhuyenMai().isTrangThai())
//            	gia = gia - (gia * chiTietHoaDon.getSanPham().getKhuyenMai().getPhanTramGiamGia()/100);
            String loaiSanPham = chiTietHoaDon.getSanPham().getLoaiSanPham().getTenloai(); 
            int soLuong = chiTietHoaDon.getSoLuong();
            double thanhTien = gia * soLuong;
            model.addRow(new Object[] { maSanPham, tenSanPham, kichThuoc, gia, loaiSanPham, soLuong, thanhTien });
        }
    }
    //Khuyen mai
    public static void updateTableKM(JTable table, ArrayList<ChiTietHoaDon> chiTietHoaDons) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.setRowCount(0);
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
    		if(chiTietHoaDon.getSanPham().getKhuyenMai() != null && chiTietHoaDon.getSanPham().getKhuyenMai().isTrangThai()) {
    			String maKM = chiTietHoaDon.getSanPham().getKhuyenMai().getMaKhuyenMai();
    			String maSanPham = chiTietHoaDon.getSanPham().getMaSanPham();
                Double gia = chiTietHoaDon.getSanPham().getKichThuocGia().values().iterator().next(); 
                Double giaKM = gia - (gia * chiTietHoaDon.getSanPham().getKhuyenMai().getPhanTramGiamGia()/100);
                model.addRow(new Object[] { maKM, maSanPham, gia, giaKM});
    		} 
        }
    }
    //Thong tin khách hàng
    //Check thông tin khách hàng
    public boolean checkTT() {
    	if(tpKhungSanPham.getCTHD().size() == 0) {
    		JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào!");
    		return false;
    	}
    	if(txtSdt.getText().trim().equals("")) {
    		JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại khách hàng");
    		txtSdt.requestFocus();
    		return false;
    	}else {
    		if(!txtSdt.getText().trim().matches("^(0)[0-9]{9}$")) {
        		JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");
        		txtSdt.requestFocus();
        		return false;
        	}
    	}
    	if(txtHoTen.getText().trim().equals("")) {
    		JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên khách hàng!");
    		txtHoTen.requestFocus();
    		return false;
    	}
    	if(!radNam.isSelected() && !radNu.isSelected()) {
    		JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính!");
    		return false;
    	}
    	String pttt = (String) cboThanhToan.getSelectedItem();
    	if(!pttt.equals("Chuyển khoản")) {
    		try {
    			if(txtTienKhachDua.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Vui lòng chọn hoặc nhập tiền khách đưa!");
        			txtTienKhachDua.requestFocus();
        			return false;
        		}
    			Double tkd = Double.parseDouble(txtTienKhachDua.getText());
        		if(tkd*1000 - tongTien < 0) {
        			JOptionPane.showMessageDialog(null, "Không đủ tiền thanh toán!");
        			txtTienKhachDua.requestFocus();
        			return false;
        		}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(pnlTienMat, "Tiền phải là số!");
				txtTienKhachDua.requestFocus();
    			return false;
			}
    	}
    	return true;
    }
    //Thông tin thanh toán
    public static void updateTTThanhToan(ArrayList<ChiTietHoaDon> chiTietHoaDons) {
    	int soLuong = chiTietHoaDons.size();
    	double phiVAT = 0;
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
			phiVAT += chiTietHoaDon.tinhVAT();
		}
    	tongTien = phiVAT;
    	for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) {
    		tongTien += chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong();
		}
    	NumberFormat fm = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    	lbltxtSoLuongSP.setText(soLuong + "");
    	lbltxtPhiVAT.setText(fm.format(phiVAT));
    	lbltxtTongTien.setText(fm.format(tongTien));
    }
    private JButton taoBtnBan(Ban ban) {
    	JButton btnBan = new JButton(ban.getMaBan());
        btnBan.setPreferredSize(new Dimension(100, 100));
        btnBan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBan.setForeground(Color.WHITE);
        btnBan.setFocusPainted(false);
        btnBan.setBorderPainted(true);
        btnBan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
    	if(ban.isTinhTrang()) {
    		btnBan.setBackground(new Color(128, 255, 128));  // Green for available
        } else {
    		btnBan.setBackground(new Color(255, 53, 53));    // Red for occupied
    	}
        
        btnBan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBan.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnBan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
        });
        
    	return btnBan;
    }
    private void capNhatThoiGian() {
    	timer = new Timer(); 
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    String tg = sdf.format(new Date());
                    lblThoiGian.setText(tg); 
                });
            }
        }, 0, 1000);
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

    private void styleTables(JTable table) {
        table.setBackground(new Color(51, 51, 51));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(90, 90, 90));
        table.setSelectionBackground(new Color(90, 90, 90));
        table.setSelectionForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(64, 64, 64));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void setupFlagControl() {
        controlFlag = new JPanel();
        controlFlag.setBackground(new Color(64, 64, 64));
        controlFlag.setBounds(0, 0, 1515, 50);
        contentPane.add(controlFlag);
        controlFlag.setLayout(null);

        // Add time label
        lblThoiGian = new JLabel();
        lblThoiGian.setForeground(Color.WHITE);
        lblThoiGian.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblThoiGian.setBounds(1400, 11, 100, 30);
        controlFlag.add(lblThoiGian);

        // Add employee name label
        lblTenNhanVien = new JLabel("Nhân viên: " + bienStatic.name);
        lblTenNhanVien.setForeground(Color.WHITE);
        lblTenNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTenNhanVien.setBounds(1200, 11, 200, 30);
        controlFlag.add(lblTenNhanVien);
    }

    private void setupMenuButtons() {
        // Create and set up menu buttons
        btnTrangChu = createMenuButton("Trang chủ", "/icon/iconTrangChu.png", 0);
        btnHoaDon = createMenuButton("Hoá đơn", "/icon/iconHoaDon.png", 60);
        btnKhuyenMai = createMenuButton("Khuyến mãi", "/icon/iconKhuyenMai.png", 120);
        btnSanPham = createMenuButton("Sản phẩm", "/icon/iconSanPham.png", 180);
        btnKhachHang = createMenuButton("Khách hàng", "/icon/iconKhachHang.png", 240);
        btnNhanVien = createMenuButton("Nhân viên", "/icon/iconNhanVien.png", 300);
        btnThongKe = createMenuButton("Thống kê", "/icon/iconThongKe.png", 360);

        // Add buttons to menu panel
        pnlMenu.add(btnTrangChu);
        pnlMenu.add(btnHoaDon);
        pnlMenu.add(btnKhuyenMai);
        pnlMenu.add(btnSanPham);
        pnlMenu.add(btnKhachHang);
        pnlMenu.add(btnNhanVien);
        pnlMenu.add(btnThongKe);
    }

    private void setupOtherPanels() {
        // Initialize other panels with adjusted bounds
        pnlHoaDon = new pnlHoaDon();
        pnlHoaDon.setBounds(258, 58, 1243, 697);
        pnlHoaDon.setVisible(false);
        contentPane.add(pnlHoaDon);

        pnlDatBan = new frmDatBan();
        pnlDatBan.setBounds(258, 58, 1243, 697);
        pnlDatBan.setVisible(false);
        contentPane.add(pnlDatBan);

        pnlKhuyenMai = new pnlKhuyenMai();
        pnlKhuyenMai.setBounds(258, 58, 1243, 697);
        pnlKhuyenMai.setVisible(false);
        contentPane.add(pnlKhuyenMai);

        pnlSanPham = new pnlSanPham(this);
        pnlSanPham.setBounds(258, 58, 1243, 697);
        pnlSanPham.setVisible(false);
        contentPane.add(pnlSanPham);

        pnlKhachHang = new pnlKhachHang(this);
        pnlKhachHang.setBounds(258, 58, 1243, 697);
        pnlKhachHang.setVisible(false);
        contentPane.add(pnlKhachHang);

        pnlThongKe = new pnlThongKe(this);
        pnlThongKe.setBounds(258, 58, 1243, 697);
        pnlThongKe.setVisible(false);
        contentPane.add(pnlThongKe);

        pnlNhanVien = new pnlNhanVien(this);
        pnlNhanVien.setBounds(258, 58, 1243, 697);
        pnlNhanVien.setVisible(false);
        contentPane.add(pnlNhanVien);
    }

    private JButton createMenuButton(String text, String iconPath, int yPosition) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(64, 64, 64));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBounds(0, yPosition, 248, 50);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("Could not load icon: " + iconPath);
        }
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(new Color(90, 90, 90));
            }
            public void mouseExited(MouseEvent evt) {
                btn.setBackground(new Color(64, 64, 64));
            }
        });
        
        return btn;
    }

}
