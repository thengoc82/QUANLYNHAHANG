package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.LoaiSanPhamDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.KichThuoc;
import entity.SanPham;

public class frmGoiMon extends JPanel {
    private final ArrayList<Ban> selectedTables;
    private JPanel pnlSanPham;
    private JTable tblOrder;
    private JLabel lblTongTien;
    private JLabel lblKhuyenMai;
    private JLabel lblThanhToan;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private SanPham selectedProduct = null;
    private ArrayList<ChiTietHoaDon> orderItems = new ArrayList<>();
    private String selectedCategory = "Lẩu và món Nướng";
    private ArrayList<SanPham> allProducts = null;
    private JButton selectedCategoryButton = null;
    private final Color SELECTED_COLOR = new Color(90, 90, 90);
    private final Color DEFAULT_COLOR = new Color(75, 75, 75);
    private final Color HOVER_COLOR = new Color(85, 85, 85);
    private JProgressBar progressBar;
    private int currentStep = 2;
    private final String[] STEPS = {"Đặt bàn", "Đặt món", "Thanh toán"};
    private final Color[] STEP_COLORS = {
        new Color(51, 153, 255),  // Blue for table selection
        new Color(255, 153, 51),  // Orange for food ordering
        new Color(51, 204, 51)    // Green for payment
    };
    private ArrayList<SanPham> dsSanPham;
    private SanPhamDAO sanPhamDAO;
    private LoaiSanPhamDAO loaiSanPhamDAO;
    
    public frmGoiMon(ArrayList<Ban> tables) {
        this.selectedTables = tables;
        initComponents();
        sanPhamDAO = SanPhamDAO.getInstance();
        loaiSanPhamDAO = LoaiSanPhamDAO.getInstance();
        allProducts = sanPhamDAO.selectAll(); // Load all products at startup
        filterProductsByCategory("Lẩu & Nướng"); // Show initial category
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBounds(240, 58, 1261, 697); // Adjusted to remove gap with menu
        
        // Progress Bar Panel at the top
        JPanel pnlProgress = new JPanel(new BorderLayout());
        pnlProgress.setBackground(Color.BLACK);
        pnlProgress.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        progressBar = new JProgressBar(0, 100) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw background
                g2d.setColor(new Color(64, 64, 64));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                // Draw steps
                int totalWidth = getWidth();
                int stepWidth = totalWidth / 3;
                
                // Draw step backgrounds
                for (int i = 0; i < 3; i++) {
                    if (i < currentStep) {
                        g2d.setColor(STEP_COLORS[i]);
                    } else {
                        g2d.setColor(new Color(64, 64, 64));
                    }
                    
                    if (i == 0) {
                        g2d.fillRoundRect(i * stepWidth, 0, stepWidth, getHeight(), 10, 10);
                    } else if (i == 2) {
                        g2d.fillRoundRect(i * stepWidth, 0, stepWidth, getHeight(), 10, 10);
                    } else {
                        g2d.fillRect(i * stepWidth, 0, stepWidth, getHeight());
                    }
                }

                // Draw step text
                g2d.setColor(Color.WHITE);
                FontMetrics fm = g2d.getFontMetrics();
                
                for (int i = 0; i < STEPS.length; i++) {
                    String step = STEPS[i];
                    int textWidth = fm.stringWidth(step);
                    int textHeight = fm.getHeight();
                    
                    g2d.setColor(i < currentStep ? Color.WHITE : Color.GRAY);
                    g2d.drawString(step, 
                        i * stepWidth + (stepWidth - textWidth) / 2,
                        (getHeight() + textHeight / 2) / 2);
                }

                g2d.dispose();
            }
        };
        
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(getWidth(), 40));
        progressBar.setBorder(BorderFactory.createEmptyBorder());
        progressBar.setOpaque(false);
        
        pnlProgress.add(progressBar, BorderLayout.CENTER);
        add(pnlProgress, BorderLayout.NORTH);
        
        // Main content panel with left sidebar and product grid
        JPanel pnlContent = new JPanel(new BorderLayout());
        pnlContent.setBackground(Color.BLACK);
        
        // Left Panel - Categories and Selected Tables
        JPanel pnlLeft = new JPanel();
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        pnlLeft.setBackground(Color.BLACK);
        pnlLeft.setPreferredSize(new Dimension(200, 0));
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Categories Panel
        String[] categories = {"Lẩu & Nướng", "Buffet", "Món Thái"};
        for (String category : categories) {
            JButton btnCategory = createCategoryButton(category);
            pnlLeft.add(btnCategory);
            pnlLeft.add(Box.createVerticalStrut(5));
        }
        
        pnlLeft.add(Box.createVerticalStrut(20));
        
        // Selected Tables Panel
        JPanel pnlSelectedTables = new JPanel();
        pnlSelectedTables.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSelectedTables.setBackground(Color.BLACK);
        pnlSelectedTables.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Bàn đã chọn",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));
        pnlSelectedTables.setMaximumSize(new Dimension(200, 60));
        pnlSelectedTables.setPreferredSize(new Dimension(200, 60));
        
        // Create table numbers text horizontally
        StringBuilder tableNumbers = new StringBuilder();
        for (int i = 0; i < selectedTables.size(); i++) {
            tableNumbers.append(selectedTables.get(i).getMaBan());
            if (i < selectedTables.size() - 1) {
                tableNumbers.append(", ");
            }
        }
        
        JLabel lblSelectedTables = new JLabel(tableNumbers.toString());
        lblSelectedTables.setForeground(Color.WHITE);
        lblSelectedTables.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSelectedTables.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSelectedTables.add(lblSelectedTables);
        
        pnlLeft.add(pnlSelectedTables);
        pnlLeft.add(Box.createVerticalGlue());
        
        // Products Grid Panel with faster scrolling
        pnlSanPham = new JPanel(new GridLayout(0, 2, 10, 10));
        pnlSanPham.setBackground(Color.BLACK);
        pnlSanPham.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollSanPham = new JScrollPane(pnlSanPham);
        scrollSanPham.setBorder(null);
        scrollSanPham.getViewport().setBackground(Color.BLACK);
        scrollSanPham.getVerticalScrollBar().setUnitIncrement(16); // Increased scroll speed
        
        // Search panel
        JPanel pnlSearch = new JPanel(new BorderLayout());
        pnlSearch.setBackground(new Color(51, 51, 51));
        JTextField txtSearch = new JTextField();
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlSearch.add(txtSearch, BorderLayout.CENTER);
        pnlSearch.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Right panel - Order details
        JPanel pnlRight = new JPanel(new BorderLayout());
        pnlRight.setBackground(Color.BLACK);
        pnlRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlRight.setPreferredSize(new Dimension(400, 0));
        
        // Order table
        String[] columnNames = {"Tên món ăn", "Kích thước", "Số lượng", "Đơn giá", "Tổng tiền"};  // Removed status column
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Only allow editing quantity column
            }
        };
        tblOrder = new JTable(model);
        tblOrder.setBackground(Color.BLACK);
        tblOrder.setForeground(Color.WHITE);
        tblOrder.getTableHeader().setBackground(Color.BLACK);
        tblOrder.getTableHeader().setForeground(Color.WHITE);
        tblOrder.setGridColor(new Color(70, 70, 70));
        tblOrder.setSelectionBackground(new Color(90, 90, 90));
        tblOrder.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollOrder = new JScrollPane(tblOrder);
        scrollOrder.setBackground(Color.BLACK);
        scrollOrder.getViewport().setBackground(Color.BLACK);
        scrollOrder.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70)));
        scrollOrder.getVerticalScrollBar().setUnitIncrement(16); // Increased scroll speed
        
        // Payment info panel
        JPanel pnlPayment = new JPanel(new GridLayout(3, 2, 5, 5));
        pnlPayment.setBackground(Color.BLACK);
        pnlPayment.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel lblTong = new JLabel("Tổng tiền:");
        lblTong.setForeground(Color.WHITE);
        lblTongTien = new JLabel("0 đ");
        lblTongTien.setForeground(Color.WHITE);
        
        JLabel lblKM = new JLabel("Khuyến mãi:");
        lblKM.setForeground(Color.WHITE);
        lblKhuyenMai = new JLabel("0 đ");
        lblKhuyenMai.setForeground(Color.GREEN);
        
        JLabel lblTT = new JLabel("Thanh toán:");
        lblTT.setForeground(Color.WHITE);
        lblThanhToan = new JLabel("0 đ");
        lblThanhToan.setForeground(Color.YELLOW);
        
        pnlPayment.add(lblTong);
        pnlPayment.add(lblTongTien);
        pnlPayment.add(lblKM);
        pnlPayment.add(lblKhuyenMai);
        pnlPayment.add(lblTT);
        pnlPayment.add(lblThanhToan);
        
        // Bottom panel with payment method and buttons
        JPanel pnlBottom = new JPanel(new BorderLayout(10, 10));
        pnlBottom.setBackground(Color.BLACK);
        
        // Payment method combo
        JPanel pnlPaymentMethod = new JPanel(new BorderLayout());
        pnlPaymentMethod.setBackground(Color.BLACK);
        JComboBox<String> cboPhuongThuc = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        cboPhuongThuc.setPreferredSize(new Dimension(120, 35));
        pnlPaymentMethod.add(cboPhuongThuc, BorderLayout.WEST);
        
        // Action buttons
        JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlButtons.setBackground(Color.BLACK);
        
        JButton btnHuy = createStyledButton("HỦY ĐƠN", new Color(239, 83, 80));
        JButton btnXacNhan = createStyledButton("XÁC NHẬN ĐƠN", new Color(102, 187, 106));
        
        pnlButtons.add(btnHuy);
        pnlButtons.add(btnXacNhan);
        
        pnlBottom.add(pnlPaymentMethod, BorderLayout.WEST);
        pnlBottom.add(pnlButtons, BorderLayout.CENTER);
        
        // Add components to right panel
        JPanel pnlRightContent = new JPanel(new BorderLayout(0, 10));
        pnlRightContent.setBackground(Color.BLACK);
        pnlRightContent.add(scrollOrder, BorderLayout.CENTER);
        pnlRightContent.add(pnlPayment, BorderLayout.SOUTH);
        
        pnlRight.add(pnlRightContent, BorderLayout.CENTER);
        pnlRight.add(pnlBottom, BorderLayout.SOUTH);
        
        // Add button actions
        btnHuy.addActionListener(e -> {
            orderItems.clear();
            updateOrderTable();
            updateTotals();
        });
        
        btnXacNhan.addActionListener(e -> {
            if (validateOrder()) {
                saveOrder();
            }
        });

        // Add all panels to the main content
        pnlContent.add(pnlLeft, BorderLayout.WEST);
        pnlContent.add(scrollSanPham, BorderLayout.CENTER);
        pnlContent.add(pnlRight, BorderLayout.EAST);
        
        add(pnlContent, BorderLayout.CENTER);
        
        // Add button actions
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = txtSearch.getText().toLowerCase();
                filterProducts(searchText);
            }
        });
    }
    
    private JButton createCategoryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(75, 75, 75));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(180, 40));
        btn.setPreferredSize(new Dimension(180, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if (text.equals("Lẩu & Nướng")) {
            selectedCategoryButton = btn;
            btn.setBackground(new Color(90, 90, 90));
        }
        
        btn.addActionListener(e -> {
            if (selectedCategoryButton != null) {
                selectedCategoryButton.setBackground(new Color(75, 75, 75));
            }
            selectedCategoryButton = btn;
            btn.setBackground(new Color(90, 90, 90));
            filterProductsByCategory(text);
        });
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (btn != selectedCategoryButton) {
                    btn.setBackground(new Color(85, 85, 85));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (btn != selectedCategoryButton) {
                    btn.setBackground(new Color(75, 75, 75));
                }
            }
        });
        
        return btn;
    }
    
    private void filterProductsByCategory(String categoryName) {
        SwingWorker<JPanel, Void> worker = new SwingWorker<JPanel, Void>() {
            @Override
            protected JPanel doInBackground() {
                // Create new panel in background
                JPanel newPanel = new JPanel(new GridLayout(0, 2, 10, 10));
                newPanel.setBackground(new Color(51, 51, 51));
                
                String loaiSanPham;
                switch(categoryName) {
                    case "Lẩu & Nướng":
                        loaiSanPham = "Loai03";
                        break;
                    case "Món Thái":
                        loaiSanPham = "Loai02";
                        break;
                    case "Buffet":
                        loaiSanPham = "Loai01";
                        break;
                    default:
                        loaiSanPham = "Loai03";
                }
                
                // Filter and create panels in background
                for (SanPham product : allProducts) {
                    if (product.getLoaiSanPham().getMaLoai().equals(loaiSanPham)) {
                        newPanel.add(createProductPanel(product));
                    }
                }
                
                return newPanel;
            }
            
            @Override
            protected void done() {
                try {
                    JPanel newPanel = get();
                    pnlSanPham.removeAll();
                    pnlSanPham.setLayout(new GridLayout(0, 2, 10, 10));
                    for (Component comp : newPanel.getComponents()) {
                        pnlSanPham.add(comp);
                    }
                    pnlSanPham.revalidate();
                    pnlSanPham.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
    
    private JPanel createProductPanel(SanPham product) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setPreferredSize(new Dimension(200, 220));

        // Create image panel with black background
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.BLACK);
        imagePanel.setLayout(new BorderLayout());
        
        // Create image label
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBackground(Color.BLACK);
        imageLabel.setOpaque(true);
        String imagePath = product.getHinhAnh().trim();
        
        // Load image from resources
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel.setText("No Image");
            imageLabel.setForeground(Color.WHITE);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        }
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        panel.add(imagePanel, BorderLayout.CENTER);

        // Create info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1, 5, 5));
        infoPanel.setBackground(Color.BLACK);

        // Add product name
        JLabel nameLabel = new JLabel(product.getTenSanPham());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add price (using the largest size as display price)
        double maxPrice = 0;
        for (Map.Entry<KichThuoc, Double> entry : product.getKichThuocGia().entrySet()) {
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
            }
        }
        JLabel priceLabel = new JLabel(currencyFormat.format(maxPrice));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setForeground(new Color(220, 20, 60)); // Crimson red

        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);
        
        panel.add(infoPanel, BorderLayout.SOUTH);

        // Add click listener
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showAddToOrderDialog(product);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBorder(BorderFactory.createLineBorder(new Color(41, 182, 246), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        });

        return panel;
    }
    
    private void showAddToOrderDialog(SanPham product) {
        JDialog dialog = new JDialog((Frame)SwingUtilities.getWindowAncestor(this), "Thêm món", true);
        dialog.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Size selection if applicable
        JComboBox<String> cboSize = new JComboBox<>();
        if (product.getLoaiSanPham().isCoPhanLoai()) {
            cboSize.addItem("S");
            cboSize.addItem("M");
            cboSize.addItem("L");
        } else {
            cboSize.addItem("D");
        }
        
        // Quantity spinner
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner spnQuantity = new JSpinner(spinnerModel);
        
        panel.add(new JLabel("Kích thước:"));
        panel.add(cboSize);
        panel.add(new JLabel("Số lượng:"));
        panel.add(spnQuantity);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdd = new JButton("Thêm");
        JButton btnCancel = new JButton("Hủy");
        
        btnAdd.addActionListener(e -> {
            String size = (String)cboSize.getSelectedItem();
            int quantity = (Integer)spnQuantity.getValue();
            addToOrder(product, KichThuoc.valueOf(size), quantity);
            dialog.dispose();
        });
        
        btnCancel.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnCancel);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void addToOrder(SanPham product, KichThuoc size, int quantity) {
        ChiTietHoaDon item = new ChiTietHoaDon(product, quantity, size);
        orderItems.add(item);
        updateOrderTable();
        updateTotals();
    }
    
    private void updateOrderTable() {
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.setRowCount(0);
        
        for (ChiTietHoaDon item : orderItems) {
            model.addRow(new Object[]{
                item.getSanPham().getTenSanPham(),
                item.getKt().name(),
                createQuantityPanel(item),
                currencyFormat.format(item.getGiaBan()),
                currencyFormat.format(item.getGiaBan() * item.getSoLuong())
            });
        }
        
        // Set custom renderer for the quantity column
        tblOrder.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof JPanel) {
                    return (JPanel) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }
    
    private JPanel createQuantityPanel(ChiTietHoaDon item) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel lblQuantity = new JLabel(String.valueOf(item.getSoLuong()), SwingConstants.CENTER);
        lblQuantity.setPreferredSize(new Dimension(30, 25));
        lblQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        panel.add(lblQuantity);
        
        return panel;
    }
    
    private void updateTotals() {
        double total = 0;
        double discount = 0;
        
        for (ChiTietHoaDon item : orderItems) {
            double itemTotal = item.getGiaBan() * item.getSoLuong();
            total += itemTotal;
            
            if (item.getSanPham().getKhuyenMai() != null && 
                item.getSanPham().getKhuyenMai().isTrangThai()) {
                discount += itemTotal * item.getSanPham().getKhuyenMai().getPhanTramGiamGia() / 100;
            }
        }
        
        lblTongTien.setText(currencyFormat.format(total));
        lblKhuyenMai.setText(currencyFormat.format(discount));
        lblThanhToan.setText(currencyFormat.format(total - discount));
    }
    
    private void filterProducts(String searchText) {
        pnlSanPham.removeAll();
        
        // Filter from pre-loaded products instead of querying database again
        for (SanPham product : allProducts) {
            if (product.getTenSanPham().toLowerCase().contains(searchText)) {
                pnlSanPham.add(createProductPanel(product));
            }
        }
        
        pnlSanPham.revalidate();
        pnlSanPham.repaint();
    }
    
    private boolean validateOrder() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một món");
            return false;
        }
        return true;
    }
    
    private void saveOrder() {
        // TODO: Implement order saving logic
        JOptionPane.showMessageDialog(this, "Đã lưu đơn hàng thành công!");
        
        // Return to frmDatBan
        Container parent = getParent();
        if (parent != null) {
            // Remove this panel
            parent.remove(this);
            
            // Create and show new frmDatBan with correct bounds
            frmDatBan datBanForm = new frmDatBan();
            datBanForm.setBounds(258, 58, 1243, 697); // Aligned with menu width
            parent.add(datBanForm);
            
            // Refresh the container
            parent.revalidate();
            parent.repaint();
        }
    }

    private void nextStep() {
        if (currentStep < 3) {
            currentStep++;
            if (progressBar != null) {
                progressBar.repaint();
            }
        }
    }

    private void resetProgress() {
        currentStep = 2;
        if (progressBar != null) {
            progressBar.repaint();
        }
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton();
        btn.setLayout(new BorderLayout(5, 0));
        
        // Create icon label
        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(30, 30));
        
        // Create text label
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Add components
        btn.add(iconLabel, BorderLayout.WEST);
        btn.add(textLabel, BorderLayout.CENTER);
        
        // Style the button
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(150, 40));
        
        // Add hover effect
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
} 