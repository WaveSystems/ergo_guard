/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.util.*;

public class Ergo_guard {
    
    static boolean exerRun;
    static boolean notiRun;
    
    private Ergo_guard() {
        exerRun = false;
        notiRun = false;
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread pd = new Process_detector();
        Thread nt = new Notifications();
        System_tray ty = new System_tray();
        
        Random random = new Random();
        
        nt.start();
        pd.start();
        ty.start();
        
        for(;;){
            int n = 20000 - 8000 + 1;
            int i = random.nextInt() % n;
            Thread.sleep(8000 + i);
            Exercises.createWindow();
        }
    }
}
