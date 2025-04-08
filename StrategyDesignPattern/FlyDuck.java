package StrategyDesignPattern;

public class FlyDuck extends Duck{
    private String name;
    private Integer id;
    private FlyBehaviour flyBehaviour;

    public FlyDuck(String name, Integer id, FlyBehaviour flyBehaviour){
        super(flyBehaviour);
        this.id = id;
        this.name = name;
    }
}
