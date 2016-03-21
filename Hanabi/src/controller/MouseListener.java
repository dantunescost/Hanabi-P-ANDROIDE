package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.AdditionMainPleineException;
import model.Couleur;
import model.EnleverCarteInexistanteException;
import model.IndiceSoitMemeException;
import model.Joueur;
import model.JoueurIA;
import model.PartiePerdueException;
import model.PiocheVideException;
import view.Defausse;
import view.FenetrePartie;

public class MouseListener extends MouseAdapter {
	private boolean firstClick = true;
	private boolean secondClick = false;
	private boolean jouerCoup = false;
	private boolean thirdClick = false;
	private FenetrePartie partie;
	private Joueur targetPlayer = null;
	private int nbCartes;
	private int karteH;
	private int karteW;
	
	public MouseListener(FenetrePartie partie){
		this.partie = partie;
        nbCartes = this.partie.getPartie().getNbCartes();
        karteH = this.partie.tableHeight/4;
        karteW =(int)( (float)karteH*0.645);
	}
	
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if(this.firstClick && isInButtonJouer(x, y)){
			this.partie.setAnnuler(true);
			this.firstClick = false;
			this.secondClick = true;
			this.jouerCoup = true;
			this.partie.update(this.partie.getGraphics());
		}
		else{ 
			if(this.secondClick && isInButtonAnnuler(x,y)){
				this.partie.setAnnuler(false);
				this.firstClick = true;
				this.secondClick = false;
				this.jouerCoup = false;
				this.partie.update(this.partie.getGraphics());
			}
			else{
				if(this.secondClick && isInPlayersCards(x,y)!=0 && this.jouerCoup){
					try {
						this.partie.getPartie().joueCarte(this.partie.getPartie().getJoueurs()[0], isInPlayersCards(x, y)-1);
					} catch (EnleverCarteInexistanteException | PartiePerdueException | AdditionMainPleineException | PiocheVideException e1) {
						e1.printStackTrace();
					}
					if(this.partie.getPartie().getaQuiLeTour()!=0){
						faireJouerIAs();
					}
					this.partie.setAnnuler(false);
					this.firstClick = true;
					this.secondClick = false;
					this.jouerCoup = false;
					this.partie.update(this.partie.getGraphics());
				}
				else{ 
					if(this.firstClick && isInDefausse(x,y)){
						if(this.partie.getPartie().getDefausse().size() != 0){
							new Defausse(this.partie.getPartie());
						}
					}
					else{ 
						if(this.secondClick && isInPlayersCards(x,y) != 0){
							try {
								this.partie.getPartie().defausse(this.partie.getPartie().getJoueurs()[0], isInPlayersCards(x, y)-1);
							} catch (EnleverCarteInexistanteException | AdditionMainPleineException | PiocheVideException e1) {
								e1.printStackTrace();
							}
							if(this.partie.getPartie().getaQuiLeTour()!=0){
								faireJouerIAs();
							}
							this.partie.setAnnuler(false);
							this.firstClick = true;
							this.secondClick = false;
							this.partie.update(this.partie.getGraphics());
						}
						else{ 
							if(this.firstClick && isInButtonDefausser(x,y)){
								this.partie.setAnnuler(true);
								this.firstClick = false;
								this.secondClick = true;
								this.partie.update(this.partie.getGraphics());
							}
							else{ 
								if(this.firstClick && isInButtonIndice(x,y)){
									this.partie.setAnnuler(true);
									this.firstClick = false;
									this.secondClick = true;
									this.partie.update(this.partie.getGraphics());
								}
								else{  
									if(this.secondClick && isInCoPlayersHand(x,y)!=0){
										this.thirdClick = true;
										this.secondClick = false;
										this.targetPlayer  = this.partie.getPartie().getJoueurs()[isInCoPlayersHand(x, y)];
									}
									else{  
										if(this.thirdClick && isInButtonAnnuler(x,y)){
											this.partie.setAnnuler(false);
											this.firstClick = true;
											this.thirdClick = false;
											this.targetPlayer = null;
											this.partie.update(this.partie.getGraphics());
										}
										else{  
											if(this.thirdClick && isInIndices(x,y) != 0){
												try {
													if(isInIndices(x, y)>5){
														this.partie.getPartie().indiceCouleur(this.targetPlayer, Couleur.intToCardColor(isInIndices(x, y)));
													}
													else{
														this.partie.getPartie().indiceValeur(this.targetPlayer, isInIndices(x, y));
													}
												} catch (IndiceSoitMemeException e1) {
													e1.printStackTrace();
												}
												if(this.partie.getPartie().getaQuiLeTour()!=0){
													faireJouerIAs();
												}
												this.partie.setAnnuler(false);
												this.firstClick = true;
												this.thirdClick = false;
												this.targetPlayer = null;
												this.partie.update(this.partie.getGraphics());
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private int isInIndices(int x, int y) {
		boolean trouve = false;
		int i = 0;
		while(i<5 && !trouve){
			if(x>=20+i*45 && y>=this.partie.getHeight()-100 && x<=50+i*45 && y<=this.partie.getHeight()-70){
				trouve = true;
			}
			i++;
		}
		if(trouve){
			return i;
		}
		else{
			if(y>=this.partie.getHeight()-50 && y<=this.partie.getHeight()-20){
				if(x>=20 && x<=50 ){
					return 6;
				}
				else{
					if(x>=65 && x<=95){
						return 7;
					}
					else{
						if(x>=110 && x<=140){
							return 8;
						}
						else{
							if(x>=155 && x<=185){
								return 9;
							}
							else{
								if(x>=200 && x<=230){
									return 10;
								}
							}
						}
					}
				}
			}
		}
		if(this.partie.getPartie().isMulticolor() && x>=245 && y>=this.partie.getHeight()-75 && x<=275 && y<=this.partie.getHeight()-45){
			return 11;
		}
		return 0;
	}

	private boolean isInButtonIndice(int x, int y) {
		int startX = (this.partie.getWidth()/2)-205;
		int startY = (this.partie.getHeight())-80;
		if(x >= startX && y >= startY && x <= startX+99 && y <= startY+40){
			System.out.println("You clicked on indice!");
			return true;
		}
		else{
			return false;
		}
	}

	private boolean isInButtonDefausser(int x, int y) {
		int startX = (this.partie.getWidth()/2)+65;
		int startY = (this.partie.getHeight())-80;
		if(x >= startX && y >= startY && x <= startX+140 && y <= startY+40){
			System.out.println("You clicked on defausser!");
			return true;
		}
		else{
			return false;
		}
	}

	private boolean isInDefausse(int x, int y) {
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
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + this.partie.tableWidth/2;
        if(this.nbCartes==5){
            startX -= (karteW/2)*5;
        }
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2+ (this.partie.tableHeight/8)*7;
		if(x>=startX && y>=startX && x<=startX+(this.nbCartes*karteW) && y<=startY+karteH){
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
	
	public int isInCoPlayersHand(int x, int y){
		switch(this.partie.getPartie().getNbJoueurs()){
		case 2:
			if(isHandCenterTopSelected(x, y)){
				return 1;
			}
			break;
		case 3:
			if(isHandLeftTopCornerSelected(x, y)){
				return 1;
			}
			if(isHandRightTopCornerSelected(x, y)){
				return 2;
			}
			break;
		case 4:
			if(isHandLeftTopCornerSelected(x, y)){
				return 1;
			}
			if(isHandCenterTopSelected(x, y)){
				return 2;
			}
			if(isHandRightTopCornerSelected(x, y)){
				return 3;
			}
			break;
		case 5:
			if(isHandLeftBottomCornerSelected(x, y)){
				return 1;
			}
			if(isHandLeftTopSelected(x, y)){
				return 2;
			}
			if(isHandRightTopSelected(x, y)){
				return 3;
			}
			if(isHandRightBottomCornerSelected(x, y)){
				return 4;
			}
			break;
		}
		return 0;
	}
	
	public boolean isHandCenterTopSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + this.partie.tableWidth/2-karteW;
        if(nbCartes==5){
            startX -= 25;
        }
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2-(karteH/3)*2;
		if(x>=startX && y>=startY && x<=startX+nbCartes*25 && y<=startY+karteH){
			System.out.println("You clicked on the top centered player's cards!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isHandRightTopSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/10)*7 - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2-(karteH/3)*2;
        if(x>=startX && y>=startY && x<=startX+nbCartes*25 && y<=startY+karteH){
			System.out.println("You clicked on the top right player's cards!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isHandLeftTopSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/10)*3 - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2-(karteH/3)*2;
        if(x>=startX && y>=startY && x<=startX+nbCartes*25 && y<=startY+karteH){
			System.out.println("You clicked on the top right player's cards!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isHandRightTopCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/10)*9 - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 - (karteH/3)*2;
        if(nbCartes==5){
            startX -= 25;
        }
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX+i*25 && y>=startY+i*20 && x<=startX+i*25+karteW && y<=startY+i*20+karteH){
        		trouve = true;
        	}
        	i++;
        }
		return trouve;
	}
	
	public boolean isHandLeftTopCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/4) - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 - (karteH/3)*2;
        if(nbCartes==5){
            startX -= 25;
        }
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX-i*25 && y>=startY+i*20 && x<=startX-i*25+karteW && y<=startY+i*20+karteH){
        		trouve = true;
        	}
        	i++;
        }
        return trouve;
	}
	
	public boolean isHandRightBottomCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/11)*10 - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 + (this.partie.tableHeight/8)*7 - (karteH/3)*2;
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX+i*20 && y>=startY-i*20 && x<=startX+i*20+karteW && y<=startY-i*20+karteH){
        		trouve = true;
        	}
        	i++;
        }
        return trouve;
	}
	
	public boolean isHandLeftBottomCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/11)*2 - karteW;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 + (this.partie.tableHeight/8)*7 - (karteH/3)*2;
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX-i*20 && y>=startY-i*20 && x<=startX-i*20+karteW && y<=startY-i*20+karteH){
        		trouve = true;
        	}
        	i++;
        }
        return trouve;
	}
	
	public void faireJouerIAs(){
		for(int i=1;i<this.partie.getPartie().getNbJoueurs();i++){
			JoueurIA player = (JoueurIA) this.partie.getPartie().getJoueurs()[i];
			player.jouerCoup();
		}
		
	}
}
