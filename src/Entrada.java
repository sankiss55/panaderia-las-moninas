
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Entrada extends JFrame implements ActionListener {

    JLabel usuario = new JLabel(new ImageIcon(this.getClass().getResource("/imagenes/badge_FILL0_wght400_GRAD-25_opsz24.png")));
    JLabel contrasena = new JLabel(new ImageIcon(this.getClass().getResource("/imagenes/lock_FILL0_wght400_GRAD-25_opsz24.png")));
    JTextField usuariores = new JTextField(10);
    JPasswordField contrasenares = new JPasswordField(10);
    JButton ingresar = new JButton("Ingresar", new ImageIcon(this.getClass().getResource("/imagenes/login_FILL0_wght400_GRAD-25_opsz40.png")));
    JButton ver_contrasena = new JButton("Visualizar contraseña");
    JLabel imagen_user = new JLabel(new ImageIcon(this.getClass().getResource("/imagenes/face_FILL0_wght300_GRAD200_opsz48.png")));
    JButton Ingresar = new JButton("Ingresar");
    public Entrada() {
        this.setSize(400, 400);
        this.setResizable(false);
this.setIconImage(new ImageIcon(this.getClass().getResource("/imagenes/meeting_room_48dp_FILL0_wght400_GRAD0_opsz48.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ver_contrasena.setBackground(new Color(116, 255, 127));
        Ingresar.setBackground(new Color(115, 130, 255));
        ver_contrasena.setToolTipText("Visualiza tu contraseña");
        ver_contrasena.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ver_contrasena.putClientProperty("JButton.buttonType", "roundRect");
        Ingresar.putClientProperty("JButton.buttonType", "roundRect");
        usuariores.setToolTipText("Ingresa tu usuario");
       usuariores.setText("Ingresa tu usuario");
       usuariores.setForeground(new Color(175, 175, 175));
        contrasenares.setToolTipText("Ingresa tu contraseña");
        contrasenares.setForeground(new Color(175, 175, 175));
        contrasenares.setEchoChar((char)0);
        contrasenares.setText("Ingresa tu contraseña");
        contrasenares.setText("Ingresa tu contraseña");
        Ingresar.setToolTipText("Accede al programa ");
        Ingresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Container contenedor = getContentPane();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(243, 228, 255));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panel2.setBackground(new Color(0, 0, 0, 0));
        contenedor.add(panel);
        panel.setLayout(null);
        panel2.setLayout(null);
        panel.setBackground(new Color(247, 170, 134));
        panel.add(imagen_user).setBounds(150, 50, 100, 50);
        panel.add(panel2).setBounds(45, 50, 300, 275);
        panel2.add(usuario).setBounds(0, 60, 40, 20);
        panel2.add(usuariores).setBounds(50, 60, 235, 28);
        panel2.add(contrasena).setBounds(0, 100, 40, 20);
        panel2.add(contrasenares).setBounds(50, 100, 235, 28);
        panel2.add(ver_contrasena).setBounds(135, 135, 150, 20);
        panel2.add(Ingresar).setBounds(60, 200, 200, 30);
        //150, 161, 255
       ver_contrasena.setFocusable(false);
        ver_contrasena.addActionListener(this);
        ver_contrasena.addMouseListener(mos);
        Ingresar.addActionListener(this);
        contrasenares.addFocusListener(foc);
        contrasenares.addKeyListener(key);
        usuariores.addKeyListener(key);
        usuariores.addFocusListener(foc);
    }
    int i = -1;
    int i2=0;
    @Override
    public void actionPerformed(ActionEvent x) {
        if (ver_contrasena == x.getSource()) {
            if (i2 == 0) {
                contrasenares.setEchoChar((char) 0);
                ver_contrasena.setText("Tapar contraseña");
                ver_contrasena.setToolTipText("Elimina la vista a la contraseña");
                i2++;
            } else {
                contrasenares.setEchoChar('•');
                ver_contrasena.setText("Visualizar contraseña");
                ver_contrasena.setToolTipText("Visualiza tu contraseña");
                i2--;
            }
            contrasenares.requestFocus();
        }
        if (Ingresar == x.getSource()) {
            if (contrasenares.getText().equals("123") && usuariores.getText().equals("vendedor1")) {
                inicio2 h = new inicio2();
                h.setVisible(true);
                notificacion("Biemvenido de nuevo", "Hagamos ventas exitosas como tu ");
                this.dispose();
            } else if (contrasenares.getText().equals("321")&& usuariores.getText().equals("administrador")){
                Administrador h=new Administrador();
                h.setVisible(true);
                this.dispose();
                notificacion("Biemvenido de nuevo", "Tegamos el dia bien administrado, jefe ");
            }else{
                JOptionPane.showMessageDialog(this, "Error el usuaio o contraseña es incorrecta", "Error de datos", JOptionPane.ERROR_MESSAGE);
                usuariores.requestFocus();
            }
        }
    }
    MouseAdapter mos = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (ver_contrasena == e.getSource()) {
                ver_contrasena.setBackground(new Color(218, 255, 221));
            }
            if (Ingresar == e.getSource()) {
                Ingresar.setBackground(new Color(169, 178, 255));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (ver_contrasena == e.getSource()) {
                ver_contrasena.setBackground(new Color(150, 255, 158));
            }
            if (Ingresar == e.getSource()) {
                Ingresar.setBackground(new Color(115, 130, 255));
            }
        }
    };
FocusAdapter foc=new FocusAdapter(){
    @Override
    public void focusLost(FocusEvent e){
        
        if(e.getSource()==contrasenares && contrasenares.getText().isEmpty()){
             contrasenares.setText("Ingresa tu contraseña");
             contrasenares.setEchoChar((char)0);
        }
        if(e.getSource()==usuariores && usuariores.getText().isEmpty()){
             usuariores.setText("Ingresa tu usuario");
        }
    }
    @Override
   public void focusGained(FocusEvent e) {
        if (e.getSource() == contrasenares && escrito_usuario == true && contrasenares.getText().equals("Ingresa tu contraseña")) {
            contrasenares.setText("");
            if(i==-1){
                contrasenares.setEchoChar('•');
                i=0;
            }
        }
        if (e.getSource() == usuariores && escrito_usuario2 == true && usuariores.getText().equals("Ingresa tu usuario")) {
            usuariores.setText("");
        }
    }
};
boolean escrito_usuario=true;
boolean escrito_usuario2=true;
KeyAdapter key=new KeyAdapter(){
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getSource()==contrasenares &&!contrasenares.getText().isEmpty()){
            contrasenares.setForeground(Color.BLACK);
            escrito_usuario=false;
        }else if(contrasenares.getText().isEmpty()){
            escrito_usuario=true;
            contrasenares.setForeground(new Color(175, 175, 175));
        }
        if(e.getSource()==usuariores &&!usuariores.getText().isEmpty()){
            usuariores.setForeground(Color.BLACK);
            escrito_usuario2=false;
        }else if(usuariores.getText().isEmpty()){
            escrito_usuario2=true;
            usuariores.setForeground(new Color(175, 175, 175));
        }
    }
};
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.getMessage();
        }
        Entrada h = new Entrada();
        h.setVisible(true);
    }

    public void notificacion(String titulo, String mensaje) {
        SystemTray tray = SystemTray.getSystemTray();
        Image imagen = Toolkit.getDefaultToolkit().getImage("/imagenes/face_FILL0_wght300_GRAD200_opsz48");
        TrayIcon imagen2 = new TrayIcon(imagen, "Las Moninas");
        imagen2.setImageAutoSize(true);

        try {
            tray.add(imagen2);
            imagen2.displayMessage(titulo, mensaje, MessageType.INFO);
        } catch (AWTException e) {
            e.getMessage();
        }
    }

}
