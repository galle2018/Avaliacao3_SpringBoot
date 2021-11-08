package br.com.brasilinfo.avaliacao3.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.brasilinfo.avaliacao3.modelo.State;

public class StateDto {
	
	private long id;	
	private String nome;	
	private String regiao;
	private Long populacao;
	private String capital;
	private Long area;	
	private String dataDeFundacao;
	private int tempoDesdeFundacao;
	
	public StateDto(State state) {
		this.id = state.getId();
		this.nome = state.getNome();
		this.regiao = state.getRegiao();
		this.populacao = state.getPopulacao();
		this.capital = state.getCapital();
		this.area = state.getArea();
		this.dataDeFundacao = state.getDataDeFundacao();
		this.tempoDesdeFundacao = state.getTempoDesdeFundacao();
	}

	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getRegiao() {
		return regiao;
	}
	public Long getPopulacao() {
		return populacao;
	}
	public String getCapital() {
		return capital;
	}
	public Long getArea() {
		return area;
	}
	public String getDataDeFundacao() {
		return dataDeFundacao;
	}
	public int getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}
		
	public static List<StateDto> converter(List<State> states) {
		return states.stream().map(StateDto::new).collect(Collectors.toList());
		//usando o java 8 para converssao, llamo el constructor por cada uno y creo 1 lista
	}
	
	

}
