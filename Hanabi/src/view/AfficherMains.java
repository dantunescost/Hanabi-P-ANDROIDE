package view;

import model.EnleverCarteInexistanteException;
import model.Main;

import javax.swing.*;
import java.awt.*;

public class AfficherMains {

    FenetrePartie p;

    public void showHandCenterTop(Graphics g, Main main) throws EnleverCarteInexistanteException {
        int karteH = p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - p.tableWidth) /2 + p.tableWidth/2;
        if(main.getNbCartes()==5){
            startX -= 25;
        }
        int startY = (this.p.getHeight() - this.p.tableHeight) /2;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
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
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
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
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
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
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
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
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
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
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW-i*25, startY-(karteH/3)*2+i*20, karteW, karteH, this.p);
        }
    }

    public void showHandLeftBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/11)*2;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2 + (this.p.tableHeight/8)*7;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW-i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this.p);
        }
    }

    public void showHandRightBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
        int karteH = this.p.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.p.getWidth() - this.p.tableWidth) /2 + (this.p.tableWidth/11)*10 ;
        int startY = (this.p.getHeight() - this.p.tableHeight) /2 + (this.p.tableHeight/8)*7;
        for(int i=0;i<main.getNbCartes();i++){
            Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
            g.drawImage(karte, startX-karteW+i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this.p);
        }
    }
    
    public AfficherMains(FenetrePartie p){

        this.p = p;

        int nbJ = p.getPartie().getJoueurs().length;

        if (nbJ==2){
            //p.showHandCenterTop(g, p.getPartie().getJoueurs()[1].getMain());
        }


    /*
        showHandCenterBottom(g,this.partie.getJoueurs()[1].getMain());
        showHandLeftBottomCorner(g,this.partie.getJoueurs()[1].getMain());
        showHandRightBottomCorner(g,this.partie.getJoueurs()[1].getMain());
        showHandLeftTopCorner(g,this.partie.getJoueurs()[1].getMain());
        showHandRightTop(g,this.partie.getJoueurs()[1].getMain());
        */
    }

}
