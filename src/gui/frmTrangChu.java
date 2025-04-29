package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controler.bienStatic;
import dao.BanDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.SanPham;

public class frmTrangChu extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlMenu;
    private JPanel pnlMenuIcon;
    private JPanel pnlTrangChu;
    private JPanel pnlTienMat;
    private JPanel controlFlag;
    private JPanel pnlTables;
    
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
    
    private JButton currentSelectedButton = null;
    private final Color MENU_DEFAULT_COLOR = new Color(64, 64, 64);
    private final Color MENU_HOVER_COLOR = new Color(90, 90, 90);
    private final Color TEXT_DEFAULT_COLOR = Color.WHITE;
    private final Color TEXT_SELECTED_COLOR = new Color(41, 182, 246);
    private int menuWidth = 248; // Will be calculated based on longest text
    private final int TOP_PANEL_HEIGHT = 58;
    
    private tpKhungSanPham tpKhungSanPham;
    private SanPhamDAO sanPhamDAO;
    
	/**
	 * Create the frame.
	 */
	public frmTrangChu() {
        initComponents();
        setupWindowListener();
        // Show Trang chủ by default
        showPanel(pnlTrangChu);
        sanPhamDAO = new SanPhamDAO();
        setupDashboard();
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
    
    private void setupWindowListener() {
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustPanelSizes();
            }
        });
    }
    
    private void adjustPanelSizes() {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        
        // Adjust menu panel height to match frame
        if (pnlMenu != null) {
            pnlMenu.setBounds(0, 0, menuWidth, frameHeight);
        }
        
        // Adjust control flag width
        if (controlFlag != null) {
            controlFlag.setBounds(0, 0, frameWidth, TOP_PANEL_HEIGHT);
        }
        
        // Calculate content width and adjust all content panels
        int contentWidth = frameWidth - menuWidth - 10; // 10px padding
        int contentHeight = frameHeight - TOP_PANEL_HEIGHT - 35; // 35px for bottom margin
        int contentX = menuWidth + 5; // 5px padding from menu
        
        Component[] components = contentPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && comp != pnlMenu && comp != controlFlag) {
                comp.setBounds(contentX, TOP_PANEL_HEIGHT, contentWidth, contentHeight);
            }
        }
        
        // Ensure time and user info stay in correct position
        if (lblThoiGian != null) {
            lblThoiGian.setBounds(frameWidth - 115, 11, 100, 30);
        }
        if (lblTenNhanVien != null) {
            lblTenNhanVien.setBounds(frameWidth - 315, 11, 200, 30);
        }
        
        revalidate();
        repaint();
    }

    private void setupUI() {
        // Set minimum size for the frame
        setMinimumSize(new Dimension(1000, 600));
        
        // Initialize the content pane with null layout for absolute positioning
        contentPane = new JPanel(null);
        contentPane.setBackground(new Color(51, 51, 51));
        setContentPane(contentPane);

        // Set up menu panel
        pnlMenu = new JPanel(null);
        pnlMenu.setBackground(new Color(64, 64, 64));
        contentPane.add(pnlMenu);

        // Create and set up menu buttons
        setupMenuButtons();

        // Initialize main content panel (pnlTrangChu)
        pnlTrangChu = new JPanel(new BorderLayout(10, 10));
        pnlTrangChu.setBackground(new Color(51, 51, 51));
        
        // Create the dashboard layout
        setupDashboard();

        // Initialize other panels
        setupOtherPanels();

        // Set up the flag control panel
        setupFlagControl();
        
        // Initial adjustment of panel sizes
        adjustPanelSizes();
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

        // Ensure tables are loaded
        loadInitialData();
        
        // Force initial update of table grid for Lau 1
        if (pnlTables != null) {
            updateTableGrid(pnlTables, 1);
        }
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

        pnlButtons.add(btnDonMoi);

        panel.add(pnlSearch, BorderLayout.WEST);
        panel.add(pnlButtons, BorderLayout.CENTER);
        return panel;
    }

    private void showDatBanForm() {
        // Hide all other panels
        if (pnlTrangChu != null) pnlTrangChu.setVisible(false);
        if (pnlHoaDon != null) pnlHoaDon.setVisible(false);
        if (pnlKhuyenMai != null) pnlKhuyenMai.setVisible(false);
        if (pnlSanPham != null) pnlSanPham.setVisible(false);
        if (pnlKhachHang != null) pnlKhachHang.setVisible(false);
        if (pnlNhanVien != null) pnlNhanVien.setVisible(false);
        if (pnlThongKe != null) pnlThongKe.setVisible(false);

        // Remove existing pnlDatBan if it exists
        if (pnlDatBan != null) {
            contentPane.remove(pnlDatBan);
        }

        // Create new instance of frmDatBan
        pnlDatBan = new frmDatBan();
        // Position it right next to the menu
        pnlDatBan.setBounds(menuWidth + 5, TOP_PANEL_HEIGHT, getWidth() - menuWidth - 10, getHeight() - TOP_PANEL_HEIGHT - 35);
        contentPane.add(pnlDatBan);
        pnlDatBan.setVisible(true);

        // Refresh the display
        contentPane.revalidate();
        contentPane.repaint();
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

        // Create tabs for floors
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        tabPanel.setBackground(new Color(64, 64, 64));
        
        // Track currently selected floor button
        final JButton[] selectedFloorButton = new JButton[1];
        
        JButton[] floorButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            final int floor = i + 1;
            floorButtons[i] = createStyledButton("Lầu " + floor, new Color(75, 75, 75));
            floorButtons[i].setPreferredSize(new Dimension(100, 35));
            floorButtons[i].setForeground(Color.WHITE);
            
            if (i == 0) {
                selectedFloorButton[0] = floorButtons[i];
                floorButtons[i].setForeground(new Color(41, 182, 246));
                floorButtons[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
            }
            
            floorButtons[i].addActionListener(e -> {
                if (selectedFloorButton[0] != null) {
                    selectedFloorButton[0].setForeground(Color.WHITE);
                    selectedFloorButton[0].setBorder(BorderFactory.createEmptyBorder());
                }
                JButton clickedButton = (JButton)e.getSource();
                clickedButton.setForeground(new Color(41, 182, 246));
                clickedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
                selectedFloorButton[0] = clickedButton;
                
                updateTableGrid(pnlTables, floor);
            });
            
            tabPanel.add(floorButtons[i]);
        }

        // Table grid with increased scroll speed
        pnlTables = new JPanel(new GridLayout(0, 5, 10, 10));
        pnlTables.setBackground(new Color(64, 64, 64));
        pnlTables.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(pnlTables);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(64, 64, 64));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Increased scroll speed
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Initial load of floor 1
        updateTableGrid(pnlTables, 1);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(64, 64, 64));
        mainPanel.add(tabPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(mainPanel, BorderLayout.CENTER);
        return panel;
    }

    private void updateTableGrid(JPanel pnlTables, int floor) {
        pnlTables.removeAll();
        
        // Sort tables by number
        ArrayList<Ban> sortedTables = new ArrayList<>(bienStatic.bans);
        Collections.sort(sortedTables, (b1, b2) -> {
            int num1 = Integer.parseInt(b1.getMaBan().replaceAll("\\D+", ""));
            int num2 = Integer.parseInt(b2.getMaBan().replaceAll("\\D+", ""));
                    return Integer.compare(num1, num2);
        });
        
        // Create a grid layout with appropriate number of columns
        int numTables = (int) sortedTables.stream()
            .filter(b -> b.getLau() == floor)
            .count();
        
        int columns = 4; // Fixed number of columns
        int rows = (numTables + columns - 1) / columns; // Calculate needed rows
        pnlTables.setLayout(new GridLayout(rows, columns, 10, 10));
        
        // Add table buttons for the selected floor
        for (Ban ban : sortedTables) {
            if (ban.getLau() == floor) {
                JButton btnBan = createTableButton(ban);
                pnlTables.add(btnBan);
            }
        }
        
        // Add scroll speed
        if (pnlTables.getParent() instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane) pnlTables.getParent();
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        }
        
        pnlTables.revalidate();
        pnlTables.repaint();
    }

    private JButton createTableButton(Ban ban) {
        JButton btnBan = new JButton();
        btnBan.setLayout(new BorderLayout(5, 5));
        btnBan.setPreferredSize(new Dimension(100, 100));
        btnBan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnBan.setFocusPainted(false);
        
        // Table number at top
        JLabel lblNumber = new JLabel(ban.getMaBan(), SwingConstants.CENTER);
        lblNumber.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNumber.setForeground(Color.WHITE);
        btnBan.add(lblNumber, BorderLayout.NORTH);
        
        // Status indicator in center
        JPanel statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(60, 60));
        
        if (ban.isTinhTrang()) {
            btnBan.setBackground(new Color(102, 187, 106));     // Green for available
            statusPanel.setBackground(new Color(102, 187, 106));
        } else {
            btnBan.setBackground(new Color(239, 83, 80));       // Red for occupied
            statusPanel.setBackground(new Color(239, 83, 80));
        }
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(statusPanel, BorderLayout.CENTER);
        btnBan.add(centerPanel, BorderLayout.CENTER);

        btnBan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBan.setBorder(BorderFactory.createLineBorder(new Color(41, 182, 246), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBan.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
        });
        
        return btnBan;
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

        // Search panel at top
        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(new Color(64, 64, 64));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JTextField txtSearch = new RoundedTextField(20);
        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(txtSearch, BorderLayout.CENTER);

        // Create table model with custom column widths
        String[] columns = {"Bàn", "Lầu", "Thời gian", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable tblActiveOrders = new JTable(model);
        tblActiveOrders.setRowHeight(40); // Increased row height
        styleTables(tblActiveOrders);

        // Set column widths
        tblActiveOrders.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblActiveOrders.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblActiveOrders.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblActiveOrders.getColumnModel().getColumn(3).setPreferredWidth(150);

        // Custom renderer for table number column
        DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 0) { // Table number column
                    setFont(new Font("Segoe UI", Font.BOLD, 14));
                    setHorizontalAlignment(SwingConstants.CENTER);
                } else if (column == 3) { // Total amount column
                    setForeground(new Color(255, 196, 0));
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                return c;
            }
        };

        // Apply renderer to all columns
        for (int i = 0; i < tblActiveOrders.getColumnCount(); i++) {
            tblActiveOrders.getColumnModel().getColumn(i).setCellRenderer(tableRenderer);
        }

        // Add table selection listener
        tblActiveOrders.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblActiveOrders.getSelectedRow() != -1) {
                showTableActions(tblActiveOrders);
            }
        });

        // Create scroll pane with increased speed
        JScrollPane scrollPane = new JScrollPane(tblActiveOrders);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(51, 51, 51));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Add search functionality
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = txtSearch.getText().toLowerCase();
                filterActiveTables(model, searchText);
            }
        });

        // Initial table load
        filterActiveTables(model, "");

        // Layout
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBackground(new Color(64, 64, 64));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        panel.add(mainPanel);
        return panel;
    }

    private void showTableActions(JTable table) {
        int row = table.getSelectedRow();
        if (row != -1) {
            String tableNumber = table.getValueAt(row, 0).toString();
            
            JPopupMenu popup = new JPopupMenu();
            popup.setBackground(new Color(64, 64, 64));
            
            JMenuItem menuThemMon = new JMenuItem("Thêm món");
            menuThemMon.setForeground(Color.WHITE);
            menuThemMon.setBackground(new Color(64, 64, 64));
            menuThemMon.addActionListener(e -> openGoiMonForm(tableNumber));
            
            JMenuItem menuThanhToan = new JMenuItem("Thanh toán");
            menuThanhToan.setForeground(Color.WHITE);
            menuThanhToan.setBackground(new Color(64, 64, 64));
            menuThanhToan.addActionListener(e -> processPayment(tableNumber));
            
            popup.add(menuThemMon);
            popup.add(menuThanhToan);
            
            Point p = table.getLocationOnScreen();
            int row_height = table.getRowHeight();
            popup.show(table, table.getSelectedColumn() * 100, 
                      (table.getSelectedRow() * row_height) + row_height/2);
        }
    }

    private void openGoiMonForm(String tableNumber) {
        // Hide all current panels
        Component[] components = contentPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && comp != pnlMenu && comp != controlFlag) {
                comp.setVisible(false);
            }
        }

        // Find the selected table
        Ban selectedTable = null;
        for (Ban ban : bienStatic.bans) {
            if (ban.getMaBan().equals(tableNumber)) {
                selectedTable = ban;
                break;
            }
        }
        
        if (selectedTable != null) {
            ArrayList<Ban> selectedTables = new ArrayList<>();
            selectedTables.add(selectedTable);
            
            // Remove existing frmGoiMon if present
            for (Component comp : contentPane.getComponents()) {
                if (comp instanceof frmGoiMon) {
                    contentPane.remove(comp);
                }
            }
            
            // Create and show new frmGoiMon
            frmGoiMon goiMonForm = new frmGoiMon(selectedTables);
            goiMonForm.setBounds(240, 58, 1261, 697); // Aligned perfectly with menu
            contentPane.add(goiMonForm);
            goiMonForm.setVisible(true);
            contentPane.revalidate();
            contentPane.repaint();
        }
    }

    private void processPayment(String tableNumber) {
        // TODO: Implement payment processing
        JOptionPane.showMessageDialog(this, 
            "Xử lý thanh toán cho bàn " + tableNumber, 
            "Thanh toán", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void filterActiveTables(DefaultTableModel model, String searchText) {
        model.setRowCount(0);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        
        for (Ban ban : bienStatic.bans) {
            if (!ban.isTinhTrang() && 
                (ban.getMaBan().toLowerCase().contains(searchText) || 
                 ("Lầu " + ban.getLau()).toLowerCase().contains(searchText))) {
                model.addRow(new Object[]{
                    ban.getMaBan(),
                    "Lầu " + ban.getLau(),
                    timeFormat.format(new Date()),
                    currencyFormat.format(calculateTableTotal(ban))
                });
            }
        }
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

        // Popular items list with images
        String[] columns = {"", "Món ăn", "Số lượng", "Doanh thu"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? ImageIcon.class : String.class;
            }
        };
        
        JTable tblPopularItems = new JTable(model);
        tblPopularItems.setRowHeight(60); // Increased height for images
        styleTables(tblPopularItems);
        
        // Set column widths
        tblPopularItems.getColumnModel().getColumn(0).setPreferredWidth(60);  // Image
        tblPopularItems.getColumnModel().getColumn(1).setPreferredWidth(150); // Name
        tblPopularItems.getColumnModel().getColumn(2).setPreferredWidth(80);  // Quantity
        tblPopularItems.getColumnModel().getColumn(3).setPreferredWidth(100); // Revenue

        // TODO: Add logic to fetch and display most frequently ordered items with images
        addPopularItem(model, "/images/lau_thai.png", "Lẩu Thái", "15", "1,500,000 đ");
        addPopularItem(model, "/images/bo_nuong.png", "Bò nướng", "12", "960,000 đ");
        addPopularItem(model, "/images/hai_san.png", "Hải sản", "10", "2,000,000 đ");

        JScrollPane scrollPane = new JScrollPane(tblPopularItems);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panel.add(pnlMetrics, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void addPopularItem(DefaultTableModel model, String imagePath, String name, String quantity, String revenue) {
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            model.addRow(new Object[]{scaledIcon, name, quantity, revenue});
        } catch (Exception e) {
            // If image loading fails, add row without image
            model.addRow(new Object[]{null, name, quantity, revenue});
        }
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
        // Update button highlighting
        if (currentSelectedButton != null) {
            currentSelectedButton.setForeground(TEXT_DEFAULT_COLOR);
            currentSelectedButton.setBorder(BorderFactory.createEmptyBorder());
        }
        
        // Set new selected button based on panel
        if (panel == pnlTrangChu) {
            currentSelectedButton = btnTrangChu;
        } else if (panel == pnlHoaDon) {
            currentSelectedButton = btnHoaDon;
        } else if (panel == pnlKhuyenMai) {
            currentSelectedButton = btnKhuyenMai;
        } else if (panel == pnlSanPham) {
            currentSelectedButton = btnSanPham;
        } else if (panel == pnlKhachHang) {
            currentSelectedButton = btnKhachHang;
        } else if (panel == pnlNhanVien) {
            currentSelectedButton = btnNhanVien;
        } else if (panel == pnlThongKe) {
            currentSelectedButton = btnThongKe;
        }

        // Highlight new selected button
        if (currentSelectedButton != null) {
            currentSelectedButton.setForeground(TEXT_SELECTED_COLOR);
            currentSelectedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, TEXT_SELECTED_COLOR));
        }

        // Remove all content panels
        Component[] components = contentPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel && comp != pnlMenu && comp != controlFlag) {
                contentPane.remove(comp);
            }
        }

        // Add and show the new panel
        if (panel != null) {
            contentPane.add(panel);
            adjustPanelSizes();
            panel.setVisible(true);
        }

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
        btnTrangChu = createMenuButton("Trang chủ", "/icon/iconHome.png", 0);
        btnHoaDon = createMenuButton("Hoá đơn", "/icon/iconHoaDon.png", 60);
        btnKhuyenMai = createMenuButton("Khuyến mãi", "/icon/iconKhuyenMai.png", 120);
        btnSanPham = createMenuButton("Sản phẩm", "/icon/iconSanPham.png", 180);
        btnKhachHang = createMenuButton("Khách hàng", "/icon/iconKhachHang.png", 240);
        btnNhanVien = createMenuButton("Nhân viên", "/icon/iconNhanVien.png", 300);
        btnThongKe = createMenuButton("Thống kê", "/icon/iconThongKe.png", 360);

        // Calculate required width based on longest text
        int maxWidth = 0;
        Font menuFont = new Font("Segoe UI", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(menuFont);
        
        JButton[] buttons = {btnTrangChu, btnHoaDon, btnKhuyenMai, btnSanPham, 
                           btnKhachHang, btnNhanVien, btnThongKe};
        
        for (JButton btn : buttons) {
            int textWidth = fm.stringWidth(btn.getText());
            maxWidth = Math.max(maxWidth, textWidth);
        }
        
        // Add padding for icon (40px) and left/right margins (20px each)
        menuWidth = maxWidth + 80;
        
        // Update menu panel width
        pnlMenu.setPreferredSize(new Dimension(menuWidth, getHeight()));
        
        // Add buttons to menu panel
        pnlMenu.add(btnTrangChu);
        pnlMenu.add(btnHoaDon);
        pnlMenu.add(btnKhuyenMai);
        pnlMenu.add(btnSanPham);
        pnlMenu.add(btnKhachHang);
        pnlMenu.add(btnNhanVien);
        pnlMenu.add(btnThongKe);

        // Update button widths
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(menuWidth, 50));
        }
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
        btn.setForeground(TEXT_DEFAULT_COLOR);
        btn.setBackground(MENU_DEFAULT_COLOR);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBounds(0, yPosition, menuWidth, 50);
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
            btn.setIconTextGap(10);
        } catch (Exception e) {
            System.err.println("Could not load icon: " + iconPath);
        }
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (btn != currentSelectedButton) {
                    btn.setBackground(MENU_HOVER_COLOR);
                }
            }
            public void mouseExited(MouseEvent evt) {
                if (btn != currentSelectedButton) {
                    btn.setBackground(MENU_DEFAULT_COLOR);
                }
            }
        });
        
        // Set initial selection for Trang chu
        if (text.equals("Trang chủ")) {
            currentSelectedButton = btn;
            btn.setForeground(TEXT_SELECTED_COLOR);
            btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, TEXT_SELECTED_COLOR));
        }
        
        return btn;
	}

    // Helper method to calculate total amount for a table
    private double calculateTableTotal(Ban ban) {
        // TODO: Implement actual total calculation based on orders
        // This is a placeholder - replace with actual calculation from the database
        return 0.0;
    }

}
