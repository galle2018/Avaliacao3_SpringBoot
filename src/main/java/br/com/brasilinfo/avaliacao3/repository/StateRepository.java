package br.com.brasilinfo.avaliacao3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.brasilinfo.avaliacao3.modelo.State;

public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findByRegiao(String regiao);
	

}
