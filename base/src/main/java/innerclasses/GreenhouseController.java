package innerclasses;

/**
 * @ClassName GreenhouseController
 * @Author bin
 * @Date 2020/7/2 下午4:28
 * @Decr TODO
 * @Link TODO
 **/
public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();

        gc.addEvent(gc.new Bell(900));

        Event[] eventList= {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400L),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatNight(1400)
        };
        gc.addEvent(gc.new Restart(2000L,eventList));
        if(args.length == 1){
            gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
        }
        gc.run();

    }
}
