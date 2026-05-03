# 🍔 FoodApp — Full Stack Ordering System

Une application web complète de gestion de restaurants, menus et commandes, développée avec :

* ⚙️ **Backend** : Spring Boot (Java)
* 🎨 **Frontend** : React
* 🔐 **Auth** : JWT
* 🗄️ **Database** : JPA / Hibernate

---

# 🚀 Fonctionnalités

## 👤 Authentification

* Inscription / Connexion
* Auth JWT sécurisée
* Routes protégées

---

## 🏪 Restaurants

* Liste des restaurants
* Consultation des détails
* Navigation vers les menus

---

## 🍽 Menus

* Affichage des menus par restaurant
* Ajout / suppression de menus
* Liaison avec restaurant

---

## 🛒 Commandes (Core Feature)

* Ajout au panier
* Création de commande
* Calcul du prix total
* Gestion des items (OrderItem)

---

## 🔄 Workflow des commandes

```text
PENDING → PAID → PREPARING → READY → DELIVERED
```

* 💳 Paiement
* 👨‍🍳 Préparation
* 🍽 Prête
* 🚚 Livraison

---

# 🧠 Architecture

## 📦 Backend (Spring Boot)

```
backend
│   │               │   BackendApplication.java
│   │               │   
│   │               ├───config
│   │               │       CorsConfig.java
│   │               │       SecurityBeans.java
│   │               │       SecurityConfig.java
│   │               │       
│   │               ├───controller
│   │               │       AuthController.java
│   │               │       MenuController.java
│   │               │       OrderController.java
│   │               │       RestaurantController.java
│   │               │       UserController.java
│   │               │       
│   │               ├───dto
│   │               │       OrderItemRequest.java
│   │               │       OrderRequest.java
│   │               │       
│   │               ├───model
│   │               │       Menu.java
│   │               │       Order.java
│   │               │       OrderItem.java
│   │               │       OrderStatus.java
│   │               │       Restaurant.java
│   │               │       User.java
│   │               │       
│   │               ├───repository
│   │               │       MenuRepository.java
│   │               │       OrderRepository.java
│   │               │       RestaurantRepository.java
│   │               │       UserRepository.java
│   │               │       
│   │               └───security
│   │                       JwtFilter.java
│   │                       JwtService.java
```

---

## 📁 Frontend (React)

```
src/
│   App.css
│   App.js
│   App.test.js
│   index.css
│   index.js
│   logo.svg
│   ProtectedRoute.js
│   reportWebVitals.js
│   setupTests.js
│   
└───pages
        Home.js
        Login.js
        Menus.js
        Orders.js
        OrdersNG.js
        Register.js
        RestaurantMenus.js
        Restaurants.js
```

---

# 🧱 Modèle de données

## 🧾 Order

```java
@Entity
public class Order {
    private Long id;
    private OrderStatus status;
    private double totalPrice;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
```

---

## 🍱 OrderItem

```java
@Entity
public class OrderItem {
    private Long id;
    private int quantity;
    private double price;

    @ManyToOne
    private Menu menu;

    @ManyToOne
    private Order order;
}
```

---

# 🔌 API Endpoints

## 🏪 Restaurants

* `GET /restaurants`
* `GET /restaurants/{id}`
* `POST /restaurants`
* `DELETE /restaurants/{id}`

---

## 🍽 Menus

* `GET /menus`
* `GET /restaurants/{id}/menus`
* `POST /menus`
* `DELETE /menus/{id}`

---

## 🛒 Orders

* `GET /orders`
* `POST /orders`

### 🔄 Status flow

* `PUT /orders/{id}/pay`
* `PUT /orders/{id}/prepare`
* `PUT /orders/{id}/ready`
* `PUT /orders/{id}/deliver`

---

# 🔐 Sécurité

* Auth via JWT
* Header requis :

```http
Authorization: Bearer <token>
```

---

# ⚠️ Problèmes rencontrés & solutions

## 🔁 Boucle JSON infinie

Erreur :

```
Document nesting depth exceeds max
```

✔ Solution :

* `@JsonManagedReference`
* `@JsonBackReference`

---

## ❌ Mauvais format de commande

✔ Passage de `Order` → `OrderRequest` (DTO)

---

## ❌ Injection repository

✔ Utilisation correcte :

```java
private final OrderRepository repository;
```

---

# 🎨 UI

* Design moderne type restaurant premium
* Animations CSS
* Toast notifications
* Panier dynamique

---

# 🚀 Lancer le projet

## Backend

```bash
cd backend
mvn spring-boot:run
```

---

## Frontend

```bash
cd frontend
npm install
npm start
```

---

# 📈 Améliorations possibles

## 🔥 Niveau PRO

* Dashboard restaurant (commandes live)
* Filtrage par utilisateur
* WebSocket temps réel
* Paiement Stripe
* Rôles (USER / ADMIN / RESTAURANT)

---

## 🎯 UX

* Timeline des commandes
* Badges de statut
* Historique utilisateur

---

# 🧑‍💻 Auteur

Projet réalisé dans une logique d’apprentissage fullstack :

* Spring Boot avancé
* React moderne
* Architecture REST propre

---

# 💬 Conclusion

Ce projet couvre :

✔ CRUD complet
✔ Auth sécurisée
✔ Relations JPA complexes
✔ Workflow métier réel
✔ Frontend dynamique

👉 Base solide pour un projet portfolio ou SaaS 🍔
