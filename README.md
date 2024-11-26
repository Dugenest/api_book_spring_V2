# api_book_spring_V2    
   
# API de Gestion des Livres Version 2


### Description
Cette API permet de gérer une collection de livres avec des informations sur les auteurs et les catégories. Elle propose des fonctionnalités CRUD (Create, Read, Update, Delete) pour les entités suivantes :
- **Book** (Livre)
- **Author** (Auteur)
- **Category** (Catégorie)

### Prérequis
Pour utiliser cette API, vous aurez besoin des outils suivants :
- Java 17 ou version supérieure.
- Maven pour la gestion des dépendances.
- Une base de données relationnelle (comme MySQL).
- Postman (ou tout autre outil similaire) pour tester les endpoints.

### Structure du Modèle de Données

#### Entité **Book**
- **idBook** : Identifiant unique du livre.
- **titleBook** : Titre du livre.
- **dateBook** : Date de publication du livre.
- **author** : Relation *ManyToOne* avec l'entité **Author**.
- **categories** : Relation *ManyToMany* avec l'entité **Category**.

#### Entité **Author**
- **authorId** : Identifiant unique de l'auteur.
- **authorName** : Nom de l'auteur.
- **authorFirstname** : Prénom de l'auteur.
- **authorNationality** : Nationalité de l'auteur.
- **books** : Relation *OneToMany* avec l'entité **Book**.

#### Entité **Category**
- **idCategory** : Identifiant unique de la catégorie.
- **nameCategory** : Nom de la catégorie.
- **books** : Relation *ManyToMany* avec l'entité **Book**.

### Installation

1.	Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/votre-repo.git
   cd votre-repo

2.	Configurez la base de données :
	Modifiez les propriétés de connexion dans le fichier application.properties :

	spring.datasource.url=jdbc:mysql://localhost:3306/votre_base_de_donnees
	spring.datasource.username=votre_nom_utilisateur
	spring.datasource.password=votre_mot_de_passe
	spring.jpa.hibernate.ddl-auto=update

3.	Construisez et exécutez l'application :

mvn spring-boot:run

4.	Endpoints de l'API :
	Endpoints pour Book
		- GET /books : Récupère la liste de tous les livres.
		- GET /books/{id} : Récupère les détails d'un livre par son ID.
		- POST /books : Ajoute un nouveau livre.
		
		Body JSON :		
			{
			  "titleBook": "Titre Exemple",
			  "dateBook": "2023-01-01",
			  "authorId": 1,
			  "categories": [1, 2]
			}
		
		- PUT /books/{id} : Met à jour un livre existant.
		- DELETE /books/{id} : Supprime un livre par son ID.
		
	Endpoints pour Author
		- GET /authors : Récupère la liste de tous les auteurs.
		- GET /authors/{id} : Récupère les détails d'un auteur par son ID.
		- POST /authors : Ajoute un nouvel auteur.
		
		Body JSON :
			{
			  "authorName": "Nom Exemple",
			  "authorFirstname": "Prénom Exemple",
			  "authorNationality": "Française Exemple"
			}
		
		- PUT /authors/{id} : Met à jour un auteur existant.
		- DELETE /authors/{id} : Supprime un auteur par son ID.
		
	Endpoints pour Category
		- GET /categories : Récupère la liste de toutes les catégories.
		- GET /categories/{id} : Récupère les détails d'une catégorie par son ID.
		- POST /categories : Ajoute une nouvelle catégorie.
		
		Body JSON :
			{
			  "nameCategory": "Catégorie Exemple"
			}
		
		- PUT /categories/{id} : Met à jour une catégorie existante.
		- DELETE /categories/{id} : Supprime une catégorie par son ID.
		
5.	Exécution des Tests :
	Importez la collection Postman (fichier .json) disponible dans le projet.
	Configurez l'environnement Postman avec les valeurs correspondantes.
	Exécutez les requêtes pour tester les différents endpoints.
	
## Contributions
	Les contributions sont les bienvenues ! Pour toute modification ou suggestion, ouvrez une issue ou une pull request.

## Licence
	Ce projet est sous licence MIT.
