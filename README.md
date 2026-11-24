# Mina fordon

En Android-app utvecklad i Kotlin med Jetpack Compose som en del av Examination 1 i Androidutveckling.

## Om appen

Mina fordon är en app där användaren kan hålla reda på sina fordon och deras servicestatus.

Appen använder Material 3, ViewModel och UiState.

## Funktioner

- Lägga till fordon med namn och fordonstyp
- Markera service som utförd eller inte utförd
- Redigera befintliga fordon
- Ta bort fordon
- Visa statistik över servade fordon
- Visa hjälptext när fordonslistan är tom

## Felsökning

**Problem:**

Ett av mina största problem med Kotlin är var alla måsvingar, parenteser och komman ska vara, så det hände flera gånger att jag fick kompileringsfel pga detta. Detta hände t.ex när jag byggde Dropdown-menyn, att måsvingarna hamnade fel.

**Lösning:**

IDE:n beskrev tydligt vad själva felet var, mitt problem var att lösa det helt själv. Ofta får jag till det själv, men i detta fall fick jag be AI att granska koden och förklara exakt vad som felade, sen åtgärdade jag det. Efter det gick det att kompilera igen.

## Hjälpmedel och lärdomar

**Hjälpmedel jag använt:**

Jag använde vårt tidigare projekt Filmlistan, teoriguiden, en checklista samt ChatGPT som hjälpmedel. Jag började med att skapa en checklista för att först uppfylla G-kraven, som jag bockade av eftersom jag fyllde på med fler funktionaliteter i appen.

**Det som hjälpte mig mest:**

Genom att börja om från början med ett nytt projekt när jag kände att jag började tappa bort mig i koden, så hittade jag lättare sambandet mellan alla funktioner i VehicleViewModel och MainActivity. Jag byggde på en synlig funktion i taget, och testade mig fram tills det fungerade innan jag gjorde en ny och tydlig commit.

## Utvecklat med

- Kotlin
- Jetpack Compose
- Material 3
- Android Studio
- Git och GitHub
