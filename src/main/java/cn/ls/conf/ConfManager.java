package cn.ls.conf;

import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by luosong on 2016/11/27.
 */
public class ConfManager {

    private static Properties prop = new Properties();

    static {
        // 通过一个“类名.class”的方式，就可以获取到这个类在JVM中对应的Class对象
        // 然后再通过这个Class对象的getClassLoader()方法，就可以获取到当初加载这个类的JVM
        // 中的类加载器（ClassLoader），然后调用ClassLoader的getResourceAsStream()这个方法
        // 就可以用类加载器，去加载类加载路径中的指定的文件
        // 最终可以获取到一个，针对指定文件的输入流（InputStream）
        InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("my.properties");
        try {

            // 调用Properties的load()方法，给它传入一个文件的InputStream输入流
            // 即可将文件中的符合“key=value”格式的配置项，都加载到Properties对象中
            // 加载过后，此时，Properties对象中就有了配置文件中所有的key-value对了
            // 然后外界其实就可以通过Properties对象获取指定key对应的value
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperties (String key){ return prop.getProperty(key); }

}
