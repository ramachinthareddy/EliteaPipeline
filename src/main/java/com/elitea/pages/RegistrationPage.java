package com.elitea.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Registration page. Locators are placeholders and should be updated
 * to match the AUT. Methods are kept small (Single Responsibility) to follow SOLID.
 */
public class RegistrationPage extends BasePage {

    // Locators - update these to match application under test
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By phoneInput = By.id("phone");
    private final By passwordInput = By.id("password");
    private final By passwordConfirmInput = By.id("passwordConfirm");
    private final By submitButton = By.id("register-submit");
    private final By validationMessage = By.cssSelector(".validation-error");
    private final By contactMethodToggleEmail = By.id("contact-email");
    private final By contactMethodTogglePhone = By.id("contact-phone");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(contactMethodToggleEmail).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPhone(String phone) {
        driver.findElement(contactMethodTogglePhone).click();
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterPasswordConfirmation(String pwConfirm) {
        driver.findElement(passwordConfirmInput).clear();
        driver.findElement(passwordConfirmInput).sendKeys(pwConfirm);
    }

    public void submit() {
        driver.findElement(submitButton).click();
    }

    public String getValidationMessage() {
        WebElement el = driver.findElement(validationMessage);
        return el.isDisplayed() ? el.getText() : "";
    }

    public boolean isOnRegistrationPage() {
        // Quick heuristic - override as required
        return driver.getCurrentUrl().contains("register") || driver.getTitle().toLowerCase().contains("register");
    }
}
