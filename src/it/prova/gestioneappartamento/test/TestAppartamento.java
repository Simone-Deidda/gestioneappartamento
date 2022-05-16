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

		testCancellazioneAppartamenti(appartamentoDAOInstance);
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");

		testCercaAppartamentoPerId(appartamentoDAOInstance);
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");
		
		System.out.println("Example = null:");
		Appartamento appartamento = null;
		testCercaAppartamentoPerExample(appartamentoDAOInstance, appartamento);
		
		System.out.println("Example with quartiere = (Ga):");
		appartamento = new Appartamento();
		appartamento.setQuartiere("Ga");
		testCercaAppartamentoPerExample(appartamentoDAOInstance, appartamento);
		
		System.out.println("Example with metriquadri = 30:");
		appartamento.setQuartiere(null);
		appartamento.setMetriQuadri(30);
		testCercaAppartamentoPerExample(appartamentoDAOInstance, appartamento);
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
		if (appartamentoDAOInstance.list().size() < 1) {
			System.out.println("Database vuoto o connessione fallita!");
			throw new RuntimeException("testModificaAppartamenti : FAILED");
		}

		Appartamento primoAppartamentoDellaLista = appartamentoDAOInstance.list().get(0);
		primoAppartamentoDellaLista.setMetriQuadri(41);
		primoAppartamentoDellaLista.setPrezzo(600);

		int appartamentoModificato = appartamentoDAOInstance.update(primoAppartamentoDellaLista);
		if (appartamentoModificato == 0)
			throw new RuntimeException("testModificaAppartamenti : FAILED");

		System.out.println("<<<<testModificaAppartamenti: PASSED>>>>\n");
	}

	public static void testCancellazioneAppartamenti(AppartamentoDAO appartamentoDAOInstance) {
		if (appartamentoDAOInstance.list().size() < 1) {
			System.out.println("Database vuoto o connessione fallita!");
			throw new RuntimeException("testCancellazioneAppartamenti : FAILED");
		}

		Appartamento primoAppartamentoDellaLista = appartamentoDAOInstance.list().get(0);

		int appartamentoEliminato = appartamentoDAOInstance.delete(primoAppartamentoDellaLista.getId());
		if (appartamentoEliminato == 0)
			throw new RuntimeException("testModificaAppartamenti : FAILED");

		System.out.println("<<<<testModificaAppartamenti: PASSED>>>>\n");
	}

	public static void testCercaAppartamentoPerId(AppartamentoDAO appartamentoDAOInstance) {
		if (appartamentoDAOInstance.list().size() < 1) {
			System.out.println("Database vuoto o connessione fallita!");
			throw new RuntimeException("testCercaAppartamentoPerId : FAILED");
		}

		Appartamento primoAppartamentoDellaLista = appartamentoDAOInstance.list().get(0);
		Appartamento appartamentoTrovato = appartamentoDAOInstance.findById(primoAppartamentoDellaLista.getId());
		
		if (!appartamentoTrovato.equals(primoAppartamentoDellaLista))
			throw new RuntimeException("testCercaAppartamentoPerId : FAILED");

		System.out.println("<<<<testCercaAppartamentoPerId: PASSED>>>>\n");
	}

	public static void testCercaAppartamentoPerExample(AppartamentoDAO appartamentoDAOInstance, Appartamento example) {
		if (appartamentoDAOInstance.list().size() < 1) {
			System.out.println("Database vuoto o connessione fallita!");
			throw new RuntimeException("testCercaAppartamentoPerExample : FAILED");
		}

		List<Appartamento> listaAppartamentiTrovati = appartamentoDAOInstance.findByExample(example);
		
		if (listaAppartamentiTrovati == null)
			throw new RuntimeException("testCercaAppartamentoPerExample : FAILED");

		for (Appartamento appartamento : listaAppartamentiTrovati) {
			System.out.println(appartamento);
		}
		
		System.out.println("<<<<testCercaAppartamentoPerExample: PASSED>>>>\n");
	}

}
