package cn.ls.util;

/**
 * Created by Administrator on 2016/11/28.
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {}

    /**
     * 必须考虑到可能会出现的多线程并发访问安全的问题，可能会导致创建多次单例
     *
     * 首先，就是，说到多线程并发访问安全的控制，大家觉得最简单的就是在方法上加入synchronized关键词
     * public static synchronized Singleton getInstance()方法
     * 但是这样做有一个很大的问题
     * 在第一次调用的时候，的确是可以做到避免多个线程并发访问创建多个实例的问题
     * 但是在第一次创建完实例以后，就会出现以后的多个线程并发访问这个方法的时候，就会在方法级别进行同步
     * 导致并发性能大幅度降低
     *
     * 个人理解，在方法级别同步，就会在方法级别等待，造成资源给予不及时！！
     *
     * @return
     */
    public static Singleton getInstance() {

        if(instance == null) {
            // 在这里，进行多个线程的同步
            // 同一时间，只能有一个线程获取到Singleton Class对象的锁
            synchronized(Singleton.class) {
                // 只有第一个获取到锁的线程，进入到这里，会发现是instance是null
                // 然后才会去创建这个单例
                // 此后，线程，哪怕是走到了这一步，也会发现instance已经不是null了
                // 就不会反复创建一个单例
                // 个人理解，当第一次有5个线程都为null都进来了，在同步外面等着，就会进来一个创建一个！
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
