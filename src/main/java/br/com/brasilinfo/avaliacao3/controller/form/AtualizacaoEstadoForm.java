package br.com.brasilinfo.avaliacao3.controller.form;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.brasilinfo.avaliacao3.modelo.State;
import br.com.brasilinfo.avaliacao3.repository.StateRepository;

public class AtualizacaoEstadoForm {

	@NotNull 
	@NotEmpty 
	@Length(min = 3)
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
	public int getTempoDesdeFundacao() {
		return tempoDesdeFundacao;
	}
	
	public State atualizar(Long id, StateRepository stateRepository) {
		
		State state = stateRepository.getOne(id);
		
		LocalDate fFundacao = LocalDate.parse(dataDeFundacao);
		LocalDate hoje = LocalDate.now();
		
		if(fFundacao.isBefore(hoje)){//se a data e anterior a data atual(inclusive por 1 dia), se atualiza e faz o calculo
			state.setNome(this.nome);
			state.setRegiao(this.regiao);
			state.setPopulacao(this.populacao);
			state.setCapital(this.capital);
			state.setArea(this.area);
			state.setDataDeFundacao(dataDeFundacao);		
			int anosdeexistencia = (int) ChronoUnit.YEARS.between(fFundacao, LocalDate.now());		
			state.setTempoDesdeFundacao(anosdeexistencia);			
			
			return state; //devuelvo el State actualizado
		}
		return state;//se nao se devolve sem atualizar
	}

	
}
