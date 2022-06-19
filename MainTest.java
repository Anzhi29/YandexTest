import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MainTest {
    public WebDriver driver;
    public WebDriverWait wait;//функция для ожидания конкретных действий для конкретных элементов
    public PrintStream ps;
    public String a;
    private WebElement we;
    private static boolean b;
    private static String s, s2;
    private static int i, i2;
    private static byte[] myBytes = null;

    //вход в аккаунт
    @BeforeClass
    public void LogInAccount(){//WebDriver driver static
        try {
            //подключение драйвера для работы с chrome
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Program Files (x86)\\Google\\Driver\\101.0.4951.41\\chromedriver.exe");

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            //развернуть на весь экран
            driver.manage().window().maximize();
            //открыть страницу
            driver.get("https://mail.yandex.ru/?win=93&clid=1985545&uid=1370599934#inbox");

            //ожидает минуту или до загрузки страницы
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            //ожидает 30 секунд или до нахождения элемента
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            /* применимо ко всем элементам driver в методе*/

            //перейти на страницу авторизации
            driver.findElement(By.xpath("//a[@class='control button2 " +
                    "button2_view_classic button2_size_mail-big button2_theme_mail-white " +
                    "button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")).click();

            //ввести логин
            driver.findElement(By.xpath("//input[@name='login']"))
                    .sendKeys("anzh.mats@yandex.ru");

            //перейти к вводу пароля
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='passwd']")));

            // ввести пароль A1b2c3d4e5f6G7
            driver.findElement(By.xpath("//input[@name" +
                    "='passwd']")).sendKeys("A1b2c3d4e5f6G7");

            //войти
            driver.findElement(By.xpath("//button[@id='passp:sign-in']")).click();

            //System.out.println("Авторизация выполнена");

            ps = new PrintStream(System.out, true, "Windows-1251");
            ps.println("Авторизация выполнена");

            s = "Ошибка";
            myBytes = s.getBytes("Windows-1251");
            a = new String(myBytes);
        }
        catch (Exception e){
            ps.println("Ошибка\n");
        }
    }

    //переключение языка на английский
    @Test (priority = 1)
    public void English(){
        try{
            System.out.println("YA-1.1:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            /** ссылка разделяется на составляющие компоненты,
             * т.к. страница может иметь в адресе
             * номер экземпляра страницы(предположительно)
             *  */

            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //перейти в настройки
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //проверяет, ткрывается ли окно "Все настройки"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            /** div имеет разный class при открытом и закрытом окне настроек.
             * В условии создаётся список элементов,
             * чей тег соответсвует открытому окну настроек,
             * и проверяется наличие элементов в списке.
             * Если список пуст, значит, окно настроек не открыто*/
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //перейти в другие настройки
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //проверяет, совпадает ли текущая ссылка со страницей настроек
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, a + "\n");
            ps.println("3. Совпадение");

            //раскрыть выпадающий список языков
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //проверяет, открылся ли выпадающий список языков
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("4. Совпадение");

            //переключить на английский
            driver.findElement(By.xpath("//a[@data-params='lang=en']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, закрылся ли выпадающий список языков и переключился ли язык
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='English']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //переключение языка на русский
    @Test (priority = 2)
    public void Russia(){
        try{
            System.out.println("YA-1.2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //перейти в настройки
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //проверяет, ткрывается ли окно "Все настройки"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //перейти в другие настройки
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //проверяет, совпадает ли текущая ссылка со страницей настроек
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, a + "\n");
            ps.println("3. Совпадение");

            //раскрыть выпадающий список языков
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //проверяет, открылся ли выпадающий список языков
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("4. Совпадение");

            //переключить на русский
            driver.findElement(By.xpath("//a[@data-params='lang=ru']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            s = "Русский";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);

            //проверить, закрылся ли выпадающий список языков и подходящий ли выбранный язык
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && s.equals(driver.findElement(By.xpath
                            ("//span[@class='b-selink__link mail-Settings-Lang']")).getText());

            Assert.assertTrue(b, a + "\n");
            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Удаление без выбора писем для удаления
    @Test (priority = 3)
    public void DeleteWithoutSelection(){
        try{
            System.out.println("YA-2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //проверяет кликабельность кнопки удаления
            b = driver.findElement(By.xpath("//*[contains(@title, '(Delete)')]"))
                    .getAttribute("class").contains("is-disabled");

            /**Кириллица чувствительна к кодировке, поэтому, дабы её избежать,
             * была использована функция поиска по частичному совпадению
             *
             * wait.until(ExpectedConditions.elementToBeClickable(driver.findElement
             * (By.xpath("//*[contains(@title, '(Delete)')]"))))
             * данное выражение считает кнопку кликабельной даже в том случае, когда она не работает
             *
             * В Яндекс.Почте кнопки изменения состояния выбранных писем
             * в теге div имеют в своём классе значение "is-disabled"*/

            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
            return;
        }
    }

    //Удаление выбранного письма
    @Test (priority = 4)
    public void DeleteSelection(){
        try{
            System.out.println("YA-3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //создёт список из всех элементов сообщений
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            s = "Во входящих сообщениях писем нет";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);

            //проверяет наличие элементов в списке
            Assert.assertFalse(listElements.isEmpty(), s + "\n");

            //генерация случайного числа в пределах размера списка
            i = new Random().nextInt(listElements.size() - 1);

            //получить из списка элемент со случайным номером
            we = listElements.get(i);

            //выделить случайное письмо
            we.click();

            //получить чекбокс выделенного письма
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);

            //проверяет, отмечет ли чекбокс
            Assert.assertTrue(we.isSelected(), a + "\n");
            ps.println("2. Совпадение");

            i = listElements.size();

            //удаление выделенного письма
            driver.findElement(By.xpath("//span[@class='mail-Toolbar-Item-Text " +
                    "js-toolbar-item-title js-toolbar-item-title-delete']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, что писем стало меньше
            i2 = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']")).size() + 1;
            Assert.assertEquals(i2, i, a + "\n");
            ps.println("3. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Выделение писем
    @Test (priority = 5)
    public void Selection(){
        try{
            System.out.println("YA-4:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //создёт список из всех элементов сообщений
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            s = "Во входящих сообщениях только одно письмо или их нет";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);

            //проверка на наличие подходящего числа элементов в списке
            Assert.assertFalse(listElements.size() < 2,
                    s + "\n");

            //генерация первого случайного числа в пределах размера списка
            i = new Random().nextInt(listElements.size() - 1);

            //получить из списка первый элемент со случайным номером
            we = listElements.get(i);

            //выделить первое случайное письмо
            we.click();

            //получить чекбокс первого выделенного письма
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);

            //проверяет, отмечет ли первый чекбокс
            Assert.assertTrue(we.isSelected(), a + "\n");

            //генерация второго случайного числа в пределах размера списка
            i2 = new Random().nextInt(listElements.size() - 1);

            //если оба числа равны, то второе число изменяется
            while (i == i2){
                i2 = new Random().nextInt(listElements.size() - 1);
            }
            //получить из списка второй элемент со случайным номером
            we = listElements.get(i2);

            //выделить второе случайное письмо
            we.click();

            //получить чекбокс второго выделенного письма
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i2);

            //проверяет, отмечет ли второй чекбокс
            Assert.assertTrue(we.isSelected(), a + "\n");
            ps.println("2. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма
    @Test (priority = 6)
    public void SendingLetter(){//WebDriver driver, WebDriverWait wait static
        try{
            System.out.println("YA-5:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            s = "anzh.mats@yandex.ru";

            //ввести email отправителя (себя)
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, правильно ли введён отправитель
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, a + "\n");
            ps.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, a + "\n");
            /** Т.к. в сроке темы текст записывается не во внутрь элемента,
             * getText() не может его считать*/
            ps.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Письмо отправлено";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_direction_bottom-left popup2_visible_yes popup2_target_position " +
                            "popup2_motionless ComposeDoneScreen']")).size() > 0
                &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeDoneScreen-Title']")).getText())
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).isEmpty();
            Assert.assertTrue(b, a + "\n");
            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания адреса получателя и темы
    @Test (priority = 7)
    public void LetterWithoutAddressSubject(){
        try{
            //Thread.sleep(5000);//ожидает 5 секунд
            System.out.println("YA-6.1:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу
            /** при отправке\попытке отправки письма несколько раз подряд программными методами возникает проблема,
             * при которой программа не может взаимодействовать с полями на форме письма.
             * Но при перезагрузке страницы эта проблема решается*/

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Письмо не отправлено";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Пожалуйста, укажите адрес получателя";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и не закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText())
                &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;

            Assert.assertTrue(b, a + "\n");

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            ps.println("3. Совпадение\n");

        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания адреса получателя
    @Test (priority = 8)
    public void LetterWithoutAddress(){
        try{
            //Thread.sleep(5000);//ожидает 5 секунд
            System.out.println("YA-6.2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, a + "\n");
            ps.println("3. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Письмо не отправлено";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Пожалуйста, укажите адрес получателя";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и не закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                    &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText())
                    &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;

            Assert.assertTrue(b, a + "\n");

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            ps.println("4. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания темы
    @Test (priority = 9)
    public void LetterWithoutSubject(){
        try{
            //Thread.sleep(5000);//ожидает 5 секунд
            System.out.println("YA-6.3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //ввести email отправителя (себя)
            s = "anzh.mats@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, правильно ли введён отправитель
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, a + "\n");
            ps.println("3. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Внимание!";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Ваше письмо пока не отправлено";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и не закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                    &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText())
                    &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;

            Assert.assertTrue(b, a + "\n");
            /**На данном этапе будет выводиться ошибка,
             * т.к. Яндекс.Почта предоставляет возможность
             * отправлять письма без темы*/
            ps.println("4. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (без @)
    @Test (priority = 10)
    public void SendingLetterInvalidAddress1(){
        try{
            //Thread.sleep(5000);//ожидает 5 секунд
            System.out.println("YA-6.4.1:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //ввести email отправителя (себя) без @
            s = "anzh.matsyandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, a + "\n");
            ps.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, a + "\n");
            ps.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Проверьте получателя";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Похоже, что-то не так с адресом: anzh.matsyandex.ru.";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                    &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText())
                    &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;

            Assert.assertTrue(b, a + "\n");

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (содержащий @ @)
    @Test (priority = 11)
    public void SendingLetterInvalidAddress2(){
        try{
            System.out.println("YA-6.4.2:");

            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //ввести email отправителя (себя) с @@
            s = "anzh.mats@@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, a + "\n");
            ps.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, a + "\n");
            ps.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Проверьте получателя";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Похоже, что-то не так с адресом: anzh.mats@@yandex.ru.";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //s = driver.findElement(By.xpath
                    //("//div[@class='ComposeConfirmPopup-Description']")).getText();
            //System.out.println(s);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']"))
                            .size() > 0;

            Assert.assertTrue(b, a + "\n");

            b = s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                    &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText());

            Assert.assertTrue(b, a + "\n");

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (без домена)
    @Test (priority = 12)
    public void SendingLetterInvalidAddress3(){
        try{
            Thread.sleep(5000);//ожидает 5 секунд
            System.out.println("YA-6.4.3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, a + "\n");
            ps.println("1. Совпадение");

            driver.navigate().refresh();//обновить страницу

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;
            Assert.assertTrue(b, a + "\n");
            ps.println("2. Совпадение");

            //ввести email отправителя (себя) без домена
            s = "anzh.mats@";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, a + "\n");
            ps.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, a + "\n");
            ps.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            s = "Проверьте получателя";
            myBytes = s.getBytes("Windows-1251");
            s = new String(myBytes);
            s2 = "Похоже, что-то не так с адресом: anzh.mats@.";
            myBytes = s2.getBytes("Windows-1251");
            s2 = new String(myBytes);

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    s.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Title']")).getText())
                    &&
                    s2.equals(driver.findElement(By.xpath
                            ("//div[@class='ComposeConfirmPopup-Description']")).getText())
                    &&
                    driver.findElements(By.xpath("//div[@class='composeReact__scrollable-content']")).size() > 0;

            Assert.assertTrue(b, a + "\n");

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            ps.println("5. Совпадение\n");
        }
        catch (Exception e){
            ps.println("Ошибка: " + e + "\n");
        }
    }
}
