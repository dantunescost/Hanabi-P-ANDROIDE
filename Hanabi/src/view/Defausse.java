package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Partie;

public class Defausse extends JFrame {
	private static final long serialVersionUID = 5536245608560809206L;
	public Partie partie;
	public static String R = System.getProperty("user.dir");

	public Defausse(Partie p){
		super("Hanabi, la pile de défausse");
		this.partie = p;
		int x = (p.getDefausse().size()>4)?7*128:p.getDefausse().size()*128;
		int y = 200 + (p.getDefausse().size()/7)*65;
		this.setSize(x, y);
		this.setResizable(false);
		
		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i=0; i<this.partie.getDefausse().size(); i++){
			Image img = new ImageIcon(Defausse.R+this.partie.getDefausse().get(i).getCardName()).getImage();
			g.drawImage(img, (i%7)*128, (i/7)*65, 128, 200, this);
		}
	}
}
