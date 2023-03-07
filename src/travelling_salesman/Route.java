package travelling_salesman;

import java.util.Vector;
import java.lang.Comparable;

public class Route implements Comparable<Route>{
    Vector<City> route;
    int distance;

    public Route() {
        this.route = new Vector<>();
        this.distance = 0;
    }

    public Route(Vector<City> route, int distance) {
        this.route = route;
        this.distance = distance;
    }

    public Vector<City> getRoute() {
        return route;
    }


    @Override
    public int compareTo(Route o) {
        if (o.getDistance() < this.getDistance()) {
            return  1;
        } else if(o.getDistance() == this.getDistance()){
            return 0;
        }else{
            return  -1;
        }
    }


    public void setRoute(Vector<City> route) {
        this.route = route;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public void setStart(int start){
        if (start < 0){
            start = 0;
        }
        Vector<City> routeBuffer = new Vector<>();
        for (int i = start; i < route.size(); i++) {
            routeBuffer.add(this.route.get(i));
        }
        for (int i = 0; i < start; i++) {
            routeBuffer.add(this.route.get(i));
        }
        this.route = routeBuffer;
    }

    public void close(){
        Vector<Neighbour> finalCityNeighbours = route.get(route.size()-1).getNeighbours();
        City startCity = route.get(0);
        for (int i = 0; i < finalCityNeighbours.size(); i++) {
            if(startCity == finalCityNeighbours.get(i).getCity()){
                this.distance += finalCityNeighbours.get(i).getDistance(); 
                break;
            }
        }
    }; 
    
}
