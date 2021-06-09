
package Personas;
//Liberia
import ConexionSQL.Database;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public final class Persona extends JFrame {
    //Atributos de la clase
    JPanel panelA;//Creacion del panel
    JLabel Etq_Nombre,Etq_Tipo,Etq_Edad;
    JButton BT_Agregar,BT_Eliminar,BT_Limpiar,BT_Mostrar;
    DefaultTableModel modelo;
    JTable tabla;
    JTextField jt_nombre,jt_edad;
    public JComboBox<String> combo; // Si le quitás el parametrizado va a tomar como Object
    public String[] names, Object;
    public String generacion,edad,nombre,tipo;
    public int filas;
    
    
    //Contructor de la clase
    public Persona()
    {
       setTitle("Tipos de Personas");//titulo de mi ventana
       this.setSize(350,400); //Este será el tamaño de la ventana
       //Posición iniciar de la ventana
       setIconImage(new ImageIcon(getClass().getResource("/Imagenes/PersonaIcon.png")).getImage());
       setLocationRelativeTo(null);//Centralización de ventana en la pantalla
       Componentes();//Aqui se llama al metodo de los componentes de la ventana
       setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
       setDefaultCloseOperation(EXIT_ON_CLOSE);//Aquí cumple la función de cerrar mi ventana
       setVisible(true);//Hacemos visible la venatana
       
    }
    
    //Metodo Principal
    public void Componentes()
    {
       PanelA();//Agregamos al constructor de componentes; el constructor del panelA
       crearCampos();//Agregamos al constructor de componentes; el constructor de las etiquetas
       Botones();//se agregó el constructor de los botones
       crearTabla();//metodo que crea la tabla
       Mult_boton();//se agregó el construstor de opciones de boton
       Enter_Datos();//constructor para aceptar datos por teclado
    }
    //Metodo del panelA de la ventana
    public void PanelA()
    {
        panelA = new JPanel();//instanciamiento del panel
        //panelA.setBackground(Color.GRAY);//Color del panel
        panelA.setLayout(null);//Posicion por defecto de la etiqueta desactivada
        this.getContentPane().add(panelA);  //Agregacion del panel a la ventana  
    }
   
     //Metodo de etiquetas o campos
    public void crearCampos()
    {
        //Agregamos un borde interno a nuestro panelA
        panelA.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),"// Datos //",
         TitledBorder.LEFT,TitledBorder.DEFAULT_POSITION));
       
        //---------Etq_Nombre
        Etq_Nombre = new JLabel("-Nombre(s) de la Persona:");
        Etq_Nombre.setBounds(15,25,150,40);
        Etq_Nombre.setForeground(Color.black);//Color de las letras
        Etq_Nombre.setFont(new Font("calibri light",Font.ITALIC,14));//Tipo de letra
        panelA.add(Etq_Nombre);
       
        
        //-------Tipo de individuo
        Etq_Tipo = new JLabel("-Tipo de persona:");
        Etq_Tipo.setBounds(15,80,170,20);
        Etq_Tipo.setForeground(Color.black);
        Etq_Tipo.setFont(new Font("calibri light",Font.ITALIC,14));//Tipo de letra
        panelA.add(Etq_Tipo);
        
         //-------Apellidos
        Etq_Edad = new JLabel("-Edad:");
        Etq_Edad.setBounds(15,90,250,110);
        Etq_Edad.setForeground(Color.black);
        Etq_Edad.setFont(new Font("calibri light",Font.ITALIC,14));//Tipo de letra
        panelA.add(Etq_Edad);

    
    }
    //Metodo crear tbla de datos
    public void crearTabla()
    {
       //Tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Persona");
        modelo.addColumn("Edad");
        tabla = new JTable(modelo);
        tabla.setForeground(Color.blue);//color de las letras de las filas
        JScrollPane JS_sp = new JScrollPane(tabla);
        //tabla.setEnabled(false);
        JS_sp.setBounds(10, 190, 310, 120);//Posición de la tabla
         panelA.add(JS_sp);
    }
    
    //Metodo para agregar datos del usuario
    public void Enter_Datos()
    {
       //-----recibe el nombre
       jt_nombre = new JTextField();// acepta cadena de textos
       jt_nombre.setBounds(20,50,200,20);//posicion
       panelA.add(jt_nombre);
       
      
       //-----recibe la edad
       jt_edad = new JTextField();// acepta cadena de textos
       jt_edad.setBounds(20,150,50,20);//posicion
       panelA.add(jt_edad);
       
       
    }
   
    //Metodo botones
    public void Botones()
    {
        //------Boton Entrar
        BT_Agregar = new JButton("Add");//instanciamiento del boton
        BT_Agregar.setBounds(8, 320, 97, 30);//Posicion del boton
        //Agregamos una foto al boton
        ImageIcon salvar = new ImageIcon("src/Imagenes/agregar.png");//imagen creada
        BT_Agregar.setIcon(new ImageIcon(salvar.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH)));
        panelA.add(BT_Agregar);//agregamos el boton la panel
        
        //-----Acción o Evento del botón agregar
       ActionListener oyente = (ActionEvent e) -> 
       {
           
           modelo.addRow(new Object[filas]);
           nombre = jt_nombre.getText();
           //Variable que recibirá el valor generado por el comboBox
           generacion = (String) combo.getItemAt(combo.getSelectedIndex());
           
           edad = jt_edad.getText();
           for(int x=0; x < this.tabla.getColumnCount()-1; x++)
           {
               modelo.setValueAt(this.jt_nombre.getText(),filas,0);
               modelo.setValueAt(this.generacion,filas,1);
               modelo.setValueAt(this.jt_edad.getText(),filas,2);
               
           }
           filas++;//según se vaya agregando elementos se va subando una fila más
           //----
             /*try{
          
          String Nombre ="";
          String Persona = "";
          String Edad = "";
 
          Class.forName("oracle.jdbc.OracleDriver");
               Connection Conexión = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE");
 
 
          PreparedStatement enrollItmt;
          enrollItmt = Conexión.prepareStatement("INSERT INTO Persona(Nombre, Persona,Edad) VALUES ("+ Nombre+Persona+Edad+ ")");
          enrollItmt.execute();
 
 
        }
        catch(ClassNotFoundException | SQLException ex)
        {
 
        }*/
    
           //---
           //Mensaje de agregación de una persona
           JOptionPane.showMessageDialog(null, "¡SE AGREGÓ UNA PERSONA!"+"\n"+"- Su nombre es: "+nombre+""
                   + "\n"+"- Es un: "+generacion+"\n"+"- Edad: "+edad+" años");
           
        };//fin del evento
       
        BT_Agregar.addActionListener(oyente);//Boton que insertará
        
        //------Boton BT_Eliminar
        BT_Eliminar= new JButton("Delete");//instanciamiento del boton
        BT_Eliminar.setBounds(225, 320, 100, 30);//Posicion del boton
        //Agregamos una foto al boton
        ImageIcon delete = new ImageIcon("src/Imagenes/eliminar.png");//imagen creada
        BT_Eliminar.setIcon(new ImageIcon(delete.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        panelA.add(BT_Eliminar);//agregamos el boton la panel  
        
          //-----Acción o Evento del botón eliminar
       ActionListener oyente2 = (ActionEvent e) -> 
       {
           
            modelo.removeRow(this.tabla.getSelectedRow());
            filas--;//Según se seleccione una fila se va ir elimiando
           
           //Mensaje de agregación de una persona
           JOptionPane.showMessageDialog(null, "¡SE ELIMINÓ UNA PERSONA!"+"\n"+"- Su nombre es: "+nombre+""
                   + "\n"+"- Es un: "+generacion+"\n"+"- Edad: "+edad+" años");
           
        };//fin del evento
        BT_Eliminar.addActionListener(oyente2);//Boton que eliminará
        
        //------Boton Limpiar
        BT_Limpiar= new JButton("Clean up");//instanciamiento del boton
        BT_Limpiar.setBounds(111, 320, 110, 30);//Posicion del boton
        //Agregamos una foto al boton
        ImageIcon limpiar = new ImageIcon("src/Imagenes/limpiar.png");//imagen creada
        BT_Limpiar.setIcon(new ImageIcon(limpiar.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        panelA.add(BT_Limpiar);//agregamos el boton la panel 
        //Acción del botón limpiar
        ActionListener oyente3 = (ActionEvent e) -> 
       {
           limpiar();//llamamos al método limpiar....
           //Mensaje de limpieza
           JOptionPane.showMessageDialog(null, "¡SE HAN LIMPIADO LOS CAMPOS!");
           
        };//fin del evento
        BT_Limpiar.addActionListener(oyente3);//Boton que limpiará
        
        //------Boton Mostrar datos en Base de datos
        BT_Mostrar= new JButton("View Database");//instanciamiento del boton
        BT_Mostrar.setBounds(180, 15, 145, 20);//Posicion del boton
        //Agregamos una foto al boton
        ImageIcon BD = new ImageIcon("src/Imagenes/mostrarDB.jpg");//imagen creada
        BT_Mostrar.setIcon(new ImageIcon(BD.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH)));
        panelA.add(BT_Mostrar);//agregamos el boton la panel 
        ActionListener oyente4 = (ActionEvent a)-> 
        {
           Database ver = new Database();//objeto de la clase base de datos 
           ver.setVisible(true);//hacemos visible la ventana
            dispose();//Al presionar el boton mostrar database se cerrará la ventana principal
        
        };
        BT_Mostrar.addActionListener(oyente4);//Botón que mostrará la ventana de BD
        
    }
    //Metodo limpiar 
    public void limpiar()
    {
       jt_nombre.setText("");//reiniciar el nombre
       jt_edad.setText("");//....
       combo.setSelectedIndex(0);//reiniciar el combobox a su indice 0
        
    }
    //Metodo de boton con opciones multiples
    public void Mult_boton()
    {
        names = new String[]{"Seleccione","Adulto","Joven","Niño","Niña"};//Arreglo
        combo = new JComboBox(names);//Boton instanciado
        combo.setBounds(20, 100, 150, 20);//Localizacion y tamaño
        combo.setBackground(Color.cyan);//Color del botón
        combo.setForeground(Color.black);//Color letras
        panelA.add(combo);//Agregamos al panel el boton de opciones
        
    }
}
    


