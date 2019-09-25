package com.icarexm.jiedi.Bean;

public class PositionBean {
    public String token;
    public String type;
    public String send;
    public String event;
    public data data;
    public class data{
        public String positionE;
        public String positionN;
        public String position;
        public String speed;
        public String city;
        public String points;

        public String getPositionE() {
            return positionE;
        }

        public void setPositionE(String positionE) {
            this.positionE = positionE;
        }

        public String getPositionN() {
            return positionN;
        }

        public void setPositionN(String positionN) {
            this.positionN = positionN;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public data(String positionE, String positionN, String position, String speed, String city, String points) {
            this.positionE = positionE;
            this.positionN = positionN;
            this.position = position;
            this.speed = speed;
            this.city = city;
            this.points = points;
        }
    }

    public PositionBean(String token, String type, String send, String event, PositionBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
