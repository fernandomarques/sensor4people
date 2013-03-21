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
import java.util.Enumeration;
import java.util.Hashtable;

// Wrapper de acesso ao Hashtable que contem as Informacoes complexas cujas reposta estao sendo recebidas
public class Wrapper {
	// Hashtable que armazena as informações.
	private Hashtable<String,Informacao> buffer2 = new Hashtable<String,Informacao>();
	// Tamanho máximo do buffer. Quantas requisições são esperadas?
	private int max=1;
	
	public Wrapper(int valor){
		max = valor;
	}
	/**
	* @return Retorna todas as informações contidas no Buffer (cria uma nova lista)
	* */
	public ArrayList<Informacao> get(){
		Enumeration<Informacao> list= buffer2.elements();
		ArrayList<Informacao> lista = new ArrayList<Informacao>();
		while(list.hasMoreElements()){
			lista.add(list.nextElement());
		}
		return lista;
	}
	/**
	 * @param info  Informação a ser adicionada ao Wrapper
	 */
	public void put(Informacao info){
		Informacao tmp = buffer2.get(info.getHash2());
		if(tmp!= null){
			// caso já exista, atualizada o valor
			tmp.setValor(info.getValor());
		}else{
			buffer2.put(info.getHash2(),info);
		}	
	}
	/**
	 *  @return Retorna o tamanho da fila dividido pelo tamanho máximo possível. Ex. Se foram feitas 4 requisições e 2 foram recebidas, retorn 0.5,
	 *  ou seja, 50% do esperado
	 */
	public float bufferSize(){
		return  (float)buffer2.size()/(float) max;
	}
	
}
