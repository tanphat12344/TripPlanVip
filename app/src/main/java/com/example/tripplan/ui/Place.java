package com.example.tripplan.ui;

public class Place {

        private String Name;
        private String Image;

        public Place(){
        }

        public Place(String name, String image) {
            Name = name;
            Image = image;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }


}
