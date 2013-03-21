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
package sensor4People.gestorRequisição;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Classe que controla o Buffer das Informações Complexas
 */
public class Buffer {
	
	private static Hashtable<String,Wrapper> buffer = new Hashtable<String,Wrapper>();

	
	/**
	 * @param key ID da requisição complexa
	 * @return  Retorna todas as informações de uma requisição complexa
	 */
	static public ArrayList<Informacao> get(String key){
		return buffer.get(key).get();
	}
	/**
	 * @param info Informação
	 * @return coloca a informação no buffer e retorna a chave de acesso às informações desse grupo de requisição complexa
	 */
	static public String put(Informacao info){
		String key = info.getHash();
		if (buffer.get(key) == null)
			return null;

		buffer.get(key).put(info);
		return key;
	}
	/**
	 * @param  key Chave da requisição complexa
	 * @param tam Numero de requisições que foram feitas para a requisição complexa
	 * @return Retorna verdadeiro se foi criado, ou falso se já existe uma requisição feita
	 */
	static public boolean addWrapper(String key,int tam){
		Wrapper wrp;
		wrp = buffer.get(key);
		if(wrp == null){
			buffer.put(key,new Wrapper(tam));
			return true;
		}
		return false;
	}
	/**
	 * @param key Chave de uma requisição
	 * @return Retorna verdadeiro ou falso se já existe uma requisição com aquela chave
	 */
	static public boolean hasWrapper(String key){
		Wrapper wrp;
		wrp = buffer.get(key);
		if(wrp != null){
			return true;
		}
		return false;
	}
	/**
	 * @param key Chave de uma requisição
	 * @return Remove a requisição de chave 'key' do wrapper
	 */
	static public void destroyWrapper(String key){
		buffer.remove(key);
	}
	/**
	 * @param key Chave de uma requisição
	 * Ver Weapper
	 */
	static public float bufferSize(String key){
		return buffer.get(key).bufferSize();
	}
}
