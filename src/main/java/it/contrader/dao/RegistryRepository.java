package it.contrader.dao;

import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;
import it.contrader.model.UrineTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RegistryRepository extends CrudRepository<Registry, Long> {

    @Query(value = "select distinct registry.* from registry join bloodtest on bloodtest.idUser= registry.idUser where idAdmin= :idAdmin  union select distinct registry.* from registry join urinetest on urinetest.idUser= registry.idUser where idAdmin= :idAdmin  ", nativeQuery = true)
    List<Registry> findAllPatientByIdAdmin(Long idAdmin);

    @Query(value= "SELECT * FROM registry WHERE idUser = :idUser", nativeQuery = true)
    Registry findByIdUser(Long idUser);
}
