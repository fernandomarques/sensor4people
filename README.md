sensor4people
=============

Prototipo utilizado como caso de uso para o framework Fresso.

O Código possui dois projetos:
  - Sensor4People: Toda a implementação do protótipo do framework Fresso, com exceção da Interface de Gerencia de Dispositivos.
  - UIGerenciaDispositivos: Implementação da Interface de Gerencia de Dispositivos como uma página Web para cadastrar Objetos Inteligentes. A implementação foi feita com uso d GWT (Google Web Toolkit).

Para testar o projeto:
    
    1) Crie um banco de dados.
    1.a) Se não for MySQL será necessário atualizar o driver e modificar a classe sensor4People.gestorDispositivosPrivacidade.ConFactory
    1.b) Modifique as variaveis DATABASE_URL, DATABASE_USER e DATABASE_PW da classe sensor4People.Configuracoes
    1.c) O banco de dados deve conter duas tabelas:
    1.c.1) TABLE: Usuario. (ID usuário, Rede Social, Token de acesso a rede social) do tipo string.
    1.c.2) TABLE: objeto_inteligente. (ID objeto, ID proprietário objeto, tipo do objeto, suporte a publish subscribe, IP do objeto, porta do objeto, grupo do objeto)
    1.c.3) TABLE: keyword (keyword, ID objeto, ID do proprietário) sendo (ID objeto, ID proprietário) a chave da tabela. 
    1.c.4) TABLE: recursos (identificador, keyword,privacidade get, privacidade set, ID objeto, ID do proprietário) sendo (ID objeto, ID proprietário) a chave da tabela. 
    1.d) Para mais informações, e possiveis modificações que sejam feita na base de dados, as classes de Data Access Object (DAO) se encontram em sensor4People.gestorDispositivosPrivacidade.
    2) Crie um aplicação para Facebook
    2.a) No site https://developers.facebook.com/ crie uma applicação (bem simples)
    2.b) Atualize o valor da variavel sensor4People.Configuracoes.FACEBOOK_CLIENT_ID para o identificador da aplicação.
