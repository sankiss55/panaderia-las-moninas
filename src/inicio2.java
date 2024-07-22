
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.sound.sampled.*;
import java.io.IOException;
public class inicio2 extends JFrame implements ActionListener {
   private Clip clip1;
   private int currentSongIndex=0;
    static inicio2.tabla_de_venta ventana_tabla_istancia= new inicio2.tabla_de_venta();
    JButton boton1 = new JButton("Hacer nueva venta", new ImageIcon(this.getClass().getResource("/imagenes/Pasted-20231219-181230_preview_rev_1.png")));
    JButton boton2 = new JButton("Activar funcion 'Audio'", new ImageIcon(this.getClass().getResource("/imagenes/hearing_FILL0_wght400_GRAD0_opsz48_1.png")));
    JButton boton3 = new JButton("¿Quienes somos?", new ImageIcon(this.getClass().getResource("/imagenes/group_FILL0_wght400_GRAD0_opsz48.png")));
    JButton humano_negro = new JButton("Configuración", new ImageIcon(this.getClass().getResource("/imagenes/settings_FILL0_wght400_GRAD0_opsz48.png")));
    JButton boton4 = new JButton("Historial de venta", new ImageIcon(this.getClass().getResource("/imagenes/lab_profile_FILL0_wght400_GRAD0_opsz24 (1).png")));
    JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource("/imagenes/icono23.png")));
    JLabel titulo = new JLabel("Las Moninas");
    JLabel hora = new JLabel();
    JLabel fecha = new JLabel();
        JLabel frase = new JLabel("Sabor y arte en cada una de las migajas.");
    JPopupMenu menu_configuracion = new JPopupMenu();
    JMenuItem opcion1 = new JMenuItem("Agrandar letra", new ImageIcon(this.getClass().getResource("/imagenes/text_fields_FILL0_wght400_GRAD-25_opsz20.png")));
    JMenuItem opcion2 = new JMenuItem("Cerrar sesión", new ImageIcon(this.getClass().getResource("/imagenes/toggle_off_FILL0_wght400_GRAD-25_opsz20.png")));
    JMenuItem opcion3 = new JMenuItem("Soporte", new ImageIcon(this.getClass().getResource("/imagenes/support_FILL0_wght400_GRAD-25_opsz20.png")));
    JMenuItem opcion4 = new JMenuItem("Notificaciones", new ImageIcon(this.getClass().getResource("/imagenes/notifications_FILL0_wght400_GRAD-25_opsz20.png")));
    JMenuItem opcion5 = new JMenuItem("<html>Cambiar la hora a<br> formato 12 horas<html>", new ImageIcon(this.getClass().getResource("/imagenes/schedule_FILL0_wght400_GRAD0_opsz20.png")));
    JPopupMenu menu_secundario = new JPopupMenu();
    JMenuItem agrandar_letra1 = new JMenuItem("1x");
    JMenuItem agrandar_letra1x5 = new JMenuItem("1.5x");
    JMenuItem agrandar_letra2 = new JMenuItem("2x");
    static Choice choice2;
    static Choice choice;
    JPanel panel;
    JPanel panel2;
    DefaultTableModel modelo_de_tabla;
     JTable tabla;
    JScrollPane dibujo_de_la_tabla;
    int []tamaños;
     int []tamaños2;
     int []tamaños3;
      static trabajo_con_bd trabajo_bd = new trabajo_con_bd();
    public inicio2() {
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
this.setIconImage(new ImageIcon(this.getClass().getResource("/imagenes/bakery_dining_48dp_FILL0_wght400_GRAD0_opsz48.png")).getImage());
        
        this.setTitle("Inicio");
        JButton[] botones = {
            boton1, boton2, boton3, humano_negro, boton4
        };
        for (JButton boton : botones) {
            boton.setBackground(new Color(246, 209, 25));
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            boton.setFont(new Font("Times New Roman", Font.ITALIC, 12));
            boton.setFocusPainted(false);
            boton.putClientProperty("JButton.buttonType", "roundRect");
            boton.addMouseListener(mos);
            boton.addActionListener(this);
        }
        menu_configuracion.setBackground(new Color(214, 234, 248));
        menu_configuracion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu_configuracion.setBorder(new LineBorder(Color.black, 1));
        boton1.setToolTipText("Hacer una nueva venta :)");
        boton2.setToolTipText("La funcion de audio");
        boton4.setToolTipText("Revisa el reporte de venta de algun dia");
        boton3.setToolTipText("Descuble quienes somos");
        humano_negro.setToolTipText("Configuración");
        titulo.setFont(new Font("Sylfaen", Font.ITALIC, 28));
        frase.setFont(new Font("Lucida Fax", Font.ROMAN_BASELINE, 16));
        menu_configuracion.add(opcion1).setToolTipText("Modifique la letra segun tu gusto");
        menu_configuracion.add(opcion2).setToolTipText("Cierra la sesión");
        menu_configuracion.add(opcion3).setToolTipText("Pide ayuda a soporte por alguna anomalia");
        menu_configuracion.add(opcion4).setToolTipText("Notificaciones");
        menu_configuracion.add(opcion5).setToolTipText("Cambiar formato de hora");
        //otroJPopulMenu
        menu_secundario.add(agrandar_letra1);
        menu_secundario.add(agrandar_letra1x5);
        menu_secundario.add(agrandar_letra2);
        menu_secundario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu_secundario.setBackground(new Color(214, 234, 248));
        choice2 = new Choice();
        choice2.add("Todos los metodos");
        choice2.addItem("Nombre del producto");
        choice2.addItem("Codigo de barras");
        choice2.add("Precio");

        choice2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        choice2.addItemListener(e
                -> {
            try {
                String opcion = choice2.getSelectedItem();
                Robot robot_teclado=new Robot();
                trabajo_con_bd.busqueda.requestFocus();
                robot_teclado.keyPress(KeyEvent.VK_ENTER);
                robot_teclado.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException ex) {
               ex.printStackTrace();
            }
        });
        choice = new Choice();
        
        choice.add("Todas las secciones");
            choice.add("Repostería");
            choice.add("biscocho");
            choice.add("hojaldre");
            choice.add("feite");
            choice.add("dona");
            choice.add("batidos");
            
            choice.add("otros");
        choice.setCursor(new Cursor(Cursor.HAND_CURSOR));
        choice.addItemListener(e
                -> {
            try {
                String opcion = choice.getSelectedItem();
                Robot robot_teclado=new Robot();
                trabajo_con_bd.busqueda.requestFocus();
                robot_teclado.keyPress(KeyEvent.VK_ENTER);
                robot_teclado.keyRelease(KeyEvent.VK_ENTER);
                
            } catch (AWTException ex) {
               ex.printStackTrace();
            }
        });
        boton_anterior=boton1;
        boton1.setEnabled(false);
        Container contenedor = getContentPane();
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics x) {
                super.paintComponent(x);
                ImageIcon fondo = new ImageIcon(this.getClass().getResource("/imagenes/fondo_de_la_panaderia.png"));
                x.drawImage(fondo.getImage(), 0, 100, getWidth(), getHeight(), this);

            }
        };

        panel.setBackground(new Color(170, 50, 255));
        panel2 = new JPanel() {
            @Override
            public void paintComponent(Graphics x) {
                super.paintComponents(x);
                x.setColor(new Color(214, 225, 240));
                x.fillRoundRect(0, 0, getWidth(), 100, 20, 20);

            }
        };

        contenedor.add(panel);
        panel.setLayout(null);
        panel2.setLayout(null);
        panel.add(boton1).setBounds(0, 100, 200, 50);
        panel.add(boton2).setBounds(210, 100, 250, 50);
        panel.add(boton3).setBounds(730, 100, 200, 50);
        panel.add(humano_negro).setBounds(940, 100, 250, 50);
        panel.add(boton4).setBounds(470, 100, 250, 50);
        panel.add(choice2).setBounds(450, 180, 250, 20);
        panel2.add(logo).setBounds(10, 0, 100, 100);
        panel2.add(hora).setBounds(getWidth() - 200, 30, 200, 20);
        panel2.add(fecha).setBounds(getWidth() - 200, 50, 200, 20);
        panel2.add(titulo).setBounds(getWidth() / 2 - 75, -5, 600, 80);

        panel2.add(frase).setBounds(getWidth() / 3 + 25, 60, 600, 30);
        panel.add(panel2).setBounds(0, 0, getWidth() - 15, 100);
        panel.add(choice).setBounds(450, 150, 250, 20);
        opcion1.addActionListener(this);
        opcion2.addActionListener(this);
        opcion3.addActionListener(this);
        opcion4.addActionListener(this);
        opcion5.addActionListener(this);
        agrandar_letra1.addActionListener(this);
        agrandar_letra2.addActionListener(this);
        agrandar_letra1x5.addActionListener(this);

        timer.start();
       trabajo_con_bd h=new trabajo_con_bd();
        h.traer_productos(this);
    
       /* while( i==panel.getComponents().length&& y==panel2.getComponents().length){
            if(panel.getComponent(i) instanceof JButton || panel.getComponent(i) instanceof JLabel){
                tamaños=new int[i];
                i++;
            }
            if(panel2.getComponent(y) instanceof JButton || panel2.getComponent(y) instanceof JLabel){
                tamaños2=new int[y];
                y++; 
            }
            
        }*/
        tamaños2 = new int[panel2.getComponents().length];
tamaños = new int[panel.getComponents().length+menu_secundario.getComponents().length];
tamaños3 = new int[ trabajo_con_bd.panel_para_productos.getComponents().length];
for (int i = 0; i < panel.getComponents().length; i++) {
    if (panel.getComponent(i) instanceof JButton || panel.getComponent(i) instanceof JLabel) {
        tamaños[i] = panel.getComponent(i).getFont().getSize();
    }
    if(panel.getComponent(i) ==boton4){
        for(int y=0; y<menu_configuracion.getComponents().length;y++){
             tamaños[i] = menu_configuracion.getComponent(y).getFont().getSize();
        }
    }
}

for (int y = 0; y < panel2.getComponents().length; y++) {
    if (panel2.getComponent(y) instanceof JButton || panel2.getComponent(y) instanceof JLabel) {
        tamaños2[y] = panel2.getComponent(y).getFont().getSize();
    }
}
for (int y = 0; y < trabajo_con_bd.panel_para_productos.getComponents().length; y++) {
    if(trabajo_con_bd.panel_para_productos.getComponent(y) instanceof JPanel){
       JPanel panelInterior = (JPanel) trabajo_con_bd.panel_para_productos.getComponent(y);

        for (int i = 0; i < panelInterior.getComponentCount(); i++) {
           if (panelInterior.getComponent(i) instanceof JLabel) {
        tamaños3[i] = panelInterior.getComponent(i).getFont().getSize();
    }
        }
            
    }
    
}
    }

    int i = 0;
    int y = 0;
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if (trabajo_bd.notificacio_activa(2) == true) {
                humano_negro.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/settings_alert_FILL0_wght400_GRAD0_opsz48.png")));
                opcion4.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/notification_important_FILL0_wght400_GRAD0_opsz24.png")));
            } else {
                humano_negro.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/settings_FILL0_wght400_GRAD0_opsz48.png")));
                opcion4.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/notifications_FILL0_wght400_GRAD-25_opsz20.png")));
            }
            if (y == 0 && isVisible()) {
                ventana_tabla_istancia = new tabla_de_venta();
                ventana_tabla_istancia.setVisible(true);
                
                y++;
            }
            
            Date fechaHoraActual = new Date();
            SimpleDateFormat formatoHora;
            if (i == 0) {
                formatoHora = new SimpleDateFormat("HH:mm:ss");
            } else {
                formatoHora = new SimpleDateFormat("hh:mm:ss a");
            }

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

            hora.setText("Hora: " + formatoHora.format(fechaHoraActual));
            fecha.setText("Fecha: " + formatoFecha.format(fechaHoraActual));
            hora.setFont(new Font("Serif", Font.PLAIN, 20));
            fecha.setFont(new Font("Serif", Font.PLAIN, 16));

        }
    });

    MouseAdapter mos = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

            if (e.getSource() == boton1 || e.getSource() == boton2 || e.getSource() == boton3 || e.getSource() == boton4 || e.getSource() == humano_negro) {
                JButton boton = (JButton) e.getSource();
                boton.setBackground(new Color(255, 249, 218));

            }
            if (e.getSource() == cerrar || e.getSource() == seleccionar) {
                JButton boton = (JButton) e.getSource();
                boton.setBackground(new Color(203, 127, 255));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == boton1 || e.getSource() == boton2 || e.getSource() == boton3 || e.getSource() == boton4 || e.getSource() == humano_negro) {
                JButton boton = (JButton) e.getSource();
                boton.setBackground(new Color(246, 209, 25));
            }
            if (e.getSource() == cerrar || e.getSource() == seleccionar) {
                JButton boton = (JButton) e.getSource();
                boton.setBackground(new Color(210, 180, 222));
            }
        }
        @Override
        public void mouseClicked (MouseEvent e){
             if(e.getSource()==titulo || frase==e.getSource()){
                 reproducir_audios("/audios/titulo.wav");
            }
          if(e.getSource() == boton1  ||e.getSource() == boton4){
              boton_anterior.setEnabled(true);
              boton_anterior=(JButton)e.getSource();
              JButton boton_seleccionado=(JButton) e.getSource();
              boton_seleccionado.setEnabled(false);
          } 
        }
    };
   
    JButton boton_anterior=new JButton();
    JDateChooser calendario = new JDateChooser();
    JButton cerrar = new JButton("Cerrar");
    JButton seleccionar = new JButton("Seleccionar");
    JDialog ventana = new JDialog(inicio2.this, "Selecciona la hora", true);
int contador_de_imagen=0;
static DefaultTableModel modelo_de_la_tabla;
    @Override
    public  void actionPerformed(ActionEvent x) {
       
        if (boton1 == x.getSource()) {  
             this.reproducir_audios("/audios/hacer_nueva_ventana.wav");
      for(Component componentes: panel.getComponents()){
          if(componentes instanceof JScrollPane){
              panel.remove(componentes);
              panel.repaint();
              panel.revalidate();
          }
      }
          System.out.print("jdjd");
            choice.setVisible(true);
            choice2.setVisible(true);
           
            trabajo_bd.traer_productos(this);
            if(abierta_o_no==false){
            ventana_tabla_istancia  = new tabla_de_venta();
            ventana_tabla_istancia.setVisible(true);
            }
         
        }
        if (opcion2 == x.getSource()) {
             this.reproducir_audios("/audios/opcion2_cerrar_sesion_.wav");
           System.exit(0);
        }
        if (opcion3 == x.getSource()) {
this.reproducir_audios("/audios/opcion3_soporte_.wav");
            caja_de_texto h = new caja_de_texto();
            h.setVisible(true);
        }
        if (opcion4 == x.getSource()) {
            this.reproducir_audios("/audios/opcion4_notif_.wav");
            ventana_de_mensajes h = new ventana_de_mensajes();
            h.setVisible(true);
        }
        if (opcion5 == x.getSource()) {
            
        this.reproducir_audios("/audios/opcion5_hora_.wav");
            if (i == 0) {
                i++;
                opcion5.setText("<html>Cambiar la hora a<br>formato 24 horas<html>");
            } else {
                i--;
                opcion5.setText("<html>Cambiar la hora a<br>formato 12 horas<html>");
            }
        }
        if (agrandar_letra1 == x.getSource()) {
            aumentar_tamaño(panel, panel2, 1);
        }
        if (agrandar_letra1x5 == x.getSource()) {
            aumentar_tamaño(panel, panel2, 1.2);
        }
        if (agrandar_letra2 == x.getSource()) {
            aumentar_tamaño(panel, panel2,1.4);
        }
        if (x.getSource() == boton3) {
            
        this.reproducir_audios("/audios/descubre quienes somos.wav");
             try {
                    URI uri = new URI("https://sites.google.com/soycecytem.mx/moninas?usp=sharing");
                    Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al abrir el vinculo.\n Intentalo despues.");
                }
        }
        if (humano_negro == x.getSource()) {
            menu_configuracion.show(humano_negro, 0, humano_negro.getHeight());
        }

        if (opcion1 == x.getSource()) {
            
            this.reproducir_audios("/audios/opcion1_letra_.wav");
            menu_secundario.show(humano_negro, -50, humano_negro.getHeight());

        }
        if (boton2 == x.getSource()) {
            
            if(contador_de_imagen==0)
            {
           boton2.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/hearing_disabled_FILL0_wght400_GRAD0_opsz48.png")));
            boton2.setToolTipText("Desactivar función audio");
            boton2.setText("Desactivar función audio");
            contador_de_imagen++;
            
            currentSongIndex=1;
            this.reproducir_audios("/audios/audioactivado.wav");
            }else{
                
            this.reproducir_audios("/audios/audiodesactiado.wav");
                 boton2.setToolTipText("Activar función audio");
            boton2.setText("Activar función audio");
            boton2.setIcon( new ImageIcon(this.getClass().getResource("/imagenes/hearing_FILL0_wght400_GRAD0_opsz48_1.png")));
            contador_de_imagen--;
            
            currentSongIndex=0;
            }
        }
        if (x.getSource() == boton4 ) {
            
            this.reproducir_audios("/audios/historial de ventas.wav");
            calendario.setDate(new Date());

            JPanel panel = new JPanel();
            panel.add(calendario);
            panel.add(seleccionar);
            seleccionar.setToolTipText("Aceptar la fecha ingresada");
            panel.add(cerrar);
            cerrar.setToolTipText("Cancelar");
            for (Component componentes : panel.getComponents()) {
                componentes.setBackground(new Color(210, 180, 222));
                componentes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                componentes.setFont(new Font("Arial", Font.PLAIN, 12));
                if(componentes instanceof JButton){
                    ((JButton) componentes).addActionListener(this);
                    componentes.addMouseListener(mos);
                }
               
            }
           seleccionar.addActionListener(this);
            ventana.getContentPane().removeAll();
            ventana.getContentPane().add(panel);
            ventana.setMaximumSize(new Dimension(400, 100));
            ventana.setMinimumSize(new Dimension(400, 100));
            panel.setBackground(new Color(174, 214, 241));
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(true);
            JTextFieldDateEditor editor = (JTextFieldDateEditor) calendario.getDateEditor();
            editor.setEditable(false);
            ventana.setSize(300, 100);
            ventana.setVisible(true);
           
        }
        if (x.getSource() == cerrar) {
            ventana.dispose();

        }
        if (x.getSource() == seleccionar ) {
             Date dia=new Date();
             if(dia.before(calendario.getDate())){
            JOptionPane.showMessageDialog(this, "Fecha establecida aun no ha pasado\n Selecciona una fecha que ya haya pasado o la actual", "ERROR", JOptionPane.WARNING_MESSAGE);
        ventana.dispose();
        boton4.doClick();
        
             }else {
          choice.setVisible(false);
            choice2.setVisible(false);
           
            trabajo_bd.busqueda.setVisible(false);
            panel.remove(trabajo_bd.scrollPane);
            panel.repaint();
            panel.revalidate();
            boton_anterior.setEnabled(true);
            boton4.setEnabled(false);
            boton_anterior=boton4;
            ventana.dispose();
             String[] columnas={
           "Producto", "codigo de barras", "Precio c/u", "Cantidad vendidas", "sub total" 
         };
          modelo_de_la_tabla=new DefaultTableModel(columnas,0);
          JTable tabla2=new JTable(modelo_de_la_tabla){
             @Override
             public boolean isCellEditable(int filas, int columnas){
                 return false;
             }
         };
       
tabla2.getTableHeader().setReorderingAllowed(false); 
         JScrollPane dibujo_de_la_tabla2=new JScrollPane(tabla2);
         panel.add(dibujo_de_la_tabla2).setBounds(200,150,800, 500);
        trabajo_bd.traer_datos_para_tabla_de_ventas(calendario.getDate(),1);
        
       
        }
         
    }
    }
    public static JTextArea espacio_para_escribir;

    class caja_de_texto extends JFrame implements ActionListener {

        JButton enviar = new JButton("Enviar");
        JTextField asuntores = new JTextField(10);
        JLabel mensaje = new JLabel("Escribe tu mensaje");
        JLabel asunto = new JLabel("Asuto");

        public caja_de_texto() {
            opcion3.setEnabled(false);
            this.setSize(400, 300);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            espacio_para_escribir = new JTextArea();
            espacio_para_escribir.setLineWrap(true);
            JScrollPane subida_o_bajada = new JScrollPane(espacio_para_escribir);
            enviar.setBackground(new Color(130, 164, 223));
            enviar.setCursor(new Cursor(Cursor.HAND_CURSOR));

            Container contenedor = getContentPane();
            JPanel panel = new JPanel();
            contenedor.add(panel);
            panel.setLayout(null);
            panel.add(asunto).setBounds(0, 5, 100, 30);
            panel.add(asuntores).setBounds(0, 40, getWidth() - 7, 20);
            panel.add(mensaje).setBounds(0, 70, 100, 30);
            panel.add(subida_o_bajada).setBounds(0, 90, getWidth() - 7, 150);
            panel.add(enviar).setBounds(0, 240, 100, 20);
            enviar.addActionListener(this);
            tiempo.start();
        }

        Timer tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                if (!(isVisible())) {
                    opcion3.setEnabled(true);
                    tiempo.stop();
                }
            }
        });

        @Override
        public void actionPerformed(ActionEvent x) {
            if (x.getSource() == enviar) {
               
                if (asuntores.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Porfavor de rellenar todos los campos", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    asuntores.requestFocus();
                } else if (espacio_para_escribir.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Porfavor de rellenar todos los campos", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    espacio_para_escribir.requestFocus();
                } else {
                    trabajo_bd.mesnaje_sopote(espacio_para_escribir.getText(), asuntores.getText(), "Vendedor1");
                    this.dispose();
                }
            }
        }
    }

    static DefaultTableModel tableModel;
    static boolean abierta_o_no = false;
static String productostikects[];
static float precioticket[];
static String preciocu[];
static int suma;
    public static class tabla_de_venta extends JFrame implements ActionListener{

        JButton finalizar = new JButton("Finalizar compra");
        JLabel total = new JLabel("Total:");
        JTable table;
        JScrollPane dibujo_de_la_tabla;
int celda;
        public tabla_de_venta() {
            abierta_o_no = true;
            this.setSize(500, 530);
            this.setLocation(0,0);
            this.setTitle("Tabla de venta");
            String[] columnNames = {"Producto", "precio c/u", "Cantidad", "codigo de barras", "Sub Total"};
            tableModel = new DefaultTableModel(columnNames, 0);
             table = new JTable(tableModel) {
                @Override
                public boolean isCellEditable(int filas, int columnas) {
                    return columnas==2;
                }
            };
            table.getModel().addTableModelListener(new TableModelListener() {
    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 2) {
            try {
                
                  celda = e.getFirstRow();
                int cantidad_nueva = Integer.parseInt(table.getValueAt(celda, 2).toString());
                float costo = Float.parseFloat( table.getValueAt(celda, 1).toString());
                table.setValueAt(cantidad_nueva*costo , celda, 4);
                if (cantidad_nueva*costo <1) {
                   tableModel.removeRow(celda);
                }
                
            } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(null, "Verifica la respuesta puesta", "ERROR", JOptionPane.WARNING_MESSAGE);
           
            }
        }
    }
});
           

            dibujo_de_la_tabla = new JScrollPane(table);
            Container contenedor = getContentPane();
            JPanel panel = new JPanel();
            contenedor.add(panel);
            table.setBackground(new Color(254, 255, 157));
            table.getTableHeader().setReorderingAllowed(false);
            panel.add(dibujo_de_la_tabla).setBounds(0, 0, getWidth(), getHeight() - 100);
            panel.add(total).setBounds(0, getWidth() - 50, 100, 30);
            panel.add(finalizar).setBounds(getWidth() - 100, getHeight() - 100, 100, 30);
            tiempo.start();
            finalizar.addActionListener(this);
            JScrollPane movimiento_en_la_ventana=new JScrollPane(panel);
              getContentPane().add(movimiento_en_la_ventana);
        }
    
        Timer tiempo = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                if (!(isVisible())) {
                    abierta_o_no = false;
                    tiempo.stop();
                }
            }
        });

        @Override
        public void actionPerformed(ActionEvent x) {
            if(x.getSource()==finalizar){
            
               // table.requestFocus(); 
                    table.setDefaultEditor(Object.class, null);
                    int confirmacion=JOptionPane.showConfirmDialog(this, "¿Seguro quieres terminar la compra?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                    if(confirmacion==JOptionPane.YES_OPTION){
                        productostikects=new String[table.getRowCount()];
                        precioticket=new float[table.getRowCount()];
                         preciocu=new String[table.getRowCount()];
                       for(int i=0; i<table.getRowCount();i++){
                        trabajo_bd.mandar_la_venta(i,Integer.parseInt( table.getValueAt(i,2).toString()), Integer.parseInt(table.getValueAt(i,4).toString()), table.getValueAt(i,3).toString(),String.valueOf( table.getValueAt(i,0)));
                         productostikects[i] = String.valueOf(table.getValueAt(i, 0));
                        precioticket[i] = Float.parseFloat(table.getValueAt(i, 4).toString());
           
                    suma += Integer.parseInt(table.getValueAt(i, 4).toString());
                   }
                       /*aqui pondras la referencia de la ventana osea de 
                       que se abrira la venta, en la ventana saldra el total si 
                       quieres tambien los productos que se compraron y tendra dos
                       votones uno que diga finalizar y el otro Implimir ticket*/
                       
                    trabajo_bd.implimir();
                   this.dispose();
                    
                    }
                    
                }
                            }
        
    }
static int tamaño_para_micropanales=12;
    public void aumentar_tamaño(Container contenedor, Container contenedor2, double tamaño) {
      for (int i = 0; i < contenedor.getComponents().length; i++) {
    if (contenedor.getComponent(i) instanceof JButton || contenedor.getComponent(i) instanceof JLabel) {
        contenedor.getComponent(i).setFont(new Font(contenedor.getComponent(i).getFont().getName(),
                contenedor.getComponent(i).getFont().getStyle(), (int) (tamaños[i] * tamaño)));
    }
    if(panel.getComponent(i) ==boton4){
        for(int y=0; y<menu_configuracion.getComponents().length;y++){
            menu_configuracion.getComponent(y).setFont(new Font(menu_configuracion.getComponent(y).getFont().getName(),
            menu_configuracion.getComponent(y).getFont().getStyle(),
             (int) (tamaños[i] * tamaño)));
        }
    }
}
      for (int i = 0; i < contenedor2.getComponents().length; i++) {
    if (contenedor2.getComponent(i) instanceof JButton || contenedor2.getComponent(i) instanceof JLabel) {
        contenedor2.getComponent(i).setFont(new Font(contenedor2.getComponent(i).getFont().getName(),
                contenedor2.getComponent(i).getFont().getStyle(), (int) (tamaños2[i] * tamaño)));
    }
}
      for (int y = 0; y < trabajo_con_bd.panel_para_productos.getComponents().length; y++) {
    if (trabajo_con_bd.panel_para_productos.getComponent(y) instanceof JPanel) {
        JPanel panelInterior = (JPanel) trabajo_con_bd.panel_para_productos.getComponent(y);

        for (int i = 0; i < panelInterior.getComponentCount(); i++) {
            if (panelInterior.getComponent(i) instanceof JLabel) {
                panelInterior.getComponent(i).setFont(new Font(
                        panelInterior.getComponent(i).getFont().getName(),
                        panelInterior.getComponent(i).getFont().getStyle(),
                        (int) (tamaños3[i] * tamaño)
                        
                ));
                tamaño_para_micropanales= (int) (tamaños3[i] * tamaño);
            }
        }
    }
}


/*
        for (Component componente : contenedor2.getComponents()) {
            if (componente instanceof JLabel || componente instanceof JButton) {

                if (titulo == componente) {
                    if (tamaño == 12) {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 28));

                    } else if (tamaño == 14) {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 31));
                    } else {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 34));
                    }

                } else if (frase == componente) {
                    if (tamaño == 12) {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 16));

                    } else if (tamaño == 14) {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 19));
                    } else {
                        componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), 22));
                    }
                }

            }
        }
        for (Component componente : contenedor.getComponents()) {

            if (componente instanceof JLabel || componente instanceof JButton) {
                componente.setFont(new Font(componente.getFont().getFontName(), componente.getFont().getStyle(), tamaño));
            }
        }*/
    }
    static String[] mensaje1;
    static String[] asunto1;
    static String[] fecha1;
    static boolean[] leido1;
    static int[] numero_de_mensaje;
    static int contador;
    static boolean ventana_abierta;

    public class ventana_de_mensajes extends JFrame implements ActionListener {

        JButton filtrar = new JButton("Filtrar");
        JPopupMenu menu_filtrar = new JPopupMenu();
        JMenuItem opcion1 = new JMenuItem("Todos");
        JMenuItem opcion2 = new JMenuItem("No leidos");
        JMenuItem opcion3 = new JMenuItem("Leidos");
        JPanel panel2;
        JPanel panel;
        JScrollPane scr;

        public ventana_de_mensajes() {
            this.setSize(500, 500);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setTitle("Mensajes");
            menu_filtrar.add(opcion1);
            opcion1.addActionListener(this);
            menu_filtrar.add(opcion2);
            opcion2.addActionListener(this);
            menu_filtrar.add(opcion3);
            opcion3.addActionListener(this);
            filtrar.add(menu_filtrar);
            filtrar.setBackground(new Color(210, 180, 222));
            filtrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            menu_filtrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           
            trabajo_bd.traer_mensajes2(0);
            Container contenedor = getContentPane();
            panel = new JPanel();
            panel2 = new JPanel();
            panel2.setLayout(null);
            int inclemento = 0;
            for (int i = 0; i < mensaje1.length; i++) {
                String leido_o_no;
                if (leido1[i] == true) {
                    leido_o_no = "Leido";
                } else {
                    leido_o_no = "No visto";
                }
                JLabel informacion = new JLabel("<html>Fecha: " + fecha1[i] + "<br>Lo manda: Administrador<br>Asunto:" + asunto1[i] + "<br>Estatus[" + leido_o_no + "]<html>");
                JPanel paneles_pequeños = new JPanel();
                paneles_pequeños.setBackground(new Color(246, 221, 204));
                paneles_pequeños.setCursor(new Cursor(Cursor.HAND_CURSOR));
                paneles_pequeños.setLayout(null);
                paneles_pequeños.addMouseListener(mos);
                paneles_pequeños.add(informacion).setBounds(0, 0, 500, 60);
                panel2.add(paneles_pequeños).setBounds(0, inclemento, 500, 60);
                inclemento += 100;
            }
            scr = new JScrollPane(panel2);
            panel2.setPreferredSize(new Dimension(500, inclemento));
            panel2.setBackground(new Color(255, 175, 203));
            panel.setBackground(new Color(255, 175, 203));
            contenedor.add(panel);
            panel.setLayout(null);
            panel.add(filtrar).setBounds(0, 0, 100, 30);
            panel.add(scr).setBounds(0, 50, 500, 450);
            filtrar.addActionListener(this);
            tiempo.start();
        }
        Timer tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                if (isVisible()) {
                    opcion4.setEnabled(false);
                    ventana_abierta = true;
                } else {
                    opcion4.setEnabled(true);
                    tiempo.stop();
                    ventana_abierta = false;
                }
            }

        });
        MouseAdapter mos = new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel panel_tocado = (JPanel) e.getSource();
                    panel_tocado.setBackground(new Color(246, 221, 204));
                }
                if (e.getSource() == filtrar) {
                    filtrar.setBackground(new Color(210, 180, 222));
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {
                    JPanel panel_tocado = (JPanel) e.getSource();
                    panel_tocado.setBackground(new Color(255, 205, 155));
                }
                if (e.getSource() == filtrar) {
                    filtrar.setBackground(new Color(154, 59, 255));
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() instanceof JPanel) {

                    contador = 0;
                    JPanel panel_seleccionado = (JPanel) e.getSource();
                    for (Component componentes : panel2.getComponents()) {
                        if (componentes == panel_seleccionado) {
                            System.out.println(contador);
                            mensajes_de_administrador h = new mensajes_de_administrador();
                            h.setVisible(true);
                            for (Component componentes2 : panel_seleccionado.getComponents()) {
                                if (componentes2 instanceof JLabel) {
                                    ((JLabel) componentes2).setText("<html>Fecha: " + fecha1[contador] + "<br>Lo manda: Administrador<br>Asunto:" + asunto1[contador] + "<br>Estatus[Leido]<html>");
                                }
                            }
                            return;
                        }
                        contador++;
                    }
                }

            }
        };

        @Override
        public void actionPerformed(ActionEvent x) {
            if (x.getSource() == filtrar) {
                menu_filtrar.show(filtrar, 100, 0);
            }
            if (opcion1 == x.getSource()) {
                mensajes_opciones(2);
            }
            if (opcion2 == x.getSource()) {
                mensajes_opciones(0);
            }
            if (opcion3 == x.getSource()) {
                mensajes_opciones(1);
            }
        }

        public void mensajes_opciones(int opcion) {
            panel2.removeAll();
            trabajo_bd.traer_mensajes2(opcion);
            int incremento = 0;
            for (int i = 0; i < mensaje1.length; i++) {
                String leido_o_no;
                if (leido1[i]) {
                    leido_o_no = "Leido";
                } else {
                    leido_o_no = "No visto";
                }
                JLabel informacion = new JLabel("<html>Fecha: " + fecha1[i] + "<br>Lo manda: Administrador<br>Asunto:" + asunto1[i] + "<br>Estatus[" + leido_o_no + "]<html>");
                JPanel paneles_pequeños = new JPanel();
                paneles_pequeños.setBackground(new Color(246, 221, 204));
                paneles_pequeños.setCursor(new Cursor(Cursor.HAND_CURSOR));
                paneles_pequeños.setLayout(null);
                paneles_pequeños.addMouseListener(mos);
                paneles_pequeños.add(informacion).setBounds(0, 0, 500, 60);
                panel2.add(paneles_pequeños).setBounds(0, incremento, 500, 60);
                incremento += 100;
            }
            panel2.setPreferredSize(new Dimension(500, incremento));
            panel2.revalidate();
            panel.repaint();
        }

    }

    public class mensajes_de_administrador extends JFrame {

        JLabel mensaje = new JLabel("<html>Fecha: " + fecha1[contador] + "<br>Lo manda: Operador<br>Asunto: " + asunto1[contador] + "<br> Mensaje: " + mensaje1[contador] + "<html>");

        public mensajes_de_administrador() {
            this.setSize(300, 300);
            this.setLocationRelativeTo(null);
            this.setTitle("Ver mensaje");
            Container contenedor = getContentPane();
            JPanel panel = new JPanel();
            contenedor.add(panel);
            mensaje.setFont(new Font("Lucida Fax", Font.PLAIN, 12));
            JScrollPane scr = new JScrollPane(panel);
            contenedor.add(scr);
            panel.setLayout(null);
            panel.setBackground(new Color(174, 214, 241));
            panel.setPreferredSize(new Dimension(getWidth(), getHeight()));
            panel.add(mensaje).setBounds(0, 0, getWidth(), getHeight());
           
            trabajo_bd.cambiar_a_leido(numero_de_mensaje[contador]);
            tiempo.start();
        }
        Timer tiempo = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                if (ventana_abierta == false) {
                    dispose();
                    tiempo.stop();
                }

            }

        });
    }

public  void reproducir_audios( String ruta) {
        if(currentSongIndex==1){
             try {
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(ruta));
            clip1 = AudioSystem.getClip();
            clip1.open(audioInputStream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        if (clip1 != null && !clip1.isRunning()) {
            clip1.start();
            
        }
        }
        
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
             inicio2 h = new inicio2();
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h.setVisible(true);
        } catch (UnsupportedLookAndFeelException  ex) {
            ex.getMessage();
        }
       

    }
}
