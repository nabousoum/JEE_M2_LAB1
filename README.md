
Commande – Application de Gestion de Commandes

Ce projet est une application de gestion de commandes construite avec Spring Boot, divisée en plusieurs modules : `services`, `web`, et `start`.

Technologies utilisées
- Java 17
- Spring Data JPA
- MapStruct (pour le mapping DTO-Entity)
- MySQL (Base de données)
- Thymeleaf (Template Engine)
- JUnit 5, Mockito (pour les tests unitaires)
- Log4j2 (pour la gestion des logs)
- Maven (pour la gestion des dépendances)
  
Structure du projet

Commande/
│
├── start/           # Module de démarrage Spring Boot
│
├── services/        # Couche Service : entités, DTOs, services, mappings, repos
│
├── web/             # Couche Web : contrôleurs et configuration Web
│
└── pom.xml          # Fichier Maven parent

Installation

1. Cloner le projet :


2. Aller dans le répertoire du projet :
   ```bash
   cd commande
   ```

3. Construire le projet avec Maven :
   ```bash
   mvn clean install
   ```

4. Configurer ta base de données dans `application.properties` (situé dans `start/src/main/resources`).

Configuration

Fichier `application.properties` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lab1_db
spring.datasource.username=root
spring.datasource.password=ton_mot_de_passe

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

logging.level.org.springframework=DEBUG
logging.file.name=logs/logs.log
```

Lancer l'application

Dans le répertoire `start`, lance l'application avec :
```bash
mvn spring-boot:run
```

Ou directement dans ton IDE (IntelliJ, Eclipse).

Endpoints REST

| Méthode | Endpoint             | Description                       |
|---------|----------------------|-----------------------------------|
| **GET**    | `/roles`             | Liste tous les rôles              |
| **POST**   | `/roles`             | Crée un nouveau rôle              |
| **PUT**    | `/roles/{id}`        | Met à jour un rôle existant       |
| **DELETE** | `/roles/{id}`        | Supprime un rôle                  |
| **GET**    | `/users`             | Liste tous les utilisateurs       |
| **POST**   | `/users`             | Crée un nouvel utilisateur        |
| **GET**    | `/products`          | Liste tous les produits           |
| **POST**   | `/products`          | Crée un nouveau produit           |

Exemples d'utilisation dans Postman :

- Ajouter un rôle :
  ```json
  POST /roles
  {
    "nom": "ROLE_ADMIN"
  }
  ```

- Ajouter un utilisateur :
  ```json
  POST /users
  {
    "nom": "Dupont",
    "prenom": "Jean",
    "email": "jean.dupont@example.com",
    "password": "secret",
    "etat": 1
  }
  ```

Tests

- Les tests pour les entités `Role`, `User` et `Product` sont dans le module `services`.
- Utilisation de Mockito et JUnit 5 pour simuler les dépendances et tester les services.


Journalisation (Logs)

Les logs sont configurés via Log4j2 et sont sauvegardés dans le fichier :
```
logs/logs.log
```

Pour activer les logs dans `application.properties` :
```properties
logging.level.org.springframework=DEBUG
logging.file.name=logs/logs.log
```

Auteurs
Seynabou SOUMARE
