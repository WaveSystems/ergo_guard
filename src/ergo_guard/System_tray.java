package ergo_guard;

import java.util.*;
import java.awt.*; 
import java.awt.event.*; 

public class SystemTray {

  private SystemTray tray;
  private Image img;
  private PopupMenu popup;
  private MenuItem exit;

  public SystemTray(){
    tray = SystemTray.getSystemTray();
    img = Toolkit.getDefaultToolkit().getImage("/imgs/system_tray/system_tray.png");
    popup = new PopupMenu();
    exit = new MenuItem("Exit");

    exit.addActionListener(new ActionListener() {
      System.exit(0);
    }
    popup.add(exit);
    TrayIcon trayIcon = new TrayIcon(img, "Tray Demo", popup);
    try {
      tray.add(trayIcon);
    } catch (AWTException e) {
      System.err.println("Problem loading Tray icon");
    }
  }

  public void run() {
    new SystemTray();
  }
}
