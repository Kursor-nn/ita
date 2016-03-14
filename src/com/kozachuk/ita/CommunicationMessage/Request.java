package com.kozachuk.ita.CommunicationMessage;

/**
 * Created by alexanderkozachuk on 13.03.16.
 */

import com.kozachuk.ita.States.StateType;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request implements Message{
    private StateType stateType;
    public Request(){}

    public Request(StateType stateType){
        this.stateType = stateType;
    }

    public StateType getStateType() {
        return stateType;
    }

    @XmlElement
    public void setStateType(StateType stateType) {
        this.stateType = stateType;
    }

    public void print(){
        System.out.println("State : " + getStateType());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("state type", getStateType())
                .toString();
    }
}
