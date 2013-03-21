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

public class BuscaObjeto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 926508355135386192L;
	
	private String Token;
	private String IDOwner;
	private String IDObj;
	private String Rec;
	private String KW;
	private String Limite;
	private String Tipo;

	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		this.Token = token;
	}
	public String getIDOwner() {
		return IDOwner;
	}
	public void setIDOwner(String iDOwner) {
		IDOwner = iDOwner;
	}
	public String getIDObj() {
		return IDObj;
	}
	public void setIDObj(String iDObj) {
		IDObj = iDObj;
	}
	public String getRec() {
		return Rec;
	}
	public void setRec(String rec) {
		Rec = rec;
	}
	public String getKW() {
		return KW;
	}
	public void setKW(String kW) {
		KW = kW;
	}
	public String getLimite() {
		return Limite;
	}
	public void setLimite(String limite) {
		Limite = limite;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "BuscaObjeto [Token=" + Token + "\n, IDOwner=" + IDOwner
				+ "\n, IDObj=" + IDObj + ", Rec=" + Rec + "\n, KW=" + KW
				+ "\n, Limite=" + Limite + "\n, Tipo=" + Tipo + "]";
	}

}
