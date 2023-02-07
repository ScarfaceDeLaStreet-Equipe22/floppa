# TP1

# Planification du travail sur Github

## Github Project
### Présentation claire des étapes du processus
Pour suivre l’avancement des issues, Github Project sera utilisé.
Dans cet outil, il est possible d’indiquer l’avancement des différents issues.
Il y a quatre catégories dans lesquelles
un issue peut être : Ready, In Progress, In review, Done.
De plus, il y a quatres différentes vue du tableau : une pour chaque itération

### Présence de l'ensemble des issues dans les bonnes colonnes
Dans le screenshot ci-dessous, on observe l'avancement des tâches selon leur status.

### Screenshots
<p align="center">
  <img src="./img/githubProject.jpg">
</p>


## Milestone
L'équipe à créée 4 milestones pour mener le projet à bien : un pour chaque Tp.
Chaque issue doit être associé à un mileston, ainsi, dans la section milestone, l'équipe peut facilement suivre l'avancement des tâches.
### Screenshots
<p align="center">
  <img src="./img/milestone.jpg">
</p>


## Issues
Chaque tâche est représentée par une issue. Chaque issue à été determiner en équipe.
De plus, pour une meilleur séparation du travail à faire, chaque fonctionnalité est représenter en issue et est liée à un label nommée feature.
Chaque issue de type feature est redécoupé en d'autre issues qui sont associées à des label story.
Graĉe à cette séparation issue, il devient facile de suivre notre stratégie de branching. Il suffit de merge toutes les branches story dans celle de la feature.
Toutes les issues se retrouvent dans le gitProject.
### Screenshots
<p align="center">
  <img src="./img/issue1.jpg">
</p>
<p align="center">
  <img src="./img/issue2.jpg">
</p>
<p align="center">
  <img src="./img/issue3.jpg">
</p>


## Pull requests
### Screenshots


## Arbre de commits
### Screenshots

## Fichiers ignorés
Notre fichier .gitignore ignore tous les fichiers de configurations personnelles, ceux qui contiennent des paths personnelles et
les fichiers auto-générés (par java et intelliJ). Le fichier est principalement fait à partir d'un dépôt git collaboratif qui discute des fichier gitignore. https://gist.github.com/dedunumax/54e82214715e35439227

## Stratégie de commit
1. Voici la nommenclature des commits que l'équipe à choisi : `type : message`. Voici un exemple : s:"ajout de la classe Product". Les types de commits sont : story (s), clean code (c) et documentation (d).
2. On commit lorsque le travail éffectuer pour la branches et terminées, puis on fait un dernier commit de clean code.

## Stratégie de branchage
1. Les branches de base sont main et develop. Main est la branche principale elle contient le code officiel pour chaque remise. Il y a aussi la branche develop qui contient nos branches features. La branche sert principalement à tester le code regroupé avant de tout mettre dans la branceh main.
2. La branche principal est main.
3. L'équipe à choisi de s'inspirer de Git Flow pour sa stratégie de branching.
   Chaque branche est relié à une issue.
   Voici la nommenclature des branches : `type d'issue/nom de l'issue`.
   Les types d'issue sont feature (f) ou story (s).
   Voici un exemple de deux branches: f/creation_vendeur et s/creation_vendeur_route.
   On crée une branche à chaque fois qu'on veux travailler sur une issue.
4. Les branches de story vers celle de la feature n'ont pas besoin d'être des pull request (pas de revue de code). En revanche, les merge de feature vers develop doivent nécéssairement passées par une pull request et doivent être revue par au moins une personne. Même chose pour les merge de develop vers la branche main.



