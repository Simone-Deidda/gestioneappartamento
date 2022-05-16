package it.prova.gestioneappartamento.test;

import java.util.List;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestAppartamento {

	public static void main(String[] args) {
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		System.out.println("Attualmente nel DB ci sono: " + appartamentoDAOInstance.list().size() + " elementi");
		
		
	}
	public static void testInserimentoAppartamenti(AppartamentoDAO appartamentoDAOInstance) {
		
	}
}
