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
package sensor4People.InterfaceComRedeSocial;

import sensor4People.gestorRequisição.Informacao;
/**
 * 
 * @author Fernando
 * Classe Dummy que sempre retorna true para 'perguntas' e imprime no terminal o valor recebido
 * Feita para testes
 */
public class DummyRedeSocial implements IDriverRedeSocial {

	@Override
	public void logar() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean amigoDe(String u1, String u2) {
		return true;

	}

	@Override
	public boolean parteDe() {
		return true;

	}

	@Override
	public void postar(Informacao info) {
		System.out.println("-----     Interface com Rede social     -----");
		System.out.println("Valor:" + info.getValor() + "\tNome:" + info.getIDUser());
		System.out.println("Prop/Obj ID:"+ info.getIDProp()+ info.getIDObj());
		System.out.println("Tipo:" + info.getTipo() + "Recurso" + info.getRecurso());
		System.out.println("---------------------------------------------");
		// TODO Auto-generated method stub

	}

}
