#include <jni.h>
#include <android/log.h>
#include <string>

const char *TAG = "JniTag";

#define logD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__)
#define logI(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define logW(...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__)
#define logE(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
#define logF(...)  __android_log_print(ANDROID_LOG_FATAL,TAG,__VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_me_peace_javajni_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_me_peace_javajni_MainActivity_initCallback(JNIEnv *env, jobject thiz, jobject c) {
    jobject callback = env->NewGlobalRef(c);
    jclass classType = env->GetObjectClass(callback);
    jmethodID methodId = env->GetMethodID(classType, "exchange", "(Ljava/lang/String;)Ljava/lang/String;");
    jstring str = (jstring)env->CallObjectMethod(callback,methodId,env->NewStringUTF("Jni Message"));
    const char *pStr = env->GetStringUTFChars(str,NULL);
    logE("callback exchange result => %s",pStr);
    env->ReleaseStringUTFChars(str,pStr);
    env->DeleteGlobalRef(callback);
}