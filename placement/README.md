# TP Gestionnaire de placement

## 1 - Conteneurs de haut niveau

1) La JWindow peut être interressante pour les apps plein écran ou pour les splash screen lors du chargment d'une appli lourde

3) La fenetre se ferme mais l'appli ne s'arrete pas

5) La JDialog version modale (`setModal(true);`) empeche l'interaction avec la fenêtre principale

## Gestionnaire de placement

6) Les boutons se redimensionnent pour occuper tout l'espace.

7) La taille des boutons ne change pas , ils se repositionnent pour occuper l'espace horizontal

8) Les boutons se redimensionnent pour occuper tout l'espace. Ils font toujours tous la même taille

9) 
* `RigidArea` permet de créer un espace de taille fixe. Elle ne changera pas même si la fenêtre est redimensionnée
* `VerticalGlue` rempli tout l'espace vertical encore disponible, ce qui fait que le 3eme bouton est en base de la fenêtre
* `HorizontalGlue` a la même fonction, mais pour l'espace horizontal


