package it.contrader.dao;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import it.contrader.dto.UrineTestDTO;
import it.contrader.model.UrineTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.contrader.model.User;

import java.util.List;

@Repository
@Transactional
public interface UrineTestRepository extends CrudRepository<UrineTest, Long> {

    List<UrineTest> findAllByIdUser(Long idUser);
    List<UrineTest> findAllByIdAdmin(Long idAdmin);

    @Query("SELECT ut FROM UrineTest ut WHERE SUBSTRING(ut.dateInsert, 1, 4) = :year AND SUBSTRING(ut.dateInsert, 6, 2) = :month AND ut.idUser = :userId")
    List<UrineTest> findAllByYearMonthAndUserId(String year, String month, Long userId);

    @Query(value = "Select  urinetest.*, registry.cf from urinetest  inner Join registry on urinetest.idUser = registry.id Where urinetest.idAdmin= :idAdmin AND registry.cf = :cf", nativeQuery = true)
    List<UrineTest> findAllByCf(Long idAdmin, @Param("cf") String cf);

    @Query(value = "Select  urinetest.*, registry.cf from urinetest  inner Join registry on urinetest.idUser = registry.id Where registry.cf = :cf", nativeQuery = true)
    List<UrineTest> findAllByCf(@Param("cf") String cf);
}
