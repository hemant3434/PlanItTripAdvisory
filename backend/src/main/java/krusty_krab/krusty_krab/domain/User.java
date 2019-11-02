package krusty_krab.krusty_krab.domain;

import krusty_krab.krusty_krab.controller.MainController;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NodeEntity
public class User {

    @GraphId
    private Long id;
    private String username;
    private List<String> visitedEvents = new ArrayList();
    private List<Float> eventRatings = new ArrayList();
    private List<ItineraryItem> itin = new ArrayList<ItineraryItem>();

    public User() {}

    public User(String username, List<String> visitedEvents, List<Float> eventRatings, List<ItineraryItem> itin) {
        this.username = username;
        this.visitedEvents = visitedEvents;
        this.eventRatings = eventRatings;
        this.itin = itin;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getVisitedEvents() {
        return visitedEvents;
    }

    public List<ItineraryItem> getItin() {
        return itin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setVisitedEvents(List<String> visitedEvents) {
        this.visitedEvents = visitedEvents;
    }

    public void setItin(List<ItineraryItem> itin) {
        this.itin = itin;
    }

    public List<Float> getEventRatings() {
        return eventRatings;
    }

    public void setEventRatings(List<Float> eventRatings) {
        this.eventRatings = eventRatings;
    }
}
