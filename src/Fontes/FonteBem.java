package Fontes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FonteBem extends JLabel{
	
	public FonteBem(String n,int x,int y) {
		setText(n);
		setBounds(x, y,150,30);
		setFont(new Font("Arial",Font.BOLD,14));
		

}
}
