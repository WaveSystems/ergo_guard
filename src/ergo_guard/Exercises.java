/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Alberto
 */
public class Exercises extends JFrame{

    Image img;
    Random random = new Random();
    
    public Exercises() {
        
        // Se crea el array que indica la ruta de los backgrounds
        String zen[] = new String[] {
            "imgs/background/zen_1.jpg",
            "imgs/background/zen_2.jpg",
            "imgs/background/zen_3.jpg",
            "imgs/background/zen_4.jpg",
            "imgs/background/zen_5.jpg"};
        
        //Se decide cual background se utilizara mediante un random
        String imgBg = zen[random.nextInt(5)];
        
        img = Toolkit.getDefaultToolkit().createImage(imgBg);
        
        JPanel panel = new PicturePanel();
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,10));
        panel2.setOpaque(false);
        
        JLabel legend = new JLabel("Has pasado mucho tiempo en la computadora");
        Font f = new Font("Tahoma", Font.BOLD, 20);
        legend.setFont(f);
        if(imgBg.equals("imgs/background/zen_2.jpg"))
            legend.setForeground(Color.white);   //Set text color
        else
            legend.setForeground(Color.black    );   //Set text color
        legend.setOpaque(false);
        
        String[] bodyExer = bodyPart();
        
        Random rn = new Random();
        
        JLabel exerPic = new JLabel("",SwingConstants.CENTER);
        exerPic.setLocation(200, 200);
        
        ImageIcon imgExer = new ImageIcon(bodyExer[rn.nextInt(bodyExer.length)]);
        exerPic.setIcon(imgExer);
        
        panel2.add(legend);
        panel2.add(exerPic);
        
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(panel2);
        box.add(Box.createVerticalGlue());
        
        panel.add(box);
        
        add(panel);
        
        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
    }
    
    
    private Dimension exercisePosition(){
        //Se obtiene las dimenciones de la pantalla
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        return dim;
    }
    
    private class PicturePanel extends JPanel
    {
        // **** this method should be paintComponent, not paint
        public void paintComponent(Graphics g)
        {
            //  **** don't forget to call the super method first
            super.paintComponent(g);
            g.drawImage(img,0,0,this);
        }
    }
    
    static String[] bodyPart(){
        String work[] = {"imgs/exercises/E3-1.jpg",
            "imgs/exercises/E3-3.jpg",
            "imgs/exercises/E3-4.jpg",
            "imgs/exercises/E7-1.jpg",
            "imgs/exercises/E7-3.jpg"};
        
        String leisure[] = {"imgs/exercises/E2-1.jpg",
            "imgs/exercises/E2-2.jpg",
            "imgs/exercises/E3-2.jpg"};
        
        String entertainment[] ={"imgs/exercises/E1-1.jpg",
            "imgs/exercises/E1-2.jpg",
            "imgs/exercises/E7-3.jpg"};
        
        if(!Process_detector.workFlag)
            return work;
        
        if(!Process_detector.leisureFlag)
            return leisure;
        
        if(!Process_detector.entertainmentFlag)
            return entertainment;
        
        if(!Process_detector.workFlag && !Process_detector.leisureFlag){
            String temp1[] = {"imgs/exercises/E3-1.jpg",
                "imgs/exercises/E3-3.jpg",
                "imgs/exercises/E3-4.jpg",
                "imgs/exercises/E7-1.jpg",
                "imgs/exercises/E7-3.jpg",
                "imgs/exercises/E2-1.jpg",
                "imgs/exercises/E2-2.jpg",
                "imgs/exercises/E3-2.jpg"};
            return temp1;
        }
        
        if(!Process_detector.workFlag && !Process_detector.entertainmentFlag){
            String temp2[] = {"imgs/exercises/E3-1.jpg",
                "imgs/exercises/E3-3.jpg",
                "imgs/exercises/E3-4.jpg",
                "imgs/exercises/E7-1.jpg",
                "imgs/exercises/E7-3.jpg",
                "imgs/exercises/E1-1.jpg",
                "imgs/exercises/E1-2.jpg",
                "imgs/exercises/E7-3.jpg"};
            return temp2;
        }
        
        if(!Process_detector.leisureFlag && !Process_detector.entertainmentFlag){
            String temp3[] = {"imgs/exercises/E2-1.jpg",
                "imgs/exercises/E2-2.jpg",
                "imgs/exercises/E3-2.jpg",
                "imgs/exercises/E1-1.jpg",
                "imgs/exercises/E1-2.jpg",
                "imgs/exercises/E7-3.jpg"};
            return temp3;
        }
        
        String random[] = {"imgs/exercises/E2-1.jpg",
            "imgs/exercises/E2-2.jpg",
            "imgs/exercises/E3-2.jpg",
            "imgs/exercises/E3-4.jpg"};
        
        return random;
    }

    static void createWindow() {
        
        Exercises exercise = new Exercises();
        
        Ergo_guard.exerRun = true;
        
        if(Ergo_guard.notiRun){
            try {
                Thread.sleep (30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Se le da posicion
        Dimension position = exercise.exercisePosition();
        
        exercise.setLocation((position.width/2)-400, (position.height/2)-300);
        exercise.pack();
        exercise.setVisible(true);
        
        try {
            //Espera 5 segundos
            Thread.sleep (5000);            
        } catch (InterruptedException ex) {
            Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        exercise.setVisible (false);
        exercise.dispose();
        
        Ergo_guard.exerRun = false;
    }
}