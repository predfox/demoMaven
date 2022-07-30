package com.monkey1024.test;

import com.alibaba.fastjson.JSON;
import com.monkey1024.bean.City;
import com.monkey1024.bean.Province;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    public static void main(String[] args) {

        City c1 = new City();
        c1.setId(1005);
        c1.setName("石家庄");

        City c2 = new City();
        c2.setId(1006);
        c2.setName("唐山");

        City c3 = new City();
        c3.setId(1007);
        c3.setName("保定");

        List<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);

        Province hebei = new Province();
        hebei.setName("河北");
        hebei.setCities(cities);
        //将Province对象转换为json格式并返回字符串
        String hebeiString = JSON.toJSONString(hebei);
        System.out.println(hebeiString);

        //将json格式的字符串转换为Province对象
        Province hebeiNew = JSON.parseObject(hebeiString, Province.class);
        System.out.println(hebeiNew.getName());

    }

}