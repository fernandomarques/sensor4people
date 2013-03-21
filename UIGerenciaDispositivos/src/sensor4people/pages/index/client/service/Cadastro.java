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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sensor4people.pages.index.client.model.CadastroObjeto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimpleCheckBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Cadastro {
	private VerticalPanel mainPanel;
	private TextBox keywordBox;
	private Label lblKeyword;
	private List<String> keywords;
	private Button btnFim;
	private HorizontalPanel KeywordPanel;
	private Label lblerrKeyword;
	private HorizontalPanel TipoPanel;
	private Label lblTipo;
	private Label lblerrTipo;
	private TextBox TipoBox;
	private HorizontalPanel IPPanel;
	private Label lblIP;
	private TextBox IPBox;
	private Label lblerrIP;
	private Label lblPorta;
	private IntegerBox PortaBox;
	private Label lblerrPorta;
	private HorizontalPanel pubsubPanel;
	private Label lblPubSub;
	private SimpleCheckBox pubsubCheckBox;
	private HorizontalPanel IDObjPanel;
	private Label lblIDOBJ;
	private TextBox IDOBJBox;
	private Label lblerrIDOBJ;
	private HorizontalPanel RecursosPanel;
	private FlexTable RecursosflexTable;
	private VerticalPanel vRecursosPanel;
	private Label lblRecursos;
	private TextBox recursosBox;
	private Button recursosButton;
	private Label lblerr;
	private List<String> listaRecursos = new ArrayList<String>();
	private List<Integer> listaGet = new ArrayList<Integer>();
	private List<Integer> listaSet = new ArrayList<Integer>();
	private Label lblNewLabel;
	private CadastrarAsync cadastroAsync = GWT.create(Cadastrar.class);
	private String token;
	private Panel page;
	
	public Cadastro(Panel pag, String tkn){
		token = tkn;
		page = pag;
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		
		boolean DEBUG_MODE = true;
		keywords = new ArrayList<String>();
		this.mainPanel = new VerticalPanel();
		this.mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		page.clear();
		page.add(mainPanel);
		mainPanel.setSize("436px", "142px");
		
		IDObjPanel = new HorizontalPanel();
		mainPanel.add(IDObjPanel);
		IDObjPanel.setWidth("435px");
		
		lblIDOBJ = new Label("ID do Objeto:");
		lblIDOBJ.setStyleName("gwt-LabelBig");
		IDObjPanel.add(lblIDOBJ);
		
		IDOBJBox = new TextBox();
		IDOBJBox.setFocus(true);
		IDObjPanel.add(IDOBJBox);
		IDOBJBox.setWidth("270px");
		if(DEBUG_MODE) IDOBJBox.setText("SENSOR");
		
		lblerrIDOBJ = new Label("*");
		lblerrIDOBJ.setVisible(false);
		lblerrIDOBJ.setStyleName("gwt-errLabel");
		IDObjPanel.add(lblerrIDOBJ);
		
		IPPanel = new HorizontalPanel();
		mainPanel.add(IPPanel);
		IPPanel.setWidth("436px");
		
		lblIP = new Label("IP:");
		lblIP.setStyleName("gwt-LabelBig");
		IPPanel.add(lblIP);
		
		IPBox = new TextBox();
		IPPanel.add(IPBox);
		if(DEBUG_MODE) IPBox.setText("localhost");
		
		lblerrIP = new Label("*");
		lblerrIP.setVisible(false);
		lblerrIP.setStyleName("gwt-errLabel");
		IPPanel.add(lblerrIP);
		
		lblPorta = new Label("Porta:");
		lblPorta.setStyleName("gwt-LabelBig");
		IPPanel.add(lblPorta);
		
		PortaBox = new IntegerBox();
		PortaBox.setMaxLength(10);
		IPPanel.add(PortaBox);
		PortaBox.setWidth("126px");
		if(DEBUG_MODE) PortaBox.setValue(54321);
		
		lblerrPorta = new Label("*");
		lblerrPorta.setVisible(false);
		lblerrPorta.setStyleName("gwt-errLabel");
		IPPanel.add(lblerrPorta);
		
		TipoPanel = new HorizontalPanel();
		mainPanel.add(TipoPanel);
		TipoPanel.setWidth("435px");
		
		lblTipo = new Label("Tipo do Objeto:");
		lblTipo.setStyleName("gwt-LabelBig");
		TipoPanel.add(lblTipo);
		
		TipoBox = new TextBox();
		TipoPanel.add(TipoBox);
		TipoBox.setWidth("252px");
		if(DEBUG_MODE) TipoBox.setText("CoAP");
		
		lblerrTipo = new Label("*");
		lblerrTipo.setVisible(false);
		lblerrTipo.setStyleName("gwt-errLabel");
		TipoPanel.add(lblerrTipo);
		
		KeywordPanel = new HorizontalPanel();
		mainPanel.add(KeywordPanel);
		KeywordPanel.setWidth("436px");
		
		this.lblKeyword = new Label("Palavras-chave:");
		KeywordPanel.add(lblKeyword);
		this.lblKeyword.setStyleName("gwt-LabelBig");
		
		this.keywordBox = new TextBox();
		KeywordPanel.add(keywordBox);
		keywordBox.setWidth("251px");
		if(DEBUG_MODE) keywordBox.setText("VV;VIX");
		
		lblerrKeyword = new Label("*");
		lblerrKeyword.setStyleName("gwt-errLabel");
		lblerrKeyword.setVisible(false);
		KeywordPanel.add(lblerrKeyword);
		
		vRecursosPanel = new VerticalPanel();
		mainPanel.add(vRecursosPanel);
		
		RecursosPanel = new HorizontalPanel();
		vRecursosPanel.add(RecursosPanel);
		
		lblRecursos = new Label("Recursos:");
		lblRecursos.setStyleName("gwt-LabelBig");
		RecursosPanel.add(lblRecursos);
		
		recursosBox = new TextBox();
		RecursosPanel.add(recursosBox);
		
		recursosButton = new Button("New button");
		recursosButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addRecurso();
			}
		});
		recursosButton.setText("+");
		RecursosPanel.add(recursosButton);
		
		RecursosflexTable = new FlexTable();
		vRecursosPanel.add(RecursosflexTable);
		RecursosflexTable.setText(0, 0, "Nome");
		RecursosflexTable.setText(0, 1, "Privacidade GET");
		RecursosflexTable.setText(0, 2, "Privacidade PUT");
		RecursosflexTable.setText(0, 3, "Remove");
		RecursosflexTable.getRowFormatter().addStyleName(0, "watchListHeader");
		RecursosflexTable.addStyleName("watchList");
		if(DEBUG_MODE){ 
			recursosBox.setText("temp");
			addRecurso();
		}
		
		
		this.lblNewLabel = new Label("             ");
		this.vRecursosPanel.add(this.lblNewLabel);
		RecursosflexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
		
		pubsubPanel = new HorizontalPanel();
		mainPanel.add(pubsubPanel);
		pubsubPanel.setWidth("429px");
		
		lblPubSub = new Label("Suporte a Publish/Subcribe?");
		lblPubSub.setStyleName("gwt-LabelBig");
		pubsubPanel.add(lblPubSub);
		lblPubSub.setSize("300px", "22px");
		
		pubsubCheckBox = new SimpleCheckBox();
		pubsubPanel.add(pubsubCheckBox);
		
		lblerr = new Label("Erro");
		this.lblerr.setVisible(false);
		this.lblerr.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		lblerr.setStyleName("gwt-errLabel");
		mainPanel.add(lblerr);
		lblerr.setWidth("160px");
		
		this.btnFim = new Button("New button");
		mainPanel.add(btnFim);
		this.btnFim.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cadastrar();
			}
		});
		this.btnFim.setText("Cadastrar");
		this.mainPanel.setCellHorizontalAlignment(this.btnFim, HasHorizontalAlignment.ALIGN_CENTER);
		this.keywordBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if(event.getUnicodeCharCode() == KeyCodes.KEY_ENTER){
					cadastrar();
				}
			}
		});
	}

	protected void cadastrar() {
		boolean err = false;
		btnFim.setEnabled(false);
		lblerr.setText("");
		CadastroObjeto oi = new CadastroObjeto();
		String temp;
		// Pega o ID
		temp = IDOBJBox.getValue();
		if(!testar(temp)){
			err = true;
			//lblerrIDOBJ.setVisible(true);
			//lblerr.setText("ID Inválido");
			Window.alert("ID do Objeto Invalido");
		}else{
			oi.setID(temp);
		}
		// Pega o tipo
		temp = TipoBox.getValue();
		if(temp == null){
			err = true;
			lblerrTipo.setVisible(true);
		}else{
			oi.setTipo(temp);
		}
		// Pega IP
		temp = IPBox.getValue();
		if(!testar(temp)){
			err = true;
			lblerrIP.setVisible(true);
		}else{
			oi.setIP(temp);
		}
		// Pega Porta
		int porta;
		if(PortaBox.getValue() == null ||(porta = PortaBox.getValue())<0){
			err = true;
			lblerrPorta.setVisible(true);
		}else{
			oi.setPorta(porta);
		}
		// Pega Keyword
		keywords.addAll(Arrays.asList(keywordBox.getValue().split(";")));
		for(int i = 0;i< keywords.size();i++){
			if(!testar(keywords.get(i))){
				err = true;
				lblerrKeyword.setVisible(true);
				lblerr.setText("Uma ou mais palavras-chave inválidas.");
			}else{
				oi.setKeywords(keywords);
			}
		}
		// Pega Recurso
		int row = RecursosflexTable.getRowCount() ;
		oi.setRecursos(listaRecursos);
		for(int i=1;i<row;i++){
			listaGet.add(((ListBox) RecursosflexTable.getWidget(i, 1)).getSelectedIndex());
			listaSet.add(((ListBox) RecursosflexTable.getWidget(i, 2)).getSelectedIndex());
		}
		oi.setPriv_get(listaGet);
		oi.setPriv_set(listaSet);
		// Pega Subscribe-able
		oi.setPublisbhSubscribe(pubsubCheckBox.getValue());
		
		// Se não houve problema no cadastro, salva as informações na base de dados
		oi.setOwnerID(token);
		if(!err){
			 if (cadastroAsync == null)
				 cadastroAsync = GWT.create(Cadastrar.class);
			 AsyncCallback<Boolean> callback = new CadastrarCallback();
			 cadastroAsync.cadastrar(oi, callback);
			 page.clear();
			 Label lblEnviado = new Label("Cadastrando Objeto");
			 page.add(lblEnviado);
		}
		
	}
	protected boolean testar(String str){
		if (str == null)
				return false;
		return true;
	}

	protected void addRecurso() {
		//listaRecursos
		final String kw = recursosBox.getText().trim();
		int row = RecursosflexTable.getRowCount();
		recursosBox.setFocus(true);
		if(!kw.matches("^[0-9A-Za-z\\.]{1,10}$")){
			return;
		}
		if(listaRecursos.contains(kw)){
			return;
		}
		recursosBox.setText("");
		listaRecursos.add(kw);	
		RecursosflexTable.setText(row, 0, kw);
		// DropsBox GET
		ListBox lb = new ListBox();
		lb.addItem("ALL");
		lb.addItem("FRIENDS");
		lb.addItem("OWNER");
		lb.setItemSelected(2, true);
		RecursosflexTable.setWidget(row,2,lb);
		// DropsBox SET
		lb = new ListBox();
		lb.addItem("ALL");
		lb.addItem("FRIENDS");
		lb.addItem("OWNER");
		lb.setItemSelected(1, true);
		RecursosflexTable.setWidget(row,1,lb);
		// Add a button to remove this stock from the table.
		Button removeRecursoButton = new Button("x");
		removeRecursoButton.addStyleDependentName("remove");
		removeRecursoButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				int removedindex = listaRecursos.indexOf(kw);
				listaRecursos.remove(removedindex);
				RecursosflexTable.removeRow(removedindex+1);
			}
		});
		RecursosflexTable.setWidget(row,3,removeRecursoButton);
		
	}
}
