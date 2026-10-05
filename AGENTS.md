# Claramente — Padrão de arquitetura

Referência normativa do projeto. Segue o mesmo padrão do `iris-mobile` (multi-módulo, contrato separado
de implementação, um tipo por arquivo, pastas por papel). O teste `ArchitectureRulesTest` (módulo `app`)
verifica as regras abaixo; não afrouxe o teste para acomodar código fora do padrão.

## 1. Um tipo por arquivo

- Cada arquivo `.kt` tem **exatamente uma declaração de topo** (class, data class, interface,
  sealed interface, enum class, object ou função `@Composable`) e o **nome do arquivo é o nome dela**.
- Proibido no topo do arquivo: outra classe, `val`/`const val`, função utilitária, `typealias`.
  Constantes vão para o `companion object` do dono ou para um `object XxxDefaults`/`XxxStyle`.
  Funções auxiliares viram membros de um `object` com nome de papel (`XxxFormatter`, `XxxPolicy`).
- Única exceção: os subtipos de uma `sealed interface`/`sealed class` ficam aninhados nela
  (a hierarquia é um tipo só). Qualquer outro tipo aninhado vai para arquivo próprio.
- Composable: uma função por arquivo. Telas são públicas; peças de tela ficam em `component/`
  como `internal`.

## 2. Módulos e direção das dependências

```
app ──► feature:* ──► core:*            feature nunca depende de outra feature
core:domain ──► core:model, core:data
core:data ──► core:model
core:ar                                  só a biblioteca de RA (SceneView/ARCore)
core:designsystem                        sem dependência interna
```

`app` é a raiz de composição: só ele conhece todos os módulos, monta o `AppContainer` e a navegação.
Módulos novos (`core:network`, `core:auth`, ...) só nascem quando houver arquivo para colocar neles.

## 3. Pastas por papel

Pastas permitidas dentro do pacote de cada módulo (crie só as que tiverem arquivo):

| Pasta | O que guarda | Sufixo/forma |
|---|---|---|
| `contract/` | **somente interfaces**: clientes, stores, gateways e casos de uso | prefixo `I`: `ILessonCatalogStore`, `IListLessonsUseCase` |
| `usecase/` | implementação de um caso de uso (uma operação) | `*UseCase` implementando `I*UseCase` |
| `error/` | mensagens de negócio/erro do módulo como constantes | `*Errors` (`object`) |
| `client/` | implementação HTTP de um contrato | `Http` + nome |
| `store/` | implementação de persistência local ou de dados embutidos | `Prefs`/`Asset`/`Seed` + nome: `SeedLessonCatalogStore` |
| `http/`, `dto/`, `mapper/` | infraestrutura de rede (quando houver backend) | |
| `model/` | modelo de domínio (só dados, sem org.json/Android) | substantivo |
| `lesson/` | área do `core:model` (modelo de domínio) | `Lesson`, `LessonShape` |
| `policy/` | regra pura, sem estado e sem I/O | `*Policy` |
| `helper/` | lógica com estado de uma feature | `*Tracker`, `*Recorder` |
| `view/` | tela/diálogo de topo da feature | `*Screen`, `*Dialog` |
| `component/` | peça de tela usada pela própria feature | substantivo + papel |
| `state/` | estado de UI e ações da tela | `*UiState`, `*Actions` |
| `viewmodel/` | `ViewModel` da tela | `*ViewModel` |
| `controller/` | orquestrador de escopo de aplicação | `*Controller` |
| `service/` | **somente** subclasses de `android.app.Service` | `*Service` |
| `di/`, `navigation/` | só no `app` | |
| `theme/` | tema do `core:designsystem` | `ClaramenteTheme`, `ClaramenteColors` |

Em `core:*` e `feature:*` todo arquivo fica dentro de uma pasta de papel; nada solto na raiz do pacote.

Proibido: `utils/`, `common/`, `misc/`, `helpers/` genérico, `Manager` como saco de responsabilidades.

## 4. Fluxo único e casos de uso

```
View (Composable) ──► ViewModel ──► I*UseCase ──► I*Store / I*Client ──► Seed*/Prefs*/Http*
```

- **Um caso de uso é uma operação.** Cada `*UseCase` tem exatamente um método público,
  `suspend fun execute(...)`, e recebe no construtor só o que essa operação usa.
  Interface `I*UseCase` em `core:domain/contract/`, implementação em `core:domain/usecase/`,
  mensagens em `core:domain/error/`.
- **ViewModel é fino:** chama o caso de uso, trata sucesso/falha e publica estado.
  Não importa `store/` nem os contratos de store/client — só `I*UseCase`.
- Regra de negócio fica no caso de uso ou numa `policy/`; a View só desenha e repassa ações.

## 5. Contratos e injeção

- Toda interface vive em `contract/` com prefixo `I`; `contract/` não tem classe concreta.
- Crie contrato quando a dependência fala com o mundo externo (servidor, disco) ou é um caso de
  uso. Não crie interface para data class nem para regra pura (`policy/`).
- Dependa do contrato, nunca da implementação.
- Injeção manual pelo construtor. Só o `AppContainer` instancia implementações; o `ViewModel` é
  criado na navegação com `viewModelFactory { initializer { ... } }` a partir do `AppContainer`.

## 6. Resultados e erros

- **Erro esperado é retornado, não lançado:** o caso de uso devolve `Result<T>`
  (`runCatching`/`Result.failure`), e quem chama trata `onSuccess`/`onFailure`.
- **Mensagem de negócio é constante** em `error/*Errors` do módulo, nunca literal solta no ViewModel.

## 7. Modelos e nomes

- `model/` é Kotlin puro: nada de `org.json`, `Context` ou tipos de rede.
- Sufixos proibidos: `Manager`, `Util`, `Helper` (como nome de classe), `Impl`, `Service` fora de `service/`.
- Código em inglês, textos de tela em português, sem comentários.
