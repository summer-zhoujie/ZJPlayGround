package com.example.playground.Rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 为接收基于推送的通知提供一个语法定义
 *
 * 当一个 Observer 实例通过 ObservableSource.subscribe 被一个 ObservableSource 实例订阅的时候,会调用onSubscribe
 * 方法,并且返回一个 Disposable 实例, 利用这个 Disposable 实例可以在任意时间中断 squeuece(?事件执行队列), 然后
 * ObservableSource 实例会调用 Observer.onNext 方法任意次数. 在一个正常的行为逻辑中, ObservableSource 会调用精确调用一次
 * Observer.onComplete 或者 Observer.onError
 *
 * 对 Observer 中方法的调用必须是线性的(线程同步), 意思就是, 你不能够在并行的多线程中调用, 另外, 调用形式必须遵循以下协议:
 * onSubscribe onNext* (onError | onComplete)?
 *
 * 一个 Observer 实例订阅多个 ObservableSource 是不被推荐的, 假设这种重用发生的话, 使用者需要为 Observer 的方法适配多线程并行访问,
 * 确保其在多线程下保持合适的逻辑
 *
 * 调用 onSubscribe onNext onError 传入参数为 null 是不被允许的
 *
 * onXX 类型的方法的实现应该避免抛出 RuntimeException ,但有种情况除外, 响应式说明书中提到的规则2.13, 规则如下:
 * 调用 onSubscribe, onNext, onError 或者 onComplete 方法时必须正常返回, 有一个例外, 当方法的参数传入 null 的时候,
 * 必须抛出 NullPointerException 给调用者, 其他情况下, Subscriber 唯一合法的通知错误方式是取消它的 Subscription, 在
 * 以上规则被违反的情况下, 任何关联的 Subscription 都必须被认为是取消掉的, 并且调用者必须以一个适合当前运行环境的方式上报错误
 * 情况
 *
 * 如果 onXX 方法的参数是 null, 那么它可以抛出 NullPointerException, 请注意, Rxjava 防止 null 参数进入执行流中, 因此
 * 通常不会对标准源或者中间操作的参数进行判空.
 *
 * 违反规则 2.13 会导致未定义的流错误, 通常的, 将会发生以下情况:
 * ~ 一个上流的操作报错, 并进入onError调用
 * ~ 如果流程是同步的, ObservableSource.subscribe(Observer) 这个函数会抛出异常,来代替通常返回的正常结果
 * ~ 如果流程是异步的, 异常会传播到为代码提供异步执行条件的组件(比如: Scheduler 或者 Executor), 并且路由这个异常到Rxjava全局的
 * RxJavaPlugins.onError(Throwable) 或者当前线程的异常处理类 Thread.UncaughtExceptionHandler.uncaughtException(Thread, Throwable)
 *
 * 从 Observable 的观点来看, Observer 是最后的消费者, 因此, 处理异常并向下传播是 Observer 的职责, 意思就是,所有在onXX方法中不可靠的代码,需要被
 * 'try-catch'等等措施包裹起来, 尤其是在 onError(Throwable) 或者 onComplete(), 并且在这其中处理(比如, 通过Log打印或者给用户弹出一个错误信息弹窗)
 * 然而, 这个错误可能会被从 onNext(Object) 抛出, 规则 2.13 授权实现类调用 Disposable.dispose()  并且抛出这个异常用一种适配目标环境的方式, 例如,
 * 在同一个Observer实例中调用 onError(Throwable)
 *
 * 如果, 因为某些原因, Observer 实例不能遵循规则 2.13 , Observable.safeSubscribe(Observer) 将会采取必要的安全保护包裹这个错误,并且路由这个错误
 * 从 onNext 抛出到 onError,然后从 onError 和 onComplete 经由RxJavaPlugins.onError(Throwable)到全局错误处理
 *
 */
public interface ObserverZJ<T> {


    /**
     * 给 Observer 传入 Disposable 实例, 该实例含有取消与 Observable 的连接方法(包含同步的,异步的)
     * @param d Disposable 实例, 它的 dispose 方法可以在任意时间取消 Observer 与 Observable 的连接
     * @since 2.0
     */
    void onSubscribe(@NonNull Disposable d);

    /**
     * 给 Observer 提供一个新值去订阅
     * Observable 实例可能会调用这个方法 0 或者更多次
     * Observable 实例如果执行过 onComplete 或者 onError ,则这个方法就不会被调用了
     * @param t 被 Observable 实例发射的值
     */
    void onNext(@NonNull T t);

    /**
     * 通知 Observer 实例 Observable 实例已经遇到一个错误情况
     * 如果 Observable 实例调用了该方法, 那么之后就不会调用 onNext 或者 onComplete 方法
     * @param e Observable 实例遇到的异常
     */
    void onError(@NonNull Throwable e);

    /**
     * 通知 Observer 实例, Observable 实例已经结束了发送基于-推送的通知
     * Observable 实例如果调用 onError 之后便不会再调用此方法
     */
    void onComplete();
}
