package br.com.original.rf2bpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.original.rf2bpm.entity.Arquivo;


public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

}
