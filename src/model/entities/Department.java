package model.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa uma entidade de Departamento no sistema.
 * 
 * <p>Essa classe contém os atributos básicos de um departamento,
 * como {@code id} e {@code name}, e é utilizada principalmente na camada de modelo (Model)
 * para representar a estrutura dos dados relacionados a departamentos em um banco de dados.</p>
 * 
 * <p>Implementa a interface {@link Serializable} para permitir que objetos do tipo
 * {@code Department} possam ser serializados, por exemplo, ao salvar em arquivos ou trafegar em rede.</p>
 * 
 * <p>Também sobrescreve os métodos {@code hashCode}, {@code equals} e {@code toString}
 * para garantir o correto funcionamento em coleções e facilitar o debug.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     Department d = new Department(1, "Computers");
 *     System.out.println(d); // Department {id: 1, name: Computers}
 * }</pre>
 * 
 * @author Diego Muller
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do departamento.
     */
    private Integer id;

    /**
     * Nome do departamento.
     */
    private String name;

    /**
     * Construtor padrão.
     */
    public Department() {
    }

    /**
     * Construtor com parâmetros.
     * 
     * @param id Identificador do departamento
     * @param name Nome do departamento
     */
    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retorna o ID do departamento.
     * 
     * @return ID do departamento
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o ID do departamento.
     * 
     * @param id Novo ID do departamento
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do departamento.
     * 
     * @return Nome do departamento
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do departamento.
     * 
     * @param name Novo nome do departamento
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gera o hash code com base no ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Compara dois objetos Department com base no ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Department other = (Department) obj;
        return Objects.equals(id, other.id);
    }

    /**
     * Retorna uma representação em string do departamento.
     */
    @Override
    public String toString() {
        return String.format("Department { id: %d, name: '%s' }", id, name);
    }
}