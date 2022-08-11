package com.ewmw.addr.services.gar.interfaces;

public interface HasLevels {
    public String getLevelFullName(String level, String shortName) throws LevelNotFoundException;
}
