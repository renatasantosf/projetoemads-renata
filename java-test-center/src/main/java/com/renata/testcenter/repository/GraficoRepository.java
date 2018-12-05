package com.renata.testcenter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renata.testcenter.model.Grafico;
import com.renata.testcenter.model.GraficoAux;

public interface GraficoRepository extends JpaRepository<Grafico, Long> {
	@Query("SELECT g FROM Grafico g WHERE id_projeto = :id")
	public List<Grafico> graficoByProjeto(@Param("id") Long id);
	
	@Query("select new com.renata.testcenter.model.GraficoAux(count(d),d.nivelDeCriticidade) from Defeito d " + 
			       "where d.projeto.id = :id " + 
			       "group by (d.nivelDeCriticidade) order by d.nivelDeCriticidade asc")
	public List<GraficoAux> gerarGraficoCriticidade(@Param("id") Long id);
	
}
