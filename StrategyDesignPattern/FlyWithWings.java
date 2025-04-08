package StrategyDesignPattern;

public class FlyWithWings implements FlyBehaviour{
    @Override
    public void fly() {
        System.out.println("i can fly with wings");
    }
}
