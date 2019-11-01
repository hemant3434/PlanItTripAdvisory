package krusty_krab.krusty_krab.domain;

import java.util.List;

public class Event extends ItineraryItem{

	private String location;
	private String activity;
	private int rating;
	private String image;
	private String description;
	private String id;
	
	public Event() {
	}
	
	public Event(String title, String location, String activity, int rating, float price, Time startTime, Time endTime, Time expectedLength, String image, String description, String id) {
		super("event", title, price, startTime, endTime, expectedLength);
		this.location = location;
		this.activity = activity;
		this.rating = rating;
		this.image = image;
		this.description = description;
		this.id = id;
	}

	// Computes a score for an event, based on its rating, price, wait time until it opens, and distance needed to travel
	public float getScore(Time curTime, String curLoc, GoogleMaps gm, float maxDist, float budget){
	    //Importance of each factor of an event quantified by a weight integer
		int ratingWeight = 3;
	    int waitTimeWeight = 5;
	    int maxDistWeight = 1;
	    int budgetWeight = 1;

	    //Upper bound of factors
	    int ratingMax = 5;
	    int waitTimeMax = 360;
	    float maxDistMax = maxDist;
	    float budgetMax = budget;

	    //gets transportation object, if user were to travel from their current location to this event
		Transportation transp = gm.getTransportation(curLoc, getLocation(), curTime);

		float rating, waitTime, dist, price;
        rating = this.getRating();

        //If the start time of the event is before the current time, then the user does not have to wait for the event to open
        if(this.getStartTime().toMinutes() - transp.getEndTime().toMinutes() < curTime.toMinutes()){
            waitTime = 0;
        }
        // otherwise, how long user needs to wait is computed
        else{
            waitTime = this.getStartTime().toMinutes() - curTime.toMinutes();
        }

        dist = transp.getDistance();
        price = this.getPrice();

        //Puts each factor on a scale between 0 and 1 (higher the better) by dividing by the upper bound, then multiplying by the weight
        return ratingWeight/ratingMax*rating + 1/waitTimeWeight/waitTimeMax*waitTime + 1/maxDistWeight/maxDistMax*dist + 1/budgetWeight/budgetMax*price;

    }

	public String getLocation() {
		return location;
	}

	public String getActivity() {
		return activity;
	}

	public int getRating() {
		return rating;
	}

	public String getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}
}
