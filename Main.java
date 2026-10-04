public class Main {
    private static int passed = 0;
    private static int total = 0;

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("--demo")) {
            System.out.println("Run: java -cp out Main --demo");
            return;
        }

        Circle circle = new Circle("circle-1", 2, new VectorRenderer());
        check("T1", "Circle + VectorRenderer", circle.execute(),
                "VECTOR circle radius=2");
        circle.setImplementation(new RasterRenderer());
        check("T2", "Circle + RasterRenderer", circle.execute(),
                "RASTER circle radius=2");

        Square square = new Square("square-1", 3, new VectorRenderer());
        check("T3", "Square + VectorRenderer", square.execute(),
                "VECTOR square side=3");
        square.setImplementation(new RasterRenderer());
        check("T4", "Square + RasterRenderer", square.execute(),
                "RASTER square side=3");

        checkSwitch();

        circle.setImplementation(new AsciiRenderer());
        check("T6", "Circle + AsciiRenderer", circle.execute(),
                "ASCII circle radius=2 (o)");
        square.setImplementation(new AsciiRenderer());
        check("T7", "Square + AsciiRenderer", square.execute(),
                "ASCII square side=3 [+]");

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static void checkSwitch() {
        Circle circle = new Circle("circle-switch", 2, new VectorRenderer());
        Circle original = circle;
        String idBefore = circle.getId();
        int radiusBefore = circle.getRadius();
        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());
        String after = circle.execute();

        boolean sameObject = original == circle;
        boolean idUnchanged = idBefore.equals(circle.getId());
        boolean radiusUnchanged = radiusBefore == circle.getRadius();
        String actual = "sameObject=" + sameObject
                + " | idUnchanged=" + idUnchanged
                + " | radiusUnchanged=" + radiusUnchanged
                + "\n  before=" + before + " | after=" + after;
        String expected = "sameObject=true | idUnchanged=true | radiusUnchanged=true"
                + "\n  before=VECTOR circle radius=2 | after=RASTER circle radius=2";
        check("T5", "Circle + VectorRenderer -> RasterRenderer", actual, expected);
    }

    private static void check(String id, String classes, String actual, String expected) {
        total++;
        boolean success = expected.equals(actual);
        if (success) {
            passed++;
        }
        String status = success ? "PASS" : "FAIL";
        System.out.println(id + " " + status + " | " + classes + " | result=" + actual);
        if (!success) {
            System.out.println("  expected=" + expected);
        }
    }
}
