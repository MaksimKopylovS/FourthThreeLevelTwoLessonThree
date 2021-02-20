package max_sk.HomeWork.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Aspect
@Component
public class CountMethodProductService {
    private int addProdut;
    private int createOrder;
    private int decrimentCount;
    private int incrementCount;
    private int getProductList;
    private int deleteProductFromBasket;

    @Override
    public String toString(){
        return "addProdut = " + addProdut +"\n"+
                "createOrder = " + createOrder +"\n"+
                "decrimentCount = " + decrimentCount +"\n"+
                "incrementCount = " + incrementCount +"\n"+
                "getProductList = " + getProductList +"\n"+
                "deleteProductFromBasket = " + deleteProductFromBasket;
    }

    @PostConstruct
    public void init() {
        addProdut = 0;
        createOrder = 0;
        decrimentCount = 0;
        incrementCount = 0;
        getProductList = 0;
        deleteProductFromBasket = 0;
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @Before("execution(public * max_sk.HomeWork.services.BasketProductsService.*(..))")
    public void countMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        if (methodName == "addProdut") {
            addProdut++;
        }
        if (methodName == "createOrder") {
            createOrder++;
        }
        if (methodName == "decrimentCount") {
            decrimentCount++;
        }
        if (methodName == "incrementCount") {
            incrementCount++;
        }
        if (methodName == "getProductList") {
            getProductList++;
        }
        if (methodName == "deleteProductFromBasket") {
            deleteProductFromBasket++;
        }
        System.out.println(toString());
    }
}
