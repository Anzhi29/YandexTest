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
/**программа работает корректно
 * при кодировке windows-1251*/
public class MainTest {
    public WebDriver driver;
    public WebDriverWait wait;//функция для ожидания конкретных действий для конкретных элементов
    private WebElement we;
    private static boolean b;
    private static String s;
    private static int i, i2;

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


            System.out.println("Авторизация выполнена\n");
        }
        catch (Exception e){
            System.out.println("Ошибка\n");
        }
    }

    //переключение языка на английский
    @Test
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
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

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
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //перейти в другие настройки
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //проверяет, совпадает ли текущая ссылка со страницей настроек
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("3. Совпадение");

            //раскрыть выпадающий список языков
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //проверяет, открылся ли выпадающий список языков
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("4. Совпадение");

            //переключить на английский
            driver.findElement(By.xpath("//a[@data-params='lang=en']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, закрылся ли выпадающий список языков и переключился ли язык
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='English']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //переключение языка на русский
    @Test
    public void Russia(){
        try{
            System.out.println("YA-1.2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //перейти в настройки
            driver.findElement(By.xpath("//button[@class='Button2 Button2_view_clear mail-GhostButton " +
                    "mail-SettingsButton SettingsButton__hasNew--5shl0']")).click();

            //проверяет, ткрывается ли окно "Все настройки"
            b = driver.findElements(By.xpath("//div[@class='Popup2 Popup2_visible " +
                    "Popup2_target_anchor Popup2_view_default SettingsButton__popup--q2Y0l " +
                    "Theme Theme_root_ps-light Theme_color_ps-light']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //перейти в другие настройки
            driver.findElement(By.xpath("//a[@href='#setup/other']")).click();

            //проверяет, совпадает ли текущая ссылка со страницей настроек
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#setup/other");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("3. Совпадение");

            //раскрыть выпадающий список языков
            driver.findElement(By.xpath("//span[@class='b-selink ns-action']")).click();

            //проверяет, открылся ли выпадающий список языков
            b = driver.findElements
                    (By.xpath("//div[@class='b-mail-dropdown__box' ]")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("4. Совпадение");

            //переключить на русский
            driver.findElement(By.xpath("//a[@data-params='lang=ru']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверить, закрылся ли выпадающий список языков
            b = driver.findElements(By.xpath("//div[@class='b-mail-dropdown__box' ]")).isEmpty()
                    && driver.findElements(By.xpath("//span[text()='Русский']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Удаление без выбора писем для удаления
    @Test
    public void DeleteWithoutSelection(){
        try{
            System.out.println("YA-2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //проверяет кликабельность кнопки удаления
            b = driver.findElement(By.xpath("//div[@title='Удалить (Delete)']")).
                    getAttribute("class").contains("is-disabled");
            /**В Яндекс.Почте кнопки изменения состояния выбранных писем
             * в теге div имеют в своём классе значение "is-disabled"*/
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
            return;
        }
    }

    //Удаление выбранного письма
    @Test
    public void DeleteSelection(){
        try{
            System.out.println("YA-3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //создёт список из всех элементов сообщений
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //проверяет наличие элементов в списке
            Assert.assertFalse(listElements.isEmpty(), "Во входящих сообщениях писем нет\n");

            //генерация случайного числа в пределах размера списка
            i = new Random().nextInt(listElements.size() - 1);

            //получить из списка элемент со случайным номером
            we = listElements.get(i);

            //выделить случайное письмо
            we.click();

            //получить чекбокс выделенного письма
            we = driver.findElements(By.xpath("" +
                    "//input[@class='_nb-checkbox-input']")).get(i);
            /*элемент данного типа не кликабелен
            * */

            //проверяет, отмечет ли чекбокс
            Assert.assertTrue(we.isSelected(), "Ошибка\n");
            System.out.println("2. Совпадение");

            i = listElements.size();

            //удаление выделенного письма
            driver.findElement(By.xpath("//span[@class='mail-Toolbar-Item-Text " +
                    "js-toolbar-item-title js-toolbar-item-title-delete']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, что писем стало меньше
            i2 = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']")).size() + 1;
            Assert.assertEquals(i2, i, "Ошибка\n");
                System.out.println("3. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Выделение писем
    @Test
    public void Selection(){
        try{
            System.out.println("YA-4:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //создёт список из всех элементов сообщений
            List<WebElement> listElements = driver.findElements(By.xpath("" +
                    "//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));

            //проверка на наличие подходящего числа элементов в списке
            Assert.assertFalse(listElements.size() < 2,
                    "Во входящих сообщениях только одно письмо или их нет\n");

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
            Assert.assertTrue(we.isSelected(), "Ошибка\n");

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
            Assert.assertTrue(we.isSelected(), "Ошибка\n");
            System.out.println("2. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма
    @Test
    public void SendingLetter(){//WebDriver driver, WebDriverWait wait static
        try{
            System.out.println("YA-5:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            s = "anzh.mats@yandex.ru";

            //ввести email отправителя (себя)
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, правильно ли введён отправитель
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Ошибка\n");
            /** Т.к. в сроке темы текст записывается не во внутрь элемента,
             * getText() не может его считать*/
            System.out.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_direction_bottom-left popup2_visible_yes popup2_target_position " +
                            "popup2_motionless ComposeDoneScreen']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Письмо отправлено']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).isEmpty();
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания адреса получателя и темы
    @Test
    public void LetterWithoutAddressSubject(){
        try{
            System.out.println("YA-6.1:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Письмо не отправлено']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Пожалуйста, укажите адрес получателя']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("3. Совпадение\n");

        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания адреса получателя
    @Test
    public void LetterWithoutAddress(){
        try{
            System.out.println("YA-6.2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Письмо не отправлено']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Пожалуйста, укажите адрес получателя']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("4. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма без указания темы
    @Test
    public void LetterWithoutSubject(){
        try{
            System.out.println("YA-6.3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //ввести email отправителя (себя)
            s = "anzh.mats@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, правильно ли введён отправитель
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b =     driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Внимание!']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Ваше письмо пока не отправлено']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            /**На данном этапе будет выводиться ошибка,
             * т.к. Яндекс.Почта предоставляет возможность
             * отправлять письма без темы*/
            System.out.println("4. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (без @)
    @Test
    public void SendingLetterInvalidAddress1(){
        try{
            System.out.println("YA-6.4.1:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //ввести email отправителя (себя) без @
            s = "anzh.matsyandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Ошибка\n");
            System.out.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[text()='Проверьте получателя']")).size() > 0
                &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Похоже, что-то не так с адресом: anzh.matsyandex.ru.']")).size() > 0
                &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (содержащий @ @)
    @Test
    public void SendingLetterInvalidAddress2(){
        try{
            System.out.println("YA-6.4.2:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //ввести email отправителя (себя) с @@
            s = "anzh.mats@@yandex.ru";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Ошибка\n");
            System.out.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='Проверьте получателя']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Похоже, что-то не так с адресом: anzh.mats@@yandex.ru.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }

    //Отправка письма с некорректным адресом получателя (без домена)
    @Test
    public void SendingLetterInvalidAddress3(){
        try{
            System.out.println("YA-6.4.3:");
            //перейти на главную страницу Яндекс.Почта
            driver.findElement(By.xpath("//div[@class='PSHeaderIcon PSHeaderIcon_Mail']")).click();

            Thread.sleep(2000);//ожидает 2 секунды (до начала загрузки страницы)

            //проверяет, совпадает ли ссылка с главной страницей Яндекс.Почта
            b = driver.getCurrentUrl().contains("https://mail.yandex.ru/") &&
                    driver.getCurrentUrl().contains("uid=1625522601#tabs/relevant");
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("1. Совпадение");

            //открыть форму письма
            driver.findElement(By.xpath("//a[@href='#compose']")).click();

            //проверяет, открылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                    "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                    "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                    "ComposePopup_size_large']")).size() > 0;
            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("2. Совпадение");

            //ввести email отправителя (себя) без домена
            s = "anzh.mats@";
            driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    sendKeys(s);

            //проверяет, совпадает ли введёный отправитель с планируемым
            Assert.assertEquals(driver.findElement(By.xpath("//div[@class='composeYabbles']")).
                    getText(), s, "Ошибка\n");
            System.out.println("3. Совпадение");

            //ввести тему письма
            s = "Test Letter";
            driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(s);

            //проверяет, правильно ли введена тема письма
            Assert.assertEquals(driver.findElement(By.xpath("//input[@name='subject']")).
                    getAttribute("value"), s, "Ошибка\n");
            System.out.println("4. Совпадение");

            //отправить письмо
            driver.findElement(By.xpath("//button[@class='Button2 " +
                    "Button2_pin_circle-circle Button2_view_default Button2_size_l']")).click();

            //проверяет, появилось ли предупреждение, правильность текста сообщения и закрылась ли форма письма
            b = driver.findElements(By.xpath("//div[@class='modal__content']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[text()='Проверьте получателя']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//span[" +
                            "text()='Похоже, что-то не так с адресом: anzh.mats@.']")).size() > 0
                    &&
                    driver.findElements(By.xpath("//div[@class='popup2 popup2_view_classic " +
                            "popup2_theme_normal popup2_direction_top-center popup2_nonvisual_yes " +
                            "popup2_visible_yes popup2_target_position popup2_motionless ComposePopup " +
                            "ComposePopup_size_large']")).size() > 0;

            //закрывает предупреждение
            driver.findElement(By.xpath("//button[@class='control button2 " +
                    "button2_view_default button2_tone_default button2_size_xs button2_theme_clear " +
                    "ComposeConfirmPopup-Close']")).click();

            Assert.assertTrue(b, "Ошибка\n");
            System.out.println("5. Совпадение\n");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e + "\n");
        }
    }
}
