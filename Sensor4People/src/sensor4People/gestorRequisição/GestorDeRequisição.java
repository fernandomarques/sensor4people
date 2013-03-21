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
import java.util.Iterator;
import java.util.List;

import sensor4People.InterfaceComObjetosInteligentes.InterfaceComObjetosInteligentes;
import sensor4People.InterfaceComRedeSocial.InterfaceComRedeSocial;
import sensor4People.gestorDispositivosPrivacidade.DAOObjetoInteligente;
import sensor4People.gestorDispositivosPrivacidade.GestorDeDispositivosPrivacidade;
import sensor4People.gestorDispositivosPrivacidade.ObjetoInteligente;
import sensor4People.gestorRequisição.Requisicao.Tipo;
import sensor4People.gestorRequisição.gestorCombinação.GestorDeCombinação;
import sensor4People.gestorRequisição.gestorSubscrição.GestorDeSubscrição;

public class GestorDeRequisição {
	
	private static final float QTD_MIN = (float) 0.9;	
	private GestorDeCombinação GdC;
	private GestorDeSubscrição GdS;
	public static GestorDeRequisição GdR;
	
	public GestorDeRequisição(){
		GdC = new GestorDeCombinação();
		GdS = new GestorDeSubscrição();
		GdR = this;
	}
	/**
	*Cria uma nova requisição
	* @return Retorna a requisição criada
	*/
	public Requisicao criarRequisição(String IDObj, String IDProp, String User,Tipo tipo, String Keyword, String Recurso,String Combinacao, int limite){
		// Função que cria uma nova requisição
		Requisicao req = new Requisicao(IDObj,IDProp,User,tipo, Keyword,Recurso,Combinacao, limite);
		return req;
	}
	/**
	 * Requere a requisição, de forma assincrona, ao objeto inteligente
	 * @param req Requisição a ser feita	
	 */
	public void requerer(Requisicao req){
		boolean permissao;
		// Função que envia a requisição
		permissao = GestorDeDispositivosPrivacidade.verificar(req);
		if(permissao){
			InterfaceComObjetosInteligentes.request(req);
		}else{
			System.out.println("Acesso negado");
		}
	}

	public void excluirRequisição(){
		//TODO Apenas subscrição
		
	}
	/**
	 * Trata a função de acordo com seu tipo. Ex.: Se complexa, cria varias requisições
	 * @param req Requisição a ser tratada
	 */
	public void tratarRequisição(Requisicao req){
		Requisicao.Tipo tipo = req.getTipo();
		if(Requisicao.Tipo.simples == tipo){
			DAOObjetoInteligente dao = new DAOObjetoInteligente();
			ObjetoInteligente oi = dao.buscar(req.getIDObj(), req.getIDProp());
			if (oi == null)
				return ;
			req.setOi(oi);
			requerer(req);
		}else if(Requisicao.Tipo.complexo == tipo){
			List<Requisicao> reqs;
			reqs = GdC.receberRequisição(req);
			Iterator<Requisicao> it = reqs.iterator();
			while(it.hasNext()){
				requerer(it.next());
			}
			
		}else{
			GdS.receberRequisição(req);
		}
		
	}
	/**
	 * Trata a informação final que foi recebida do objeto inteligente
	 * @param info Informação a ser tratada
	 * @return Retorna o valor da informação
	 */
	public String tratarInformação(Informacao info){
		if(Requisicao.Tipo.subscrição == info.getTipo()){
			info = GdS.receberInformação(info);
			if(info != null){
				InterfaceComRedeSocial.postar(info);
			}
		}else if (Requisicao.Tipo.simples != info.getTipo()){
			String key = info.getHash();
			if(Buffer.hasWrapper(key)){
				Buffer.put(info);
				if(Buffer.bufferSize(key) > QTD_MIN){
					// Tem informcao suficiente
					ArrayList<Informacao> infos;
					infos = Buffer.get(key);
					// combina as informacoes
					info = GdC.receberInformação(infos);
					if(Requisicao.Tipo.subscrição_complexa == info.getTipo()){
						info = GdS.receberInformação(info);
					}
					if(info != null){
						InterfaceComRedeSocial.postar(info);
						Buffer.destroyWrapper(key);
					}
				}
			}
		}else{
			InterfaceComRedeSocial.postar(info);
		}
		return info.getValor();
	}
	public static void main(String[] args) {
		TestReqSubs();
	}
	public static void TestReqSimples(){
		// 1367804517
		// 100002612920197
		// Os valores númericos aqui usados são o ID de usuário do facebook.
		GestorDeRequisição GDR = new GestorDeRequisição();
		// Simula uma requisição do tipo simples
		Requisicao req = GDR.criarRequisição("SENSOR", "100000078963029", "100002612920197", Requisicao.Tipo.simples,
				"KW", "TEMP","media", 20);
		GDR.tratarRequisição(req);
		
	}
	public static void TestReqSubs(){
		GestorDeRequisição GDR = new GestorDeRequisição();
		Requisicao req = GDR.criarRequisição("SENSOR", "100000078963029", "100000078963029", Requisicao.Tipo.subscrição,
				"VIX", "TEMP","media", 10);
		GDR.tratarRequisição(req);		
	}
	public static void TestReqCompl(){
		GestorDeRequisição GDR = new GestorDeRequisição();
		Requisicao req = GDR.criarRequisição("SENSOR", "100000078963029", "100000078963029", Requisicao.Tipo.complexo,
				"VIX", "TEMP","Media", 18);
		GDR.tratarRequisição(req);		
	}
	public static void TestReqSubCompl(){
		GestorDeRequisição GDR = new GestorDeRequisição();
		Requisicao req = GDR.criarRequisição("SENSOR", "100000078963029", "100000078963029", Requisicao.Tipo.subscrição_complexa,
				"VIX", "TEMP","Media", 10);
		GDR.tratarRequisição(req);		
	}
	
}
