package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneappartamento.connecion.MyConnection;
import it.prova.gestioneappartamento.model.Appartamento;

public class AppartamentoDAO {

	public List<Appartamento> list() {

		List<Appartamento> result = new ArrayList<Appartamento>();

		try (Connection c = MyConnection.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM appartamento")) {

			while (rs.next()) {
				Appartamento appartamentoTemp = new Appartamento();
				appartamentoTemp.setQuartiere(rs.getString("quartiere"));
				appartamentoTemp.setId(rs.getLong("id"));
				appartamentoTemp.setMetriQuadri(rs.getInt("metriquadri"));
				appartamentoTemp.setPrezzo(rs.getInt("prezzo"));
				appartamentoTemp.setDataCostruzione(rs.getDate("datacostruzione"));
				
				result.add(appartamentoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public int insert() {

		int result = 0;

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("");
				result = ps.executeUpdate()) {

			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

}
