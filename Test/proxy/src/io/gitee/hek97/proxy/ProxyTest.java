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
/*                System.out.println("代理对象调用的方法名称："+method.getName());
                System.out.println("传递的实际参数："+args[0]);*/
                if (method.getName().equals("sale")) {
                    //1.增强参数
                    double money = (double) args[0];
                    money = money * 0.85;
                    //3.增强方法体执行逻辑
                    //3.1 买前专车接
                    System.out.println("专车接你");
                    //使用lenovo(真实对象)调用method(代理对象调用的方法，如：sale)方法
                    Object obj = method.invoke(lenovo, money);
                    //3.2 送货上门
                    System.out.println("送货上门");
                    //2.增强返回值类型
                    return obj+"_鼠标垫";
                }
                else{
                    //使用lenovo(真实对象)调用method(代理对象调用的方法，如：sale)方法
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }

            }
        });
        String sale = proxyLenovo.sale(8000);
        System.out.println(sale);
/*        String show = proxyLenovo.show();
        System.out.println(show);*/
    }
}
