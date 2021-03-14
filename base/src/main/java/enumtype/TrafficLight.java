package enumtype;

/**
 * @ClassName TrafficLight
 * @Author bin
 * @Date 2020/7/31 上午10:53
 * @Decr TODO
 *      switch 使用enum
 * @Link TODO
 **/
public class TrafficLight {
    Singal color = Singal.RED;

    public void change() {
        switch (color) {
            case RED: color = Singal.GREEN;
            break;
            case GREEN:color = Singal.YELLOW;
            break;
            case YELLOW:color = Singal.RED;
            break;
        }
    }

    @Override
    public String toString(){
        return "the trafficLight is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for(int i=0; i< 7; i++) {
            System.out.println(t);
            t.change();
        }
    }
}
