import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class GenerateProxy {
    public static void main(String[] args) throws Exception{
        AccountDao accountDao = (AccountDao) generate(AccountDao.class);
        accountDao.insert("1","1");
    }

    /**
     * generate proxy
     * @param daoInterface the interface to implement and create instance
     * @return the instance of interface
     */
    public static Object generate(Class daoInterface) {
        //get class pool
        ClassPool pool = ClassPool.getDefault();
        //get implement class
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Impl");
        //get interface
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        //implement the interface
        ctClass.addInterface(ctInterface);

        //implement the abstract methods
        //get the methods
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            Parameter[] parameters = method.getParameters();
            try {
                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName() + " ");
                methodCode.append(method.getName());
                methodCode.append("(");
                for (int i = 0; i < parameters.length; i++) {
                    methodCode.append(parameters[i].getType().getName() + " arg" + i);
                    if (i != parameters.length - 1) {
                        methodCode.append(",");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                methodCode.append("System.out.println(123);");
                methodCode.append("}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }
        });

        //create instance
        Object obj = null;
        try {
            Class<?> aClass = ctClass.toClass();
            obj = aClass.newInstance();
        } catch (CannotCompileException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Object easyGenerate(Class daoInterface) {
        ClassPool pool = ClassPool.getDefault();
        //get implement class
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Impl");
        //get interface
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        //implement the interface
        ctClass.addInterface(ctInterface);

        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            try {
                CtMethod ctMethod = null;
                String methodCode = "public void insert(java.lang.String arg0,java.lang.String arg1){System.out.println(345);}";
                ctMethod = CtMethod.make(methodCode, ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }
        });


        Object obj = null;
        try {
            Class<?> aClass = ctClass.toClass();
            obj = aClass.newInstance();
        } catch (CannotCompileException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
