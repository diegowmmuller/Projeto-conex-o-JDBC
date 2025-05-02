package model.dao;

import java.util.List;
import model.entities.Department;

/**
 * Interface para operações de acesso a dados (DAO) relacionadas à entidade {@link Department}.
 * 
 * <p>Define os métodos básicos de persistência como inserir, atualizar, deletar,
 * buscar por ID e buscar todos os departamentos.</p>
 * 
 * <p>Implementações dessa interface podem interagir com diferentes fontes de dados,
 * como bancos relacionais (via JDBC, JPA, etc).</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     DepartmentDao dao = DaoFactory.createDepartmentDao();
 *     Department dept = dao.findById(1);
 * }</pre>
 * 
 * @author Diego Muller
 */
public interface DepartmentDao {

    /**
     * Insere um novo departamento no banco de dados.
     * 
     * @param obj Objeto {@link Department} a ser inserido
     */
    void insert(Department obj);

    /**
     * Atualiza os dados de um departamento existente no banco de dados.
     * 
     * @param obj Objeto {@link Department} com os dados atualizados
     */
    void update(Department obj);

    /**
     * Remove um departamento com base no seu ID.
     * 
     * @param id Identificador do departamento a ser removido
     */
    void deleteById(Integer id);

    /**
     * Busca um departamento pelo seu ID.
     * 
     * @param id Identificador do departamento
     * @return Objeto {@link Department} correspondente, ou {@code null} se não encontrado
     */
    Department findById(Integer id);

    /**
     * Retorna uma lista com todos os departamentos existentes no banco de dados.
     * 
     * @return Lista de objetos {@link Department}
     */
    List<Department> findAll();
}
