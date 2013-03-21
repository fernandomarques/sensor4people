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


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;


public class CadastrarCallback implements AsyncCallback<Boolean> {



	public CadastrarCallback() {
		
	}

	public void onFailure(Throwable caught) {
		RootPanel rootPanel = RootPanel.get();
		Label lblResposta = new Label("Falha ao cadastrar");
		rootPanel.add(lblResposta);
	
	}

	@Override
	public void onSuccess(Boolean result) {
		Window.alert("Cadastro realizado com sucesso");
	}


}
