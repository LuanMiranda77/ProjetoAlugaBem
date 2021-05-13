package Telas;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Classes.Bem;
import Classes.Central;
import Classes.Persistencia;
import Classes.TabelaBens;
import Fontes.FontesGeral;

public class TelaTabela extends Principal{
	
	private TabelaBens tabela;
	
	public TelaTabela() {
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
		JLabel label = new FontesGeral("Lista Geral de Bem pra locação", 50, -80, 400, 40);
		label.setFont(new Font("Times New Roman", Font.ITALIC, 28));
		JLabel logo= new JLabel(new ImageIcon("Icones/logopequena.png"));
		logo.setBounds(400, -50, 250, 250);
		add(logo);
		add(label);
		
	}
	public void tabela(JFrame janela) {
		TabelaBens tabela = new TabelaBens();
		tabela.adicionarJTable(janela);
		
		janela.add(tabela);
		tabela.repaint();
	}
}
