describe('Login - Cenários de Teste', () => {
  
  beforeEach(() => {
    cy.visit('http://automationpractice.pl/index.php?controller=authentication&back=myaccount');
  });

  // Cenário 1: Login com credenciais válidas
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário inserir um e-mail e senha válidos e clicar em "Login"
  // - Então o usuário deve ser redirecionado para a página de conta
  describe('Cenário 1: Login com credenciais válidas', () => {
    it('Deve redirecionar o usuário para a página de conta ao usar credenciais válidas', () => {
      cy.get('#email').type('testuser@domain.com');
      cy.get('#passwd').type('correctPassword');
      cy.get('#SubmitLogin').click();

      cy.url().should('include', 'controller=my-account');
    });
  });

  // Cenário 2: Tentativa de login com senha incorreta
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário inserir um e-mail válido, mas uma senha incorreta, e clicar em "Login"
  // - Então uma mensagem de erro de "Authentication failed" deve ser exibida
  describe('Cenário 2: Tentativa de login com senha incorreta', () => {
    it('Deve exibir uma mensagem de erro de autenticação ao inserir senha incorreta', () => {
      cy.get('#email').type('testuser@domain.com');
      cy.get('#passwd').type('wrongPassword');
      cy.get('#SubmitLogin').click();

      cy.get('.alert.alert-danger').should('contain', 'Authentication failed');
    });
  });

  // Cenário 3: Tentativa de login com campo de email vazio
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário deixar o campo de e-mail vazio e inserir apenas uma senha
  // - Então uma mensagem de erro informando que o e-mail é necessário deve ser exibida
  describe('Cenário 3: Tentativa de login com campo de email vazio', () => {
    it('Deve exibir uma mensagem de erro informando que o email é necessário', () => {
      cy.get('#passwd').type('somePassword');
      cy.get('#SubmitLogin').click();

      cy.get('.alert.alert-danger').should('contain', 'An email address required');
    });
  });

  // Cenário 4: Tentativa de login com campo de senha vazio
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário inserir um e-mail válido e deixar o campo de senha vazio, e clicar em "Login"
  // - Então uma mensagem de erro informando que a senha é necessária deve ser exibida
  describe('Cenário 4: Tentativa de login com campo de senha vazio', () => {
    it('Deve exibir uma mensagem de erro informando que a senha é necessária', () => {
      cy.get('#email').type('testuser@domain.com');
      cy.get('#SubmitLogin').click();

      cy.get('.alert.alert-danger').should('contain', 'Password is required');
    });
  });

  // Cenário 5: Tentativa de login com ambos os campos vazios
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário clicar em "Login" sem preencher o e-mail e a senha
  // - Então uma mensagem de erro informando que o e-mail é necessário deve ser exibida
  describe('Cenário 5: Tentativa de login com ambos os campos vazios', () => {
    it('Deve exibir uma mensagem de erro informando que o email é necessário', () => {
      cy.get('#SubmitLogin').click();

      cy.get('.alert.alert-danger').should('contain', 'An email address required');
    });
  });

  // Cenário 6: Login com email inválido (formato incorreto)
  // - Dado que o usuário esteja na página de login do site "Automation Practice"
  // - Quando o usuário inserir um e-mail em formato incorreto e uma senha válida e clicar em "Login"
  // - Então uma mensagem de erro informando que o e-mail é inválido deve ser exibida
  describe('Cenário 6: Login com email inválido (formato incorreto)', () => {
    it('Deve exibir uma mensagem de erro informando que o email é inválido', () => {
      cy.get('#email').type('invalidemail.com');
      cy.get('#passwd').type('somePassword');
      cy.get('#SubmitLogin').click();

      cy.get('.alert.alert-danger').should('contain', 'Invalid email address');
    });
  });

});
