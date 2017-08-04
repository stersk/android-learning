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