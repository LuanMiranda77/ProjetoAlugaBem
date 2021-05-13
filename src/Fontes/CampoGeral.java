package Fontes;

import java.awt.Font;

import javax.swing.JTextField;

public class CampoGeral extends JTextField{
	
	public CampoGeral(String n,int x,int y, int w, int h) {
		setText(n);
		setBounds(x, y, w, h);
		setFont(new Font("Arial",Font.ITALIC,14));	
			
			
		}

}
