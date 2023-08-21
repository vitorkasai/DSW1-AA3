package br.ufscar.dc.dsw.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Locadora;

@SuppressWarnings("uncheked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long> { 
    Locadora findById(long id);
    Locadora findByCNPJ(String CNPJ);
    List<Locadora> findAll();
    Locadora save(Locadora locadora);
    void deleteById(Long id);    
    List<Locadora> findAllByCidade(String cidade);

    @Query("SELECT l FROM Locadora l WHERE l.username = :username")
    public Locadora getUserByUsername(@Param("username") String username);
    
}
