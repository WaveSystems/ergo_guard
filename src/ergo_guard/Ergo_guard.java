/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;

public class Ergo_guard {
    
  private Ergo_guard() {;}

  public static void main(String[] args) throws InterruptedException {
      Thread pd = new Process_detector();
      Thread nt = new Notifications();
       
//      nt.start();
//      pd.start();
    
      
    
      
      Exercises.createWindow();
  }
}
