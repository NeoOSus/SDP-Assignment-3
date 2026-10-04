public class RasterRenderer implements Renderer {
    @Override
    public String drawCircle(int radius) {
        return "RASTER circle radius=" + radius;
    }

    @Override
    public String drawSquare(int side) {
        return "RASTER square side=" + side;
    }
}
