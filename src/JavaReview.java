/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
This source file provides some of the key features in java 8 for those who wants 
to review those features quickly.

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;


interface Number{
    Integer add(Integer i);
}


class Robot{
      public Robot(){}
      public boolean isRobot(){
          return true;
      }
      
     }
class MaleRobot extends Robot{
      
    }
class FemaleRobot extends Robot{
      
    }

class MyGenericClass<T, R>{
    R r_value;
    R get_val(T t){
        return r_value;
    }
}


public class JavaReview {
    static Integer adder(List<Integer> l, Number n){
        Integer sum=0;
        for (Integer i: l){
            sum+=n.add(i);
        }
        return sum;
    }
  
    
    public static <T extends Robot>  boolean testRobot(T  param){
        return param.isRobot();
    };
 
    static boolean function_using_generic_class(MyGenericClass<String, ? extends Robot>si ){
        return true;
        
    }
    
    interface MyLambda{
            int test();
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //ArrayList example
        List<String>persons=new ArrayList();
        persons.add("name1");
        persons.add("name2");
        for (String p: persons){
            System.out.println(p);
        }
        
        //Map Example
        Map<String,String>mp=new HashMap<>();
        mp.put("key1", "value1");
        mp.put("key2", "value2");
        mp.put("key3", "value3");
        
        for (String keys:mp.keySet()){
            System.out.println(mp.get(keys));
        }
        
         //java 8: feature - method reference or functional reference
        //Robot::isRobot;
        
        //java 8: functional interface: an interface with just one abstract function. 
        //it can have many functions with 'default'
        //exmple MyLambda
        
        //without lambda expression
        MyLambda m1=new MyLambda(){
            @Override
            public int test(){return 1;}
        };
        
        //with lambda expression
        MyLambda l1=()->{return 1;};

        //Lambda example
        Number lambda_function_1=(i)-> i*i;
        
        //Lambda usage examples
        List listnums=Arrays.asList(1,2,3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(adder(listnums, lambda_function_1));
        
        
        //Special interface (a functional interface) which has the abstract method,
        //boolean test(T t);
        Predicate<String> lambda_function_predicate=(t) ->t.isEmpty();
        System.out.println(lambda_function_predicate.test(""));
        
                
        //default methods in interfaces are added in java 8.
        //before 8, interfaces can have abstract methods only (methods with no implementations)-derived classes need to implement those abstract methods
        //in java 8 new methods needed in the interface are provided with an implementation so, those derived classes are not required to implement those methods
        //sort of providing backward compatibility
        
        
        //another very import functional interface, Function has a method apply
        //which takes any type and returns another type
        //public interface Function<T, R> {
        //    R apply(T t);
        //}
        //Below example function takes String, apply length and returns Integer
        Function<String, Integer> function_interface=(String t)->{return t.length();};
        System.out.println(function_interface.apply("abcd"));
        
        
        //streams, useful for doing filter, map, reduce functionality.
        List<Integer> myList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer>odd_nums=(num)->num%2==1;
        
        //filter takes Predicate<>
        Stream s1=myList.stream().filter(odd_nums);
    
        Object o1=s1.collect(toList());
        
        //review of generics in java
        Robot r=new Robot();
        MaleRobot sr=new MaleRobot();
        FemaleRobot br=new FemaleRobot();
        
        //testRobot is generic method which accepts types of Robot
        //(either Robot or its derived types)
        testRobot(r); //OK
        testRobot(sr); //OK
        //testRobot(""); Not OK
        
        
        //MyGericClass is a generic class parametrized with two types
        MyGenericClass<String, MaleRobot> mgen=new MyGenericClass<>(); //OK
        MyGenericClass<Integer, Robot> mgen2=new MyGenericClass<>();  //OK
        MyGenericClass<Integer, String> mgen3=new MyGenericClass<>();  //OK
        
        
        //function_using_generic_class takes one parameter of type MyGenericClass<String, ? Robot>
        //? in second parameter type to MyGenericClass means , it can take any 
        //classes of type Robot or its derived classes
        //it is called upperbound wildcard
        
        function_using_generic_class(mgen); //OK
        //function_using_generic_class(mgen2); //Not OK mgen2 is of type MyClassGen<Integer, Robot>, first parameter is not String
                      
    }
    
}
