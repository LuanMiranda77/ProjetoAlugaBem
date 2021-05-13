package Telas;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.security.auth.login.LoginException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import BotoesGeral.BotoesGeral;
import Classes.CalcularData;
import Classes.Central;
import Classes.Locador;
import Classes.Locatario;
import Classes.Persistencia;
import Execoes.CampoVasioException;
import Execoes.DataInvalida;
import Execoes.EmailJaCadastrado;
import Execoes.LocadorIgualException;
import Execoes.SenhaIgual_Login;
import Execoes.SenhaVasiaException;
import Fontes.CampoGeral;
import Fontes.FonteBem;

public class TelaUsuario extends Principal{
	
	private JCheckBox administrador;
	private JTextField nome;
	private JTextField email;
	private JPasswordField  senha;
	private JTextField   senha2;
	private JFormattedTextField CPF;
	private JFormattedTextField data;
	private JTextField end;
	private JFormattedTextField celular;
	private JFormattedTextField niver;
	private int cod=0;
	private JButton botao1;
	private boolean band = false;
	private boolean band1 = false;
	private boolean band2 = false;
	
	
	public JTextField getEnd() {
		return end;
	}
	public void setEnd(JTextField end) {
		this.end = end;
	}
	public JTextField getCelular() {
		return celular;
	}
	public void setCelular(JFormattedTextField celular) {
		this.celular = celular;
	}
	private JRadioButton sexo;
	
	public JTextField getNome() {
		return nome;
	}
	public void setNome(JTextField nome) {
		this.nome = nome;
	}
	public JTextField getEmail() {
		return email;
	}
	public void setEmail(JTextField email) {
		this.email = email;
	}
	public JPasswordField getSenha() {
		return senha;
	}
	public void setSenha(JPasswordField  senha) {
		this.senha = senha;
	}
	public JTextField getCPF() {
		return CPF;
	}
	public void setCPF(JFormattedTextField cPF) {
		CPF = cPF;
	}
	public JTextField getData() {
		return data;
	}
	public void setData(JFormattedTextField data) {
		this.data = data;
	}
	public JRadioButton getSexo() {
		return sexo;
	}
	public void setSexo(JRadioButton sexo) {
		this.sexo = sexo;
	}
	
	public JFormattedTextField getNiver() {
		return niver;
	}
	public void setNiver(JFormattedTextField niver) {
		this.niver = niver;
	}
	public JCheckBox getAdministrador() {
		return administrador;
	}
	public void setAdministrador(JCheckBox administrador) {
		this.administrador = administrador;
	}
	private String icon="Icones/ic_salvarm.png";
	
	public TelaUsuario(boolean band1,boolean band2) {
		setTitle("Tela de Cadastro");
		setBounds(400, 50, 500, 650);
		setResizable(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		textField(this);
		botao(icon, band1,band2);
		radioButton();
		jLabels(this);
		setVisible(true);
		repaint();	
	}
	
	private void jLabels (JFrame janela) {
		JLabel lbTitulo = new JLabel(new ImageIcon("Icones/logopequena.png"),JLabel.CENTER);
		lbTitulo.setBounds(100, -25, 300, 200);
		JLabel label  = new FonteBem("Nome", 15, 135);
		add(label);
		JLabel label1  = new FonteBem("E-mail",15 , 250);
		add(label1);
		JLabel label2  =new FonteBem("Senha", 15, 310);
		add(label2);
		JLabel label3  =new FonteBem("CPF", 300, 135);
		add(label3);
		JLabel label4  =new FonteBem("Data de Nascimento", 15, 370);
		add(label4);
		add(lbTitulo);
		JLabel end  =new FonteBem("Endereço", 15, 190);
		add(end);
		JLabel cel  =new FonteBem("Celular", 300, 190);
		add(cel);
		
		JLabel sexo  =new FonteBem("Sexo", 300, 300);
		add(sexo);
	}
	public void textField(JFrame janela) {
		nome = new CampoGeral("",10, 160, 250, 25);
		nome.setToolTipText("nome");
		janela.add(nome);
		
		end = new CampoGeral("",10, 215, 250, 25);
		end.setToolTipText("Digite endereço");
		janela.add(end);
		
		MaskFormatter m;
		try {
			m = new MaskFormatter("(##)#.####-####");
			celular = new JFormattedTextField(m);
			celular.setBounds(300, 215, 150, 25);
			celular.setToolTipText("Digite celular");
			janela.add(celular);
		} catch (ParseException e2) {
			JOptionPane.showMessageDialog(null, "Celular invalido", "Alerta", 3);
			
		}
		
		
		
		email = new CampoGeral("",10, 275, 250, 25);
		email.setToolTipText("Digite e-mail");
		janela.add(email);
		
		senha = new JPasswordField("");
		senha.setBounds(10, 335, 150, 25);
		senha.setToolTipText("Digite senha");
		janela.add(senha);
		
		senha2 = new JTextField("");
		senha2.setBounds(10, 335, 150, 25);
		senha2.setToolTipText("Digite senha");
		janela.add(senha2);
		
		MaskFormatter cpf;
		try {
			cpf = new MaskFormatter("###.###.###-##");
			CPF = new JFormattedTextField(cpf);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "CPF invalido", "Alerta", 3);
		}
		
		CPF.setBounds(300, 160, 150, 25);
		CPF.setToolTipText("Digite CPF");
		janela.add(CPF);
		
		MaskFormatter m3;
		try {
			m3 = new MaskFormatter("##/##/####");
			niver = new JFormattedTextField(m3);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Data errada", "Alerta", 3);
		}
		niver.setBounds(10, 400, 150, 25);
		niver.setToolTipText("Digite ano de nascimento");
		janela.add(niver);
		
	}
	private void botao(String icon, boolean band1,boolean band2) {
		botao1 = new BotoesGeral("<html>Salvar<html>",new ImageIcon(icon),80,500,100,100);
		botao1.repaint();
		add(botao1);
		botao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Locador user = new Locador();
					Locatario admin = new Locatario();
					Persistencia pers= new Persistencia();
					Central cdi = pers.recuperarCentral("dados.xml");
					
					if(administrador.isSelected()==true) {
						admin.setNome(nome.getText());
						admin.setCPF (CPF.getText());
						admin.setEndereco(end.getText());
						admin.setTelefone(celular.getText());
						admin.setLogin(email.getText());
						admin.setSenha(senha.getText());
						try {
							cdi.cadastroLocatario(admin);
							 band=true;
						} catch (LoginException e1) {
							JOptionPane.showMessageDialog(null, "Email ivalido", "Alerta",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						
						cod=(cdi.getCod());
						cod++;
						cdi.setCod(cod);
						user.setCod(cod);
						user.setNome(nome.getText());
						user.setCPF (CPF.getText());
						user.setEndereco(end.getText());
						user.setTelefone(celular.getText());
						user.setLogin(email.getText());
						user.setSenha(senha.getText());
						user.setNiver(niver.getText());
						
						
							try {
								
								cdi.cadastroLocador(user);
								band=true;
							 
							}catch(DataInvalida a) {
								niver.requestFocus();
							}catch(EmailJaCadastrado a) {
								email.requestFocus();
							} catch (LoginException e1) {
								JOptionPane.showMessageDialog(null, "Email é Invalido", "Alerta",JOptionPane.ERROR_MESSAGE);
							}
							
					}
					if(band==true) {
					pers.salvarCentral(cdi,"dados.xml");
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso !", "Alerta", 1);
					dispose();
					new TelaLogin();
					}
				} catch (SenhaIgual_Login e1) {
					JOptionPane.showMessageDialog(null, "Login e senha igual", "Alerta",JOptionPane.ERROR_MESSAGE);
				} catch (CampoVasioException e1) {
					JOptionPane.showMessageDialog(null, "Campo vazio", "Alerta",JOptionPane.ERROR_MESSAGE);
				} catch (LocadorIgualException e1) {
					JOptionPane.showMessageDialog(null, "Usuario já Cadastrado", "Alerta",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		JButton botao2 = new BotoesGeral("Sair",new ImageIcon("Icones/ic_sair.png"),250,500,100,100);
		botao2.setToolTipText("Voltar");
		botao2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new TelaLogin();
			}
		}
		);
		add(botao2);
		
		administrador = new JCheckBox("Administrador");
		administrador.setBackground(new Color(255,215,0));
		administrador.setOpaque(true);
		administrador.setEnabled(band1);
		administrador.setBounds(10, 450, 150, 30);
		add(administrador);
		
		
		BotoesGeral botao3 = new BotoesGeral("<html>Editar<html>",new ImageIcon("Icones/editar.png"),80,500,100,100);
		botao3.repaint();
		botao3.setVisible(band2);
		if(band2==true) {
		 botao1.setVisible(false);
		 botao2.setVisible(false);
		}
		add(botao3);
		
		botao3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Persistencia pers= new Persistencia();
				Central cdi = pers.recuperarCentral("dados.xml");
				boolean cond = false;
				
				if(cdi.getUserlogado().equals(cdi.getAdmin().getLogin())){
			    	cdi.getAdmin().setNome(nome.getText());
			   		cdi.getAdmin().setLogin(email.getText());
			   		cdi.getAdmin().setSenha(senha.getText());
			   		cdi.getAdmin().setCPF(CPF.getText());
			   		cdi.getAdmin().setEndereco(end.getText());
			   		cdi.getAdmin().setTelefone(celular.getText());
			    }
			    
			    else {
			    	CalcularData d = new CalcularData();
			    	for(Locador u: cdi.getListaLocador() ) {
					   	if(u.getLogin().equals(cdi.getUserlogado())) {
						   		u.setNome(nome.getText());
						   		u.setLogin(email.getText());
						   		u.setSenha(senha.getText());
						   		u.setCPF(CPF.getText());
						   		u.setEndereco(end.getText());
						   		u.setTelefone(celular.getText());
						   		u.setNiver(niver.getText());
						   		try {
									d.calcularNiver(niver.getText());
								} catch (DataInvalida e1) {
									cond = true;
									niver.requestFocus();
								}
						   		
			   	}
			   	}
			   }
				if(cond==false) {
				 pers.salvarCentral(cdi,"dados.xml");
				 JOptionPane.showMessageDialog(null,"Usuario editado com sucesso","Aviso",JOptionPane.INFORMATION_MESSAGE);
				 dispose();
				}
			}
		});
	
		JLabel olho = new JLabel(new ImageIcon("Icones/olhof.png"));
		olho.setBounds(165,325,50,50);
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
	
	
	
	private void radioButton() {
		JRadioButton m = new JRadioButton("Masculino");
		m.setBounds(300, 340, 90, 20);
		m.setBackground(new Color(255,215,0));
		m.setOpaque(true);
		m.setSelected(true);
		add(m);
		m.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(m.isSelected()==true) {
					botao1.setIcon(new ImageIcon("Icones/ic_salvarm.png"));
				}
				
			}
			
		});
		JRadioButton f = new JRadioButton("Feminino");
		f.setBounds(300,400 , 90, 20);
		f.setBackground(new Color(255,215,0));
		f.setOpaque(true);
		add(f);
		f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(f.isSelected()==true) {
					botao1.setIcon(new ImageIcon("Icones/ic_salvarf.png"));
				}
				
			}
			
		});
		ButtonGroup gp = new ButtonGroup();
		gp.add(m);
		gp.add(f);
	}
	public void recuperarUsuario() {
	Persistencia pers= new Persistencia();
    Central cdi = pers.recuperarCentral("dados.xml");
   
    if(cdi.getUserlogado().equals(cdi.getAdmin().getLogin())){
    	nome.setText(cdi.getAdmin().getNome());
   		email.setText(cdi.getAdmin().getLogin());
   		senha.setText(cdi.getAdmin().getSenha());
   		CPF.setText(cdi.getAdmin().getCPF());
   		end.setText(cdi.getAdmin().getEndereco());
   		celular.setText(cdi.getAdmin().getTelefone());
    }
    
    else {
   for(Locador u: cdi.getListaLocador() ) {
   	if(u.getLogin().equals(cdi.getUserlogado())) {
   		nome.setText(u.getNome());
   		email.setText(u.getLogin());
   		senha.setText(u.getSenha());
   		CPF.setText(u.getCPF());
   		end.setText(u.getEndereco());
   		celular.setText(u.getTelefone());
   		niver.setText(u.getNiver());
   	}
   	}
   }
	
	}
	
}
