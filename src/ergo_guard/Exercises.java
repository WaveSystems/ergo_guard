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
        JLabel legend = new JLabel("Has pasado mucho tiempo en la computadora");
        
        legend.setOpaque(false);
        
        panel.add(legend);
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

    static void createWindow() {
        
        Exercises exercise = new Exercises();
        
        //Se le da posicion
        Dimension position = exercise.exercisePosition();
        
        
        exercise.setLocation((position.width/2)-400, (position.height/2)-300);
        exercise.pack();
        exercise.setVisible(true);
                
    }
}