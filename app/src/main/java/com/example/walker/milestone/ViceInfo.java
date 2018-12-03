package com.example.walker.milestone;

public class ViceInfo {
    public String getMilestones(String vice) {
        switch (vice) {
            case "cigarette":
                //Do cig stuff
                return "cig stuff";

            case "alcohol":
                //Do alc stuff
                return "alc stuff";

            case "boba":
                return "boba stuff";

            default:
                return "Error";
        }
    }

    // TODO: Add more methods here to get information
}
