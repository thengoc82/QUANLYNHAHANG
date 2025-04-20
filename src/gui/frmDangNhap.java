
package gui;
import controler.bienStatic;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.TaiKhoan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


public class frmDangNhap extends javax.swing.JFrame {
	dlgXacNhanCa dlgXacNhan;
	
    private javax.swing.JPasswordField pwdPass;
    private javax.swing.JTextField txtUserName;
  
    public frmDangNhap() {
        initComponents();
        txtUserName.requestFocus();
        this.setResizable(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setBounds(145, 279, 0, 0);
        lblTen = new javax.swing.JLabel();
        lblTen.setBounds(90, 334, 265, 55);
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtUserName.setDropMode(DropMode.INSERT);
        jLabel3 = new javax.swing.JLabel();
        pwdPass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton1.addActionListener((ActionEvent e) -> {
            checkPassword();
        });
        btnQuenMatKhau = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(Color.GRAY);
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

//        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Rahemet\\Documents\\NetBeansProjects\\LoginAndSignUp\\src\\Icon\\logo.png")); // NOI18N
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(frmDangNhap.class.getResource("/icon/ICON_LOGO_341x281.png")));
        lblLogo.setBounds(34, 85, 341, 281);

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 471);
        Right.setLayout(null);
        Right.add(lblLogo);
        Right.add(lblTen);
        Right.add(jLabel5);

        Left.setBackground(new java.awt.Color(0, 0, 0));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(Color.GRAY);
        jLabel1.setText("Đăng nhập");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        jLabel2.setText("Tên đăng nhập:");
        
        txtUserName = new RoundedTextField(10);
        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setForeground(Color.WHITE);
        jLabel3.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // NOI18N
        jLabel3.setText("Mật khẩu:");
        
        

        jButton1 = new RoundedButton("Đăng nhập");
        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
//        jButton1.setText("Đăng nhập");

        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ActionPerformed(evt);
                    });
            
                    btnQuenMatKhau = new RoundedButton("Quên mật khẩu");
                    btnQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    btnQuenMatKhau.setForeground(new java.awt.Color(255, 51, 51));
            //        btnQuenMatKhau.setText("Quên mật khẩu?");
                    btnQuenMatKhau.addActionListener((java.awt.event.ActionEvent evt) -> {
                        frmDangNhap.this.setVisible(false);
                        dlgQuenMK dlgQuenMK =  new dlgQuenMK(frmDangNhap.this);
                        dlgQuenMK.setVisible(true);
                        dlgQuenMK.setLocationRelativeTo(null);
                        dlgQuenMK.setAlwaysOnTop(true);
                    });
            
                    javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
                    LeftLayout.setHorizontalGroup(
                        LeftLayout.createParallelGroup(Alignment.TRAILING)
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addGap(30)
                                .addGroup(LeftLayout.createParallelGroup(Alignment.LEADING)
                                    .addGroup(LeftLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(txtUserName)
                                        .addComponent(pwdPass, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(LeftLayout.createSequentialGroup()
                                        .addGap(110)
                                        .addComponent(btnQuenMatKhau)))
                                .addContainerGap(27, Short.MAX_VALUE))
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addContainerGap(109, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(107))
                    );
                    LeftLayout.setVerticalGroup(
                        LeftLayout.createParallelGroup(Alignment.LEADING)
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addGap(51)
                                .addComponent(jLabel1)
                                .addGap(40)
                                .addComponent(jLabel2)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(jLabel3)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pwdPass, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(26)
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(btnQuenMatKhau)
                                .addContainerGap(79, Short.MAX_VALUE))
                    );
                    Left.setLayout(LeftLayout);
            
                    jPanel1.add(Left);
                    Left.setBounds(400, 0, 400, 500);
            
                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 129, Short.MAX_VALUE))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 149, Short.MAX_VALUE))
                    );
            
                    getAccessibleContext().setAccessibleName("LOGIN");
            
                    pack();
                    txtUserName.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {}
            
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                checkPassword();
                            }
                        }
            
                        @Override
                        public void keyReleased(KeyEvent e) {}
                    });
                    pwdPass.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {}
            
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                checkPassword();
                            }
                        }
            
                        @Override
                        public void keyReleased(KeyEvent e) {}
                    });
                    getRootPane().setDefaultButton(jButton1);
                }// </editor-fold>//GEN-END:initComponents
            
                private void jButton1ActionPerformed(ActionEvent evt) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'jButton1ActionPerformed'");
                }
            
                public static void main(String[] args) {
    	frmDangNhap SignUpFrame = new frmDangNhap();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null); 
	}

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton btnQuenMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTen;
    private javax.swing.JPanel jPanel1;

    // End of variables declaration//GEN-END:variables
    
    //Check mật khẩu
    public void checkPassword() {
    	String userchk = txtUserName.getText();
    	@SuppressWarnings("deprecation")
		String passchk = pwdPass.getText();
    	if(userchk.equals("") || passchk.equals("")) {
    		JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
    	}else {
    		try {
				TaiKhoan tk = TaiKhoanDAO.getInstance().selectById(userchk);
				if(tk == null) {
					JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại trên hệ thống !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
				}else {
					if(passchk.equals(tk.getMatKhau())) {
							bienStatic.role = tk.isLoaiTaiKhoan();
							bienStatic.maTKLogin = userchk;
							bienStatic.nhanVienLogin = NhanVienDAO.getInstance().selectByTK(userchk);
                            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
							final JOptionPane otp = new JOptionPane("Đăng nhập thành công!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);
							final JDialog dlgThongBao = otp.createDialog(this, "Thông báo");
							javax.swing.Timer timer = new javax.swing.Timer(500, (ActionEvent e) -> {
                                                            dlgThongBao.dispose();
                                                        });
							timer.setRepeats(false);
							timer.start();
							dlgThongBao.setVisible(true);
                            this.dispose();
                            dlgXacNhan = new dlgXacNhanCa(this);
                            dlgXacNhan.setVisible(true);
                            dlgXacNhan.setAlwaysOnTop(true);
                            
					}else {
						JOptionPane.showMessageDialog(this, "Sai mật khẩu !", "Cảnh báo !", JOptionPane.WARNING_MESSAGE);
					}
				}
			} catch (HeadlessException | SecurityException e) {
				// TODO: handle exception
			}
    	}
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
 			g2.setColor(Color.GRAY);
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