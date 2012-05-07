package ergo_guard;

import java.util.*;
import java.awt.*; 
import java.awt.event.*; 

public class System_tray{

  private SystemTray tray;
  private Image img;
  private PopupMenu popup;
  private MenuItem ex;

  public System_tray(){
    tray = SystemTray.getSystemTray();
    img = Toolkit.getDefaultToolkit().getImage("/imgs/system_tray/system_tray.png");
    popup = new PopupMenu();
    ex = new MenuItem("Exit");

    ex.addActionListener(new ActionListener() {
        System.exit(0);
    }
            
    popup.add(ex);
    TrayIcon trayIcon = new TrayIcon(img, "Tray Demo", popup);
    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      System.err.println("Problem loading Tray icon");
    }
  }

  public void main(String[] args) {
    new System_tray();
  }
}
