#include <jni.h>
#include <opencv2/opencv.hpp>

using namespace cv;

extern "C"
JNIEXPORT void JNICALL
Java_com_example_facechk_MainActivity_ConvertRGBtoGray(JNIEnv *env, jobject thiz,
                                                       jlong mat_addr_input,
                                                       jlong mat_addr_result) {

    Mat &matInput = *(Mat *)mat_addr_input;
    Mat &matResult = *(Mat *)mat_addr_result;

    cvtColor(matInput, matResult, COLOR_RGBA2GRAY);
}extern "C"
JNIEXPORT void JNICALL
Java_com_example_facechk_ImageActivity_detectEdgeJNI(JNIEnv *env, jobject thiz, jlong input_image,
                                                     jlong output_image, jint th1, jint th2) {
    // TODO: implement detectEdgeJNI()
}extern "C"
JNIEXPORT void JNICALL
Java_com_example_facechk_CameraActivity_ConvertRGBtoGray(JNIEnv *env, jobject thiz,
                                                         jlong mat_addr_input,
                                                         jlong mat_addr_result) {
    // TODO: implement ConvertRGBtoGray()
}