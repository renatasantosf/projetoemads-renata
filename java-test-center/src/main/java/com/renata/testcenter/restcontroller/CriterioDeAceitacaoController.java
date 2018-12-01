package com.renata.testcenter.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renata.testcenter.model.CriterioDeAceitacao;
import com.renata.testcenter.model.CriterioDeAceitacao.CriterioDeAceitacaoPK;
import com.renata.testcenter.model.HistoriaDeUsuario;
import com.renata.testcenter.service.CriterioDeAceitacaoService;
import com.renata.testcenter.service.HistoriaDeUsuarioService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/criterio-aceitacao"})
public class CriterioDeAceitacaoController {
	
	@Autowired
	private CriterioDeAceitacaoService service;
	@Autowired
	private HistoriaDeUsuarioService historiaDeUsuarioService;
	
	@GetMapping(path = {"/{id_historia}/{id_criterio}"})
	public CriterioDeAceitacao getCriterioById(@PathVariable("id_historia") Long id_historia,
			                                   @PathVariable("id_criterio") Long id_criterio) {
		
		HistoriaDeUsuario historiaVar = historiaDeUsuarioService.getHistoriaDeUsuarioById(id_historia);
		CriterioDeAceitacaoPK criterioVar = new CriterioDeAceitacaoPK();
		criterioVar.setHistoriaDeUsuario(historiaVar);
		criterioVar.setIdLinhaCriterio(id_criterio);
		
		return this.service.getCriterioDeAceitacaoById(criterioVar);
	}
	
	@PostMapping
	public CriterioDeAceitacao saveCriterio(@RequestBody CriterioDeAceitacao criterio) {
		criterio.getId().setIdLinhaCriterio(this.service.gerarIdLinhaCriterio());
		return this.service.saveCriterioDeAceitacao(criterio);
	}
	
	@DeleteMapping(path = {"/{id_historia}/{id_criterio}"})
	public void deleteCriterio(@PathVariable("id_historia") Long id_historia,
			                   @PathVariable("id_criterio") Long id_criterio) {
		
		HistoriaDeUsuario historiaVar = historiaDeUsuarioService.getHistoriaDeUsuarioById(id_historia);
		CriterioDeAceitacaoPK criterioVar = new CriterioDeAceitacaoPK();
		criterioVar.setHistoriaDeUsuario(historiaVar);
		criterioVar.setIdLinhaCriterio(id_criterio);
		
		this.service.deleteCriterioDeAceitacao(criterioVar);
	}
	
	@GetMapping
	public List<CriterioDeAceitacao> getAllCriterio() {
		return this.service.getAllCriterioDeAceitacao();
	}
	
	@GetMapping(path = {"/byhistoria/{id_historia}/{id_projeto}"})
	public List<CriterioDeAceitacao> getCriterioByProjetoHistoria(@PathVariable("id_historia") Long idHistoria,
			                                              		  @PathVariable("id_projeto") Long idProjeto) {
		return this.service.getCriterioByProjetoHistoria(idHistoria, idProjeto);
	}
	
	@GetMapping(path = {"/byprojeto/{id_projeto}"})
	public List<CriterioDeAceitacao> getCriterioByProjeto(@PathVariable("id_projeto") Long idProjeto) {
		return this.service.getCriterioByProjeto(idProjeto);
	}
	
	@PutMapping
	public CriterioDeAceitacao updateCriterio(@RequestBody CriterioDeAceitacao criterio) {
		return this.service.updateCriterio(criterio);
	}
	
	@GetMapping(path = {"/criteriobyhistoria/{id_historia}"})
	public List<CriterioDeAceitacao> getCriterioByHistoria(@PathVariable("id_historia") Long id) {
		return this.service.getCriterioByHistoria(id);
	}

}
