##饿汉式
在程序启动或单件模式类被加载的时候，单件模式实例就已经被创建。
```java
public class Singleton {
    private Singleton(){}  //防止被其他类对该类进行实例化，避免被调用
    private static Singleton singleton = new Singleton();
    public Singleton getInstance(){
        return singleton;
    }
}
```
##懒汉式
#####方式1
多线程下不安全，只适用于单线程
```java
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton;

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
```
#####方式2
在方式1上添加了同步锁，不推荐
```java
public class Singleton {
    private Singleton() {
    }

    private static Singleton singleton;

    public synchronized static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
```
#####方式3
双重检查，注意关键字volatile，不加可能发生指令重排序
```java
    singleton = new Singleton();
```
这个步骤在jvm里分为三步
  `* 1.在堆内存开辟内存空间。` 
  `* 2.在堆内存中实例化SingleTon里面的各个参数。`   
  `* 3.把对象指向堆内存空间。` 
 由于jvm存在乱序执行功能，可能3在2之前执行了，导致实例对象还未初始化完成就被另一个线程判断为非空进行使用，
 从而出现异常。这个就是著名的DCL失效问题。
 因此需要加关键字volatile禁止指令重排序
```java
public class Singleton {
  private Singleton() {}

    private static volatile Singleton singleton;

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class){
                //获得锁之后继续再一次判断，这样就安全了
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

##静态内部类
不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化
外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。即当SingleTon第一次被加载时，并不需要去加载SingleTonHoler，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,第一次调用getInstance()方法会导致虚拟机加载SingleTonHoler类
```java
public class SingleTon{
  private SingleTon(){}
 
  private static class SingleTonHoler{
     private static SingleTon INSTANCE = new SingleTon();
 }
 
  public static SingleTon getInstance(){
    return SingleTonHoler.INSTANCE;
  }
}
```