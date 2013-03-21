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
package sensor4people.pages.index.server;

import java.util.ArrayList;
import java.util.List;

import sensor4People.gestorDispositivosPrivacidade.DAOObjetoInteligente;
import sensor4People.gestorDispositivosPrivacidade.DAOPessoa;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente;
import sensor4People.gestorDispositivosPrivacidade.Pessoa;
import sensor4People.gestorDispositivosPrivacidade.Recurso;
import sensor4people.pages.index.client.model.CadastroObjeto;
import sensor4people.pages.index.client.service.Cadastrar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class CadastrarImpl extends RemoteServiceServlet implements Cadastrar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2391871261835328859L;

	@Override
	public boolean cadastrar(CadastroObjeto co) {
		String token = co.getOwnerID();
		co.setOwnerID(buscarOwnerID(co.getOwnerID()));
		DAOPessoa daop = new DAOPessoa();
		Pessoa p = daop.buscar(co.getOwnerID());
		if(p == null){
			p = new Pessoa();
			p.setID(co.getOwnerID());
			p.setRedeSocial("facebook");
			p.setToken(token);
			daop.inserir(p);
		}else{
			p.setRedeSocial("facebook");
			p.setToken(token);
			daop.atualizar(p);
		}
		
		ObjetoInteligente oi = new ObjetoInteligente();
		//co.print();
		oi.setID(co.getID());
		oi.setOwnerID(co.getOwnerID());
		oi.setIP(co.getIP());
		oi.setPorta(co.getPorta());
		oi.setPublisbhSubscribe(co.isPublisbhSubscribe());
		oi.setKeywords(co.getKeywords());
		oi.setGroup_ID(co.getGroup_ID());
		oi.setTipo(co.getTipo());
		
		List<Recurso> recs = new ArrayList<Recurso>();
		
		List<String> coRec = co.getRecursos();
		List<Integer> coGet = co.getPriv_get();
		List<Integer> coSet = co.getPriv_set();
		for(int i=0;i<coRec.size();i++){
			Recurso temp = new Recurso();
			temp.setNome(coRec.get(i));
			temp.setPriv_get(ObjetoInteligente.getPrivacidade(iToS(coGet.get(i))));
			temp.setPriv_set(ObjetoInteligente.getPrivacidade(iToS(coSet.get(i))));
			recs.add(temp);
		}
		oi.setRecursos(recs);
		DAOObjetoInteligente dao = new DAOObjetoInteligente();
		try{
			dao.conectar();
			dao.inserir(oi);
			dao.send();
			return true;
		}catch(Exception e){
			return false;			
		}

	}
	private String buscarOwnerID(String ownerID) {
		FacebookClient facebookClient = new DefaultFacebookClient(ownerID);
		User user = facebookClient.fetchObject("me", User.class);
		return user.getId();
	}
	private String iToS(int index){
		switch(index){
		case(0): return "ALL";
		case(1): return "FRIENDS";
		default: return "OWNER";
		}
		
	}
}
