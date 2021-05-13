package Classes;

import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaAdmin extends TabelaAlugados{

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
		tabela.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(63);
		contener = new JScrollPane(tabela);
		
		
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		for(BensJaAlugados e:cdi.getListaAlugados()) {
			modelo.addRow(new Object[] {e.getCod(),
					                    e.getNome(),
					                    e.getBens(),
					                    e.getQuant(),
					                    moeda.format(e.getPreco()),
					                    e.getPrazo(),
					                    e.getStatus()
			                             });
			
		}
		contener.setBounds(850,150,450,400);
		janela.add(contener);
		tabela.repaint();

}
}