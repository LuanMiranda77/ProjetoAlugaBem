package Classes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioHistoria {
	private Persistencia pers= new Persistencia();
	private Central cdi = pers.recuperarCentral("dados.xml");
	private PdfPTable tabela3;
	
	
	public void gerarHistorico(String nome) throws DocumentException, IOException{
	    	Document historico=new Document();
	    	String arquivoPdf="Historico.pdf";
    	
    		 PdfWriter.getInstance(historico, new FileOutputStream(arquivoPdf));
    		 historico.open();
    	     
    	     Paragraph p  =new Paragraph("Historico.pdf");
    	     p.setAlignment(1);
    	     historico.add(p);
    	     p=new Paragraph("\n");
    	     historico.add(p);
    	     
    	     Image logo = Image.getInstance("Icones/logopequena.png");
    	     logo.scaleToFit(150, 150);
    	     logo.setAlignment(1);
    	     historico.add(logo);
    	     
    	     
    	     //titulo da tabela
    	     Date d = new Date();
   	         String hoje = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
    	     PdfPTable tabela=new PdfPTable(1);
    	     PdfPCell celula1=new PdfPCell(new Paragraph("Historico de Bens Alugados    "+"Data "+hoje));
    	     celula1.setBackgroundColor(new BaseColor(139, 137, 137));
    	     celula1.setBorder(5);
    	     celula1.setHorizontalAlignment(1);
    	     tabela.addCell(celula1);
    	     
    	     //cirando as colunas
    	     PdfPTable tabela2=new PdfPTable(5);
    	     tabela2.addCell(new PdfPCell(new Paragraph("BEM")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("CLIENTES")));
    	     tabela2.addCell(new PdfPCell(new Paragraph("TEMPO DIAS")));  
    	     tabela2.addCell(new PdfPCell(new Paragraph("PERIODO")));  
    	     tabela2.addCell(new PdfPCell(new Paragraph("VALOR")));
    	     historico.add(tabela);
    	     historico.add(tabela2);
    	     
    	     //tabela do historico
    	     tabela3=new PdfPTable(5);
    	     for(BensJaAlugados e: cdi.getListaAlugados()){ 
      	     if(e.getBens().equals(nome)) {
      	     tabela3.addCell(new PdfPCell(new Paragraph(e.getBens())));
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getNome())));
    	     tabela3.addCell(new PdfPCell(new Paragraph(""+e.getDia())));  
    	     tabela3.addCell(new PdfPCell(new Paragraph(e.getPeriodo()+" Até "+ e.getPrazo())));  
    	     tabela3.addCell(new PdfPCell(new Paragraph(""+e.getPreco()+"0")));
    	     historico.add(tabela3); 
    	     }
    	     }
    	     //totla logica
    	     float total1=0;
    			for(BensJaAlugados a:cdi.getListaAlugados()) {
    				if(a.getBens().equals(nome)) {
    					total1+=a.getPreco();
    			}
    			}
    			PdfPTable tl=new PdfPTable(1);
       	     PdfPCell total=new PdfPCell(new Paragraph("TOTAL DE BENS ALUGADO  R$ "+total1+"0"));
       	     total.setBackgroundColor(new BaseColor(139, 137, 137));
       	     total.setBorder(5);
       	     total.setHorizontalAlignment(1);
       	     tl.addCell(total);
       	     historico.add(tl);
    			
    			
    	     
   	         
    	     historico.close();
   	         Desktop.getDesktop().open(new File("Historico.pdf"));
	}

}
