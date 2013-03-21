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

public class Combinador implements ICombinador{
	/**
	 * "Proxy" do combinador. Usado para pegar de forma dinamica a classe mais adequada para fazer a combinação
	 * TODO Caso seja criado um novo combinador, é aconselhavel adiciona-lo a getCombinador
	 * @param info A Informação será combinada
	 * @return A classe a qual a combinação será delegada
	 */
	private ICombinador getCombinador(Informacao info){
		String comb =info.getCombinacao();
		if(comb.equalsIgnoreCase("Menor")){
			return (new Min());
		
		}else if(comb.equalsIgnoreCase("Maior")){
			return (new Max());
		}else
			return (new Media());
	}
	/**
	 * @see sensor4People.gestorRequisição.gestorCombinação.ICombinador#combinar(java.util.List)
	 * @param info A Informação a ser combinada
	 * @return A nova informação, já com o valor devidamente combinado
	 */
	public Informacao combinar(List<Informacao> infos){
		ICombinador combinador;
		combinador = getCombinador(infos.get(0)); 
		return combinador.combinar(infos);
	}
}
