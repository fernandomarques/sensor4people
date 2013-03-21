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
package sensor4People;

/**
 * 
 * @author Fernando
 * Classe utilizada para pegar valores de banco de dados e redes sociais
 */
public class Configuracoes {
	// TODO para testar o código, atualize os campo a baixo com valores reais
	// URL de acesso ao banco de dados
	public final static String DATABASE_URL= "jdbc:mysql://meumysql.com/";
	// Usuário utilizado para acessar o banco de dados
	public final static String DATABASE_USER = "usuario";
	// Senha do usuário
	public final static String DATABASE_PW = "senha";

	// ID da Aplicação Cliente que será usada para acessar o Facebook
	// Para criar uma aplicação acesse: https://developers.facebook.com/apps
	public final static String FACEBOOK_CLIENT_ID = "client id";
}
