# Operación Fuego de Quasar

## Arquitectura de software

## Vista de paquetes

[![](https://i.ibb.co/tsdf3mx/diagrama-De-Paquetes.png)](https://i.ibb.co/tsdf3mx/diagrama-De-Paquetes.png)

* controller: Todos las clases que reciben peticiones HTTP a los end-point defindos.
* services: Todas las clases con lógica de negocio para localizar nave y construir mensaje.
* entidades: Todas las clases que representan el negocio.
* exceptions: Todas las excepciones para el manejo de errores controlados.
* libraries: Dependencias con librerías externas.

## Vista de clases

El diagrama de clases expone las clases necesarias para representar el negocio, los servicios con lógica y reglas de negocio y Controladores para atender la petición HTTP a los end-points.

[![](https://i.ibb.co/H7k2fRF/diagrama-De-Clases.png)](https://i.ibb.co/H7k2fRF/diagrama-De-Clases.png)

## Componentes utilizados

* Java 1.8
* Graddle
* Springboot 2.4.1

## Inicializar aplicación

Una vez iniciada la aplicación, el servicio topsecret se encontrará disponible para ser consumido. Como la aplicación está basada en Spring Boot, esta trae consigo un servidor web tomcat embebido, el cual estará escuchando por el puerto 8080, es decir, el servicio se podrá consumir desde la url http://localhost:8080.

El servicio topsecret tiene las siguientes operaciones disponibles:  

- api/v1/topsecret (POST)  
  Esta funcionalidad se encarga de recibir el mensaje de auxilio obtenido por cada uno de los satélites con el fin de poder determinar la ubicación de la nave carguera. Cada uno  de los mensajes está acompañado por la distancia existente entre el satélite y la nave carguera. 
  > Input:  
  {
    "satellites": [  
        {  “name”: "kenobi",  
          “distance”: 100.0,  
          “message”: ["este", "", "", "mensaje", ""]  
        },  
        {  “name”: "skywalker",  
            “distance”: 115.5  
            “message”: ["", "es", "", "", "secreto"]  
        },  
        {  “name”: "sato",  
           “distance”: 142.7  
           “message”: ["este", "", "un", "", ""]  
        }  
      ]  
    }
  
  De ser posible localizar la nave de carga y obtener el mensaje completo, la resultado será un código de respuesta 200 y respuesta de la siguiente manera:
  
  > Output:  
  {  
    "position": {  
      "x": -100.0,  
      "y": 75.5  
    },  
    "message": "este es un mensaje secreto"  
  }  
  
  En caso de que no se pueda ubicar la nave carguera o el mensaje no pueda ser completado, el resultado será un codigo de respuesta 404 más una breve descripción de lo sucedido.
  
  
- api/v1/topsecret_split/{satelliteId} (POST)  
  Esta funcionalidad se encarga de recibir el mensaje de auxilio obtenido por un satélite (*{satelliteId}*). QuasarFireOperation quedará a la espera de la información de los demás satelites para poder intentar localizar la nave de carga.
  > Input:  
  {  
    "distance": 100.0,  
    "message": ["este", "", "", "mensaje", ""]  
  }  
  
  > Output:  
  En caso de que el mensaje sea válido y el nombre del satélite sea correcto, la operación será exitosa y se obtendrá un código de respuesta 200. En caso contrario, el resultado será un codigo de respuesta 404.
  
  
- api/v1/topsecret_split (GET)  
  Esta funcionalidad permite obtener, de ser posible, la ubicación de la nave de carga y el mensaje de auxilio.
   > Output:  
  {  
    "position": {  
      "x": -100.0,  
      "y": 75.5  
    },  
    "message": "este es un mensaje secreto"  
  }  
  
  En caso de que no se pueda ubicar la nave carguera o el mensaje no pueda ser completado, el resultado será un código de respuesta 404
  
  ## Ejecución de aplicación desde IDE Intellij IDEA
  Como primer paso se deberá clonar el repositorio remoto a uno local.
  
  Abrimos el IDE intellij IDEA, luego nos dirigimos hacia File - Open
  
  Vamos haciae la carpeta del proyecto clonada en nuestra pc abrimos el archivo build.gradle con el examinador de archivos.
  Nos preguntará como queremos abrirlo y haremos clic en "As project" o "Como proyecto" y luego si en esta misma ventana o en una nueva.
  
  Una vez cargado el proyecto en el IDEA, desplegamos el arbol del proyecto (generalmente ubicado a la izquierda) y nos dirigimos a la siguiente ruta abriendo las carpetas del arbol src/main/java/ar.com.quasar/QuasarApplication, daremos click derecho sobre la clase "QuasarApplication", se desplegará un menú de opciones y luego  hacemos clic en "Run 'QuasarApplicat...main()'. 

  Por consola podemos verificar que la aplicación inició.
  
  Podemos mediante por ejemplo Postman, realizar las pruebas a los endpoints indicados.
  
  
