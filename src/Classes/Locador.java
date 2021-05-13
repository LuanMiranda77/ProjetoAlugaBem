package Classes;
import java.util.ArrayList;

public class Locador extends Pessoa {
	  
	   private String login;
	   private String senha;
	   private String niver;
	   private int cod=0;
	   
	   
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getLogin() {
			return login;
	}
	public void setLogin(String login) {
			this.login = login;
	}   
	   
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNiver() {
		return niver;
	}
	public void setNiver(String niver) {
		this.niver = niver;
	}
	
	
	
	
	
}
