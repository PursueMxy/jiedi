package com.icarexm.jiediuser.bean;

public class PositionsBean {
    public String token;
    public String type;
    public String send;
    public String event;
    public String equipment;
    public data data;

    public static class data{
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

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public PositionsBean.data getData() {
        return data;
    }

    public void setData(PositionsBean.data data) {
        this.data = data;
    }


    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public PositionsBean(String token, String type, String send, String event, String equipment, PositionsBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.equipment = equipment;
        this.data = data;
    }

    public PositionsBean(String token, String type, String send, String event, PositionsBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }
}
