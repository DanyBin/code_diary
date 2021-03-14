package io.serialization.xml;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import java.util.ArrayList;

/**
 * @ClassName People
 * @Author bin
 * @Date 2020/7/30 下午4:10
 * @Decr TODO
 * @Link TODO
 **/
public class People extends ArrayList<Person> {

    public People(String fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements =doc.getRootElement().getChildElements();
        for(int i=0; i< elements.size(); i++){
            add(new Person(elements.get(i)));
        }
    }

    public static void main(String[] args) throws Exception {
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/People.xml";
        People p = new People(file);
        System.out.println(p);

    }
}
