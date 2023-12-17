package tiktactoe.rendering;

public class RendererFactory {
    public Renderer buildRenderer(String type){
        switch (type) {
            case "console":
            case "Console":
                return new ConsoleRenderer();
            case "none" :
            case "None":
                return new NoneRenderer();
            default: return null;
        }
    }
}
