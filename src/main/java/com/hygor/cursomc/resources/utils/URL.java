package com.hygor.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static List<Integer> decodeIntList(String s){

        List<Integer> list = new ArrayList<>();

        String[] arr = s.split(",");

        for(int i = 0; i < arr.length; i++){
            try{
                list.add(Integer.parseInt(arr[i]));
            }catch(NumberFormatException e){

            }

            System.out.println(arr[i]);
        }


        return list;

        //return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }

    public static String decodeParam(String s){

        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }

}
