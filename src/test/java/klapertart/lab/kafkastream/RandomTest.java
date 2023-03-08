package klapertart.lab.kafkastream;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@SpringBootTest
public class RandomTest {

    @Test
    @Disabled
    public void splitTest(){
        String[] name = {"abdillah abdillah","hamka hamza","hamza hamza","hamka"};

        List<String> strings = Arrays.asList(name);

//        strings.stream()
//                .map(value -> value.toUpperCase())
//                .flatMap()
//                //.flatMap(value -> Arrays.asList(value.split("\\w+")))
//                .forEach(System.out::println);

        String names = "abdillah abdillah hamka hamza hamza hamza hamka";
        List<String> tnames = Arrays.asList(names.split("\\w+"));
        tnames.forEach(System.out::println);

    }
}
