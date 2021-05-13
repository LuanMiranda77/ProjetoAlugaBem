package Classes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.LineNumberInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.joda.time.Days;
import org.joda.time.ReadableInstant;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.collection.PdfTargetDictionary;

import Execoes.CampoVasioException;
import Execoes.DataInvalida;
import Execoes.EmailJaCadastrado;
import Execoes.LocadorIgualException;
import Execoes.SenhaIgual_Login;
import Execoes.SenhaVasiaException;
import Execoes.ValorNegativo;

public class Central {
	 
	 private ArrayList<Locador>listaLocador=new ArrayList<Locador>();
	 private ArrayList<Bem>  listaBens = new ArrayList<Bem>();
	 private ArrayList<BensJaAlugados> listaAlugados = new ArrayList<BensJaAlugados>();
	 private Locatario admin = new Locatario();
	 private Locador user=new Locador();
	 private Bem bem = new Bem();
	 private int cod=0,cont=0;
	 private int cod2;
	 private String userlogado;
	 private String us="";
	 private String end="";
	 private String CPF="";
	 

	public Locador getUser() {
		return user;
	}

	public void setUser(Locador user) {
		this.user = user;
	}

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public int getCod2() {
		return cod2;
	}

	public void setCod2(int cod2) {
		this.cod2 = cod2;
	}

	public ArrayList<BensJaAlugados>  getListaAlugados() {
		return listaAlugados;
	}

	public void setListaAlugados(ArrayList<BensJaAlugados> listaAlugados) {
		this.listaAlugados = listaAlugados;
	}

	public String getUserlogado() {
		return userlogado;
	}

	public void setUserlogado(String userlogado) {
		this.userlogado = userlogado;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public Locatario getAdmin() {
		return admin;
	}

	public void setAdmin(Locatario admin) {
		this.admin = admin;
	}
	
	public ArrayList<Locador> getListaLocador() {
		return listaLocador;
	}

	public void setListaLocador(ArrayList<Locador> listaLocador) {
		this.listaLocador = listaLocador;
	}

	public ArrayList<Bem> getListaBens() {
		return listaBens;
	}

	public void setListaBens(ArrayList<Bem> listaBens) {
		this.listaBens = listaBens;
	}

	 
	 public void cadastroLocador(Locador novo) throws SenhaIgual_Login,CampoVasioException, LocadorIgualException, LoginException, DataInvalida, EmailJaCadastrado  {
	 	Persistencia pers = new Persistencia();
		 Central ct = pers.recuperarCentral("dados.xml"); 
		 JavaMail test= new JavaMail();
		 CalcularData test1 = new CalcularData();
		 
	 	if(cont>=1) {
	 		for(Locador locador:ct.listaLocador) { 
		 		if(locador.getLogin().equals(novo.getLogin())) {
					throw new LocadorIgualException();
		 		}
	 		}
	 	  }
	 	else if(novo.getNome().equals("")||novo.getCPF().equals("   .   .   -  ")||novo.getEndereco().equals("")||novo.getTelefone().equals("(  ) .    -    ")||
	 	         novo.getSenha().equals("")||novo.getLogin().equals("")||novo.getNiver().equals("")){
	 		  throw new CampoVasioException();
	 	}
	 	else if (novo.getLogin().equals(novo.getSenha())) {
	 		throw new SenhaIgual_Login();
	 		
	 	}
	 	else if(test.validaEmail(novo.getLogin())==false) {
			throw new LoginException();
		}
	 	else {
	 		test1.calcularNiver(novo.getNiver());
			test1.validarEmail(novo.getLogin());
	 		listaLocador.add(novo);
	 	}
	 }
	 
	 public void cadastroLocatario(Locatario novo) throws LoginException,SenhaIgual_Login,LocadorIgualException,CampoVasioException  { 
		    JavaMail test= new JavaMail();
		 
			if(novo.getNome().equals("")||novo.getCPF().equals("")||novo.getEndereco().equals("")||novo.getTelefone().equals("")||
					novo.getLogin().equals("")||novo.getSenha().equals("")) {
				    throw new CampoVasioException();
			}
			else if(novo.getLogin().equals(novo.getSenha())){
				throw new SenhaIgual_Login();
			}
			else if(test.validaEmail(novo.getLogin())==false) {
				throw new LoginException();
			}
			
			else {
				admin=novo;
			}
	 }
	 
	public void cadastroBens (Bem novo)throws CampoVasioException, ValorNegativo {
		if(novo.getDescricao().equals("")||novo.getValor()==0||novo.getPrazo()==0) {
			     throw new CampoVasioException();
		}
		else if(novo.getValor()<0) {
			throw new ValorNegativo();
		}
		else {
			listaBens.add(novo);
		}
}
	public void novoAluguel(BensJaAlugados locacao) {
		listaAlugados.add(locacao);
	}
	

	public void devolverBem(int index ){
		GeradorBoleto boleto=new GeradorBoleto();
		CalcularData calc=new CalcularData();
		Persistencia pers = new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml"); 
		float valor=0;
		boolean band=false;
		int q = 0;
		 
		 for(Locador user:cdi.getListaLocador()) {
			   if(cdi.getUserlogado().equals(user.getLogin())) {
				   us=user.getNome();
				   end=user.getEndereco();
				   CPF=user.getCPF();
			   }
		   }
		 
		   for(BensJaAlugados alugado:cdi.getListaAlugados()) {
			   if(alugado.getCod()==index) {
				   try {
						 q=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite quantidade do item pra Devolução"));
						 }catch(NumberFormatException e) {
							 JOptionPane.showMessageDialog(null,"cancelado");
						 }
				   
				    if(q>alugado.getQuant()) {
						  JOptionPane.showMessageDialog(null,"Quantidade superior ao disponivel");
						  break;
				   }
				   else {
					   band=true;
				   for(Bem bem :cdi.getListaBens()) {
					   if(bem.getNome().equals(alugado.getBens())) {
						  
						   bem.setQuantidade(bem.getQuantidade()+q);
						   alugado.setQuant(alugado.getQuant()-q);
						   valor=bem.getValor();
						   
						   if(bem.getQuantidade()==bem.getQuantidade()+alugado.getQuant()) {
							   bem.setStatus("disponivel");
						       }
						   break;
					       }
					   } 
				   
				   try {
					    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
						Date dia =new Date();   
						Date prazo = new Date(formato.parse(alugado.getPrazo()).getTime());
						String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(dia);
						
					    if(prazo.before(dia)) {
							JOptionPane.showMessageDialog(null,"Bem foi devolvido depois do prazo sera gerado uma multa");	
							long dias=calc.calcularDiminir(hoje,alugado.getPrazo());
							float total=(float) (dias*valor);
							boleto.gerarBoleto(us,end,CPF, total);
							
					        }
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null,"data ivalida");
				}
				   if(alugado.getQuant()==0) {
					   alugado.setStatus("Devolvido");
				   }
				   //salvar a central
				   if(band==true) {
					   pers.salvarCentral(cdi,"dados.xml");
					   JOptionPane.showMessageDialog(null,"Bem devolvido com sucesso"); 
				   }
				break; 
			   }
			   }
            }
		   if(band==false) {
			   JOptionPane.showMessageDialog(null,"Item não encontrado");
		   }
		   
	 }
}
		
	