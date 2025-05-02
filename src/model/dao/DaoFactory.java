package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

/**
 * Classe responsável por fornecer instâncias de objetos DAO (Data Access Object).
 * Implementa o padrão Factory, encapsulando a criação dos objetos DAO.
 * 
 * <p>Essa classe é responsável por fornecer as instâncias de DAOs para o acesso ao banco de dados.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     SellerDao sellerDao = DaoFactory.createSellerDao();
 * }</pre>
 * 
 * @author Diego Muller
 */
public class DaoFactory {

    /**
     * Cria uma instância de {@link SellerDao} utilizando a implementação {@link SellerDaoJDBC}.
     * A conexão com o banco de dados é obtida através da classe {@link DB}.
     *
     * @return uma instância de {@code SellerDao} já conectada ao banco de dados
     */
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
    
    /**
     * Cria uma instância de {@link DepartmentDao} utilizando a implementação {@link DepartmentDaoJDBC}.
     * A conexão com o banco de dados é obtida através da classe {@link DB}.
     *
     * @return uma instância de {@code DepartmentDao} já conectada ao banco de dados
     */
    public static DepartmentDao createDepartmentDao() {
    	return new DepartmentDaoJDBC(DB.getConnection());
    }

}