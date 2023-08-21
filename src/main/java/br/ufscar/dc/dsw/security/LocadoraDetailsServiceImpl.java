package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;

import java.util.logging.Logger;

 
public class LocadoraDetailsServiceImpl implements UserDetailsService {
    
    private static final Logger logger = Logger.getLogger(LocadoraDetailsServiceImpl.class.getName());

    @Autowired
    private ILocadoraDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        logger.info("Carregando detalhes da locadora por username: " + username);
        Locadora locadora = dao.getUserByUsername(username);
        System.out.println("\n\nLOCADORA RECUPERADA: " + locadora + "\n\n");
         
        if (locadora == null) {
            logger.warning("Locadora não encontrada para o username: " + username);
            throw new UsernameNotFoundException("Could not find locadora");
        }
        logger.info("Locadora encontrada: " + locadora.getUsername());
        return new LocadoraDetails(locadora);
    }
 
}