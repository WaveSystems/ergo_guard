package ergo_guard;

import java.util.*;
import java.awt.*; 
import java.awt.event.*; 

public class System_tray{

  private SystemTray tray;
  private Image img;
  private PopupMenu popup;
  private MenuItem ex;
  private MenuItem pause;

  public System_tray(){
    tray = SystemTray.getSystemTray();
    img = Toolkit.getDefaultToolkit().getImage("imgs/system_tray/system_tray.png");
    popup = new PopupMenu();
    ex = new MenuItem("Exit");
    pause = new MenuItem("Pause");
  }

  public void create_tray_icon() {

    ex.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
        System.exit(0);
      }
    });
    
    pause.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
          if(!Ergo_guard.active){
              pause.setLabel("Continue");
              Ergo_guard.active = true;
          }else{
              pause.setLabel("Pause");
              Ergo_guard.active = false;
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
