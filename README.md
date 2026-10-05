# Claramente

App Android educacional de realidade aumentada (Kotlin, Jetpack Compose, ARCore via SceneView).

## Requisitos

- Android Studio (AGP 9.3, Kotlin 2.2, JDK 21 embutido)
- **Aparelho físico compatível com ARCore** (Android 7+, `minSdk 24`). O emulador comum não serve para testar RA.
- Google Play Services para RA instalado no aparelho (o app pede a instalação na primeira abertura)
- Lista de aparelhos: https://developers.google.com/ar/devices

## Testar a realidade aumentada

1. Conecte o aparelho com depuração USB e rode o módulo `app` pelo Android Studio.
2. Na tela inicial, toque em **Testar realidade aumentada**.
3. Permita a câmera. Aponte para o chão ou uma mesa e mova o aparelho devagar; a grade de superfície aparece.
4. Quando o aviso mudar para "Superfície encontrada", toque nela. Um cubo deve aparecer ancorado no lugar.
5. Ande ao redor: o cubo tem que ficar fixo no ambiente. **Limpar** remove os objetos.

Aparelhos sem suporte a ARCore abrem a lição em **modo 3D** (a forma gira com o dedo, sem câmera).
Se a RA falhar no meio (ARCore ausente, câmera negada), a tela mostra o motivo e oferece "Ver em 3D".

## Estrutura

Multi-módulo, um tipo por arquivo e pastas por papel. Regras completas em [AGENTS.md](AGENTS.md),
verificadas pelo `ArchitectureRulesTest` a cada `testDebugUnitTest`.

```
app/                 ClaramenteApplication, MainActivity, navigation/, di/AppContainer
core/
├─ model/            lesson/ (Lesson, LessonShape)
├─ data/             contract/ILessonCatalogStore, store/SeedLessonCatalogStore
├─ domain/           contract/ (I*UseCase), usecase/, error/
├─ ar/               model/ArHint, policy/ArHintPolicy, error/ArErrors (expõe SceneView/ARCore)
└─ designsystem/     theme/, component/
feature/
├─ hub/              tela inicial
├─ catalog/          lista de lições
└─ lesson/           tela de RA (cena, posicionamento por toque)
```

Fluxo: `View → ViewModel → I*UseCase (core:domain) → I*Store (core:data) → Seed*`.

## Testes

```
./gradlew testDebugUnitTest
```
