package view;

import model.EnleverCarteInexistanteException;
import model.Main;

import javax.swing.*;

import java.awt.*;

public class AfficherMains {

    FenetrePartie p;
    
    public AfficherMains(FenetrePartie p){
    	this.p = p;
    }
    public static String R = System.getProperty("user.dir")+"/Hanabi/ressources/";

    public void showHandCenterTop(Graphics g, Main main) throws EnleverCarteInexistanteException {
        int karteH = p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
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
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + this.p.tableWidth/2;
        if(main.getNbCartes()==5){
            startX -= (karteW/2)*5;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2+ (this.p.tableHeight/8)*7;
        Image karte = new ImageIcon("ressources/cardback.png").getImage();
        for(int i=0;i<main.getNbCartes();i++){
            g.drawImage(karte, startX+i*karteW, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandRightTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/10)*7;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandLeftTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/10)*3;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this.p);
        }
    }

    public void showHandRightTopCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
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
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
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
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/11)*2;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2 + (this.p.tableHeight/8)*7;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon(R+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW-i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this.p);
        }
    }

    public void showHandRightBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
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
        showHandLeftTopCorner(g,this.p.getPartie().getJoueurs()[3].getMain());
	}
	
	public void show5players(Graphics g)throws EnleverCarteInexistanteException {
        showHandCenterBottom(g,this.p.getPartie().getJoueurs()[0].getMain());
        showHandLeftBottomCorner(g,this.p.getPartie().getJoueurs()[1].getMain());
        showHandLeftTop(g,this.p.getPartie().getJoueurs()[2].getMain());
        showHandRightTop(g,this.p.getPartie().getJoueurs()[3].getMain());
        showHandRightBottomCorner(g,this.p.getPartie().getJoueurs()[4].getMain());
	}

}
