package db;

/**
 * Classe personalizada de exceção para erros relacionados ao banco de dados.
 * 
 * <p>Esta exceção é uma extensão de {@link RuntimeException} e pode ser utilizada
 * para encapsular erros ocorridos durante operações de acesso ao banco de dados,
 * como falhas de conexão, execução de comandos SQL ou problemas de configuração.</p>
 * 
 * <p>É comumente usada em conjunto com a camada de persistência (DAO) para lançar 
 * exceções específicas do domínio de banco de dados.</p>
 * 
 * <p>Exemplo de uso:</p>
 * <pre>{@code
 *     if (connection == null) {
 *         throw new DbException("Falha na conexão com o banco de dados.");
 *     }
 * }</pre>
 * 
 * @author Diego Muller
 */

public class DbException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg){
		super(msg);
	}

}
