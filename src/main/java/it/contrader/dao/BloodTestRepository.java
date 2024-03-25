package it.contrader.dao;


import javax.transaction.Transactional;


import it.contrader.model.UrineTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;



import it.contrader.model.BloodTest;

import java.util.List;

@Repository
@Transactional
public interface BloodTestRepository extends CrudRepository<BloodTest, Long> {


    List<BloodTest> findAllByIdAdmin(Long idAdmin);

    List<BloodTest> findAllByIdUser(Long idUser);

    @Query("SELECT ut FROM BloodTest ut WHERE SUBSTRING(ut.dateInsert, 1, 4) = :year AND SUBSTRING(ut.dateInsert, 6, 2) = :month AND ut.idUser = :userId")
    List<BloodTest> findAllByYearMonthAndUserId(String year, String month, Long userId);

    @Query(value = "Select  bloodtest.*, registry.cf from bloodtest  inner Join registry on bloodtest.idUser = registry.id Where bloodtest.idAdmin= :idAdmin AND registry.cf = :cf", nativeQuery = true)
    List<BloodTest> findAllByCf(Long idAdmin, @Param("cf") String cf);

    @Query(value = "Select  bloodtest.*, registry.cf from bloodtest  inner Join registry on bloodtest.idUser = registry.id Where registry.cf = :cf", nativeQuery = true)
    List<BloodTest> findAllByCf(@Param("cf") String cf);
}
