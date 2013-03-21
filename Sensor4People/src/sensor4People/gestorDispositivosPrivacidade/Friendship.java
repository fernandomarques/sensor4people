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
package sensor4People.gestorDispositivosPrivacidade;

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

public class Friendship extends NamedFacebookType {
	/**
	 *   Utilizado pela Interface com Rede Social, é um tipo para o facebook
	 */
	private static final long serialVersionUID = -7491461793024495391L;
	@Facebook("data")
	private String data;
	
	public String getData() {
		return data;
	}
}
