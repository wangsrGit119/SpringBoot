package cn.wangsr.utils;



/**
 * @author: wjl
 * @description:
 * @time: 2019/9/30 0030 17:11
 */
public class PdfUtils {
    public static void main(String[] args) {

        String name="华为智慧园区PPT201908292019-09-30-11-30-39-UID-1.pdf";
        String[] strArray = name.split("\\.");
        int a = name.indexOf("-");//*第一个出现的索引位置
        if(a != -1){
            name=name.substring(0,a);
        }

        System.out.println(name+"."+strArray[strArray.length-1]);
    }





}
