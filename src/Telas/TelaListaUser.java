package Telas;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Classes.TabelaBens;
import Classes.TabelaUser;
import Fontes.FontesGeral;

public class TelaListaUser extends TelaTabela{
	
private TabelaUser tabela;
	
	public TelaListaUser() {
		setTitle("Lista de bens");
		setSize(700,500);
		setLocationRelativeTo(null);
		tabela(this);
		listaBem();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		repaint();
		
	}
	public void listaBem() {
		JLabel label = new FontesGeral("Lista Geral de Usuario", 50, -80, 400, 40);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		JLabel logo= new JLabel(new ImageIcon("Icones/logopequena.png"));
		logo.setBounds(400, -50, 250, 250);
		add(logo);
		add(label);
		
	}
	public void tabela(JFrame janela) {
		TabelaUser tabela = new TabelaUser();
		tabela.TabelaUser(janela);
		janela.add(tabela);
		tabela.repaint();
	}
}
