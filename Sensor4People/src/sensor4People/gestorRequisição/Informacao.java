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

public class Informacao extends Requisicao { 
	
	private String valor;
	
	public Informacao(Requisicao req, String valor){
		super(req.getIDObj(),req.getIDProp(),req.getIDUser(),req.getTipo(),req.getKeyword(),
				req.getRecurso(),req.getCombinacao(),req.getLimite());
		this.setOi(req.getOi());
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor){
		this.valor = valor;
	}
	
}
