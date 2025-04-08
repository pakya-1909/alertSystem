package ObserverDesignPattern;

public class ForecastWeather implements Observer{

    public ForecastWeather(WeatherStation weatherStation){
        weatherStation.addObserver(this);
    }

    @Override
    public void updateObserver() {
        System.out.println("update the forecast data");
    }
}
