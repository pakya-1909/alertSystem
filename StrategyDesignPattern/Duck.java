package StrategyDesignPattern;

public abstract class Duck {

    private final FlyBehaviour flyBehaviour;

    public Duck(FlyBehaviour flyBehaviour){
        this.flyBehaviour = flyBehaviour;
    }

    public void fly(){
        flyBehaviour.fly();
    }
}
