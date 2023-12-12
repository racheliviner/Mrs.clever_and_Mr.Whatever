public class RendererFactory {
    public Renderer buildRenderer(String type){
        switch (type) {
            case "console":
                return new ConsoleRenderer();
            default: return null;
        }
    }
}
