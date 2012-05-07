/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.util.*;

public class Ergo_guard {
    
    static boolean exerRun;
    static boolean notiRun;
    static int exerTime;
    
    private Ergo_guard() {
        exerRun = false;
        notiRun = false;
        exerTime = 1800000;
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread pd = new Process_detector();
        Thread nt = new Notifications();
        System_tray ty = new System_tray();
        
        Random random = new Random();
        
        nt.start();
        pd.start();
        ty.create_tray_icon();
        
        for(;;){
            int n = 2700000 - exerTime + 1;
            int i = random.nextInt() % n;
            Thread.sleep(exerTime + i);
            Exercises.createWindow();
        }
    }
}
