package com.gavin.demo.features.usage.rxjava;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentVectorBinding;
import com.gavin.demo.utils.L;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava 变换(map & flatMap)
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment3 extends SupportFragment implements View.OnClickListener {

    FragmentVectorBinding binding;

    public static RxJavaFragment3 newInstance() {
        return new RxJavaFragment3();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vector, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        code();
    }

    /**
     * 可以看到，map() 方法将参数中的 Integer 对象转换成一个 Drawable 对象后返回，
     * 而在经过 map() 方法后，事件的参数类型也由 Integer 转为了 Drawable。
     * 这种直接变换对象并返回的，是最常见的也最容易理解的变换。
     * 不过 RxJava 的变换远不止这样，它不仅可以针对事件
     */
    private void code() {
        Observable.just(R.drawable.ic_arrow_back_black_24dp) // 输入类型 Integer
                .map(new Func1<Integer, Drawable>() {
                    @Override
                    public Drawable call(Integer drawableRes) { // 参数类型 Integer
                        return ContextCompat.getDrawable(_mActivity, drawableRes); // 返回类型 Drawable
                    }
                })
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) { // 参数类型 Drawable
                        binding.imageView.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 从此面的代码可以看出，
     *      flatMap() 和 map() 有一个相同点：
     *          它也是把传入的参数转化之后返回另一个对象。
     *      但需要注意，和 map() 不同的是，
     *          flatMap() 中返回的是个 Observable 对象，
     *          并且这个 Observable 对象并不是被直接发送到了 Subscriber 的回调方法中。
     *
     *      flatMap() 的原理是这样的：
     *          1. 使用传入的事件对象创建一个 Observable 对象；
     *          2. 并不发送这个 Observable, 而是将它激活，于是它开始发送事件；
     *          3. 每一个创建出来的 Observable 发送的事件，都被汇入同一个 Observable ，而这个 Observable 负责将这些事件统一交给 Subscriber 的回调方法。
     *
     *          这三个步骤，把事件拆成了两级，通过一组新创建的 Observable 将初始的对象『铺平』之后通过统一路径分发了下去。而这个『铺平』就是 flatMap() 所谓的 flat。
     */
    private void code2() {
//        Student[] students = ...;
//        Subscriber<Course> subscriber = new Subscriber<Course>() {
//            @Override
//            public void onNext(Course course) {
//                Log.d(tag, course.getName());
//            }
//            ...
//        };
//        Observable.from(students)
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return Observable.from(student.getCourses());
//                    }
//                })
//                .subscribe(subscriber);
    }

    /**
     * 扩展：由于可以在嵌套的 Observable 中添加异步代码，
     * flatMap() 也常用于嵌套的异步操作，例如嵌套的网络请求。
     * 示例代码（Retrofit + RxJava）：
     */
    private void code3() {
//        networkClient.token() // 返回 Observable<String>，在订阅时请求 token，并在响应后发送 token
//                .flatMap(new Func1<String, Observable<Messages>>() {
//                    @Override
//                    public Observable<Messages> call(String token) {
//                        // 返回 Observable<Messages>，在订阅时请求消息列表，并在响应后发送请求到的消息列表
//                        return networkClient.messages();
//                    }
//                })
//                .subscribe(new Action1<Messages>() {
//                    @Override
//                    public void call(Messages messages) {
//                        // 处理显示消息列表
//                        showMessages(messages);
//                    }
//                });
    }

    /**
     * 讲述 lift() 的原理只是为了让你更好地了解 RxJava ，从而可以更好地使用它。
     * 然而不管你是否理解了 lift() 的原理，RxJava 都不建议开发者自定义 Operator 来直接使用 lift()，
     *      而是建议尽量使用已有的 lift() 包装方法（如 map() flatMap() 等）进行组合来实现需求，
     *      因为直接使用 lift() 非常容易发生一些难以发现的错误。
     */
    private void code4() {
        Observable.just(1, 2, 4, 5)
                .lift(new Observable.Operator<String, Integer>() {
                    @Override
                    public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                        return new Subscriber<Integer>() {
                            @Override
                            public void onNext(Integer integer) {
                                subscriber.onNext("" + integer);
                            }

                            @Override
                            public void onCompleted() {
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(Throwable e) {
                                subscriber.onError(e);
                            }
                        };
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        L.e(s);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    /**
     * compose: 对 Observable 整体的变换
     *
     * 除了 lift() 之外， Observable 还有一个变换方法叫做 compose(Transformer)。
     * 它和 lift() 的区别在于， lift() 是针对事件项和事件序列的，
     * 而 compose() 是针对 Observable 自身进行变换。
     * 举个例子，假设在程序中有多个 Observable ，并且他们都需要应用一组相同的 lift() 变换。你可以这么写：
     */
    private void code5() {
//        observable1
//                .lift1()
//                .lift2()
//                .lift3()
//                .lift4()
//                .subscribe(subscriber1);
//        observable2
//                .lift1()
//                .lift2()
//                .lift3()
//                .lift4()
//                .subscribe(subscriber2);
//        observable3
//                .lift1()
//                .lift2()
//                .lift3()
//                .lift4()
//                .subscribe(subscriber3);
//        observable4
//                .lift1()
//                .lift2()
//                .lift3()
//                .lift4()
//                .subscribe(subscriber1);
//
//        /**
//         * 你觉得这样太不软件工程了，于是你改成了这样：
//         */
//
//        private Observable liftAll(Observable observable) {
//            return observable
//                    .lift1()
//                    .lift2()
//                    .lift3()
//                    .lift4();
//        }
//        ...
//        liftAll(observable1).subscribe(subscriber1);
//        liftAll(observable2).subscribe(subscriber2);
//        liftAll(observable3).subscribe(subscriber3);
//        liftAll(observable4).subscribe(subscriber4);
//
//        /**
//         * 可读性、可维护性都提高了。可是 Observable 被一个方法包起来，
//         * 这种方式对于 Observale 的灵活性似乎还是增添了那么点限制。
//         * 怎么办？这个时候，就应该用 compose() 来解决了：
//         */
//
//        public class LiftAllTransformer implements Observable.Transformer<Integer, String> {
//            @Override
//            public Observable<String> call(Observable<Integer> observable) {
//                return observable
//                        .lift1()
//                        .lift2()
//                        .lift3()
//                        .lift4();
//            }
//        }
//        ...
//        Transformer liftAll = new LiftAllTransformer();
//        observable1.compose(liftAll).subscribe(subscriber1);
//        observable2.compose(liftAll).subscribe(subscriber2);
//        observable3.compose(liftAll).subscribe(subscriber3);
//        observable4.compose(liftAll).subscribe(subscriber4);
//
//
//        /**
//         * 像上面这样，使用 compose() 方法，Observable 可以利用传入的 Transformer 对象的 call 方法直接对自身进行处理，也就不必被包在方法的里面了。
//         *
//         * compose() 的原理比较简单，不附图喽。
//         */
    }
}
