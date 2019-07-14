import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class TesteAgenda {
	public static void main(String args[]) {
		ContatoDAO contatoDAO = new ContatoDAO();

		Contato contato = new Contato();
		contato.setNome("ETELVINO");
		contato.setIdade(503);
		contato.setDataCadastro(new Date());
		contatoDAO.salvar(contato);

		Contato contato1 = new Contato();
		contato1.setId(1);
		contato1.setNome("NOME NOVO");
		contato1.setIdade(32);
		contato1.setDataCadastro(new Date());
		contatoDAO.atualizar(contato1);
		
		contatoDAO.deletarPorId(0);
		
		for (Contato c : contatoDAO.getContatos()) {
			System.out.println("NOME: "+c.getNome()+" ID: "+c.getId());
			
		}
	}
}
