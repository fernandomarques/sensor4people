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
package sensor4people.pages.index.client.model;

import java.io.Serializable;
import java.util.List;

public class CadastroObjeto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ID;
	private String ownerID;
	private List<String> recursos;
	private List<Integer> priv_get;
	private List<Integer> priv_set;
	private String tipo; // informação é utilizada pelo driver
	private List<String> keywords;
	private boolean publisbhSubscribe;
	private String IP;
	private int Porta;
	private String Group_ID;
	
	public void print(){
		System.out.println("ID: "+ ID);
		System.out.println("Tipo: "+ tipo);
		System.out.println("PubSub: "+ publisbhSubscribe);
		System.out.println("IP: "+ IP);
		System.out.println("Porta: "+ Porta);
		System.out.println("--- Keywords ---");
		for(int i=0;i<keywords.size();i++){
			System.out.println("i="+i+":"+keywords.get(i));
		}
		System.out.println("--- End-Keywords ---");
		
		System.out.println("--- Recursos ---");
		for(int i=0;i<recursos.size();i++){
			System.out.println("Recurso="+recursos.get(i)+":"+priv_get.get(i)+":"+priv_set.get(i));
		}
		System.out.println("--- End-Recursos ---");
	}
	
	public CadastroObjeto() {
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

	public List<String> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<String> recursos) {
		this.recursos = recursos;
	}

	public List<Integer> getPriv_get() {
		return priv_get;
	}

	public void setPriv_get(List<Integer> priv_get) {
		this.priv_get = priv_get;
	}

	public List<Integer> getPriv_set() {
		return priv_set;
	}

	public void setPriv_set(List<Integer> priv_set) {
		this.priv_set = priv_set;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
