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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sensor4People.gestorRequisição.Requisicao;

public class Monitor {
	private List<Thread> list = new ArrayList<Thread>();
	// Implementação simples de um monitor controla os diversos poolers
	public void add(Requisicao req){
		Thread t = new Pooler(req);
		list.add(t);
		t.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop(){
		Iterator<Thread> it = list.iterator();
		while(it.hasNext()){
			it.next().stop();
		}
	}
}
