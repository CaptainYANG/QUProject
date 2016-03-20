//
// Created by LIU Ziqi on 31/01/16.
//



#include <jni.h>
#include <stdlib.h>

#include "SuperpoweredDecoder.h"
#include "SuperpoweredAnalyzer.h"
#include "SuperpoweredSimple.h"
#include "SuperpoweredAudioBuffers.h"
#include "SuperpoweredMixer.h"

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

    const char *path = javaEnvironment->GetStringUTFChars(apkPath, JNI_FALSE);

    jfloatArray jresult;

    jresult = (javaEnvironment) -> NewFloatArray(2);

    if(jresult == NULL){
        return NULL;
    }

    jfloat array1[2];
    float results[2] = {124,0};

    SuperpoweredDecoder *decoder = new SuperpoweredDecoder();
    const char *openError = decoder->open(path);
    if (openError) {
        //NSLog(@"%s", openError);
        delete decoder;
        return 0;
    }

    int sampleRate = decoder->samplerate;
    double durationSeconds = decoder->durationSeconds;
    SuperpoweredAudiobufferPool *bufferPool = new SuperpoweredAudiobufferPool(4, 1024 * 1024);             // Allow 1 MB max. memory for the buffer pool.

    SuperpoweredOfflineAnalyzer *analyzer = new SuperpoweredOfflineAnalyzer(sampleRate, 0, durationSeconds);

    short int *intBuffer = (short int *)malloc(decoder->samplesPerFrame * 2 * sizeof(short int) + 16384);

    int samplesMultiplier = 4; ////-->> Performance Tradeoff
    while (true) {
        // Decode one frame. samplesDecoded will be overwritten with the actual decoded number of samples.
        unsigned int samplesDecoded = decoder->samplesPerFrame * samplesMultiplier;
// NSLog(@"Samples per Frame for decoding->%d->>>", samplesDecoded);
// NSLog(@"Sample Position->%d->>>", decoder->samplePosition);
        if (decoder->decode(intBuffer, &samplesDecoded) != SUPERPOWEREDDECODER_OK) break;

        // Create an input buffer for the analyzer.
        SuperpoweredAudiobufferlistElement inputBuffer;

        bufferPool->createSuperpoweredAudiobufferlistElement(&inputBuffer, decoder->samplePosition, samplesDecoded + 8);

        // Convert the decoded PCM samples from 16-bit integer to 32-bit floating point.
        SuperpoweredShortIntToFloat(intBuffer, bufferPool->floatAudio(&inputBuffer), samplesDecoded);
        inputBuffer.endSample = samplesDecoded;             // <-- Important!
        analyzer->process(bufferPool->floatAudio(&inputBuffer), samplesDecoded);
    }

    //[self freeDecoder:decoder]; //this will free the allocated memory.
    delete bufferPool;
    free(intBuffer);

    unsigned char **averageWaveForm = (unsigned char **)malloc(150 * sizeof(unsigned char *));
    unsigned char **peakWaveForm = (unsigned char **)malloc(150 * sizeof(unsigned char *));
    char **overViewWaveForm = (char **)malloc(durationSeconds * sizeof(char *));

    int *keyIndex = (int *)malloc(sizeof(int));
    int *waveFormSize = (int *)malloc(sizeof(int));

    float *averageDecibel = (float *)malloc(sizeof(float));
    float *loudPartsAverageDecibel = (float *)malloc(sizeof(float));
    float *peakDecibel = (float *)malloc(sizeof(float));
    float *bpm = (float *)malloc(sizeof(float));
    float *beatGridStart = (float *)malloc(sizeof(float));

    analyzer->getresults(averageWaveForm, peakWaveForm, NULL, NULL, NULL, NULL, waveFormSize, overViewWaveForm, averageDecibel, loudPartsAverageDecibel, peakDecibel, bpm, beatGridStart, keyIndex);

    results[0] = *bpm;
    results[1] = *beatGridStart;

    array1[0] = results[0];
    array1[1] = results[1];
    javaEnvironment -> ReleaseStringUTFChars(apkPath, path);
    javaEnvironment -> SetFloatArrayRegion(jresult, 0, 2, array1);

    free(averageWaveForm);
    free(peakWaveForm);
    free(overViewWaveForm);
    free(keyIndex);
    free(waveFormSize);
    free(averageDecibel);
    free(loudPartsAverageDecibel);
    free(peakDecibel);
    free(bpm);
    free(beatGridStart);

    return  jresult;
};



/*
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


    jfloatArray jresult;

    jresult = (javaEnvironment) -> NewFloatArray(2);

    if(jresult == NULL){
        return NULL;
    }

    jfloat array1[2];
    float results[2] = {124,0};

    __android_log_print(ANDROID_LOG_INFO, "MusicToOnset", "open file", &path);
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

    javaEnvironment -> ReleaseStringUTFChars(apkPath, path);
    javaEnvironment -> SetFloatArrayRegion(jresult, 0, 2, array1);
    return  jresult;

    */
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








