package cuisine;

public class Step {
    private String description;

    public Step(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void displayStep() {
        System.out.println(description);
    }

    public static void main(String[] args) {
        Step step1 = new Step("Préchauffez le four à 180°C.");
        Step step2 = new Step("Mélangez les ingrédients secs dans un bol.");
        Step step3 = new Step("Incorporez les ingrédients liquides au mélange sec.");
        Step step4 = new Step("Mettre au four pendant 1h et laissé reposé à l'air libre 10m");


        step1.displayStep();
        step2.displayStep();
        step3.displayStep();
        step4.displayStep();
    }
}
