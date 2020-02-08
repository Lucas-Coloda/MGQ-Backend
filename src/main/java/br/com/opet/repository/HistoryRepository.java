package br.com.opet.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.opet.entity.History;

public abstract interface HistoryRepository extends CrudRepository<History, Long> {

	@Query("select h from History h where h.title= :title")
	Stream<History> findByTitle(@Param("title") String title);
}
