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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sensor4People.gestorDispositivosPrivacidade.*;
import sensor4People.gestorRequisição.Buffer;
import sensor4People.gestorRequisição.Informacao;
import sensor4People.gestorRequisição.Requisicao;

/**
 * Gestor responsável por gerenciar o Buffer, receber requisições encaminhadas pelo Gestor de Requisições e informações encaminhados pelo objeto
 * inteligente
 */
public class GestorDeCombinação {
	// utilizado para agrupar as respostas as requisições geradas em receberRequisição, a incrimentado quando uma nova requisição é feita
	private static int IDReq=0; 
	public static GestorDeCombinação GdC;
	
	public GestorDeCombinação(){
		GestorDeCombinação.GdC = this;
	}
	/** O Papel da função é apenas quebrar uma requisição em várias, e não as requerer. 
	 * @param req A requisição a ser quebrada em várias
	 * @return A lista de requisições que devem ser feitas
	 */
	public List<Requisicao> receberRequisição(Requisicao req){
		req.setIDRequisicao(IDReq++);
		if(IDReq < 0)
				IDReq = 0;
		List<ObjetoInteligente> ois = new ArrayList<ObjetoInteligente>();
		List<Requisicao> reqs = new ArrayList<Requisicao>();
		ois = GestorDeDispositivosPrivacidade.buscar_palavrachave(req.getKeyword(),req.getRecurso()); 
		if (ois.size() < 1)
				return null;
		Buffer.addWrapper(req.getHash(), ois.size());
		Iterator<ObjetoInteligente> it = ois.iterator();
		while(it.hasNext()){
			Requisicao reqTemp = req.clone();
			ObjetoInteligente oiTemp = it.next();
			reqTemp.setIDProp(oiTemp.getOwnerID());
			reqTemp.setIDObj(oiTemp.getID());
			reqTemp.setOi(oiTemp);
			reqs.add(reqTemp);
		}
		return reqs;
	}
	/**
	 * @param infos Lista de informações que devem ser combinada
	 * @return Combinação das informações, utilizando o combinador escolhido
	 */
	public Informacao receberInformação(List<Informacao> infos){
		Informacao info;
		Combinador c = new Combinador();
		info = c.combinar(infos);
		return info;
	}
}
