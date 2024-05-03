package treePermit.controller;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    public static ConcurrentHashMap<String, Antrag> antraege = new ConcurrentHashMap<>();
}
