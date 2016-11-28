import cn.ls.conf.ConfManager;

/**
 * Created by Administrator on 2016/11/27.
 */
public class Test {

    public static void main(String[] args) {
        ConfManager confManager = new ConfManager();
        String properties = confManager.getProperties("jdbc.driver");
        System.out.println(properties);
    }

}
