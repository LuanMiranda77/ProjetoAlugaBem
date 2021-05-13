package Classes;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabelaUser extends JTable{
	
	public  void TabelaUser(JFrame janela) {
		Persistencia pers= new Persistencia();
		Central cdi = pers.recuperarCentral("dados.xml");
		
		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabela = new JTable(modelo);
		modelo.addColumn("Cod");
		modelo.addColumn("Sócio");
		modelo.addColumn("CPF");
		modelo.addColumn("E-mail");
		modelo.addColumn("Telefone");
		JScrollPane contener = new JScrollPane(tabela);
		contener.setBounds(10, 150,660,300);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for(Locador a: cdi.getListaLocador()) {
			Object[] linha = {a.getCod(),a.getNome(), a.getCPF(), a.getLogin(), a.getTelefone()};
			modelo.addRow(linha);
		}
		tabela.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(211);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(211);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		janela.add(contener);
	}

}
