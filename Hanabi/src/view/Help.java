package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Help extends JFrame {
	private static final long serialVersionUID = -4091180897492088549L;

	public Help(){
		super("Aide");
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel content = new JPanel();
		
		String titre1 = "Règles du jeu";
		String text1 = "<html>Hanabi est un jeu de cartes coopératif composé d’un ensemble de cartes et de jetons. Les cartes <br>"
				+ "représentent des feux d’artifice et les joueurs doivent collaborer afin de composer cinq feux d’artifice de couleur<br>"
				+ "différente. On dispose au début de la partie de 8 jetons «indice» qui représentent le nombre d’indices qu’il<br>"
				+ "est encore possible de donner, ainsi que 3 jetons «éclair» qui représentent le nombre d’erreur que l’on peut<br>"
				+ "faire avant de perdre la partie. Les jetons sont retournés lorsqu’un indice est donné ou une erreur commise.<br>"
				+ "Un jeton d’indice peut être remis sur son côté initial lorsqu’on défausse une carte.<br><br>"
				+ "Personne ne connaît ses propres cartes, mais chacun voit les cartes des autres. En utilisant les indices et<br>"
				+ "les cartes visibles, les joueurs doivent essayer de poser les feux d’artifice en suites croissantes de cartes de<br>"
				+ "même couleur.<br><br>"
				+ "Le score final est la somme des valeurs des dernières cartes posées pour chaque couleur.<br>"
				+ "Pour réussir ce défi, chaque joueur peut à son tour effectuer plusieurs actions différentes : donner un indice<br>"
				+ "à un autre joueur, défausser une carte ou jouer une carte.<br><br>"
				+ "Un indice consiste à indiquer quelles cartes sont d’une certaine couleur ou d’une certaine valeur dans la<br>"
				+ "main d’un joueur. Un indice négatif peut aussi être donné, indiquant qu’un joueur ne possède aucune carte<br>"
				+ "d’une certaine couleur ou valeur. A chaque indice donné, un jeton «indice» est utilisé et donc retourné.<br>"
				+ "Si le joueur choisit de défausser une carte, il regagne un jeton «indice». Dans le cas où un joueur tente sa<br>"
				+ "chance et joue une carte, si celle-ci est valide, elle est ajoutée dans la colonne de sa couleur. Cependant,<br>"
				+ "il est possible que la carte ne complète pas la suite de sa couleur de feu d’artifice, dans ce cas un jeton<br>"
				+ "«éclair» est retourné et la carte en question est mise dans la défausse. De plus, une fois que trois jetons<br>"
				+ "éclairs sont retournés, le jeu est fini et les joueurs ont perdu.</html>";
		content.add(new JLabel(titre1));
		content.add(new JLabel(text1));
		JScrollPane sp=new JScrollPane(content);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(20, 50));
		sp.setMinimumSize(new Dimension(20,50));
		sp.setAlignmentX(LEFT_ALIGNMENT);
		this.getContentPane().add(sp);
		this.setVisible(true);
	}
}
