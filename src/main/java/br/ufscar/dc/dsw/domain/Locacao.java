package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
public class Locacao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dataLocacao;
    
    @Column(nullable = false)
    private String horarioLocacao;

    @NotNull()
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

    @NotNull
	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "locadora_id")
	private Locadora locadora;

    public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
    public String getDataLocacao(){
        return dataLocacao;
    }
    public void setDataLocacao(String dataLocacao){
        this.dataLocacao = dataLocacao;
    }
    public String getHorarioLocacao(){
        return horarioLocacao;
    }
    public void setHorarioLocacao(String horarioLocacao){
        this.horarioLocacao = horarioLocacao;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public Locadora getLocadora(){
        return locadora;
    }
    public void setLocadora(Locadora locadora){
        this.locadora = locadora;
    }
    public Long getIdUsuario(){
        return usuario.getId();
    }
    public Long getIdLocadora(){
        return locadora.getId();
    }
}