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

import java.util.ArrayList;
import java.util.List;


import sensor4People.InterfaceComRedeSocial.InterfaceComRedeSocial;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente.Privacidade;
import sensor4People.gestorRequisição.Requisicao;
/**
 *  Gestor de Dispositivo e Privacidade
 */
public abstract class GestorDeDispositivosPrivacidade {
	static public void cadastrar(){
		// TODO
	}
	static public void editar(){
		// TODO
	}
	static public void remover(){
		// TODO
	}
	static public boolean verificar(Requisicao req){
		// Verifica se a pessoa que fez a requisição tem direito de acesso ao que foi requerido
		ObjetoInteligente oi = req.getOi();			

		Recurso rec = oi.getRecurso(req.getRecurso());
		if( rec == null)
			return false; // TODO pode ser que isso quebre a complexa

		// o dono sempre pode
		if(req.getIDProp().equalsIgnoreCase(req.getIDUser()) )
			return true;
		// se está disponivel a todos...
		else if(rec.getPriv_get() == Privacidade.ALL )
			return true;
		else if(rec.getPriv_get() == Privacidade.FRIENDS ){
			if(InterfaceComRedeSocial.amigoDe(req.getIDProp(), req.getIDUser())){
				return true;
			}else
				return false;
		}

		return true;

	}
	static public List<ObjetoInteligente> buscar_palavrachave(String keyword, String recurso){

		// Retornar 
		List<ObjetoInteligente> list= new ArrayList<ObjetoInteligente>();
		DAOObjetoInteligente dao = new DAOObjetoInteligente();
		List<ObjetoInteligente> objs = dao.buscarKeyword(keyword);
		for( int i =0; i < objs.size(); i++){
			ObjetoInteligente obj = objs.get(i);
			if(obj.getRecurso(recurso)!=null){
				list.add(objs.get(i));
			}
		}
		return list;
	}
	static public String IDtoIP(ObjetoInteligente oi){
		// Pega o ID de um Objeto e descobre seu IP e Porta. Caso o dono seja localhost retorna valores preestabalecidos (apenas para teste) 
		String idOwner = oi.getOwnerID();
		if(idOwner.contains("localhost")){ 
			if(idOwner.contains("1")){
				return "localhost:4321";
			}else if(idOwner.contains("2")){
				return "localhost:4322";
			}else{
				return "localhost:4323";
			}

		}
		return(oi.getIP()+":"+oi.getPorta());
	}
}
