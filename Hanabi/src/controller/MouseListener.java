package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.FenetrePartie;

public class MouseListener extends MouseAdapter {
	private boolean firstClick = true;
	private boolean secondClick = false;
	private boolean thirdClick = false;
	private int x1,y1,x2,y2,x3,y3;
	private FenetrePartie partie;
	
	public MouseListener(FenetrePartie partie){
		this.partie = partie;
	}
	
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		
		if(this.firstClick && isInButtonJouer(x, y)){
			this.partie.setAnnuler(true);
			this.partie.update(this.partie.getGraphics());
		}
	}
	
	public boolean isInButtonJouer(int x, int y){
		int startX = (this.partie.getWidth()/2)-96;
		int startY = (this.partie.getHeight())-80;
		if(x>=startX && y>=startX && x<=startX+151 && y<=startY+40){
			System.out.println("You clicked on Jouer!");
			return true;
		}
		else{
			return false;
		}
	}
}
