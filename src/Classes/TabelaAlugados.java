package Classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaAlugados extends JTable{
	
	private DefaultTableModel modelo;
	private JScrollPane contener;
	private JTable tabela;
	private int index=0;
	private NumberFormat moeda = NumberFormat.getCurrencyInstance();

	public void adicionarTabela(JFrame janela) {
		
	    modelo = new DefaultTableModel();
	    modelo.addColumn("Cod");
	    modelo.addColumn("Nome Usuario");
		modelo.addColumn("Discrição");
		modelo.addColumn("Quant");
		modelo.addColumn("Valor");
		modelo.addColumn("Prazo devolução");
		modelo.addColumn("Status");
		tabela = new  JTable(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(162);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
		contener = new JScrollPane(tabela);
		
		
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		for(BensJaAlugados e:cdi.getListaAlugados()) {
			if(e.getLogin().equals(cdi.getUserlogado())) {
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getBens(),
					                    e.getQuant(),
					                    moeda.format(e.getPreco()),
					                    e.getPrazo(),
					                    e.getStatus()
			                             });
			}
		}
		contener.setBounds(20,200,680,400);
		janela.add(contener);
		tabela.repaint();
		}
		public void excluirLinha(int linha) {
			modelo.removeRow(linha);
			
		}
		public void adicionarLinha(BensJaAlugados novo) {
			modelo.addRow(new Object[] {novo.getCod(),
					                    novo.getNome(),
						                novo.getBens(),
						                novo.getQuant(),
						                moeda.format(novo.getPreco()),
						                novo.getPrazo(),
						                novo.getStatus()
						                 });
			
		}
		public void filtroLinha(String n) {
			
			Persistencia pers= new Persistencia();
			Central cdi = pers.recuperarCentral("dados.xml");
			
			for(BensJaAlugados novo: cdi.getListaAlugados()) {
				if(novo.getLogin().equals(cdi.getUserlogado())) {
					if(novo.getNome().equals(n)||novo.getStatus().equals(n))  {
						modelo.addRow(new Object[] {novo.getCod(),
								                    novo.getNome(),
									                novo.getBens(),
									                novo.getQuant(),
									                moeda.format(novo.getPreco()),
									                novo.getPrazo(),
									                novo.getStatus(),
									                 });
				}
			  }
			}
		}
		public void filtroValor(float n) {
			
			Persistencia pers= new Persistencia();
			Central cdi = pers.recuperarCentral("dados.xml");
			
			for(BensJaAlugados novo: cdi.getListaAlugados()) {
				if(novo.getLogin().equals(cdi.getUserlogado())) {
					if(novo.getPreco()<=n) {
						modelo.addRow(new Object[] {novo.getCod(),
								                    novo.getNome(),
								                    novo.getBens(),
									                novo.getQuant(),
									                moeda.format(novo.getPreco()),
									                novo.getPrazo(),
									                novo.getStatus(),
									                 });
					}
				}
			}
		}
		
		public int selecionarLinha() {
		     
		    index = tabela.getSelectedRow();
		    	
			return index;
				
			}
		public void limparTabela() {
		while(tabela.getModel().getRowCount()>0) {
			modelo.removeRow(0);
		}
		

}
}	
