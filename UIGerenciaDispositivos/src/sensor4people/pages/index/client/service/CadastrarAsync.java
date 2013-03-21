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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CadastrarAsync {
	void cadastrar(CadastroObjeto oi, AsyncCallback<Boolean> callback);
}
