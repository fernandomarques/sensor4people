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
package sensor4People.gestorRequisição.gestorSubscrição;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sensor4People.gestorDispositivosPrivacidade.DAOObjetoInteligente;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente;
import sensor4People.gestorRequisição.Informacao;
import sensor4People.gestorRequisição.Requisicao;
import sensor4People.gestorRequisição.gestorCombinação.GestorDeCombinação;
import sensor4People.InterfaceComObjetosInteligentes.*;

public class GestorDeSubscrição {

	public GestorDeSubscrição(){
	}
	/**
	 * @param req Requisição a ser recebida
	 */
	public void receberRequisição(Requisicao req){
// Se for complexa quebra a requisição em varias e se subscreve a elas
		if(req.getTipo() == Requisicao.Tipo.subscrição_complexa){
			List<Requisicao> reqs = new ArrayList<Requisicao>();
			reqs = GestorDeCombinação.GdC.receberRequisição(req);

			Iterator<Requisicao> it = reqs.iterator();
			while(it.hasNext()){
				InterfaceComObjetosInteligentes.subscrever(it.next());
			}
// Caso contrario se subscreve a uma única requisição
		}else{
			DAOObjetoInteligente dao = new DAOObjetoInteligente();
			ObjetoInteligente oi = dao.buscar(req.getIDObj(), req.getIDProp());
			if( oi == null)// não existe o objeto
				return ;
			req.setOi(oi);
			InterfaceComObjetosInteligentes.subscrever(req);
		}
	}
	/**
	 * param info Informação subscrita
	 * return Caso o valor seja maior ou igual ao limite retorna o valor e cancela a subscrição
	 * caso contrário retorna nulo
	 */
	public Informacao receberInformação(Informacao info){
		if(Integer.decode(info.getValor()) >= info.getLimite()){
			InterfaceComObjetosInteligentes.cancelarSubscricao(info.getHash());
			return info;
		}
		else return null;
	}
	/**
	 * Cancela a subscrição a req
	 * @param req Requsição a ser cancelada
	 */
	public void cancelarSubscrição(Requisicao req){
		InterfaceComObjetosInteligentes.cancelarSubscricao(req.getHash());
	}

}
