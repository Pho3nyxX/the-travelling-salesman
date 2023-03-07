
package travelling_salesman;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;

/**
 * City - represents a city on the map 
 */
public class City {
    int id; //unique identifier
    String name;
    String country;
    double longitude;
    double latitude;
    Vector<Neighbour> neighbours; // stores a list of neighbours for each city. Neighbours are city objects
    boolean visited = false;
    public static Vector<City> cities;

    /**
     * contructs a empty city
     */
    public City() {
        this.name = "";
        this.id = 0;
        this.neighbours = new Vector<>();
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.country = "";
    }

    /**
     * Contructs a city given the basic info
     * @param name
     * @param id
     * @param neighbours
     * @param longitude
     * @param latitude
     * @param country
     */
    public City(String name, int id, Vector<Neighbour> neighbours, double longitude, double latitude, String country) {
        this.name = name;
        this.id = id;
        if (neighbours != null) {
            this.neighbours = neighbours;
        } else {
            this.neighbours = new Vector<>();
        }
        this.longitude = longitude;
        this.latitude = latitude;
        this.country = country;
    }

    //loads cities from file. eliminates data entry in app
    public static void loadCities(String mode) {
        //Default file name for four default towns
        String filename ="cities.json";
        //change filename if mode is not default 
        if(mode == "Full (Jamaica)" ){
            filename = "cities-all.json";//all Jamaican cities
        }else if(mode == "Full (US)" ){
            filename = "cities-us.json"; //all US cities
        }

        try {
            // Object ob;
            Vector<City> cities = new Vector<>();
            JSONArray ar;
            FileReader reader;
            reader = new FileReader(filename);
            ar = (JSONArray) JSONValue.parse(reader);

            // JSONArray cities = ar;
            //load the cities from the json file without 
            for (int i = 0; i < ar.size(); i++) {
                JSONObject ob = (JSONObject) ar.get(i);
                //create a city to hold the value found in the file
                City city = new City();
                
                // load city values
                city.setName((String) ob.get("name"));
                city.setId(Integer.parseInt(ob.get("id").toString()));
                city.setLongitude(Double.parseDouble(ob.get("longitude").toString()));
                city.setLatitude(Double.parseDouble(ob.get("latitude").toString()));
                city.setCountry((String) ob.get("country"));
                
                // add loaded city to list
                cities.add(city);
                
            }

            //load neighbours
            for (int i = 0; i < ar.size(); i++) {
                JSONObject ob = (JSONObject) ar.get(i);
                if(ob.get("neighbours") != null){
                    JSONArray neighbours = (JSONArray) ob.get("neighbours");
                    for (int j = 0; j < neighbours.size(); j++) {
                        JSONObject neighb = (JSONObject) neighbours.get(j);
                        int index = Integer.parseInt(neighb.get("city").toString());
                        int distance = Integer.parseInt(neighb.get("distance").toString());
                        
                        Neighbour newNeighbour = new Neighbour(cities.get(index), distance);
                        cities.get(i).getNeighbours().add(newNeighbour);
                    }
                }
            }
            City.cities = cities;
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<Neighbour> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Vector<Neighbour> neighbours) {
        this.neighbours = neighbours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addNeighbour(Neighbour neighbour) {
        this.neighbours.add(neighbour);
    }

    public void addNeighbour(City city, int distance) {
        this.neighbours.add(new Neighbour(city, distance));
    }

    public void sortNeighbours() {
        Collections.sort(this.neighbours);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "City [country=" + country + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude
                + ", name=" + name +"]" ;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
