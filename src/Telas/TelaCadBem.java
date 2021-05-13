package Telas;

import java.awt.Font;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import com.itextpdf.text.log.SysoCounter;

import BotoesGeral.BotoesGeral;
import Classes.Bem;
import Classes.Central;
import Classes.Locador;
import Classes.Persistencia;
import Classes.TabelaBens;
import Execoes.CampoVasioException;
import Execoes.ValorNegativo;
import Fontes.CampoGeral;
import Fontes.FonteBem;
import Fontes.FontesGeral;
import nu.xom.Text;

public class TelaCadBem  extends Principal{
	
	//## atributos
	private JTextField boxnome;
	private JTextArea boxdisc;
	private JTextField boxquant;
	private JTextField preco;
	private JComboBox  boxcond;
	private JComboBox  boxstatus;
	private JTextField boxprazo;
	private TelaCadBem janela;
	private TabelaBens tabela;
	private String nomebem; 
	private int cod=0, linha;	
	private int index;
	
	
	private int tamanho=2;
	
	
	public TelaCadBem() {
		
		setTitle("Tela de Bens");
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 50, 1100, 650);
		botao();
		label();
		dados();
		tabela(this);
		setVisible(true);
		repaint();
	}
	private class Ouvir implements ActionListener{		
		
		public void actionPerformed(ActionEvent event) {
			String nome = event.getActionCommand();
			Persistencia pers= new Persistencia();
			Central cdi = pers.recuperarCentral("dados.xml");
			String n="";
		 
			
			switch (nome) {		
			case"<HTML>Cadastro<HTML>":
				if(boxnome.getText().equals("")||boxdisc.getText().equals("")||boxquant.getText().equals("  ")||
						preco.getText().equals("  .  ")||boxprazo.getText().equals("  ")) {
							JOptionPane.showMessageDialog(null, "Campo vazio ", "Aviso", JOptionPane.ERROR_MESSAGE);
						}
				else {
				for(Bem e:cdi.getListaBens()) {
					cod=(e.getCod());
				}
				cod++;
				String nome1=boxnome.getText();
				nome1=nome1.toUpperCase();
				String disc=boxdisc.getText();
				disc=disc.toUpperCase();
				int quant=Integer.parseInt(boxquant.getText()); 
				float p = Float.parseFloat(preco.getText());
				String status =(String) boxstatus.getSelectedItem();
				String cond=(String)boxcond.getSelectedItem();
				int prazo=Integer.parseInt(boxprazo.getText());
				
				Bem novo = new Bem(cod,nome1, disc, quant, p, status, cond, prazo);
			
			try {
				cdi.cadastroBens(novo);
				pers.salvarCentral(cdi, "dados.xml");		
				JOptionPane.showMessageDialog(null, "Bem cadastrado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				tabela.adicionarLinha(novo);
				boxnome.setText("");
				boxdisc.setText("");
				boxquant.setText("");
				preco.setText("");
				boxprazo.setText("");
				boxnome.requestFocus();
				} catch (CampoVasioException e) {
					  JOptionPane.showMessageDialog(null, "Campo vasio", "Aviso", JOptionPane.ERROR_MESSAGE);
					} catch (ValorNegativo e) {
					JOptionPane.showMessageDialog(null, "valor Invalido", "Aviso", JOptionPane.ERROR_MESSAGE);			    
					}
				}
			    break;
			    
			case"Editar":
				if(boxnome.getText().equals("")||boxdisc.getText().equals("")||boxquant.getText().equals("  ")||
				preco.getText().equals("  .  ")||boxprazo.getText().equals("  ")) {
					JOptionPane.showMessageDialog(null, "Campos vazios, click no botao pesquisar pra editar ", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
				else {
				for(Bem u: cdi.getListaBens() ) {
				   	if(u.getCod()==index) {
				   		u.setNome(boxnome.getText().toUpperCase());
				   		u.setDescricao(boxdisc.getText().toUpperCase());
				   		String[] s=boxquant.getText().split("");
				   		u.setQuantidade(Integer.parseInt(s[0]));
				   		u.setStatus((String) boxstatus.getSelectedItem());
				   		u.setValor(Float.parseFloat(preco.getText()));
				   		u.setCondicao((String) (boxcond.getSelectedItem()));
				     	}
				  }
				    pers.salvarCentral(cdi, "dados.xml");
				   	JOptionPane.showMessageDialog(null, "Bem editado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
			case"<HTML>Pesquisar<HTML>":
				try {
				     n= JOptionPane.showInputDialog(null, "Digite o nome do bem pra alteração", "editar",
						JOptionPane.INFORMATION_MESSAGE);
				     n=n.toUpperCase();
				}catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Cancelado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				
				boolean band=false;
				for(Bem bem: cdi.getListaBens()) {
				   	if(n.equals(bem.getNome())) {
				   		index=bem.getCod();
				   		boxnome.setText(bem.getNome());
				   		boxdisc.setText(bem.getDescricao());
				   		boxquant.setText(Integer.toString(bem.getQuantidade()));
				   		boxstatus.setSelectedItem(bem.getStatus());
				   		boxcond.setSelectedItem(bem.getCondicao());
				   		boxprazo.setText(Integer.toString(bem.getPrazo()));
				   		preco.setText(""+bem.getValor());
				   		band=true;
				   		if(!bem.getStatus().equals("disponivel")) {
					   		boxquant.setEnabled(false);
					   	}
					   	else {
					   		boxquant.setEnabled(true);
					   	}
				   	}
				   
				}
				if(band==false) {
				JOptionPane.showMessageDialog(null, "Bem não cadastrado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
				break;
				
				
				
			case"Excluir":
				boolean band1 = false;
				int linha1 =tabela.selecionarLinha();
				System.out.println(linha1);
				      for (Bem bem:cdi.getListaBens()) {
				           if(bem.getCod()==linha1 && !bem.getStatus().equals("disponivel")) {
				        	   JOptionPane.showMessageDialog(null, "Bem não pode ser excluido por que esta alugado", "Aviso", JOptionPane.ERROR_MESSAGE);
				        	   band1=true;
				        	   break;
				          }
				        }
				      if(band1==false) {
				        if(cdi.getListaBens().size()==1) {
				        	tabela.excluirLinha(linha1);
							cdi.getListaBens().remove(linha1);
							pers.salvarCentral(cdi, "dados.xml");
							JOptionPane.showMessageDialog(null, "Bem excluido  com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				        }
				        else if(linha1<=0) {
						    JOptionPane.showMessageDialog(null, "Selecione a linha pra exclusão", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
						tabela.excluirLinha(linha1);
						cdi.getListaBens().remove(linha1);
						pers.salvarCentral(cdi, "dados.xml");
						JOptionPane.showMessageDialog(null, "Bem excluido  com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
				      }
				break;
			}
		}
	}

	public void botao() {
	
		
		BotoesGeral botcad = new  BotoesGeral("<HTML>Cadastro<HTML>",new ImageIcon("Icones/bem.png"),tamanho,20, 80, 90);
	
		botcad.setToolTipText("Cadastar de bem");
		botcad.addActionListener(new Ouvir());
		BotoesGeral botpesquisa = new  BotoesGeral("<HTML>Pesquisar<HTML>",new ImageIcon("Icones/pesquisar.png"),tamanho+=80,20,80, 90);
		botpesquisa.addActionListener(new Ouvir());
		BotoesGeral boteditar = new  BotoesGeral("Editar",new ImageIcon("Icones/editar.png"),tamanho+=80,20,80, 90);
	
		boteditar.addActionListener(new Ouvir());
		BotoesGeral botexcluir = new  BotoesGeral("Excluir",new ImageIcon("Icones/excluir.png"),tamanho+=80,20,80, 90);
		
		botexcluir.addActionListener(new Ouvir());
		add(botexcluir);
		add(botpesquisa);
		add(botcad);
		add(boteditar);
		
	//botcad.addActionListener(new ActionListener() {
	}
	public void label() {
		add(new FontesGeral("Nome",25,20,100,30));
		add(new FontesGeral("Discrição",25,90,100,30));
		add(new FontesGeral("Quantidade disponivel",25,220,200,30));
		add(new FontesGeral("Preço",200,220,50,30));
		add(new FontesGeral("Condição",25,290,100,30));
		add(new FontesGeral("Status",205,290,50,30));
		add(new FontesGeral("Prazo em dias",25,350,120,30));
		add(new FontesGeral("Lista de Bens pra Locação",600,60,300,30));

		JLabel logo = new JLabel("",new ImageIcon("Icones/logopequena.png"),JLabel.LEFT);
		logo.setBounds(800, -20, 260, 200);
		add(logo);
		

	}
	
	
	public void dados() {
			String[]lista= {"novo","usado"};
			String[]lista2= {"disponivel","indisponivel","parcialmente"};
			
			boxnome =new JTextField();
			boxnome.setBounds(20,150,200,30);
			   add(boxnome);
			
			boxdisc =new JTextArea();
			boxdisc.setLineWrap(true);
			boxdisc.setWrapStyleWord(true);
			boxdisc.setBounds(20,220,350,90);
			   add(boxdisc);
			    
		    MaskFormatter q;
			try {
				q = new MaskFormatter("##");
				boxquant =new JFormattedTextField(q);
				boxquant.setBounds(20,350,100,30);
				add(boxquant);
				
				boxprazo =new JFormattedTextField(q);
				boxprazo.setBounds(20,480,60,30);
				add(boxprazo);
				
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,"Só digita numero");
			}
			
			
			
			MaskFormatter p;
			try {
				p = new MaskFormatter("##.##");
				p.setValidCharacters("0123456789.");
				preco = new JFormattedTextField(p);
				preco.setBounds(200, 350, 80, 30);
				add(preco);
				
			} catch (ParseException e) {
				
			}
			
				
				
			boxcond =new JComboBox(lista);
			boxcond.setBounds(20,420,100,30);
			    add(boxcond);
			
			boxstatus =new JComboBox(lista2);
			boxstatus.setBounds(200,420,100,30);
			boxstatus.setEnabled(false);
			add(boxstatus);
			
			
			  
	}
	public void tabela(JFrame janela) {
		tabela = new TabelaBens();
		tabela.adicionarJTable(janela);
		tabela.revalidate();
		janela.add(tabela);
		
	}
	public static void main(String[] args) {
		new TelaCadBem();
	}
}
