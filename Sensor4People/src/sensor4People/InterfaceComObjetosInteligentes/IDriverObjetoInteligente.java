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

import sensor4People.gestorRequisição.Informacao;

public  abstract class IDriverObjetoInteligente {
	protected Informacao info;
	public IDriverObjetoInteligente(Informacao info) {
		this.info = info;
	}
	/**
	 * @param IP IP:porta do objeto a ser requisitado
	 * @param Resource Recurso a ser requisitao
	 * @return Chamada ao gestor de Requisição com o valor recebido
	 */
	public abstract void get(String IP,String Resource);
	/**
	 * @param IP IP:porta do objeto a ser requisitado
	 * @param Resource Recurso a ser requisitao
	 * @return Chamada ao gestor de Requisição com o valor recebido
	 * GET e PUT funcionam de forma similar, a diferença é que suas privacidades podem ser diferentes (PUT = SET) 
	 */
	public abstract boolean put(String IP,String resource,String Payload);
	/**
	 * Observa o recurso, através de pooling se não existir suporte a observe
	 * @param IP IP:porta do objeto a ser observado
	 * @param Resource Recurso a ser observado
	 */
	public abstract void observe(String IP,String Resource);
}
