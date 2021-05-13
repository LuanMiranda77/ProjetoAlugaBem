package Classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Font;

public class TabelaBens extends JTable{

	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private int index=0;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();

	public void adicionarJTable(JFrame janela) {
		
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Nome");
		modelo.addColumn("Discrição");
		modelo.addColumn("Quant");
		modelo.addColumn("Valor");
		modelo.addColumn("Condição");
		modelo.addColumn("Status");
		modelo.addColumn("Prazo");
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
		contener = new JScrollPane(tabela);
		
		
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		
		
		for(Bem e:cdi.getListaBens()) {
		modelo.addRow(new Object[] {e.getCod(),
				                    e.getNome(),
				                    e.getDescricao(),
				                    e.getQuantidade(),
				                    moeda.format(e.getValor()),
				                    e.getStatus(),
				                    e.getCondicao(),
				                    e.getPrazo()
		                             });
		}
		if(janela.getTitle().equals("Tela de Bens")) {
		contener.setBounds(400,200,680,400);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(168);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(80);
		}
		else if(janela.getTitle().equals("Lista de Bens Alugados")){
			contener.setBounds(10,200,670,400);
			
			
		}
		else {
			contener.setBounds(10, 150,660,300);
		}
		janela.add(contener);
		tabela.repaint();
	}
	public void excluirLinha(int linha) {
		modelo.removeRow(linha);
		
	}
	public void adicionarLinha(Bem novo) {
		modelo.addRow(new Object[] {novo.getCod(),
				                    novo.getNome(),
					                novo.getDescricao(),
					                novo.getQuantidade(),
					                moeda.format(novo.getValor()),
					                novo.getStatus(),
					                novo.getCondicao(),
					                novo.getPrazo()
					                 });
		
	}
   public void filtroLinha(String n) {
		
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		
		for(Bem novo: cdi.getListaBens()) {
			if(novo.getNome().equals(n)||novo.getStatus().equals(n)) {
				modelo.addRow(new Object[] {novo.getCod(),
						                    novo.getNome(),
							                novo.getDescricao(),
							                novo.getQuantidade(),
							                moeda.format(novo.getValor()),
							                novo.getStatus(),
							                novo.getCondicao(),
							                novo.getPrazo()
							                 });
			}
		}
	}
   public void filtroValor(float n) {
		
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		
		for(Bem novo: cdi.getListaBens()) {
			if(novo.getValor()<=n) {
				modelo.addRow(new Object[] {novo.getCod(),
						                    novo.getNome(),
							                novo.getDescricao(),
							                novo.getQuantidade(),
							                moeda.format(novo.getValor()),
							                novo.getStatus(),
							                novo.getCondicao(),
							                novo.getPrazo()
							                 });
			}
		}
	}
   
	public void limparTabela() {
	while(tabela.getModel().getRowCount()>0) {
		modelo.removeRow(0);
	}
	
	}
	public int selecionarLinha() {
		  
		tabela.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				index = tabela.getSelectedRow();
			}
			
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		return index;
			
		}
}
