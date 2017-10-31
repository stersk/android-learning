# android-learning
Project for Android learning

_p0261_intentfilter_

Налаштування фільтрів на відкриття інших Activity (відкриття через фільтри без передачі додаткових даних)

_p0271_getintentaction_

При відкритті Activity отримання параметру по якому фільтру (intent filter) відкриття проводиться

_p0281_intentextras_

Передача додаткових данних з одного Activity в другий через об'єкт Intent

_p0291_simpleactivityresult_

Відкриття іншого Activity з возвратом результату після свого закриття (наприклад відкриття форми підбору)

_p0301_activityresult_

Обробка результатів двох різних Activity одним обробником (requestCode, resultCode)

_p0311_simpleintents_

За допомогою інтентів відкриття програм для здійснення операцій (напр. набір номера, показ місця на карті,
відкриття посилання. Передача параметрів відкриття за допомогою Uri (напр. "tel:112334455")

_p0321_simplebrowser_

Простий браузер: на екрані кнопка по нажиманні на яку пробується відкрити сайт. На другому
вікні настроєний інтент-фільтр, який добавляє його в вікно вибору програми для відкриття сайту.
Якраз на ньому і компонент для відображення сайту.

_p0341_simplemysqllite_

Робота з базами данних: ініціалізація БД, добавлення в базу, очистка бази, Читання всієї бази.
Також добавлено читання по айдішці, обновлення по айдішці і видалення по айдішці.

_p0361_sqlitequery_

Робота з базами данних: приклади селекта з умовою, групіовка, селект з умовою по групіровці (having),
сортування і т.д. з допомогою метода quety об'єкта SQLiteDatabase.
Також імітація нажимання кнопки (через визов метода onClick лістенера, якому передається об'єкт кнопки.
Якщо класс має інтерфейс onClick... , то метод onClick виконується напряму (і йому годується все та ж кнопка).

_p0371_sqlinnerjoin_

Консольний приклад як виконувати складні запроси через чистий текстовий запрос (зокрема дані з двох
таблиці лівим з'єднанням).
добавлено аналогічний приклад через об'єктну модель (query), правдо з выдбором.

_p0381_sqlitetransactions_

Консольний приклад використання транзакцій. Також приклад як працює блокіровка при транзакції,
аналогіно про те що в межах об'єкта SQLiteOpenDBHelper може бути не більше 1-го з'єднання (
при повторному полученні не відкривається нове, а віддається існуюче з'єднання.) 

_p0391_sqliteonupdatedb_

Приклад роботи програми при переході з одної версії БД на другу (зміна структури БД) через
OnUpdate SQLiteOpenDBHelper.

_p0401_layoutInflater_

Експеременти з layoutInflater: добавляння елементів з іншого xml-файла: параметри layoutInflate

_p0411_layoutInflaterlist_

Генерація простого списку з допомогою inflater. Ключевим моментом було що ми з допомогою
inflate() генерим рядок, але з останнім параметром в false, заповняем полуений об'єкт, а
потім вже вручну добавляем його в LinearView.

_p0421_simplelist_

Генерація списку з допомогою адаптора по шаблону рядка.

_p0431_listsimplechoice_

Генерація списку з допомогою адаптора по шаблону рядка можливістю відмітити рядки
і отримати відмічені рядки. Цікаві методи по загрузці масиву з ресурсів і створення
адаптора з масиву ресурса.
P.S. під час відладки на домашньому компі під віндою ide паде. Цікаво чи це через
баг в коді модуля, чи через саму ide.

_p0441_listsimpleevents_

Доробка до минулого проекту: приклади вішання на ListView обробників скролла, кліка по пункту, і 
відмітки пункту списка (відмітка правда це не відмічення його галочкою, а активація (підсвітка) його
в списку, напр. мош побачити після кліка мишкою клацаючи на клавіші вверх/вниз)

_p0451_expandablelist_

Приклад виводу в дворівневий список (дерево з двома рівнями).

_p0461_expandablelistevents_

Робота з подіями в дворівневому списку. Корисно також приклад відміни події (1С: СтандарнтаяОбработка = Ложь),
Також виокремлення окремого функціоналу з одного класу в інший для спрощення коду (Клас з конструктором,
туди передається контекст основного класу, а далі він формує і віддає аваптер для ExpandableList)).

_p0481_simpleadapter_

Використання SimpleAdapter для виведення данних в список. Виводити в рядок можна в будь-який шаблон
рядка з будь-якою кылькыстю елементів, але виводити можна тільки текст, помітку або картинку (в елементи
які підтримують відповідний тип. Якщо тип не підходить до елемента, виведення в нього не відбувається).
Також якщо в помітку замість булєво передати текст, то цей текст установиться як помітка до флажка.

_p0491_simpleadaptercustom1_
Схоже до попереднього, але тут ми наслідуем класс SimpleAdapter і дешо модифікуєм ого функціонал (
крім заповнення рядків ми ще модифікуєм поля рядків, типу колір, фон, тобто реалізуем умовне оформлення).

_p0501_simpleadaptercustom2_

Робим свій BinderAdapter для того щоб реалізувати свій алгоритм роботи SimpleAdapter, щоб навчити
його працювати з ProgressBar.

_p0511_simpleadapterdata_

Тут добавляння і видалення рядків з списку через адаптер (смисл: редагуєм структуру данних, якою 
ініціалізували адаптер, а потім повідомляем його про зміну данних). Також цікава штука як з пункту 
вибраного контекстсного меню витягнути додаткові дані (через такий адаптер як AdapterContextMenuInfo,
якому годуєм витягнуту з MenuItem MenuInfo)

_p0531_simpecursortreeadapter_

В даному випадку організація виводу дерева з допомогою SimpleCursorTreeAdapter, який використовує дані 
з БД. Особливість в тому що при роботі тре наслідувати класс SimpleCursorTreeAdapter з реалізацією 
метода для отримання курсора дочірнього елемента.

_p0541_customadapter_

Приклад як можна зробити свій адаптер (створенням класса через наслідування BaseAdapter і реалізацію
його авбстрактних методів).
Додатково цікаво передавання даних через Tag View-a. (методи setTag і getTag), при цьому можна передати
як одне, так і кілька значень.
Також цікаве отримання Inflater через: getSystemService(Context.LAYOUT_INFLATER_SERVICE) в контексті
основного додатка ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE))

_p0551_headerfooter_

Приклад добавляння шапки і підвала до списку. Особливість: добавити шапку чи підвал можна тільки до 
приєднання адаптора. При цьому адаптор "оборачується" в адаптор з шапкою. Результуючий адаптор містить
дані обгорнутого адаптора з шапками (вірніше шапки з даними обгорнутого адаптора). Що цікаво, данних
підвалу нема, і з результуючого адаптора можна або отримати обгорнутий адаптор, або працювати
з даними результуючого дадптора (які без футерів-підвалів) або видаляти футери-шапки).
На рахунок підвалів це поки шо так я зрозумів.

_p0561_spinner_

Spinner це фактично дропдавн. Одне не до кінця зрозуміло: OnItemSelectedListener реалізує два методи:
при виборі пункта, і коли нічого не вибрано. Не зрозуміло якраз коли викликається другий, о при зникненні
поля виору (напр. при кліку поза ним) він не викликається. Для ЧОго він тоді для спіннера. Можливо
що даний клас ороника використовується не тільки для спіннера, і тут цей другий метод просто ні до чого.

_p0571_gridview_

Використання gridview (типу як список, але не по рядках, а по комірках таблиці: (типу як зменшені
види фоток в файловому менеджері)). Якраз і спроба показати як передогляд фоток реалізувати.
Вилізла цікава штука: якщо загнати віеликі фотки то виникає помилка що додатку нехватає пам'яті і воно 
вилітає. Поки що не знаю з чим це зв'язано. Також приклади як розраховувати кільківсь колонок в гріді (
чи вказувати жорстко, чи вказати ширину колонки і автокількість), а також як розприділити лишній
простір. З чим стикнувся: в методах к-сті колонок і установки способу розприділення є константи
класа GridView. При їх заповненні в параметрах вони всі показані скопом (разом), тобто якщо при встановленні
кількості може бути тільки цифра або константа авторозприділення, то при вводі якавтодоповнення 
з ними відображаються і константи використання вільного простору, указання яких не викликає помилок, 
але дає нам не зовсім адекватний результат.

_p0581_timepickerdialog_

Вивід діалогу з вибором часу.
Через ShowDialog/OnDialogCreate, на даний момент вони показані як застарілі (deprecated). Тут і далі
вони використовуться.

_p0591_datepickerdialog_

Аналогічно до попереднього, тільки осоливість: місяць вказується через індекс (січень як 0 а грудень як 11)

_p0601_simplealertdialog_

Діалог з 3-ма кнопками (максимум) типу так/ні/відміна. Для генерації діалогу використано AlertDialog.Builder
Не знаю чому, але кнопки вистроїлися зліва направо (навпаки). Пробував, порядок виклику методів установки
кнопок не впливає на це.

_p0611_alertdialogprepare_

В уроках писало що onCreateDialog  виконуэться тыльки раз при першому выдкритті діалога. А далі 
перед кожним показом виконується тільки onPrepareDialog (де можна з діалогом щось робити, окрім
керуванням виидиміст його елементів, яке конфігурується при створенні). В мене в реалі onCreateDialog
виконалось при старті програми.

_p0621_alertdialogitems_ 

В даном випадку приклад як можна виводити список в тіло діалогу (по пунктах). Зроблено трьома прикладами: 
через адаптер, через курсор, або прямою передаче списка пунктів (ListArray).

_p0631_alertdialogitemsingle_

Реалізація діалогу з списком вибору (тільки одного пункта). Виводиться дані в список через курсор, але
можна це робити так як і в попередньому прикладі, через адаптер або список.

_p0641_alertdialogitemsmulti_
Реалізація діалогу з списком вибору, Але вже з можливістю вибор кількох пунктів зі списку.

_p0651_alertdialogcustom_
Приклад як можна для діалога призначати свій View і відповідно опервати ним. В даному випадку редагування
своїх же рядків в AlertDialog

_p0661_alertdialogoprations_

Робота з подіями діалогу, також і управління ним з допомогою його методів.
Також можна ним управляти з Activity. При цьому при showDialog сам діалог по ідентифікатору зв'язуєтся
з Activity і ми можем з Activity взаємодіяти зі зв'язаним діалогом (показувати і ховати його, або відв'язати
його від Activity. Проте я думаю зараз це неактуально через те що ільшість цих методів на даний момент застарілі.

_p0671_processdialog_
Показ діалога з прогресом виконання: два вида, горизонтальна (також і з другорядним прогрессом), абол кругова
безкінечна. В даному прикладі відкривається не з-під Activity, а просто конструктором. Такшо ніц не мішає
робити так. Цей діалог також позначений застарілим.
Класний приклад як використовувати Handler.

_p0681_parcel_

Приклади роботи з об'єктом parcel. По своїй суті це контейнер, куди можна записувати набір значень різних
типів. При цьому в середині об'єкта вони тип перетворюються в байти (к-сть байт залежить від типу значення),
і приклеюються один до одного. При цьому зсовується курсор (це типу виборка байт, при запису в яку
значення перетворюються в байти і запихаються туда)). По ній можна позиціонуватися побайтно і вибирати 
який тре тип. Відповідно якщо ми будем знати на якій позиції починається який тип, ми при читанні можем
зпозиціонуватись і прочитати його. Ящо не вгадаємо позицію поатку значення або його тип, то на виході оримається
каша. Також ми можемо отримати байтову послідовність контейнера (метод marshall()).
В довідці наголошують, що його використовувати як серілізатор немош, а його розробили для передачі
через високошвидкісні канали IPC.

_p0691_parcelable_

Переробка користувацького класа для інтерфейса Parcelable: коли нам з ынтента треба передати не простий тип,
а об'єкт, то шоб його дати в putExtra(), треба щоб в ньому був реалізований інтерфейс Parcelable. 
Я так зрозумів, що це щось наподобі Serializable (і через нього це тоже можна зробити), але він "лугший", 
і для андроїда рекомендують використовувати саме його. При цьому в класі, який виконує інтерфейс, ми якраз
в двої місцях описуєм, як об'єкт класа упаковувати в Parcel, і як з нього нам конструювати об'єкт на виході.

Висновок: тре тянути і згадвати JAVA, а то я код дуже поверхнево зрозумів.

_p0701_saveinstancestate_

При, наприклад, повороты орієнтації екрану, Activity знищується, а замысть нього тим самим кодом створюється 
інший екземпляр цього Activity. При цьому весь код ініціалізується заново. Проте в SDK зроблено так,
що якщо View-елементи мали присвоєні id, то вони відновлюють свый стан (напр. поле вводу зберыгаэ введений
текст.). На інші випадки пропонується використовувати події onSaveInstanceState() і onRestoreInstanceState(),
в якому і потрібно прописати код зереження/відновлення стану. 
Якщо потрібно зберегти ссылку на об'єкт в пам'яті, то щоб при дестрої/створенні Activity його не підчистили,
можна користуватись такими речами як onRetainNonConfigurationInstance/getLastNonConfigurationInstance.

_p0711_preferencessimple_

В попередньому уроці (десь 30-ті), ми зберігали прості дані по ключу в хранилище SharedPreferences (
воно там зберыгаэться як в бд, тыльки по принципу ключ/значення). Так ось можна створити ресурс з тегом
в ньому типу <PreferenceScreen>, де описати вигляд єлементів цих настроєк, а потім в окремому класі
(class PreferencesActivity extends PreferenceActivity) можна його догрузити з ресурсів. При  цьому при
відкритті цього Activity автоматом елементи на ньому зв'яжуться по ключах з ресурсів з значеннями із
SharedPreferences. Без коду при їх реидагуванні вони відразу зберігаються в SharedPreferences.
Так з мінімум коду можна створити строінку настроєк аплікухи.
_P.S. Правда метод загрузки даних з файла ресурсу на даний момент позначений застарілим._

_p0721_preferencessimple2_

Приклад як просто маніпулюючи з тегами в файлі ресурсів з PreferenceScreen можна організувати діалог
вибору зі списку (парами масивів, де в першому зберігаються значення пунктів, в другому відповідні їм
користувацькі представлення). Такж маніпуляціями можна організувати ієрархію вікон настройок, і групувати
пункти настройок по категоріям.
НЕ ЗАБУВАТИ доавляти новий актівіті PreferenceScreen в маніфест, ато забуваю і не розумію що твориться.

_p0731_preferencesenable_

Приклад як ще в тегах ресурса налаштувань можна робити активність одних пунктів від стан чекбоксів 
 інших (активність по умові). При цьому треба пам'ятати що це не діє на категорії (активність категорій,
 а вірніше їх вмісту, тре накодити руцями в коді, а не просто сконфігурувати в ресурсі). Також можна
 ресурсом керувати активеністю переходу на вложені екрани. Аналогічно можна вказати пояснення по
 включаючих активність чекбоксів окремо для включеного і виключеного станів (summaryOn i summaryOff).
 (_Відмітка що використаний в коді findPreference також застарілий_)
 
 _p0741_preferencescode_
 
 Той самий приклад, але тільки всі елементи актівіті настроєк кнструюються кодом (а не грузяться з xml),
 правда метод встановлення PreferenceScreen також вже застарілий.
 Є один момент: встановлювати Dependency для налаштувань (тобто від якої настройки залежить поточна),
 бажано в кінці (ну як мінімум не раніше від того як до екрану будуть приєднані як поточна настройка, так
 і та від якої все залежить, бо інакше виникала помилка що залежної настройки не знайдено).
 
 _p0751_files_
 
 Робота з читанням/записом файлів. Все як в звичайній JAVA. Особливість тільки в кількох методах по
 отриманню стандартних шляхів для Android (до сд-шки, і т.д.). Також прописати дозвіл до доступу до
 microSD на запис, інакше будуть сипатись помилки, при чому типу шо "Файла не існує". А ті методи які
 записують і вертають результат (типу file.createFile()) вертає просто ложь, шо просто ніц не створено.
 
 _p0761_tab_
 
 Панель з табами. По ходув коментах почитав що то вже застаріле, і гугл ренкомендує робити то по іншому.
 Воно по ходу вже і до кінця працює: іконку заставити показуватись мені не получилось: одні пишуть що на деяких 
 старих темах вонео працюе (Black), хоча я та і не зміг заставити під такою темою запахать, а інші пишуть
 що іконки відображаються в такому методі тільки на старих андроїдах (до 3-ї версії).
 
 UPD Тему получилось поміняти на Black (прописав не в маніфесті, а в тезі основного LinearLayout-а 
 головної сторінки), картинка появилась. Правда тупо виглядає, мабуть дійсно це все вже застаріло.
 
 _p0771_tabintent_
 
 Якщо головне Activity наслідувати від (TabActivity), то TabHost можна отримати через метод getTabHost,
 при цьому зразу ініціалізується самі таби, а далі просто встановлюєм інші Activity в контенти табів.
 При цьому в них треба використовувати Activity (бо якщо в них вид AppCompatActivity, то під табами
 відобразиться шапка, що неправильно виглядає, і забрати її якось по іншому не мош). Тож видно що TabActivity
 застаріла, і адрекватно відображається зі старими класами Activity, а зі новими не хоче. 
 
 _p0781_tabcontentfactory_
 
 В контент таба можна додавати окрім іншого Activity, айдышок елементів, які мають стати контентом, 
 можна передати TabHost.TabContentFactory. При цьому треба доробити метод OnCreate, який би по по вхідному
 тегу рулив яку вкладку йому треба вернути (а її можна із View нафігачити чи кодом, чи через 
 LayoutInflater). А от тегами э теги, якы ми кидали в конструктори вкладок TabSpec (він тегом і 
 буде дьоргати TabContentFactory). Жаль тільки що TabActivity застаріла.
 
_p0891_xmlpullparser_

Парсер, який поступово проходить те за тегом (виборка), дерево обходить по вітці. При цьому кожного разу
курсор приймає тип початок тегу, текст, кінець тегу (на початку початок документу, в кінці кінець документу)ю
Для тега є така штука як колекція атрибутів, глибина (в типі Початок тегу), вміст тегу (текстовий).
Конструктов: через getResources(...).getXml, якщо тре прочитати з ресурсів, або через клас-фабрику, якщо
з текстового файлу.

_p0801_handler_

По ходу хендлери - це об'єкти, які при запуску дозволяють приймати повідомлення (з шини) і обробляти їх
(вішати обробники подій на них). При цьому в шину можна кидати будь-яку кількість мессажів, при чому хитрожопих (які 
спрацьомують, тобто оробляються в хендлері, наприклад, в певний час).
В даному прикладі хендлер використовується для взіємодій процесу запущеного в іншому потоці з основним потоком.
Зроблено це тому, що якщо напр. загрузку файлів запустити в основному потоці, він буде стопатись (завмирати на час скачування),
а так створиться інший потік, який буде після загрузки кожного з файлів кидати мессадж, а він в основному
потоці буде обролятися хендлером). Також причиною того що використовується хендлер є те, що створювані 
нові потоки не мають доступу до елементів інтерфейсу, тому не можна напряму з другорядного потоку
міняти вигляд форм.

_p0811_handlersimplemessage_

Інший приклад використання Handler для організації простої взаємодії між потоками.

_p0821_handleradvmessage)_

Передача складніших данніих через Message (крім that можна передати два параметра типу int і один Object).
На відміну від sendEmptyMessage (який меседж створює і зразу надсилає), треба спочатку створити меседж,
а потім самому його надіслати. При цьому рекомендується створювати його не конструктором, а методом
obtainMessage самого хендлера (що нібито є продуктивнішим).

_p0831_handlermessagemanage_

Приклад як можна закинути месседж в обробку з затримкою: одним методом по часу від закдування, по іншому 
від часу з моменту запуску аплікухи (якщо цей час вже пройшов, то все обробиться зразу, без затримки).
Також з черги по ыдентифікатору that можна видаляти невиконані месседжі з черги (з одинаковим that месседжі
видалються одним запросом). Також можна видаляти і по комбінації that i obj.
Також сам обробник можна конструктити в виды об'єкта і передавати його на вхід конструктора Handler

_p0841_handlerrunnable_

З допомогою методів post (напр. post, postDelayer...) мт можем кидати в чергу до запуску об'єкти з 
інтерфейсом Runnable, які при настанні обробки в черзі і будуть запущені. Прикольна можливість.

_p0851_runnableuithread_

Тут приклад як можна запускати о ообробку Runnable без явного використання handle. Ці методи поставляються
самим андроїдом. Два зразу кидають в чергу, один в чергу з затримкою на спрацювання.

_p0861_asynctask_

Є також такий клас як AsyncTask. Він є обгорткою над handle-ом. По факту программер наслідуючи його
реалізує (обов'язково) метод який і робить всю роботу в паралельному потоці, а також при необхідності
інші методи (які викликаються як події об'єкту класу, напр. перед стартом, після виконання). При цьому 
об'єкти класу можна стартанути тільки з головного Гуі-потоку і тільки раз.

_p0871_asynctaskparams_

Розширення попереднього прикладу: при оприділенні субкласу (AsyncTask) ми в кутових дужках вказали 
три войда (типу пусті значення). Так ось, в першому вказується тип вхідних параметрів, в другому тип 
проміжних (прогресса) параметрів, в треьому - результатів. Покищо треті не використовували. Ще момент, якщо
вказати тип типу напр. "String ...", це типу массив значень стрінгів. Далі методом execute ми можемо
в якості параметрів вказати будь-яку кількість параметрів типу стрінг, які передадутся в doInBackground.
Далі в doInBackground ми можемо викликати метод postProgress аналогічно до execute, який викличе виконня
методу класу onProgressUpdate, але він вже виконається в UI-потоці з відповідними переданими параметрами.
Так ми запустим другий потік і отримаємо прогрес його виконання в головний потік.

_p0881_asynctaskresult_

Третім типом параметра при написанні еласа вказуєм тип результату. Саметакого типу і повинен вертати наш 
реалізований в класі метод doInBackground. При цьому це значеннями отримаємо в "події" onPostExecute.
Також ми його можем отримати методом get нашого класу в головному потоці, от тілки якщо  другорядні
потоки ще не викликали цей метод блокує виконання головного потоку до виконання другорядних і вертає результат.
Можна визвати цей метод з параметром таймауту. Тоді він блокуватиме не довше таймауту і якщо процес не 
завершиться до таймауту то викличе Виключення (але самі запущені AtyncTask продовжат виконуватись).

_p0891_asynctaskcancel_

Це приклад переривання асинхронних подій. В головному потоці визиваєм метод cancel (з параметром типу
булєво, чи потрібно системі перервати потік, чи чекати його виконання). При цьому рекомендується по 
пририванні потоку не надіятись на систему, а в виконваном дргорядному потоці періодично викликати 
метод "isCanceled", який якщо верне признак чи необхідно нам самим перевати потік, що кодом і робиться.
При цьому (перериванні) в головному потоці спрацює "подія" onCancelled, а onPostExecute ніколи не спрацює.
Якщо потік завершив свою робот ще до переривання, або його немож з якихось причин перервати, то визваний
метод cancel в головному потоці верне false, а при спышному перериванні true.

_p0901_asynctaskstatus_

Також можнаметодом getStatus взнати статус асинхронної задачіЖ може бти запланованим/в процесі/завершеним.
Проте э нюанси: пишуть якщо скасувати задачу ще до її старту, то статус буде все-одно запланованим.
По моїм експерементам при будь-якому скасуванні задача переходить в статус виконаної. Проте що там, що там
відрізнити виконану чи заплановану (кому шо і в яких випадках потрібно) можна методом isCancled (розглядався
в попередньому прикладі).
 
_p0911_asynctaskrotate_ 

Тут вже поэднання специфіки андроїда і асинхронних задач. В попередніх прикладах задачі ми зроили внтрішнім
класом, тому при повороті екрану в нас перествориться актівіті, а так як задача в нас з ним також
перествориться, то перествориться і запуститься друга аналогічна асинхронна задача (причому перша, 
хоч актівіті і пропав, в пам'яті лишиться і продовжить свою роботу). В даному прикладі проують суміщенням
70-го урока пр повороті перез знищеннями одногоактівіті зберегти асинхронну задачу, а при створенні 
нового знову створити її. Правда цього мало, так як асинхронна задача (об'єкт класу) зв'язано з першим
актівіті (так як це вложений клас), тому тт вирішили при реалізації класу асинхронної задачі зробити
його статичним, і кодом в класі вручну реалізувати зв'язок йього статичного коласа задачі з конкретним
актівіті.
Правда щоб цього не роити мош просто реалізувати клас фонової задачі не внутрішнім класом, а "збоку"
від актівіті.
P.S. В даному прикладі для відтворення задачі використовується реалізація методу 
onRetainNonConfigurationInstance, який в нас непрацював через те що в якості актівіті використовувався
android.support.v7.app.AppCompatActivity. Том я поки переправив його на android.app.Activity. Варто
для практики розібратись з цим і зробити через android.support.v7.app.AppCompatActivity (як в 70-му прикладі)

_p0921_ServiceSimple_

Я так зрозумів що сервіс, це як Актівіті, тільки без GUI, він існує на протязі життя додатку (а не 
тільки на час видимості інтерфейсу). Також інтересно що в ньому кож також рекомендують виносити в інші
потоки (а не виконувати прямо в його потоці). Такоє має події onCreate, onStartCommand, onDestroy. При 
цьому перші два виконуються тільки раз на час життя процесу, а onStartCommand може виконуватись кілька раз 
(напр в нашому прикладі якщо нажати ще раз старт ще до закінчення минулого сервісу, то onCreate і onDestroy
не удуть ще раз виконуватись, а виконається тільки onStartCommand і нагрузка цього методу). А от якщо 
нажати старт після дестроя, то все почнеться по колу.

_p0931_servicestop_

По ходу так і є, після створення сервіс приймає задачі, кожному старту задачі присвоюєтьтся свій ідентифікатор.
Коли у всіх буде виконано метод stopSelf (повідомлення про те що задача в сервісі виконана), то сервіс
буде ліквідовано. Якщо виконано stopSelfResult, він верне ще і резльтат, чи загалом сервіс зупинено.
Що в прикладі цікаво, це те що використовується такий клас як Executor, який створюэ чергу виконання
об'єктів runnable (вірніше організовує фіксований пул потоків, які виконують задачі поступово). Також
и створили об'єкт, який по факту доступний на протязі існування сервіса (від onCreate до onDestroy для
всых запущених задач).

_p0941_servicekillclient_ - _p0942_servicekillservice_
 
Демонстрація того як сервіси обробляють своє переривання. При своїй реалізації onStartCommand можна там
вернути одну з трьх констант, яка вплине на поведінку сервіса після переривання (як правило системою
при нестачі ресурсів). Якщо вернемо константу NOT_STICKY (якось так), то при наступному зверненні до
сервіса він собі стартане як нічого не бувало. Якщо вернем STICKY, то він при першії же нагоді буде перезапущений,
при цьому в флагах, переданих в onStartCommand буде видно що він запущений рестартом, але тіко раз, спочатку,
і з інтентом в виді null (тільки раз і з одною ід, незалежно в якому стані його перервали). Також є 
і режим RESTART_INTENT_REDELIVERY. В цьому випадку система спробуэ перезапустити сервіс, але на вхід
знову подасть тої же інтент і вному ж стані, який був поданий стопнутому процесу. При цьому якщо його
перервали з кількома незакінченими стартами, то відповідно всі вони (з відповідними ід і інтентами) буде
рестартовано.

_p0951_servicebackpendingintent_

Приклад як можна вернути результат з сервіса в актівіті, який його запустив, з допомогою pendingIntent.
Коли в сервісі виконається метод pendingIntent.send, в актівіті виконується метод onActivityResult з
цими даними. В даному випадку я для трьох сервісів використав один pendingIntent. В результаті в мене
все підвисло на час виконання всіх сервісів, а після їх всіх виконання відразу вернулись 3 результата.
Якщо б я їх створив тоже 3, то вони б повертались поступово для кожного сервіса окремо.

_p0961_servicebackpendingintent_

То саме тыльки через BroadcastReceiver, тіко ця штука працює навпаки: в актівіті створюєм його і реєструєм
його на прослуовування певного IntentFilter, а далі там, звідки треа послати мессадж, по URL створюєм
Intent, в нього пхаєм необідні для передавання дані, і методом sendBroadcast шлем його. Там де ми 
екстендили клас BroadcastReceiver (в актівіті) виконається реалізований нами метод onReceive. По ходу цей
метод правельніший за попередній.

Мля, та сама тема з виконанням потоків що і в попередньому прикладі.
По опису урока такого не має бути, тре буде перевірити.
!Всьо добре! В даному прикладі для виконання процесів використали Executor, якому треба просто передати
процес (я я просто його ще і стартував (після Execute виконав ще і Runnable.run)), а в попередньому
приклады все виконалось та, бо ми стартували одночасно три потока з однаковим часом виконання. Тому все

_p0971_servicebackpendingintent_
Приклад як можна підключатись до сервісу (bindService, unbindService). При цьому в запущеному сервісі
спрацюють відоповідні "події". Також після того як сервіс підключено в об'єкті ServiceConnection 
з допомогою якого робився конект з Актівіті (з ініціатора) виконається ще подія після конекту
onServiceConnected. А от disconnected виконаэться тільки в разі коли з'єднання розірвано не з
ініціативи його ініціатора (напр. примусово, при цьому система через деякий час знову запустить його 
 автоматом), а в разі унбіндінга - воно не виконається. 
Також при конекті вказується флаг режиму конекту, і якщо вказати BIND_AUTO_CREATE, то в разі відсутності
 запущеного сервісу він автоматично запуститься, а після унбіндінга він спробує відключитись.
 Якщо ж сервіс був запущений, то після унбіндінга він і залишеться запущений. Якщо було кілька 
 біндінгів до сервіса, то він лишиться живим поки вони всі не відключаться. 
В сервісі в події onUnbind треба вернути булєво. По вмовчуванню вертається Ложь. При цьому якщо
в межах існування сервіса пройде другий раз бандінг, то все буде як звичайно, а якщо вернути
Истину, то при слідуючому біндінгу після того, від якого від'єднуємся, при старті замість 
onBind виконається onRebind.

_p0981_servicelocalbinding_
В даному випадку приклад як можна заставити взаэмодіяти актівіті з сервісом (актівіті може виконувати
експортні методи сервіса, та інше). В даному випадку все виконається в одному спільному потоці (актівіті
біндиться до сервіса, а той віддає актівіті розширений об'єкт біндера, в якому є метод по віддачі
самого актвіті. За допомогою цого методами і отримуєм доступ до сервіса, тобто ссилку на нього, в
самому актівіті). Момент: для приклада ми в сервісі використали таймер, який первідично виконує
таймертаск. Так от, для зміни періоду циклу я перестворював ці об'єкти, а по вакту достатньо було
перестворити тільки таймертаск, а сам таймер можна було не чіпати. При цьому зупиняти заплановану 
задачу треба методом Cancel самого таймертаска (а я таким методом таймера робив).