package view;

import model.Partie;

public class AfficherMains {

    FenetrePartie p;

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
