//
// Created by LIU Ziqi on 31/01/16.
//

#include "MusicToOnset.h"


#include <jni.h>
#include <stdlib.h>

#include "SuperpoweredDecoder.h"
#include "SuperpoweredAnalyzer.h"
#include "SuperpoweredSimple.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_AndroidConfiguration.h>



static SuperpoweredDecoder *decoder;
static SuperpoweredOfflineAnalyzer *analyzer;


static float*  detectOnset(const char* path) {
    short int *pcmOutput;
    unsigned int *samples;
    unsigned int samplerate;
    int length;
    float *bpm;
    float *startMs;
    float *input;

    if (decoder->open(path)!= NULL){
        if (decoder->decode(pcmOutput, samples)==1) {
            unsigned int samplenum = decoder->durationSamples;
            SuperpoweredShortIntToFloat(pcmOutput, input, samplenum );

            analyzer->process(input, samplenum);

            analyzer->getresults(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                                 bpm, startMs, NULL);

            float  *results = new float[2];
            results[0] = *bpm;
            results[1] = *startMs;

            return results;

        };
        return NULL;
    }
    return NULL;
}

extern "C" {

JavaVM* gJavaVM = NULL;
jobject gJavaActivityClass;


JNIEXPORT jfloatArray JNICALL Java_com_mygdx_rhythm_AndroidLauncher_MusicToOnset
        (JNIEnv *javaEnvironment, jobject self, jstring data ){
    const char *nativeString = (javaEnvironment)->GetStringUTFChars(data, JNI_FALSE);
    jfloatArray result;

    result = (javaEnvironment) -> NewFloatArray(2);

    if(result==NULL){
        return NULL;
    }

    jfloat array1[2];
    float *rr = detectOnset(nativeString);

    array1[0] = rr[0];
    array1[1] = rr[1];

    (javaEnvironment)->ReleaseStringUTFChars(data, NULL);
    javaEnvironment -> SetFloatArrayRegion(result, 0, 2, array1);
    return  result;
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






