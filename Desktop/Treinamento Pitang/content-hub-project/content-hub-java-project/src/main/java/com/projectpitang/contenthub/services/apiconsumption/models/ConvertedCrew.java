package com.projectpitang.contenthub.services.apiconsumption.models;

public class ConvertedCrew extends ConvertedCastCrew{

    private String department;
    private String job;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
