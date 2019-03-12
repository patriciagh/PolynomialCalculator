package frontend;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import backend.*;
public class Gui {
	private int salvat = 0;
	
	private JFrame frame = new JFrame("Calculator de polinoame");
	private JLabel labelP1 = new JLabel("P1 : ");
	private JLabel labelP2 = new JLabel("P2 : ");
	private JLabel labelRez = new JLabel("R : ");
	private JLabel afisare1 = new JLabel();
	private JLabel afisare2 = new JLabel();
	private JLabel eroare = new JLabel();
	
	private JTextField textP1 = new JTextField();
	private JTextField textP2 = new JTextField();
	private JTextField rezultat = new JTextField();
	
	private JButton adunare = new JButton(" + ");
	private JButton scadere = new JButton(" - ");
	private JButton inmultire = new JButton(" * ");
	private JButton derivare1 = new JButton("Derivare P1");
	private JButton derivare2 = new JButton("Derivare P2");
	private JButton integrare1 = new JButton("Integrare P1");
	private JButton integrare2 = new JButton("Integrare P2");
	private JButton stergere = new JButton("Stergere");
	private JButton salvare = new JButton("Salvare");
	
	private ArrayList<Monom> monoame1=new ArrayList<Monom>();
	private ArrayList<Monom> monoame2=new ArrayList<Monom>();
	
	private Polinom p1=new Polinom(0,monoame1);
	private Polinom p2=new Polinom(0,monoame2);
	
	public Gui()
	{
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setSize(700,650);
		textP1.setFont(new Font("Arial",Font.PLAIN,19));
		labelP1.setBounds(30,40,50,50);		
		textP1.setBounds(50,50,570,50);
		labelP2.setBounds(30,100,50,50);
		textP2.setBounds(50,110,570,50);
		textP2.setFont(new Font("Arial",Font.PLAIN,19));		
		rezultat.setBounds(50,217,570,50);
		rezultat.setFont(new Font("Arial",Font.PLAIN,19));
		labelRez.setBounds(30,210,50,50);
		
		afisare1.setBounds(230, 280, 300, 35);
		afisare1.setFont(new Font("Arial",Font.PLAIN,19));
		afisare2.setBounds(230, 340, 300, 35);
		afisare2.setFont(new Font("Arial",Font.PLAIN,19));
		eroare.setBounds(240, 450, 350, 35);
		eroare.setFont(new Font("Arial",Font.BOLD,15));
		
		adunare.setBounds(50,280,50, 35);
		scadere.setBounds(110,280,50,35);
		inmultire.setBounds(170, 280, 50, 35);
		derivare1.setBounds(50, 330, 170, 35);
		derivare2.setBounds(50, 380, 170, 35);
		integrare1.setBounds(50, 430, 170, 35);
		integrare2.setBounds(50, 480, 170, 35);
		stergere.setBounds(50,530,170,35);	
		salvare.setBounds(50, 170, 200, 35);
		
		salvare.addActionListener(new ActionListener()
			{
				//@Override
				public void actionPerformed(ActionEvent e)
				{
					p1=Regex.citirePolinom(textP1.getText());
					p2=Regex.citirePolinom(textP2.getText());
					p1.aranjarePolinom(p1);
					p2.aranjarePolinom(p2);
					p1.ordonarePolinom(p1);
					p2.ordonarePolinom(p2);
					if(p1.verificareNull()==1 || p2.verificareNull()==1)
					{
						eroare.setText("Minim unul dintre polinoame e introdus gresit !");
						afisare1.setText("");
						afisare2.setText("");
					}
					else
					{
						eroare.setText("");
						afisare1.setText(p1.getPolinom());
						afisare2.setText(p2.getPolinom());
						salvat=1;
					}
					
				}
			}
		);
		adunare.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				if(salvat==1) {
				Polinom suma=p1.adunare(p2);
				suma.ordonarePolinom(suma);
				if(suma.verificareNull()==1)
				{
					suma.adaugareMonom(new Monom(0,0,0));
				}
				rezultat.setText(suma.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
			}else eroare.setText("Introduceti datele");
			}
		}
		);
		scadere.addActionListener(new ActionListener()
			{
			//@Override
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				Polinom diferenta=p1.scadere(p2);
				diferenta.ordonarePolinom(diferenta);
				rezultat.setText(diferenta.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
				}else eroare.setText("Introduceti datele");
			}
		}
		);
		inmultire.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				Polinom inmultire=p1.inmultire(p2);
				inmultire.ordonarePolinom(inmultire);
				rezultat.setText(inmultire.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
				}else eroare.setText("Introduceti datele");
			}
		}
		);
		derivare1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				p1.derivare();
				rezultat.setText(p1.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
				}else eroare.setText("Introduceti datele");
			}
		}
		);
		derivare2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				p2.derivare();
				rezultat.setText(p2.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
				}else eroare.setText("Introduceti datele");
			}
		}
		);
		integrare1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				p1.integrare();
				rezultat.setText(p1.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
			}else eroare.setText("Introduceti datele");
			}
		}
		);
		integrare2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(salvat==1) {
				p2.integrare();
				rezultat.setText(p2.getPolinom());
				afisare1.setText(p1.getPolinom());
				afisare2.setText(p2.getPolinom());
			}else eroare.setText("Introduceti datele");
			}
		}
		);
		stergere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				for(Monom m1 : p1.getMonoame())
				{
					m1.setCoeficient(0);
					m1.setPutere(0);
				}
				for(Monom m2 : p2.getMonoame())
				{
					m2.setCoeficient(0);
					m2.setPutere(0);
				}
				textP1.setText("");
				textP2.setText("");
				rezultat.setText("");
				afisare1.setText("");
				afisare2.setText("");
			}
		}
		);
		frame.add(labelP1);
		frame.add(labelP2);
		frame.add(labelRez);
		frame.add(afisare1);
		frame.add(afisare2);
		frame.add(eroare);
		frame.add(textP1);
		frame.add(textP2);
		frame.add(rezultat);
		frame.add(adunare);
		frame.add(scadere);
		frame.add(inmultire);
		frame.add(derivare1);
		frame.add(derivare2);
		frame.add(integrare1);
		frame.add(integrare2);
		frame.add(stergere);
		frame.add(salvare);
		frame.setVisible(true);
		
	}
	

}
