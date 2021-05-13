package Classes;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioPdf {
	private Persistencia pers= new Persistencia();
	private Central cdi = pers.recuperarCentral("dados.xml");
	private PdfPTable tabela3;
	
	
	public void gerarRelatorio() throws DocumentException, ParseException, IOException{
		
		
	    	Document boleto=new Document();
	    	String arquivoPdf="relatorio.pdf";
    	
    		 PdfWriter.getInstance(boleto, new FileOutputStream(arquivoPdf));
    	     boleto.open();
    	     
    	     Paragraph p  =new Paragraph("Relatorio.pdf");
    	     p.setAlignment(1);
    	     boleto.add(p);
    	     p=new Paragraph("\n");
    	     boleto.add(p);
    	     
    	     Image logo = Image.getInstance("Icones/logopequena.png");
    	     logo.scaleToFit(150, 150);
    	     logo.setAlignment(1);
    	     boleto.add(logo);
    	     
    	     
    	     //tabela 
    	     Date d = new Date();
   	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
    	     PdfPTable tabela=new PdfPTable(1);
    	     PdfPCell celula1=new PdfPCell(new Paragraph("Relatorio dos clientes em atraso     "+"Data "+hoje));
    	     celula1.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celula1.setBorder(5);
    	     celula1.setHorizontalAlignment(1);
    	     tabela.addCell(celula1);
    	     
    	     PdfPTable tabela2=new PdfPTable(4);
    	     tabela2.addCell(new PdfPCell(new Paragraph("NOME")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("CONTATO")));  
    	     tabela2.addCell(new PdfPCell(new Paragraph("BEM ")));  
    	     tabela2.addCell(new PdfPCell(new Paragraph("DEVOLUÇÃO")));
    	     boleto.add(tabela);
   	         boleto.add(tabela2);
    	     
    	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	     for(BensJaAlugados e: cdi.getListaAlugados()){ 
    	    	 Date prazo = new Date(formato.parse(e.getPrazo()).getTime());
    	  
    	     if(prazo.before(d))   {
    	     //tabela de pagador
      	     tabela3=new PdfPTable(4);
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getNome())));
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getTel())));  
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getBens())));  
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getPrazo())));
    	     boleto.add(tabela3); 
    	     }
    	     }
    	     
   	         
    	     boleto.close();
   	         Desktop.getDesktop().open(new File("relatorio.pdf"));
    	
    	
    	
    }
}
