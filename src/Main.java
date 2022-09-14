public class Main {

    static public void main(String[] args) {
        Abstraction abstraction = new Abstraction();
        Presentation presentation = new Presentation();
        Controller controller = new Controller(abstraction, presentation);
    }
}
