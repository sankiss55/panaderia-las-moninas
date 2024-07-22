import com.formdev.flatlaf.FlatIntelliJLaf;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.javafx.application.PlatformImpl;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.table.DefaultTableModel;
public class Administrador extends JFrame implements ActionListener {
     static JButton agregar_producto; 
     JButton audio=new JButton(new ImageIcon(this.getClass().getResource("/imagenes/hearing_FILL0_wght400_GRAD0_opsz24.png")));
    JButton eliminar_producto = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/cancel_FILL0_wght400_GRAD0_opsz24.png")));
    static JButton modificar_informacion;
    JButton salir=new JButton(new ImageIcon(this.getClass().getResource("/imagenes/power_settings_new_FILL0_wght400_GRAD0_opsz24.png")));
    JButton reportes =new JButton (new ImageIcon(this.getClass().getResource("/imagenes/lab_profile_FILL0_wght400_GRAD0_opsz24.png")));
   JButton enviar_mensaje =new JButton(new ImageIcon(this.getClass().getResource("/imagenes/mail_FILL0_wght400_GRAD-25_opsz24.png")));
    JPopupMenu menu_desplegable=new JPopupMenu();
    JMenuItem cerrar_sesion=new JMenuItem("Cerrar sesión");
    JMenuItem salir_del_programa=new JMenuItem("Salir");
    JButton notificaciones=new JButton(new ImageIcon(this.getClass().getResource("/imagenes/notifications_FILL0_wght400_GRAD0_opsz24.png")));
    JTextArea mensajeria=new JTextArea();
    JScrollPane scl=new JScrollPane(mensajeria);
    JButton enviar_mail =new JButton("Enviar");
    JLabel mensajetxt=new JLabel("Escribe el mensaje aqui:");
    JLabel asuntotxt=new JLabel("Asunto:");
    JTextField asuntores=new JTextField(10);
     JPanel panel2;
      JPanel panel3;
     JButton filtrar=new JButton("Filtrar", new ImageIcon(this.getClass().getResource("/imagenes/filter_list_FILL0_wght400_GRAD0_opsz24.png")));
    JPanel panel_paramicro_paneles=new JPanel();
    JScrollPane scr_panelmicro;
    JPopupMenu menu_filtrar=new JPopupMenu();
    JMenuItem opcion_todos=new JMenuItem("Todos");
    JMenuItem opcion_leidos=new JMenuItem("Leidos");
    JMenuItem opcion_no_leidos=new JMenuItem("No leidos");
    
    trabajo_con_bd conexion = new trabajo_con_bd();
     public Administrador(){
        tiempo.start();
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/badge_48dp_FILL0_wght400_GRAD0_opsz48.png")).getImage());
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setTitle("Administrador");
        this.setResizable(false);
         agregar_producto = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/add_circle_FILL0_wght400_GRAD0_opsz24.png")));
       modificar_informacion = new JButton(new ImageIcon(this.getClass().getResource("/imagenes/published_with_changes_FILL0_wght400_GRAD0_opsz24.png")));
         Component[]componentes={
            audio,agregar_producto,eliminar_producto,modificar_informacion, salir,reportes,notificaciones,enviar_mensaje,enviar_mail
        };
       audio.setToolTipText("Activa la funcion audio");
         agregar_producto.setToolTipText("Crea un nuevo producto");
         eliminar_producto.setToolTipText("Desactiva algun producto");
         modificar_informacion.setToolTipText("Modifica la informacion del algun producto");
       reportes.setToolTipText("Revisa algun reporte del dia");
         for(Component componets : componentes){
            componets.setCursor(new Cursor(Cursor.HAND_CURSOR));
            ((JButton) componets).addActionListener(this);
           //quitar_fondo( ((JButton) componets));
          ((JButton) componets).setBackground(new Color(174, 214, 241));
           ((JButton) componets).setFocusPainted(false);
            ((JButton)componets).putClientProperty("JButton.buttonType", "roundRect");
            ((JButton)componets).addMouseListener(mos);
        }
        mensajeria.setLineWrap(true);
        
        menu_desplegable.add(cerrar_sesion);
        menu_desplegable.add(salir_del_programa);
        cerrar_sesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir_del_programa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cerrar_sesion.addActionListener(this);
        salir_del_programa.addActionListener(this);
        enviar_mail.setBackground(new Color(255, 137, 78));
        Container contenedor =getContentPane();
        JPanel panel =new JPanel ();
         panel2=new JPanel(){
            @Override
            public void paintComponent(Graphics x){
                super.paintComponent(x);
                x.setColor(new Color(61, 102, 175));
                x.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panel2.setBackground(new Color(0,0,0,0));
        panel3=new JPanel();
       panel3.setLayout(null);
        contenedor.add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(212, 212, 212));
        panel2.setLayout(null);
        panel.add(panel2).setBounds(15,15,getWidth()-45, getHeight()-75);
        panel2.add(panel3).setBounds(50,20,getWidth()-115, getHeight()-105);
        panel2.add(agregar_producto).setBounds(5,40,40,40);
        panel2.add(eliminar_producto).setBounds(5,90,40,40);
        panel2.add(modificar_informacion).setBounds(5,150,40,40);
        panel2.add(salir).setBounds(5,450,40,40);
        panel2.add(notificaciones).setBounds(5,390,40,40);
        panel2.add(reportes).setBounds(5,330,40,40);
        panel2.add(enviar_mensaje).setBounds(5,270,40,40);
        panel3.add(scl).setBounds(0,100,getWidth()-115, 350);
        panel3.add(enviar_mail).setBounds(0,450, 100,20);
        panel3.add(asuntotxt).setBounds(0,0,150,20);
        panel3.add(asuntores).setBounds(0,30,getWidth()-115,30);
        panel3.add(mensajetxt).setBounds(0,60,150,30);
        panel2.add(audio).setBounds(5,210,40,40);
       panel3.setBackground(new Color(255, 252, 147));
       enviar_mensaje.setEnabled(false);
       boton_anteror=enviar_mensaje;
            asuntotxt.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/asuntogmail.wav");
                }
            });
             asuntotxt.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/asuntogmail.wav");
                }
            });
             mensajetxt.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/escribe el mensaje aqui.wav");
                }
            });
             agregar_producto.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/crearproducto.wav");
                }
            });
             eliminar_producto.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/desactivarproducto.wav");
                }
            });
             modificar_informacion.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/modificainfo.wav");
                }
            });
             enviar_mensaje.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/enviarmensajesavendedor.wav");
                }
            });
             notificaciones.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/opcion4_notif_.wav");
                }
            });
             reportes.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/escojediaparareporte.mp3.wav");
                }
            });
    }
     JButton boton_anteror=new JButton();
    MouseAdapter mos =new MouseAdapter(){
        @Override
      public void mouseExited(MouseEvent e) {
          if(e.getSource()instanceof JButton){
    JButton boton = (JButton) e.getSource();
   if(componente_dentro_del_panel(panel2,boton)){
       boton.setBackground(new Color(174, 214, 241));
   }else if (componente_dentro_del_panel(panel3,boton)){
       boton.setBackground(new Color(255, 137, 78));
   }
          }
          if(e.getSource()instanceof JPanel){
              JPanel panel_tocado= (JPanel) e.getSource();
              for(Component componentes: panel_paramicro_paneles.getComponents()){
                  if(componentes==panel_tocado){
                      panel_tocado.setBackground(new Color(255, 140, 124));
                  }
              }
          }
}
        @Override
       public void mouseEntered(MouseEvent e) {
           if(e.getSource()instanceof JButton){
              JButton entrada = (JButton) e.getSource();
    if(componente_dentro_del_panel(panel2,entrada)){
         entrada.setBackground(new Color(0, 128, 255));
    }else if (componente_dentro_del_panel(panel3,entrada)){
        entrada.setBackground(new Color(255, 197, 168));
    } 
           }
           if(e.getSource()instanceof JPanel){
              JPanel panel_tocado= (JPanel) e.getSource();
              for(Component componentes: panel_paramicro_paneles.getComponents()){
                  if(componentes==panel_tocado){
                      panel_tocado.setBackground(new Color(255, 100, 100));
                  }
              }
          }
}
        @Override
         public void mouseClicked(MouseEvent e){
             if( e.getSource()instanceof JButton &&audio!=e.getSource()){
             if(componente_dentro_del_panel(panel2,(JButton)e.getSource()))
             {
                 boton_anteror.setEnabled(true);
                 JButton boton =(JButton)e.getSource();
                 boton.setEnabled(false);
                 boton_anteror=boton;
             }
         }
             
           if (e.getSource() instanceof JPanel) {
               contador=0;
               for(Component componentes : panel_paramicro_paneles.getComponents()){
                    JPanel panel=(JPanel) e.getSource();
                   if (componentes==e.getSource()){
                       mensajes_de_administrador h=new mensajes_de_administrador();
                       h.setVisible(true);
                       for(Component componentes2: panel.getComponents()){
                           if(componentes2 instanceof JLabel ){
                               ((JLabel) componentes2).setText("<html>Fecha: " + fecha[contador] + "<br>Lo manda: Administrador<br>Asunto:" + asunto[contador] + "<br>Estatus[Leido]<html>");
                           }
                       }
                       return ;
                   }
                   contador++;
               }
}  
          }
    };
    public boolean componente_dentro_del_panel( JPanel panel, JButton boton){
        for(Component componentes :panel.getComponents()){
            if(componentes==boton){
               return true;
            }
        }
        return false;
    }
    Timer tiempo =new Timer(1000,new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent x){
           
           
        if(conexion.notificacio_activa(1)==true){
            notificaciones.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/notification_important_FILL0_wght400_GRAD0_opsz24.png")));
        }else{
            notificaciones.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/notifications_FILL0_wght400_GRAD0_opsz24.png")));
        }
        }
    });
    public static void main (String []args){
        try{
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        }catch(UnsupportedLookAndFeelException e){
            e.getMessage();
        }
        

        Administrador h=new Administrador();
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     static String[] mensaje;
    static String[] asunto;
    static String[] fecha;
    static boolean[] leido;
    static int[] numero_de_mensaje; 
    static int contador;
    int contador_audio=1;
   static  DefaultTableModel modelo_de_la_tabla;
   static  JTable tabla;
   JDateChooser calendario;
    @Override
    public void actionPerformed(ActionEvent x){
        if(reportes==x.getSource()){
            archivoSeleccionado=null;
 reproducir_audios("/audios/escojediaparareporte.wav");
            boton_anteror.setEnabled(true);
             boton_anteror=reportes;
             reportes.setEnabled(false);
           JDialog ventana=new JDialog(this,"Selecciona la fecha",true);
           ventana.setSize(400,100);
           ventana.setResizable(false);
           ventana.setLocationRelativeTo(null);
           JPanel panel =new JPanel();
             calendario = new JDateChooser(new Date());
            JTextFieldDateEditor editor = (JTextFieldDateEditor) calendario.getDateEditor();
            editor.setEditable(false);
            JButton seleccionar =new JButton ("Selecciona la hora");
           seleccionar.putClientProperty("JButton.buttonType", "roundRect");
            seleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
            seleccionar.setBackground(new Color(203, 199, 255));
            JButton cancelar=new JButton ("Cancelar");
            cancelar.addActionListener(e->{
                ventana.dispose();
            });
            seleccionar.addActionListener(e->{
                Date tiempo=new Date();
                
                if(tiempo.before(calendario.getDate())){
                     JOptionPane.showMessageDialog(this, "Fecha establecida aun no ha pasado\n Selecciona una fecha que ya haya pasado o la actual", "ERROR", JOptionPane.WARNING_MESSAGE);
                }else{
                ventana.dispose();
                
                trabajo_con_bd h=new trabajo_con_bd();
                h.traer_datos_para_tabla_de_ventas(calendario.getDate(), 2);
                }
            });
            cancelar.putClientProperty("JButton.buttonType", "roundRect");
           cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
           cancelar.setBackground(new Color(203, 199, 255));
            panel.add(calendario);
            panel.add(seleccionar);
            panel.add(cancelar);
            panel.setBackground(new Color(227, 255, 199));
            seleccionar.addActionListener(e->{
                panel3.removeAll();
                JButton descargar_pdf=new JButton("Descargar PDF", new ImageIcon(this.getClass().getResource("/imagenes/picture_as_pdf_FILL0_wght400_GRAD0_opsz24.png")));
                JButton hacer_otra_busuqeda=new JButton("Realizar otra busqueda");
                hacer_otra_busuqeda.addActionListener(e2->{
                    reportes.setEnabled(true);
                    reportes.doClick();
                    reportes.setEnabled(false);
                }
                );
               descargar_pdf.addActionListener(e3->{
                   JPopupMenu menu=new JPopupMenu();
                   JMenuItem opcion1=new JMenuItem("Descargar pdf del dia consultado");
                   JMenuItem opcion2=new JMenuItem("Descargar pdf del dia A al dia B");
                   menu.add(opcion1);
                   menu.add(opcion2);
                   menu.show(descargar_pdf, 100, 0);
                   opcion1.addActionListener(e4->{
                       seleccionar_ruta_pdf(1, null, null);
                   });
                   opcion2.addActionListener(e5->{
                       JDialog ventana_dia_a_dia=new JDialog(this,"Escoje los dias", false);
                       
                       ventana_dia_a_dia.setSize(300,100);
                       ventana_dia_a_dia.setLocationRelativeTo(null);
                       JDateChooser calendario1=new JDateChooser(new Date());
                       JTextFieldDateEditor editor2 = (JTextFieldDateEditor) calendario1.getDateEditor();
            editor2.setEditable(false);
                        JDateChooser calendario2=new JDateChooser(new Date());
                       JButton seleccionar_dias= new JButton("Seleccionar");
                       JPanel panel_dia_dia =new JPanel();
                       panel_dia_dia.setBackground(new Color(205, 255, 148));
                       panel_dia_dia.add(new JLabel("Del"));
                       panel_dia_dia.add(calendario1);
                       panel_dia_dia.add(new JLabel("al"));
                       panel_dia_dia.add(calendario2);
                       panel_dia_dia.add(seleccionar_dias);
                       ventana_dia_a_dia.getContentPane().add(panel_dia_dia);
                       ventana_dia_a_dia.setVisible(true);
                      seleccionar_dias.addActionListener(e6->{
                          if(calendario1.getDate().after(new Date())||calendario2.getDate().after(new Date())){
                              JOptionPane.showMessageDialog(this, "El o los dias seleccionados no han pasado\n porfavor de correguirlo.", "ERROR",JOptionPane.OK_OPTION);
                          }else if(calendario1.getDate().after(calendario2.getDate())){
                               JOptionPane.showMessageDialog(this, "Las fechas seleccionada debe que ser\n Calendario1 < Calendario2", "ERROR",JOptionPane.OK_OPTION);
                          }else{
                          ventana_dia_a_dia.dispose();
                          this.seleccionar_ruta_pdf(2,calendario1.getDate(), calendario2.getDate() );
                          }
                      });
                   });
               });
                String[] columnas=new String[]{
                 "Producto", "codigo de barras", "Precio c/u", "Cantidad vendidas", "sub total" 
            };
                 modelo_de_la_tabla=new DefaultTableModel(columnas,0);
                tabla=new JTable(modelo_de_la_tabla){
                   @Override
                   public boolean isCellEditable(int columnas, int filas){
                       return false;
                   }
                   
               };
               tabla.getTableHeader().setReorderingAllowed(false);
               JScrollPane scr=new JScrollPane(tabla);
               panel3.add(scr).setBounds(50,40,600,400);
               panel3.add(hacer_otra_busuqeda).setBounds(425,0,200,30);
               panel3.add(descargar_pdf).setBounds(0,450,200,30);
               panel3.repaint();    
               panel3.revalidate();
           });
           ventana.getContentPane().add(panel);
           ventana.setVisible(true);
           for(Component componentes: panel3.getComponents()){
                    if(componentes instanceof JButton){
                         componentes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                componentes.setBackground(new Color(203, 199, 255));
                 ((JButton)componentes).putClientProperty("JButton.buttonType","roundRect");
                 ((JButton) componentes).addActionListener(this);
                    }
                }
        }
        if(agregar_producto==x.getSource()){
            archivoSeleccionado=null;
            System.out.print("direc");
            panel3.removeAll();
            JLabel coditext=new JLabel("Escribe el codigo de barras o nombre del pan");
            coditext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reproducir_audios("/audios/codigoecribe.wav");
                }
            });
            JButton Finalizar=new JButton ("Crear producto");
            JTextField codigo=new JTextField(10);
            JLabel nombretext=new JLabel("Ingresa el nombre");
            nombretext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reproducir_audios("/audios/ingresanombreproducto.wav");
                }
            });
            JTextField nombre=new JTextField(10);
            JLabel tipotext=new JLabel("Selecciona el tipo de producto que es:");
            tipotext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reproducir_audios("/audios/tipodeproducto.wav");
                }
            });
            Choice tipos=new Choice();
            tipos.setVisible(true);
            tipos.add("Repostería");
            tipos.add("biscocho");
            tipos.add("hojaldre");
            tipos.add("feite");
            tipos.add("dona");
            tipos.add("batidos"); tipos.add("Otros");
            tipos.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JLabel preciotext=new JLabel("Ingresa el precio ");
              preciotext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reproducir_audios("/audios/precioproducto.wav");
                }
            });
            JTextField precio=new JTextField(10);  
              JLabel imagentext=new JLabel("Selecciona la imagen");
              
                      imagentext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    reproducir_audios("/audios/buscarimag.wav");
                }
            });
              JButton imagen =new JButton("Haz click para buscar la imagen");
              panel3.add(coditext).setBounds(0,0,200,30);
              panel3.add(codigo).setBounds(0,40,690,30);
              panel3.add(nombretext).setBounds(0,80,200,30);
              panel3.add(nombre).setBounds(0,120,690,30);
              panel3.add(tipotext).setBounds(0,150,300,30);
              panel3.add(tipos).setBounds(0,210,300,30);
              panel3.add(preciotext).setBounds(0,250,200,30);
              panel3.add(precio).setBounds(0,290,690,30);
              panel3.add(imagentext).setBounds(0,340,200,30);
              panel3.add(imagen).setBounds(0,380,300,30);
              panel3.add(Finalizar).setBounds(525,385,150,25);
              for(Component componentes : panel3.getComponents()){
                  if(componentes instanceof JLabel){
                      componentes.setFont(new Font("Lucida Fax",Font.ITALIC,13));
                  }
                  if(componentes instanceof JButton){
                      componentes.setCursor(new Cursor(Cursor.HAND_CURSOR));
              componentes.setBackground(new Color(249, 91, 122));
              ((JButton)componentes).putClientProperty("JButton.buttonType", "roundRect");
                  }
              }
              Finalizar.setBackground(new Color(255, 178, 100));
              Finalizar.addMouseListener(new MouseAdapter(){
                 @Override
                 public void mouseEntered(MouseEvent e){
                    ((JButton)e.getSource()).setBackground(new Color(255, 124, 28));
                 }
                 @Override
                 public void mouseExited(MouseEvent e){
                     ((JButton)e.getSource()).setBackground(new Color(255, 178, 100));
                 }
              });
            Finalizar.addActionListener(e->{
                if(codigo.getText().isEmpty()||nombre.getText().isEmpty()||precio.getText().isEmpty()||archivoSeleccionado==null){
                    JOptionPane.showMessageDialog(this, "No haz llenado todos los campos \nPor favor de llenarlos ", "ERROR",JOptionPane.INFORMATION_MESSAGE);
                }else{
                   if( conexion.crear_producto(codigo.getText(), nombre.getText(), tipos.getSelectedItem(), precio.getText())==1){
                       agregar_producto.setEnabled(true);
                       agregar_producto.doClick();
                       agregar_producto.setEnabled(false);
                   }
                }
            });
              imagen.addActionListener(e->{
                  PlatformImpl.startup(()->{
            
              abrirFileChooser();
        });
              });
              imagen.addMouseListener(new MouseAdapter(){
                  @Override
                  public void mouseEntered(MouseEvent e){
                      ((JButton )e.getSource()).setBackground(new Color(255, 26, 71));
                  }
                  @Override
                  public void mouseExited(MouseEvent e){
                      ((JButton)e.getSource()).setBackground(new Color(249, 91, 122));
                  }
              }
              );
              panel3.revalidate();
              panel3.repaint();
              codigo.requestFocus();
        }
        if(eliminar_producto==x.getSource()){
            
 reproducir_audios("/audios/desactivarproducto2.wav");
            JTextField codigo=new JTextField(18);
            JLabel coditext=new JLabel("Escribe el codigo de barras o el nombre del pan ");
            coditext.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/codigoecribe.wav");
                }
            });
            coditext.setFont(new Font("Times New Roman",Font.PLAIN,14));
            JButton desabilitar =new JButton("Desabilitar producto");
            JLabel error=new JLabel("No haz puesto el codigo de barras");
            error.setFont(new Font("Arial", Font.BOLD, 14));
            error.setOpaque(true);
            error.setBackground(Color.red);
            error.setForeground(Color.white);
            error.setVisible(false);
            JLabel image_error=new JLabel(new ImageIcon(this.getClass().getResource("/imagenes/Pasted-20240110-165916_preview_rev_1.png")));
            image_error.setVisible(false);
            JDialog seleccionar_prodcuto=new JDialog(this,"Escribe el codigo de barras",true);
             JButton cancelar=new JButton("Cerrar");
             cancelar.setBackground(new Color(94, 107, 152));
             desabilitar.setBackground(new Color(94, 107, 152));
            cancelar.addActionListener(e->{
            seleccionar_prodcuto.dispose();
            }
            );
            
            
            
            desabilitar.addActionListener(e->{
                if(codigo.getText().isEmpty()){
                    error.setVisible(true);
                     reproducir_audios("/audios/error.wav");
                    image_error.setVisible(true);
                    codigo.requestFocus();
                }else{
            conexion.desabilitar_producto(codigo.getText());
            seleccionar_prodcuto.dispose();
            error.setVisible(false);
            image_error.setVisible(false);
                }
            });
            
            seleccionar_prodcuto.setSize(400,150);
            seleccionar_prodcuto.setLocationRelativeTo(null);
            seleccionar_prodcuto.setResizable(false);
            JPanel panel =new JPanel();
           panel.add(coditext);
            panel.add(codigo);
            panel.add(image_error);
            panel.add(error);
            panel.add(desabilitar);
            panel.add(cancelar);
            for(Component componentes: panel.getComponents()){
                if(componentes instanceof JButton ){
                    
                     componentes.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                    ((JButton)e.getSource()).setBackground(new Color(200, 211, 255));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    ((JButton)e.getSource()).setBackground(new Color(140, 157, 218));
                }
            });
               ((JButton) componentes).putClientProperty("JButton.buttonType", "RoundRect");
                componentes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
               
            }
            panel.setBackground(new Color(255, 126, 100));
            seleccionar_prodcuto.getContentPane().add(panel);
            seleccionar_prodcuto.setVisible(true);
        }
        if(x.getSource()==modificar_informacion){
            archivoSeleccionado=null;
            panel3.removeAll();
             JLabel info=new JLabel("Escribe el codigo de barras");
            JTextField codigo =new JTextField(10);
             codigo.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/ escribircodigoorpan.wav");
                }
            });
            JButton buscar =new JButton("Buscar producto");
            buscar.setBackground(new Color(165, 209, 236));
            JButton rainiciar_busqueda=new JButton("Reinicia la busqueda");
            JLabel label_nuevo=new JLabel("Nueva informacion");
            JLabel label_viejo=new JLabel("Vieja informacion");
            JLabel label_nombre_producto=new JLabel("Nombre del producto: ");
            label_nombre_producto.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/nombredelproducto.wav");
                }
            });
            JLabel  label_nombre_precio=new JLabel("precio: ");
            JLabel  label_imagen=new JLabel("Foto");
            JLabel label_nombre_producto2=new JLabel("Nombre del producto: ");
            JLabel  label_nombre_precio2=new JLabel("precio: ");
            label_nombre_precio2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/precioproducto.wav");
                }
            });
            JLabel  label_imagen2=new JLabel("Foto");
            label_imagen2.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
 reproducir_audios("/audios/buscarimag.wav");
                }
            });
            JLabel  label_imagen3=new JLabel();
            JTextField text_nombre_producto=new JTextField(10);
            JTextField precio_text=new JTextField(10);
           JButton foto_boton=new JButton("Buscar foto");
           JButton cambiar_informacion=new JButton("Cambiar informacion");
            panel3.add(label_nuevo).setBounds(0,90,200,30);
            panel3.add(label_viejo).setBounds(550,90,200,30);
            panel3.add(label_nombre_producto).setBounds(0,130,200,30);
            panel3.add(text_nombre_producto).setBounds(0,170,250,30);
            panel3.add(label_nombre_precio).setBounds(0,200,200,30);
            panel3.add(precio_text).setBounds(0,240,250,30);
            panel3.add(label_imagen).setBounds(0,270,200,30);
            panel3.add(foto_boton).setBounds(0,310,250,30);
            panel3.add(label_nombre_producto2).setBounds(450,130,200,30);
            panel3.add(label_nombre_precio2).setBounds(450,200,200,30);
            panel3.add(label_imagen2).setBounds(450,270,200,30);
            panel3.add(label_imagen3).setBounds(450,300,225,180);
            panel3.add(cambiar_informacion).setBounds(0,450,150,30);
            buscar.putClientProperty("JButton.buttonType", "roundRect");
            foto_boton.putClientProperty("JButton.buttonType", "roundRect");
            foto_boton.addActionListener(e -> {
    Platform.runLater(() -> abrirFileChooser());
});
            cambiar_informacion.addActionListener(e->{
                    String verificacion=null;
                    if(precio_text.getText().isEmpty() ){
                        verificacion="mal";
                    }
                   
                   if(precio_text.getText().equals("")||precio_text.getText().isEmpty()||archivoSeleccionado==null){
                        conexion.cambiar_informacion(codigo.getText(),text_nombre_producto.getText() ,archivoSeleccionado,"no se escribio" );
                    
                
                   }else{
                   conexion.cambiar_informacion(codigo.getText(),text_nombre_producto.getText() ,archivoSeleccionado.getAbsoluteFile(),precio_text.getText() );
                 
                   }
                   
                 
                
                
            }
            );
            buscar.addActionListener(e->{
                int opcion=conexion.buscar_producto(codigo.getText());
            if(opcion==1){
                agregar_producto.doClick();
                
               modificar_informacion.setEnabled(true);
               
               agregar_producto.setEnabled(false);
            }else if(opcion==2){
                JOptionPane.showMessageDialog(this,"Lo sentimos por ahora la busqueda fue rechazada\n intentalo despues", "ERROR", JOptionPane.OK_OPTION);
            
        }else if(opcion ==-1){
                
                for(Component componentes: panel3.getComponents()){
                   componentes.setEnabled(true);
               }
                buscar.setEnabled(false);
               label_nombre_producto2.setText("Nombre del producto: "+ trabajo_con_bd.enviar_informacion[0]);
               label_nombre_precio2.setText("precio: "+ trabajo_con_bd.enviar_informacion[1]);
               BufferedImage imagen = trabajo_con_bd.imagen; 
              
    label_imagen3.setIcon( new ImageIcon(imagen.getScaledInstance(225,180 , Image.SCALE_DEFAULT)));
            }
           
            });
            
            panel3.add(info).setBounds(0,0,200,20);
            panel3.add(buscar).setBounds(0,60,150,30);
            panel3.add(codigo).setBounds(0,30,getWidth()-115,30);
            panel3.add(rainiciar_busqueda).setBounds(200,450,150,30);
             for(Component componentes: panel3.getComponents()){
                if(componentes instanceof JButton ){
                    ((JButton) componentes).putClientProperty("JButton.buttonType", "roundRect");
                    componentes.setBackground(new Color(255, 137, 78));
                    componentes.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                if(componentes instanceof JLabel ){
                    componentes.setFont(new Font("Times New Roman", Font.BOLD,12));
                }
                if(componentes instanceof JButton  || componentes instanceof JTextField){
                    componentes.setEnabled(false);
                    
                }
                codigo.setEnabled(true);
                    buscar.setEnabled(true);
                componentes.addMouseListener(mos);
            }
             rainiciar_busqueda.addActionListener(e->{
                 modificar_informacion.setEnabled(true);
                 modificar_informacion.doClick();
                 modificar_informacion.setEnabled(false);
             });
            panel3.repaint();
            panel3.revalidate();
            codigo.requestFocus();
        }
        
            
        if(x.getSource()==enviar_mail){
           
            if(mensajeria.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Rellena todos los campos", "ERROR", JOptionPane.WARNING_MESSAGE);
           mensajeria.requestFocus();
            }else if (asuntores.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Rellena todos los campos", "ERROR", JOptionPane.WARNING_MESSAGE);
            asuntores.requestFocus();
            }else{
             if(conexion.mesnaje_sopote(mensajeria.getText(), asuntores.getText(), "Administrador")==false){
                JOptionPane.showMessageDialog(this, "Lo sentimos no se pudo enviar el mensaje, intentalo mas tarde", "ERROR", JOptionPane.INFORMATION_MESSAGE);
             } 
              asuntores.setText("");
              mensajeria.setText("");
            }
            
        }
        if (notificaciones == x.getSource()) {
            archivoSeleccionado=null;
    for (Component componentes : panel3.getComponents()) {
        panel3.remove(componentes);
    }

   
    conexion.traer_mensajes2(3);

    panel3.add(filtrar).setBounds(0, 0, 100, 30);
    filtrar.setBackground(new Color(255, 194, 128));
filtrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    int inclemento = 0;
    for (int i = 0; i < mensaje.length; i++) {
        String leido_o_no;
        if (leido[i]) {
            leido_o_no = "Leido";
        } else {
            leido_o_no = "No visto";
        }
        JLabel informacion = new JLabel("<html>Fecha: " + fecha[i] + "<br>Lo manda: Administrador<br>Asunto:" + asunto[i] + "<br>Estatus[" + leido_o_no + "]<html>");
        JPanel paneles_pequeños = new JPanel();
        paneles_pequeños.setBackground(new Color(255, 140, 124));
        paneles_pequeños.setCursor(new Cursor(Cursor.HAND_CURSOR));
        paneles_pequeños.setLayout(null);
        paneles_pequeños.addMouseListener(mos);
        paneles_pequeños.add(informacion).setBounds(0, 0, 500, 60);
        panel_paramicro_paneles.add(paneles_pequeños).setBounds(0, inclemento, 685, 60);
        inclemento += 100;
    }
panel_paramicro_paneles.setLayout(null);
    panel_paramicro_paneles.setPreferredSize(new Dimension(685, inclemento));
     scr_panelmicro = new JScrollPane(panel_paramicro_paneles);
     panel_paramicro_paneles.setBackground(new Color(255, 252, 147));
    panel3.add(scr_panelmicro).setBounds(0, 50,685, 450);
    filtrar.addActionListener(this); 
    menu_filtrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    menu_filtrar.add(opcion_todos);
    opcion_todos.addActionListener(this);
    menu_filtrar.add(opcion_leidos);
    opcion_leidos.addActionListener(this);
      menu_filtrar.add(opcion_no_leidos);
      opcion_no_leidos.addActionListener(this);
    panel3.repaint();
    panel3.revalidate();
}
        if(filtrar==x.getSource()){
            menu_filtrar.show(filtrar, 100,0);
        }
        if(opcion_leidos==x.getSource()){
            mensajes_opciones(4);
        }
        if(opcion_todos==x.getSource()){
           mensajes_opciones(5); 
        }
        if(opcion_no_leidos==x.getSource()){
            mensajes_opciones(3);
        }
        if(salir==x.getSource()){
            menu_desplegable.show(salir,50, 0);
        }
        if(salir_del_programa==x.getSource()){
            System.exit(0);
        }
        if(cerrar_sesion==x.getSource()){
            System.exit(0);
        }
        if(enviar_mensaje==x.getSource()){
            archivoSeleccionado=null;
             for (Component componentes : panel3.getComponents()) {
        panel3.remove(componentes);
    }
            panel3.add(scl).setBounds(0,100,getWidth()-115, 350);
        panel3.add(enviar_mail).setBounds(0,450, 100,20);
        panel3.add(asuntotxt).setBounds(0,0,150,20);
        panel3.add(asuntores).setBounds(0,30,getWidth()-115,30);
        panel3.add(mensajetxt).setBounds(0,60,150,30);
        asuntotxt.requestFocus();
        panel3.repaint();
        panel3.revalidate();
        }
        if(audio==x.getSource()){
            Entrada h=new Entrada();
            if(contador_audio==1){
                currentSongIndex=1;
                audio.setToolTipText("Desactiva la funcion audio");
            contador_audio=0;
            h.notificacion("Ativacion de audio", "Se a activado exitosamente la funcion 'Audio'");
            audio.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/hearing_disabled_FILL0_wght400_GRAD0_opsz24.png")));
            this.reproducir_audios("/audios/audioactivado.wav");
            }else{
                this.reproducir_audios("/audios/audiodesactiado.wav");
                currentSongIndex=0;
                contador_audio=1;
                 audio.setToolTipText("Activa la funcion audio");
                 audio.setIcon(new ImageIcon(this.getClass().getResource("/imagenes/hearing_FILL0_wght400_GRAD0_opsz24.png")));
                 h.notificacion("Desactivacion de audio", "Se a Deactivado exitosamente la funcion 'Audio'");
            }
            
        }
    }
   static  File archivoSeleccionado;
  private void abrirFileChooser() {
        Platform.runLater(() -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Archivos de Imagen", "*.jpg", "*.jpeg", "*.png", "*.gif")
            );

             archivoSeleccionado = fileChooser.showOpenDialog(null);

            if (archivoSeleccionado != null) {
                System.out.println("Ubicación de la imagen seleccionada: " + archivoSeleccionado.getAbsolutePath());
                
            } else {
                System.out.println("No se seleccionó ninguna imagen.");
            }
        });
  }
    public void mensajes_opciones(int opcion) {
            panel_paramicro_paneles.removeAll();

           
            conexion.traer_mensajes2(opcion);
            int incremento = 0;
            for (int i = 0; i < mensaje.length; i++) {
                String leido_o_no;
                if (leido[i]) {
                    leido_o_no = "Leido";
                } else {
                    leido_o_no = "No visto";
                }
                JLabel informacion = new JLabel("<html>Fecha: " + fecha[i] + "<br>Lo manda: Administrador<br>Asunto:" + asunto[i] + "<br>Estatus[" + leido_o_no + "]<html>");
                JPanel paneles_pequeños = new JPanel();
                paneles_pequeños.setBackground(new Color(255, 140, 124));
                paneles_pequeños.setCursor(new Cursor(Cursor.HAND_CURSOR));
                paneles_pequeños.setLayout(null);
                paneles_pequeños.addMouseListener(mos);
                paneles_pequeños.add(informacion).setBounds(0, 0, 685, 60);
                panel_paramicro_paneles.add(paneles_pequeños).setBounds(0, incremento, 685, 60);
                incremento += 100;
            }
            panel_paramicro_paneles.setPreferredSize(new Dimension(685, incremento));
            panel3.repaint();
            panel_paramicro_paneles.revalidate();
        }
    public class mensajes_de_administrador extends JFrame {

        JLabel mensaje2= new JLabel("<html>Fecha: " + fecha[contador] + "<br>Lo manda: Operador<br>Asunto: " + asunto[contador] + "<br> Mensaje: " + mensaje[contador] + "<html>");

        public mensajes_de_administrador() {
            this.setSize(300, 300);
            this.setLocationRelativeTo(null);
            this.setTitle("Ver mensaje");
            Container contenedor = getContentPane();
            JPanel panel = new JPanel();
            contenedor.add(panel);
            mensaje2.setFont(new Font("Lucida Fax", Font.PLAIN, 12));
            JScrollPane scr = new JScrollPane(panel);
            contenedor.add(scr);
            panel.setLayout(null);
            panel.setBackground(new Color(174, 214, 241));
            panel.setPreferredSize(new Dimension(getWidth(), getHeight()));
            panel.add(mensaje2).setBounds(0, 0, getWidth(), getHeight());
          
            conexion.cambiar_a_leido(numero_de_mensaje[contador]);
            tiempo.start();
        }
        
    }
    String ruta_para_guardar;
    public void seleccionar_ruta_pdf(int opcion, Date dia1, Date dia2){
        Platform.runLater(()->{
        DirectoryChooser ventana_explorador=new DirectoryChooser();
        ventana_explorador.setTitle("Selecciona donde guardar el PDF");
        Stage ventana=new Stage();
        ruta_para_guardar=ventana_explorador.showDialog(ventana).getAbsolutePath();
        if(ruta_para_guardar!=null){
            if(opcion==1){
               guardarPDF(); 
            }else{
                guardarPDF2(dia1, dia2); 
            }
            
        }
    });
    }
    public void guardarPDF() {
        try {
            
            Random ram = new Random();
            boolean archivoRepetido;
            Date date =new Date();
            SimpleDateFormat formato=new SimpleDateFormat("yyyy_MM_dd");
            System.out.println(formato.format(date));
            do {
            String nombreDelDocumento = ruta_para_guardar + "/Reporte_" +formato.format(date)+"_"+(ram.nextInt()) + ".pdf";
            File documentoRuta = new File(nombreDelDocumento);
            archivoRepetido = documentoRuta.exists();

            if (!archivoRepetido) {
                Document documento = new Document();
                
                PdfWriter.getInstance(documento, new FileOutputStream(nombreDelDocumento));
                
                documento.open();
                PdfPTable tablapdf=new PdfPTable(tabla.getColumnCount());
                 for (int i = 0; i < tabla.getColumnCount(); i++) {
                    PdfPCell celda =new PdfPCell(new Phrase(tabla.getColumnName(i)));
                    celda.setBackgroundColor(new BaseColor(143, 134, 255));
                tablapdf.addCell(celda);
                
            }
                 
                     for (int i = 0; i < tabla.getRowCount(); i++) {
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    PdfPCell columna=new PdfPCell(new Phrase(tabla.getValueAt(i, j).toString()));
                    columna.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna);
                }
            }
                 
                   
                com.itextpdf.text.Image Imagen= com.itextpdf.text.Image.getInstance(this.getClass().getResource("/imagenes/icono2.png"));
                Imagen.scaleToFit(100,100);
               Chunk chunk = new Chunk("Reporte de venta del dia: " + formato.format(calendario.getDate()));
               chunk.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,20,com.itextpdf.text.Font.ITALIC));
              Chunk chunk_dos=new Chunk("Las moninas ® S.A.");
              chunk_dos.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,10,com.itextpdf.text.Font.ITALIC));
               Imagen.setAlignment(Element.ALIGN_TOP);
               Paragraph he=new Paragraph(chunk);
                he.setAlignment(Element.ALIGN_CENTER);
                Paragraph he2=new Paragraph(chunk_dos);
                he2.setAlignment(Element.ALIGN_RIGHT);
                
                tablapdf.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablapdf.setWidthPercentage(75);
               documento.add(Imagen);
                documento.add(he);
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(tablapdf);
                documento.add(he2);
                documento.close();
                System.out.println("PDF creado: " + nombreDelDocumento);
            }else{
                System.out.println("se encontro el archivo");
            }
        } while (archivoRepetido);
            } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void guardarPDF2(Date dia1, Date dia2) {
        try {
            
            Random ram = new Random();
            boolean archivoRepetido;
            Date date =new Date();
            SimpleDateFormat formato=new SimpleDateFormat("yyyy_MM_dd");
            System.out.println(formato.format(date));
            do {
            String nombreDelDocumento = ruta_para_guardar + "/Reporte_" +formato.format(dia1)+"_"+formato.format(dia2)+""+(ram.nextInt()) + ".pdf";
            File documentoRuta = new File(nombreDelDocumento);
            archivoRepetido = documentoRuta.exists();

            if (!archivoRepetido) {
                Document documento = new Document();
                
                PdfWriter.getInstance(documento, new FileOutputStream(nombreDelDocumento));
                
                documento.open();
                PdfPTable tablapdf=new PdfPTable(tabla.getColumnCount()+1);
                PdfPCell fechas= new PdfPCell(new Phrase("Fechas"));
                fechas.setBackgroundColor(new BaseColor(143, 134, 255));
                tablapdf.addCell(fechas);
                 for (int i = 0; i < tabla.getColumnCount(); i++) {
                    PdfPCell celda =new PdfPCell(new Phrase(tabla.getColumnName(i)));
                    celda.setBackgroundColor(new BaseColor(143, 134, 255));
                tablapdf.addCell(celda);
                
            }
                 trabajo_con_bd h=new trabajo_con_bd();
                 float total = 0;
                h.traer_datos_del_dia1_al_2(dia1,dia2);
                     for (int i = 0; i < trabajo_con_bd.datos_a_b1.length; i++) {
                         
                                 PdfPCell columnaf=new PdfPCell(new Phrase(trabajo_con_bd.datos_a_b[i]));
                    columnaf.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columnaf);
                    PdfPCell columna=new PdfPCell(new Phrase(trabajo_con_bd.datos_a_b1[i]));
                    columna.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna);
                         PdfPCell columna2=new PdfPCell(new Phrase(trabajo_con_bd.datos_a_b2[i]));
                    columna2.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna2);
                    PdfPCell columna3=new PdfPCell(new Phrase("$"+trabajo_con_bd.datos_a_b3[i]));
                    columna3.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna3);
                    PdfPCell columna4=new PdfPCell(new Phrase(trabajo_con_bd.datos_a_b4[i]));
                    columna4.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna4);
                    PdfPCell columna5=new PdfPCell(new Phrase("$"+trabajo_con_bd.datos_a_b5[i]));
                    columna5.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna5);
                    total=total+Float.parseFloat(trabajo_con_bd.datos_a_b5[i]);
            }
                  PdfPCell columna=new PdfPCell(new Phrase("Total: "+total));
                    columna.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columna);
                   for(int i=0; i<4;i++){
                       PdfPCell columnas=new PdfPCell(new Phrase(" "));
                    columnas.setBackgroundColor(new BaseColor(255, 219, 95));
                    tablapdf.addCell(columnas);
                   }
                com.itextpdf.text.Image Imagen= com.itextpdf.text.Image.getInstance(this.getClass().getResource("/imagenes/icono2.png"));
                Imagen.scaleToFit(100,100);
               Chunk chunk = new Chunk("Reporte de venta del dia: " + formato.format(dia1)+" Al "+formato.format(dia2));
               chunk.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,16,com.itextpdf.text.Font.ITALIC));
              Chunk chunk_dos=new Chunk("Las moninas ® S.A.");
              chunk_dos.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA,10,com.itextpdf.text.Font.ITALIC));
               Imagen.setAlignment(Element.ALIGN_TOP);
               Paragraph he=new Paragraph(chunk);
                he.setAlignment(Element.ALIGN_CENTER);
                Paragraph he2=new Paragraph(chunk_dos);
                he2.setAlignment(Element.ALIGN_RIGHT);
                
                tablapdf.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablapdf.setWidthPercentage(75);
               documento.add(Imagen);
                documento.add(he);
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(tablapdf);
                documento.add(he2);
                documento.setPageSize(PageSize.A4);
                documento.newPage();
                 documento.add(Imagen);
                 Chunk chunk1 = new Chunk("Preferencia del dia: "+formato.format(dia1)+" Al "+ formato.format(dia2));
               chunk1.setFont(new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN,16,com.itextpdf.text.Font.ITALIC));
                Paragraph texto_arriba =new Paragraph(chunk1);
               texto_arriba .setAlignment(Element.ALIGN_CENTER);
                 documento.add(texto_arriba);
                 documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                documento.add(new Paragraph(" "));
                 PdfPTable tabla2=new PdfPTable(3);
                 PdfPCell uno=new PdfPCell(new Phrase("Producto"));
                 uno.setBackgroundColor(new BaseColor(143, 134, 255));
                 tabla2.addCell(uno);
                  PdfPCell dos=new PdfPCell(new Phrase("Cantidad Vendida"));
                 dos.setBackgroundColor(new BaseColor(143, 134, 255));
                 tabla2.addCell(dos);
                 PdfPCell tres=new PdfPCell(new Phrase("Porsentaje de preferencia"));
                 tres.setBackgroundColor(new BaseColor(143, 134, 255));
                 tabla2.addCell(tres);
                 /*
                 PdfPCell cuatro=new PdfPCell(new Phrase("Promedio de crecimiento"));
                 tres.setBackgroundColor(new BaseColor(143, 134, 255));
                 tabla2.addCell(tres);*/
                 h.datos_para_tabla_dos_pdf(dia1, dia2);
                 for(int i=0; i<trabajo_con_bd.codigo_tabla.length;i++){
                     PdfPCell codigo=new PdfPCell(new Phrase(trabajo_con_bd.codigo_tabla[i]));
                    codigo.setBackgroundColor(new BaseColor(255, 219, 95));
                    tabla2.addCell(codigo);
                    PdfPCell cantidad=new PdfPCell(new Phrase(trabajo_con_bd.cantidad[i]));
                    cantidad.setBackgroundColor(new BaseColor(255, 219, 95));
                    tabla2.addCell(cantidad);
                    PdfPCell promedio=new PdfPCell(new Phrase(trabajo_con_bd.promedio[i]));
                    promedio.setBackgroundColor(new BaseColor(255, 219, 95));
                    tabla2.addCell(promedio);
                 }
                 
                 documento.add(tabla2);
                 
                documento.add(he2);
                documento.close();
                System.out.println("PDF creado: " + nombreDelDocumento);
            }else{
                System.out.println("se encontro el archivo");
            }
        } while (archivoRepetido);
            } catch (FileNotFoundException | DocumentException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
     private Clip clip1;
   private int currentSongIndex=0;
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
}
