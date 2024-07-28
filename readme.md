# TurtleShelterService
## Márton András demo applikációja Java Developer pozícióra a Raiffeisen Banknál (martonandras84@gmail.com)

### Általános kommentek
- Ezt a readme-t magyarul írom. Elfelejtettem megkérdezni, hogy milyen nyelven készülnek Náltok a doksik, de mivel magyarul volt a feladat kiírás, remélem, elfogadható magyarul a doksi is! :)
- Az elkészült projekt minden funkciót tartalmaz, de nem production-ready
  - Írtam unit teszteket, de inkább csak demo jelleggel. Nem fedtem le velük minden osztályt, minden metódust, és minden branchet.
  - Automatizált integration tesztekkel is tovább javítható a kód minősége (@AutoConfigureMockMvc or @WebMvcTest)
  - A code coverage ellenőrzésére lehet használni Jacoco-t, be lehet állítani a minimálisan elvárt coverage-et is
  - Production környezet setup kidolgozása
  - Jelenleg csak a Teknős objektumok kezelésére vannak endpointok az app-ban, a többi objektumra (fajok, élőhey típusok) nincsenek. Ezeket is szükséges lenne később kidolgozni.
  - Security / user authentikáció: valamilyen authentikáció, illetve az endpointok védelme (pl spring security) szükséges
- Adatbázis struktúra
  - Ahogy Virával egyeztettük, külön táblát csináltam a teknős fajoknak és az élőhelyeknek is a könnyebb bővíthetőség kedvéért. De jelenleg az élőhely típusokról csak 1 adatot tárolunk. Ha ez maradna a hosszútávú igény, akkor érdemes lenne megfontolni a két tábla összevonását, hogy a kód átláthatóbb legyen
  - Nem definiáltam külső kulcsokat a táblák között és nem kapcsoltam össze a táblákat a JPA-val sem. Korábbi tapasztalatom volt, hogy az összekapcsolt táblák rekordjaival dolgozva nagy volt a memória-fogyasztása az alkalmazásnak. Könnyebb a memória használatot kontrollálni, ha az összetartozó rekordokat inkább külön-külön kezeljük a JPA-val még akkor is, ha ez valamennyi plusz kódot igényel
- Teknősök súlyának megadásakor a különböző mértékegységek kezelése
  - Saját converter osztályt írtam, ami a következő mértékegységeket kezeli: gramm, kilogramm, uncia (ounce), font (pound). A kezelendő mértékegységek listáját és az esetleges egyéb részleteket a product teammel kellene egyeztetni
  - A tömeg tárolása grammban történik egész számokkal. Tört grammot nem kezel a rendszer. Szerintem nincs is szükség a teknősök súlyát tized-, századgrammban tárolni. De ezt is kellene pontosítani a product teammel

### Implementált endpointok
- GET all turtles
  - URL: ```{host}/turtleshelter/api/turtle```
  - visszaadja az összes nem törölt teknőst
  - a response objektum pageable és tartalmazza a teknősök összes tárolt adatát
- GET one turtle
  - URL: ```{host}/turtleshelter/api/turtle/{turtleId}```
  - visszaadja az átadott id-hoz tartozó teknős összes tárolt adatát
  - 404 NOT FOUND-ot ad, ha az id nem létezik
- GET all turtles by speceis
  - URL: ```{host}/turtleshelter/api/turtle?speciesId={speciesId}```
  - visszaadja az átadott species id-hoz tartozó összes teknős tárolt adatait
  - a response objektum pageable
  - 404 NOT FOUND-ot ad, ha az id nem létezik
- POST one turtle
  - URL: ```{host}/turtleshelter/api/turtle```
  - 404 NOT FOUND-ot ad, ha a species id nem létezik
  - a response objektum a létrehozott turtle objektum, benne az adatbázis mentéskor kapott id-val
  - resiliency: a teknős neve unique - ha kétszer küldjük be ugyanazt az inputot, nem jön létre kétszer az adatbázisban, hanem másodszor is az előzőleg létrehozott rekordot kapjuk vissza. Ezt is tisztázni kell a product teammel, vagy a front-end csapattal
  - request body - minden mező kötelező:
```
  {
    "speciesId": 2,
    "name": "TurtleUI",
    "arrivalDate": "2024-07-25",
    "weight": 10,
    "measurementUnitOfWeight": "kg",
    "age": 120
   }
```
- PUT one turtle
  - URL: ```{host}/turtleshelter/api/turtle/{id}```
  - 404 NOT FOUND-ot ad, ha a turtle id vagy a species id nem létezik
  - a response objektum a módosított turtle objektum, benne a turtle id-val
  - request body - minden mező opcionális (kivéve a ```weight``` és a ```measurementUnitOfWeight``` - ezek csak együtt szerepelhetnek, vagy egyik sem szerepelhet):
```
  {
    "speciesId": 2,
    "name": "TurtleUI",
    "arrivalDate": "2024-07-25",
    "weight": 10,
    "measurementUnitOfWeight": "kg",
    "age": 120
   }
```
- DELETE one turtle
  - URL: ```{host}/turtleshelter/api/turtle/{id}```
  - 404 NOT FOUND-ot ad, ha a turtle id nem létezik
  - 404 NOT FOUND-ot ad, ha a teknős már törölve van
  - a response objektum a módosított turtle objektum, benne a turtle id-val

### Egyéb commentek
#### Exception kezelés
- Custom Exception-öket definiáltam az invalid inputok kezelésére
- @ControllerAdvice implementáációk kezelik az eldobott exception-öket, és állítják be a response type-ot
- Így a kód tisztább és átláthatóbb
- 
#### JavaDoc
Az osztályokhoz és a metódusokhoz JavaDoc comment tartozik.

#### Adatbázis
- In memory h2 adatbázist használtam
- Local futtatáskor
  - URL: ```http://localhost:8080/turtleshelter/h2-console```
  - JDBC URL: ```jdbc:h2:mem:testdb```
  - user: ```sa```
  - password: ```password```
- A későbbi változások kezeléséhez elő van készítve a FlyWay implementáció
- Audit mezőket (creationTimestamp, updateTimestamp and version) adtam minden táblához egy ős entity osztállyal. Későbbi részletesebb audit megoldás kidolgozása szintén a producttal való egyeztetést igényel, vagy a céges audit policy alkalmazását, ha van (pl creator és updater userek tárolása, vagy a rekordok korábbi verziójának teljes tárolása)
- Indexeket hoztam létre a táblákhoz
  - Minden táblának van unique indexe az id-ra, és non unique indexe azokrta a mezőkre, amik alapján lekérdezés fut a táblára