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

import java.util.Hashtable;

import sensor4People.gestorDispositivosPrivacidade.GestorDeDispositivosPrivacidade;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente;
import sensor4People.gestorRequisição.Informacao;
import sensor4People.gestorRequisição.Requisicao;
import sensor4People.gestorRequisição.Requisicao.Tipo;

/**
 * @author Fernando Antonio Marques Filho
 *
 */
public class InterfaceComObjetosInteligentes {
	
	private static Hashtable<String, Monitor> list = new Hashtable<String,Monitor>();
	
	/**
	 * @param req Requisição
	 * @return Retorna se uma requisição existe ou não no monitor
	 */
	public static boolean exists(Requisicao req){
		String key = req.getHash();
		Monitor m = list.get(key);
		if(m == null)
			return false;
		else
			return true;
	}
	
	/**
	 * @param req Requisiçao a se subscrever
	 */
	public static void subscrever(Requisicao req){
		String key = req.getHash();
		Monitor m =	list.get(key);
		if(m  == null){
			m = new Monitor();
			list.put(key,m);
		}
		m.add(req);
	}
	
	/**
	 * @param key Chave de uma requisição que será cancelada
	 */
	public static void cancelarSubscricao(String key){
		Monitor m = list.get(key);
		if(m != null){
			m.stop();
			list.remove(key);
		}
	}
	/**
	 * @param req Requisiçao requisitada
	 */
	public static void request(Requisicao req){
		//FIXME adicionar o tipo de operacao que será realizada no request GET/PUT/OBSERVE
		// pegar IP, etc
		ObjetoInteligente oi = req.getOi();
		IDriverObjetoInteligente driver;
		Informacao info = new Informacao(req,"");
		driver = getDriver(info);
		if(req.getTipo() == Tipo.subscrição || req.getTipo() == Tipo.subscrição_complexa){
			if(oi.isPublisbhSubscribe())
				driver.observe(GestorDeDispositivosPrivacidade.IDtoIP(oi),req.getRecurso());
			else
				driver.get(GestorDeDispositivosPrivacidade.IDtoIP(oi),req.getRecurso());
		}else
			driver.get(GestorDeDispositivosPrivacidade.IDtoIP(oi),req.getRecurso());
	}
	
	/**
	 * @param info Informação
	 * @return O driver adequado para lidar o tipo de Objeto Inteligente
	 */
	private static IDriverObjetoInteligente getDriver(Informacao info){
		// TODO
		// gestorDeDispositivosPrivacidade.getSOProtocol(IDObjeto);
		if(info.getOi().getTipo().equalsIgnoreCase("CoAP")){
			return new CoAPDriver(info);
		}
		return new DummyObjetoInteligente(info);

	}
}
