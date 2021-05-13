package Ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;

import Classes.Bem;
import Classes.Central;
import Classes.Persistencia;
import Classes.RelatorioHistoria;
import Classes.RelatorioPdf;
import Telas.TelaAluga;
import Telas.TelaAlugados;
import Telas.TelaCadBem;
import Telas.TelaEmail;
import Telas.TelaListaUser;
import Telas.TelaMenu;
import Telas.TelaUsuario;



public class OuvinteMenu implements ActionListener {
	
	private TelaMenu janela;
	private boolean band = false;
	


	public void actionPerformed(ActionEvent e) {
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		String tela=e.getActionCommand();
		
		switch (tela) {
		
			
		case"<html>Cadastrar<html>":
			new TelaCadBem();
				
			break;
			
		case"Aluga Já":
			new TelaAluga();
			
			break;
			
		case"Alugados":
			new TelaAlugados();			
			break;
			
		case"Email":
			new TelaEmail();
			
			break;
			
		case"Contato":
			new TelaListaUser();
			break;	
			
		case"Configurar":
			if(cdi.getUserlogado().equals(cdi.getAdmin().getLogin())) {
				band=true;
			}
			TelaUsuario user = new TelaUsuario(band,true);
			user.recuperarUsuario();

			break;
			
		case"Sair":
			janela.dispose();
			break;
			
			
		}
		
		
	}

}
