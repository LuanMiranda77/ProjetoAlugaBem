package BotoesGeral;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class BotoesGeral extends JButton{
	private String fonte="Times New Roman";
	private int tamanho=13;
	
	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public BotoesGeral(String n,ImageIcon ic,int x,int y, int w, int h) {
	setBounds(x, y	, w, h);
	setIcon(ic);
	setText(n);
	setToolTipText(n);
	setVerticalTextPosition(SwingConstants.BOTTOM);
	setHorizontalTextPosition(SwingConstants.CENTER);
	setFont(new Font(fonte,Font.ITALIC,tamanho));
		
	}

}
