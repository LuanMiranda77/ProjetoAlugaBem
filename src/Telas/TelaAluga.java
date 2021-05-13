package Telas;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.Date;

import javax.sql.rowset.FilteredRowSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;

import BotoesGeral.BotoesGeral;
import Classes.Bem;
import Classes.BensJaAlugados;
import Classes.CalcularData;
import Classes.Central;
import Classes.GeradorBoleto;
import Classes.Locador;
import Classes.Persistencia;
import Classes.TabelaBens;
import Execoes.CampoVasioException;
import Execoes.ValorNegativo;
import Fontes.FontesGeral;

public class TelaAluga extends Principal {
	
	
	private TabelaBens tabela = new TabelaBens();;
	private int index;
	private String end="";
	private String CPF="";
	private  String us ="";
	private int cod; 
	private String tel="";
	private int q=1;
	private boolean band=false;
	private String login;
	private GeradorBoleto boleto = new GeradorBoleto();
	
	public TelaAluga() {
		setTitle("Lista de Bens");
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
			String op="";
			String opcoes[]= {"Pesquisar por nome","Pesquisar por valor"};
			
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
					else {
					try {	
						String valor=JOptionPane.showInputDialog("Digite a valor a ser procurado");
						tabela.limparTabela();
						tabela.filtroValor(Float.parseFloat(valor));
						tabela.repaint();
					}catch(NullPointerException e) {
						JOptionPane.showMessageDialog(null,"Cancelado");
					}
					}
				}catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null,"Cancelado");
				}
			 }
			 if(nome.equals("Alugar")) {
				   BensJaAlugados bem = new BensJaAlugados();
				   for(Locador user:cdi.getListaLocador()) {
					   if(cdi.getUserlogado().equals(user.getLogin())) {
						   us=user.getNome();
						   end=user.getEndereco();
						   CPF=user.getCPF();
						   tel=user.getTelefone();
						   login=user.getLogin();
					   }
				   }
				   ///int index=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o codigo do bem pra locação"));
						  
				   int index=tabela.selecionarLinha();
				         if(index<0) {
				        	 JOptionPane.showMessageDialog(null,"Click no Bem pra locação");
				        	 
				         }
				         System.out.println(index);
						  index++;
						  try {
				          q=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite quantidade do item pra alugar")); 
						  }catch(NumberFormatException e) {
							  JOptionPane.showMessageDialog(null,"Cancelado");
						  }
				   for(Bem e:cdi.getListaBens()) {
					   if(e.getCod()==index) {
						   band=true;
						   try {
							   //teste status do item
						   if (e.getStatus().equals("indisponivel")){
							   JOptionPane.showMessageDialog(null,"Bem não está disponivel pra locação");
						   }
				           //teste quantidade do item
						   else if(q>e.getQuantidade()) {
							   JOptionPane.showMessageDialog(null,"Quantidade superior ao disponivel");
						   }
						   else {
						   int dia=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite numero de dias pra alugar"));
						   //teste de prazo
						   if(dia>e.getPrazo()) {
							   JOptionPane.showMessageDialog(null,"Quantidade Digitada e maior que o prazo estabelecido");
						   }
						   else {
						   CalcularData data=new CalcularData();
						   //pegar data de hoje em string
						   Date d =new Date();
						   String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
						   //
						   //procura o codigo do item automatico
						   for(BensJaAlugados alugados:cdi.getListaAlugados()) {
								cod=(alugados.getCod());
							}
							cod++;
							//calcula o total do aluguel
						   float total=(int) (q*e.getValor());
							//subitrai o bem
						   int quant=e.getQuantidade();
						   quant=quant-q;
						   //seta os valores do bem alugado e salva
						   e.setQuantidade(quant);
						   bem.setCod(cod);
						   bem.setNome(us);
						   bem.setTel(tel);
						   bem.setDia(dia);
						   bem.setLogin(login);
						   bem.setPeriodo(hoje);
						   bem.setBens(e.getNome());
						   bem.setQuant(q);
						   bem.setPreco(total);
						   bem.setPrazo(data.calcularSoma(dia));
						   bem.setStatus("A devolver");
						   cdi.novoAluguel(bem);
						   if(e.getQuantidade()==0) {
							   e.setStatus("indisponivel");
						   }
						   else  {
						   e.setStatus("parcialmente");
						   }
						   cod=(e.getVezes());
						   cod++;
						   e.setVezes(cod);
						   
						   //salvar a central
						   pers.salvarCentral(cdi,"dados.xml");
						   JOptionPane.showMessageDialog(null,"Bem alugado com sucesso");
						   tabela.repaint();
						   boleto.gerarBoleto(us,end,CPF, total);
						   }
						     }
						   }catch(NumberFormatException ex){
							   JOptionPane.showMessageDialog(null,"Numero invalido");
						   }
					       }
				           }
					   
				   if(band==false) {
					   JOptionPane.showMessageDialog(null,"Item não encontrado");
				   }
				   
			 }
			
		}
	}
	
	public void adicionar() {
		String[]lista2= {"disponivel","indisponivel","parcialmente"};
		
		
		JLabel logo = new JLabel("",new ImageIcon("Icones/logopequena.png"),JLabel.LEFT);
		logo.setBounds(800, -20, 260, 200);
		add(logo);
		
		JButton devolver = new  BotoesGeral("Alugar",new ImageIcon("Icones/alugar.png"),10,20, 80, 90);
		devolver.setToolTipText("Alugar um bem");
		devolver.addActionListener(new Ouvir());
		
		BotoesGeral pesquisa = new  BotoesGeral("<HTML>Pesquisar<HTML>",new ImageIcon("Icones/pesquisar.png"),85,20,80, 90);
		pesquisa.setToolTipText("Fazer pesquisa");
		pesquisa.addActionListener(new Ouvir());
		add(devolver);
		add(pesquisa);
		
		
		JLabel label2 = new FontesGeral("Pesquisar por Status",200, -90, 150, 30);
		add(label2);
		
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
		
	}
	public void tabela(JFrame janela) {
		tabela.adicionarJTable(janela);
		//tabela.revalidate();
		janela.add(tabela);	
		tabela.repaint();
	}
	public static void main(String[] args) {
		new TelaAluga();
	}
}
