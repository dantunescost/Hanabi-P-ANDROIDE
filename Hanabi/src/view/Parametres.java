package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import controller.EcouteBouton;
import controller.EcouteItem;
import controller.EcouteFocus;
import model.DummyJoueurIA;
import model.EpistemicJoueurIA;
import model.SemiDummyJoueurIA;
import model.HeuristicJoueurIA;

public class Parametres extends JFrame {
	private static final long serialVersionUID = -5591954412370299930L;
	private FenetrePartie fen = null;
	public JButton ok;
	public JComboBox<String> joueurIA1 = new JComboBox<String>();
	public JComboBox<String> joueurIA2 = new JComboBox<String>();
	public JComboBox<String> joueurIA3 = new JComboBox<String>();
	public JComboBox<String> joueurIA4 = new JComboBox<String>();
	public JSlider slider1 = new JSlider();
	public JSlider slider2 = new JSlider();
	public JSlider slider3 = new JSlider();
	public JSlider slider4 = new JSlider();
	public JComboBox<Integer> nbJoueurs= new JComboBox<Integer>();
	public JTextField pseudo = new JTextField();
	public JCheckBox multiColor = new JCheckBox("Jouer avec les cartes multicolor");
	
	public Parametres(){
		initParametres();
	}
	
	public Parametres(FenetrePartie fen){
		this.fen = fen;
		initParametres();
	}
	
	public void initParametres(){
		this.setTitle("Paramètres de jeu");
		this.setSize(700,520);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.ok= new JButton("Lancer la partie");
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		this.setContentPane(contentPane);

		//selection du nombre de joueurs
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.setBorder(BorderFactory.createTitledBorder("Nombre de joueurs : "));
		for(int i=2; i<6; i++){
			nbJoueurs.addItem(i);
		}
		nbJoueurs.setSelectedItem(3);
		nbJoueurs.addItemListener(new EcouteItem(this));
		JLabel nbJoueursLabel = new JLabel("Séléctionnez le nombre de joueurs ");
		p1.add(nbJoueursLabel);
		p1.add(nbJoueurs);
		
		//les joueurs
		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createTitledBorder("Les joueurs : "));
		p2.setLayout(new GridLayout(5,1));
		JLabel pseudonyme = new JLabel("Votre pseudonyme ");
		pseudo.setPreferredSize(new Dimension(150,25));
		pseudo.setForeground(Color.GRAY);
		pseudo.setText("Entrez un ID!");
		pseudo.addFocusListener(new EcouteFocus());
		JPanel p21[] = new JPanel[5];
		JLabel labelJoueurIA[] = new JLabel[4];
		for(int i=0;i<5;i++){
			p21[i] = new JPanel();
			p21[i].setLayout(new FlowLayout());
		}
		for(int i=0;i<4;i++){
			labelJoueurIA[i] = new JLabel("Choisissez l'IA numéro " + Integer.toString(i+1) + " ");
		}
		joueurIA1.addItem(DummyJoueurIA.nom+" (faible)");
		joueurIA1.addItem(SemiDummyJoueurIA.nom+" (moyen)");
		joueurIA1.addItem(HeuristicJoueurIA.nom+" (bon)");
		joueurIA1.addItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA1.setSelectedItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA1.addItemListener(new EcouteItem(this));

		joueurIA2.addItem(DummyJoueurIA.nom+" (faible)");
		joueurIA2.addItem(SemiDummyJoueurIA.nom+" (moyen)");
		joueurIA2.addItem(HeuristicJoueurIA.nom+" (bon)");
		joueurIA2.addItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA2.setSelectedItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA2.addItemListener(new EcouteItem(this));

		joueurIA3.addItem(DummyJoueurIA.nom+" (faible)");
		joueurIA3.addItem(SemiDummyJoueurIA.nom+" (moyen)");
		joueurIA3.addItem(HeuristicJoueurIA.nom+" (bon)");
		joueurIA3.addItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA3.setSelectedItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA3.addItemListener(new EcouteItem(this));

		joueurIA4.addItem(DummyJoueurIA.nom+" (faible)");
		joueurIA4.addItem(SemiDummyJoueurIA.nom+" (moyen)");
		joueurIA4.addItem(HeuristicJoueurIA.nom+" (bon)");
		joueurIA4.addItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA4.setSelectedItem(EpistemicJoueurIA.nom+" (très bon)");
		joueurIA4.addItemListener(new EcouteItem(this));

		slider1.setMinimum(0);
		slider1.setMaximum(100);
		slider1.setValue(38);
		slider2.setMinimum(0);
		slider2.setMaximum(100);
		slider2.setValue(38);
		slider3.setMinimum(0);
		slider3.setMaximum(100);
		slider3.setValue(38);
		slider4.setMinimum(0);
		slider4.setMaximum(100);
		slider4.setValue(38);
		slider1.setMinorTickSpacing(5);
		slider2.setMinorTickSpacing(5);
		slider3.setMinorTickSpacing(5);
		slider4.setMinorTickSpacing(5);
		slider1.setMajorTickSpacing(20);
		slider2.setMajorTickSpacing(20);
		slider3.setMajorTickSpacing(20);
		slider4.setMajorTickSpacing(20);
		slider1.setPaintTicks(true);
		slider2.setPaintTicks(true);
		slider3.setPaintTicks(true);
		slider4.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider2.setPaintLabels(true);
		slider3.setPaintLabels(true);
		slider4.setPaintLabels(true);

		joueurIA3.setEnabled(false);
		joueurIA4.setEnabled(false);
		slider3.setEnabled(false);
		slider4.setEnabled(false);
		p21[0].setLayout(new FlowLayout());
		p21[0].add(pseudonyme);
		p21[0].add(pseudo);
		p21[1].add(labelJoueurIA[0]);
		p21[1].add(joueurIA1);
		p21[1].add(slider1);
		p21[2].add(labelJoueurIA[1]);
		p21[2].add(joueurIA2);
		p21[2].add(slider2);
		p21[3].add(labelJoueurIA[2]);
		p21[3].add(joueurIA3);
		p21[3].add(slider3);
		p21[4].add(labelJoueurIA[3]);
		p21[4].add(joueurIA4);
		p21[4].add(slider4);
		for(int i=0;i<5;i++){
			p2.add(p21[i]);
		}
		
		//MultiColor
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		p3.setBorder(BorderFactory.createTitledBorder("Multicolor : "));
		p3.add(multiColor);
		
		JPanel p4 = new JPanel();
		this.ok.addActionListener(new EcouteBouton(this));
		p4.add(this.ok);
		contentPane.add(p1);
		contentPane.add(p2);
		contentPane.add(p3);
		contentPane.add(p4);
		this.setVisible(true);
	}
	
	public FenetrePartie getFen() {
		return fen;
	}

}
