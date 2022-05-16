package it.prova.gestioneappartamento.model;

import java.sql.Date;
import java.util.Objects;

public class Appartamento {
	private Long id;
	private String quartiere;
	private int metriQuadri;
	private int prezzo;
	private Date dataCostruzione;
	
	public Appartamento() {
	}

	public Appartamento(String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}

	public Appartamento(Long id, String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		this.id = id;
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}
	
	public boolean everythingIsNotInitialized() {
		return dataCostruzioneIsNull() && prezzoIsZero() && dataCostruzioneIsNull() && metriQuadriIsZero();
	}
	
	public boolean dataCostruzioneIsNull() {
		return this.dataCostruzione == null;
	}

	public boolean prezzoIsZero() {
		return this.prezzo == 0;
	}

	public boolean metriQuadriIsZero() {
		return this.metriQuadri == 0;
	}

	public boolean quartiereIsNull() {
		return this.quartiere == null || this.quartiere.isEmpty();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	public int getMetriQuadri() {
		return metriQuadri;
	}

	public void setMetriQuadri(int metriQuadri) {
		this.metriQuadri = metriQuadri;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataCostruzione() {
		return dataCostruzione;
	}

	public void setDataCostruzione(Date dataCostruzione) {
		this.dataCostruzione = dataCostruzione;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appartamento other = (Appartamento) obj;
		return Objects.equals(dataCostruzione, other.dataCostruzione) && Objects.equals(id, other.id)
				&& metriQuadri == other.metriQuadri && prezzo == other.prezzo
				&& Objects.equals(quartiere, other.quartiere);
	}

	@Override
	public String toString() {
		return "Appartamento [id=" + id + ", quartiere=" + quartiere + ", metriQuadri=" + metriQuadri + ", prezzo="
				+ prezzo + ", dataCostruzione=" + dataCostruzione + "]";
	}

}
