package StrategyDesignPattern;

/*

 */

public class StrategyDesignPattern {
    public static void main(String[] args) {
        FlyBehaviour flyWithWings = new FlyWithWings();
        FlyBehaviour canNotFly = new CanNotFly();

        Duck duck1 = new FlyDuck("pankaj-1", 1,flyWithWings);
        Duck duck2 = new FlyDuck("pankaj-2", 2,canNotFly);

        duck1.fly();
        duck2.fly();
    }
}
