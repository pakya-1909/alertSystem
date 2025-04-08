package ObserverDesignPattern;

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Observer observer1 = new ForecastWeather(weatherStation);
        Observer observer2 = new CurrentWeather(weatherStation);

        weatherStation.addObserver(observer1);
        weatherStation.addObserver(observer2);

        weatherStation.changeWeather();
    }
}
