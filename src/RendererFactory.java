public class RendererFactory {
    public Renderer buildRenderer(String type){
        return new ConsoleRenderer();
    }
}
