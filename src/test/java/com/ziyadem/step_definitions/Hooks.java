package com.ziyadem.step_definitions;

import com.ziyadem.pages.AccountPage;
import com.ziyadem.pages.ChangePasswordPage;
import com.ziyadem.pages.LoginPage;
import com.ziyadem.utilities.ConfigurationReader;
import com.ziyadem.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {
    @Before
    public void setUp(){
        Driver.get().manage().window().setPosition(new Point(-1000,0));
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

    }

    @After(order=0)
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] screenshot=((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
      Driver.closeDriver();
    }

    /**
     * TC07 cleanup - Runs when TC07 fails
     * Resets password from Strong2024 back to TestPass123!
     */
    @After(value = "@bug", order = 1)
    public void cleanup_bug_tests(Scenario scenario) {
        if (scenario.isFailed() && scenario.getName().contains("TC07")) {

            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║   TC07 CLEANUP AFTER FAILURE           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("Bug caused test to fail (expected)");
            System.out.println("Resetting password to TestPass123!...\n");

            try {
                LoginPage loginPage = new LoginPage();
                AccountPage accountPage = new AccountPage();
                ChangePasswordPage changePasswordPage = new ChangePasswordPage();

                String username = ConfigurationReader.get("Benutzername");
                String bugPassword = "Strong2024";
                String originalPassword = "TestPass123!";

                // Step 1: Logout
                try {
                    accountPage.logout();
                    System.out.println("  ✓ Logged out");
                } catch (Exception e) {
                    System.out.println("  - Not on account page");
                }

                // Step 2: Login with bug password
                loginPage.navigateToLoginPage();
                loginPage.loginWithPassword(username, bugPassword);
                System.out.println("  ✓ Logged in with: " + bugPassword);

                // Step 3: Navigate to Account Details
                accountPage.clickAccountDetailsPage();
                System.out.println("  ✓ Navigated to Account Details");

                // Step 4: Reset password
                changePasswordPage.resetPasswordToOriginal(bugPassword, originalPassword);
                System.out.println("  ✓ Password reset: " + bugPassword + " → " + originalPassword);

                // Step 5: Logout
                accountPage.logout();
                System.out.println("  ✓ Logged out");

                // Step 6: Verify
                loginPage.navigateToLoginPage();
                loginPage.loginForChangePassword();

                if (loginPage.isLoginSuccessful()) {
                    System.out.println("  ✓ Original password verified\n");
                    System.out.println("✅ CLEANUP COMPLETED SUCCESSFULLY");
                } else {
                    System.out.println("  ⚠️ Verification failed");
                }

            } catch (Exception e) {
                System.out.println("  ❌ Cleanup failed: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("════════════════════════════════════════\n");
        }
    }

}
