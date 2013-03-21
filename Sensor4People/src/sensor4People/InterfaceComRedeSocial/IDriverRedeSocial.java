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

/**
 * @author Fernando
 *
 */
public interface IDriverRedeSocial {
		/**
		 * Faz com que o usuario logue na sua rece social
		 */
		public void logar();
		/**
		 * @param idOwner Usuario1  (dono da requisição)
		 * @param idUser  Usuario2  (dono do objeto inteligente) 
		 * @return Retorna verdadeiro se os dois usuuário forem amigos
		 */
		public boolean amigoDe(String idOwner, String idUser);
		/*
		 * TODO função não implementada, verifica se um usuário faz parte de um grupo na rede social
		 */
		public boolean parteDe();
		
		/**
		 * Publica a informação na rede social de quem a solicitou.
		 * @param info Informação a ser postada
		 */
		public void postar(Informacao info);
}
