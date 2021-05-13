package Telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;

import Classes.Central;
import Classes.JavaMail;
import Classes.Locador;
import Classes.Persistencia;
import Execoes.EmailInvalido;
import Fontes.FontesGeral;

public class TelaEmail  extends Principal{
	
	private JComboBox destinatario;
	private JTextField assunto;
	private JTextArea area1;
	private Persistencia pers= new Persistencia();
	private Central cdi = pers.recuperarCentral("dados.xml");
	
	public TelaEmail() {
		setSize(700,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Tela de Email");
		setVisible(true);
		textField(this);
		botao(this);
		labelEmail(this);
		repaint();
	}
	
	
	private void labelEmail(JFrame janela) {
		JLabel label1 = new FontesGeral("Destinatário", 10,-60,150,20);
		add(label1);
		
		
		JLabel lbTitulo = new JLabel(new ImageIcon("Icones/logopequena.png"),JLabel.CENTER);
		lbTitulo.setBounds(420, -20,250, 200);
		add(lbTitulo);
		
		JLabel  label3 = new FontesGeral("Assunto", 40,1,150,20);
		add(label3);
		
		JLabel  label4 = new FontesGeral("Menssagem", 180,40,150,20);
		add(label4);
	
	}
	private void textField(JFrame janela) {
		int cont=0;
		String[] lista= new String[cdi.getListaLocador().size()];
        if(!cdi.getUserlogado().equals(cdi.getAdmin().getLogin())) {
			
			lista[0]=cdi.getAdmin().getLogin();
		}
        else {
		for(Locador a:cdi.getListaLocador()) {
			String email=a.getLogin();
			lista[cont]=email;
			cont++;
		}
        }
		destinatario  = new JComboBox(lista);
		destinatario.setBounds(100, 40,300,30);
		destinatario.setToolTipText("Digite o e-mail para quem dejesa enviar.");
		add(destinatario);
		
		
		assunto  = new JTextField();
		assunto.setBounds(100, 100,300,30);
		assunto.setToolTipText("Digite o assunto do e-mail.");
		add(assunto);
		
		area1 = new JTextArea();
		area1.setToolTipText("Digite o texto desejado.");
		area1.setBounds(100, 160, 400, 250);
		area1.setLineWrap(true);
		area1.setWrapStyleWord(true);
		add(area1);
	}
	private void botao(JFrame janela) {
		JButton botao1 = new JButton("Enviar e-mail");
		botao1.setBounds(520, 420, 150, 30);
		add(botao1);
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				JavaMail enviar=new JavaMail();
				String dest=(String) destinatario.getSelectedItem();
				String tema=assunto.getText();
				String text = area1.getText();
			
				try {
					if(tema.equals("")) {
						JOptionPane.showMessageDialog(null, "Campo assunto vazio", "Alerta", JOptionPane.ERROR_MESSAGE);
						assunto.requestFocus();
					}
					else if(text.equals("")) {
						JOptionPane.showMessageDialog(null, "Campo da messagem vazio", "Alerta", JOptionPane.ERROR_MESSAGE);
						area1.requestFocus();
					}
					else {
						destinatario.requestFocus();
						enviar.enviarEmail(dest, tema, text);
						JOptionPane.showMessageDialog(null,"Email enviado com sucesso!");
					}
				} catch (EmailException e) {
					JOptionPane.showMessageDialog(null, "Email Não encontrado", "Alerta", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
	}
	public static void main(String[] args) {
		new TelaEmail();
	}

}
