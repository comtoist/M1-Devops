# M1-Devops
[![Java CI with Maven](https://github.com/comtoist/M1-Devops/actions/workflows/maven.yml/badge.svg)](https://github.com/comtoist/M1-Devops/actions/workflows/maven.yml)

## Fonctionnalité

  - Creation de data frame en utilisant :
      * Un chemin vers un fichier csv.
      * Un tableau d'objet représentant l'intérieur de la frame, la liste des labels et la liste des types.
      * Un tableau d'entier (ou de chaine de caractère) avec un label (pour créer un data frame d'une seule colonne).
  - Affichage de l'entièreté ou des premières (ou dernières lignes) de la data frame.
  - La sélection des lignes à utiliser d'une data frame.
  - Création de data frame en utilisant seulement certaines lignes de data frame existant.
  - Demander des statistiques sur les colonnes d'entier d'un data frame.

## Installation

Une fois les fichiers sources téléchargés depuis le dépot git il faut utiliser les lignes :
```sh
mvn compile
mvn package
```

Une archive jar sera alors créée dans le dossier target.

## Utilisation

En utilisant l'archive créée par la compilation dans le classpath de vos projet, vous aurez accès au dataframe.

Pour utiliser le data frame il faut penser à l'importer :
```sh
import fr.uga.devops.Datafram
```

## Documentation
Voici la spécification des classes utilisées :

### Datafram

#### Constructors
```sh
Datafram(Integer[] tab,String label)
```
Crée un data frame contenant une colonne *label* avec *tab* comme contenu.

```sh
Datafram(String[] tab,String label)
```
Crée un data frame contenant une colonne *label* avec *tab* comme contenu.

```sh
Datafram(Object[][] tab,String[] label,String[] type)
```
Crée un data frame contenant plusieurs colonnes portant comme nom *label* avec *type* comme type et *tab* comme contenu.
*label* et *type* doivent être de la même taille.

```sh
Datafram(String filename)
```
Crée un data frame grace au fichier au format csv qui a pour chemin relatif *filename*.

```sh
void printf(ArrayList<Colonne> dat)
```
Affiche le data frame formé des colonnes *dat*.

```sh
void tail()
```
Affiche les 5 dernières lignes du data frame.

```sh
void tail(int n)
```
Affiche les *n* dernières lignes du data frame.

```sh
void head()
```
Affiche les 5 premières lignes du data frame.

```sh
void head(int n)
```
Affiche les n premières lignes du data frame.

```sh
ArrayList<Colonne> newDatafram(ArrayList<Colonne> datafram, String line, String col)
```
Crée un nouveau data frame en utilisant les lignes d'index *line* et les colonnes de label *col* depuis le data frame *datafram*.

```sh
Datafram getSubFram(ArrayList<Integer> listLigne)
```
Crée un sous data frame en utilisant les lignes d'index à l'intérieur de *listLigne*

```sh
Datafram GroupBy(String op, String col, ArrayList<String> constante)
```
Crée un sous data frame en fonction de la valeur dans la colonne *col* de chaque ligne.
Si *op* vaut "=" alors seule les lignes ayant une valeur dans *col* aussi présente dans *constante* seront sélectionnées.
Sinon seule les lignes qui n'ont pas de valeur dans col sont sélectionnées.

```sh
Datafram GroupBy(String op, String col, Integer constante)
```
Crée un sous data frame en fonction de la valeur dans la colonne *col* de chaque ligne.
*op* peut prendre les valeurs "<", "<=", ">", ">=", "=" ou "!=".

```sh
void printf()
```
Affiche le data frame.

```sh
Boolean equals(Object dat)
```
Renvoie *true* si dat est le même data frame.

```sh
float maximum(Colonne c)
```
Renvoie le maximum de la colonne d'Integer *c*.

```sh
float minimum(Colonne c)
```
Renvoie le minimum de la colonne d'Integer *c*.

```sh
float moyenne(Colonne c)
```
Renvoie la moyenne de la colonne d'Integer *c*.

```sh
float somme(Colonne c)
```
Renvoie la somme de la colonne d'Integer *c*.

## Choix des outils

On a utilisé maven pour la compilation, le packaging et le test automatique.
Le résultat des tests se trouve dans surefire-report, en format txt ou en xml.

On a utilisé le workflow des features branchs car c'est un workflow adapté au petit projet n'ayant pas besoin de release.
Pour la validation des pull request, nous avons fait valider nos pull request par une autre personne que celle qui a fait la pull request. Le processus de validation était de s'assurer que les tests unitaires fonctionnent bien, lire les modifications fichier par fichier et valider. Ensuite quand cela est fait, le merge passe une nouvelle fois par le pipeline et si tout est validé, alors on accepte la pull request.

Pour la couverture de code nous avons utilisé jacoco comme conseillé, ce qui nous permet d'avoir des rapports sur le code coverage sous différents formats. 
Le résultat de la couverture de code se trouve dans target/site/jacoco sous différents formats.

## feedback

Nous avons beaucoup appris sur les différents outils utilisés dans ce projet notamment sur le workflow git, les pull_requests et sur les pipelines.
Nous sommes un peu déçu de ne pas avoir réussi à mettre en place docker. Nous avons fait un Dockerfile qui est encore à la racine du dépot si vous souhaitez le voir, mais celui ne fonctionne pas. Nous avons également mis en place le username et le token pour docker hub dans les settings du dépot git.
