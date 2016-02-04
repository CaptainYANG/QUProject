//
// Created by LIU Ziqi on 31/01/16.
//

#ifndef QUPROJECT_MUSICTOONSET_H
#define QUPROJECT_MUSICTOONSET_H

#include <jni.h>
#include <stdlib.h>

#include "SuperpoweredDecoder.h"
#include "SuperpoweredAnalyzer.h"
#include "SuperpoweredSimple.h"

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_AndroidConfiguration.h>

class MusicToOnset {
public:

    JNIEXPORT jfloatArray JNICALL Java_com_mygdx_rhythm_AndroidLauncher_MusicToOnset(JNIEnv *javaEnvironment, jobject self, jstring data );

};


#endif //QUPROJECT_MUSICTOONSET_H
