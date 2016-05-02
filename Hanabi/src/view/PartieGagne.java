package view;

import model.Partie;

import javax.swing.*;
import java.awt.*;

public class PartieGagne extends JFrame {
    public static String R = System.getProperty("user.dir");

    public PartieGagne(Partie p) {
        super("Gagné!");
        this.setSize(529,501);
        this.setMinimumSize(new Dimension(529,501));
        this.setResizable(false);

        if(System.getProperty("os.name").equals("Mac OS X")){
            R += "/Hanabi";
        }
        R += "/ressources/";

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g, Partie p){

        g.clearRect(0,0,this.getWidth(),this.getHeight());

        if (0<=p.calculerPoints() &&  p.calculerPoints()<=5) {
            g.drawImage(new ImageIcon(R + "0_5.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }
        else if(6<=p.calculerPoints() &&  p.calculerPoints()<=10){
            g.drawImage(new ImageIcon(R + "6_10.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }
        else if(11<=p.calculerPoints() &&  p.calculerPoints()<=15){
            g.drawImage(new ImageIcon(R + "11_15.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }
        else if(16<=p.calculerPoints() &&  p.calculerPoints()<=20){
            g.drawImage(new ImageIcon(R + "16_20.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }
        else if(21<=p.calculerPoints() &&  p.calculerPoints()<=24){
            g.drawImage(new ImageIcon(R + "21_24.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }
        else {
            g.drawImage(new ImageIcon(R + "25.png").getImage(), 0, 10, this.getWidth(), this.getHeight() - 51, this);
        }


        g.drawImage(new ImageIcon(R+"nouvellePartie.png").getImage(), 10, 449+6, 193, 40, this);
        g.drawImage(new ImageIcon(R+"chargerPartie.png").getImage(), 10+193+10, 449+6, 184, 40, this);
        g.drawImage(new ImageIcon(R+"quitter.png").getImage(), 10+193+10+184+10, 449+6, 112, 40, this);

    }
}
