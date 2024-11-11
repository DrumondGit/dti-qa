# Automação de Testes - Página de Login

Teste realizado como etapa do processo seletivo da empresa DTI Digital.

Este projeto tem como objetivo criar e automatizar cenários de teste para a página de login do site [Automation Practice](http://www.automationpractice.pl/index.php?controller=authentication&back=my-account)


## Cenários de Teste Implementados
Durante o desenvolvimento, os seguintes cenários de teste foram criados para cobrir possíveis interações de um usuário na página de login:

**1. Login com Credenciais Válidas**
  - Verifica se o usuário é redirecionado corretamente para a página da conta ao fornecer credenciais válidas. Obs: Usuário criado previamente.

**2. Tentativa de Login com Senha Incorreta**
  - Verifica se uma mensagem de erro é exibida quando a senha está incorreta.

**3. Tentativa de Login com Campo de E-mail Vazio**
  - Verifica se uma mensagem de erro é exibida ao tentar logar sem preencher o e-mail.

**4. Tentativa de Login com Campo de Senha Vazio**
  - Verifica se uma mensagem de erro é exibida ao tentar logar sem preencher a senha.

**5. Tentativa de Login com Ambos os Campos Vazios**
  - Verifica se uma mensagem de erro é exibida ao tentar logar com e-mail e senha vazios.

**6. Login com E-mail Inválido (Formato Incorreto)**
  - Verifica se uma mensagem de erro é exibida ao tentar logar com um e-mail em formato incorreto.

## Tecnologias Utilizadas
- Framework de Teste: Cypress
- Linguagem: JavaScript
- IDE: Visual Studio
  
## Pré-requisitos
- [Visual Studio Code](https://code.visualstudio.com/download) instalado.
- [Git](https://git-scm.com/downloads) instalado.
- [Node.js](https://nodejs.org/en/download/package-manager) `v18.15.0` (versão utilizada no projeto) instalado.
- Extensão Cypress Snippets `v1.2.0` instalada (pesquisar por Cypress Snippets em extensões no VS Code)


## Instruções para Executar os Testes
**1. Clone o repositório:**

```
git clone https://github.com/DrumondGit/dti-qa.git
```
**2. Instale as dependências:**
  - Acesse o diretório raíz do projeto (caso não esteja):
```
cd dti-qa
```
  - No diretório raiz do projeto, execute:
```
npm install
```
**3. Execute os Testes:**
  - No terminal, execute o Cypress com o comando:
```
npx cypress open
```
Em caso de erro, execute o comando abaixo para instalar o Cypress diretamente, assegurando que a instalação seja realizada corretamente. Após a conclusão, execute novamente o comando anterior.
```
npx cypress install
```
Isso abrirá a interface do Cypress:
- Selecione E2E Testing
- Selecione o browser (Chrome `v130`)
- Clique na opção **Start E2E Testing in Chrome**
- Por fim, acesse o arquivo `login_spec.cy.js` e execute os testes.



**4. Executar no modo Headless (opcional):**
Para executar os testes sem abrir a interface do Cypress:
```
npx cypress run
```
