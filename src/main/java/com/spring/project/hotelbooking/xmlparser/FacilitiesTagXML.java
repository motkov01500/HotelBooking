package com.spring.project.hotelbooking.xmlparser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "facilities")
@XmlAccessorType(XmlAccessType.FIELD)
public class FacilitiesTagXML {

    @XmlElement(name = "facility")
    private List<FacilityTagXML> facilities = new ArrayList<>();

    public List<FacilityTagXML> getFacilityTagElements() {
        return facilities;
    }

    public void setFacilities(List<FacilityTagXML> facilityXMLList) {
        this.facilities = facilityXMLList;
    }
}
