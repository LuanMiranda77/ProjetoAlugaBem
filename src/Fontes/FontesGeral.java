package Fontes;

import java.awt.Font;

import javax.swing.JLabel;

public class FontesGeral extends JLabel {
	
	public FontesGeral(String n,int x,int y, int w, int h) {
	setText(n);
	setBounds(x, y+=100, w, h);
	setFont(new Font("Arial",Font.BOLD,14));	
		
		
	}
	

}
