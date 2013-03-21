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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.logging.Level;

import sensor4People.gestorRequisição.GestorDeRequisição;
import sensor4People.gestorRequisição.Informacao;
import sensor4People.gestorRequisição.Requisicao;
import ch.ethz.inf.vs.californium.coap.DELETERequest;
import ch.ethz.inf.vs.californium.coap.GETRequest;
import ch.ethz.inf.vs.californium.coap.MediaTypeRegistry;
import ch.ethz.inf.vs.californium.coap.Option;
import ch.ethz.inf.vs.californium.coap.OptionNumberRegistry;
import ch.ethz.inf.vs.californium.coap.POSTRequest;
import ch.ethz.inf.vs.californium.coap.PUTRequest;
import ch.ethz.inf.vs.californium.coap.Request;
import ch.ethz.inf.vs.californium.coap.Response;
import ch.ethz.inf.vs.californium.coap.TokenManager;
import ch.ethz.inf.vs.californium.endpoint.RemoteResource;
import ch.ethz.inf.vs.californium.endpoint.Resource;
import ch.ethz.inf.vs.californium.util.Log;

public class CoAPDriver extends IDriverObjetoInteligente{
	// DISCOVERY_RESOURCE pode ser usado para saber quais são os recursos do objeto (caso o mesmo implemente tal funcionalidade)
	private static final String DISCOVERY_RESOURCE = "/.well-known/core";
	
	public CoAPDriver(Informacao info) {
		super(info);
		method = null;
		uri = null;
		payload = null;
		loop = false;
	}
	private  String method;
	private  URI uri;
	private  String payload;
	private  boolean loop;

	public void call() {
		Log.setLevel(Level.ALL);
		Log.init();
		// create request according to specified method
		Request request = newRequest(method);
		if (request == null) {
			System.err.println("Unknown method: " + method);
		}
		if (method.equals("OBSERVE")) {
			request.setOption(new Option(0, OptionNumberRegistry.OBSERVE));
			loop = true;
		}
		// set request URI
		if (method.equals("DISCOVER") && (uri.getPath() == null || uri.getPath().isEmpty() || uri.getPath().equals("/"))) {
			// add discovery resource path to URI
			try {
				uri = new URI(uri.getScheme(), uri.getAuthority(), DISCOVERY_RESOURCE, uri.getQuery());		
			} catch (URISyntaxException e) {
				System.err.println("Failed to parse URI: " + e.getMessage());
			}
		}
		request.setURI(uri);
		request.setPayload(payload);
		request.setToken( TokenManager.getInstance().acquireToken() );
		// enable response queue in order to use blocking I/O
		request.enableResponseQueue(true);
		//
		//request.prettyPrint();
		// execute request
		try {
			request.execute();

			// loop for receiving multiple responses
			do {

				// receive response

				//System.out.println("Receiving response...");
				Response response = null;
				try {
					response = request.receiveResponse();
				} catch (InterruptedException e) {
					System.err.println("Failed to receive response: " + e.getMessage());
				}

				// output response

				if (response != null) {
					response.prettyPrint();
					//System.out.println("Time elapsed (ms): " + response.getRTT());
					info.setValor(response.getPayloadString());	
					new Thread(){ public void run(){
						if(GestorDeRequisição.GdR != null)
							GestorDeRequisição.GdR.tratarInformação(info);
					}}.run();
					if (info.getTipo() == Requisicao.Tipo.subscrição ||info.getTipo() == Requisicao.Tipo.subscrição_complexa){
						loop = InterfaceComObjetosInteligentes.exists(info);
					}

					// check of response contains resources
					if (response.getContentType()==MediaTypeRegistry.APPLICATION_LINK_FORMAT) {

						String linkFormat = response.getPayloadString();

						// create resource three from link format
						Resource root = RemoteResource.newRoot(linkFormat);
						if (root != null) {

							// output discovered resources
							System.out.println("\nDiscovered resources:");
							root.prettyPrint();

						} else {
							System.err.println("Failed to parse link format");
						}
					} else {

						// check if link format was expected by client
						if (method.equals("DISCOVER")) {
							System.out.println("Server error: Link format not specified");
						}
					}

				} else {

					// no response received	
					System.err.println("Request timed out");
					break;
				}

			} while (loop);

		} catch (UnknownHostException e) {
			System.err.println("Unknown host: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Failed to execute request: " + e.getMessage());
		}

		// finish
		System.out.println();
	}

	/**
	 * Instantiates a new request based on a string describing a method.
	 * 
	 * @return A new request object, or null if method not recognized
	 */
	private static Request newRequest(String method) {
		if (method.equals("GET")) {
			return new GETRequest();
		} else if (method.equals("POST")) {
			return new POSTRequest();
		} else if (method.equals("PUT")) {
			return new PUTRequest();
		} else if (method.equals("DELETE")) {
			return new DELETERequest();
		} else if (method.equals("DISCOVER")) {
			return new GETRequest();
		} else if (method.equals("OBSERVE")) {
			return new GETRequest();
		} else {
			return null;
		}
	}

	@Override
	public void get(String IP, String Resource) {
		method = "GET";
		//ip = IP; 
		try {
			uri = new URI(new String("coap://"+IP+"/"+Resource));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		payload = null;
		loop = false;
		call();
	}

	@Override
	public boolean put(String IP, String Resource, String Payload) {
		method = "PUT";
		try {
			uri = new URI(new String("coap://"+IP+"/"+Resource));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		payload = Payload;
		loop = false;
		call();
		return true;
	}
	public void observe(String IP, String Resource) {
		method = "OBSERVE";
		try {
			uri = new URI(new String("coap://"+IP+"/"+Resource));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		loop = true;
		call();
	}
	public static void main(String[] args) {
		CoAPDriver client = new CoAPDriver(new Informacao(new Requisicao("CoAPSensor", "localhost", "Fernando", Requisicao.Tipo.subscrição_complexa,
				"LOCAL", "temp","media", 20),""));
		client.observe("localhost:4321", "obs");
	}
}
