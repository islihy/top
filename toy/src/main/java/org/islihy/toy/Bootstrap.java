package org.islihy.toy;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2019-04-11 14:31
 */
public class Bootstrap {
    public static void main(String[] args) {
        Test t = new Test("Hello","World!");
        t.toString();
    }

    static  class Test{

        String s1 ;
        String s2 ;

        public Test(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        @Override
        public String toString() {
            return s1+','+s2;
        }
    }
}

