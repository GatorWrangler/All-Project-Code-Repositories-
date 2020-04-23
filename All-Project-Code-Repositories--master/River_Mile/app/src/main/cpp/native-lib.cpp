#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_brant_river_1mile_HomePage_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
