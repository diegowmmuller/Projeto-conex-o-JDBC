package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

/**
 * Interface DAO para a entidade {@link Seller}.
 * 
 * <p>Define operações básicas de persistência como inserção, atualização, remoção e consultas
 * para objetos do tipo {@link Seller}.</p>
 * 
 * <p>Implementações dessa interface devem conter a lógica para interagir com o banco de dados,
 * usando JDBC, JPA, ou outro mecanismo de persistência.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     SellerDao dao = DaoFactory.createSellerDao();
 *     Seller seller = dao.findById(1);
 * }</pre>
 * 
 * @author Diego Muller
 */
public interface SellerDao {

    /**
     * Insere um novo vendedor no banco de dados.
     * 
     * @param obj Objeto {@link Seller} a ser inserido
     */
    void insert(Seller obj);

    /**
     * Atualiza os dados de um vendedor existente no banco de dados.
     * 
     * @param obj Objeto {@link Seller} com os dados atualizados
     */
    void update(Seller obj);

    /**
     * Remove um vendedor com base no seu ID.
     * 
     * @param id Identificador do vendedor a ser removido
     */
    void deleteById(Integer id);

    /**
     * Busca um vendedor pelo seu ID.
     * 
     * @param id Identificador do vendedor
     * @return Objeto {@link Seller} correspondente, ou {@code null} se não encontrado
     */
    Seller findById(Integer id);

    /**
     * Retorna uma lista com todos os vendedores existentes no banco de dados.
     * 
     * @return Lista de objetos {@link Seller}
     */
    List<Seller> findAll();
    
    
    /**
     * Recupera uma lista de vendedores pertencentes a um determinado departamento.
     *
     * @param department o departamento utilizado como critério de busca
     * @return uma lista contendo todos os vendedores associados ao departamento informado
     */
    List<Seller> findByDepartment(Department department);
}