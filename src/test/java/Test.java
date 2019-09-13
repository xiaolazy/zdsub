import com.google.common.hash.Hasher;
import com.zdsub.common.constant.Common;
import com.zdsub.component.exception.GlobalException;
import com.zdsub.component.token.TokenBean;
import com.zdsub.utils.Base64Util;
import com.zdsub.utils.DateUtil;
import com.zdsub.utils.Jwt;
import com.zdsub.utils.Md5Util;
import io.jsonwebtoken.Claims;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.zdsub.common.constant.Common.SALT;
import static com.zdsub.common.constant.Common.USER_LOGIN_TIME;

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
        String token = Jwt.createJWT("lyy", "2233", "lyy", USER_LOGIN_TIME, Base64Util.Encoder(SALT));
        TokenBean instance = TokenBean.getInstance();
        instance.put(token, token);
        Claims claims = Jwt.parseJWT((String) instance.get(token), Base64Util.Encoder(SALT));
        System.out.println(claims.getExpiration());
        claims.setExpiration(new Date(System.currentTimeMillis()+46));
        String to = (String) TokenBean.getInstance().get((String) instance.get(token));
        Claims claims1 = Jwt.parseJWT(to, Base64Util.Encoder(SALT));
        System.out.println("----------------");
        System.out.println(claims1.getExpiration());
        System.out.println(new Date(System.currentTimeMillis()+46));
//        System.out.println(jwt);
//        System.out.println(Jwt.parseJWT(jwt,"lyy+"));
//        String encoder = Base64Util.Encoder(null);
//        System.out.println(encoder);
//        System.out.println(Base64Util.Decoder(encoder));
//        System.out.println(Md5Util.Md5("2233445566"));
//        System.out.println(Md5Util.Md5("8888888888llll"));
//        System.out.println(Md5Util.Md5("wewewewewewe"));
//        System.out.println("/home".substring(0,5));
        /*git branch --set-upstream-to=zdsub/master master*/
    }
    @org.junit.Test
    public void t(){
        System.out.println(101^010);
        System.out.println();
//        List<String> menus = new ArrayList<>();
//        menus.add("88");
//        menus.add("7");
//        menus.add("9");
//        menus.add("6");
//        for (int i = 0; i < menus.size(); i++) {
//            menus.remove(2);
//        }
//        System.out.println(menus.size());
//        System.out.println();
//        System.out.println(new java.sql.Date(DateUtil.getSysDate().getTime()));
//        System.out.println("23".hashCode());
//        System.out.println("jdlkjf".indexOf("/"));
//        System.out.println(1<<4);
//        System.out.println(1>>4);
//        System.out.println(16>>4);
//        System.out.println(16>>1);
//        System.out.println(2<<4);
//        System.out.println(1<<5);
//        String protalURL = "/manager/aabb";
//        System.out.println(protalURL.contains("ma"));
//        System.out.println("ma".contains(protalURL));
//        System.out.println(protalURL.substring(1,protalURL.lastIndexOf("/")));
//        System.out.println(protalURL.substring(protalURL.indexOf("/")+1,protalURL.length()));
    }
}

