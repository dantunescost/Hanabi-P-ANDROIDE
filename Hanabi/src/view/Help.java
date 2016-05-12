package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Help extends JFrame {
	private static final long serialVersionUID = -4091180897492088549L;
	public static String R = System.getProperty("user.dir");

	public Help(){
		super("Aide");
		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
		this.setSize(800, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		Font f1=new Font("Century",Font.BOLD+Font.ITALIC,24);
		Font f2=new Font("Century",Font.PLAIN,16);
		
		String titre1 = "Règles du jeu";
		String text1 = "<html>Hanabi est un jeu de cartes coopératif composé d’un ensemble de cartes et de jetons. Les cartes <br>"
				+ "représentent des feux d’artifice et les joueurs doivent collaborer afin de composer cinq feux<br>d’artifice de couleur "
				+ "différente. On dispose au début de la partie de 8 jetons «indice» qui<br>représentent le nombre d’indices qu’il "
				+ "est encore possible de donner, ainsi que 3 jetons «éclair» qui <br>représentent le nombre d’erreur que l’on peut "
				+ "faire avant de perdre la partie. Les jetons sont <br>retournés lorsqu’un indice est donné ou une erreur commise. "
				+ "Un jeton d’indice peut être remis sur <br>son côté initial lorsqu’on défausse une carte.<br><br>"
				+ "Personne ne connaît ses propres cartes, mais chacun voit les cartes des autres. En utilisant les <br>indices et "
				+ "les cartes visibles, les joueurs doivent essayer de poser les feux d’artifice en suites <br>croissantes de cartes de "
				+ "même couleur.<br><br>"
				+ "Le score final est la somme des valeurs des dernières cartes posées pour chaque couleur.<br>"
				+ "Pour réussir ce défi, chaque joueur peut à son tour effectuer plusieurs actions différentes : <br>donner un indice "
				+ "à un autre joueur, défausser une carte ou jouer une carte.<br><br>"
				+ "Un indice consiste à indiquer quelles cartes sont d’une certaine couleur ou d’une certaine valeur <br>dans la "
				+ "main d’un joueur. Un indice négatif peut aussi être donné, indiquant qu’un joueur ne <br>possède aucune carte "
				+ "d’une certaine couleur ou valeur. A chaque indice donné, un jeton «indice» <br>est utilisé et donc retourné. "
				+ "Si le joueur choisit de défausser une carte, il regagne un jeton «indice». <br>Dans le cas où un joueur tente sa "
				+ "chance et joue une carte, si celle-ci est valide, elle est ajoutée dans <br>la colonne de sa couleur. Cependant, "
				+ "il est possible que la carte ne complète pas la suite de sa <br>couleur de feu d’artifice, dans ce cas un jeton "
				+ "«éclair» est retourné et la carte en question est mise <br>dans la défausse. De plus, une fois que trois jetons "
				+ "éclairs sont retournés, le jeu est fini et les <br>joueurs ont perdu.</html>";
		JLabel l1 = new JLabel(titre1);
		l1.setFont(f1);
		l1.setForeground(Color.blue);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		Border border = l1.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		l1.setBorder(new CompoundBorder(border, margin));
		content.add(l1);
		JLabel l2 = new JLabel(text1);
		l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		l2.setFont(f2);
		content.add(l2);
		JLabel im1 = new JLabel(new ImageIcon(R+"rsz_partie.png"));
		border = im1.getBorder();
		margin = new EmptyBorder(50,10,50,10);
		im1.setBorder(new CompoundBorder(border, margin));		
		im1.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(im1);
		
		JLabel titre2 = new JLabel("Comment jouer?");
		titre2.setFont(f1);
		titre2.setForeground(Color.BLUE);
		titre2.setAlignmentX(Component.CENTER_ALIGNMENT);
		border = titre2.getBorder();
		margin = new EmptyBorder(0,10,20,10);
		titre2.setBorder(new CompoundBorder(border, margin));
		content.add(titre2);
		
		String text2 = "<html>Dans la fenêtre de jeu, trois boutons vous permettent de jouer : <br><br>"
				+ "<b>Indice:</b> ce bouton vous permet de donner un indice à une autre joueur. <br>"
				+ "Pour cela appuyez sur ce bouton, selectionnez ensuite le joueur auquel vous voulez<br>"
				+ "donner l'indice en cliquant sur ses cartes et choisissez finalement l'indice enbas à <br>"
				+ "gauche de la fenêtre.<br><br>"
				+ "<b>Jouer carte:</b> ce bouton vous permet de jouer une carte, pour cela appuyez sur <br>"
				+ "le bouton et ensuite sur la carte que vous voulez jouer. <br><br>"
				+ "<b>Défausser:</b> ce bouton vous permet de défausser une carte. Pour cela appuyez sur<br>"
				+ "le bouton et choisissez ensuite la carte que vous voulez défausser.<br><br>"
				+ "Vous pouvez aussi à tout moment vérifier les cartes déjà défaussées. Pour cela appuyez<br>"
				+ "sur la carte qui s'affiche sur la gauche de la fenêtre. Pour revenir à la partie il vous<br>"
				+ "suiffira de cliquer sur n'importe quelle partie de la fenêtre.<br><br>"
				+ "La barre de menu vous permet aussi d'enregistrer ou charger des parties ainsi d'en apprendre<br>"
				+ "plus sur ce logiciel";
		JLabel l3 = new JLabel(text2);
		l3.setFont(f2);
		l3.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(l3);
		
		JScrollPane sp=new JScrollPane(content);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setAutoscrolls(true);
		sp.setPreferredSize(new Dimension(20, 50));
		sp.setMinimumSize(new Dimension(20,50));
		sp.setAlignmentX(LEFT_ALIGNMENT);
		this.getContentPane().add(sp);
		this.setVisible(true);
	}
}
