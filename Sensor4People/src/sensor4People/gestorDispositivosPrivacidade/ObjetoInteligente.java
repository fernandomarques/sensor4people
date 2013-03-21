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

import java.util.List;

public class ObjetoInteligente {
	public enum Privacidade{
		ALL,FRIENDS,GROUP,OWNER
	}
	// Identificador Unico do Objeto
	private String ID;    
	// Identificador do dono do Objeto
	private String ownerID; 
	// Lista dos recursos exitentes no objeto
	private List<Recurso> recursos;
	// Qual é o protocolo do Objeto? CoAP? informação é utilizada pelo driver
	private String tipo;  
	// Palavras chaves ao qual o objeto responde. Ex. UFES, LPRM. (Usado em requisições complexas)
	private List<String> keywords;  
	// O Objeto dá suporte ao publish subscribe?
	private boolean publisbhSubscribe;
	// IP do Objeto
	private String IP; 
	// Porta de acesso ao Objeto
	private int Porta;  
	// Tipo de Privacidade de acesso ao objeto
	private String Group_ID; 

	public static Privacidade getPrivacidade(String priv){
		if(priv.equalsIgnoreCase("ALL")){
			return Privacidade.ALL;
		}else if(priv.equalsIgnoreCase("FRIENDS")){
			return Privacidade.FRIENDS;
		}else if(priv.equalsIgnoreCase("GROUP")){
			return Privacidade.GROUP;
		}else if(priv.equalsIgnoreCase("OWNER")){
			return Privacidade.OWNER;
		}else
			return null;
	}

	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		ID = iD;
	}



	public String getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public List<Recurso> getRecursos() {
		return recursos;
	}

	public Recurso getRecurso(String rec){
		for(int i=0; i< recursos.size(); i++){
			if(recursos.get(i).getNome().equalsIgnoreCase(rec))
				return recursos.get(i);
		}
		return null;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public List<String> getKeywords() {
		return keywords;
	}



	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}



	public boolean isPublisbhSubscribe() {
		return publisbhSubscribe;
	}



	public void setPublisbhSubscribe(boolean publisbhSubscribe) {
		this.publisbhSubscribe = publisbhSubscribe;
	}



	public String getIP() {
		return IP;
	}



	public void setIP(String iP) {
		IP = iP;
	}



	public int getPorta() {
		return Porta;
	}



	public void setPorta(int porta) {
		Porta = porta;
	}



	public String getGroup_ID() {
		return Group_ID;
	}



	public void setGroup_ID(String group_ID) {
		Group_ID = group_ID;
	}
}
