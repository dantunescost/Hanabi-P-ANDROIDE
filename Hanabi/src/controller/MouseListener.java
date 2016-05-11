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
import view.FenetrePartie;
import view.PartieGagne;
import view.PartiePerdue;


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
	private boolean defausser = false;
	
	public MouseListener(FenetrePartie partie){
		this.partie = partie;
        nbCartes = this.partie.getPartie().getNbCartes();
        karteH = this.partie.tableHeight/4;
        karteW =(int)( (float)karteH*0.645);
	}

	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if(this.partie.afficheDef){
			this.partie.afficheDef = false;
			this.partie.repaint();
		}
		else if(!this.partie.getPartie().getFinPartie()){
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
					this.defausser  = false;
					this.partie.update(this.partie.getGraphics());
				}
				else{
					if(this.secondClick && isInPlayersCards(x,y)!=0 && this.jouerCoup){
						try {
							this.partie.getPartie().joueCarte(this.partie.getPartie().getJoueurs()[0], isInPlayersCards(x, y)-1);
						} catch (EnleverCarteInexistanteException | AdditionMainPleineException | PiocheVideException e1) {
							e1.printStackTrace();
						} catch(PartiePerdueException e2){
							new PartiePerdue(this.partie.getPartie(), partie);
						}
						this.partie.setAnnuler(false);
						this.firstClick = true;
						this.secondClick = false;
						this.jouerCoup = false;
						this.partie.update(this.partie.getGraphics());
						if(this.partie.getPartie().getaQuiLeTour()!=0){
							if(this.partie.getPartie().getFinPartie()){
								System.out.println("Partie finie 1");
								new PartieGagne(this.partie.getPartie(), partie);
							}
							else
							{
								faireJouerIAs();
							}
						}
					}
					else{
						if(this.firstClick && isInDefausse(x,y)){
							if(this.partie.getPartie().getDefausse().size() > 0){
								this.partie.afficheDef = true;
								this.partie.repaint();
							}
						}
						else{
							if(this.secondClick && isInPlayersCards(x,y) != 0 && this.defausser){
								try {
									this.partie.getPartie().defausse(this.partie.getPartie().getJoueurs()[0], isInPlayersCards(x, y)-1);
								} catch (EnleverCarteInexistanteException | AdditionMainPleineException | PiocheVideException e1) {
									e1.printStackTrace();
								}
								this.partie.setAnnuler(false);
								this.firstClick = true;
								this.secondClick = false;
								this.defausser=false;
								this.partie.update(this.partie.getGraphics());
								if(this.partie.getPartie().getaQuiLeTour()!=0){
									if(this.partie.getPartie().getFinPartie()){
										System.out.println("Partie finie 2");
										new PartieGagne(this.partie.getPartie(), partie);
									}
									else
									{
										faireJouerIAs();
									}
								}
							}
							else{
								if(this.firstClick && isInButtonDefausser(x,y)){
									this.partie.setAnnuler(true);
									this.firstClick = false;
									this.secondClick = true;
									this.defausser = true;
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
											this.partie.repaint();
										}
										else{
											if(this.thirdClick && isInButtonAnnuler(x,y)){
												this.partie.setAnnuler(false);
												this.partie.playerSelected = 0;
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
													this.partie.setAnnuler(false);
													this.partie.playerSelected = 0;
													this.firstClick = true;
													this.thirdClick = false;
													this.targetPlayer = null;
													this.partie.update(this.partie.getGraphics());
													if(this.partie.getPartie().getaQuiLeTour()!=0){
														if(this.partie.getPartie().getFinPartie()){
															System.out.println("Partie finie 3");
															new PartieGagne(this.partie.getPartie(), partie);
														}
														else
														{
															faireJouerIAs();
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
			}
		}
		this.partie.repaint();
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
		if(x >= startX && y >= startY && x <= startX+99 && y <= startY+40 && partie.getPartie().getJetonIndice()>0){
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
        else{
        	startX -= karteW*2;
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
				this.partie.playerSelected = 1;
				return 1;
			}
			break;
		case 3:
			if(isHandLeftTopCornerSelected(x, y)){
				this.partie.playerSelected = 1;
				return 1;
			}
			if(isHandRightTopCornerSelected(x, y)){
				this.partie.playerSelected = 2;
				return 2;
			}
			break;
		case 4:
			if(isHandLeftTopCornerSelected(x, y)){
				this.partie.playerSelected = 1;
				return 1;
			}
			if(isHandCenterTopSelected(x, y)){
				this.partie.playerSelected = 2;
				return 2;
			}
			if(isHandRightTopCornerSelected(x, y)){
				this.partie.playerSelected = 3;
				return 3;
			}
			break;
		case 5:
			if(isHandLeftBottomCornerSelected(x, y)){
				this.partie.playerSelected = 1;
				return 1;
			}
			if(isHandLeftTopSelected(x, y)){
				this.partie.playerSelected = 2;
				return 2;
			}
			if(isHandRightTopSelected(x, y)){
				this.partie.playerSelected = 3;
				return 3;
			}
			if(isHandRightBottomCornerSelected(x, y)){
				this.partie.playerSelected = 4;
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
			System.out.println("You clicked on the top left player's cards!");
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isHandRightTopCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/10)*9 - karteW - 25;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 - (karteH/3)*2;
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX+i*25 && y>=startY+i*20 && x<=startX+i*25+karteW && y<=startY+i*20+karteH){
    			System.out.println("You clicked on the top right corner player's cards!");
        		trouve = true;
        	}
        	i++;
        }
		return trouve;
	}
	
	public boolean isHandLeftTopCornerSelected(int x, int y){
        int startX = (this.partie.getWidth() - this.partie.tableWidth) /2 + (this.partie.tableWidth/4) - karteW - 25;
        int startY = (this.partie.getHeight() - this.partie.tableHeight) /2 - (karteH/3)*2;
        int i = 0;
        boolean trouve = false;
        while(i<nbCartes && !trouve){
        	if(x>=startX-i*25 && y>=startY+i*20 && x<=startX-i*25+karteW && y<=startY+i*20+karteH){
    			System.out.println("You clicked on the top left corner player's cards!");
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
    			System.out.println("You clicked on the bottom right corner player's cards!");
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
    			System.out.println("You clicked on the bottom left corner player's cards!");
        		trouve = true;
        	}
        	i++;
        }
        return trouve;
	}
	
	public void faireJouerIAs(){
		for(int i=this.partie.getPartie().getaQuiLeTour();i<this.partie.getPartie().getNbJoueurs();i++){
			if(!this.partie.getPartie().getFinPartie())
			{
				JoueurIA player = (JoueurIA) this.partie.getPartie().getJoueurs()[i];
				if( (player.getId() == this.partie.getPartie().getDernierJoueur()) && (this.partie.getPartie().getDernierTour()) ){
					this.partie.getPartie().finirPartie();
					System.out.println("Partie finie 4, id_ia : " + player.getId());
					new PartieGagne(this.partie.getPartie(), partie);
				}
				player.jouerCoup();
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.partie.update(this.partie.getGraphics());
			}
			
		}
		
	}
}
