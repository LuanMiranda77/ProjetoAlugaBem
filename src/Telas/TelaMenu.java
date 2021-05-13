package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.itextpdf.text.DocumentException;

import BotoesGeral.BotoesGeral;
import Classes.Bem;
import Classes.BensJaAlugados;
import Classes.Central;
import Classes.Locador;
import Classes.Persistencia;
import Classes.RelatorioHistoria;
import Classes.RelatorioPdf;
import Classes.TabelaAdmin;
import Ouvintes.OuvinteMenu;

public class TelaMenu extends Principal {
	 private int tamanho=1;
	 private int tmbem=1;
	 private boolean band;
	 private Persistencia pers= new Persistencia();
	 private Central cdi = pers.recuperarCentral("dados.xml");
	 private JLabel user;
	 
	
	public void setBand(boolean band) {
		this.band=band;
	}
	public void setUser(String user) {
		this.user.setText(user);
		
	}
	
	public TelaMenu(boolean band) {
		setLayout(null);
	   setSize(1400,750);
	   setLocationRelativeTo(null);
	   adicionarbutton(this,band);
	   adicionarlogo(this,band);
	   tabela(this);
	   setVisible(true);
	   repaint();
	   
	   

	}
	public void adicionarbutton(JFrame frame,boolean band) {
		OuvinteMenu ouvinte= new OuvinteMenu();
		
		JButton botbem=new BotoesGeral("<html>Cadastrar<html>",new ImageIcon("Icones/bemg.png"),tamanho,20, 100, 110);
		botbem.setToolTipText("Cadastro de Bens");
		botbem.setEnabled(band);
		add(botbem);
		botbem.addActionListener(ouvinte);
		
		JButton botaluga =new BotoesGeral("Aluga Já",new ImageIcon("Icones/ic_aluga.png"),tamanho+=100,20, 100, 110);
		botaluga.setToolTipText("Itens pra alugar");
		botaluga.addActionListener(ouvinte);
		if(cdi.getUserlogado().equals(cdi.getAdmin().getLogin())) {
		botaluga.setEnabled(false);	
		}
		frame.add(botaluga);
		
		JButton botloca =new BotoesGeral("Alugados",new ImageIcon("Icones/ic_carteira.png"),tamanho+=100,20, 100, 110);
		botloca.setToolTipText("Bens Alugados");
		if(cdi.getUserlogado().equals(cdi.getAdmin().getLogin())) {
			botloca.setEnabled(false);	
			}
		botloca.addActionListener(ouvinte);
		frame.add(botloca);
		
		JButton botemail =new BotoesGeral("Email",new ImageIcon("Icones/email.png"),tamanho+=100,20, 100, 110);
		botemail.setToolTipText("Enviar Email");
		botemail.addActionListener(ouvinte);
		frame.add(botemail);
		
		BotoesGeral botrelatorio =new BotoesGeral("Relatorio",new ImageIcon("Icones/ic_relatrio.png"),tamanho+=100,20, 100, 110);
		botrelatorio.setToolTipText("Relatorios");
		botrelatorio.setEnabled(band);
		frame.add(botrelatorio);
		botrelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String []op= {"GERA RELATORIO DE ATRASO", "GERAR HISTORICO DE BEM"};
				int opcao=JOptionPane.showOptionDialog(null, "Esolha o tipo de ralatorio?", "Aviso", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
				if(opcao==0) {
					RelatorioPdf re =new RelatorioPdf();
					try {
						re.gerarRelatorio();
					} catch (DocumentException e1) {
						JOptionPane.showMessageDialog(null, "Documento Invalido", "Alerta", JOptionPane.ERROR_MESSAGE);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Erro data relátorio invalido", "Alerta", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro Arquivo aberto", "Alerta", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(opcao==1) {
					RelatorioHistoria hi =new RelatorioHistoria();
					String[]lista=new String[cdi.getListaAlugados().size()];
					int cont=0,cont1=1;
					for(BensJaAlugados a: cdi.getListaAlugados()) {
						if(!a.getBens().equals(lista[cont1])) {
							lista[cont]=a.getBens();
							cont++;
							if(lista.length!=cdi.getListaAlugados().size()) {
							     cont1++;
							}
						}
					}
					String nome=(String) JOptionPane.showInputDialog(null, "Escolha o item pra gerar o historico", "aviso",JOptionPane.QUESTION_MESSAGE,null,lista,lista[0] );
					try {
						if(!nome.equals("")) {
						hi.gerarHistorico(nome);
						}
					} catch (DocumentException e1) {
						JOptionPane.showMessageDialog(null, "Documento Invalido", "Alerta", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro Arquivo aberto", "Alerta", JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException e1){
						JOptionPane.showMessageDialog(null, "Operação cancelada", "Alerta", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
			}
		});
				
		
		BotoesGeral botcontato =new BotoesGeral("Contato",new ImageIcon("Icones/ic_fone.png"),tamanho+=100,20, 100, 110);
		botcontato.setToolTipText("Contatos");
		botcontato.addActionListener(ouvinte);
		frame.add(botcontato);
	   
		
		JButton botconfig =new BotoesGeral("Configurar",new ImageIcon("Icones/ic_confg.png"),tamanho+=100,20, 100, 110);
		botconfig.setFont(new Font("Times New Roman",Font.ITALIC,14));
		botconfig.setToolTipText("Configuração");
		botconfig.addActionListener(ouvinte);
		frame.add(botconfig);
		
		JButton botsair =new BotoesGeral("Sair",new ImageIcon("Icones/ic_deslg.png"),tamanho+=100,20, 100, 110);
		botsair.setToolTipText("Sair");
		frame.add(botsair);	
		botsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaLogin();
				
			}
		});
	}
	public void adicionarlogo(JFrame frame, boolean band) {
		String nome="";
		
		for (Locador n: cdi.getListaLocador())
		if (n.getLogin().equals(cdi.getUserlogado())) {
			nome=n.getNome();
		}
		else {
			nome=cdi.getAdmin().getNome();
	    }
		
		
		 Date d = new Date();
	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
		JLabel logo=  new JLabel(new ImageIcon("Icones/logo.png"), JLabel.LEFT);
		logo.setBounds(10,200,800,600);
		frame.add(logo);
		
		user=  new JLabel("Usuario Logado: "+nome);
		user.setBounds(850,500,500,200);
		user.setFont(new Font("Arial",Font.BOLD,25));
		frame.add(user);
		
		JLabel data=  new JLabel("Data: "+hoje);
		data.setBounds(900,580,400,100);
		data.setFont(new Font("Arial",Font.BOLD,25));
		frame.add(data);
		
		JLabel tb=  new JLabel("LISTA DE BEM ALUGADO POR CLIENTES");
		tb.setBounds(850,85,600,100);
		tb.setFont(new Font("Arial",Font.BOLD,20));
		
		frame.add(tb);
		float total1=0;
		for(BensJaAlugados a:cdi.getListaAlugados()) {
			if(a.getStatus().equals("A devolver")) {
				total1+=a.getPreco();
			}
			
			
		}
		
		JLabel total=  new JLabel("TOTAL DE BENS ALUGADOS----> R$ "+total1+"0");
		total.setBounds(850,520,500,100);
		total.setFont(new Font("Arial",Font.BOLD,19));
		total.setVisible(band);
		frame.add(total);
		
	}
	public void tabela(JFrame janela) {
		TabelaAdmin tabela = new TabelaAdmin();
		tabela.adicionarTabela(janela);
		//tabela.revalidate();
		tabela.setVisible(band);
		janela.add(tabela);	
		tabela.repaint();
	}
	public static void main(String[] args) {
		new TelaMenu(true);
	}
}
