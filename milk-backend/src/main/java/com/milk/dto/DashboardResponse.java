package com.milk.dto;

public class DashboardResponse {

    private long farmers;
    private double todayMilk;
    

    public DashboardResponse(long farmers, double todayMilk) {
        this.farmers = farmers;
        this.todayMilk = todayMilk;
        
    }

	public DashboardResponse() {
		
	}

	public long getFarmers() {
		return farmers;
	}

	public void setFarmers(long farmers) {
		this.farmers = farmers;
	}

	public double getTodayMilk() {
		return todayMilk;
	}

	public void setTodayMilk(double todayMilk) {
		this.todayMilk = todayMilk;
	}


	}

    // Getters and Setters

