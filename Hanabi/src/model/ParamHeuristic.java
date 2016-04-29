package model;

public class ParamHeuristic {
	private double h_nbp;
	private double h_nbi;
	private double h_nbe;
	private double h_nbj;
	private double h_nbd;
	private double h_ij;
	private double h_id;
	private double h_in;

	public ParamHeuristic()
	{
		this.h_nbp=50;
		this.h_nbi=5;
		this.h_nbe=5;
		this.h_nbj=10;
		this.h_nbd=3;
		this.h_ij=15;
		this.h_id=3;
		this.h_in=2;
	}
	
	public ParamHeuristic(double h_nbp, double h_nbi, double h_nbe, double h_nbj, double h_nbd, double h_ij, double h_id, double h_in)
	{
		this.h_nbp=h_nbp;
		this.h_nbi=h_nbi;
		this.h_nbe=h_nbe;
		this.h_nbj=h_nbj;
		this.h_nbd=h_nbd;
		this.h_ij=h_ij;
		this.h_id=h_id;
		this.h_in=h_in;
	}
	
	public double get_h_nbp() {
		return h_nbp;
	}
	public void set_h_nbp(double h_nbp) {
		this.h_nbp = h_nbp;
	}
	public double get_h_nbi() {
		return h_nbi;
	}
	public void set_h_nbi(double h_nbi) {
		this.h_nbi = h_nbi;
	}
	public double get_h_nbe() {
		return h_nbe;
	}
	public void set_h_nbe(double h_nbe) {
		this.h_nbe = h_nbe;
	}
	public double get_h_nbj() {
		return h_nbj;
	}
	public void set_h_nbj(double h_nbj) {
		this.h_nbj = h_nbj;
	}
	public double get_h_nbd() {
		return h_nbd;
	}
	public void set_h_nbd(double h_nbd) {
		this.h_nbd = h_nbd;
	}
	public double get_h_ij() {
		return h_ij;
	}
	public void set_h_ij(double h_ij) {
		this.h_ij = h_ij;
	}
	public double get_h_id() {
		return h_id;
	}
	public void set_h_id(double h_id) {
		this.h_id = h_id;
	}
	public double get_h_in() {
		return h_in;
	}
	public void set_h_in(double h_in) {
		this.h_in = h_in;
	}
}
