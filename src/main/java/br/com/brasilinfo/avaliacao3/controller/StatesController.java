package br.com.brasilinfo.avaliacao3.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilinfo.avaliacao3.controller.dto.DetalheDoEstadoDto;
import br.com.brasilinfo.avaliacao3.controller.dto.StateDto;
import br.com.brasilinfo.avaliacao3.controller.form.AtualizacaoEstadoForm;
import br.com.brasilinfo.avaliacao3.controller.form.StateForm;
import br.com.brasilinfo.avaliacao3.modelo.State;
import br.com.brasilinfo.avaliacao3.repository.StateRepository;
import io.swagger.v3.oas.annotations.Operation;


//@Api(value = "Estados do Brasil")
@RestController
@RequestMapping("/api/states")
public class StatesController {		
	
	@Autowired
	private StateRepository stateRepository;
	
	//LISTAR TODOS
	//------------------------------------------------------------------------------------------------------------------
	//@CrossOrigin(origins = "http://192.168.119.114:4200")
	@Operation(summary = "Listado de todos los estados en orden natural de ingreso")
	@GetMapping	
	public List<StateDto> listarTodos() {			

			List<State> states = stateRepository.findAll();			
			return StateDto.converter(states);		
	}//fim /listarTodos
	//------------------------------------------------------------------------------------------------------------------
	
	
	
	//este metodo pode resolver tanto para regiao como para ordenar DESC qualquer atributo que for enviado	
	//-------------------------------------------------------------------------------------------------------------------
	
	
	/*
	@GetMapping	
	public List<StateDto> listar(String regiao, String atributo) {			
		
		if(atributo != null && !(atributo.isEmpty()) && !(atributo.isBlank()) ) { //ordeno pelo atributo
			List<State> states = stateRepository.findAll(Sort.by(Direction.DESC,atributo));
			return StateDto.converter(states);
		} 
		
		else if(regiao != null && !(regiao.isEmpty()) && !(regiao.isBlank()) ){ //visualizo por regiao
			
			List<State> states = stateRepository.findByRegiao(regiao);
			return StateDto.converter(states);			
		}
		
		else {//visalizo todos

			List<State> states = stateRepository.findAll();			
			return StateDto.converter(states);
		}
		
	} */     //fim /listar
	//-------------------------------------------------------------------------------------------------------------------
	
			
	// METODOS INDEPENDENTES A CADA ATRIBUTO 
	//(BUSCA POR REGIAO, LISTA POR POPULACAO DE MAIOR A MENOR e LISTA POR AREA DE MAIOR A MENOR)
	//-------------------------------------------------------------------------------------------------------------------
	@Operation(summary = "Listado de los estados por region enviada por parametro nome")	
	@GetMapping("/regiao")
	public List<StateDto> listaxRegioes(String nome) {				
			List<State> states = stateRepository.findByRegiao(nome);
			return StateDto.converter(states);		
	}//fim /regiao
	
	
	@Operation(summary = "Listado de todos los estados ordenados por populacao de mayor a menor")
	@GetMapping("/populacao")
	public List<StateDto> listarxPopulacao() {				
			List<State> states = stateRepository.findAll(Sort.by(Direction.DESC,"populacao"));			
			return StateDto.converter(states);		
	}//fim /populacao
	
	
	@Operation(summary = "Listado de todos los estados ordenados por area de mayor a menor")
	@GetMapping("/area")
	public List<StateDto> listarxArea() {				
			List<State> states = stateRepository.findAll(Sort.by(Direction.DESC,"area"));
			return StateDto.converter(states);		
	}//fim /area
	//-------------------------------------------------------------------------------------------------------------------
	
	
	//INICIO CADASTRO ----------------------------------------------------------------------------------------------------
	@Operation(summary = "Ingreso de nuevos estados")
	@PostMapping
	@Transactional
	public ResponseEntity<StateDto> cadastrar(@RequestBody @Valid StateForm form, UriComponentsBuilder uriBuilder) {			
		
		State state = form.converter();			
		
		stateRepository.save(state);
		
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();	
		return ResponseEntity.created(uri).body(new StateDto(state));
		//devuelvo un codigo 201 junto a um cabecalho chamado location com a url desse novo recurso
		//que acaba de ser criado e  no corpo dessa resposata devolvo uma representacao desse recurso			
		
	}
	//FIM CADASTRO -------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//INICIO DETALHE -----------------------------------------------------------------------------------------------------
	@Operation(summary = "Detalle de 1 estado pasado su id por parametro")
	@GetMapping("/{id}")
	public ResponseEntity<DetalheDoEstadoDto> detalhar(@PathVariable Long id){
		Optional<State> state = stateRepository.findById(id);
		if (state.isPresent()) {//verifico se existe
			return ResponseEntity.ok(new DetalheDoEstadoDto(state.get()));
		}
		return ResponseEntity.notFound().build();
	}
	//FIM DETALHE --------------------------------------------------------------------------------------------------------	
	
	
	
	
	//INICIO ATUALIZAR ---------------------------------------------------------------------------------------------------
	@Operation(summary = "Actualizacion de 1 estado pasando su id y datos a modificar")
	@PutMapping("/{id}") //sobreescrevo o recurso inteiro
	@Transactional
	public ResponseEntity<StateDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form){
		Optional<State> optional = stateRepository.findById(id); //utilizo AtualizacaoEstadoForm para tener mas flexibilidad de lo que actualizar
		if (optional.isPresent()) {
			State state = form.atualizar(id, stateRepository);
			return ResponseEntity.ok(new StateDto(state));
		}
			
		return ResponseEntity.notFound().build();
	}
	// utilice el AtualizacaoEstadoForm pues considere cuando se elija actualizar un form que muestra todos los datos
	// y permite modificar el que se quiera y se envia todo el form
	
	//FIM ATUALIZAR ------------------------------------------------------------------------------------------------------
	

	
	
	//INICIO REMOVER -----------------------------------------------------------------------------------------------------
	@Operation(summary = "Eliminacion de 1 estado pasado su id por parametro")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<State> optional = stateRepository.findById(id);
		if (optional.isPresent()) {
			stateRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	//FIM REMOVER --------------------------------------------------------------------------------------------------------
	
	
	
	
	
		
	
	
	
}
