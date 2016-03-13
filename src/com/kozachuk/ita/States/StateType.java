package com.kozachuk.ita.States;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */
@XmlType
@XmlEnum(Integer.class)

public enum StateType {
    @XmlEnumValue("1") ONE("1"),
    @XmlEnumValue("2") TWO("2"),
    @XmlEnumValue("3") THREE("3"),
    @XmlEnumValue("4") ASTERISK("4"),
    @XmlEnumValue("5") LATTICE("5");

    private static final Map<String,StateType> lookup = new HashMap<String,StateType>();

    static {
        for(StateType type : EnumSet.allOf(StateType.class))
            lookup.put(type.getCode(), type);
    }

    private String code;

    private StateType(String code) {
        this.code = code;
    }

    public String getCode() { return code; }

    public static StateType get(String code) {
        return lookup.get(code);
    }
}
