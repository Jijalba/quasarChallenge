# Challenge Mercado Libre
## Operación Fuego de Quasar

### Introducción

Para este proyecto decidí utilizar Java Spring Boot para seguir las recomendaciones dadas en la presentación del desafío. A su vez modelé siguiendo el Diseño Guiado por el Dominio ( DDD ) haciendo foco en el modelo y separando las capas. Este desafío fue muy interesante ya que implicó un primer contacto con tecnologías y frameworks que no había tenido la oportunidad de utilizar, como así también la investigación y lectura de documentación de los mismos.

### Tecnologías utilizadas

 * Java Spring Boot
 * Maven 
 * Jpa 
 * JUnit Test 
 * MYSQL 
 * PhpMyAdmin
 * Swagger

### Nomenclatura

Para la nomenclatura de los diferentes aspectos en el código utilicé la guía propuesta por google:
 * https://google.github.io/styleguide/javaguide.html#s5-naming

### Ejecución del proyecto

 *   Para la ejecución del proyecto se necesita JDK 11 y Maven.

Se debe ubicar en la carpeta raiz y ejecutar el comando
`mvn spring-boot:run`

### Direcciones

 * Localhost: http://localhost:8080
 ⋅⋅* /topsecret/
 ⋅⋅* /topsecret_split/
 * Local Swagger  http://localhost:8080/swagger-ui.html
 * MySql Server:  vps-1917979-x.dattaweb.com:3306
 * PhpMyAdmin: http://vps-1917979-x.dattaweb.com:8080/
 * PostMan Local Collection: https://www.getpostman.com/collections/fa11aa45efabac706897
 -
 * Producción:
  ⋅⋅* AWS: 

### Domain Driven Design

Siguiendo este enfoque utilicé una arquitectura de capas donde se divide en:
 * Servicio
⋅⋅* Capa que se utiliza como orquestadora de actividades, pero no realiza aciones de negocio ni conserva estados
 * Dominio
⋅⋅* Contiene la información acerca del dominio y el estado de los objetos
 * Arquitectura
⋅⋅* Actua como capa de sorporte, provee infraestructura transversal técnica e implementa la persistencia de datos.


### Patrón Repositorio

El repositorio representa la abstracción de la colección de entidades de un dominio, esta no representa la capa de acceso a datos, ya que es independiente de la forma que se obtenga dicha colección.

### Lógica para la solución de los desafíos

En mi modelado representé la request a los servicios como Transmisiones, estas pueden ser únicas o múltiples, en el primer caso es un satélite el responsable de interceptar dicha transmisión y guardarla, en el segundo caso varios satélites participan de la acción y se entiende que al recibirse una solicitud de este tipo se intenta descifrar el mensaje y la posición de fuente de transmisión.

 * Descifrado de la posición de la fuente:
⋅⋅* Dediqué un día al estudio de la matemática detrás de la triangulación y las diferentes maneras que existía de resolver el caso, para concluir que existía un repositorio en maven que resolvía dicho planteo para n-D dimensiones con n+1 detectores (satélites)
⋅⋅* Repositorio e info técnica: https://github.com/lemmingapex/Trilateration

 * Descifrado de mensajes:
⋅⋅* Para esto utilicé una lógica que resuelve el desfasaje inicial en los mensajes mediante la búsqueda de la primer palabra y encontrando así el inicio real de cada Lista de palabras, como así también funciona para mensajes con palabras repetidas.


### Pruebas

Se encuentran diferentes pruebas con caminos correctos y erroneos utilizando JUnit en el proyecto, a su vez se puede descargar la colección de PostMan para las solicitudes o realizar el post a 
/topsecret/ con el siguiente payload:

```json
{
    "satellites": [
        {
            "name": "kenobi",
            "distance": 608.27,
            "message": [
                "Este",
                "",
                "",
                "mensaje",
                ""
            ]
        },
        {
            "name": "skywalker",
            "distance": 0,
            "message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 444.21,
            "message": [
                "Este",
                "",
                "un",
                "",
                ""
            ]
        }
    ]
}
```

donde se debería obtener la respuesta

```json
{
    "position": {
        "x": 100.0,
        "y": -100.0
    },
    "message": "Este es un mensaje secreto"
}
```

y a /topsecret_split/ con los siguientes payloads:

* POST ``` /topsecret_split/kenobi ```
```json
{
    "distance": 608.27,
    "message": [
        "Este",
        "",
        "",
        "mensaje",
        ""
    ]
}
```

* POST ``` /topsecret_split/skywalker ```
```json
{
    "distance": 0,
    "message": [
        "",
        "es",
        "",
        "",
        "secreto"
    ]
}
```

* POST ``` /topsecret_split/sato ```
```json
{
    "distance": 444.21,
    "message": [
        "Este",
        "",
        "un",
        "",
        ""
    ]
}
```

*GET 
 ``` /topsecret_split/```
 para obtener la respuesta:
 ```json
{
    "position": {
        "x": 100.0,
        "y": -100.0
    },
    "message": "Este es un mensaje secreto"
}
```