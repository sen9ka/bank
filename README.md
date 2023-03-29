Приложение "Кредитный конвейер" с применением технологий и инструментов:
Java, SpringBoot, PostgreSQL, JPA, Swagger, Kafka, JUnit, Lombok

Микросервисы
1) Conveyor: 
- По API приходит LoanApplicationRequestDTO.
- На основании LoanApplicationRequestDTO происходит прескоринг создаётся 4 кредитных предложения LoanOfferDTO на основании всех возможных комбинаций булевских полей isInsuranceEnabled и isSalaryClient (false-false, false-true, true-false, true-true).
Ответ на API - список из 4х LoanOfferDTO от "худшего" к "лучшему" (чем меньше итоговая ставка, тем лучше).
POST: /conveyor/calculation

- По API приходит ScoringDataDTO.
- Происходит скоринг данных, высчитывание ставки(rate), полной стоимости кредита(psk), размер ежемесячного платежа(monthlyPayment), график ежемесячных платежей (List<PaymentScheduleElement>).
Ответ на API - CreditDTO, насыщенный всеми рассчитанными параметрами.

2) Deal 
- По API приходит LoanApplicationRequestDTO
- На основе LoanApplicationRequestDTO создаётся сущность Client и сохраняется в БД.
- Создаётся Application со связью на только что созданный Client и сохраняется в БД.
- Отправляется POST запрос на /conveyor/offers МС conveyor через RestTemplate. Каждому элементу из списка List<LoanOfferDTO> присваивается id созданной заявки (Application)
- Ответ на API - список из 4х LoanOfferDTO от "худшего" к "лучшему".
PUT: /deal/offer

- По API приходит LoanOfferDTO
- Достаётся из БД заявка(Application) по applicationId из LoanOfferDTO.
- В заявке обновляется статус, история статусов(List<ApplicationStatusHistoryDTO>), принятое предложение LoanOfferDTO устанавливается в поле appliedOffer.
- Заявка сохраняется.
PUT: /deal/calculate/{applicationId}

- По API приходит объект FinishRegistrationRequestDTO и параметр applicationId (Long).
- Достаётся из БД заявка(Application) по applicationId.
- ScoringDataDTO насыщается информацией из FinishRegistrationRequestDTO и Client, который хранится в Application
- Отправляется POST запрос к МС КК с телом ScoringDataDTO 

3) Application
- По API приходит LoanApplicationRequestDTO
- На основе LoanApplicationRequestDTO происходит прескоринг.
- Отправляется POST-запрос на /deal/application в МС deal через RestTemplate.
- Ответ на API - список из 4х LoanOfferDTO от "худшего" к "лучшему".
POST: /application/offer

- По API приходит LoanOfferDTO
- Отправляется POST-запрос на /deal/offer в МС deal через RestTemplate.

4) Dossier
В обязанности МС-dossier входит обработка сообщений из Кафки от МС-deal на каждом шаге, который требует отправки письма на почту Клиенту: 
- Формирование письма и документов
- Отправка письма на почту Клиенту

- Добавление API в МС-deal:
POST: /deal/document/{applicationId}/send - запрос на отправку документов
POST: /deal/document/{applicationId}/sign - запрос на подписание документов
POST: /deal/document/{applicationId}/code - подписание документов
Первая отправка письма на почту клиенту должна происходит в самом конце существующей API PUT: /deal/offer

В запросе от МС-deal к МС-dossier используется ДТО EmailMessage

Реализация взаимодействия через Kafka между МС-deal и МС-dossier:

- Kafka и Zookeeper следует поднимаются через docker-compose
- МС-deal выступает в роли издателя (producer), МС-dossier в роли подписчика (consumer)
- В кафке заводится 6 топиков, соответствующие темам, по которым необходимо направить письмо на почту Клиенту:
finish-registration
create-documents
send-documents
send-ses
credit-issued
application-denied

5) Gateway
Реализация микросервисного паттерна API-Gateway для сервисов кредитного конвейера. Суть паттерна заключается в том, что клиент отправляется запросы не в несколько разных МС с бизнес-логикой, а только в один МС (gateway), чтобы он уже перенаправлял запросы в другие МСы. Главная задача этого МС - инкапсулировать сложную логику всей внутренней системы, предоставив клиенту простой и понятный API.


Все микросервисы помещаются в docker-compose контейнер, настраивается их взаимодействие.
