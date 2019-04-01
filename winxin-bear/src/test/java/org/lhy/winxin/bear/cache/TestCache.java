package org.lhy.winxin.bear.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hangyu.li E-mail:islihy@qq.com
 * @date 2018/9/12 下午9:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCache {

    static Map<Integer, Integer> cacheMap = new ConcurrentHashMap<>();
    @Test
    public void test() {
        for (int i = 0; i < 10; i++) System.out.println("f(" + i + ") = " + fibonacci(i));
    }

    static int fibonacci(int i) {
        if (i == 0)
            return i;

        if (i == 1)
            return 1;

        return cacheMap.computeIfAbsent(i, (key) -> fibonacci(i - 2) + fibonacci(i - 1));
    }
}
