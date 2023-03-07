package travelling_salesman;

public class Neighbour implements Comparable<Neighbour> {
    City city;
    int distance;
    public Neighbour() {
        this.distance = 0;
        this.city = null;
    }

    public Neighbour(City city, int distance) {
        this.distance = distance;
        this.city = city;
    }

    @Override
    public int compareTo(Neighbour o) {
        if (o.getDistance() < this.getDistance()) {
            return  1;
        } else if(o.getDistance() == this.getDistance()){
            return 0;
        }else{
            return  -1;
        }
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
