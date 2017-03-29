package com.roboy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import edu.cmu.sphinx.api.*;
//package edu.cmu.sphinx.demo.dialog;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

import net.beadsproject.beads.core.io.JavaSoundAudioIO;

public class Main
{

    public static void main(String[] args) throws Exception
    {
		//check what recording devices are plugged
        Mixer.Info[] mixerInfo;
        mixerInfo = AudioSystem.getMixerInfo();
        Line.Info targetDLInfo = new Line.Info(TargetDataLine.class);

        for(int i = 0; i < mixerInfo.length; i++)
        {
            Mixer currentMixer = AudioSystem.getMixer(mixerInfo[i]);
            if( currentMixer.isLineSupported(targetDLInfo) )
                System.out.println( mixerInfo[i].getName()+'\n' );
        }
		//! adjust number of needed recording device manually
        JavaSoundAudioIO aio = new JavaSoundAudioIO();
        aio.selectMixer(1);//in case of my machine that is 1
		//a var that can make the speaker say smth - just for conveniency of testing
        TTS speaker = new TTS();//described in the separate class, uses Free_Text-To-Speech tool
        Configuration configuration = new Configuration();
		//custumised phonetic model for Roboy recognition
        configuration.setDictionaryPath("src//edu//cmu//sphinx//models//4670//4670.dic");
        configuration.setLanguageModelPath("src//edu//cmu//sphinx//models//4670//4670.lm");
        //common english acoustic model
        configuration.setAcousticModelPath("src//edu//cmu//sphinx//models//en-us//en-us");
		//turning on the sphinx recognition tool
        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
        recognizer.startRecognition(true);
        short t = 0;
		//listening
        while (true)
        {
            String utterance = recognizer.getResult().getHypothesis();
            if (t == 0)
            {
                if (utterance.equals("TURN OFF") || utterance.equals("GOODBYE"))
                {
                    speaker.speak("goodbye.");
                    break;
                }
                else
                    ;
                System.out.println(utterance)  ;
            }
            else
                ;
        }
        recognizer.stopRecognition();
    }
}