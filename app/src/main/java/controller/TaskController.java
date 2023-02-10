
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.task;
import util.connectionFactory;

/**
 *
 * @author robson
 */
public class TaskController {
    
    /**
     * 
     * @param task
     * @throws SQLException 
     * 
     * 
     */
    
    public void save ( task task)  {
        
        String sql = "INSERT INTO TASKS  (IDPROJECT ,  NAME  , DESCRIPTION , COMPLETED  , "
                + "NOTES  , DEADLINE  ,CREATEDDATE , UPDATEDCREATE   ) VALUES (?,?,?,?,?,?,?,?)";
        
        
        Connection conn= null;
        PreparedStatement statement= null;
        
        try {
            
            conn = connectionFactory.getConnection();
             statement = conn.prepareStatement(sql);
             
             statement.setInt(1, task.getIdProject());
             statement.setString(2, task.getName());
             statement.setString(3 ,task.getDescription());
             statement.setBoolean(4, task.isIsCompleted());
             statement.setString(5, task.getNotes());
             statement.setDate(6, new Date (task.getDeadline().getTime()));
             statement.setDate(7, new Date (task.getCreatedAt().getTime()));
             statement.setDate(8, new Date(task.getUpDateAt().getTime()));
             
           // boolean execute = statement.execute();
            statement.execute();
           
        }
            
                 catch (Exception eex) {
            
                throw new RuntimeException("Erro com data nem sei oq e " +  eex.getMessage(),eex );
            
            
        }
    
        finally {
            
             connectionFactory.closeConnection(conn, statement);
             
             

        }
        
        
        
    }
    
    public void update (task task) {
        
        String sql = "UPDATE TASKS  SET "
                + "IDPROJECT = ?, "
                + "NAME= ? ,"
                + " DESCRIPTION = ?, "
                + "NOTES = ?, "
                + "COMPLETED = ?,"
                + "DEADLINE = ?,"
                + "CREATEDDATE=?,"
                + "UPDATEDCREATE=? "
                + "WHERE ID = ?";
        
        
        Connection conn= null;
        PreparedStatement statement= null;
        
        try {
            //Estabelecendo conexao com o Banco de Dados
            conn = connectionFactory.getConnection();
            //Preparando a query
             statement = conn.prepareStatement(sql);
             //Setando os valores do statement
             statement.setInt(1, task.getIdProject());
             
             statement.setString(2, task.getName());
             
             statement.setString(3 ,task.getDescription());
             statement.setString(4, task.getNotes());
             statement.setBoolean(5, task.isIsCompleted());
             
             statement.setDate(6, new Date (task.getDeadline().getTime()));
             statement.setDate(7, new Date (task.getCreatedAt().getTime()));
             statement.setDate(8, new Date(task.getUpDateAt().getTime()));
             statement.setInt(9, task.getId());

             
             //Executando a query
             statement.execute();
            
               
            
        }        catch (Exception eex) {
            
                throw new RuntimeException("Erro com data nem sei oq e " +  eex.getMessage(),eex );
            
            
        }
        finally {
            
             connectionFactory.closeConnection(conn, statement);
             
        }
        
        
    }
    
    
    public void removeById (int taskId){
        // String com comando para busca no banco de Dados
        String sql = "DELETE FROM TASKS WHERE id=?";
        
        
        //Criando uma  conexao e preparando-a
        Connection conn= null;
        PreparedStatement statement= null;
        
        
        try {
            //Criacao da conexao com o banco de dados
            conn = connectionFactory.getConnection();
            //Preparando query
            statement = conn.prepareStatement(sql);
            
            //Setando os valores
            statement.setInt(1, taskId);
            
            //Executando a query
            statement.execute();
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro ao deletar do banco de dados " + e);
        }finally{
            
            connectionFactory.closeConnection(conn, statement);
        }
        
        
        
    }
    
    public List <task>  getAll(int idProject){
        
        String sql = "SELECT * FROM TASKS WHERE IDPROJECT = ?";
        
        Connection  conn  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List <task> tasks = new ArrayList<task>();
        
        try {
            //Lista de tarefas que sera devolvida quando a chamada metodo acontecer
            conn = connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                
                task task = new task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("IDPROJECT"));
                task.setName(resultSet.getString("NAME"));
                task.setDescription(resultSet.getString("DESCRIPTION"));
                task.setNotes(resultSet.getString("NOTES"));
                task.setIsCompleted(resultSet.getBoolean("COMPLETED"));
                task.setDeadline(resultSet.getDate("DEADLINE"));
                task.setCreatedAt(resultSet.getDate("CREATEDDATE"));
                task.setUpDateAt(resultSet.getDate("UPDATEDCREATE"));

                tasks.add(task);
            
            }
            
        } catch (Exception eex) {
            
                throw new RuntimeException("Erro com data nem sei oq e " +  eex.getMessage(),eex );
            
            
        } finally {
            
            connectionFactory.closeConnection(conn, statement, resultSet);
            
        }
            //lista de tarefas que foi criada e carreaga no banco de dados
        return tasks; 
        
        
    }
}
