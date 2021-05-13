package Telas;
import Classes.Central;
import Classes.JavaMail;
import Classes.Locador;
import Classes.Locatario;
import Classes.Persistencia;
import Classes.TabelaBens;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;




public class TelaLogin extends Principal {
	
	private JTextField email;
	private JPasswordField senha;
	
	private TelaMenu janela;
	private JavaMail enviar = new JavaMail();
	
	public TelaLogin() {
	setSize(400,400);
	setResizable(false);
	setLocationRelativeTo(null);
	setTitle("Tela de Login");
	setVisible(true);
	camposDoLogin(this);
	botoesDoLogin(this);
	labelDoLogin();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	repaint();
	}
	private void labelDoLogin() {
		JLabel label = new JLabel("Login", new ImageIcon("user.png"), JLabel.HORIZONTAL);
		label.setBounds(110, 35, 150,200);
		label.setFont(new Font("Arial",Font.BOLD, 16));
		label.setForeground(Color.black);
		add(label);
		
		JLabel label2 = new JLabel("Usuario", JLabel.HORIZONTAL);
		label2.setForeground(Color.black);
		label2.setBounds(-10, 58, 200, 200);
		add(label2);
		
		JLabel label3 = new JLabel("Senha", JLabel.HORIZONTAL);
		label3.setForeground(Color.black);
		label3.setBounds(-10, 90, 200, 200);
		add(label3);
		
		JLabel label4 = new JLabel("Recuperar a Senha ?", JLabel.HORIZONTAL);
		label4.setForeground(Color.black);
		label4.setBounds(100, 250, 200, 30);
		label4.setToolTipText("Pra recuperar a senha digite seu email no campo do Usuario");
		add(label4);
		label4.addMouseListener(new MouseListener() {
			String s = "";
			boolean band = false;

			public void mouseClicked(MouseEvent e) {
				label4.setFont(new Font("",Font.BOLD,16));
				label4.setForeground(Color.GREEN);
				Persistencia pers= new Persistencia();
				Central cdi = pers.recuperarCentral("dados.xml");
				
			if(email.getText().equals("")) {
				 JOptionPane.showMessageDialog(janela, "Campo do Email vasio,", "Aviso",JOptionPane.ERROR_MESSAGE);
				 email.requestFocus();
			 } 
			 else if(enviar.validaEmail(email.getText())==false) {
				 JOptionPane.showMessageDialog(janela, "Email invalido, digite seu Email novamente,", "Aviso",JOptionPane.ERROR_MESSAGE);
				 email.requestFocus();
			 }
				try {
					for(Locador a: cdi.getListaLocador()) {
						if(a.getLogin().equals(email.getText())) {
							s=a.getSenha();
							band=true;
						}
					}
					if(band == false) {
						s=cdi.getAdmin().getSenha();
						band=true;
					}
					else if (band==true) {
					enviar.enviarEmail(email.getText(),"Senha recuperada","Sua senha é "+s);
					JOptionPane.showMessageDialog(janela, "Senha recuperada com sucesso, olhe seu caixa de email","Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (EmailException e1) {
					JOptionPane.showMessageDialog(janela, "Email invalido","Aviso", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				label4.setFont(new Font("",Font.BOLD,16));
				label4.setForeground(Color.RED);
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label4.setFont(new Font("",Font.BOLD,14));
				label4.setForeground(Color.BLACK);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		JLabel logo = new JLabel(new ImageIcon("Icones/logopequena.png"));
		logo.setBounds(140, -50, 250, 250);
		add(logo);
	}

	public void botoesDoLogin(JFrame frame) {
		Icon key = new ImageIcon("key.png");
		Icon can = new ImageIcon("can.png");
		JButton botao = new JButton("Cadastre-se");
		botao.setBounds(20, 320, 140, 30);
		botao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				dispose();
				new TelaUsuario(false, false);
			}
			});
		frame.add(botao);
		
		JButton botao2 = new JButton("Entrar");
		botao2.setBounds(130, 210, 130, 30);
		botao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean band = false;
				
				Persistencia pers= new Persistencia();
				Central cdi = pers.recuperarCentral("dados.xml");
				
				if(email.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo do Email vasio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
					email.requestFocus();
					band=true;
				}
				else if(senha.getText().equals("")) {
					JOptionPane.showMessageDialog(janela, "Campo do senha vasio", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				    senha.requestFocus();
				    band=true;
				}
				else if(email.getText().equals(cdi.getAdmin().getLogin()))  {
					if(email.getText().equals(cdi.getAdmin().getLogin()) && senha.getText().equals(cdi.getAdmin().getSenha())){
						JOptionPane.showMessageDialog(janela, "Logoado como Administrador", "Aviso",JOptionPane.INFORMATION_MESSAGE);
						cdi.setUserlogado(email.getText());
						pers.salvarCentral(cdi,"dados.xml");
						band=true;
						dispose();
						new TelaMenu(true);
					}
					else {
						band=true;
						JOptionPane.showMessageDialog(janela, "Senha ou Login invalido", "Aviso",JOptionPane.ERROR_MESSAGE);
					
					}
				}
				
				else {
				for(Locador login:cdi.getListaLocador()) {
				if(login.getLogin().equals(email.getText()) && !senha.getText().equals(login.getSenha())){
					JOptionPane.showMessageDialog(janela, "Senha ou Login invalido", "Aviso",JOptionPane.ERROR_MESSAGE);
					    band=true;
					}
				else if(login.getLogin().equals(email.getText()) && senha.getText().equals(login.getSenha())) {
						JOptionPane.showMessageDialog(janela, "Logoado como Usuario", "Aviso",JOptionPane.INFORMATION_MESSAGE);
						band=true;
						cdi.setUserlogado(email.getText());
						pers.salvarCentral(cdi,"dados.xml");
						dispose();
						new TelaMenu(false);
					    }
				}
				}
				if(band==false) {
					JOptionPane.showMessageDialog(janela, "Usuario nao cadastrado", "Aviso",JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		});
		frame.add(botao2);
		
		JButton botao3 = new JButton("Sair", can);
		botao3.setBounds(230, 320, 140, 30);
		botao3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		frame.add(botao3);
		
		JButton listarBens= new JButton ("<html><body>Listar Bens<html><body>");
		listarBens.setFont(new Font("Arial",Font.CENTER_BASELINE,16));
		listarBens.setBounds(10, 10, 80, 80);
		listarBens.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new TelaTabela();
				
			}
		});
		frame.add(listarBens);
		
	}
	public void camposDoLogin(JFrame frame) {
		email = new JTextField();
		email.setBounds(120,150,150,20);
		frame.add(email);
		
		senha = new JPasswordField();
		senha.setBounds(120,180,150,20);
		frame.add(senha);
		
		JTextField senha2 = new JTextField();
		senha2.setBounds(120,180,150,20);
		add(senha2);
		
		JLabel olho = new JLabel(new ImageIcon("Icones/olhof.png"));
		olho.setBounds(282,168,50,50);
		add(olho);
		olho.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icones/olhoa.png"));
				senha.setVisible(false);
				senha2.setText(senha.getText());
				senha2.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				olho.setIcon(new ImageIcon("Icones/olhof.png"));
				senha.setVisible(true);
				senha2.setVisible(false);
			}
			
		});
		
		
		
		
	}
	public static void main (String[]args) {
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		if(cdi.getAdmin().getLogin().equals("")) {
			TelaUsuario user = new TelaUsuario(false,false);
				user.getAdministrador().setEnabled(false);
				user.getNiver().setEnabled(false);
				user.getAdministrador().setSelected(true);
			}
		else
		new TelaLogin();
	}


}
