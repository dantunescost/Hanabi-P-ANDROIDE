package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Defausse{
	public FenetrePartie partie;
	public static String R = System.getProperty("user.dir");

	public Defausse(FenetrePartie p){
		this.partie = p;

		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
	}

	public void paint(Graphics g){
		int x = (partie.getPartie().getDefausse().size()>4)?7*128:partie.getPartie().getDefausse().size()*128;
		int y = 200 + (partie.getPartie().getDefausse().size()/7)*65;
		g.setColor(Color.black);
		g.fillRect(0, 0, x, y);
		for(int i=0; i<this.partie.getPartie().getDefausse().size(); i++){
			Image img = new ImageIcon(Defausse.R+this.partie.getPartie().getDefausse().get(i).getCardName()).getImage();
			g.drawImage(img, (i%7)*128, (i/7)*65, 128, 200, this.partie);
		}
	}
}
