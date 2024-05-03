package cn.starry.hub.api.enums;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ActivityType {
    SF2024(0, "&c春节 2024", LocalDate.of(2024, 2, 10), LocalDate.of(2024, 2, 17));

    private final int id;
    private final String displayName;
    private final LocalDate startTime;
    private final LocalDate endTime;

    private ActivityType(int id, String displayName, LocalDate startTime, LocalDate endTime) {
        this.id = id;
        this.displayName = displayName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean isActivityEnable() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isEqual(startTime);
    }

    public long getLeftTime() {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(currentDate, endTime);
    }

}
