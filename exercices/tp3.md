# TP3

## Rétrospective CI

### Combien de temps passiez-vous à vérifier et tester manuellement le code lors des intégrations et des remises avant l'implantation du pipeline de tests automatisés?

Faire les tests manuels n'était pas très long, environ une dizaine de minutes, mais il fallait les faire à presque toutes les PR pour s'assurer que les modifications n'avait brisé le code existant. Au total tous les tests s'additionnait à une heure ou deux par personne aufil de l'itération.

### Combien de temps passiez-vous à faire ces vérifications après l'implantation du CI?

Il est évidemment toujours nécessaire de tester notre code pour s'assurer que tout corresponde au demandes, mais nous pouvons concentrer la plupart des tests en une session d'une heure environ à la fin de l'itération.

### Quels sont les points positifs que le CI a apporté à votre processus? Donnez-en au moins 3.

- Comme mentionné plus haut, il nous fait sauver beaucoup de temps sur le long terme.
- Il permet de diminuer grandement le nombre de bugs laissé par inadvertance.
- Il permet de simplement détecter les bugs causé lors du merge vers develop.
- Il nous permet d'avoir confiance en notre code. Si les tests passent, on peut assumer que notre code fonctionne bien.

### Le pipeline CI amène-t-il un élément qui pourrait devenir négatif ou dangeureux pour le processus, le produit et/ou l'équipe? Justifiez.

La pipeline peut amener un faux sentiment de sécurité. Il est possible que tous les tests passent, alors qu'un bug important est toujours présent. Il faut donc toujours tester notre code et rester à l'affût.

### Quel proportion de temps passez-vous à faire l'implémentation du code fonctionnel versus celui des tests? Est-ce que cette proportion évolue au fil du temps? Pourquoi?

En général la proportion est environ 70-30, mais cela varie en fonction de la complexité des fonctionnalités. Au début, écrire les tests prenait autant de temps qu'écrire le code fonctionnel, mais plus on écrit des tests, plus on est à l'aise avec les librairies de tests, plus notre convention de tests est établie et moins de temps cela prend.

### L'implémentation de tests augmente naturellement la charge de travail. Comment cela a-t-il affecté votre processus? (ex : taille des issues/PRs, temps d'implémentation, planification, etc.)

La taille des issues et PR n'a pas été affectés, mais pour chaque fonctionnalités, une issue pour implémenter les tests est ajouté. Lors de l'attribution des tâches il a donc été nécessaire d'estimer le temps d'implémentation des tests.

### Avez-vous plus confiance en votre code maintenant que vous avez des tests? Justifiez.

Oui, les tests nous permettent d'avoir confiance que le code fonctionne à tout moment. Si les tests passent, cela veut dire que globalement, le code fonctionne et qu'on peut y faire confiance.

### Que pouvez-vous faire pour améliorer l'état actuel (début TP2) de vos tests? Donnez au moins 3 solutions.

- Ajouter des tests d'intégrations et End-To-End
- Ajouter des tests unitaires pour certains cas qui n'étaient pas pris en compte
- Améliorer notre structure pour pouvoir écrire de nouveaux tests plus simplement.

## Stories

### Story 1: Ajout de documentation API avec Swagger

#### Description: 
En tant que développeur, je veux avoir accès à une documentation claire et interactive de l'API Floppa, afin de faciliter l'intégration et l'utilisation de l'API dans mes projets.

#### Critères de succès:
- Intégrer Swagger pour générer automatiquement la documentation de l'API.
- La documentation doit être accessible via une URL dédiée (par exemple, /api-docs).
- La documentation doit couvrir toutes les routes existantes et fournir des exemples de requêtes et de réponses.
- Les développeurs doivent être en mesure de tester les routes directement à partir de la documentation Swagger.

### Story 2: Système de recommandation de produits

#### Description:
En tant qu'utilisateur, je veux recevoir des recommandations de produits basées sur mes préférences et mes achats précédents, afin de découvrir de nouveaux produits qui pourraient m'intéresser.

#### Critères de succès:

- Afficher les recommandations sur la page d'accueil de l'utilisateur. 
- Prendre en compte les préférences de l'utilisateur et les achats précédents pour générer les recommandations. 
- Mettre à jour les recommandations lorsqu'un achat est effectué.

### Story 3: Système de notation et d'évaluation des vendeurs

#### Description:
En tant qu'acheteur, je veux pouvoir noter et évaluer les vendeurs après un achat, afin de partager mon expérience et d'aider les futurs acheteurs à choisir un vendeur de confiance.

#### Critères de succès:
- Permettre aux acheteurs de noter et de laisser un commentaire sur un vendeur après un achat. 
- Afficher les notes et les commentaires sur la page de profil du vendeur. 
- Calculer la note moyenne du vendeur en fonction des notes reçues.

### Story 4: Système de suivi de l'expédition des produits

#### Description:
En tant qu'acheteur, je veux pouvoir suivre l'expédition de mon produit acheté, afin de connaître l'état de ma commande et la date estimée de livraison.

#### Critères de succès:
- Afficher les informations de suivi (statut, transporteur, numéro de suivi, etc.) sur la page de commande.
- Mettre à jour régulièrement les informations de suivi en fonction des informations fournies par le transporteur.  
- Envoyer des notifications par e-mail ou par SMS à l'acheteur lorsqu'il y a des mises à jour importantes concernant l'expédition.