//
// Created by LIU Ziqi on 31/01/16.
//



#include <jni.h>
#include <stdlib.h>

#include "SuperpoweredDecoder.h"
#include "SuperpoweredAnalyzer.h"
#include "SuperpoweredSimple.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_AndroidConfiguration.h>
#include <android/log.h>


float bpminit = 124.0f;
static SuperpoweredDecoder *decoder;
static SuperpoweredOfflineAnalyzer *analyzer;
static unsigned int *samples,samplerate;
static short int *pcmOutput;
static float *bpm, *startMs, *input;
static int length;



extern "C" {

JavaVM* gJavaVM = NULL;
jobject gJavaActivityClass;


JNIEXPORT jfloatArray JNICALL Java_com_mygdx_rhythm_AndroidLauncher_MusicToOnset
        (JNIEnv *javaEnvironment, jobject self, jstring apkPath ){

    decoder = new SuperpoweredDecoder();

    bpm = &bpminit;
    float init2 = 0.0f;
    //unsigned int intsmp = 0.0f;
    short int initpcmout[(6000000 * 4) + 16384] ;
    float initin = 0.0f;
    startMs = &init2;
    input = &initin;
    //pcmOutput = &initpcmout;
    //samples = &intsmp;
    samplerate = 0;
    length = 0;

    samplerate = decoder -> samplerate;
    analyzer = new SuperpoweredOfflineAnalyzer(samplerate);

    __android_log_print(ANDROID_LOG_INFO, "MusicToOnset", "sample rate", length);

    const char *path = javaEnvironment->GetStringUTFChars(apkPath, JNI_FALSE);
    jfloatArray jresult;

    jresult = (javaEnvironment) -> NewFloatArray(2);

    if(jresult==NULL){
        return NULL;
    }

    jfloat array1[2];
    float results[2] = {124,0};

    __android_log_print(ANDROID_LOG_INFO, "MusicToOnset", "open file", samplerate);
    __android_log_print(ANDROID_LOG_DEBUG, "MusicToOnset", "open file", results[0], results[1]);


    if (decoder->open(path) == NULL){
        unsigned int intsmp = decoder->durationSamples;

        samples = &intsmp;
        int sizeofpcm = intsmp*4+16384;
        short int pcmxx[sizeofpcm];
        pcmOutput = pcmxx;
        if (decoder->decode(pcmOutput, samples)!=2) {
            unsigned int samplenum = decoder->durationSamples;
            SuperpoweredShortIntToFloat(pcmOutput, input, samplenum );
            analyzer->process(input, samplenum);
            analyzer->getresults(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                                 bpm, startMs, NULL);
            results[0] = *bpm;
            results[1] = *startMs;
        } else{

            results[0] = *samples;
            results[1] = 2;
        };
    }else{

        results[0] = 3;
        results[1] = 4;
    }

    array1[0] = results[0];
    array1[1] = results[1];

    javaEnvironment->ReleaseStringUTFChars(apkPath, path);
    javaEnvironment -> SetFloatArrayRegion(jresult, 0, 2, array1);
    return  jresult;
};

jint JNI_OnLoad(JavaVM* aVm, void* aReserved)
{
    // cache java VM
    gJavaVM = aVm;

    JNIEnv* env;
    if (aVm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK)
    {
        return -1;
    }

    return JNI_VERSION_1_6;
}

}






