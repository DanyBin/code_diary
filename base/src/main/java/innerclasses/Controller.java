package innerclasses;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Controller
 * @Author bin
 * @Date 2020/7/2 下午3:53
 * @Decr TODO
 * @Link TODO
 **/
public class Controller {

   private List<Event> eventList =  new ArrayList<Event>();

   public void addEvent(Event c){eventList.add(c);}

   public void run(){
       while (eventList.size() > 0){
           for(Event e: new ArrayList<Event>(eventList)){
               if(e.ready()){
                   System.out.println(e);
                   e.action();
                   eventList.remove(e);
               }
           }
       }
   }
}
