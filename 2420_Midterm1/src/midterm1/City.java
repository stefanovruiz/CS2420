package midterm1;

public class City implements Comparable<City>{
	private final String name;
	private final int population;
	private final int area;
	private double density;
	
	public City(String name, int population, int area) {
		this.name = name;
		this.population = population;
		this.area = area;
	}
	
	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public int getArea() {
		return area;
	}
	
	public double getDensity(){
		density = (double) population/(double)area;
		return density;
	}

	@Override
	public String toString() {
		return String.format("%-15s density: %-8.1f",
			name, getDensity());
	}

	@Override
	public int compareTo(City o) {
		if(this.getDensity() < o.getDensity()) return -1;
		if(this.getDensity() == o.getDensity()) return 0;
		if(this.getDensity() > o.getDensity()) return 1;
		
		return 0;
		
	}
}
