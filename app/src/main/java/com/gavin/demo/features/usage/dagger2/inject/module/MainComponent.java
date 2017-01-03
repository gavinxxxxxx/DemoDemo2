package com.gavin.demo.features.usage.dagger2.inject.module;

import dagger.Component;

//这里表示Component会从MainModule类中拿那些用@Provides注解的方法来生成需要注入的实例
@Component(modules = MainModule.class)
public interface MainComponent {

    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    void inject(Dagger2Fragment5 fragment);
}