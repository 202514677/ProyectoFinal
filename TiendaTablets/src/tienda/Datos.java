package tienda;

public class Datos {
    
	// Tablet 1
    public static String modelo0 = "Samsung Galaxy Tab S9 Ultra";
    public static double precio0 = 1199.0;
    public static double ancho0 = 208.6;
    public static double alto0 = 326.4;
    public static double fondo0 = 5.5;

    // Tablet 2
    public static String modelo1 = "Apple iPad Pro 12.9\" (M2)";
    public static double precio1 = 1099.0;
    public static double ancho1 = 214.9;
    public static double alto1 = 280.6;
    public static double fondo1 = 6.4;

    // Tablet 3
    public static String modelo2 = "Xiaomi Pad 6 Pro";
    public static double precio2 = 599.0;
    public static double ancho2 = 165.2;
    public static double alto2 = 254.0;
    public static double fondo2 = 6.5;

    // Tablet 4
    public static String modelo3 = "Huawei MatePad Pro 13.2\"";
    public static double precio3 = 999.0;
    public static double ancho3 = 196.1;
    public static double alto3 = 290.4;
    public static double fondo3 = 5.5;

    // Tablet 5
    public static String modelo4 = "Lenovo Tab P12 Pro";
    public static double precio4 = 899.0;
    public static double ancho4 = 183.2;
    public static double alto4 = 285.6;
    public static double fondo4 = 5.6;

    // --- Configuración de Descuentos ---
    public static double porcentaje1 = 7.5;
    public static double porcentaje2 = 10.0;
    public static double porcentaje3 = 12.5;
    public static double porcentaje4 = 15.0;

    // --- Configuración de Obsequios ---
    public static String obsequio1 = "Lapicero";
    public static String obsequio2 = "Set de vasos de vidrio";
    public static String obsequio3 = "Tablet Samsung Galaxy M";

    // Ventas (acumuladores) y cuota
    public static int contadorVentas = 0;
    public static double importeTotalAcumulado = 0.0;
    public static double cuotaDiaria = 25000.0; 
   
    public static String modelo(int idx) {
        return switch (idx) {
            case 0 -> modelo0;
            case 1 -> modelo1;
            case 2 -> modelo2;
            case 3 -> modelo3;
            case 4 -> modelo4;
            default -> "";
        };
    }
    public static double precio(int idx) {
        return switch (idx) {
            case 0 -> precio0;
            case 1 -> precio1;
            case 2 -> precio2;
            case 3 -> precio3;
            case 4 -> precio4;
            default -> 0.0;
        };
    }
    public static double ancho(int idx) {
        return switch (idx) {
            case 0 -> ancho0;
            case 1 -> ancho1;
            case 2 -> ancho2;
            case 3 -> ancho3;
            case 4 -> ancho4;
            default -> 0.0;
        };
    }
    public static double alto(int idx) {
        return switch (idx) {
            case 0 -> alto0;
            case 1 -> alto1;
            case 2 -> alto2;
            case 3 -> alto3;
            case 4 -> alto4;
            default -> 0.0;
        };
    }
    public static double fondo(int idx) {
        return switch (idx) {
            case 0 -> fondo0;
            case 1 -> fondo1;
            case 2 -> fondo2;
            case 3 -> fondo3;
            case 4 -> fondo4;
            default -> 0.0;
        };
    }
}
