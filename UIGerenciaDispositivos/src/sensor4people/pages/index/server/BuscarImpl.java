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
package sensor4people.pages.index.server;

import sensor4people.pages.index.client.model.BuscaObjeto;
import sensor4people.pages.index.client.service.Buscar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BuscarImpl extends RemoteServiceServlet implements Buscar {

	/**
	 *   TODO Classe não implementada
	 */
	private static final long serialVersionUID = 5723973279765611625L;

	@Override
	public String buscar(BuscaObjeto bo) {
		System.out.println(bo.toString());
		//Requisicao req = new Requisicao(bo.getIDObj(),bo.getIDOwner(),bo.getToken(),bo.getTipo(),bo.getKW(),bo.getRec(),
			//	null,Integer.decode(bo.getLimite()));
		
		//GestorDeRequisição GDR = new GestorDeRequisição();
		//GDR.tratarRequisição(req);
		return null;
	}
}
