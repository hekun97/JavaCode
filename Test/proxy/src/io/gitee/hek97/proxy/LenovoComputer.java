package io.gitee.hek97.proxy;

public class LenovoComputer implements SaleComputer{
    @Override
    public String sale(double money) {
        String information = "花了"+money+"元买了一台联想电脑...";
        System.out.println(information);
        return "一台联想电脑...";
    }

    @Override
    public String show() {
        return "展示电脑...";
    }
}
