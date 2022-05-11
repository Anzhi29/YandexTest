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
/**ïðîãðàììà ðàáîòàåò êîððåêòíî
 * ïðè êîäèðîâêå windows-1251*/
public class MainTest {
    public WebDriver driver;
    public WebDriverWait wait;//ôóíêöèÿ äëÿ îæèäàíèÿ êîíêðåòíûõ äåéñòâèé äëÿ êîíêðåòíûõ ýëåìåíòîâ
    private WebElement we;
    private static boolean b;
    private static String s;
    private static int i, i2;

    //âõîä â àêêàóíò
    @BeforeClass
    public void LogInAccount(){//WebDriver driver static
        try {
            //ïîäêëþ÷åíèå äðàéâåðà äëÿ ðàáîòû ñ chrome
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Program Files (x86)\\Google\\Driver\\101.0.4951.41\\chromedriver.exe");

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //ðàçâåðíóòü íà âåñü ýêðàí
            driver.manage().window().maximize();
            //îòêðûòü ñòðàíèöó
            driver.get("https://mail.yandex.ru/?win=93&clid=1985545&uid=1370599934#inbox");

            //îæèäàåò ìèíóòó èëè äî çàãðóçêè ñòðàíèöû
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            //îæèäàåò 30 ñåêóíä èëè äî íàõîæäåíèÿ ýëåìåíòà
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            /* ïðèìåíèìî êî âñåì ýëåìåíòàì driver â ìåòîäå*/

            //ïåðåéòè íà ñòðàíèöó àâòîðèçàöèè
            driver.findElement(By.xpath("//a[@class='control button2 " +
                    "button2_view_classic button2_size_mail-big button2_theme_mail-white " +
                    "button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")).click();

            //ââåñòè ëîãèí
            driver.findElement(By.xpath("//input[@name='login']"))
                    .sendKeys("anzh.mats@yandex.ru");

            //ïåðåéòè ê ââîäó ïàðîëÿ
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='passwd']")));

            // ââåñòè ïàðîëü A1b2c3d4e5f6G7
            driver.findElement(By.xpath("//input[@name" +
                    "='passwd']")).sendKeys("A1b2c3d4e5f6G7");

            //âîéòè
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();


            System.out.println("Àâòîðèçàöèÿ âûïîëíåíà\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà\n");
        }
    }

    //ïåðåêëþ÷åíèå ÿçûêà íà àíãëèéñêèé
    @Test(priority = 1)
    public void English(){
        try{
            System.out.println("YA-1.1:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            /** ññûëêà ðàçäåëÿåòñÿ íà ñîñòàâëÿþùèå êîìïîíåíòû,
             * ò.ê. ñòðàíèöà ìîæåò èìåòü â àäðåñå
             * íîìåð ýêçåìïëÿðà ñòðàíèöû(ïðåäïîëîæèòåëüíî)
             *  */
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //ïåðåéòè â íàñòðîéêè
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //ïðîâåðÿåò, òêðûâàåòñÿ ëè îêíî "Âñå íàñòðîéêè"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            /** div èìååò ðàçíûé class ïðè îòêðûòîì è çàêðûòîì îêíå íàñòðîåê.
             * Â óñëîâèè ñîçäà¸òñÿ ñïèñîê ýëåìåíòîâ,
             * ÷åé òåã ñîîòâåòñâóåò îòêðûòîìó îêíó íàñòðîåê,
             * è ïðîâåðÿåòñÿ íàëè÷èå ýëåìåíòîâ â ñïèñêå.
             * Åñëè ñïèñîê ïóñò, çíà÷èò, îêíî íàñòðîåê íå îòêðûòî*/
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ïåðåéòè â äðóãèå íàñòðîéêè
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //ïðîâåðÿåò, ñîâïàäàåò ëè òåêóùàÿ ññûëêà ñî ñòðàíèöåé íàñòðîåê
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ðàñêðûòü âûïàäàþùèé ñïèñîê ÿçûêîâ
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //ïðîâåðÿåò, îòêðûëñÿ ëè âûïàäàþùèé ñïèñîê ÿçûêîâ
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå");

            //ïåðåêëþ÷èòü íà àíãëèéñêèé
            driver.findElement(By.xpath("//a[@data-params='lang=en']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, çàêðûëñÿ ëè âûïàäàþùèé ñïèñîê ÿçûêîâ è ïåðåêëþ÷èëñÿ ëè ÿçûê
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='English']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //ïåðåêëþ÷åíèå ÿçûêà íà ðóññêèé
    @Test(priority = 2)
    public void Russia(){
        try{
            System.out.println("YA-1.2:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //ïåðåéòè â íàñòðîéêè
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //ïðîâåðÿåò, òêðûâàåòñÿ ëè îêíî "Âñå íàñòðîéêè"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ïåðåéòè â äðóãèå íàñòðîéêè
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //ïðîâåðÿåò, ñîâïàäàåò ëè òåêóùàÿ ññûëêà ñî ñòðàíèöåé íàñòðîåê
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ðàñêðûòü âûïàäàþùèé ñïèñîê ÿçûêîâ
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //ïðîâåðÿåò, îòêðûëñÿ ëè âûïàäàþùèé ñïèñîê ÿçûêîâ
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå");

            //ïåðåêëþ÷èòü íà ðóññêèé
            driver.findElement(By.xpath("//a[@data-params='lang=ru']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðèòü, çàêðûëñÿ ëè âûïàäàþùèé ñïèñîê ÿçûêîâ
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='Ðóññêèé']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Óäàëåíèå áåç âûáîðà ïèñåì äëÿ óäàëåíèÿ
    @Test(priority = 3)
    public void DeleteWithoutSelection(){
        try{
            System.out.println("YA-2:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //ïðîâåðÿåò êëèêàáåëüíîñòü êíîïêè óäàëåíèÿ
            b = driver.findElement(By.xpath("//div[@title='Óäàëèòü (Delete)']")).
                    getAttribute("class").contains("is-disabled");
            /**Â ßíäåêñ.Ïî÷òå êíîïêè èçìåíåíèÿ ñîñòîÿíèÿ âûáðàííûõ ïèñåì
             * â òåãå div èìåþò â ñâî¸ì êëàññå çíà÷åíèå "is-disabled"*/
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
            return;
        }
    }

    //Óäàëåíèå âûáðàííîãî ïèñüìà
    @Test(priority = 4)
    public void DeleteSelection(){
        try{
            System.out.println("YA-3:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //ñîçä¸ò ñïèñîê èç âñåõ ýëåìåíòîâ ñîîáùåíèé
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //ïðîâåðÿåò íàëè÷èå ýëåìåíòîâ â ñïèñêå
            Assert.assertFalse(listElements.isEmpty(), "Âî âõîäÿùèõ ñîîáùåíèÿõ ïèñåì íåò\n");

            //ãåíåðàöèÿ ñëó÷àéíîãî ÷èñëà â ïðåäåëàõ ðàçìåðà ñïèñêà
            i = new Random().nextInt(listElements.size() - 1);

            //ïîëó÷èòü èç ñïèñêà ýëåìåíò ñî ñëó÷àéíûì íîìåðîì
            we = listElements.get(i);

            //âûäåëèòü ñëó÷àéíîå ïèñüìî
            we.click();

            //ïîëó÷èòü ÷åêáîêñ âûäåëåííîãî ïèñüìà
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);
            /*ýëåìåíò äàííîãî òèïà íå êëèêàáåëåí
            * */

            //ïðîâåðÿåò, îòìå÷åò ëè ÷åêáîêñ
            Assert.assertTrue(we.isSelected(), "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            i = listElements.size();

            //óäàëåíèå âûäåëåííîãî ïèñüìà
            driver.findElement(By.xpath("//span[@class='mail-Toolbar-Item-Text " +
                    "js-toolbar-item-title js-toolbar-item-title-delete']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ÷òî ïèñåì ñòàëî ìåíüøå
            i2 = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']")).size() + 1;
            Assert.assertEquals(i2, i, "Îøèáêà\n");
                System.out.println("3. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Âûäåëåíèå ïèñåì
    @Test(priority = 5)
    public void Selection(){
        try{
            System.out.println("YA-4:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //ñîçä¸ò ñïèñîê èç âñåõ ýëåìåíòîâ ñîîáùåíèé
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //ïðîâåðêà íà íàëè÷èå ïîäõîäÿùåãî ÷èñëà ýëåìåíòîâ â ñïèñêå
            Assert.assertFalse(listElements.size() < 2,
                    "Âî âõîäÿùèõ ñîîáùåíèÿõ òîëüêî îäíî ïèñüìî èëè èõ íåò\n");

            //ãåíåðàöèÿ ïåðâîãî ñëó÷àéíîãî ÷èñëà â ïðåäåëàõ ðàçìåðà ñïèñêà
            i = new Random().nextInt(listElements.size() - 1);

            //ïîëó÷èòü èç ñïèñêà ïåðâûé ýëåìåíò ñî ñëó÷àéíûì íîìåðîì
            we = listElements.get(i);

            //âûäåëèòü ïåðâîå ñëó÷àéíîå ïèñüìî
            we.click();

            //ïîëó÷èòü ÷åêáîêñ ïåðâîãî âûäåëåííîãî ïèñüìà
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);

            //ïðîâåðÿåò, îòìå÷åò ëè ïåðâûé ÷åêáîêñ
            Assert.assertTrue(we.isSelected(), "Îøèáêà\n");

            //ãåíåðàöèÿ âòîðîãî ñëó÷àéíîãî ÷èñëà â ïðåäåëàõ ðàçìåðà ñïèñêà
            i2 = new Random().nextInt(listElements.size() - 1);

            //åñëè îáà ÷èñëà ðàâíû, òî âòîðîå ÷èñëî èçìåíÿåòñÿ
            while (i == i2){
                i2 = new Random().nextInt(listElements.size() - 1);
            }
            //ïîëó÷èòü èç ñïèñêà âòîðîé ýëåìåíò ñî ñëó÷àéíûì íîìåðîì
            we = listElements.get(i2);

            //âûäåëèòü âòîðîå ñëó÷àéíîå ïèñüìî
            we.click();

            //ïîëó÷èòü ÷åêáîêñ âòîðîãî âûäåëåííîãî ïèñüìà
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i2);

            //ïðîâåðÿåò, îòìå÷åò ëè âòîðîé ÷åêáîêñ
            Assert.assertTrue(we.isSelected(), "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà
    @Test(priority = 6)
    public void SendingLetter(){//WebDriver driver, WebDriverWait wait static
        try{
            System.out.println("YA-5:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            s = "anzh.mats@yandex.ru";

            //ââåñòè email îòïðàâèòåëÿ (ñåáÿ)
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåä¸í îòïðàâèòåëü
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ââåñòè òåìó ïèñüìà
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåäåíà òåìà ïèñüìà
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Îøèáêà\n");
            /** Ò.ê. â ñðîêå òåìû òåêñò çàïèñûâàåòñÿ íå âî âíóòðü ýëåìåíòà,
             * getText() íå ìîæåò åãî ñ÷èòàòü*/
            System.out.println("4. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b =     driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_direction_bottom-left popup2_visible_yes popup2_target_position " +
                            "popup2_motionless ComposeDoneScreen']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Ïèñüìî îòïðàâëåíî']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).isEmpty();
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà áåç óêàçàíèÿ àäðåñà ïîëó÷àòåëÿ è òåìû
    @Test(priority = 7)
    public void LetterWithoutAddressSubject(){
        try{
            System.out.println("YA-6.1:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Ïèñüìî íå îòïðàâëåíî']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ïîæàëóéñòà, óêàæèòå àäðåñ ïîëó÷àòåëÿ']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //çàêðûâàåò ïðåäóïðåæäåíèå
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå\n");

        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà áåç óêàçàíèÿ àäðåñà ïîëó÷àòåëÿ
    @Test(priority = 8)
    public void LetterWithoutAddress(){
        try{
            System.out.println("YA-6.2:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ââåñòè òåìó ïèñüìà
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåäåíà òåìà ïèñüìà
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Ïèñüìî íå îòïðàâëåíî']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ïîæàëóéñòà, óêàæèòå àäðåñ ïîëó÷àòåëÿ']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //çàêðûâàåò ïðåäóïðåæäåíèå
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà áåç óêàçàíèÿ òåìû
    @Test(priority = 9)
    public void LetterWithoutSubject(){
        try{
            System.out.println("YA-6.3:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ââåñòè email îòïðàâèòåëÿ (ñåáÿ)
            s = "anzh.mats@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåä¸í îòïðàâèòåëü
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Âíèìàíèå!']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Âàøå ïèñüìî ïîêà íå îòïðàâëåíî']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            /**Íà äàííîì ýòàïå áóäåò âûâîäèòüñÿ îøèáêà,
             * ò.ê. ßíäåêñ.Ïî÷òà ïðåäîñòàâëÿåò âîçìîæíîñòü
             * îòïðàâëÿòü ïèñüìà áåç òåìû*/
            System.out.println("4. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà ñ íåêîððåêòíûì àäðåñîì ïîëó÷àòåëÿ (áåç @)
    @Test(priority = 10)
    public void SendingLetterInvalidAddress1(){
        try{
            System.out.println("YA-6.4.1:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ââåñòè email îòïðàâèòåëÿ (ñåáÿ) áåç @
            s = "anzh.matsyandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //ïðîâåðÿåò, ñîâïàäàåò ëè ââåä¸íûé îòïðàâèòåëü ñ ïëàíèðóåìûì
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ââåñòè òåìó ïèñüìà
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåäåíà òåìà ïèñüìà
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Ïðîâåðüòå ïîëó÷àòåëÿ']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ïîõîæå, ÷òî-òî íå òàê ñ àäðåñîì: anzh.matsyandex.ru.']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //çàêðûâàåò ïðåäóïðåæäåíèå
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà ñ íåêîððåêòíûì àäðåñîì ïîëó÷àòåëÿ (ñîäåðæàùèé @ @)
    @Test(priority = 11)
    public void SendingLetterInvalidAddress2(){
        try{
            System.out.println("YA-6.4.2:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ââåñòè email îòïðàâèòåëÿ (ñåáÿ) ñ @@
            s = "anzh.mats@@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //ïðîâåðÿåò, ñîâïàäàåò ëè ââåä¸íûé îòïðàâèòåëü ñ ïëàíèðóåìûì
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ââåñòè òåìó ïèñüìà
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåäåíà òåìà ïèñüìà
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='Ïðîâåðüòå ïîëó÷àòåëÿ']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ïîõîæå, ÷òî-òî íå òàê ñ àäðåñîì: anzh.mats@@yandex.ru.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //çàêðûâàåò ïðåäóïðåæäåíèå
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }

    //Îòïðàâêà ïèñüìà ñ íåêîððåêòíûì àäðåñîì ïîëó÷àòåëÿ (áåç äîìåíà)
    @Test(priority = 12)
    public void SendingLetterInvalidAddress3(){
        try{
            System.out.println("YA-6.4.3:");
            //ïåðåéòè íà ãëàâíóþ ñòðàíèöó ßíäåêñ.Ïî÷òà
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//îæèäàåò 2 ñåêóíäû (äî íà÷àëà çàãðóçêè ñòðàíèöû)

            //ïðîâåðÿåò, ñîâïàäàåò ëè ññûëêà ñ ãëàâíîé ñòðàíèöåé ßíäåêñ.Ïî÷òà
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("1. Ñîâïàäåíèå");

            //îòêðûòü ôîðìó ïèñüìà
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //ïðîâåðÿåò, îòêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("2. Ñîâïàäåíèå");

            //ââåñòè email îòïðàâèòåëÿ (ñåáÿ) áåç äîìåíà
            s = "anzh.mats@";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //ïðîâåðÿåò, ñîâïàäàåò ëè ââåä¸íûé îòïðàâèòåëü ñ ïëàíèðóåìûì
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Îøèáêà\n");
            System.out.println("3. Ñîâïàäåíèå");

            //ââåñòè òåìó ïèñüìà
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //ïðîâåðÿåò, ïðàâèëüíî ëè ââåäåíà òåìà ïèñüìà
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Îøèáêà\n");
            System.out.println("4. Ñîâïàäåíèå");

            //îòïðàâèòü ïèñüìî
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //ïðîâåðÿåò, ïîÿâèëîñü ëè ïðåäóïðåæäåíèå, ïðàâèëüíîñòü òåêñòà ñîîáùåíèÿ è çàêðûëàñü ëè ôîðìà ïèñüìà
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='Ïðîâåðüòå ïîëó÷àòåëÿ']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ïîõîæå, ÷òî-òî íå òàê ñ àäðåñîì: anzh.mats@.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //çàêðûâàåò ïðåäóïðåæäåíèå
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Îøèáêà\n");
            System.out.println("5. Ñîâïàäåíèå\n");
        }
        catch (Exception e){
            System.out.println("Îøèáêà: " + e + "\n");
        }
    }
}
