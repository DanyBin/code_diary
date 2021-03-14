package enumtype;

/**
 * @ClassName SpaceShip
 * @Author bin
 * @Date 2020/7/31 上午10:46
 * @Decr TODO
 *      重写toString()方法
 * @Link TODO
 **/
public enum  SpaceShip {
    SCOUT, CARGO;

    @Override
    public String toString(){
        String id =name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0)+lower;
    }

    public static void main(String[] args) {
        for(SpaceShip s: values()){
            System.out.println(s);
        }
    }
}
