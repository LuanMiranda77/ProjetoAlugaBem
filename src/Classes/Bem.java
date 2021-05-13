package Classes;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.ReadableInstant;

public class Bem {
	
	private int cod=0;
	private String nome="";
	private String descricao="";
	private int quantidade=0;
	private float valor;
	private String status="";
	private String condicao="";
	private int prazo=0;
	private String dias;
	private int vezes=0;
	private float multa=0;
	
	
	  public Bem() {
		  
		  
	  }
	  
	public Bem(int cod,String nome, String string, int j, float d, String string2, String string3, int prazo) {
		this.cod=cod;
		this.nome=nome;
		descricao=string;
		quantidade=j;
		valor=d;
		status=string2;
		condicao=string3;
		this.prazo=prazo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod=cod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getVezes() {
		return vezes;
	}

	public void setVezes(int vezes) {
		this.vezes += vezes;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public float getMulta() {
		return multa;
	}

	public void setMulta(float multa) {
		this.multa = multa;
	}
	

}
