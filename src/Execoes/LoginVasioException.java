package Execoes;

import java.lang.reflect.Executable;

public class LoginVasioException extends Exception {
	
	public String getMessage() {
		return "Login vazio !\n Digite um e-mail";
	}
}
