
package br.com.unitins.oquefazer.googlePlace;

import java.util.ArrayList;
import java.util.List;


public class Result {

    private Geometry geometry;
    private String icon;
    private String id;
    private String name;
    private String placeId;
    private String reference;
    private String scope;
    private List<String> types = new ArrayList<String>();
    private String vicinity;
    private OpeningHours openingHours;
    private List<Photo> photos = new ArrayList<Photo>();
    private Double rating;


    public Geometry getGeometry() {
        return geometry;
    }


    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }


    public String getReference() {
        return reference;
    }


    public void setReference(String reference) {
        this.reference = reference;
    }


    public String getScope() {
        return scope;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }


    public List<String> getTypes() {
        return types;
    }


    public void setTypes(List<String> types) {
        this.types = types;
    }


    public String getVicinity() {
        return vicinity;
    }


    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }


    public OpeningHours getOpeningHours() {
        return openingHours;
    }


    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }


    public List<Photo> getPhotos() {
        return photos;
    }


    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }


    public Double getRating() {
        return rating;
    }


    public void setRating(Double rating) {
        this.rating = rating;
    }

}
