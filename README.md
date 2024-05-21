# Trabajo Práctico IA 2024

## Inteligencia Artificial - UTN-FRSF

Integrantes:

* Ariel Benz
* Laura Caro
* Daniel Cabrera

El proyecto se encuentra en la siguiente carpeta:

* examples/src/frsf/cidisi/faia/examples/search/impostor

Los valores que se pueden cambiar están todos en el archivo GameStructure.java y son los siguientes:

* INITIAL_SABOTAGE_ROOMS se definen las habitaciones a sabotear, estas son constantes definidas previamente y con un ID único.

* CREW es un hashmap donde tenemos como key el valor de una habitación/pasillo y en el value la cantidad de tripulantes que contiene esa habitación.

Así que modificando esos dos valores tendremos diferentes escenarios del juego. Luego también podemos definir el método de búsqueda cambiando la función getSearchStrategy() y/o el selectAction() del ImpostorAgent.java.

Desarrollo de un Agente de software que pueda jugar en el rol de un “Impostor” que debe desplazarse en el escenario del juego, según las especificaciones de diseño para así poder sabotear las operaciones de la nave espacial y eliminar a los “Tripulantes” sin ser descubierto.

Escenario del juego

<img width="974" alt="Captura de pantalla 2024-04-30 a la(s) 4 29 33 p  m" src="https://github.com/arielbenz/tp-ia2024/assets/2475539/21d17126-b225-4140-8ff2-a834d22bc8fd">
