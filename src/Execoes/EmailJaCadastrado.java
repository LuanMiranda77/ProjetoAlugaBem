package Execoes;

import javax.swing.JOptionPane;

public class EmailJaCadastrado extends Exception {
	public EmailJaCadastrado() {
		JOptionPane.showMessageDialog(null,"Email já esta cadastrado");
	}

}
