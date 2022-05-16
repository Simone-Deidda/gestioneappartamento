package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public int insert(Appartamento input) {
		if (input == null || input.getId() < 1) {
			throw new RuntimeException("Impossibile inserire Appartamento: Dati inseriti mancanti o incorretti!");
		}

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO appartamento (quartiere, prezzo, metriquadri, datacostruzione) VALUES (?, ?, ?, ?);")) {

			ps.setString(1, input.getQuartiere());
			ps.setInt(2, input.getPrezzo());
			ps.setInt(3, input.getMetriQuadri());
			ps.setDate(4, input.getDataCostruzione());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public int update(Appartamento input) {
		if (input == null || input.getId() < 1) {
			throw new RuntimeException("Impossibile modificare Appartamento: Dati inseriti mancanti o incorretti!");
		}

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"UPDATE appartamento SET quartiere = ?, prezzo = ?, metriquadri = ?, datacostruzione = ? WHERE id = ?;")) {

			ps.setString(1, input.getQuartiere());
			ps.setInt(2, input.getPrezzo());
			ps.setInt(3, input.getMetriQuadri());
			ps.setDate(4, input.getDataCostruzione());
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public int delete(Long input) {
		if (input < 1) {
			throw new RuntimeException("Impossibile modificare Appartamento: Dati inseriti mancanti o incorretti!");
		}

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM appartamento WHERE id = ?;")) {

			ps.setLong(1, input);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	public Appartamento findById(Long idInput) {
		if (idInput < 1)
			throw new RuntimeException("Impossibile caricare Appartamento: id mancante o incorretto!");

		Appartamento result = null;

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM appartamento WHERE id = ?")) {

			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Appartamento();
					result.setId(rs.getLong("id"));
					result.setQuartiere(rs.getString("quartiere"));
					result.setPrezzo(rs.getInt("prezzo"));
					result.setDataCostruzione(rs.getDate("datacostruzione"));
					result.setMetriQuadri(rs.getInt("metriquadri"));
				} else {
					result = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public List<Appartamento> findByExample(Appartamento example) {
		List<Appartamento> result = null;

		String query = "SELECT * FROM appartamento";
		if (example != null && example.everythingIsNotInitialized()) {
			query += " WHERE quartiere LIKE ?";

			if (example.prezzoIsZero())
				query += " AND prezzo > 0";
			else
				query += " AND prezzo = ?";

			if (example.metriQuadriIsZero())
				query += " AND metriquadri > 0";
			else
				query += " AND metriquadri = ?";

			if (example.dataCostruzioneIsNull())
				query += " AND datacostruzione > '0000-01-01'";
			else
				query += " AND datacostruzione = ?";
		}
		
		try (Connection c = MyConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {
			int index = 1;

			if (example != null && example.everythingIsNotInitialized()) {
				if (example.quartiereIsNull()) {
					ps.setString(index, "%");
					index++;
				} else {
					ps.setString(index, example.getQuartiere() + "%");
					index++;
				}

				if (!example.prezzoIsZero()) {
					ps.setInt(index, example.getPrezzo());
					index++;
				}

				if (!example.metriQuadriIsZero()) {
					ps.setInt(index, example.getMetriQuadri());
					index++;
				}

				if (!example.dataCostruzioneIsNull())
					ps.setDate(index, example.getDataCostruzione());
			}
			try (ResultSet rs = ps.executeQuery()) {
				result = new ArrayList<Appartamento>();

				while (rs.next()) {
					Appartamento appartamentoTmp = new Appartamento();
					appartamentoTmp.setId(rs.getLong("id"));
					appartamentoTmp.setQuartiere(rs.getString("quartiere"));
					appartamentoTmp.setPrezzo(rs.getInt("prezzo"));
					appartamentoTmp.setDataCostruzione(rs.getDate("datacostruzione"));
					appartamentoTmp.setMetriQuadri(rs.getInt("metriquadri"));
					result.add(appartamentoTmp);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;

	}

}
