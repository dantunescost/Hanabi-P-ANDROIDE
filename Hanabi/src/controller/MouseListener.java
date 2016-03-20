package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.AdditionMainPleineException;
import model.EnleverCarteInexistanteException;
import model.PartiePerdueException;
import model.PiocheVideException;
import view.Defausse;
import view.FenetrePartie;

public class MouseListener extends MouseAdapter {
	private boolean firstClick = true;
	private boolean secondClick = false;
	private boolean thirdClick = false;
	private boolean jouerCarte = false;
	private FenetrePartie partie;
	
	public MouseListener(FenetrePartie partie){
		this.partie = partie;
	}
	
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if(this.firstClick && isInButtonJouer(x, y)){
			this.partie.setAnnuler(true);
			this.firstClick = false;
			this.secondClick = true;
			this.jouerCarte = true;
			this.partie.update(this.partie.getGraphics());
		}
		else{ 
			if(this.secondClick && isInButtonAnnuler(x,y)){
				this.partie.setAnnuler(false);
				this.firstClick = true;
				this.secondClick = false;
				this.jouerCarte = false;
				this.partie.update(this.partie.getGraphics());
			}
			else{
				if(this.secondClick && isInPlayersCards(x,y)!=0){
					try {
						this.partie.getPartie().joueCarte(this.partie.getPartie().getJoueurs()[0], isInPlayersCards(x, y)-1);
					} catch (EnleverCarteInexistanteException | PartiePerdueException | AdditionMainPleineException | PiocheVideException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.partie.setAnnuler(false);
					this.firstClick = true;
					this.secondClick = false;
					this.jouerCarte = false;
					this.partie.update(this.partie.getGraphics());
				}
				else{ 
					if(this.firstClick && isInDefausse(x,y)){
						if(this.partie.getPartie().getDefausse().size() != 0){
							new Defausse(this.partie.getPartie());
						}
					}
				}
			}
		}
	}
	
	private boolean isInDefausse(int x, int y) {
		int karteH = this.partie.tableHeight/4;
		int karteW =(int)((float)karteH*0.645);
		int startX = (this.partie.getWidth()/2 - this.partie.getTableWidth()/2)/2-karteW/2;
		int startY = (this.partie.getHeight()/2)-karteH/2;
		
		if(x >= startX && y >= startY && x <= startX+karteW && y <= startY+karteH){
			System.out.println("You clicked on defausse!");
			return true;
		}
		else{
			return false;
		}
	}

	private int isInPlayersCards(int x, int y) {
        int karteH = this.partie.tableHeight/4;
        int karteW =(int)( (float)karteH*0.645);
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + this.partie.tableWidth/2;
        if(this.partie.getPartie().getNbCartes()==5){
            startX -= (karteW/2)*5;
        }
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2+ (this.partie.tableHeight/8)*7;
		if(x>=startX && y>=startX && x<=startX+(this.partie.getPartie().getNbCartes()*karteW) && y<=startY+karteH){
			System.out.println("You clicked on your cards!");
			if(x<=startX+karteW){
				return 1;
			}
			else{
				if(x<=startX+karteW*2){
					return 2;
				}
				else{
					if(x<=startX+karteW*3){
						return 3;
					}
					else{
						if(x<=startX+karteW*4){
							return 4;
						}
						else{
							return 5;
						}
					}
				}
			}
		}
		else{
			return 0;
		}
	}

	private boolean isInButtonAnnuler(int x, int y) {
		int startX = (this.partie.getWidth()/2)-60;
		int startY = (this.partie.getHeight())-80;
		if(x>=startX && y>=startY && x<=startX+121 && y<=startY+40){
			System.out.println("You clicked on Annuler!");
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isInButtonJouer(int x, int y){
		int startX = (this.partie.getWidth()/2)-96;
		int startY = (this.partie.getHeight())-80;
		if(x>=startX && y>=startY && x<=startX+151 && y<=startY+40){
			System.out.println("You clicked on Jouer!");
			return true;
		}
		else{
			return false;
		}
	}
}
