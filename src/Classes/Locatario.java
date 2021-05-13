package Classes;
import java.util.Date;

public class Locatario extends Pessoa {
	private String login="";
	private String senha="";
	
	//public Locatario(String login, String senha) {
		//this.senha=senha;
		//this.login=login;
	//}
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
	

}
