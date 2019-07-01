package com.eco.entitys;

import java.util.HashMap;
import java.util.Map;

public class XChangeEntity {
    public int id;
    public int citizenId;
    public String requestDate;
    public String deliverDate;
    public Map<String,String> list = new HashMap<>();
    public int citizenScore;
    public int status;
    public int type;
}
