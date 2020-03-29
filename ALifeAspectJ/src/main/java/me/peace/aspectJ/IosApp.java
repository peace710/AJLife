package me.peace.aspectJ;

public class IosApp {
    private static final String TAG = IosApp.class.getSimpleName();

    private int id;

    private String name = "";

    public IosApp() {
    }

    /**
     *     public int getId() {
     *         JoinPoint var1 = Factory.makeJP(ajc$tjp_0, this, (Object)null);
     *
     *         String var10000;
     *         try {
     *             SetGetAspect.aspectOf().beforeGet(var1);
     *             var10000 = TAG;
     *         } catch (Throwable var7) {
     *             SetGetAspect.aspectOf().afterGet(var1);
     *             throw var7;
     *         }
     *
     *         SetGetAspect.aspectOf().afterGet(var1);
     *         LogUtils.i(var10000, "getId() called");
     *         IosApp var4 = this;
     *         JoinPoint var3 = Factory.makeJP(ajc$tjp_1, this, this);
     *
     *         int var8;
     *         try {
     *             SetGetAspect.aspectOf().beforeGet(var3);
     *             var8 = var4.id;
     *         } catch (Throwable var6) {
     *             SetGetAspect.aspectOf().afterGet(var3);
     *             throw var6;
     *         }
     *
     *         SetGetAspect.aspectOf().afterGet(var3);
     *         return var8;
     *     }
     */

    public int getId() {
        LogUtils.i(TAG, "getId() called");
        return id;
    }

    /**
     *
     *     public void setId(int id) {
     *         JoinPoint var2 = Factory.makeJP(ajc$tjp_2, this, (Object)null);
     *
     *         String var10000;
     *         try {
     *             SetGetAspect.aspectOf().beforeGet(var2);
     *             var10000 = TAG;
     *         } catch (Throwable var9) {
     *             SetGetAspect.aspectOf().afterGet(var2);
     *             throw var9;
     *         }
     *
     *         SetGetAspect.aspectOf().afterGet(var2);
     *
     *         //setId之前会先读值
     *         LogUtils.i(var10000, "setId() called with: id = [" + id + "]");
     *         int var5 = id;
     *         IosApp var6 = this;
     *         JoinPoint var4 = Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(id));
     *
     *         try {
     *             SetGetAspect.aspectOf().beforeSet(var4);
     *             var6.id = var5;
     *         } catch (Throwable var8) {
     *             SetGetAspect.aspectOf().afterSet(var4);
     *             throw var8;
     *         }
     *
     *         SetGetAspect.aspectOf().afterSet(var4);
     *     }
     */

    public void setId(int id) {
        LogUtils.i(TAG, "setId() called with: id = [" + id + "]");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
