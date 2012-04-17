/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ergo_guard;

import com.sun.awt.AWTUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Alberto
 */
public class Notifications extends Thread {
    
    private String alertText(){
        //Esta funcion retorna una recomendacion
        String text[] = new String[] {
            "Gira un poco la cabeza sobre. " 
                + " el cuello. Nunca fuerzes el movimiento, "
                + " hazlo suave y durante 5 segundos."
                + " Si duele hazlo más lento.",
            "Al estirar tus brazos, no hagas "
                + "fuerza, hazlo suave y poco a poco.",
            "Al estirar tus piernas , flexiona "
                + "siempre las rodillas.",
                "Respira profundo por la nariz y "
                + "exhala por la boca 3 veces.",
            "Toma 5 o 10 minutos para caminar un poco."};

        Random random = new Random();

        return text[random.nextInt(5)];
    }
    
    private Dimension alertPosition(){
        //Se obtiene las dimenciones de la pantalla
        Toolkit toolkit =  Toolkit.getDefaultToolkit ();
        Dimension dim = toolkit.getScreenSize();
        
        return dim;
    }
    
    public void run() {
        
        for(;;){
        Random random = new Random();
        int n = 10000 - 5000 + 1;
        int i = random.nextInt() % n;
        try {
            Thread.sleep(5000 + i);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Notifications notify = new Notifications();
        
        //Esta variable se usara para modificar la fuente de los textos
        Font f = new Font("Tahoma", Font.BOLD, 16);
        
        //Se instancia un JFrame que se donde se despliegue la alerta
        final JFrame alert = new JFrame("Alerta");
        
        //Gives margin for the JTexTArea
        UIManager.put("TextArea.margin", new Insets(10,10,10,10));
        
        //Se genera el texto que tendra el JFrame
        JTextArea textAlert = new JTextArea(notify.alertText());
        
        Color coulor = new Color(46,46,46);
        
        //Se el dan ciertas propeidades como que WRAP, Font, Dimension
        textAlert.setBackground(coulor);   
        textAlert.setForeground(Color.white);   //Set text color
        textAlert.setWrapStyleWord(true);
        textAlert.setLineWrap(true);
        textAlert.setEditable(false);
        textAlert.setPreferredSize(new Dimension(300, 120));
        textAlert.setFont(f);
        
        //Se instancia la funcion que determina el tamano de pantalla
        Dimension position = notify.alertPosition();
        
        //Se dan propiedades al JFrame
        alert.setSize(400, 600);
        alert.setLocation(position.width-320, 180);
        alert.setUndecorated(true);     //Remove border
        
        //Se le pasa elJText al JFrame
        alert.getContentPane().add(textAlert, BorderLayout.CENTER);
        
        //Se despliega el JFrame
        alert.pack(); 
        alert.setVisible(true);
        try {
            //Elimina la alerta despues de 5 segundos
            Thread.sleep (5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notifications.class.getName()).log(Level.SEVERE, null, ex);
        }
        alert.setVisible (false);
        alert.dispose();
        }
    }
    
}