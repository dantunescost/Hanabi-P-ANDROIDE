package view;

import model.Couleur;
import model.EnleverCarteInexistanteException;
import model.Main;

import javax.swing.*;

import java.awt.*;

public class AfficherMains {

    FenetrePartie p;
    public static String R = System.getProperty("user.dir");
    private int karteH;
    private int karteW;
    
    
    public AfficherMains(FenetrePartie p){
    	this.p = p;
		if(System.getProperty("os.name").equals("MAC OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
        karteH = this.p.tableHeight/4;
        karteW =(int)( (float)karteH*0.645);
    }

    public void showHandCenterTop(Graphics g, Main main) throws EnleverCarteInexistanteException {
        int startX = (this.p.getWidth() - p.tableWidth) /2 + p.tableWidth/2;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandCenterBottom(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + this.p.tableWidth/2;
        if(main.getNbCartes()==5){
            startX -= (karteW/2)*5;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2+ (this.p.tableHeight/8)*7;
        Image karte = new ImageIcon(R+"cardback.png").getImage();
        Font police = new Font("Arial",Font.BOLD,20);
		g.setFont(police);
        for(int i=0;i<main.getNbCartes();i++){
            g.drawImage(karte, startX+i*karteW, startY-(karteH/3)*2, karteW, karteH, this.p);
            if(this.p.getPartie().getJoueurs()[0].getMain().getCarte(i).isValeurConnue()){
            	g.setColor(Color.white);
            	g.fillOval(startX+i*karteW+karteW/6, startY-(karteH/3)*2+karteH/9, karteW/2, karteW/2);
            	g.setColor(Color.black);
            	g.drawOval(startX+i*karteW+karteW/6, startY-(karteH/3)*2+karteH/9, karteW/2, karteW/2);
        		g.drawString(Integer.toString(this.p.getPartie().getJoueurs()[0].getMain().getCarte(i).getValeur()), startX+i*karteW+karteW/3, startY-(karteH/3)*2+(karteH/14)*5);
            }
            if(this.p.getPartie().getJoueurs()[0].getMain().getCarte(i).isCouleurConnue()){
            	g.setColor(Couleur.cardColorToColor(this.p.getPartie().getJoueurs()[0].getMain().getCarte(i).getCouleur()));
            	g.fillOval(startX+i*karteW+karteW/6, startY-(karteH/3)*2+(karteH/9)*5, karteW/2, karteW/2);
            	g.setColor(Color.black);
            	g.drawOval(startX+i*karteW+karteW/6, startY-(karteH/3)*2+(karteH/9)*5, karteW/2, karteW/2);
            }
            
        }
    }

    public void showHandRightTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/10)*7;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandLeftTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/10)*3;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandRightTopCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/10)*9;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2+i*20, karteW, karteH, this.p);
        }
    }

    public void showHandLeftTopCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/8)*2;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW-i*25, startY-(karteH/3)*2+i*20, karteW, karteH, this.p);
        }
    }

    public void showHandLeftBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/11)*2;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2 + (this.p.tableHeight/8)*7;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW-i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this.p);
        }
    }

    public void showHandRightBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/11)*10 ;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2 + (this.p.tableHeight/8)*7;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this.p);
        }
    }
    
    public void afficherMain(Graphics g) throws EnleverCarteInexistanteException{
    	switch(this.p.getPartie().getJoueurs().length){
    	case 2:
    		show2players(g);
    		break;
    	case 3:
    		show3players(g);
    		break;
    	case 4:
    		show4players(g);
    		break;
    	case 5:
    		show5players(g);
    		break;
    	}
    }

	
    public void show2players(Graphics g) throws EnleverCarteInexistanteException {
        showHandCenterBottom(g, p.getPartie().getJoueurs()[0].getMain());
        showHandCenterTop(g, p.getPartie().getJoueurs()[1].getMain());
    }

    public void show3players(Graphics g) throws EnleverCarteInexistanteException {
        showHandCenterBottom(g, p.getPartie().getJoueurs()[0].getMain());
        showHandLeftTopCorner(g, p.getPartie().getJoueurs()[1].getMain());
        showHandRightTopCorner(g, p.getPartie().getJoueurs()[2].getMain());
    }
    
	public void show4players(Graphics g) throws EnleverCarteInexistanteException {
        showHandCenterBottom(g,this.p.getPartie().getJoueurs()[0].getMain());
        showHandLeftTopCorner(g,this.p.getPartie().getJoueurs()[1].getMain());
        showHandCenterTop(g,this.p.getPartie().getJoueurs()[2].getMain());
        showHandRightTopCorner(g,this.p.getPartie().getJoueurs()[3].getMain());
	}
	
	public void show5players(Graphics g)throws EnleverCarteInexistanteException {
        showHandCenterBottom(g,this.p.getPartie().getJoueurs()[0].getMain());
        showHandLeftBottomCorner(g,this.p.getPartie().getJoueurs()[1].getMain());
        showHandLeftTop(g,this.p.getPartie().getJoueurs()[2].getMain());
        showHandRightTop(g,this.p.getPartie().getJoueurs()[3].getMain());
        showHandRightBottomCorner(g,this.p.getPartie().getJoueurs()[4].getMain());
	}

}
