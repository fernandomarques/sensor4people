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
package sensor4People.gestorRequisição;

import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente;

public class Requisicao {
	private String IDObj;
	private String IDProp;
	private String IDUser;
	private Tipo tipo;
	private String keyword;
	private String recurso;
	private String combinacao; 
	private int limite;
	private int IDRequisicao;
	private ObjetoInteligente oi;
	
	public Requisicao clone(){
		Requisicao req = new Requisicao(IDObj,IDProp,IDUser,tipo,keyword,recurso,combinacao,limite,IDRequisicao);
		req.oi = this.oi;
		return req;
		
	}
	public Requisicao(String IDObj, String IDProp, String IDUser,Tipo tipo, String keyword,
			String recurso,String combinacao, int limite) {
		this.IDObj = IDObj;
		this.IDProp = IDProp;
		this.IDUser = IDUser;
		this.tipo = tipo;
		this.keyword = keyword;
		this.recurso = recurso;
		this.combinacao = combinacao;
		this.limite = limite;
	}
	public Requisicao(String IDObj, String IDProp, String IDUser,String tipo, String keyword,
			String recurso,String combinacao, int limite) {
		this.IDObj = IDObj;
		this.IDProp = IDProp;
		this.IDUser = IDUser;

		if(tipo.equalsIgnoreCase("complexa")){
			this.tipo = Tipo.complexo;
		}
		if(tipo.equalsIgnoreCase("subscrição")){
			this.tipo = Tipo.subscrição;
		}
		if(tipo.equalsIgnoreCase("subscrição complexa")){
			this.tipo = Tipo.subscrição_complexa;
		}else
			this.tipo = Tipo.simples;
	
		this.keyword = keyword;
		this.recurso = recurso;
		this.combinacao = combinacao;
		this.limite = limite;
	}
	
	public Requisicao(String IDObj, String IDProp, String IDUser,
			Tipo tipo, String keyword, String recurso, String combinacao,
			int limite, int IDRequisicao) {
		this.IDObj = IDObj;
		this.IDProp = IDProp;
		this.IDUser = IDUser;
		this.tipo = tipo;
		this.keyword = keyword;
		this.recurso = recurso;
		this.combinacao = combinacao;
		this.limite = limite;
		this.IDRequisicao = IDRequisicao;
	}
	public String getHash(){
		return (IDUser + keyword + recurso + combinacao + IDRequisicao);
	}
	public String getHash2(){
		return (IDProp + IDObj);
	}
	
	
	public enum Tipo { simples , complexo, subscrição, subscrição_complexa }
	
	// getters and setters
	public String getIDObj() {
		return IDObj;
	}

	public void setIDObj(String iDObj) {
		IDObj = iDObj;
	}

	public String getIDProp() {
		return IDProp;
	}

	public void setIDProp(String iDProp) {
		IDProp = iDProp;
	}

	public String getIDUser() {
		return IDUser;
	}

	public void setIDUser(String iDUser) {
		IDUser = iDUser;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getCombinacao() {
		return combinacao;
	}
	public void setCombinacao(String combinacao) {
		this.combinacao = combinacao;
	}
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	public int getIDRequisicao() {
		return IDRequisicao;
	}
	public void setIDRequisicao(int iDRequisicao) {
		IDRequisicao = iDRequisicao;
	}
	public ObjetoInteligente getOi() {
		return oi;
	}
	public void setOi(ObjetoInteligente oi) {
		this.oi = oi;
	}

}
