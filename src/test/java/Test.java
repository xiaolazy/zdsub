import java.math.BigDecimal;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-03 19:34
 **/
public class Test {
    @org.junit.Test
    public void test() {
        HashMap<String, Integer> map = new HashMap<>(32);
        Hashtable<String, Integer> hashTable = new Hashtable<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("初始化11");
                map.put("11", 22);
                hashTable.put("11", 22);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("初始化22");
                map.put("11", 11);
                hashTable.put("11", 11);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("初始化33");
                map.put("33", 33);
                hashTable.put("33", 33);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("初始化44");
                map.put("33", 44);
                hashTable.put("33", 22);
            }
        }).start();
        Set<String> strings = map.keySet();
        Set<String> tables = hashTable.keySet();
        for (String val : tables
        ) {
            System.out.println("table key:" + val + "hashTable Value:" + hashTable.get(val));
        }

        for (String s : strings) {
            System.out.println("数组遍历");
            System.out.println("key:" + s + " value:" + map.get(s));
            System.out.println("循环中map size:" + map.size());
        }
        System.out.println("循环外map size:" + map.size());
        System.out.println("循环外HashTable size:" + hashTable.size());
    }

    @org.junit.Test
    public strictfp void floa() {
        float priceBefore = 1.0f;
        float priceNow = 1.0f;
        float sell = 0.0f;
        float sum = 0.0f;
        for (int i = 1; i < 10000; i++) {
            priceNow = priceNow + 0.02f;
            System.out.println("第" + i + "天，大米的价格是：" + priceNow + "  要大于" + priceBefore * 1.05 + "才可以卖的哦");
            if (priceNow > priceBefore * 1.05) {
                System.out.println(priceBefore);
                System.out.println(priceNow);
                System.out.println(sell);
                System.out.println(sum);
                System.out.println("第" + i + "天，大米可以卖啦");
                priceBefore = priceNow;
                int canSell = (int) (sell * 0.05);
                sell = sell - canSell;
                sum = sum + canSell;
                if (sum * priceNow > 10000) {
                    System.out.println("第" + i + "天，大米价格是：" + priceNow + "，可卖大米" + sum + "斤，赚：" + sum * priceNow + "元钱！");
                    break;
                }
            }
        }
    }
    @org.junit.Test
    public strictfp void haha(){
        String ss = "111";
        int i = Integer.parseInt(ss, 8);
        System.out.println(i);
    }
}

