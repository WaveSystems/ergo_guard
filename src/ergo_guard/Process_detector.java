/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

/**
 *
 * @author Alberto
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Process_detector extends Thread {
    
    static boolean leisureFlag;
    static boolean workFlag;
    static boolean entertainmentFlag;
    
    public Process_detector() {
        leisureFlag = false;
        workFlag = false;
        entertainmentFlag = false;
    }
    
    static String writeVbScript(){
        try{
            //Se crea el archivo vb script
            File script = new File("processes.vbs");
            if(!script.exists()){
                script.createNewFile();

                FileWriter writeScript = new java.io.FileWriter(script);
                
                //Se llena el archivo vb (estas son las istricciones en en codigo basic para acceder al TASKLIST.EXE)
                String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                        + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                        + "Set service = locator.ConnectServer()\n"
                        + "Set processes = service.ExecQuery _\n"
                        + " (\"select name from Win32_Process\")\n"
                        + "For Each process in processes\n"
                        + "wscript.echo process.Name \n"
                        + "Next\n"
                        + "Set WSHShell = Nothing\n";
                
                writeScript.write(vbs);
                writeScript.close();
            }
            
            return script.getPath();
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
    static List<String> executeScript(String script){
        try{
            List<String> processList = new ArrayList<String>();
            
            //Java ejecuta el script
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + script);
            BufferedReader input =
                    new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            
            //Java captura las lineas de informacion que proporciona el script
            String line;
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            
            input.close();
            
            return processList;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void brainFlags(long[][] times){
        System.out.println("Entered brain");
        
        long leisure[] = {times[0][0],times[0][1]};
        long entertainment[] = {times[1][0],times[1][1]};
        long work[] = {times[2][0],times[2][1]};
        
        if(leisure[0]>=1620)
            leisureFlag = true;
        if(work[0]>=1200)
            workFlag = true;
        if(entertainment[0]>=1500)
            entertainmentFlag = true;
        
    }
    
    public void run(){
        
        long times[][] = {};
        
        // TODO code application logic here
        Process_trace tracer = new Process_trace();
        String script = Process_detector.writeVbScript();
        
        for(;;){
            List<String> processes = Process_detector.executeScript(script);
            List<String> traced = new ArrayList();
            String result = "";
            
            try{
                // Open the file that is the first command line parameter
                FileInputStream fstream = new FileInputStream("config.txt");
                              
                // Get the object of DataInputStream
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                
                //Read File Line By Line
                while ((strLine = br.readLine()) != null){
                    Iterator<String> it = processes.iterator();
                    while (it.hasNext()) {
                        if(strLine.equals(it.next())) {
                            traced.add(strLine);
                        }
                    }
                }
                
                if(!traced.isEmpty()){
                    tracer.processClassify(traced);
                    tracer.processDown(traced);
                }
                
                tracer.processActive();
                times = tracer.processesTimes();
                
                Process_detector.brainFlags(times);
                
                //Close the input stream
                in.close();
            }catch (Exception e){
                //Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}