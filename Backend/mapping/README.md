# Backend Coding Challenge: Mapping Challenge

Akzeptanzkritieren: 
 - `Article` wird korrekt zu `ArticleDTO` gemapped (Siehe `ArticleController#list` und `ArticleController#details`) und als JSON von den Controllern ausgegeben.
   * Siehe Dao2DtoMapperTest
   * DTOs suffix hinzugefügt im Package com.mhp.coding.challenges.mapping.models.dto.blocks
 - Die Collection von `ArticleBlockDto` in `ArticleDTO` ist nach dem `sortIndex` in `ArticleBlockDTO` sortiert
   * Siehe ArticleControllerTest. Die Sortierung der Blocks wird im Service vorgenommen.
 - Falls ein `Article` per ID nicht gefunden werden kann, soll eine 404 Repsonse ausgeliefert werden (Siehe `ArticleController#details`)
   * Sollte der Controller ein Null Object erhalten setze den Status auf 404.
   * Schönere Option wäre eine Exception für das "NotFound" zu implementieren. 
 - Optional: Falls eine neue Implementierung/Ableitung von `ArticleBlock` implementiert wird und noch kein Mapping implementiert ist,
   soll mann darauf hingewiesen werden. Wie ist frei überlassen.
 
Rahmenbedingungen:
 - DB Models und DTO Models können mit Interfaces/Properties erweitert werden.
 - Bestehende Felder von Models und DTOs können nicht modifiziert werden. 
 - Die Packagestruktur darf nicht modifiziert werden. 
 - Es können beliebig gradle dependencies hinzugefügt werden. 
    * Es wurde ModelMapper in der Version 2.3.0 mit der Lizenz Apache License v2.0 verwendet
      * Der Vorteil ist, dass das Mapping auf Objekt Ebene beschrieben wird. Es kann ggf. auch auf Attribute Ebene Regeln angelegt werden.
      * Der Nachteil ist, dass das Mapping bei der Ausführung etwas aufwendiger ist.