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

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import sensor4People.gestorDispositivosPrivacidade.DAOPessoa;
import sensor4People.gestorDispositivosPrivacidade.Friendship;
import sensor4People.gestorDispositivosPrivacidade.Pessoa;
import sensor4People.gestorRequisição.Informacao;
import sensor4People.gestorRequisição.Requisicao.Tipo;

public class FacebookRedeSocial implements IDriverRedeSocial {

	@Override
	public void logar() {
	}

	@Override
	public boolean parteDe() {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public void postar(Informacao info) {
		
		if(info.getTipo() == Tipo.subscrição){
			String idOwner = info.getIDProp();
			DAOPessoa daop = new DAOPessoa();
			Pessoa p = daop.buscar(idOwner);
			if( p == null){
				// usuario não está no sistema, logo não pode ser o dono
			}else{
			FacebookClient facebookClient = new DefaultFacebookClient(p.getToken());
			FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class,
					Parameter.with("message", "Sensor4People:"+ info.getValor()));
			}
		}
		System.out.println("-----     Interface com Rede social     -----");
		System.out.println("Valor:" + info.getValor() + "\tNome:" + info.getIDUser());
		System.out.println("Prop/Obj ID:"+ info.getIDProp()+ info.getIDObj());
		System.out.println("Tipo:" + info.getTipo() + "Recurso" + info.getRecurso());
		System.out.println("---------------------------------------------");
	}

	@Override
	public boolean amigoDe(String idOwner, String idUser) {
		DAOPessoa daop = new DAOPessoa();
		Pessoa p = daop.buscar(idOwner);
		if( p == null){
			// usuario não está no sistema, logo não pode ser o dono
			return false;
		}

		FacebookClient facebookClient = new DefaultFacebookClient(p.getToken());
		Friendship f = facebookClient.fetchObject("me/friends/"+ idUser, Friendship.class);
		if(f.getData().contains(idUser)){
			return true;
		}
		return false;

	}
	public static void main(String[] args) {
		// Testes ...
		String idOwner="", idUser="";
		System.out.println("Testando...");
		FacebookRedeSocial fbrs = new FacebookRedeSocial();
		System.out.println(" São amigos?! : " +fbrs.amigoDe(idOwner, idUser));
	}
}
