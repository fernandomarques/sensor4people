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

public abstract class InterfaceComRedeSocial {
	private static IDriverRedeSocial getDriver(String IDUser){
		return new FacebookRedeSocial();
		//return new DummyRedeSocial();
		
	}
	public static void logar(){
		getDriver("").logar();
	}
	public static boolean amigoDe(String idOwner, String idUser){
		return getDriver("").amigoDe(idOwner,idUser);
		
	}
	public static boolean parteDe(){
		return true;
		//return getDriver("").amigoDe();
	}
	public static void postar(Informacao info){
		getDriver("").postar(info);
	}
}
