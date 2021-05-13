package Classes;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.mail.imap.protocol.MailboxInfo;

public class GeradorBoleto {
	private Persistencia pers= new Persistencia();
	private Central cdi = pers.recuperarCentral("dados.xml");
	private String barra="584884 587588 599125 8 11888998888";
	
	
	public void gerarBoleto(String nome,String end,String CPF,float valor ){
	    CalcularData calc=new CalcularData();
		Document boleto=new Document();
    	String arquivoPdf="boleto2.pdf";
    	
    	try {
    	     PdfWriter.getInstance(boleto, new FileOutputStream(arquivoPdf));
    	     boleto.open();
    	     //paragrafo do arquivo
    	     Paragraph p  =new Paragraph("boleto.pdf");
    	     p.setAlignment(1);
    	     boleto.add(p);
    	     p=new Paragraph("\n");
    	     boleto.add(p);
    	     //logo
    	     Image logo = Image.getInstance("Icones/logopequena.png");
    	     logo.scaleToFit(150, 150);
    	     logo.setAlignment(1);
    	     boleto.add(logo);
    	     
    	     
    	     //data do dia em String
    	     Date d = new Date();
   	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
   	         // titulo
    	     PdfPTable titulo=new PdfPTable(1);
    	     PdfPCell nome1=new PdfPCell(new Paragraph("BOLETO DE PAGAMENTO"));
    	     nome1.setBackgroundColor(new BaseColor(139, 137, 137));
    	     nome1.setBorder(5);
    	     nome1.setHorizontalAlignment(1);
    	     titulo.addCell(nome1);
    	     
    	     PdfPTable titulo2=new PdfPTable(1);
    	     PdfPCell nome11=new PdfPCell(new Paragraph("BB - 001  ||  "+barra+valor));
    	     nome11.setBorder(5);
    	     nome11.setHorizontalAlignment(0);
    	     titulo2.addCell(nome11);
    	    
    	     
    	     
    	     
    	     
    	     //tabela 
    	     PdfPTable cobrador=new PdfPTable(2);
    	     PdfPCell celula=new PdfPCell(new Paragraph("Banco do Brasil"+"\n---------------------------------------------------"+"\nData emissao: "+hoje+"\n---------------------------------------------------"
    	     +"\nAgencia: 0229-1 "+"\n---------------------------------------------------"+"\nConta: 3.669-9"+"\n   "));
    	     celula.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celula.setBorder(5);
    	     celula.setHorizontalAlignment(0);
    	     PdfPCell celula1=new PdfPCell(new Paragraph("COBRADOR:\n"+"\nNome: "+cdi.getAdmin().getNome()+"\n" +"\nEndereço:"+cdi.getAdmin().getEndereco()
    	     +"\n" +"\nCPF: "+cdi.getAdmin().getCPF()+"\n     "));
    	     cobrador.addCell(celula1);
    	     cobrador.addCell(celula);
    	     
    	     
    	     
    	 
    	     
    	   
    	     
    	     
    	     //tabela de pagador
    	     PdfPTable tabela2=new PdfPTable(2);
    	     PdfPCell celula3=new PdfPCell(new Paragraph("PAGADOR:\n"+"\nNome: "+nome+"\n" + "\nEndereço: "+end
    	     +"\n" +"\nCPF: "+CPF+"\n   "));
    	     PdfPCell celula4=new PdfPCell(new Paragraph("Vencimento: "+calc.calcularSoma(3)+"\n---------------------------------------------------"
    	     +"\nValor: R$ "+valor+"0"+"\n---------------------------------------------------"+"\n   "));

    	     tabela2.addCell(celula3);
    	     tabela2.addCell(celula4);
    	    
    	     //int num=
    	     PdfPTable barras=new PdfPTable(1);
    	     PdfPCell br=new PdfPCell(new Paragraph("\nCodigo de Barras" +"\n"+barra+valor));
    	     br.setBackgroundColor(new BaseColor(139, 137, 137));
    	     br.setBorder(5);
    	     br.setHorizontalAlignment(0);
    	     barras.addCell(br);
    	     
    	     //codigo barras
    	     Image codi = Image.getInstance("Icones/barras.png");
    	     codi.scaleToFit(200, 600);
    	     codi.setAlignment(1);
    	     
    	     
             
    	     //adicionar no pdf
    	    
             boleto.add(titulo);
             boleto.add(titulo2);
   	         boleto.add(cobrador);
   	         boleto.add(tabela2);
   	         boleto.add(barras);
   	         boleto.add(codi);
   	         boleto.close();
   	         Desktop.getDesktop().open(new File("boleto2.pdf"));
    	     
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Erro não foi possivel geral o boleto ", "Alerta", JOptionPane.ERROR_MESSAGE);
    		
    	}
   
    }
	public static void main(String[] args) {
	GeradorBoleto b =new GeradorBoleto();
		b.gerarBoleto("kuku", "kksk", "54548484", 500);
	}
}
