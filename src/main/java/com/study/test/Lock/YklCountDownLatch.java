package com.study.test.Lock;

import com.study.test.util.Tools;
import com.sun.org.apache.xpath.internal.functions.FuncTrue;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.AsyncResult;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class YklCountDownLatch {

//    @PostConstruct
//    public void init() {
//
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(2, 100, 5, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(500), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("执行拒绝策略");
            }
        });
        while(true) {
            System.out.println("开始. 新一轮");
            CountDownLatch countDownLatch = new CountDownLatch(10);
            for (int i = 0; i < 10; i++) {
//                new Thread(new Test(countDownLatch, i)).start();
                fixedThreadPool.execute(new Test(countDownLatch, i));
                System.out.println("创建物流,当前线程池等待的数量为：" + fixedThreadPool.getQueue().size() + "; "
                        + "poolsize为: " + fixedThreadPool.getPoolSize() + "; "
                        + "ActiveCount: " + fixedThreadPool.getActiveCount() + "; "
                        + "TaskCount: " + fixedThreadPool.getTaskCount());
            }
            countDownLatch.await(); // 计数器倒计时开关, 为0 时打开开关
            System.out.println("结束, 开启下一轮....");
        }
    }

    static class Test implements Runnable {
        private  CountDownLatch countDownLatch;
        private  int i;
        private Test(CountDownLatch countDownLatch, int i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {
            try{
                Map<String, Object> map  = new HashMap<>();
                map.put("name", "张山");
                map.put("age", i);
                Future<Object> funcTrue = Test.ceshi(map);
                HashMap<String, Object> obj = (HashMap<String, Object>) funcTrue.get(1, TimeUnit.SECONDS);
                System.out.println(obj.toString());
            } catch (InterruptedException e) {
                System.out.println("中断异常: " + e.getMessage());
            } catch (ExecutionException e) {
                System.out.println("ExecutionException异常: " + e.getMessage());
            } catch (TimeoutException e) {
                System.out.println("TimeoutException 超时异常: " + e.getMessage());
            } finally {
                // 防止异常不出现减一的情况
                System.out.println("计数器开始减一");
                countDownLatch.countDown();  // 计数器减一
                System.out.println("剩余计数数量: " + countDownLatch.getCount());
            }
        }

        public static Future<Object> ceshi(Map map) throws InterruptedException {
            map.put("name", "李四" + Tools.toString(map.get("age")));
//            map.put("age", new Random().nextInt());
            Thread.sleep(1000 * new Random().nextInt(10));
            System.out.println("业务开始睡随机秒");
            if (1 == Tools.toInt(map.get("age"))) {
                System.out.println("模拟中断异常");
                throw new InterruptedException("未知中断异常");
            }
            Future<Object> task  = new AsyncResult<>(map);
            return task;
        }
    }
}
