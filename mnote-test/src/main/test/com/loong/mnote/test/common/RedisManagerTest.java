package com.loong.mnote.test.common;

import com.loong.mnote.test.BaseTest;

/**
 * @author: lhd
 * @date: 2018/12/26 17:40
 */
public class RedisManagerTest extends BaseTest {

//    @Autowired
//    private RedisManager redisManager;
//
//    @Test
//    public void get() {
//        String key = "user5";
//        Demo user = redisManager.get(key);
//        System.out.println(user);
//    }
//
//    @Test
//    public void queryWithCache() {
//
//        Demo user = new Demo();
//        user.setAge(20);
//        user.setName("猴子");
//        String key = "TEST_USER@"+user.getName();
//
//        Demo result = redisManager.queryWithCache(
//                () -> user,
//                ExpirePolicy.EXPIRE.FIVE_MINUTE,
//                () -> key
//        );
//        System.out.println(result);
//    }
//
//    @Test
//    public void batchQueryWithCache() {
//
//        List<Long> ids = Arrays.asList(1l,2l,3l);
//
//        List<Demo> result = redisManager.batchQueryWithCache(
//                ids,
//                ExpirePolicy.EXPIRE.FIVE_MINUTE,
//                (id) -> initData(id),
//                (id) -> buildKey(id),
//                (obj) -> obj2Key(obj),
//                (key) -> parseKey(key)
//        );
//        System.out.println(result);
//    }
//
//    private List<Demo> initData(List<Long> ids) {
//        return ids.stream().map((id) -> {
//            Demo user = new Demo();
//            user.setId(id);
//            user.setAge(20);
//            user.setName("猴子"+id);
//            return user;
//        }).collect(Collectors.toList());
//    }
//
//    private String buildKey(Long id) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("TEST_USER@").append(id);
//        return builder.toString();
//    }
//
//    private String obj2Key(Demo demo) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("TEST_USER@").append(demo.getId());
//        return builder.toString();
//    }
//
//    private Long parseKey(String key) {
//        String[] items = StringUtils.split(key, "@");
//        return Long.valueOf(items[items.length - 1]);
//    }
}
