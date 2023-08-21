package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import br.ufscar.dc.dsw.validation.UniqueCNPJ;



@Entity
@Table(name = "Locadora")
public class Locadora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

	@NotBlank
    @UniqueCNPJ (message = "{Unique.locadora.CNPJ}")
	@Size(min = 14, max = 14 /*,message = "{Size.editora.CNPJ}"*/)
    private String CNPJ;

    @Column(nullable = false, length = 60)
	private String nome;

    @Column(nullable = false, length = 60)
	private String cidade;

	@Column(nullable = false, length = 45)
    private String role;

	@Column(nullable = false)
    private boolean enabled;

	@OneToMany(mappedBy = "locadora")
		private List<Locacao> locacoes;
	
	public Locadora() {
		this.role = "ROLE_LOCADORA";
		this.enabled = true; // Ou false, se desejar que por padrão seja desabilitada
	}


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
    public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String CNPJ) {
        // Remover pontos e traços do CNPJ
        this.CNPJ = CNPJ != null ? CNPJ.replaceAll("[^0-9]", "") : null;
    }
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
    public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
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
}
