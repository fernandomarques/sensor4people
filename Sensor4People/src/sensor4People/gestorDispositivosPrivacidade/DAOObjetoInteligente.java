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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sensor4People.Configuracoes;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente.Privacidade;
import sensor4People.gestorDispositivosPrivacidade.Recurso;
/*
 * Data Acess Object para a Tabela Objeto Inteligente.
 */
public class DAOObjetoInteligente {
	private final String URL = Configuracoes.DATABASE_URL,  
			NOME = Configuracoes.DATABASE_USER, SENHA = Configuracoes.DATABASE_PW; 
	private Connection con;  
	private Statement comando; 
	private static String TABLE = "objeto_inteligente";
	private static String KW = "keyword";
	private static String REC = "recursos";
	public void apagar(String id,String idUser) {    
		try {  
			comando.addBatch("DELETE FROM " + TABLE + " WHERE ID_Obj = '" + id  
					+ "' AND ID_User = '" + idUser +  "';");  
			comando.addBatch("DELETE FROM " + KW + " WHERE ID_Obj = '" + id  
					+ "' AND ID_User = '" + idUser +  "';");
			comando.addBatch("DELETE FROM " + REC + " WHERE ID_Obj = '" + id  
					+ "' AND ID_User = '" + idUser +  "';");
		} catch (SQLException e) {  
			System.out.println("Erro ao apagar objeto: "+ e.getMessage());  
		} 
	}  
	public void inserir(ObjetoInteligente oi){    
		try {  
			comando.addBatch("INSERT INTO " + TABLE + " (`ID_Obj`, `ID_User`, `tipo`, `publisbhSubscribe`, `IP`, `porta`, `Group_ID`) VALUES ('"  
					+ oi.getID() + "', '" +oi.getOwnerID() + "', '" 
					+ oi.getTipo()  + "','" + boolToInt(oi.isPublisbhSubscribe()) + "','" + oi.getIP() + "','"  
					+ oi.getPorta() + "','" + oi.getGroup_ID() + "');");  

			// inserir recursos
			Iterator<Recurso> it = oi.getRecursos().iterator();
			System.out.println("Inserindo Recursos");
			while(it.hasNext()){
				Recurso temp = it.next();
				adicionarRecurso(oi,temp);
			} 
			// inserir keywords  
			Iterator<String> nit = oi.getKeywords().iterator();
			System.out.println("Inserindo Keyword");
			while(nit.hasNext()){
				String temp = nit.next();
				adicionarKeyword(oi,temp);
			}
			System.out.println("Inserido!");

		} catch (SQLException e) {  
			System.out.println("Erro ao inserir Objeto: "+ e.getMessage());  
		} 
	}  
	public void atualizar(ObjetoInteligente oi) {  
		try{
			conectar();
			comando.executeUpdate("UPDATE " + TABLE + " SET tipo = '" + oi.getTipo()
					+ "', group_ID ='" + oi.getGroup_ID() + "', publisbhSubscribe = '"  
					+ boolToInt(oi.isPublisbhSubscribe()) + "', IP ='" + oi.getIP()  + "', porta ='" + oi.getPorta()
					+ "' WHERE  ID = '" + oi.getID()  +"' AND ID_Usuario = '" + oi.getOwnerID() + "';");


		} catch (SQLException e) {  
			e.printStackTrace();  
		} finally{
			fechar();
		}
	} 
	public void adicionarKeyword(ObjetoInteligente oi, String keyword){
		try {  
			comando.addBatch("INSERT INTO " + KW + " VALUES(0, '"+ keyword+"', '"+ oi.getID() + "', '"+ oi.getOwnerID() +"')");
		} catch (SQLException e) {  
			System.out.println("Erro ao inserir palavra:chave: "+ e.getMessage());  
		} 

	}  
	public void adicionarRecurso(ObjetoInteligente oi, Recurso recurso){ 
		try {  
			comando.addBatch(" INSERT INTO " + REC + " VALUES(0, '"+ recurso.getNome()+ "', '" + recurso.getPriv_get() 
					+ "', '" + recurso.getPriv_set()+ "', '"+ oi.getOwnerID() + "', '" + oi.getID() +"')");
		} catch (SQLException e) {  
			System.out.println("Erro ao inserir Pessoa"+ e.getMessage());  
		} 

	}
	public ObjetoInteligente buscar(String idObj, String idUser) {    
		ResultSet rs;  
		try {  
			conectar();
			rs = comando.executeQuery("SELECT * FROM " + TABLE + " WHERE ID_Obj = '"  
					+ idObj + "' AND ID_User = '" + idUser + "';");    
			ObjetoInteligente temp = new ObjetoInteligente();  
			List <Recurso> recs = new ArrayList<Recurso>();
			List <String>  pchaves = new ArrayList<String>(); 
			// pega todos os atributos da pessoa  
			if(rs.next() == false)
				return null;
			
			temp.setGroup_ID(rs.getString("group_ID"));
			temp.setOwnerID(rs.getString("ID_User"));
			temp.setID(rs.getString("ID_Obj"));
			temp.setIP(rs.getString("IP"));
			temp.setPorta(rs.getInt("porta"));
			temp.setPublisbhSubscribe(rs.getBoolean("publisbhSubscribe"));
			temp.setTipo(rs.getString("tipo")); 
			
			// povoar os recursos
			rs =  comando.executeQuery("SELECT * FROM " + REC + " WHERE IDObj = '"  
					+ idObj + "' AND IDUser = '" + idUser + "';");
			while(rs.next()){
				Recurso rec = new Recurso();
				rec.setNome(rs.getString("recurso"));
				rec.setPriv_get(ObjetoInteligente.getPrivacidade(rs.getString("priv_get")));
				rec.setPriv_set(ObjetoInteligente.getPrivacidade(rs.getString("priv_set")));
				recs.add(rec);
			}
			temp.setRecursos(recs);
			
			
			rs =  comando.executeQuery("SELECT * FROM " + KW + " WHERE ID_Obj = '"  
					+ idObj + "' AND ID_User = '" + idUser + "';");
			while(rs.next()){
				pchaves.add(rs.getString("keyword"));
			}
			temp.setKeywords(pchaves);
			
			return temp;  
		} catch (SQLException e) {  
			System.out.println("Erro ao buscar pessoa: "+ e.getMessage());  
			return null;  
		}  finally{
			fechar();
		}

	}  
	public List<ObjetoInteligente> buscarKeyword(String keyword) {
		List<ObjetoInteligente> resultados = new ArrayList<ObjetoInteligente>();  
		List<String> IDs = new ArrayList<String>();
		ResultSet rs;  
		try {
			conectar();
			rs = comando.executeQuery("SELECT * FROM " + KW + " WHERE keyword = '"  
					+ keyword + "';");  
			while (rs.next()) {  
				String temp = new String((rs.getString("ID_Obj")));
				temp += ":" + rs.getString("ID_User");
				IDs.add(temp);  

			} 
			if(IDs.size() > 0){
				Iterator<String> it = IDs.iterator();
				while(it.hasNext()){
					String str = it.next();
					String ids[] = str.split(":");
					resultados.add(buscar(ids[0],ids[1]));
				}
			}
			return resultados;  
		} catch (SQLException e) {  
			System.out.println("Erro ao buscar keyword: "+ e.getMessage());  
			return null;  
		} finally {
			fechar();
		}

	}  
	public void conectar() {  
		try {  
			con = ConFactory.conexao(URL, NOME, SENHA, ConFactory.MYSQL);  
			comando = con.createStatement();  
		//	System.out.println("Conectado!");  
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver"+ e.getMessage());  
		} catch (SQLException e) {
			System.out.println("Erro ao conectar" + e.getMessage());  
		}  
	}  
	public void fechar() {  
		try {  
			comando.close();  
			con.close();  
		//	System.out.println("Conexão Fechada");  
		} catch (SQLException e) {  
			System.out.println("Erro ao fechar conexão: "+ e.getMessage());  
		}  
	} 
	public void send(){
		try {
			comando.executeBatch();
		} catch (SQLException e) {
			System.out.println("Erro ao enviar SQL");
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
	private int boolToInt(boolean publisbhSubscribe) {
		if(publisbhSubscribe)
			return 1;
		else
			return 0;
	}
	@SuppressWarnings("unused")
	private boolean intToBool(int publisbhSubscribe) {
		if(publisbhSubscribe == 1)
			return true;
		else
			return false;
	}
	public static void main(String[] args) {
		testBuscar();

	}
	@SuppressWarnings("unused")
	public static void testBuscar(){
		DAOObjetoInteligente dao = new DAOObjetoInteligente();
		ObjetoInteligente oi = dao.buscar("SENSOR", "100000078963029") ;
		
		System.out.println("");
	}
	public static void testes(){
		DAOObjetoInteligente dao = new DAOObjetoInteligente();
		ObjetoInteligente oi = new ObjetoInteligente();
		// List<ObjetoInteligente> ois;
		oi.setID("SENSOR");
		oi.setOwnerID("100000078963029");
		oi.setGroup_ID("");
		oi.setIP("localhost");
		oi.setPorta(5050);
		oi.setPublisbhSubscribe(false);
		oi.setTipo("CoAP");
		//dao.inserir(oi);
		//ois = dao.buscarKeyword("UFES");
		//dao.apagar("sensor", "Fernando");
		List<Recurso> recs = new ArrayList<Recurso>();
		Recurso rec = new Recurso();
		rec.setNome("temperatura"); rec.setPriv_get(Privacidade.ALL); rec.setPriv_set(Privacidade.ALL);
		recs.add(rec);

		List<String> kw = new ArrayList<String>();
		kw.add(new String("UFES"));kw.add(new String("VIX"));
		oi.setRecursos(recs);
		oi.setKeywords(kw);
		dao.conectar();
		dao.inserir(oi);
		dao.send();
		//dao.adicionarRecurso(oi, rec);		
	}
}
