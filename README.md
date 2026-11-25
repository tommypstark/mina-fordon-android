
# Mina fordon 🚘

En Android-app utvecklad i Kotlin med Jetpack Compose som en del av Examination 1 i Androidutveckling.

## Om appen

Mina fordon är en enkel app där användaren kan hålla reda på sina fordon och deras servicestatus.

Appen använder Material 3, ViewModel och UiState.

## Funktioner

- Lägg till fordon med namn och fordonstyp
- Välj mellan bil, lastbil, moped och motorcykel
- Markera service som utförd eller inte utförd
- Redigera befintliga fordon
- Ta bort fordon
- Visa statistik över servade fordon
- Visa hjälptext när fordonslistan är tom
- Filtrera på alla, servade eller ej servade fordon
- Sortera fordonen alfabetiskt (A–Ö)
- Stöd för både ljust och mörkt tema

## Kodstruktur

Appen är uppdelad i två huvudsakliga Kotlin-filer:

**MainActivity.kt**
- `VehicleScreen()` bygger upp appens gränssnitt.
- `VehicleRow()` visar ett enskilt fordon.
- Callbacks används för att hantera klick på checkboxen, redigeringsknappen och borttagningsknappen.

**VehicleViewModel.kt**
- `VehicleUiState` samlar appens tillstånd i en dataklass.
- `VehicleViewModel` hanterar fordonen och deras tillstånd.
- `uiState.copy()` används för att uppdatera tillståndet och därmed gränssnittet.

## Felsökning

**Problem:**

Ett av mina största problem med Kotlin är att hålla reda på var alla måsvingar, parenteser och komman ska vara. Det ledde flera gånger till kompileringsfel.

Ett exempel var när jag byggde dropdown-menyn och råkade placera måsvingarna fel.

**Lösning:**

Android Studio visade tydligt var felen fanns, men ibland hade jag svårt att förstå hur de skulle rättas till.

Ofta lyckades jag lösa problemen själv, men i det här fallet bad jag ChatGPT att granska koden och förklara vad som var fel. Därefter gjorde jag ändringarna själv och kunde kompilera appen igen.

## Hjälpmedel och lärdomar

**Hjälpmedel jag använt:**

- Vårt tidigare projekt Filmlistan
- Kursens teori och studieguide
- En egen checklista över examinationskraven
- ChatGPT som handledare vid kodning och felsökning

**Det som hjälpte mig mest:**

Jag började om från början med ett nytt projekt när jag kände att jag började tappa bort mig i koden. Det gjorde det lättare att förstå sambandet mellan funktionerna i `VehicleViewModel` och `MainActivity`.

Jag började med att uppfylla G-kraven och byggde sedan vidare med filtrering, sortering och en tydligare kodstruktur.

Att arbeta med en liten funktion i taget fungerade bäst för mig. Jag testade varje funktion innan jag fortsatte och gjorde regelbundna commits med tydliga meddelanden.

På så sätt kunde jag följa hur appen växte fram och samtidigt få bättre förståelse för min egen kod.

## Utvecklat med

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio
- Git och GitHub
