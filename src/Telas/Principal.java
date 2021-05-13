package Telas;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Principal extends JFrame{
	private int R=255,G=215,B=0;
	private TelaLogin login;
	private TelaMenu menu;
	private TelaCadBem bem;
	private TelaUsuario user;
	
	
	public Principal(){
	   setTitle("Tela de Menu");
	 	setLayout(null);
		setSize(getMaximumSize());
		getContentPane().setBackground(new Color(R, G, B));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		ImageIcon imagemTituloJanela = new ImageIcon("Icones/icone.png");
		setIconImage(imagemTituloJanela.getImage());
		
		//ajusteCor( R, G, B);
		repaint();
	}
	public void ajusteCor(int r,int g,int b) {
		login.getContentPane().setBackground(new Color(r,g,b));
		menu.getContentPane().setBackground(new Color(r,g,b));
		bem.getContentPane().setBackground(new Color(r,g,b));
		user.getContentPane().setBackground(new Color(r,g,b));
		
	}
	
}
