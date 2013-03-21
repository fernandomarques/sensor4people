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

import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente.Privacidade;


// Classe que representa um recurso (de um objeto inteligente)
public class Recurso {
	// Nome do Recurso
	private String nome;
	// Privacidade para acessos 'GET'
	private Privacidade priv_get;
	// Privacidade para acessos 'SET'
	private Privacidade priv_set;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Privacidade getPriv_get() {
		return priv_get;
	}
	public void setPriv_get(Privacidade priv_get) {
		this.priv_get = priv_get;
	}
	public Privacidade getPriv_set() {
		return priv_set;
	}
	public void setPriv_set(Privacidade priv_set) {
		this.priv_set = priv_set;
	}

}
