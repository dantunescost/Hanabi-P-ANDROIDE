package model;

import java.util.Scanner;

public class SimulationPartie extends Partie {
	private static final long serialVersionUID = 595418792495031308L;

	public SimulationPartie(int nbJoueurs, int maxIndices, boolean multicolor) {
		super(nbJoueurs, maxIndices, multicolor);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SimulationPartie game;
		final int nbIndices = 8;
		Joueur[] joue;
		int nbSimulations;
		int nbJoueurs;
		boolean multi = false;
		float scoreTotal = 0;
		float scoreMoyen = 0;
		float erreursTotal = 0;
		int max = -1;
		int min = 42;
	    Scanner in = new Scanner(System.in);
	    
	    String choixIA = "Veuillez entrer le type d'IA desire : \n" 
	    			   	+ "0 -> DummyJoueurIA\n"
	    			   	+ "1 -> SemiDummyJoueurIA\n"
	    			   	+ "2 -> HeuristicJoueurIA\n"
	    			   	+ "3 -> EpistemicJoueurIA";
		
		System.out.println("Veuillez entrer le nombre de simulations : ");
		while(!in.hasNextInt());
		nbSimulations = in.nextInt();
		in.nextLine();
		
		System.out.println("Veuillez entrer le nombre de joueurs : ");
		while(!in.hasNextInt())
			in.nextLine();
		nbJoueurs = in.nextInt();
		in.nextLine();
		
		System.out.println("Cartes multicolors : (y/n)");
		String mul = in.nextLine();
		if(mul.toUpperCase().equals("Y"))
			multi = true;
		
		game = new SimulationPartie(nbJoueurs, nbIndices, multi);
		joue = new Joueur[nbJoueurs];		
		
		System.out.println(choixIA);
		for(int i=1; i<= nbJoueurs; i++)
		{
			System.out.println("Pour le joueur " + i + " :");
			while(!in.hasNextInt())
				in.nextLine();
			int c = in.nextInt();
			switch (c)
			{
			case 0:
				joue[i-1] = new DummyJoueurIA(Integer.toString(i), game, i-1);
				System.out.println("Vous avez selectionne DummyJoueurIA pour le joueur " + i);
				break;
			case 1:
				joue[i-1] = new SemiDummyJoueurIA(Integer.toString(i), game, i-1);
				System.out.println("Vous avez selectionne SemiDummyJoueurIA pour le joueur " + i);
				break;
			case 2:
				ParamHeuristic param=new ParamHeuristic();
				joue[i-1] = new HeuristicJoueurIA(Integer.toString(i), game, i-1,param);
				System.out.println("Vous avez selectionne HeuristicJoueurIA pour le joueur " + i);
				break;
			case 3:
				joue[i-1] = new EpistemicJoueurIA(Integer.toString(i), game, i-1,(float)0.62);
				System.out.println("Vous avez selectionne EpistemicJoueurIA pour le joueur " + i);
				break;
			default:
				joue[i-1] = new SemiDummyJoueurIA(Integer.toString(i), game, i-1);
				System.out.println("Valeur non definie, par defaut SemiDummyJoueurIA pour le joueur " + i);
			}
		}
		
		
		for(int i=0; i<nbSimulations; i++)
		{			
			try {
				game.reinitPartie(joue);
			} catch (AdditionMainPleineException | PiocheVideException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(!game.getFinPartie())
			{
				int j = game.getaQuiLeTour();
				Joueur[] p = game.getJoueurs();
				if(p[j].getClass().getName().equals(DummyJoueurIA.class.getName()))
				{
					DummyJoueurIA ia = (DummyJoueurIA) p[j];
					ia.jouerCoup();
				}
				else if(p[j].getClass().getName().equals(SemiDummyJoueurIA.class.getName()))
				{
					SemiDummyJoueurIA ia = (SemiDummyJoueurIA) p[j];
					ia.jouerCoup();
				}
				else if(p[j].getClass().getName().equals(HeuristicJoueurIA.class.getName()))
				{
					HeuristicJoueurIA ia = (HeuristicJoueurIA) p[j];
					ia.jouerCoup();
				}
				else if(p[j].getClass().getName().equals(EpistemicJoueurIA.class.getName()))
				{
					EpistemicJoueurIA ia = (EpistemicJoueurIA) p[j];
					ia.jouerCoup();
				}
 			}
			int erreurs = game.getJetonEclair();
			int score = game.calculerPoints();
			scoreTotal += score;
			erreursTotal += erreurs;
			min = (score < min)? score:min;
			max = (score > max)? score:max;
			
			System.out.println("Partie " + (i+1) + " terminee. Score : " + score);
		}
		scoreMoyen = scoreTotal / nbSimulations;
		System.out.println("Score max : " + max + "\nScore min : " + min + "\nScore moyen : " + scoreMoyen+ "\nNombre d'erreurs en moyenne : " + erreursTotal/nbSimulations);
		
		in.close();
	}

}
