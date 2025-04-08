package StrategyDesignPattern;

public class CanNotFly implements FlyBehaviour{
    @Override
    public void fly() {
        System.out.println("i can not fly");
    }
}
