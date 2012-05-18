package ergo_guard;

import java.util.*;
import java.awt.*; 
import java.awt.event.*; 
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class System_tray{

  private SystemTray tray;
  private Image img;
  private PopupMenu popup;
  private MenuItem ex;
  private MenuItem pause;
  boolean authorized;

  public System_tray(){
    tray = SystemTray.getSystemTray();
    img = Toolkit.getDefaultToolkit().getImage("imgs/system_tray/system_tray.png");
    popup = new PopupMenu();
    ex = new MenuItem("Exit");
    pause = new MenuItem("Pause");
    authorized = false;
  }
  
  public void validate() throws FileNotFoundException{
      JPanel panel = new JPanel();
      JLabel label = new JLabel("Porfavor ingresa la contraseña: ");
      JPasswordField pass = new JPasswordField(10);
      panel.add(label);
      panel.add(pass);
      String[] options = new String[]{"OK", "Cancel"};
      int option = JOptionPane.showOptionDialog(null, panel, "Confirmar",
              JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
              null, options, options[1]);
  
      char [] password = pass.getPassword();
      
      String p = new String(password);
      
      try{
          FileInputStream fstream = new FileInputStream("password.txt");
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));  
          String strLine;
          
          while ((strLine = br.readLine()) != null)   {
              if(p.equals(strLine))
                  authorized = true;
          }
          
          in.close();
      } catch (Exception e){
          System.out.println(e);
      }
  }
  
  public void create_tray_icon() {

    ex.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
          try {
              validate();
          } catch (FileNotFoundException ex) {
              Logger.getLogger(System_tray.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          if(authorized)
              System.exit(0);
      }
    });
    
    pause.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
          try {   
              if(!Ergo_guard.active)
                  validate();
              else
                  authorized = true;
          } catch (FileNotFoundException ex) {
              Logger.getLogger(System_tray.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          if(authorized){
              if(!Ergo_guard.active){
                  pause.setLabel("Continue");
                  Ergo_guard.active = true;
                  authorized = false;
              }else{
                  pause.setLabel("Pause");
                  Ergo_guard.active = false;
                  authorized = false;
              }
          }
      }
    });

    popup.add(pause);
    popup.add(ex);
    TrayIcon trayIcon = new TrayIcon(img, "Ergo-guard", popup);
    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      System.err.println("Problem loading Tray icon");
    }
  }
}
