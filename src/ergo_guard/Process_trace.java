/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.util.*;

/**
 *
 * @author Alberto
 */
public class Process_trace {
    
    public String leisure[]={"chrome.exe","Safari.exe","iexplorer.exe","firefox.exe","Skype.exe","msnmsgr.exe"};
    public String entertainment[]={"wmplayer.exe","iTunes.exe","vlc.exe"};
    public String work[]={"WINWORD.EXE","EXCEL.EXE","MSACCESS.EXE","MSPUB.EXE","POWERPNT.EXE"};
    public List<process> processes = new ArrayList<process>();
    
    class process {
        String name;
        String type;
        long timeElapsed;
        long actualTime;
        
        process (String nam){
            Calendar cal = Calendar.getInstance();
            name = nam;
            type = "";
            timeElapsed = 0;
            actualTime = cal.getTimeInMillis()/1000;
        }
        
        void setTime(long ms){
            actualTime = ms;
        }
        
        void setElapsed (long nms) {
            long t = nms - actualTime;
            timeElapsed =+ t;
        }
        
        void setType(String t) {
            type = t;
        }
        
        String getName (){
            return name;
        }
        
        String getType (){
            return type;
        }
        
        long getElapsed (){
            return timeElapsed;
        }
    
    }
    
    public void processClassify (List<String> procs){
        
        int size, cont, listC;
        boolean exist = false;
        
        Iterator<String> proc = procs.iterator();
        
        while(proc.hasNext()){
            String process = proc.next();
            exist = false;
            
            process procc = new process(process);
            
            for(listC = 0; listC < processes.size(); listC ++){
                procc = (process) processes.get(listC);
                
                if(process.equals(procc.getName())){
                    exist = true;
                } 
            }
            
            if(exist == false){
                System.out.println("Added");
                process procTracing = new process(process);
                
                size = leisure.length;
                
                for(cont = 0; cont < size; cont ++){
                    if(process.equals(leisure[cont]))
                        procTracing.setType("leisure");
                }
                
                size = entertainment.length;
                
                for(cont = 0; cont < size; cont ++){
                    if(process.equals(entertainment[cont]))
                        procTracing.setType("entertainment");
                }
                
                size = work.length;
                
                for(cont = 0; cont < size; cont ++){
                    if(process.equals(work[cont]))
                        procTracing.setType("work");
                }
                
                System.out.println("This is the process to add: " + procTracing.getName());
                System.out.println("This is the type of the process: " + procTracing.getType());
                
                processes.add(procTracing);
            }
        }
    }
    
    public void processActive() {
        ListIterator<process> procs = processes.listIterator();
        Calendar cal = Calendar.getInstance();
        long temp;
        
        while(procs.hasNext()){
            process procc = procs.next();
            temp = cal.getTimeInMillis()/1000;
            procc.setElapsed(temp);
            procs.set(procc);
            
            System.out.println("This is the process traced: " + procc.getName());
            System.out.println("This is the time elapsed: " + procc.getElapsed());
        }
    }
    
    public void processDown(List<String> procs) {
        
        ListIterator<process> proccs = processes.listIterator();
        
        int listC;
        boolean exist = false;
 
         while(proccs.hasNext()){
            ListIterator<String> proc = procs.listIterator();
             
            process procc = proccs.next();
            
            exist = false;
            
            while(proc.hasNext()){
                
                String process = proc.next();
                
                if(!exist)
                    if(process.equals(procc.getName()))
                        exist = true;
            }
            
            if(!exist){
                System.out.println("Doesnt exist " + procc.getName());
                proccs.remove();
            }
        }
    }
}