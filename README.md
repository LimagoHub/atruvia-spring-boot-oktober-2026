# atruvia-spring-boot-oktober-2026

Spring Boot Seminar (Atruvia, Oktober 2026)

Dieses Dokument gibt den groben Ablauf des Seminars wieder. Jedes Kapitel gehört zu einem
Beispielprojekt in diesem Repository; die Projekte bauen aufeinander auf.

## Überblick

| Kap. | Thema                                   | Projekt              |
|------|-----------------------------------------|----------------------|
| 1    | Dependency Injection (ohne Spring)      | `CalculatorProject`  |
| 2    | Spring-Grundlagen (Konsolenanwendung)   | `SpringConsoleApp`   |
| 3    | REST-Endpoints (ohne Service Layer)     | `WebApp`             |
| 4    | Persistenz mit Spring Data JPA          | `WebApp`             |
| 5    | Service Layer und Transaktionen         | `WebApp`             |
| 6    | Konfiguration                           | `WebApp`             |
| 7    | Aspekte (AOP)                           | `WebApp`             |
| 8    | Events                                  | `WebApp`             |
| 9    | Tests der REST-Endpoints gegen den laufenden Container | `WebApp` |

---

## Kapitel 1: Dependency Injection am Calculator-Beispiel (`CalculatorProject`)

- Problem der festen Kopplung (`new` in der Klasse)
- Gegen Interfaces programmieren, Abhängigkeiten von außen übergeben
- Dekorierer und dynamischer Proxy (Ausblick auf AOP)
- Manuelle Verdrahtung in `main()` ("Poor Man's DI")
- Überleitung: Was nimmt uns ein Container ab? (Inversion of Control)

## Kapitel 2: Spring-Konsolenanwendung (`SpringConsoleApp`)

Einführung der Basis-Annotationen anhand derselben Calculator-Klassen:

- `@Component` und die Stereotypen `@Service`, `@Repository`, `@Controller`
- Constructor Injection (empfohlen), Setter-/Field-Injection, `@Autowired`, Lombok `@RequiredArgsConstructor`
- `@Value` für Werte und Strings
- Lebenszyklus: `@PostConstruct`, `@PreDestroy`
- `@Scope` (singleton, prototype), `@Lazy`
- Konflikte auflösen: `@Primary`, `@Qualifier`, `@Profile`
- Component-Scan und automatische Verdrahtung statt manuellem `main()`

## Kapitel 3: Web-Anwendung und REST-Endpoints (`WebApp`)

- REST-Grundprinzipien, Ressourcen-Naming
- `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, ...
- `@PathVariable`, `@RequestParam`, `@RequestBody`, `ResponseEntity`
- Zunächst ohne Service Layer: Der Controller arbeitet direkt mit den Daten
- Web-Scopes (`@RequestScope`, `@SessionScope`)

## Kapitel 4: Persistenz mit Spring Data JPA

- `@Entity`, `@Id`, Mapping auf Tabellen
- Repository-Interface (`JpaRepository`) statt Implementierung
- Abgeleitete Query-Methoden
- Controller greift zunächst direkt auf das Repository zu
- Ausblick: Domain-Modell vs. Entity, Mapper (MapStruct)

## Kapitel 5: Service Layer und Transaktionen

- Der Service als Ort der Fachlogik zwischen Controller und Repository
- Transaktionsklammer mit `@Transactional` (Propagation, Rollback-Regeln, `readOnly`)
- Fehlerbehandlung: `@ControllerAdvice`, fachliche Exceptions auf HTTP-Statuscodes abbilden

## Kapitel 6: Konfiguration

- `@Configuration` und `@Bean` (Fabriken, Parameter werden injiziert)
- `application.properties` / `application.yml`, Profile
- `@ConfigurationProperties` und `@PropertySource`

## Kapitel 7: Aspekte (AOP)

- Querschnittsbelange (Logging, Zeitmessung, Security) und Bezug zum Proxy aus Kapitel 1
- `@Aspect`, `@Around`, `@Before`, `@After`, Pointcuts
- Grenzen von Proxys (Self-Invocation), Zusammenspiel mit `@Transactional`

## Kapitel 8: Events

- Lose Kopplung durch Publish/Subscribe
- `ApplicationEventPublisher`, `@EventListener`
- Transaktionsgebundene Events (`@TransactionalEventListener`), asynchrone Verarbeitung

## Kapitel 9: Tests der REST-Endpoints gegen den laufenden Container

- `@SpringBootTest(webEnvironment = RANDOM_PORT)`
- `TestRestTemplate` bzw. `RestClient`/`WebTestClient` gegen den echten HTTP-Port
- Testdaten und Isolation der Tests
- Abgrenzung zu Unit-Tests (Mockito) und `@WebMvcTest`

---

## Begleitmaterial

- `Begriffe.txt` – Stichwortsammlung der wichtigsten Annotationen und Begriffe
