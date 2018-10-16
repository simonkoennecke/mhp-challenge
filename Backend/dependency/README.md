# Backend Coding Challenge: Module Dependency Challenge

### Projektaufbau:
Dieses Spring-Boot-Projekt besteht aus drei Submodulen (`inquiry`, `notification` und `application`). 
Das `notification`-Modul ist vom `inquiry`-Modul abhängig. Das `application` ist von beiden abhängig und dient als Spring boot Hauptmodul.

Der `InquiryTest` ruft `InquiryService#create(Inquiry)` auf und prüft, ob die Methoden `EmailHandler#sendEmail(Inquiry)`
und `PushNotificationHandler#sendNotification` mit dem gleichen Parameter aufgerufen wurden.

### Akzeptanzkritieren: 
 - Nach dem eine Inquiry erstellt wird, muss `EmailHandler#sendEmail(Inquiry)` und `PushNotificationHandler#sendNotification` ausgeführt werden.
   * `InquiryServiceInterface` Interface eingeführt. Das Interfacce erwartet die Funtkion `void send(Inquiry inquiry);`.
   * Das Interface  wurde bei EmailHandler und PushNotificationHandler angewendet und die `send` Funktion mit den Service send Funktion verknüpft.
   * InquiryService bekommt alle `InquiryServiceInterface` per Dependency Inject und verteilt die Anfrage an alle vorhanden Services.
 - Der `InquiryTest` muss erfolgreich sein
 
### Rahmenbedingungen:
 - Die Klassen `Inquiry`, `InquiryTest` und `Application` dürfen nicht modifiziert werden.
 - Die bestehenden Klassen dürfen nicht zwischen den Modulen verschoben werden
 - Die Abhängigkeiten zwischen den Modulen dürfen nicht angepasst werden.
 - Ansonsten können beliebige gradle dependencies hinzugefügt werden. 
 
