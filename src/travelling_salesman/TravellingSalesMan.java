package travelling_salesman;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;


public class TravellingSalesMan {
    City[] cities;
    Vector<City> route;

    public TravellingSalesMan() {
        this.route = new Vector<>();
    }

    /**
     * 
     * @param cities
     * @param startingPoint
     */
    public Route solve(Vector<City> cities, int startingPoint) {
        Vector<Route> routes = new Vector<>();
        Route bestRoute;
        //calculate shortest route using each city as starting point
        for (int i = 0; i < cities.size(); i++) {
            routes.add(getShortestRoute(cities, i));
        }
        Collections.sort(routes);
        System.out.println(routes.get(0).getDistance());
        bestRoute = routes.get(0);
        bestRoute.setStart(startingPoint);
        return bestRoute;
    }

    /**
     * 
     * @param cities
     * @param startingPoint
     */
    public Route getShortestRoute(Vector<City> cities, int startingPoint) {
        City currentCity;
        Hashtable<String, City> visited = new Hashtable<>();
        Route routetravelled = new Route();
        // this.route = 0;
        currentCity = cities.get(startingPoint);
        visited.put(currentCity.getName(), currentCity);
        routetravelled.getRoute().add(currentCity);

        // loop through cities to find nearest unvisited neighbour of each
        for (int i = 0; i < cities.size(); i++) {
            currentCity.setVisited(true);
            int neighbourCount = currentCity.getNeighbours().size();

            for (int j = 0; j < neighbourCount; j++) {
              
                if (!visited.containsKey(currentCity.getNeighbours().get(j).getCity().getName())) {
                  
                    routetravelled.setDistance(
                            routetravelled.getDistance() + currentCity.getNeighbours().get(j).getDistance());
                    currentCity = currentCity.getNeighbours().get(j).getCity();
                    visited.put(currentCity.getName(), currentCity);
                    routetravelled.getRoute().add(currentCity);
                    
                    break;
                }
            }
        }

        routetravelled.close();
     
        return routetravelled;
    }
}
