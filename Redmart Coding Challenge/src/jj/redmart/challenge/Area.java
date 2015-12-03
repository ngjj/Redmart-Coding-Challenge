package jj.redmart.challenge;

public class Area {

  private int elevation;
  private boolean visited;



  public Area(int elevation) {
    this.elevation = elevation;
    this.visited = false;
  }

  public int getElevation() {
    return elevation;
  }

  public void setElevation(int elevation) {
    this.elevation = elevation;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

}
