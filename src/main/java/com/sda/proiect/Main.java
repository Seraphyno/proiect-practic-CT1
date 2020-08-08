package com.sda.proiect;

import com.sda.proiect.services.OrchestratorService;

public class Main {
    public static void main(String[] args) {
        OrchestratorService orchestratorService = new OrchestratorService();
        orchestratorService.runApplication();
    }
}
