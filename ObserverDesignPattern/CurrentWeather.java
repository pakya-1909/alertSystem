package ObserverDesignPattern;

public class CurrentWeather implements Observer{

    public CurrentWeather(WeatherStation weatherStation){
        weatherStation.addObserver(this);
    }

    @Override
    public void updateObserver() {
        System.out.println("update the current data");
    }
}
