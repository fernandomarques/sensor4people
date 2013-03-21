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
package sensor4people.pages.index.client.entrypoint;

import sensor4People.Configuracoes;
import sensor4people.pages.index.client.service.Busca;
import sensor4people.pages.index.client.service.Cadastro;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint {
	private Button clickMeButton;
	private Button buscar;
	public Panel pag;
	private String tkn;
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		pag = new HorizontalPanel();
		HorizontalPanel menu = criarMenu();
		rootPanel.add(menu);
		rootPanel.add(pag);

	}
	private HorizontalPanel criarMenu(){
		HorizontalPanel menu = new HorizontalPanel();
		clickMeButton = new Button();
		buscar = new Button();

		menu.add(clickMeButton);
		menu.add(buscar);
		menu.add(addFacebookAuth());

		clickMeButton.setText("Cadastrar");
		clickMeButton.setEnabled(false);
		clickMeButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {

				Cadastro cad = new Cadastro(pag,tkn);
				cad.onModuleLoad();
			}
		});
		buscar.setText("Buscar");
		buscar.setEnabled(false);
		buscar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Busca bus = new Busca(pag,tkn);
				bus.onModuleLoad();
			}
		});			
		return menu;
	}
	private static final String FACEBOOK_AUTH_URL = "https://www.facebook.com/dialog/oauth";

	// This app's personal client ID assigned by the Facebook Developer App
	// (http://www.facebook.com/developers).
	private static final String FACEBOOK_CLIENT_ID = Configuracoes.FACEBOOK_CLIENT_ID;

	// All available scopes are listed here:
	// http://developers.facebook.com/docs/authentication/permissions/
	// This scope allows the app to access the user's email address.
	//private static final String FACEBOOK_EMAIL_SCOPE = "email";

	// This scope allows the app to access the user's birthday.
	//private static final String FACEBOOK_BIRTHDAY_SCOPE = "user_birthday";
	private static final Auth AUTH = Auth.get();
	// Adds a button to the page that asks for authentication from Facebook.
	// Note that Facebook does not allow localhost as a redirect URL, so while
	// this code will work when hosted, it will not work when testing locally.
	private Button addFacebookAuth() {
		// Since the auth flow requires opening a popup window, it must be started
		// as a direct result of a user action, such as clicking a button or link.
		// Otherwise, a browser's popup blocker may block the popup.
		final Button button = new Button("Facebook Login");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final AuthRequest req = new AuthRequest(FACEBOOK_AUTH_URL, FACEBOOK_CLIENT_ID)
				.withScopes("offline_access")
				.withScopes("publish_stream")
				//.withScopes(FACEBOOK_EMAIL_SCOPE, FACEBOOK_BIRTHDAY_SCOPE)
				// Facebook expects a comma-delimited list of scopes
				.withScopeDelimiter(",");
				AUTH.login(req, new Callback<String, Throwable>() {
					@Override
					public void onSuccess(String token) {
						//Window.alert("Got an OAuth token:\n" + token + "\n" + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
						tkn = token;
						button.setText("Logado");
						button.setEnabled(false);
						System.out.println(token);
						buscar.setEnabled(true);
						clickMeButton.setEnabled(true);

					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error:\n" + caught.getMessage());
					}
				});
			}
		});
		return button;
	}
}
