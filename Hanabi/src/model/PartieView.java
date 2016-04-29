package model;


public class PartieView extends Partie{
	private static final long serialVersionUID = 2075863595013544665L;
	private int nb_points;
	private int nb_indices;
	private int nb_erreurs;
	private int nb_jouables;
	private int nb_defaussables;
	private int info_jouables;
	private int info_defaussables;
	private int info_neutres;
	
	private double h_nbp=5;
	private double h_nbi=1.5;
	private double h_nbe=4;
	private double h_nbj=1.5;
	private double h_nbd=1.25;
	private double h_ij=2;
	private double h_id=1.6;
	private double h_in=1.25;
	
	public PartieView(Partie p)
	{
		/*Partie clone*/
		super(p.nbJoueurs,p.maxIndices, p.multicolor, true);
		/*this.jetonEclair = p.jetonEclair;
		this.jetonIndice=p.jetonIndice;
		this.aQuiLeTour=p.aQuiLeTour;
		this.dernierJoueur=p.dernierJoueur;
		this.dernierTour=p.dernierTour;
		this.defausse = (ArrayList<Carte>)p.defausse.clone();
		this.pioche = (ArrayList<Carte>)p.pioche.clone();
		this.cartesJouees.put(CardColor.BLANC, (ArrayList<Carte>)p.cartesJouees.get(CardColor.BLANC).clone());
		this.cartesJouees.put(CardColor.BLEU, (ArrayList<Carte>)p.cartesJouees.get(CardColor.BLEU).clone());
		this.cartesJouees.put(CardColor.VERT, (ArrayList<Carte>)p.cartesJouees.get(CardColor.VERT).clone());
		this.cartesJouees.put(CardColor.ROUGE, (ArrayList<Carte>)p.cartesJouees.get(CardColor.ROUGE).clone());
		this.cartesJouees.put(CardColor.JAUNE, (ArrayList<Carte>)p.cartesJouees.get(CardColor.JAUNE).clone());
		if(this.multicolor){
			this.cartesJouees.put(CardColor.MULTI, (ArrayList<Carte>)p.cartesJouees.get(CardColor.MULTI).clone());
		}
		this.dernierTour = p.dernierTour;
		this.dernierJoueur = p.dernierJoueur;
		this.partieFinie = p.partieFinie;
		this.joueurs=p.joueurs.clone();
		*/
		/*PartieView specific*/
		this.nb_points=p.calculerPoints();
		this.nb_indices=p.jetonIndice;
		this.nb_erreurs=p.jetonEclair;
	}
	public void majJoueur(JoueurIA j)
	{
		this.nb_jouables=j.compterCartesJouables();
		this.nb_defaussables=j.compterCartesDefaussables();
		this.info_jouables=j.compterInfosJouables();
		this.info_defaussables=j.compterInfosDefaussables();
		this.info_neutres=j.compterInfosNeutres();
	}
	
	public int get_nb_points() {
		return nb_points;
	}


	public void set_nb_points(int nb_points) {
		this.nb_points = nb_points;
	}
	public void incr_nb_points(){
		nb_points++;
	}
	public void decr_nb_points(){
		nb_points--;
	}
	public int get_nb_indices() {
		return nb_indices;
	}


	public void set_nb_indices(int nb_indices) {
		this.nb_indices = nb_indices;
	}
	public void incr_nb_indices(){
		nb_indices++;
	}
	public void decr_nb_indices(){
		nb_indices--;
	}

	public int get_nb_erreurs() {
		return nb_erreurs;
	}


	public void set_nb_erreurs(int nb_erreurs) {
		this.nb_erreurs = nb_erreurs;
	}
	public void incr_nb_erreurs(){
		nb_erreurs++;
	}
	public void decr_nb_erreurs(){
		nb_erreurs--;
	}

	public int get_nb_jouables() {
		return nb_jouables;
	}


	public void set_nb_jouables(int nb_jouables) {
		this.nb_jouables = nb_jouables;
	}
	public void incr_nb_jouables(){
		nb_jouables++;
	}
	public void decr_nb_jouables(){
		nb_jouables--;
	}

	public int get_nb_defaussables() {
		return nb_defaussables;
	}

	public void set_nb_defaussables(int nb_defaussables) {
		this.nb_defaussables = nb_defaussables;
	}
	public void incr_nb_defaussables(){
		nb_defaussables++;
	}
	public void decr_nb_defaussables(){
		nb_defaussables--;
	}

	public int get_info_jouables() {
		return info_jouables;
	}


	public void set_info_jouables(int info_jouables) {
		this.info_jouables = info_jouables;
	}
	public void incr_info_jouables(){
		info_jouables++;
	}
	public void decr_info_jouables(){
		info_jouables--;
	}

	public int get_info_defaussables() {
		return info_defaussables;
	}


	public void set_info_defaussables(int info_defaussables) {
		this.info_defaussables = info_defaussables;
	}
	public void incr_info_defaussables(){
		info_defaussables++;
	}
	public void decr_info_defaussables(){
		info_defaussables--;
	}

	public int get_info_neutres() {
		return info_neutres;
	}


	public void set_info_neutres(int info_neutres) {
		this.info_neutres = info_neutres;
	}
	public void incr_info_neutres(){
		info_defaussables++;
	}
	public void decr_info_neutres(){
		info_defaussables--;
	}

	public double valeur_heuristique(){
		double val=0;
		if(nb_erreurs==3)
			val=0;
		else
		{
			val= 	h_nbp*nb_points+h_nbi*nb_indices+h_nbe*nb_erreurs+h_nbj*nb_jouables+h_nbd*nb_defaussables
					+h_ij*info_jouables+h_id*info_defaussables+h_in*info_neutres;
		}
		return val;
	}
}
