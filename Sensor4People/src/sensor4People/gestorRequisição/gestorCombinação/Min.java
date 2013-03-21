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
package sensor4People.gestorRequisição.gestorCombinação;

import java.util.Iterator;
import java.util.List;

import sensor4People.gestorRequisição.Informacao;

public class Min implements ICombinador {

	@Override
	/**
	 * (non-Javadoc)
	 * @see sensor4People.gestorRequisição.gestorCombinação.ICombinador#combinar(java.util.List)
	 * Exemplo de combinação onde o menor valor é retornado
	 */
	public Informacao combinar(List<Informacao> infos) {
		Iterator<Informacao> it = infos.iterator();
		int min;
		Informacao info;
		int i;
		info = it.next();
		i = infos.indexOf(info);
		min = Integer.decode(info.getValor());
		while(it.hasNext()){
			info = it.next();
			if(Integer.decode(info.getValor()) < min){
				min = Integer.decode(info.getValor());
				i = infos.indexOf(info);
			}
		}
		return (infos.get(i));
	}



}
