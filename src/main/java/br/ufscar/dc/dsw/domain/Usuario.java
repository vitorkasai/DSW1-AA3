package br.ufscar.dc.dsw.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;
import br.ufscar.dc.dsw.validation.UniqueCPF;
 
@Entity
@Table(name = "Usuario")
public class Usuario{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;
	
	@Size(min = 11, max = 11)
    @Column(nullable = false, length = 14)
	@UniqueCPF (message = "{Unique.usuario.CPF}")
    private String CPF;

	@Column(nullable = false, length = 60)
	private String nome;

	@Size(min = 11, max = 11)
	@Column(nullable = false, length = 60)
	private String telefone;
	
	@Size(min=1, max=1)
	@Column(nullable = false, length = 2)
	private String sexo;

    @Column(nullable = false, length = 45)
    private String role;

	@Size(min = 10, max = 10)
	@Column(nullable = false, length = 15)
	private String dataNascimento;

    @Column(nullable = false)
    private boolean enabled;
	
    public Long getId() {
		return id;
	}
	
    public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
        // Remover pontos e traços do CNPJ
        this.CPF = CPF != null ? CPF.replaceAll("[^0-9]", "") : null;
    }

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone(){
		return telefone;
	}

	public void setTelefone(String telefone) {
        // Remover pontos e traços do CNPJ
        this.telefone = telefone != null ? telefone.replaceAll("[^0-9 ]", "") : null;
    }

	public String getSexo(){
		return sexo;
	}

	public void setSexo(String sexo){
		this.sexo = sexo;
	}

	public String getDataNascimento(){
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento){
		this.dataNascimento = dataNascimento;
	}
	
}