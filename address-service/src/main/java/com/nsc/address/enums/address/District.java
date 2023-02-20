package com.nsc.address.enums.address;

public enum District {
    AVCILAR("Avcılar", City.ISTANBUL),
    BESIKTAS("Beşiktaş", City.ISTANBUL),
    BAHCELIEVLER("Bahçelievler", City.ISTANBUL),
    BORNOVA("Bornova", City.IZMIR),
    CESME("Çeşme", City.IZMIR),
    URLA("Urla", City.IZMIR),
    CANKAYA("Çankaya", City.ANKARA),
    MAMAK("Mamak", City.ANKARA);

    private City city;
    private String value;

    District(String value, City city){
        this.value = value;
        this.city = city;
    }

    public String getValue(){
        return this.value;
    }

    public City getCity() {
        return this.city;
    }
}
