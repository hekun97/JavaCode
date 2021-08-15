package hek.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hek.domain.Person;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {
    @Test
    public void test1() throws IOException {
        //0.导入jar包
        //1. 创建Person对象
        Person person = new Person("张三", 23, "男");
        //2.创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        /*
            转换方法：
                writeValue(参数1，obj):
                    参数1：
                        File：将obj对象转换为JSON字符串，并保存到指定的文件中
                        Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
                        OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
                writeValueAsString(obj):将对象转为json字符串

         */
        String json = mapper.writeValueAsString(person);
        System.out.println(json);//{"name":"张三","age":23,"gender":"男"}
        //writeValue，将数据写到d://a.txt文件中
        //mapper.writeValue(new File("d://a.txt"),p);

        //writeValue.将数据关联到Writer中
        mapper.writeValue(new FileWriter("d://b.txt"), person);
    }

    @Test
    public void test2() throws IOException {
        //0.导入jar包
        //1. 创建Person对象
        Person person = new Person("张三", 23, "男", new Date());
        //2.创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        String json = mapper.writeValueAsString(person);
        System.out.println(json);//{"name":"张三","age":23,"gender":"男","birthday":"2021-08-15"}
    }

    @Test
    public void test3() throws IOException {
        //0.导入jar包
        //1. 创建Person对象
        Person p1 = new Person("张三", 23, "男", new Date());
        Person p2 = new Person("李四", 21, "女", new Date());
        Person p3 = new Person("王五", 45, "男", new Date());
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        //2.创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        String json = mapper.writeValueAsString(people);
        System.out.println(json);
        //[{"name":"张三","age":23,"gender":"男","birthday":"2021-08-15"},{"name":"李四","age":21,"gender":"女","birthday":"2021-08-15"},{"name":"王五","age":45,"gender":"男","birthday":"2021-08-15"}]
    }

    @Test
    public void test4() throws IOException {
        //0.导入jar包
        //1.创建Map对象
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        map.put("gender", "男");
        //2.创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
        //{"gender":"男","name":"张三","age":23}
    }
    @Test
    public void test5() throws IOException {
        //0.导入jar包
        //1.初始化JSON字符串
        String json = "{\"name\":\"张三\",\"age\":23,\"gender\":\"男\"}";
        //2.创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //3.转换为Person对象
        Person p = mapper.readValue(json,Person.class);
        System.out.println(p);
        //Person{name='张三', age=23, gender='男'}
    }
}
