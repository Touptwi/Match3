public class Controller {

    private Abstraction abstraction;
    private Presentation presentation;

    public Controller() {
        this.abstraction = new Abstraction(this);
        this.presentation = new Presentation(this);

    }

    public Abstraction getAbstraction() {return this.abstraction;}
    public Presentation getPresentation() {return this.presentation;}
}
