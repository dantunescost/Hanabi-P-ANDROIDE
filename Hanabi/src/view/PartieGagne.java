package view;

import model.Partie;

import javax.swing.*;
import java.awt.*;

public class PartieGagne extends JFrame {
	private static final long serialVersionUID = 2778335294567849139L;
	public static String R = System.getProperty("user.dir");
    protected Partie p;
    protected int score;

    public PartieGagne(Partie p) {
        super("Gagné!");
        this.setSize(529,501);
        this.setMinimumSize(new Dimension(529,501));
        this.setResizable(false);
        this.p = p;
        this.score = p.calculerPoints();

        if(System.getProperty("os.name").equals("Mac OS X")){
            R += "/Hanabi";
        }
        R += "/ressources/";

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g){
    	g.setColor(Color.black);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        if (0<=p.calculerPoints() &&  p.calculerPoints()<=5) {
            g.drawImage(new ImageIcon(R + "0_5.png").getImage(), 129/2, 10, 400, 317, this);
        }
        else if(6<=p.calculerPoints() &&  p.calculerPoints()<=10){
            g.drawImage(new ImageIcon(R + "6_10.png").getImage(), 129/2, 10, 400, 317, this);
        }
        else if(11<=p.calculerPoints() &&  p.calculerPoints()<=15){
            g.drawImage(new ImageIcon(R + "11_15.png").getImage(), 129/2, 10, 400, 317, this);
        }
        else if(16<=p.calculerPoints() &&  p.calculerPoints()<=20){
            g.drawImage(new ImageIcon(R + "16_20.png").getImage(), 129/2, 10, 400, 317, this);
        }
        else if(21<=p.calculerPoints() &&  p.calculerPoints()<=24){
            g.drawImage(new ImageIcon(R + "21_24.png").getImage(), 129/2, 10, 400, 317, this);
        }
        else {
            g.drawImage(new ImageIcon(R + "25.png").getImage(), 129/2, 10, 400, 317, this);
        }

        g.setColor(Color.white);
        g.drawString(p.getJoueurs()[0].getNom() + ", votre score est "+Integer.toString(score), 529/2-50, 432);

        g.drawImage(new ImageIcon(R+"nouvellePartie.png").getImage(), 10, 501-60, 193, 40, this);
        g.drawImage(new ImageIcon(R+"chargerPartie.png").getImage(), 10+193+10, 501-60, 184, 40, this);
        g.drawImage(new ImageIcon(R+"quitter.png").getImage(), 10+193+10+184+10, 501-60, 112, 40, this);

    }
}
