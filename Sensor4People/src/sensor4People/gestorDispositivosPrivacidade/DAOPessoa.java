/*******************************************************************************
 * Copyright (c) 2013 Fernando Antonio Marques Filho email: fernando.marquesfilho@gmail.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Fernando Antonio Marques Filho - initial API and implementation
 ******************************************************************************/
package sensor4People.gestorDispositivosPrivacidade;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sensor4People.Configuracoes;
/*
 * Data Acess Object para a tabela Pessoa
 */
public class DAOPessoa {
	private final String URL = Configuracoes.DATABASE_URL,  
			NOME = Configuracoes.DATABASE_USER, SENHA = Configuracoes.DATABASE_PW; 
	 private Connection con;  
	 private Statement comando; 
	 private static String TABLE = "Usuario";
	 public void apagar(String id) {  
	      conectar();  
	      try {  
	         comando  
	               .executeUpdate("DELETE FROM " + TABLE + " WHERE ID = '" + id  
	                     + "';");  
	      } catch (SQLException e) {  
	    	  System.out.println("Erro ao apagar objeto"+ e.getMessage());  
	      } finally {  
	         fechar();  
	      }  
	   }  
	   public void inserir(Pessoa p){  
		      conectar();  
		      try {  
		         comando.executeUpdate("INSERT INTO " + TABLE + " VALUES('"  
		               + p.getID() + "', '" + p.getRedeSocial() + "','"  
		               + p.getToken() + "')");  
		         
		         System.out.println("Inserida!");
		         
		      } catch (SQLException e) {  
		    	  System.out.println("Erro ao inserir Pessoa"+ e.getMessage());  
		      } finally {  
		         fechar();  
		      }  
		   }  
	   public void atualizar(Pessoa p) {  
		      conectar();  
		      try{
		    	  comando.executeUpdate("UPDATE " + TABLE + " SET redesocial = '" + p.getRedeSocial()
		    	  + "', token = '" + p.getToken()  + "';");    
		      } catch (SQLException e) {  
		         e.printStackTrace();  
		      } finally {  
		         fechar();  
		      }  
		   } 
	   public Pessoa buscar(String id) {  

		   conectar();    
		   ResultSet rs;  
		   try {  
			   rs = comando.executeQuery("SELECT * FROM " + TABLE + " WHERE ID = '"  
					   + id + "';");    
			   Pessoa temp = new Pessoa();
			   if(!rs.next())
				   return null;

			   // pega todos os atributos da pessoa  
			   temp.setToken(rs.getString("token"));
			   temp.setID(rs.getString("ID"));
			   temp.setRedeSocial(rs.getString("redesocial")); 
			   return temp;  
		   } catch (SQLException e) {  
			   System.out.println("Erro ao buscar pessoa: "+ e.getMessage());  
			   return null;  
		   }  

	   }  

	     
	   private void conectar() {  
		      try {  
		         con = ConFactory.conexao(URL, NOME, SENHA, ConFactory.MYSQL);  
		         comando = con.createStatement();  
		     //    System.out.println("Conectado!");  
		      } catch (ClassNotFoundException e) {  
		    	  System.out.println("Erro ao carregar o driver"+ e.getMessage());  
		      } catch (SQLException e) {  
		    	  System.out.println("Erro ao conectar" + e.getMessage());  
		      }  
		   }  
	   
	   private void fechar() {  
		      try {  
		         comando.close();  
		         con.close();  
		//         System.out.println("Conexão Fechada");  
		      } catch (SQLException e) {  
		    	  System.out.println("Erro ao fechar conexão"+ e.getMessage());  
		      }  
		   } 
	   
	   
	   public static void main(String[] args) {
		  // DAOPessoa dao = new DAOPessoa();
		   //Teste inserir
/*		   Pessoa p = new Pessoa();
		   p.setID("fernando");
		   p.setRedeSocial("facebook");
		   p.setToken("123@#$%");
		   dao.inserir(p);
*/
		   // Teste buscar
		 /*  Pessoa p;
		   p = dao.buscar("fernando");
		   System.out.println(p.getToken());
		   */
		   
		   // Teste update
		 /*  Pessoa p = new Pessoa();
		   p.setID("fernando");
		   p.setRedeSocial("orkut");
		   p.setToken("543@#$%");
		   dao.atualizar(p);*/
		   
		   // Teste apagar
		   //dao.apagar("fernando");
	}
	   
		   
}
