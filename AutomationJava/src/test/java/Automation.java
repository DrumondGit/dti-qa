import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.AssertJUnit.assertTrue;

public class Automation {
    /*
    @Test
    public void pesquisarGoogle() {
        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
        WebDriver navegar = new ChromeDriver();

        navegar.get("https://google.com");
    }
    */
    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuração do caminho do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/drive/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.pl/index.php?controller=authentication&back=myaccount");
    }

    @After
    public void tearDown() {
        // Fechar o navegador após os testes
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Cenário 1: Login com credenciais válidas
     * Dado que o usuário esteja na página de login
     * Quando o usuário preencher o email e a senha válidos
     * Então o usuário deve ser redirecionado para a página da conta
     */
    @Test
    public void testLoginWithValidCredentials() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("passwd"));
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));

        emailField.sendKeys("testuser@domain.com");
        passwordField.sendKeys("correctPassword");
        loginButton.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Usuário não foi redirecionado corretamente", currentUrl.contains("controller=my-account"));
    }

    /**
     * Cenário 2: Tentativa de login com senha incorreta
     * Dado que o usuário esteja na página de login
     * Quando o usuário preencher o email correto e a senha incorreta
     * Então uma mensagem de erro de autenticação deve ser exibida
     */
    @Test
    public void testLoginWithIncorrectPassword() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("passwd"));
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));

        emailField.sendKeys("testuser@domain.com");
        passwordField.sendKeys("wrongPassword");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
        assertTrue("Mensagem de erro não exibida", errorMessage.isDisplayed());
        assertTrue("Mensagem de erro incorreta", errorMessage.getText().contains("Authentication failed"));
    }

    /**
     * Cenário 3: Tentativa de login com campo de email vazio
     * Dado que o usuário esteja na página de login
     * Quando o usuário deixar o campo de email vazio e preencher a senha
     * Então uma mensagem de erro deve informar que o email é necessário
     */
    @Test
    public void testLoginWithEmptyEmailField() {
        WebElement passwordField = driver.findElement(By.id("passwd"));
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));

        passwordField.sendKeys("somePassword");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
        assertTrue("Mensagem de erro não exibida", errorMessage.isDisplayed());
        assertTrue("Mensagem de erro incorreta", errorMessage.getText().contains("An email address required"));
    }

    /**
     * Cenário 4: Tentativa de login com campo de senha vazio
     * Dado que o usuário esteja na página de login
     * Quando o usuário preencher o email e deixar o campo de senha vazio
     * Então uma mensagem de erro deve informar que a senha é necessária
     */
    @Test
    public void testLoginWithEmptyPasswordField() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));

        emailField.sendKeys("testuser@domain.com");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
        assertTrue("Mensagem de erro não exibida", errorMessage.isDisplayed());
        assertTrue("Mensagem de erro incorreta", errorMessage.getText().contains("Password is required"));
    }

    /**
     * Cenário 5: Tentativa de login com email e senha vazios
     * Dado que o usuário esteja na página de login
     * Quando o usuário deixar ambos os campos vazios
     * Então uma mensagem de erro deve informar que o email é necessário
     */
    @Test
    public void testLoginWithBothFieldsEmpty() {
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
        assertTrue("Mensagem de erro não exibida", errorMessage.isDisplayed());
        assertTrue("Mensagem de erro incorreta", errorMessage.getText().contains("An email address required"));
    }

    /**
     * Cenário 6: Login com email inválido (formato incorreto)
     * Dado que o usuário esteja na página de login
     * Quando o usuário inserir um email com formato inválido
     * Então uma mensagem de erro deve informar que o email é inválido
     */
    @Test
    public void testLoginWithInvalidEmailFormat() {
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("passwd"));
        WebElement loginButton = driver.findElement(By.id("SubmitLogin"));

        emailField.sendKeys("invalidemail.com");
        passwordField.sendKeys("somePassword");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".alert.alert-danger"));
        assertTrue("Mensagem de erro não exibida", errorMessage.isDisplayed());
        assertTrue("Mensagem de erro incorreta", errorMessage.getText().contains("Invalid email address"));
    }

    /**
     * Cenário 7: Verificar redirecionamento do botão "Esqueceu sua senha?"
     * Dado que o usuário esteja na página de login
     * Quando o usuário clicar no link "Esqueceu sua senha?"
     * Então o usuário deve ser redirecionado para a página de recuperação de senha
     */
    @Test
    public void testForgotPasswordRedirection() {
        WebElement forgotPasswordLink = driver.findElement(By.cssSelector(".lost_password a"));
        forgotPasswordLink.click();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Redirecionamento incorreto", currentUrl.contains("controller=password"));
    }
}