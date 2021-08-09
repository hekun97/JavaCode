package io.gitee.hek97.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        //1.创建真实对象 lenovo
        SaleComputer lenovo = new LenovoComputer();
        //2.动态代理增强 lenovo 对象，代理对象 proxyLenovo
        /*
            三个参数：
                1. 类加载器：真实对象.getClass().getClassLoader()
                2. 接口数组：真实对象.getClass().getInterfaces()
                3. 处理器：new InvocationHandler()
         */
        SaleComputer proxyLenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /*
            代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
                参数：
                    1. proxy:代理对象
                    2. method：代理对象调用的方法，被封装为的对象
                    3. args:代理对象调用的方法时，传递的实际参数
            */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我被执行了...");
                System.out.println(method.getName());//获取代理对象调用的方面名称
                System.out.println(args[0]);//获取传递的实际参数
                //使用lenovo(真实对象)调用method(代理对象调用的方法，如：sale、show )方法
                Object obj = method.invoke(lenovo, args);
                return obj;
            }
        });
/*        String sale = proxyLenovo.sale(8000);
        System.out.println(sale);*/
        String show = proxyLenovo.show();
        System.out.println(show);
    }
}
