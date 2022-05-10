import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Random;
/**��������� �������� ���������
 * ��� ��������� windows-1251*/
public class MainTest {
    public WebDriver driver;
    public WebDriverWait wait;//������� ��� �������� ���������� �������� ��� ���������� ���������
    private WebElement we;
    private static boolean b;
    private static String s;
    private static int i, i2;

    //���� � �������
    @BeforeClass
    public void LogInAccount(){//WebDriver driver static
        try {
            //����������� �������� ��� ������ � chrome
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Program Files (x86)\\Google\\Driver\\101.0.4951.41\\chromedriver.exe");

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //���������� �� ���� �����
            driver.manage().window().maximize();
            //������� ��������
            driver.get("https://mail.yandex.ru/?win=93&clid=1985545&uid=1370599934#inbox");

            //������� ������ ��� �� �������� ��������
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            //������� 30 ������ ��� �� ���������� ��������
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            /* ��������� �� ���� ��������� driver � ������*/

            //������� �� �������� �����������
            driver.findElement(By.xpath("//a[@class='control button2 " +
                    "button2_view_classic button2_size_mail-big button2_theme_mail-white " +
                    "button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")).click();

            //������ �����
            driver.findElement(By.xpath("//input[@name='login']"))
                    .sendKeys("anzh.mats@yandex.ru");

            //������� � ����� ������
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='passwd']")));

            // ������ ������ A1b2c3d4e5f6G7
            driver.findElement(By.xpath("//input[@name" +
                    "='passwd']")).sendKeys("A1b2c3d4e5f6G7");

            //�����
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();


            System.out.println("����������� ���������\n");
        }
        catch (Exception e){
            System.out.println("������\n");
        }
    }

    //������������ ����� �� ����������
    @Test
    public void English(){
        try{
            System.out.println("YA-1.1:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            /** ������ ����������� �� ������������ ����������,
             * �.�. �������� ����� ����� � ������
             * ����� ���������� ��������(����������������)
             *  */
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� � ���������
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //���������, ���������� �� ���� "��� ���������"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            /** div ����� ������ class ��� �������� � �������� ���� ��������.
             * � ������� �������� ������ ���������,
             * ��� ��� ������������ ��������� ���� ��������,
             * � ����������� ������� ��������� � ������.
             * ���� ������ ����, ������, ���� �������� �� �������*/
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������� � ������ ���������
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //���������, ��������� �� ������� ������ �� ��������� ��������
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "������\n");
            System.out.println("3. ����������");

            //�������� ���������� ������ ������
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //���������, �������� �� ���������� ������ ������
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("4. ����������");

            //����������� �� ����������
            driver.findElement(By.xpath("//a[@data-params='lang=en']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, �������� �� ���������� ������ ������ � ������������ �� ����
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='English']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //������������ ����� �� �������
    @Test
    public void Russia(){
        try{
            System.out.println("YA-1.2:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� � ���������
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //���������, ���������� �� ���� "��� ���������"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������� � ������ ���������
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //���������, ��������� �� ������� ������ �� ��������� ��������
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "������\n");
            System.out.println("3. ����������");

            //�������� ���������� ������ ������
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //���������, �������� �� ���������� ������ ������
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("4. ����������");

            //����������� �� �������
            driver.findElement(By.xpath("//a[@data-params='lang=ru']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, �������� �� ���������� ������ ������
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='�������']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ��� ������ ����� ��� ��������
    @Test
    public void DeleteWithoutSelection(){
        try{
            System.out.println("YA-2:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //��������� �������������� ������ ��������
            b = driver.findElement(By.xpath("//div[@title='������� (Delete)']")).
                    getAttribute("class").contains("is-disabled");
            /**� ������.����� ������ ��������� ��������� ��������� �����
             * � ���� div ����� � ���� ������ �������� "is-disabled"*/
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
            return;
        }
    }

    //�������� ���������� ������
    @Test
    public void DeleteSelection(){
        try{
            System.out.println("YA-3:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //����� ������ �� ���� ��������� ���������
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //��������� ������� ��������� � ������
            Assert.assertFalse(listElements.isEmpty(), "�� �������� ���������� ����� ���\n");

            //��������� ���������� ����� � �������� ������� ������
            i = new Random().nextInt(listElements.size() - 1);

            //�������� �� ������ ������� �� ��������� �������
            we = listElements.get(i);

            //�������� ��������� ������
            we.click();

            //�������� ������� ����������� ������
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);
            /*������� ������� ���� �� ����������
            * */

            //���������, ������� �� �������
            Assert.assertTrue(we.isSelected(), "������\n");
            System.out.println("2. ����������");

            i = listElements.size();

            //�������� ����������� ������
            driver.findElement(By.xpath("//span[@class='mail-Toolbar-Item-Text " +
                    "js-toolbar-item-title js-toolbar-item-title-delete']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��� ����� ����� ������
            i2 = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']")).size() + 1;
            Assert.assertEquals(i2, i, "������\n");
                System.out.println("3. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //��������� �����
    @Test
    public void Selection(){
        try{
            System.out.println("YA-4:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //����� ������ �� ���� ��������� ���������
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //�������� �� ������� ����������� ����� ��������� � ������
            Assert.assertFalse(listElements.size() < 2,
                    "�� �������� ���������� ������ ���� ������ ��� �� ���\n");

            //��������� ������� ���������� ����� � �������� ������� ������
            i = new Random().nextInt(listElements.size() - 1);

            //�������� �� ������ ������ ������� �� ��������� �������
            we = listElements.get(i);

            //�������� ������ ��������� ������
            we.click();

            //�������� ������� ������� ����������� ������
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);

            //���������, ������� �� ������ �������
            Assert.assertTrue(we.isSelected(), "������\n");

            //��������� ������� ���������� ����� � �������� ������� ������
            i2 = new Random().nextInt(listElements.size() - 1);

            //���� ��� ����� �����, �� ������ ����� ����������
            while (i == i2){
                i2 = new Random().nextInt(listElements.size() - 1);
            }
            //�������� �� ������ ������ ������� �� ��������� �������
            we = listElements.get(i2);

            //�������� ������ ��������� ������
            we.click();

            //�������� ������� ������� ����������� ������
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i2);

            //���������, ������� �� ������ �������
            Assert.assertTrue(we.isSelected(), "������\n");
            System.out.println("2. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������
    @Test
    public void SendingLetter(){//WebDriver driver, WebDriverWait wait static
        try{
            System.out.println("YA-5:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            s = "anzh.mats@yandex.ru";

            //������ email ����������� (����)
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //���������, ��������� �� ����� �����������
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "������\n");
            System.out.println("3. ����������");

            //������ ���� ������
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //���������, ��������� �� ������� ���� ������
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "������\n");
            /** �.�. � ����� ���� ����� ������������ �� �� ������ ��������,
             * getText() �� ����� ��� �������*/
            System.out.println("4. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b =     driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_direction_bottom-left popup2_visible_yes popup2_target_position " +
                            "popup2_motionless ComposeDoneScreen']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='������ ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).isEmpty();
            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ ��� �������� ������ ���������� � ����
    @Test
    public void LetterWithoutAddressSubject(){
        try{
            System.out.println("YA-6.1:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='������ �� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='����������, ������� ����� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //��������� ��������������
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "������\n");
            System.out.println("3. ����������\n");

        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ ��� �������� ������ ����������
    @Test
    public void LetterWithoutAddress(){
        try{
            System.out.println("YA-6.2:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������ ���� ������
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //���������, ��������� �� ������� ���� ������
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "������\n");
            System.out.println("3. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='������ �� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='����������, ������� ����� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //��������� ��������������
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "������\n");
            System.out.println("4. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ ��� �������� ����
    @Test
    public void LetterWithoutSubject(){
        try{
            System.out.println("YA-6.3:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������ email ����������� (����)
            s = "anzh.mats@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //���������, ��������� �� ����� �����������
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "������\n");
            System.out.println("3. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='��������!']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='���� ������ ���� �� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            /**�� ������ ����� ����� ���������� ������,
             * �.�. ������.����� ������������� �����������
             * ���������� ������ ��� ����*/
            System.out.println("4. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ � ������������ ������� ���������� (��� @)
    @Test
    public void SendingLetterInvalidAddress1(){
        try{
            System.out.println("YA-6.4.1:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������ email ����������� (����) ��� @
            s = "anzh.matsyandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //���������, ��������� �� ������� ����������� � �����������
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "������\n");
            System.out.println("3. ����������");

            //������ ���� ������
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //���������, ��������� �� ������� ���� ������
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "������\n");
            System.out.println("4. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='��������� ����������']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='������, ���-�� �� ��� � �������: anzh.matsyandex.ru.']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //��������� ��������������
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ � ������������ ������� ���������� (���������� @ @)
    @Test
    public void SendingLetterInvalidAddress2(){
        try{
            System.out.println("YA-6.4.2:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������ email ����������� (����) � @@
            s = "anzh.mats@@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //���������, ��������� �� ������� ����������� � �����������
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "������\n");
            System.out.println("3. ����������");

            //������ ���� ������
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //���������, ��������� �� ������� ���� ������
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "������\n");
            System.out.println("4. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='��������� ����������']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='������, ���-�� �� ��� � �������: anzh.mats@@yandex.ru.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //��������� ��������������
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }

    //�������� ������ � ������������ ������� ���������� (��� ������)
    @Test
    public void SendingLetterInvalidAddress3(){
        try{
            System.out.println("YA-6.4.3:");
            //������� �� ������� �������� ������.�����
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//������� 2 ������� (�� ������ �������� ��������)

            //���������, ��������� �� ������ � ������� ��������� ������.�����
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "������\n");
            System.out.println("1. ����������");

            //������� ����� ������
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //���������, ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "������\n");
            System.out.println("2. ����������");

            //������ email ����������� (����) ��� ������
            s = "anzh.mats@";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //���������, ��������� �� ������� ����������� � �����������
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "������\n");
            System.out.println("3. ����������");

            //������ ���� ������
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //���������, ��������� �� ������� ���� ������
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "������\n");
            System.out.println("4. ����������");

            //��������� ������
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //���������, ��������� �� ��������������, ������������ ������ ��������� � ��������� �� ����� ������
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='��������� ����������']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='������, ���-�� �� ��� � �������: anzh.mats@.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //��������� ��������������
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "������\n");
            System.out.println("5. ����������\n");
        }
        catch (Exception e){
            System.out.println("������: " + e + "\n");
        }
    }
}
