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

import sensor4People.gestorRequisição.Requisicao;

public class Pooler extends Thread {
	private Requisicao req;
	
	public Pooler(Requisicao req){
		this.req = req;
	}
	
	public void run(){
		// TODO verificar se o objeto dá suporte a publish subscribe
		boolean PublishSubscribe = false;
		while(!PublishSubscribe){
			new Thread(){
				public void run(){
					InterfaceComObjetosInteligentes.request(req);
				}
			}.start();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// mandar msg, abrir porta e blablabla
		InterfaceComObjetosInteligentes.request(req);
	}
}
