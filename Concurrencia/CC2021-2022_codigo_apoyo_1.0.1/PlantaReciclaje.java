package cc.controlReciclado;

public class PlantaReciclaje {
  private static final int MAX_GRUAS = 2;
  private static final int MIN_P_GRUA = 20;
  private static final int MAX_P_GRUA = 80;
  private static final int MAX_P_CONTENEDOR = 100;

  public static void main(String[] args) {
    ApiContenedor contenedor =
      new ApiContenedor(MAX_P_CONTENEDOR);
    ApiGruas gruas =
      new ApiGruas(contenedor, MAX_GRUAS, MIN_P_GRUA, MAX_P_GRUA);

    ControlReciclado controlReciclado =
      new ControlRecicladoMonitor(MAX_P_CONTENEDOR, MAX_P_GRUA);

    new ControladorContenedor(contenedor, controlReciclado).start();

    for (int idGrua = 0; idGrua < MAX_GRUAS; idGrua++) {
      new ControladorGrua(idGrua, gruas, controlReciclado).start();
    }
  }
}
