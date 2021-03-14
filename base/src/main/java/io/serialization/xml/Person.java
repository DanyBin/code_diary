package io.serialization.xml;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Person
 * @Author bin
 * @Date 2020/7/30 下午3:50
 * @Decr TODO
 * @Link TODO
 **/
public class Person {
    private String first,last;
    public Person(String first,String last){
        this.first = first;
        this.last = last;
    }

    public Element getXML(){
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(this.first);

        Element lastName = new Element("last");
        lastName.appendChild(this.last);

        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }

    public Person (Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }

    @Override
    public String toString(){
        return first + " " + last;
    }

    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws Exception {
        String file = "/Users/bin/gitStudy/code_diary/base/src/main/resources/People.xml";
        List<Person>  people = Arrays.asList(new Person("a","b"),new Person("c","d"));
        System.out.println(people);

        Element root = new Element("people");
        for(Person p : people) {
            root.appendChild(p.getXML());
        }

        Document doc = new Document(root);
        format(System.out,doc);
        format(new BufferedOutputStream(new FileOutputStream(file)),doc);
    }
}
