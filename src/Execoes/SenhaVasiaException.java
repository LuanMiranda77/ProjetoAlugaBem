package Execoes;

public class SenhaVasiaException extends Exception {
	
	public String getMessagem() {
		return "Senha em branco!\n Digite uma senha!";
	}

}
