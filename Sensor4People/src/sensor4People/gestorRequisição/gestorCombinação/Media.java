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

import java.util.List;

import sensor4People.gestorRequisição.Informacao;

public class Media implements ICombinador {

	@Override
/**
 * (non-Javadoc)
 * @see sensor4People.gestorRequisição.gestorCombinação.ICombinador#combinar(java.util.List)
 * Exemplo de combinação onde a média dos valores é retornada
 */
	public Informacao combinar(List<Informacao> infos) {
		
		int media=0;
		int i=0;
		for(i=0;i<infos.size();i++){
			media += Integer.decode(infos.get(i).getValor());
		}
		
		media = media/i;
		infos.get(0).setValor(media+"");
		return (infos.get(0));

	}

}
