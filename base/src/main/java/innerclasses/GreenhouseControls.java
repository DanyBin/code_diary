package innerclasses;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName GreenhouseControls
 * @Author bin
 * @Date 2020/7/2 下午3:58
 * @Decr TODO
 * @Link TODO
 **/
public class GreenhouseControls extends Controller {


    private boolean light = false;
    public class LightOn extends Event{
        public LightOn(long delayTime){
            super(delayTime);
        }
        @Override
        public void action() {
            light = true;
        }
        @Override
        public String toString(){return "Light is on";}
    }


    public class  LightOff extends Event{
        public LightOff (Long delayTime){
            super(delayTime);
        }

        @Override
        public void action(){
            light = false;
        }

        @Override
        public String toString(){return "Light is off";}
    }

    private boolean water = false;
    public class WaterOn extends Event{
        public WaterOn(long delayTime){
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }
        @Override
        public String toString(){return "water is on";}
    }
    public class WaterOff extends Event{

        public WaterOff(long delayTime){
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }
        @Override
        public String toString(){return "water is off";}
    }

    private String thermostat = "Day";
    public class  ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "Night";
        }
        @Override
        public String toString(){return  "Thermostat on night setting";}
    }

    public class ThermostatDay extends Event{

        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            thermostat = "day";
        }
        @Override
        public String toString(){return "Thermostat on day setting";}
    }


    public class Bell extends Event{

        public Bell(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString(){return "Bing! ";}
    }

    public class Restart extends Event{

        private Event[] eventList;
        public Restart(Long delay,Event[] eventList){
            super(delay);
            this.eventList = eventList;
            for(Event e: eventList){
                addEvent(e);
            }
        }

        @Override
        public void action() {
            for(Event e:eventList){
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        @Override
        public String toString(){return "Restaring system";}
    }

    public static class Terminate extends Event{

        public Terminate(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.exit(0);
        }
        @Override
        public String toString(){return "Terminating";}
    }
}
