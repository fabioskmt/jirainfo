package com.everis.jirainfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.jirainfo.entity.Etapa;
import com.everis.jirainfo.util.StatusEnum;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long>{

	Etapa findByStatusInicial(StatusEnum statusInicial);
	
	
//	public double obterTempoMedioStatus(StatusEnum statusEnum) {
//		return (Double) em.createQuery("SELECT AVG(e.tempoStatusInicial) FROM Etapa e WHERE e.statusInicial = '" + 
//				statusEnum.getStatus() + "'").getSingleResult();
//	}

}
