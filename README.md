<<<<<<< HEAD
# Yu-Gi-Oh Java (GUI)

Proyecto académico desarrollado en Java que simula un duelo completo de Yu-Gi-Oh con interfaz gráfica, aplicando principios de Programación Orientada a Objetos (POO).
=======
# 🃏 Yu-Gi-Oh Java (Consola)

Proyecto académico desarrollado en Java que simula un duelo básico de Yu-Gi-Oh desde consola, aplicando principios de Programación Orientada a Objetos (POO).
>>>>>>> 7b027d838d38431844a0980b682f7894a22c63b4

---

##  Características

<<<<<<< HEAD
- **Sistema completo de cartas**: Monstruos, Cartas Mágicas y Cartas Trampa
- **Mecánica de turnos**: Robo, invocación, combate y fase final
- **Sistema de combate avanzado**: ATK vs DEF, ataques directos, sacrificios para cartas de alto nivel
- **50 cartas en total**: 30 monstruos, 10 cartas mágicas, 10 cartas trampa
- **Efectos variados**: Cada carta tiene un efecto único que se activa durante el turno
- **Interfaz Gráfica**: Menús interactivos con Swing
- **8.000 LP iniciales**: Sistema de vida para ambos jugadores
- **Mazo compartido**: 50 cartas se distribuyen aleatoriamente entre los jugadores

---

## Mecánica de Juego

- **Inicio**: Cada jugador comienza con 5 cartas en mano
- **Turnos**: Se alterna entre jugadores automáticamente
- **Robo**: Al iniciar turno, se roba 1 carta del mazo
- **Invocación**: Se puede jugar 1 carta por turno
- **Sacrificios**: Las cartas con más de 4 estrellas requieren sacrificar un monstruo
- **Combate**: Un monstruo puede atacar 1 sola vez por turno (no en el primer turno)
- **Victoria**: El primer jugador en llegar a 0 LP o quedarse sin cartas pierde

---

## Estructura del proyecto

```
src/
├── cartas/
│   ├── Carta.java (clase abstracta)
│   ├── Mounstruo.java
│   ├── CartaMagica.java
│   ├── CartaTrampa.java
│   └── Activable.java (interfaz)
├── efectos/
│   ├── Efecto.java (interfaz)
│   ├── (15+ efectos diferentes)
│   └── (TrampaDestructor, TrampaDefensa, etc.)
├── jugadores/
│   └── Jugador.java
├── juego/
│   ├── Juego.java
│   └── Mazo.java
├── vista/
│   ├── App.java (main)
│   ├── Vista.java (interfaz)
│   └── InterfasGrafica.java
└── InterfasGrafica.java
```

---

## Tecnologías utilizadas

- **Lenguaje**: Java 21
- **GUI**: Swing (java.awt, javax.swing)
- **Control de versiones**: Git & GitHub

---

## Ejecución

1. **Compilar el proyecto**:
   ```bash
   javac -d bin src/**/*.java
   ```

2. **Ejecutar el juego**:
   ```bash
   java -cp bin vista.App
   ```

3. **Interfaz interactiva**:
   - Ingresa los nombres de los duelistas
   - Selecciona acciones mediante menús
   - ¡Que comience el duelo!

---

## Conceptos OOP Aplicados

- **Clases y Objetos**: Toda entidad del juego (cartas, jugadores, mazo)
-  **Herencia**: Monstruo, CartaMagica y CartaTrampa heredan de Carta
-  **Clases Abstractas**: Carta es abstracta (no se puede instanciar directamente)
-  **Interfaces**: Activable, Efecto
-  **Polimorfismo**: Métodos sobrecargados en CartaMagica y CartaTrampa
-  **Encapsulamiento**: Atributos privados con getters/setters controlados

---

##  Cartas Implementadas

### Monstruos (30)
- Dragon Blanco, Vampiro, Pitbull, Golem, Ciclope, Furia Nocturna, etc.
- Cada uno con ATK, DEF y Nivel únicos

### Cartas Mágicas (10)
1. Pot of Greed - Roba 2 cartas
2. Curación - Recupera 800 LP
3. Terraforming - +500 ATK temporal
4. Orden de Destrucción - Destruye monstruo débil
5. Daño - 800 daño directo
6. Aumento Ataque - +700 ATK
7. Escudo - +900 DEF
8. Doble Ataque - Duplica ATK
9. Debilidad - -500 ATK
10. Drenaje - Drena 600 LP
11. Robo Curación - Roba 1 + 300 LP

### Cartas Trampa (10)
1. Trampa Destructor - Destruye atacante
2. Trampa Defensa - +800 DEF
3. Trampa Bloqueo - Bloquea ataque
4. Trampa Devolución - Refleja 700 LP
5. Trampa Robo - Descarta carta enemiga
6. Trampa Curación - Recupera 600 LP
7. Trampa Debilidad - -600 ATK
8. Trampa Volteada - Voltea posición
9. Trampa Recuperación - +500 LP defensivo
10. Trampa Doble - Duplica DEF

---

## Autores

- Alejandro Jaramillo
- Karen Elizabeth Berg Garcia
- Juan Polania Navarro
- Alejandro Galeano Castro

---

## Notas

- No se utilizan librerías externas (solo Java SE estándar)
- Interfaz completamente en español
- Sistema de eventos para todas las interacciones del usuario
- Código modular y fácil de extender con nuevas cartas y efectos

---

**"Confía en el corazón de las cartas" — Yugi Muto**
=======
- Creación de cartas (Monstruo, Mágicas)
- Sistema de efectos mediante interfaces
- Uso de herencia y polimorfismo
- Gestión de jugadores
- Ejecución desde consola

---

##  Estructura del proyecto

src/
├── cartas/
├── efectos/
├── jugadores/
├── juego/
└── App.java


---

##  Lo que use

- Java
- VS Code
- Git & GitHub

---

##  Ejecución

1. Compilar el proyecto
2. Ejecutar `App.java`

---

## 📌 Conceptos aplicados

- Clases y objetos
- Herencia (`extends`)
- Interfaces (`implements`)
- Polimorfismo
- Encapsulamiento

---

##  Autores

- Alejandro Jaramillo
- Alejandro Galeano -2477228
- Karen Berg -2459506
- Juan Polania Navarro

>>>>>>> 7b027d838d38431844a0980b682f7894a22c63b4
