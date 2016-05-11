package controller;

import model.JoueurIA;
import model.Partie;
import view.FenetrePartie;
import view.Parametres;
import view.PartieGagne;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GagneListener extends MouseAdapter {
    PartieGagne pg;
    FenetrePartie fp;

    public GagneListener(PartieGagne p, FenetrePartie fp) {
        this.pg = p;
        this.fp = fp;
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x>=10 && x<= 203 && y>=420 && y<=460) {
            pg.dispose();
            fp.setVisible(false);
            new Parametres(fp);
        }
        else if (x>=407 && x<= 519 && y>=420 && y<=460){
            pg.dispose();
            fp.dispose();
        }
        else if (x>=213 && x<=397 && y>=420 && y<=460){
            Partie p = loadFromFile();
            if (p != null) {
                fp.setPartie(p);
                if (p.getaQuiLeTour() != 0) {
                    for (int i = ((FenetrePartie) fp).getPartie().getaQuiLeTour(); i < ((FenetrePartie) fp).getPartie().getNbJoueurs(); i++) {
                        if (!((FenetrePartie) fp).getPartie().getFinPartie()) {
                            JoueurIA player = (JoueurIA) ((FenetrePartie) fp).getPartie().getJoueurs()[i];
                            if ((player.getId() == ((FenetrePartie) fp).getPartie().getDernierJoueur()) && (((FenetrePartie) fp).getPartie().getDernierTour())) {
                                ((FenetrePartie) fp).getPartie().finirPartie();
                                System.out.println("Partie finie 4, id_ia : " + player.getId());
                                new PartieGagne(p, fp); // Does this need to be there ?
                            }
                            player.jouerCoup();
                            try {
                                Thread.sleep(750);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            ((FenetrePartie) fp).update(((FenetrePartie) fp).getGraphics());
                        }
                    }
                }
                fp.repaint();
                pg.dispose();
            }
        }
    }

    public Partie loadFromFile() {
        Partie p = null;
        FileDialog dialog = new FileDialog(pg, "Charger Partie", FileDialog.LOAD);
        dialog.setVisible(true);
        String dir = dialog.getDirectory();
        String fileName = dialog.getFile();
        String filePath = dir + fileName;
        if (fileName != null && fileName.trim().length() != 0) {
            File file = new File(filePath);
            FileInputStream fileStream;
            try {
                fileStream = new FileInputStream(file);
                ObjectInputStream objectStream = new ObjectInputStream(fileStream);
                p = (Partie) objectStream.readObject();
                objectStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return p;
    }
}

