package view;

import model.Partie;

import javax.swing.*;
import java.awt.*;

public class PartiePerdue extends JFrame{

    private Partie partie;
    public static String R = System.getProperty("user.dir");

    public PartiePerdue(Partie p) {
        super("Hanabi");
        this.partie = p;
        this.setSize(409, 480);
        this.setMinimumSize(new Dimension(529,501));
        this.setResizable(false);
        this.setBackground(Color.white);

        if(System.getProperty("os.name").equals("Mac OS X")){
            R += "/Hanabi";
        }
        R += "/ressources/";

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g){

        g.clearRect(0,0,this.getWidth(),this.getHeight());
        g.drawImage(new ImageIcon(R+"lose.png").getImage(), 0, 0, this.getWidth(), this.getHeight()-51, this);

        g.drawImage(new ImageIcon(R+"nouvellePartie.png").getImage(), 10, 449+6, 193, 40, this);
        g.drawImage(new ImageIcon(R+"chargerPartie.png").getImage(), 10+193+10, 449+6, 184, 40, this);
        g.drawImage(new ImageIcon(R+"quitter.png").getImage(), 10+193+10+184+10, 449+6, 112, 40, this);

    }
}
