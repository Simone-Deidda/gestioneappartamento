package it.prova.gestioneappartamento.test;

import java.sql.Date;
import java.util.List;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {

	public static void main(String[] args) {
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");

		testInserimentoAppartamenti(appartamentoDAOInstance);
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");
		
		testModificaAppartamenti(appartamentoDAOInstance);
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");
	}

	public static void testInserimentoAppartamenti(AppartamentoDAO appartamentoDAOInstance) {
		Long longDate = new java.util.Date().getTime();
		Appartamento insert = new Appartamento("Laurentina", 30, 370, new java.sql.Date(longDate));
		insert.setId((long) (appartamentoDAOInstance.list().size() + 1));

		int appartamentoInserito = appartamentoDAOInstance.insert(insert);
		if (appartamentoInserito == 0)
			throw new RuntimeException("testInserimentoAppartamenti : FAILED");

		System.out.println("<<<<testInserimentoAppartamenti: PASSED>>>>\n");
	}
	
	public static void testModificaAppartamenti(AppartamentoDAO appartamentoDAOInstance) {
		Appartamento primoAppartamentoDellaLista = appartamentoDAOInstance.list().get(0);
		primoAppartamentoDellaLista.setMetriQuadri(41);
		primoAppartamentoDellaLista.setPrezzo(600);
		
		int appartamentoModificato = appartamentoDAOInstance.update(primoAppartamentoDellaLista);
		if (appartamentoModificato == 0)
			throw new RuntimeException("testModificaAppartamenti : FAILED");

		System.out.println("<<<<testModificaAppartamenti: PASSED>>>>\n");
	}
}
