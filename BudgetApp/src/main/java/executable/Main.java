package executable;

import main.BudgetApp;

public class Main {

    public static void main(String[] args) throws Exception {
        BudgetApp budgetApp = new BudgetApp(args);
        budgetApp.start();
    }
}
