package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/**
 * Implementação da interface {@link SellerDao} utilizando JDBC.
 * Responsável por realizar as operações de persistência (CRUD) para a entidade {@link Seller}.
 */
public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	/**
     * Construtor que recebe uma conexão com o banco de dados.
     *
     * @param conn Conexão ativa com o banco de dados.
     */
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	 /**
     * Insere um novo vendedor no banco de dados e atualiza o ID do objeto com o valor gerado.
     *
     * @param obj Objeto {@link Seller} a ser inserido.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public void insert(Seller obj) {
		String sql = "INSERT INTO seller "
				   + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
				   + "VALUES (?, ?, ?, ?, ?)";
		
		try(PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				try(ResultSet rs = st.getGeneratedKeys()){
					if(rs.next()) {
						int id = rs.getInt(1);
						obj.setId(id);
					}									
				}
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	/**
     * Atualiza os dados de um vendedor existente no banco de dados.
     *
     * @param obj Objeto {@link Seller} com os dados atualizados.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public void update(Seller obj) {
		String sql = "UPDATE seller "
				   + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
				   + "WHERE Id = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();

			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}

	}

	/**
     * Deleta um vendedor do banco de dados com base no seu ID.
     *
     * @param id ID do vendedor a ser deletado.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM seller "
				+ "WHERE Id = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}
	
	/**
     * Busca um vendedor pelo seu ID, incluindo os dados do departamento associado.
     *
     * @param id ID do vendedor.
     * @return Objeto {@link Seller} correspondente ou null se não encontrado.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public Seller findById(Integer id) {
		String sql = "SELECT seller.*, department.Name as DepName " 
				   + "FROM seller INNER JOIN department "
				   + "ON seller.DepartmentId = department.Id " 
				   + "WHERE seller.Id = ?";

		try (PreparedStatement st = conn.prepareStatement(sql)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					Department dep = instantiateDepartment(rs);
					Seller obj = instantiateSeller(rs, dep);
					return obj;
				}
				return null;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	/**
     * Retorna uma lista com todos os vendedores, ordenados pelo nome.
     * Inclui os dados do departamento associado.
     *
     * @return Lista de vendedores.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public List<Seller> findAll() {
	    String sql = "SELECT seller.*, department.Name as DepName "
	               + "FROM seller INNER JOIN department "
	               + "ON seller.DepartmentId = department.Id "
	               + "ORDER BY Name";

	    try (PreparedStatement st = conn.prepareStatement(sql);
	         ResultSet rs = st.executeQuery()) {

	        List<Seller> list = new ArrayList<>();
	        Map<Integer, Department> map = new HashMap<>();

	        while (rs.next()) {
	            Department dep = map.get(rs.getInt("DepartmentId"));

	            if (dep == null) {
	                dep = instantiateDepartment(rs);
	                map.put(rs.getInt("DepartmentId"), dep);
	            }

	            Seller obj = instantiateSeller(rs, dep);
	            list.add(obj);
	        }

	        return list;

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	 /**
     * Retorna uma lista de vendedores pertencentes a um departamento específico.
     *
     * @param department Objeto {@link Department} a ser usado como filtro.
     * @return Lista de vendedores do departamento informado.
     * @throws DbException se ocorrer erro de SQL.
     */
	@Override
	public List<Seller> findByDepartment(Department department) {
	    String sql = "SELECT seller.*, department.Name as DepName "
	               + "FROM seller INNER JOIN department "
	               + "ON seller.DepartmentId = department.Id "
	               + "WHERE DepartmentId = ? "
	               + "ORDER BY Name";

	    try (PreparedStatement st = conn.prepareStatement(sql)) {
	        st.setInt(1, department.getId());

	        try (ResultSet rs = st.executeQuery()) {
	            List<Seller> list = new ArrayList<>();
	            Map<Integer, Department> map = new HashMap<>();

	            while (rs.next()) {
	                Department dep = map.get(rs.getInt("DepartmentId"));

	                if (dep == null) {
	                    dep = instantiateDepartment(rs);
	                    map.put(rs.getInt("DepartmentId"), dep);
	                }

	                Seller obj = instantiateSeller(rs, dep);
	                list.add(obj);
	            }

	            return list;
	        }

	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    }
	}

	 /**
     * Instancia e retorna um objeto {@link Seller} a partir dos dados do {@link ResultSet}.
     *
     * @param rs  ResultSet com os dados do vendedor.
     * @param dep Objeto {@link Department} associado.
     * @return Objeto {@link Seller}.
     * @throws SQLException se ocorrer erro ao acessar os dados do ResultSet.
     */
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		return obj;
	}

	/**
     * Instancia e retorna um objeto {@link Department} a partir dos dados do {@link ResultSet}.
     *
     * @param rs ResultSet com os dados do departamento.
     * @return Objeto {@link Department}.
     * @throws SQLException se ocorrer erro ao acessar os dados do ResultSet.
     */
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("depName"));
		return dep;
	}

}
