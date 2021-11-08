package br.com.brasilinfo.avaliacao3.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class State {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;	
	private String regiao;
	private Long populacao;
	private String capital;
	private Long area;		
	private String dataDeFundacao;
	private int tempoDesdeFundacao;	
	
	public State() {
		
	}
			
	public State(String nome, String regiao, Long populacao, String capital, Long area, String dataDeFundacao, int tempoDesdeFundacao) {	
		this.nome = nome;
		this.regiao = regiao;
		this.populacao = populacao;
		this.capital = capital;
		this.area = area;
		this.dataDeFundacao = dataDeFundacao;
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public String getDataDeFundacao() {
		return dataDeFundacao;
	}

	public void setDataDeFundacao(String dataDeFundacao) {
		this.dataDeFundacao = dataDeFundacao;
	}

	public int getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}

	public void setTempoDesdeFundacao(int tempoDesdeFundacao) {
		this.tempoDesdeFundacao = tempoDesdeFundacao;
	} 
	
	
	
	
	

	
	
}
