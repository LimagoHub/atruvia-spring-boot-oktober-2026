# WebAppNeu – Spring Boot 4.1.1

Kopie von `WebApp` mit aktualisierter Basis. Build und alle 7 Tests laufen (`mvn clean verify`), Start, Swagger UI (`/swagger-ui/index.html`), `/v3/api-docs` und XML-Ausgabe wurden geprüft. Getestet mit Java 25 / Maven 3.9.9 (Zielversion `java.version` = 17).

## Änderungen gegenüber `WebApp`

| Stelle | Alt | Neu | Grund |
|---|---|---|---|
| Parent `spring-boot-starter-parent` | 4.0.0 | **4.1.1** | Ziel-Version |
| `springdoc-openapi-starter-webmvc-ui` | 3.1.0 | **3.1.1** | 3.1.0 + Boot 4.1 nicht geprüft; 3.1.1 ist die aktuelle 3.x-Version und läuft mit Boot 4.1.1 |
| `jackson-dataformat-xml` | `com.fasterxml.jackson.dataformat` | **`tools.jackson.dataformat`** | Boot 4 nutzt Jackson 3. Version kommt aus dem BOM |
| neu: `spring-boot-starter-restclient-test` (test) | – | ergänzt | **Ohne diese Dependency scheitern alle `@SpringBootTest` mit `TestRestTemplate`**: `NoClassDefFoundError: org.springframework.boot.restclient.RestTemplateBuilder`. In 4.0.0 kam die Klasse noch über `starter-webmvc-test` mit, in 4.1 nicht mehr |
| Projektname | WebApp | WebAppNeu | – |

Unverändert: MapStruct 1.6.3 (Property `mapstruct.version`), `lombok-mapstruct-binding` 0.2.0, `annotationProcessorPaths` (siehe `../WebApp/DEPENDENCIES.md`).

## Swagger / springdoc – worauf achten
- springdoc-Versionen sind an die Boot-Linie gekoppelt: springdoc 3.x für Boot 4.x. Nie 2.x verwenden (das ist Boot 3).
- Bei einem Boot-Update springdoc mit anheben und `/v3/api-docs` sowie `/swagger-ui/index.html` kurz aufrufen.
- Die Swagger-Endpunkte sind standardmäßig aktiv (Log-Warnung). Für Produktion `springdoc.api-docs.enabled=false` und `springdoc.swagger-ui.enabled=false` setzen.

## Auffälligkeiten im Code (nicht geändert)
1. **`PersonenController`**: `getPersonen` ignoriert die Parameter `vorname`/`nachname` (nur `System.out.printf`, Ergebnis ist immer `findeAlle()`). Der Endpunkt liefert nur JSON (`produces`), XML gibt daher 406; nur `GET /{id}` kann XML. `POST`/`PUT` akzeptieren nur JSON.
2. `PersonenController`: ungenutzter Import `ch.qos.logback.core.encoder.EchoEncoder` und auskommentierter Swagger-Block. Passender Ort für die springdoc-Annotationen im Seminar.
3. **`spring-boot-starter-jersey`** (+ `-jersey-test`) ist eingebunden, im Code gibt es aber keinerlei JAX-RS/Jersey-Nutzung. Zwei Web-Stacks (Jersey und MVC) sind unnötig und eine Fehlerquelle; kann entfernt werden.
4. `Demo`: ungenutzte Imports, `@RequiredArgsConstructor` importiert aber nicht verwendet, `System.out.println` im Konstruktor.
5. `application-test.properties`: `hibernate.dialect` explizit gesetzt, Hibernate warnt (`HHH90000025`), Zeile kann entfallen. Die Datei hat außerdem keinen Zeilenumbruch am Ende, ebenso `application-production.properties`.
6. `application-production.properties`: H2-Datei unter `/tmp/db/fi` (unter Windows relativ zum aktuellen Laufwerk) und `ddl-auto=update`, `show-sql=true`. Für ein Seminar ok, für Produktion nicht. Der Profilname `production` ist zudem in `application.properties` fest als Default aktiv.
7. `spring.jpa.open-in-view` ist implizit aktiv (Warnung beim Start). Explizit auf `false` setzen.
8. `MyAspect` loggt auf WARN-Level (`######## ...`), erzeugt bei jedem Aufruf Rauschen.
