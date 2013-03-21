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
package sensor4People.InterfaceComObjetosInteligentes;

import java.util.Random;

import sensor4People.gestorRequisição.GestorDeRequisição;
import sensor4People.gestorRequisição.Informacao;

public class DummyObjetoInteligente extends IDriverObjetoInteligente {
	/**
	 * Classe Dummy que não acessa nenhum objeto inteligente, e retorna valores fixos quando requisitada.
	 * Criada para testes
	 */
	public DummyObjetoInteligente(Informacao info) {
		super(info);
	}

	@Override
	public void get(String IP,String Resource) {
		Random gen = new Random();
		int value = gen.nextInt(20);
		//value += 5;
		System.out.println(value);
		info.setValor(value+"");
		GestorDeRequisição.GdR.tratarInformação(info);
	}

	@Override
	public boolean put(String IP,String resource,String Payload) {
		return true;

	}

	@Override
	public void observe(String IP, String Resource) {
		// TODO Auto-generated method stub
		
	}

}
