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

public interface ICombinador {
	/**
	 * Interface combinador
	 * @param infos Informações que devem ser combinadas
	 * return A combinação das informações
	 */
	public Informacao combinar(List<Informacao> infos);

}
