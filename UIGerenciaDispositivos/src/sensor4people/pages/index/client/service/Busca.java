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
package sensor4people.pages.index.client.service;

import sensor4people.pages.index.client.model.BuscaObjeto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Busca {
	private VerticalPanel mainPanel;
	private TextBox keywordBox;
	private Label lblTipoReq;
	private Button btnFim;
	private Label lblkw;
	private Label lblOwner;
	private TextBox ownerBox;
	private Label lblIDOBJ;
	private TextBox IDOBJBox;
	private Label lblRecursos;
	private TextBox recursosBox;
	private BuscarAsync buscaAsync = GWT.create(Buscar.class);
	private String token;
	private Panel page;
	private HorizontalPanel horizontalPanel;
	private VerticalPanel verticalPanel;
	private VerticalPanel verticalPanel_1;
	private ListBox listBox;
	private Label lblLimite;
	private TextBox limiteBox;
	
	public Busca(Panel pag, String tkn){
		token = tkn;
		page = pag;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		
		boolean DEBUG_MODE = true;
		this.mainPanel = new VerticalPanel();
		this.mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		page.clear();
		page.add(mainPanel);
		mainPanel.setSize("436px", "142px");
		
		this.horizontalPanel = new HorizontalPanel();
		this.mainPanel.add(this.horizontalPanel);
		
		this.verticalPanel = new VerticalPanel();
		this.horizontalPanel.add(this.verticalPanel);
		this.verticalPanel.setWidth("242px");
		
		lblOwner = new Label("ID Proprietário:");
		this.verticalPanel.add(this.lblOwner);
		lblOwner.setStyleName("gwt-LabelBig");
		this.lblOwner.setSize("140px", "28px");
		
		lblIDOBJ = new Label("ID do Objeto:");
		this.verticalPanel.add(this.lblIDOBJ);
		this.lblIDOBJ.setSize("140", "29px");
		lblIDOBJ.setStyleName("gwt-LabelBig");
		
		lblRecursos = new Label("Recurso:");
		this.verticalPanel.add(this.lblRecursos);
		this.lblRecursos.setSize("140px", "29px");
		lblRecursos.setStyleName("gwt-LabelBig");
		
		this.lblTipoReq = new Label("Tipo da Requisição:");
		this.verticalPanel.add(this.lblTipoReq);
		this.lblTipoReq.setSize("140", "28px");
		this.lblTipoReq.setStyleName("gwt-LabelBig");
		
		lblkw = new Label("Palavra-chave:");
		this.verticalPanel.add(this.lblkw);
		this.lblkw.setSize("140", "30");
		lblkw.setStyleName("gwt-LabelBig");
		
		this.lblLimite = new Label("Limite:");
		this.lblLimite.setStyleName("gwt-LabelBig");
		this.verticalPanel.add(this.lblLimite);
		this.lblLimite.setSize("140", "30");
		
		this.verticalPanel_1 = new VerticalPanel();
		this.horizontalPanel.add(this.verticalPanel_1);
		
		ownerBox = new TextBox();
		this.verticalPanel_1.add(this.ownerBox);
		this.ownerBox.setSize("140", "30");
		
		IDOBJBox = new TextBox();
		this.verticalPanel_1.add(this.IDOBJBox);
		IDOBJBox.setFocus(true);
		this.IDOBJBox.setSize("140", "30");
		
		recursosBox = new TextBox();
		this.verticalPanel_1.add(this.recursosBox);
		this.recursosBox.setSize("140", "30");
		
		this.listBox = new ListBox();
		this.listBox.setDirectionEstimator(false);
		this.listBox.addItem("Simples");
		this.listBox.addItem("Complexa");
		this.listBox.addItem("Subscrição");
		this.listBox.addItem("Subscrição Complexa");
		this.listBox.setSelectedIndex(0);
		this.verticalPanel_1.add(this.listBox);
		this.listBox.setWidth("150px");
		this.listBox.setVisibleItemCount(1);
		
		this.keywordBox = new TextBox();
		this.verticalPanel_1.add(this.keywordBox);
		this.keywordBox.setSize("140", "30");
		
		this.limiteBox = new TextBox();
		this.verticalPanel_1.add(this.limiteBox);
		this.limiteBox.setSize("140", "30");

		if(DEBUG_MODE) IDOBJBox.setText("SENSOR");
		if(DEBUG_MODE) ownerBox.setText("100000078963029");
		if(DEBUG_MODE) recursosBox.setText("TEMP");
		if(DEBUG_MODE) keywordBox.setText("Vix");
		if(DEBUG_MODE) limiteBox.setText("50");
			
		this.btnFim = new Button("New button");
		mainPanel.add(btnFim);
		this.btnFim.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				buscar();
			}
		});
		this.btnFim.setText("Requisitar");
		this.mainPanel.setCellHorizontalAlignment(this.btnFim, HasHorizontalAlignment.ALIGN_CENTER);
	}

	protected void buscar() {
		boolean err = false;
		btnFim.setEnabled(false);
		BuscaObjeto bo = new BuscaObjeto();
		String temp;
	
		bo.setToken(token);
		
		temp = IDOBJBox.getText();
		if(testar(temp)){
			bo.setIDObj(temp);
		}else err = true;
		
		temp = ownerBox.getText();
		if(testar(temp)){
			bo.setIDOwner(temp);
		}else err = true;
		
		temp = recursosBox.getText();
		if(testar(temp)){
			bo.setRec(temp);
		}else err = true;
		
		temp = listBox.getItemText(listBox.getSelectedIndex());
		if(testar(temp)){
			bo.setTipo(temp);
		}
		
		temp = keywordBox.getText();
		if(testar(temp)){
			bo.setKW(temp);
		}else if(bo.getTipo().contentEquals("Complexa")||bo.getTipo().contentEquals("Subscrição Complexa")) err = true;
		
		temp = limiteBox.getText();		
		if(testar(temp)){
			bo.setLimite(temp);
		}else if(bo.getTipo().contentEquals("Subscrição")||bo.getTipo().contentEquals("Subscrição Complexa")) err = true;
		
		
		if(!err){
			 if (buscaAsync == null)
				 buscaAsync = GWT.create(Buscar.class);
			 AsyncCallback<String> callback = new BuscarCallback();
			 buscaAsync.buscar(bo, callback);
			 page.clear();
			 Label lblEnviado = new Label("Requisitando Objeto");
			 page.add(lblEnviado);
		}
		
	}
	protected boolean testar(String str){
		if (str == null)
				return false;
		return true;
	}
}
