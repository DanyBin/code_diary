
## 《Java并发编程实战》 笔记


##### 2.1 线程安全性
线程安全性，核心是正确性。 正确性的定义"所见即所知"。

线程安全性的定义: 当多个线程访问某个类，这个类始终都能表现正确的行为(结果)。

无状态对象一定是线程安全(多个线程不会相互影响)

- 活跃性: 某个操作无法继续执行下去，就会发生活跃性问题(例如: 无意造成的死循环/死锁等场景)

##### 2.2 原子性

高级语言中 ++count 看上去是一个操作，但这个操作并非原子的。

实际上包含三个独立的操作: "读取-修改-写入"的操作序列，并且结果依赖于之前的状态

- 竞态条件(有序性) 定义: 由于不可控的执行时许而出现不正确的结果。

- 复合操作: 将 ++ count["读取-修改-写入"] 一组必须以原子方式执行的操作以确保线程安全性。



##### 2.3 加锁机制

加锁机制，在多个线程之间操作，都要保证状态的一致性。(保证不变性条件)

- 要保证状态的一致性，需要在单个原子操作中更新所有相关的状态变量

- 内置锁: 同步代码块（Synchronized）. 作为锁的对象引用(Java对象);由这个锁保护代码块;
    
    - 内置锁是一种互斥锁，最多只有一个线程能持有该锁

- 重入: 当某个线程持有该对象锁，再次请求该对象锁是可以成功的。
   
    - Java中获取对象锁的单位是「线程」不是以「调用」为单位
     
    - 参考: https://www.cnblogs.com/cielosun/p/6684775.html

##### 2.4 用锁保护状态

锁能够使多个线程以此已独占的方式访问对象，并不会并发访问。

- 对于共享与可变的变量都应该由一个锁来保护。例如: 线程安全类Vector,使用对象锁进行同步，不会发生并发访问

- 虽然单个sychronized方法可以确保单个操作的原子性，但对于多个操作合并的复合操作，还需要额外的加锁机制。

##### 2.5 活跃性与性能

- 使用同步代码块，要"足够短" 并且要权衡 安全性、简单行和性能。

- 执行时间过长或无法快速完成的操作(例如: I/O)一定不要持有锁，否则会带来活跃性和性能问题。

##### 3.1 可见性

定义: 当一个线程修改了对象状态后,其他线程能够看到发生的变化。但如果没有同步，这种情况无法实现。所以确保多个线程之间对内存写入操作的可见性，必须使用同步机制。

- 加锁不仅局限于互斥行为，还包括内存可见性. 为了确保所有线程都能看到共享变量的最新值，所有执行读操作或写操作的线程都必须在同一个锁上同步。

- volatile变量，用于确保将变量的更新操作通知其他线程
    
   - 场景上: 作为某个操作完成、发生中断的状态标志。表示状态信息。
   
   - 使用要求: https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
   
- 加锁机制即可以确保可见性又可以确保原子性，而volatile 变量只能确保 可见性。


##### 3.2 发布与逸出

「发布(Publish)」对象定义: 当前对象能够在当前作用域之外的代码中使用。（例如:将对象的引用保存到一个公共静态变量中，以便任何类和线程都能看见该对象）
```java
public class Test{
   public static Set<Test> set;
   
   public void initialize(){
       set = new HashMap<Test>();
   }
}

```
「逸出(Escape)」:某个不应该发布的对象被发布时。

- 特殊示例： 即this引用在构造函数中逸出。常见错误是:在构造函数中启动一个线程

- 构造函数中注册一个事件监听器或启动线程，可以使用一个私有的构造函数和一个公共的工厂方法,从而避免不正确的构造

##### 3.3 线程封闭

定义: 在单线程内访问数据，不需要同步，也就是线程封闭(例如: JDBC连接池)

- 栈封闭: 将所有类型或对象都声明成局部变量，始终封闭在线程内。

- ThreadLocal类: 用于同一个线程内的方法 要共享某些变量或状态，提供线程内的局部变量。
 
  - Thread对象维护一个key以ThreadLocal的Map. 注意使用后要finally remove,防止内存泄露
  - 参考:https://juejin.im/post/5e5e1f876fb9a07ce31eefbc 

##### 3.4 不变性

定义: 某个对象在被创建后其状态就不能被修改，就是不可变对象

 - 不可变对象只有一种状态，并且该状态由「构造函数来控制」。
 
 - Final关键字，用于构造不可变对象。
 
 - 对于获取和更新多个相关变量时出现的竞争条件问题，可以将所有相关变量保存到一个不可变对象中来消除.
 
 
##### 3.4 安全发布

场景: 某些情况下，我们希望在多个线程间共享对象，此时必须确保安全的进行共享对象。

- 共享对象: 也就是要安全发布一个对象，对象的引用以及对象的状态必须同时对其他线程可见。常用模式有
    
    - 在静态初始化函数中 初时一个对象的引用。 （单例对象模式）
    
    - 将对象的引用保存到volatile 类型或者AtomicReferance对象中。(通过 可见性,更新时对其他线程可见)
    
    - 将对象的引用保存到某个正确构造对象的final类型域中。 (不可变对象的构建)
    
    - 将对象的引用保存到一个由锁保护的域中。(通过锁，来保证可见性。Vector/Hashtable/ConcurrentMap)
    
- 可变对象: 在对象发布后，依旧可以修改状态。每次修改后，都需要使用同步来保证后续的可见性
    
    - 该对象必须是线程安全
    
    - 由某锁保护起来
    
- 在并发程序中使用和共享对象的常用策略
    
    - 线程封闭: 对象只能由一个线程持有，对象被封闭在该线程内，并且只能由这个线程修改。
    
    - 只读共享: 在没有额外同步的情况下，共享对象只可读，不可以修改。共享的只读对象包括不可变对象
    
    - 线程安全共享: 对象在其内部实现同步，因此多个线程可以通过对象的公共接口进行访问无需同步。
    
    - 保护对象: 被保护的对象只能通过持有锁访问。
    

    
##### 4.1 设计线程安全的类
    
基于要素

- 找出对象状态的所有变量
   
   - 对象状态首先看 对象的域。如果是变量都是基本类型，那么这些域构成了对象的全部状态。如果是引用其他对象，那么该对象包含被引用对象的域
   
- 找出约束对象变量的不变性条件
    
   - 不变性条件是状态进行推断。对象与变量都有一个状态空间，即所有可能的取值。状态空间越小，越容易判断线程的状态。
   - 后验条件: 该操作必须是复合操作，下一个状态依赖当前状态。例如 count++ == 18 
   
- 建立对象状态的并发访问管理策略
    
   - 是如何在不违背对象不变条件或后验条件的情况下对其状态的访问操作进行「协同」
   
 
##### 4.2 实例封闭

定义: 当一个对象被封闭到另一个对象中时，能够访问被封装对象的所有代码路径都是已知的。

将数据封装在对象的内部，可以将数据的访问限制在对象的方法上，从而更容易确保线程在访问数据时总能持有正确的锁。

##### 4.3 线程安全性的委托

定义: 当前对象的线程安全性委托给其他线程对象来进行保证。

委托失效时: 当某个类含有复合操作，仅靠委托不能保证线程安全性。例如
```java
import  java.util.concurrent.atomic.AtomicInteger;
   public class NumberRage{
        private final AtomicInteger lower = new AtomicInteger(1);
        
   }
```

##### 5.1 同步容器类

包括:Vector 和HashTable 通过synchronized的方法创建。
实现的方式: 将它们的状态封装起来，并对每个公有方法都进行同步，使得每次只有一个线程能够访问容器的状态

存在的问题: 

- 复合操作(跌打、next、条件运算)在并发修改容器时，会出现不一致的问题

##### 5.2 并发容器

通过并发容器来代替同步容器，极大提升伸缩性并降低风险

- ConcurrentHashMap 也是基于散列的Map,使用一种粒度更细的加锁机制实现更大的共享「分段锁」。 
    
    - ConcurrentHashMap的迭代器具有弱一致性，可以容忍并发修改。也导致size 与isEmpty 返回有可能是过期的值，故是近似值而不是精确值
    
- CopyOnWriteArrayList 用来代替List,采用「写入时复制(Copy on writer)」实现容器安全

    - 每次修改时，都会创建并重新发布一个新的容器副本，从而实现可变性。
    - 每次修改容器，都会复制底层数组，需要一定的性能开销。适用于「迭代操作远远多于修改操作时」



##### 5.3 阻塞队列和生产者 - 消费模式

当数据生成时，生产者将数据放入队列，而当消费者准备处理数据时,将从队列中获取数据。

常见例子-线程池与工作队列组合，在Executor任务执行框架中体现该模式

阻塞队列

- take操作会一直阻塞直到有可用的数据。

- offer 如果数据不能被添加到队列中，将返回一个失败状态。对于负荷过载的情况，例如: 将多余的工作项序列化并写入磁盘，减少生产者线程的数量/通过某种方式抑制生产者线程。

有界队列是一种强大的资源管理工具: 它们能抑制并防止产生过多的工作项，使应用程序在负载过载的情况下变得更加健壮

Deque是一个双端对列，实现在队列头和队列尾的高效插入与移除，具体实现包括ArrayDeque 和LinkedBlockingDeque

- 适用 工作密取（Work Stealing):如果一个消费者完成自己双端队列中的全部工作，那么它可以从其他消费者双端队列的末尾获取工作。

##### 5.4 阻塞方法和中断方法

线程可能会阻塞或暂停执行，原因多种：等待I/O操作结束，等待获取一个锁，等待从Thread.sleep方法种醒来，等着另一个线程的计算结果。

当线程阻塞时，通常被挂起并处于某种阻塞状态(Blocked,Waiting 或 Timed_Waiting)。

InterruptedException异常表示该方法是一个阻塞方法。

Thread提供interrupt方法，用中断线程或者查询线程是否已经被中断。

处理中断

-  传递InterruptException，将异常抛给方法的调用者。

- 恢复中断，不能抛出InterruptException时，必须捕获InterruptExecption并在当前线程方法种恢复中断状态，这样调用栈种更高层的代码将看到引发一个中断
```java
    public class TaksRunnable implements Runnable{
        java.util.concurrent.BlockingDeque<javafx.concurrent.Task> queue;
        
        public void run(){
            try{
                proccessTask(queue.take());
            }catch (InterruptedException e) {
                //恢复被中断的状态
                Thread.currentThread().interrupt();
            }
        }
    }
```


##### 5.5 同步工具类

阻塞队列是一种特殊的类: 不仅支持保存对象的容器，还能协调生产者与消费者等线程之间的控制流。

同步工具可以是任何一个对象， 能够根据自身的状态来协调线程的控制流。

主要包含: 阻塞队列、信号量(Semaphore)、栅栏(Barrier)、闭锁(Latch)等。
 
同步工具类使用特定的结构化属性，封装一些状态，这些状态来进行控制线程。

- 闭锁: 可以延迟线程的进度直到终止状态 (闭锁是一次性对象，一旦进入终止状态，就不能被重置)

    - 延迟操作-服务/资源依赖上游多个服务/资源，等待所有上游就绪后，在启动当前服务/资源
    
    - 等待所有操作者就绪后在执行。(多人游戏)
    
    - CountDownLatch 一种灵活的闭锁实现。countDown 表示一个事件已经发生，await方法等待计数器到达零。
   
    - FutureTask 也是一种闭锁。
    
    - 信号量(Counting Semaphore) 用来控制同时访问某个特定资源的操作数量，或者同时执行某个指定操作的数量。(实现某种资源池/对容器施加边界)
    
        - Semaphore中管理着一组虚拟的许可(permit),执行操作时首先获取许可，并在使用后释放许可。
        
        - 不具备不可重入的加锁语义: 谁拥有这个唯一的许可，谁就拥有互斥锁。
    
    - 栅栏(Barrier) 类似于闭锁，能阻塞一组线程直到某个事件发生。
    
        - 栅栏于闭锁的区别: 所有线程必须同时到达栅栏位置，才能执行。闭锁用于等待事件，而栅栏用于等待其他线程。
        
        - CyclicBarrier 使一定数量的参与方反复在栅栏位置汇集，在并行迭代算法中非常有用:将一个问题拆分成一系列相对独立的子问题。
        
   
