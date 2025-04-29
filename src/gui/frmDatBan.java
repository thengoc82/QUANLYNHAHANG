package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import dao.BanDAO;
import entity.Ban;

public class frmDatBan extends JPanel {
    private final JPanel pnlFloorPlan;
    private int currentFloor = 1;
    private ArrayList<Ban> dsBan = null;
    private final Color colorAvailable = new Color(102, 187, 106);     // Green
    private final Color colorOccupied = new Color(239, 83, 80);       // Red
    private final Color colorSelected = new Color(41, 182, 246);      // Blue
    private final Color colorReserved = new Color(255, 152, 0);      // Orange for reserved
    private Set<Ban> selectedTables = new HashSet<>();
    private String currentFilter = "Tất cả";
    private JProgressBar progressBar;
    private int currentStep = 1;
    private final String[] STEPS = {"Đặt bàn", "Đặt món", "Thanh toán"};
    private final Color[] STEP_COLORS = {
        new Color(51, 153, 255),  // Blue for table selection
        new Color(255, 153, 51),  // Orange for food ordering
        new Color(51, 204, 51)    // Green for payment
    };
    
    // Add new fields for reservation
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JCheckBox chkDatTruoc;
    private JPanel pnlReservation;

    public frmDatBan() {
        setBackground(new Color(51, 51, 51));
        setLayout(new BorderLayout());
        
        // Progress Bar Panel at the top
        JPanel pnlProgress = new JPanel(new BorderLayout());
        pnlProgress.setBackground(new Color(51, 51, 51));
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
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
        
        // Left Panel for Controls
        JPanel pnlLeft = new JPanel();
        pnlLeft.setBackground(new Color(64, 64, 64));
        pnlLeft.setPreferredSize(new Dimension(200, 0));
        pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));
        
        // Add Reservation Panel
        pnlReservation = createReservationPanel();
        
        // Floor Selection Panel
        JPanel pnlFloorSelect = new JPanel();
        pnlFloorSelect.setBackground(new Color(64, 64, 64));
        pnlFloorSelect.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Chọn lầu",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));
        pnlFloorSelect.setLayout(new GridLayout(3, 1, 0, 5));
        
        // Create floor buttons
        for (int i = 1; i <= 3; i++) {
            JButton btnFloor = createFloorButton("Lầu " + i, i);
            pnlFloorSelect.add(btnFloor);
        }
        
        // Legend Panel with colored squares
        JPanel pnlLegend = new JPanel();
        pnlLegend.setBackground(new Color(64, 64, 64));
        pnlLegend.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Chú thích",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));
        pnlLegend.setLayout(new GridLayout(4, 1, 5, 5));
        
        // Create filter buttons with colored squares
        JButton btnAll = createFilterButtonWithSquare("Tất cả", new Color(75, 75, 75));
        JButton btnAvailable = createFilterButtonWithSquare("Bàn trống", colorAvailable);
        JButton btnOccupied = createFilterButtonWithSquare("Bàn đã đặt", colorOccupied);
        JButton btnSelected = createFilterButtonWithSquare("Bàn đang chọn", colorSelected);
        
        pnlLegend.add(btnAll);
        pnlLegend.add(btnAvailable);
        pnlLegend.add(btnOccupied);
        pnlLegend.add(btnSelected);
        
        // Add components to left panel
        pnlLeft.add(Box.createVerticalStrut(20));
        pnlLeft.add(pnlReservation);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(pnlFloorSelect);
        pnlLeft.add(Box.createVerticalStrut(10));
        pnlLeft.add(pnlLegend);
        pnlLeft.add(Box.createVerticalStrut(20));
        
        // Center Panel for Floor Plan
        pnlFloorPlan = new JPanel();
        pnlFloorPlan.setBackground(new Color(51, 51, 51));
        pnlFloorPlan.setLayout(new GridLayout(4, 6, 20, 20));
        
        // Wrap floor plan in a scroll pane with padding
        JPanel pnlFloorPlanWrapper = new JPanel();
        pnlFloorPlanWrapper.setBackground(new Color(51, 51, 51));
        pnlFloorPlanWrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlFloorPlanWrapper.setLayout(new BorderLayout());
        pnlFloorPlanWrapper.add(pnlFloorPlan, BorderLayout.CENTER);
        
        JScrollPane scrollPane = new JScrollPane(pnlFloorPlanWrapper);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(51, 51, 51));
        
        // Right Panel for Actions
        JPanel pnlRight = new JPanel();
        pnlRight.setBackground(new Color(64, 64, 64));
        pnlRight.setPreferredSize(new Dimension(200, 0));
        pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
        
        JButton btnDatBan = createStyledButton("Đặt bàn");
        JButton btnHuyBan = createStyledButton("Hủy bàn");
        
        pnlRight.add(Box.createVerticalStrut(20));
        pnlRight.add(btnDatBan);
        pnlRight.add(Box.createVerticalStrut(10));
        pnlRight.add(btnHuyBan);
        
        // Add all main panels to the frame
        add(pnlLeft, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(pnlRight, BorderLayout.EAST);
        
        // Load tables and set up listeners
        loadTables();
        
        setupDatBanButton(btnDatBan);

        btnHuyBan.addActionListener(e -> {
            if (!selectedTables.isEmpty()) {
                boolean anyCanceled = false;
                for (Ban selectedTable : selectedTables) {
                    if (!selectedTable.isTinhTrang()) {
                        selectedTable.setTinhTrang(true);
                        BanDAO.getInstance().setTinhTrang(selectedTable, true);
                        anyCanceled = true;
                    }
                }
                if (anyCanceled) {
                    JOptionPane.showMessageDialog(this, "Đã hủy đặt " + selectedTables.size() + " bàn");
                    updateFloorPlan();
                } else {
                    JOptionPane.showMessageDialog(this, "Các bàn chưa được đặt!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một bàn!");
            }
        });
    }

    private JButton createFilterButtonWithSquare(String text, Color color) {
        JButton btn = new JButton();
        btn.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        
        // Create colored square
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(20, 20));
        square.setBackground(color);
        
        // Add square and text
        btn.add(square);
        JLabel lblText = new JLabel(text);
        lblText.setForeground(Color.WHITE);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.add(lblText);
        
        btn.setBackground(new Color(64, 64, 64));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(160, 40));
        
        btn.addActionListener(e -> {
            currentFilter = text;
            updateFloorPlan();
        });
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                lblText.setForeground(new Color(41, 182, 246));
                btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
            }
            public void mouseExited(MouseEvent e) {
                lblText.setForeground(Color.WHITE);
                btn.setBorder(null);
            }
        });
        
        return btn;
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(75, 75, 75));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setMaximumSize(new Dimension(160, 40));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(90, 90, 90));
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(75, 75, 75));
            }
            public void mousePressed(MouseEvent e) {
                btn.setBackground(new Color(100, 100, 100));
            }
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(new Color(90, 90, 90));
            }
        });
        
        return btn;
    }

    private void loadTables() {
        dsBan = BanDAO.getInstance().selectAll();
        updateFloorPlan();
    }

    private void updateFloorPlan() {
        pnlFloorPlan.removeAll();
        int currentFloor = this.currentFloor;
        
        // If no tables are selected, show all tables based on current filter
        if (selectedTables.isEmpty()) {
            for (Ban ban : dsBan) {
                if (ban.getLau() == currentFloor) {
                    boolean showTable = false;
                    
                    if (currentFilter.equals("Tất cả")) {
                        showTable = true;
                    } else if (currentFilter.equals("Bàn trống") && ban.isTinhTrang()) {
                        showTable = true;
                    } else if (currentFilter.equals("Bàn đã đặt") && !ban.isTinhTrang()) {
                        showTable = true;
                    }
                    
                    if (showTable) {
                        pnlFloorPlan.add(createTableButton(ban));
                    }
                }
            }
        } else {
            // If tables are selected, only show tables with the same status
            boolean selectedTableStatus = selectedTables.iterator().next().isTinhTrang();
            
            for (Ban ban : dsBan) {
                if (ban.getLau() == currentFloor && ban.isTinhTrang() == selectedTableStatus) {
                    pnlFloorPlan.add(createTableButton(ban));
                }
            }
        }
        
        pnlFloorPlan.revalidate();
        pnlFloorPlan.repaint();
    }

    private JButton createTableButton(Ban ban) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());

        // Add table number label at the top
        JLabel numberLabel = new JLabel(ban.getMaBan(), SwingConstants.CENTER);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 14));
        numberLabel.setForeground(Color.WHITE);
        button.add(numberLabel, BorderLayout.NORTH);

        // Create center panel for status indicator
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        
        // Add status indicator (colored square)
        JPanel statusIndicator = new JPanel();
        statusIndicator.setPreferredSize(new Dimension(60, 60));
        
        // Set color based on selection and status
        if (selectedTables.contains(ban)) {
            statusIndicator.setBackground(new Color(41, 182, 246)); // Blue color for selected tables
            button.setBorder(BorderFactory.createLineBorder(new Color(41, 182, 246), 2));
        } else if (!ban.isTinhTrang()) {
            statusIndicator.setBackground(new Color(239, 83, 80));  // Red for occupied
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            statusIndicator.setBackground(new Color(102, 187, 106));  // Green for available
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
        
        centerPanel.add(statusIndicator, BorderLayout.CENTER);
        button.add(centerPanel, BorderLayout.CENTER);

        // Set button properties
        button.setPreferredSize(new Dimension(100, 100));
        button.setBackground(new Color(51, 51, 51));
        button.setFocusPainted(false);

        // Add click listener for table selection
        button.addActionListener(e -> {
            if (selectedTables.contains(ban)) {
                selectedTables.remove(ban);
            } else {
                // If selecting a table with different status, clear previous selections
                if (!selectedTables.isEmpty() && 
                    selectedTables.iterator().next().isTinhTrang() != ban.isTinhTrang()) {
                    selectedTables.clear();
                }
                selectedTables.add(ban);
            }
            updateFloorPlan();
        });

        // Add hover effect that only changes the border
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selectedTables.contains(ban)) {
                    button.setBorder(BorderFactory.createLineBorder(new Color(41, 182, 246), 2));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!selectedTables.contains(ban)) {
                    button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            }
        });

        return button;
    }
    
    private void openGoiMonForm(ArrayList<Ban> tables) {
        currentStep = 2;  // Move to "Đặt món" step
        progressBar.repaint();
        
        // Hide current panel
        setVisible(false);
        
        // Create and show frmGoiMon
        Container parent = getParent();
        if (parent != null) {
            frmGoiMon goiMonForm = new frmGoiMon(tables);
            goiMonForm.setBounds(258, 58, 1243, 697); // Adjusted to align with menu width
            parent.add(goiMonForm);
            goiMonForm.setVisible(true);
            parent.revalidate();
            parent.repaint();
        }
    }

    private JButton createFloorButton(String text, int floor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(75, 75, 75));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(160, 40));
        btn.setMaximumSize(new Dimension(160, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if (floor == currentFloor) {
            btn.setForeground(new Color(41, 182, 246));
            btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
        }
        
        btn.addActionListener(e -> {
            // Update all floor buttons
            for (Component c : ((JPanel)btn.getParent()).getComponents()) {
                if (c instanceof JButton) {
                    JButton otherBtn = (JButton)c;
                    otherBtn.setForeground(Color.WHITE);
                    otherBtn.setBorder(null);
                }
            }
            
            currentFloor = floor;
            btn.setForeground(new Color(41, 182, 246));
            btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
            updateFloorPlan();
        });
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (floor != currentFloor) {
                    btn.setForeground(new Color(41, 182, 246));
                    btn.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(41, 182, 246)));
                }
            }
            public void mouseExited(MouseEvent e) {
                if (floor != currentFloor) {
                    btn.setForeground(Color.WHITE);
                    btn.setBorder(null);
                }
            }
        });
        
        return btn;
    }

    private void startProgress() {
        currentStep = 1;
        progressBar.repaint();
    }

    private ImageIcon loadFoodImage() {
        try {
            // Load a default food image from resources
            ImageIcon foodIcon = new ImageIcon(getClass().getResource("/images/food_default.png"));
            Image img = foodIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void paintProgress(Graphics g) {
        int width = progressBar.getWidth();
        int height = progressBar.getHeight();
        int stepWidth = width / 3;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw steps
        for (int i = 0; i < 3; i++) {
            g2d.setColor(i < currentStep ? STEP_COLORS[i] : Color.GRAY);
            g2d.fillRect(i * stepWidth, 0, stepWidth - 5, height);
            
            // Draw step text
            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            String text = STEPS[i];
            int textWidth = fm.stringWidth(text);
            int textX = i * stepWidth + (stepWidth - textWidth) / 2;
            int textY = height / 2 + fm.getAscent() / 2;
            g2d.drawString(text, textX, textY);
        }
    }

    private void nextStep() {
        if (currentStep < 3) {
            currentStep++;
            progressBar.repaint();
        }
    }

    private void resetProgress() {
        currentStep = 1;
        progressBar.repaint();
    }

    private JPanel createReservationPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 64, 64));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE),
            "Đặt bàn trước",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            Color.WHITE
        ));
        panel.setLayout(new GridLayout(0, 1, 5, 5));

        // Checkbox for advance reservation
        chkDatTruoc = new JCheckBox("Đặt bàn trước");
        chkDatTruoc.setFont(new Font("Segoe UI", Font.BOLD, 12));
        chkDatTruoc.setForeground(Color.WHITE);
        chkDatTruoc.setBackground(new Color(64, 64, 64));

        // Date Spinner
        JPanel datePanel = new JPanel(new BorderLayout(5, 0));
        datePanel.setBackground(new Color(64, 64, 64));
        JLabel lblDate = new JLabel("Ngày:");
        lblDate.setForeground(Color.WHITE);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        datePanel.add(lblDate, BorderLayout.WEST);
        datePanel.add(dateSpinner, BorderLayout.CENTER);

        // Time Spinner
        JPanel timePanel = new JPanel(new BorderLayout(5, 0));
        timePanel.setBackground(new Color(64, 64, 64));
        JLabel lblTime = new JLabel("Giờ:");
        lblTime.setForeground(Color.WHITE);
        timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timePanel.add(lblTime, BorderLayout.WEST);
        timePanel.add(timeSpinner, BorderLayout.CENTER);

        // Add components
        panel.add(chkDatTruoc);
        panel.add(datePanel);
        panel.add(timePanel);

        // Enable/disable date/time based on checkbox
        dateSpinner.setEnabled(false);
        timeSpinner.setEnabled(false);
        chkDatTruoc.addActionListener(e -> {
            boolean isSelected = chkDatTruoc.isSelected();
            dateSpinner.setEnabled(isSelected);
            timeSpinner.setEnabled(isSelected);
            updateTableDisplay();
        });

        return panel;
    }

    private void updateTableDisplay() {
        // Update the table display based on reservation status
        updateFloorPlan();
    }

    private void setupDatBanButton(JButton btnDatBan) {
        btnDatBan.addActionListener(e -> {
            if (!selectedTables.isEmpty()) {
                if (chkDatTruoc.isSelected()) {
                    // Handle advance reservation
                    Date selectedDate = (Date) dateSpinner.getValue();
                    Date selectedTime = (Date) timeSpinner.getValue();
                    
                    // Combine date and time
                    Calendar date = Calendar.getInstance();
                    date.setTime(selectedDate);
                    Calendar time = Calendar.getInstance();
                    time.setTime(selectedTime);
                    
                    date.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
                    date.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
                    
                    // Validate reservation time
                    if (date.getTime().before(new Date())) {
                        JOptionPane.showMessageDialog(this, 
                            "Vui lòng chọn thời gian trong tương lai!", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Process reservation
                    for (Ban selectedTable : selectedTables) {
                        // TODO: Add reservation to database with date/time
                        selectedTable.setTinhTrang(false);
                        BanDAO.getInstance().setTinhTrang(selectedTable, false);
                    }
                    JOptionPane.showMessageDialog(this, 
                        "Đã đặt " + selectedTables.size() + " bàn cho ngày " + 
                        new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date.getTime()));
                } else {
                    // Handle immediate booking
                    boolean anyBooked = false;
                    for (Ban selectedTable : selectedTables) {
                        if (selectedTable.isTinhTrang()) {
                            selectedTable.setTinhTrang(false);
                            BanDAO.getInstance().setTinhTrang(selectedTable, false);
                            anyBooked = true;
                        }
                    }
                    if (anyBooked) {
                        JOptionPane.showMessageDialog(this, "Đã đặt " + selectedTables.size() + " bàn");
                        // Open the frmGoiMon form with multiple tables
                        openGoiMonForm(new ArrayList<>(selectedTables));
                    } else {
                        JOptionPane.showMessageDialog(this, "Các bàn đã được đặt!");
                    }
                }
                updateFloorPlan();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một bàn!");
            }
        });
    }
} 