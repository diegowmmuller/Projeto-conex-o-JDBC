package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 * Implementação da interface {@code DepartmentDao} usando JDBC para acesso ao banco de dados.
 * Realiza operações CRUD (Create, Read, Update, Delete) na tabela {@code department}.
 */
public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	/**
     * Construtor que recebe uma conexão JDBC.
     *
     * @param conn conexão com o banco de dados
     */
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	/**
     * Insere um novo departamento no banco de dados.
     * O ID gerado automaticamente será atribuído ao objeto {@code Department}.
     *
     * @param obj o objeto {@code Department} a ser inserido
     * @throws DbException se ocorrer um erro durante a inserção
     */
	@Override
	public void insert(Department obj) {
		String sql = "INSERT INTO department (Name) VALUES (?)";
		
		try(PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				else {
					throw new DbException("Unexpected error! No rows affected");
				}
			}
			
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	 /**
     * Atualiza os dados de um departamento existente no banco de dados.
     *
     * @param obj o objeto {@code Department} com os dados atualizados
     * @throws DbException se ocorrer um erro durante a atualização
     */
	@Override
	public void update(Department obj) {
		String sql = "UPDATE department " +
					"SET Name = ? " +
					"WHERE Id = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();	
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	/**
     * Exclui um departamento do banco de dados pelo ID.
     *
     * @param id o ID do departamento a ser excluído
     * @throws DbException se o departamento não for encontrado ou ocorrer um erro
     */
	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM department WHERE Id = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			st.setInt(1, id);			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected == 0) {
				throw new DbException("No department found with the given Id");
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	 /**
     * Busca um departamento pelo ID.
     *
     * @param id o ID do departamento
     * @return o objeto {@code Department} encontrado, ou {@code null} se não encontrado
     * @throws DbException se ocorrer um erro durante a consulta
     */
	@Override
	public Department findById(Integer id) {
		String sql = "SELECT * FROM department WHERE Id = ?";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			
			st.setInt(1, id);
			
			try (ResultSet rs = st.executeQuery()) {
	            if (rs.next()) {
	                return instantiateDepartment(rs);
	            }
	        }			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}		
	
		return null;
	}

	/**
     * Retorna todos os departamentos do banco de dados, ordenados por nome.
     *
     * @return uma lista de todos os departamentos
     * @throws DbException se ocorrer um erro durante a consulta
     */
	@Override
	public List<Department> findAll() {
		String sql = "SELECT * FROM department ORDER BY Name";
		
		try(PreparedStatement st = conn.prepareStatement(sql)){
			
			try(ResultSet rs = st.executeQuery()){
				
				List<Department> list = new ArrayList<>();
				
				while(rs.next()) {
					
					Department dep = instantiateDepartment(rs);
					
					list.add(dep);					
					
				}
				
				return list;
				
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}			
		
	}

	/**
     * Cria um objeto {@code Department} com base nos dados do {@code ResultSet}.
     *
     * @param rs o {@code ResultSet} contendo os dados do departamento
     * @return o objeto {@code Department} criado
     * @throws SQLException se ocorrer um erro ao acessar os dados do {@code ResultSet}
     */
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}
}
