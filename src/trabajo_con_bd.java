
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.Date;
import base_de_datos.ConexionBaseDatos;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class trabajo_con_bd implements ActionListener {

    private String sql;
    private ResultSet rs;
    private PreparedStatement ps;
    private Connection conexion;
    ConexionBaseDatos conectar;

    public trabajo_con_bd() {
        conectar = new ConexionBaseDatos();
    }

    public boolean mesnaje_sopote(String mensaje, String asunto, String aquien_lo_manda) {
        Entrada h = new Entrada();
        try {
            Date hora = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(hora);
            conexion = conectar.getconexion();
            sql = "INSERT INTO mensajes (mensaje,fecha,asunto,quien_lo_manda, leido) VALUES(?,?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, mensaje);
            ps.setString(2, formattedDate);
            ps.setString(3, asunto);
            ps.setString(4, aquien_lo_manda);
            ps.setBoolean(5, false);
            ps.executeUpdate();
            if (ver == 0) {
                h.notificacion("Mensaje exitoso", "El mensaje se fue exitosamente :)");
            }
            return true;
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
            h.notificacion("Error", "El mensaje no se pudo enviar por favor de intentarlo despues");
            return false;

        }
        
    }

    
    public boolean notificacio_activa(int opcion) {
        try {
            conexion = conectar.getconexion();
            if(opcion==1){
                sql = "SELECT leido from mensajes where leido=0 AND quien_lo_manda = 'Vendedor1' ";
            }else{
               sql = "SELECT leido from mensajes where leido=0 AND quien_lo_manda = 'Administrador' "; 
            }
            
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }
        
    }    
    JButton agregar;
    JButton tres_puntos;
    JPopupMenu menu_tres_puntos;
     static JPanel panel_para_productos;
    static JTextField busqueda;
    int ver = 0;
      inicio2 istancia=null;
     JPanel panel_producto;
static JScrollPane scrollPane;
    public void traer_productos(inicio2 instanciaActual) {
        try {
            
            conexion = conectar.getconexion();
            sql = "SELECT * FROM productos  WHERE activo_o_inactivo=0";
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            istancia = instanciaActual;
             scrollPane = new JScrollPane();
            panel_para_productos = new JPanel(new GridLayout(0, 3, 10, 10));
             rs.last();
             resultados = rs.getRow();
            informacion=new String[resultados+1];
            resultados=0;
            rs.beforeFirst();
            while (rs.next()) {
                 resultados++;
                 informacion[resultados]="producto:"+rs.getString("nombre_de_producto")+"\ncodigo de barras:"+rs.getString("codigo_de_barras")+"\ntipo de producto:"+rs.getString("tipo_de_producto")+"\nprecio:"+rs.getString("precio")+"";
                Blob blob = rs.getBlob("imagen");

                if (blob != null) {
                    try (InputStream inputStream = blob.getBinaryStream()) {
                        if (inputStream != null) {
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            byte[] buffer = new byte[4096];
                            int bytesRead;

                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }

                            byte[] bytes = outputStream.toByteArray();
                            ImageIcon imageIcon = new ImageIcon(bytes);
                            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
                            JLabel imagen = new JLabel(imageIcon);
                            tres_puntos = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/more_horiz_FILL0_wght500_GRAD-25_opsz20.png")));
                            menu_tres_puntos = new JPopupMenu();
                            Entrada h = new Entrada();
                         menu_tres_puntos.add(new JMenuItem("Error en el precio")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el precio del producto\nInformacion del producto:\n"+informacion[cual], "Error de precio", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en el nombre")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el nombre del producto\nInformacion del producto:\n"+informacion[cual], "Error en el nombre", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en la imagen")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error la imagen del producto\nInformacion del producto:\n"+informacion[cual], "Error de la imagen", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en el código de barras")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el codigo de barras del producto\nInformacion del producto:\n"+informacion[cual], "Error de codigo de barras", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                            menu_tres_puntos.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            agregar = new JButton("Agregar");
                            agregar.setToolTipText("Agregar producto");
                            tres_puntos.setPreferredSize(new Dimension(20, 20));
                            agregar.setPreferredSize(new Dimension(100, 30));

                             Informacion_label = new JLabel("<html>Nombre: " + rs.getString("nombre_de_producto") + "<br>Código de barras: "
                                    + rs.getString("codigo_de_barras") + "<br>Precio: " + rs.getString("precio"));

                             panel_producto = new JPanel(null);
                            panel_producto.setPreferredSize(new Dimension(200, 200));
                            Informacion_label.setFont(new Font("Constantia", Font.PLAIN, inicio2.tamaño_para_micropanales));
                            imagen.setBounds(0, 0, 200, 100);
                            Informacion_label.setBounds(0, 105, 200, 55);
                            agregar.setBounds(100, 160, 100, 30);
                            agregar.setBackground(new Color(208, 233, 250));
                            tres_puntos.setBounds(300, 0, 20, 20);
                            tres_puntos.setToolTipText("Reportar Problema");
                            panel_producto.add(imagen);
                            panel_producto.add(Informacion_label);
                            panel_producto.add(agregar);
                            panel_producto.add(tres_puntos);
                            for (Component componentes : panel_producto.getComponents()) {
                                if (componentes instanceof JButton) {
                                    ((JButton) componentes).setCursor(new Cursor(Cursor.HAND_CURSOR));
                                    ((JButton) componentes).addActionListener((ActionListener) this);
                                }
                            }
                            panel_producto.setBackground(new Color(210, 180, 222));
                            panel_para_productos.add(panel_producto);
                        } else {
                            System.err.println("El InputStream de la imagen es nulo.");
                        }
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            
            busqueda = new JTextField(10);
            busqueda.setToolTipText("Inresa la informacion a buscar");
            busqueda.addKeyListener(key);
            scrollPane.setViewportView(panel_para_productos);
            instanciaActual.panel.add(scrollPane).setBounds(100, 300, 1000, 350); // Ajusta el tamaño según sea necesario
            
            instanciaActual.panel.add(busqueda).setBounds(450, 230, 250, 25);
            instanciaActual.panel.repaint();
            ver = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void informacion_producto(int producto) {
       
       
    try {
        conexion = conectar.getconexion();
        sql = "SELECT * FROM productos";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        int contador = 1;
        boolean codigoDeBarrasExistente = false;

        while (rs.next()) {
            if (contador == producto) {
                String codigoDeBarras = rs.getString("codigo_de_barras");
                for (int i = 0; i < istancia.tableModel.getRowCount(); i++) {
                    if (codigoDeBarras.equals(istancia.tableModel.getValueAt(i, 3))) {
                        int cantidadActual = (int) istancia.tableModel.getValueAt(i, 2);
                        istancia.tableModel.setValueAt(cantidadActual + 1, i, 2);
                        int nuevoSubtotal = (int) istancia.tableModel.getValueAt(i, 2) * rs.getInt("precio");
                        istancia.tableModel.setValueAt(nuevoSubtotal, i, 4);

                        codigoDeBarrasExistente = true;
                        break;
                    }
                }

                if (!codigoDeBarrasExistente) {
                    Object[] informacion = {
                        rs.getString("nombre_de_producto"), rs.getString("precio"), 1, codigoDeBarras, rs.getInt("precio") 
                    };
                   
                    istancia.tableModel.addRow(informacion);
                    
                }
            }
            contador++;
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        e.getMessage();
    }

   }

int cual;

    @Override
    public void actionPerformed(ActionEvent x) {
        if(x.getSource() instanceof JButton){
        JButton cual_toco = (JButton) x.getSource();
         cual = 1;
        System.err.println(busqueda.getText());
        for (Component componentes : panel_para_productos.getComponents()) {
            if (componentes instanceof JPanel) {
                for (Component componentes2 : ((JPanel) componentes).getComponents()) {
                    if (cual_toco == componentes2 && cual_toco.getText().equals("Agregar") && inicio2.abierta_o_no == true) {
                      
                        informacion_producto(cual);
                       inicio2.ventana_tabla_istancia.requestFocus();
                        return;
                    } else if (cual_toco == componentes2 && cual_toco.getText().equals("Agregar") && inicio2.abierta_o_no == false) {
                       inicio2.tabla_de_venta h= new  inicio2.tabla_de_venta();
                        h.setVisible(true);
                      informacion_producto(cual);
                    return;
                    } else if (cual_toco == componentes2 && cual_toco.getText().equals("")) {
                        menu_tres_puntos.show(componentes2, 0, 0);
                        return;
                    }
                }
                cual++;
            }
        }
         
        }
    }

    int donde=0;
      private StringBuilder scannedData = new StringBuilder();
    KeyAdapter key = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == busqueda) {
                istancia.panel.remove(scrollPane);
                busqueda_todo(inicio2.choice.getSelectedItem(),inicio2.choice2.getSelectedItem(), busqueda.getText());
             
        }}
         @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (ch == KeyEvent.VK_ENTER) {
                    //JOptionPane.showMessageDialog(null, "holaaa");
                       
                        imprimirConEscaner();
                    scannedData.setLength(0); 
                    busqueda.setText(""); 
                }
                }
    };
            
    public void imprimirConEscaner() {
    try {
        conexion = conectar.getconexion();
         sql = "SELECT * FROM productos WHERE codigo_de_barras=?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, busqueda.getText());
        rs = ps.executeQuery();

        boolean codigoDeBarrasExistente = false;

        if (rs.next()) {
            // Obtener los datos del producto desde la base de datos
            String nombreProducto = rs.getString("nombre_de_producto");
            int precio = rs.getInt("precio");
            String codigoDeBarras = rs.getString("codigo_de_barras");

            // Buscar el producto en la tabla
            for (int i = 0; i < istancia.tableModel.getRowCount(); i++) {
                if (codigoDeBarras.equals(istancia.tableModel.getValueAt(i, 3))) {
                    // Producto encontrado, incrementar cantidad y subtotal
                    int cantidadActual = (int) istancia.tableModel.getValueAt(i, 2);
                    int nuevaCantidad = cantidadActual + 1;
                    istancia.tableModel.setValueAt(nuevaCantidad, i, 2);

                    int nuevoSubtotal = nuevaCantidad * precio;
                    istancia.tableModel.setValueAt(nuevoSubtotal, i, 4);

                    codigoDeBarrasExistente = true;
                    break;
                }
            }

            if (!codigoDeBarrasExistente) {
                // Producto no encontrado en la tabla, agregar nueva fila
                Object[] informacion = {
                    nombreProducto, precio, 1, codigoDeBarras, precio
                };
                istancia.tableModel.addRow(informacion);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
String[] informacion;
int resultados;
static JLabel Informacion_label ;
    public void busqueda_todo(String busqueda, String categoria, String busqueda2) {
        try {
            conexion = conectar.getconexion();
           if (busqueda.equals("Todas las secciones") && categoria.equals("Todos los metodos")) {
    sql = "SELECT * FROM productos WHERE tipo_de_producto LIKE ? OR codigo_de_barras LIKE ? OR nombre_de_producto LIKE ? OR precio LIKE ?";
    ps = conexion.prepareStatement(sql);
    ps.setString(1, busqueda2 + "%");
    ps.setString(2, busqueda2 + "%");
    ps.setString(3, busqueda2 + "%");
    ps.setString(4, busqueda2 + "%");
System.out.print(""+donde+1);
            }else if (!busqueda.equals("Todas las secciones") && categoria.equals("Todos los metodos")) {
    sql = "SELECT * FROM productos WHERE tipo_de_producto=? AND (nombre_de_producto LIKE ? OR precio LIKE ? OR codigo_de_barras LIKE ?)";
    ps = conexion.prepareStatement(sql);
    ps.setString(1, busqueda);  
    ps.setString(2, "%" + busqueda2 + "%");  
    ps.setString(3, "%" + busqueda2 + "%");  
    ps.setString(4, "%" + busqueda2 + "%");
                
System.out.print(""+donde+2);
            }else if(!busqueda.equals("Todas las secciones") && !categoria.equals("Todos los metodos")){
                if(categoria.equals("Nombre del producto")){
                     sql = "SELECT * FROM productos WHERE tipo_de_producto=? AND nombre_de_producto  LIKE ? ";
                }else if(categoria.equals("Codigo de barras")){
                    
                     sql = "SELECT * FROM productos WHERE tipo_de_producto=? AND codigo_de_barras  LIKE ? ";
                }else{
                     sql = "SELECT * FROM productos WHERE tipo_de_producto=? AND precio  LIKE ? ";
                }
                 ps = conexion.prepareStatement(sql);
                  ps.setString(1,  busqueda);  
            ps.setString(2, busqueda2 + "%");
            
            }else if(busqueda.equals("Todas las secciones") && !categoria.equals("Todos los metodos")){
                
                    if(categoria.equals("Nombre del producto")){
                     sql = "SELECT * FROM productos WHERE  nombre_de_producto  LIKE ? ";
                     
                }else if(categoria.equals("Codigo de barras")){
                    
                     sql = "SELECT * FROM productos WHERE codigo_de_barras  LIKE ? ";
                }else{
                    
                     sql = "SELECT * FROM productos WHERE   precio  LIKE ? ";
                }
                 ps = conexion.prepareStatement(sql);
                  ps.setString(1,  busqueda2 + "%");              }
            
            rs = ps.executeQuery();
             rs.last();
             resultados = rs.getRow();
            informacion=new String[resultados+1];
            resultados=0;
            rs.beforeFirst();
              scrollPane = new JScrollPane();
             panel_para_productos = new JPanel(new GridLayout(0, 3, 10, 10));
            while (rs.next()) {
                resultados++;
                informacion[resultados]="producto:"+rs.getString("nombre_de_producto")+"\ncodigo de barras:"+rs.getString("codigo_de_barras")+"\ntipo de producto:"+rs.getString("tipo_de_producto")+"\nprecio:"+rs.getString("precio")+"";
                 System.out.println( informacion[resultados]);
               
            
            Blob blob = rs.getBlob("imagen");

            if (blob != null) {
                try (InputStream inputStream = blob.getBinaryStream()) {
                    if (inputStream != null) {
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] bytes = outputStream.toByteArray();
                        ImageIcon imageIcon = new ImageIcon(bytes);

                        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(200, 100, Image.SCALE_DEFAULT));
                        JLabel imagen = new JLabel(imageIcon);
                        tres_puntos = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/more_horiz_FILL0_wght500_GRAD-25_opsz20.png")));
                        menu_tres_puntos = new JPopupMenu();
                        Entrada h = new Entrada();
                        menu_tres_puntos.add(new JMenuItem("Error en el precio")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el precio del producto\nInformacion del producto:\n"+informacion[cual], "Error de precio", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en el nombre")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el nombre del producto\nInformacion del producto:\n"+informacion[cual], "Error en el nombre", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en la imagen")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error la imagen del producto\nInformacion del producto:\n"+informacion[cual], "Error de la imagen", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.add(new JMenuItem("Error en el código de barras")).addActionListener(e -> {
                            ver = 1;
                            this.mesnaje_sopote("Hay un error en el codigo de barras del producto\nInformacion del producto:\n"+informacion[cual], "Error de codigo de barras", "Vendedor1");
                            h.notificacion("Reporte", "El reporte se ha ido");

                        });
                        menu_tres_puntos.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        agregar = new JButton("Agregar");
                        agregar.setToolTipText("Agregar producto");
                        tres_puntos.setPreferredSize(new Dimension(20, 20));
                        agregar.setPreferredSize(new Dimension(100, 30));

                         Informacion_label = new JLabel("<html>Nombre: " + rs.getString("nombre_de_producto") + "<br>Código de barras: "
                                + rs.getString("codigo_de_barras") + "<br>Precio: " + rs.getString("precio"));
                         Informacion_label.setFont(new Font("Constantia", Font.PLAIN, inicio2.tamaño_para_micropanales));
                         panel_producto = new JPanel(null);
                        panel_producto.setPreferredSize(new Dimension(200, 200));

                        imagen.setBounds(0, 0, 200, 100);
                        Informacion_label.setBounds(0, 105, 200, 80);
                        agregar.setBounds(100, 160, 100, 30);
                        agregar.setBackground(new Color(208, 233, 250));
                        tres_puntos.setBounds(300, 0, 20, 20);
                        tres_puntos.setToolTipText("Reportar Problema");
                        panel_producto.add(imagen);
                        panel_producto.add(Informacion_label);
                        panel_producto.add(agregar);
                        panel_producto.add(tres_puntos);
                        for (Component componentes : panel_producto.getComponents()) {
                            if (componentes instanceof JButton) {
                                ((JButton) componentes).setCursor(new Cursor(Cursor.HAND_CURSOR));
                                ((JButton) componentes).addActionListener((ActionListener) this);
                            }
                        }
                        panel_producto.setBackground(new Color(210, 180, 222));
                        panel_para_productos.add(panel_producto);
                    } else {
                        System.err.println("El InputStream de la imagen es nulo.");
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        scrollPane.setViewportView(panel_para_productos);
          istancia.panel.add(scrollPane).setBounds(100, 300, 1000, 350);
        istancia.panel.repaint();
        ver = 0;
        System.out.print("hola\n");
    }
    catch(SQLException e

    
        ){
            e.printStackTrace();
        e.getMessage();
    }
}
    public void traer_mensajes2(int opcion){
        try{
            conexion=conectar.getconexion();
            if(opcion==0){
              sql="SELECT * FROM mensajes WHERE leido=0 AND quien_lo_manda='Administrador'";  
            }else if (opcion==1){
               sql="SELECT * FROM mensajes WHERE leido=1 AND quien_lo_manda='Administrador'"; 
            }else if (opcion==2){
                 sql="SELECT * FROM mensajes WHERE quien_lo_manda='Administrador'"; 
            }else if(opcion==3){
                sql="SELECT * FROM mensajes WHERE quien_lo_manda='Vendedor1' AND leido=0";
                
            }else if (opcion==4){
                 sql="SELECT * FROM mensajes WHERE quien_lo_manda='Vendedor1' AND leido=1";
            }else if(opcion==5){
                 sql="SELECT * FROM mensajes WHERE quien_lo_manda='Vendedor1' ";
            }
            
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            rs.last();
            int filas=rs.getRow();
            if(opcion>=0 && opcion<3){
               inicio2.mensaje1=new String[filas];
             inicio2.asunto1=new String[filas];
              inicio2.fecha1=new String[filas];
               inicio2.leido1=new boolean[filas];
               inicio2.numero_de_mensaje=new int[filas]; 
            }else{
                Administrador.mensaje=new String[filas];
             Administrador.asunto=new String[filas];
              Administrador.fecha=new String[filas];
               Administrador.leido=new boolean[filas];
               Administrador.numero_de_mensaje=new int[filas]; 
            }
           
               rs.beforeFirst();
               filas=0;
            while(rs.next()){
                 if(opcion>=0 && opcion<3){
                 inicio2.mensaje1[filas]=rs.getString("mensaje");
                 inicio2.asunto1[filas]=rs.getString("asunto");
                  inicio2.fecha1[filas]=rs.getString("fecha");
                  inicio2.leido1[filas]=rs.getBoolean("leido");
                  inicio2.numero_de_mensaje[filas]=rs.getInt("numero_de_mensaje");
                 }else{
                      Administrador.mensaje[filas]=rs.getString("mensaje");
                 Administrador.asunto[filas]=rs.getString("asunto");
                  Administrador.fecha[filas]=rs.getString("fecha");
                  Administrador.leido[filas]=rs.getBoolean("leido");
                  Administrador.numero_de_mensaje[filas]=rs.getInt("numero_de_mensaje");
                 }
            filas+=1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void cambiar_a_leido(int numero){
        
    try{
       conexion=conectar.getconexion();
       sql="UPDATE mensajes SET leido=1 WHERE numero_de_mensaje=?";
       ps=conexion.prepareStatement(sql);
       ps.setInt(1, numero);
       ps.executeUpdate();
    }catch(SQLException e){
        e.printStackTrace();
    }
}
    public void mandar_la_venta(int contador, int cantidad_vendida, float total_de_esa_venta, String codigo_de_barras_foreign, String producto) {
    try {
        conexion = conectar.getconexion();
        sql = "SELECT * FROM reportes WHERE codigo_de_barras_foreign=? AND fecha=?";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, codigo_de_barras_foreign);
        Date dia = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        ps.setString(2, formato.format(dia));

        rs = ps.executeQuery();

        if (rs.next()) {
            sql = "UPDATE reportes SET cantidad_vendida = ?, total_de_esa_venta = ? WHERE codigo_de_barras_foreign = ? AND fecha = ?";
            ps = conexion.prepareStatement(sql);

            ps.setInt(1, rs.getInt("cantidad_vendida") + cantidad_vendida);
            ps.setFloat(2, rs.getFloat("total_de_esa_venta") + total_de_esa_venta);
            ps.setString(3, codigo_de_barras_foreign);
            ps.setString(4, rs.getString("fecha"));

            ps.executeUpdate();
        } else {
            sql = "INSERT INTO reportes (fecha, codigo_de_barras_foreign, cantidad_vendida, total_de_esa_venta, productos) VALUES (?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);

            ps.setString(1, formato.format(dia));
            ps.setString(2, codigo_de_barras_foreign);
            ps.setInt(3, cantidad_vendida);
            ps.setFloat(4, total_de_esa_venta);
            ps.setString(5, producto);

            ps.executeUpdate();
        }
         } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    public void implimir(){
        
        PrinterMatrix printer = new PrinterMatrix();
       Extenso e = new Extenso();
        e.setNumber(101.85);

        printer.setOutSize(20, 32); // Ajusta el tamaño de la matriz
        printer.printCharAtCol(1, 1, 32, "=");
        printer.printTextWrap(1, 2, 8, 32, "Las Moninas");

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        printer.printTextWrap(2, 3, 1, 32, "Productos");
        printer.printTextWrap(3, 3, 1, 32, "Fecha de Emisión " + formattedDate);
        printer.printTextWrap(4, 3, 1, 32, "Hora: " + formattedTime);
for (int i = 0; i < inicio2.productostikects.length; i++) {
   printer.printTextWrap(5 + i, 3, 1, 32, inicio2.productostikects[i]); // Imprimir nombre del producto
   printer.printTextWrap(5 + i, 3, 16, 32, "$"+String.valueOf(inicio2.precioticket[i])); // Imprimir precio del producto
}


        // Imprimir el total
        printer.printTextWrap(6 + inicio2.productostikects.length, 3, 1, 32, "Total: $" + inicio2.suma);
        printer.printTextWrap(7 + inicio2.productostikects.length, 3, 1, 32, "Sabor y arte en cada una de las ");
printer.printTextWrap(8+ inicio2.productostikects.length, 3, 1, 32, "migajas.");
printer.printTextWrap(9 + inicio2.productostikects.length, 3, 1, 32, " ");
printer.printTextWrap(10 + inicio2.productostikects.length, 3, 1, 32, " ");
        String fileName = "impresion.txt";
        printer.toFile(fileName);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc documento = new SimpleDoc(inputStream, docFormat, null);
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(documento, attributeSet);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            System.err.println("No hay una impresora instalada");
        }
   
    }
      public void traer_datos_para_tabla_de_ventas(Date fecha, int opcion){
            try{
                conexion=conectar.getconexion();
                sql="SELECT * from reportes where fecha=?";
                 SimpleDateFormat  formato_de_fecha= new SimpleDateFormat("yyyy/MM/dd");
                 
                ps=conexion.prepareStatement(sql);
                ps.setString(1,formato_de_fecha.format(fecha) );
                rs=ps.executeQuery();
                float fin=0;
                float fin2=0;
                while(rs.next()){
                  Object[] filas={
                            rs.getString("productos"),rs.getString("codigo_de_barras_foreign"),rs.getFloat("total_de_esa_venta")/rs.getInt("cantidad_vendida"),rs.getFloat("cantidad_vendida"),rs.getFloat("total_de_esa_venta")
                        };
                  if(opcion==1){
                        inicio2.modelo_de_la_tabla.addRow(filas); 
                        fin=fin+rs.getFloat("total_de_esa_venta");
                    }else{
                        
                         Administrador.modelo_de_la_tabla.addRow(filas);
                          fin2=fin2+rs.getFloat("total_de_esa_venta");
                    }
                   
                   
                }
                 if(opcion==1){
                        inicio2.modelo_de_la_tabla.addRow(new Object[]{"Total: "+fin," "," "," "," "}); 
                    }else{
                        
                         Administrador.modelo_de_la_tabla.addRow(new Object[]{"Total: "+fin2," "," "," "," "}); 
                    } 
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
      static String enviar_informacion[];
      static BufferedImage imagen;
      public int buscar_producto(String producto){
          try{
              int resultado=-1;
              if (producto.contains(" ")){
                 JOptionPane.showMessageDialog(null,"El codigo de barras no debe que contener espacios", "ERROR", JOptionPane.WARNING_MESSAGE);
              resultado=50;
              }else if(producto.isEmpty()){
                  JOptionPane.showMessageDialog(null,"No haz ingresado el codigo de barras\nPor favror ingresalo", "ERROR",JOptionPane.WARNING_MESSAGE);
              resultado=100;
              }else{
              conexion=conectar.getconexion();
              sql="SELECT * FROM productos where codigo_de_barras=?";
              ps=conexion.prepareStatement(sql);
              ps.setString(1, producto);
              rs=ps.executeQuery();
              if(!rs.next()){
                  UIManager.put("OptionPane.yesButtonText", "Si");
                  UIManager.put("OptionPane.noButtonText", "Más tarde");
                  int respuesta=JOptionPane.showConfirmDialog(null, "El codigo de barras no exixte en la base de datos\n Quieres ir a agregarlo", "ERROR",JOptionPane.YES_NO_OPTION);
              if(respuesta==JOptionPane.YES_OPTION){
                  resultado= 1;
              }else {
                  resultado= 0;
              }
              }else{
                    
                 enviar_informacion=new String[]{
                     rs.getString("nombre_de_producto"), rs.getString("precio")
                 }; 
                 Blob blob = rs.getBlob("imagen");
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

                  try {
                       imagen = ImageIO.read(inputStream);
                  } catch (IOException ex) {
                      Logger.getLogger(trabajo_con_bd.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              }
              return resultado;
          }catch(SQLException e){
              e.printStackTrace();
              return 2;
          }
      }
       public void cambiar_informacion(String codigo_de_barras, String nombre_de_producto,File imagen,  String precio){
       try{
          conexion=conectar.getconexion();
          if(codigo_de_barras.isEmpty()||nombre_de_producto.isEmpty()||imagen==null|| precio.isEmpty()){
              sql="SELECT * FROM productos WHERE codigo_de_barras=?";
              ps=conexion.prepareStatement(sql);
              ps.setString(1, codigo_de_barras);
             rs= ps.executeQuery();
             if(rs.next()){
                
                           sql="UPDATE productos SET nombre_de_producto=?, precio=?, imagen=? WHERE codigo_de_barras=? ";
              ps=conexion.prepareStatement(sql);
            
            
            if(nombre_de_producto.isEmpty()){
                   ps.setString(1, rs.getString("nombre_de_producto"));
         
             }else{
                 ps.setString(1, nombre_de_producto);
            }
             if(precio.equals("no se escribio")){
          ps.setFloat(2, rs.getFloat("precio"));
             }else{
                  if( Float.parseFloat(precio)<=0 ){
              JOptionPane.showMessageDialog(null, "El precio debe que ser mayor a 0", "ERROR", JOptionPane.WARNING_MESSAGE);
         return;
                  }else{
                 ps.setFloat(2,Float.parseFloat(precio)); 
                  }
             }
             if(imagen==null){
          ps.setBlob(3, rs.getBlob("imagen"));
             }else{
                ps.setBytes(3, Files.readAllBytes(Administrador.archivoSeleccionado.getAbsoluteFile().toPath()));
             }
             
                ps.setString(4, codigo_de_barras);
          
          ps.executeUpdate();
         
           Administrador.modificar_informacion.setEnabled(true);
                  Administrador.modificar_informacion.doClick();
                  Administrador.modificar_informacion.setEnabled(false); 
          JOptionPane.showMessageDialog(null, "Los cambios se realizarion exitosamente", "CAMBIOS EXITOSOS", JOptionPane.INFORMATION_MESSAGE);
            Administrador h=new Administrador(); 
             }
          }else{
          sql="UPDATE productos SET nombre_de_producto=?, precio=?, imagen=? WHERE codigo_de_barras=?";
          ps=conexion.prepareStatement(sql);
          ps.setString(1, nombre_de_producto);
          
          ps.setFloat(2, Float.parseFloat(precio));
          
          ps.setBytes(3, Files.readAllBytes(Administrador.archivoSeleccionado.getAbsoluteFile().toPath()));
          ps.setString(4, codigo_de_barras );
          if( Float.parseFloat(precio)<=0){
              JOptionPane.showMessageDialog(null, "El precio debe que ser mayor a 0", "ERROR", JOptionPane.WARNING_MESSAGE);
          }else{
          ps.executeUpdate();
          JOptionPane.showMessageDialog(null, "Los cambios se realizarion exitosamente", "CAMBIOS EXITOSOS", JOptionPane.INFORMATION_MESSAGE);
           Administrador.modificar_informacion.setEnabled(true);
                  Administrador.modificar_informacion.doClick();
                  Administrador.modificar_informacion.setEnabled(false); 
          }
          
          }
       }catch(NumberFormatException e){
          e.printStackTrace();
         JOptionPane.showMessageDialog(null,"El precio debe que ser con numeros", "ERROR",JOptionPane.WARNING_MESSAGE);
       }catch(IOException e2){
           JOptionPane.showMessageDialog(null, "Hubo un problema en la imagen escoje otra porfavor", "ERROR", JOptionPane.OK_OPTION);
       }catch(SQLException e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Los cambios no se pudieron hacer intentalo despues ", "ERROR", JOptionPane.OK_OPTION);
       }
       Administrador.archivoSeleccionado=null;
   }
       public void desabilitar_producto(String codigo){
           try{
               
               conexion=conectar.getconexion();
               sql="SELECT * FROM productos WHERE codigo_de_barras=?";
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               rs=ps.executeQuery();
               if(rs.next()){
               sql="SELECT activo_o_inactivo FROM productos WHERE codigo_de_barras=? AND (activo_o_inactivo=1)";
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               rs=ps.executeQuery();
               if(rs.next()){
                   UIManager.put("OptionPane.noButtonText", "No, por ahora no");
                   UIManager.put("OptionPane.yesButtonText", "Si, Habilitalo");
                   int opcion=JOptionPane.showConfirmDialog(null,"el codigo esta desabilitado\n ¿Lo quieres hailitar ?", "Habilitar", JOptionPane.YES_NO_OPTION);
              if(opcion==JOptionPane.YES_OPTION){
                  sql="UPDATE productos SET activo_o_inactivo=0 WHERE codigo_de_barras=?";
                  ps=conexion.prepareStatement(sql);
                  ps.setString(1, codigo);
                  ps.executeUpdate();
                  Entrada h=new Entrada();
                  h.notificacion("Producto Habilitado", "El producto se habilito correctamente\nListo para usarlo de nuevo");
              }
               }else if (!rs.next()){
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               sql="UPDATE productos SET activo_o_inactivo=1 WHERE codigo_de_barras=?";
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               ps.executeUpdate();
               Entrada h=new Entrada();
               h.notificacion("Desabilitacion exitosa", "El producto se ha desabilitado correctamente");
               }
               }else{
                   UIManager.put("OptionPane.noJButtonText", "No, por ahora no");
                   
                   UIManager.put("OptionPane.yesJButtonText", "Si, ir a crearlo");
                  int opcion= JOptionPane.showConfirmDialog(null, "El codigo de barras no existe\n¿Quieres crear un producto?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                   if(opcion==JOptionPane.YES_OPTION){
                       Administrador.agregar_producto.doClick();
                   }
               }
           }catch(SQLException e){
               e.printStackTrace();
           }
       }
       public int crear_producto(String codigo,String nombre,String tipo_de_pan, String precio){
           int resultado=0;
           try{
           
               conexion=conectar.getconexion();
               sql="SELECT codigo_de_barras FROM productos WHERE codigo_de_barras=?";
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               rs=ps.executeQuery();
               if(!rs.next()){
               sql="INSERT INTO productos(codigo_de_barras,nombre_de_producto,tipo_de_producto,precio,imagen, activo_o_inactivo) VALUES (?,?,?,?,?, 0)";
               ps=conexion.prepareStatement(sql);
               ps.setString(1, codigo);
               ps.setString(2, nombre);
               ps.setString(3, tipo_de_pan);
               if(Float.parseFloat(precio)<=0){
                   JOptionPane.showMessageDialog(null, "El precio debe que ser mayor a 0", "ERROR", JOptionPane.WARNING_MESSAGE);
               }else{
               ps.setFloat(4, Float.parseFloat(precio));
               ps.setBytes(5, Files.readAllBytes(Administrador.archivoSeleccionado.getAbsoluteFile().toPath()));
               ps.executeUpdate();
               resultado=1;
               Entrada h =new Entrada();
               h.notificacion("Producto creado exitosamente", "El producto se ha creado exitosamente listo para usarlo");
               
               }
               }else{
                   JOptionPane.showMessageDialog(null, "El producto ya existe", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
               }
           }catch(NumberFormatException e){
                 JOptionPane.showMessageDialog(null, "El precio debe que ser con numeros", "ERROR", JOptionPane.WARNING_MESSAGE);
                 e.printStackTrace();
           }catch(IOException e){
               e.printStackTrace();
           }catch(SQLException e){
               e.printStackTrace();
           }
           Administrador.archivoSeleccionado=null;
           return resultado;
       }
       static String[] datos_a_b;
       static String[] datos_a_b1;
       static String[] datos_a_b2;
       static String[] datos_a_b3;
       static String[] datos_a_b4;
       static String[] datos_a_b5;
       public void traer_datos_del_dia1_al_2(Date uno, Date dos){
           try{
               SimpleDateFormat formato=new SimpleDateFormat("yyyy/MM/dd");
               conexion=conectar.getconexion();
               sql="SELECT * from reportes WHERE fecha BETWEEN ? AND ?";
               ps=conexion.prepareStatement(sql);
               ps.setString(1,formato.format(uno));
               ps.setString(2, formato.format(dos));
               rs=ps.executeQuery();
               rs.last();
               datos_a_b=new String[rs.getRow()];
               datos_a_b1=new String [rs.getRow()];
               datos_a_b2=new String [rs.getRow()];
               datos_a_b3=new String [rs.getRow()];
               datos_a_b4=new String [rs.getRow()];
               datos_a_b5=new String [rs.getRow()];
               rs.beforeFirst();
               int contador=0;
               while(rs.next()){
                   datos_a_b[contador]=rs.getString("fecha");
                  datos_a_b1[contador]= rs.getString("productos");
                   datos_a_b2[contador]= ""+rs.getString("codigo_de_barras_foreign");
                           datos_a_b3[contador]=""+ rs.getFloat("total_de_esa_venta")/rs.getInt("cantidad_vendida");
                           datos_a_b4[contador]= ""+rs.getFloat("cantidad_vendida");
                           datos_a_b5[contador]= ""+rs.getFloat("total_de_esa_venta");
                  contador++;
               }
           }catch(SQLException e){
               
           }
       }
       static String[] codigo_tabla;
        static String[] cantidad;
        static String[] promedio;
       public void datos_para_tabla_dos_pdf(Date dia1, Date dia2){
           try{
               SimpleDateFormat formato=new SimpleDateFormat("yyyy/MM/dd");
               conexion=conectar.getconexion();
               sql="SELECT  codigo_de_barras_foreign, SUM(cantidad_vendida) as total_cantidad_vendida, (SELECT SUM(cantidad_vendida) FROM reportes WHERE fecha BETWEEN ? AND ?) as total_general_cantidad_vendida FROM reportes WHERE fecha BETWEEN ? AND ? GROUP BY codigo_de_barras_foreign;";
               ps=conexion.prepareStatement(sql);
               ps.setString(1,formato.format(dia1));
               ps.setString(2,formato.format(dia2));
               ps.setString(3,formato.format(dia1));
               ps.setString(4,formato.format(dia2));
               rs=ps.executeQuery();
               rs.last();
               int contador=0;
               codigo_tabla=new String[rs.getRow()];
               cantidad=new String[rs.getRow()];
              promedio=new String[rs.getRow()];
               rs.beforeFirst();
               while(rs.next()){
                   codigo_tabla[contador]=rs.getString("codigo_de_barras_foreign");
                   cantidad[contador]=rs.getString("total_cantidad_vendida");
                   System.out.println(""+rs.getInt("total_cantidad_vendida")/rs.getInt("total_general_cantidad_vendida")*100);
                    double ratio = (double) rs.getInt("total_cantidad_vendida") / rs.getInt("total_general_cantidad_vendida") * 100;
    promedio[contador] = String.format("%.2f%%", ratio);
    contador++;
               }
               
           }catch(SQLException e){
               e.printStackTrace();
           }
       }
    }

