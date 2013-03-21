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
package sensor4people.pages.index.client.service;



import sensor4people.pages.index.client.model.CadastroObjeto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Cadastrar")
public interface Cadastrar extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static CadastrarAsync instance;
		public static CadastrarAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(Cadastrar.class);
			}
			return instance;
		}
	}
	boolean cadastrar(CadastroObjeto oi);
}
