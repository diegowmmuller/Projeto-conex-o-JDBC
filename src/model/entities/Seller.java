package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Representa um vendedor (Seller) no sistema.
 * 
 * <p>Contém informações como nome, e-mail, data de nascimento, salário base
 * e o departamento ao qual está associado.</p>
 * 
 * <p>Essa classe implementa {@link Serializable} permitindo que os objetos
 * sejam serializados se necessário, como ao salvar ou transferir dados.</p>
 * 
 * <p>Inclui os métodos {@code equals}, {@code hashCode} e {@code toString}
 * para facilitar a comparação e exibição dos objetos.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     Seller s = new Seller(1, "Maria", "maria@gmail.com", new Date(), 2500.0, department);
 *     System.out.println(s);
 * }</pre>
 * 
 * @author Diego Muller
 */
public class Seller implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do vendedor.
     */
    private Integer id;

    /**
     * Nome do vendedor.
     */
    private String name;

    /**
     * E-mail do vendedor.
     */
    private String email;

    /**
     * Data de nascimento do vendedor.
     */
    private Date birthDate;

    /**
     * Salário base do vendedor.
     */
    private Double baseSalary;

    /**
     * Departamento ao qual o vendedor pertence.
     */
    private Department department;

    /**
     * Construtor padrão.
     */
    public Seller() {
    }

    /**
     * Construtor com todos os campos.
     * 
     * @param id Identificador do vendedor
     * @param name Nome do vendedor
     * @param email E-mail do vendedor
     * @param birthDate Data de nascimento
     * @param baseSalary Salário base
     * @param department Departamento do vendedor
     */
    public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }


    /**
     * Retorna o ID do vendedor.
     * 
     * @return ID do vendedor
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID do vendedor.
     * 
     * @param id Novo ID do vendedor
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do vendedor.
     * 
     * @return Nome do vendedor
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do vendedor.
     * 
     * @param name Novo nome do vendedor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o e-mail do vendedor.
     * 
     * @return E-mail do vendedor
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do vendedor.
     * 
     * @param email Novo e-mail do vendedor
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a data de nascimento do vendedor.
     * 
     * @return Data de nascimento do vendedor
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Define a data de nascimento do vendedor.
     * 
     * @param birthDate Nova data de nascimento
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retorna o salário base do vendedor.
     * 
     * @return Salário base do vendedor
     */
    public Double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Define o salário base do vendedor.
     * 
     * @param baseSalary Novo salário base
     */
    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    /**
     * Retorna o departamento do vendedor.
     * 
     * @return Departamento ao qual o vendedor pertence
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Define o departamento do vendedor.
     * 
     * @param department Novo departamento
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
    /**
     * Gera o hash code com base no ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara dois vendedores com base no ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Seller other = (Seller) obj;
        return Objects.equals(id, other.id);
    }

    /**
     * Retorna uma representação em string do vendedor.
     */
    @Override
    public String toString() {
        return String.format(
            "Seller { id: %d, name: '%s', email: '%s', birthDate: %s, baseSalary: %.2f, department: %s }",
            id, name, email, birthDate, baseSalary, department
        );
    }
}