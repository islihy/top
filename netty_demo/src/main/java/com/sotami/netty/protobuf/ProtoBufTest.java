package com.sotami.netty.protobuf;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019/2/8 3:45 PM
 */
public class ProtoBufTest {

    /**
     * 把对象构造好-生成字节数组
     * 从字节数组总读取出来，还原成对象并输出
     * @param args
     */
    public static void main(String[] args) throws Exception{
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();

        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student1 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student1.toString());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }

}
