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