Versión de android studio Koala 2024.1.2.12

Para ingresar a la app es necesario registrarse primero y luego hacer el login.

¿Qué tipo de arquitectura usaron y por qué? Patrón mvvm, porque la estructura tiene componentes que separan la vista de la lógica de negocio ¿Podría mejorarla? si, podría aplicarse una arquitectura en capas al mvvm para tener bien separadas las capas de persistencia, negocio, presentación, aplicación

¿Tuvieron objetos stateful y stateless? Usamos stateful para los íconos del footer cuando se tocan cambien de color y para el darkmode, stateless para las imageviews, los títulos de cada sección, botones como en la screen del onboardin ¿Cómo definen la elección de los mismos? Para los stateless pensamos en objetos estáticos que no necesitan guardar ningún estado interno, mientras que para los stateful pensamos en funcionalidades que guarden un estado interno que cambia cuando se toca el objeto

¿Qué mejoras detectan que podrían realizarle a la app?¿ Tendrían side effect si escala el volumen de datos? Comenten al menos 2 cuestiones a refactorizar y tener en cuenta.

Actualmente lo estamos haciendo sin persistencia en la base de datos por lo tanto lo primero que refactorizaríamos sería las categorías de productos o la manera de crear los usuarios y guardarlos en la base de datos, también la manera de pagar la compra en el checkout para que verifique el medio de pago mediante un patrón estrategy y un state para los pedidos según estén pagados o no.

¿Qué manejo de errores harían? ¿dónde los contemplan a nivel código? Mencionen la estrategia de mappeo que más se adecúe.

Para el login, actualmente como no tenemos persistencia no pudimos agregarle lo que queríamos pero en caso de que el usuario ingrese las claves incorrectas hay que hacerle catch a esas claves incorrectas, lo mismo a la hora de agregar al carrito los productos o el checkout, si no hay stock no se puede agregar el producto al carrito ni se puede comprar, si el medio de pago no se puede corroborar la excepción indicaría que no se pudo procesar.

En el caso de uso de persistencia para Favoritos, ¿que estrategia sugieren?.

Podríamos hacer una relación de 1 a muchos entre usuarios y la tabla favoritos en la base de datos y un producto tiene n favoritos porque el mismo producto puede estar en varias lista de favoritos.

Si la tendríamos que convertir a Español y conservar el Inglés, qué estrategia utilizaría para extenderla. Y si necesitamos agregar otros idiomas?
Al igual que en el plugin de php moodle que estamos haciendo en proyecto final hay una carpeta dedicada exclusivamente a guardar un archivo por idioma con las constantes con los títulos en el idioma que se quiera y dependiendo como el usuario elija el idioma se presenta el título con esas constantes guardadas en ese idioma o en otro.
