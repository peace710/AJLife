package me.peace.jetpack.startup;


import androidx.work.WorkManager;

public class JetpackLogger {
    private WorkManager manager;

    public JetpackLogger(WorkManager manager) {
        this.manager = manager;
    }
}
