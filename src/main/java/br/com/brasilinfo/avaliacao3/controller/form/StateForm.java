package br.com.brasilinfo.avaliacao3.controller.form;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.brasilinfo.avaliacao3.modelo.State;

public class StateForm {
		
	@NotNull 
	@NotEmpty 
	@Length(min = 4)
	private String nome;
	
	@Pattern(regexp = "Norte|Nordeste|Sul|Sudeste|Centro-Oeste",
		        message="Solo se permite 5 regiones: Norte, Nordeste, Sul, Sudeste o Centro-Oeste")
	@NotNull 
	@NotEmpty 
	@Length(min = 3)
	private String regiao;	
	
	@NotNull 	
	@Min(value = 1) 	
	private Long populacao;
	
	@NotNull 
	@NotEmpty 
	@Length(min = 3) 
	private String capital;
	
	@NotNull	
	@Min(value = 1) 
	private Long area;	
	
	@NotNull
	@NotEmpty
	private String dataDeFundacao;
	
	@NotNull
	private int tempoDesdeFundacao;
		
	
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
	
	
	public State converter() {	
		
		LocalDate fFundacao = LocalDate.parse(dataDeFundacao);
		int anosdeexistencia = (int) ChronoUnit.YEARS.between(fFundacao, LocalDate.now());		
		this.setTempoDesdeFundacao(anosdeexistencia);
		
		return new State(nome, regiao, populacao, capital, area, dataDeFundacao, tempoDesdeFundacao);
	}

}
