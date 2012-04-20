/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.util.*;

public class Ergo_guard {
    
  private Ergo_guard() {;}

  public static void main(String[] args) throws InterruptedException {
      Thread pd = new Process_detector();
      Thread nt = new Notifications();
      
      Random random = new Random();
      
      nt.start();
      pd.start();
      
      for(;;){
          int n = 10000 - 5000 + 1;
          int i = random.nextInt() % n;
          Thread.sleep(5000 + i);
          Exercises.createWindow();
      }
  }
}
