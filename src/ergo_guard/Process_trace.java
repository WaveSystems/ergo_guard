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
    
    int countl = 0;
    long timel[] = {0,0};
    int counte = 0;
    long timee[] = {0,0};
    int countw = 0;
    long timew[] = {0,0};
    long tempTime = 0;
    
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
                proccs.remove();
            }
        }
    }
    
    public long[][] processesTimes(){
        
        ListIterator<process> procs = processes.listIterator();
        Calendar cal = Calendar.getInstance();
        boolean findl = false;
        boolean finde = false;
        boolean findw = false;
        int tempContl = 0;
        int tempConte = 0;
        int tempContw = 0;
        
        while(procs.hasNext()){

            process procc = procs.next();
            
            if(procc.getType().equals("leisure")){
                findl = true;
                if(timel[0] == 0){
                    timel[0]= cal.getTimeInMillis()/1000;
                }else{
                    timel[1] = (cal.getTimeInMillis()/1000) - timel[0];
                }
                tempContl ++;
                if(tempContl > countl)
                    countl = tempContl;
            }
            
            
            if(procc.getType().equals("entertainment")){
                finde = true;
                if(timee[0] == 0){
                    timee[0]= cal.getTimeInMillis()/1000;
                }else{
                    timee[1] = (cal.getTimeInMillis()/1000) - timee[0];
                }
                tempConte ++;
                if(tempConte > counte)
                    counte = tempConte;
            }
            
            
            if(procc.getType().equals("work")){
                findw = true;
                if(timew[0] == 0){
                    timew[0]= cal.getTimeInMillis()/1000;
                }else{
                    timew[1] = (cal.getTimeInMillis()/1000) - timew[0];
                }
                tempContw ++;
                if(tempContw > countw)
                    countw = tempContw;
            }
        }
        if(!findl){
            countl = 0;
            timel[0] = 0;
            timel[1] = 0;
        }
        if(!finde){
            counte = 0;
            timee[0] = 0;
            timee[1] = 0;
        }
        if(!findw){
            countw = 0;
            timew[0] = 0;
            timew[1] = 0;
        }
        
        long times[][] = {
            {countl, timel[1]},
            {counte, timee[1]},
            {countw, timew[1]}
        };
        
        return times;
    }
}