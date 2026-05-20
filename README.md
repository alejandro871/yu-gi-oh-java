# 🃏 Yu-Gi-Oh Java

Proyecto académico desarrollado en Java que simula un duelo básico de Yu-Gi-Oh, aplicando principios de **Programación Orientada a Objetos (POO)** y patrón **MVC**.

---

## Características

- **Sistema completo de cartas**: Monstruos, Cartas Mágicas y Cartas Trampa
- **Mecánica de turnos**: Robo, invocación, combate y fase final
- **Sistema de combate avanzado**: ATK vs DEF, ataques directos, sacrificios para cartas de alto nivel
- **50 cartas en total**: 30 monstruos, 10 cartas mágicas, 10 cartas trampa
- **Efectos variados**: Cada carta tiene un efecto único que se activa durante el turno
- **Dos modos de juego**: Consola y GUI (Swing)
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
- **Efectos temporales**: Algunos efectos (como Terraforming) duran solo un turno y se revierten en fase final

---

## Estructura del Proyecto (Patrón MVC)

```
src/
├── modelo/                    # MODELO - Lógica del juego
│   ├── Carta.java           # Clase abstracta base
│   ├── Monstruo.java        # Cartas de monstruos
│   ├── CartaMagica.java     # Cartas mágicas
│   ├── CartaTrampa.java     # Cartas trampa
│   ├── Jugador.java         # Lógica del jugador
│   ├── Juego.java           # Control del flujo del juego
│   ├── Mazo.java           # Creación y reparto de cartas
│   ├── Efecto.java          # Interfaz para efectos
│   └── efecto*.java         # Implementaciones de efectos
├── vista/                    # VISTA - Interfaz de usuario
│   ├── Vista.java           # Interfaz abstracta
│   ├── Consola.java         # Implementación CLI
│   ├── InterfazGrafica.java # Implementación GUI (Swing)
│   └── App.java            # Punto de entrada
└── controlador/             # CONTROLADOR - Coordinación
    └── Controlador.java    # Controla el flujo del juego
```

---

## Tecnologías utilizadas

- **Lenguaje**: Java 21
- **GUI**: Swing (java.awt, javax.swing)
- **Patrón**: MVC (Model-View-Controller)
- **Control de versiones**: Git & GitHub

---

## Ejecución

### Compilar el proyecto
```bash
javac -d out -sourcepath src src\vista\App.java
```

### Ejecutar (desde la raíz del proyecto)
```bash
# Modo Consola o GUI (selección automática)
java -cp out vista.App
```

### También puedes usar los scripts
- `ejecutar-consola.bat` - Modo consola
- `ejecutar-gui.bat` - Modo gráfico

---

## Conceptos OOP Aplicados

- **Clases y Objetos**: Toda entidad del juego (cartas, jugadores, mazo)
- **Herencia**: Monstruo, CartaMagica y CartaTrampa heredan de Carta
- **Clases Abstractas**: Carta es abstracta
- **Interfaces**: Activable, Efecto (con métodos default)
- **Polimorfismo**: Métodos sobrecargados en activación de efectos
- **Encapsulamiento**: Atributos privados con getters/setters controlados
- **Patrón MVC**: Separación clara de modelo, vista y controlador

---

## Cartas Implementadas

### Monstruos (30)
- Dragon Blanco, Vampiro, Pitbull, Golem, Golem de Piedra, Ciclope, Furia Nocturna, etc.
- Cada uno con ATK, DEF y Nivel únicos

### Cartas Mágicas (10)
1. **Pot of Greed** - Roba 2 cartas
2. **Curación** - Recupera 800 LP
3. **Terraforming** - +500 ATK temporal (se revierte al final del turno)
4. **Orden de Destrucción** - Destruye monstruo débil
5. **Daño** - 800 daño directo
6. **Aumento Ataque** - +700 ATK
7. **Escudo** - +900 DEF
8. **Doble Ataque** - Duplica ATK
9. **Debilidad** - -500 ATK
10. **Drenaje** - Drena 600 LP del oponente

### Cartas Trampa (10)
1. **Trampa Destructor** - Destruye atacante
2. **Trampa Defensa** - +800 DEF
3. **Trampa Bloqueo** - Bloquea ataque
4. **Trampa Devolución** - Refleja 700 LP
5. **Trampa Robo** - Descarta carta enemiga
6. **Trampa Curación** - Recupera 600 LP
7. **Trampa Debilidad** - -600 ATK
8. **Trampa Volteada** - Voltea posición
9. **Trampa Recuperación** - +500 LP defensivo
10. **Trampa Doble** - Duplica DEF

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
- Código modular y extensible
- Soporta efectos condicionales de trampas (solo se activan con monstruos en campo)

---

**"Confía en el corazón de las cartas" — Yugi Muto**