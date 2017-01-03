package com.gavin.demo.features.usage.rxjava;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.demo.R;
import com.gavin.demo.databinding.FragmentTestBinding;
import com.gavin.demo.databinding.LayoutNullBinding;
import com.gavin.demo.features.style.bottombar.StartBrotherEvent;
import com.gavin.demo.test.RecyclerFragment;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 线程控制：Scheduler
 *
 * @author gavin.xiong 2016/11/10
 */
public class RxJavaFragment4 extends SupportFragment implements View.OnClickListener{

    LayoutNullBinding binding;

    public static RxJavaFragment4 newInstance() {
        return new RxJavaFragment4();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_null, container, false);
        initViews();
        return binding.getRoot();
    }

    private void initViews() {

    }

    @Override
    public void onClick(View view) {

    }

    /**
     * 1) Scheduler 的 API (二)
     *
     * 前面讲到了，可以利用 subscribeOn() 结合 observeOn() 来实现线程控制，
     *      让事件的产生和消费发生在不同的线程。可是在了解了 map() flatMap() 等变换方法后，
     *      有些好事的（其实就是当初刚接触 RxJava 时的我）就问了：能不能多切换几次线程？
     *
     * 答案是：能。因为 observeOn() 指定的是 Subscriber 的线程，
     *      而这个 Subscriber 并不是（严格说应该为『不一定是』，但这里不妨理解为『不是』）subscribe() 参数中的 Subscriber ，
     *      而是 observeOn() 执行时的当前 Observable 所对应的 Subscriber ，
     *      即它的直接下级 Subscriber 。
     *      换句话说，observeOn() 指定的是它之后的操作所在的线程。
     *      因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可。
     *      上代码：
     */
    private void code1() {
//        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.newThread())
//            .map(mapOperator) // 新线程，由 observeOn() 指定
//            .observeOn(Schedulers.io())
//            .map(mapOperator2) // IO 线程，由 observeOn() 指定
//            .observeOn(AndroidSchedulers.mainThread)
//            .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
    }

    /**
     * 3) 延伸：doOnSubscribe()
     *
     * 然而，虽然超过一个的 subscribeOn() 对事件处理的流程没有影响，但在流程之前却是可以利用的。
     *
     * 在前面讲 Subscriber 的时候，提到过 Subscriber 的 onStart() 可以用作流程开始前的初始化。
     *      然而 onStart() 由于在 subscribe() 发生时就被调用了，因此不能指定线程，而是只能执行在 subscribe() 被调用时的线程。
     *      这就导致如果 onStart() 中含有对线程有要求的代码（例如在界面上显示一个 ProgressBar，这必须在主线程执行），
     *      将会有线程非法的风险，因为有时你无法预测 subscribe() 将会在什么线程执行。
     *
     * 而与 Subscriber.onStart() 相对应的，有一个方法 Observable.doOnSubscribe() 。
     *      它和 Subscriber.onStart() 同样是在 subscribe() 调用后而且在事件发送前执行，但区别在于它可以指定线程。
     *      默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；
     *      而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
     */
    private void code2() {
//        Observable.create(onSubscribe)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        progressBar.setVisibility(View.VISIBLE); // 需要在主线程执行
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//
//        /**
//         * 如上，在 doOnSubscribe()的后面跟一个 subscribeOn() ，就能指定准备工作的线程了。
//         */
    }
}
