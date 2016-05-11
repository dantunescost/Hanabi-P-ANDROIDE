package view;

import controller.PerduListener;
import model.Partie;

import javax.swing.*;
import java.awt.*;

public class PartiePerdue extends JFrame{
	private static final long serialVersionUID = 7734525171653464234L;
    public static String R = System.getProperty("user.dir");
    protected FenetrePartie fp;

    public PartiePerdue(Partie p, FenetrePartie fp) {
        super("Perdu!");
        this.setSize(529,501);
        this.setMinimumSize(new Dimension(529,501));
        this.setResizable(false);
        this.fp = fp;

        if(System.getProperty("os.name").equals("Mac OS X")){
            R += "/Hanabi";
        }
        R += "/ressources/";

        this.addMouseListener(new PerduListener(this, fp));


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void paint(Graphics g){

        g.clearRect(0,0,this.getWidth(),this.getHeight());
        g.drawImage(new ImageIcon(R+"lose.png").getImage(), 0, 10, this.getWidth(), 317, this);

        g.drawImage(new ImageIcon(R+"perdu.png").getImage(), 0, 317+10, this.getWidth(), 60, this);

        g.drawImage(new ImageIcon(R+"nouvellePartie.png").getImage(), 10, 501-80, 193, 40, this);
        g.drawImage(new ImageIcon(R+"chargerPartie.png").getImage(), 10+193+10, 501-80, 184, 40, this);
        g.drawImage(new ImageIcon(R+"quitter.png").getImage(), 10+193+10+184+10, 501-80, 112, 40, this);

    }
}
