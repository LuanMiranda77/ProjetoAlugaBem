package Execoes;

public class SenhaIgual_Login extends Exception{
	
	public String getMessage() {
		return "Senha igual a login !\n Digite novamente";
	}

}
