from shiny import *
from shiny.types import ImgData
from typing import List
from shiny.types import NavSetArg
from htmltools import *
import asyncio
import pathlib
import pandas as pd


def nav_controls(prefix: str) -> List[NavSetArg]:
    return [
        ui.nav(ui.div("SKRM",
                      style='font-family: Gentium Basic; font-size: 35px; color: white;text-align: center; margin-top: 20%'),
               prefix + "",
               ui.div(ui.panel_title("SPRINTS COMPLETADOS"),
                      style='text-align: center;',
                      ),
               ui.div(
                   ui.input_slider("a", "", 1, 4, 2, step=1, width="70%", ticks=False),
                   style='margin-left: 20% ;',

               ),
               ui.row(
                   ui.column(4,

                             ui.a(
                                 ui.input_action_button("go", " TESTS CREADOS:    12%",
                                                        icon=ui.tags.i(class_="glyphicon glyphicon-thumbs-up",
                                                                       style="font-size:45px;"),
                                                        class_="btn-success",
                                                        width="150%",
                                                        style='border: 2px solid white;border-radius: 5px;background-color:'
                                                              '#77DD77;padding: 4px;margin-top:50px; margin-bottom: 20px;height:90px; color:black; font-size:35px;margin-left:30%;',
                                                        ),

                                 href="#tests",
                                 target="_blank",
                             ),

                             ),
                   ui.column(4,
                             ui.input_action_button("go", " FIN DEL SPRINT:    28/03/2023",
                                                    icon=ui.tags.i(class_="glyphicon glyphicon-calendar",
                                                                   style="font-size:45px;"),
                                                    class_="btn-success",
                                                    width="75%",
                                                    style='border: 2px solid white;border-radius: 5px;background-color:'
                                                          '#87CEFA;padding: 4px;margin-top:50px; margin-bottom: 20px;height:90px; color:black; font-size:23px; margin-left:90%;',
                                                    ),
                             ),

               ),

               ui.row(
                   ui.column(4,
                             ui.a(
                                 ui.input_action_button("go", "REQUISITOS CUMPLIDOS :    52%",
                                                        icon=ui.tags.i(class_="glyphicon glyphicon-list-alt",
                                                                       style="font-size:45px;"),
                                                        class_="btn-success",
                                                        width="150%",
                                                        style='border: 2px solid white;border-radius: 5px;background-color:'
                                                              '#AFEEEE;padding: 4px;margin-top:20px; margin-bottom: 25px;height:90px; color:black;font-size:35px;margin-left:30%'),
                                 href="https://skrm.shinyapps.io/testing/#Tests",
                                 target="_blank",
                             )
                             ),
                   ui.column(4,
                             ui.input_action_button("go", " FIN DEL PROYECTO:    23/03/2023",
                                                    icon=ui.tags.i(class_="glyphicon glyphicon-calendar",
                                                                   style="font-size:45px;"),
                                                    class_="btn-success",
                                                    width="75%",
                                                    style='border: 2px solid white;border-radius: 5px;background-color:'
                                                          '#87CEFA;padding: 4px;margin-top:20px; margin-bottom: 20px;height:90px; color:black; font-size:22px; margin-left:90%;',
                                                    ),
                             ),
               ),

               ui.div(ui.panel_title("RECIENTES"),
                      style='text-align: center;',
                      ),
               ui.output_table("result"),

               ui.div("© 2023 SKRM - Todos los derechos reservados",
                      style='background-color: #fff2f2; color: gray; border-top: 2px solid gray; '
                            'text-align: center; margin-top:10%; font-size: 15px; margin-left: 4%; bottom: 0; '
                            'margin-right: 14%; width: 90%;',
                      )

               ),
        ui.nav(ui.div("Estadisticas", style='font-size: 20px; color: white;text-align: center; margin-top: 28%'),
               prefix + "",
               ui.div(ui.panel_title("SPRINTS COMPLETADOS"),
                      style='text-align: center;',
                      ),
               ui.div(
                   ui.input_slider("a", "", 1, 4, 2, step=1, width="70%", ticks=False),
                   style='margin-left: 20% ;',

               ),
               ui.row(
                   ui.column(4,

                             ui.a(
                                 ui.input_action_button("go", " TESTS CREADOS:    12%",
                                                        icon=ui.tags.i(class_="glyphicon glyphicon-thumbs-up",
                                                                       style="font-size:45px;"),
                                                        class_="btn-success",
                                                        width="150%",
                                                        style='border: 2px solid white;border-radius: 5px;background-color:'
                                                              '#77DD77;padding: 4px;margin-top:50px; margin-bottom: 20px;height:90px; color:black; font-size:35px;margin-left:30%',
                                                        ),

                                 href="#tests",
                                 target="_blank",
                             ),

                             ),
                   ui.column(4,
                             ui.input_action_button("go", " FIN DEL SPRINT:    28/03/2023",
                                                    icon=ui.tags.i(class_="glyphicon glyphicon-calendar",
                                                                   style="font-size:45px;"),
                                                    class_="btn-success",
                                                    width="75%",
                                                    style='border: 2px solid white;border-radius: 5px;background-color:'
                                                          '#87CEFA;padding: 4px;margin-top:50px; margin-bottom: 20px;height:90px; color:black; font-size:23px; margin-left:90%;',
                                                    ),
                             ),

               ),

               ui.row(
                   ui.column(4,
                             ui.a(
                                 ui.input_action_button("go", "REQUISITOS CUMPLIDOS :    52%",
                                                        icon=ui.tags.i(class_="glyphicon glyphicon-list-alt",
                                                                       style="font-size:45px;"),
                                                        class_="btn-success",
                                                        width="150%",
                                                        style='border: 2px solid white;border-radius: 5px;background-color:'
                                                              '#AFEEEE;padding: 4px;margin-top:20px; margin-bottom: 25px;height:90px; color:black;font-size:35px;margin-left:30%'),
                                 href="https://skrm.shinyapps.io/testing/#Tests",
                                 target="_blank",
                             )
                             ),
                   ui.column(4,
                             ui.input_action_button("go", " FIN DEL PROYECTO:    23/03/2023",
                                                    icon=ui.tags.i(class_="glyphicon glyphicon-calendar",
                                                                   style="font-size:45px;"),
                                                    class_="btn-success",
                                                    width="75%",
                                                    style='border: 2px solid white;border-radius: 5px;background-color:'
                                                          '#87CEFA;padding: 4px;margin-top:20px; margin-bottom: 20px;height:90px; color:black; font-size:22px; margin-left:90%;',
                                                    ),
                             ),
               ),

               ui.div(ui.panel_title("RECIENTES"),
                      style='text-align: center;',
                      ),
               #ui.output_table("result"),

               ui.div("© 2023 SKRM - Todos los derechos reservados",
                      style='background-color: #fff2f2; color: gray; border-top: 2px solid gray; '
                            'text-align: center; margin-top:30%; font-size: 15px; margin-left: 4%; bottom: 0; '
                            'margin-right: 14%; width: 90%;',
                      ),

               ),

        ui.nav(ui.div("Auditorías", style='font-size: 20px; color: white;text-align: center; margin-top: 32%'),
               prefix + "",
               ui.row(
                   ui.column(4,
                             ui.panel_title("AUDITORÍAS"),
                             style='margin-bottom:20px;',

                             )
               ),
               ui.navset_pill_list(*aud_controls("")),
               ui.div('© 2023 SKRM - Todos los derechos reservados',
                      style='background-color: #fff2f2; color: gray; border-top: 2px solid gray; '
                            'text-align: center; margin-top:30%; font-size: 15px; margin-left: 4%; bottom: 0; '
                            'margin-right: 14%; width: 90%;',
                      )
               ),
        ui.nav(ui.div("Requisitos", style='font-size: 20px; color: white;text-align: center; margin-top: 32%'),
               prefix + "",
               ui.panel_title("REQUISITOS GENERALES"),
               p("En esta sección, mostramos los requisitos que debe cumplir el sistema para asegurar su correcto "
                 "funcionamiento. "
                 "Estos requisitos los hemos obtenido a base de nuestra interpretación del funcionamiento del sistema. "
                 "Gracias a estos requisitos podemos comprobar a qué se debe el error del sistema si en un futuro "
                 "fallase."),
               ui.tags.style(
                   """
                   .app-col1 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #FFFAF0;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col2 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #98FB98;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col3 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #7FFFD4;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col4 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #F0FFFF;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col5 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #BDB76B;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col6 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #FFA07A;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col7 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #00BFFF;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),
               ui.tags.style(
                   """
                   .app-col8 {
                       border: 2px solid black;
                       border-radius: 5px;
                       background-color: #FFD700;
                       padding: 8px;
                       margin-top: 5px;
                       margin-bottom: 5px;
                       width:75%;
                   }
                   """
               ),

               ui.row(
                   ui.column(4,
                             ui.panel_title(title="CIUDADANO"),
                             ui.div(
                                 {"class": "app-col1"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Completar el formulario online"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Capacidad de visualizacion del GIS y seleccion de la zona deseada"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Poder solicitar ayudas para la instalacion de placas fotovoltaicas en una region"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Solo ciudadanos registrados pueden acceder al portal para realizar las solicitudes"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Subida de los documentos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="TRAMITADOR"),
                             ui.div(
                                 {"class": "app-col2"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Procesar los documentos para que el ciudadano pueda solicitar fondos para la instalación de placas fotovoltaicas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Poder visualizar los documentos asociados a una solicitud entregada por un ciudadano"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Aparición de cada una de las fases de la petición"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "En caso de denegación indicar la causa"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "En caso de validarse, proceso automático que cambie el estado"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="SUPERVISOR"),
                             ui.div(
                                 {"class": "app-col3"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Con la revisión de la petición del ciudadano, presentar una notificación y comprobar la cantidad de ayudas que recibirá el ciudadano"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Restar las ayudas aprobadas de una cuenta general"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "El supervisor llevará un registro de los ciudadanos que reciben ayudas y así podrá dar por cerrada su participación"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             )
                             )
               ),
               ui.row(
                   ui.column(4, ui.panel_title(title="OCR"),
                             ui.div(
                                 {"class": "app-col4"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Correcta lectura y almacenaje de documentos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Comprobación de una extensión válida de los documentos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Escritura del tipo de documento leído"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),

                             ),
                             ),
                   ui.column(4, ui.panel_title(title="CALCULADORA"),
                             ui.div(
                                 {"class": "app-col5"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Crear un GIS funcional"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Calcular el porcentaje de superficie que puede ser susceptible de tener terrenos habilitados para placas fotovoltaicas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "El GIS debe ser capaz de visualizar un municipio en vista aérea y se podrá diferenciar las zonas residenciales y agrarias"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Generar presupuesto ayudas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             )

                             ),
                   ui.column(4, ui.panel_title(title="CONTROLADOR"),
                             ui.div(
                                 {"class": "app-col6"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Conectar todos los módulos con la Base de Datos correctamente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Asegurar que solo ellos tienen acceso a la Base de Datos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),

                             )
                             )
               ),
               ui.row(
                   ui.column(4, ui.panel_title(title="ADMINISTRADOR"),
                             ui.div(
                                 {"class": "app-col7"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Creación del LOGIN"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Creación de una interfaz en la que se puede visualizar el estado de las solicitudes"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Crear cuenta"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Debe poder controlar el flujo de los procesos y comprobar que es consistent"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "El administrador no podrá tener acceso a los datos del proceso"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="BASES DE DATOS"),
                             ui.div(
                                 {"class": "app-col8"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Crear una base funcional"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Evitar SQL-Injection u otros posibles ataques"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   style='margin-bottom: 30px',

               ),
               ui.div("© 2023 SKRM - Todos los derechos reservados",
                      style='background-color: #fff2f2; color: gray; border-top: 2px solid gray; '
                            'text-align: center; margin-top:10%; font-size: 15px; margin-left: 4%; bottom: 0; '
                            'margin-right: 14%; width: 90%;',
                      id="footer_id"
                      ),

               ),
        ui.nav(ui.div("Tests", style='font-size: 20px; color: white;text-align: center; margin-top: 68%'),
               prefix + "",

               ui.panel_title("TESTs E2E"),

               p("En esta sección, vamos a incluir los test e2e necesarios para cada equipo del proyecto. Para "
                 "verificar la viabilidad y certeza del proyecto se deben cumplir estos tests.", id="tests"),

               ui.row(
                   ui.column(4,
                             ui.panel_title(title="CIUDADANO"),
                             ui.div(
                                 {"class": "app-col1"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Haber completado el formulario online"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Subida de los documentos correcta"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Registrarse en el portal de tramitaciones"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Puede solicitar ayudas al municipio de la Comunidad de Madrid que le interese"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Puede visualizar el GIS y seleccionar las zonas que quiera solicitar"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Señalar cualquier tipo de error al hacer el registro"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="TRAMITADOR"),
                             ui.div(
                                 {"class": "app-col2"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Capaz de analizar y visualizar todos los documentos asociados a una solicitud entregada por el ciudadano"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Puede validar la documentación y cambio de estado automáticamente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "En caso de denegar la documentación, hay una causa justificada"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "La documentación rechazada no avanza de fase"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Cuando la documentación es validada, a su vez es transferida al supervisor"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Los documentos están en la fase 2 o 4 del proceso"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="SUPERVISOR"),
                             ui.div(
                                 {"class": "app-col3"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Existe un registro de los ciudadanos que reciben ayudas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Se descuentan las ayudas autorizadas por el supervisor de los fondos europeos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Existe la capacidad de finalizar el proceso de una solicitud aceptándola o denegándola"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Puede conceder las ayudas y emitir los fondos una vez comprobadas las solicitudes"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Envia notificación al ciudadano con la causa en caso de la denegación"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             )
                             )
               ),
               ui.row(
                   ui.column(4, ui.panel_title(title="OCR"),
                             ui.div(
                                 {"class": "app-col4"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Los documentos de la solicitud son documentos reales"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Los documentos están correctamente almacenados y su lectura es legible"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Formato de los documentos es electrónico y sin ninguna extensión en concreto"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="CALCULADORA"),
                             ui.div(
                                 {"class": "app-col5"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Existe un GIS que es funcional"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Ofrece un presupuesto por metro cuadrado para la instalación de placasfotovoltaicas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "El GIS es capaz de visualizar un municipio en vista aérea y puede diferenciar las zonas residenciales y agrarias"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "El GIS es capaz de mostrar al ciudadano las zonas disponibles para la instalación de placas"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   ui.column(4, ui.panel_title(title="CONTROLADOR"),
                             ui.div(
                                 {"class": "app-col6"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "La base de datos funciona correctamente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "La base de datos está actualizada"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Solo ellos pueden acceder los diseñadores de la base de datos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Se conectan todos los módulos con la BBDD"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             )
                             ),
               ),
               ui.row(
                   ui.column(4, ui.panel_title(title="ADMINISTRADOR"),
                             ui.div(
                                 {"class": "app-col7"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Campos implementados correctamente (Nombre o usuario, contraseña,email,etc)"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Existe capacidad necesaria para la escritura"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Diseño en cascada de los campos e indicados correctamente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "La contraseña debe estar en oculto"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Errores al completar los campos indicados al usuario correctamente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Recuperación de contraseña"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Ayudas complementarias para la facilidad del usuario"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-ok", style="color: #34a247;"),
                                               "Implementación de opciones como log in, registro, log out ,etc"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Seguridad a la hora de crear los datos. (Captcha, tiempo de sesión ,autenticación,etc)"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "No tiene acceso a los datos del proceso"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),

                             ),
                             ),
                   ui.column(4, ui.panel_title(title="BASES DE DATOS"),
                             ui.div(
                                 {"class": "app-col8"},
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Es segura para evitar errores que desvirtúen la base de datos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Tener en cuenta la Protección de los Datos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Actualización periódica de la base de datos"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Se adapta a las necesidades y características del cliente"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Evita SQL-Injection"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Diseño correcto de las entidades, relaciones, aridades,etc"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                                 ui.row(
                                     ui.column(10,
                                               ui.tags.i(class_="glyphicon glyphicon-remove", style="color: #ec1818;"),
                                               "Parcialización de los datos y no acumular datos irrelevantes"),
                                     style='margin-top: 3px; margin-bottom: 3px;'
                                 ),
                             ),
                             ),
                   style='margin-bottom: 30px',
               ),
               ui.div("© 2023 SKRM - Todos los derechos reservados",
                      style='background-color: #fff2f2; color: gray; border-top: 2px solid gray; '
                            'text-align: center; margin-top:10%; font-size: 15px; margin-left: 4%; bottom: 0; '
                            'margin-right: 14%; width: 90%;',
                      id="footer_id"
                      ),

               ),
        ui.nav_spacer(),
        ui.nav_menu(
            ui.div("Otros links", style='font-size: 20px; color: white;text-align: center; margin-top: 28%'),
            prefix + "",
            ui.nav_control(
                ui.a(
                    "Quality-assurance",
                    href="https://ismigit.fi.upm.es",
                    target="_blank",
                )
            ),
            "----",
            ui.nav_control(
                ui.a(
                    "Shiny for Python",
                    href="https://shiny.rstudio.com/py",
                    target="_blank",
                )
            ),
            align="right",

        ),
    ]


def aud_controls(prefix: str) -> List[NavSetArg]:
    return [
        ui.nav("PRIMER SPRINT",
               ui_card(
                   "Descarga un pdf sobre 1er sprint",
                   ui.download_button("download1", "Auditoria_Sprint1.pdf"),
               ),
               ),
        ui.nav("SEGUNDO SPRINT",
               ui_card(
                   "Descarga un pdf sobre 2do sprint",
                   ui.download_button("downloadData", "Auditoria_Sprint2.pdf"),
               ),

               ),
        ui.nav("TERCER SPRINT",
               ),
        ui.nav("CUARTO SPRINT",
               ),

    ]


def ui_card(title, *args):
    return [
        ui.div(
            {"class": "card mb-4"},
            ui.div(title, class_="card-header"),
            ui.div({"class": "card-body"}, *args),
            # style='width:70%;'
        ),
    ]


dir = pathlib.Path(__file__).parent
mtcars = pd.read_csv(dir / "mtcars.csv")

app_ui = ui.page_fluid(
    ui.page_navbar(

        *nav_controls(""),

        title=ui.a(
            ui.output_image("image", height="50px", inline=True),
            href="https://skrm.shinyapps.io/testing/",
            target="_blank",
        ),

        bg="#204FA1",
        inverse=True,
        window_title="SKRM",
        fluid=True,
        id="navbar_id",

    ),
    style='border-radius: 5px; background-color: #fff2f2; padding: 2%',
)


def server(input: Inputs, output: Outputs, session: Session):
    @output
    @render.image
    def image():
        from pathlib import Path

        dir_path = Path(__file__).resolve().parent
        img: ImgData = {"src": str(dir_path / "Logocolor.png"), "width": "100px"}
        return img

    @render.image
    def icon():
        from pathlib import Path

        fav_path = Path(__file__).resolve().parent
        fav: ImgData = {"src": str(fav_path / "favicon.png"), "width": "32px"}
        return fav

    @reactive.Effect
    def _():
        print("Current navbar page: ", input.navbar_id())

    @session.download()
    def download1():
        from pathlib import Path

        path = Path(__file__).parent / "Comprobaciones_Requisitos_Primer_Sprint.pdf"
        return str(path)

    @session.download(
        filename=lambda: f"Comprobaciones_Requisitos_Segundo_Sprint.pdf"
    )
    async def downloadData():
        await asyncio.sleep(0.25)

    @output
    @render.table
    def result():
        return (
            mtcars.style.set_table_attributes(
                'class="dataframe shiny-table table " style="width:100%; font-size:30px; margin: 0 auto; margin-top:3%;"'
            )
            .hide(axis="index")
            .format(
                {
                    "mpg": "{0:0.1f}",
                    "disp": "{0:0.1f}",
                    "drat": "{0:0.2f}",
                    "wt": "{0:0.3f}",
                    "qsec": "{0:0.2f}",
                }
            )
            .set_table_styles(
                [dict(selector="th", props=[("text-align", "left")])],
            )
        )


app = App(app_ui, server)
