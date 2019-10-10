package com.icarexm.jiediuser.bean;

public class PlaceOrderBean {
    public String token;
    public String type;
    public String mobile;
    public String send;
    public String event;
    public data data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getsend() {
        return send;
    }

    public void setsend(String send) {
        this.send = send;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public PlaceOrderBean.data getData() {
        return data;
    }

    public void setData(PlaceOrderBean.data data) {
        this.data = data;
    }

    public static class data {
        String startingpointE;
        String startingpointN;
        String startingpoint;
        String destinationE;
        String destinationN;
        String destination;
        String estimated_mileage;
        String estimated_time;
        String budget;
        String service_type;
        String city;
        String flightno;
        String estimatedeparturetime;


        public String getStartingpointE() {
            return startingpointE;
        }

        public void setStartingpointE(String startingpointE) {
            this.startingpointE = startingpointE;
        }

        public String getStartingpointN() {
            return startingpointN;
        }

        public void setStartingpointN(String startingpointN) {
            this.startingpointN = startingpointN;
        }

        public String getStartingpoint() {
            return startingpoint;
        }

        public void setStartingpoint(String startingpoint) {
            this.startingpoint = startingpoint;
        }

        public String getDestinationE() {
            return destinationE;
        }

        public void setDestinationE(String destinationE) {
            this.destinationE = destinationE;
        }

        public String getDestinationN() {
            return destinationN;
        }

        public void setDestinationN(String destinationN) {
            this.destinationN = destinationN;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getEstimated_mileage() {
            return estimated_mileage;
        }

        public void setEstimated_mileage(String estimated_mileage) {
            this.estimated_mileage = estimated_mileage;
        }

        public String getEstimated_time() {
            return estimated_time;
        }

        public void setEstimated_time(String estimated_time) {
            this.estimated_time = estimated_time;
        }

        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getFlightno() {
            return flightno;
        }

        public void setFlightno(String flightno) {
            this.flightno = flightno;
        }

        public String getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(String estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
        }

        public data(String startingpointE, String startingpointN, String startingpoint, String destinationE, String destinationN, String destination, String estimated_mileage, String estimated_time, String budget, String service_type, String city, String flightno, String estimatedeparturetime) {
            this.startingpointE = startingpointE;
            this.startingpointN = startingpointN;
            this.startingpoint = startingpoint;
            this.destinationE = destinationE;
            this.destinationN = destinationN;
            this.destination = destination;
            this.estimated_mileage = estimated_mileage;
            this.estimated_time = estimated_time;
            this.budget = budget;
            this.service_type = service_type;
            this.city = city;
            this.flightno = flightno;
            this.estimatedeparturetime = estimatedeparturetime;
        }
    }

    public PlaceOrderBean(String token, String type, String mobile, String send, String event, PlaceOrderBean.data data) {
        this.token = token;
        this.type = type;
        this.mobile = mobile;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
