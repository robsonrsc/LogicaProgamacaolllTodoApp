/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.connectionFactory;

/**
 *
 * @author robson
 */
public class ProjectController {
    
     public void save ( Project project) throws SQLException {
        
        String sql = "INSERT INTO projecs(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //Cria uma conex�o com o banco
            conn = connectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, project.getName());
            stmt.setString(2, project.getDescription());
            stmt.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            stmt.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));

            //Executa a sql para inser��o dos dados
            stmt.execute();
        } catch (SQLException ex) {
            throw new SQLException("Erro ao salvar o projeto", ex);
        } finally {
            //Fecha as conex�es
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        
        
        
    }
    
    public void update (Project project) throws SQLException{
        
        String sql = "UPDATE projecs  SET "
                + "name= ? ,"
                + " description = ?, "
                + "createdAt = ?,"
                + "updatedAt = ? "
                + "WHERE ID = ?";
        
        
        Connection conn= null;
        PreparedStatement statement= null;
        
        try {
            //Estabelecendo conexao com o Banco de Dados
            conn = connectionFactory.getConnection();
            //Preparando a query
             statement = conn.prepareStatement(sql);
             //Setando os valores do statement
                         
             statement.setString(1, project.getName());
             
             statement.setString(2 ,project.getDescription());
            statement.setDate(3, new Date (project.getCreatedAt().getTime()));
             statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
             statement.setInt(5, project.getId()); 

             
             //Executando a query
             statement.execute();
            
               
            
        } catch (SQLException e) {
            
           throw new SQLException("Erro ao Salvar" + e);
            
            
        }
        finally {
            
             connectionFactory.closeConnection(conn, statement);
             
             if (statement !=null){statement.close(); }
        }
        
        
    }
    
    
    public void removeById (int Id){
        // String com comando para busca no banco de Dados
        String sql = "DELETE FROM projecs WHERE id=?";
        
        
        //Criando uma  conexao e preparando-a
        Connection conn= null;
        PreparedStatement statement= null;
        
        
        try {
            //Criacao da conexao com o banco de dados
            conn = connectionFactory.getConnection();
            //Preparando query
            statement = conn.prepareStatement(sql);
            
            //Setando os valores
            statement.setInt(1, Id);
            
            //Executando a query
            statement.execute();
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Erro ao deletar do banco de dados");
        }finally{
            
            connectionFactory.closeConnection(conn, statement);
        }
        
        
        
    }
    
    public List <Project>  getAll (){
        
        String sql = "SELECT * FROM projecs ";
         List <Project> projects = new ArrayList<>();

        
        
        Connection  conn  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        
        try {
            //Lista de tarefas que sera devolvida quando a chamada metodo acontecer
            conn = connectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                
                Project  project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                projects.add(project);
            
            }
            
        } catch (SQLException e) {
            
                 throw new RuntimeException("Erro ao retornar valores do banco de dados " + e);
        } finally {
            
            connectionFactory.closeConnection(conn, statement, resultSet);
            
        }
            //lista de tarefas que foi criada e carreaga no banco de dados
        return projects; 
        
        
    }
    
    
}
