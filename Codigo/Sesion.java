public class Sesion {
    private static boolean estadoSesion = false;

    public static boolean isEstadoSesion() {
        return estadoSesion;
    }

    public static void setEstadoSesion(boolean nuevoEstado) {
        estadoSesion = nuevoEstado;
    }
}