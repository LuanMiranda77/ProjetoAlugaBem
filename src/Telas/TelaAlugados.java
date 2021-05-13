package Telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import BotoesGeral.BotoesGeral;
import Classes.Bem;
import Classes.BensJaAlugados;
import Classes.CalcularData;
import Classes.Central;
import Classes.GeradorBoleto;
import Classes.Locador;
import Classes.Persistencia;
import Classes.TabelaAlugados;
import Classes.TabelaBens;
import Execoes.CampoVasioException;
import Execoes.ValorNegativo;

public class TelaAlugados extends Principal {
	
	private TabelaAlugados tabela = new TabelaAlugados();;
	private int index;
	private String end="";
	private String CPF="";
	private  String us ="";
	private int cod;
	private int q=1;
	private boolean band=false;
	private float valor=0;
	private GeradorBoleto boleto = new GeradorBoleto();
	
	public TelaAlugados() {
		setTitle("Lista de Bens Alugados");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 50, 1100, 650);
		adicionar();
		tabela(this);
		setVisible(true);
		repaint();
	}
private class Ouvir implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			String nome = event.getActionCommand();
			Persistencia pers= new Persistencia();
			Central cdi = pers.recuperarCentral("dados.xml");
			String opcoes[]= {"Pesquisar por nome","Pesquisar por valor"};
			String op="";
	
			if(nome.equals("<HTML>Pesquisar<HTML>")) {
			try {	
				Object opcao=JOptionPane.showInputDialog(null, "Escolha o tipo de  Pesquisa","Tela de pesquisa", JOptionPane.QUESTION_MESSAGE
						, null, opcoes, opcoes[0]);
				op=(String)opcao;
				
				if(op.equals("Pesquisar por nome")) {
					try {
					String nome1=JOptionPane.showInputDialog("Digite a nome a ser procurado");
					 nome1=nome1.toUpperCase();
					 tabela.limparTabela();
				     tabela.filtroLinha(nome1);
					 tabela.repaint();
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null,"Cancelado");
					}
				}
				else if(op.equals("Pesquisar por valor")){
				try {	
					String valor=JOptionPane.showInputDialog("Digite a valor a ser procurado");
					tabela.limparTabela();
					tabela.filtroValor(Float.parseFloat(valor));
					tabela.repaint();
				}catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Cancelado");
				}
					
				}
			}catch(NullPointerException e) {
				JOptionPane.showMessageDialog(null,"Cancelado");
			}
				
			 }
			 if(nome.equals("<HTML>Devolver<HTML>")) {
				 try {
					 //int index=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite codigo do item pra Devolução"));
	                 int index=tabela.getSelectedRow();
					 cdi.devolverBem(index);
				 }catch(NumberFormatException e) {
					 JOptionPane.showMessageDialog(null,"Cancelado");
				 }
			 } 
		}
}
				  
	public void tabela(JFrame janela) {
		tabela.adicionarTabela(janela);
		janela.add(tabela);
		tabela.repaint();
		
	}
	public void adicionar() {
		JLabel logo = new JLabel("",new ImageIcon("Icones/logopequena.png"),JLabel.LEFT);
		logo.setBounds(800, -20, 260, 200);
		add(logo);
		
		String[]lista2= {"Devolvido","A devolver"};
		JComboBox box = new JComboBox(lista2);
		box.setBounds(200,50,100, 40);
		add(box);
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String op=(String) box.getSelectedItem();
				tabela.limparTabela();
				tabela.filtroLinha(op);
				tabela.repaint();
				
			}
		});
		
		JButton devolver = new  BotoesGeral("<HTML>Devolver<HTML>",new ImageIcon("Icones/bem.png"),10,20, 80, 90);
		devolver.setToolTipText("Devolver bem alugado");
		add(devolver);
		devolver.addActionListener(new Ouvir());
		BotoesGeral pesquisa = new  BotoesGeral("<HTML>Pesquisar<HTML>",new ImageIcon("Icones/pesquisar.png"),85,20,80, 90);
		add(pesquisa);
		pesquisa.addActionListener(new Ouvir());
		
		devolver.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				Persistencia pers= new Persistencia();
				Central cdi = pers.recuperarCentral("dados.xml");
				
				
				
			}
			
		});
	}
}
